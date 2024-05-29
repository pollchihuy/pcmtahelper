package com.juaracoding.pcmtahelper.util;

import com.juaracoding.pcmtahelper.connection.Constants;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GlobalFunction {

    public static int testCount = 0;
    public static int countOutline = 1;
    public static String rootProject = System.getProperty("user.dir");

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"/FailedTestScreenshot/"
                +screenshotName+"_"+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public static void dropDownlist(WebDriver driver, String strXpath){
        List<WebElement> d =driver.findElements(By.xpath(strXpath));
        Iterator<WebElement> itr = d.iterator();

        int intIter = 1;
        String value = "";
        while (itr.hasNext()){
            WebElement element = itr.next();
            value = element.getText();
            System.out.println("Element get Text : "+value);
        }
    }

    /** ini untuk konfigurasi di seluruh step */
    public static  void delay(int intDetik){
        if(Constants.GLOB_PARAM_DELAY.equalsIgnoreCase("y")){
            try {
                Thread.sleep(intDetik*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /** ini untuk proses Asyncronous */
    public static  void delay(int intDetik,String strNull){
        try {
            Thread.sleep(intDetik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateTextOCR(String pathFile){
        String result = "";
        try {
            File image = new File(pathFile);
            Tesseract tesseract = new Tesseract();
            /** arahkan ke tessa data folder OCR nya */
            tesseract.setDatapath("D:\\tessa\\tessdata-3.04.00");
            tesseract.setLanguage("eng");
            tesseract.setPageSegMode(1);
            tesseract.setOcrEngineMode(1);
            result = tesseract.doOCR(image);
        } catch (TesseractException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
    public static void checkAndCreateDirectory(String path){
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }
    public static void downloadImage(String sourceUrl, String targetDirectory)
    {
        try {
            URL website = new URL(sourceUrl);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(targetDirectory);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}