package com.mediacorp.smoke.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import com.mediacorp.pages.AndroidTV_Page;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtilsnew;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Sony_AndroidTV_Demo extends BaseTest {
 
	
	@Before("@Demo_AndroidTV")
	public void setUp(Scenario scenario) {
		
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
 	}
	
	
	@Given("^Launching the Application AndroidTV$")
	public void Launching_the_Application_AndroidTV() {
		createNode("Given", "Launching the Application");
		System.out.println("Launching the Application");
		launchApplication();
		logStatus("Pass", "Launching the application");
		
		
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	@Then("^Verify the playback for the Show AndroidTV$")
	public void Verify_the_playback_forthe_Show_AndroidTV() {

		createNode("Then", "Verify the playback for the Show AndroidTV");
		EventUtilsnew eventUtils = new EventUtilsnew(driver,test);
		AndroidTV_Page androidTV_Page = new AndroidTV_Page(driver);



		

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.HomeLogoOfAndroidTV, 20)) {
			logStatus("pass", "User is able to see Home Logo Of AndroidTV");
		} else {
			logStatus("fail", "User is not able to see Home Logo Of AndroidTv");
		}

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.searchButtonOfAndroidTv, 20)) {
			eventUtils.clickOnElement(androidTV_Page.searchButtonOfAndroidTv, "Search Button Of AndroidTV", 20);
			logStatus("info", "User is able to see Home Logo Of AndroidTv");
		} else {
			logStatus("fail", "User is not able to see Home Logo Of AndroidTv ");
		}

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.searchFieldOfAndroidTv, 20)) {
			eventUtils.enterValue(androidTV_Page.searchFieldOfAndroidTv, "Shark Tank India", "Search Field Of Android",
					20);
			logStatus("info", "User is able to enter value on Search Field Of AndroidTV");
		} else {
			logStatus("fail", "User is not able to enter value on Search Field Of AndroidTV");
		}

		eventUtils.sleep(2);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.TitleofSearchResultOfAndroidTV, 10)
				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.platButtonOfAndroidTV, 10)) {
			eventUtils.clickOnElement(androidTV_Page.SearchResultContentOfAndroidTv,
					"Search Result Content Of Android Tv", 10);
			logStatus("pass", "User is able to Title & click on play Button of Search Content Of AndroidTV");
		} else {
			logStatus("fail", "User is not able to Title & click on play Button of Search Content Of AndroidTV");
		}

		eventUtils.sleep(2);
		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.WatchButtonOfAndroidTV, 10)) {
			eventUtils.clickOnElement(androidTV_Page.WatchButtonOfAndroidTV, "Watch Button Of Android Tv", 10);
			logStatus("Pass", "User is able to click on watch Button of Search Content Of AndroidTV");
		} else {
			logStatus("fail", "User is not able to click on watch Button of Search Content Of AndroidTv");
		}

		eventUtils.sleep(30);
		try {
			Utilities.openCamera();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Utilities.clickOnRecord();
		try {
			Runtime.getRuntime().exec("adb -s " + Web_Constants.UDID + " shell input keyevent KEYCODE_DPAD_UP");
			eventUtils.sleep(2);
			Runtime.getRuntime().exec("adb -s " + Web_Constants.UDID + " shell input keyevent KEYCODE_ENTER");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		eventUtils.sleep(3);
//		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.playerOfShowDetailsPageOfAndroidTV, 10)) {
//			eventUtils.clickOnElement(androidTV_Page.playerOfShowDetailsPageOfAndroidTV,
//					"player Of Show Details Page Of AndroidTV", 10);
//			logStatus("info", "User is able to click on Player Show detail Page of Search Content Of AndroidTv");
//		} else {
//			logStatus("fail", "User is not able to click on Player Show detail Page of Search Content Of AndroidTv");
//		}

//		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.PlayandpauseButtonOfShowDetailsPageOfAndroidTV, 10)) {
//			eventUtils.clickOnElement(androidTV_Page.PlayandpauseButtonOfShowDetailsPageOfAndroidTV,
//					"Play and pause Button Of Show Details Page OfAndroidTV", 10);
//			logStatus("info", "User is able to click on play&Pause button Show detail Page Of AndroidTv");
//		} else {
//			logStatus("fail", "User is not able to click on play&Pause button Show detail Page Of AndroidTv");
//		}
//
//		if (eventUtils.waitUntilElementIsVisible(androidTV_Page.BackButtonOfShowDetailsPageOfAndroidTV, 10)
//				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.settingButtonOfShowDetailsPageOfAndroidTV, 10)
//				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.StartFromBeginningButtonOfShowDetailsPageOfAndroidTV, 10)
//				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.nextContentButtonOfShowDetailsPageOfAndroidTV, 10)
//				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.episodeTitleOfShowDetailsPageOfAndroidTV, 10)
//				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.LanguagebuttonOfShowDetailsPageOfAndroidTV, 10)
//				&& eventUtils.waitUntilElementIsVisible(androidTV_Page.controlProgressOfShowDetailsPageOfAndroidTV, 10)) {
//			logStatus("Pass", "User is able to see Play&Pause button , back button,setting button,startfrombeginning button,nextcontent button,episode Title,language button,control progress on Show detail Page Of AndroidTv");
//		} else {
//			logStatus("fail", "User is not able to see Play&Pause button , back button,setting button,startfrombeginning button,nextcontent button,episode Title,language button,control progress on Show detail Page Of AndroidTv");
//		}
//		
		eventUtils.sleep(60);
        Utilities.stopRecord();
		
		Utilities.closeRecordApp();
		 String folderPath = "C:\\Users\\Gourav\\Pictures\\Camera Roll";
	        File latestFile = getLatestFile(folderPath);

	        if (latestFile != null) {
	            System.out.println("Latest file: " + latestFile.getName());
	        } else {
	            System.out.println("No files found in the folder.");
	        }
	        
//	        File file = new File("/Users/ifocus/Downloads/timer_check.mp4");
	        Response response = RestAssured.given()
	                .multiPart(latestFile)
	                .when()
	                .post("https://analytics.autocodium.com/Video_Analysis");
	        System.out.println(response.getStatusCode());
	        System.out.println(response.getBody().asString());
		
	
	}
	
	
	
	
	
	@After("@Demo_AndroidTV")
	public void tearDown(Scenario scenario) {
		try {
			
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static  File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }
	
	
	
	
}
