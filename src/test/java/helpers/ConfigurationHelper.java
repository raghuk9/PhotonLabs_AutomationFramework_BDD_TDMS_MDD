package helpers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import configuration.RunConfig;

public class ConfigurationHelper {

	private static JSONObject platformProperties = null;
	private static String platformStr = null;
	private static String driverUrl = null;

	public static void init() throws FileNotFoundException, IOException, ParseException {
		platformStr=getPlatform();
		platformProperties=getPlatformProperties();
	}

	public static String getPlatform() throws FileNotFoundException, IOException, ParseException {
		String platform = System.getProperty("platform");
		if(platform==null) {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("src/test/configuration/RunConfig.json"));
			JSONObject jsonObject = (JSONObject) obj;
			platformStr = (String) jsonObject.get("platform");
		}else {
			platformStr = platform;	
		}		
		return platformStr;
	}
	
	public static void setDriverUrl(String url) {
		driverUrl = url;
	}
	
	private static JSONObject getPlatformProperties() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/configuration/Config.json"));
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject platformProperties = (JSONObject) jsonObject.get(platformStr);
		return platformProperties;
	}

	public static String getFeatureFile() throws IOException, ParseException {
		init();
		return (String) platformProperties.get("featureFile");
	}

	public static String getLanguage() {
		String lang = System.getProperty("lang");
		if(lang==null) {
			lang = "en-us";
		}
		return lang;
	}
	
	public static String getBaseUri(){
		return (String) platformProperties.get("baseUri_"+getLanguage());
	}
	
	public static String getPlatformName(){
		return (String) platformProperties.get("platformName");
	}
	
	public static String getDeviceName(){
		return (String) platformProperties.get("deviceName");
	}
	
	public static String getPlatformVersion(){
		return (String) platformProperties.get("platformVersion");
	}
	
	public static String getDriverUrl(){
		return driverUrl;
	}
	
	public static String getAppPackage() {
		return (String) platformProperties.get("appPackage");
	}
	
	public static String getAppActivity(){
		return (String) platformProperties.get("appActivity");
	}
	
	public static String getBrowserName(){
		String browserName = System.getProperty("browserName");
		if(browserName==null) {
			browserName = (String) platformProperties.get("browserName"); 
		}
		return browserName; 
	}
	
	public static String getUdid() {
		return (String) platformProperties.get("udid");
	}
	
}