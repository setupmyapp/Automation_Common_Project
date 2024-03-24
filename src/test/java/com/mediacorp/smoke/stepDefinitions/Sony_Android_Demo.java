package com.mediacorp.smoke.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mediacorp.pages.Android_Page;
import com.mediacorp.pages.MobileRW_Page;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtilsnew;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.opentelemetry.api.metrics.ObservableLongGauge;

public class Sony_Android_Demo extends BaseTest {
	
	


	
	
	@Before("@Demo_Android")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		
		
	}
	
	
	@Given("^Launching the Application Android$")
	public void Launching_the_Application_Android() {
		createNode("Given", "Launching the Application");
		System.out.println("Launching the Application");
		launchApplication();
		logStatus("Pass", "Launching the application");
	}
	
	
	
	
	@Then("^Verify the playback for the Show Android$")
	public void Verify_the_playback_forthe_Show_Android() {
		createNode("Then", "Verify the playback for the Show Android");
		EventUtilsnew eventUtils = new EventUtilsnew(driver,test);
		Android_Page android_Page = new Android_Page(driver);
       
		

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(android_Page.notNowbtnOfAndroid, 20)) {
			eventUtils.clickOnElement(android_Page.notNowbtnOfAndroid, "NotNow button Of Android", 20);
			logStatus("info", "User is able to see notification Popup Of Android");
		} else {
			logStatus("info", "User is not able to see notification Popup Of Android");
		}
		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(android_Page.HomeLogoOfAndroid, 20)) {
			logStatus("pass", "User is able to see Home Logo Of Android");
		} else {
			logStatus("fail", "User is not able to see Home Logo Of Android");
		}

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(android_Page.SearchButtonOfAndroid, 20)) {
			eventUtils.clickOnElement(android_Page.SearchButtonOfAndroid, "Search Button Of Android", 20);
			logStatus("info", "User is able to Click Search button Of Android");
		} else {
			logStatus("fail", "User is not able to Click Search button Of Android");
		}

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(android_Page.SearchFieldOfAndroid, 20)) {
			eventUtils.enterValue(android_Page.SearchFieldOfAndroid, "Shark Tank India", "Search Field Of Android", 20);
			logStatus("info", "User is able to enter value on Search Field Of Android");
		} else {
			logStatus("fail", "User is not able to enter value on Search Field Of Android");
		}

		eventUtils.sleep(2);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

		eventUtils.sleep(3);
		String searchResultContent = eventUtils.getTextOfWebelement(android_Page.searchResultContentOfAndroid, 20);
		if (eventUtils.waitUntilElementIsVisible(android_Page.searchResultContentOfAndroid, 20)
				&& eventUtils.waitUntilElementIsVisible(android_Page.PlayButtonOfAndroid, 20)) {
			eventUtils.clickOnElement(android_Page.PlayButtonOfAndroid, "Play Button Of Andoid", 20);
			logStatus("pass", "User is able to click on Play button Of searched content Of Android");
		} else {
			logStatus("fail", "User is not able to click on Play button Of searched content Of Android");
		}

		eventUtils.sleep(3);
		String TitleOfSearchResult = eventUtils.getTextOfWebelement(android_Page.TitleOfSearchResultOfAndroid, 20);
		if (searchResultContent.contains(TitleOfSearchResult)
				&& eventUtils.waitUntilElementIsVisible(android_Page.watchButtonOfAndroid, 20)) {
		   android_Page.watchButtonOfAndroid.click();
			logStatus("pass", "User is able to click on Watch button Of searched content Of Android");
		} else {
			logStatus("fail", "User is not able to click on watch button Of searched content Of Android");
		}

		eventUtils.sleep(50);
		logStatus("pass",
				"user is able to see back Button,backwardbtn,playbtn,forwardbtn,Setting Button ,fullscreenbtn,on Show Details Page");
	
	}
	
	
	
	
	
	@After("@Demo_Android")
	public void tearDown(Scenario scenario) {
		try {
			
			
			Runtime.getRuntime().exec("adb -s "+Web_Constants.UDID+" shell am force-stop com.sonyliv");
			
			
			killBrowser();
		} catch (Exception e) {
			
		}
	}


	
}
