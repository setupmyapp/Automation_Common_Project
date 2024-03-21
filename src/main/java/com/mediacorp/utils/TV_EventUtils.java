package com.mediacorp.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mediacorp.pages.BasePage;

public class TV_EventUtils {

	EventUtils eventUtils = new EventUtils();

	public void directClickElement(RemoteWebDriver driver, By locator, String typeOfElement, int timeout,
			ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;
		if (typeOfElement != null) {
			if (eventUtils.waitUntilElementIsPresent(driver, locator, 0)) {
				if (eventUtils.waitUntilElementIsVisible(driver, locator, timeout)) {
					WebElement element = driver.findElement(locator);
					if (element.getAttribute("clickable").equalsIgnoreCase("clickable")) {
						element.click();
						isElementClicked = true;
					} else {
						basePage.logStatus("warning", typeOfElement + "not clickable element");
					}
				} else {
					basePage.logStatus("warning",
							typeOfElement + " is not visible waiting till " + timeout + " seconds");
				}
			} else {
				basePage.logStatus("warning", "Element not present");
			}
		} else {
			basePage.logStatus("warning", "element is null");
		}
		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
		}
	}

	public void enterSearchValue(RemoteWebDriver driver, String inputValue, int timeout, ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;
		
		for (int i = 0; i < inputValue.length(); i++) {
			eventUtils.sleep(2);
			String alphabet = String.valueOf(inputValue.charAt(i));
            By locator;
            if (alphabet.replaceAll(" ", "").length()!=0) {
                locator = By.xpath(
                        "//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.mediacorp.android:id/keyboardRV']//android.widget.TextView[@text='"
                                + alphabet.toUpperCase() + "']");
            } else {
                locator = By.xpath(
                        "//android.view.ViewGroup[@index=7]//android.widget.ImageView[contains(@resource-id,'icon')]");
            }
			if (locator != null) {
				if (eventUtils.waitUntilElementIsPresent(driver, locator, 0)) {
					if (eventUtils.waitUntilElementIsVisible(driver, locator, timeout)) {
						WebElement element = driver.findElement(locator);
						if (element.getAttribute("clickable").equalsIgnoreCase("true")) {
							element.click();
							eventUtils.sleep(2);
							isElementClicked = true;
						} else {
							basePage.logStatus("warning", "Keyboard not clickable element");
						}
					} else {
						basePage.logStatus("warning", "Keyboard is not visible waiting till " + timeout + " seconds");
					}
				} else {
//					basePage.logStatus("info", "Element not present");
					break;
				}
			} else {
				basePage.logStatus("warning", "element is null");
			} 
		}
		if (isElementClicked) {
			basePage.logStatus("info", "User entered" + inputValue);
		} else {
			basePage.logStatus("error", "User unable to click on " + inputValue);
		}
	}

	public void okButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_UP");
			eventUtils.sleep(1);
			adbCommand("KEYCODE_ENTER");

			test.info("Clicked on OK Button");
			System.out.println("[INFO]:- Clicked on OK Button");
		} catch (Exception e) {
			System.err.println("Clicked on OK Button");
			test.warning("Not able to click on Up Button");
		}
	}

	public void upButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_UP");
			eventUtils.sleep(1);
//			test.info("Clicked on DPAD UP Button");
			System.out.println("[INFO]:- Clicked on DPAD UP Button");
		} catch (Exception e) {
			System.err.println("Not able to click on Up Button");
//			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}

	public void downButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_DOWN");
			eventUtils.sleep(1);
//			test.info("Clicked on DPAD DOWN Button");
			System.out.println("[INFO]:- Clicked on Down Button");
		} catch (Exception e) {
//			test.log(Status.INFO, "Not able to click on Down Button");
			System.err.println("Not able to click on Down Button");
		}
	}

	public void rightButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_RIGHT");
			eventUtils.sleep(1);
//			test.info("Clicked on DPAD Right Button");
			System.out.println("[INFO]:- Clicked on DPAD Right Button");
		} catch (Exception e) {
//			test.log(Status.INFO, "Not able to click on Right Button");
			System.err.println("Not able to click on Right Button");
		}
	}

	public void leftButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_LEFT");
			eventUtils.sleep(1);
//			test.info("Clicked on DPAD LEFT Button");
			System.out.println("[INFO]:- Clicked on DPAD LEFT Button");
		} catch (Exception e) {
//			test.log(Status.INFO, "Not able to click on Left Button");
			System.err.println("Not able to click on Left Button");
		}
	}

	public void backButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_BACK");
			test.info("Clicked on DPAD BACK Button");
			System.out.println("[INFO]:- Clicked on DPAD BACK Button");
			eventUtils.sleep(1);
			} catch (Exception e) {
//			test.log(Status.INFO, "Not able to click on BACK Button");
			System.err.println("[ERROR]:- Not able to click on BACK Button");
		}
	}

	public void adbCommand(String command) throws Exception {
		Runtime.getRuntime().exec("adb -s " + Web_Constants.UDID + " shell input keyevent " + command);
	}
	
    public void verticalDownNavigation(RemoteWebDriver driver, By locator, int count, ExtentTest test) {
        for (int i = 0; i < count; i++) {
            if (eventUtils.waitUntilElementIsVisible(driver, locator, 2)) {
                break;
            } else {
                downButton(test);
            }
        }
    }
    public void verticalUpNavigation(RemoteWebDriver driver, By locator, int count, ExtentTest test) {
        for (int i = 0; i < count; i++) {
            if (eventUtils.waitUntilElementIsVisible(driver, locator, 2)) {
                break;
            } else {
                upButton(test);
            }
        }
    }
    public void horizontalLeftNavigation(RemoteWebDriver driver, By locator, int count, ExtentTest test) {
        for (int i = 0; i < count; i++) {
            if (eventUtils.waitUntilElementIsVisible(driver, locator, 2)) {
                break;
            } else {
            	leftButton(test);
            }
        }
    }
    public void horizontalRightNavigation(RemoteWebDriver driver, By locator, int count, ExtentTest test) {
        for (int i = 0; i < count; i++) {
            if (eventUtils.waitUntilElementIsVisible(driver, locator, 2)) {
                break;
            } else {
            	rightButton(test);
            }
        }
    }
    
    public boolean selectTab(RemoteWebDriver driver, By optionsLocator, String expectedTab, ExtentTest test) {

        boolean isTabFound = false;

        try {
            if (eventUtils.waitUntilElementIsPresent(driver, optionsLocator, 20)) {
                List<WebElement> browseOptions = driver.findElements(optionsLocator);
                System.out.println(browseOptions.size());
                int focussedElementIndex = 1;
                int ExpectedButtonIndex = 1;
                for (WebElement webElement : browseOptions) {
                    if (eventUtils.waitUntilElementIsPresent(driver, webElement, 5)) {
                        if (webElement.getAttribute("focused").equalsIgnoreCase("true")) {
                            System.out.println(focussedElementIndex);
                            break;
                        } else {
                            // focussedElementIndex = focussedElementIndex + 1;
                            ++focussedElementIndex;
                        }
                    } else {
                        test.log(Status.WARNING, "Element Not present waiting till 5 seconds");
                    }
                }
                if (eventUtils.waitUntilElementIsPresent(driver, optionsLocator, 20)) {
                    List<WebElement> browseOptionsText = driver.findElements(optionsLocator);

                    for (WebElement webElement : browseOptionsText) {
                        if (eventUtils.waitUntilElementIsPresent(driver, webElement, 5)) {
                            System.out.println(webElement.getText());
                            if (webElement.getText().equalsIgnoreCase(expectedTab)) {
                                System.out.println(ExpectedButtonIndex);
                                isTabFound = true;
                                break;
                            } else {
                                // ExpectedButtonIndex=ExpectedButtonIndex+1;
                                ++ExpectedButtonIndex;
                            }
                        } else {
                            test.log(Status.WARNING, "Element Not present waiting till 5 seconds");
                        }
                    }

                    if (ExpectedButtonIndex < focussedElementIndex) {
                        for (int i = 1; i < ExpectedButtonIndex; i++) {
                            upButton(test);
                            Thread.sleep(1000);
                        }
                    } else {
                        for (int i = focussedElementIndex; i < ExpectedButtonIndex; i++) {
                            downButton(test);
                            Thread.sleep(1000);
                        }
                    }
                }

                okButton(test);

            } else {
                test.log(Status.WARNING, "Not able to find the tab options");
                isTabFound = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isTabFound = false;
        }

        return isTabFound;
    }
}
