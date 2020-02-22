package pageobjects.webportal;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import helpers.ConfigurationHelper;

public class JpmcRewardsPage {

    private RemoteWebDriver driver;

    public JpmcRewardsPage(RemoteWebDriver driver){
        this.driver=driver;
    }

    public WebElement getRewardsElement(){
        return driver.findElement(By.className("single-logo-icon"));
    }

    public WebElement getOpenAnAccountLink(){
        return driver.findElement(By.className("header__section--dropdown__title__link"));
    }

    public WebElement getBrowseCardsButton(){
        return driver.findElement(By.className("body-copy__cta--buttons"));
    }

    public WebElement getManageMyAccount(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[1]/div/h2"));
    }

    public WebElement getTravelCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[2]/div/h2"));
    }

    public WebElement getRewardsCard(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[3]/div/h2"));
    }

    public WebElement getCashBackCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[4]/div/h2"));
    }

    public WebElement getPartnerCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[5]/div/h2"));
    }

    public WebElement getSmallBusinessCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[6]/div/h2"));
    }

    public WebElement getSignUpLink(){
        return driver.findElement(By.id("enrollment"));
    }

    public WebElement getSignUpHome() {
    	return driver.findElement(By.xpath("//*[@class='jpui primary link last chaseanalytics-track-link']"));
    }
    
    public WebElement getAccountField(){
        return driver.findElement(By.id("accountIdentifier-text-validate-input-field"));
    }

    public WebElement getAccountFieldHighlighted(){
        return driver.findElement(By.id("accountIdentifier-label"));
    }

    public WebElement getSsnNumberField(){
        return driver.findElement(By.id("socialSecurityNumber-text-validate-input-field"));
    }

    public WebElement getUserNameField(){
        return driver.findElement(By.id("userId-text-validate-input-field"));
    }

    public WebElement submitSignUpInformation(){
        return driver.findElement(By.id("nextButton"));
    }

    public WebElement getSignUpError(){
        return driver.findElement(By.id("inner-identificationError"));
    }
    
    public WebElement getLoginWelcomeMessage(){
        return driver.findElement(By.id("welcomeHeader"));
    }
    
    public WebElement getLoginWelcomeSection(){
        return driver.findElement(By.id("logonbox"));
    }
    
    public WebElement getLoginUserNameField() throws FileNotFoundException, IOException, ParseException{
    	WebElement element;
//    	if(ConfigurationHelper.getPlatform().equalsIgnoreCase("android_web")) {
//    		element = driver.findElement(By.id("userId-text-input-field"));
//    	}else {
//    		element = driver.findElement(By.id("userId-input-field"));
//    	}
    	element = driver.findElement(By.id("userId-text-input-field"));
        return element;
    }
    
    public WebElement getLoginPasswordField() throws FileNotFoundException, IOException, ParseException{
    	WebElement element;
//    	if(ConfigurationHelper.getPlatform().equalsIgnoreCase("android_web")) {
//    		element = driver.findElement(By.id("password-text-input-field"));
//    	}else {
//    		element = driver.findElement(By.id("password-input-field"));
//    	}
    	element = driver.findElement(By.id("password-text-input-field"));
        return element;
    }
    
    public WebElement getLoginRememberMeText(){
        return driver.findElement(By.id("label-rememberMe"));
    }
    
    public WebElement getLoginUseTokenText(){
        return driver.findElement(By.id("useToken"));
    }
    
    public WebElement getLoginSigninButton(){
        return driver.findElement(By.id("signin-button"));
    }
    
    public WebElement getMenuButton(){
        return driver.findElement(By.id("skip-sidemenu"));
    }
    
    public WebElement getMenuSignInButton(){
        return driver.findElement(By.xpath("//a[contains(@class,'signInBtn')]/p/parent::a"));
    }
    
    public WebElement getLoginForgotPasswordLink(){
        return driver.findElement(By.id("forgotPassword"));
    }
    
}
