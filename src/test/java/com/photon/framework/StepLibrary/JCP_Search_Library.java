package com.photon.framework.StepLibrary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.Keys;

import com.photon.framework.Constant.JCP_Search_Constants;

public class JCP_Search_Library extends CommonLibrary{
	
	
	/*
	 * Method 
	 */

	public JCP_Search_Library() throws ConfigurationException, IOException {
		super();
		
	}


	public static void searchtextbox (Map<String, List<String>> dataMap) throws Exception{

		
		
		String[] Products = {"SearchValue1","SearchValue2","SearchValue3"};
			for(int i=0;i<Products.length;i++)
		
			{
				String ProductName =getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), Products[i]);
				
				try{	
					if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
						if(isElementPresentVerification(JCP_Search_Constants.mobile_Search_BTN)){
							if(!clearAndEnterText(JCP_Search_Constants.mobile_Search_BTN,ProductName)) {
								throw new Exception("Element Not able to click Explore Link");
							}
						}else {
							throw new Exception("Not able to verify element");
						}
						
						Thread.sleep(7000);
						element.sendKeys(Keys.ENTER);
						Thread.sleep(7000);
					}else {
						if(isElementPresentVerification(JCP_Search_Constants.Search_TXT)){
							if(!clearAndEnterText(JCP_Search_Constants.Search_TXT,ProductName)) {
								throw new Exception("User Not able to Enter search_keyword in Search TextField");
							}
						}else {
							throw new Exception("Not able to verify element");
						}
						
						Thread.sleep(5000);
						element.sendKeys(Keys.ENTER);
						Thread.sleep(5000);
					}
					Thread.sleep(5000);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
	}
		
			
}
