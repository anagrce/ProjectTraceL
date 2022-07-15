package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class RegisterNewAccountPage {

    //Nazivi metoda u page klasama treba da budu opisane
    // akcije koje radimo kako bi neko mogao da procita naredne linije i razume sta se radi

    WebDriver driver;

    WebDriverWait wdwait;
    WebElement usernameTextBox;
    WebElement emailTextBox;
    WebElement confirmEmailTextBox;
    WebElement passwordTextBox;
    WebElement confirmPasswordTextBox;
    WebElement confirmTermsAndConditionsCheckBox;
    WebElement subscribeCheckBox;
    WebElement notRobotCheckBox;
    WebElement createAnAccountButton;
    WebElement gotItButton;
    WebElement userNameIsInvalidMessage;
    WebElement invalidEmailMessage;
    WebElement reenterEmailMessage;
    WebElement passwordMustBeAtLeastMessage;
    WebElement confirmPasswordMessage;
    WebElement pleaseAcceptTermsMessage;
    WebElement invalidCaptchaMessage;





    public RegisterNewAccountPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement getUsernameTextBox() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtUserName"));
    }

    public WebElement getEmailTextBox() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtEmail"));
    }

    public WebElement getConfirmEmailTextBox() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtConfirmEmail"));
    }

    public WebElement getPasswordTextBox() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtPassword"));
    }

    public WebElement getConfirmPasswordTextBox() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtPassword2"));
    }

    public WebElement getConfirmTermsAndConditionsCheckBox() {
        return driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_maindiv\"]/div[5]/div/label"));



    }

    public WebElement getSubscribeCheckBox() {
        return driver.findElement(By.id("ContentPlaceHolder1_SubscribeNewsletter"));
    }

    public WebElement getGotItButton() {
        return driver.findElement(By.xpath("//*[@id=\"btnCookie\"]"));
    }

    public WebElement getNotRobotCheckBox() {
        return driver.findElement(By.xpath("xpath_of_reCaptcha_checkbox"));
    }

    public WebElement getCreateAnAccountButton() {
        return driver.findElement(By.id("ContentPlaceHolder1_btnRegister"));
    }

    public WebElement getUserNameIsInvalidMessage() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtUserName-error"));
    }

    public WebElement getInvalidEmailMessage() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtEmail-error"));
    }

    public WebElement getReenterEmailMessage() {
        return driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txtConfirmEmail-error\"]"));
    }

    public WebElement getPasswordMustBeAtLeastMessage() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtPassword-error"));
    }

    public WebElement getConfirmPasswordMessage() {
        return driver.findElement(By.id("ContentPlaceHolder1_txtPassword2-error"));
    }

    public WebElement getPleaseAcceptTermsMessage() {
        return driver.findElement(By.id("ctl00$ContentPlaceHolder1$MyCheckBox-error"));
    }

    public WebElement getInvalidCaptchaMessage() {
        return driver.findElement(By.cssSelector(".alert.alert-danger"));
    }
    //---------------------------------------------------------

    public void insertUsernameTextBox(String username){
        this.getUsernameTextBox().clear();
        this.getUsernameTextBox().sendKeys(username);
    }
    public void insertEmailAddress(String email){
        this.getEmailTextBox().clear();
        this.getEmailTextBox().sendKeys(email);
    }
    public void confirmEmailAddress(String email){
        this.getConfirmEmailTextBox().clear();
        this.getConfirmEmailTextBox().sendKeys(email);
    }
    public void insertPassword(String password){
        this.getPasswordTextBox().clear();
        this.getPasswordTextBox().sendKeys(password);
    }
    public void confirmPassword(String password){
        this.getConfirmPasswordTextBox().clear();
        this.getConfirmPasswordTextBox().sendKeys(password);
    }
   /*public void checkOnTermsAndConditionsCheckBox(){
   //     Action action=new Action(driver);
     //   action(driver).moveToElement(confirmTermsAndConditionsCheckBox,1,1).click().perform();
        this.getConfirmTermsAndConditionsCheckBox().click();
    }*/

    public void clickElementsJS(WebElement element){
    ((JavascriptExecutor)driver).executeScript
    ("arguments[0].click()",element);
}
   public void scrollInoViewJS(WebElement element){
    ((JavascriptExecutor)driver).executeScript
            ("arguments[0].scrollIntoView()", element);
}

    private void executeScript(String s, WebElement confirmTermsAndConditionsCheckBox) {
    }


    public void checkOnSubscribeCheckBox(){
        this.getSubscribeCheckBox().click();
    }

    public void clickOnGotItButton(){
        this.gotItButton.click();
    }
    public void checkNotRobotCheckBox(){
        this.getNotRobotCheckBox().click();
    }
    public void clickOnRegisterButton(){
        this.getCreateAnAccountButton().click();
    }
}
