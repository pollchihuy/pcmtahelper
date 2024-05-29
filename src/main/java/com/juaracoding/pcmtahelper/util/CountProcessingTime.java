package com.juaracoding.pcmtahelper.util;

public class CountProcessingTime {
    private static long startProcessing = 1L;
    private static long startProcessingTwo = 1L;
    private static long endProcessing = 1L;
    private static long endProcessingTwo = 1L;

    public static void start (String processName) {
        System.out.println("Proses "+processName+" Dimulai");
        startProcessing = System.currentTimeMillis();
    }

    public static long startWithReturn () {
        startProcessingTwo = System.currentTimeMillis();
        return startProcessingTwo;
    }

    public static void end(){
//        endProcessing = (System.currentTimeMillis()-startProcessing)/1000;
        endProcessing = (System.currentTimeMillis()-startProcessing);
        System.out.println("Selesai Dalam "+endProcessing+" ms");
    }

    public static long endWithReturn(){
        return endProcessingTwo = (System.currentTimeMillis()-startProcessingTwo);
    }
}
