package pageobjects.webportal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import helpers.ConfigurationHelper;

public class JpmcRewardsPage {

    private RemoteWebDriver driver;
        
    public JpmcRewardsPage(RemoteWebDriver driver){
        this.driver=driver;
    }

    private Point getLocation(Point webLocation,Point androidLocation) throws FileNotFoundException, IOException, ParseException {
    	Point point = null;
    	if(ConfigurationHelper.getPlatform().equalsIgnoreCase("android_Web")) {
    		point = androidLocation;
    	}else {
    		point = webLocation;
    	}
    	return point;
    }
    
    public Dimension getSize(Dimension webSize,Dimension androidSize) throws FileNotFoundException, IOException, ParseException {
    	Dimension dimension = null;
    	if(ConfigurationHelper.getPlatform().equalsIgnoreCase("android_Web")) {
    		dimension = androidSize;
    	}else {
    		dimension = webSize;
    	}
    	return dimension;
    }
    
    public Point logoLocation() throws FileNotFoundException, IOException, ParseException {
    	Point point = getLocation(new Point(630, 0), new Point(128, 10));
    	return point;
    }
    
    public Dimension logoSize() throws FileNotFoundException, IOException, ParseException {
    	Dimension dimension = getSize(new Dimension(179, 70), new Dimension(136, 56));
    	return dimension;
    }
    
    public Point menuLocation() throws FileNotFoundException, IOException, ParseException {
    	Point point = getLocation(new Point(128, 0), new Point(8, 0));
    	return point;
    }
    
    public Dimension menuSize() throws FileNotFoundException, IOException, ParseException {
    	Dimension dimension = getSize(new Dimension(22, 70), new Dimension(23, 56));
    	return dimension;
    }
    
    public WebElement getRewardsElement(){  
        return driver.findElement(By.className("single-logo-icon"));
    }

    public WebElement getOpenAnAccountLink(){
        return driver.findElement(By.className("header__section--dropdown__title__link"));
    }

    public WebElement getBrowseCardsButton(){
        return driver.findElement(By.className("body-copy__cta--buttons"));
    }

    public WebElement getManageMyAccount(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[1]/div/h2"));
    }

    public WebElement getTravelCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[2]/div/h2"));
    }

    public WebElement getRewardsCard(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[3]/div/h2"));
    }

    public WebElement getCashBackCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[4]/div/h2"));
    }

    public WebElement getPartnerCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[5]/div/h2"));
    }

    public WebElement getSmallBusinessCards(){
        return driver.findElement(By.xpath("/html/body/div/footer/div/div[2]/div/div/div[2]/div[1]/div/div[6]/div/h2"));
    }

    public WebElement getSignUpLink(){
        return driver.findElement(By.id("enrollment"));
    }

    public WebElement getSignUpHome() {
    	return driver.findElement(By.xpath("//*[@class='jpui primary link last chaseanalytics-track-link']"));
    }
    
    public WebElement getAccountField(){
        return driver.findElement(By.id("accountIdentifier-text-validate-input-field"));
    }

    public WebElement getAccountFieldHighlighted(){
        return driver.findElement(By.id("accountIdentifier-label"));
    }

    public WebElement getSsnNumberField(){
        return driver.findElement(By.id("socialSecurityNumber-text-validate-input-field"));
    }

    public WebElement getUserNameField(){
        return driver.findElement(By.id("userId-text-validate-input-field"));
    }

    public WebElement submitSignUpInformation(){
        return driver.findElement(By.id("nextButton"));
    }

    public WebElement getSignUpError(){
        return driver.findElement(By.id("inner-identificationError"));
    }
    
    public WebElement getLoginWelcomeMessage(){
        return driver.findElement(By.id("welcomeHeader"));
    }
    
    public WebElement getLoginWelcomeSection(){
        return driver.findElement(By.id("logonbox"));
    }
    
    public WebElement getLoginUserNameField(){
        return driver.findElement(By.id("userId-text-input-field"));
    }
    
    public WebElement getLoginPasswordField(){
        return driver.findElement(By.id("password-text-input-field"));
    }
    
    public WebElement getLoginRememberMeText(){
        return driver.findElement(By.id("label-rememberMe"));
    }
    
    public WebElement getLoginUseTokenText(){
        return driver.findElement(By.id("useToken"));
    }
    
    public WebElement getLoginSigninButton(){
        return driver.findElement(By.id("signin-button"));
    }
    
    public WebElement getMenuButton(){
        return driver.findElement(By.id("skip-sidemenu"));
    }
    
    public WebElement getMenuSignInButton(){
        return driver.findElement(By.xpath("//a[contains(@class,'signInBtn')]/p/parent::a"));
    }
    
    public WebElement getLoginForgotPasswordLink(){
        return driver.findElement(By.id("forgotPassword"));
    }
    
    public WebElement getHeaderLogo() {
    	return driver.findElement(By.xpath("//*[@class='header__section--center--link chaseanalytics-track-link']"));
    }
    
    /////////////////////////////
    
    public WebElement getFindaCardLink() {
    	return driver.findElement(By.xpath("//div[@id='slick-slide01']//div[@class='carousel--custom-pager__icon icon-credit-medium']"));
    }
    
    public WebElement getTryOurCardFinderLink() {
    	return driver.findElement(By.xpath("//a[@data-lh-name='CardFinder']"));
    }
    
    public WebElement getPersonalIcon() {
    	return driver.findElement(By.xpath("//span[text()='Personal']/parent::a"));
    }
    
    public WebElement getRewardsIcon() {
    	return driver.findElement(By.xpath("//span[text()='Rewards']/parent::a[@style='display: inline-block;']"));
    }
    
    public WebElement getCashBackIcon() {
    	return driver.findElement(By.xpath("//span[text()='Cash Back']/parent::a[@style='display: inline-block;']"));
    }

    public WebElement getNoAnnualFeeIcon() {
        return driver.findElement(By.xpath("//span[text()='No Annual Fee']/parent::a[@style='display: inline-block;']"));
    }
    
    public WebElement getBalanceTransferIcon() {
    	return driver.findElement(By.xpath("//span[text()='Balance Transfer']/parent::a[@style='display: inline-block;']"));
    }
    
    public List<WebElement> getCardSection(){
    	return driver.findElements(By.xpath("//div[contains(@id,'slick-slide0')]"));
    }
    
    public List<WebElement> getCardCriteria(int index){
    	return driver.findElements(By.xpath("(//div[contains(@id,'slick-slide0')])["+index+"]//li"));
    }
    
    public WebElement getBusinessIcon() {
    	return driver.findElement(By.xpath("//span[text()='Business']/parent::a"));
    }
}
