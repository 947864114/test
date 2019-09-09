package com.mq.util.upload;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileTypeValidationHelper {
    public static final Map<String, String[]> FILE_TYPE_MAP = new HashMap();

    public FileTypeValidationHelper() {
    }

    private static void getAllFileType() {
        FILE_TYPE_MAP.put(".jpg", new String[]{"FFD8FF"});
        FILE_TYPE_MAP.put(".png", new String[]{"89504E47"});
        FILE_TYPE_MAP.put(".gif", new String[]{"47494638"});
        FILE_TYPE_MAP.put(".bmp", new String[]{"424D"});
        FILE_TYPE_MAP.put(".zip", new String[]{"504B0304"});
        FILE_TYPE_MAP.put(".3gp", new String[]{"2321414D52", "0000002066747970", "0000001866747970"});
        FILE_TYPE_MAP.put(".mp3", new String[]{"494433"});
        FILE_TYPE_MAP.put(".swf", new String[]{"465753", "435753"});
    }

    public static final String getImageFileType(File f) {
        if (isImage(f)) {
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(f);
                Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
                if (!iterator.hasNext()) {
                    return null;
                } else {
                    ImageReader reader = (ImageReader)iterator.next();
                    iis.close();
                    return reader.getFormatName();
                }
            } catch (IOException var4) {
                return null;
            } catch (Exception var5) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static final boolean isImage(Object input) {
        InputStream is = getInputStreamParameter(input);

        boolean flag;
        try {
            BufferedImage bufReader = ImageIO.read(is);
            int width = bufReader.getWidth();
            int height = bufReader.getHeight();
            if (width != 0 && height != 0) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (IOException var6) {
            flag = false;
        } catch (Exception var7) {
            flag = false;
        }

        return flag;
    }

    public static final FileType getFileType(Object input) {
        InputStream is = getInputStreamParameter(input);
        String type = null;
        byte[] b = new byte[50];

        try {
            is.read(b);
            type = getFileTypeByStream(b);
            is.close();
        } catch (FileNotFoundException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        if (type != null && type.length() > 0) {
            FileType[] arr$ = FileType.values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                FileType fileType = arr$[i$];
                if (type.equals(fileType.getValue())) {
                    return fileType;
                }
            }
        }

        return FileType.NO_TYPE;
    }

    public static final boolean isValidImageFile(Object input) {
        FileType fileType = getFileType(input);
        if (fileType.ordinal() != FileType.NO_TYPE.ordinal()) {
            String fileTypeValue = fileType.getValue();
            String[] arr$ = PhotoConstants.SUPPORT_PHOTO_TYPE;
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String type = arr$[i$];
                if (fileTypeValue.equals(type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static final boolean isValidZipFile(Object input) {
        FileType fileType = getFileType(input);
        return fileType.ordinal() == FileType.ZIP.ordinal();
    }

    private static final String getFileTypeByStream(byte[] b) {
        String fileTypeHex = String.valueOf(getFileHexString(b));
        Iterator entryIterator = FILE_TYPE_MAP.entrySet().iterator();

        while(entryIterator.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry)entryIterator.next();
            String[] fileTypeHexValues = (String[])entry.getValue();
            String[] arr$ = fileTypeHexValues;
            int len$ = fileTypeHexValues.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String fileTypeHexValue = arr$[i$];
                if (fileTypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                    return (String)entry.getKey();
                }
            }
        }

        return null;
    }

    private static final String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b != null && b.length > 0) {
            for(int i = 0; i < b.length; ++i) {
                int v = b[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private static final InputStream getInputStreamParameter(Object input) {
        if (input == null) {
            throw new IllegalArgumentException("input == null!");
        } else {
            Object is;
            if (input instanceof InputStream) {
                is = (InputStream)input;
            } else {
                if (!(input instanceof File)) {
                    throw new IllegalArgumentException("Input parameter is not support");
                }

                try {
                    is = new FileInputStream((File)input);
                } catch (FileNotFoundException var3) {
                    throw new IllegalArgumentException("file not found");
                }
            }

            return (InputStream)is;
        }
    }

    public static void main(String[] args) throws Exception {
        File f = new File("/home/xw/Documents/100000.3");
        System.out.println("Image Type ==>>  " + getImageFileType(f));
        System.out.println("File Type ==>>" + getFileType(f));
        System.out.println("Validation Image File ==>>" + isValidImageFile(f));
        System.out.println("Is Image ==>>" + isImage(f));
        System.out.println("Is Image ==>>" + isImage(new FileInputStream(f)));
        System.out.println("File Type ==>>" + getFileType(new FileInputStream(f)));
    }

    static {
        getAllFileType();
    }
}
