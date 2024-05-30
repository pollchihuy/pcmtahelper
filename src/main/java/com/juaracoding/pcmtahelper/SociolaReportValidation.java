package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.ExcelReader;
import com.juaracoding.pcmtahelper.util.GlobalFunction;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SociolaReportValidation {

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
    private ExcelReader excelReader;
    public SociolaReportValidation() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        PageFactory.initElements(driver,this);
    }

    public void eksekusi(){

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

        linkMenuReport.click();
        delay(intDelay);
        Select s = new Select(selectAll);
//        s.selectByValue("All");
        s.selectByVisibleText("All");
        delay(intDelay);
        btnFilter.click();
        delay(intDelay);

        String strRecordsInfo = labelRecordsInfo.getText();
        int intTotalRecords = Integer.parseInt(strRecordsInfo.split(" ")[5]);
        delay(intDelay);

        System.out.println("total Records : "+intTotalRecords);

        delay(intDelay);
        /** pastikan setelah proses download file nya selesai, hapus file yg sebelumnya di download */
        String pathFileReport = "C:\\Users\\pollc\\Downloads\\merchant.xls";
        GlobalFunction.deleteFile(pathFileReport);
        delay(intDelay);
        btnDownloadExcel.click();

        delay(intDelay);
        GlobalFunction.htmlToExcelXLSX(pathFileReport,"merchant");
        excelReader = new ExcelReader(pathFileReport+"x","merchant");
        int intAllDataWithoutHeader = excelReader.getRowCountWH();
        System.out.println("Seluruh Data Dari File Excel : "+intAllDataWithoutHeader);
//        https://ipolcore.ipol.im/demo/clientApp/demo.html?id=63&key=A9666882F0EB85527637BACB617CD9F6
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