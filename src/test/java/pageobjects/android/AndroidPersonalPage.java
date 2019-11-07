package pageobjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidPersonalPage {
    private WebDriver driver;

    public AndroidPersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getPersonalButton() {
        return driver.findElements(By.id("personal"));
    }

    public WebElement getAddressButton() {
        return driver.findElement(By.id("changeAddress"));
    }

    public WebElement getNavigateUpButton() {
        return driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"));
    }

}
