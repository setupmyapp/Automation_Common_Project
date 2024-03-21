package com.mediacorp.TVstepDefinitions.meWatchTab;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.mediacorp.pages.AccountPage;
import com.mediacorp.pages.HomePage;
import com.mediacorp.pages.LiveTvPage;
import com.mediacorp.pages.LoginPage;
import com.mediacorp.pages.MyListPage;
import com.mediacorp.pages.PlayerPage;
import com.mediacorp.pages.PremiumPage;
import com.mediacorp.pages.SearchPage;
import com.mediacorp.pages.SettingsPage;
import com.mediacorp.pages.SidemenuAndFooterPage;
import com.mediacorp.pages.SubscriptionPage;
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

public class SidemenuAndFooterFunctionalityStepDefination extends BaseTest {

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
	SidemenuAndFooterPage sf;
	MyListPage myList;
	LiveTvPage livetv;
	String searchContent;
	String searchedContentXpath;

	@Before("@SidemenuAndFooter")
	public void setUp(Scenario scenario) {
		String scenarioName = getScenario(scenario);
		createTest(scenarioName);
		launchApplication();
		loginPage = new LoginPage(driver);
		myList = new MyListPage(driver);
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
		livetv = new LiveTvPage(driver);
		sf = new SidemenuAndFooterPage(driver);

	}

	int Err_TC_sanity_0176 = 0;
	int Err_TC_sanity_0177 = 0;

	@Given("^Verify the following UI in the side menu and redirection of each links for Anonymous user$")
	public void verifythefollowinguiinthesidemenuandredirectionofeachlinksforanonymoususer() throws Throwable {
		createNode("Given",
				"Verify the following UI in the side menu and redirection of each links for Anonymous user");
		if (eventUtils.waitUntilElementIsVisible(driver, homePage.MeWatchLogo, 20)) {
			logStatus("info", "Home Page  visible");
			if ((Web_Constants.PROJECT.equalsIgnoreCase("MobileRW"))
					|| (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp"))) {
				homePage.clickOnHamburgerMenu(test);
			}
			if (eventUtils.waitUntilElementIsVisible(driver, loginPage.SignInLink, 20)) {
				loginPage.clickSignInLink(test);
				eventUtils.sleep(3);
				if (eventUtils.waitUntilElementIsVisible(driver, loginPage.SignInButton, 20)) {
					logStatus("info", "User able to click on signin link");
				}

			} else {
				Err_TC_sanity_0176++;
				logStatus("error", "User not able to click on signin link");
			}
			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);

			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.Premium_Tab, 3);
			eventUtils.scrollDownBy500(driver);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.Premium_Tab, 20)) {
				homePage.clickOnPremiumTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("premium")) {
						eventUtils.scrollDownBy500(driver);
						eventUtils.sleep(5);
						if (eventUtils.waitUntilElementIsPresent(driver, premiumPage.LockSymbol, 30)) {
							logStatus("info", "User Navigated to  premium page");
						}
					} else {
						logStatus("error", "User unable to navigate to premium page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.verticalSwipe(driver, premiumPage.LiveTvTrayTitle, 1);
					if (eventUtils.waitUntilElementIsPresent(driver, premiumPage.LiveTvTrayTitle, 30)) {
						if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.LockSymbol, 30)) {
							logStatus("info", "User Navigated to  premium page");
						}
					} else {
						logStatus("error", "User unable to navigate to premium page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}
			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.LiveTV_Tab, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.LiveTV_Tab, 20)) {
				homePage.clickOnLivetvTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("live-tv")) {
						eventUtils.scrollDownBy500(driver);
						eventUtils.sleep(5);

						if (eventUtils.waitUntilElementIsPresent(driver, livetv.On_NowTrayTitle, 30)) {
							logStatus("info", "User Navigated to  live tv page");
						}
					} else {
						logStatus("error", "User unable to navigate to live tv page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					// eventUtils.verticalSwipe(driver, livetv.On_NowTrayContents, 1);
					eventUtils.sleep(3);
					if (eventUtils.waitUntilElementIsPresent(driver, livetv.On_NowTrayTitle, 30)) {
						logStatus("info", "User Navigated to  live tv page");
					} else {
						logStatus("error", "User unable to navigate to live tv page");
						Err_TC_sanity_0176++;
					}
				}
				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}

			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.Languages_Tab, 2);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.Languages_Tab, 20)) {
				homePage.clickOnLanguagesTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("languages")) {
						eventUtils.sleep(5);
						eventUtils.scrollDownBy500(driver);

						if (eventUtils.waitUntilElementIsPresent(driver, homePage.EnglishLanguage, 20)) {
							eventUtils.mouseHoverToElement(driver, homePage.EnglishLanguage, 20, test);
							logStatus("info", "User Navigated to  Languages page");
						}
					} else {
						logStatus("error", "User unable to navigate to Languages page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.sleep(5);
					// eventUtils.verticalSwipe(driver, homePage.EnglishLanguage, 2);

					if (eventUtils.waitUntilElementIsPresent(driver, homePage.EnglishLanguage, 20)) {
						eventUtils.mouseHoverToElement(driver, homePage.EnglishLanguage, 20, test);
						logStatus("info", "User Navigated to  Languages page");
					} else {
						logStatus("error", "User unable to navigate to Languages page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}

			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.Series_Tab, 2);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.Series_Tab, 20)) {
				homePage.clickOnSeriesTab(test);
				homePage.ClickOnMainButton(test);
				searchContent = utilities.getDataFromPropertyFile("SearchContents", "FreeContent2");
				searchedContentXpath = "//*[text()='" + searchContent + "']";
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("series")) {
						eventUtils.sleep(5);
						eventUtils.scrollDownBy500(driver);

						if (eventUtils.waitUntilElementIsVisible(driver, homePage.SeriesPage, 20)) {
							eventUtils.mouseHoverToElement(driver, homePage.SeriesPage, 20, test);

							logStatus("info", "User Navigated to  series page");
						}
					} else {
						logStatus("error", "User unable to navigate to series page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.sleep(5);
					// eventUtils.verticalSwipe(driver, homePage.SeriesPage, 20);

					if (eventUtils.waitUntilElementIsVisible(driver, homePage.SeriesPage, 20)) {
						eventUtils.mouseHoverToElement(driver, homePage.SeriesPage, 20, test);

						logStatus("info", "User Navigated to  series page");
					} else {
						logStatus("error", "User unable to navigate to series page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}

			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.Movies_Tab, 2);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.Movies_Tab, 20)) {
				homePage.clickOnMoviesTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("movies")) {
						eventUtils.sleep(5);
						eventUtils.scrollDownBy500(driver);

						if (eventUtils.waitUntilElementIsPresent(driver, homePage.MoviesPage, 20)) {
							eventUtils.sleep(5);

							logStatus("info", "User Navigated to  movies page");
						}
					} else {
						logStatus("error", "User unable to navigate to movies page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.sleep(5);
					// eventUtils.verticalSwipe(driver, homePage.MoviesPage, 2);
					if (eventUtils.waitUntilElementIsPresent(driver, homePage.MoviesPage, 20)) {
						eventUtils.sleep(5);
						logStatus("info", "User Navigated to  movies page");
					} else {
						logStatus("error", "User unable to navigate to movies page");
						Err_TC_sanity_0176++;
					}
				}
				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}
			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.Kids_Tab, 2);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.Kids_Tab, 20)) {
				homePage.clickOnKidsTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("kids")) {
						eventUtils.sleep(5);
						if (eventUtils.waitUntilElementIsVisible(driver, homePage.WatchYourFavoriteCharacters, 20)) {

							logStatus("info", "User Navigated to kids page");
						}
					} else {
						logStatus("error", "User unable to navigate to kids page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, homePage.WatchYourFavoriteCharacters, 20)) {

						logStatus("info", "User Navigated to kids page");
					} else {
						logStatus("error", "User unable to navigate to kids page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}
			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.News_Tab, 2);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.News_Tab, 20)) {
				homePage.clickOnNewsTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("news")) {
						eventUtils.sleep(5);
						if (eventUtils.waitUntilElementIsVisible(driver, homePage.EnglishNewsUpdate, 20)) {
							logStatus("info", "User Navigated to  news page");
						}
					} else {
						logStatus("error", "User unable to navigate to news page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, homePage.EnglishNewsUpdate, 20)) {
						logStatus("info", "User Navigated to  news page");
					} else {
						logStatus("error", "User unable to navigate to news page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}

			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollDownBy500(driver);
			// eventUtils.verticalSwipereverse(driver, homePage.Sports_Tab, 2);

			if (eventUtils.waitUntilElementIsVisible(driver, homePage.Sports_Tab, 20)) {
				homePage.clickOnSportsTab(test);
				homePage.ClickOnMainButton(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("sports")) {
						eventUtils.sleep(5);
						eventUtils.scrollDownBy500(driver);
						if (eventUtils.waitUntilElementIsVisible(driver, homePage.SportsPage, 20)) {
							// eventUtils.mouseHoverToElement(driver, homePage.SportsPage, 20, test);
							logStatus("info", "User Navigated to  sports page");
						}
					} else {
						logStatus("error", "User unable to navigate to sports page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.verticalSwipe(driver, homePage.SportsPage, 5);
					if (eventUtils.waitUntilElementIsPresent(driver, homePage.SportsPage, 20)) {
					} else {
						logStatus("error", "User unable to navigate to sports page");
						Err_TC_sanity_0176++;
					}
				}
				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}
			// not for web
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);
			eventUtils.scrollTillEnd(driver);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.AboutUs, 20)) {
				sf.clickOnAboutUs(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("about-mewatch")) {
						eventUtils.sleep(3);
						if (eventUtils.waitUntilElementIsPresent(driver, sf.AboutUsPage, 20)) {
							eventUtils.sleep(3);
							logStatus("info", "User Navigated to About us page");
						}
					} else {
						logStatus("error", "User unable to navigate to About us page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsPresent(driver, sf.AboutUsPage, 20)) {
						logStatus("info", "User Navigated to About us page");
					} else {
						logStatus("error", "User unable to navigate to About us page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}
			// not for web and mobile rw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.SubscriptionPlan, 20)) {
				sf.clickOnSubscriptionPlan(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("subscription")) {
						eventUtils.sleep(5);
						if (eventUtils.waitUntilElementIsVisible(driver, sf.SubscriptionPlanPage, 20)) {
							logStatus("info", "User Navigated to subscription plan page");
						}
					} else {
						logStatus("error", "User unable to navigate to subscription plan page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsPresent(driver, sf.SubscriptionPlanPage, 20)) {
						logStatus("info", "User Navigated to subscription plan page");
					} else {
						logStatus("error", "User unable to navigate to subscription plan page");
						Err_TC_sanity_0176++;
					}
				}

				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(5);
			}
			// not for web and mobile rw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.Help, 20)) {
				sf.clickOnHelp(test);
				eventUtils.sleep(2);

				eventUtils.switchToLatestWindow(driver, test);
				if (!Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (!Web_Constants.browser.equals("Safari")) {

						if (eventUtils.getCurrentUrl(driver, test).contains("helpcentre/s/"))

						{
							eventUtils.sleep(3);
							if (eventUtils.waitUntilElementIsVisible(driver, sf.HelpPage, 20)) {
								logStatus("info", "User Navigated to help page");
							}
						} else {
							logStatus("error", "User unable to navigate to help page");
							Err_TC_sanity_0176++;
						}
					} else {
						if (eventUtils.waitUntilElementIsVisible(driver, sf.helpCentre, 20)){
							eventUtils.sleep(3);
							if (eventUtils.waitUntilElementIsVisible(driver, sf.HelpPage, 20)) {
								logStatus("info", "User Navigated to help page");
							}
						} else {
							logStatus("error", "User unable to navigate to help page");
							Err_TC_sanity_0176++;
						}
					}

					eventUtils.close(driver);
					eventUtils.switchToParenttWindow(driver, test);

				} else {

					eventUtils.sleep(3);
					if (eventUtils.waitUntilElementIsVisible(driver, sf.HelpPage, 20)) {
						logStatus("info", "User Navigated to help page");
					} else {
						logStatus("error", "User unable to navigate to help page");
						Err_TC_sanity_0176++;
					}
				}
				if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					eventUtils.navigateBack(driver, test);
					eventUtils.sleep(5);	
				}
				
			}
			
			// not for web and mobile rw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.Advertise, 20)) {
				sf.clickOnAdvertise(test);
				eventUtils.sleep(5);

				eventUtils.switchToLatestWindow(driver, test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("business/advertising")) {
						eventUtils.sleep(5);
						if (eventUtils.waitUntilElementIsVisible(driver, sf.AdvertisePage, 20)) {
							logStatus("info", "User Navigated to adverstise page");
						}
					} else {
						logStatus("error", "User unable to navigate to advertise page");
						Err_TC_sanity_0176++;
					}

					eventUtils.close(driver);
					eventUtils.switchToParenttWindow(driver, test);
				} else {
					if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
						if (eventUtils.waitUntilElementIsVisible(driver, sf.AdvertisePage, 20)) {
							logStatus("info", "User Navigated to adverstise page");
						} else {
							logStatus("error", "User unable to navigate to advertise page");
							Err_TC_sanity_0176++;
						}
						eventUtils.navigateBack(driver, test);
						eventUtils.sleep(5);
					}

				}

			}
			
			// not for web and mobile rw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.TermsAndConditions, 20)) {
				sf.clickOnTermsAndConditions(test);
				eventUtils.sleep(5);

				eventUtils.switchToLatestWindow(driver, test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("terms-conditions")) {

						if (eventUtils.waitUntilElementIsVisible(driver, sf.TermsAndConditionsPage, 20)) {
							logStatus("info", "User Navigated to Terms and Conditions page");
						}
					} else {
						logStatus("error", "User unable to navigate to  Terms and Conditions");
						Err_TC_sanity_0176++;
					}

					eventUtils.close(driver);
					eventUtils.switchToParenttWindow(driver, test);
				} else {
					if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
						if (eventUtils.waitUntilElementIsVisible(driver, sf.TermsAndConditionsPage, 20)) {
							logStatus("info", "User Navigated to Terms and Conditions page");
						}
					 else {
						logStatus("error", "User unable to navigate to  Terms and Conditions");
						Err_TC_sanity_0176++;
					}
					eventUtils.navigateBack(driver, test);
					eventUtils.sleep(5);
				  }
			   }
			}
			
			// not for web and mobile rw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.PrivacyPolicy, 20)) {
				sf.clickOnPrivacyPolicy(test);

				eventUtils.switchToLatestWindow(driver, test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("privacy-policy")) {
						eventUtils.sleep(2);

						if (eventUtils.waitUntilElementIsVisible(driver, sf.PrivacyPolicyPage, 20)) {
							logStatus("info", "User Navigated to privacy-policy  page");
						}
					} else {
						logStatus("error", "User unable to navigate to privacy-policy page");
						Err_TC_sanity_0176++;
					}

					eventUtils.close(driver);
					eventUtils.switchToParenttWindow(driver, test);

				} else {
					if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
						if (eventUtils.waitUntilElementIsVisible(driver, sf.PrivacyPolicyPage, 20)) {
							logStatus("info", "User Navigated to privacy-policy  page");
						}
					 else {
						logStatus("error", "User unable to navigate to privacy-policy page");
						Err_TC_sanity_0176++;
					}
					eventUtils.navigateBack(driver, test);
					eventUtils.sleep(5);
				  }
			   }
			}
			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
			// not for web and mobilerw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.FacebookLink, 20)) {
				sf.clickOnFacebookLink(test);

				eventUtils.switchToLatestWindow(driver, test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("mewatch.mediacorp")) {
						logStatus("info", "User Navigated to facebook page");
					} else {
						logStatus("error", "User unable to navigate to facebook page");
						Err_TC_sanity_0176++;
					}

					eventUtils.close(driver);
					eventUtils.switchToParenttWindow(driver, test);
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsPresent(driver, sf.FacebookPage, 20)) {
						logStatus("info", "User Navigated to facebook page");
					} else {
						logStatus("error", "User unable to navigate to facebook page");
						Err_TC_sanity_0176++;
					}
					eventUtils.navigateBack(driver, test);
					eventUtils.sleep(5);     
				}

			}
			
			// not for web and mobile rw
			homePage.clickOnHamburgerMenu(test);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

			if (eventUtils.waitUntilElementIsVisible(driver, sf.InstagramLink, 20)) {
				sf.clickOnInstagramLink(test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					String currentUrl = eventUtils.getCurrentUrlofchildWindow(driver, test);
					if (currentUrl.contains("mewatch.mediacorp/")) {
						logStatus("info", "User Navigated to instagram  page");
					} else {
						logStatus("error", "User unable to navigate to instagram  page");
						Err_TC_sanity_0176++;
					}
				} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsPresent(driver, sf.InstagramPage, 20)) {
						logStatus("info", "User Navigated to instagram  page");
					} else {
						logStatus("error", "User unable to navigate to instagram  page");
						Err_TC_sanity_0176++;
					}
					eventUtils.navigateBack(driver, test);
					eventUtils.sleep(5);
				}
			}
			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);

			// not for web and mobilerw
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

//for mobilerw youtube is not considered due to it is directly going to 
//youtube app when CTA is done on youtube link
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
				sf.clickOnYoutubeLink(test);
				if (eventUtils.waitUntilElementIsVisible(driver, sf.YoutubeLink, 20)) {
					// sf.clickOnYoutubeLink(test);
					eventUtils.sleep(5);
					String currentUrl = eventUtils.getCurrentUrlofchildWindow(driver, test);

					if (currentUrl.contains("user/ToggleMediacorp")) {
						logStatus("info", "User Navigated to youtube page");
					} else {
						logStatus("error", "User unable to navigate to youtube page");
						Err_TC_sanity_0176++;
					}
				}

			} else {
				if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					sf.clickOnYoutubeLink(test);
					if (eventUtils.waitUntilElementIsPresent(driver, sf.YouTubePage, 20)) {
						logStatus("info", "User Navigated to youtube page");
					} else {
						logStatus("error", "User unable to navigate to youtube page");
						Err_TC_sanity_0176++;
					}
				}
				eventUtils.navigateBack(driver, test);
				eventUtils.navigateBack(driver, test);
				eventUtils.sleep(3);
			}
			if (Err_TC_sanity_0176 == 0)

			{
				logStatus("Pass",
						"TC_sanity_0116	Verify the following UI in the  side menu and redirection of each links for Anonymous user\r\n"
								+ "1. Sign in CTA\r\n" + "2. Home\r\n" + "3. All the main Pages\r\n" + "4. About us\r\n"
								+ "5. Subsription plans\r\n" + "6. Help\r\n" + "7. Advertise\r\n"
								+ "8. Terms & Conditions\r\n" + "9. Privacy policy\r\n"
								+ "10. Social media links (FB, Instagram, Youtube)");
			} else {
				logStatus("Fail",
						"TC_sanity_0176	Verify the following UI in the  side menu and redirection of each links for Anonymous user\r\n"
								+ "1. Sign in CTA\r\n" + "2. Home\r\n" + "3. All the main Pages\r\n" + "4. About us\r\n"
								+ "5. Subsription plans\r\n" + "6. Help\r\n" + "7. Advertise\r\n"
								+ "8. Terms & Conditions\r\n" + "9. Privacy policy\r\n"
								+ "10. Social media links (FB, Instagram, Youtube)");
			}

		} else

		{
			logStatus("FAIL", "Unable to validate the home page UI as home page is not visible ");
		}
	}

	@And("^Verify the the following UI in the side menu and redirection of each links for signed in user$")
	public void verify_the_the_following_ui_in_the_side_menu_and_redirection_of_each_links_for_signed_in_user()
			throws Throwable {
		createNode("And",
				"Verify the the following UI in the side menu and redirection of each links for signed in user");
		String email = utilities.getCredentialsFromPropertyFile("PremiumUserEmail");
		String password = utilities.getCredentialsFromPropertyFile("PremiumUserPassword");

		eventUtils.verticalSwipereverse(driver, loginPage.SignInLink, 3);
		loginPage.loginToTheApplication(email, password, test);
		eventUtils.sleep(3);

		// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.Premium_Tab, 3);
		eventUtils.scrollDownBy500(driver);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.Premium_Tab, 20)) {
			homePage.clickOnPremiumTab(test);
			homePage.ClickOnMainButton(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("premium")) {
					eventUtils.scrollDownBy500(driver);
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsPresent(driver, premiumPage.LockSymbol, 30)) {
						logStatus("info", "User Navigated to  premium page");
					}
				} else {
					logStatus("error", "User unable to navigate to premium page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.verticalSwipe(driver, premiumPage.LiveTvTrayTitle, 1);
				if (eventUtils.waitUntilElementIsPresent(driver, premiumPage.LiveTvTrayTitle, 30)) {
					if (eventUtils.waitUntilElementIsVisible(driver, premiumPage.LockSymbol, 30)) {
						logStatus("info", "User Navigated to  premium page");
					}
				} else {
					logStatus("error", "User unable to navigate to premium page");
					Err_TC_sanity_0177++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}
		// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		eventUtils.verticalSwipereverse(driver, homePage.LiveTV_Tab, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.LiveTV_Tab, 20)) {
			homePage.clickOnLivetvTab(test);
			homePage.ClickOnMainButton(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("live-tv")) {
					eventUtils.scrollDownBy500(driver);
					eventUtils.sleep(5);

					if (eventUtils.waitUntilElementIsPresent(driver, livetv.On_NowTrayTitle, 30)) {
						logStatus("info", "User Navigated to  live tv page");
					}
				} else {
					logStatus("error", "User unable to navigate to live tv page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				// eventUtils.verticalSwipe(driver, livetv.On_NowTrayContents, 1);
				eventUtils.sleep(3);
				if (eventUtils.waitUntilElementIsPresent(driver, livetv.On_NowTrayTitle, 30)) {
					logStatus("info", "User Navigated to  live tv page");
				} else {
					logStatus("error", "User unable to navigate to live tv page");
					Err_TC_sanity_0177++;
				}
			}
			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}

		// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		eventUtils.verticalSwipereverse(driver, homePage.Languages_Tab, 2);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.Languages_Tab, 20)) {
			homePage.clickOnLanguagesTab(test);
			homePage.ClickOnMainButton(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("languages")) {
					eventUtils.sleep(5);
					eventUtils.scrollDownBy500(driver);

					if (eventUtils.waitUntilElementIsPresent(driver, homePage.EnglishLanguage, 20)) {
						eventUtils.mouseHoverToElement(driver, homePage.EnglishLanguage, 20, test);
						logStatus("info", "User Navigated to  Languages page");
					}
				} else {
					logStatus("error", "User unable to navigate to Languages page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				// eventUtils.verticalSwipe(driver, homePage.EnglishLanguage, 2);

				if (eventUtils.waitUntilElementIsPresent(driver, homePage.EnglishLanguage, 20)) {
					eventUtils.mouseHoverToElement(driver, homePage.EnglishLanguage, 20, test);
					logStatus("info", "User Navigated to  Languages page");
				} else {
					logStatus("error", "User unable to navigate to Languages page");
					Err_TC_sanity_0177++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}

		// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		// eventUtils.verticalSwipereverse(driver, homePage.Series_Tab, 2);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.Series_Tab, 20)) {
			homePage.clickOnSeriesTab(test);
			homePage.ClickOnMainButton(test);
			searchContent = utilities.getDataFromPropertyFile("SearchContents", "FreeContent2");
			searchedContentXpath = "//*[text()='" + searchContent + "']";
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("series")) {
					eventUtils.sleep(5);
					eventUtils.scrollDownBy500(driver);

					if (eventUtils.waitUntilElementIsVisible(driver, homePage.SeriesPage, 20)) {
						eventUtils.mouseHoverToElement(driver, homePage.SeriesPage, 20, test);

						logStatus("info", "User Navigated to  series page");
					}
				} else {
					logStatus("error", "User unable to navigate to series page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				// eventUtils.verticalSwipe(driver, homePage.SeriesPage, 20);

				if (eventUtils.waitUntilElementIsVisible(driver, homePage.SeriesPage, 20)) {
					eventUtils.mouseHoverToElement(driver, homePage.SeriesPage, 20, test);

					logStatus("info", "User Navigated to  series page");
				} else {
					logStatus("error", "User unable to navigate to series page");
					Err_TC_sanity_0177++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}

		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		// eventUtils.verticalSwipereverse(driver, homePage.Movies_Tab, 2);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.Movies_Tab, 20)) {
			homePage.clickOnMoviesTab(test);
			homePage.ClickOnMainButton(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("movies")) {
					eventUtils.sleep(5);
					eventUtils.scrollDownBy500(driver);

					if (eventUtils.waitUntilElementIsPresent(driver, homePage.MoviesPage, 20)) {
						eventUtils.sleep(5);

						logStatus("info", "User Navigated to  movies page");
					}
				} else {
					logStatus("error", "User unable to navigate to movies page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				// eventUtils.verticalSwipe(driver, homePage.MoviesPage, 2);
				if (eventUtils.waitUntilElementIsPresent(driver, homePage.MoviesPage, 20)) {
					eventUtils.sleep(5);
					logStatus("info", "User Navigated to  movies page");
				} else {
					logStatus("error", "User unable to navigate to movies page");
					Err_TC_sanity_0177++;
				}
			}
			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}
		// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		eventUtils.verticalSwipe(driver, homePage.Kids_Tab, 2);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.Kids_Tab, 20)) {
			homePage.clickOnKidsTab(test);
			homePage.ClickOnMainButton(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("kids")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, homePage.WatchYourFavoriteCharacters, 20)) {

						logStatus("info", "User Navigated to kids page");
					}
				} else {
					logStatus("error", "User unable to navigate to kids page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				if (eventUtils.waitUntilElementIsVisible(driver, homePage.WatchYourFavoriteCharacters, 20)) {

					logStatus("info", "User Navigated to kids page");
				} else {
					logStatus("error", "User unable to navigate to kids page");
					Err_TC_sanity_0177++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}
		// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		// eventUtils.verticalSwipereverse(driver, homePage.News_Tab, 2);

		if (eventUtils.waitUntilElementIsVisible(driver, homePage.News_Tab, 20)) {
			homePage.clickOnNewsTab(test);
			homePage.ClickOnMainButton(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("news")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, homePage.EnglishNewsUpdate, 20)) {
						logStatus("info", "User Navigated to  news page");
					}
				} else {
					logStatus("error", "User unable to navigate to news page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				if (eventUtils.waitUntilElementIsVisible(driver, homePage.EnglishNewsUpdate, 20)) {
					logStatus("info", "User Navigated to  news page");
				} else {
					logStatus("error", "User unable to navigate to news page");
					Err_TC_sanity_0177++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}

		homePage.clickOnHamburgerMenu(test);
		eventUtils.scrollDownBy500(driver);
		// eventUtils.verticalSwipereverse(driver, homePage.Sports_Tab, 2);

		homePage.clickOnSportsTab(test);
		homePage.ClickOnMainButton(test);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			if (eventUtils.getCurrentUrl(driver, test).contains("sports")) {
				eventUtils.sleep(5);
				eventUtils.scrollDownBy500(driver);
				if (eventUtils.waitUntilElementIsVisible(driver, homePage.SportsPage, 20)) {
					// eventUtils.mouseHoverToElement(driver, homePage.SportsPage, 20, test);
					logStatus("info", "User Navigated to  sports page");
				}
			} else {
				logStatus("error", "User unable to navigate to sports page");
				Err_TC_sanity_0177++;
			}
		} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			eventUtils.verticalSwipe(driver, homePage.SportsPage, 5);
			eventUtils.sleep(5);
			if (eventUtils.waitUntilElementIsPresent(driver, homePage.SportsPage, 20)) {
				logStatus("info", "User Navigated to  sports page");
			} else {
				logStatus("error", "User unable to navigate to sports page");
				Err_TC_sanity_0177++;
			}
		}
		eventUtils.sleep(3);

		if ((Web_Constants.PROJECT.equalsIgnoreCase("WEB"))) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 10, test);
		} else if (Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
		} else {
//			eventUtils.verticalSwipereverse(driver, homePage.HamburgerMenu, 5);
//			homePage.clickOnHamburgerMenu(test);
//			eventUtils.verticalSwipereverse(driver, homePage.ProfileLogo, 3);
			eventUtils.terminateApp(driver);
			eventUtils.activateApp(driver);
			homePage.clickOnHamburgerMenu(test);
			homePage.clickOnProfileLogo(test);

		}
		eventUtils.sleep(2);
		if ((Web_Constants.PROJECT.equalsIgnoreCase("WEB")) || (Web_Constants.PROJECT.equalsIgnoreCase("MobileRW"))) {
			if (eventUtils.waitUntilElementIsVisible(driver, settingsPage.SettingsButton, 20)) {
				settingsPage.clickOnSettingsButton(test);
				eventUtils.sleep(5);
			}
		}
		if (eventUtils.waitUntilElementIsPresent(driver, homePage.GotItButton, 20)) {
			homePage.clickOnGotItButton(test);
			eventUtils.sleep(4);
		}
		if ((Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) || (Web_Constants.PROJECT.equalsIgnoreCase("WEB"))) {
			if (eventUtils.getCurrentUrl(driver, test).contains("account")) {

				logStatus("info", "User Navigated to  account page");
			} else {
				logStatus("error", "User unable to navigate to account page");
				Err_TC_sanity_0177++;
			}
		} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			if (eventUtils.waitUntilElementIsVisible(driver, accountPage.AccountPage, 20)) {
				logStatus("info", "User Navigated to  account page");
			} else {
				logStatus("error", "User unable to navigate to account page");
				Err_TC_sanity_0177++;
			}
		}

		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);

/// not for web
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);
		eventUtils.scrollTillEnd(driver);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.AboutUs, 20)) {
			sf.clickOnAboutUs(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("about-mewatch")) {
					eventUtils.sleep(3);
					if (eventUtils.waitUntilElementIsPresent(driver, sf.AboutUsPage, 20)) {
						logStatus("info", "User Navigated to About us page");
					}
				} else {
					logStatus("error", "User unable to navigate to About us page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (eventUtils.waitUntilElementIsPresent(driver, sf.AboutUsPage, 20)) {
					logStatus("info", "User Navigated to About us page");
				} else {
					logStatus("error", "User unable to navigate to About us page");
					Err_TC_sanity_0177++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.SubscriptionPlan, 20)) {
			sf.clickOnSubscriptionPlan(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("subscription")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, sf.SubscriptionPlanPage, 20)) {
						logStatus("info", "User Navigated to subscription plan page");
					}
				} else {
					logStatus("error", "User unable to navigate to subscription plan page");
					Err_TC_sanity_0177++;
				}
			}  else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (eventUtils.waitUntilElementIsPresent(driver, sf.SubscriptionPlanPage, 20)) {
					logStatus("info", "User Navigated to subscription plan page");
				} else {
					logStatus("error", "User unable to navigate to subscription plan page");
					Err_TC_sanity_0176++;
				}
			}

			eventUtils.navigateBack(driver, test);
			eventUtils.sleep(5);
		}
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.Help, 20)) {
			sf.clickOnHelp(test);
			eventUtils.sleep(2);

			eventUtils.switchToLatestWindow(driver, test);
			if (!Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (!Web_Constants.browser.equals("Safari")) {

					if (eventUtils.getCurrentUrl(driver, test).contains("helpcentre/s/"))

					{
						eventUtils.sleep(3);
						if (eventUtils.waitUntilElementIsVisible(driver, sf.HelpPage, 20)) {
							logStatus("info", "User Navigated to help page");
						}
					} else {
						logStatus("error", "User unable to navigate to help page");
						Err_TC_sanity_0177++;
					}
				} else {
					if (eventUtils.waitUntilElementIsVisible(driver, sf.helpCentre, 20))

					{
						eventUtils.sleep(3);
						if (eventUtils.waitUntilElementIsVisible(driver, sf.HelpPage, 20)) {
							logStatus("info", "User Navigated to help page");
						}
					} else {
						logStatus("error", "User unable to navigate to help page");
						Err_TC_sanity_0177++;
					}
				}
				eventUtils.sleep(5);
				eventUtils.close(driver);
				eventUtils.sleep(5);
				eventUtils.switchToParenttWindow(driver, test);

			} else {

				eventUtils.sleep(3);
				if (eventUtils.waitUntilElementIsVisible(driver, sf.HelpPage, 20)) {
					logStatus("info", "User Navigated to help page");
				} else {
					logStatus("error", "User unable to navigate to help page");
					Err_TC_sanity_0177++;
				}
			}

		}
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.Advertise, 20)) {
			sf.clickOnAdvertise(test);
			eventUtils.sleep(5);

			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("business/advertising")) {
					if (eventUtils.waitUntilElementIsVisible(driver, sf.AdvertisePage, 20)) {
						logStatus("info", "User Navigated to adverstise page");
					}
				} else {
					logStatus("error", "User unable to navigate to advertise page");
					Err_TC_sanity_0177++;
				}

				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			} else {
				if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsVisible(driver, sf.AdvertisePage, 20)) {
						logStatus("info", "User Navigated to adverstise page");
					} else {
						logStatus("error", "User unable to navigate to advertise page");
						Err_TC_sanity_0177++;
					}
				}

			}

		}
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.TermsAndConditions, 20)) {
			sf.clickOnTermsAndConditions(test);
			eventUtils.sleep(5);

			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("terms-conditions")) {

					if (eventUtils.waitUntilElementIsVisible(driver, sf.TermsAndConditionsPage, 20)) {
						logStatus("info", "User Navigated to Terms and Conditions page");
					}
				} else {
					logStatus("error", "User unable to navigate to  Terms and Conditions");
					Err_TC_sanity_0177++;
				}

				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			} else {
				if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsVisible(driver, sf.TermsAndConditionsPage, 20)) {
						logStatus("info", "User Navigated to Terms and Conditions page");
					}
				} else {
					logStatus("error", "User unable to navigate to  Terms and Conditions");
					Err_TC_sanity_0177++;
				}
			}

		}
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.PrivacyPolicy, 20)) {
			sf.clickOnPrivacyPolicy(test);

			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("privacy-policy")) {
					eventUtils.sleep(2);

					if (eventUtils.waitUntilElementIsVisible(driver, sf.PrivacyPolicyPage, 20)) {
						logStatus("info", "User Navigated to privacy-policy  page");
					}
				} else {
					logStatus("error", "User unable to navigate to privacy-policy page");
					Err_TC_sanity_0177++;
				}

				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);

			} else {
				if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
					if (eventUtils.waitUntilElementIsVisible(driver, sf.PrivacyPolicyPage, 20)) {
						logStatus("info", "User Navigated to privacy-policy  page");
					}
				} else {
					logStatus("error", "User unable to navigate to privacy-policy page");
					Err_TC_sanity_0177++;
				}
			}

		}
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.FacebookLink, 20)) {
			sf.clickOnFacebookLink(test);

			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("mewatch.mediacorp")) {
					logStatus("info", "User Navigated to facebook page");
				} else {
					logStatus("error", "User unable to navigate to facebook page");
					Err_TC_sanity_0177++;
				}

				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (eventUtils.waitUntilElementIsPresent(driver, sf.FacebookPage, 20)) {
					logStatus("info", "User Navigated to facebook page");
				} else {
					logStatus("error", "User unable to navigate to facebook page");
					Err_TC_sanity_0177++;
				}

			}

		}
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);
		// not for web and mobile rw
		homePage.clickOnHamburgerMenu(test);
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

		if (eventUtils.waitUntilElementIsVisible(driver, sf.InstagramLink, 20)) {
			sf.clickOnInstagramLink(test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				String currentUrl = eventUtils.getCurrentUrlofchildWindow(driver, test);
				if (currentUrl.contains("mewatch.mediacorp/")) {
					logStatus("info", "User Navigated to instagram  page");
				} else {
					logStatus("error", "User unable to navigate to instagram  page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (eventUtils.waitUntilElementIsPresent(driver, sf.InstagramPage, 20)) {
					logStatus("info", "User Navigated to instagram  page");
				} else {
					logStatus("error", "User unable to navigate to instagram  page");
					Err_TC_sanity_0177++;
				}
			}
		}
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);
		// not for web and mobile rw
		eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);

//for mobilerw youtube is not considered due to it is directly going to youtube app when CTA is done on youtube link
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			sf.clickOnYoutubeLink(test);
			if (eventUtils.waitUntilElementIsVisible(driver, sf.YoutubeLink, 20)) {
				// sf.clickOnYoutubeLink(test);
				eventUtils.sleep(5);
				String currentUrl = eventUtils.getCurrentUrlofchildWindow(driver, test);

				if (currentUrl.contains("user/ToggleMediacorp")) {
					logStatus("info", "User Navigated to youtube page");
				} else {
					logStatus("error", "User unable to navigate to youtube page");
					Err_TC_sanity_0177++;
				}
			}

		} else {
			if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				sf.clickOnYoutubeLink(test);
				if (eventUtils.waitUntilElementIsPresent(driver, sf.YouTubePage, 20)) {
					logStatus("info", "User Navigated to youtube page");
				} else {
					logStatus("error", "User unable to navigate to youtube page");
					Err_TC_sanity_0177++;
				}
			}
		}
		eventUtils.navigateBack(driver, test);
		eventUtils.navigateBack(driver, test);
		eventUtils.sleep(5);

		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 10, test);
		} else if (Web_Constants.PROJECT.equalsIgnoreCase("MobileRw")) {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
		} else {
			if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				homePage.clickOnHamburgerMenu(test);
			}
		}
		eventUtils.sleep(3);

		if (eventUtils.waitUntilElementIsVisible(driver, myList.MyListCTAOnProfileLogoPage, 10)) {
			eventUtils.verticalSwipereverse(driver, myList.MyListCTAOnProfileLogoPage, 3);
			myList.clickOnMyListCTAOnProfileLogoPage(test);
			eventUtils.sleep(5);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("account/profiles/mylist")) {
					logStatus("info", "User Navigated to  myList page");
				} else {
					logStatus("error", "User unable to navigate to myList page");
					Err_TC_sanity_0177++;
				}
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {

				if (eventUtils.waitUntilElementIsVisible(driver, myList.MylistTitle, 20)) {
					logStatus("info", "User Navigated to  myList page");
				} else {
					logStatus("error", "User unable to navigate to myList page");
					Err_TC_sanity_0177++;
				}

			}

			eventUtils.navigateBack(driver, test);
		}
		eventUtils.sleep(5);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 10, test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 20);

		}
		if (eventUtils.waitUntilElementIsVisible(driver, sf.CashbackLink, 20)) {
			sf.clickOnCashbackLink(test);
			if (!Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.switchToLatestWindow(driver, test);
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					if (eventUtils.getCurrentUrl(driver, test).contains("profile_cashback")) {
						eventUtils.sleep(5);
						if (eventUtils.waitUntilElementIsVisible(driver, sf.CashBackPage, 20)) {
							eventUtils.sleep(5);
							logStatus("info", "User Navigated to  cashback page");
						}
					} else {
						logStatus("error", "User unable to navigate to cashback page");
						Err_TC_sanity_0177++;
					}
				}
				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			}

			else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				if (eventUtils.waitUntilElementIsVisible(driver, sf.CashBackPage, 20)) {
					eventUtils.sleep(3);
					logStatus("info", "User Navigated to  cashback page");
				} else {
					logStatus("error", "User unable to navigate to cashback page");
					Err_TC_sanity_0177++;
				}
				eventUtils.navigateBack(driver, test);
			}

		}
		eventUtils.sleep(5);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 10, test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			eventUtils.verticalSwipe(driver, sf.CouponLink, 3);
		}

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, sf.CouponLink, 20)) {
			sf.clickOnCouponLink(test);
			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("coupons")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, sf.CouponPage, 20)) {
						eventUtils.sleep(5);
						logStatus("info", "User Navigated to  coupons page");
					}
				} else {
					logStatus("error", "User unable to navigate to coupons page");
					Err_TC_sanity_0177++;
				}
				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			}

			else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				if (eventUtils.waitUntilElementIsVisible(driver, sf.CouponPage, 20)) {
					eventUtils.sleep(5);
					logStatus("info", "User Navigated to  coupons page");
				} else {
					logStatus("error", "User unable to navigate to coupons page");
					Err_TC_sanity_0177++;
				}
				eventUtils.navigateBack(driver, test);
			}

		}
		eventUtils.sleep(5);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 10, test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			eventUtils.verticalSwipe(driver, sf.SurveyLink, 3);
		}

		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, sf.SurveyLink, 20)) {
			sf.clcikOnSurveyLink(test);

			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("survey")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, sf.SurveyPage, 20)) {
						eventUtils.sleep(5);

						logStatus("info", "User Navigated to  survey page");
					}
				} else {
					logStatus("error", "User unable to navigate to survey page");
					Err_TC_sanity_0177++;
				}

				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				if (eventUtils.waitUntilElementIsVisible(driver, sf.SurveyPage, 20)) {
					eventUtils.sleep(5);

					logStatus("info", "User Navigated to  survey page");
				} else {
					logStatus("error", "User unable to navigate to survey page");
					Err_TC_sanity_0177++;
				}
				eventUtils.navigateBack(driver, test);
			}

		}
		eventUtils.sleep(5);
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")) {
			eventUtils.mouseHoverToElement(driver, homePage.ProfileLogo, 10, test);
		} else {
			homePage.clickOnHamburgerMenu(test);
			eventUtils.scrollTillEnd(driver);
			eventUtils.verticalSwipe(driver, homePage.HambergerMenuTillEndVersionName, 3);
		}
		eventUtils.sleep(5);
		if (eventUtils.waitUntilElementIsVisible(driver, sf.meRewardsLink, 20)) {
			sf.clickOnmeRewardsLink(test);

			eventUtils.switchToLatestWindow(driver, test);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (eventUtils.getCurrentUrl(driver, test).contains("merewards.sg")) {
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(driver, sf.meRewardsPage, 20)) {
						eventUtils.sleep(5);
						logStatus("info", "User Navigated to  merewards page");
					}
				} else {
					logStatus("error", "User unable to navigate to merewards page");
					Err_TC_sanity_0177++;
				}

				eventUtils.close(driver);
				eventUtils.switchToParenttWindow(driver, test);
			} else if (Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
				eventUtils.sleep(5);
				if (eventUtils.waitUntilElementIsVisible(driver, sf.meRewardsPage, 20)) {
					eventUtils.sleep(5);
					logStatus("info", "User Navigated to  merewards page");
				} else {
					logStatus("error", "User unable to navigate to merewards page");
					Err_TC_sanity_0177++;
				}
				eventUtils.navigateBack(driver, test);
			}

		}
		eventUtils.sleep(5);
		homePage.logout(test);

		if (eventUtils.waitUntilElementIsVisible(driver, loginPage.SignInLink, 20)) {
			logStatus("info", "User able to signout");
		} else {
			logStatus("error", "User unable to signout");
			Err_TC_sanity_0177++;
		}

		if (Err_TC_sanity_0177 == 0) {
			logStatus("Pass",
					"TC_sanity_0177	Verify the the following UI in the  side menu and redirection of each links for signed in user\r\n"
							+ "1. Account menu\r\n" + "2. My list\r\n" + "3. My downloads\r\n"
							+ "4. All the main Pages\r\n" + "5. meRewards links \r\n" + "6. Sign out link\r\n"
							+ "7. About us\r\n" + "8. Subsription plans\r\n" + "9. Help\r\n" + "10. Advertise\r\n"
							+ "11. Terms & Conditions\r\n" + "12. Privacy policy\r\n"
							+ "13. Social media links (FB, Instagram, Youtube)");
		} else {
			logStatus("Fail",
					"TC_sanity_0177	Verify the the following UI in the  side menu and redirection of each links for signed in user\r\n"
							+ "1. Account menu\r\n" + "2. My list\r\n" + "3. My downloads\r\n"
							+ "4. All the main Pages\r\n" + "5. meRewards links \r\n" + "6. Sign out link\r\n"
							+ "7. About us\r\n" + "8. Subsription plans\r\n" + "9. Help\r\n" + "10. Advertise\r\n"
							+ "11. Terms & Conditions\r\n" + "12. Privacy policy\r\n"
							+ "13. Social media links (FB, Instagram, Youtube)");

		}
	}

	@After("@SidemenuAndFooter")
	public void tearDown() {
		try {
			killBrowser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
