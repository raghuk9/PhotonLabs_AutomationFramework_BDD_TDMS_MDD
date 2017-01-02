package com.photon.framework.StepDefinition;

//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
//import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;

import com.photon.framework.StepLibrary.CommonLibrary;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CommonStep {
	public static Configuration config = null;
	static HSSFWorkbook workBook = null;
	static FileInputStream fis = null;
	static HSSFSheet workSheet = null;
	static HSSFRow row = null;

	@Before
	public void launchBrowser() throws ConfigurationException, FileNotFoundException, IOException {
		new CommonLibrary();

	}

	@Given("^Customer launch the Browser$")
	public void Customer_launch_the_Browser() throws IOException, InterruptedException, ConfigurationException {
		try{
			CommonLibrary.initiateBrowser();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@And("^Customer Close the Browser$")
	public void Customer_Close_the_Browser() throws IOException, InterruptedException {
		try{
			CommonLibrary.closeBrowser();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@Then("^Customer navigate back$")
	public void Customer_navigate_back() throws IOException, InterruptedException {
		try{
			CommonLibrary.browserNavigation_Back();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	




	@After
	public void closeBrowser() {
	}
}
