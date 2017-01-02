package com.photon.framework.StepDefinition;

import java.util.List;
import java.util.Map;

import com.photon.framework.StepLibrary.CommonLibrary;
import com.photon.framework.StepLibrary.JCP_Search_Library;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class JCP_SearchSteps {
	
	
	//Add products to the Cart Scenario
	
		@Then("^Customer search data in JCPenny website$")
		public void Customer_search_data_in_chase_dot_com(DataTable arg2) throws Exception {
			Map<String, List<String>> dataMap = null;
			dataMap = CommonLibrary.getHorizontalData(arg2);
			JCP_Search_Library.searchtextbox(dataMap);
		}
		
		

}
