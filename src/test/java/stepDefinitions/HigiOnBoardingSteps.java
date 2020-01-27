package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.PlatformHelper;
import org.json.simple.parser.ParseException;
import platforms.MobilePlatform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class HigiOnBoardingSteps extends AbstractStepDefinition  {
    MobilePlatform platform;
    private HashMap<String, String> data;

    private HashMap<String, String> globalData;

    public HigiOnBoardingSteps() throws FileNotFoundException, IOException, ParseException {
        super();
        this.platform = (MobilePlatform) super.platform;
        //this.data = (MobilePlatform)super.data;
    }

    @Given("^user navigates to sign up")
    public void navigateToSignUp() throws Exception {
        platform.navigateToSignUp();
    }

    @When("^user signs up with email (.*) and (.*)")
    public void registerWithEmail(String email, String password) throws Exception {
        platform.signUpWithEmail(getDataValue(email), getDataValue(password));
    }

    @Then("^user validates error message (.*) when the password is incorrect$")
    public void validateErrorMessage(String message) throws Exception {
        platform.validatePasswordErrorMessage(getDataValue(message));
    }
}
