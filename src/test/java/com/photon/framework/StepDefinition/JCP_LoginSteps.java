package com.photon.framework.StepDefinition;

import java.util.List;
import java.util.Map;

import com.photon.framework.StepLibrary.CommonLibrary;
import com.photon.framework.StepLibrary.JCP_LogIn_Library;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class JCP_LoginSteps {

	
	//Login Scenario
	@Then("^Customer validate LoginScreen$")
	public void Customer_validate_login_screen() throws Exception {
		JCP_LogIn_Library.navigateTo_LoginScreen();		
	}
		
	@Then("^Customer Login into the JCpenny website$")
	public void Customer_login_into_jcp_web(DataTable arg2) throws Exception {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg2);
		JCP_LogIn_Library.login(dataMap); 		
	}	
	
	@Then("^Customer validate LogoutScreen$")
	public void logout_from_homepage() throws Exception {
		JCP_LogIn_Library.navigateTo_LogoutScreen();
	}
	
	
	
	
	
	
	
}
