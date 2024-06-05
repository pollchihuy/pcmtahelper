package com.juaracoding.pcmtahelper;

import com.juaracoding.pcmtahelper.connection.Constants;
import com.juaracoding.pcmtahelper.util.ExcelReader;
import com.juaracoding.pcmtahelper.util.GlobalFunction;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class InventoryDelete {
    private WebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement fieldUsername;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement btnLogin;

    @FindBy(xpath = "//a[normalize-space()='Lokasi']")
    private WebElement linkMenuLokasi;

    @FindBy(xpath = "//div[@id='dataTables-example_info']")
    private WebElement labelInfoEntries;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement btnFilter;

    @FindBy(xpath = "//td[@class='dataTables_empty']")
    private WebElement labelRecordNotFound;

    @FindBy(xpath = "//a[@id='btn_excel']")
    private WebElement btnDownloadExcel;

    @FindBy(xpath = "//a[@href='https://dev.ptdika.com/staging.sociola/panel/logout']")
    private WebElement btnLogout;
    @FindBy(xpath = "//div[@id='table_info']")
    private WebElement labelRecordsInfo;

    private int entriesAwal =0;
    private int entriesAkhir =0;
    private int selisih =0;
    private Random r = new Random();

    private ExcelReader excelReader;
    public InventoryDelete() {
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

        linkMenuLokasi.click();
        delay(intDelay);

        /** random dari 10 data di page 1 untuk di delete */
        int getData = r.nextInt(11);
        String strLokasi = "";
        WebElement btnDelete = null;

        Boolean isValid = false;
        List<WebElement> allRows = driver.findElements(By.xpath(".//tr[not(position()=1)]"));
        System.out.println("jumlah data per page / di dalam tabel page 1 -> "+allRows.size());
        /** JADI STEP NYA
         *  1. PILIH DATA YANG AKAN DI DELETE DI FILTER DULU SEBELUM DI DELETE
         *  2. KEMUDIAN FILTER DATA BERDASARKAN INFORMASI LOKASI YANG SUDAH DISIMPAN DI VARIABEL
         *  3. ENTRIES AWAL NYA BERAPA ITU DITAMPUNG KE DALAM VARIABEL entriesAwal , MISAL WAKTU DI FILTER ADALAH 3
         *  4. KEMUDIAN FILTERNYA DIKOSONGKAN AGAR KEMBALI KE DATA SEPERTI SEMULA
         *  5. SELANJUTNYA DELETE DATANYA
         *  6. LALU FILTER LAGI MENGGUNAKAN LOKASI TADI
         *  7. TAMPUNG KE VARIABEL entriesAkhir
         *  8. SEHARUSNYA HASILNYA ADALAH 2 SEHINGGA KALAU DIKURANGI SELISIH ENTRIES NYA MENJADI 1, ARTINYA FUNGSI DELETE NYA SUDAH BERJALAN
         */

        /** step 1 */
        for (int i=0;i<allRows.size();i++) {
            List<WebElement> cells = allRows.get(i).findElements(By.tagName("td"));

            if(i==getData){
                isValid = true;
                for (int j = 0; j < cells.size(); j++) {
                    if(j==2){
                        strLokasi = cells.get(j).getText();
                    }
                    List<WebElement> span = cells.get(j).findElements(By.tagName("a"));
                    for (int k = 0; k < span.size(); k++) {
                        if(k==1){
                            btnDelete = span.get(k);
                        }
                    }
                }
            }
            if(isValid){
                break;
            }
        }
        /** step 2 */
        btnFilter.sendKeys(strLokasi+ Keys.RETURN);
        delay(intDelay);
        /** step 3 */
        int entriesAwal = Integer.parseInt(labelInfoEntries.getText().split(" ")[5]);
        delay(intDelay);
        System.out.println("total Records awal : "+entriesAwal);
        delay(intDelay);
        /** step 4 */
        btnFilter.sendKeys(""+ Keys.RETURN);
        delay(intDelay);
//        ((JavascriptExecutor)driver).executeScript("return arguments[0].click();", btnDelete);
        /** step 5 */
        btnDelete.click();
        delay(intDelay);
        driver.switchTo().alert().accept();
        delay(intDelay);
        /** step 6 */
        btnFilter.sendKeys(strLokasi+ Keys.RETURN);
        /** step 7 */
        int entriesAkhir = Integer.parseInt(labelInfoEntries.getText().split(" ")[5]);
        int selisih = entriesAkhir - entriesAwal;
        System.out.println("Selisih -> "+selisih);

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