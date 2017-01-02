package com.photon.framework.StepDefinition;

import com.photon.framework.StepLibrary.JCP_LogIn_LibrarySE;

//import cucumber.api.DataTable;
//import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JCP_LoginStepsSE {

	
	//Login Scenerio
	
	@When("^I enter username as \"([^\"]*)\"$")
	public void I_enter_username_as(String arg1) throws Throwable {
		JCP_LogIn_LibrarySE.usernameSE(arg1);
	   	Thread.sleep(2000);
	    
	}
	
	
	@When("^I enter password as \"([^\"]*)\"$")
	public void i_enter_password_as(String arg1) throws Throwable {
		JCP_LogIn_LibrarySE.passwordSE(arg1);
	    Thread.sleep(2000);
		   
	}	
	
	
	
	
	
	
	
	
}
