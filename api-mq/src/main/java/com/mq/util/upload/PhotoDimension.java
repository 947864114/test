package com.mq.util.upload;

public enum PhotoDimension {
    ORIGINAL_FILE("original"),
    THUMB_32X32("32X32"),
    THUMB_64X64("64X64"),
    THUMB_90X90("90X90"),
    THUMB_110X110("110X110"),
    THUMB_128X128("128X128"),
    THUMB_150X150("150X150"),
    THUMB_180X180("180X180"),
    THUMB_270X270("270X270"),
    THUMB_300X300("300X300"),
    THUMB_320X480("320X480"),
    THUMB_800X1280("800X1280"),
    THUMB_480X800("480X800"),
    THUMB_640X640("640X640"),
    THUMB_768X1024("768X1024");

    private final String value;

    private PhotoDimension(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
