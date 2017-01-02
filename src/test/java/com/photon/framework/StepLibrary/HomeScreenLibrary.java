package com.photon.framework.StepLibrary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
//import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.photon.framework.Constant.HomeScreenUIConstants;

public class HomeScreenLibrary extends CommonLibrary {

	public HomeScreenLibrary() throws ConfigurationException, IOException {
		super();
	}

	/*
	 * Method 
	 */

	public static void jpmc_HomeScreen_ExploreProducts_Validation () throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_ICN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_ICN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				Thread.sleep(1000);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_CreditCards_mobile);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_Checking_mobile);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_savingsAndCDs_mobile);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_ReloadableCard_mobile);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_AutoLoans_mobile);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_Mortgage_mobile);
				Thread.sleep(1000);
				if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_Home_BTN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_Home_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
			}else {
				if(isElementPresentVerification(HomeScreenUIConstants.explore_Products)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				Thread.sleep(2000);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_CreditCards);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_Checking);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_savingsAndCDs);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_ReloadableCard);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_AutoLoans);
				isElementPresentVerification(HomeScreenUIConstants.explore_Products_Mortgage);
				Thread.sleep(2000);
				if(isElementPresentVerification(HomeScreenUIConstants.explore_Products)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * Method 
	 */

	public static void search (Map<String, List<String>> dataMap) throws Exception{

		String search_keyword =getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "SearchValue");
		
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_ICN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_ICN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_Search_TXT_mobile)){
					element = webDriver.findElement(By.xpath(HomeScreenUIConstants.homeScreen_Search_TXT_mobile));
					Thread.sleep(1000);
					element.sendKeys(search_keyword);
					Thread.sleep(1000);
					element.sendKeys(Keys.ENTER);
				}else {
					throw new Exception("Element Not Verified");
				}
			}else {

				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_Search_ICN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.homeScreen_Search_ICN)) {
						throw new Exception("Element Not able to click");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_Search_TXT)){
					//				if(!clearAndEnterText(HomeScreenUIConstants.userName_TXT,search_keyword)) {
					//					throw new Exception("User Not able to Enter search keyword in search TextField");
					//				}
					element = webDriver.findElement(By.xpath(HomeScreenUIConstants.homeScreen_Search_TXT));
					Thread.sleep(1000);
					element.sendKeys(search_keyword);
					Thread.sleep(1000);
					element.sendKeys(Keys.ENTER);
				}else {
					throw new Exception("Element Not Verified");
				}
			}
			Thread.sleep(5000);
		} catch (Exception e){
			e.printStackTrace();
		}
	}



	/*
	 * Method 
	 */

	public static void jpmc_HomeScreen_ImageValidation () throws Exception{
		try{	

			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
//				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_compare_CC_IMG_mobile)){
//					validateImagePosition(HomeScreenUIConstants.homeScreen_compare_CC_IMG_mobile,HomeScreenUIConstants.homeScreen_CC_IMG_Cooridinate, HomeScreenUIConstants.homeScreen_NameOf_CC_IMG);
//
//					validateImageSize(HomeScreenUIConstants.homeScreen_compare_CC_IMG_mobile,HomeScreenUIConstants.homeScreen_CC_IMG_HeightWidth, HomeScreenUIConstants.homeScreen_NameOf_CC_IMG);
//
//					accessibilityValidation(HomeScreenUIConstants.homeScreen_compare_CC_IMG_mobile, HomeScreenUIConstants.homeScreen_CC_IMG_Accessibility, HomeScreenUIConstants.homeScreen_NameOf_CC_IMG);
//				} 
//				else {
//					throw new Exception("Not able to verify Image");
//				}
			}else {
				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_compare_CC_IMG)){
					validateImagePosition(HomeScreenUIConstants.homeScreen_compare_CC_IMG,HomeScreenUIConstants.homeScreen_CC_IMG_Cooridinate, HomeScreenUIConstants.homeScreen_NameOf_CC_IMG);

					validateImageSize(HomeScreenUIConstants.homeScreen_compare_CC_IMG,HomeScreenUIConstants.homeScreen_CC_IMG_HeightWidth, HomeScreenUIConstants.homeScreen_NameOf_CC_IMG);

					accessibilityValidation(HomeScreenUIConstants.homeScreen_compare_CC_IMG, HomeScreenUIConstants.homeScreen_CC_IMG_Accessibility, HomeScreenUIConstants.homeScreen_NameOf_CC_IMG);
				}
				else {
					throw new Exception("Not able to verify Image");
				}
			}

			Thread.sleep(2000);
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
//				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_Rewards_IMG_mobile)){
//					validateImagePosition(HomeScreenUIConstants.homeScreen_Rewards_IMG_mobile,HomeScreenUIConstants.homeScreen_Rewards_IMG_Cooridinate, HomeScreenUIConstants.homeScreen_NameOf_Rewards_Logo_IMG);
//					validateImageSize(HomeScreenUIConstants.homeScreen_Rewards_IMG_mobile,HomeScreenUIConstants.homeScreen_Rewards_IMG_HeightWidth, HomeScreenUIConstants.homeScreen_NameOf_Rewards_Logo_IMG);
//					accessibilityValidation(HomeScreenUIConstants.homeScreen_Rewards_IMG_mobile, HomeScreenUIConstants.homeScreen_Rewards_IMG_Accessibility, HomeScreenUIConstants.homeScreen_NameOf_Rewards_Logo_IMG);
//				} 
			}else {
				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_Rewards_IMG)){
					validateImagePosition(HomeScreenUIConstants.homeScreen_Rewards_IMG,HomeScreenUIConstants.homeScreen_Rewards_IMG_Cooridinate, HomeScreenUIConstants.homeScreen_NameOf_Rewards_Logo_IMG);
					validateImageSize(HomeScreenUIConstants.homeScreen_Rewards_IMG,HomeScreenUIConstants.homeScreen_Rewards_IMG_HeightWidth, HomeScreenUIConstants.homeScreen_NameOf_Rewards_Logo_IMG);
					accessibilityValidation(HomeScreenUIConstants.homeScreen_Rewards_IMG, HomeScreenUIConstants.homeScreen_Rewards_IMG_Accessibility, HomeScreenUIConstants.homeScreen_NameOf_Rewards_Logo_IMG);
				}
				else {
					throw new Exception("Not able to verify Image");
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * Method 
	 */

	public static void jpmc_HomeScreen_LinkTextValidation () throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_ICN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_ICN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_SignIn_BTN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_SignIn_BTN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_ForgetPassword_ELE_mobile)){
					linkText_Validation(HomeScreenUIConstants.homeScreen_ForgetPassword_ELE_mobile, HomeScreenUIConstants.homeScreen_Forget_Password_LinkTXT_mobile);
				}else {
					throw new Exception("Not able to verify element");
				}
			}else {
				Thread.sleep(2000);
				if(isElementPresentVerification(HomeScreenUIConstants.homeScreen_ForgetPassword_ELE)){
					linkText_Validation(HomeScreenUIConstants.homeScreen_ForgetPassword_ELE, HomeScreenUIConstants.homeScreen_Forget_Password_LinkTXT);
				}else {
					throw new Exception("Not able to verify element");
				}
			}
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
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(HomeScreenUIConstants.userName_TXT_mobile)){
					if(!clearAndEnterText(HomeScreenUIConstants.userName_TXT_mobile,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.password_TXT_mobile)){
					if(!clearAndEnterText(HomeScreenUIConstants.password_TXT_mobile,passWord)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
//				if(isElementPresentVerification(HomeScreenUIConstants.login_BTN_mobile)){
//					if(!isElementPresentVerifyClick(HomeScreenUIConstants.login_BTN_mobile)) {
//						throw new Exception("Element Not sblr to click");
//					}
//				}else {
//					throw new Exception("Element Not Verified");
//				}
			} else {
				if(isElementPresentVerification(HomeScreenUIConstants.userName_TXT)){
					if(!clearAndEnterText(HomeScreenUIConstants.userName_TXT,usrName)) {
						throw new Exception("User Not able to Enter UserName in UserName TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.password_TXT)){
					if(!clearAndEnterText(HomeScreenUIConstants.password_TXT,passWord)) {
						throw new Exception("User Not able to Enter Password in Password TextField");
					}
				}else {
					throw new Exception("Element Not Verified");
				}
				if(isElementPresentVerification(HomeScreenUIConstants.login_BTN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.login_BTN)) {
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

	public static void navigateTo_ExploreProducts (String para) throws Exception{
		try{	
			if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
				if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_ICN)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_ICN)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
			} else {
				if(isElementPresentVerification(HomeScreenUIConstants.explore_Products)){
					if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products)) {
						throw new Exception("Element Not able to click Explore Link");
					}
				}else {
					throw new Exception("Not able to verify element");
				}
				Thread.sleep(2000);
			}
			if(para.equalsIgnoreCase("Creditcards")){
				if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
					if(isElementPresentVerification(HomeScreenUIConstants.explore_Products_CreditCards_mobile)){
						if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products_CreditCards_mobile)) {
							throw new Exception("Element Not able to click Explore Link");
						}
					}else {
						throw new Exception("Not able to verify element");
					}
				} else {
					if(isElementPresentVerification(HomeScreenUIConstants.explore_Products_CreditCards)){
						if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products_CreditCards)) {
							throw new Exception("Element Not able to click Explore Link");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
				}
			}
			if(para.equalsIgnoreCase("Checking")){
				if(config.getString("breakPoint").equalsIgnoreCase("Mobile")){
					if(isElementPresentVerification(HomeScreenUIConstants.hamburger_menu_ICN)){
						if(!isElementPresentVerifyClick(HomeScreenUIConstants.hamburger_menu_ICN)) {
							throw new Exception("Element Not able to click Explore Link");
						}
					}else {
						throw new Exception("Not able to verify element");
					}
					if(isElementPresentVerification(HomeScreenUIConstants.explore_Products_Checking_mobile)){
						if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products_Checking_mobile)) {
							throw new Exception("Element Not able to click Explore Link");
						}
					}else {
						throw new Exception("Not able to verify element");
					}
				} else {
					if(isElementPresentVerification(HomeScreenUIConstants.explore_Products_Checking)){
						if(!isElementPresentVerifyClick(HomeScreenUIConstants.explore_Products_Checking)) {
							throw new Exception("Element Not able to click Explore Link");
						}
					}else {
						throw new Exception("Element Not Verified");
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}


}
