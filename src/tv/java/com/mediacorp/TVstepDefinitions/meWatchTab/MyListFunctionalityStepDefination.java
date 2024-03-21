package com.mediacorp.TVstepDefinitions.meWatchTab;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.MyListPage;
import com.mediacorp.pages.PlayerPage;
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

public class MyListFunctionalityStepDefination extends BaseTest

{
	EventUtils eventUtils;
	LoginPage loginPage;
	HomePage homePage;
	PlayerPage playerPage;
	SoftAssert softAssert;
	Utilities utilities;
	SearchPage searchPage;
	MyListPage mylist;
	String searchContent;
	String searchedContentXpath;

	@Before("@mylist")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		launchApplication();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		eventUtils = new EventUtils();
		playerPage = new PlayerPage(driver);
		softAssert = new SoftAssert();

		utilities = new Utilities();
		mylist = new MyListPage(driver);
		searchPage = new SearchPage(driver);

	}

	@Given("^Verify that for anonymous user the SignIn required  modal is prompted, when Plus action is performed on My List CTA in IDP$")
	public void verify_that_for_anonymous_user_the_signin_required_modal_is_prompted_when_plus_action_is_performed_on_my_list_cta_in_idp()
			throws Throwable {
		createNode("And",
				"Verify that for anonymous user the SignIn required  modal is prompted, when Plus action is performed on My List CTA in IDP");

		searchContent = utilities.getDataFromPropertyFile("SearchContents", "FreeContent");
		searchPage.searchAndClickTheContent(searchContent, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			searchedContentXpath = "//*[text()='" + searchContent + "']";
		} else {
			if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				searchedContentXpath = "//android.widget.TextView[@text='" + searchContent
						+ "']//..//preceding-sibling::android.widget.FrameLayout";
			}
		}
//		eventUtils.waitUntilElementIsPresent(driver, By.xpath(searchedContentXpath), 10);
//		eventUtils.scrollToParticularElement(driver, By.xpath(searchedContentXpath));
//		eventUtils.clickOnElement(driver, By.xpath(searchedContentXpath), "searchedContentXpath", 20, test);
		eventUtils.scrollDownBy500(driver);
		mylist.clickOnMyListPlusCTAOnIDPPage(test);
		eventUtils.mouseHoverToElement(driver, loginPage.SignInButtonPopUp, 20, test);
		if (eventUtils.waitUntilElementIsVisible(driver, loginPage.SignInButtonPopUp, 20)) {
			logStatus("pass",
					"TC_sanity_0090 Signin required model is prompted when anonymous user clicks on my list plus action in IDP page ");
		} else
			logStatus("fail",
					"TC_sanity_0090 Signin required model is not  prompted when anonymous user clicks on my list plus action in IDP page");
		softAssert.assertTrue(false,
				"TC_sanity_0090 Signin required model is not  prompted when anonymous user clicks on my list plus action in IDP page");
	}

	@Then("^Verify that the content should be added to My list after the anonymous user signs in from the Sign in required modal$")
	public void verify_that_the_content_should_be_added_to_my_list_after_the_anonymous_user_signs_in_from_the_sign_in_required_modal()
			throws Throwable {
		createNode("Then",
				"Verify that the content should be added to My list after the anonymous user signs in from the Sign in required modal");
		// loginPage.clickSignInButton(test);
		String email = utilities.getCredentialsFromPropertyFile("FreeUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("FreeUserPassword");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			loginPage.loginToTheApplication(email, password, test);
			eventUtils.sleep(5);
		} else {
			loginPage.clickSignInButtonPopUp(test);
			;
			loginPage.clickSignInMeConnectButton(test);

			loginPage.enterEmail(email, test);

			loginPage.enterPassword(password, test);

			loginPage.clickSignInButton(test);
		}

		// homePage.enterSearchContent(searchContent, test);
		// homePage.clickOnSearchButton(test);

		// eventUtils.sleep(1000);
		// searchedContentXpath = "//*[text()='" + searchContent + "']";
		// eventUtils.scrollDownBy500(driver);
		// eventUtils.waitUntilElementIsPresent(driver, By.xpath(searchedContentXpath),
		// 10);
		// eventUtils.mouseHoverToElement(driver,By.xpath(searchedContentXpath), 20,
		// test);
		// eventUtils.clickOnElement(driver,By.xpath(searchedContentXpath),
		// "searchedContentXpath", 20, test);
		// eventUtils.scrollDownBy500(driver);
		// eventUtils.waitUntilElementIsPresent(driver, mylist.MyListPlusCTAOnIDPPage,
		// 30);
		// eventUtils.clickOnElement(driver,mylist.MyListPlusCTAOnIDPPage, "myList CTA",
		// 20, test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 20, test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else if (Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);

		} else {
			homePage.clickOnHamburgerMenu(test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			if (eventUtils.getCurrentUrl(driver, test).contains("account/profiles/mylist")) {
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 30)) {
					logStatus("pass", "TC_sanity_0092 content is added to mylist page when anonymus user signs in");
				} else
					logStatus("fail", "TC_sanity_0092 content is not added to mylist page when anonymus user signs in");
				softAssert.assertTrue(false,
						"TC_sanity_0092 content is not added to mylist page when anonymus user signs in");

			} else {
				logStatus("fail", "TC_sanity_0092 Unable to navigate to mylist page");
				softAssert.assertTrue(false, "TC_sanity_0092 Unable to navigate to mylist page");
			}
		} else {
			if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (eventUtils.waitUntilElementIsPresent(driver, mylist.MylistTitle, 30)) {
					if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 30)) {
						logStatus("pass", "TC_sanity_0092 content is added to mylist page when anonymus user signs in");
					} else {
						logStatus("fail",
								"TC_sanity_0092 content is not added to mylist page when anonymus user signs in");
						softAssert.assertTrue(false,
								"TC_sanity_0092 content is not added to mylist page when anonymus user signs in");

					}
				} else {
					logStatus("fail", "TC_sanity_0087 able to navigate to my list page");
					softAssert.assertTrue(false, "TC_sanity_0087 Signed-in user unable to navigate to my list page");
				}

			}
		}
	}

	@And("^Verify that a signed-in user can Remove series/movies to My List by performing Plus action on My List CTA in IDP$")
	public void verify_that_a_signedin_user_can_remove_seriesmovies_to_my_list_by_performing_plus_action_on_my_list_cta_in_idp()
			throws Throwable {
		createNode("And",
				"Verify that a signed-in user can Remove series/movies to My List by performing Plus action on My List CTA in IDP");
		// not for web and mobile rw
		eventUtils.clickOnElement(driver, By.xpath(searchedContentXpath), "addedcontentxpathtomylist", 20, test);
		eventUtils.scrollDownBy500(driver);
		mylist.clickOnMyListPlusCTAOnIDPPage(test);
		eventUtils.sleep(3);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 20, test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else if ((Web_Constants.PROJECT.equalsIgnoreCase("MobileRW"))) {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			if (eventUtils.getCurrentUrl(driver, test).contains("account/profiles/mylist")) {
				if (!eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 30)) {
					logStatus("pass", "TC_sanity_0087 Signed-in user able to remove series/movies to my list page");

				} else {
					logStatus("fail", "TC_sanity_0087 Signed-in user unable to remove series/movies to my list page");
					softAssert.assertTrue(false,
							"TC_sanity_0087 Signed-in user unable to remove series/movies to my list page");
				}
			} else {
				logStatus("fail", "TC_sanity_0087 Unable to navigate to mylist page");
				softAssert.assertTrue(false, "TC_sanity_0087 Unable to navigate to mylist page");
			}
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			if (eventUtils.waitUntilElementIsPresent(driver, mylist.MylistTitle, 30)) {
				eventUtils.sleep(10);
				if (!eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 30)) {
					logStatus("pass", "TC_sanity_0087 Signed-in user able to remove series/movies to my list page");

				} else {
					logStatus("fail", "TC_sanity_0087 Signed-in user unable to remove series/movies to my list page");
					softAssert.assertTrue(false,
							"TC_sanity_0087 Signed-in user unable to remove series/movies to my list page");
				}
			} else {
				logStatus("fail", "TC_sanity_0087 able to navigate to my list page");
				softAssert.assertTrue(false, "TC_sanity_0087 Signed-in user unable to navigate to my list page");
			}
		}

		eventUtils.navigateBack(driver, test);

	}

	@Then("^Verify that a signed-in user can add series/movies to My List by performing Plus  action on  My List CTA in IDP$")
	public void verify_that_a_signedin_user_can_add_seriesmovies_to_my_list_by_performing_plus_action_on_my_list_cta_in_idp()
			throws Throwable {
		createNode("Then",
				"Verify that a signed-in user can add series/movies to My List by performing Plus  action on  My List CTA in IDP");

		// String searchContent = utilities.getDataFromPropertyFile("SearchContents",
		// "FreeContent");

		// homePage.enterSearchContent(searchContent, test);
		// homePage.clickOnSearchButton(test);
		// eventUtils.sleep(1000);

		// eventUtils.scrollDownBy500(driver);
		// eventUtils.waitUntilElementIsPresent(driver, By.xpath(searchedContentXpath),
		// 10);

		// eventUtils.mouseHoverToElement(driver,By.xpath(searchedContentXpath), 20,
		// test);

		// eventUtils.clickOnElement(driver,By.xpath(searchedContentXpath),
		// "searchedContentXpath", 20, test);
		// eventUtils.scrollDownBy500(driver);

		mylist.clickOnMyListPlusCTAOnIDPPage(test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 20, test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else if ((Web_Constants.PROJECT.equalsIgnoreCase("MobileRW"))) {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			if (eventUtils.getCurrentUrl(driver, test).contains("account/profiles/mylist")) {
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 30)) {
					logStatus("pass", "TC_sanity_0086 Signed-in user able to add series/movies to my list page");
				} else {
					logStatus("fail", "TC_sanity_0086 Signed-in user unable to add series/movies to my list page");
					softAssert.assertTrue(false,
							"TC_sanity_0086 Signed-in user unable to add series/movies to my list page");
				}
			} else {
				logStatus("fail", "TC_sanity_0086 Unable to navigate to mylist page");
				softAssert.assertTrue(false, "TC_sanity_0086 Unable to navigate to mylist page");
			}
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			if (eventUtils.waitUntilElementIsPresent(driver, mylist.MylistTitle, 30)) {
				if (eventUtils.waitUntilElementIsVisible(driver, By.xpath(searchedContentXpath), 30)) {
					logStatus("pass", "TC_sanity_0086 Signed-in user able to add series/movies to my list page");
				} else {
					logStatus("fail", "TC_sanity_0086 Signed-in user unable to add series/movies to my list page");
					softAssert.assertTrue(false,
							"TC_sanity_0086 Signed-in user unable to add series/movies to my list page");
				}
			} else {
				logStatus("fail", "TC_sanity_0086 Unable to navigate to mylist page");
				softAssert.assertTrue(false, "TC_sanity_0086 Unable to navigate to mylist page");
			}

		}
	}

	@Then("Verify the metadata\\(Unwatched episode no. for series) showed in My List rail")
	public void verify_the_metadata_unwatched_episode_no_for_series_showed_in_my_list_rail() {
		createNode("And", "Verify the metadata(Unwatched episode no. for series) showed in My List rail");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			if (eventUtils.getCurrentUrl(driver, test).contains("account/profiles/mylist")) {
				// eventUtils.clickOnElement(mylist.MyListPlusCTAOnIDPPage, "myList CTA", 20,
				// test);
				if (eventUtils.waitUntilElementIsPresent(driver, mylist.Unwatched, 20)) {
					logStatus("pass", "TC_sanity_0088 unwatched episode no is showed in mylist rail");
				} else {
					logStatus("fail", "TC_sanity_0088 unwatched episode no is not showed in mylist rail");
					softAssert.assertTrue(false, "TC_sanity_0088 unwatched episode no is not  showed in mylist rail");
				}
			} else {
				logStatus("fail", "TC_sanity_0088 Unable to navigate to mylist page");
				softAssert.assertTrue(false, "TC_sanity_0088 Unable to navigate to mylist page");
			}
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			if (eventUtils.waitUntilElementIsPresent(driver, mylist.MylistTitle, 30)) {
				if (eventUtils.waitUntilElementIsPresent(driver, mylist.Unwatched, 20)) {
					logStatus("pass", "TC_sanity_0088 unwatched episode no is showed in mylist rail");
				} else {
					logStatus("fail", "TC_sanity_0088 unwatched episode no is not showed in mylist rail");
					softAssert.assertTrue(false, "TC_sanity_0088 unwatched episode no is not  showed in mylist rail");
				}
			} else {
				logStatus("fail", "TC_sanity_0086 Unable to navigate to mylist page");
				softAssert.assertTrue(false, "TC_sanity_0086 Unable to navigate to mylist page");
			}

		}
	}

	@Then("^Verify recently added should display first in the rail$")
	public void verify_recently_added_should_display_first_in_the_rail() throws Throwable {

		createNode("Then", "Verify recently added should display first in the rail");

		mylist.mouseHoverAndClickOnFilterInMyListPage(test);
		if (eventUtils.waitUntilElementIsPresent(driver, mylist.RecentlyAdded, 20)) {
			logStatus("pass", "TC_sanity_0091 first element visible is recently added");
		} else {
			logStatus("fail", "TC_sanity_0091  first element visible is not recently added");
			softAssert.assertTrue(false, "first element visible is not recently added");

		}
		eventUtils.navigateBack(driver, test);
	}

	@Then("^Verify the user can navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed$")
	public void verify_the_user_can_navigate_to_the_my_list_lisitng_page_on_selecting_the_tile_my_list_and_the_listing_page_filters_options_are_displayed()
			throws Throwable {
		createNode("Then",
				"Verify the user can navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 20, test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else if ((Web_Constants.PROJECT.equalsIgnoreCase("MobileRW"))) {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			mylist.clickOnMyListCTAOnProfileLogoPage(test);
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			if (eventUtils.getCurrentUrl(driver, test).contains("account/profiles/mylist")) {
				if (eventUtils.waitUntilElementIsVisible(driver, mylist.FilterInMyListPage, 30)) {
					logStatus("pass",
							"TC_sanity_0089  user can navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
				} else {
					logStatus("fail",
							"TC_sanity_0089  user cannot navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
					softAssert.assertTrue(false,
							"TC_sanity_0089  user cannot navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
				}
			} else {
				logStatus("fail", "TC_sanity_0089  unable to navigate to mylist page");
				softAssert.assertTrue(false, "unable to navigate to mylist page");

			}
		}
		if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			if (eventUtils.waitUntilElementIsPresent(driver, mylist.MylistTitle, 30)) {
				if (eventUtils.waitUntilElementIsVisible(driver, mylist.FilterInMyListPage, 30)) {
					logStatus("pass",
							"TC_sanity_0089  user can navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
				} else {
					logStatus("fail",
							"TC_sanity_0089  user cannot navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
					softAssert.assertTrue(false,
							"TC_sanity_0089  user cannot navigate to the My List lisitng page  on selecting the tile My list and the listing page filters options are displayed");
				}
			} else {
				logStatus("fail", "TC_sanity_0091  first element visible is not recently added");
				softAssert.assertTrue(false, "first element visible is not recently added");

			}
		}
	}

	@After("@mylist")
	public void tearDown() {
		try {

			homePage.logout(test);
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
