package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CanvaDownloadImage {
    private WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement fieldUsername;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[@class='login100-form-btn']")
    private WebElement btnLogin;

    @FindBy(xpath = "//p[normalize-space()='Report Data']")
    private WebElement linkMenuReport;

    @FindBy(xpath = "//select[@id='status']")
    private WebElement selectAll;

    @FindBy(xpath = "//span[@class='fa fa-filter']")
    private WebElement btnFilter;

    @FindBy(xpath = "//a[@id='btn_excel']")
    private WebElement btnDownloadExcel;

    @FindBy(xpath = "//a[@href='https://dev.ptdika.com/staging.sociola/panel/logout']")
    private WebElement btnLogout;
    @FindBy(xpath = "//div[@id='table_info']")
    private WebElement labelRecordsInfo;

    @FindBy(xpath = "//input[@name='start_date']")
    private WebElement datePeriodeAwal;

    @FindBy(xpath = "//input[@name='end_date']")
    private WebElement datePeriodeAkhir;

    @FindBy(xpath = "//button[@id='btn-filter']")
    private WebElement btnFilterDashboard;

    @FindBy(xpath = "//p[normalize-space()='Dashboard']")
    private WebElement linkMenuDashboard;

    @FindBy(xpath = "//*[@id=\":r1j:\"]/div/div/div/div/div[3]/form/div/div[1]/div/div[1]/div/div[1]/div/div/div/div/div/button/span/div/div/div/img")
    private WebElement panel1;
    @FindBy(xpath = "//*[@id=\":r1j:\"]/div/div/div/div/div[3]/form/div/div[1]/div/div[1]/div/div[2]/div/div/div/div/div/button/span/div/div/div/img")
    private WebElement panel2;
    @FindBy(xpath = "//*[@id=\":r1j:\"]/div/div/div/div/div[3]/form/div/div[1]/div/div[1]/div/div[3]/div/div/div/div/div/button/span/div/div/div/img")
    private WebElement panel3;
    @FindBy(xpath = "//*[@id=\":r1j:\"]/div/div/div/div/div[3]/form/div/div[1]/div/div[1]/div/div[4]/div/div/div/div/div/button/span/div/div/div/img")
    private WebElement panel4;

    @FindBy(xpath = "//textarea[@id=':r2j:']")
    private WebElement areaText;

    @FindBy(xpath = "//span[normalize-space()='Generate image']")
    private WebElement btnGenerate;


    private ExcelReader excelReader;
    public CanvaDownloadImage() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        PageFactory.initElements(driver,this);
    }

    public void eksekusi(String text){

        int intDelay = 1;
        String baseUrl = "https://www.canva.com/design/DAGHLXskzus/X3y99yv8oDCj0sYIi_2KNA/edit?ui=eyJFIjp7IkE_IjoiViIsIkIiOiJCIn0sIkciOnsiQiI6dHJ1ZX19";
        driver.get(baseUrl);
        driver.manage().window().maximize();
        String pathRootDownload = "D:\\download-automation";

        delay(1);
        areaText.sendKeys(text);
        delay(1);
        btnGenerate.click();
        delay(20);

        String url1 = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
                .until(ExpectedConditions.visibilityOf(panel1)).getAttribute("src");
        delay(1);
        String url2 = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
                .until(ExpectedConditions.visibilityOf(panel2)).getAttribute("src");
        String url3 = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
                .until(ExpectedConditions.visibilityOf(panel3)).getAttribute("src");
        String url4 =new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_DELAY))
                .until(ExpectedConditions.visibilityOf(panel4)).getAttribute("src");

        System.out.println(url1);
        System.out.println(url2);
        System.out.println(url3);
        System.out.println(url4);





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