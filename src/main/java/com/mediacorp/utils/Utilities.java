package com.mediacorp.utils;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.event.MenuKeyEvent;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.mediacorp.pages.BasePage;


import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class Utilities {

	public AppiumDriverLocalService service;
	public AppiumServiceBuilder builder;
	public DesiredCapabilities cap;
	public String service_url;

	public String getDataFromPropertyFile(String propertyFile, String key) {

		String fileName = "";
		
		
//		if (Web_Constants.OS.equalsIgnoreCase("mac")) {
//			fileName = System.getProperty("user.dir") + "/PropertyFiles/" + propertyFile + ".properties";
//		} else {
//			fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" + propertyFile + ".properties";
//		}

		if(Web_Constants.ENV.equalsIgnoreCase("Stage")) {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/" + "SearchContents_Stage.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" +"SearchContents_Stage.properties";
			}
		}
		else if (Web_Constants.ENV.equalsIgnoreCase("PreProd")) {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/" + "SearchContentsPreProd.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" +"SearchContentsPreProd.properties";
			}
		}
		else {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/" + "SearchContents.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" + "SearchContents.properties";
			}
		}
		
		
		String value = "";

		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.get(key).toString();
		} catch (IOException e) {

		}
		return value;
	}
	
	public String getTimeStamp_sec() {

		String timeStamp = "";
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		timeStamp = timeStamp + (c.get(Calendar.MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR)
				+ c.get(Calendar.MINUTE) + c.get(Calendar.SECOND) + c.get(Calendar.MILLISECOND);

		return timeStamp;
	}
	
	public String replaceHexadeciValuetoUnichar(String base64String) {
		int i = 1;
		// String
		// base64String="eyJldmVudCI6ICJNYXN0aGVhZCBJbXByZXNzaW9uIiwicHJvcGVydGllcyI6IHsiJG9zIjogIldpbmRvd3MiLCIkYnJvd3NlciI6ICJDaHJvbWUiLCIkcmVmZXJyZXIiOiAiaHR0cHM6Ly93d3cudm9vdC5jb20vIiwiJHJlZmVycmluZ19kb21haW4iOiAid3d3LnZvb3QuY29tIiwiJGN1cnJlbnRfdXJsIjogImh0dHBzOi8vd3d3LnZvb3QuY29tLyIsIiRicm93c2VyX3ZlcnNpb24iOiA3MiwiJHNjcmVlbl9oZWlnaHQiOiA3NjgsIiRzY3JlZW5fd2lkdGgiOiAxMzY2LCJtcF9saWIiOiAid2ViIiwiJGxpYl92ZXJzaW9uIjogIjIuMjYuMCIsInRpbWUiOiAxNTQ4ODM3NjM0Ljk3MywiZGlzdGluY3RfaWQiOiAiMTY4OWRkMTJkMmZiLTBlYjAzZjg4MmZhMjMyLTU3YjE0M2EtMTAwMjAwLTE2ODlkZDEyZDMwMmNkIiwiJGRldmljZV9pZCI6ICIxNjg5ZGQxMmQyZmItMGViMDNmODgyZmEyMzItNTdiMTQzYS0xMDAyMDAtMTY4OWRkMTJkMzAyY2QiLCIkaW5pdGlhbF9yZWZlcnJlciI6ICJodHRwczovL3d3dy52b290LmNvbS8iLCIkaW5pdGlhbF9yZWZlcnJpbmdfZG9tYWluIjogInd3dy52b290LmNvbSIsIkZpcnN0IFRpbWUiOiBmYWxzZSwiRGV2aWNlIjogIkRlc2t0b3AiLCJVc2VyIFR5cGUiOiAiVHJhZGl0aW9uYWwiLCJHZW5yZSBTZWxlY3RlZCI6ICIiLCJMYW5ndWFnZSBTZWxlY3RlZCI6ICIgRW5nbGlzaCwgSGluZGkiLCJBZ2UiOiAiMTQiLCJHZW5kZXIiOiAiTSIsIkZpcnN0IE5hbWUiOiAiVGVzdCIsIkxhc3QgTmFtZSI6ICJUZXN0IiwiRW1haWwiOiAidGVzdDUyNzg1MkBnbWFpbC5jb20iLCJQbGF0Zm9ybSI6ICJXZWIiLCJEYXRlIjogIjIwMTktMDEtMzBUMDg6NDA6MzQuMDc0WiIsIkZpcnN0IFZpc2l0IERhdGUiOiAiMjAxOS0wMS0zMFQwODo0MDozMy4xMjVaIiwiTG9jYXRpb24iOiAiIiwiV2F0Y2ggSGlzdG9yeSBDbGVhcmVkIjogIiIsIkRheSBvZiBXZWVrIjogIldlZG5lc2RheSIsIklzIEZyb20gU2VhcmNoPyI6ICJObyIsIkNsaWNrZWQgVmlzaXQgQWQ%2FIjogZmFsc2UsIk1hc3RoZWFkIE1lbnUiOiAiaG9tZSIsIk1hc3RoZWFkIEFkIFNlcnZlciI6ICJERlAiLCJDYW1wYWlnbiBJRCI6IDI0Nzg2NTUzOTYsIkFkdmVydGlzZXIgSUQiOiA0NjMyMzIwMTExLCJ0b2tlbiI6ICJiNTdiOTJlZGNhOWRlNThjYWQ1YTYxM2E3ZDgyMDM0YiJ9fQ%3D%3D";
		for (char letter = ' '; letter < 274; letter++) {
			char asciichar = letter;
			int asciivalue = (int) asciichar;
			String hexvalue = ("%" + Integer.toHexString(asciivalue).toUpperCase());
			if (base64String.contains(hexvalue)) {
				base64String = base64String.replaceAll(hexvalue, "" + letter);
			}
			System.out.println("Base 64 String post replacing with hexa values was: " + base64String);

		}
		return base64String;

	}
	
	
	public String getDataFromPropertyFileforRW_MRW(String key) {

		String fileName = "";
		if(Web_Constants.ENV.equalsIgnoreCase("Stage")) {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/" + "SearchContents_Stage.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" +"SearchContents_Stage.properties";
			}
		}
		else if (Web_Constants.ENV.equalsIgnoreCase("PreProd")) {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/" + "SearchContentsPreProd.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" +"SearchContentsPreProd.properties";
			}
		}
		else {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/" + "SearchContents.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\" + "SearchContents.properties";
			}
		}

		String value = "";

		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.get(key).toString();
		} catch (IOException e) {

		}
		return value;
	}

	public String getCredentialsFromPropertyFile(String key) {

		String fileName = "";
		if (Web_Constants.PROJECT.contains("WEB")||Web_Constants.PROJECT.contains("MobileRW")) {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/Gmail_Cred.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\Gmail_Cred.properties";
			} 
		}else {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				fileName = System.getProperty("user.dir") + "/PropertyFiles/Credentials.properties";
			} else {
				fileName = System.getProperty("user.dir") + "\\PropertyFiles\\Credentials.properties";
			} 
		}
		String value = "";

		switch (key) {
		case "PremiumUserEmail": {
			value = System.getProperty("PremiumUserName");
			break;
		}

		case "PremiumUserPassword": {
			value = System.getProperty("PremiumPassword");
			break;
		}

		case "FreeUserEmail": {
			value = System.getProperty("FreeUserName");
			break;
		}

		case "FreeUserPassword": {
			value = System.getProperty("FreePassword");
			break;
		}

		default: {
			break;
		}
		}

		if (value == "" || value == null) {
			try {
				FileInputStream fis = new FileInputStream(fileName);
				Properties prop = new Properties();
				prop.load(fis);
				value = prop.getProperty(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	// To generate random Password. generatePwd renamed to generateRandomPassword
	public String generateRandomPassword() {
		
		String strRandom = "";
		String strAlpha = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		String strNumerics = "0123456789";
		String strSpecial = "@$#";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		StringBuilder strRandomAlpha = new StringBuilder(9);
		StringBuilder strRandomSpecial = new StringBuilder(9);
		for (int i = 0; i < 4; i++) {
			strRandomAlpha.append(strAlpha.charAt(rnd.nextInt(strAlpha.length())));
			strRandomNumber.append(strNumerics.charAt(rnd.nextInt(strNumerics.length())));
			strRandomSpecial.append(strSpecial.charAt(rnd.nextInt(strSpecial.length())));

		}
		strRandom = strRandomAlpha.toString() + strRandomSpecial.toString() + strRandomNumber.toString();
		String pwd = strRandom;
		return pwd;
	}

	public String generateNo() {
		Random rnd = new Random();
		int i = rnd.nextInt(9);
		String num="1"+i;
		return num;
	}
	
	public String generatePostalCodeNo() {
		String strRandom = "";
		String strNumbers = "0123456789";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 6; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandom = strRandomNumber.toString();
		String postalNumber = "3" + strRandom;
		System.out.println("Random Generated Postal Number: "+postalNumber);
		return postalNumber;
	}

	public String generateEmailid() {
		String strRandom = "";
		String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 5; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandom = strRandomNumber.toString();
		String email = strRandom + "@" + "gmail" + ".com";
		return email;
	}
	
	public String generateMobileNumber() {
		String strRandom = "";
		String strNumbers = "0123456789";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 9; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandom = strRandomNumber.toString();
		String mobileNumber = "3" + strRandom;
		System.out.println("Random Generated Mobile Number: "+mobileNumber);
		return mobileNumber;
	}

	public String generateRandomName() {
		String strRandom = "";
		String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 5; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandom = strRandomNumber.toString();
		
		return strRandom;
	}
	// To take the screen shot
	public String captureScreenshot(WebDriver driver, boolean screenshotToFile) throws IOException {

		String screenShotFilePath = "";
		String screenShotName = "";
		String returnString = "";
		BaseTest configMethod = new BaseTest();
		try {
			if (!screenshotToFile) {

				String Base64StringOfScreenshot = "";
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
				String sDate = sdf.format(date);
				FileUtils.copyFile(src, new File(configMethod.screenShotFilePath + "image_" + sDate + ".png"));

				byte[] fileContent = FileUtils.readFileToByteArray(src);
				Base64StringOfScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
				returnString = Base64StringOfScreenshot;

				// TakesScreenshot ts = (TakesScreenshot) driver;
				// String image = ts.getScreenshotAs(OutputType.BASE64);
				// String image2 = createResizedCopy(image, 800, 600, true);
				// return image2;
			} else if (screenshotToFile) {
				if (Web_Constants.OS.equalsIgnoreCase("Windows")) {
					screenShotFilePath = configMethod.screenShotFilePath + configMethod.className + "_"
							+ configMethod.hour + "\\";
				}

				else if (Web_Constants.OS.equalsIgnoreCase("Mac")) {
					screenShotFilePath = configMethod.screenShotFilePath + configMethod.className + "_"
							+ configMethod.hour + "/";
				}

				createFolder(screenShotFilePath);
				screenShotName = configMethod.methodName + "_" + getTimeStampWithsec() + ".png";
				TakesScreenshot ts = (TakesScreenshot) driver;
				File ScreenShot = ts.getScreenshotAs(OutputType.FILE);
				String destpath = "";

				destpath = screenShotFilePath + screenShotName;

				File destPath = new File(destpath);

				FileUtils.moveFile(ScreenShot, destPath);

				returnString = destpath;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

	// To convert compress the base64 screenshot
	public String createResizedCopy(String base64String, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
		try {

			byte[] decodedBytes = Base64.getDecoder().decode(base64String);
			BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(decodedBytes));
			int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
			BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
			Graphics2D g = scaledBI.createGraphics();
			if (preserveAlpha) {
				g.setComposite(AlphaComposite.Src);
			}
			g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(scaledBI, "gif", bos);
			String imageString = Base64.getEncoder().encodeToString(bos.toByteArray());

			g.dispose();
			return imageString;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String generateRandomname() {
		String strRandomname = "";
		String strNumbers = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 8; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandomname = strRandomNumber.toString();
		return strRandomname;
	}

	public String generateRandomId() {
		String strRandomname = "";
		String strNumbers = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		for (int i = 0; i < 6; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		strRandomname = strRandomNumber.toString() + "003";
		return strRandomname;
	}

	// Method To Create Folder
	public void createFolder(String foldPath) {
		File f = new File(foldPath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	// To start the Appium Server
	public void startAppiumServer() {
		Map<String, String> env;
		
		if (Web_Constants.OS.equalsIgnoreCase("Mac")) {
			env = new HashMap<>(System.getenv());
			env.put("PATH", "/usr/local/bin:" + env.get("PATH"));
			AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress(Web_Constants.APPIUM_IPADDRESS)
					.usingPort(Web_Constants.APPIUM_PORT).withEnvironment(env)
					.usingDriverExecutable(new File(Web_Constants.NODEJS_PATH))
					.withAppiumJS(new File(Web_Constants.APPIUM_JS_PATH)).withArgument (BASEPATH, "/wd/hub");
			service = AppiumDriverLocalService.buildService(builder);
			service.clearOutPutStreams();
		} else {
			AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress(Web_Constants.APPIUM_IPADDRESS)
					.usingPort(Web_Constants.APPIUM_PORT).usingDriverExecutable(new File(Web_Constants.NODEJS_PATH))
					.withAppiumJS(new File(Web_Constants.APPIUM_JS_PATH)).withArgument (BASEPATH, "/wd/hub");
			service = AppiumDriverLocalService.buildService(builder);
			service.clearOutPutStreams();
		}

		if (service.isRunning() == true) {
			service.stop();
		} else {
			try {
				service.start();
			} catch (AppiumServerHasNotBeenStartedLocallyException e) {
				startAppiumServer();
			}
		}
		
	}

	public void stopAppiumServer() {

		try {
			
			if (service != null) {
				System.out.println("Stopping Appium Server");
				service.stop();
				System.out.println("Appium Server Stopped");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// test.log(Status.INFO, "service::" + service.isRunning());

	}

	public void installingApplication(String environment, ExtentTest test) {
		RemoteWebDriver driver;

		EventUtils eventUtils = new EventUtils();

		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp") || Web_Constants.PROJECT.equalsIgnoreCase("AndroidTV")) {
			try {
				Runtime.getRuntime().exec("adb -s " + Web_Constants.UDID + " uninstall " + Web_Constants.APP_PACKAGE);
				System.out.println("[Event]  Me Watch if present, then Uninstalled or Not Present Now");
				Thread.sleep(10000);
			} catch (Exception g) {
				System.err.println("[EXCEPTION] Unable to Uninstall MeWatch App");

			} 
		}
		if (environment.equalsIgnoreCase("Prod")) {

			if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				DesiredCapabilities DesiredCaps = new DesiredCapabilities();
				DesiredCaps.setCapability("appPackage", "com.android.vending");
				DesiredCaps.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
				DesiredCaps.setCapability("deviceName", "Moto-G5S");
				DesiredCaps.setCapability("platformName", "Android");
				DesiredCaps.setCapability("udid", Web_Constants.UDID);
				DesiredCaps.setCapability("noReset", true);
				DesiredCaps.setCapability("fullReset", false);
				DesiredCaps.setCapability("newCommandTimeout", 300 * 600);
				driver = new AndroidDriver(service.getUrl(), DesiredCaps);
				String searchBox = "//android.widget.TextView[contains(@text,'Search')]";
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchBox), 20)) {
					driver.findElement(By.xpath(searchBox)).click();
				}
				String searchBoxField = "//android.widget.EditText";
			//	String search1stContent ="//android.view.View[contains(@content-desc,'Search')]";
				String search1stContent ="//android.view.View[contains(@content-desc,\"Search for 'mewatch' \")]";
				
				//String searchBoxField = "//android.widget.EditText[contains(@text,'Search')]";
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchBoxField), 20)) {
					driver.findElement(By.xpath(searchBoxField)).sendKeys("mewatch");
					if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(search1stContent), 30)) {
					driver.findElement(By.xpath(search1stContent)).click();
					}
				}
			//	String InstallButton = "//*[@content-desc='Install']";
				String InstallButton = "//android.widget.TextView[contains(@text,'mewatch: Watch Video, Movies')]/..//android.view.View[@content-desc='Install']";
				String InstallButton1 = "//android.view.View[contains(@content-desc,'mewatch: Watch Video, Movies')]/..//android.view.View[@content-desc='Install']";
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(InstallButton), 10)) {
					driver.findElement(By.xpath(InstallButton)).click();
				}else {
					driver.findElement(By.xpath(InstallButton1)).click();
				}
				//String OpenButton = "//*[@content-desc='Open']";
				String OpenButton = "//android.widget.TextView[contains(@text,'mewatch: Watch Video, Movies')]/..//android.view.View[@content-desc='Open']";
				String OpenButton1 = "//android.view.View[contains(@content-desc,'mewatch: Watch Video, Movies')]/..//android.view.View[@content-desc='Open']";
				
				i:	for(int i=0;i<40;i++)
					{
						try {
							if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(OpenButton), 20)
									||eventUtils.waitUntilElementIsVisible(driver, By.xpath(OpenButton1), 20)) {
								System.out.println("Application Launched Successfully");
								break i;
							}
						} catch (Exception e) {
							try {
								Thread.sleep(5);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(OpenButton), 20)
						||eventUtils.waitUntilElementIsVisible(driver, By.xpath(OpenButton1), 20)) {
					System.out.println("Application Launched Successfully");
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidTV")) {
				DesiredCapabilities DesiredCaps = new DesiredCapabilities();
				DesiredCaps.setCapability("appPackage", "com.android.vending");
				DesiredCaps.setCapability("appActivity", "com.google.android.finsky.tvmainactivity.TvMainActivity");
				DesiredCaps.setCapability("deviceName", Web_Constants.DEVICE_NAME);
				DesiredCaps.setCapability("platformName", "Android");
				DesiredCaps.setCapability("udid", Web_Constants.UDID);
				DesiredCaps.setCapability("noReset", true);
				DesiredCaps.setCapability("fullReset", false);
				DesiredCaps.setCapability("newCommandTimeout", 300 * 600);
				driver = new AndroidDriver(service.getUrl(), DesiredCaps);

				TV_EventUtils tvEventUtils = new TV_EventUtils();

				tvEventUtils.upButton(test);

				String searchBox = "//androidx.leanback.widget.StreamingTextView[@text='Search']";

				tvEventUtils.horizontalLeftNavigation(driver, By.xpath(searchBox), 3, test);

				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchBox), 20)) {
					driver.findElement(By.xpath(searchBox)).sendKeys("mewatch");
				}

				String InstallButton = "//android.widget.Button[@text='Install']";
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(InstallButton), 20)) {
					driver.findElement(By.xpath(InstallButton)).click();
				}
				String OpenButton = "//android.widget.Button[@text='Open']";
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(OpenButton), 180)) {
					System.out.println("Application Launched Successfully");
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("iOSApp")) {
				
				Web_Constants.PLATFORM_NAME = "iOS";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", Web_Constants.DEVICE_NAME);
				capabilities.setCapability("platformName", Web_Constants.PLATFORM_NAME);
				capabilities.setCapability("automationName", Web_Constants.AUTOMATION_NAME);
				capabilities.setCapability("platformVersion", Web_Constants.DEVICE_VERSION);
				capabilities.setCapability("udid", Web_Constants.UDID);
				capabilities.setCapability("xcodeSigningId", "iPhone Developer");
				capabilities.setCapability("xcodeOrgId", Web_Constants.XCODE_ORG_ID);
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("waitForQuiescence", false);
				capabilities.setCapability("newCommandTimeout", 0);
				capabilities.setCapability("simpleIsVisibleCheck", true);
				capabilities.setCapability("useJSONSource", true);
				capabilities.setCapability("newCommandTimeout", 300 * 300);
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("usePrebuiltWDA", true);
				capabilities.setCapability("unlockType", "pin");
				capabilities.setCapability("unlockKey", "111111");
				capabilities.setCapability("autoAcceptAlerts", "true");
				capabilities.setCapability("bundleId", "com.apple.AppStore");

				try {
					driver = new AppiumDriver(new URL(Web_Constants.HUB_URL), capabilities);

					String searchBox = "//XCUIElementTypeButton[@label='Search']";

					if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchBox), 20)) {
						driver.findElement(By.xpath(searchBox)).click();
					}
					
					String searchBoxField = "//XCUIElementTypeSearchField";
					if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchBoxField), 20)) {
						driver.findElement(By.xpath(searchBoxField)).sendKeys("mewatch");
					}
					
					String SearchText = "//XCUIElementTypeStaticText[contains(@name,'mewatch')]";
					if (eventUtils.waitUntilElementIsPresent(driver, By.xpath(SearchText), 20)) {
						driver.findElement(By.xpath(SearchText)).click();
					}
					
					String InstallButton = "//XCUIElementTypeButton[contains(@name,'mewatch')]/XCUIElementTypeButton";
					if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(InstallButton), 20)) {
						driver.findElement(By.xpath(InstallButton)).click();
					}
					
					String OpenButton = "//XCUIElementTypeButton[contains(@name,'mewatch')]//XCUIElementTypeButton[contains(@label,'open')]";
					if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(OpenButton), 180)) {
						System.out.println("Application Launched Successfully");
					}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
			}

		} else if (environment.equalsIgnoreCase("Prod(Test Flight)")) {

			if (Web_Constants.PROJECT.equalsIgnoreCase("iOSApp")) {

				By meWatchAppLocator = By.xpath("(//XCUIElementTypeStaticText[contains(@name,'mewatch -')])[1]");

				By meWatchAppInstallLocator = By.name("INSTALL");

				By meWatchAppOpenLocator = By.name("OPEN");

				Web_Constants.PLATFORM_NAME = "iOS";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", Web_Constants.DEVICE_NAME);
				capabilities.setCapability("platformName", Web_Constants.PLATFORM_NAME);
				capabilities.setCapability("automationName", Web_Constants.AUTOMATION_NAME);
				capabilities.setCapability("platformVersion", Web_Constants.DEVICE_VERSION);
				capabilities.setCapability("udid", Web_Constants.UDID);
				capabilities.setCapability("xcodeSigningId", "iPhone Developer");
				capabilities.setCapability("xcodeOrgId", Web_Constants.XCODE_ORG_ID);
				capabilities.setCapability("clearSystemFiles", true);
				capabilities.setCapability("waitForQuiescence", false);
				capabilities.setCapability("newCommandTimeout", 0);
				capabilities.setCapability("simpleIsVisibleCheck", true);
				capabilities.setCapability("useJSONSource", true);
				capabilities.setCapability("newCommandTimeout", 300 * 300);
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("usePrebuiltWDA", true);
				capabilities.setCapability("unlockType", "pin");
				capabilities.setCapability("unlockKey", "111111");
				capabilities.setCapability("autoAcceptAlerts", "true");
				capabilities.setCapability("bundleId", "com.apple.TestFlight");

				try {
					driver = new AppiumDriver(new URL(Web_Constants.HUB_URL), capabilities);

					if (eventUtils.waitUntilElementIsPresent(driver, meWatchAppLocator, 20)) {
						driver.findElement(meWatchAppLocator).click();
					}

					eventUtils.sleep(3);

					if (eventUtils.waitUntilElementIsPresent(driver, meWatchAppInstallLocator, 20)) {
						driver.findElement(meWatchAppInstallLocator).click();
					}

					eventUtils.waitUntilElementIsVisible(driver, meWatchAppOpenLocator, 240);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

		} else {
			
			GoogleSheetUtils googleSheetUtils = new GoogleSheetUtils();

			String BuildLink = googleSheetUtils.getBuildLink();

			

			// adb -s RZCR4035L0T install
			// "C:\Users\iFocus\Downloads\mewatch-preprod-5.3.650(51212)-release (2).apk"

			if (Web_Constants.PROJECT.toLowerCase().contains("androidapp") || Web_Constants.PROJECT.toLowerCase().contains("tv")) {
				
				RemoteWebDriver chromedriver = new EdgeDriver();
				chromedriver.get(BuildLink);
				BasePage basePage = new BasePage(chromedriver, test);
				String downloadXpath = "//a[text()='Download']";
				if (eventUtils.waitUntilElementIsVisible(chromedriver, By.xpath(downloadXpath), 20)) {
					chromedriver.findElement(By.xpath(downloadXpath)).click();
				}
				chromedriver.get("edge://downloads/all");
				String downloadCompleteXpath = "//button[contains(@id,'open_file')]";
				String fileName = "";
				if (eventUtils.waitUntilElementIsVisible(chromedriver, By.xpath(downloadCompleteXpath), 20)) {
					fileName = chromedriver.findElement(By.xpath(downloadCompleteXpath)).getText();
				}
				String showInFolder = "//span[text()='Show in folder']";
				String filePath = System.getProperty("user.home") + "\\Downloads\\" + fileName;
				if (eventUtils.waitUntilElementIsVisible(chromedriver, By.xpath(showInFolder), 180)) {
					basePage.logStatus("info", "Application Downloaded Successfull");
					chromedriver.quit();
				}
				try {
					Runtime.getRuntime().exec("adb -s " + Web_Constants.UDID + " install " + "\"" + filePath + "\"");
					Thread.sleep(50000);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}
	}

	// To get the time stamp with seconds
	public String getTimeStampWithsec() {

		String timeStamp = "";
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		timeStamp = timeStamp + (c.get(Calendar.MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.HOUR)
		+ c.get(Calendar.MINUTE) + c.get(Calendar.SECOND) + c.get(Calendar.MILLISECOND);

		return timeStamp;
	}

	// To get the time stamp with seconds
	public String getDayOfTheMonth() {

		String dateStamp = "";
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);

		dateStamp = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

		return dateStamp;
	}
	
	public String getSpecificFormat() {
		String dateStamp = "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd");  
			Date date = new Date();  
			dateStamp = formatter.format(date);
			System.out.println(dateStamp);  
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dateStamp ;
	}

	// To get the time stamp with seconds
		public String getLastDateOfTheMonth() {

			String dateStamp = "";
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			
			dateStamp = String.valueOf(c.getActualMaximum(Calendar.DATE));
			return dateStamp;
		}
		
	// To get the Time Stamp in Date and Time Format
	public String getTimeStamp() {
		Date d = new Date();
		return d.toString().replace(":", "_").replace(" ", "_");
	}

	
	public static String getTimeStampEpoch() {
		Instant instant = Instant.now();
		// java.util.Date date= new java.util.Date();
		Long time = instant.getEpochSecond();
		time = time;
		return time.toString();
	}
	
	public static String getTimeStampEpochAfter() {
		Instant instant = Instant.now();
		// java.util.Date date= new java.util.Date();
		Long time = instant.getEpochSecond();
		time = time+100;
		return time.toString();
	}
	
	// To Open Charles
	public void openCharles() {
		try {

			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				Runtime.getRuntime().exec("open /Applications/Charles.app");
			} else {
				Runtime.getRuntime().exec("Charles.exe");
			}

			Thread.sleep(5000);
			Web_Constants.localhost = InetAddress.getLocalHost();
			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();
			System.out.println("System IP Address : " + (Web_Constants.localhost.getHostAddress()).trim());
			Thread.sleep(5000);
			Runtime.getRuntime()
			.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(2000);
			Runtime.getRuntime().exec(
					"curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/recording/start");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To save charles logs and close charles
	public void saveCharlesAndClearCharles(String fileName) {
		try {
			DateFormat date = new SimpleDateFormat("ddmmyyHHMMss");
			Date date2 = new Date();
			String date1 = date.format(date2);
			Web_Constants.charlesName = fileName + date1 + ".xml";
			Web_Constants.charleslogsName = fileName + date1 + ".chls";

			if (Web_Constants.OS.equalsIgnoreCase("Mac")) {
				createFolder("/Users/" + Web_Constants.Mac_UserName + "/Desktop/meWatch-Automation/Charleslogs/");
				Web_Constants.filePathxml = "/Users/" + Web_Constants.Mac_UserName
						+ "/Desktop/meWatch-Automation/Charleslogs/" + Web_Constants.charlesName;
				Web_Constants.filePathlogs = "/Users/" + Web_Constants.Mac_UserName
						+ "/Desktop/meWatch-Automation/Charleslogs/" + Web_Constants.charleslogsName;
			} else {
				Web_Constants.filePathxml = "C:\\Charleslogs\\" + Web_Constants.charlesName;
				Web_Constants.filePathlogs = "C:\\Charleslogs\\" + Web_Constants.charleslogsName;
			}

			Web_Constants.localhost = InetAddress.getLocalHost();
			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();
			Runtime.getRuntime().exec(
					"curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/recording/stop");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("curl -o " + Web_Constants.filePathxml + " -x http://" + Web_Constants.ipAdress
					+ ":8888 http://control.charles/session/export-xml");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("curl -o " + Web_Constants.filePathlogs + " -x http://" + Web_Constants.ipAdress
					+ ":8888 http://control.charles/session/download");
			Thread.sleep(2000);
			Runtime.getRuntime().exec(
					"curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/recording/start");
			Thread.sleep(2000);
			Runtime.getRuntime()
			.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(2000);
		} catch (Exception e) {

		}
	}

	// To close charles
	public void clearCharles() {
		try {

			Web_Constants.localhost = InetAddress.getLocalHost();
			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();

			Runtime.getRuntime().exec(
					"curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/recording/start");
			Thread.sleep(2000);
			Runtime.getRuntime()
			.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(2000);
		} catch (Exception e) {

		}
	}

	// To close charles
	public void closeCharles() {
		try {
			Runtime.getRuntime()
			.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/quit");
		} catch (Exception e) {

		}
	}

	// To save charles logs and close charles
	public void saveandcloseCharles(String fileName) {
		try {
			DateFormat date = new SimpleDateFormat("ddmmyyHHMMss");
			Date date2 = new Date();
			String date1 = date.format(date2);
			Web_Constants.charlesName = fileName + date1 + ".xml";
			Web_Constants.charleslogsName = fileName + date1 + ".chls";
			if (Web_Constants.OS.equalsIgnoreCase("Mac")) {
				createFolder("/Users/" + Web_Constants.Mac_UserName + "/Desktop/VootKids-Web-Automation/Charleslogs/");
				Web_Constants.filePathxml = "/Users/" + Web_Constants.Mac_UserName
						+ "/Desktop/meWatch-Automation/Charleslogs/" + Web_Constants.charlesName;
				Web_Constants.filePathlogs = "/Users/" + Web_Constants.Mac_UserName
						+ "/Desktop/meWatch-Automation/Charleslogs/" + Web_Constants.charleslogsName;
			} else {
				Web_Constants.filePathxml = "C:\\Charleslogs\\" + Web_Constants.charlesName;
				Web_Constants.filePathlogs = "C:\\Charleslogs\\" + Web_Constants.charleslogsName;
			}
			Web_Constants.ipAdress = Web_Constants.localhost.getHostAddress().trim();
			Runtime.getRuntime().exec(
					"curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/recording/stop");
			Thread.sleep(10000);
			Runtime.getRuntime().exec("curl -o " + Web_Constants.filePathxml + " -x http://" + Web_Constants.ipAdress
					+ ":8888 http://control.charles/session/export-xml");
			Thread.sleep(5000);
			Runtime.getRuntime().exec("curl -o " + Web_Constants.filePathlogs + " -x http://" + Web_Constants.ipAdress
					+ ":8888 http://control.charles/session/download");
			Thread.sleep(10000);
			Runtime.getRuntime()
			.exec("curl -v -x http://" + Web_Constants.ipAdress + ":8888 http://control.charles/quit");
			Thread.sleep(2000);
		} catch (Exception e) {

		}
	}

	public String generateRandomMobNo(int n) {
		Random ran=new Random();
		String s="";
		for (int i = 0; i <=n; i++) {
			s=s+String.valueOf(ran.nextInt(++i));
			i--;
		}
		return s;  
	}
	
	public String generateRandomCharater(int i) {
		Random ran = new Random();
		String s="AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		return String.valueOf(s.charAt(ran.nextInt(i)));
	}

	public void upgradeTheAPKFile() {
		try {
			if(Web_Constants.ENV.equalsIgnoreCase("preprod")) {
				Runtime.getRuntime().exec("adb install " + Web_Constants.preprod_Upgrade_APK_PATH);
			}else {
				Runtime.getRuntime().exec("adb install " + Web_Constants.Upgrade_APK_PATH);
			}
			System.out.println("[Event] UpGrading the Me Watch apk");
			Thread.sleep(35000);
		} catch (Exception g) {
			System.err.println("[EXCEPTION] Unable to UpGrade the MeWatch App");
		} 
	}
	
	public void downgradeTheAPKFile() {
		try {
			if(Web_Constants.ENV.equalsIgnoreCase("preprod")) {
				Runtime.getRuntime().exec("adb install -r -d " + Web_Constants.preprod_DownGrade_APK_PATH);
			}else {
				Runtime.getRuntime().exec("adb install -r -d " + Web_Constants.DownGrade_APK_PATH);
			}
			System.out.println("[Event]  DownGrading the Me Watch apk");
			Thread.sleep(40000);
		} catch (Exception g) {
			System.err.println("[EXCEPTION] Unable to DownGrading the Me Watch apk");
		} 
	}
	
}
