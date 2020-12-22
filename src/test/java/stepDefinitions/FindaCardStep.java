package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FindaCardStep extends AbstractStepDefinition{
	
	public FindaCardStep() throws FileNotFoundException, IOException, ParseException {
		super();
	}

	@When("^the user clicks on find a credit card link$")
	public void the_user_clicks_on_find_a_credit_card_link() throws Throwable {
		platform.clickOnFindaCreditCardlink();
	}

	@Then("^validate user navigates to \"(.*?)\" Page$")
	public void validate_user_navigates_to_Page(String pageTitle) throws Throwable {
		platform.validatePageNavigation(pageTitle);
	}

	@Then("^the user clicks on 'Try our Card Finder' link$")
	public void the_user_clicks_on_Try_our_Card_Finder_link() throws Throwable {
		platform.clickOnTryOurCardFinderLink();
	}

	@Then("^validate user navigates to \"(.*?)\" page$")
	public void validate_user_navigates_to_page(String pageTitle) throws Throwable {
		platform.validatePageNavigation(pageTitle);
	}

	@Then("^the user clicks on Personal icon$")
	public void the_user_clicks_on_Personal_icon() throws Throwable {
		platform.clickOnPersonalIcon();
	}

	@Then("^the user clicks on Rewards icon$")
	public void the_user_clicks_on_Rewards_icon() throws Throwable {
		platform.clickOnRewardsIcon();	
	}

	@Then("^the user clicks on Cash Back icon$")
	public void the_user_clicks_on_Cash_Back_icon() throws Throwable {
		platform.clickOnCashBackIcon();
	}

	@Then("^the user clicks on No Annual Fee icon$")
	public void the_use_clicks_on_No_Annual_Fee_icon() throws Throwable {
		platform.clickOnNoAnnualFeeIcon();
	}

	@Then("^the user clicks on Balance Transfer icon$")
	public void the_user_clicks_on_Balance_Transfer_icon() throws Throwable {
		platform.clickOnBalanceTransferIcon();
	}

	@Then("^Validate application shows only the list of cards which is matching the above criteria$")
	public void validate_application_shows_only_the_list_of_cards_which_is_matching_the_above_criteria(List<String> criteria) throws Throwable {
		platform.validateCardShowsAsPerTheCriteria(criteria);
	}
	
	@Then("^the user clicks on Business icon$")
	public void the_user_clicks_on_Business_icon() throws Throwable {
		platform.clickOnBusinessIcon();
	}
}
