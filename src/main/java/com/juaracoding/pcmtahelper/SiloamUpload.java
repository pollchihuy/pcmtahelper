package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.DataGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class SiloamUpload {

    public static void main(String[] args) {
        int intDelay = 1;
        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://dev.ptdika.com/siloam/panel/login/";
        driver.get(baseUrl);
        driver.manage().window().maximize();

        WebElement fieldUsername = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        WebElement fieldPassword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));

        fieldUsername.sendKeys("K6400001");
        delay(intDelay);
        fieldPassword.sendKeys("a");
        btnLogin.click();

        /** masuk menu homepage */
        delay(intDelay);
        WebElement linkMenuInput = driver.findElement(By.xpath("//a[@href='https://dev.ptdika.com/siloam/sales/input']"));
        linkMenuInput.click();

        /** masuk form input data */
        delay(intDelay);
        WebElement fieldNama = driver.findElement(By.xpath("//input[@id='name']"));
        WebElement fieldNomorBpjs = driver.findElement(By.xpath("//input[@id='no_bpjs']"));
        WebElement fieldNoKtp = driver.findElement(By.xpath("//input[@id='no_ktp']"));
        WebElement fieldAlamat = driver.findElement(By.xpath("//textarea[@id='address']"));
        WebElement ddlKotaKTP = driver.findElement(By.xpath("//span[@id='select2-ktp_city-container']"));//KOTA JAKARTA SELATAN
        WebElement fieldFaskesAwal = driver.findElement(By.xpath("//input[@id='origin_faskes']"));//Klinik Pondok Pinang
        WebElement ddlFaskesTujuan = driver.findElement(By.xpath("//span[@id='select2-destination_faskes-container']"));//KOTA JAKARTA SELATAN
        WebElement btnSubmitFormInputData = driver.findElement(By.xpath("//button[@type='submit']"));//KOTA JAKARTA SELATAN


        DataGenerator dataGenerator = new DataGenerator();
        Random r = new Random();
        fieldNama.sendKeys(dataGenerator.dataNamaLengkap());
        delay(intDelay);
        fieldNomorBpjs.sendKeys(String.valueOf(r.nextLong(1000000000000L,9999999999999L)));
        delay(intDelay);
        fieldNoKtp.sendKeys(String.valueOf(r.nextLong(1000000000000000L,9999999999999999L)));
        delay(intDelay);
        fieldAlamat.sendKeys(dataGenerator.dataAlamat());
        delay(intDelay);
        ddlKotaKTP.click();
        delay(intDelay);
        WebElement txtBoxFaskesAwal = driver.findElement(By.xpath("//input[@role='textbox']"));//Klinik Pondok Pinang
        txtBoxFaskesAwal.sendKeys("KOTA JAKARTA SELATAN"+Keys.RETURN);
        delay(intDelay);
        fieldFaskesAwal.sendKeys("Klinik Pondok Pinang");
        delay(intDelay);
        ddlFaskesTujuan.click();
        delay(intDelay);
        WebElement txtBoxFaskesTujuan = driver.findElement(By.xpath("//input[@role='textbox']"));
        txtBoxFaskesTujuan.sendKeys("Siloam Clinic Bona Indah || Kota Jakarta Selatan"+Keys.RETURN);
        btnSubmitFormInputData.click();

        /** masuk ke form upload document */

        String winHandleBefore = driver.getWindowHandle();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Windows Handle Before "+winHandleBefore);
        System.out.println("Current URL "+currentUrl);
        WebElement formUploadDocument = driver.findElement(By.xpath("//a[@class='nav-link active']//span[@class='d-sm-block d-none']"));
        WebElement uploadFotoAskesAwal = driver.findElement(By.xpath("//span[normalize-space()='Foto Faskes Awal']"));
        WebElement file = driver.findElement(By.xpath("//input[@id='file']"));
        WebElement btnSave = driver.findElement(By.xpath("//button[@id='btnSave']"));
        WebElement uploadTTDDigital = driver.findElement(By.xpath("//span[normalize-space()='Tanda Tangan Digital']"));
        WebElement uploadFotoFaskesTujuan = driver.findElement(By.xpath("//span[normalize-space()='Foto Faskes Tujuan']"));
        WebElement formInputTitle = driver.findElement(By.xpath("//h1[@class='page-header']"));

        delay(intDelay);
        formUploadDocument.click();

//        System.out.println("Form Input Title : "+formInputTitle.getText());
        delay(1);
                ((JavascriptExecutor)driver).executeScript("return arguments[0].click();", uploadFotoFaskesTujuan);
//        uploadFotoFaskesTujuan.click();
        delay(intDelay);
        file.sendKeys(System.getProperty("user.dir")+"\\data\\foto-faskes-tujuan-1.png");
        delay(intDelay);
        btnSave.click();
        delay(intDelay);
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
//        WebElement btnOK = driver.findElement(By.xpath("//button[@class='swal-button swal-button--confirm']"));
//        btnOK.click();


        delay(2);
//        formUploadDocument.click();
        System.out.println("Window Handle Before "+winHandleBefore);
        driver.get(currentUrl);
//        driver.switchTo().window(winHandleBefore);
        delay(2);

        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
                    .until(ExpectedConditions.elementToBeClickable(uploadFotoAskesAwal)).click();
//            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ui-lib-popup-element__close']//input"))).click();
            System.out.println("Element was clicked");
            delay(intDelay);
            file.sendKeys(System.getProperty("user.dir")+"\\data\\foto-faskes-awal-1.png");
            delay(intDelay);
            btnSave.click();
            delay(intDelay);
            //button[@class='swal-button swal-button--confirm']
//        btnOK.click();
        }
        catch(Exception e) {
            System.out.println(e.getMessage() +" Element wasn't clicked");
        }
//        uploadFotoAskesAwal.click();





        delay(intDelay);
        uploadTTDDigital.click();
        delay(intDelay);
        file.sendKeys(System.getProperty("user.dir")+"\\data\\ttd-digital-1.png");
        delay(intDelay);
        btnSave.click();
        delay(intDelay);
    }

    public static  void delay(int intDetik){
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