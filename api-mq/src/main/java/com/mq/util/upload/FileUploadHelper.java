package com.mq.util.upload;

import com.mq.util.exception.BlException;
import com.mq.util.exception.ExceptionStatus;
import com.mq.util.idGenerate.IdGenerator;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUploadHelper extends FileBase {
    private static final FileType[] SUPPORT_IMG_TYPE;
    private static final int BUFFER = 1024;
    private static ThreadPoolExecutor executor;

    public FileUploadHelper() {
    }

    public static void moveFolderToSpecify(String rootPath, String specifyPath, String fileId) throws IOException {
        if (StringUtils.isEmpty(rootPath)) {
            throw new IllegalStateException("Root Path can't be empty !");
        } else if (StringUtils.isEmpty(specifyPath)) {
            throw new IllegalStateException("Specify Path can't be empty !");
        } else if (StringUtils.isEmpty(fileId)) {
            throw new IllegalStateException("File Id can't be empty !");
        } else {
            File sourceFolder = new File(rootPath, generateFilePath(fileId));
            if (!sourceFolder.exists()) {
                throw new IllegalStateException("The Source Folder is not existed");
            } else {
                File specifyFolder = new File(specifyPath);
                if (!specifyFolder.exists()) {
                    specifyFolder.mkdirs();
                }

                File[] files = sourceFolder.listFiles();
                File[] arr$ = files;
                int len$ = files.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    File file = arr$[i$];
                    if (file.isDirectory()) {
                        System.out.println("file is a directory");
                    } else {
                        File dstFile = getUploadFilePath(specifyFolder.getAbsolutePath(), fileId);
                        moveFile(file.getAbsolutePath(), dstFile.getAbsolutePath());
                    }
                }

                sourceFolder.delete();
            }
        }
    }

    public static void deletePersistentFile(String rootPath, String fileId) throws IOException {
        File filePath = getUploadFilePath(rootPath, fileId);
        if (filePath.exists()) {
            if (filePath.isFile()) {
                filePath.delete();
            } else if (filePath.isDirectory()) {
                File[] files = filePath.listFiles();
                File[] arr$ = files;
                int len$ = files.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    File file = arr$[i$];
                    file.delete();
                }
            }

            filePath.delete();
        } else {
            System.out.println("delete file is not a exist");
        }

    }

    public static void copyFolderToSpecify(File srcPath, File dstPath) throws IOException {
        if (!srcPath.exists()) {
            throw new IOException("The source directory is not existed");
        } else {
            if (!dstPath.exists()) {
                dstPath.mkdirs();
            }

            File[] files = srcPath.listFiles();
            File[] arr$ = files;
            int len$ = files.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File file = arr$[i$];
                File dstFile = new File(dstPath, file.getName());
                moveFile(file.getAbsolutePath(), dstFile.getAbsolutePath());
            }

        }
    }

    public static File getUploadFilePath(String rootPath, String fileId) throws IOException {
        if (!StringUtils.isEmpty(rootPath) && !StringUtils.isEmpty(fileId)) {
            File filePath = new File(rootPath);
            if (StringUtils.isNotEmpty(fileId)) {
                filePath = new File(rootPath, generateFilePath(fileId));
            }

            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            return filePath;
        } else {
            throw new IllegalStateException("Root path and File ID can't be empty !");
        }
    }

    public static void persistentUploadFileSync(String rootPath, String fileId, FileType fileType, InputStream is, List<PhotoDimension> dimensionList) throws IOException {
        if (fileType == null) {
            throw new IllegalStateException("FileType can't be empty !");
        } else {
            File filePath = getUploadFilePath(rootPath, fileId);
            String fileName = fileId + fileType.getValue();
            File file = new File(filePath, fileName);
            if (file.exists()) {
                file.delete();
            }

            write2File(is, file);
            fileType = FileTypeValidationHelper.getFileType(file);
            if (fileType.ordinal() == FileType.NO_TYPE.ordinal()) {
                filePath.delete();
                throw new BlException(ExceptionStatus.SERVER_ERROR);
            } else {
                if (isSupportImageFile(fileType) && dimensionList != null && dimensionList.size() > 0) {
                    Iterator i$ = dimensionList.iterator();

                    while(i$.hasNext()) {
                        PhotoDimension dimension = (PhotoDimension)i$.next();
                        File smallThumbFile = new File(filePath, getThumbnailFileName(fileName, dimension.getValue()));
                        ThumbHelper.scalePhoto(smallThumbFile, file, dimension.getValue(), 1, false);
                    }
                }

            }
        }
    }

    public static void persistentUploadFileSync(String rootPath, String fileId, FileType fileType, InputStream is, PhotoDimension... photoDimensions) throws IOException {
        persistentUploadFileSync(rootPath, fileId, fileType, is, Arrays.asList(photoDimensions));
    }

    public static void persistentUploadFileSync(String rootPath, String fileId, FileType fileType, InputStream is) throws IOException {
        persistentUploadFileSync(rootPath, fileId, fileType, is, (List)(new ArrayList()));
    }

    public static void persistentUploadFile(String rootPath, String fileId, FileType fileType, InputStream is, List<PhotoDimension> dimensionList) throws IOException {
        if (fileType == null) {
            throw new IllegalStateException("FileType can't be empty !");
        } else {
            final File filePath = getUploadFilePath(rootPath, fileId);
            final String fileName = fileId + fileType.getValue();
            final File file = new File(filePath, fileName);
            if (file.exists()) {
                file.delete();
            }

            write2File(is, file);
            fileType = FileTypeValidationHelper.getFileType(file);
            if (fileType.ordinal() == FileType.NO_TYPE.ordinal()) {
                filePath.delete();
                throw new BlException(ExceptionStatus.SERVER_ERROR);
            } else {
                if (isSupportImageFile(fileType) && dimensionList != null && dimensionList.size() > 0) {
                    Iterator i$ = dimensionList.iterator();

                    while(i$.hasNext()) {
                        final PhotoDimension dimension = (PhotoDimension)i$.next();
                        executor.execute(new Runnable() {
                            public void run() {
                                File smallThumbFile = new File(filePath, getThumbnailFileName(fileName, dimension.getValue()));

                                try {
                                    ThumbHelper.scalePhoto(smallThumbFile, file, dimension.getValue(), 1, false);
                                } catch (IOException var3) {
                                    var3.printStackTrace();
                                }

                                System.out.println("线程池中线程数目：" + FileUploadHelper.executor.getPoolSize() + "，队列中等待执行的任务数目：" + FileUploadHelper.executor.getQueue().size() + "，已执行完的任务数目：" + FileUploadHelper.executor.getCompletedTaskCount());
                            }
                        });
                    }
                }

            }
        }
    }

    public static void persistentUploadFile(String rootPath, String fileId, FileType fileType, InputStream is, PhotoDimension... photoDimensions) throws IOException {
        persistentUploadFile(rootPath, fileId, fileType, is, Arrays.asList(photoDimensions));
    }

    public static void persistentUploadFile(String rootPath, String fileId, FileType fileType, InputStream is) throws IOException {
        persistentUploadFile(rootPath, fileId, fileType, is, (List)(new ArrayList()));
    }

    public static Map<String, String> persistentUploadZipFile(String rootPath, String fileId, InputStream is) throws IOException {
        String filePath = getDecompressFilePath(rootPath, fileId, is);
        return renameAndGenerateThumbnailFiles(filePath);
    }

    public static void persistentUploadZipFileNotRename(String rootPath, String fileId, InputStream is) throws IOException {
        String filePath = getDecompressFilePath(rootPath, fileId, is);
        readExtractingFiles(filePath);
    }

    private static String getDecompressFilePath(String rootPath, String fileId, InputStream is) throws IOException {
        if (!StringUtils.isEmpty(rootPath) && !StringUtils.isEmpty(fileId)) {
            File filePath = new File(rootPath, generateFilePath(fileId));
            filePath = new File(filePath, fileId);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            String zipFileName = fileId + FileType.ZIP.getValue();
            File file = new File(filePath, zipFileName);
            write2File(is, file);
            if (!FileTypeValidationHelper.isValidZipFile(file)) {
                deleteFile(filePath.getAbsolutePath());
                throw new BlException(ExceptionStatus.SERVER_ERROR);
            } else {
                decompress(file, filePath);
                deleteFile(file.getAbsolutePath());
                return filePath.getAbsolutePath();
            }
        } else {
            throw new IllegalStateException("Root path and file id can't be empty !");
        }
    }

    private static void decompress(File srcFile, File destFile) throws IOException {
        CheckedInputStream cis = new CheckedInputStream(new FileInputStream(srcFile), new CRC32());

        ZipInputStream zis;
        ZipEntry entry;
        for(zis = new ZipInputStream(cis); (entry = zis.getNextEntry()) != null; zis.closeEntry()) {
            File dirFile = new File(destFile, entry.getName());
            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                decompressFile(dirFile, zis);
            }
        }

        zis.close();
    }

    private static void decompressFile(File destFile, ZipInputStream zis) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        byte[] data = new byte[1024];

        int count;
        while((count = zis.read(data, 0, 1024)) != -1) {
            bos.write(data, 0, count);
        }

        bos.close();
    }

    private static boolean deleteFile(String deletePath) throws IOException {
        File file = new File(deletePath);
        if (file.isDirectory()) {
            File[] delFiles = file.listFiles();
            File[] arr$ = delFiles;
            int len$ = delFiles.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File delFile = arr$[i$];
                if (delFile.isDirectory()) {
                    deleteFile(delFile.getAbsolutePath());
                } else {
                    delFile.delete();
                }
            }

            file.delete();
        } else {
            file.delete();
        }

        return true;
    }

    private static boolean readExtractingFiles(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.isDirectory()) {
            String[] fileList = file.list();

            for(int i = 0; i < fileList.length; ++i) {
                File readFile = new File(filePath, fileList[i]);
                if (!readFile.isDirectory()) {
                    FileType fileType = getFileTypeOfFile(readFile);
                    if (isSupportImageFile(fileType)) {
                        File smallThumbFile = new File(filePath, getThumbnailFileName(readFile.getName(), "64X64"));
                        ThumbHelper.scalePhoto(smallThumbFile, readFile, "64X64", 1, true);
                    }
                }
            }
        }

        return true;
    }

    private static Map<String, String> renameAndGenerateThumbnailFiles(String fileFolder) throws IOException {
        Map<String, String> fileIdMap = null;
        File file = new File(fileFolder);
        if (file.isDirectory()) {
            fileIdMap = new HashMap();
            String[] fileList = file.list();
            String[] arr$ = fileList;
            int len$ = fileList.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String originalFileName = arr$[i$];
                File readFile = new File(fileFolder, originalFileName);
                if (!readFile.isDirectory()) {
                    FileType fileType = getFileTypeOfFile(readFile);
                    String id = IdGenerator.getID();
                    String newFileName = id + fileType.getValue();
                    File newFilePath = new File(file.getParentFile(), id);
                    if (!newFilePath.exists()) {
                        newFilePath.mkdirs();
                    }

                    File newFile = new File(newFilePath, newFileName);
                    readFile.renameTo(newFile);
                    if (isSupportImageFile(fileType)) {
                        List<PhotoDimension> photoDimensions = new ArrayList();
                        photoDimensions.add(PhotoDimension.THUMB_64X64);
                        if (photoDimensions != null && photoDimensions.size() > 0) {
                            Iterator i = photoDimensions.iterator();

                            while(i.hasNext()) {
                                PhotoDimension dimension = (PhotoDimension)i.next();
                                File smallThumbFile = new File(newFilePath, getThumbnailFileName(newFileName, dimension.getValue()));
                                ThumbHelper.scalePhoto(smallThumbFile, file, dimension.getValue(), 1, true);
                            }
                        }
                    }

                    fileIdMap.put(getFileNameExcludeExtensionType(originalFileName), id);
                }
            }
        }

        deleteFile(file.getAbsolutePath());
        return fileIdMap;
    }

    private static FileType getFileTypeOfFile(File readFile) {
        FileType fileType = FileTypeValidationHelper.getFileType(readFile);
        if (fileType.ordinal() == FileType.NO_TYPE.ordinal()) {
            readFile.getParentFile().delete();
            throw new BlException(ExceptionStatus.SERVER_ERROR);
        } else {
            FileType fileTypeByFileName = getFileType(readFile.getName());
            if (fileTypeByFileName.ordinal() != FileType.NO_TYPE.ordinal()) {
                fileType = fileTypeByFileName;
            }

            return fileType;
        }
    }

    private static boolean isSupportImageFile(FileType fileType) {
        FileType[] arr$ = SUPPORT_IMG_TYPE;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            FileType type = arr$[i$];
            if (type.ordinal() == fileType.ordinal()) {
                return true;
            }
        }

        return false;
    }

    private static File write2File(InputStream is, File descFile) throws IOException {
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(descFile);
            byte[] buffer = new byte[1024];

            while(is.read(buffer) != -1) {
                os.write(buffer);
            }

            os.flush();
            return descFile;
        } catch (IOException var11) {
            var11.printStackTrace();
            throw var11;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

                if (is != null) {
                    is.close();
                }
            } catch (IOException var10) {
                var10.printStackTrace();
                throw var10;
            }

        }
    }

    private static boolean moveFile(String srcFileName, String destDirName) {
        File srcFile = new File(srcFileName);
        if (srcFile.exists() && srcFile.isFile()) {
            File destDir = new File(destDirName);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            return srcFile.renameTo(new File(destDirName + File.separator + srcFile.getName()));
        } else {
            return false;
        }
    }

    private static boolean moveDirectory(String srcDirName, String destDirName) {
        File srcDir = new File(srcDirName);
        if (srcDir.exists() && srcDir.isDirectory()) {
            File destDir = new File(destDirName);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            File[] sourceFiles = srcDir.listFiles();
            File[] arr$ = sourceFiles;
            int len$ = sourceFiles.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File sourceFile = arr$[i$];
                if (sourceFile.isDirectory()) {
                    moveDirectory(sourceFile.getAbsolutePath(), destDir.getAbsolutePath() + File.separator + sourceFile.getName());
                } else {
                    moveFile(sourceFile.getAbsolutePath(), destDir.getAbsolutePath());
                }
            }

            return srcDir.delete();
        } else {
            return false;
        }
    }

    public static final void waterMarkWithImage(String pressImg, String targetImg, int pos, float alpha) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int width = src.getWidth((ImageObserver)null);
            int height = src.getHeight((ImageObserver)null);
            BufferedImage image = new BufferedImage(width, height, 1);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, (ImageObserver)null);
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            g.setComposite(AlphaComposite.getInstance(10, alpha / 100.0F));
            int width_biao = src_biao.getWidth((ImageObserver)null);
            int height_biao = src_biao.getHeight((ImageObserver)null);
            int x = 0;
            int y = 0;
            if (pos == 1) {
                ;
            }

            if (pos == 2) {
                x = (width - width_biao) / 2;
                y = 0;
            }

            if (pos == 3) {
                x = width - width_biao;
                y = 0;
            }

            if (pos == 4) {
                x = width - width_biao;
                y = (height - height_biao) / 2;
            }

            if (pos == 5) {
                x = width - width_biao;
                y = height - height_biao;
            }

            if (pos == 6) {
                x = (width - width_biao) / 2;
                y = height - height_biao;
            }

            if (pos == 7) {
                x = 0;
                y = height - height_biao;
            }

            if (pos == 8) {
                x = 0;
                y = (height - height_biao) / 2;
            }

            if (pos == 9) {
                x = (width - width_biao) / 2;
                y = (height - height_biao) / 2;
            }

            g.drawImage(src_biao, x, y, width_biao, height_biao, (ImageObserver)null);
            g.dispose();
            ImageIO.write(image, "jpg", new File(targetImg));
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    public static boolean waterMarkWithText(String filePath, String outPath, String text, String markContentColor, Font font, int pos, float qualNum) {
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image theImg = imgIcon.getImage();
        int width = theImg.getWidth((ImageObserver)null);
        int height = theImg.getHeight((ImageObserver)null);
        BufferedImage bimage = new BufferedImage(width, height, 1);
        Graphics2D g = bimage.createGraphics();
        if (font == null) {
            font = new Font("黑体", 1, 30);
            g.setFont(font);
        } else {
            g.setFont(font);
        }

        g.setColor(getColor(markContentColor));
        g.setBackground(Color.white);
        g.drawImage(theImg, 0, 0, (ImageObserver)null);
        FontMetrics metrics = new FontMetrics(font) {
        };
        Rectangle2D bounds = metrics.getStringBounds(text, (Graphics)null);
        int widthInPixels = (int)bounds.getWidth();
        int heightInPixels = (int)bounds.getHeight();
        int left = 0;
        int top = heightInPixels;
        if (pos == 1) {
            ;
        }

        if (pos == 2) {
            left = width / 2;
            top = heightInPixels;
        }

        if (pos == 3) {
            left = width - widthInPixels;
            top = heightInPixels;
        }

        if (pos == 4) {
            left = width - widthInPixels;
            top = height / 2;
        }

        if (pos == 5) {
            left = width - widthInPixels;
            top = height - heightInPixels;
        }

        if (pos == 6) {
            left = width / 2;
            top = height - heightInPixels;
        }

        if (pos == 7) {
            left = 0;
            top = height - heightInPixels;
        }

        if (pos == 8) {
            left = 0;
            top = height / 2;
        }

        if (pos == 9) {
            left = width / 2;
            top = height / 2;
        }

        g.drawString(text, left, top);
        g.dispose();

        try {
            ImageIO.write(bimage, "jpg", new File(outPath));
            return true;
        } catch (Exception var20) {
            return false;
        }
    }

    public static Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }

        if (color.length() != 6) {
            return null;
        } else {
            try {
                int r = Integer.parseInt(color.substring(0, 2), 16);
                int g = Integer.parseInt(color.substring(2, 4), 16);
                int b = Integer.parseInt(color.substring(4), 16);
                return new Color(r, g, b);
            } catch (NumberFormatException var4) {
                return null;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "/home/xw/Documents/100001.png";
        String uploadRootPath = "/home/xw/Documents/upload";
        String downloadRootPath = "/home/xw/Documents/download";
        String attachmentId = IdGenerator.getID();
        String taskId = IdGenerator.getID();
        deleteFile(uploadRootPath);
        String fileId = "5487e84e559ae211c6cb1df0";

        for(int i = 0; i < 5; ++i) {
            persistentUploadFile(uploadRootPath,
                    fileId, FileType.IMG_PNG, new FileInputStream(filePath),
                    PhotoDimension.THUMB_128X128);
        }

        deletePersistentFile(uploadRootPath, fileId);
        System.out.println(getFileNameExcludeExtensionType("test.jpg"));
        System.out.println(getFileNameExcludeExtensionType("test"));
        System.out.println(getFileType("test.jpg").getValue());
    }

    static {
        SUPPORT_IMG_TYPE = new FileType[]{FileType.IMG_JPG, FileType.IMG_JPEG, FileType.IMG_PNG, FileType.IMG_GIF, FileType.IMG_BMP};
        executor = new ThreadPoolExecutor(5, 5, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        executor.allowCoreThreadTimeOut(true);
    }
}
