package pageobjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndroidInitialSignUpPage {

    private WebDriver driver;

    public AndroidInitialSignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNewUserEmail() {
        return driver.findElement(By.id("email"));
    }

    public WebElement getNewUserPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getTermsAndConditionsCheckBox() {
        return driver.findElement(By.xpath("//android.widget.CheckBox"));
    }

    public WebElement getNextButton() {
        return driver.findElement(By.id("nextButton"));
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.id("passwordErrorText"));
    }
}
