package com.mediacorp.utils;

import java.net.InetAddress;
import java.util.Date;

import org.testng.Assert;

public class Web_Constants {



	public static String URL = "https://www.sonyliv.com/";
	public static String ENV = "Prod";


	static String BUNDLE_ID = "com.msmpl.sonyliviphone";
	public static String preprod_BUNDLE_ID = "com.msmpl.sonyliviphone";


	static String browser_Stack="false";
	public static final String  browserStackUserName= "ifocusmewatch_G0FtvM"; 
	public static final String  browserStackKey= "QrP2xRortKU2fJ7JZeAe"; 
	
	
	
	static String KLOV_FLAG="true";

	static String  webBuildVersion="1.50.59";
	static String  APPBuildVersion="5.5.553";
	public static String prepodURL ="https://preprod.mewatch.sg/";
	public static String stageURL ="https://stag9.mewatch.sg/";

	

//	public static String USERTYPE = "Premium";
	public static String USERTYPE = "nonPremium";
//	public static   String USERTYPE = "guest";

	/** WEB Configured Browsers and Platforms **/
//	public static String PROJECT = "WEB", OS = "Windows", PLATFORM_NAME = "Desktop", browser = "chrome";
//	public static String PROJECT= "WEB", OS = "Windows", PLATFORM_NAME = "Desktop", browser = "firefox";
//	public static String PROJECT= "WEB", OS = "Mac", PLATFORM_NAME = "Desktop", browser = "Chrome";
//	public static String PROJECT= "WEB", OS = "Mac", PLATFORM_NAME = "Desktop", browser = "Safari";
//	public static String PROJECT= "WEB", OS = "Mac", PLATFORM_NAME = "Desktop", browser = "safaritechnologypreview";
//	public static String PROJECT= "WEB", OS = "Mac", PLATFORM_NAME = "Desktop", browser = "firefox";

	/** PWA Configured Browsers and Platforms **/
//	public static String PROJECT= "MobileRW", OS = "Windows", PLATFORM_NAME = "Android", browser = "Chrome";
//	public static String PROJECT= "MobileRW", OS = "Mac", PLATFORM_NAME = "iOS", browser = "Safari";
 	
//    public static String PROJECT = "AndroidApp", OS = "Windows", PLATFORM_NAME = "Android", browser = "Android";
//      public static String PROJECT = "AndroidApp", OS = "Windows", PLATFORM_NAME = "AndroidTAB", browser = "Android";
// 	public static String PROJECT= "iOSApp", OS = "Mac", PLATFORM_NAME = "ios", browser = "Safari";
	public static String PROJECT = "AndroidTV", OS = "Windows", PLATFORM_NAME = "Android", browser = "Android";
// 	public static String PROJECT= "iOSApp", OS = "Mac", PLATFORM_NAME = "ios", browser = "Safari";
//	public static String PROJECT= "iOSApp", OS = "Mac", PLATFORM_NAME = "ipad", browser = "Safari";
	
	//	public static String PROJECT = "AndroidTV", OS = "Windows", PLATFORM_NAME = "Android", browser = "Android";

	public static final String APK_PATH = System.getProperty("user.dir") + "\\Apps\\Android\\mewatch-prod-5.3.402(50821)-release.apk";
	public static final String DownGrade_APK_PATH = "C://Users//"+System.getProperty("user.name")+"//Downloads//mewatch-prod-5.5.550(54586)-release.apk";
	public static final String Upgrade_APK_PATH = "C://Users//"+System.getProperty("user.name")+"//Downloads//mewatch-prod-5.5.561(55644)-release.apk";
	public static final String preprod_DownGrade_APK_PATH = "C://Users//"+System.getProperty("user.name")+"//Downloads//mewatch-preprod-5.5.565(56474)-release.apk";
	public static final String preprod_Upgrade_APK_PATH = "C://Users//"+System.getProperty("user.name")+"//Downloads//mewatch-preprod-5.5.570(56089)-release.apk";
	
	public static final String IPA_PATH = "//Apps//iOS//toggle.ipa"; 
	public static final String OLD_BUILD = "Voot_3.2.5_346_RC_GE";
	public static final String LATEST_BUILD = "Voot_com.tv.v18.viola";
	public static final String APK_PATH_MAC = System.getProperty("user.dir") + "//app//Android//RC//Voot_com.tv.v18.viola_3.4.6.apk";
	public static final String AUTOMATION_NAME = "XCUITest";
	public static final String XCODE_ORG_ID = "7GZEJ5HA3G";	
	public static final String APP_PACKAGE= "com.sonyliv";
	public static final String APP_ACTIVITY= "com.sonyliv.ui.splash.SplashActivity";
	public static final String Preprod_APP_PACKAGE= "com.sonyliv";
	public static final String Preprod_APP_ACTIVITY= "com.sonyliv.ui.splash.SplashActivity";
	public static final String TV_APP_ACTIVITY= "com.sonyliv.ui.splash.SplashActivity";

	
//	if you don't want to reinstall the app everytime. Installs Only Once
	public static  boolean NO_RESET_FLAG = true; public static  boolean FULL_RESET_FLAG = false;
//	If you want to Re-Install the App Everytime.
//	public static  boolean NO_RESET_FLAG = false;	public static  boolean FULL_RESET_FLAG = true;
	public static final int INNVOCATION_COUNT = 1;
	public static boolean RESETAPP_FLAG=false;
//	public static final String APPIUM_DRIVER_IP_ADDRESS = "127.0.0.1";
//	public static final int APPIUM_PORT = 4723;
	public static final int SYSTEM_PORT = 8298;
	
	/** ScreenShot and Report Paths **/
	public final static String REPORT_PATH = "C:\\meWatch_Automation-Reports\\";
	public final static String SCREENSHOT_PATH = "C:\\AutomationScreenshots\\meWatchScreenshots\\";
	public final static String REPORT_PATH_MAC = "/Users/"+System.getProperty("user.name")+"/Desktop/meWatchReports/";
	public final static String SCREENSHOT_PATH_MAC = "/Users/"+System.getProperty("user.name")+"/Desktop/meWatchScreenshots/";
	public final static String CucumberReportPath = "Reports/cucumberHtmlReport";
	public static final String HUB_URL = "http://localhost:4723/wd/hub";
	public static final String APPIUM_IPADDRESS = "127.0.0.1";
	public static final int PORT_NUMBER = 4724;
	public static final int APPIUM_PORT = 4723;
	public static final String Mac_UserName = "ifocus";
	
	public static final String REPORT_EMAIL_SUBJECT = "[Automation] meWatch Reports";
	
	public static String ExtentReportPath="";

//	public static final boolean SCREENSHOT_TO_FOLDER = true;
	public static final boolean SCREENSHOT_TO_FOLDER = false;
	
	public static final boolean CREATE_JIRA_TICKET = false;

	/*
	 * Note: You need to Download the latest version of ChromeDriver.exe and add to
	 * C:\Users\IFOCUS\.appium\node_modules\appium-uiautomator2-driver\node_modules\appium-chromedriver\chromedriver\win
	 */

	/*************************************************************************************
	 * 7.0 Select the proper Node.exe path according to the system that you are
	 * working on
	 **************************************************************************************/
	// Windows
	public static String NODEJS_PATH = "C:\\Program Files\\nodejs\\node.exe";
	// MAC
	// public static String NODEJS_PATH = "/usr/local/bin/node";
	/*************************************************************************************
	 * 8.0 Select the proper Appium.JS paths based on the system that you are
	 * working on.
	 **************************************************************************************/

//	APPIUM_JS_PATH 
	public static String APPIUM_JS_PATH = "C:\\Users\\" + System.getProperty("user.name")
			+ "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

	
//	public static String APPIUM_JS_PATH = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";
	/*************************************************************************************
	 * 9.0 Select the proper UDID based on the Device that you are working on.
	 **************************************************************************************/
//	Android  Real devices
//	public static String UDID = "421042efc4159421";public static String DEVICE_NAME = "Samsung";public static String DEVICE_VERSION = "8";	
//	public static String UDID = "b9319696";public static String DEVICE_NAME = "Vivo";public static String DEVICE_VERSION = "8";
//	public static String UDID = "bd12d3c7";public static String DEVICE_NAME = "Vivo";public static String DEVICE_VERSION = "8";	
//	public static String UDID = "RZ8M303Z0TE";public static String DEVICE_NAME = "device";public static String DEVICE_VERSION = "11";
//	public static String UDID = "32012439840d5631";public static String DEVICE_NAME = "Samsung";public static String DEVICE_VERSION = "10";
//	public static String UDID = "17241JEC214467";public static String DEVICE_NAME = "Huawei";public static String DEVICE_VERSION = "10";
	public static String UDID = "192.168.0.156:5555";public static String DEVICE_NAME = "Samsung";public static String DEVICE_VERSION = "11";
// 	public static String UDID = "192.168.0.149:5555";public static String DEVICE_NAME = "Samsung";public static String DEVICE_VERSION = "11";
//	public static String UDID = "33001ef1d426c37f";public static String DEVICE_NAME = "Vivo";public static String DEVICE_VERSION = "8.1.0";

//	iOS Real devices
//	public static String UDID = "d2cac7e512d8c16c1b4ad94ad6120b2ca1a6deff"; public static String DEVICE_NAME = "iPhone 6"; public static String DEVICE_VERSION = "12.4.7"; //Sudha
//	public static String UDID = "7a94414fd90ea61ee56520c77ce8ed4ef18385ef"; public static String DEVICE_NAME = "iPhone 8"; public static String DEVICE_VERSION = "14.0"; //Office
//	public static String UDID = "00008030-001415193AD3402E"; public static String DEVICE_NAME = "iPhone SE"; public static String DEVICE_VERSION = "16.7.2"; //Office

	
	//	Android  BrowserStack devices
	
	//	public static String UDID = "17241JEC214467";public static String DEVICE_NAME = "Samsung Galaxy S23";public static String DEVICE_VERSION = "13.0";

	//	IOS  BrowserStack devices
//	public static String UDID = "d2cac7e512d8c16c1b4ad94ad6120b2ca1a6deff"; public static String DEVICE_NAME = "iPhone 13 Pro"; public static String DEVICE_VERSION = "15";
	
	public static InetAddress localhost;
	public static String ipAdress = "";
	public static String charleslogsName = "";
	public static String charlesName = "";
	public static String filePathxml = "";
	public static String filePathlogs = "";
	public static String charlesNameDFP = "";
	public static String filePathxmlDFP = "";
	public static String filePathlogsDFP = "";
	public static String charleslogsNameDFP = "";

	public void Web_ConstantsConfiguration(String OS,String browser,String project,String UDID,String Environment , String platformName ,String browser_Stack,String KLOV_FLAG) {
		
		if (project!=null) {
			this.PROJECT=project;
		}
		if (OS!=null) {
			this.OS=OS;
		}
		if (UDID!=null) {
			this.UDID=UDID;
		}
		if (Environment!=null) {
			this.ENV=Environment;
		}
		 
		if (platformName!=null) {
			this.PLATFORM_NAME=platformName;
		}
		if (browser_Stack!=null) {
			this.browser_Stack=browser_Stack;
		}
		if (KLOV_FLAG!=null) {
			this.KLOV_FLAG=KLOV_FLAG;
		}

		if (OS.equalsIgnoreCase("windows")) {
			if (PROJECT.equalsIgnoreCase("web")||PROJECT.equalsIgnoreCase("mobilerw")) {
				Web_Constants.OS = "Windows";
				switch (browser.toLowerCase()) {
				case "chrome":
					Web_Constants.browser = "Chrome";
					break;
				case "firefox":
					Web_Constants.browser = "Firefox";
					break;
				case "edge":
					Web_Constants.browser = "edge";
					break;
				case "ie":
					Web_Constants.browser = "ie";
					break;
				default:
					Assert.assertTrue(false, "Selected Browser not Applicable:-" + browser);
					break;
				}
			}

		} else if (OS.equalsIgnoreCase("mac")) {
			Web_Constants.OS = "Mac";
			switch (browser.toLowerCase()) {
			case "chrome":
				Web_Constants.browser = "Chrome";
				break;
			case "safari":
				Web_Constants.browser = "Safari";
				break;
			case "firefox":
				Web_Constants.browser = "Firefox";
				break;
			default:
				Assert.assertTrue(false, "Selected Browser not Applicable:-" + browser);
				break;
			}

		} else {
			Assert.assertTrue(false, "Selected OS not Applicable:-" + OS);
		}
		
		switch (ENV.toLowerCase()) {
		case "prod":{
			URL="https://www.sonyliv.com/";
			break;
		}
		
		case "preprod":{
			URL="https://www.sonyliv.com/";
			break;
		}
		
		case "stage":{
			URL="https://www.sonyliv.com/";
			break;
		}
		
		case "dev":{
			URL="https://www.sonyliv.com/";
			break;
		}

		default:
			break;
		}
		
		System.out.println("[INFO] Project - "+project);
		
		System.out.println("[INFO] OS - "+OS);
		
		System.out.println("[INFO] Environment - "+Environment);

		System.out.println("[INFO] Browser - "+browser);
		
		System.out.println("[INFO] UDID - "+UDID);
		
		System.out.println("[INFO] URL - "+URL);
		
		System.out.println("[INFO] browser_Stack - "+browser_Stack);
		
		System.out.println("[INFO] KLOV_FLAG - "+KLOV_FLAG);
		
		if (Web_Constants.OS.equalsIgnoreCase("mac")) {
			NODEJS_PATH = "/usr/local/bin/node";
			APPIUM_JS_PATH = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";
		}

		
	}
	
	// To get current time
		public static String getTimeStamp() {
			Date d = new Date();
			return d.toString().replace(":", "_").replace(" ", "_");
		}
}
