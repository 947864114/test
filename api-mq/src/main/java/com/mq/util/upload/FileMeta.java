package com.mq.util.upload;

import java.io.Serializable;
import java.net.URL;

public class FileMeta implements Serializable{
    private String id;          //文件ID
    private String fileName;    //文件名
    private long fileSize;      //文件大小
    private String fileType;    //文件类型 .png , .jpg ....
    private URL url;        //文件访问的URL
    private int server;         //文件存放的服务器ID
    private String error;   //错误信息

    public FileMeta() {
    }

    public FileMeta(String id, String fileName, long fileSize, String fileType, URL url, int server, String error) {
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.url = url;
        this.server = server;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getServer() {
        return server;
    }

    public String getError() {
        return error;
    }
}
