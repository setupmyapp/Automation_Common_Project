package com.mediacorp.Mixpanel;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.MPUtilities;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/***
 * Name of Author :- Harish  B R
 * Developed By :- Ifocus Automation Team
 * Organization Name :- Ifocus Systec
 * Date :- 15-02-2024
 * Function Description :-  Lg-web-os -Tv Scenario
 */
public class LGWEBOS  extends BaseTest {
	EventUtils eventUtils;
	
	@Before("@Demo_LGWEBOSTV")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		eventUtils=new EventUtils();
		createNode("Before Scenario", "Launching the Application");
		System.out.println("Launching the Application");
		
		UiAutomator2Options options = new UiAutomator2Options();
		   
		options.setPlatformName("LGTV");
		options.setAutomationName("webOS");
		options.setDeviceName("LGWEBOSTV");
		options.setUdid("192.168.0.217");
		options.setCapability("deviceHost", "192.168.0.217");
		options.setCapability("appPackage", "mediacorp.prod");
		options.setCapability("appId", "mediacorp.prod");
		options.setCapability("chromedriverExecutable", "/Users/ifocus/Downloads/chromedriver");
		options.setCapability("rcMode", "rc");
		
	

		try {
				driver = new RemoteWebDriver(new URL("http://localhost:4723"), options);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 @Given("^As a Anonymous user Navigate to IDP page$")
	    public void anonymous_user_Navigate_to_IDP_page () {
		 createNode("Given", "As a Anonymous user Navigate to IDP page");
	 try {
		 eventUtils.sleep(30);
			WebElement ele=driver.findElement(By.xpath("//button[text()='Next']"));
			logStatus("INFO", "User clicked on Next Button onboaring page ");
			ele.click();
		} catch (Exception e) {
			logStatus("ERROR", "User unable clicked on Next Button onboaring page ");
		}
	 eventUtils.sleep(30);
		 
		try {
			WebElement ele=driver.findElement(By.xpath("//button[text()='Skip']"));
			logStatus("INFO", "User Clicking on Skip element onboaring page ");
			ele.click();
		} catch (Exception e) {
			logStatus("ERROR", "User unable clicked on Skip Button onboaring page ");
		}
		
		eventUtils.sleep(20);
		

		
		  try {
			WebElement ele=driver.findElement(By.xpath("//div[@class='Menu_menu-icon-wrapper__NHC2K']"));
			logStatus("PASS", "User Navigate to Home page");
			logStatus("INFO", "User clicked on hemberger tab from Home page");
			ele.click();
		} catch (Exception e) {
			logStatus("FAIL", "User Navigate to Home page");
			logStatus("ERROR", "User unable to clicked on hemberger tab from Home page");
		}
		
		eventUtils.sleep(10);   
		try {
			WebElement ele=driver.findElement(By.xpath("//li[@class='MenuIcon_menu-icon-search__pnobg  ']"));
			logStatus("INFO", "User clicked on Search tab in hemberger  page");
			ele.click();
		} catch (Exception e) {
			logStatus("ERROR", "User unable to clicked on Search tab in hemberger  page");
		}
		 
		eventUtils.sleep(10);
		try {
			driver.findElement(By.xpath("//button[text()='K']")).click();
			eventUtils.sleep(2);
			driver.findElement(By.xpath("//button[text()='I']")).click();
			eventUtils.sleep(2);
			driver.findElement(By.xpath("//button[text()='N']")).click();
			
			logStatus("INFO", "User enter the KIN content in the search ");
			
		
		} catch (Exception e) {
			logStatus("ERROR", "User unable to enter the KIN content in the search ");
		}
		
		
		eventUtils.sleep(2);
	 
		try {
			WebElement ele=driver.findElement(By.xpath("//button[@class='primary large  icon icon--leading icon--leading__keyboard--search  ']"));
			logStatus("INFO", "User Clicking on Search button in search page");
			ele.click();
		} catch (Exception e) {
			logStatus("ERROR", "User unable to Clicking on Search button in search page");
		}
		
		eventUtils.sleep(10);
		try {
			WebElement ele=driver.findElement(By.xpath("//div[@class='thumbnail  thumbnail--focused   ']"));
			logStatus("INFO", "User Clicking on Search results content in search page");
			ele.click();
		} catch (Exception e) {
			logStatus("ERROR", "User unable to Clicking on Search results content in search page");
		}
	 }
	 
	 @When("^User Selecting the Watch Now CTA from IDP page$")
	    public void user_Selecting_Watch_Now_CTA_from_IDP_page () {
		 createNode("When", "User Selecting the Watch Now CTA from IDP page");
		eventUtils.sleep(30);
		try {
			WebElement ele=driver.findElement(By.xpath("//button[@class='primary medium focus  icon icon--leading icon--leading__play DH1_action__6iJaA ']"));
			
			logStatus("PASS", "User landend on IDP Page ");
			logStatus("INFO", "User clicked on watch CTA  from IDP page");
			ele.click();
		} catch (Exception e) {
			logStatus("FAIL", "User landend on IDP Page ");
			logStatus("ERROR", "User unable to clicked on watch CTA  from IDP page");
		}
		
	 }
	 
	 @Then("^verify the Series content should start playing$")
	    public void verify_the_Series_content_should_start_playing  () {
		 createNode("Then", "verify the Series content should start playing");
		 
		eventUtils.sleep(50);
		try {
			driver.findElement(By.xpath("//div[@class='PlayerComponent_player-component__ze29x']"));
			logStatus("PASS", "User Navigate to palyback page");
			
		} catch (Exception e) {
			logStatus("FAIL", "User Navigate to palyback page");
		}
		eventUtils.sleep(30);
	 }
	 @After("@Demo_LGWEBOSTV")
		public void tearDown(Scenario scenario) {
			try {
				driver.quit();
			} catch (Exception e) {
				
			}
		}
	 
	
	
}





