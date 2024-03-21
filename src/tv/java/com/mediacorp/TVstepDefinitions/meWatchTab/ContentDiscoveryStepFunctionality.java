package com.mediacorp.TVstepDefinitions.meWatchTab;

import java.io.IOException;

import org.testng.asserts.SoftAssert;

import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.IDP_Page;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.MyListPage;
import com.mediacorp.pages.PlayerPage;
import com.mediacorp.pages.SearchPage;
import com.mediacorp.pages.TrayInfoPage;
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

public class ContentDiscoveryStepFunctionality extends BaseTest {

	LoginPage loginPage;
	HomePage homePage;
	TrayInfoPage trayInfoPage;
	IDP_Page idpPage;
	PlayerPage playerPage;
	MyListPage myListPage;
	SearchPage searchPage;
	EventUtils eventUtils;
	Utilities utilities;
	SoftAssert softAssert;

	int Err_TC_sanity_0133 = 0;
	int Err_TC_sanity_0141 = 0;
	int Err_TC_sanity_0137 = 0;

	@Before("@contentDiscovery")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		launchApplication();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		trayInfoPage = new TrayInfoPage(driver);
		idpPage = new IDP_Page(driver);
		searchPage = new SearchPage(driver);
		myListPage = new MyListPage(driver);
		playerPage = new PlayerPage(driver);
		eventUtils = new EventUtils();
		utilities = new Utilities();
		softAssert = new SoftAssert();
	}

	@Given("^User logins to the application and navigates to feature rail$")
	public void userLoginsToTheApplicationAndNavigatesToFeatureRail() {
		createNode("Given", "User logins to the application and navigates to feature rail");
		String email = utilities.getCredentialsFromPropertyFile("FreeUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("FreeUserPassword");

		loginPage.loginToTheApplication(email, password, test);
		
		if(Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.verticalSwipe(driver, homePage.FeaturedRail,8);
		}
		homePage.clickFeatureRail(test);

	}

	@And("^Verifying the functionality of filter options$")
	public void verifyingTheFunctionalityOfFilterOptions() {
		createNode("And", "Verifying the functionality of filter options");

		if (eventUtils.waitUntilElementIsVisible(driver, trayInfoPage.TrayTitle, 40)) {
			String trayTitle = eventUtils.getTextOfWebelement(driver, trayInfoPage.TrayTitle, 20, test);
			if (trayTitle.equalsIgnoreCase("Featured")) {
				logStatus("info", "User navigated to Feature Rail");
			} else {
				logStatus("error", "User unable to navigated to Feature Rail");
			}

			trayInfoPage.clickLanguageFilters(test);
			if (eventUtils.waitUntilElementIsVisible(driver, trayInfoPage.AudioFilterOptions, 20)) {
				logStatus("info", "Audio Filter Options are visible");

			} else {
				Err_TC_sanity_0133++;
				logStatus("warning", "Audio Filter Options are visible");
			}

			trayInfoPage.clickRatingFilter(test);
			if (eventUtils.waitUntilElementIsVisible(driver, trayInfoPage.RatingFilterOptions, 20)) {
				logStatus("info", "Rating Filter Options are visible");
			} else {
				Err_TC_sanity_0133++;
				logStatus("warning", "Rating Filter Options are visible");
			}

			trayInfoPage.clickSortingFilters(test);
			if (eventUtils.waitUntilElementIsVisible(driver, trayInfoPage.SortingFilterOptions, 20)) {
				logStatus("info", "Sorting Filter Options are visible");

			} else {
				Err_TC_sanity_0133++;
				logStatus("warning", "Sorting Filter Options are visible");
			}

			trayInfoPage.clickGenreFilters(test);
			if (eventUtils.waitUntilElementIsVisible(driver, trayInfoPage.GenreFilterOptions, 20)) {
				logStatus("info", "Genre Filter Options are visible");

			} else {
				Err_TC_sanity_0133++;
				logStatus("warning", "Genre Filter Options are visible");
			}

			if (Err_TC_sanity_0133 == 0) {
				logStatus("pass", "TC_sanity_0133:-Verify the filters behaviour in see all listing page");
			} else {
				logStatus("fail", "TC_sanity_0133:-Verify the filters behaviour in see all listing page");
				softAssert.assertTrue(false, "TC_sanity_0133:-Verify the filters behaviour in see all listing page");
			}
		} else {
			logStatus("warning", "User unable to navigated to Feature Rail");
			logStatus("fail", "TC_sanity_0133:-Verify the filters behaviour in see all listing page");
			softAssert.assertTrue(false, "TC_sanity_0133:-Verify the filters behaviour in see all listing page");
		}

	}

	@Then("^Verifying the sorting functionality of filter options$")
	public void verifyingTheSortingFunctionalityOfFilterOptions() {
		createNode("Then", "Verifying the sorting functionality of filter options");

		String filterTypes[] = { "audio", "sorting", "genre" };

		try {
			for (int i = 0; i < filterTypes.length; i++) {
				if (i > 0) {
					eventUtils.navigateToGivenUrl(driver, Web_Constants.URL, test);
					eventUtils.sleep(5);
					homePage.clickFeatureRail(test);
					eventUtils.sleep(5);
				}
				trayInfoPage.validationOfFilters(filterTypes[i], test);
			}
		} catch (Exception e) {
			trayInfoPage.Err_TC_sanity_0134++;
		}
		if (trayInfoPage.Err_TC_sanity_0134 == 0) {
			logStatus("pass", "TC_sanity_0134	Verify sorting options for different filter");
		} else {
			logStatus("fail", "TC_sanity_0134 Verify sorting options for different filters");
			softAssert.assertTrue(false, "TC_sanity_0134 Verify sorting options for different filters");
		}
		if (trayInfoPage.Err_TC_sanity_0136 == 0) {
			logStatus("pass", "TC_sanity_0136	Verify if there are no results to dispaly, when user applied filters");
		} else {
			logStatus("fail", "TC_sanity_0136	Verify if there are no results to dispaly, when user applied filters");
			softAssert.assertTrue(false,
					"TC_sanity_0136	Verify if there are no results to dispaly, when user applied filters");
		}
	}

	@And("^Selecting the content and validating the IDP$")
	public void selectingTheContentsAndValidatingTheIDP() {
		createNode("And", "Selecting the content and validating the IDP");

		String showName = utilities.getDataFromPropertyFile("SearchContents", "FreeShowMultipleSeasonContent");

		if (eventUtils.waitUntilElementIsPresent(driver, homePage.SearchButton, 20)) {
			searchPage.searchAndClickTheContent(showName, test);
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.IDP_Title, 20)) {
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.IDP_Title, 20)) {

				} else {
					logStatus("error", "IDP title is not displaying in IDP Page");
					Err_TC_sanity_0137++;
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "IDP title is not present in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.Languages, 20)) {
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.Languages, 20)) {

				} else {
					logStatus("error", "Audio is not displaying in IDP Page");
					Err_TC_sanity_0137++;
				}
			} else {
				logStatus("error", "Audio is not present in IDP Page");
				Err_TC_sanity_0137++;
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.RatingText, 20)) {
				eventUtils.scrollToElement(driver, idpPage.RatingText);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.Languages, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Rating is not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Rating is not present in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.SeasonsText, 20)) {
				eventUtils.scrollToElement(driver, idpPage.SeasonsText);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.SeasonsText, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Saesons are not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Saesons not present in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.TrailerCTA, 20)) {
				eventUtils.scrollToElement(driver, idpPage.TrailerCTA);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.TrailerCTA, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Trailer CTA not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Trailler CTA is not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, myListPage.MyListPlusCTAOnIDPPage, 20)) {
				eventUtils.scrollToElement(driver, myListPage.MyListPlusCTAOnIDPPage);
				if (eventUtils.waitUntilElementIsVisible(driver, myListPage.MyListPlusCTAOnIDPPage, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "My List CTA not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "My List CTA is not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, playerPage.WatchOrResumeCTA, 20)) {
//				eventUtils.scrollToElement(driver, playerPage.WatchOrResumeCTA);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
					eventUtils.scrollDownBy500(driver);
					eventUtils.sleep(3);
				} else {
					eventUtils.sleep(3);
				}
				if (eventUtils.waitUntilElementIsVisible(driver, playerPage.WatchOrResumeCTA, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Watch or Resume not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Watch or Resume not displaying in IDP Page");
			}

			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.SeasonsText, 20)) {
				if (eventUtils.waitUntilElementIsPresent(driver, idpPage.SeasonsList, 20)) {
					eventUtils.scrollToElement(driver, idpPage.SeasonsList);
					if (eventUtils.waitUntilElementIsVisible(driver, idpPage.SeasonsList, 20)) {

					} else {
						Err_TC_sanity_0137++;
						logStatus("error", "SeasonsList not displaying in IDP Page");
					}
				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "SeasonsList not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "SeasonsText not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.EpisodesFilter, 20)) {
				eventUtils.scrollToElement(driver, idpPage.EpisodesFilter);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.EpisodesFilter, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Episode Filter not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Episode Filter not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.EpisodesSortingFilter, 20)) {
				eventUtils.scrollToElement(driver, idpPage.EpisodesSortingFilter);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.EpisodesSortingFilter, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Episode sorting filter not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Episode sorting filter not displaying in IDP Page");
			}
			eventUtils.scrollToElement(driver, idpPage.BackToTopButton);
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.BackToTopButton, 20)) {

				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.BackToTopButton, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Scroll Up CTA not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Scroll Up CTA not displaying in IDP Page");
			}

			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.EpisodesList, 20)) {
				eventUtils.scrollToElement(driver, idpPage.EpisodesList);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.EpisodesList, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Episodes List not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Episodes List not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.ExtrasRail, 20)) {
//				eventUtils.scrollDownBy500(driver);
				eventUtils.mouseHoverToElement(driver, idpPage.ExtrasRail, 20, mainTest);
//				eventUtils.scrollToElement(driver, idpPage.ExtrasRail);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.ExtrasRail, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "Extras Rail not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "Extras Rail not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.RelatedProgramsRail, 20)) {

				eventUtils.scrollToElement(driver, idpPage.RelatedProgramsRail);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.RelatedProgramsRail, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "RelatedPrograms Rail not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "RelatedPrograms Rail not displaying in IDP Page");
			}
			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.YouMayAlsoLikeRail, 20)) {
				eventUtils.scrollDownBy500(driver);
				// eventUtils.scrollToElement(driver, idpPage.YouMayAlsoLikeRail);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.YouMayAlsoLikeRail, 20)) {

				} else {
					Err_TC_sanity_0137++;
					logStatus("error", "YouMayAlsoLike Rail not displaying in IDP Page");
				}
			} else {
				Err_TC_sanity_0137++;
				logStatus("error", "YouMayAlsoLike Rail not displaying in IDP Page");
			}

			if (Err_TC_sanity_0137 == 0) {
				logStatus("pass", "TC_sanity_0137:-Verify all the content is displayed in  IDP of the series/movies");
			} else {
				logStatus("fail", "TC_sanity_0137:-Verify all the content is displayed in  IDP of the series/movies");
				softAssert.assertTrue(false,
						"TC_sanity_0137:-Verify all the content is displayed in  IDP of the series/movies");
			}

			if (eventUtils.waitUntilElementIsPresent(driver, idpPage.IDP_Banner, 20)) {
				eventUtils.scrollToElement(driver, idpPage.IDP_Banner);
				if (eventUtils.waitUntilElementIsVisible(driver, idpPage.IDP_Banner, 20)) {
					logStatus("info", "IDP banner is displaying in IDP page");
					logStatus("pass", "TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
				} else {
					logStatus("error", "IDP Banner not displaying in IDP Page");
					logStatus("fail", "TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
					softAssert.assertTrue(false,
							"TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
				}
			} else {
				logStatus("error", "IDP Banner not displaying in IDP Page");
				logStatus("fail", "TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
				softAssert.assertTrue(false,
						"TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
			}
		} else {

			logStatus("fail", "TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
			softAssert.assertTrue(false, "TC_sanity_0138	Verify the banner in the IDP of the series/movies/shows");
		}

	}

	@Then("^Verifying the episode filter in IDP page$")
	public void verifyingTheEpisodeFilterInIDPPage() {
		createNode("Then", "Verifying the episode filter in IDP page");

		if (eventUtils.waitUntilElementIsPresent(driver, idpPage.EpisodesFilter, 20)) {
			idpPage.clickEpisodeFilter(test);
			if (eventUtils.waitUntilElementIsVisible(driver, idpPage.JumpToEpisode, 20)) {

			} else {
				Err_TC_sanity_0141++;
				logStatus("error", "Jump to Episode is displaying");
			}

			if (eventUtils.waitUntilElementIsVisible(driver, idpPage.EpisodesFilter, 20)) {

			} else {
				Err_TC_sanity_0141++;
				logStatus("error", "Episode Filter is not displaying");
			}

			if (eventUtils.waitUntilElementIsVisible(driver, idpPage.EpisodeSortingList, 20)) {

			} else {
				Err_TC_sanity_0141++;
				logStatus("error", "Episode Sorting list is displaying");
			}
			if (Err_TC_sanity_0141 == 0) {
				logStatus("pass", "TC_sanity_0141:-Verify Episode filter in IDP page");
			} else {
				logStatus("fail", "TC_sanity_0141:-Verify Episode filter in IDP page");
				softAssert.assertTrue(false, "TC_sanity_0141:-Verify Episode filter in IDP page");
			}
		} else {
			logStatus("error", "Jump to Episode is displaying");
			logStatus("fail", "TC_sanity_0141:-Verify Episode filter in IDP page");
			softAssert.assertTrue(false, "TC_sanity_0141:-Verify Episode filter in IDP page");
		}
	}

	@After("@contentDiscovery")
	public void tearDown() {

		try {
			eventUtils.navigateToUrl(driver, Web_Constants.URL);
			homePage.logout(test);
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
