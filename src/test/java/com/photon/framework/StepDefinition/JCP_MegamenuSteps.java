package com.photon.framework.StepDefinition;

import java.util.List;
//import java.util.Map;

import com.photon.framework.StepLibrary.JCP_Megamenu_Library;

import cucumber.api.java.en.Then;

public class JCP_MegamenuSteps {
	
	@Then("^Customer validate megamenu in JCpenny website$")
	public void Customer_search_data_in_chase_dot_com(List<String> lists) throws Exception {
		JCP_Megamenu_Library.searchtextbox(lists);
	}

}
