package com.mediacorp.smoke.stepDefinitions;

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

import com.mediacorp.pages.MobileRW_Page;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtilsnew;
import com.mediacorp.utils.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Sony_MRW_Demo extends BaseTest {

	
	
	@Before("@Demo_MRW")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		
		
	}
	
	
	@Given("^Launching the Application MRW$")
	public void Launching_the_Application_MRW() {
		createNode("Given", "Launching the Application");
		System.out.println("Launching the Application");
		launchApplication();
		logStatus("Pass", "Launching the application");
	}
	
	
	
	
	@Then("^Verify the playback for the Show MRW$")
	public void Verify_the_playback_forthe_Show_MRW() {
		createNode("Then", "Verify the playback for the Show MRW");
		EventUtilsnew eventUtils = new EventUtilsnew(driver,test);
		MobileRW_Page mobileRW_Page = new MobileRW_Page(driver);

		String title = driver.getTitle();
		System.out.println(title);

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.installPopUpOfMobileRW, 10)) {
			eventUtils.clickOnElement(mobileRW_Page.installPopUpOfMobileRW, "close button Of Install App Now PopUp", 20);
			logStatus("info", "user is able to see Install App Now PopUp & close the Popup");
		} else {
			logStatus("fail", "user is able to see Install App Now PopUp & close the Popup");
		}

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.notificationPopUpOfMobileRW, 10)) {
			eventUtils.clickOnElement(mobileRW_Page.notificationPopUpOfMobileRW, "Skip button Of notificatio PopUp",
					20);
			logStatus("info", "user is able to see notificatio PopUp & click on skip Button of the Popup");
		} else {
			logStatus("info", "user is not able to see notificatio PopUp & click on skip Button of the Popup");
		}

		eventUtils.sleep(3);
		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.homelogoOfMobileRW, 20)) {
			logStatus("Pass", "Homelogo is visible on Home Page");
		} else {
			logStatus("fail", "Homelogo is not visible on Home Page");
		}

		eventUtils.sleep(5);
		eventUtils.clickOnElement(mobileRW_Page.searchOfMobileRW, "Search Button ", 20);

		eventUtils.sleep(5);
		eventUtils.enterValue(mobileRW_Page.inputofSearchOfMobileRw, "Shark Tank India", "Enter the Show Name", 20);

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.watchbtnOfMobileRW, 20)) {
			logStatus("pass", "User is able to see The Search Result watch Button");
			eventUtils.clickOnElement(mobileRW_Page.watchbtnOfMobileRW, "Watch btn", 20);
		} else {
			logStatus("fail", "User is able to see The Search Result watch Button");
		}

		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.watchbtnOfEpisodepageOfMobileRw, 20)) {
			logStatus("pass", "User is able to see The Search Result watch Button On Episode Page");
			eventUtils.clickOnElement(mobileRW_Page.watchbtnOfEpisodepageOfMobileRw, "Watch btn", 20);
		} else {
			logStatus("fail", "User is able to see The Search Result watch Button On Episode Page");
		}

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.preRollAdofShowPageOfMobileRW, 40)) {
			logStatus("pass", "User is able to see AD visible content");
		} else {
			logStatus("fail", "User is able to see AD visible content");
		}
		eventUtils.sleep(60);

		eventUtils.clickByCoordinates(driver, mobileRW_Page.videoPlayerOfMobileRW);

		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.pausebtnOfMobileRw, 20)) {
			eventUtils.clickOnElement(mobileRW_Page.pausebtnOfMobileRw, "pause btn", 20);
			logStatus("pass", "User is able to see pause content on Show detail Page");
		} else {
			logStatus("fail", "User is not able to see pause content on Show detail Page");
		}

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.playbtnOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.backbtnOfshowpageOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.backWardbtnOfshowPageOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.forWardbtnOfShowPageOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.volumebtnOfShowPageOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.fullScreenOfshowOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.settingbtnOfShowOfMobileRW, 20)) {

			logStatus("pass",
					"User is able to see play ,backward ,back ,forward,volume ,full screen ,setting  contents on Show detail Page");
		} else {
			logStatus("fail",
					"User is not able to see play ,backward ,back ,forward,volume ,full screen ,setting  contents on Show detail Page");
		}

		eventUtils.clickOnElement(mobileRW_Page.playbtnOfMobileRW, "Play button", 20);

		eventUtils.clickOnElement(mobileRW_Page.volumebtnOfShowPageOfMobileRW, "volume button", 20);

		eventUtils.clickOnElement(mobileRW_Page.volumebtnOfShowPageOfMobileRW, "volume button", 20);

		eventUtils.clickOnElement(mobileRW_Page.settingbtnOfShowOfMobileRW, "setting button", 20);

		if (eventUtils.waitUntilElementIsVisible(mobileRW_Page.videoQualitybtnOfshowOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.subtitlebtnOfshowOfMobileRW, 20)
				&& eventUtils.waitUntilElementIsVisible(mobileRW_Page.audiobtnOfshowOfMobileRW, 20)) {

			logStatus("pass", "User is able to see the videoQualitybtn,subtitlebtn,audiolanguage in episode page");
		} else {
			logStatus("fail", "User is not able to see the videoQualitybtn,subtitlebtn,audiolanguage in episode page");
		}
	
	}
	
	
	
	
	
	@After("@Demo_MRW")
	public void tearDown(Scenario scenario) {
		try {
			
			try {
				Utilities utilities = new Utilities();
				
				utilities.stopAppiumServerone();
			} catch (Exception e) {
				
			}
			
			
			try {
				driver.close();
			} catch (Exception e) {
				
			}
			try {
				driver.quit();
			} catch (Exception e) {
				
			}
			
			
			
			
			
			killBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
