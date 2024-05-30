package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.DataGenerator;
import com.juaracoding.pcmtahelper.util.GlobalFunction;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class SiloamUploadPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement fieldUsername ;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement fieldPassword ;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnLogin ;

//    @FindBy(xpath = "//a[@href='https://dev.ptdika.com/staging.siloam/sales/input']")
    @FindBy(xpath = "//a[@href='https://dev.ptdika.com/staging.siloam/sales/input']")
    WebElement linkMenuInput ;

    @FindBy(xpath = "//input[@id='name']")
    WebElement fieldNama ;

    @FindBy(xpath = "//input[@id='no_bpjs']")
    WebElement fieldNomorBpjs ;

    @FindBy(xpath = "//input[@id='no_ktp']")
    WebElement fieldNoKtp ;

    @FindBy(xpath = "//textarea[@id='address']")
    WebElement fieldAlamat ;

    @FindBy(xpath = "//span[@id='select2-ktp_city-container']")
    WebElement ddlKotaKTP ;//KOTA JAKARTA SELATAN

    @FindBy(xpath = "//input[@id='origin_faskes']")
    WebElement fieldFaskesAwal ;//Klinik Pondok Pinang

    @FindBy(xpath = "//span[@id='select2-destination_faskes-container']")
    WebElement ddlFaskesTujuan ;//KOTA JAKARTA SELATAN

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmitFormInputData ;//KOTA JAKARTA SELATAN

    @FindBy(xpath = "//input[@role='textbox']")
    WebElement txtBoxFaskesAwal ;//Klinik Pondok Pinang

    @FindBy(xpath = "//input[@role='textbox']")
    WebElement txtBoxFaskesTujuan ;

    @FindBy(xpath = "//a[@class='nav-link active']//span[@class='d-sm-block d-none']")
    WebElement formUploadDocument ;

    @FindBy(xpath = "//span[normalize-space()='Foto Faskes Awal']")
    WebElement uploadFotoAskesAwal ;

    @FindBy(xpath = "//input[@id='file']")
    WebElement file ;

    @FindBy(xpath = "//button[@id='btnSave']")
    WebElement btnSave ;

    @FindBy(xpath = "//span[normalize-space()='Tanda Tangan Digital']")
    WebElement uploadTTDDigital;

    @FindBy(xpath = "//span[normalize-space()='Foto Faskes Tujuan']")
    WebElement uploadFotoFaskesTujuan ;

    @FindBy(xpath = "//h1[@class='page-header']")
    WebElement formInputTitle;

    @FindBy(xpath = "//button[@class='swal-button swal-button--confirm']")
    WebElement btnOK;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement btnConfirmUploadDocument;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement btnSubmit;

    @FindBy(xpath = "//a[@class='dropdown-toggle']")
    WebElement iconProfile;

    @FindBy(xpath = "//a[@class='dropdown-item']")
    WebElement btnLogOut;

    @FindBy(xpath = "//span[normalize-space()='View & Export']")
    WebElement viewAndReport;

    @FindBy(xpath = "//input[@id='datepicker']")
    WebElement dateBefore;

    @FindBy(xpath = "//input[@id='datepicker2']")
    WebElement dateAfter;

    @FindBy(xpath = "//button[normalize-space()='Filter']")
    WebElement btnFilter;

    @FindBy(xpath = "//input[@name='uploadfile']")
    WebElement btnFileOneOpenCV;

    @FindBy(xpath = "//input[@name='uploadfile2']")
    WebElement btnFileTwoOpenCV;

    @FindBy(xpath = "//input[@value='OK']")
    WebElement btnConfirmOpenCVOnline;

    @FindBy(xpath = "//*[@id=\"content\"]/span")
    WebElement resultOpenCV;

    @FindBy(xpath = "//b[normalize-space()='Go back']")
    WebElement btnBack;



    public SiloamUploadPage() {
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        PageFactory.initElements(driver,this);
    }

    public void executeThis() {
        int intDelay = 1;
        String baseUrl = "https://dev.ptdika.com/staging.siloam/panel/login/";
//        String baseUrl = "https://dev.ptdika.com/siloam/panel/login/";
        driver.get(baseUrl);
        driver.manage().window().maximize();

        fieldUsername.sendKeys("K6400001");
        delay(intDelay);
        fieldPassword.sendKeys("a");
        btnLogin.click();

        /** masuk menu homepage */
        delay(intDelay);
        linkMenuInput.click();

        /** masuk form input data */
        delay(intDelay);
        DataGenerator dataGenerator = new DataGenerator();
        Random r = new Random();

        fieldNama.sendKeys(dataGenerator.dataNamaLengkap());
        delay(intDelay);
        /** untuk pencarian nantinya */
        String strNoBPJS = String.valueOf(r.nextLong(1000000000000L,9999999999999L));
        fieldNomorBpjs.sendKeys(strNoBPJS);

        delay(intDelay);
        fieldNoKtp.sendKeys(String.valueOf(r.nextLong(1000000000000000L,9999999999999999L)));
        delay(intDelay);
        fieldAlamat.sendKeys(dataGenerator.dataAlamat());
        delay(intDelay);
        ddlKotaKTP.click();
        delay(intDelay);

        txtBoxFaskesAwal.sendKeys("KOTA JAKARTA SELATAN"+Keys.RETURN);
        delay(intDelay);
        fieldFaskesAwal.sendKeys("Klinik Pondok Pinang");
        delay(intDelay);
        ddlFaskesTujuan.click();
        delay(intDelay);

        txtBoxFaskesTujuan.sendKeys("Siloam Clinic Bona Indah || Kota Jakarta Selatan"+Keys.RETURN);
        btnSubmitFormInputData.click();

        /** masuk ke form upload document */

        String winHandleBefore = driver.getWindowHandle();
        String currentUrl = driver.getCurrentUrl();
//        System.out.println("Windows Handle Before "+winHandleBefore);
//        System.out.println("Current URL "+currentUrl);


        delay(intDelay);
        formUploadDocument.click();

        delay(1);
        uploadFotoFaskesTujuan.click();
        delay(intDelay);
        file.sendKeys(System.getProperty("user.dir")+"\\data\\foto-faskes-tujuan-1.png");
        delay(intDelay);
        btnSave.click();
        delay(intDelay);
        btnOK.click();


        delay(2);
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
                .until(ExpectedConditions.elementToBeClickable(uploadFotoAskesAwal)).click();
        delay(intDelay);
        file.sendKeys(System.getProperty("user.dir")+"\\data\\foto-faskes-awal-1.png");
        delay(intDelay);
        btnSave.click();
        delay(intDelay);
        btnOK.click();

        delay(intDelay);
        uploadTTDDigital.click();
        delay(intDelay);
        file.sendKeys(System.getProperty("user.dir")+"\\data\\ttd-digital-1.png");
        delay(intDelay);
        btnSave.click();
        delay(intDelay);
        btnOK.click();

        delay(intDelay);
        btnConfirmUploadDocument.click();
        delay(intDelay);
        /** konfirmasi secara javascript (by Enginge) dan menghilangkan alert konfirmasi */
        delay(intDelay);
        driver.switchTo().alert().accept();
        delay(intDelay);
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        delay(intDelay);
        btnSubmit.click();
        delay(intDelay);
        driver.switchTo().alert().accept();
        delay(intDelay);
        iconProfile.click();
        delay(intDelay);
        btnLogOut.click();

        fieldUsername.sendKeys("admindika");
        delay(intDelay);
        fieldPassword.sendKeys("d1k4@passw0rd");
        delay(intDelay);
        btnLogin.click();

        delay(intDelay);
        viewAndReport.click();

        delay(intDelay);
        dateBefore.sendKeys(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//2024-05-29
//        dateBefore.sendKeys("2024-05-28");
        delay(intDelay);
        dateAfter.sendKeys(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//        dateAfter.sendKeys("2024-05-28");
        delay(intDelay);
        btnFilter.click();

        /** locator xpath mutlak untuk setiap table pure html */
//        By dataRowsLocator = By.xpath(".//tr[not(position()=1)]");
        List<WebElement> allRows = driver.findElements(By.xpath(".//tr[not(position()=1)]"));
        System.out.println(allRows.size());
        String noBpjs="";
        String strLinkFaskesAwal = "";
        String strLinkFaskesTujuan = "";
        String strLinkPDF = "";

        boolean isValid = false;
        for (int i=0;i<allRows.size();i++) {
            List<WebElement> cells = allRows.get(i).findElements(By.tagName("td"));
            for (int j = 0; j < cells.size(); j++) {
                if(j==4){
                    noBpjs=cells.get(j).getText();

//                    if(noBpjs.equals("2454715683724")){
                    if(noBpjs.equals(strNoBPJS)){
                        System.out.println("NO BPJS : "+noBpjs);
                        isValid=true;
                    }
                }
                if(isValid && j==6){
                    strLinkFaskesAwal = cells.get(j).findElement(By.xpath(".//a")).getAttribute("href");
                }
                if(isValid && j==7){
                    strLinkFaskesTujuan = cells.get(j).findElement(By.xpath(".//a")).getAttribute("href");
                }
                if(isValid && j==8){
                    strLinkPDF = cells.get(j).findElement(By.xpath(".//a")).getAttribute("href");
                }
            }
            if(isValid){
                break;
            }
        }

        // strLinkFaskesAwal --> https://dev.ptdika.com/staging.siloam/upload/dokumen/1554/1554_Before_16f97c5b3736a8e0142b5e2456c4ccbd.png
        // strLinkFaskesTujuan --> https://dev.ptdika.com/staging.siloam/upload/dokumen/1554/1554_After_cc536c6cff0153c8aa287d6a42680eb2.png
        // strLinkPDF --> https://dev.ptdika.com/staging.siloam/upload/dokumen/1554/1554_agreement_1716985744.pdf

        System.out.println("Link Faskes Awal : "+strLinkFaskesAwal);
        System.out.println("Link Faskes Tujuan : "+strLinkFaskesTujuan);
        System.out.println("Link PDF : "+strLinkPDF);

        String pathRootDownload = "D:\\download-automation";
        GlobalFunction.checkAndCreateDirectory(pathRootDownload);
        String fileDownloadFaskesAwal = "\\"+new SimpleDateFormat("ddmmyyyyHHMMssSSS").format(new Date())+"_faskes-awal-1-DL.jpg";
        String fileDownloadFaskesTujuan = "\\"+new SimpleDateFormat("ddmmyyyyHHMMssSSS").format(new Date())+"_faskes-tujuan-1-DL.jpg";
        String fileDownloadPDF = "\\"+new SimpleDateFormat("ddmmyyyyHHMMssSSS").format(new Date())+"_pdf-1-DL.pdf";

        GlobalFunction.downloadImage(strLinkFaskesAwal,pathRootDownload+fileDownloadFaskesAwal);
        GlobalFunction.downloadImage(strLinkFaskesTujuan,pathRootDownload+fileDownloadFaskesTujuan);
        GlobalFunction.downloadImage(strLinkPDF,pathRootDownload+fileDownloadPDF);

        /** Open CV compare yg di download dengan yang di upload */
        driver.get("https://www.imgonline.com.ua/eng/similarity-percent.php");

        /** Open CV Untuk Compare Gambar Faskes Awal */
        delay(intDelay);
        btnFileOneOpenCV.sendKeys(System.getProperty("user.dir")+"\\data\\foto-faskes-awal-1.png");
        delay(intDelay);
        btnFileTwoOpenCV.sendKeys(pathRootDownload+fileDownloadFaskesAwal);
        delay(intDelay);
        btnConfirmOpenCVOnline.click();
        System.out.println("Result OpenCV Gambar Faskes Awal "+ resultOpenCV.getText());

        delay(intDelay);
        btnBack.click();

        delay(intDelay);
        /** Open CV Untuk Compare Gambar Faskes Tujuan */
        delay(intDelay);
        btnFileOneOpenCV.sendKeys(System.getProperty("user.dir")+"\\data\\foto-faskes-tujuan-1.png");
        delay(intDelay);
        btnFileTwoOpenCV.sendKeys(pathRootDownload+fileDownloadFaskesTujuan);
        delay(intDelay);
        btnConfirmOpenCVOnline.click();
        System.out.println("Result OCR Gambar Faskes Tujuan "+ resultOpenCV.getText());


        /** OCR Baca data dari PDF */
        System.out.println(GlobalFunction.generateTextOCR(pathRootDownload+fileDownloadPDF));


    }

    private void delay(int intDetik){
        if(Constants.GLOB_PARAM_DELAY.equalsIgnoreCase("y")){
            try {
                Thread.sleep(intDetik*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

//        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
//                .until(ExpectedConditions.visibilityOf(uploadFotoAskesAwal)).click();
//        driver.switchTo().frame(0);
//        driver.switchTo().defaultContent();
//        driver.navigate().refresh();
//        driver.switchTo().activeElement();
//        driver.switchTo().window(winHandleBefore);
//        ((JavascriptExecutor)driver).executeScript("return arguments[0].click();", uploadFotoAskesAwal);