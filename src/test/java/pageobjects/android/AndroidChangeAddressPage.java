package pageobjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidChangeAddressPage {
    private WebDriver driver;

    public AndroidChangeAddressPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getStreetAddress() {
        return driver.findElement(By.id("streetAddress"));
    }

    public WebElement getCity() {
        return driver.findElement(By.id("city"));
    }

    public WebElement getStateListDropDown() {
        return driver.findElement(By.id("text1"));
    }

    public By getStateListView() {
        return By.id("select_dialog_listview");
    }

    public By getState(String state) {
        return By.xpath("//android.widget.CheckedTextView[@text='"+state+"']");
    }

    public WebElement getZipCode() {
        return driver.findElement(By.id("zip"));
    }

    public WebElement getChangeButton() {
        return driver.findElement(By.id("changeButton"));
    }

}
