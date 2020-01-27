package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.PlatformHelper;
import org.json.simple.parser.ParseException;
import platforms.MobilePlatform;

import java.io.IOException;
import java.util.HashMap;

public class HigiSettingsSteps extends AbstractStepDefinition {
    MobilePlatform platform;
    private HashMap<String, String> data;

    private HashMap<String, String> globalData;

    public HigiSettingsSteps() throws IOException, ParseException {
        super();
        this.platform = (MobilePlatform) super.platform;
    }

    @Given("^user navigates to Account Settings$")
    public void navigateToPersonalSettings() throws Exception {
        platform.navigateToPersonalSettings();
        System.out.println("Navigated to Account Settings");
    }

    @When("^user validates Address with (.*)$")
    public void validateAddress(String stateKey)
            throws Exception {
        platform.validateAddress( getDataValue(stateKey));
    }

    @Then("^user changes Address with (.*)$")
    public void changeAddress(String stateKey)
            throws Exception {
        platform.changeAddress(getDataValue(stateKey));
    }


}
