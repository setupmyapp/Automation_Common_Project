package com.mediacorp.smoke.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;


import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.GlobalVariables;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * 
 * @author Harish B R
 *
 */

public class Smoke_FunctionalityRWStepDefintition extends BaseTest {
	EventUtils eventUtils;
	
	SoftAssert softAssert;
	Utilities utilities;
	
    String parentSessionId = "";


	@Before("@smoke_RW")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		createNode("Before Scenario", "Launching the Application");
		System.out.println("Launching the Application");
		launchApplication();
		
	}

	@Given("^Verify the url for meWatch Product RW$")
	public void verify_the_url_for_mewatch_product_rw() {
		createNode("Given", "Verify the url for meWatch Product RW");
		
	}

	@When("^Verify the home page all the rails load properly RW$")
	public void verify_the_home_page_all_the_rails_load_properly_rw() {
		createNode("When", "Verify the home page all the rails load properly RW");
		try {
			eventUtils.scrollTillEnd(driver);
		} catch (Exception e) {

		}
		
			logStatus("PASS", "Smoke_003 Scroll through the homepage and check if all the rails load properly");
		
			logStatus("FAIL", "Smoke_003 Scroll through the homepage and check if all the rails load properly");

		
	}


			
	
		

	
	
	

	@After("@smoke_RW")
	public void tearDown(Scenario scenario) {
		try {
			
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
