package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PendingEmailVerificationPage {

    WebDriver driver;
    WebDriverWait wdwait;
    WebElement registerANewAccount;
    WebElement emailVerificationMessage;
    WebElement signInButton;

    public PendingEmailVerificationPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }


    public WebElement getRegisterANewAccount() {
        return driver.findElement(By.cssSelector(".h3.text-primary.font-weight-normal.mb-2"));
    }

    public WebElement getEmailVerificationMessage() {
        return driver.findElement(By.cssSelector(".alert.alert-info"));

    }

    public WebElement getSignInButton() {
        return driver.findElement(By.cssSelector(".nav-item.my-2.my-md-0"));
    }
    //.....................................

    public void clickOnSignInButton(){
        this.getSignInButton().click();
    }
}