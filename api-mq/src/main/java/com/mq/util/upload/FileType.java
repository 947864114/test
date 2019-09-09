package com.mq.util.upload;

public enum FileType {
    NO_TYPE(""),
    IMG_JPG(".jpg"),
    IMG_JPEG(".jpeg"),
    IMG_PNG(".png"),
    IMG_GIF(".gif"),
    IMG_BMP(".bmp"),
    ZIP(".zip"),
    AUDIO_3GP(".3gp"),
    AUDIO_MP3(".mp3"),
    WIN_EXL(".xls"),
    WIN_EXLX(".xlsx"),
    WIN_DOC(".doc"),
    WIN_DOCX(".docx"),
    PDF(".pdf"),
    SWF(".swf");

    private final String value;

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
