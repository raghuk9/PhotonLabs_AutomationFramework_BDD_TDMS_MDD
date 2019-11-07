package pageobjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AndroidHomePage {

    private WebDriver driver;

    public AndroidHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getHomePageIcon() {
        return driver.findElements(By.xpath(
                "//androidx.appcompat.app.ActionBar.Tab[1]/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ImageView"));
    }

    public List<WebElement> getMoreIconButton() {
        return driver.findElements(By.id("More options"));
    }

}
