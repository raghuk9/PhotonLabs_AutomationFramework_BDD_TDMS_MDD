package platforms;

import helpers.ConfigurationHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.android.*;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class iOSPlatform implements JPMCPlatform{
    private static IOSDriver driver;
    private static WebDriverWait wait;

    @Override
    public void launch() throws Exception {
        try {
            ConfigurationHelper.init();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", ConfigurationHelper.getDeviceName());
            capabilities.setCapability("platformVersion", ConfigurationHelper.getPlatformVersion());
            capabilities.setCapability("platformName", ConfigurationHelper.getPlatformName());
            capabilities.setCapability("appPackage", ConfigurationHelper.getAppPackage());
            capabilities.setCapability("udid", ConfigurationHelper.getUdid());
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("newCommandTimeout", 300);
            driver = new IOSDriver(new URL(ConfigurationHelper.getDriverUrl()), capabilities);
            wait = new WebDriverWait(driver, 30);

            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new Exception("Unable to connect to the Appium server.");
        }
    }

    @Override
    public void goToHome() throws Exception {

    }

    @Override
    public void logOut() throws Exception {

    }

    @Override
    public void validateLogIn() throws Exception {

    }

    @Override
    public void validateLogOut() throws Exception {

    }

    @Override
    public void validateReward() throws Exception {

    }

    @Override
    public void validateOpenAccount() throws Exception {

    }

    @Override
    public void validateBrowseCards() throws Exception {

    }

    @Override
    public void validateManageMyAccount() throws Exception {

    }

    @Override
    public void validateTravelCards() throws Exception {

    }

    @Override
    public void validateRewardCards() throws Exception {

    }

    @Override
    public void validateCashBackCards() throws Exception {

    }

    @Override
    public void validatePartnerCards() throws Exception {

    }

    @Override
    public void validateSmallBusinessCards() throws Exception {

    }

    @Override
    public void signUp() throws Exception {

    }

    @Override
    public void fillAccountNumber(String accountNumber) throws Exception {

    }

    @Override
    public void fillSsnNumber(String ssnNumber) throws Exception {

    }

    @Override
    public void fillUserName(String userName) throws Exception {

    }

    @Override
    public void submitInformation() throws Exception {

    }

    @Override
    public void validateErrorMessageWhileSignUp(String errorMessage) throws Exception {

    }

    @Override
    public void loginWithUserName(String username) throws Exception {

    }

    @Override
    public void loginWithPassword(String password) throws Exception {

    }

    @Override
    public void navigateToSigninPage() throws Exception {

    }

    @Override
    public void validateLoginUserNameField() throws Exception {

    }

    @Override
    public void validateLoginpasswordField() throws Exception {

    }

    @Override
    public void validateLoginrememberMe(String rememberMe) throws Exception {

    }

    @Override
    public void validateLoginuseTokenLink(String useToken) throws Exception {

    }

    @Override
    public void validateLoginsignInButton(String signInButton) throws Exception {

    }

    @Override
    public void validateLoginforgotLink(String forgotLink) throws Exception {

    }

    @Override
    public void validateLoginsignUpLink(String signUp) throws Exception {

    }

    @Override
    public void visualValidtionOfLogo() throws Exception {

    }

    @Override
    public void visualValiationOfMenu() throws Exception {

    }

    @Override
    public void homePageLodeTest(int time) throws Exception {

    }

    @Override
    public void signInPageLodeTest(int time) throws Exception {

    }

    @Override
    public void clickOnFindaCreditCardlink() throws Exception {

    }

    @Override
    public void validatePageNavigation(String pageTitle) throws InterruptedException {

    }

    @Override
    public void clickOnTryOurCardFinderLink() throws Exception {

    }

    @Override
    public void clickOnPersonalIcon() throws Exception {

    }

    @Override
    public void clickOnRewardsIcon() throws Exception {

    }

    @Override
    public void clickOnCashBackIcon() throws Exception {

    }

    @Override
    public void clickOnNoAnnualFeeIcon() throws Exception {

    }

    @Override
    public void clickOnBalanceTransferIcon() throws Exception {

    }

    @Override
    public void validateCardShowsAsPerTheCriteria(List<String> criteria) throws Exception {

    }

    @Override
    public void clickOnBusinessIcon() throws Exception {

    }
}
