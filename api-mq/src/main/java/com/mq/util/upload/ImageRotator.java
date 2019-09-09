package com.mq.util.upload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ImageRotator {
    public ImageRotator() {
    }

    public static BufferedImage rotateImage(int orientation, BufferedImage orignalImage) {
        int width = orignalImage.getWidth((ImageObserver)null);
        int height = orignalImage.getHeight((ImageObserver)null);
        int dstWidth = width;
        int dstHeight = height;
        AffineTransform t = new AffineTransform();
        switch(orientation) {
            case 1:
                return orignalImage;
            case 2:
                t.scale(-1.0D, 1.0D);
                t.translate((double)(-width), 0.0D);
                break;
            case 3:
                t.translate((double)width, (double)height);
                t.rotate(3.141592653589793D);
                break;
            case 4:
                t.scale(1.0D, -1.0D);
                t.translate(0.0D, (double)(-height));
                break;
            case 5:
                t.rotate(-1.5707963267948966D);
                t.scale(-1.0D, 1.0D);
                dstWidth = height;
                dstHeight = width;
                break;
            case 6:
                t.translate((double)height, 0.0D);
                t.rotate(1.5707963267948966D);
                dstWidth = height;
                dstHeight = width;
                break;
            case 7:
                t.scale(-1.0D, 1.0D);
                t.translate((double)(-height), 0.0D);
                t.translate(0.0D, (double)width);
                t.rotate(4.71238898038469D);
                dstWidth = height;
                dstHeight = width;
                break;
            case 8:
                t.translate(0.0D, (double)width);
                t.rotate(4.71238898038469D);
                dstWidth = height;
                dstHeight = width;
                break;
            default:
                return orignalImage;
        }

        BufferedImage buffer = new BufferedImage(dstWidth, dstHeight, 1);
        Graphics2D g2d = buffer.createGraphics();
        g2d.drawImage(orignalImage, t, (ImageObserver)null);
        g2d.dispose();
        return buffer;
    }

    public static void main(String[] args) {
        try {
            BufferedImage read = ImageIO.read(new File("/home/xw/Documents/1.jpg"));

            for(int i = 1; i <= 8; ++i) {
                ImageIO.write(rotateImage(i, read), "jpg", new File("/home/xw/Documents/" + i));
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
