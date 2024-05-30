package com.juaracoding.pcmtahelper.util;

import com.juaracoding.pcmtahelper.connection.Constants;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Document;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

//    public static void dropDownlist(WebDriver driver, String strXpath){
//        List<WebElement> d =driver.findElements(By.xpath(strXpath));
//        Iterator<WebElement> itr = d.iterator();
//
//        int intIter = 1;
//        String value = "";
//        while (itr.hasNext()){
//            WebElement element = itr.next();
//            value = element.getText();
//            System.out.println("Element get Text : "+value);
//        }
//    }

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

    /** Fungsional OCR mengubah image yang berisi text menjadi String */
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

    /** melakukan pengecekan apakah folder telah dibuat atau belum, jika belum otomatis akan terbuat */
    public static void checkAndCreateDirectory(String path){
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }

    /** melakukan download file dari url
        Parameter targetDirectory harus berbentuk Path + penamaan file
     */
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

    public static void deleteFile(String pathFile){
        File myObj = new File(pathFile);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    /** dari file HTML ke file excel dengan ekstensi XLSX */
    public static void htmlToExcelXLSX(String pathFile,String sheetName){
        String pathOutPut = pathFile+"x";
        JsoupTableParser jsoParser = new JsoupTableParser();
        Document doc = jsoParser.loadFromFile(pathFile);
        List<Map<String, String>> tableData = jsoParser.parseTable(doc, 0);
        GlobalFunction.deleteFile(pathOutPut);
        try {
            XSSFWorkbook xwork = new XSSFWorkbook();
            XSSFSheet xsheet = xwork.createSheet(sheetName);
            XSSFRow xrow  = null;
            int rowid =0;
            int colCount =0;
            for (int i = 0; i < tableData.size(); i++) {
                Cell cell;
                xrow = xsheet.createRow(i);
                colCount =0;
                for (Map.Entry<String,String> entry : tableData.get(i).entrySet()) {
                    cell = xrow.createCell(colCount);
                    cell.setCellValue(entry.getValue());
                    colCount++;
                }
            }

            FileOutputStream fout = new FileOutputStream(new File(pathOutPut));
            xwork.write(fout);
            fout.close();
        }

        catch (Exception e) {
            e.getMessage();
        }
    }
}