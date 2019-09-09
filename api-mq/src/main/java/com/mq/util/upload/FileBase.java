package com.mq.util.upload;

import java.io.File;

public class FileBase {
    public FileBase() {
    }

    protected static String getClientAppFileName(int versionCode) {
        return String.format("hausung-ppm-%d.apk", versionCode);
    }

    public static String getFileNameExcludeExtensionType(String fileName) {
        if (fileName != null && fileName.length() != 0) {
            int fileExtensionIndex = fileName.lastIndexOf(".");
            return fileExtensionIndex == -1 ? fileName : fileName.substring(0, fileExtensionIndex);
        } else {
            throw new IllegalStateException("File name is not empty!");
        }
    }

    public static FileType getFileTypeByFileName(String fileName) {
        if (fileName != null && fileName.length() != 0) {
            int fileExtensionIndex = fileName.lastIndexOf(".");
            if (fileExtensionIndex == -1) {
                throw new IllegalStateException("File has not extension type!");
            } else {
                String extensionName = fileName.substring(fileExtensionIndex, fileName.length());
                return getFileType(extensionName);
            }
        } else {
            throw new IllegalStateException("File name is not empty!");
        }
    }

    public static FileType getFileType(String extensionName) {
        FileType[] arr$ = FileType.values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            FileType fileType = arr$[i$];
            if (fileType.getValue().equalsIgnoreCase(extensionName)) {
                return fileType;
            }
        }

        return FileType.NO_TYPE;
    }

    protected static String getThumbnailFileName(String fileName, String dimenString) {
        int fileExtensionIndex = fileName.lastIndexOf(".");
        if (fileExtensionIndex == -1) {
            return fileName + "_" + dimenString;
        } else {
            String fileExtension = fileName.substring(fileExtensionIndex, fileName.length());
            return fileName.substring(0, fileExtensionIndex) + "_" + dimenString + fileExtension;
        }
    }

    public static String generateFilePath(String id) {
        String digest = Digests.md5(id);
        StringBuffer sb = new StringBuffer();
        int level = 3;

        for (int i = 0; i < digest.length() && level != 0; i += 2) {
            if (level != 3) {
                sb.append(File.separator);
            }

            sb.append(digest.substring(i, i + 2));
            --level;
        }

        sb.append(File.separator);
        sb.append(id);
        return sb.toString();
    }

    public static String generateFileURIPath(String id) {
        String digest = Digests.md5(id);
        StringBuffer sb = new StringBuffer();
        int level = 3;

        for (int i = 0; i < digest.length() && level != 0; i += 2) {
            if (level != 3) {
                sb.append("/");
            }

            sb.append(digest.substring(i, i + 2));
            --level;
        }

        sb.append("/");
        sb.append(id);
        return sb.toString();
    }
}
