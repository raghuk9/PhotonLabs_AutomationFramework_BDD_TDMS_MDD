package platforms;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import helpers.ConfigurationHelper;
import helpers.VideoRecorder;
import io.appium.java_client.remote.MobileCapabilityType;
import pageobjects.webportal.JpmcRewardsPage;

public class WebPortal implements JPMCPlatform {
	private static RemoteWebDriver driver;
//	private Properties locatorPool = null;
	private static JpmcRewardsPage home;
	public static String previousElementStyle = null;
	private static StopWatch pageLoad;
	private static Long pageLoadtime;
	private static int elementWaitInSeconds = 30;

	public static RemoteWebDriver getDriver() {
		return driver;
	}
	
	private static void javaWait(long millis) throws InterruptedException {
		Thread.sleep(millis);
	}
	
	private WebDriverWait webDriverWait() {
		return new WebDriverWait(driver, elementWaitInSeconds);
	}
	
	private void waitForElementVisibilityAndEnterText(WebElement element,String input) throws IOException, ParseException, InterruptedException {
		waitElementClickable(element);
		element.click();
		element.clear();
		element.sendKeys(input);;
	}
	
	private String getText(WebElement element) throws Exception {
		String result = "";
		result = element.getText();
		highLighterMethod(element, driver);
		return result;
	}
	
	private void waitForElementVisibilityAndClick(WebElement element) throws IOException, ParseException, InterruptedException {
		waitElementClickable(element);
		try {
			element.click();			
		}catch (TimeoutException e) {
			
		}
	}
	
	private void textAssertion(WebElement element,String expectedText) throws IOException, ParseException, InterruptedException {
		scrollToElement(element, driver);
		waitVisibilityOfElement(element);
		highLighterMethod(element, driver);
		Assert.assertEquals(expectedText, element.getText());
	}

	private boolean waitVisibilityOfElement(WebElement element) throws IOException, ParseException, InterruptedException {
		boolean elementVisible = false;
		WebElement val = webDriverWait().until(ExpectedConditions.visibilityOf(element));
		if(val!=null) {
			highLighterMethod(element, driver);
			elementVisible = true;
		}
		return elementVisible;
	}
	
	private void waitElementClickable(WebElement element) throws IOException, ParseException, InterruptedException {
		webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
		highLighterMethod(element, driver);
	}

	private void validateLocation(WebElement element, Point location, Dimension size)
			throws IOException, ParseException, InterruptedException {
		waitVisibilityOfElement(element);
		Assert.assertEquals(element.getLocation(), location);
		Assert.assertEquals(element.getSize(), size);
	}

	public WebElement languageToggleButton() {
		return driver.findElement(
				By.xpath("//li[@class='header__section--link']/a[@class='chaseanalytics-track-link language-toggle']"));
	}

	public void switchLanguageForAndroidWeb() {

	}

	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public void launch() throws Exception {
		try {
			HashMap<String, Object> chromePref =  new HashMap<String, Object>();
			chromePref.put("intl.accept_languages", ConfigurationHelper.getLanguage());
			String platformName = ConfigurationHelper.getPlatform().toLowerCase();
			String browserName = ConfigurationHelper.getBrowserName().toLowerCase();
			VideoRecorder.startRecording();
			if (platformName.equalsIgnoreCase("webPortal_Dev")) {
				switch (browserName) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver_70v");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("start-maximized");
					options.addArguments("disable-infobars");
					options.setExperimentalOption("prefs",chromePref);
					driver = new ChromeDriver(options);
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
					driver= new FirefoxDriver();
				case "safari":
					driver= new SafariDriver();
				default:
					break;
				}
				driver.manage().deleteAllCookies();
				driver.manage().window().fullscreen();
				ConfigurationHelper.init();
				pageLoad = new StopWatch();
				pageLoad.start();
				driver.get(ConfigurationHelper.getBaseUri());
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				pageLoad.stop();
				pageLoadtime = pageLoad.getTime(TimeUnit.SECONDS);
				pageLoad.reset();
				System.out.println("Page Load Time : " + pageLoadtime);
			} else if (platformName.equalsIgnoreCase("android_Web")) {
				ConfigurationHelper.init();
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.UDID, ConfigurationHelper.getUdid());
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigurationHelper.getPlatformName());
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
						ConfigurationHelper.getPlatformVersion());
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
				pageLoad = new StopWatch();
				pageLoad.start();
				driver.get(ConfigurationHelper.getBaseUri());
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				pageLoad.stop();
				pageLoadtime = pageLoad.getTime(TimeUnit.SECONDS);
				pageLoad.reset();
				System.out.println("Page Load Time : " + pageLoad.getTime(TimeUnit.SECONDS));
			}
			Thread.sleep(5000);
			home = new JpmcRewardsPage(driver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unknown error while user tries to launch the jpmc application");
		}
	}

	public void highLighterMethod(WebElement element, RemoteWebDriver driver) throws IOException, ParseException, InterruptedException {
		if (ConfigurationHelper.getPlatform().contains("webPortal")) {
			previousElementStyle = element.getAttribute("style");	
			if(previousElementStyle.equals("")) {
				JavascriptExecutor js = driver;
				js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;');",
						element);				
			}else {
				JavascriptExecutor js = driver;
				js.executeScript("arguments[0].setAttribute('style','border: 2px solid red;"+previousElementStyle+"');",
						element);
			}
			javaWait(3000);
			revertHighLighterMethod(element, driver);
		}
	}

	public void revertHighLighterMethod(WebElement element, RemoteWebDriver driver) throws IOException, ParseException {
		if (ConfigurationHelper.getPlatform().contains("webPortal")) {
			if(previousElementStyle.equals("")) {
				JavascriptExecutor js = driver;
				js.executeScript("arguments[0].setAttribute('style','border: none');", element);	
			}else {
				JavascriptExecutor js = driver;
				js.executeScript("arguments[0].setAttribute('style','"+previousElementStyle+"');", element);
			}
			
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
	public void validateLogIn() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateLogOut() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateReward() throws Exception {
		Thread.sleep(10000);
		System.out.println("Element is visible ----" + home.getRewardsElement());
		highLighterMethod(home.getRewardsElement(), driver);
		Assert.assertTrue("Rewards page not available",home.getRewardsElement().isDisplayed());
	}

	@Override
	public void validateOpenAccount() throws Exception {
		Thread.sleep(2000);
		if (ConfigurationHelper.getPlatform().equals("webPortal_Dev")) {
			highLighterMethod(home.getOpenAnAccountLink(), driver);
			Assert.assertTrue("Open account link is not available",home.getOpenAnAccountLink().isDisplayed());
		} else {
			Assert.assertFalse("Open account link is available",home.getOpenAnAccountLink().isDisplayed());
		}

	}

	@Override
	public void validateBrowseCards() throws Exception {
		Thread.sleep(3000);
		highLighterMethod(home.getBrowseCardsButton(), driver);
		Assert.assertTrue("Open account link is not available",home.getBrowseCardsButton().isDisplayed());
	}

	@Override
	public void validateManageMyAccount() throws Exception {
		scrollToElement(home.getManageMyAccount(), driver);
		highLighterMethod(home.getManageMyAccount(), driver);
		Assert.assertTrue("Manage my account footer link is not available",home.getManageMyAccount().isDisplayed());
	}

	@Override
	public void validateTravelCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getTravelCards(), driver);
		highLighterMethod(home.getTravelCards(), driver);
		Assert.assertTrue("Travel cards footer link is not available",home.getTravelCards().isDisplayed());
	}

	@Override
	public void validateRewardCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getRewardsCard(), driver);
		highLighterMethod(home.getRewardsCard(), driver);
		Assert.assertTrue("Reward cards footer link is not available",home.getRewardsCard().isDisplayed());
	}

	@Override
	public void validateCashBackCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getCashBackCards(), driver);
		highLighterMethod(home.getCashBackCards(), driver);
		Assert.assertTrue("Cash back cards footer link is not available",home.getCashBackCards().isDisplayed());
	}

	@Override
	public void validatePartnerCards() throws Exception {
		Thread.sleep(2000);
		scrollToElement(home.getPartnerCards(), driver);
		highLighterMethod(home.getPartnerCards(), driver);
		Assert.assertTrue("Partner cards footer link is not available",home.getPartnerCards().isDisplayed());
	}

	@Override
	public void validateSmallBusinessCards() throws Exception {
		Thread.sleep(2000);
		highLighterMethod(home.getSmallBusinessCards(), driver);
		Assert.assertTrue("Partner cards footer link is not available",home.getSmallBusinessCards().isDisplayed());
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
		Thread.sleep(5000);
		try {
			WebElement frameElement;
			if (ConfigurationHelper.getPlatform().equalsIgnoreCase("android_Web")) {
				frameElement = driver.findElement(By.id("routablecpologonbox"));
			} else {
				frameElement = driver.findElement(By.id("logonbox"));
			}
			switchFrame(frameElement);
			waitForElementVisibilityAndClick(home.getSignUpLink());
			switchToDefaultFrame();
		} catch (TimeoutException | ElementNotInteractableException | NoSuchElementException e) {
			switchToDefaultFrame();
			waitForElementVisibilityAndClick(home.getSignUpHome());
		}
		Thread.sleep(10000);
	}

	@Override
	public void fillAccountNumber(String accountNumber) throws Exception {
		Thread.sleep(10000);
		waitForElementVisibilityAndEnterText(home.getAccountField(), accountNumber);
	}

	@Override
	public void fillSsnNumber(String ssnNumber) throws Exception {
		System.out.println("ssn number" + ssnNumber);
		if (ConfigurationHelper.getPlatform().contains("webPortal")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", home.getSsnNumberField());
		}
		waitForElementVisibilityAndEnterText(home.getSsnNumberField(), ssnNumber);
	}

	@Override
	public void fillUserName(String userName) throws Exception {
		waitForElementVisibilityAndEnterText(home.getUserNameField(), userName);
		if (ConfigurationHelper.getPlatform().equalsIgnoreCase("android_web")) {
			home.getSsnNumberField().click();
		}
	}

	@Override
	public void submitInformation() throws Exception {
		waitForElementVisibilityAndClick(home.submitSignUpInformation());
	}

	@Override
	public void validateErrorMessageWhileSignUp(String errorMessage) throws Exception {
		textAssertion(home.getSignUpError(), errorMessage);
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
		if (ConfigurationHelper.getPlatform().contains("webPortal")) {
			waitForElementVisibilityAndClick(home.getMenuButton());		
			Thread.sleep(3000);
			waitForElementVisibilityAndClick(home.getMenuSignInButton());
			pageLoad = new StopWatch();
			pageLoad.start();
			Thread.sleep(5000);
		}

	}

	@Override
	public void validateLoginUserNameField() throws Exception {
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement frameElement;
		if (ConfigurationHelper.getPlatform().equals("android_Web")) {
			frameElement = driver.findElement(By.id("routablecpologonbox"));
		} else {
			frameElement = driver.findElement(By.id("logonbox"));
		}		
		switchFrame(frameElement);
		Assert.assertTrue(waitVisibilityOfElement(home.getLoginUserNameField()));
	}

	@Override
	public void validateLoginpasswordField() throws Exception {
		Assert.assertTrue(waitVisibilityOfElement(home.getLoginPasswordField()));
	}

	@Override
	public void validateLoginrememberMe(String rememberMe) throws Exception {
		textAssertion(home.getLoginRememberMeText(), rememberMe);
	}

	@Override
	public void validateLoginuseTokenLink(String useToken) throws Exception {
		textAssertion(home.getLoginUseTokenText(), useToken);
	}

	@Override
	public void validateLoginsignInButton(String signInButton) throws Exception {
		textAssertion(home.getLoginSigninButton(), signInButton);
	}

	@Override
	public void validateLoginforgotLink(String forgotLink) throws Exception {
		textAssertion(home.getLoginForgotPasswordLink(), forgotLink);
	}

	@Override
	public void validateLoginsignUpLink(String signUp) throws Exception {
		textAssertion(home.getSignUpLink(), signUp);
		switchToDefaultFrame();
	}

	@Override
	public void validateSignuperrorMessage(String errorMessaage) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", home.getSsnNumberField());

	}

	@Override
	public void goToHome() throws Exception {
		driver.get(ConfigurationHelper.getBaseUri());
	}

	@Override
	public void visualValidtionOfLogo() throws Exception {
		validateLocation(home.getHeaderLogo(), home.logoLocation(), home.logoSize());
	}

	@Override
	public void visualValiationOfMenu() throws Exception {
		validateLocation(home.getMenuButton(), home.menuLocation(), home.menuSize());
	}

	@Override
	public void homePageLodeTest(int time) throws Exception {
		if (pageLoadtime <= time) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue("Actual Time Taken : " + pageLoadtime + "\nExpected Time : " + time,false);
		}
	}

	@Override
	public void signInPageLodeTest(int time) throws Exception {
		pageLoad.stop();
		pageLoadtime = pageLoad.getTime(TimeUnit.SECONDS);
		if (pageLoadtime <= time) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue("Actual Time Taken : " + pageLoadtime + "\nExpected Time : " + time,false);
		}
	}

	@Override
	public void clickOnFindaCreditCardlink() throws Exception {
		waitForElementVisibilityAndClick(home.getFindaCardLink());
	}

	@Override
	public void validatePageNavigation(String pageTitle) {
		Assert.assertEquals(pageTitle, driver.getTitle());
	}

	@Override
	public void clickOnTryOurCardFinderLink() throws Exception {
		waitForElementVisibilityAndClick(home.getTryOurCardFinderLink());
	}

	@Override
	public void clickOnPersonalIcon() throws Exception {	
		waitForElementVisibilityAndClick(home.getPersonalIcon());
	}

	@Override
	public void clickOnRewardsIcon() throws Exception {
		waitForElementVisibilityAndClick(home.getRewardsIcon());
	}

	@Override
	public void clickOnCashBackIcon() throws Exception {
		waitForElementVisibilityAndClick(home.getCashBackIcon());		
	}

	@Override
	public void clickOnBalanceTransferIcon() throws Exception {
		Thread.sleep(3000);
		waitForElementVisibilityAndClick(home.getBalanceTransferIcon());		
	}

	@Override
	public void validateCardShowsAsPerTheCriteria(List<String> criteria) throws Exception {
		List<WebElement> elements = home.getCardSection();
		for(int i=1;i<=elements.size();i++) {
			List<WebElement> webelement = home.getCardCriteria(i);
			for (int j = 0; j < criteria.size(); j++) {
				Assert.assertEquals(criteria.get(j), getText(webelement.get(j)));
			}
		}
		
	}

	@Override
	public void clickOnBusinessIcon() throws Exception {
		waitForElementVisibilityAndClick(home.getBusinessIcon());
	}
}