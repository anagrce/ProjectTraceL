package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPracticeTest extends BaseTest {

    //Uvod: Tokom testiranja koristim IntelliJ Idea u kome kreiram Maven projekat
    // zbog toga sto mi je lakse da ubacujem biblioteke preko pom fajla

    //Prilikom testiranja koristim POM jer je laksi za odrzavanje, lakse se prati rad i elementi
    // se definisu na samo jednom mestu

    //Koristim i TestNG i koristim Before, After i Test anotacije

    //Da izbegnem hardkodiranje koristim DataDrivenTesting kako lakse mogu da menjam/dodajem
    // testne podatke ili uporedjujem dobijeni rezultat sa ocekivanim rezultatom

    //Testiranje vrsim na Chrome browseru jer prema izvoru Chrome koristi trenutno 67% korisnika na svetu preko Desktopa
    //Izvor: https://gs.statcounter.com/browser-market-share/desktop/worldwide

    //Ako bude potrebe da se testira i na drugim browserima samo treba zameniti driver i ubaciti u directory.
    // Na primer gecko driver za Firefox.



    String validUsername= "Username11111";
    String validEmail="email@gmail.com";
    String invalidEmail="adresa@gmail.com";
   // String invalidFormEmail = "adresa@adresa";

    String validPassword= "password";
    String invalidUsername= "pogresan username";
    String invalidPassword= "pogresan password";

    //ovo je kod za citanje podataka iz excel tabele
    //String validUsername = excelReader.getStringData("Register", 1, 0);
    //String validEmail= excelReader.getStringData("Register", 2,0);
    //String validPassword= excelReader.getStringData("Register", 3,0);



    //Putanja koju prosledjujem za citanje excel fajla je jedinstvena za moj racunar
    // i mora se promeniti putanja ako se testiranje vrsi na drugom racunaru

    @BeforeMethod
    public void pageSetUp(){
        driver.manage().window().maximize();
        driver.navigate().to("https://etherscan.io/register");
    }

    //Testiranjem da li su polja obavezno prvo odradim klik na dugme bez unosa i proverim da li sam na istoj stranici

   //Nakon toga proveravam unos samo jednog polja pa unos samo drugog polja da se uverim da su oba polja obavezna

   //Potencijalni bug je sto za dva razlicita elementa na aplikaciji je podesen isti ID - tekst koji se
   // ispisuje iznad oba polja ima isti ID
   //ID bi trebalo da sadrzi jedinstven naziv

    //Sto vise assertova postavimo to je nas test pouzdaniji

    @Test(priority = 10)
    public void successfulLogin() throws InterruptedException {
        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());

        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());

        Thread.sleep(15000);
        //Sustina je da capcha ne moze da se automatizuje, jer je upravo zbog toga i napravljena, da razlikuje coveka od robota.
        //Thread sleep sam stavila jer je to najlaksi nacin i da se rucno cekira captcha dok program ceka
        //u praksi ce developeri da sklone capchu i moze da se radi bez toga
        //Drugi nacine je da se salje takin i to je prilicno komplikovano

        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
       // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        Assert.assertTrue(pendingEmailVerificationPage.getRegisterANewAccount().isDisplayed());

        //proveravamo da li se pojavila poruka koja korisniku govori da mu je poslata poruka na mejl zbog zavrsetka registracije
        Assert.assertTrue(pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed());

    }

    @Test (priority=20)
    public void cantRegisterWithoutFillingMandatoryTextBoxFields(){
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        registerNewAccountPage.clickOnRegisterButton();

        //proveravamo da li su se pojavile poruke upozorenja kod svoakog obaveznog polja koje treba popuniti da bi se
       // registrovao novi korisnik. Pojavile su se, korisnik se nije registrovao
        Assert.assertTrue(registerNewAccountPage.getUserNameIsInvalidMessage().isDisplayed());

        Assert.assertTrue(registerNewAccountPage.getInvalidEmailMessage().isDisplayed());

        Assert.assertTrue(registerNewAccountPage.getReenterEmailMessage().isDisplayed());

        Assert.assertTrue(registerNewAccountPage.getInvalidEmailMessage().isDisplayed());

        Assert.assertTrue(registerNewAccountPage.getConfirmPasswordMessage().isDisplayed());

        Assert.assertTrue(registerNewAccountPage.getPleaseAcceptTermsMessage().isDisplayed());
    }

    @Test (priority = 30)
    public void  userCanNotRegisterWithoutFillingUsernameTextBox() throws InterruptedException {

        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());

        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());

        Thread.sleep(15000);

        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //provera da li se pojavila poruka upozorenja da je neispravan Username
        Assert.assertTrue(registerNewAccountPage.getUserNameIsInvalidMessage().isDisplayed());

        //provera da li se pojavila poruka da treba verifikovati Email adresu, nije se pojavila, nije registrovan novi korisnik
        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);
    }

    @Test (priority = 40)
    public void userCanNotRegistreWithoutFillingEmailAndConfirmEmailTextBoxes() throws InterruptedException {

        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());

        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());

        Thread.sleep(15000);

        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //provera da li se pojavila poruka nije ispravana mejl adresa tj da je nema
        Assert.assertTrue(registerNewAccountPage.getInvalidEmailMessage().isDisplayed());
        //provera da nema ponovljenog mejla
        Assert.assertTrue(registerNewAccountPage.getReenterEmailMessage().isDisplayed());

        //provera da li se pojavila poruka da treba verifikovati Email adresu, nije se pojavila, nije registrovan novi korisnik
        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);

    }
    @Test (priority = 50)
    public void userCanNotRegisterWithoutFillingPasswordAndConfirmPasswordTextBoxes() throws InterruptedException {

        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());

        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());

        Thread.sleep(15000);

        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //provera da li se pojavila poruka o passwordu, da nedostaje i kolikob treba biti dugacka
        Assert.assertTrue(registerNewAccountPage.getPasswordMustBeAtLeastMessage().isDisplayed());
        //provera da se pojavila poruka da treba ponoviti password
        Assert.assertTrue(registerNewAccountPage.getConfirmPasswordMessage().isDisplayed());

        //provera da li se pojavila poruka da treba verifikovati Email adresu, nije se pojavila, nije registrovan novi korisnik
        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);
    }
    @Test (priority = 60)
    public void  userCanNotRegisterWithoutCheckingTermsAndConditionsChechBox() throws InterruptedException {

        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        Thread.sleep(15000);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //provera da li se pojavila poruka da korisnik mora prihvatiti uslove da bi se registrovao
        Assert.assertTrue(registerNewAccountPage.getPleaseAcceptTermsMessage().isDisplayed());
        //provera da li se pojavila poruka da treba verifikovati Email adresu, nije se pojavila, nije registrovan novi korisnik
        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);
    }
    @Test (priority = 70)
    public void userCanNotRegisterWithoutCheckingCaptcha(){
        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        registerNewAccountPage.clickOnRegisterButton();

        Assert.assertTrue(registerNewAccountPage.getInvalidCaptchaMessage().isDisplayed());

        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);
    }

    @Test(priority = 80)
    public void userCanNotRegisterWithInvalidUsername() throws InterruptedException {

        registerNewAccountPage.insertUsernameTextBox(invalidUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        Thread.sleep(15000);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //poruka da nije dobar username se pojavila
        Assert.assertTrue(registerNewAccountPage.getUserNameIsInvalidMessage().isDisplayed());
        //nema poruke za verifikaciju mejla, nije se regisrtovao korisnik
        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);
    }

    @Test(priority = 90)
    public void userCanNotRegisterWithInvalidEmailAddress() throws InterruptedException {
        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(invalidEmail);
        registerNewAccountPage.confirmEmailAddress(invalidEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        Thread.sleep(15000);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //poruke da nije dobra ardesa i ponovljena adresa
        Assert.assertTrue(registerNewAccountPage.getInvalidEmailMessage().isDisplayed());
        Assert.assertTrue(registerNewAccountPage.getReenterEmailMessage().isDisplayed());

    }
    @Test (priority = 100)
    public void userCanNotRegisterWithInvalidPassword() throws InterruptedException {

        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.confirmEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(invalidPassword);
        registerNewAccountPage.confirmPassword(invalidPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        Thread.sleep(15000);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        Assert.assertTrue(registerNewAccountPage.getPasswordMustBeAtLeastMessage().isDisplayed());
        Assert.assertTrue(registerNewAccountPage.getConfirmPasswordMessage().isDisplayed());

    }
    @Test (priority = 110)
    public void userCanNotRegisterWithoutConfirmingEmail() throws InterruptedException {

        registerNewAccountPage.insertUsernameTextBox(validUsername);
        registerNewAccountPage.insertEmailAddress(validEmail);
        registerNewAccountPage.insertPassword(validPassword);
        registerNewAccountPage.confirmPassword(validPassword);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getConfirmTermsAndConditionsCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getSubscribeCheckBox());
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getGotItButton());
        scrollIntoView(registerNewAccountPage.getCreateAnAccountButton());
        Thread.sleep(15000);
        registerNewAccountPage.clickElementsJS(registerNewAccountPage.getNotRobotCheckBox());
        // registerNewAccountPage.checkNotRobotCheckBox();
        registerNewAccountPage.clickOnRegisterButton();

        //pojavila se poruka da je potrebno ponovo uneti istu email adresu
        Assert.assertTrue(registerNewAccountPage.getReenterEmailMessage().isDisplayed());

        //nema poruke za verifikaciju mejla, nije se regisrtovao korisnik
        boolean check = false;
        try {
            check = pendingEmailVerificationPage.getEmailVerificationMessage().isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(check);

    }

    //ova metoda ce biti pozvna nakon izvrsenja svake test metode

    @AfterMethod
    public void removeCookies(){
        driver.manage().deleteAllCookies();
    }



}
