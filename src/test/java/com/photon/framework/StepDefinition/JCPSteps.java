package com.photon.framework.StepDefinition;
/*package com.jpmc.poc.StepDefinition;

import java.util.List;
import java.util.Map;

import com.jpmc.poc.StepLibrary.CommonLibrary;
import com.jpmc.poc.StepLibrary.JCPLibrary;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class JCPSteps {

	
	//Login Scenerio
	@Then("^Customer validate LoginScreen$")
	public void Customer_validate_JPMC_home_screen_images() throws Exception {
		JCPLibrary.navigateTo_LoginScreen();
	}
	
	
	@Then("^Customer Login into the jcp website$")
	public void Customer_Login_into_the_jcp_website(DataTable arg2) throws Exception {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg2);
		JCPLibrary.login(dataMap);
	}
	
	
	@Then("^Customer validate LogoutScreen$")
	public void logout_from_homepage() throws Exception {
		JCPLibrary.navigateTo_LogoutScreen();
	}
	
	//Add products to the Cart Scenerio
	
	@Then("^Customer search data in jcp website$")
	public void Customer_search_data_in_jcp_website(DataTable arg2) throws Exception {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg2);
		JCPLibrary.searchtextbox(dataMap);
	}
	
	@Then("^user select the product in PLP page$")
	public void Select_product_PLPpage() throws Exception {
		JCPLibrary.validate_PLPpage();
	}
	
	@Then("^user validate the PDP page$")
	public void Select_size_PDPpage() throws Exception {
		JCPLibrary.validate_PDPpage();
	}
	
	@Then("^user validate the Checkout$")
	public void Validate_Checkout_page() throws Exception {
		JCPLibrary.validate_Checkoutpage();
	}
	
	//Megamenu Validations
	
	@Then("^Validate the  Megamenu should contain the following links$")
	public void Validate_Megamenu_Links(DataTable arg2) throws Exception {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getVerticalData(arg2);
		JCPLibrary.menulinks(dataMap);
	}
	
	
	
	
	
}
*/