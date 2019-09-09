package com.mq.util.upload;

public class FileInfo {
    private String newName;
    private String oldName;
    private String url;

    public FileInfo(){}

    public FileInfo(String newName, String oldName, String url){
        this.newName = newName;
        this.oldName = oldName;
        this.url = url;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
