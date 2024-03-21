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
import com.mediacorp.pages.SearchPage;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
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

public class ContinueWatchingFunctionalityStepDefination extends BaseTest {
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

	@Before("@continuewatching")
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

		eventUtils.terminateApp(driver);

		eventUtils.activateApp(driver);
	}

	int playerpageduartion;
	int continuewatchingduration;
	String recentlyplayedcontent;
	String firstcontentincw;

	@Then("^Verify that continue watching rail should not display for new user/signed In user when user has not watched any show/series/movies$")
	public void verify_that_continue_watching_rail_should_not_display_for_new_usersigned_in_user_when_user_has_not_watched_any_showseriesmovies()
			throws Throwable {
		createNode("Then",
				"Verify that continue watching rail should not display for new user/signed In user when user has not watched any show/series/movies");
		if (Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")
				|| (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp"))) {
			homePage.clickOnHamburgerMenu(test);
			loginPage.clickSignInLink(test);
			loginPage.clickCreateNewCTA(test);

		} else {
			loginPage.clickSignInLink(test);
			loginPage.clickCreateNewCTA(test);
		}
		String email = utilities.generateEmailid();
		String password = utilities.generateRandomPassword();
		loginPage.enterEmail(email, test);
		loginPage.enterPassword(password, test);
		loginPage.enterConfirmPassword(password, test);

		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			loginPage.clickNextButton(test);
		}
		loginPage.enterFirstName("ab", test);
		loginPage.enterLastName("cd", test);
		loginPage.selectGender("Male", test);

		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			loginPage.clickNextButton(test);
		}
		loginPage.enterDOB("12/12/1995", test);

		loginPage.selectTnC_CheckBox(test);
		eventUtils.hideKeyboard(driver);
		loginPage.clickCreateAccountButton(test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			loginPage.clickSkipButton(test);
		}

		eventUtils.clickOnElement(driver, loginPage.WillDoLaterButton, "No, Will do it later", 10, test);
		eventUtils.sleep(2);
//		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
//			homePage.clickCloseButton(test);
//		}
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, homePage.MeWatchLogo, 20)) {
			if (!eventUtils.waitUntilElementIsVisible(driver, cw.ContinueWatchingRail, 20)) {

				logStatus("pass", "TC_sanity_0080 continue watching rail is not displayed for new user");
			} else {
				logStatus("fail", "TC_sanity_0080 continue watching rail is  displayed for new user");
				softAssert.assertTrue(false, "TC_sanity_0080 continue watching rail is  displayed for new user");
			}
		} else {
			logStatus("fail", "TC_sanity_0080 home page is not visible");
			softAssert.assertTrue(false, "TC_sanity_0080 home page is not visible");
		}

	}

	@And("^Verify the Continue Watching item metadata and Progress bar in Continue Watching Rail after watching series/movies for both anoymous user/signed In user$")
	public void verify_the_continue_watching_item_metadata_and_progress_bar_in_continue_watching_rail_after_watching_seriesmovies_for_both_anoymous_usersigned_in_user()
			throws Throwable {
		createNode("And",
				"Verify the Continue Watching item metadata and Progress bar in Continue Watching Rail after watching series/movies for both anoymous user/signed In user");
		searchContent = utilities.getDataFromPropertyFile("SearchContents", "FreeContent2");

		searchPage.searchAndClickTheContent(searchContent, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		} else {
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		}
		eventUtils.sleep(5);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		if (eventUtils.waitUntilElementIsPresent(driver, playerPage.PlaybackScreen, 20)) {

			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30)) {
				eventUtils.sleep(5);
				playerPage.scrubPlayerTillHalf(test);
				playerPage.waitUntilAdCompleted(test);
				playerPage.waitUntilAdCompleted(test);
				playerPage.waitUntilAdCompleted(test);
				playerPage.pauseTheContent(test);

				logStatus("info", "able to scrub the content");
				logStatus("info", "player screen is present");
			} else {
				logStatus("error", "not able to scrub content");
			}

		} else {
			logStatus("error", "playback screen is not present");
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, cw.ProgressBar, 20)
				&& eventUtils.waitUntilElementIsVisible(driver, cw.MetaData, 20)) {
			logStatus("pass", "TC_sanity_0081 metadata and progress bar is displayed for new user");
		} else {
			logStatus("fail", "TC_sanity_0081 metadata and progress bar is not displayed for new user");
			softAssert.assertTrue(false, "TC_sanity_0081 metadata and progress bar is not displayed for new user");
		}
	}

	@Then("^Verify video is played at exact duration at last stopped, when user played from continue watching rail$")
	public void verify_video_is_played_at_exact_duration_at_last_stopped_when_user_played_from_continue_watching_rail()
			throws Throwable {
		createNode("Then",
				"Verify video is played at exact duration at last stopped, when user played from continue watching rail");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			searchedContentXpath = "//*[text()='" + searchContent + "']";
		} else {
			searchedContentXpath = "//android.widget.TextView[@text='" + searchContent + "']";
		}

		eventUtils.clickOnElement(driver, By.xpath(searchedContentXpath), "searchedContentXpath", 20, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		} else {
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		}
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);

		if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30)) {
			playerpageduartion = playerPage.contentDuration(test);
		} else
			logStatus("fail", " TC_sanity_0082 playback screen is not visible");
		softAssert.assertTrue(false, "TC_sanity_0082 playback screen is not visible");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}
		eventUtils.clickOnElement(driver, By.xpath(searchedContentXpath), "searchedContentXpath", 20, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(5);
			playerPage.clickWatchOrResumeCTA(test);
		} else {
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		}
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30)) {
			continuewatchingduration = playerPage.contentDuration(test);
		} else
			logStatus("fail", "TC_sanity_0082 playback screen is not visible");
		softAssert.assertTrue(false, "TC_sanity_0082 playback screen is not visible");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}
		if (playerpageduartion == continuewatchingduration || playerpageduartion > continuewatchingduration - 4) {
			logStatus("pass",
					"TC_sanity_0082 video is played at exact duration at last stopped, when user played from continue watching rail");
		} else {
			logStatus("fail",
					"TC_sanity_0082 video is not played at exact duration at last stopped, when user played from continue watching rail");
			softAssert.assertTrue(false,
					"TC_sanity_0082 video is not played at exact duration at last stopped, when user played from continue watching rail");
		}
	}

	@And("^Verify recently watched should display first in the rail$")

	public void verify_recently_watched_should_display_first_in_the_rail() throws Throwable {
		createNode("And", "Verify recently watched should display first in the rail");
//		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
//			searchedContentXpath = "//*[text()='" + searchContent + "']";
//		} else {
//			searchedContentXpath = "//android.widget.TextView[@text='" + searchContent + "']";
//		}
		eventUtils.clickOnElement(driver, By.xpath(searchedContentXpath), "searchedContentXpath", 20, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		} else {
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		}

		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);

		eventUtils.mouseHoverToElement(driver, playerPage.PlaybackScreen, 20, test);
		eventUtils.mouseHoverToElement(driver, By.xpath(searchedContentXpath), 20, test);
		String recentlyplayedcontent = eventUtils.getTextOfWebelement(driver, By.xpath(searchedContentXpath), 20, test);

		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}

		eventUtils.mouseHoverToElement(driver, By.xpath(searchedContentXpath), 20, test);
		String firstcontentincw = eventUtils.getTextOfWebelement(driver, By.xpath(searchedContentXpath), 20, test);

		if (recentlyplayedcontent.equals(firstcontentincw)) {
			logStatus("pass", "TC_sanity_0084 recently watched is  displayed first in the rail");
		} else {
			logStatus("fail", "TC_sanity_0084 recently watched  is not  displayed first in the rail");
			softAssert.assertTrue(false, "TC_sanity_0084 recently watched  is not  displayed first in the rail");
		}
	}

	@Then("^Verify after watching completely, item should be removed  from the continue watching rail$")
	public void verify_after_watching_completely_item_should_be_removed_from_the_continue_watching_rail()
			throws Throwable {
		createNode("Then", "Verify after watching completely, item should be removed  from the continue watching rail");
//		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
//			searchedContentXpath = "//*[text()='" + searchContent + "']";
//		} else {
//			searchedContentXpath = "//android.widget.TextView[@text='" + searchContent + "']";
//		}
		eventUtils.clickOnElement(driver, By.xpath(searchedContentXpath), "searchedContentXpath", 20, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		} else {
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		}
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		playerPage.waitUntilAdCompleted(test);
		if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 20)) {
			playerPage.scrubPlayerTillEnd(test);
			playerPage.waitUntilAdCompleted(test);
			eventUtils.sleep(2);
		} else {
			logStatus("fail", "TC_sanity_0083 playback screen is not visible");
			softAssert.assertTrue(false, "TC_sanity_0083 playback screen is not visible");

		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
			eventUtils.navigateBack(driver, test);
		}
		if (!eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 20)) {

			logStatus("pass", "TC_sanity_0083 continue watching rail is removed");
		} else {
			logStatus("fail", "TC_sanity_0083 continue watching rail is not removed ");
			softAssert.assertTrue(false, "TC_sanity_0083 continue watching rail is not removed ");

		}
	}

	@After("@continuewatching")
	public void tearDown() {
		try {
			if (!Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				homePage.logout(test);
			}
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
