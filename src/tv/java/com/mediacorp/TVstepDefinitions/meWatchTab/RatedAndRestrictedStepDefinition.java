package com.mediacorp.TVstepDefinitions.meWatchTab;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.mediacorp.pages.AccountPage;
import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.IDP_Page;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.PlayerPage;
import com.mediacorp.pages.PremiumPage;
import com.mediacorp.pages.SearchPage;
import com.mediacorp.pages.TrayInfoPage;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.TV_EventUtils;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RatedAndRestrictedStepDefinition extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TrayInfoPage trayInfoPage;
	IDP_Page idpPage;
	EventUtils eventUtils;
	Utilities utilities;
	SearchPage searchPage;
	PlayerPage playerPage;
	String content;
	SoftAssert softAssert;
	PremiumPage premiumPage;
	By signInPopButton;
	TV_EventUtils tvEventsUtils;

	String allRatedContents[] = { "GFreeSeries", "PGFreeSeries", "PG13FreeSeries", "NC16FreeSeries", "M18FreeSeries",
			"R21FreeSeries" };

	@Before("@rated")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		launchApplication();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		trayInfoPage = new TrayInfoPage(driver);
		idpPage = new IDP_Page(driver);
		eventUtils = new EventUtils();
		utilities = new Utilities();
		searchPage = new SearchPage(driver);
		playerPage = new PlayerPage(driver);
		softAssert = new SoftAssert();
		premiumPage = new PremiumPage(driver);
		tvEventsUtils = new TV_EventUtils();
	}

	@Given("^User lands on Home Page and  searches the (.+) content$")
	public void userLandsOnHomePageAndSearchesTheContent(String ratedcontent) {
		createNode("Given", "User lands on Home Page and  searches the " + ratedcontent + " content");

		homePage.clickOnHamburgerMenu(test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.MeWatchLogo, 20)) {
			logStatus("info", "Application Launched successfully");
		} else {
			logStatus("error", "Application Launch Unsuccessful");
		}

		homePage.logout(test);

		content = utilities.getDataFromPropertyFile("SearchContents", ratedcontent);

		searchPage.searchAndClickTheContent(content, test);

		if (ratedcontent.toLowerCase().contains("free")) {
			signInPopButton = loginPage.SignInButtonPopUp;
		} else if (ratedcontent.toLowerCase().contains("premium")) {
			signInPopButton = premiumPage.SignInButtonBesideSubscibeButton;
		}

	}

	@When("^User selects the content sign in pop up should display$")
	public void userSelectsTheContentSignInPopUpShouldDisplay() {
		createNode("When", "User selects the content sign in pop up should display");

		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(3);
		} else {
			eventUtils.sleep(3);
		}

		if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchCTA, 20)) {
//		
			eventUtils.scrollToParticularElement(driver, playerPage.WatchCTA);
			playerPage.clickWatchCta(test);

//			if (eventUtils.waitUntilElementIsVisible(driver, signInPopButton, 10)) {
			if (eventUtils.waitUntilElementIsVisible(driver, signInPopButton, 10)) {
				logStatus("info", "Sign in pop displaying after clicking on Watch Now CTA for the content:-" + content);
				logStatus("pass",
						"TC_sanity_0155	Verify that when an anonymous user tries to watch free rated contents(Nc16\\M18), the \"free rated sign in required modal\" should  be displayed");
				eventUtils.clickOnElement(driver, signInPopButton, "SignInPopBUtton", 20, test);
			} else {
				logStatus("fail",
						"TC_sanity_0155	Verify that when an anonymous user tries to watch free rated contents(Nc16\\M18), the \"free rated sign in required modal\" should  be displayed");
				softAssert.assertTrue(false,
						"TC_sanity_0155 Verify that when an anonymous user tries to watch free rated contents(Nc16\\M18), the \"free rated sign in required modal\" should  be displayed");
			}

		} else {
			logStatus("error", "Watch CTA not displaying for the " + content);
			logStatus("fail",
					"TC_sanity_0155	Verify that when an anonymous user tries to watch free rated contents(Nc16\\M18), the \"free rated sign in required modal\" should  be displayed");
			softAssert.assertTrue(false,
					"TC_sanity_0155	Verify that when an anonymous user tries to watch free rated contents(Nc16\\M18), the \"free rated sign in required modal\" should  be displayed");
		}

	}

	@And("^User logins to the application$")
	public void userLoginsToTheApplication() {
		createNode("And", "User logins to the application");
//
		String userName = utilities.getCredentialsFromPropertyFile("FreeUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("FreeUserPassword");
//

		LoginPage loginPageWeb = new LoginPage(driver);

		loginPageWeb.loginToTV(userName, password, test);

	}

	@Then("^User should be able to play the N16 or M18 content when the user age greater than 16 years$")
	public void userShouldBeAbleToPlayTheN16OrM18ContentWhenTheUserAgeGreaterThan16Years() {
		createNode("Then",
				"User should be able to play the N16 or M18 content when the user age greater than 16 years");
		playerPage.waitUntilAdCompleted(test);
		if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30)) {
			logStatus("info", "user able to play the content:-" + content);
			logStatus("pass",
					"TC_sanity_0157	Verify that the user should be able to watch the Free Rated contents(NC16\\M18) after signing in from the \"Free Rated sign in required modal\"  if the user's age >=16 years for NC16 contents and  age >=18 for M18 contents ");
		} else {
			logStatus("error", "user able to play the content:-" + content);
			logStatus("fail",
					"TC_sanity_0157	Verify that the user should be able to watch the Free Rated contents(NC16\\M18) after signing in from the \"Free Rated sign in required modal\"  if the user's age >=16 years for NC16 contents and  age >=18 for M18 contents ");
			softAssert.assertTrue(false,
					"TC_sanity_0157	Verify that the user should be able to watch the Free Rated contents(NC16\\M18) after signing in from the \"Free Rated sign in required modal\"  if the user's age >=16 years for NC16 contents and  age >=18 for M18 contents ");
		}

	}

	@Given("^User logins to the application and searches the R21 Series content$")
	public void userLoginsToTheApplicationAndSearchesTheR21SeriesContent() {
		createNode("Given", "User logins to the application and searches the R21 Series content");

		String UserName = utilities.getCredentialsFromPropertyFile("PremiumUserEmail");
		String Password = utilities.getCredentialsFromPropertyFile("PremiumUserPassword");

		String R21Content = utilities.getDataFromPropertyFile("SearchContents", "R21PremiumSeries");

		homePage.clickOnHamburgerMenu(test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 3)) {
			AccountPage accountPage = new AccountPage(driver);
			homePage.clickOnProfileLogo(test);
			String loggedInEmail = eventUtils.getTextOfWebelement(driver, accountPage.email, 5, test);
			if (loggedInEmail.equalsIgnoreCase(UserName)) {
				tvEventsUtils.backButton(test);
				eventUtils.sleep(3);
				tvEventsUtils.backButton(test);
			} else {
				homePage.logout(test);
				loginPage.loginToTV(UserName, Password, test);
			}
		} else {
			loginPage.loginToTV(UserName, Password, test);
		}

		homePage.clickOnHamburgerMenu(test);

		searchPage.searchAndClickTheContent(R21Content, test);

	}

	@When("^User access the content should ask for the pin$")
	public void userAccessTheContentShouldAskForThePin() {
		createNode("When", "User access the content should ask for the pin");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(3);
		} else {
			eventUtils.sleep(3);
		}
		if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {

			eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);

			playerPage.clickWatchOrResumeCTA(test);

			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ContentRatedTitle, 20)) {
				logStatus("info", "Control Pin is asking by accessing R21 content");
				logStatus("pass",
						"TC_sanity_0159	Verify that the user is always asked to enter the PIN on trying to Watch R21 contents");
			} else {
				logStatus("warning", "Control Pin is not asking by accessing R21 content");
				logStatus("fail",
						"TC_sanity_0159	Verify that the user is always asked to enter the PIN on trying to Watch R21 contents");
				softAssert.assertTrue(false,
						"TC_sanity_0159	Verify that the user is always asked to enter the PIN on trying to Watch R21 contents");
			}

		}
	}

	@Then("^User should be able to access the content after entering the pin$")
	public void userShouldBeAbleToAccessTheContentAfterEnteringThePin() {
		createNode("Then", "User should be able to access the content after entering the pin");

		if (playerPage.enterAccessPin("123123", test)) {
			logStatus("info", "User able to enter the pin");
			playerPage.clickProceedButton(test);
			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30)) {
				logStatus("info", "User able to access the content after entering the pin");
				logStatus("pass",
						"TC_sanity_0162	Verify that the user is able to successfully able to watch the R21 contents after entering the correct PIN");
			} else {
				logStatus("warning", "User unable to access the content after entering the pin");
				logStatus("fail",
						"TC_sanity_0162	Verify that the user is able to successfully able to watch the R21 contents after entering the correct PIN");
				softAssert.assertTrue(false,
						"TC_sanity_0162	Verify that the user is able to successfully able to watch the R21 contents after entering the correct PIN");
			}
		} else {
			logStatus("warning", "User unable to enter the pin");
			logStatus("fail",
					"TC_sanity_0162	Verify that the user is able to successfully able to watch the R21 contents after entering the correct PIN");
			softAssert.assertTrue(false,
					"TC_sanity_0162	Verify that the user is able to successfully able to watch the R21 contents after entering the correct PIN");
		}
	}

	@Given("^User logins to the application which set with (.+) account$")
	public void userLoginsToTheApplicationWhichSetWithAccount(String rated) {
		createNode("Given", "User logins to the application which set with " + rated);

		String UserName = "";
		String Password = "";

		switch (rated) {
		case "G":
			UserName = utilities.getCredentialsFromPropertyFile("GFreeUserEmail");
			Password = utilities.getCredentialsFromPropertyFile("GFreeUserPassword");
			break;
		case "PG":
			UserName = utilities.getCredentialsFromPropertyFile("PGFreeUserEmail");
			Password = utilities.getCredentialsFromPropertyFile("PGFreeUserPassword");
			break;
		case "PG13":
			UserName = utilities.getCredentialsFromPropertyFile("PG13FreeUserEmail");
			Password = utilities.getCredentialsFromPropertyFile("PG13FreeUserPassword");
			break;
		case "NC16":
			UserName = utilities.getCredentialsFromPropertyFile("NC16FreeUserEmail");
			Password = utilities.getCredentialsFromPropertyFile("NC16FreeUserPassword");
			break;

		case "NoPin":
			UserName = utilities.getCredentialsFromPropertyFile("FreeUserNoPinEmail");
			Password = utilities.getCredentialsFromPropertyFile("FreeUserNoPinPassword");
			break;

		case "AgeLessthan16":
			UserName = utilities.getCredentialsFromPropertyFile("FreeUserAge14Email");
			Password = utilities.getCredentialsFromPropertyFile("FreeUserAge14Password");
			break;
		default:
			break;
		}

		homePage.clickOnHamburgerMenu(test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 3)) {
			AccountPage accountPage = new AccountPage(driver);
			homePage.clickOnProfileLogo(test);
			String loggedInEmail = eventUtils.getTextOfWebelement(driver, accountPage.email, 5, test);
			if (loggedInEmail.equalsIgnoreCase(UserName)) {
				tvEventsUtils.backButton(test);
				eventUtils.sleep(3);
				tvEventsUtils.backButton(test);
			} else {
				tvEventsUtils.backButton(test);
				eventUtils.sleep(3);
				tvEventsUtils.backButton(test);
				homePage.logout(test);
				loginPage.loginToTV(UserName, Password, test);
			}
		} else {
			loginPage.loginToTV(UserName, Password, test);
		}

		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 20)) {
				logStatus("info", "login successfull");
			} else {
				logStatus("warning", "login UnSuccessful");
			}
		} else {
			homePage.clickOnHamburgerMenu(test);
			if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 20)) {
				logStatus("info", "login successfull");
			} else {
				logStatus("warning", "login UnSuccessful");
			}
		}

	}

	@When("^User access the contents below NC16 level should play the content$")
	public void userAccessTheContentsBelowNC16LevelShouldPlayTheContent() {
		createNode("When", "User access the contents below NC16 level should play the content");

		String belowRatedContents[] = { "GFreeSeries", "PGFreeSeries", "PG13FreeSeries" };
		boolean isContentRated = true;
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		for (int i = 0; i < belowRatedContents.length; i++) {
			content = utilities.getDataFromPropertyFile("SearchContents", belowRatedContents[i]);

			searchPage.searchAndClickTheContent(content, test);

			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(3);
			} else {
				eventUtils.sleep(3);
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
//				eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);
				playerPage.clickWatchOrResumeCTA(test);
				playerPage.waitUntilAdCompleted(test);
				playerPage.waitUntilAdCompleted(test);
				playerPage.waitUntilAdCompleted(test);
				if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 10)) {
					logStatus("info",
							"Pin Restriction is not displaying for the rated content:-" + belowRatedContents[i]);
				} else if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ContentRatedTitle, 10)) {
					logStatus("error", "Pin Restriction is displaying for the rated content:-" + belowRatedContents[i]);
				} else {
					logStatus("error", "Unable to play the content" + belowRatedContents[i]);
					isContentRated = false;
				}
			}

			for (int j = 0; j < 5; j++) {

				if (eventUtils.waitUntilElementIsVisible(driver, homePage.HamburgerMenu, 2)) {
					homePage.clickOnHamburgerMenu(test);
					break;
				} else {
					tvEventsUtils.backButton(test);
				}

			}
		}

		if (isContentRated) {
			logStatus("pass",
					"TC_sanity_0164	Verify that if the user has restricted the access to any age rating from account settings(for example NC16), then the user should not be asked to enter the PIN on trying to watch any contents below the restricted age rating(eg: G,PG and Pg13)");
		} else {
			logStatus("fail",
					"TC_sanity_0164	Verify that if the user has restricted the access to any age rating from account settings(for example NC16), then the user should not be asked to enter the PIN on trying to watch any contents below the restricted age rating(eg: G,PG and Pg13)");
			softAssert.assertTrue(false,
					"TC_sanity_0164 Verify that if the user has restricted the access to any age rating from account settings(for example NC16), then the user should not be asked to enter the PIN on trying to watch any contents below the restricted age rating(eg: G,PG and Pg13)");
		}

	}

	@And("^user access the contents above NC16 level should ask for pin$")
	public void userAccessTheContentsAbsoveNC16LevelShouldAskForPin() {
		createNode("And", "user access the contents above NC16 level should ask for pin");

		String aboveRatedContents[] = { "NC16FreeSeries", "M18FreeSeries" };
		boolean isContentRated = true;
//		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
//			eventUtils.navigateBack(driver, test);
//		}
		for (int i = 0; i < aboveRatedContents.length; i++) {
			content = utilities.getDataFromPropertyFile("SearchContents", aboveRatedContents[i]);

			searchPage.searchAndClickTheContent(content, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(3);
			} else {
				eventUtils.sleep(3);
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
				// eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);
				playerPage.clickWatchOrResumeCTA(test);
				if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ContentRatedTitle, 10)) {
					logStatus("info", "Pin Restriction is displaying for the rated content:-" + aboveRatedContents[i]);
				} else {
					isContentRated = false;
					logStatus("error",
							"Pin Restriction is not displaying for the rated content:-" + aboveRatedContents[i]);
				}
			}
			if (!eventUtils.waitUntilElementIsPresent(driver, homePage.SearchButton, 3)) {
				eventUtils.hideKeyboard(driver);
				eventUtils.navigateBack(driver);
			}
		}

		if (isContentRated) {
			logStatus("pass",
					"TC_sanity_0163	Verify that if the user has restricted the access to any age rating from account settings(for example NC 16), then the user should be asked to enter the PIN on trying to watch any contents of the restricted age rating and all the age ratings above it(eg: Nc16 and M18)");
		} else {
			logStatus("fail",
					"TC_sanity_0163	Verify that if the user has restricted the access to any age rating from account settings(for example NC 16), then the user should be asked to enter the PIN on trying to watch any contents of the restricted age rating and all the age ratings above it(eg: Nc16 and M18)");
			softAssert.assertTrue(false,
					" TC_sanity_0163	Verify that if the user has restricted the access to any age rating from account settings(for example NC 16), then the user should be asked to enter the PIN on trying to watch any contents of the restricted age rating and all the age ratings above it(eg: Nc16 and M18)");
		}
	}

	@When("^User access all rated contents should ask for the pin$")
	public void userAccessAllRatedContentsShouldAskForThePin() {
		createNode("When", "User access all rated contents should ask for the pin");
		boolean isContentRated = true;
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		for (int i = 0; i < allRatedContents.length; i++) {
			content = utilities.getDataFromPropertyFile("SearchContents", allRatedContents[i]);

			searchPage.searchAndClickTheContent(content, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(3);
			} else {
				eventUtils.sleep(3);
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
//				eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);
				playerPage.clickWatchOrResumeCTA(test);
				if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ContentRatedTitle, 10)) {
					logStatus("info", "Pin Restriction is displaying for the rated content:-" + allRatedContents[i]);
				} else {
					logStatus("error",
							"Pin Restriction is not displaying for the rated content:-" + allRatedContents[i]);
					isContentRated = false;
				}
			}
			for (int j = 0; j < 5; j++) {

				if (eventUtils.waitUntilElementIsVisible(driver, homePage.HamburgerMenu, 2)) {
					homePage.clickOnHamburgerMenu(test);
					break;
				} else {
					tvEventsUtils.backButton(test);
				}

			}
		}

		if (isContentRated) {
			logStatus("pass", "TC_sanity_0166	Verify that the user can play contents of G Rating");
		} else {
			logStatus("fail", "TC_sanity_0166	Verify that the user can play contents of G Rating");
			softAssert.assertTrue(false, "TC_sanity_0166	Verify that the user can play contents of G Rating");
		}
	}

	@When("^User access the all rated contents only G rated content should play without displaying pin prompt$")
	public void userAccessTheAllRatedContentsOnlyGRatedContentShouldPlayWithoutDisplayingPinPrompt() {
		createNode("When",
				"User access the all rated contents only G rated content should play without displaying pin prompt");
		boolean isContentRated = true;
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		for (int i = 0; i < allRatedContents.length; i++) {
			content = utilities.getDataFromPropertyFile("SearchContents", allRatedContents[i]);

			searchPage.searchAndClickTheContent(content, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(3);
			} else {
				eventUtils.sleep(3);
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
//				eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);
				playerPage.clickWatchOrResumeCTA(test);

				if (allRatedContents[i].equalsIgnoreCase("GFreeSeries")) {
					playerPage.waitUntilAdCompleted(test);
					if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 10)) {
						logStatus("info", "Pin Restriction is not displaying for the rated content:-"
								+ allRatedContents[i] + " and able to play the content");
					} else {
						logStatus("error", "Pin Restriction is not displaying for the rated content:-"
								+ allRatedContents[i] + " and able to play the content");
						isContentRated = false;
					}
				} else {
					if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ContentRatedTitle, 10)) {
						logStatus("info",
								"Pin Restriction is displaying for the rated content:-" + allRatedContents[i]);
					} else {
						logStatus("error",
								"Pin Restriction is not displaying for the rated content:-" + allRatedContents[i]);
						isContentRated = false;
					}
				}
			}
			for (int j = 0; j < 5; j++) {

				if (eventUtils.waitUntilElementIsVisible(driver, homePage.HamburgerMenu, 2)) {
					homePage.clickOnHamburgerMenu(test);
					break;
				} else {
					tvEventsUtils.backButton(test);
				}

			}
		}

		if (isContentRated) {
			logStatus("pass", "TC_sanity_0167	Verify that the user can play contents of PG Rating");
		} else {
			logStatus("fail", "TC_sanity_0167	Verify that the user can play contents of PG Rating");
			softAssert.assertTrue(false, "TC_sanity_0167	Verify that the user can play contents of PG Rating");
		}
	}

	@When("^User access the all rated contents only G and PG rated content should play without displaying pin prompt$")
	public void userAccessTheAllRatedContentsOnlyGAndPGRatedContentShouldPlayWithoutDisplayingPinPrompt() {
		createNode("When",
				"User access the all rated contents only G and PG rated content should play without displaying pin prompt");
		boolean isContentRated = true;
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		for (int i = 0; i < allRatedContents.length; i++) {
			content = utilities.getDataFromPropertyFile("SearchContents", allRatedContents[i]);

			searchPage.searchAndClickTheContent(content, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(3);
			} else {
				eventUtils.sleep(3);
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
//				eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);
				playerPage.clickWatchOrResumeCTA(test);

				if (allRatedContents[i].equalsIgnoreCase("GFreeSeries")
						|| allRatedContents[i].equalsIgnoreCase("PGFreeSeries")) {
					playerPage.waitUntilAdCompleted(test);
					playerPage.waitUntilAdCompleted(test);
					if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 10)) {
						logStatus("info", "Pin Restriction is not displaying for the rated content:-"
								+ allRatedContents[i] + " and able to play the content");
					} else {
						logStatus("error", "Pin Restriction is not displaying for the rated content:-"
								+ allRatedContents[i] + " and able to play the content");
						isContentRated = false;
					}
				} else {
					if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ContentRatedTitle, 10)) {
						logStatus("info",
								"Pin Restriction is displaying for the rated content:-" + allRatedContents[i]);
					} else {
						logStatus("error",
								"Pin Restriction is not displaying for the rated content:-" + allRatedContents[i]);
						isContentRated = false;
					}
				}
			}
			for (int j = 0; j < 5; j++) {

				if (eventUtils.waitUntilElementIsVisible(driver, homePage.HamburgerMenu, 2)) {
					homePage.clickOnHamburgerMenu(test);
					break;
				} else {
					tvEventsUtils.backButton(test);
				}

			}
		}

		if (isContentRated) {
			logStatus("pass", "TC_sanity_0168	Verify that the user can play contents of PG13 Rating");
		} else {
			logStatus("fail", "TC_sanity_0168	Verify that the user can play contents of PG13 Rating");
			softAssert.assertTrue(false, "TC_sanity_0168	Verify that the user can play contents of PG13 Rating");
		}
	}

	@Given("^User navigates to home page as a anonymous user$")
	public void userNavigatesToHomePageAsAAnonymousUser() {
		createNode("Given", "User navigates to home page as a anonymous user");
		
		homePage.logout(test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 20)) {
			logStatus("error", "User in logged state");
		} else {
			logStatus("info", "User in logged state");
		}
	}

	@When("^User access the all rated contents only G and PG and PG13 rated content should play without displaying login prompt$")
	public void userAccessTheAllRatedContentsOnlyGAndPGAndPG13RatedContentShouldPlayWithoutDisplayingLoginPrompt() {
		createNode("When",
				"User access the all rated contents only G and PG and PG13 rated content should play without displaying login prompt");
		boolean isContentRated = true;
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		for (int i = 0; i < allRatedContents.length; i++) {
			content = utilities.getDataFromPropertyFile("SearchContents", allRatedContents[i]);

			searchPage.searchAndClickTheContent(content, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				eventUtils.scrollDownBy500(driver);
				eventUtils.sleep(3);
			} else {
				eventUtils.sleep(3);
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
//				eventUtils.scrollToParticularElement(driver, playerPage.WatchOrResumeCTA);
				playerPage.clickWatchOrResumeCTA(test);

				if (allRatedContents[i].equalsIgnoreCase("GFreeSeries")
						|| allRatedContents[i].equalsIgnoreCase("PGFreeSeries")
						|| allRatedContents[i].equalsIgnoreCase("PG13FreeSeries")) {
					playerPage.waitUntilAdCompleted(test);
					playerPage.waitUntilAdCompleted(test);
					if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 10)) {
						logStatus("info", "Pin Restriction is not displaying for the rated content:-"
								+ allRatedContents[i] + " and able to play the content");
					} else {
						logStatus("error", "Pin Restriction is displaying for the rated content:-" + allRatedContents[i]
								+ " and unable to play the content");
						isContentRated = false;
					}
				} else {
					if (eventUtils.waitUntilElementIsVisible(driver, loginPage.SignInButtonPopUp, 10)) {
						logStatus("info",
								"Pin Restriction is displaying for the rated content:-" + allRatedContents[i]);
					} else {
						logStatus("error",
								"Pin Restriction is not displaying for the rated content:-" + allRatedContents[i]);
						isContentRated = false;
					}
				}
			}
			for (int j = 0; j < 5; j++) {

				if (eventUtils.waitUntilElementIsVisible(driver, homePage.HamburgerMenu, 2)) {
					homePage.clickOnHamburgerMenu(test);
					break;
				} else {
					tvEventsUtils.backButton(test);
				}

			}
		}

		if (isContentRated) {
			logStatus("pass", "TC_sanity_0169	Verify that the user can play contents of NC16 Rating");
		} else {
			logStatus("fail", "TC_sanity_0169	Verify that the user can play contents of NC16 Rating");
			softAssert.assertTrue(false, "TC_sanity_0169	Verify that the user can play contents of NC16 Rating");
		}
	}

	@When("^User access the R21 content should ask for Create Pin$")
	public void userAccessTheR21ContentShouldAskForCreatePin() {
		createNode("When", "User access the R21 content should ask for Create Pin");
		String R21Content = utilities.getDataFromPropertyFile("SearchContents", "R21FreeSeries");
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		searchPage.searchAndClickTheContent(R21Content, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(3);
		} else {
			eventUtils.sleep(3);
		}
		if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
			playerPage.clickWatchOrResumeCTA(test);
			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.CreateSetUpNowButton, 20)) {
				logStatus("info",
						"User asked for to create pin by accessing R21 content when pin not set for the content");
				logStatus("pass",
						"TC_sanity_0160	Verify that the user is asked to create a PIN when a user without a PIN tries to access R21 contents");
			} else {
				logStatus("error",
						"User not asked for to create pin by accessing R21 content when pin not set for the content");
				logStatus("fail",
						"TC_sanity_0160	Verify that the user is asked to create a PIN when a user without a PIN tries to access R21 contents");
				softAssert.assertTrue(false,
						"TC_sanity_0160	Verify that the user is asked to create a PIN when a user without a PIN tries to access R21 contents");
			}
		} else {
			logStatus("error",
					"User not asked for to create pin by accessing R21 content when pin not set for the content");
			logStatus("fail",
					"TC_sanity_0160	Verify that the user is asked to create a PIN when a user without a PIN tries to access R21 contents");
			softAssert.assertTrue(false,
					"TC_sanity_0160	Verify that the user is asked to create a PIN when a user without a PIN tries to access R21 contents");
		}

	}

	@When("^User access the rated content should display Age Restriction Modal$")
	public void userAccessTheRatedContentShouldDisplayAgeRestrictionModal() {
		createNode("When", "User access the rated content should display Age Restriction Modal");
		String NC16FreeSeries = utilities.getDataFromPropertyFile("SearchContents", "NC16FreeSeries");
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.navigateBack(driver, test);
		}
		searchPage.searchAndClickTheContent(NC16FreeSeries, test);
		eventUtils.scrollDownBy500(driver);
		eventUtils.sleep(3);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(3);
		} else {
			eventUtils.sleep(3);
		}
		if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
			playerPage.clickWatchOrResumeCTA(test);
			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.AgeRestrictionModal, 20)) {
				logStatus("info", "Age restriction displayed when user age is less then 16 years");
				logStatus("pass",
						"TC_sanity_0158	Verify that the user should be displayed the \"age restriction modal\"  after signing in from the \"Free Rated sign in required modal\"  and the user's age <=16 years ");
			} else {
				logStatus("error", "Age restriction is not displayed when user age is less then 16 years");
				logStatus("fail",
						"TC_sanity_0158	Verify that the user should be displayed the \"age restriction modal\"  after signing in from the \"Free Rated sign in required modal\"  and the user's age <=16 years ");
				softAssert.assertTrue(false,
						"TC_sanity_0158	Verify that the user should be displayed the \"age restriction modal\"  after signing in from the \"Free Rated sign in required modal\"  and the user's age <=16 years ");
			}
		} else {
			logStatus("error",
					"User not asked for to create pin by accessing R21 content when pin not set for the content");
			logStatus("fail",
					"TC_sanity_0158	Verify that the user should be displayed the \"age restriction modal\"  after signing in from the \"Free Rated sign in required modal\"  and the user's age <=16 years ");
			softAssert.assertTrue(false,
					"TC_sanity_0158	Verify that the user should be displayed the \"age restriction modal\"  after signing in from the \"Free Rated sign in required modal\"  and the user's age <=16 years ");
		}
	}

	@After("@rated")
	public void tearDown() {
		try {
//			homePage.logout(test);
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
