package com.mediacorp.smoke.stepDefinitions;

import java.io.IOException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mediacorp.pages.RW_Page;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.EventUtilsnew;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Sony_RW_Demo  extends BaseTest {

	
	
	
	@Before("@Demo_RW")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		
		
	}
	
	
	@Given("^Launching the Application RW$")
	public void verify_the_url_for_mewatch_product_rw() {
		createNode("Given", "Launching the Application");
		System.out.println("Launching the Application");
		launchApplication();
		logStatus("Pass", "Launching the application");
	}
	
	@Then("^Verify the playback for the Show RW$")
	public void Verify_the_playback_for_the_Show_RW() {
		createNode("Then", "Verify the playback for the Show RW");
		EventUtilsnew eventUtils = new EventUtilsnew(driver,test);
		RW_Page rw_Page = new RW_Page(driver);
		String title = driver.getTitle();
		System.out.println(title);
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(rw_Page.homelogo, 20)) {
			logStatus("Pass", "Homelogo is visible on Home Page");
		} else {
			logStatus("fail", "Homelogo is not visible on Home Page");
		}

		eventUtils.sleep(5);
		eventUtils.clickOnElement(rw_Page.SearchButton, "Search Button ", 20);

		eventUtils.sleep(5);
		eventUtils.enterValueToTheField(rw_Page.SearchField, "Shark Tank India", "Enter the Show Name", 10);

		eventUtils.sleep(5);
		List<WebElement> autosearchs = driver.findElements(By.xpath("//ul//li"));
		for (int i = 0; i < autosearchs.size(); i++) {
			String dataofautosearch = autosearchs.get(i).getText();
			if (dataofautosearch.contains("Shark Tank India")) {
				autosearchs.get(i).click();
				logStatus("info", "User is able to see search result ");
				break;
			} else {
				logStatus("error", "User is able to see search result ");
			}
		}
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(rw_Page.playNowbtn, 20)) {
			logStatus("info", "User is able to see search result ");
			eventUtils.clickOnElement(rw_Page.playNowbtn, "Click on search result", 20);
		} else {
			logStatus("fail", "User is unable to see search Sugession Content");
		}
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(rw_Page.titleofshow, 20)) {
			logStatus("pass", "User as navigate to show details page");
			eventUtils.clickOnElement(rw_Page.watchbtnOfshow, "watch free episode btn", 20);
		} else {
			logStatus("fail", "User unable to navigate to show details page");
		}

		eventUtils.sleep(20);

		for (int i = 0; i < 100; i++) {
			if (!eventUtils.waitUntilElementIsVisible(rw_Page.adtimer, 10)) {
				break;
			}
		}
		eventUtils.sleep(40);
		Actions act = new Actions(driver);
		act.moveToElement(
				driver.findElement(By.xpath("//div[@class='player-footer-controls']//..//img[@title='Pause']"))).build()
				.perform();
		if (eventUtils.waitUntilElementIsVisible(rw_Page.pausebtnOfShow, 60)) {
			logStatus("info", "playback started after ad completed ");

			eventUtils.clickOnElement(rw_Page.pausebtnOfShow, "pause btn", 20);
		} else {
			logStatus("fail", "Still ad is playing in the player page ");

		}
		if (eventUtils.waitUntilElementIsVisible(rw_Page.headerOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.backbtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.videoQualitybtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.SubtitleandaudiobtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.EpisodesbtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.backwardbtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.playbtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.forwardbtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.volumebtnOfshow, 20)
				&& eventUtils.waitUntilElementIsVisible(rw_Page.fullscreenbtnOfshow, 20)) {
			logStatus("pass",
					"user is able to see backbtn,videoqualitybtn,subtitle&audiobtn,episodesbtn,backwardbtn,playbtn,forwardbtn,volumebtn,fullscreenbtn,reportissueIcon on Shows Page");
		} else {
			logStatus("fail",
					"user is not able to see backbtn,videoqualitybtn,subtitle&audiobtn,episodesbtn,backwardbtn,playbtn,forwardbtn,volumebtn,fullscreenbtn,reportissueIcon on Shows Page");
		}

		eventUtils.sleep(5);
		eventUtils.clickOnElement(rw_Page.videoQualitybtnOfshow, "video Button", 20);

		eventUtils.sleep(5);
		eventUtils.clickOnElement(rw_Page.SubtitleandaudiobtnOfshow, "SubTitle Button", 20);

		eventUtils.sleep(5);
		eventUtils.clickOnElement(rw_Page.EpisodesbtnOfshow, "Episodes Button", 20);

		eventUtils.sleep(5);
		eventUtils.clickOnElement(rw_Page.closebtnOfEpisode, "Close Button", 20);

		eventUtils.sleep(5);
		eventUtils.clickOnElement(rw_Page.playbtnOfshow, "play Button", 20);

		eventUtils.clickOnElement(rw_Page.backwardbtnOfshow, "Backward Button", 20);

		eventUtils.clickOnElement(rw_Page.forwardbtnOfshow, "Forward Button", 20);

		eventUtils.clickOnElement(rw_Page.volumebtnOfshow, "volume Button", 20);

		eventUtils.clickOnElement(rw_Page.volumebtnOfshow, "volume Button", 20);

	
		
	}
	
	@After("@Demo_RW")
	public void tearDown(Scenario scenario) {
		try {
			
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
