package com.photon.framework.StepLibrary;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;

import com.photon.framework.Constant.CreditcardsUIConstants;


public class CreditcardScreenLibrary extends CommonLibrary {

	public CreditcardScreenLibrary() throws ConfigurationException, IOException {
		super();
	}

	public static void creditcardScreen_Validation () throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){

				if(isElementPresentVerification(CreditcardsUIConstants.creditCards_Screen_IMG)){

					validateImagePosition(CreditcardsUIConstants.creditCards_Screen_IMG_mobile,CreditcardsUIConstants.creditCards_Screen_IMG_Cooridinate_Mobile, CreditcardsUIConstants.creditCards_Screen_NameOf_Logo);

					validateImageSize(CreditcardsUIConstants.creditCards_Screen_IMG_mobile,CreditcardsUIConstants.creditCards_Screen_IMG_HeightWidth_Mobile, CreditcardsUIConstants.creditCards_Screen_NameOf_Logo);

					accessibilityValidation(CreditcardsUIConstants.creditCards_Screen_IMG_mobile, CreditcardsUIConstants.creditCards_Screen_IMG_Accessibility, CreditcardsUIConstants.creditCards_Screen_NameOf_Logo);

				} else {
					throw new Exception("Not able to verify element");
				}

			}else {
				if(isElementPresentVerification(CreditcardsUIConstants.creditCards_Screen_IMG)){

					validateImagePosition(CreditcardsUIConstants.creditCards_Screen_IMG,CreditcardsUIConstants.creditCards_Screen_IMG_Cooridinate, CreditcardsUIConstants.creditCards_Screen_NameOf_Logo);

					validateImageSize(CreditcardsUIConstants.creditCards_Screen_IMG,CreditcardsUIConstants.creditCards_Screen_IMG_HeightWidth, CreditcardsUIConstants.creditCards_Screen_NameOf_Logo);

					accessibilityValidation(CreditcardsUIConstants.creditCards_Screen1_IMG, CreditcardsUIConstants.creditCards_Screen_IMG_Accessibility, CreditcardsUIConstants.creditCards_Screen_NameOf_Logo);

				} else {
					throw new Exception("Not able to verify element");
				}

			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}



}
