package pageobjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidLoginPage {
    private WebDriver driver;

    public AndroidLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailTextBox() {
        return driver.findElement(By.id("username"));
    }

    public WebElement getNextButton() {
        return driver.findElement(By.id("actionButton"));
    }

    public WebElement getPwdTextBox() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getLogInButton() {
        return driver.findElement(By.id("actionButton"));
    }

    public List<WebElement> getLogInText() {
        return driver.findElements(By.id("loginText"));
    }

    public WebElement getSignUpButton() {
        return driver.findElement(By.id("createAccountButton"));
    }

}
