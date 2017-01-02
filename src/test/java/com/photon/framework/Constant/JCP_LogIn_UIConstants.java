package com.photon.framework.Constant;

public class JCP_LogIn_UIConstants {

	//Desktop
	public static final String userName_TXT =  "emailidLogin~ID";
	public static final String password_TXT =  "mypasswdLogin~ID";
	public static final String login_BTN =  "signIn~ID";
	public static final String my_Account_BTN = ".//*[@id='coldState']/ul/li[2]/a";
	public static final String Logout_BTN = ".//*[@id='logoutForm']/fieldset/p/a/span";
		
	//Mobile web
	public static final String mobile_userName_TXT = ".//*[@id='sign-in-email-id']";
	public static final String mobile_password_TXT = ".//*[@id='sign-in-password']";
	public static final String mobile_my_Account_BTN = ".//*[@id='jcp-layout']/header/hgroup/article[2]/section/ul/li[4]/div[1]/div[2]/span";
	public static final String mobile_SignIn_BTN = ".//*[@id='my-account-drop-down']/ul/li[1]/a";
	public static final String mobile_LogIn_BTN = ".//*[@id='user-sign-in']";
	public static final String mobile_my_Account_BTN1 = ".//*[@id='jcp-layout']/header/hgroup/article[2]/section/ul/li[4]";
	public static final String mobile_LogOut_BTN = ".//*[@id='my-account-drop-down']/ul/li[14]/a";
	
	//Native Application Android
	public static String NAA_noThanks_BTN = "com.jcp:id/notifications_decline~ID";
	public static String NAA_allow_BTN = "com.android.packageinstaller:id/permission_allow_button~ID";
	public static String NAA_signIN_BTN = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[5]/android.widget.LinearLayout[1]/android.widget.TextView[1]~XPATH";
	public static String NAA_signUp_BTN = "com.jcp:id/profile_create_account~ID";
	public static String NAA_firstName_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_lastName_TXT = " //android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_emailAddress_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_password_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_phone_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_zipcode_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_ac_Create_BTN = "com.jcp:id/create_account_button~ID";
	public static String NAA_sign_IN_BTN = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[2]~XPATH";
	public static String NAA_signIN_Email_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_signIN_password_TXT = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/TextInputLayout[1]/android.widget.FrameLayout[1]/android.widget.EditText[1]~XPATH";
	public static String NAA_sign_SignIN_BTN = "com.jcp:id/sign_in_button~ID";
	public static String NAA_setting_ICON = "com.jcp:id/profile_account_settings~ID";
	public static String NAA_sign_Out = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]~XPATH";
}
