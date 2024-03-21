package com.mediacorp.TVstepDefinitions.Login_SignUp;

import java.io.IOException;
import org.apache.tools.ant.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.PlayerPage;
import com.mediacorp.pages.PremiumPage;
import com.mediacorp.pages.SearchPage;
import com.mediacorp.utils.BaseTest;
import com.mediacorp.utils.EventUtils;
import com.mediacorp.utils.TV_EventUtils;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;
import io.cucumber.core.backend.StepDefinition;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginFunctionality_ATV_StepDefintition extends BaseTest {

	EventUtils eventUtils;
	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	PlayerPage playerPage;
	SoftAssert softAssert;
	Utilities utilities;
	PremiumPage premiumPage;
	TV_EventUtils TV_eventUtils;
	LoginPage loginPageWeb;
	RemoteWebDriver webDriver;

	int ErrTC_sanity_0139 = 0;

	By SignInPopUp = null;

	String searchContent;
	private String project;

	@Before("@login")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);

		System.out.println("Launching the Application");
		launchApplication();

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		premiumPage = new PremiumPage(driver);
		playerPage = new PlayerPage(driver);
		searchPage = new SearchPage(driver);
		eventUtils = new EventUtils();
		softAssert = new SoftAssert();

		utilities = new Utilities();
		TV_eventUtils = new TV_EventUtils();

		switch (Web_Constants.PROJECT.toLowerCase()) {
		case "androidtv":
		case "androidapp": {
			homePage.clickOnHamburgerMenu(mainTest);
			if (eventUtils.waitUntilElementIsPresent(driver, homePage.ProfileLogo, 3)) {
				homePage.logout(mainTest);
			}
			break;
		}
		default:
			break;
		}

	}

	@Given("^user searches the content (.+) and clicks on watch cta as anonymous user$")
	public void userSearchesTheContentAndClicksOnWatchCtaAsAnonymousUser(String contenttype) {

		createNode("Given", "user searches the content <ContentType> and clicks on watch cta as anonymous user");

		homePage.clickOnHamburgerMenu(test);

		if (contenttype.equalsIgnoreCase("FreeContentSignIn")) {
			searchContent = utilities.getDataFromPropertyFile("SearchContents", "FreeContentSignIn");
			SignInPopUp = loginPage.SignInButtonPopUp;
		} else if (contenttype.equalsIgnoreCase("PremiumMovieContent")) {
			searchContent = utilities.getDataFromPropertyFile("SearchContents", "PremiumMovieContent");
			SignInPopUp = premiumPage.SignInButtonBesideSubscibeButton;
		}

		searchPage.searchAndClickTheContent(searchContent, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.scrollDownBy500(driver);
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		} else {
			eventUtils.sleep(2);
			playerPage.clickWatchOrResumeCTA(test);
		}
	}

	@Then("^signin popup should display$")
	public void signinPopupShouldDisplay() {

		createNode("Then", "signin popup should display");
		eventUtils.mouseHoverToElement(driver, SignInPopUp, 20, test);
		if (eventUtils.waitUntilElememtIsClickable(driver, SignInPopUp, 20)) {
			logStatus("pass",
					"TC_sanity_0014/17:-Verify that the \"Sign in required modal\" is displayed when an anonymous user accesses any Free content/Premium that requires sign in");
			eventUtils.clickOnElement(driver, SignInPopUp, "Sign In Button", 20, test);

			if (searchContent.toLowerCase().contains("bat")) {
				logStatus("pass",
						"TC_sanity_0027	Verify that the \"Sign in required SVOD modal \" is displayed when the an anonymous user tries to play an Premium content ");
			}
		} else {
			logStatus("fail",
					"TC_sanity_0014/17:-Verify that the \"Sign in required modal\" is displayed when an anonymous user accesses any Free content/Premium that requires sign in");
			softAssert.assertTrue(false,
					"TC_sanity_0014/17:-Verify that the \"Sign in required modal\" is displayed when an anonymous user accesses any Free content/Premium that requires sign in");

			if (searchContent.toLowerCase().contains("bat")) {
				logStatus("fail",
						"TC_sanity_0027	Verify that the \"Sign in required SVOD modal \" is displayed when the an anonymous user tries to play an Premium content ");
			}

			softAssert.assertTrue(false,
					"TC_sanity_0027	Verify that the \"Sign in required SVOD modal \" is displayed when the an anonymous user tries to play an Premium content ");
		}
	}

	@And("^user should be able to login successfully as (.+) user$")
	public void userShouldBeAbleToLoginSuccessfullyAsUser(String user) {
		createNode("And", "user should be able to login successfully as " + user);

		String userName = "";
		String password = "";
		if (user.equalsIgnoreCase("NonPremium")) {
			userName = utilities.getCredentialsFromPropertyFile("FreeUserEmail");
			password = utilities.getCredentialsFromPropertyFile("FreeUserPassword");
		} else if (user.equalsIgnoreCase("Premium")) {
			userName = utilities.getCredentialsFromPropertyFile("PremiumUserEmail");
			password = utilities.getCredentialsFromPropertyFile("PremiumUserPassword");
		}

		loginPage.loginToTV(userName, password, test);

		eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30);

		if (user.equalsIgnoreCase("premium")) {

			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 30)) {
				logStatus("info", "Premium user able to access the premium content");
				logStatus("pass",
						"TC_sanity_0019	Verify that the user is able to access the premium contents after signing in with entitled user account from the \"sign-in required svood modal\" ");
			} else {
				logStatus("error", "Premium user able to access the premium content");
				logStatus("fail",
						"TC_sanity_0019	Verify that the user is able to access the premium contents after signing in with entitled user account from the \"sign-in required svood modal\" ");
				softAssert.assertTrue(false,
						"TC_sanity_0019	Verify that the user is able to access the premium contents after signing in with entitled user account from the \\\"sign-in required svood modal\\\" ");
			}

		}

		TV_eventUtils.backButton(test);
		eventUtils.sleep(10);
		TV_eventUtils.backButton(test);

		// not for web
		homePage.clickOnHamburgerMenu(test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 10)) {
			logStatus("pass",
					"TC_sanity_0015/18:-Verify that the user is able to sign in to the app via the \"sign in required modal\"");
		} else {
			logStatus("fail",
					"TC_sanity_0015/18:-Verify that the user is able to sign in to the app via the \"sign in required modal\"");
			softAssert.assertTrue(false,
					"TC_sanity_0015/18:-Verify that the user is able to sign in to the app via the \"sign in required modal\"");
		}

		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			driver.navigate().back();
		}

	}

	/* Verifying the New Account Create Functionality */
	@When("^User launches the application and lands on the homepage$")
	public void userLaunchesTheApplicationAndLandsOnTheHomepage() {
		createNode("When", "User launches the application and lands on the homepage");

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		eventUtils = new EventUtils();
		playerPage = new PlayerPage(driver);
		utilities = new Utilities();
		softAssert = new SoftAssert();

		homePage.clickOnHamburgerMenu(test);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.MeWatchLogo, 10)) {
			logStatus("info", "Application launched successfully");
			logStatus("info", "User Lands on HomePage");

		} else {
			logStatus("error", "Unable to launch the application");
			logStatus("error", "User not able to Lands on HomePage");

		}
	}

	@And("^User clicks on sign in and navigating to login screen$")
	public void userClicksOnSignInAndNavigatingToLoginScreen() {
		createNode("And", "User selects Create Account CTA and navigates to Create Account Modal");

		homePage.clickOnHamburgerMenu(test);
		loginPage.clickSignInLink(test);

	}

	@Then("^User selects Create Account CTA and navigates to Create Account Modal$")
	public void userSelectsCreateAccountCTAAndNavigatesToCreateAccountModal() {
		createNode("Then", "User selects Create Account CTA and navigates to Create Account Modal");

		project = "AndroidTV";

		Web_Constants.PROJECT = "WEB";

		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.get("https://www.mewatch.sg/code");
		webDriver.manage().window().maximize();

		loginPageWeb = new LoginPage(webDriver);

		loginPageWeb.clickCreateNewCTA(test);

		if (eventUtils.waitUntilElementIsVisible(webDriver, loginPageWeb.CreateAccountModalTitle, 10)) {
			logStatus("pass",
					"TC_sanity_0016	Verify that the Create account modal is displayed when the user selects the \"Create Account\" CTA in the \"sign in required modal\"");
		} else {
			logStatus("fail",
					"TC_sanity_0016	Verify that the Create account modal is displayed when the user selects the \"Create Account\" CTA in the \"sign in required modal\"");
			softAssert.assertTrue(false,
					"TC_sanity_0016	Verify that the Create account modal is displayed when the user selects the \\\"Create Account\\\" CTA in the \\\"sign in required modal\\\"");

		}
	}

	@And("^User entering valid data in all the fields$")
	public void userEnteringValidDataInAllTheFields() {

		createNode("And", "User entering valid data in all the fields");

		String email = utilities.generateEmailid();
		String password = utilities.generateRandomPassword();
		loginPageWeb.enterEmail(email, test);
		loginPageWeb.enterPassword(password, test);
		loginPageWeb.enterConfirmPassword(password, test);
		loginPageWeb.enterFirstName("Automation", test);
		loginPageWeb.enterLastName("User", test);
		loginPageWeb.selectGender("Male", test);

		loginPageWeb.enterDOB("12/12/1990", test);
		loginPageWeb.selectTnC_CheckBox(test);
		loginPageWeb.clickCreateAccountButton(test);

	}

	@Then("^User should be able to create the account successfully$")
	public void userShouldBeAbleToCreateTheAccountSuccessfully() {
		createNode("Then", "User entering valid data in all the fields");

		if (eventUtils.waitUntilElementIsVisible(webDriver, loginPageWeb.WillDoLaterButton, 10)) {
			eventUtils.clickOnElement(driver, loginPage.WillDoLaterButton, "No, Will do it later", 10, test);
			homePage.clickCloseButton(test);
			if (eventUtils.waitUntilElementIsVisible(driver, homePage.ProfileLogo, 20)) {
				logStatus("pass",
						"TC_sanity_0024:-Verify the user is able to sign in using a newly created mewatch account");
			} else {
				logStatus("fail",
						"TC_sanity_0024:-Verify the user is able to sign in using a newly created mewatch account");
				softAssert.assertTrue(false,
						"TC_sanity_0024:-Verify the user is able to sign in using a newly created mewatch account");

			}
		} else {
			logStatus("fail",
					"TC_sanity_0024:-Verify the user is able to sign in using a newly created mewatch account");
			softAssert.assertTrue(false,
					"TC_sanity_0024:-Verify the user is able to sign in using a newly created mewatch account");
		}

	}

	/*
	 * Verifying the login functionality for non premium user accessing premiuim
	 * linear content
	 */

	@Given("^User accessing premium linear content as anonymous user and login modal should display$")
	public void userAccessingPremiumLinearContentAsAnonymousUserAndLoginModalShouldDisplay() {
		createNode("Given", "User accessing premium linear content as anonymous user and login modal should display");
		PremiumPage premiumPage = new PremiumPage(driver);

		homePage.clickOnHamburgerMenu(test);

		homePage.clickOnPremiumTab(test);

		TV_eventUtils.verticalNavigation(driver, premiumPage.LiveTrayPremiumContent, 5, test);

		premiumPage.clickOnPremiumLinearContent(test);

		if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.SignInButtonBesideSubscibeButton, 20)) {
			logStatus("info", "Sign In Required SVOD Modal displayed");
			logStatus("pass",
					"TC_sanity_0032	Verify that the \"Sign in required SVOD modal \" is displayed when the an anonymous user tries to play an Premium Linear content ");

		} else {
			logStatus("error", "Sign In Required SVODs Modal not displayed");
			logStatus("fail",
					"TC_sanity_0032	Verify that the \"Sign in required SVOD modal \" is displayed when the an anonymous user tries to play an Premium Linear content ");
			softAssert.assertTrue(false,
					"TC_sanity_0032	Verify that the \"Sign in required SVOD modal \" is displayed when the an anonymous user tries to play an Premium Linear content ");
		}
	}

	@When("^User login to the application as a non entitled user and subscribe modal should display$")
	public void userLoginToTheApplicationAsANonEntitledUserAndSubscribeModalShouldDisplay() {
		createNode("Given", "User login to the application as a non entitled user and subscribe modal should display");
		premiumPage.clickOnSignInButtonBesideSubscibeButton(test);
//		loginPage.clickSignInMeConnectButton(test);
		String userName = utilities.getCredentialsFromPropertyFile("FreeUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("FreeUserPassword");

		loginPage.loginToTV(userName, password, test);

		if (eventUtils.waitUntilElementIsVisible(driver, playerPage.SubscribeButtonInPlayerScreen, 20)) {
			String actualText = eventUtils.getTextOfWebelement(driver, playerPage.SubscribeButtonInPlayerScreen, 10,
					test);
			String expectedText = "This programme is available to subscribers only";
			if (actualText.equalsIgnoreCase(expectedText)) {
				logStatus("info", "Subscribe Modal is displaying by accessing premium linear content");
				logStatus("pass",
						"TC_sanity_0033	Verify that the \"Subscription required modal\" is displayed when a non-entitled logged in user tries to play an Premium Linear content ");
			} else {
				logStatus("warning", "Subscribe Modal is not displaying by accessing premium linear content");
				logStatus("fail",
						"TC_sanity_0033	Verify that the \"Subscription required modal\" is displayed when a non-entitled logged in user tries to play an Premium Linear content ");
				softAssert.assertTrue(false,
						"TC_sanity_0033	Verify that the \"Subscription required modal\" is displayed when a non-entitled logged in user tries to play an Premium Linear content ");
			}
		} else {
			logStatus("warning", "Subscribe Modal is not displaying by accessing premium linear content");
			logStatus("fail",
					"TC_sanity_0033	Verify that the \"Subscription required modal\" is displayed when a non-entitled logged in user tries to play an Premium Linear content ");
			softAssert.assertTrue(false,
					"TC_sanity_0033	Verify that the \"Subscription required modal\" is displayed when a non-entitled logged in user tries to play an Premium Linear content ");
		}
	}

	@And("^User searches and play the content and verify behaviour of Watch CTA$")
	public void userSearchesAndPlayTheContentAndVerifyBehaviourOfWatchCTA() {
		createNode("And", "User searches and play the content and verify behaviour of Watch CTA");

		String searchContent = utilities.getDataFromPropertyFile("SearchContents", "FreeContent");

		Web_Constants.PROJECT = project;

		if (eventUtils.waitUntilElementIsPresent(driver, homePage.SearchButton, 20)) {
			searchPage.searchAndClickTheContent(searchContent, test);

			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.WatchCTA, 20)) {
				logStatus("info", "Watch CTA is displaying for the unwatched content");

				eventUtils.sleep(2);
				playerPage.clickWatchOrResumeCTA(test);
			} else {
				ErrTC_sanity_0139++;
			}

			playerPage.waitUntilAdCompleted(test);
			playerPage.waitUntilAdCompleted(test);

			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.PlaybackScreen, 10)) {
				logStatus("info", "Player Screen is visible");
			} else {
				logStatus("error", "player screen not visible");
			}

			eventUtils.sleep(30);

			TV_eventUtils.backButton(test);

			if (eventUtils.waitUntilElementIsVisible(driver, playerPage.ResumeCTA, 20)) {
				logStatus("info", "Resume CTA is displaying for the watched content");
			} else {
				ErrTC_sanity_0139++;
			}

		} else {
			ErrTC_sanity_0139++;
		}

		if (ErrTC_sanity_0139 == 0) {
			logStatus("pass", "Verify the WATCH CTA change over in IDP");
		} else {
			logStatus("fail", "Verify the WATCH CTA change over in IDP");
		}
	}

	@After("@login")
	public void tearDown(Scenario scenario) {
		try {
//			homePage.logout(test);
//			softAssert.assertAll();
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
