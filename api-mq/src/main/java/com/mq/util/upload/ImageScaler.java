package com.mq.util.upload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.ImageObserver;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

public class ImageScaler {
    public ImageScaler() {
    }

    public static void scale(File imagePath, File destFile, float scale) throws IOException {
        Image image = ImageIO.read(imagePath);
        scale((Image)image, destFile, scale);
    }

    public static void scale(File imagePath, File destFile, int preferredWidth, int preferredHeight) throws IOException {
        Image image = ImageIO.read(imagePath);
        scaleImage(destFile, preferredWidth, preferredHeight, image);
    }

    public static void scale(Image image, File destFile, float scale) throws IOException {
        int preferredWidth = (int)(scale * (float)image.getWidth((ImageObserver)null));
        int preferredHeight = (int)(scale * (float)image.getHeight((ImageObserver)null));
        scaleImage(destFile, preferredWidth, preferredHeight, image);
    }

    public static void scale(Image image, File destFile, int preferredWidth, int preferredHeight) throws IOException {
        scaleImage(destFile, preferredWidth, preferredHeight, image);
    }

    private static void scaleImage(File destFile, int preferredWidth, int preferredHeight, Image originalImage) throws IOException {
        ImageIO.write(scaleImage(preferredWidth, originalImage), "jpg", destFile);
    }

    public static BufferedImage scaleImage(int preferredWidth, Image originalImage) {
        Image scaleImage = originalImage.getScaledInstance(preferredWidth, -1, 2);
        int width = scaleImage.getWidth((ImageObserver)null);
        int height = scaleImage.getHeight((ImageObserver)null);
        BufferedImage buffer = new BufferedImage(width, height, 1);
        buffer.getGraphics().drawImage(scaleImage, 0, 0, width, height, (ImageObserver)null);
        return buffer;
    }

    public static BufferedImage scaleImage(int preferredWidth, int preferredHeight, Image originalImage) {
        Image scaleImage = originalImage.getScaledInstance(preferredWidth, preferredHeight, 16);
        int width = scaleImage.getWidth((ImageObserver)null);
        int height = scaleImage.getHeight((ImageObserver)null);
        BufferedImage buffer = new BufferedImage(width, height, 1);
        Graphics2D g2 = buffer.createGraphics();
        g2.drawImage(scaleImage, 0, 0, width, height, Color.white, (ImageObserver)null);
        g2.dispose();
        float[] kernelData2 = new float[]{-0.125F, -0.125F, -0.125F, -0.125F, 2.0F, -0.125F, -0.125F, -0.125F, -0.125F};
        Kernel kernel = new Kernel(3, 3, kernelData2);
        ConvolveOp cOp = new ConvolveOp(kernel, 1, (RenderingHints)null);
        buffer = cOp.filter(buffer, (BufferedImage)null);
        return buffer;
    }
}
