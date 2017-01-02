package com.photon.framework.StepDefinition;

//import java.util.List;
//import java.util.Map;

//import com.phtn.poc.StepLibrary.CommonLibrary;
//import com.phtn.poc.StepLibrary.JCP_Search_Library;

//import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class JCP_TestFailSteps {
	
	
	//Failure scenario
	
		@Then("^Introduce Failure$")
		public void Introduce_Failure() throws Exception {
			org.junit.Assert.fail("Failing the test case");
		}
		
		

}
