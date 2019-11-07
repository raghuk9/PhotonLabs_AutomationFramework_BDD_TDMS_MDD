package pageobjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidSettingsPage {
    private WebDriver driver;

    public AndroidSettingsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getSettingsButton() {
        return driver.findElements(By.xpath(
                "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]"));
    }

    public List<WebElement> getLogOutButtonList() {
        return driver.findElements(By.id("logout"));
    }

}
