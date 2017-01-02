package com.photon.framework.StepLibrary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

import com.photon.framework.Constant.JCP_LogIn_UIConstants;
import com.photon.framework.Constant.androidNativeappORConstants;

public class JCP_LogIn_Library extends CommonLibrary {
	
	public JCP_LogIn_Library() throws ConfigurationException, IOException {
		super();
	}
	
	
	
	public static void navigateTo_LoginScreen() throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile") && config.getString("appType").equalsIgnoreCase("androidNative")){
				if(isElementPresentVerificationNativeApp(androidNativeappORConstants.noThanks_BTN)){
					if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.noThanks_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}
				/*if(isElementPresentVerificationNativeApp(androidNativeappORConstants.allow_BTN)){
					if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.allow_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}*/
				Thread.sleep(9000);
				if(isElementPresentVerificationNativeApp(androidNativeappORConstants.signIN_BTN)){
					if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.signIN_BTN)) {
						throw new Exception("Element Not able to click signIN button");
					}
				}
				Thread.sleep(3000);
			}
			else {
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
		}
		Thread.sleep(2000);
		
} catch (Exception e){
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
			
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile") && config.getString("appType").equalsIgnoreCase("androidNative")){
				if(isElementPresentVerificationNativeApp(androidNativeappORConstants.sign_IN_BTN)){
					if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.sign_IN_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}
				
				if(isElementPresentVerificationNativeApp(androidNativeappORConstants.signIN_Email_TXT)){
					if(!clearAndEnterText_NativeApp(androidNativeappORConstants.signIN_Email_TXT,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}
if(isElementPresentVerificationNativeApp(androidNativeappORConstants.signIN_password_TXT)){
	if(!clearAndEnterText_NativeApp(androidNativeappORConstants.signIN_password_TXT,passWord)) {
		throw new Exception("User Not able to Enter Password in Password TextField");
	}
				}

if(isElementPresentVerificationNativeApp(androidNativeappORConstants.sign_SignIN_BTN)){
	if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.sign_SignIN_BTN)) {
		throw new Exception("Element Not able to click Explore Link");
	}
}
			}
			else{
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_userName_TXT)){
					if(!clearAndEnterText(JCP_LogIn_UIConstants.mobile_userName_TXT,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_password_TXT)){
					if(!clearAndEnterText(JCP_LogIn_UIConstants.mobile_password_TXT,passWord)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				
				if(isElementPresentVerification(JCP_LogIn_UIConstants.mobile_LogIn_BTN)){
					if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.mobile_LogIn_BTN)) {
						throw new Exception("User Not able to Click Mobile Login button");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				Thread.sleep(10000);
				
			} else {
				if(isElementPresentVerification(JCP_LogIn_UIConstants.userName_TXT)){
					if(!clearAndEnterText(JCP_LogIn_UIConstants.userName_TXT,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCP_LogIn_UIConstants.password_TXT)){
					if(!clearAndEnterText(JCP_LogIn_UIConstants.password_TXT,passWord)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(JCP_LogIn_UIConstants.login_BTN)){
					if(!isElementPresentVerifyClick(JCP_LogIn_UIConstants.login_BTN)) {
						throw new Exception("Element Not sblr to click");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				Thread.sleep(10000);
			}
		}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Method 
	 */
	
	
	public static void navigateTo_LogoutScreen() throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile") && config.getString("appType").equalsIgnoreCase("androidNative")){
				if(isElementPresentVerificationNativeApp(androidNativeappORConstants.setting_ICON)){
					if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.setting_ICON)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}
				if(isElementPresentVerificationNativeApp(androidNativeappORConstants.sign_Out)){
					if(!isElementPresentVerifyClick_nativeApp(androidNativeappORConstants.sign_Out)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}
			}
			else{
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
		}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

		

}
