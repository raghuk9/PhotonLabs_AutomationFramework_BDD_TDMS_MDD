package platforms;

import java.io.File;

public interface JPMCPlatform {

    void launch() throws Exception;

    void logOut() throws Exception;

    File takeScreenshot() throws Exception;

    void navigateToLogin() throws Exception;

    void login() throws Exception;

    void validateLogIn() throws Exception;

    void validateLogOut() throws Exception;
    
    void enterEmailAndPassword(String email, String password) throws Exception;
    
    void validateUnsuccessfulLogin(String error) throws Exception;

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

}