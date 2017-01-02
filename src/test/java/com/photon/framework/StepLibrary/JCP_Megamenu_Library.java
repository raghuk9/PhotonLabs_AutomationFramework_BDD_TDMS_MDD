package com.photon.framework.StepLibrary;

import java.io.IOException;
import java.util.List;
//import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
//import org.openqa.selenium.Keys;

import com.photon.framework.Constant.JCP_MegamenuConstants;

public class JCP_Megamenu_Library extends CommonLibrary{
	
	
	/*
	 * Method for Mega_menu highlight
	 */

	public JCP_Megamenu_Library() throws ConfigurationException, IOException {
		super();
		
	}


	public static void searchtextbox (List<String> lists) throws Exception{

				try{	
				
				
					if(isElementPresentVerification(JCP_MegamenuConstants.Menu_1)){
						
						if(Menu_Validation(JCP_MegamenuConstants.Menu_1,lists.get(0)))
							highlightElement(JCP_MegamenuConstants.Menu_1);
							}else {
						throw new Exception("Element Not Verified");
					}
					
					
					
					if(isElementPresentVerification(JCP_MegamenuConstants.Menu_2)){
						
						if(Menu_Validation(JCP_MegamenuConstants.Menu_2,lists.get(1)))
							highlightElement(JCP_MegamenuConstants.Menu_2);
						}else {
						throw new Exception("Element Not Verified");
					}
					
					if(isElementPresentVerification(JCP_MegamenuConstants.Menu_3)){
						
						if(Menu_Validation(JCP_MegamenuConstants.Menu_3,lists.get(2)))
							highlightElement(JCP_MegamenuConstants.Menu_3);
						}else {
						throw new Exception("Element Not Verified");
					}
					
					if(isElementPresentVerification(JCP_MegamenuConstants.Menu_4)){
						
						if(Menu_Validation(JCP_MegamenuConstants.Menu_4,lists.get(3)))
							highlightElement(JCP_MegamenuConstants.Menu_4);
						}else {
						throw new Exception("Element Not Verified");
					}
					
					
					
					Thread.sleep(10000);
				
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
}
