package com.photon.framework.Constant;

import java.io.File;

public class UserConfig {


    public static final String projectLocation = System.getProperty("user.dir")+"/drivers"+"/chromedriver";

    /*
     * iOS 
     */
    public static final String deviceUDID =  "a1be71915746553b6146507619c8225cb640d98b";
    public static final String platform_Version =  "8.0";
    public static final String deviceName =  "iPhone 6 Plus";
    public static final String safarilauncherLocation =  "/Applications/Appium/Contents/Resources/node_modules/appium/build/SafariLauncher/SafariLauncher.app";
    public static final String appiumServerURL_iOS =  "http://127.0.0.1:4723/wd/hub";

    /*
     * Android
     */
    public static final String sdk_location =  "/Users/muralikrishnan_g/Library/Android/sdk";
    public static final String deviceId =  "ce10171ac135680d05";
    public static final String chromeDriver_location =  "http://localhost:9515";
    
    
    /*ChromeDriver */
    public static final String chromeDriver_Desktop_Location = System.getProperty("user.dir")+"/drivers"+"/chromedriver";
 
    
    public static String reportDir=System.getProperty("user.dir")+File.separator+"CSVFile"+File.separator;
	public static String reportFile = "Pageload_Report";
}
