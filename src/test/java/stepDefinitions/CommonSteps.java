package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.json.simple.parser.ParseException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.NoSuchElementException;

public class CommonSteps extends AbstractStepDefinition {
    public CommonSteps() throws FileNotFoundException, IOException, ParseException {
        super();
    }
    
    @Given("JPMC application is launched")
    public void launch() throws Exception {
        platform.launch();
    }

    @And("user navigates to Login")
    public void navigateToLogin() throws Exception {
        platform.navigateToLogin();
    }

    @Given("^higi is launched$")
    public void launchHigi() throws Exception {
        platform.launch();
    }

    @And("^user is logged out$")
    public void attemptLogOut() throws Exception {
        try {
            platform.logOut();
            platform.validateLogOut();
        } catch (Exception e) {
            // Already logged out
        }
    }

    @When("^user logs in with email (.*) and (.*)$")
    public void login(String emailKey, String passwordKey) throws Exception {
        platform.loginWithUserName(getDataValue(emailKey));
        platform.loginWithPassword(getDataValue(passwordKey));
    }

    @Then("^user is logged in successfully$")
    public void validateLogIn() throws Exception {
        platform.validateLogIn();
    }

    @And("^user logs out$")
    public void logOut() throws Exception {
        platform.logOut();
    }

    @Then("^user is logged out successfully$")
    public void validateLogout() throws Exception {
        platform.validateLogOut();
    }
}