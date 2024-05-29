package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.util.ManipulateImage;
import com.juaracoding.pcmtahelper.util.OpenCVFunction;

public class Main {
    public static void main(String[] args) {
        /** example compare image */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String filename2 = System.getProperty("user.dir")+"\\data\\gambar-awal.png";
//        String filename2 = System.getProperty("user.dir")+"\\data\\Foto-Faskes-Awal-1.png";
//        System.out.println(ManipulateImage.compareImage(filename1,filename2));

//        /** contoh untuk membuat gambar menjadi abu-abu */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String pathTujuan = "D:\\download-automation\\gambar-180-abu-abu.png";
//        ManipulateImage.grayScale(filename1,pathTujuan);

//        /** contoh untuk membuat gambar menjadi sepia */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String pathTujuan = "D:\\download-automation\\gambar-180-sepia.png";
//        ManipulateImage.sepia(filename1,pathTujuan);

//        /** contoh untuk membuat gambar menjadi mirroring */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String pathTujuan = "D:\\download-automation\\gambar-180-mirror.png";
//        ManipulateImage.mirroring(filename1,pathTujuan);

//        /** contoh untuk membuat gambar menjadi negatif */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String pathTujuan = "D:\\download-automation\\gambar-180-negative.png";
//        ManipulateImage.negative(filename1,pathTujuan);

        //        /** contoh untuk membuat gambar menjadi negatif */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String pathTujuan = "D:\\download-automation\\gambar-180-negative.png";
//        ManipulateImage.negative(filename1,pathTujuan);

        /** contoh untuk mengubah orientasi gambar */
        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
        String pathTujuan = "D:\\download-automation\\gambar-180-beda-orientasi.png";
        OpenCVFunction.changeOrientation(filename1,pathTujuan);

//        /** contoh untuk membuat gambar menjadi watermark */
//        String filename1 = System.getProperty("user.dir")+"\\data\\gambar-180.png";
//        String pathTujuan = "D:\\download-automation\\gambar-180-watermark.png";
//        ManipulateImage.watermarking(filename1,pathTujuan,"INI TESTING !!");
    }
}