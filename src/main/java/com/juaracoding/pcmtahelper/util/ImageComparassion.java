package com.juaracoding.pcmtahelper.util;


import org.opencv.core.*;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.SIFT;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

public class ImageComparassion {

    public static Double calculateDifferences(String imgSource,int intSource,String imgToCompare,int intImageToCompare){
        OpenCVFunction.loadLibraries();
//        Mat img1 = Imgcodecs.imread(imgSource, Imgcodecs.IMWRITE_PNG_COMPRESSION);
//        Mat img2 = Imgcodecs.imread(imgToCompare, Imgcodecs.IMREAD_ANYCOLOR);
        /** compress = 16, abu-abu = 1, berwarna = 1, full warna = 4*/
        Mat img1 = Imgcodecs.imread(imgSource,intSource);
        Mat img2 = Imgcodecs.imread(imgToCompare, intImageToCompare);
        if (img1.empty() || img2.empty()) {
            System.err.println("Cannot read images!");
            System.exit(0);
        }

        double contrastThreshold = 0.03;
        double edgeThreshold = 2.0;
        double sigma = 1.0;
        int nOctaveLayers = 3;
        int hessianThreshold = 400;
        SIFT detector = SIFT.create(hessianThreshold, nOctaveLayers, contrastThreshold, edgeThreshold, sigma);

        MatOfKeyPoint keypoints1 = new MatOfKeyPoint(), keypoints2 = new MatOfKeyPoint();
        Mat descriptors1 = new Mat(), descriptors2 = new Mat();

        detector.detectAndCompute(img1, new Mat(), keypoints1, descriptors1);
        detector.detectAndCompute(img2, new Mat(), keypoints2, descriptors2);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
        List<MatOfDMatch> knnMatches = new ArrayList<>();
        matcher.knnMatch(descriptors1, descriptors2, knnMatches, 2);

        float ratioThresh = 0.7f;
        List<DMatch> listOfGoodMatches = new ArrayList<>();
        for (int i = 0; i < knnMatches.size(); i++) {
            if (knnMatches.get(i).rows() > 1) {
                DMatch[] matches = knnMatches.get(i).toArray();
                if (matches[0].distance < ratioThresh * matches[1].distance) {
                    listOfGoodMatches.add(matches[0]);
                }
            }
        }
        MatOfDMatch goodMatches = new MatOfDMatch();
        goodMatches.fromList(listOfGoodMatches);

        Mat imgMatches = new Mat();
        Features2d.drawMatches(img1, keypoints1, img2, keypoints2, goodMatches, imgMatches, Scalar.all(-1),
                Scalar.all(-1), new MatOfByte(), Features2d.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS);

        /** uncomment ini untuk melihat visualisasi nya ini di comment kalau sudah mau deploy testing */
//        HighGui.imshow("Displayed", imgMatches);
//        HighGui.waitKey(0);
        return Double.valueOf(listOfGoodMatches.size());
    }
}
