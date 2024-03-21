package com.mediacorp.TVstepDefinitions.meWatchTab;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.mediacorp.pages.AccountPage;
import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.LiveTvPage;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.PlayerPage;
import com.mediacorp.pages.PremiumPage;
import com.mediacorp.pages.SearchPage;
import com.mediacorp.pages.SettingsPage;
import com.mediacorp.pages.SubscriptionPage;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.Utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageFunctionalityStepDefination extends BaseTest {
	EventUtils eventUtils;
	LoginPage loginPage;
	HomePage homePage;
	PlayerPage playerPage;
	SoftAssert softAssert;
	Utilities utilities;
	SearchPage searchPage;
	SettingsPage settingsPage;
	PremiumPage premiumPage;
	SubscriptionPage subscriptionPage;
	AccountPage accountPage;

	String searchContent;
	String searchedContentXpath;
	int Err_TC_sanity_0172 = 0;

	@Before("@homepage")
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
		settingsPage = new SettingsPage(driver);
		premiumPage = new PremiumPage(driver);
		subscriptionPage = new SubscriptionPage(driver);
		accountPage = new AccountPage(driver);

	}

	@Given("^Verify the UI Of Home Page for Anonymous/non-entitled user /SVOD User$")
	public void verify_the_ui_of_home_page_for_anonymousnonentitled_user_svod_user() throws Throwable {
		createNode("Given", "Verify the UI Of Home Page for Anonymous/non-entitled user /SVOD User");

		String email = utilities.getCredentialsFromPropertyFile("PremiumUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("PremiumUserPassword");
		loginPage.loginToTheApplication(email, password, test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.MeWatchLogo, 20)) {
			logStatus("pass",
					"TC_sanity_0171 the UI Of Home Page for Anonymous/non-entitled user /SVOD User is validated");
		} else
			logStatus("fail",
					"TC_sanity_0171 the UI Of Home Page for Anonymous/non-entitled user /SVOD User can't be validated");
		softAssert.assertTrue(false,
				"TC_sanity_0171 the UI Of Home Page for Anonymous/non-entitled user /SVOD User can't be validated");
	}

	@Then("^Verify the UI and Metadata of All types of Rails$")
	public void verify_the_ui_and_metadata_of_all_types_of_rails() throws Throwable {
		createNode("Then", "Verify the UI and Metadata of All types of Rails");
		// eventUtils.sleep(5);
		// eventUtils.scrollDownBy500(driver);
		if (eventUtils.waitUntilElementIsVisible(driver, homePage.MeWatchLogo, 20)) {
			eventUtils.verticalSwipe(driver,  homePage.FeaturedRail, 1);
			if (eventUtils.waitUntilElementIsVisible(driver, homePage.FeaturedRail, 20)) {

				List<WebElement> featuredrailcontents = driver.findElements(homePage.FeaturedRailContentMetaData);

				int totalcontents0ffeaturedrail = featuredrailcontents.size();
				
				for (int i = 0; i < 1; i++) {

					if (eventUtils.waitUntilElementIsVisible(driver, featuredrailcontents.get(i), 20)) {

						logStatus("info", "the meta data of featured rail is present");

					} else {

						logStatus("error", "the meta data of featured rail is not present");
						Err_TC_sanity_0172++;
					}
				}
			} else {
				logStatus("error", " the  featured rail  is not present");
			}
			eventUtils.verticalSwipe(driver, homePage.KoreanMovieRail, 2);
			if (eventUtils.waitUntilElementIsVisible(driver, homePage.KoreanMovieRail, 20)) {

				List<WebElement> koreanmovierailcontents = driver.findElements(homePage.KoreanMovieRailContentMetaData);

				int totalcontents0fkoreanmovierail = koreanmovierailcontents.size();
				for (int i = 0; i < 1; i++) {

					if (eventUtils.waitUntilElementIsVisible(driver, koreanmovierailcontents.get(i), 20)) {

						logStatus("info", "the meta data of korean movie rail is present");

					} else {

						logStatus("error", "the meta data of korean movie rail is not present");
						Err_TC_sanity_0172++;
					}
				}
			} else {
				logStatus("error", " TC_sanity_0172 the  korean movie rail  is not present");
			}
			 eventUtils.verticalSwipe(driver, homePage.LiveTvRail, 1);
			if (eventUtils.waitUntilElementIsVisible(driver, homePage.LiveTvRail, 20)) {

				List<WebElement> livetvrailcontentstitle = driver.findElements((homePage.LiveTvRailContentTitle));
				List<WebElement> livetvrailcontentstime = driver.findElements((homePage.LiveTvRailContentTitle));

				int totalcontenttitles0flivetv = livetvrailcontentstitle.size();
				int totalcontenttime0flivetv = livetvrailcontentstime.size();

				for (int i = 0; i < 1; i++) {

					if (eventUtils.waitUntilElementIsVisible(driver, livetvrailcontentstitle.get(i), 20)) {

						logStatus("info", "the meta data of live tv  rail is present");

					} else {

						logStatus("error", "the meta data of   live tv rail is not present");
						Err_TC_sanity_0172++;
					}
					if (eventUtils.waitUntilElementIsVisible(driver, livetvrailcontentstime.get(i), 20)) {

						logStatus("info", "the meta data of live tv  rail is present");

					} else {

						logStatus("error", "the meta data of live tv  rail is not present");
						Err_TC_sanity_0172++;
					}

				}
			} else {
				logStatus("error", " TC_sanity_0172 the  live tv  rail  is not present");
			}

			if (Err_TC_sanity_0172 == 0) {
				logStatus("pass", " TC_sanity_0172 metadata of all rails is present");

			} else {
				logStatus("fail", " TC_sanity_0172 the  metadata of all rails is is not present");
				softAssert.assertTrue(false, "TC_sanity_0172 the  metadata of all rails is  not present");
			}
		} else {
			logStatus("fail", "TC_sanity_0172 HomePage is not visible");
			softAssert.assertTrue(false, "TC_sanity_0172 HomePage is not visible");
		}

	}

	@After("@homepage")
	public void tearDown() {
		try {
			homePage.logout(test);
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
