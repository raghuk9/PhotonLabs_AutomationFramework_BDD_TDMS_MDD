package com.photon.framework.StepDefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
//import org.openqa.selenium.TakesScreenshot;

import com.photon.framework.StepLibrary.CommonLibrary;
import com.photon.framework.StepLibrary.HomeScreenLibrary;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class HomeScreenStep extends CommonLibrary {

	public HomeScreenStep() throws ConfigurationException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Then("^Customer validate JPMC home screen explore products objects$")
	public void Customer_validate_JPMC_home_screen_explore_products_objects() throws Exception {
		//org.junit.Assert.fail("Exception expected");
		//HomeScreenLibrary.jpmc_HomeScreen_ExploreProducts_Validation();
		System.out.println("No op");
		
	}
	
	@Then("^Customer validate JPMC home screen images$")
	public void Customer_validate_JPMC_home_screen_images() throws Exception {
		org.junit.Assert.fail("Exception expected");
		HomeScreenLibrary.jpmc_HomeScreen_ImageValidation();
	}
	
	@Then("^Customer validate JPMC home screen linkTexts$")
	public void Customer_validate_JPMC_home_screen_linkTexts() throws Exception {
		HomeScreenLibrary.jpmc_HomeScreen_LinkTextValidation();
	}
	
	@Then("^Customer Login into the chase dot com$")
	public void Customer_Login_into_the_chase_dot_com(DataTable arg2) throws Exception {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg2);
		HomeScreenLibrary.login(dataMap);
		//org.junit.Assert.fail("Exception expected");
	}
	
	@Then("^Customer search data in chase dot com$")
	public void Customer_search_data_in_chase_dot_com(DataTable arg2) throws Exception {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg2);
		HomeScreenLibrary.search(dataMap);
	}
	
	@Then("^Customer navigate to \"(.*)\" products objects$")
	public void Customer_navigate_to_explore_Product_Page(String para) throws Exception {
		HomeScreenLibrary.navigateTo_ExploreProducts(para);
	}
	
		
	@After
	public void tearDown(Scenario scenario) throws Exception {
	    
		if (scenario.isFailed()) 
	    {
	    	getscreenshotEmbed("TestFail", scenario);
	    	
	    }
	    
	}
	
}
