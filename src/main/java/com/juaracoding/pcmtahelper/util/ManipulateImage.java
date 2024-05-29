package com.juaracoding.pcmtahelper.util;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ManipulateImage {

    public static Double compareImage(String image1, String image2) {
        BufferedImage imgA = null;
        BufferedImage imgB = null;
        double totalPixels = 0.0;
        double avgDifferentPixel = 0.0;
        double percentage = 0.0;

        try {
            File fileA = new File(image1);
            File fileB = new File(image2);

            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        if ((width1 != width2) || (height1 != height2))

            System.out.println("Error: PixeL / Dimensi Image Tidak Sama !!");
        else {
            long difference = 0;
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {

                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;

                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }
            totalPixels = width1 * height1 * 3;
            avgDifferentPixel = difference / totalPixels;
            percentage = (avgDifferentPixel / 255) * 100;

            // Lastly print the difference percentage
            System.out.println("Persentase Perbedaan-->" + percentage);
        }
        return percentage;
    }

    /** convert gambar ke sepia */
    public static void sepia(String imgSource, String pathDestination){
        BufferedImage img = null;
        File f = null;
        try {
            f = new File(imgSource);
            img = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int R = (p >> 16) & 0xff;
                int G = (p >> 8) & 0xff;
                int B = p & 0xff;

                int newRed = (int)(0.393 * R + 0.769 * G
                        + 0.189 * B);
                int newGreen = (int)(0.349 * R + 0.686 * G
                        + 0.168 * B);
                int newBlue = (int)(0.272 * R + 0.534 * G
                        + 0.131 * B);

                if (newRed > 255)
                    R = 255;
                else
                    R = newRed;

                if (newGreen > 255)
                    G = 255;
                else
                    G = newGreen;

                if (newBlue > 255)
                    B = 255;
                else
                    B = newBlue;

                p = (a << 24) | (R << 16) | (G << 8) | B;

                img.setRGB(x, y, p);
            }
        }

        try {
            f = new File(pathDestination);
            ImageIO.write(img, "png", f);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /** convert gambar ke grayscale */
    public static void grayScale(String imgSource, String pathDestination){
        BufferedImage img = null;
        File f = null;

        // read image
        try {
            f = new File(imgSource);
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
        for (int i = 0; i < pixels.length; i++) {
            int p = pixels[i];
            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;
            int avg = (r + g + b) / 3;
            p = (a << 24) | (avg << 16) | (avg << 8) | avg;
            pixels[i] = p;
        }
        img.setRGB(0, 0, width, height, pixels, 0, width);
        try {
            f = new File(pathDestination);
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /** convert gambar ke negative */
    public static void negative(String imgSource, String pathDestination){
        BufferedImage img = null;
        File f = null;
        try {
            f = new File(imgSource);
            img = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                p = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, p);
            }
        }
        try {
            f = new File(pathDestination);
            ImageIO.write(img, "png", f);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /** miroring */
    public static void mirroring(String imgSource, String pathDestination){
        BufferedImage simg = null;
        File f = null;
        try {
            f = new File(imgSource);
            simg = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int width = simg.getWidth();
        int height = simg.getHeight();

        BufferedImage mimg = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                int p = simg.getRGB(lx, y);
                mimg.setRGB(rx, y, p);
            }
        }

        // save mirror image
        try {
            f = new File(pathDestination);
            ImageIO.write(mimg, "png", f);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** memberikan watermark ke gambar */
    public static void watermarking(String imgSource, String pathDestination,String textWatermark){
        BufferedImage img = null;
        File f = null;
        try {
            f = new File(imgSource);
            img = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        BufferedImage temp = new BufferedImage(
                img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Graphics graphics = temp.getGraphics();
        graphics.drawImage(img, 0, 0, null);

        graphics.setFont(new Font("Arial", Font.PLAIN, 80));
        graphics.setColor(new Color(255, 0, 0, 40));

        String watermark = textWatermark.equals("")?"Paul Banget!!":textWatermark;

        graphics.drawString(watermark, img.getWidth() / 5,
                img.getHeight() / 3);

        graphics.dispose();

        f = new File(pathDestination);
        try {
            ImageIO.write(temp, "png", f);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    /** mengacak pixeL */
    //D:\download-automation\gambar-180-sepia.png
    public static void randomPixel(String imgSource){
        int width = 640, height = 320;
        BufferedImage img = null;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        File f = null;
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // generating values less than 256
                int a = (int)(Math.random()*256);
                int r = (int)(Math.random()*256);
                int g = (int)(Math.random()*256);
                int b = (int)(Math.random()*256);

                //pixel
                int p = (a<<24) | (r<<16) | (g<<8) | b;

                img.setRGB(x, y, p);
            }
        }

        // write image
        try
        {
            f = new File(imgSource);
            ImageIO.write(img, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
    }


}