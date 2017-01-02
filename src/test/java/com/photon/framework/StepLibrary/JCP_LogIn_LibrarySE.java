package com.photon.framework.StepLibrary;

import java.io.IOException;
//import java.util.List;
//import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

import com.photon.framework.Constant.JCP_LogIn_UIConstants;
import com.photon.framework.Constant.JCP_LogIn_UIConstantsSE;

public class JCP_LogIn_LibrarySE extends CommonLibrary {
	
	public JCP_LogIn_LibrarySE() throws ConfigurationException, IOException {
		super();
	}
	
	
	/*
	 * Method 
	 */

	public static void navigateTo_LoginScreen() throws Exception{
		try{	
	if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
		if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_my_Account_BTN)){
			if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.mobile_my_Account_BTN)) {
				throw new Exception("Element Not able to click Explore Link");
			}
		}else {
			throw new Exception("Not able to verify element");
		}
		Thread.sleep(1000);
		
		if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_SignIn_BTN)){
			if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.mobile_SignIn_BTN)) {
				throw new Exception("Element Not able to click Explore Link");
			}
		}else {
			throw new Exception("Not able to verify element");
		}
	}else {
		if(isElementPresentVerification(JCP_LogIn_UIConstants.my_Account_BTN)){
			if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.my_Account_BTN)) {
				throw new Exception("Element Not able to click my_Account_BTN");
			}
		}else {
			throw new Exception("Element Not Verified");
		}
		}
		Thread.sleep(2000);
		
} catch (Exception e){
	e.printStackTrace();
}
}
	
	
	/*
	 * Method for Username SE
	 */

	public static void usernameSE (String arg1) throws Exception{

		
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(JCP_LogIn_UIConstantsSE.userName_TXT_SE)){
					if(!clearAndEnterText(JCP_LogIn_UIConstantsSE.userName_TXT_SE,arg1)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				
				Thread.sleep(10000);
				
			} else {
				if(isElementPresentVerification(JCP_LogIn_UIConstantsSE.userName_TXT_SE)){
					if(!clearAndEnterText(JCP_LogIn_UIConstantsSE.userName_TXT_SE,arg1)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
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
	 * Method for Password SE
	 */

	public static void passwordSE (String arg1) throws Exception{

		
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(JCP_LogIn_UIConstantsSE.password_TXT_SE)){
					if(!clearAndEnterText(JCP_LogIn_UIConstantsSE.password_TXT_SE,arg1)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				
				Thread.sleep(10000);
				
			} else {
				if(isElementPresentVerification(JCP_LogIn_UIConstantsSE.password_TXT_SE)){
					if(!clearAndEnterText(JCP_LogIn_UIConstantsSE.password_TXT_SE,arg1)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				
				Thread.sleep(10000);
				
				
				if(isElementPresentVerification(JCP_LogIn_UIConstantsSE.login_BTN_SE)){
					if(!isElementPresentVerifyClick(JCP_LogIn_UIConstantsSE.login_BTN_SE)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				Thread.sleep(10000);
				
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	/*
	 * Method for LogOut 
	 */
	
	
	public static void navigateTo_LogoutScreen() throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_my_Account_BTN1)){
					if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.mobile_my_Account_BTN1)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				Thread.sleep(1000);
				
				if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_LogOut_BTN)){
					if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.mobile_LogOut_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
			}else {
				if(isElementPresentVerification(JCP_LogIn_UIConstants.Logout_BTN)){
					if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.Logout_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				Thread.sleep(2000);
				
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

		

}
