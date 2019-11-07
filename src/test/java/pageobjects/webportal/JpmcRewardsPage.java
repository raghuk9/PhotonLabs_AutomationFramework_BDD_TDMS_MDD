package pageobjects.webportal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        return driver.findElement(By.xpath("//a[@id='enrollment']"));
    }

    public WebElement getAccountField(){
        return driver.findElement(By.id("accountIdentifier-text-input-field"));
    }

    public WebElement getAccountFieldHighlighted(){
        return driver.findElement(By.id("accountIdentifier-label"));
    }

    public WebElement getSsnNumberField(){
        return driver.findElement(By.id("socialSecurityNumber-text-input-field"));
    }

    public WebElement getUserNameField(){
        return driver.findElement(By.id("userId-text-input-field"));
    }

    public WebElement submitSignUpInformation(){
        return driver.findElement(By.id("nextButton"));
    }

    public WebElement getSignUpError(){
        return driver.findElement(By.id("inner-identificationError"));
    }
}
