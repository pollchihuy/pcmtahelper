package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.GlobalFunction;
import com.juaracoding.pcmtahelper.util.ImageComparassion;
import com.juaracoding.pcmtahelper.util.OpenCVFunction;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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

//    /html/body/div[1]/div[1]/section[2]/div[1]/form/div/div[2]/div/div[2]/div[1]/img
//    @FindBy(xpath = "//img[@src = 'https://dev.ptdika.com/staging.sociola/upload/Foto_Struk_EDC_2024-05-26_1716727424.png']")
    @FindBy(xpath = "//*[@id=\"div-Foto_Struk_EDC\"]/img")
    private WebElement panelGambarSatu;

    @FindBy(xpath = "//*[@id=\"previewing_edc\"]")
    private WebElement elementGambarPanel;

    @FindBy(xpath = "//div[@id='div-Foto_Struk_EDC']//a[1]")
    private WebElement btnRotate90;

    @FindBy(xpath = "//a[@href = 'javascript:;' and @id = 'btnRotate' and (text() = '180' or . = '180')]")
    private WebElement btnRotate180;

    @FindBy(xpath = "//div[@id='div-Foto_Struk_EDC']//a[3]")
    private WebElement btnRotate270;

    @FindBy(xpath = "//input[@name='uploadfile']")
    WebElement btnFileOneOpenCV;

    /** kasih informasi minimal disini */
    @FindBy(xpath = "//input[@name='uploadfile2']")
    WebElement btnFileTwoOpenCV;

    @FindBy(xpath = "//input[@value='OK']")
    WebElement btnConfirmOpenCVOnline;

    @FindBy(xpath = "//*[@id=\"content\"]/span")
    WebElement resultOpenCV;

    @FindBy(xpath = "//b[normalize-space()='Go back']")
    WebElement btnBack;


    public SociolaRotateImage() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        PageFactory.initElements(driver,this);
    }

    public void eksekusi(){

        String strSociId = "SOCIOLLASBY0132";
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

        String parentWindow = driver.getWindowHandle();
        System.out.println(" Windows Pertama / Parent : " +parentWindow);
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> i1 = allWindowHandles.iterator();
        String childWindow = "";
        while (i1.hasNext()){
            childWindow = i1.next();

            System.out.println(" Windows Baru / Child : " +childWindow);
            if(!parentWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                delay(intDelay);
                btnRotate180.click();
                delay(intDelay);
                String linkGambarPanel1=panelGambarSatu.getAttribute("src");
                System.out.println("SOURCE -> "+linkGambarPanel1);

                String pathRootDownload = "D:\\download-automation";
                String fileDownloadRotate180 = "\\"+new SimpleDateFormat("ddmmyyyyHHMMssSSS").format(new Date())+"_gambar-DL-rotate-180.jpg";
                GlobalFunction.downloadImage(linkGambarPanel1,pathRootDownload+fileDownloadRotate180);

                /** gambar untuk compare */
                String pathGambarSumber = System.getProperty("user.dir")+"\\data\\Struk-Awal.jpg";
                String pathGambarDestiny = pathRootDownload+"\\Struk-Awal-Rotate-180.jpg";
                OpenCVFunction.rotateImage(pathGambarSumber,pathGambarDestiny,180);

//                ImageComparassion.calculateDifferences(pathRootDownload+fileDownloadRotate180,1,pathGambarDestiny,1);

                /** Open CV compare yg di download dengan yang di upload */
                driver.get("https://www.imgonline.com.ua/eng/similarity-percent.php");

                /** Open CV Untuk Compare Gambar Faskes Awal */
                delay(intDelay);
                btnFileOneOpenCV.sendKeys(pathGambarDestiny);
                delay(intDelay);
                btnFileTwoOpenCV.sendKeys(pathRootDownload+fileDownloadRotate180);
                ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
                delay(intDelay);
                btnConfirmOpenCVOnline.click();
                System.out.println("Compare Rotate 180 "+ resultOpenCV.getText());
            }

        }
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
