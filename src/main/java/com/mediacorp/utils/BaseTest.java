package com.mediacorp.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.ios.options.XCUITestOptions;


import net.rcarz.jiraclient.JiraException;

public class BaseTest extends ReportUtils {

	public RemoteWebDriver driver;

	public void launchApplication() {
		
		 
		 String URL = "https://" + Web_Constants.browserStackUserName + ":" + Web_Constants.browserStackKey + "@hub-cloud.browserstack.com/wd/hub";
		Utilities utilities = new Utilities();

		EventUtils eventUtils = new EventUtils();

		if (!Web_Constants.PROJECT.equalsIgnoreCase("web")&& (Web_Constants.browser_Stack.equalsIgnoreCase("true"))) {
			utilities.startAppiumServer();
		}

		switch (Web_Constants.PROJECT.toLowerCase()) {
		case "web":
		case "mobilerw":{
			MutableCapabilities capabilities = new MutableCapabilities();
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("browserVersion", "latest");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "4.15.0");
			browserstackOptions.put("projectName", "meWatch_RW");
			browserstackOptions.put("sessionName", "meWatch"+utilities.getTimeStamp());
			switch (Web_Constants.OS.toLowerCase()) {
			case "mac":{
				browserstackOptions.put("os", "OS X");
				browserstackOptions.put("osVersion", "Sonoma");
				switch (Web_Constants.browser.toLowerCase()) {
				case "chrome": {
					if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
						try {
							
							browserstackOptions.put("buildName", "Web_Application_Chrome_Mac_Sonoma");
							capabilities.setCapability("bstack:options", browserstackOptions);
							capabilities.setCapability("browserName", "Chrome");
							driver = new RemoteWebDriver(new URL(URL), capabilities);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}else {
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--window-size=1920,1080");
						options.addArguments("--disable-notifications");
						driver = new ChromeDriver(options);
					}
					
					
					
					
					break;
				}
				case "safari": {

					if (Web_Constants.PROJECT.equalsIgnoreCase("web")) {
						try {
							Thread.sleep(2000);
							Runtime.getRuntime().exec("killall safaridriver");
						} catch (IOException | InterruptedException e1) {

						}

						try {
							
							if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
								try {
									browserstackOptions.put("buildName", "Web_Application_Safari_Mac_Sonoma");
									capabilities.setCapability("bstack:options", browserstackOptions);
									capabilities.setCapability("browserName", "Safari");
									driver = new RemoteWebDriver(new URL(URL), capabilities);
								
								} catch (MalformedURLException e) {
									e.printStackTrace();
								}
							}else {
								SafariOptions options = new SafariOptions();
								driver = new SafariDriver(options);
							}
							
							
					        

						} catch (Exception a) {
							System.out.println("Error ::: " + a);
						}
					} else {
						if(Web_Constants.browser_Stack.equalsIgnoreCase("true")){
							HashMap<String, Object> stackOptions = new HashMap<String, Object>();
							stackOptions.put("userName", Web_Constants.browserStackUserName);
							stackOptions.put("accessKey", Web_Constants.browserStackKey);
							stackOptions.put("osVersion", "15");
							stackOptions.put("deviceName", "iPhone 13 Pro");
							stackOptions.put("projectName", "meWatchMRWSafari");
							stackOptions.put("buildName", "meWatchIOSSafari");
							stackOptions.put("sessionName", "meWatchIOSSafari");
							stackOptions.put("appiumVersion", "2.0.1");
							stackOptions.put("appProfiling", "true");
							stackOptions.put("local", "false");
							stackOptions.put("debug", "true");
							XCUITestOptions options = new XCUITestOptions();
							options.setCapability("browserName", "Safari");
							options.setCapability("bstack:options", stackOptions);	
							try {
								URL strAppiumURL=new URL("http://hub.browserstack.com/wd/hub");
								 driver = new IOSDriver(strAppiumURL, options);
							} catch (Exception e) {
								System.out.println("Desired Capabilities not Set Properly" + e.getMessage());
							}
							
					}else {
						 utilities.startAppiumServer();	
						UiAutomator2Options options = new UiAutomator2Options();
						options.setPlatformName(Web_Constants.PLATFORM_NAME);
						options.setPlatformVersion(Web_Constants.DEVICE_VERSION);
						options.setDeviceName(Web_Constants.DEVICE_NAME);
						options.setUdid(Web_Constants.UDID);
						options.setAutomationName(Web_Constants.AUTOMATION_NAME);
						options.setNewCommandTimeout(Duration.ofSeconds(60));
						options.setCapability("xcodeSigningId", "iPhone Developer");
						options.setCapability("xcodeOrgId", Web_Constants.XCODE_ORG_ID);
						options.setCapability("useNewWDA", false);
						options.setCapability("usePrebuiltWDA", true);
						try {
							driver = new IOSDriver(new URL(Web_Constants.HUB_URL), options);
						} catch (Exception e) {
							System.out.println("Desired Capabilities not Set Properly" + e.getMessage());
						}
					}
					}
					break;
				}
				case "firefox":
				{   
					FirefoxProfile profile = new FirefoxProfile();
					 profile.setPreference("media.eme.enabled", true);
					 profile.setPreference("media.gmp-manager.updateEnabled", true);
					
					
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("-private");
					options.setProfile(profile);
					
					 if(Web_Constants.browser_Stack.equalsIgnoreCase("true")){
							try {
								
								browserstackOptions.put("buildName", "Web_Application_Firefox_Mac_Sonoma");
								FirefoxOptions fOptions = new FirefoxOptions();
								fOptions.setCapability("bstack:options", browserstackOptions);	
								fOptions.setCapability("browserName", "Firefox");
								driver = new RemoteWebDriver(new URL(URL), fOptions);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
						}else {
							driver = new FirefoxDriver(options);
						}

					break;
				}
				default:
					Assert.assertTrue(false, "Given OS : '" + Web_Constants.OS + "'or Given Browser :'"
							+ Web_Constants.browser + "' is invalid");
					break;
				}
				break;
			}
			case "windows": {
//				browserstackOptions.put("os", "Windows");
//				browserstackOptions.put("osVersion", "Windows 10");
				switch (Web_Constants.browser.toLowerCase()) {

				case "firefox":
				{

					FirefoxProfile profile = new FirefoxProfile();
					 profile.setPreference("media.eme.enabled", true);
					 profile.setPreference("media.gmp-manager.updateEnabled", true);
					
					
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("-private");
					options.setProfile(profile);
					
					 if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
							try {
								
								browserstackOptions.put("buildName", "Web_Application_Firefox_Windows");
								FirefoxOptions fOptions = new FirefoxOptions();
								fOptions.setCapability("bstack:options", browserstackOptions);	
								fOptions.setCapability("browserName", "Firefox");
								driver = new RemoteWebDriver(new URL(URL), fOptions);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
						}else {
							driver = new FirefoxDriver(options);
						}
				    


					break;
				}
				case "chrome": {
					if (Web_Constants.PROJECT.equalsIgnoreCase("web")) {

						ChromeOptions options = new ChromeOptions();
						options.addArguments("--disable-notifications");
						options.addArguments("disable-infobars");

						if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
							try {
								
								browserstackOptions.put("buildName", "Web_Application_Chrome_Windows");
								capabilities.setCapability("bstack:options", browserstackOptions);
								capabilities.setCapability("browserName", "Chrome");
								driver = new RemoteWebDriver(new URL(URL), capabilities);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
						}else {
							driver = new ChromeDriver(options);
						}
						
						
						break;
					} else {
						if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
							HashMap<String, Object> browSerstackOptions = new HashMap<String, Object>();
							browSerstackOptions.put("userName", Web_Constants.browserStackUserName);
							browSerstackOptions.put("accessKey", Web_Constants.browserStackKey);
							browSerstackOptions.put("osVersion", "13");
							browSerstackOptions.put("deviceName", "Samsung Galaxy S23");
							browSerstackOptions.put("projectName", "meWatchMRWChrome");
							browSerstackOptions.put("buildName", "meWatchAndroidChrome");
							browSerstackOptions.put("sessionName", "meWatchAndroidChrome");
							browSerstackOptions.put("appiumVersion", "2.0.1");
							browSerstackOptions.put("appProfiling", "true");
							browSerstackOptions.put("local", "false");
							browSerstackOptions.put("debug", "true");
							UiAutomator2Options options = new UiAutomator2Options();
							options.setCapability("browserName", "chrome");
							options.setCapability("bstack:options", browSerstackOptions);
							
							
							try {
								URL strAppiumURL = new URL("http://hub.browserstack.com/wd/hub");
								driver = new AndroidDriver(strAppiumURL, options);
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							
						}else {
						utilities.startAppiumServer();
						ChromeOptions chromeoptions = new ChromeOptions();
						chromeoptions.addArguments("--disable-notifications");
						chromeoptions.addArguments("--disable-infobars");
						chromeoptions.addArguments("androidPackage", "com.android.chrome");
						chromeoptions.addArguments("--disable-popup-blocking");
						chromeoptions.setExperimentalOption("w3c", false);

						UiAutomator2Options options = new UiAutomator2Options();
						
						options.setPlatformName(Web_Constants.PLATFORM_NAME);
						options.setPlatformVersion(Web_Constants.DEVICE_VERSION);
						options.setDeviceName(Web_Constants.DEVICE_NAME);
						options.setUdid(Web_Constants.UDID);
						options.setCapability("Browser_Name", Web_Constants.browser);
						options.setCapability("SYSTEM_PORT", Web_Constants.SYSTEM_PORT);
						options.setAutoGrantPermissions(true);
						options.setCapability("autoAcceptAlerts", true);
						options.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
						options.setCapability("newCommandTimeout", 90 * 40);
						System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

						try {
							driver = new AndroidDriver(new URL(Web_Constants.HUB_URL), options);
						} catch (Exception e) {
							System.out.println("Desired Capabilities not Set Properly" + e.getMessage());
						}

						
					}
						break;
					}
					
				}

				case "edge":{
					EdgeOptions options = new EdgeOptions();
					
					if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
						try {
							
							browserstackOptions.put("buildName", "Web_Application_Edge_Windows");
							capabilities.setCapability("bstack:options", browserstackOptions);
							capabilities.setCapability("browserName", "Edge");
							driver = new RemoteWebDriver(new URL(URL), capabilities);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}else {
						driver = new EdgeDriver(options);
					}
					
					break;
				}
				default:
					Assert.assertTrue(false, "Given OS : '" + Web_Constants.OS + "'or Given Browser :'"
							+ Web_Constants.browser + "' is invalid");
					break;
				}
				break;
			}

			}

			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {

				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
//	            
			}

			try {
				if (Web_Constants.browser.equalsIgnoreCase("Safari")) {
					driver.navigate().to(Web_Constants.URL);
				} else {
					if (Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
						try {
	
							driver.get(Web_Constants.URL);
						} catch (Exception e) {
							driver.navigate().to(Web_Constants.URL);

						}
					} else {
						driver.get(Web_Constants.URL);

					}

				}
			} catch (Exception e) {

				driver.navigate().to(Web_Constants.URL);

			}

			

			
		
			break;
		}
		case "androidapp": {
			if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
				HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
				browserstackOptions.put("userName", Web_Constants.browserStackUserName);
				browserstackOptions.put("accessKey", Web_Constants.browserStackKey);
				browserstackOptions.put("osVersion", "13.0");
				browserstackOptions.put("deviceName", "Samsung Galaxy S23");
				browserstackOptions.put("projectName", "meWatchAndroidApp");
				browserstackOptions.put("buildName", "meWatchAndroidApp");
				browserstackOptions.put("sessionName","meWatchAndroidApp");
				browserstackOptions.put("appiumVersion", "2.0.1");
				browserstackOptions.put("appProfiling", "true");
				browserstackOptions.put("local", "false");
				browserstackOptions.put("debug", "true");
				UiAutomator2Options options = new UiAutomator2Options();
				options.setCapability("app", "bs://5baa19e83d18a03d52cedb0875c53d5d6cb0b481");
				options.setCapability("bstack:options", browserstackOptions);
				
				
				try {
					URL strAppiumURL=new URL("http://hub.browserstack.com/wd/hub");
					driver = new AndroidDriver(strAppiumURL, options);
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
				
				
			}else {
			utilities.startAppiumServer();
			
			UiAutomator2Options options = new UiAutomator2Options();
			options.setCapability("automationName", "UiAutomator2");
			options.setPlatformName(Web_Constants.PLATFORM_NAME);
			options.setPlatformVersion(Web_Constants.DEVICE_VERSION);
			options.setDeviceName(Web_Constants.DEVICE_NAME);
			options.setUdid(Web_Constants.UDID);
			options.setCapability("SYSTEM_PORT", Web_Constants.SYSTEM_PORT);
			options.setAutoGrantPermissions(true);
			options.setUnlockType("pin");
			options.setUnlockKey("1111");

			if (Web_Constants.ENV.equalsIgnoreCase("preprod")) {
				options.setAppPackage(Web_Constants.Preprod_APP_PACKAGE);
				options.setAppActivity(Web_Constants.Preprod_APP_ACTIVITY);
			} else {
				options.setAppPackage(Web_Constants.APP_PACKAGE);
				options.setAppActivity(Web_Constants.APP_ACTIVITY);
			}
			options.setCapability("autoAcceptAlerts", true);
			options.setCapability("enforceXPath1", true);
			if (GlobalVariables.reset == true) {
				options.setCapability("noReset", false);
				options.setCapability("fastReset", true);
			} else {
				options.setCapability("noReset", Web_Constants.NO_RESET_FLAG);
				options.setCapability("fastReset", Web_Constants.FULL_RESET_FLAG);
			}
			options.setDisableWindowAnimation(true);
			options.setCapability("–session-override", true);
			
			try {
				
////				 -s " + Web_Constants.UDID +
////				Runtime.getRuntime().exec("adb shell am start -n com.sonyliv/com.sonyliv.ui.splash.SplashActivity");
//					Runtime.getRuntime().exec("adb -s "+Web_Constants.UDID+" shell am start -n com.sonyliv/com.sonyliv.ui.splash.SplashActivity");
//					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			try {
				driver = new AndroidDriver(new URL(Web_Constants.HUB_URL), options);
				
			} catch (Exception e) {
				if (driver != null) {
					System.out.println("Driver is not Null, So Quiting Driver");
					driver.quit();
				}
				System.err.println("Re-Starting Appium Server, as Appium server stopped abruptly.");
				utilities.startAppiumServer();
				try {
					driver = new AppiumDriver(new URL(Web_Constants.HUB_URL), options);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
 
			break;
			}
		}
		case "androidtv": {
			if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{
				HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
				browserstackOptions.put("userName", Web_Constants.browserStackUserName);
				browserstackOptions.put("accessKey", Web_Constants.browserStackKey);
				browserstackOptions.put("osVersion", "13.0");
				browserstackOptions.put("deviceName", "Samsung Galaxy S23");
				browserstackOptions.put("projectName", "meWatchAndroidApp");
				browserstackOptions.put("buildName", "meWatchAndroidApp");
				browserstackOptions.put("sessionName","meWatchAndroidApp");
				browserstackOptions.put("appiumVersion", "2.0.1");
				browserstackOptions.put("appProfiling", "true");
				browserstackOptions.put("local", "false");
				browserstackOptions.put("debug", "true");
				UiAutomator2Options options = new UiAutomator2Options();
				options.setCapability("app", "bs://5baa19e83d18a03d52cedb0875c53d5d6cb0b481");
				options.setCapability("bstack:options", browserstackOptions);
				
				
				
				try {
					URL strAppiumURL=new URL("http://hub.browserstack.com/wd/hub");
					driver = new AndroidDriver(strAppiumURL, options);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
				
			}else {
			utilities.startAppiumServer();
			
			UiAutomator2Options options = new UiAutomator2Options();
			options.setCapability("automationName", "UiAutomator2");
			options.setPlatformName(Web_Constants.PLATFORM_NAME);
			options.setPlatformVersion(Web_Constants.DEVICE_VERSION);
			options.setDeviceName(Web_Constants.DEVICE_NAME);
			options.setUdid(Web_Constants.UDID);
			options.setCapability("SYSTEM_PORT", Web_Constants.SYSTEM_PORT);
			options.setAutoGrantPermissions(true);
			if (Web_Constants.ENV.equalsIgnoreCase("preprod")) {
				options.setAppPackage(Web_Constants.Preprod_APP_PACKAGE);
				options.setAppActivity(Web_Constants.Preprod_APP_ACTIVITY);
			} else {
				options.setAppPackage(Web_Constants.APP_PACKAGE);
				options.setAppActivity(Web_Constants.TV_APP_ACTIVITY);
			}
			options.setCapability("autoAcceptAlerts", true);
			options.setCapability("enforceXPath1", true);
			if (GlobalVariables.reset == true) {
				options.setCapability("noReset", false);
				options.setCapability("fastReset", true);
			} else {
				options.setCapability("noReset", Web_Constants.NO_RESET_FLAG);
				options.setCapability("fastReset", Web_Constants.FULL_RESET_FLAG);
			}
			options.setDisableWindowAnimation(true);
			options.setCapability("–session-override", true);

			try {
				Runtime.getRuntime().exec("adb -s "+Web_Constants.UDID+" shell am start -n com.sonyliv/com.sonyliv.ui.splash.SplashActivity");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				driver = new AndroidDriver(new URL(Web_Constants.HUB_URL), options);
				
			} catch (Exception e) {
				if (driver != null) {
					System.out.println("Driver is not Null, So Quiting Driver");
					driver.quit();
				}
				System.err.println("Re-Starting Appium Server, as Appium server stopped abruptly.");
				utilities.startAppiumServer();
				try {
					driver = new AppiumDriver(new URL(Web_Constants.HUB_URL), options);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
 
			break;
			}
		}
		case "iosapp": {

			
			if(Web_Constants.browser_Stack.equalsIgnoreCase("true"))	{

				HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
				browserstackOptions.put("userName", Web_Constants.browserStackUserName);
				browserstackOptions.put("accessKey", Web_Constants.browserStackKey);
				browserstackOptions.put("osVersion", "15");
				browserstackOptions.put("deviceName", "iPhone 13 Pro");
				browserstackOptions.put("projectName", "meWatchIOS");
				browserstackOptions.put("buildName", "meWatchIOSApp");
				browserstackOptions.put("sessionName","meWatchIOSApp");
				browserstackOptions.put("appiumVersion", "2.0.1");
				browserstackOptions.put("appProfiling", "true");
				browserstackOptions.put("local", "false");
				browserstackOptions.put("debug", "true");
				XCUITestOptions options = new XCUITestOptions();
				options.setCapability("app", "bs://5baa19e83d18a03d52cedb0875c53d5d6cb0b481");
				options.setCapability("bstack:options", browserstackOptions);
				
			
				try {
					URL strAppiumURL=new URL("http://hub.browserstack.com/wd/hub");
					driver = new IOSDriver(strAppiumURL, options);
					for (int j = 0; j < 10; j++) {
						if (eventUtils.waitUntilElementIsPresent(driver, By.id("Allow"), 1)) {
							eventUtils.clickOnElement(driver, By.id("Allow"), "Allow Button", 1);

						} else if (eventUtils.waitUntilElementIsPresent(driver, By.id("OK"), 1)) {
							eventUtils.clickOnElement(driver, By.id("OK"), "OK Button", 1);

						} else {
							break;
						}
					}

					try {
						if (eventUtils.waitUntilElementIsPresent(driver,
								By.xpath("//XCUIElementTypeOther//XCUIElementTypeButton[@name='Close']"), 1)) {
							eventUtils.clickOnElement(driver,
									By.xpath("//XCUIElementTypeOther//XCUIElementTypeButton[@name='Close']"),
									"Close Button", 10, test);
						}
					} catch (Exception e) {

					}

					if (Web_Constants.ENV.equalsIgnoreCase("Prod(Test Flight)")) {
						eventUtils.clickOnElement(driver, By.name("Next"), "Next Button", 30, test);
						if (eventUtils.waitUntilElementIsPresent(driver, By.id("Allow"), 2)) {
							eventUtils.clickOnElement(driver, By.id("Allow"), "Allow Button", 1);

						} else if (eventUtils.waitUntilElementIsPresent(driver, By.id("OK"), 1)) {
							eventUtils.clickOnElement(driver, By.id("OK"), "OK Button", 1);

						}
						eventUtils.clickOnElement(driver, By.name("Start Testing"), "Start Testing Button", 20, test);
						if (eventUtils.waitUntilElementIsPresent(driver, By.id("Allow"), 1)) {
							eventUtils.clickOnElement(driver, By.id("Allow"), "Allow Button", 1);

						} else if (eventUtils.waitUntilElementIsPresent(driver, By.id("OK"), 1)) {
							eventUtils.clickOnElement(driver, By.id("OK"), "OK Button", 1);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
				
			}else {
				
				utilities.startAppiumServer();
				
			XCUITestOptions options = new XCUITestOptions();

			options.setDeviceName(Web_Constants.DEVICE_NAME);
			options.setPlatformName("ios");
			options.setPlatformVersion(Web_Constants.DEVICE_VERSION);
			options.setAutomationName(Web_Constants.AUTOMATION_NAME);
			options.setUdid(Web_Constants.UDID);
			options.setCapability("xcodeSigningId", "iPhone Developer");
			options.setCapability("xcodeOrgId", Web_Constants.XCODE_ORG_ID);
			options.usePrebuiltWda();
			if (Web_Constants.ENV.equalsIgnoreCase("preprod")) {
				options.setBundleId(Web_Constants.preprod_BUNDLE_ID);
			} else {
				options.setBundleId(Web_Constants.BUNDLE_ID);
			}
//			options.setAppPushTimeout(Duration.ofSeconds(60));



			try {
				driver = new IOSDriver(new URL(Web_Constants.HUB_URL), options);
				if (GlobalVariables.reset == true) {
//				((AppiumDriver) driver).removeApp(Web_Constants.BUNDLE_ID);
					utilities.startAppiumServer();
					utilities.installingApplication(Web_Constants.ENV, test);

					driver = new AppiumDriver(new URL(Web_Constants.HUB_URL), options);

					for (int j = 0; j < 10; j++) {
						if (eventUtils.waitUntilElementIsPresent(driver, By.id("Allow"), 1)) {
							eventUtils.clickOnElement(driver, By.id("Allow"), "Allow Button", 1);

						} else if (eventUtils.waitUntilElementIsPresent(driver, By.id("OK"), 1)) {
							eventUtils.clickOnElement(driver, By.id("OK"), "OK Button", 1);

						} else {
							break;
						}
					}

					try {
						if (eventUtils.waitUntilElementIsPresent(driver,
								By.xpath("//XCUIElementTypeOther//XCUIElementTypeButton[@name='Close']"), 1)) {
							eventUtils.clickOnElement(driver,
									By.xpath("//XCUIElementTypeOther//XCUIElementTypeButton[@name='Close']"),
									"Close Button", 10, test);
						}
					} catch (Exception e) {

					}

					if (Web_Constants.ENV.equalsIgnoreCase("Prod(Test Flight)")) {
						eventUtils.clickOnElement(driver, By.name("Next"), "Next Button", 30, test);
						if (eventUtils.waitUntilElementIsPresent(driver, By.id("Allow"), 2)) {
							eventUtils.clickOnElement(driver, By.id("Allow"), "Allow Button", 1);

						} else if (eventUtils.waitUntilElementIsPresent(driver, By.id("OK"), 1)) {
							eventUtils.clickOnElement(driver, By.id("OK"), "OK Button", 1);

						}
						eventUtils.clickOnElement(driver, By.name("Start Testing"), "Start Testing Button", 20, test);
						if (eventUtils.waitUntilElementIsPresent(driver, By.id("Allow"), 1)) {
							eventUtils.clickOnElement(driver, By.id("Allow"), "Allow Button", 1);

						} else if (eventUtils.waitUntilElementIsPresent(driver, By.id("OK"), 1)) {
							eventUtils.clickOnElement(driver, By.id("OK"), "OK Button", 1);
						}
					}

				}
			} catch (Exception e) {

				utilities.startAppiumServer();
				try {

					if (GlobalVariables.reset == true) {

						utilities.installingApplication(Web_Constants.ENV, test);

						driver = new IOSDriver(new URL(Web_Constants.HUB_URL), options);

						

						
					} else {
						driver = new IOSDriver(new URL(Web_Constants.HUB_URL), options);
					}
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();

			}

			
		}
			break;
		}
		}

	}

	public void logStatusMP(String status, String Message) {

		switch (status.toLowerCase()) {
		case "info":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.info(MarkupHelper.createLabel(Message, ExtentColor.WHITE));
			break;
		case "warning":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));
			break;
		case "error":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));
			
			break;
		case "pass":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.pass(MarkupHelper.createLabel(Message, ExtentColor.GREEN));
			break;
		case "fail":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.fail(MarkupHelper.createLabel(Message, ExtentColor.RED));
			break;
		case "skip":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.skip(MarkupHelper.createLabel(Message, ExtentColor.PINK));
			break;
		default:
			test.warning(Message);
			break;
		}

		report.flush();

	}


	public void logStatus(String status, String Message) {

		switch (status.toLowerCase()) {
		case "info":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.info(MarkupHelper.createLabel(Message, ExtentColor.WHITE));
			
			break;
		case "warning":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));
			break;
		case "error":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));
			logScreenShot(Status.INFO, "");
			break;
		case "pass":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.pass(MarkupHelper.createLabel(Message, ExtentColor.GREEN));
			
			break;
		case "fail":
			System.err.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.fail(MarkupHelper.createLabel(Message, ExtentColor.RED));
			if (Web_Constants.CREATE_JIRA_TICKET) {
				JiraUtils jiraUtils = new JiraUtils("https://ifocusmediacorp.atlassian.net/", "baluv2323@gmail.com",
						"jpswOpuFtrtjdgdUyaOdF08C", "MEW");
				try {
					jiraUtils.CreateJiraTicket("Bug", Message, "", "Bala Vijaya");
				} catch (JiraException e) {
					e.printStackTrace();
					test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));

				}
			}
			logScreenShot(Status.INFO, "");
			break;
		case "skip":
			System.out.println("[" + status.toUpperCase() + "]" + " : " + Message);
			test.skip(MarkupHelper.createLabel(Message, ExtentColor.PINK));
			logScreenShot(Status.INFO, Message);
			break;
		default:
			test.warning(Message);
			break;
		}

		report.flush();

	}//

	// To Kill the crome browser
	public void killBrowser() throws IOException {
		if (driver != null) {
			if (Web_Constants.OS.equalsIgnoreCase("windows")) {
				try {
					driver.quit();
					logStatus("INFO", "Quit Browser Successfully");
				} catch (Exception e) {
				}
			}
			if (Web_Constants.OS.equals("Mac")) {
				try {
					clearCookies();
					driver.manage().deleteAllCookies();
					driver.quit();
					logStatus("INFO", "Quit Browser Successfully");
				} catch (Exception e) {
					driver.quit();
					logStatus("INFO", "Quit Browser Successfully");
				}
			}
			switch (Web_Constants.PROJECT.toLowerCase()) {
			case "androidapp":
			case "androidtv":
			case "mobilerw": {
				Utilities utilities = new Utilities();
				utilities.stopAppiumServer();
				logStatus("INFO", "Quit Appium Server Successfully");
				driver.quit();
				break;
			}
			default:
				break;
			}
		}
	}

	public void clearCookies() {
		((JavascriptExecutor) driver)
				.executeScript(" var cookies = document.cookie.split(';');								"
						+ " for (var i = 0; i < cookies.length; i++) {							"
						+ " var cookie = cookies[i];      										"
						+ " var eqPos = cookie.indexOf('=');  									"
						+ " var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;			"
						+ " document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT';	" + " };");
	}

	public void logScreenShot(Status status, String details) {

		Utilities utilities = new Utilities();

		if (Web_Constants.SCREENSHOT_TO_FOLDER) {
			try {
				test.log(status, details,
						MediaEntityBuilder
								.createScreenCaptureFromPath(
										utilities.captureScreenshot(driver, Web_Constants.SCREENSHOT_TO_FOLDER))
								.build());
			} catch (Exception e) {
				e.printStackTrace();
				test.log(Status.INFO, "Unable to take a screenshot");
			}
		} else if (!Web_Constants.SCREENSHOT_TO_FOLDER) {
			try {
				test.log(status, details,
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										utilities.captureScreenshot(driver, Web_Constants.SCREENSHOT_TO_FOLDER))
								.build());
			} catch (Exception e) {
				e.printStackTrace();
				test.log(Status.INFO, "Unable to take a screenshot");
			}
		}
	}

	public void logScreenShot() {

		Utilities utilities = new Utilities();
		try {
			test.log(Status.INFO, "", MediaEntityBuilder
					.createScreenCaptureFromBase64String(utilities.captureScreenshot(driver, false)).build());
		} catch (IOException e) {
//			test.error("Unable to take the screenshot");
		}

	}

	

	public void quitDriverForAndroidApp() {
		if (driver != null) {
			try {
				driver.quit();
				logStatus("INFO", "Quit Browser Successfully");
			} catch (Exception e) {
			}
		}
	}
}
