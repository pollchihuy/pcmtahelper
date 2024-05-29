package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.GlobalFunction;
import com.juaracoding.pcmtahelper.util.ImageComparassion;
import com.juaracoding.pcmtahelper.util.OpenCVFunction;
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

public class SociolaRotateImage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement fieldUsername;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[@class='login100-form-btn']")
    private WebElement btnLogin;

    @FindBy(xpath = "//p[normalize-space()='Verifikasi']")
    private WebElement linkToMenuVerifikasi;

    @FindBy(xpath = "//input[@class='form-control form-control-sm']")
    private WebElement fieldFilter;

    @FindBy(xpath = "//a[@href='https://dev.ptdika.com/staging.sociola/panel/logout']")
    private WebElement btnLogout;

    @FindBy(xpath = "//i[@class='fa fa-eye']")
    private WebElement btnEditRecords;

    @FindBy(xpath = "//img[@src = 'https://dev.ptdika.com/staging.sociola/upload/Foto_Struk_EDC_2024-05-26_1716727424.png']")
    private WebElement panelGambarSatu;

    @FindBy(xpath = "//*[@id=\"previewing_edc\"]")
    private WebElement elementGambarPanel;

    @FindBy(xpath = "//div[@id='div-Foto_Struk_EDC']//a[1]")
    private WebElement btnRotate90;

    @FindBy(xpath = "//a[@href = 'javascript:;' and @id = 'btnRotate' and (text() = '180' or . = '180')]")
    private WebElement btnRotate180;

    @FindBy(xpath = "//div[@id='div-Foto_Struk_EDC']//a[3]")
    private WebElement btnRotate270;


    public SociolaRotateImage() {
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        PageFactory.initElements(driver,this);
    }

    public void eksekusi(){

        String strSociId = "SOCIOLLASBY0136";
        int intDelay = 1;
        String baseUrl = "https://dev.ptdika.com/staging.sociola/login";
//        String baseUrl = "https://dev.ptdika.com/sociola/login";
        driver.get(baseUrl);
        driver.manage().window().maximize();

        fieldUsername.sendKeys("admintiara2");
        delay(intDelay);
        fieldPassword.sendKeys("a");
        delay(intDelay);
        btnLogin.click();
        delay(intDelay);

        linkToMenuVerifikasi.click();
        delay(intDelay);
        fieldFilter.sendKeys(strSociId+ Keys.RETURN);
        delay(intDelay);
        fieldFilter.sendKeys(strSociId+ Keys.RETURN);
        delay(intDelay);

        btnEditRecords.click();
        delay(intDelay);
        btnRotate180.click();
//        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
//            .until(ExpectedConditions.elementToBeClickable(btnRotate180)).click();
//        ((JavascriptExecutor)driver).executeScript("return arguments[0].click();",btnRotate180);
//        String linkGambarPanel1 = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
//                .until(ExpectedConditions.visibilityOf(panelGambarSatu)).getAttribute("src");
//        String linkGambarPanel1=panelGambarSatu.getAttribute("src");
        delay(intDelay);
        String linkGambarPanel1=panelGambarSatu.findElement(By.xpath(".//img")).getAttribute("src");
        System.out.println("Link Panel : "+linkGambarPanel1);

        String pathRootDownload = "D:\\download-automation";
        String fileDownloadRotate180 = "\\"+new SimpleDateFormat("ddmmyyyyHHMMssSSS").format(new Date())+"_gambar-DL-rotate-180.jpg";
        GlobalFunction.downloadImage(linkGambarPanel1,pathRootDownload+fileDownloadRotate180);

        /** gambar untuk compare */
        String pathGambarSumber = System.getProperty("user.dir")+"\\data\\gambar-awal.png";
        String pathGambarDestiny = pathRootDownload+"\\gambar-rotate-180.png";
        OpenCVFunction.rotateImage(pathGambarSumber,pathRootDownload+"\\",180);

        ImageComparassion.calculateDifferences(pathRootDownload+fileDownloadRotate180,1,pathGambarDestiny,1);
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
