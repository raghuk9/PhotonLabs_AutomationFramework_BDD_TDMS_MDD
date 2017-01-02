package com.photon.framework.StepLibrary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

import com.photon.framework.Constant.JCPUIConstants;

public class JCPLibrary extends CommonLibrary {

	public JCPLibrary() throws ConfigurationException, IOException {
		super();
	}
	
	/*
	 * Method 
	 */

	public static void navigateTo_LoginScreen() throws Exception{
		try{	
			
			if(isElementPresentVerification(JCPUIConstants.my_Account_BTN)){
				if(!isElementPresentVerifyClick(JCPUIConstants.my_Account_BTN)) {
					throw new Exception("Element Not able to click my_Account_BTN");
				}
			}else {
				throw new Exception("Element Not Verified");
			}
		
			
			
	}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * Method 
	 */

	public static void login (Map<String, List<String>> dataMap) throws Exception{

		String usrName =getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "UserName");
		String passWord = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Password");
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(JCPUIConstants.userName_TXT_mobile)){
					if(!clearAndEnterText(JCPUIConstants.userName_TXT_mobile,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCPUIConstants.password_TXT_mobile)){
					if(!clearAndEnterText(JCPUIConstants.password_TXT_mobile,passWord)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
			} else {
				if(isElementPresentVerification(JCPUIConstants.userName_TXT)){
					if(!clearAndEnterText(JCPUIConstants.userName_TXT,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCPUIConstants.password_TXT)){
					if(!clearAndEnterText(JCPUIConstants.password_TXT,passWord)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCPUIConstants.login_BTN)){
					if(!isElementPresentVerifyClick(JCPUIConstants.login_BTN)) {
						throw new Exception("Element Not sblr to click");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				Thread.sleep(10000);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Method 
	 */

		public static void navigateTo_LogoutScreen() throws Exception{
		
			try {
			
				if(isElementPresentVerification(JCPUIConstants.Logout_BTN)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Logout_BTN)) {
					throw new Exception("Element Not able to click Logout_BTN");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
		
			
			
			}
		catch (Exception e){
			e.printStackTrace();
		}
		}
	
		/*
		 * Method 
		 */

		public static void searchtextbox (Map<String, List<String>> dataMap) throws Exception{

			String search_keyword =getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "SearchValue");
			try{	
				
					if(isElementPresentVerification(JCPUIConstants.Search_TXT)){
						if(!clearAndEnterText(JCPUIConstants.Search_TXT,search_keyword)) {
							throw new Exception("User Not able to Enter search_keyword in Search TextField");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
					
					if(isElementPresentVerification(JCPUIConstants.Search_BTN)){
						if(!isElementPresentVerifyClick(JCPUIConstants.Search_BTN)) {
							throw new Exception("Element Not sblr to click");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
					Thread.sleep(10000);
				
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		/*
		 * Method 
		 */
		
		public static void validate_PLPpage() throws Exception{
			
			try {
					if(isElementPresentVerification(JCPUIConstants.Product_Select_BTN)){
							if(!isElementPresentVerifyClick(JCPUIConstants.Product_Select_BTN)) {
								throw new Exception("Element Not able to click Product_Select_BTN");
							}
						}else {
							throw new Exception("Element Not Verified");
						}
					
				}
					catch (Exception e){
						e.printStackTrace();
					}
					}

	
		/*
		 * Method 
		 */
		
		public static void validate_PDPpage() throws Exception{
			
			try {
						
				{
					Thread.sleep(10000);
					if(isElementPresentVerification(JCPUIConstants.POPUP_BTN)){
						if(!isElementPresentVerifyClick(JCPUIConstants.POPUP_BTN)) {
							throw new Exception("Element Not able to click POPUP_BTN");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
					if(isElementPresentVerification(JCPUIConstants.Size_Select_BTN)){
						if(!isElementPresentVerifyClick(JCPUIConstants.Size_Select_BTN)) {
							throw new Exception("Element Not able to click Size_Select_BTN");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
					if(isElementPresentVerification(JCPUIConstants.Sleevesize_Select_BTN)){
						if(!isElementPresentVerifyClick(JCPUIConstants.Sleevesize_Select_BTN)) {
							throw new Exception("Element Not able to click Sleevesize_Select_BTN");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
					if(isElementPresentVerification(JCPUIConstants.AddTOBAG_BTN)){
						if(!isElementPresentVerifyClick(JCPUIConstants.AddTOBAG_BTN)) {
							throw new Exception("Element Not sblr to click");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
					Thread.sleep(10000);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
	
		/*
		 * Method 
		 */
		
		public static void validate_Checkoutpage() throws Exception{
			
			try {
						Thread.sleep(10000);
						if(isElementPresentVerification(JCPUIConstants.CHECKOUT_POPUP_BTN)){
							if(!isElementPresentVerifyClick(JCPUIConstants.CHECKOUT_POPUP_BTN)) {
								throw new Exception("Element Not able to click CHECKOUT_POPUP_BTN");
							}
						}else {
							throw new Exception("Element Not Verified");
						}
						if(isElementPresentVerification(JCPUIConstants.CHECKOUT_BTN)){
							if(!isElementPresentVerifyClick(JCPUIConstants.CHECKOUT_BTN)) {
								throw new Exception("Element Not able to click CHECKOUT_BTN");
							}
						}else {
							throw new Exception("Element Not Verified");
						}
						Thread.sleep(10000);
						
				}
					catch (Exception e){
						e.printStackTrace();
					}
					}
		
		
		
		public static void menulinks (Map<String, List<String>> dataMap) throws Exception{

			//String menu01 =getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Menuname");
			//String menu02 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Menuname");
			//String search_keyword =getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "SearchValue");
			try{	
				if(isElementPresentVerification(JCPUIConstants.Menu_01)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Menu_01)) {
						throw new Exception("Element Not able to click Menu_01");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCPUIConstants.Menu_02)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Menu_02)) {
						throw new Exception("Element Not able to click Menu_02");
					}
				}else {
					throw new Exception("Element Not Verified");
				}							
				if(isElementPresentVerification(JCPUIConstants.Menu_03)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Menu_03)) {
						throw new Exception("Element Not able to click Menu_03");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCPUIConstants.Menu_04)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Menu_04)) {
						throw new Exception("Element Not able to click Menu_04");
					}
				}else {
					throw new Exception("Element Not Verified");
				}				
				if(isElementPresentVerification(JCPUIConstants.Menu_05)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Menu_05)) {
						throw new Exception("Element Not able to click Menu_05");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				
				if(isElementPresentVerification(JCPUIConstants.Menu_06)){
					if(!isElementPresentVerifyClick(JCPUIConstants.Menu_06)) {
						throw new Exception("Element Not able to click Menu_06");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				Thread.sleep(10000);
				
			} catch (Exception e){
				e.printStackTrace();
			}
		}

		
		
		
		
		
		
		
		
		
		
		
	
	
}
