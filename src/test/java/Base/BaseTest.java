package Base;

import Pages.PendingEmailVerificationPage;
import Pages.RegisterNewAccountPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    //U ovom ispod delu deklarisem driver, webdriverwait ako bude potrebe i sve stranice koje cu da testiram


    public WebDriver driver;
    public WebDriverWait wdwait;
    public RegisterNewAccountPage registerNewAccountPage;
    public PendingEmailVerificationPage pendingEmailVerificationPage;
    public ExcelReader excelReader;

    //Zbog toga sto koristim TestNG ovde ubacujem BeforeClass, taj deo koda se izvrsava samo jednom pre pocetka testiranja
    //Before se izvrsava pre svakog testa i unutar te metode ubacujem sta ocekujem da se uradi pre svakog testa


    @BeforeClass
    public void setUp() throws IOException {
       WebDriverManager.chromedriver().setup();

        driver= new ChromeDriver();
        wdwait= new WebDriverWait(driver, Duration.ofSeconds(10));
        excelReader = new ExcelReader("C:\\Users\\Grce\\Desktop\\TestData1.xlsx");
        registerNewAccountPage= new RegisterNewAccountPage(driver,wdwait);
        pendingEmailVerificationPage = new PendingEmailVerificationPage(driver,wdwait);


    }

    public void visibilityWait(WebElement element){
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }
    public void clickabilityWait(WebElement element){
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void scrollIntoView(WebElement element){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void tearDown(){
        //driver.close();
        //driver.quit();
    }

}
