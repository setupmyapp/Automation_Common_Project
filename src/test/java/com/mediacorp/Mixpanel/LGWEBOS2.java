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
public class LGWEBOS2  extends BaseTest {
	EventUtils eventUtils;
	
	@Before("@Demo_LGWEBOSTV2")
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
		options.setCapability("appPackage", "LGWEBOS");
		options.setCapability("appId", "com.h5-ppe.app");
		options.setCapability("chromedriverExecutable", "/Users/ifocus/Downloads/chromedriver");
		options.setCapability("rcMode", "rc");
		
	

		try {
				driver = new RemoteWebDriver(new URL("http://localhost:4723"), options);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void waitForElement(String locator)
	{
		
		for(int i=0;i<500;i++)
		{
		try {
			
			driver.findElement(By.xpath(locator));
			break;
			
		} catch (Exception e) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
	}
	
	
	 @Given("^As a Anonymous user Navigate to IDP page2$")
	    public void anonymous_user_Navigate_to_IDP_page2() {
		 createNode("Given", "As a Anonymous user Navigate to IDP page2");
	 try {
		 
		 eventUtils.sleep(10);
		 
		 String logo="//div[contains(@class,'user_profile')]/img[@alt='logo']";
		 waitForElement(logo);
			driver.findElement(By.xpath("//div[contains(@class,'user_profile')]/img[@alt='logo']"));
			logStatus("INFO", "Application Opened and user navigated to home page ");
			logStatus("PASS", "User Navigate to Home page");
		} catch (Exception e) {
			logStatus("ERROR", "Application not Opened and user unable to navigate to home page");
			logStatus("FAIL", "User Navigate to Home page");
		}
 
	 
	 //search\
	 String search="//div[text()='Search']/..";
	 waitForElement(search);
	 eventUtils.sleep(10);

		try {
			WebElement ele=driver.findElement(By.xpath(search));
			logStatus("INFO", "User Clicking on Search box ");
			ele.click();
			
		} catch (Exception e) {
			logStatus("ERROR", "User unable to Click on Search box ");
		}
	
	 //search box
	 String searchBox="//input[@type='text']";
	 
	
	 waitForElement(searchBox);
	 eventUtils.sleep(10);
	 	try {
			WebElement ele=driver.findElement(By.xpath(searchBox));
			logStatus("INFO", "User Clicking on Search box and searching for Bigboss");
			ele.click();
			eventUtils.sleep(15);
			ele.sendKeys("Bigg Boss"); 
			  
			eventUtils.sleep(15);
		} catch (Exception e) {
			logStatus("ERROR", "User unable to Click on Search box and search for Bigboss");
		}
		
		
		//search box
		 String searchResults="//p[contains(text(),'Top Results')]/..//div[text()='Bigg Boss Kannada']";
		 waitForElement(searchResults);
		
		
		
		try {
			WebElement ele=driver.findElement(By.xpath(searchResults));
			ele.click();
			
			try {
				ele.click();
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			logStatus("INFO", "User clicking on Results from search");
			
		} catch (Exception e) {
			logStatus("ERROR", "User unable to click on Results from search");
		}
		
		
		//search box
		 String ipdWatch="//button[text()='Watch']";
		 waitForElement(ipdWatch);
		
		
		
		try {
			WebElement ele=driver.findElement(By.xpath(ipdWatch));
			logStatus("INFO", "User navigated to IDP Page");
			logStatus("PASS", "User landend on IDP Page ");
		} catch (Exception e) {
			logStatus("ERROR", "User unable to navigate to IDP Page");
			logStatus("FAIL", "User landend on IDP Page ");
		}
		
		 
	 }
	 
	 
	 @When("^User Selecting the Watch Now CTA from IDP page2$")
	 public void user_Selecting_Watch_Now_CTA_from_IDP_page2() {
		 createNode("When", "User Selecting the Watch Now CTA from IDP page2");
		//search box
		 String ipdWatch="//button[text()='Watch']";
		 waitForElement(ipdWatch);
		 eventUtils.sleep(10);
		
		
		try {
			WebElement ele=driver.findElement(By.xpath(ipdWatch));
			logStatus("INFO", "User navigated to IDP Page and clicked on watch CTA");
			logStatus("PASS", "User Clicked on watch CTA ");
			ele.click();
		} catch (Exception e) {
			logStatus("ERROR", "User unable to navigate to IDP Page");
			logStatus("FAIL", "User unable to Click on watch CTA ");
		}
		
	 }
	 
	 @Then("^verify the Series content should start playing2$")
	    public void verify_the_Series_content_should_start_playing2() {
		 createNode("Then", "verify the Series content should start playing2");
		 
		//search box
		 String playerScreen="//div[@class='Player-Wrapper']//video";
		 waitForElement(playerScreen);
		
		 eventUtils.sleep(120);
		
		try {
			driver.findElement(By.xpath(playerScreen));
			logStatus("INFO", "User navigated to player screen");
			logStatus("PASS", "Player playing content");
			 
		} catch (Exception e) {
			logStatus("ERROR", "User unable to navigate to IDP Page");
			logStatus("FAIL", "Player unable to play content");
		}
	 }
	 
	 
	 @Then("^verify the Series content should start playing$")
	  
	 
	  
	 @After("@Demo_LGWEBOSTV2")
		public void tearDown(Scenario scenario) {
			try {
				driver.quit();
			} catch (Exception e) {
				
			}
		}
	 
	
	
}





