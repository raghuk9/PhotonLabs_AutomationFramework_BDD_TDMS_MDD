package com.photon.framework.StepLibrary;

import java.io.IOException;
//import java.util.List;
//import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

import com.photon.framework.Constant.CheckingScreenUIConstants;


public class CheckingScreenLibrary extends CommonLibrary {

	public CheckingScreenLibrary() throws ConfigurationException, IOException {
		super();
	}

	public static void checkingScreen_Validation () throws Exception{
		try{	

			if(isElementPresentVerification(CheckingScreenUIConstants.checking_Screen_PopUp_ZipCode_TXT)){
				clearAndEnterText(CheckingScreenUIConstants.checking_Screen_PopUp_ZipCode_TXT, "60090");
			} else {
				throw new Exception("Not able to verify element");
			}
			if(isElementPresentVerification(CheckingScreenUIConstants.checking_Screen_PopUp_Enter_BTN)){
				isElementPresentVerifyClick(CheckingScreenUIConstants.checking_Screen_PopUp_Enter_BTN);
			} else {
				throw new Exception("Not able to verify element");
			}
			if(isElementPresentVerification(CheckingScreenUIConstants.checking_Screen_IMG)){

				validateImagePosition(CheckingScreenUIConstants.checking_Screen_IMG,CheckingScreenUIConstants.checking_Screen_IMG_Cooridinate, CheckingScreenUIConstants.checking_Screen_NameOf_Logo);

				validateImageSize(CheckingScreenUIConstants.checking_Screen_IMG,CheckingScreenUIConstants.checking_Screen_IMG_HeightWidth, CheckingScreenUIConstants.checking_Screen_NameOf_Logo);

				accessibilityValidation(CheckingScreenUIConstants.checking_Screen_IMG, CheckingScreenUIConstants.checking_Screen_IMG_Accessibility, CheckingScreenUIConstants.checking_Screen_NameOf_Logo);

			} else {
				throw new Exception("Not able to verify element");
			}
			Thread.sleep(5000);
		} catch (Exception e){
			e.printStackTrace();
		}

	}



}
