package com.juaracoding.pcmtahelper.util;

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OpenCVFunction {

    /**
     * Open CV Libaray
     */
    public static void loadLibraries() {

        try {
            InputStream in = null;
            File fileOut = null;
            String osName = System.getProperty("os.name");
            String opencvpath = "C:\\opencv46\\opencv\\build\\java\\";

            if (osName.startsWith("Windows")) {
                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
                if (bitness == 32) {
                    opencvpath = opencvpath + "\\x86\\";
                } else if (bitness == 64) {
                    opencvpath = opencvpath + "\\x64\\";
                } else {
                    opencvpath = opencvpath + "\\x86\\";
                }
            } else if (osName.equals("Mac OS X")) {
                opencvpath = opencvpath + "Your path to .dylib";
            }
            System.load(opencvpath + "opencv_java460.dll");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load opencv native library", e);
        }
    }

    public static void rotateImage(String pathSource, String pathDestiny, int rotateAngle) {
        loadLibraries();
        String file = pathSource;
        Mat src = Imgcodecs.imread(file);

        Mat dst = new Mat();
        double angle = rotateAngle;

        if (angle == 90 || angle == -270) {
            Core.rotate(src, dst, Core.ROTATE_90_CLOCKWISE);
        } else if (angle == 180 || angle == -180) {
            Core.rotate(src, dst, Core.ROTATE_180);
        } else if (angle == 270 || angle == -90) {
            Core.rotate(src, dst,
                    Core.ROTATE_90_COUNTERCLOCKWISE);
        } else {
            Point rotPoint = new Point((src.cols() / 2.0), (src.rows() / 2.0));
            Mat rotMat = Imgproc.getRotationMatrix2D(rotPoint, angle, 1);
            Imgproc.warpAffine(src, dst, rotMat, src.size(),
                    Imgproc.WARP_INVERSE_MAP);
        }
        Imgcodecs.imwrite(pathDestiny, dst);
        System.out.println("Image Rotated Successfully");
    }

    /** akan selalu ke orientasi gambar nya */
    public static void changeOrientation(String imgSource, String pathDestination) {
        loadLibraries();
        File input = new File(imgSource);
        BufferedImage image = null;
        try{
            image = ImageIO.read(input);
            byte[] data = ((DataBufferByte)image.getRaster()
                    .getDataBuffer())
                    .getData();
            Mat mat = new Mat(image.getHeight(),
                    image.getWidth(), CvType.CV_8UC3);
            mat.put(0, 0, data);

            Mat newMat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            Core.flip(mat, newMat, -1);

            byte[] newData
                    = new byte[newMat.rows() * newMat.cols()
                    * (int)(newMat.elemSize())];
            newMat.get(0, 0, newData);
            BufferedImage image1 = new BufferedImage(
                    newMat.cols(), newMat.rows(), 5);
            image1.getRaster().setDataElements(
                    0, 0, newMat.cols(), newMat.rows(), newData);

            File output = new File(pathDestination);
            ImageIO.write(image1, "jpg", output);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /** untuk mengcompress image */
    public static void compressImage(String imgSource,String pathDestination){
        loadLibraries();
        String image_location = imgSource;
        Mat src = Imgcodecs.imread(image_location);
        Mat dst = new Mat();
        Imgproc.resize(src, dst, new Size(0, 0), 0.1, 0.1, Imgproc.INTER_AREA);
//        Imgproc.resize(src, dst, new Size(367, 158), 0.1, 0.1, Imgproc.INTER_AREA);
        Imgcodecs.imwrite(pathDestination, dst);
    }


}