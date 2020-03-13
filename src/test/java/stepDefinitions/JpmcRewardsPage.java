package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JpmcRewardsPage extends AbstractStepDefinition {

    public JpmcRewardsPage() throws FileNotFoundException, IOException, ParseException {
        super();
    }
    
    @Then("^user validates the ultimate rewards page$")
    public void userValidatesTheUltimateRewardsPage() throws Throwable {
        platform.validateReward();
    }

    @And("^user validates open an account link$")
    public void userValidatesOpenAnAccountLink() throws Throwable {
        platform.validateOpenAccount();
    }

    @And("^user validates browse card link$")
    public void userValidatesBrowseCardLink() throws Throwable {
        platform.validateBrowseCards();
    }


    @And("^user validates manage my account footer link$")
    public void userValidatesManageMyAccountFooterLink() throws Throwable {
        platform.validateManageMyAccount();
    }

    @And("^user validates travel cards footer link$")
    public void userValidatesTravelCardsFooterLink() throws Throwable {
        platform.validateTravelCards();
    }

    @And("^user validates rewards cards footer link$")
    public void userValidatesRewardsCardsFooterLink() throws Throwable {
        platform.validateRewardCards();
    }

    @And("^user validates cash back cards footer link$")
    public void userValidatesCashBackCardsFooterLink() throws Throwable {
        platform.validateCashBackCards();
    }

    @And("^user validates partner cards footer link$")
    public void userValidatesPartnerCardsFooterLink() throws Throwable {
        platform.validatePartnerCards();
    }

    @And("^user validates small business cards footer link$")
    public void userValidatesSmallBusinessCardsFooterLink() throws Throwable {
        platform.validateSmallBusinessCards();
    }

    @When("^user signup in jpmc rewards page$")
    public void userSignupInJpmcRewardsPage() throws Throwable {
        platform.signUp();
    }

    @And("^user fill account details with (.*)$")
    public void userFillAccountDetails(String accountNumber) throws Throwable {
        String number =getDataValue(accountNumber);
        platform.fillAccountNumber(number);
    }

    @And("^user fill ssn details with (.*)$")
    public void userFillSocialSecurityNumber(String ssnNumber) throws Throwable {
        String number =getDataValue(ssnNumber);
        platform.fillSsnNumber(number);
    }

    @And("^user fill username details with (.*)$")
    public void userFillUsername(String userName) throws Throwable {
        String number =getDataValue(userName);
        platform.fillUserName(number);
    }

    @And("^user submit the information$")
    public void userSubmitTheInformation() throws Throwable {
        platform.submitInformation();
    }

    @Then("^user should validate the error messaage$")
    public void userShouldValidateTheErrorMessaage() throws Throwable {
    	String data =getDataValue("errorMessage");
        platform.validateErrorMessageWhileSignUp(data);
    }
    
    @Then("^navigate to login page$")
    public void navigate_to_login_page() throws Throwable {
    	platform.navigateToSigninPage();
    }

    @Then("^validate userNameField is displaying$")
    public void validate_userNameField_is_displaying() throws Throwable {
    	platform.validateLoginUserNameField();
    }

    @Then("^validate passwordField is displaying$")
    public void validate_passwordField_is_displaying() throws Throwable {
    	platform.validateLoginpasswordField();
    	System.out.println("password field validated");
    }

    @Then("^validate rememberMe check box and text$")
    public void validate_rememberMe_check_box_and_text() throws Throwable {
    	String data =getDataValue("rememberMe");
    	platform.validateLoginrememberMe(data);
    }

    @Then("^validate useToken link$")
    public void validate_useToken_link() throws Throwable {
    	String data =getDataValue("useToken");
    	platform.validateLoginuseTokenLink(data);
    }

    @Then("^validate signInButton and text$")
    public void validate_signInButton_and_text() throws Throwable {
    	String data =getDataValue("signInButton");
    	platform.validateLoginsignInButton(data);
    }

    @Then("^validate forgotLink in page$")
    public void validate_forgotLink_in_page() throws Throwable {
    	String data =getDataValue("forgotLink");
    	platform.validateLoginforgotLink(data);
    }

    @Then("^validate signUp link in page$")
    public void validate_signUp_link_in_page() throws Throwable {
    	String data =getDataValue("signUp");
    	platform.validateLoginsignUpLink(data);
    }
    
    @Then("^visually validate the logo is displaying in the top center of the page$")
    public void visually_validate_the_logo_is_displaying_in_the_top_center_of_the_page() throws Throwable {
    	platform.visualValidtionOfLogo();
    }
    
    @Then("^visually validate the menu button is displaying in the top left corner of the page$")
    public void visually_validate_the_menu_button_is_displaying_in_the_top_left_corner_of_the_page() throws Throwable {
    	platform.visualValiationOfMenu();
    }
    
    @Then("^Validate home page compents are loaded with in (.*) sec$")
    public void Validate_home_page_compents_are_loaded_with_in_sec(int time) throws Throwable {
    	platform.homePageLodeTest(time);
    }
    
    @Then("^Validate Signin page compents are loaded with in (.*) sec$")
    public void Validate_Signin_page_compents_are_loaded_with_in_sec(int time) throws Throwable {
    	platform.signInPageLodeTest(time);
    }
}