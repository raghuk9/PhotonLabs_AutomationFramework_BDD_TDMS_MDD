package platforms;

import java.io.File;
import java.util.List;

public interface JPMCPlatform {

    void launch() throws Exception;
    
    void goToHome() throws Exception;

    void logOut() throws Exception;

    File takeScreenshot() throws Exception;

    void validateLogIn() throws Exception;

    void validateLogOut() throws Exception;

    void validateReward() throws Exception;

    void validateOpenAccount() throws Exception;

    void validateBrowseCards() throws  Exception;

    void validateManageMyAccount() throws Exception;

    void validateTravelCards() throws Exception;

    void validateRewardCards() throws Exception;

    void validateCashBackCards() throws Exception;

    void validatePartnerCards() throws Exception;

    void validateSmallBusinessCards() throws Exception;

    void signUp() throws Exception;

    void fillAccountNumber(String accountNumber) throws Exception;

    void fillSsnNumber(String ssnNumber) throws Exception;

    void fillUserName(String userName) throws Exception;

    void submitInformation() throws Exception;

    void validateErrorMessageWhileSignUp(String errorMessage) throws Exception;
  
    void loginWithUserName(String username) throws Exception;

    void loginWithPassword(String password) throws Exception;
    
    void navigateToSigninPage() throws Exception;
    
    void validateLoginUserNameField() throws Exception;
    
    void validateLoginpasswordField() throws Exception;
    
    void validateLoginrememberMe(String rememberMe) throws Exception;
    
    void validateLoginuseTokenLink(String useToken) throws Exception;
    
    void validateLoginsignInButton(String signInButton) throws Exception;
    
    void validateLoginforgotLink(String forgotLink) throws Exception;
    
    void validateLoginsignUpLink(String signUp) throws Exception;
    
    void validateSignuperrorMessage(String errorMessaage) throws Exception;
    
    void visualValidtionOfLogo() throws Exception;
    
    void visualValiationOfMenu() throws Exception;
    
    void homePageLodeTest(int time) throws Exception;
    
    void signInPageLodeTest(int time) throws Exception;
    
    void clickOnFindaCreditCardlink() throws Exception;
    
    void validatePageNavigation(String pageTitle);
    
    void clickOnTryOurCardFinderLink() throws Exception;
    
    void clickOnPersonalIcon() throws Exception;
    
    void clickOnRewardsIcon() throws Exception;
    
    void clickOnCashBackIcon() throws Exception;
    
    void clickOnBalanceTransferIcon() throws Exception;
    
    void validateCardShowsAsPerTheCriteria(List<String> criteria) throws Exception;
    
    void clickOnBusinessIcon() throws Exception;

}