package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class InventoryDatePicker {
    private WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement fieldUsername;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement fieldPassword;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement btnLogin;
    @FindBy(xpath = "//a[normalize-space()='Data Asset']")
    private WebElement linkMenuDataAset;

    @FindBy(xpath = "//a[@title='Input Data']")
    private WebElement btnInputData;

    @FindBy(xpath = "//input[@name='purchase_date']")
    private WebElement dateTglPembelian;

    public InventoryDatePicker() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        PageFactory.initElements(driver,this);
    }

    public void eksekusi(){

        int intDelay = 1;
        String baseUrl = "https://dev.ptdika.com/staging.inventory/";
        driver.get(baseUrl);
        driver.manage().window().maximize();

        fieldUsername.sendKeys("admindika");
        delay(intDelay);
        fieldPassword.sendKeys("d1k4@passw0rd");
        delay(intDelay);
        btnLogin.click();
        delay(intDelay);

        linkMenuDataAset.click();
        delay(intDelay);
        btnInputData.click();
        delay(intDelay);
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        /** format nya default datepicker itu yyyy-mm-dd
         * tahun-bulan-tanggal
         * nah usahakan buat data nya seperti ini, lalu sendKeys saja.....
         */
        dateTglPembelian.sendKeys("2001-12-01");
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