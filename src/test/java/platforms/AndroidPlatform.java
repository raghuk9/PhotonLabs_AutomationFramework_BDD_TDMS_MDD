package platforms;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import models.States;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.ConfigurationHelper;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import pageobjects.android.*;

public class AndroidPlatform implements MobilePlatform {
	
	 private static AndroidDriver driver;
	 private static WebDriverWait wait;
     private static AndroidLoginPage loginPage;
     private static AndroidInitialSignUpPage signUpPage;
     private static AndroidSettingsPage settingPage;
     private static AndroidHomePage homePage;
     private static AndroidPersonalPage personalPage;
     private static AndroidChangeAddressPage addressPage;

    public static AndroidDriver getDriver(){
        return driver;
    }

	@Override
    public void launch() throws Exception {
        //if (driver == null) {
            try {
            	ConfigurationHelper.init();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("deviceName", ConfigurationHelper.getDeviceName());
                capabilities.setCapability("platformVersion", ConfigurationHelper.getPlatformVersion());
                capabilities.setCapability("platformName", ConfigurationHelper.getPlatformName());
                capabilities.setCapability("appPackage", ConfigurationHelper.getAppPackage());
                capabilities.setCapability("appActivity", ConfigurationHelper.getAppActivity());
                capabilities.setCapability("newCommandTimeout", 300);
                String ADB = System.getenv("ANDROID_HOME");
                String cmd = "/platform-tools/adb shell input keyevent 224";
                
                Runtime run = Runtime.getRuntime();
                Process pr = run.exec(ADB + cmd);
                pr.waitFor();
                driver = new AndroidDriver(new URL(ConfigurationHelper.getDriverUrl()), capabilities);
                signUpPage = new AndroidInitialSignUpPage(driver);
                loginPage = new AndroidLoginPage(driver);
                settingPage = new AndroidSettingsPage(driver);
                homePage = new AndroidHomePage(driver);
                personalPage = new AndroidPersonalPage(driver);
                addressPage = new AndroidChangeAddressPage(driver);
                wait = new WebDriverWait(driver, 30);

                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new Exception("Unable to connect to the Appium server.");
            }
        //}
    }

    @Override
    public void navigateToSignUp() throws Exception {
        try {
            loginPage.getSignUpButton().click();
        }
        catch(Exception e){
            throw new Exception("Unknown Error navigating to sign up");
        }
    }

    @Override
    public void signUpWithEmail(String email, String password)
            throws Exception {
	    try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            signUpPage.getNewUserEmail().sendKeys(email);
            signUpPage.getNewUserPassword().sendKeys(password);
            if (driver.isKeyboardShown() != false) {
                driver.hideKeyboard();
            }
            signUpPage.getTermsAndConditionsCheckBox().click();
            signUpPage.getNextButton().click();
        }catch(Exception e){
	        throw new Exception("Unknown error occurred while signing up with email");
        }
    }

    @Override
    public void validatePasswordErrorMessage(String message) throws Exception {
	    try{
            String errorMessage = signUpPage.getErrorMessage().getText();
            Assert.assertEquals(errorMessage,message,"Password error message does not match");
        }catch (Exception e){
            throw new Exception("Unknown error while validating password error messages");
        }
        }


    @Override
    public void validateLogIn() throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            boolean isElementMissing = homePage.getHomePageIcon().isEmpty();
            if (isElementMissing) {
                throw new Exception("User is not logged in");
            }
        } catch (Exception e) {
            throw new Exception("Unknown error while Log in");
        }
    }

    @Override
    public void navigateToPersonalSettings() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homePage.getMoreIconButton().get(0)));
            homePage.getMoreIconButton().get(0).click();
            boolean isElementMissing = settingPage.getSettingsButton().isEmpty();
            if (!isElementMissing) {
                settingPage.getSettingsButton().get(0).click();
            }
            personalPage.getPersonalButton().get(0).click();
        } catch (Exception e) {
            throw new Exception("Unknown error while navigating to Account Settings", e);
        }
    }

    @Override
    public void validateAddress(String state) throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            personalPage.getAddressButton().click();
           // String streetAddress = addressPage.getStreetAddress().getText();
           // String cityValue = addressPage.getCity().getText();
            String stateValue = addressPage.getStateListDropDown().getText();
           // String zipCodeValue = addressPage.getZipCode().getText();
            String stateName = States.getStateName(state);
            if (!stateValue.equalsIgnoreCase(stateName)) {
                throw new Exception("Account Settings - Address Mismatch. \nExpected address: "
                        + state + "\nActual address: " + stateValue);
            }
        } catch (Exception e) {
            throw new Exception("Unknown error while validating address in Account settings", e);
        }

    }

    @Override
    public void changeAddress(String stateKey) throws Exception {
        try {
            //addressPage.getStreetAddress().sendKeys(address);
            //addressPage.getCity().sendKeys(city);
            addressPage.getStateListDropDown().click();
            getStateName(stateKey);
           // addressPage.getZipCode().sendKeys(zipCode);
            /*if (driver.isKeyboardShown()) {
                driver.hideKeyboard();
            }*/
            addressPage.getChangeButton().click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.navigate().back();
            boolean isBackButton = personalPage.getNavigateUpButton().isDisplayed();
            if(isBackButton)
            personalPage.getNavigateUpButton().click();
        } catch (Exception e) {
            throw new Exception("Unknown error while changing address");
        }
    }

    @Override
    public void getStateName(String stateKey) throws Exception {
        try {
            String currentStateName = addressPage.getStateListDropDown().getText();
            String stateName = States.getStateName(stateKey);
            WebElement stateElement = null;
            if (currentStateName.compareTo(stateName) <= 0) {
                stateElement = scrollUntilTheElementIsFound(addressPage.getState(stateName),
                        addressPage.getStateListView(), 5, false);
            } else {
                stateElement = scrollUntilTheElementIsFound(addressPage.getState(stateName),
                        addressPage.getStateListView(), 5, true);
            }
            if (stateElement == null) {
                throw new Exception("Unknown error while scrolling to find state");
            }
            stateElement.click();
        } catch (Exception e) {
            throw new Exception("Unknown error while changing address");
        }
    }

    public WebElement scrollUntilTheElementIsFound(By elment, By frameElement, int iteration, boolean isScrollUp)
            throws Exception {
        try {
            for (int i = 0; i < iteration; i++) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                boolean isPresent = driver.findElements(elment).size() > 0;
                if (isPresent) {
                    return driver.findElement(elment);
                } else {
                    WebElement finalFrameElement = driver.findElement(frameElement);
//                    scrollVertical(finalFrameElement, 3000, isScrollUp);
                }
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Unknown error while scrolling to find the element", e);
        }
    }

    // To scroll down or scroll up this method can be utilized. The exact frame
    // to
    // scrolled should be passed as a parameter.
//    private void scrollVertical(WebElement frameElement, int duration, boolean isScrollUp) throws Exception {
//        try {
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            Point startCoordinatesOfYearList = frameElement.getLocation();
//            Point centerCoordinatesOfYearList = ((MobileElement) frameElement).getCenter();
//            int frameStartXCoordinate = startCoordinatesOfYearList.getX();
//            int frameStartYCoordinate = startCoordinatesOfYearList.getY();
//            int centerXCoordinate = centerCoordinatesOfYearList.getX();
//            int centerYCoordinate = centerCoordinatesOfYearList.getY();
//            int commonXCoordinate = ((centerXCoordinate - frameStartXCoordinate) / 2);
//            int startYCoordinate, endYCoordinate;
//            if (isScrollUp) {
//                startYCoordinate = frameStartYCoordinate + 50;
//                endYCoordinate = ((centerYCoordinate - frameStartYCoordinate) + centerYCoordinate) - 50;
//            } else {
//                startYCoordinate = ((centerYCoordinate - frameStartYCoordinate) + centerYCoordinate) - 50;
//                endYCoordinate = frameStartYCoordinate + 50;
//            }
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            new TouchAction(driver).press(commonXCoordinate, startYCoordinate).waitAction(Duration.ofMillis(duration))
//                    .moveTo(commonXCoordinate, endYCoordinate).release().perform();
//        } catch (Exception e) {
//            throw new Exception("Unknown error while scrolling vertically", e);
//        }
//    }

    @Override
    public void logOut() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        settingPage.getLogOutButtonList().get(0).click();
    }
    
    private boolean logoutIfHomePage() throws Exception {
        try {
            boolean isLoginMissing = loginPage.getLogInText().isEmpty();
            if (!isLoginMissing) {
                // Already logged out
                return true;
            }
            boolean isLogoutMissing = settingPage.getLogOutButtonList().isEmpty();
            if (!isLogoutMissing) {
                // Logout button found
                settingPage.getLogOutButtonList().get(0).click();
                return true;
            }
            boolean getMoreIconMissing = homePage.getMoreIconButton().isEmpty();
            if (getMoreIconMissing) {
                return false;
            }
            homePage.getMoreIconButton().get(0).click();
            WebElement settingsList = settingPage.getSettingsButton().get(0);
            settingsList.click();
            settingPage.getLogOutButtonList().get(0).click();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

	@Override
	public File takeScreenshot() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void validateLogOut() throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            boolean isElementMissing = loginPage.getLogInText().isEmpty();
            if (isElementMissing) {
                throw new Exception("User not logged out successfully");
            }
        } catch (Exception e) {
            throw new Exception("Unknown error while validating logout", e);
        }
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
    public void loginWithUserName(String username) throws Exception {
        try {
            loginPage.getEmailTextBox().sendKeys(username);
            loginPage.getNextButton().click();
        } catch (Exception e) {
            throw new Exception("Unknown error while trying to enter user name");
        }
    }

    @Override
    public void loginWithPassword(String password) throws Exception {
        try {
            loginPage.getPwdTextBox().sendKeys(password);
            loginPage.getLogInButton().click();
        } catch (Exception e) {
            throw new Exception("Unknown error while trying to enter password");
        }
    }

	@Override
	public void validateLoginUserNameField() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLoginpasswordField() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLoginrememberMe(String rememberMe) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLoginuseTokenLink(String useToken) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLoginsignInButton(String signInButton) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLoginforgotLink(String forgotLink) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateLoginsignUpLink(String signUp) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateSignuperrorMessage(String errorMessaage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateErrorMessageWhileSignUp(String errorMessage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void navigateToSigninPage() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToHome() throws Exception {
		launch();
	}

	@Override
	public void visualValidtionOfLogo() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visualValiationOfMenu() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void homePageLodeTest(int time) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signInPageLodeTest(int time) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnFindaCreditCardlink() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validatePageNavigation(String pageTitle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnTryOurCardFinderLink() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnPersonalIcon() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnRewardsIcon() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnCashBackIcon() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnBalanceTransferIcon() throws IOException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateCardShowsAsPerTheCriteria(List<String> criteria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnBusinessIcon() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
