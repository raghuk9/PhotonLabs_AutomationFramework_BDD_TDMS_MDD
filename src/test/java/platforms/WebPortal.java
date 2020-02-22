package platforms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import helpers.ConfigurationHelper;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pageobjects.webportal.JpmcRewardsPage;

public class WebPortal implements JPMCPlatform {
	private static RemoteWebDriver driver;
	private Properties locatorPool = null;
	private static JpmcRewardsPage home;
	public static WebElement previousElement = null;
	private WebDriverWait wait;

	public static RemoteWebDriver getDriver() {
		return driver;
	}
	
	private boolean isDisplayed(WebElement element) throws FileNotFoundException, IOException, ParseException {
		boolean isDisplayed = false;
		if(!ConfigurationHelper.getPlatform().equals("android_Web")) {
			element.isDisplayed();
			isDisplayed = true;
		}else if(element!=null) {
			isDisplayed = true;
		}
		return isDisplayed;
	}

	public WebElement languageToggleButton() {
		return driver.findElement(
				By.xpath("//li[@class='header__section--link']/a[@class='chaseanalytics-track-link language-toggle']"));
	}

	public void switchLanguageForAndroidWeb() {
		
	}
	
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
		previousElement = null;
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
		previousElement = null;
	}

	public void launch() throws Exception {
		try {
			String platformName = ConfigurationHelper.getPlatform();
			if (platformName.equalsIgnoreCase("webPortal_Dev")) {
//				FileReader reader = new FileReader("src/test/resources/pageobjects/web_pageobjects.properties");
//				locatorPool = new Properties();
//				locatorPool.load(reader);
				System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver_70v");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
				ConfigurationHelper.init();
				driver.manage().deleteAllCookies();
				driver.manage().window().fullscreen();
				driver.get(ConfigurationHelper.getBaseUri());
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				Thread.sleep(5000);	
				String languageChangeOption = languageToggleButton().getText();
				if (System.getProperty("lang") != null) {
					if (System.getProperty("lang").equalsIgnoreCase("en-us")
							&& languageChangeOption.equalsIgnoreCase("english")) {
						languageToggleButton().click();
					} else if (System.getProperty("lang").equalsIgnoreCase("es")
							&& languageChangeOption.equalsIgnoreCase("Español")) {
						languageToggleButton().click();
					}
				} else {
					if (languageChangeOption.equalsIgnoreCase("english")) {
						languageToggleButton().click();
					}
				}
			} else if (platformName.equalsIgnoreCase("android_Web")) {
				ConfigurationHelper.init();
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.UDID, ConfigurationHelper.getUdid());				
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigurationHelper.getPlatformName());
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigurationHelper.getPlatformVersion());
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigurationHelper.getDeviceName());
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, ConfigurationHelper.getBrowserName());
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
				capabilities.setCapability("nativeWebScreenshot", true);

				String ADB = System.getenv("ANDROID_HOME");
				String cmd = "/platform-tools/adb shell input keyevent 224";
				Runtime run = Runtime.getRuntime();
				Process pr = run.exec(ADB + cmd);
				pr.waitFor();

				driver = new RemoteWebDriver(new URL(ConfigurationHelper.getDriverUrl()), capabilities);

				wait = new WebDriverWait(driver, 30);

				driver.get(ConfigurationHelper.getBaseUri());

				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//				String currentLanguage = driver.getPageSource();
//				if (System.getProperty("lang") != null) {
//					if (!currentLanguage.contains(System.getProperty("lang"))) {
//						home.getSignUpLink().click();
//						Thread.sleep(3000);
//						driver.findElement(By.id("languageChange")).click();
//						Thread.sleep(3000);
//						driver.navigate().back();
//					} 
//				} else {
//					if (currentLanguage.equalsIgnoreCase("es")) {
//						home.getSignUpLink().click();
//						Thread.sleep(3000);
//						driver.findElement(By.id("languageChange")).click();
//						Thread.sleep(3000);
//						driver.navigate().back();
//					}
//				}
			}
			home = new JpmcRewardsPage(driver);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unknown error while user tries to launch the jpmc application");
		}
	}

	public void highLighterMethod(WebElement element, RemoteWebDriver driver) throws IOException, ParseException {
		if (ConfigurationHelper.getPlatform().contains("webPortal")) {
			if (previousElement != null)
				revertHighLighterMethod(previousElement, driver);
			JavascriptExecutor js = driver;
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",
					element);
			previousElement = element;
		}
	}

	public void revertHighLighterMethod(WebElement element, RemoteWebDriver driver) throws IOException, ParseException {
		if (ConfigurationHelper.getPlatform().contains("webPortal")) {
			JavascriptExecutor js = driver;
			js.executeScript("arguments[0].setAttribute('style','background: transparent; border: none');", element);
		}
	}

	@Override
	public void logOut() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public File takeScreenshot() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void navigateToLogin() throws Exception {
		try {
			driver.findElement(By.linkText(locatorPool.getProperty("jpmc.home.homescreen.menu.login"))).click();
		} catch (Exception e) {
			throw new Exception("Unknown error while user tries to navigate to login");
		}
	}

	@Override
	public void login() throws Exception {
		try {
			driver.findElement(By.linkText(locatorPool.getProperty("jpmc.home.homescreen.menu.submitButton"))).click();
		} catch (Exception e) {
			throw new Exception("Unknown error while user tries to login the jpmc application");
		}
	}

	@Override
	public void validateLogIn() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateLogOut() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterEmailAndPassword(String email, String password) throws Exception {
		try {
			driver.findElement(By.linkText(locatorPool.getProperty("jpmc.home.homescreen.menu.userName")))
					.sendKeys(email);
			driver.findElement(By.linkText(locatorPool.getProperty("jpmc.home.homescreen.menu.password")))
					.sendKeys(password);
		} catch (Exception e) {
			throw new Exception("Unknown error while user enters email and password in login");
		}

	}

	@Override
	public void validateUnsuccessfulLogin(String error) throws Exception {
		try {
			String errormessage = driver
					.findElement(By.linkText(locatorPool.getProperty("jpmc.home.homescreen.menu.submitButton")))
					.getText();
			if (!errormessage.equalsIgnoreCase(error)) {
				throw new Exception("warning" + error + "message is not displayed");
			}
		} catch (Exception e) {
			throw new Exception("Unknown error while user tries to validate unseccessful login");
		}

	}

	@Override
	public void validateReward() throws Exception {
		Thread.sleep(10000);
		System.out.println("Element is visible ----" + home.getRewardsElement());
		highLighterMethod(home.getRewardsElement(), driver);
		Assert.assertTrue(home.getRewardsElement().isDisplayed(), "Rewards page not available");
	}

	@Override
	public void validateOpenAccount() throws Exception {
		Thread.sleep(2000);
		if (ConfigurationHelper.getPlatform().equals("webPortal_Dev")) {
			highLighterMethod(home.getOpenAnAccountLink(), driver);
			Assert.assertTrue(home.getOpenAnAccountLink().isDisplayed(), "Open account link is not available");
		} else {
			Assert.assertFalse(home.getOpenAnAccountLink().isDisplayed(), "Open account link is available");
		}

	}

	@Override
	public void validateBrowseCards() throws Exception {
		Thread.sleep(3000);
		highLighterMethod(home.getBrowseCardsButton(), driver);
		Assert.assertTrue(home.getBrowseCardsButton().isDisplayed(), "Open account link is not available");
	}

	@Override
	public void validateManageMyAccount() throws Exception {
		scrollToElement(home.getManageMyAccount(), driver);
//		Actions act = new Actions(driver);
//		act.moveToElement(home.getManageMyAccount()).build().perform();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,100)");
//		Thread.sleep(3000);
//		js.executeScript("arguments[0].scrollIntoView();", home.getManageMyAccount());
		highLighterMethod(home.getManageMyAccount(), driver);
		Assert.assertTrue(home.getManageMyAccount().isDisplayed(), "Manage my account footer link is not available");
	}

	@Override
	public void validateTravelCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getTravelCards(), driver);
		highLighterMethod(home.getTravelCards(), driver);
		Assert.assertTrue(home.getTravelCards().isDisplayed(), "Travel cards footer link is not available");
	}

	@Override
	public void validateRewardCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getRewardsCard(), driver);
		highLighterMethod(home.getRewardsCard(), driver);
		Assert.assertTrue(home.getRewardsCard().isDisplayed(), "Reward cards footer link is not available");
	}

	@Override
	public void validateCashBackCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getCashBackCards(), driver);
		highLighterMethod(home.getCashBackCards(), driver);
		Assert.assertTrue(home.getCashBackCards().isDisplayed(), "Cash back cards footer link is not available");
	}

	@Override
	public void validatePartnerCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getPartnerCards(), driver);
		highLighterMethod(home.getPartnerCards(), driver);
		Assert.assertTrue(home.getPartnerCards().isDisplayed(), "Partner cards footer link is not available");
	}

	@Override
	public void validateSmallBusinessCards() throws Exception {
		Thread.sleep(2000);
		highLighterMethod(home.getSmallBusinessCards(), driver);
		Assert.assertTrue(home.getSmallBusinessCards().isDisplayed(), "Partner cards footer link is not available");
		// Assert.fail();
	}

	public static void getScreenshotEmbed(Scenario scenario) throws Exception {
		String scrname = scenario.getId();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("output/FailureScreenShots/" + scrname + ".png"));
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
		Reporter.addScreenCaptureFromPath("FailureScreenShots/" + scrname + ".png", "Screen shot");
	}

	@Override
	public void signUp() throws Exception {
		Thread.sleep(10000);		
		try {
			WebElement frameElement;
			if(ConfigurationHelper.getPlatform().equalsIgnoreCase("android_Web")) {
				frameElement = driver.findElement(By.id("routablecpologonbox"));
			}else {
				frameElement = driver.findElement(By.id("logonbox"));
			}
			switchFrame(frameElement);
			highLighterMethod(home.getSignUpLink(), driver);
			home.getSignUpLink().click();
			switchToDefaultFrame();
		}catch (ElementNotInteractableException|NoSuchElementException e) {
			switchToDefaultFrame();
			highLighterMethod(home.getSignUpHome(), driver);
			home.getSignUpHome().click();
		}
	}

	@Override
	public void fillAccountNumber(String accountNumber) throws Exception {
		Thread.sleep(10000);
		// new WebDriverWait(driver,
		// 30).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(home.getAccountFieldHighlighted()));
		// driver.findElement(By.id("checkoutLink")).click();
		// highLighterMethod(home.getAccountFieldHighlighted());
		home.getAccountField().click();
		home.getAccountField().sendKeys(accountNumber);
	}

	@Override
	public void fillSsnNumber(String ssnNumber) throws Exception {
		System.out.println("ssn number" + ssnNumber);
		Thread.sleep(4000);
		if(ConfigurationHelper.getPlatform().contains("webPortal")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", home.getSsnNumberField());
		}
		home.getSsnNumberField().click();
		Thread.sleep(5000);
		home.getSsnNumberField().sendKeys(ssnNumber);
	}

	@Override
	public void fillUserName(String userName) throws Exception {
		home.getUserNameField().click();
		Thread.sleep(5000);
		home.getUserNameField().sendKeys(userName);
		if(ConfigurationHelper.getPlatform().equalsIgnoreCase("android_web")) {
			home.getSsnNumberField().click();
		}
	}

	@Override
	public void submitInformation() throws Exception {
		Thread.sleep(2000);
		home.submitSignUpInformation().click();
	}

	@Override
	public void validateErrorMessageWhileSignUp(String errorMessage) throws Exception {
		Thread.sleep(5000);
		Assert.assertEquals(home.getSignUpError().getText(), errorMessage);
	}

	@Override
	public void loginWithUserName(String username) throws Exception {

	}

	@Override
	public void loginWithPassword(String password) throws Exception {

	}

	private void scrollToElement(WebElement element, RemoteWebDriver driver)
			throws IOException, ParseException, InterruptedException {
		if (ConfigurationHelper.getPlatform().equals("android_Web")) {
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();
		} else if (ConfigurationHelper.getPlatform().equals("webPortal_Dev")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,100)");
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
	}

	@Override
	public void navigateToSigninPage() throws Exception {
		if(ConfigurationHelper.getPlatform().equals("webPortal_Dev")) {
			highLighterMethod(home.getMenuButton(), driver);
			home.getMenuButton().click();
			Thread.sleep(3000);
			highLighterMethod(home.getMenuSignInButton(), driver);
			home.getMenuSignInButton().click();
			Thread.sleep(5000);
		}
	}

	@Override
	public void validateLoginUserNameField() throws Exception {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement frameElement;
		if(ConfigurationHelper.getPlatform().equals("android_Web")) {
			frameElement = driver.findElement(By.id("routablecpologonbox"));
		}else {
			frameElement = driver.findElement(By.id("logonbox"));
		}
		switchFrame(frameElement);
		highLighterMethod(home.getLoginUserNameField(), driver);
		Assert.assertTrue(isDisplayed(home.getLoginUserNameField()));
	}

	@Override
	public void validateLoginpasswordField() throws Exception {
		highLighterMethod(home.getLoginPasswordField(), driver);
		Assert.assertTrue(isDisplayed(home.getLoginPasswordField()));
	}

	@Override
	public void validateLoginrememberMe(String rememberMe) throws Exception {
		highLighterMethod(home.getLoginRememberMeText(), driver);
		Assert.assertEquals(home.getLoginRememberMeText().getText(), rememberMe);
	}

	@Override
	public void validateLoginuseTokenLink(String useToken) throws Exception {
		highLighterMethod(home.getLoginUseTokenText(), driver);
		Assert.assertEquals(home.getLoginUseTokenText().getText(), useToken);
	}

	@Override
	public void validateLoginsignInButton(String signInButton) throws Exception {
		highLighterMethod(home.getLoginSigninButton(), driver);
		Assert.assertEquals(home.getLoginSigninButton().getText(), signInButton);
	}

	@Override
	public void validateLoginforgotLink(String forgotLink) throws Exception {
		highLighterMethod(home.getLoginForgotPasswordLink(), driver);
		Assert.assertEquals(home.getLoginForgotPasswordLink().getText(), forgotLink);
	}

	@Override
	public void validateLoginsignUpLink(String signUp) throws Exception {
		highLighterMethod(home.getSignUpLink(), driver);
		Assert.assertEquals(home.getSignUpLink().getText(), signUp);
		switchToDefaultFrame();
	}

	@Override
	public void validateSignuperrorMessage(String errorMessaage) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", home.getSsnNumberField());
		
	}
}