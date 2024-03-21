package com.mediacorp.TVstepDefinitions.meWatchTab;

import java.io.IOException;
import java.util.List;

import org.junit.experimental.theories.Theory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.mediacorp.pages.ContinueWatchingPage;
import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.MyListPage;
import com.mediacorp.pages.PlayerPage;
import com.mediacorp.pages.PremiumPage;
import com.mediacorp.pages.SearchPage;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PremiumUserChecksStepDefination extends BaseTest {
	EventUtils eventUtils;
	LoginPage loginPage;
	HomePage homePage;
	PlayerPage playerPage;
	SoftAssert softAssert;
	Utilities utilities;
	MyListPage mylist;
	String searchContent;
	String searchedContentXpath;
	ContinueWatchingPage cw;
	SearchPage searchPage;
	PremiumPage premiumPage;

	@Before("@premiumuserchecks")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		launchApplication();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		eventUtils = new EventUtils();
		playerPage = new PlayerPage(driver);
		softAssert = new SoftAssert();
		searchPage = new SearchPage(driver);
		utilities = new Utilities();
		mylist = new MyListPage(driver);
		cw = new ContinueWatchingPage(driver);
		premiumPage = new PremiumPage(driver);
	}

	@Given("^Verify the CTA of premium shows should be watch for anonymous users$")
	public void verify_the_cta_of_premium_shows_should_be_watch_for_anonymous_users() throws Throwable {
		createNode("And", "Verify the CTA of premium shows should be 'watch' for anonymous users");
		searchContent = utilities.getDataFromPropertyFile("SearchContents", "PremiumShowContent");
		searchPage.searchAndClickTheContent(searchContent, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(5);
		} else {
			eventUtils.sleep(5);
		}

		if (eventUtils.waitUntilElementIsVisible(driver, playerPage.WatchCTA, 20)) {
			logStatus("pass", "TC_sanity_0181 The  CTA of premium shows is 'watch' for anonymous users");
		} else {
			logStatus("fail", "TC_sanity_0181 The CTA of premium shows is not 'watch' for anonymous users");
			softAssert.assertTrue(false, "TC_sanity_0181 The CTA of premium shows is not 'watch' for anonymous users");
		}

	}

	@Then("^Verify the CTA of premium shows should be subscribe for signed in users$")
	public void verify_the_cta_of_premium_shows_should_be_subscribe_for_signed_in_users() throws Throwable {
		createNode("Then", "Verify the CTA of premium shows should be 'subscribe' for signed in users");
		playerPage.clickWatchOrResumeCTA(test);
		String email = utilities.getCredentialsFromPropertyFile("FreeUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("FreeUserPassword");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			loginPage.loginToTheApplication(email, password, test);
			eventUtils.sleep(5);
			searchContent = utilities.getDataFromPropertyFile("SearchContents", "PremiumShowContent");
			searchPage.searchAndClickTheContent(searchContent, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(5);
			} else {
				eventUtils.sleep(5);
			}

		} else {
			premiumPage.clickOnSignInButtonBesideSubscibeButton(test);
			loginPage.clickSignInMeConnectButton(test);

			loginPage.enterEmail(email, test);

			loginPage.enterPassword(password, test);
			eventUtils.sleep(2);
			loginPage.clickSignInButton(test);
			eventUtils.sleep(3);
		}

		if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.SubscibeButtonOnIDP, 20)) {
			logStatus("pass", "TC_sanity_0182 The  CTA of premium shows is 'subscribe' for signedin users");
		} else {
			logStatus("fail", "TC_sanity_0182 The CTA of premium shows is not 'subscribe' for signedin users");
			softAssert.assertTrue(false,
					"TC_sanity_0182 The CTA of premium shows is not 'subscribe' for signedin users");

		}

	}

	@Then("Verify the lock symbol is displayed for all the premium show contents")
	public void verify_the_lock_symbol_is_displayed_for_all_the_premium_show_contents() {
		createNode("Then", "Verify the 'lock' symbol is displayed for all the premium show contents");
		
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			// eventUtils.navigateBack(driver, test);
		}
		searchContent = utilities.getDataFromPropertyFile("SearchContents", "PremiumShowContent");
		eventUtils.sleep(2);
		searchPage.enterAndSearchTheContent(searchContent, test);
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.LockSymbol, 20)) {
			logStatus("pass", "TC_sanity_0178 The 'lock' symbol is displayed for the premium show contents");
		} else {
			logStatus("fail", "TC_sanity_0178 The 'lock' symbol is not displayed for the premium show contents");
			softAssert.assertTrue(false,
					"TC_sanity_0178 The 'lock' symbol is not displayed for the premium show contents");
		}
	}

	@Then("Verify the lock symbol is displayed for all the premium movie contents")
	public void verify_the_lock_symbol_is_displayed_for_all_the_premium_movie_contents() {
		createNode("And", "Verify the 'lock' symbol is displayed for all the premium movie contents");
		
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}
		searchContent = utilities.getDataFromPropertyFile("SearchContents", "PremiumMovieContent");
		eventUtils.sleep(2);
		searchPage.enterAndSearchTheContent(searchContent, test);
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.LockSymbol, 20)) {
			logStatus("pass", "TC_sanity_0179 The 'lock' symbol is displayed for the movie  contents");
		} else {
			logStatus("fail", "TC_sanity_0179 The 'lock' symbol is not displayed for the premium movie contents");
			softAssert.assertTrue(false,
					"TC_sanity_0179 The 'lock' symbol is not displayed for the premium movie contents");
		}
	}

	@Then("Verify the lock symbol is displayed for all the premium Live channel")
	public void verify_the_lock_symbol_is_displayed_for_all_the_premium_live_channel() {
		createNode("When", "Verify the 'lock' symbol is displayed for all the premium Live channel");
		
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}
		eventUtils.sleep(2);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			homePage.clickOnPremiumTab(test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.sleep(2);
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(3);
			homePage.clickOnPremiumTab(test);
		}
//not foR web and mobileRW
		homePage.ClickOnMainButton(test);

		eventUtils.mouseHoverToElement(driver, premiumPage.LiveTrayPremiumContent, 20, test);
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.LockSymbol, 20)) {
			logStatus("pass", "TC_sanity_0180 The 'lock' symbol is displayed for the premium live contents");
			softAssert.assertTrue(false,
					"TC_sanity_0180 The 'lock' symbol is not displayed for the premium live contents");
		}

	}

	@After("@premiumuserchecks")
	public void tearDown() {
		try {

			homePage.logout(test);
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
