package com.mq.util.upload;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ThumbHelper {
    public ThumbHelper() {
    }

    public static void scalePhoto(File dstFile, File srcFile, String dimenString, int orientation) throws IOException {
        scalePhoto(dstFile, srcFile, dimenString, orientation, false);
    }

    public static void scalePhoto(File dstFile, File srcFile, String dimenString, int orientation, boolean crop) throws IOException {
        if (srcFile.exists()) {
            Dimension size = extractDimen(dimenString);
            if (!dstFile.exists()) {
                Image src = Toolkit.getDefaultToolkit().getImage(srcFile.getPath());
                BufferedImage image = toBufferedImage(src);
                if (image == null) {
                    return;
                }

                int width = image.getWidth();
                int height = image.getHeight();
                float scale = getRatio(width, height, size.width, size.height);
                width = (int)(scale * (float)width);
                height = (int)(scale * (float)height);
                image = ImageScaler.scaleImage(width, height, image);
                image = ImageRotator.rotateImage(orientation, image);
                if (crop) {
                    width = image.getWidth();
                    height = image.getHeight();
                    if (width > height) {
                        image = image.getSubimage((width - height) / 2, 0, height, height);
                    } else if (height > width) {
                        image = image.getSubimage(0, (height - width) / 2, width, width);
                    }
                }

                ImageIO.write(image, "jpg", dstFile.getAbsoluteFile());
            }

        }
    }

    public static float getRatio(int width, int height, int maxWidth, int maxHeight) {
        float ratio = 1.0F;
        float widthRatio = (float)maxWidth / (float)width;
        float heightRatio = (float)maxHeight / (float)height;
        if ((double)widthRatio < 1.0D || (double)heightRatio < 1.0D) {
            ratio = widthRatio <= heightRatio ? heightRatio : widthRatio;
        }

        return ratio;
    }

    public static Dimension extractDimen(String dimenString) throws IOException {
        Dimension size = (Dimension)PhotoConstantsExt.scaleMap.get(dimenString);
        if (size == null) {
            if (dimenString.startsWith("64X64")) {
                size = parseSize(dimenString.substring("64X64".length()));
            } else if (dimenString.startsWith("32X32")) {
                size = parseSize(dimenString.substring("32X32".length()));
            }

            if (size == null) {
                throw new IOException("Invalid type:" + dimenString);
            }
        }

        return size;
    }

    public static Dimension parseSize(String dimenString) {
        String[] sizes = dimenString.toLowerCase().split("x");
        if (sizes.length > 1) {
            try {
                int width = Integer.parseInt(sizes[0]);
                int height = Integer.parseInt(sizes[1]);
                return new Dimension(width, height);
            } catch (NumberFormatException var4) {
                ;
            }
        }

        return null;
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        } else {
            image = (new ImageIcon(image)).getImage();
            BufferedImage bimage = null;
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            byte type;
            try {
                type = 1;
                GraphicsDevice gs = ge.getDefaultScreenDevice();
                GraphicsConfiguration gc = gs.getDefaultConfiguration();
                bimage = gc.createCompatibleImage(image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), type);
            } catch (HeadlessException var6) {
                ;
            }

            if (bimage == null) {
                type = 1;
                bimage = new BufferedImage(image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), type);
            }

            Graphics g = bimage.createGraphics();
            g.drawImage(image, 0, 0, (ImageObserver)null);
            g.dispose();
            return bimage;
        }
    }

    public static void main(String[] args) {
        File file = new File("/Users/xw/Downloads/1-1.jpg");
        File smallThumbFile = new File("/Users/xw/Downloads/1-1_s1.jpg");

        try {
            long c = System.currentTimeMillis();
            scalePhoto(smallThumbFile, file, PhotoDimension.THUMB_90X90.getValue(), 1, false);
            System.out.println("elapse time:" + (float)(System.currentTimeMillis() - c) / 1000.0F + "s");
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}
