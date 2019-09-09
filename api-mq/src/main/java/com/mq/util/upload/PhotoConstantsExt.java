package com.mq.util.upload;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PhotoConstantsExt extends PhotoConstants {
    public static final Dimension THUMB_SIZE_32X32 = new Dimension(32, 32);
    public static final Dimension THUMB_SIZE_64X64 = new Dimension(64, 64);
    public static final Dimension THUMB_SIZE_90X90 = new Dimension(90, 90);
    public static final Dimension THUMB_SIZE_110X110 = new Dimension(110, 110);
    public static final Dimension THUMB_SIZE_128X128 = new Dimension(128, 128);
    public static final Dimension THUMB_SIZE_150X150 = new Dimension(150, 150);
    public static final Dimension THUMB_SIZE_180X180 = new Dimension(180, 180);
    public static final Dimension THUMB_SIZE_270X270 = new Dimension(270, 270);
    public static final Dimension THUMB_SIZE_300X300 = new Dimension(300, 300);
    public static final Dimension THUMB_SIZE_320X480 = new Dimension(320, 480);
    public static final Dimension THUMB_SIZE_800X1280 = new Dimension(800, 1280);
    public static final Dimension THUMB_SIZE_480X800 = new Dimension(480, 800);
    public static final Dimension THUMB_SIZE_768X1024 = new Dimension(768, 1024);
    public static final Dimension THUMB_SIZE_640X640 = new Dimension(640, 640);
    public static final Map<String, Dimension> scaleMap = new HashMap();

    public PhotoConstantsExt() {
    }

    static {
        scaleMap.put("32X32", THUMB_SIZE_32X32);
        scaleMap.put("64X64", THUMB_SIZE_64X64);
        scaleMap.put("90X90", THUMB_SIZE_90X90);
        scaleMap.put("110X110", THUMB_SIZE_110X110);
        scaleMap.put("128X128", THUMB_SIZE_128X128);
        scaleMap.put("150X150", THUMB_SIZE_150X150);
        scaleMap.put("180X180", THUMB_SIZE_180X180);
        scaleMap.put("270X270", THUMB_SIZE_270X270);
        scaleMap.put("300X300", THUMB_SIZE_300X300);
        scaleMap.put("320X480", THUMB_SIZE_320X480);
        scaleMap.put("800X1280", THUMB_SIZE_800X1280);
        scaleMap.put("480X800", THUMB_SIZE_480X800);
        scaleMap.put("768X1024", THUMB_SIZE_768X1024);
        scaleMap.put("640X640", THUMB_SIZE_640X640);
    }
}
