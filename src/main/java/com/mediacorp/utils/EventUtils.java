package com.mediacorp.utils;

import java.awt.AWTException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.mediacorp.pages.BasePage;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;


public class EventUtils {

	/***
	 * Name of Function :- getTextOfWebelement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the Text of a Web Element
	 * @return String
	 */
	public String getTextOfWebelement(RemoteWebDriver driver, WebElement element, int timeOut, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (waitUntilElementIsPresent(driver, element, timeOut)) {
			text = element.getText();
			System.out.println("Text Of The Element:-" + text);
			report.logStatus("info", "Text of the element is:-" + text);
		} else {
			report.logStatus("error", "Unable to get the text of the element due to element not present");
		}
		return text;
	}	

	/***
	 * Name of Function :- getWebelements
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the list of a WebElements
	 * @return List
	 */
	public List<WebElement> getWebelements(RemoteWebDriver driver, By locator, int timeOut, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		List<WebElement> ele = null;
		if (locator != null) {
			if (waitUntilElementIsVisible(driver, locator, timeOut) ||
					waitUntilElementIsPresent(driver, locator, timeOut)) {
				ele = driver.findElements(locator);

				report.logStatus("info", "get webelements");
			} else {
				report.logStatus("error", "Unable to get webelement");
			}
		}
		return ele;
	}

	/***
	 * Name of Function :- getWebelement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the  WebElement
	 * @return WebElement
	 */
	public WebElement getWebelement(RemoteWebDriver driver, By locator, int timeOut, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		WebElement ele = null;
		if (locator != null) {
			if (waitUntilElementIsVisible(driver, locator, timeOut)) {
				ele = driver.findElement(locator);

				report.logStatus("info", "get webelement");
			} else {
				report.logStatus("error", "Unable to get webelement");
			}
		}
		return ele;
	}

	/***
	 * Name of Function :- clearContent
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- this method clear the text in the input field 
	 */
	public void clearContent(RemoteWebDriver driver, By locator) {
		driver.findElement(locator).sendKeys("");
	}

	/***
	 * Name of Function :- getTextOfWebelements
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the List of a WebElements by Locator
	 * @return ArrayList
	 */

	public ArrayList<String> getTextOfWebelements(RemoteWebDriver driver, By locator, int timeOut,
			ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		ArrayList<String> textContent = new ArrayList<>();
		if (locator != null) {
			if (waitUntilElementIsVisible(driver, locator, timeOut)) {
				List<WebElement> ele = driver.findElements(locator);
				for (int i = 0; i < ele.size(); i++) {
					text = ele.get(i).getText();
					textContent.add(text);
				}
				report.logStatus("info", "Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return textContent;
	}

	/***
	 * Name of Function :- getTextOfWebelement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the Text of a Web Element by Locator
	 * @return String
	 */

	public String getTextOfWebelement(RemoteWebDriver driver, By locator, int timeOut, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (locator != null) {
			if (waitUntilElementIsPresent(driver, locator, timeOut) ||
					waitUntilElementIsVisible(driver, locator, timeOut)) {

				if (Web_Constants.PROJECT.contains("WEB") || (Web_Constants.PROJECT.contains("MobileRW"))) {
					text = driver.findElement(locator).getAttribute("innerText");
				} else {

					try {
						text = driver.findElement(locator).getText();
					} catch (Exception e) {
						report.logStatus("error", "Unable to get the text of the element due to element not present");

					}

				}

				report.logStatus("info", "Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return text;
	}

	/***
	 * Name of Function :- getValueOfWebelement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the Text of a WebElement by Locator using Attribute
	 * @return String
	 */
	public String getValueOfWebelement(RemoteWebDriver driver, By locator, int timeOut, ExtentTest test,
			String value) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (locator != null) {
			if (waitUntilElementIsPresent(driver, locator, timeOut)) {
				text = driver.findElement(locator).getAttribute(value);

				report.logStatus("info", "Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return text;
	}
	/***
	 * Name of Function :- getSizeOfWebelement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To get the WebElement Dimension  
	 * @return Dimension
	 */
	public Dimension getSizeOfWebelement(RemoteWebDriver driver, WebElement element, int timeOut, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		Dimension count = null;

		if (waitUntilElementIsVisible(driver, element, timeOut)) {
			count = element.getSize();

			report.logStatus("info", "count of the element is:-" + count);
		} else {
			report.logStatus("error", "Unable to get the count of the element due to element not present");
		}

		return count;
	}

	/***
	 * Name of Function :- dismissAlert
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To dismiss Alert  
	 */

	public void dismissAlert(RemoteWebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/***
	 * Name of Function :- acceptAlert
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To accept Alert  
	 */
	public void acceptAlert(RemoteWebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/***
	 * Name of Function :- switchToFrame
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To switch to frame by Index
	 */
	public void switchToFrame(RemoteWebDriver driver, int index) {
		if (!Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			driver.switchTo().frame(index);
		}
	}

	/***
	 * Name of Function :- switchToFrame
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To switch to frame by Name
	 */
	public void switchToFrame(RemoteWebDriver driver, String name) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("Web") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			driver.switchTo().frame(name);
		}
	}

	/***
	 * Name of Function :- switchToDefaultContent
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To switch to Default Content
	 */
	public void switchToDefaultContent(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("Web") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			driver.switchTo().defaultContent();
		}
	}

	/***
	 * Name of Function :- switchToFrame
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To switch to Frame by web element
	 */
	public void switchToFrame(RemoteWebDriver driver, WebElement element) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("Web") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			if (waitUntilElementIsPresent(driver, element, 20)) {
				driver.switchTo().frame(element);
			}
		}
	}
	/***
	 * Name of Function :- switchToFrame
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To switch to Frame by locator
	 */
	public void switchToFrame(RemoteWebDriver driver, By locator) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("Web") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			if (waitUntilElementIsVisible(driver, locator, 10)) {
				driver.switchTo().frame(driver.findElement(locator));
				System.out.println("swiched to frame");
			}
		}
	}

	/***
	 * Name of Function :- switchToFrame
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To switch to Frame by index
	 */
	public void switchToFrameIndex(RemoteWebDriver driver, int num) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("Web") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			driver.switchTo().frame(num);
		}
	}

	/***
	 * Name of Function :- sleep
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To wait for particular seconds
	 */
	public void sleep(int seconds) {

		String sec = String.valueOf(seconds) + "000";
		Integer time = Integer.parseInt(sec);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}

	/***
	 * Name of Function :- resetApp
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- reseting the APP using driver
	 */
	@SuppressWarnings("unchecked")
	public void resetApp(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.contains("App")) {
			//			((AppiumDriver<WebElement>) driver).resetApp();
			sleep(5);
		}
	}

	/***
	 * Name of Function :- waitUntilPageLoaded
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To wait until page gets loaded
	 */
	public void waitUntilPageLoaded(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			Object status = "";

			while (!status.toString().equalsIgnoreCase("complete")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				status = js.executeScript("return document.readyState");
			}
		}
	}

	/***
	 * Name of Function :- clickTextField
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- to click element in text field ,  cursor comes beginning when clicked
	 */
	public void clickTextField(RemoteWebDriver driver, By locator)
	{

		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {

			List<WebElement> elements = driver.findElements(locator);
			int elementSize = elements.size();
			WebElement element = elements.get(elementSize - 1);

			int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
			int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
			int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			//				try {
			//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startX, startY)).perform();
			//					System.out.println("clicked"); 
			//				} catch (Exception e) {
			//				}

			try {
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startX,startY ))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				System.out.println("clicked"); 
				driver.perform(Collections.singletonList(sequence));

			} catch (Exception e) {
			}
			break;
		}

		}

	}

	/***
	 * Name of Function :- clickAndClearTextField
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- to click on element in text field and clear the field,  cursor comes beginning when clicked
	 */
	public void clickAndClearTextField(RemoteWebDriver driver, By locator , ExtentTest test)
	{

		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {

			List<WebElement> elements = driver.findElements(locator);
			int elementSize = elements.size();
			WebElement element = elements.get(elementSize - 1);

			int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
			int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
			int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			//				try {
			//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startX, startY)).perform();
			//					driver.findElement(locator).clear();
			//					
			//					System.out.println("clicked"); 
			//				} catch (Exception e) {
			//				}


			try {
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startX,startY ))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				System.out.println("clicked"); 
				driver.perform(Collections.singletonList(sequence));

			} catch (Exception e) {
			}
			break;
		}

		}

	}
	/***
	 * Name of Function :- scrollToTop
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Scroll To Top Of The Page
	 */
	public void scrollToTop(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)", "");
		}
	}
	/***
	 * Name of Function :- scrollElementToYouMayLikeRail
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Scroll To The WebElement
	 */
	public void scrollElementToYouMayLikeRail(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//div[contains(@class,'recommendation')]//h4[text()='You May Also Like']")));
		}
	}

	/***
	 * Name of Function :- getCurrentUrl
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To get the Current URL
	 * @return String
	 */
	public String getCurrentUrl(RemoteWebDriver driver, ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		String currentURL = "";
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			currentURL = driver.getCurrentUrl();
			basePage.logStatus("info", "Current Page Url:-" + currentURL);
		}
		return currentURL;
	}

	/***
	 * Name of Function :- getPageTitle
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To get the Current Page Title
	 * @return String
	 */
	public String getPageTitle(RemoteWebDriver driver, ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		basePage.logStatus("info", "Current Page Title:-" + driver.getTitle());
		return driver.getTitle();
	}

	/***
	 * Name of Function :- clickOnElement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To click the element by webElement
	 */
	public void clickOnElement(RemoteWebDriver driver, WebElement elementToClick, String typeOfElement, int timeOut,
			ExtentTest test) throws Exception {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;

		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (elementToClick != null) {

				if (waitUntilElementIsPresent(driver, elementToClick, timeOut)) {
					try {
						elementToClick.click();
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						elementToClick.click();
						isElementClicked = true;
					}
				}

			}
			break;
		}
		case "MobileRW": {
			if (elementToClick != null) {

				if (waitUntilElementIsPresent(driver, elementToClick, timeOut)) {
					try {
						elementToClick.click();
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						elementToClick.click();
						isElementClicked = true;
					}
				}

			}
			break;
		}
		case "AndroidApp": {
			if (elementToClick != null) {

				if (waitUntilElementIsPresent(driver, elementToClick, timeOut)) {
					try {
						elementToClick.click();
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						elementToClick.click();
						isElementClicked = true;
					}
				}

			}
			break;
		}
		case "iOSApp": {
			if (elementToClick != null) {

				if (waitUntilElementIsPresent(driver, elementToClick, timeOut)) {
					try {
						elementToClick.click();
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						elementToClick.click();
						isElementClicked = true;
					}
				}

			}
			break;
		}
		}

		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
		}
	}
	/***
	 * Name of Function :- clickOnElement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To click the element by locator
	 */
	public void clickOnElement(RemoteWebDriver driver, By elementToClick, String typeOfElement, int timeOut,
			ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;

		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}catch (Exception e) {
						try {

							clickUsingJavaScript(driver, elementToClick);

							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		case "MobileRW": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		case "AndroidApp": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		case "iOSApp": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		}

		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
		}
	}

	/***
	 * Name of Function :- clickOnElement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To click the element by locator
	 */
	// To click the element by locator
	public void clickOnElement(RemoteWebDriver driver, By elementToClick, String typeOfElement, int timeOut) {
		boolean isElementClicked = false;
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					} catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		case "MobileRW": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					} catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		case "AndroidApp": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					} catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		case "iOSApp": {
			if (elementToClick != null) {
				if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					} catch (Exception e) {
						try {
							driver.findElement(elementToClick).click();
							isElementClicked = true;
						} catch (Exception e1) {

						}
					}
				}
			}
			break;
		}
		}

		if (isElementClicked) {

			System.out.println("User clicked on :-" + typeOfElement);
		} else {
			System.out.println("User unable to click on :-" + typeOfElement);
		}
	}

	/***
	 * Name of Function :- clickOnElement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To click the element by webElement using javascript
	 */
	public void ClickOnElement(RemoteWebDriver driver, By elementToClick, String typeOfElement, int timeOut, ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (elementToClick != null) {
				if (waitUntilElementIsPresent(driver, elementToClick, 20)) {
					scrollToParticularElement(driver, elementToClick);
					try {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(elementToClick));
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}
				} else {
					basePage.logStatus("info", "Not able to find the element");
				}
			}
			break;
		}
		case "MobileRW": {
			if (elementToClick != null) {
				if (waitUntilElementIsPresent(driver, elementToClick, 20)) {
					scrollToParticularElement(driver, elementToClick);
					try {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(elementToClick));
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}
				} else {
					basePage.logStatus("info", "Not able to find the element");
				}
			}
			break;
		}
		case "AndroidApp": {
			if (elementToClick != null) {
				if (waitUntilElementIsPresent(driver, elementToClick, 20)) {
					scrollToParticularElement(driver, elementToClick);
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}
				} else {
					basePage.logStatus("info", "Not able to find the element");
				}
			}
			break;
		}
		case "iOSApp": {
			if (elementToClick != null) {
				if (waitUntilElementIsPresent(driver, elementToClick, 20)) {
					scrollToParticularElement(driver, elementToClick);
					try {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
						driver.findElement(elementToClick).click();
						isElementClicked = true;
					}
				} else {
					basePage.logStatus("info", "Not able to find the element");
				}
			}
			break;
		}
		}

		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
		}
	}

	/***
	 * Name of Function :- enterValue
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To enter the value in the field
	 */
	public void enterValue(RemoteWebDriver driver, By locator, String valueToEnter, String typeOfField, int timeOut,
			ExtentTest test) {
		boolean isValueEntered = false;
		BasePage basePage = new BasePage(driver, test);
		try {
			switch (Web_Constants.PROJECT) {
			case "WEB": {
				if (locator != null) {
					if (waitUntilElementIsVisible(driver, locator, timeOut)) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].value = '';",driver.findElement(locator));
						driver.findElement(locator).sendKeys(valueToEnter);
						isValueEntered = true;
					} else {
						basePage.logStatus("error",
								typeOfField + " is not visible waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("error", "Not able to find the element" + typeOfField);
				}
				break;
			}
			case "MobileRW": {
				if (locator != null) {
					if (waitUntilElementIsVisible(driver, locator, timeOut)) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].value = '';",driver.findElement(locator));
						driver.findElement(locator).sendKeys(valueToEnter);
						isValueEntered = true;
					} else {
						basePage.logStatus("error",
								typeOfField + " is not visible waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("error", "Not able to find the element" + typeOfField);
				}
				break;
			}
			case "AndroidApp": {
				if (locator != null) {
					if (waitUntilElementIsVisible(driver, locator, timeOut)) {
						driver.findElement(locator).clear();
						driver.findElement(locator).sendKeys(valueToEnter);
						isValueEntered = true;
					} else {
						basePage.logStatus("error",
								typeOfField + " is not visible waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("error", "Not able to find the element" + typeOfField);
				}
				break;
			}
			case "iOSApp": {
				if (locator != null) {
					if (waitUntilElementIsPresent(driver, locator, timeOut)) {
						driver.findElement(locator).clear();
						driver.findElement(locator).sendKeys(valueToEnter);
						isValueEntered = true;
					} else {
						basePage.logStatus("error",
								typeOfField + " is not visible waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("error", "Not able to find the element" + typeOfField);
				}
				break;
			}
			}
		} catch (Exception e) {
			basePage.logStatus("error", "User unable to enter " + valueToEnter + " in " + typeOfField);
		}
		if (isValueEntered) {
			basePage.logStatus("info", "User entered the value :-  " + valueToEnter + " in " + typeOfField);
		} else {
			basePage.logStatus("error", "User unable to enter " + valueToEnter + " in " + typeOfField);
		}
	}

	/***
	 * Name of Function :- enterValue
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To enter the value in the field
	 */
	public void enterValue(RemoteWebDriver driver, WebElement element, String valueToEnter, String typeOfField,
			int timeOut, ExtentTest test) {

		boolean isValueEntered = false;
		BasePage basePage = new BasePage(driver, test);
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (element != null) {
				if (waitUntilElementIsVisible(driver, element, timeOut)) {
					element.clear();
					element.sendKeys(valueToEnter);
					isValueEntered = true;
				} else {
					basePage.logStatus("error",
							typeOfField + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Not able to find the element" + typeOfField);
			}
			break;
		}
		case "MobileRW": {
			if (element != null) {
				if (waitUntilElementIsVisible(driver, element, timeOut)) {
					element.clear();
					element.sendKeys(valueToEnter);
					isValueEntered = true;
				} else {
					basePage.logStatus("error",
							typeOfField + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Not able to find the element" + typeOfField);
			}
			break;
		}
		case "AndroidApp": {
			if (element != null) {
				if (waitUntilElementIsVisible(driver, element, timeOut)) {
					element.clear();
					element.sendKeys(valueToEnter);
					isValueEntered = true;
				} else {
					basePage.logStatus("error",
							typeOfField + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Not able to find the element" + typeOfField);
			}
			break;
		}
		case "iOSApp": {
			if (element != null) {
				if (waitUntilElementIsPresent(driver, element, timeOut)) {
					element.clear();
					element.sendKeys(valueToEnter);
					isValueEntered = true;
				} else {
					basePage.logStatus("error",
							typeOfField + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Not able to find the element" + typeOfField);
			}
			break;
		}
		}

		if (isValueEntered) {
			basePage.logStatus("info", "User entered the value :-  " + valueToEnter + " in " + typeOfField);
		} else {
			basePage.logStatus("error", "User unable to enter " + valueToEnter + " in " + typeOfField);
		}
	}

	/***
	 * Name of Function :- moveToElementAndClick
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To move to the element and click
	 */
	public void moveToElementAndClick(RemoteWebDriver driver, WebElement element, String typeOfElement, int timeOut,
			ExtentTest test) {

		boolean isElementClicked = false;
		BasePage basePage = new BasePage(driver, test);
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (element != null) {
				if (waitUntilElementIsVisible(driver, element, timeOut)) {
					try {
						Actions act = new Actions(driver);
						act = act.moveToElement(element);
						act.click().build().perform();
						isElementClicked = true;
					} catch (Exception e) {
						basePage.logStatus("error", "Unable to Click on  the element:-" + typeOfElement);
					}
				} else {
					basePage.logStatus("info",
							typeOfElement + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("info", "Unable to find the element:-" + typeOfElement);
			}
			break;
		}
		case "MobileRW": {
			if (element != null) {
				if (waitUntilElementIsVisible(driver, element, timeOut)) {
					try {
						Actions act = new Actions(driver);
						act = act.moveToElement(element);
						act.click().build().perform();
						isElementClicked = true;
					} catch (Exception e) {
						basePage.logStatus("error", "Unable to Click on  the element:-" + typeOfElement);
					}
				} else {
					basePage.logStatus("info",
							typeOfElement + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("info", "Unable to find the element:-" + typeOfElement);
			}
			break;
		}

		}

		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable click " + typeOfElement);

		}

	}
	/***
	 * Name of Function :- moveToElementAndClick
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To move to the element and click
	 */
	public void moveToElementAndClick(RemoteWebDriver driver, By locator, String typeOfElement, int timeOut,
			ExtentTest test) {

		boolean isElementClicked = false;
		BasePage basePage = new BasePage(driver, test);
		switch (Web_Constants.PROJECT) {
		case "WEB": {

			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				Actions act = new Actions(driver);
				if (waitUntilElementIsVisible(driver, locator, timeOut)) {
					act.moveToElement(driver.findElement(locator)).click().build().perform();
					isElementClicked = true;
				} else {
					basePage.logStatus("info",
							typeOfElement + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, driver.findElement(locator));
				driver.findElement(locator).click();
				isElementClicked = true;
			}
			break;
		}
		case "MobileRW": {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				Actions act = new Actions(driver);
				if (waitUntilElementIsVisible(driver, locator, timeOut)) {
					act.moveToElement(driver.findElement(locator)).click().build().perform();
					isElementClicked = true;
				} else {
					basePage.logStatus("info",
							typeOfElement + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(mouseOverScript, driver.findElement(locator));
				driver.findElement(locator).click();
				isElementClicked = true;
			}
			break;
		}

		}

		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable click " + typeOfElement);

		}

	}

	/***
	 * Name of Function :- moveToElementAndClick
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To move to the locator and click
	 */
	public void moveToElementAndClick(RemoteWebDriver driver, By locator, int timeOut, ExtentTest test) {

		boolean isElementClicked = false;
		BasePage basePage = new BasePage(driver, test);
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			if (locator != null) {
				if (waitUntilElementIsVisible(driver, locator, timeOut)) {
					try {
						if (!Web_Constants.OS.equalsIgnoreCase("mac")) {
							String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript(mouseOverScript, driver.findElement(locator));
							driver.findElement(locator).click();
							isElementClicked = true;
						} else {
							clickOnElement(driver, locator, "", 10);
							isElementClicked = true;
						}
					} catch (Exception e) {
						basePage.logStatus("error", "Unable to click on the element:-");
					}

				} else {
					basePage.logStatus("info", "is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Unable to find the element:-");
			}
			break;
		}
		case "MobileRW": {
			if (locator != null) {
				if (waitUntilElementIsVisible(driver, locator, timeOut)) {
					try {
						if (!Web_Constants.OS.equalsIgnoreCase("mac")) {
							String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript(mouseOverScript, driver.findElement(locator));
							driver.findElement(locator).click();
						} else {
							clickOnElement(driver, locator, "", 10);
						}
						isElementClicked = true;
					} catch (Exception e) {
						basePage.logStatus("error", "Unable to click on the element:-");
					}

				} else {
					basePage.logStatus("info", "is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Unable to find the element:-");
			}
			break;
		}
		case "AndroidApp": {
			if (locator != null) {
				if (waitUntilElementIsVisible(driver, locator, timeOut)) {
					try {
						clickOnElement(driver, locator, "", 10);
						isElementClicked = true;
					} catch (Exception e) {
						basePage.logStatus("error", "Unable to click on the element:-");
					}

				} else {
					basePage.logStatus("info", "is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Unable to find the element:-");
			}
			break;
		}
		case "iOSApp": {
			if (locator != null) {
				if (waitUntilElememtIsClickable(driver, locator, 3)) {
					try {
						clickOnElement(driver, locator, "", 10);
						isElementClicked = true;
					} catch (Exception e) {
						basePage.logStatus("error", "Unable to click on the element:-");
					}

				} else {
					basePage.logStatus("info", "is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("error", "Unable to find the element:-");
			}
			break;
		}
		}

		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + locator.toString());
		} else {
			basePage.logStatus("error", "User unable click " + locator.toString());

		}

	}


	/***
	 * Name of Function :- selectValueFromOptions
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To select the value from the drop down
	 * @return boolean
	 */
	@SuppressWarnings("null")
	public boolean selectValueFromOptions(RemoteWebDriver driver, List<WebElement> elements, String input, int timeout,
			ExtentTest test) {


		BasePage basePage = new BasePage(driver, test);
		boolean isValueSelected = false;
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			try {
				String selectinggender = "//li[contains(@class,'select')]//span[contains(text(),'"+input+"')]";
				clickOnElement(driver, By.xpath(selectinggender), input, 20, test);
				isValueSelected = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case "MobileRW": {
			try {
				String selectinggender = "//li[contains(@class,'select')]//span[contains(text(),'"+input+"')]";
				clickOnElement(driver, By.xpath(selectinggender), input, 20, test);
				isValueSelected = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case "AndroidApp": {
			if (elements == null) {
				String optionText = "";
				for (WebElement option : elements) {
					optionText = option.getAttribute("name");
					if (optionText.equalsIgnoreCase(input)) {
						if (waitUntilElementIsPresent(driver, option, timeout)) {
							try {
								clickOnElement(driver, option, input, timeout, test);
								isValueSelected = true;
								break;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				basePage.logStatus("error", "Element not found");
			}
			break;
		}
		case "iOSApp": {
			if (elements == null) {
				String optionText = "";
				for (WebElement option : elements) {
					optionText = option.getText();
					if (optionText.equalsIgnoreCase(input)) {
						if (waitUntilElementIsPresent(driver, option, timeout)) {
							try {
								clickOnElement(driver, option, input, timeout, test);
								isValueSelected = true;
								break;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				basePage.logStatus("error", "Element not found");
			}
			break;
		}
		}

		if (isValueSelected) {
			basePage.logStatus("info", "User selected the  option:-" + input);
		} else {
			basePage.logStatus("error", "User unable to select the  option:-" + input);
		}
		return isValueSelected;

	}

	/***
	 * Name of Function :- clickValueFromOption
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To select the value from the drop down
	 * @return boolean
	 */
	public boolean clickValueFromOption(WebElement ele, String option, String value) {
		Select sel = new Select(ele);

		if (value.equalsIgnoreCase("value")) {
			if (value.equalsIgnoreCase("value")) {
				sel.selectByValue(option);
				return true;
			} else {
				return false;
			}
		} else if (value.equalsIgnoreCase("index")) {
			if (value.equalsIgnoreCase("index")) {
				sel.selectByValue(option);
				return true;
			} else {
				return false;
			}
		} else if (value.equalsIgnoreCase("VisibleText")) {
			if (value.equalsIgnoreCase("VisibleText")) {
				sel.selectByValue(option);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/***
	 * Name of Function :- switchToLatestWindow
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To switch To Latest Window
	 */
	public void switchToLatestWindow(RemoteWebDriver driver, ExtentTest test) {

		BasePage basePage = new BasePage(driver, test);
		Set<String> windows = driver.getWindowHandles();
		for (String winHandle : windows) {
			driver.switchTo().window(winHandle);
			sleep(2);
			basePage.logStatus("info", "Switched to the window");
			sleep(2);
		}
	}

	/***
	 * Name of Function :- switchToParenttWindow
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To switch To parent Window
	 */
	public void switchToParenttWindow(RemoteWebDriver driver, ExtentTest test) {

		if ((Web_Constants.PROJECT.equalsIgnoreCase("MobileRW"))
				|| (Web_Constants.PROJECT.equalsIgnoreCase("WEB"))) {
			Set<String> windows = driver.getWindowHandles();
			List<String> windowHandles = new ArrayList<String>(windows);
			driver.switchTo().window(windowHandles.get(0));
		}
	}

	/***
	 * Name of Function :- getCurrentWindowSessionId
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To get the Window SessionID
	 * @return String 
	 */
	public String getCurrentWindowSessionId(RemoteWebDriver driver, ExtentTest test) {
		String sessionId = driver.getWindowHandle();
		return sessionId;
	}

	/***
	 * Name of Function :- switchToParticularWindow
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Switch to particular Window Session 
	 */
	public void switchToParticularWindow(RemoteWebDriver driver, String sessionId, ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		driver.switchTo().window(sessionId);
		basePage.logStatus("info", "user is on particular window");
	}

	/***
	 * Name of Function :- switchToAnyWindow
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Switch to particular Window number
	 */
	public void switchToAnyWindow(RemoteWebDriver driver, ExtentTest test, int num) {

		String windowHandle = driver.getWindowHandle();
		ArrayList tabs = new ArrayList(driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window((String) tabs.get(num));
		driver.switchTo().window(windowHandle);

	}
	/***
	 * Name of Function :- getCurrentUrlofchildWindow
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To get current url of child window
	 * @return String 
	 */
	public String getCurrentUrlofchildWindow(RemoteWebDriver driver, ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		String url = "";
		Set<String> windows = driver.getWindowHandles();
		String parentwindow = driver.getWindowHandle();
		for (String winHandle : windows) {
			driver.switchTo().window(winHandle);
			if (!parentwindow.equalsIgnoreCase(winHandle)) {
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					url = getCurrentUrl(driver, test);
				}
				basePage.logStatus("info", "Fetched the current url of child window");
				driver.close();
			}
			driver.switchTo().window(parentwindow);
		}
		return url;
	}

	/***
	 * Name of Function :- scrollToParticularElement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to particular element by web element
	 */
	public void scrollToParticularElement(RemoteWebDriver driver, WebElement element) {
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
					|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/***
	 * Name of Function :- scrollToParticularElement
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to particular element by locator
	 */
	public void scrollToParticularElement(RemoteWebDriver driver, By locator) {
		try {
			waitUntilElementIsPresent(driver, locator, 10);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
					|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-400)");
				waitUntilElementIsVisible(driver, locator, 5);
			}
		} catch (Exception e) {

		}
	}
	/***
	 * Name of Function :- scrollTillEnd
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-Scroll To Page End
	 */
	public void scrollTillEnd(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i < 30; i++) {
			try {
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB")
						|| Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
					Thread.sleep(500);
				}
			} catch (Exception e) {
				break;
			}
		}
	}
	/***
	 * Name of Function :- waitUntilElementIsVisible
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until element is visible by web element
	 * @return boolean
	 */
	public boolean waitUntilElementIsVisible(RemoteWebDriver driver, WebElement element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
		}
		return isElementEnabled;
	}
	/***
	 * Name of Function :- waitUntilElementIsVisible
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until element is visible by locator
	 * @return boolean
	 */
	public boolean waitUntilElementIsVisible(RemoteWebDriver driver, By locator, int timeOut) {
		boolean isElementEnabled = false;
		//		if (waitUntilElementIsPresent(driver, locator, timeOut)) {
		if (locator != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

				switch (Web_Constants.PROJECT) {

				case "iOSApp":
				{
					wait.until(ExpectedConditions.presenceOfElementLocated(locator));
					isElementEnabled = true;
					break;
				}
				case "AndroidApp": {
					try {
						for (int i = 0; i < timeOut*2; i++) {
							try {
								if (driver.findElement(locator).isDisplayed()) {
									isElementEnabled = true;
									break;
								}
							} catch (Exception e) {
							}
							Thread.sleep(500);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				default :
				{
					isElementEnabled=waitUntilElementIsDisplayed(driver, locator, timeOut);
				}
				}

			} catch (Exception e) {
				isElementEnabled = false;

			}
		}
		//		}
		return isElementEnabled;
	}
	
	
	public  String getTimeStampEpoch() {
		Instant instant = Instant.now();
		// java.util.Date date= new java.util.Date();
		Long time = instant.getEpochSecond();
		time = time;
		return time.toString();
	}
	
	/***
	 * Name of Function :- waitUntilElememtIsClickable
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until element is clickable
	 * @return boolean
	 */
	public boolean waitUntilElememtIsClickable(RemoteWebDriver driver, WebElement element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
		}
		return isElementEnabled;
	}
	/***
	 * Name of Function :- waitUntilElememtIsClickable
	 * Developed By :- Ifocus Automation Team
	 * Organization Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until element is clickable
	 * @return boolean
	 */
	public boolean waitUntilElememtIsClickable(RemoteWebDriver driver, By locator, int timeOut) {
		boolean isElementEnabled;
		try {
			if (Web_Constants.OS.equalsIgnoreCase("mac")) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			// e.printStackTrace();
			isElementEnabled = false;
		}
		return isElementEnabled;
	}

	/***
	 * Name of Function :- waitUntilElementIsPresent
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until element is present by Webelement
	 * @return boolean
	 */
	public boolean waitUntilElementIsPresent(RemoteWebDriver driver, WebElement element, int timeout) {
		boolean isElementPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementPresent = true;
		} catch (Exception e) {
			isElementPresent = false;
			e.printStackTrace();
		}
		return isElementPresent;
	}
	/***
	 * Name of Function :- waitUntilElementIsPresent
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until element is present by locator
	 * @return boolean
	 */
	public boolean waitUntilElementIsPresent(RemoteWebDriver driver, By locator, int timeout) {
		boolean isElementPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementPresent = true; 
		} catch (Exception e) {
			isElementPresent = false;
		}
		return isElementPresent;
	}
	/***
	 * Name of Function :- refreshPage
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To refresh the page
	 */
	public void refreshPage(RemoteWebDriver driver, ExtentTest test) {
		EventUtils eventutils = new EventUtils();
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			boolean isPageRefresh = false;
			BasePage report = new BasePage(driver, test);
			try {
				if (driver != null) {
					driver.navigate().refresh();
					isPageRefresh = true;
				}
			} catch (Exception e) {

			}
			if (!isPageRefresh) {
				report.logStatus("error", "Unable to refresh the page");
			}
			eventutils.sleep(5);
		}
	}
	/***
	 * Name of Function :- scrollToElement
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to the specific element
	 */
	public void scrollToElement(RemoteWebDriver driver, WebElement element) {
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
		} catch (StaleElementReferenceException e) {
			sleep(2);
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
		}
	}
	/***
	 * Name of Function :- scrollToElement
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to the specific Locator
	 */
	public void scrollToElement(RemoteWebDriver driver, By locator) {
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
			}
		} catch (Exception e) {
			sleep(2);
			try {
				if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
				}
			} catch (Exception e1) {

			}
		}
	}
	/***
	 * Name of Function :- mouseHoverToElement
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To mousehover to element by webelement
	 */
	public void mouseHoverToElement(RemoteWebDriver driver, WebElement element, int timeout, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				if (Web_Constants.OS.equalsIgnoreCase("mac")) {
					String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript(mouseOverScript, element);
				}
			} else {
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception e) {
			report.logStatus("error", "Unable to mouse hover to the element");
		}
	}
	/***
	 * Name of Function :- mouseHoverToElement
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To mousehover to element by locator
	 */
	public void mouseHoverToElement(RemoteWebDriver driver, By locator, int timeout, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		try {

			if (waitUntilElementIsPresent(driver, locator, timeout)) {
				if (!(Web_Constants.PROJECT.contains("App") || Web_Constants.PROJECT.contains("TV"))) {
					if (Web_Constants.OS.equalsIgnoreCase("mac")) {

						// this below line of code is used to mouse over on card : mac , actions don't
						// work
						String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript(mouseOverScript, driver.findElement(locator));
					} else {
						Actions action = new Actions(driver);
						action.moveToElement(driver.findElement(locator)).build().perform();
					}
				}
				sleep(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			report.logStatus("error", "Unable to mouse hover to the element");
		}
	}
	/***
	 * Name of Function :- clickUsingJavaScript
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-to click on the webelement using javascript locator
	 */
	public void clickUsingJavaScript(RemoteWebDriver driver, By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));

	}
	/***
	 * Name of Function :- scrollToElementInSearch
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to element
	 */
	public void scrollToElementInSearch(RemoteWebDriver driver, WebElement element) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,200)", "");
		}
	}
	/***
	 * Name of Function :- scrollDownBy500
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll by co-ordinates 500
	 */
	public void scrollDownBy500(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,900)");
		}
	}
	/***
	 * Name of Function :- scrollDownBy200
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll by co-ordinates 200
	 */
	public void scrollDownBy200(RemoteWebDriver driver) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,400)");
		}
	}

	/***
	 * Name of Function :- navigateBack
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Navigate Back 
	 */
	public void navigateBack(RemoteWebDriver driver, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			driver.navigate().back();
			break;
		}
		case "MobileRW": {
			driver.navigate().back();
			break;
		}
		case "AndroidApp": {
			driver.navigate().back();
			break;
		}
		case "iOSApp": {
			if (waitUntilElementIsPresent(driver, By.xpath("//*[contains(@name,'Back')]"), 2)) {
				driver.findElement(By.xpath("//*[contains(@name,'Back')]")).click();
				report.logStatus("info", "User clicked on Back BUtton");
			}
			break;
		}
		}

	}

	/***
	 * Name of Function :- scrollToParticularElementInPage
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to particular element by locator
	 */
	public void scrollToParticularElementInPage(RemoteWebDriver driver, By locator) {
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
				sleep(1);
			}
		} catch (Exception e) {

		}
	}
	/***
	 * Name of Function :- scrollToParticularElementInPage
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to particular element by WebElement
	 */
	public void scrollToParticularElementInPage(RemoteWebDriver driver, WebElement ele) {
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", ele);
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
				sleep(1);
			}
		} catch (Exception e) {

		}
	}
	/***
	 * Name of Function :- navigateToGivenUrl
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Navigate to Given URL
	 */
	public void navigateToGivenUrl(RemoteWebDriver driver, String url, ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		try {
			if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {


				try {
					driver.get(url);
					
				} catch (Exception e) {
					driver.navigate().to(url);
				}

			}

		} catch (Exception e) {

			report.logStatus("error", "Unable to navigate to specified url");
		}
	}
	/***
	 * Name of Function :- getTextOfAlert
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To return the text for the Alert
	 * @return String
	 */
	public String getTextOfAlert(RemoteWebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}
	/***
	 * Name of Function :- scrollDownUp
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To scroll to the Bottom and Top Of the Page
	 */
	public void scrollDownUp(RemoteWebDriver driver) {
		try {
			scrollTillEnd(driver);
			scrollToTop(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/***
	 * Name of Function :- waitUntilElementAreVisible
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To wait until elements are visible by web element
	 * @return boolean
	 */
	public boolean waitUntilElementAreVisible(RemoteWebDriver driver, List<WebElement> element, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} catch (Exception e) {
			isElementEnabled = false;
		}
		return isElementEnabled;
	}

	/***
	 * Name of Function :- close
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Close The browser
	 */
	public void close(RemoteWebDriver driver) {
		driver.close();
	}

	/***
	 * Name of Function :- navigateToUrl
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Navigate to Given URL
	 */
	public void navigateToUrl(RemoteWebDriver driver, String URL) {
		if (Web_Constants.PROJECT.equalsIgnoreCase("WEB") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			driver.get(URL);
		}
	}
	/***
	 * Name of Function :- navigateBack
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Navigate to Back Page 
	 */
	public void navigateBack(RemoteWebDriver driver) {
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			driver.navigate().back();
			break;
		}
		case "MobileRW": {
			driver.navigate().back();
			break;
		}
		case "AndroidApp": {
			driver.navigate().back();
			break;
		}
		case "iOSApp": {
			if (waitUntilElementIsPresent(driver, By.xpath("//*[contains(@name,'Back')]"), 10)) {
				driver.findElement(By.xpath("//*[contains(@name,'Back')]")).click();
			}
			break;
		}
		}

	}
	/***
	 * Name of Function :- navigateForward
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Navigate to next Page 
	 */
	public void navigateForward(RemoteWebDriver driver, String URL) {
		driver.navigate().forward();
	}

	/***
	 * Name of Function :- randomVerticalSwipe
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe The down 
	 */
	public void randomVerticalSwipe(RemoteWebDriver driver, int swipeLimit) {
		switch (Web_Constants.PROJECT) {
		case "AndroidApp": {


			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			for (int i = 0; i < swipeLimit; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
				}
			}

			break;
		}
		case "iOSApp": {


			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			for (int i = 0; i < swipeLimit; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
				}
			}

			break;
		}
		}

	}
	/***
	 * Name of Function :- verticalSwipe
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe The down 
	 */
	public boolean verticalSwipe(RemoteWebDriver driver, By locator, int swipeLimit) {
		boolean isElementFound=false;
		switch (Web_Constants.PROJECT)
		{

		case "AndroidApp": {
			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				if (waitUntilElementIsPresent(driver, locator, 3)) {
					isElementFound=true;
					break;
				}
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(2000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}
			}

			break;
		}
		case "iOSApp": {
			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				if (waitUntilElememtIsClickable(driver, locator, 3)) {
					isElementFound=true;
					break;
				}
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(1000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
				}
			}

			break;
		}
		}
		return isElementFound;

	}

	/***
	 * Name of Function :- verticalSwipeToHideKeyBoard
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Hide The Keyboard 
	 */
	public void verticalSwipeToHideKeyBoard(RemoteWebDriver driver, int swipeLimit) {
		switch (Web_Constants.PROJECT) {
		case "WEB": {

			break;
		}
		case "MobileRW": {

			break;
		}
		case "AndroidApp": {


			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.30);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}

			}

			break;
		}
		case "iOSApp": {


			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.30);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}

			}

			break;
		}
		}

	}


	/***
	 * Name of Function :- hideKeyboard
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Hide The Keyboard 
	 */
	@SuppressWarnings("rawtypes")
	public void hideKeyboard(RemoteWebDriver driver) {

		if ((Web_Constants.PROJECT.contains("AndroidApp"))||(Web_Constants.PROJECT.contains("MobileRW"))) {

			((AndroidDriver) driver).hideKeyboard();
		}
	}
	/***
	 * Name of Function :- verticalSwipereverse
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe The Reverse
	 */
	@SuppressWarnings("rawtypes")
	public void verticalSwipereverse(RemoteWebDriver driver, By locator, int swipeLimit) throws InterruptedException {
		boolean isElementFound = false;
		switch (Web_Constants.PROJECT) {

		case "AndroidApp": {

			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int starty = (int) (height * 0.30);
			int endy = (int) (height * 0.70);
			for (int i = 0; i < swipeLimit; i++) {
				if (waitUntilElementIsPresent(driver, locator, 3)) {
					isElementFound=true;
					break;
				}
				try {

					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(3000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (waitUntilElementIsVisible(driver, locator, 1)) {
					break;
				}
			}

			break;
		}
		case "iOSApp": {

			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int starty = (int) (height * 0.30);
			int endy = (int) (height * 0.70);
			for (int i = 0; i < swipeLimit; i++) {
				try {

					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (waitUntilElementIsVisible(driver, locator, 1)) {
					break;
				}
			}

			break;
		}
		}

	}

	/***
	 * Name of Function :- verticalSwipereverse
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe The Reverse
	 */
	@SuppressWarnings("rawtypes")
	public void verticalSwipereverse(RemoteWebDriver driver, WebElement element, int swipeLimit)
			throws InterruptedException {
		switch (Web_Constants.PROJECT) {

		case "AndroidApp": {

			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int starty = (int) (height * 0.30);
			int endy = (int) (height * 0.70);
			for (int i = 0; i < swipeLimit; i++) {
				try {

					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (waitUntilElementIsPresent(driver, element, 1)) {
					break;
				}
			}

			break;
		}
		case "iOSApp": {

			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int starty = (int) (height * 0.30);
			int endy = (int) (height * 0.70);
			for (int i = 0; i < swipeLimit; i++) {
				try {

					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (waitUntilElementIsPresent(driver, element, 1)) {
					break;
				}
			}

			break;
		}
		}

	}

	/***
	 * Name of Function :- verticalSwipereverseLoginPage
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe The Reverse
	 */
	public void verticalSwipereverseLoginPage(RemoteWebDriver driver, By locator, int swipeLimit)
			throws InterruptedException {
		switch (Web_Constants.PROJECT) {

		case "iOSApp": {

			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int starty = (int) (height * 0.10);
			int endy = (int) (height * 0.4);
			for (int i = 0; i < swipeLimit; i++) {
				try {

					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();




					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (waitUntilElementIsPresent(driver, locator, 1)) {
					break;
				}
			}

			break;
		}
		}

	}

	public void longTouch(RemoteWebDriver driver, WebElement element, int duration) {
		switch (Web_Constants.PROJECT) {

		case "AndroidApp": 
		case "iOSApp": 
		{

			//			TouchAction touchAction = new TouchAction((PerformsTouchActions) driver).longPress(new LongPressOptions()
			//					.withElement(ElementOption.element(element)).withDuration(Duration.ofSeconds(duration))).release()
			//					.perform();


			Point location = element.getLocation();
			Dimension size = element.getSize();

			Point centerOfElement = getCenterOfElement(location, size);

			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofSeconds(2)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));





			break;
		}

		}


	}
	private Point getCenterOfElement(Point location, Dimension size) {
		return new Point(location.getX() + size.getWidth() / 2,
				location.getY() + size.getHeight() / 2);
	}
	/***
	 * Name of Function :- verticalSwipereverseLoginPage
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe The Reverse
	 */
	public void longTouch(RemoteWebDriver driver, By locator, int duration) {
		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {

			//			TouchAction touchAction = new TouchAction((PerformsTouchActions) driver)
			//					.longPress(new LongPressOptions().withElement(ElementOption.element(driver.findElement(locator)))
			//							.withDuration(Duration.ofSeconds(duration)))
			//					.release().perform();

			WebElement element=driver.findElement(locator);
			Point location = element.getLocation();
			Dimension size = element.getSize();

			Point centerOfElement = getCenterOfElement(location, size);

			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofSeconds(2)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));	


			break;
		}

		}

	}

	/***
	 * Name of Function :- terminateApp
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To terminate the App's
	 */
	public void terminateApp(RemoteWebDriver driver) {

		switch (Web_Constants.PROJECT) {

		case "AndroidApp": {
			if(Web_Constants.ENV.equalsIgnoreCase("prod")) {
				((AndroidDriver) driver).terminateApp(Web_Constants.APP_PACKAGE);
			}else {
				((AndroidDriver) driver).terminateApp(Web_Constants.Preprod_APP_PACKAGE);
			}
			break;
		}
		case "iOSApp": {
			if(Web_Constants.ENV.equalsIgnoreCase("prod")) {
				((IOSDriver) driver).terminateApp(Web_Constants.BUNDLE_ID);
			}else {
				((IOSDriver) driver).terminateApp(Web_Constants.preprod_BUNDLE_ID);	
			}
			break;
		}
		}

	}

	/***
	 * Name of Function :- activateApp
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To activate the App's
	 */
	public void activateApp(RemoteWebDriver driver) {
		switch (Web_Constants.PROJECT) {

		case "AndroidApp": {
			if(Web_Constants.ENV.equalsIgnoreCase("prod")) {
				((AndroidDriver) driver).activateApp(Web_Constants.APP_PACKAGE);
			}else {
				((AndroidDriver) driver).activateApp(Web_Constants.Preprod_APP_PACKAGE);
			}
			break;
		}
		case "iOSApp": {
			if(Web_Constants.ENV.equalsIgnoreCase("prod")) {
				((IOSDriver) driver).activateApp(Web_Constants.BUNDLE_ID);
			}else {
				((IOSDriver) driver).activateApp(Web_Constants.preprod_BUNDLE_ID);
			}
			break;
		}
		}

	}



	/***
	 * Name of Function :- clickUsingCoordinate
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Click on Screen co-ordinates 
	 */
	public void clickUsingCoordinate(RemoteWebDriver driver,double startX,double endX)
	{
		int screenwidth = driver.manage().window().getSize().getWidth();
		int screenheight = driver.manage().window().getSize().getHeight();
		int startx = (int) (screenwidth * startX);
		int starty = (int) (screenheight * endX);

		try {
			//			new TouchAction((AppiumDriver<WebElement>) driver).tap(PointOption.point(startx, starty)).release()
			//			.perform();


			try {
			    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			    Sequence sequence = new Sequence(finger1, 1)
			        .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startx,starty ))
			        .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			        .addAction(new Pause(finger1, Duration.ofMillis(200)))
			        .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			    System.out.println("clicked"); 
			    driver.perform(Collections.singletonList(sequence));

			} catch (Exception e) {
			}

		} catch (Exception e) {
		}

	}

	/***
	 * Name of Function :- horizontalSwipe
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Swipe the horizontal direction
	 */

	public void horizontalSwipe(RemoteWebDriver driver, By locator, By expectedLocator, int swipeCount) {
		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {
			for (int i = 0; i < swipeCount; i++) {

				List<WebElement> elements = driver.findElements(locator);
				int elementSize = elements.size();
				WebElement element = elements.get(elementSize - 1);

				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startX, startY))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(endX, endY)).release().perform();
					//					if (waitUntilElementIsVisible(driver, expectedLocator, 1)) {
					//						break;
					//					}


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
					if (waitUntilElementIsVisible(driver, expectedLocator, 1)) {
						break;
					}


				} catch (Exception e) {
				}
			}
			break;
		}

		}

	}
	/***
	 * Name of Function :- longTouchScreen
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- Long Press the Screen
	 */
	public void longTouchScreen(RemoteWebDriver driver, By locator) {
		switch (Web_Constants.PROJECT) {
		case "WEB": {

			break;
		}
		case "MobileRW": {

			break;
		}
		case "AndroidApp":
		case "iOSApp": {

			//			int x = driver.findElement(locator).getSize().getWidth() / 2;
			//			int y = driver.findElement(locator).getSize().getHeight() / 2;
			//
			//			(new TouchAction((AppiumDriver<WebElement>) driver)).longPress(PointOption.point(x, y))
			//			.tap(PointOption.point(x, y)).perform();
			//			(new TouchAction((AppiumDriver<WebElement>) driver)).longPress(PointOption.point(x, y)).perform();


			WebElement element=driver.findElement(locator);
			Point location = element.getLocation();
			Dimension size = element.getSize();

			Point centerOfElement = getCenterOfElement(location, size);

			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofSeconds(2)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));	



			break;
		}

		}
	}

	/***
	 * Name of Function :- verticalSwipe
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Swipe The Down
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void verticalSwipe(RemoteWebDriver driver, int swipeLimit) {
		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				try {

					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();
					//				
					//				

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					        .addAction(new Pause(finger1, Duration.ofMillis(1000)))
					        .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}




			}
			break;
		}

		}

	}

	/***
	 * Name of Function :- doubleTaplongTouchScreen
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- functioning the Double Tab the Screen 
	 */
	public void doubleTaplongTouchScreen(RemoteWebDriver driver, By locator) {
		switch (Web_Constants.PROJECT) {
		case "iOSApp":
		case "AndroidApp": {

			//			int x = driver.findElement(locator).getSize().getWidth() / 2;
			//			int y = driver.findElement(locator).getSize().getHeight() / 2;
			//			(new TouchAction((AppiumDriver<WebElement>) driver)).tap(PointOption.point(x, y)).perform()
			//			.longPress(PointOption.point(x, y)).perform();
			//			// (new TouchAction((AppiumDriver<WebElement>) driver))




			WebElement element =driver.findElement(locator);
			Point location = element.getLocation();
			Dimension size = element.getSize();

			Point centerOfElement = getCenterOfElement(location, size);

			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));


			break;

		}

		}




	}

	/***
	 * Name of Function :- hamburgerMenuVerticalSwipe
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- functioning the Swipe down 
	 */
	public void hamburgerMenuVerticalSwipe(RemoteWebDriver driver, By locator, int swipeLimit) {
		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {
			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 4;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));



				} catch (Exception e) {
				}
				if (waitUntilElementIsPresent(driver, locator, 1)) {
					break;
				}
			}

			break;
		}

		}


	}
	/***
	 * Name of Function :- tapElement
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To click on the Element
	 */
	public void tapElement(RemoteWebDriver driver, By locator) {
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			driver.findElement(locator).click();
			break;
		}
		case "MobileRW": {
			driver.findElement(locator).click();
			break;
		}
		case "iOSApp":
		case "AndroidApp": {

			//			int x = driver.findElement(locator).getLocation().getX();
			//			int y = driver.findElement(locator).getLocation().getY();
			//			(new TouchAction((AppiumDriver<WebElement>) driver)).tap(PointOption.point(x, y)).perform();

			WebElement element=driver.findElement(locator);
			Point location = element.getLocation();
			Dimension size = element.getSize();

			Point centerOfElement = getCenterOfElement(location, size);

			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));




			break;

		}

		}

	}
	/***
	 * Name of Function :- verticalswipeReverse
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Swipe the screen down 
	 */
	public void verticalswipeReverse(RemoteWebDriver driver, int count) throws InterruptedException {

		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {

			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = height / 4;
			// int endy = (int) (height * 0.10);

			for (int i = 0; i < count; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, endy))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					//					.moveTo(PointOption.point(startx, starty)).release().perform();


					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(1000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));


				} catch (Exception e) {
				}
			}

			break;
		}

		}

	}



	public void verticalswipeReverseCDP(RemoteWebDriver driver, int count) throws InterruptedException {

		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {

			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.65);
			int endy = height / 3;
			// int endy = (int) (height * 0.10);

			for (int i = 0; i < count; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, endy))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					//					.moveTo(PointOption.point(startx, starty)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));


				} catch (Exception e) {
				}
			}

			break;
		}

		}

	}

	/***
	 * Name of Function :- tap_SpaceBar
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Tap on Keyboard SpaceBar
	 * @param driver
	 */
	public void tap_SpaceBar(RemoteWebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.SPACE).build().perform();
	}

	/***
	 * Name of Function :- tap_UpArrowKey
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Tap on Keyboard UpArrowKey
	 * @param driver
	 */
	public void tap_UpArrowKey(RemoteWebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_UP).build().perform();
	}

	/***
	 * Name of Function :- tap_DownArrowKey
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Tap on Keyboard DownArrowKey
	 * @param driver
	 */
	public void tap_DownArrowKey(RemoteWebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
	}

	/***
	 * Name of Function :- tap_LeftArrowKey
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Tap on Keyboard LeftArrowKey
	 * @param driver
	 */
	public void tap_LeftArrowKey(RemoteWebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_LEFT).build().perform();
	}

	/***
	 * Name of Function :- tap_RightArrowKey
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Tap on Keyboard RightArrowKey
	 * @param driver
	 */
	public void tap_RightArrowKey(RemoteWebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_RIGHT).build().perform();
	}

	/***
	 * Name of Function :- tap_EscapeKey
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Tap on Keyboard EscapeKey
	 * @param driver
	 */
	public void tap_EscapeKey(RemoteWebDriver driver) throws AWTException {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	/***
	 * Name of Function :- getAttributeValueOfWebelement
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Get the Text of the particular  Attribute Value of the Element  
	 * @param driver
	 * @param locator
	 * @param Attribute 
	 * @param timeOut
	 * @param test
	 * 
	 * @return String 
	 */
	public String getAttributeValueOfWebelement(RemoteWebDriver driver, By locator, String tagName, int timeOut,
			ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (locator != null) {
			if (waitUntilElementIsVisible(driver, locator, timeOut)) {

				text = driver.findElement(locator).getAttribute(tagName);
				report.logStatus("info", "Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return text;
	}

	

	/***
	 * Name of Function :- returnHexColorCode
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Get the colour code of the element 
	 * @param driver
	 * @param locator
	 * @param test 
	 * @param colourCode
	 * 
	 * @return String 
	 */
	public String returnHexColorCode(RemoteWebDriver driver, ExtentTest test, By locator, String colorCode) {
		String str = null;
		String c1 = null;

		if (waitUntilElementIsVisible(driver, locator, 2)) {
			str = driver.findElement(locator).getCssValue(colorCode);

			int startIndex = str.indexOf('(');
			int endIndex = str.indexOf(')');

			str = str.substring(startIndex + 1, endIndex);
			try {
				c1 = Color.fromString("rgb(" + str + ")").asHex();
			} catch (Exception e) {
				c1 = Color.fromString("rgba(" + str + ")").asHex();
			}
			c1 = Color.fromString(c1).asHex();
		}
		return c1;
	}

	/***
	 * Name of Function :- cleartext
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To clear the value in the text field  
	 * @param driver
	 * @param locator
	 * @param test 
	 *
	 */
	public void cleartext(RemoteWebDriver driver, By locator, ExtentTest test) {
		try {
			for (int i = 0; i < 3; i++) {
				WebElement textField = driver.findElement(locator);
				clickOnElement(driver, textField, "clickOnElement", 20, test);
				String c = Keys.BACK_SPACE.toString();
				textField.sendKeys(c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c
						+ c + c + c + c + c + c + c + c + c + c + c + c + c + c);
				if (textField.equals("")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/***
	 * Name of Function :- cleartextInputField
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To clear the value in the text field  
	 * @param driver
	 * @param locator
	 * @param test 
	 *
	 */
	public void cleartextInputField(RemoteWebDriver driver, By locator, ExtentTest test) {
		// div[contains(@class,'pin__inputs')]/input
		List<WebElement> ele = driver.findElements(locator);
		int count = ele.size();
		try {
			for (int i = 0; i < count; i++) {
				WebElement textField = ele.get(i);
				clickOnElement(driver, textField, "clickOnElement", 20, test);
				String c = Keys.BACK_SPACE.toString();
				textField.sendKeys(c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c
						+ c + c + c + c + c + c + c + c + c + c + c + c + c + c);
				if (textField.equals("")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Name of Function :- cleartext
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To clear the value in the text field  
	 * @param driver
	 * @param element
	 * @param test 
	 *
	 */
	public void cleartext(RemoteWebDriver driver, WebElement element, ExtentTest test) {
		try {
			// WebElement textField= driver.findElement(element);
			clickOnElement(driver, element, "clickOnElement", 20, test);
			String c = Keys.BACK_SPACE.toString();
			element.sendKeys(c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c
					+ c + c + c + c + c + c + c + c + c + c + c + c + c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Name of Function :- ClickOutsidePopup
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- Action on the offsets 
	 * @param driver
	 * @param test 
	 * @param 1st offset 
	 * @param 2nd offset
	 *
	 */
	public void ClickOutsidePopup(RemoteWebDriver driver, ExtentTest test, int num1, int num2) {
		try {
			Actions action = new Actions(driver);
			action.moveByOffset(num1, num2).click().build().perform();
		} catch (Exception e) {

		}
	}

	/***
	 * Name of Function :- ClickOutsideJumpEpisodePopup
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Click on the Element
	 * @param driver
	 * @param test 
	 */
	public void ClickOutsideJumpEpisodePopup(RemoteWebDriver driver, ExtentTest test) {
		try {
			String outside = "//div[contains(@class,'overlay__backdrop')]";
			try {
				clickOnElement(driver, By.xpath(outside), "outside element", 20, test);
				System.out.println("Clicked outside the popup");
			}catch (Exception e1) {
				clickOnElement(driver, By.xpath(outside), "outside element", 20, test);
				System.out.println("Clicked outside the popup");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Name of Function :- cleartext
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To clear the value in the text field  
	 * @param driver
	 * @param locator
	 * @param test 
	 *
	 */
	public void clearDOBtextField(RemoteWebDriver driver, By locator, ExtentTest test) {
		try {
			WebElement textField = driver.findElement(locator);
			String c = Keys.BACK_SPACE.toString();
			textField.sendKeys(c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c
					+ c + c + c + c + c + c + c + c + c + c + c + c + c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Name of Function :- hideKeyBoaredUntilElementVisible
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To Hide the Browser
	 * @param driver
	 * @param locator
	 * @param test 
	 *
	 */
	public void hideKeyBoaredUntilElementVisible(RemoteWebDriver driver, By element, ExtentTest test) {
		EventUtils eventUtils = new EventUtils();
		// This only for mobileRW
		if (Web_Constants.PROJECT.equalsIgnoreCase("MobileRW") || Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			for (int i = 1; i < 10; i++) {
				hideKeyboard(driver);
				if (eventUtils.waitUntilElementIsVisible(driver, element, 30)) {
					break;
				}
			}
		}
	}

	/***
	 * Name of Function :- normalClick
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To click on the locator of the Element 
	 * @param driver
	 * @param locator
	 * @param test 
	 *
	 */
	public void normalClick(RemoteWebDriver driver, By locator, ExtentTest test) {
		try {

			driver.findElement(locator).click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/***
	 * Name of Function :- normalClick
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To click on the locator of the Element 
	 * @param driver
	 * @param locator
	 * @param test 
	 *
	 */
	public void horizontalSwipeforcards(RemoteWebDriver driver, By locator, By arrowBtn, String condition, int xOffset,
			int yOffset, int timeout) {
		WebElement element = null;

		element = driver.findElement(locator);

		try {
			if (!condition.equalsIgnoreCase("left")) {
				Actions move = new Actions(driver);
				move.moveToElement(element).clickAndHold().moveByOffset(xOffset, yOffset).release().perform();
			}
			if (waitUntilElementIsVisible(driver, arrowBtn, 20)) {
				clickOnElement(driver, arrowBtn, "Arrow mark", 20);
			}
		} catch (Exception e) {
		}
	}

	/***
	 * Name of Function :- clickByCoordinates
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :- To click on the co-ordinates of the page 
	 * @param driver
	 * @param locator
	 * 
	 */
	public void clickByCoordinates(AppiumDriver driver, By locator) {
		// For Watch Tab, these are working x= 0.35, y = 0.69
		// For My Stuff Tab, these are working x= 0.50, y = 0.80
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();

		int startx = (int) (width * 0.5);
		int starty = (int) (height * 0.3);

		//		TouchAction action1 = new TouchAction(driver);
		try {

			for (int i = 0; i < 10; i++) {
				if (!waitUntilElementIsPresent(driver, locator, 5)) {
					break;
				} else {
					//					action1.press(PointOption.point(startx, starty)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx,starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));


				}
			}
			//		System.out.println("hi");
			//			action1.longPress(PointOption.point(startx, starty)).release().perform();
			//			// action1.press(PointOption.point(startx, starty)).release().perform();
			// event.Wait(5000);
		} catch (Exception e) {
			try {
				//				action1.press(PointOption.point(startx, starty)).release().perform();

				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx,starty))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Collections.singletonList(sequence));
				// event.Wait(5000);
			} catch (Exception e1) {
			}
		}
	}

	public void horizontalSwipeleftToRight(RemoteWebDriver driver, By locator, By expectedLocator, int swipeCount) {
		for (int i = 0; i < swipeCount; i++) {

			List<WebElement> elements = driver.findElements(locator);
			int elementSize = elements.size();
			WebElement element = elements.get(elementSize - 1);

			int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
			int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
			int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			try {
				//				(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(endX, startY))
				//				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				//				.moveTo(PointOption.point(startX, endY)).release().perform();


				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), endX, startY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Collections.singletonList(sequence));



				if (waitUntilElementIsVisible(driver, expectedLocator, 1)) {
					break;
				}
			} catch (Exception e) {
			}
		}

	}

	public boolean horizontalSwipebyarrowbutton(RemoteWebDriver driver, By expectedLocator, String string, By arrowBtn,
			int swipeCount, ExtentTest test) {

		BasePage basePage = new BasePage(driver, test);

		if (waitUntilElementIsVisible(driver, arrowBtn, 5)) {
			for (int i = 0; i < swipeCount; i++) {

				if (waitUntilElementIsVisible(driver, expectedLocator, 5)) {
					basePage.logStatus("info", "Expected result " + string + " is visible");

					break;
				} else {
					clickOnElement(driver, arrowBtn, "Arrow mark", 5);

				}
			}
		} else {

			basePage.logStatus("error", "Expected result " + string + " is not visible");
		}
		return true;

	}

	//   Horizontal swipe reverse for app
	public void horizontalSwipereverse(RemoteWebDriver driver, By locator, By expectedLocator, int swipeCount) {
		for (int i = 0; i < swipeCount; i++) {

			List<WebElement> elements = driver.findElements(locator);
			int elementSize = elements.size();
			WebElement element = elements.get(elementSize - 1);

			int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
			int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
			int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			try {
				//				(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(endX, endY))
				//				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				//				.moveTo(PointOption.point(startX, startY)).release().perform();


				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Collections.singletonList(sequence));

				if (waitUntilElementIsVisible(driver, expectedLocator, 1)) {
					break;
				}
			} catch (Exception e) {
			}
		}

	}

	public void runAppInBackground(RemoteWebDriver driver, int timeInSeconds, ExtentTest test) {

		BasePage basePage = new BasePage(driver, test);

		try {
			((AndroidDriver) driver).runAppInBackground(Duration.ofSeconds(timeInSeconds));
			basePage.logStatus("info", "Application running in Background for '" + timeInSeconds + "' seconds");
		} catch (Exception e) {
			basePage.logStatus("error", "Application unable running in Background for '" + timeInSeconds + "' seconds");
		}

	}

	public void verticalswipeReverseforEPG(RemoteWebDriver driver, int count) throws InterruptedException {
		if (Web_Constants.PROJECT.contains("App")) {
			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.95);
			int endy = (int) (height / 2);
			//			 int endy = (int) (height * 0.10);

			for (int i = 0; i <count; i++) {
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, endy))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					//					.moveTo(PointOption.point(startx, starty)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, endy))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startx,starty ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));


				} catch (Exception e) {
				}
			}
		}
	}

	public void horizontalSwipe(RemoteWebDriver driver, List<WebElement> listOfElements, WebElement expectedElement,
			int swipeCount) {
		for (int i = 0; i < swipeCount; i++) {

			int elementSize = listOfElements.size();
			WebElement element = listOfElements.get(elementSize - 1);

			int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
			int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
			int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
			try {
				//				(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startX, startY))
				//				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				//				.moveTo(PointOption.point(endX, endY)).release().perform();

				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX,endY ))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Collections.singletonList(sequence));


				if (waitUntilElementIsVisible(driver, expectedElement, 1)) {
					break;
				}
			} catch (Exception e) {
			}
		}

	}

	public void doubleTap(RemoteWebDriver driver, By locator) {

		switch (Web_Constants.PROJECT.toLowerCase()) {
		case "iosapp": 
		case "androidapp": {
			//			int x = driver.findElement(locator).getSize().getWidth() / 2;
			//			int y = driver.findElement(locator).getSize().getHeight() / 2;
			//			(new TouchAction((AppiumDriver<WebElement>) driver)).tap(PointOption.point(x, y)).perform().tap(PointOption.point(x, y)).perform();
			//			(new TouchAction((AppiumDriver<WebElement>) driver))

			Point location = driver.findElement(locator).getLocation();
			Dimension size = driver.findElement(locator).getSize();

			Point centerOfElement = getCenterOfElement(location, size);

			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(sequence));


			break;
		}
		//		case "iosapp": {
		//			int x = driver.findElement(locator).getSize().getWidth() / 2;
		//			int y = driver.findElement(locator).getSize().getHeight() / 2;
		//			(new TouchAction((AppiumDriver<WebElement>) driver)).tap(PointOption.point(x, y)).perform();
		//			sleep(1);
		//			(new TouchAction((AppiumDriver<WebElement>) driver)).tap(PointOption.point(x, y)).perform();
		//			break;
		//
		//		}
		default:
			break;
		}

	}
	public void clearTextofAnyContent(RemoteWebDriver driver,By element) {
		try {
			driver.findElement(element).clear();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Scroll down to element by 100
	public void scrollDownBy100(RemoteWebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		switch (Web_Constants.PROJECT.toLowerCase()) {
		case "web":
			js.executeScript("window.scrollBy(0,200)");
			break;
		case "mobilerw":
			js.executeScript("window.scrollBy(0,200)");
			break;
		default:
			break;
		}
	}
	// To click the element by locator
	public void clickOnElementjavaScrpit(RemoteWebDriver driver, By elementToClick, String typeOfElement, int timeOut,
			ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;
		switch (Web_Constants.PROJECT.toLowerCase()) {
		case "web":
			try {
				Thread.sleep(1000);
				driver.findElement(elementToClick).click();
				isElementClicked = true;
			}catch (Exception e) {
				driver.findElement(elementToClick).click();
				isElementClicked = true;
			}
			break;
		case "mobilerw": 
			if (waitUntilElementIsVisible(driver, elementToClick, timeOut)) {
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(elementToClick));
					isElementClicked = true;
				}catch (Exception e) {
					driver.findElement(elementToClick).click();
					isElementClicked = true;
				}
			} else {
				basePage.logStatus("info",typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
			}
			break;
		case "androidapp":
			if (waitUntilElememtIsClickable(driver, elementToClick, timeOut)) {
				try {
					driver.findElement(elementToClick).click();
					isElementClicked = true;
				} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
					scrollToParticularElement(driver, elementToClick);
					driver.findElement(elementToClick).click();
					isElementClicked = true;
				}
			} else {
				basePage.logStatus("info",typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
			}
			break;
		case "iosapp": 
			if (waitUntilElememtIsClickable(driver, elementToClick, timeOut)) {
				try {
					driver.findElement(elementToClick).click();
					isElementClicked = true;
				} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
					scrollToParticularElement(driver, elementToClick);
					driver.findElement(elementToClick).click();
					isElementClicked = true;
				}
			} else {
				basePage.logStatus("info",typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
			}
			break;
		}
		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
		}
	}

	/**
	 * @author Vidya
	 */
	public void navigateBackTimes(RemoteWebDriver driver, int count) {
		try {
			for(int i=0;i<count;i++) {
				try {
					navigateBack(driver);
					sleep(1);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Vidya
	 */
	public void horizontalSwipeforAppcards(RemoteWebDriver driver, String Xpath, By locator, int swipecount) {
		WebElement element = null, element1 = null;

		element = driver.findElement(By.xpath(Xpath));
		for(int i=0; i<swipecount; i++) {
			try {


				if (waitUntilElememtIsClickable(driver,  driver.findElement(locator), 5)) {
					break;
				}
			}catch (Exception e) {
				// TODO: handle exception
				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.50)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.04)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				try {
					//					TouchAction act = new TouchAction((AppiumDriver<WebElement>) driver);
					//					(new TouchAction((AppiumDriver<WebElement>)driver)).press(PointOption.point(startX, startY))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, endY))
					//					.release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX,endY ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e1) {
				}
			}
		}
	}

	//Code by VIDYA
	public String fetchOTPThroughtHeadlessLoginForApp(RemoteWebDriver driver, ExtentTest test, String email, String password) {
		String otp = null;
		try {
			ChromeOptions option = new ChromeOptions();
			//				option.setHeadless(true);

			driver = new ChromeDriver(option);
			driver.get("http://webmail.ifocussystec.in/");

			if(waitUntilElementIsVisible(driver, By.xpath("//img[contains(@src,'webmail-logo')]"), 20)) {
				enterValue(driver, By.xpath("//input[@id='user']"), email, "Email", 20, test);
				enterValue(driver, By.xpath("//input[@id='pass']"), password, "Password", 20, test);
				clickOnElement(driver, By.xpath("//button[@id='login_submit']"), "SignIn Button", 20);
				sleep(2);
				clickOnElement(driver, By.id("launchActiveButton"), "webmailLogIN", 30);
				clickOnElement(driver, By.xpath("//a[contains(@class,'refresh')]"), "Refresh button", 20);
				clickOnElement(driver, By.xpath("//tr[@class='message unread focused']//span[text()='meconnect Account']|//tr[@class='message unread']//span[@aria-label='Not Flagged']/../../..//span[text()='meconnect Account']"), "First Mail", 20);
				//				WebElement element = driver.findElementByXPath("//iframe[@id='messagecontframe']");
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='messagecontframe']")));
				otp = getTextOfWebelement(driver, By.xpath("//div[@id='layout-content']//td[contains(@class,'email-message')]//span | //div[@id='layout-content']//td[contains(@class,'email-message')]//a"), 20, test);

				driver.switchTo().defaultContent();
				driver.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return otp;
	}

	public String getAttributeValue(RemoteWebDriver driver, By locator, String tagName) {
		String text = "";
		try {
			text = driver.findElement(locator).getAttribute(tagName);
			return text;
		}catch (StaleElementReferenceException e) {
			text = driver.findElement(locator).getAttribute(tagName);
			return text;
		}catch (Exception e1) {
			System.err.println("Unable to get text of element");
			return text;
		}
	}

	public boolean passwordAdvisoryInChangePasswordpage(RemoteWebDriver driver, ExtentTest test, String email, String password) {
		String otp = null;
		boolean passwordAdvisory = false;
		EventUtils eventUtils = new EventUtils();
		BasePage basePage;
		try {
			if(Web_Constants.ENV.equalsIgnoreCase("prod")) {
				ChromeOptions option = new ChromeOptions();
				//				option.setHeadless(true);

				RemoteWebDriver Webmaildriver= new ChromeDriver();
				Webmaildriver.get("http://webmail.ifocussystec.in/");
				Webmaildriver.manage().window().maximize();
				basePage = new BasePage(Webmaildriver, test);
				basePage.logScreenShot(Status.INFO, "Launch application");
				if(waitUntilElementIsVisible(Webmaildriver, By.xpath("//img[contains(@src,'webmail-logo')]"), 20)) {
					enterValue(Webmaildriver, By.xpath("//input[@id='user']"), email, "Email", 20, test);
					enterValue(Webmaildriver, By.xpath("//input[@id='pass']"), password, "Password", 20, test);
					clickOnElement(Webmaildriver, By.xpath("//button[@id='login_submit']"), "SignIn Button", 20);
					sleep(5);
					basePage.logScreenShot(Status.INFO, "");
					clickOnElement(Webmaildriver, By.id("launchActiveButton"), "webmailLogIN", 30);
					clickOnElement(Webmaildriver, By.xpath("//a[contains(@class,'refresh')]"), "Refresh button", 20);
					clickOnElement(Webmaildriver, By.xpath("(//span[contains(@title,'Unread')]//following-sibling::a//span[contains(text(),'meconnect Account - Reset Password')])[1]"), "First Mail", 20);
					Webmaildriver.switchTo().frame(Webmaildriver.findElement(By.xpath("//iframe[@id='messagecontframe']")));
					clickOnElement(Webmaildriver, By.xpath("//div[@id='layout-content']//td[contains(@class,'email-message')]//a"), "Link", 20);
					eventUtils.sleep(8);
					eventUtils.switchToLatestWindow(Webmaildriver, test);
					eventUtils.switchToLatestWindow(Webmaildriver, test);
					eventUtils.sleep(5);
					basePage.logScreenShot(Status.INFO, "");
					clickOnElement(Webmaildriver, By.xpath("//label[text()='New Password']"), "NewPassword", 10);
					for(int i = 0; i<3; i++) {
						if (eventUtils.waitUntilElementIsVisible(Webmaildriver, By.xpath("//div[@class='me-pass-txt-input__advisory-header']"), 10)) {
							break;
						}else {
							clickElement(Webmaildriver, By.xpath("//label[text()='New Password']//following-sibling::input[@id='password']"), test);
						}
					}
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(Webmaildriver, By.xpath("//div[@class='me-pass-txt-input__advisory-header']"), 30) ||
							eventUtils.waitUntilElementIsPresent(Webmaildriver, By.xpath("//div[@class='me-pass-txt-input__advisory-header']"), 30)) {
						passwordAdvisory = true;
						basePage.logStatus("info", "User able to see the passwordadvisory in restpassword page");
					}else {
						passwordAdvisory = false;
						basePage.logStatus("error", "User unable to see the passwordadvisory in restpassword page");
					}
					Webmaildriver.switchTo().defaultContent();
					Webmaildriver.close();
					Webmaildriver.quit();
				}
			}else {
				ChromeOptions option = new ChromeOptions();
				RemoteWebDriver Webmaildriver= new ChromeDriver(option);
				Webmaildriver.get("http://webmail.ifocussystec.in/");
				Webmaildriver.manage().window().maximize();
				basePage = new BasePage(Webmaildriver, test);
				basePage.logScreenShot(Status.INFO, "Launch");
				if(waitUntilElementIsVisible(Webmaildriver, By.xpath("//img[contains(@src,'webmail-logo')]"), 20)) {
					enterValue(Webmaildriver, By.xpath("//input[@id='user']"), email, "Email", 20, test);
					enterValue(Webmaildriver, By.xpath("//input[@id='pass']"), password, "Password", 20, test);
					clickOnElement(Webmaildriver, By.xpath("//button[@id='login_submit']"), "SignIn Button", 20);
					clickOnElement(Webmaildriver, By.id("launchActiveButton"), "webmailLogIN", 30);
					sleep(5);
					int k=0;
					while(k<3) {
						if (eventUtils.waitUntilElementIsPresent(Webmaildriver, By.xpath("(//span[contains(@title,'Unread')]//following-sibling::a//span[contains(text(),'meconnect Account - Reset Password')])[1]"), 10)) {
							break;
						}else {
							clickOnElement(Webmaildriver, By.xpath("//a[contains(@class,'refresh')]"), "Refresh button", 20);
							k++;
						}
					}
					basePage.logScreenShot(Status.INFO, "After refresh");
					if (eventUtils.waitUntilElementIsPresent(Webmaildriver, By.xpath("(//span[contains(@title,'Unread')]//following-sibling::a//span[contains(text(),'meconnect Account - Reset Password')])[1]"), 20)) {
						clickOnElement(Webmaildriver, By.xpath("(//span[contains(@title,'Unread')]//following-sibling::a//span[contains(text(),'meconnect Account - Reset Password')])[1]"), "First Mail", 20);
					}else {
						clickOnElement(Webmaildriver, By.xpath("(//span[contains(text(),'meconnect Account - Reset Password')])[1]"), "Refresh button", 20);
					}
					eventUtils.sleep(3);
					Webmaildriver.switchTo().frame(Webmaildriver.findElement(By.xpath("//iframe[@id='messagecontframe']")));
					clickOnElement(Webmaildriver, By.xpath("//div[@id='layout-content']//td[contains(@class,'email-message')]//a"), "Link", 20);
					eventUtils.sleep(15);
					eventUtils.switchToLatestWindow(Webmaildriver, test);
					String resetGetURL = Webmaildriver.getCurrentUrl();
					resetGetURL = resetGetURL.replace("https://preprod92.mewatch.sg", "https://annapurna:mcvalley@preprod92.mewatch.sg");
					Webmaildriver.get(resetGetURL);
					eventUtils.sleep(20);
					basePage.logScreenShot(Status.INFO, "Changing url");
					eventUtils.switchToLatestWindow(Webmaildriver, test);
					eventUtils.sleep(5);
					if(eventUtils.waitUntilElementIsPresent(Webmaildriver, By.xpath("//button[@class='notification__close-btn']"), 20)) {
						eventUtils.clickOnElement(Webmaildriver, By.xpath("//button[@class='notification__close-btn']"),"Notification Close Button", 5);
						eventUtils.clickOnElement(Webmaildriver, By.xpath("//button[@class='notification__close-btn']"),"Notification Close Button", 5);
					}
					clickOnElement(Webmaildriver, By.xpath("//label[text()='New Password']"), "NewPassword", 10);
					basePage.logScreenShot(Status.INFO, "Password advisory");
					for(int i = 0; i<3; i++) {
						if (eventUtils.waitUntilElementIsVisible(Webmaildriver, By.xpath("//div[@class='me-pass-txt-input__advisory-header']"), 10)) {
							break;
						}else {
							clickElement(Webmaildriver, By.xpath("//label[text()='New Password']/.."), test);
						}
					}
					eventUtils.sleep(5);
					if (eventUtils.waitUntilElementIsVisible(Webmaildriver, By.xpath("//div[@class='me-pass-txt-input__advisory-header']"), 30) ||
							eventUtils.waitUntilElementIsPresent(Webmaildriver, By.xpath("//div[@class='me-pass-txt-input__advisory-header']"), 30)) {
						passwordAdvisory = true;
						basePage.logStatus("info", "User able to see the passwordadvisory in restpassword page");
					}else {
						passwordAdvisory = false;
						basePage.logStatus("error", "User unable to see the passwordadvisory in restpassword page");
					}
					Webmaildriver.switchTo().defaultContent();
					Webmaildriver.close();
					Webmaildriver.quit();
				}else {
					Webmaildriver.close();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return passwordAdvisory;
	}

	public boolean changepasswordPage(ExtentTest test, RemoteWebDriver driver, String email, String password) {
		boolean sucessfullreset = false;
		EventUtils eventUtils = new EventUtils();
		try {
			ChromeOptions option = new ChromeOptions();
			//				option.setHeadless(true);

			driver = new ChromeDriver(option);
			driver.get("http://webmail.ifocussystec.in/");

			if(waitUntilElementIsVisible(driver, By.xpath("//img[contains(@src,'webmail-logo')]"), 20)) {
				enterValue(driver, By.xpath("//input[@id='user']"), email, "Email", 20, test);
				enterValue(driver, By.xpath("//input[@id='pass']"), password, "Password", 20, test);
				clickOnElement(driver, By.xpath("//button[@id='login_submit']"), "SignIn Button", 20);
				sleep(5);
				clickOnElement(driver, By.id("launchActiveButton"), "webmailLogIN", 30);
				clickOnElement(driver, By.xpath("//a[contains(@class,'refresh')]"), "Refresh button", 20);
				clickOnElement(driver, By.xpath("(//span[contains(@title,'Unread')]//following-sibling::a//span[contains(text(),'meconnect Account - Reset Password')])[1]"), "First Mail", 25);
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='messagecontframe']")));
				clickOnElement(driver, By.xpath("//div[@id='layout-content']//td[contains(@class,'email-message')]//a"), "Link", 20);
				eventUtils.sleep(5);
				eventUtils.switchToLatestWindow(driver, test);
				eventUtils.sleep(5);
				clickOnElement(driver, By.xpath("//h2[text()='Change Password']//..//input[@id='password']"), "New password", 20);
				eventUtils.enterValue(driver, By.xpath("//label[text()='New Password']/..//input"), password, "new password", 20, test);
				eventUtils.enterValue(driver, By.xpath("//label[text()='Confirm Password']/..//input"), password, "confim password", 20, test);
				eventUtils.clickOnElement(driver, By.xpath("//span[text()='Confirm']/.."), "Confirm", 30);
				if(eventUtils.waitUntilElementIsVisible(driver, By.xpath("//div[text()='Password successfully reset']"), 30)) {
					eventUtils.clickOnElement(driver, By.xpath("//button[text()='Ok']"), "OK button", 30);
					sucessfullreset = true;
				}else {
					sucessfullreset = false;
				}
				driver.switchTo().defaultContent();
				driver.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sucessfullreset;
	}

	//Code by VIDYA
	public void settingpageCarosalhorizontalSwipe(RemoteWebDriver driver, String Xpath, By locator) {
		WebElement element = null, element1 = null;

		element = driver.findElement(By.xpath(Xpath));
		for(int i=0; i<5; i++) {
			try {
				if(waitUntilElememtIsClickable(driver, locator, 2)) {
					element1 = driver.findElement(locator);
					break;
				}else {
					// TODO: handle exception
					int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.50)));
					int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

					int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.04)));
					int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

					try {
						//						TouchAction act = new TouchAction((AppiumDriver<WebElement>) driver);
						//						(new TouchAction((AppiumDriver<WebElement>)driver)).press(PointOption.point(startX, startY))
						//						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, endY))
						//						.release().perform();

						PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
						Sequence sequence = new Sequence(finger1, 1)
								.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
								.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
								.addAction(new Pause(finger1, Duration.ofMillis(1000)))
								.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX,endY ))
								.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

						driver.perform(Collections.singletonList(sequence));

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.50)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.04)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				try {
					//					TouchAction act = new TouchAction((AppiumDriver<WebElement>) driver);
					//					(new TouchAction((AppiumDriver<WebElement>)driver)).press(PointOption.point(startX, startY))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, endY))
					//					.release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX,endY ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e1) {
					e.printStackTrace();
				}
			}
		}
	}

	public void horizontalSwipeforrail(RemoteWebDriver driver, String Xpath, int swipecount) {
		WebElement element = null, element1 = null;

		element = driver.findElement(By.xpath(Xpath));
		for(int i=0; i<swipecount; i++) {
			try {
				// TODO: handle exception
				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.50)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.04)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				try {
					//					TouchAction act = new TouchAction((AppiumDriver<WebElement>) driver);
					//					(new TouchAction((AppiumDriver<WebElement>)driver)).press(PointOption.point(startX, startY))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, endY))
					//					.release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX,endY ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e1) {
				}
			}catch (Exception e) {

			}
		}
	}

	/**
	 * horizontalSwipeRightToLeft : method applicable for android and ios for rail swipe from right to left 
	 * @param driver
	 * @param locator
	 * @param swipeCount
	 */

	public void horizontalSwipeRightToLeft(RemoteWebDriver driver, By locator, int swipeCount) {
		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {
			for (int i = 0; i < swipeCount; i++) {

				List<WebElement> elements = driver.findElements(locator);
				int elementSize = elements.size();
				WebElement element = elements.get(elementSize - 1);

				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startX, startY))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(endX, endY)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(2000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX,endY ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
 					driver.perform(Collections.singletonList(sequence));

					
					 
				} catch (Exception e) {
				}
			}
			break;
		}
		}

	}
	/**
	 * horizontalSwipeLeftToRight : method applicable for android and ios for rail swipe from left  to  right
	 * @param driver
	 * @param locator
	 * @param swipeCount
	 */
	public void horizontalSwipeLeftToRight(RemoteWebDriver driver, By locator,int swipeCount) {

		switch (Web_Constants.PROJECT) {

		case "iOSApp":
		case "AndroidApp": {
			for (int i = 0; i < swipeCount; i++) {

				List<WebElement> elements = driver.findElements(locator);
				int elementSize = elements.size();
				WebElement element = elements.get(elementSize - 1);

				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(endX, startY))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startX, endY)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),endX ,endY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(2000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, startY ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}
			}
		}
		}

	}


	//Click on element using click method
	public void clickElement(RemoteWebDriver driver, By elementToClick, ExtentTest test) {
		try {
			driver.findElement(elementToClick).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void clickElementAfterWait(RemoteWebDriver driver, By elementToClick,int firstwait, int secondWait,ExtentTest test ) { 
		EventUtils eventUtils = new EventUtils();
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked =false;
		eventUtils.sleep(firstwait);
		try {
			driver.findElement(elementToClick).click();
			isElementClicked = true;
		}catch (Exception e) {
			try {
				clickUsingJavaScript(driver, elementToClick);
				isElementClicked = true;
			} catch (Exception e1) {

			}

		}
		if (isElementClicked) {
          basePage.logStatus("info", "user able to click");
		} else {
			basePage.logStatus("info", "user unable to click");
		}
		eventUtils.sleep(secondWait);	
	}



	/**
	 * 
	 * This method is user for scrolling to particular element using window.scrollBY
	 * 
	 * 
	 */
	public void scrollToParticularElementUsingWindows(RemoteWebDriver driver , By element,ExtentTest test) {
		EventUtils eventUtils = new EventUtils();
		BasePage basePage = new BasePage(driver, test);
		eventUtils.sleep(3);
		int y_axis = driver.findElement(element).getRect().getY();
		driver.executeScript("window.scrollBy(0,"+y_axis+")");
		if(eventUtils.waitUntilElementIsPresent(driver, element, 20)) {
			basePage.logStatus("INFO", "user successfully scroll to particular element");
		}else {
			basePage.logStatus("INFO", "user unable to scroll to particular element");
		}

	}

	public void clickOnESC(RemoteWebDriver driver) {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ESCAPE).build().perform();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void clearValue(RemoteWebDriver driver,By elementId) {
		try {
			driver.findElement(elementId).clear();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String gettingText(RemoteWebDriver driver,By elementId) {
		String text = "";
		try {
			text = driver.findElement(elementId).getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return text;
	}

	public void scrollRandomlyUsingWindows(RemoteWebDriver driver ,int times) {
		try {
			EventUtils eventUtils = new EventUtils();
			eventUtils.sleep(3);
			driver.executeScript("window.scrollBy(0,"+times+")");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	


	/***
	 * Name of Function :- BrowserNewTABOpen
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 06-11-2023
	 * Function Description :-To open a given URL in a new tab
	 */
	public void BrowserNewTABOpen(RemoteWebDriver driver, String URL) {
		try {
			String NewTAB = driver.getWindowHandle();
			((JavascriptExecutor) driver).executeScript("window.open()");
			Set<String> Windows = driver.getWindowHandles();
			for (String Window : Windows) {
				if (!Window.equalsIgnoreCase(NewTAB)) {
					driver.switchTo().window(Window);
					driver.get(URL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/***
	 * Name of Function :- extractItegerfromString
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 06-11-2023
	 * Function Description :-extract the First integer from given String
	 */
	public  int extractItegerfromString(String Text)
	{
		int i = 0;
		while (i < Text.length() && !Character.isDigit(Text.charAt(i))) {
			i++;
		}
		int j = i;
		while (j <Text.length() && Character.isDigit(Text.charAt(j))) {
			j++;
		}
		return Integer.parseInt(Text.substring(i, j));

	}

	public boolean forgotPasswordResetMail(ExtentTest test, RemoteWebDriver driver, String email, String password) {
		boolean expiredHours = false;
		EventUtils eventUtils = new EventUtils();
		try {
			ChromeOptions option = new ChromeOptions();

			driver = new ChromeDriver(option);
			driver.get("http://webmail.ifocussystec.in/");

			if(waitUntilElementIsVisible(driver, By.xpath("//img[contains(@src,'webmail-logo')]"), 20)) {
				enterValue(driver, By.xpath("//input[@id='user']"), email, "Email", 20, test);
				enterValue(driver, By.xpath("//input[@id='pass']"), password, "Password", 20, test);
				clickOnElement(driver, By.xpath("//button[@id='login_submit']"), "SignIn Button", 20);
				sleep(5);
				clickOnElement(driver, By.id("launchActiveButton"), "webmailLogIN", 30);
				clickOnElement(driver, By.xpath("//a[contains(@class,'refresh')]"), "Refresh button", 20);
				clickOnElement(driver, By.xpath("(//span[contains(@title,'Unread')]//following-sibling::a//span[contains(text(),'meconnect Account - Reset Password')])[1]"), "First Mail", 20);
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='messagecontframe']")));
				String actualexpireTime = getTextOfWebelement(driver, By.xpath("//p[contains(text(),'The above link will expire in 24 hours')]"), 20, test);
				if(actualexpireTime.contains("24")) {
					eventUtils.clickOnElement(driver, By.xpath("//button[text()='Ok']"), "OK button", 30);
					expiredHours = true;
				}else {
					expiredHours = false;
				}
				driver.switchTo().defaultContent();
				driver.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return expiredHours;
	}

	public void screenRotation(RemoteWebDriver driver) {
		ScreenOrientation orientation = ((AndroidDriver) driver).getOrientation();
		try {
			if(orientation == ScreenOrientation.LANDSCAPE) {
				((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
				sleep(1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
















	/***
	 * Name of Function :- tapUsingElementCoordinate
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 03-Jan-2024
	 * Function Description :-To tap on element using element coordinate : checked for both tab and app ios
	 */
	public void tapUsingElementCoordinate(AppiumDriver driver,By locator)
	{
		List<WebElement> elements = driver.findElements(locator);
		int elementSize = elements.size();
		WebElement element = elements.get(elementSize - 1);

		int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.80)));
		int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
		//		int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.05)));
		//		int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * (0.5)));
		//		

		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence sequence = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),(startX+5),(startY+5) ))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(sequence));
		System.out.println("clicked");

	}

	/***
	 * Name of Function :- verticalSwipe
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-VerticalSwipe For to Click on HBO In LiveTVSection
	 */
	public boolean verticalSwipeForTOClickHBOInLiveTVSection(RemoteWebDriver driver, By locator, int swipeLimit) {
		boolean isElementFound=false;
		switch (Web_Constants.PROJECT)
		{

		case "AndroidApp": {
			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.50);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				if (waitUntilElementIsPresent(driver, locator, 3)) {
					isElementFound=true;
					break;
				}
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(2000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}
			}

			break;
		}
		case "iOSApp": {
			// scrolling starts
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.80);
			int endy = (int) (height / 4);
			// int endy = (int) (height * 0.10);
			for (int i = 0; i < swipeLimit; i++) {
				if (waitUntilElememtIsClickable(driver, locator, 3)) {
					isElementFound=true;
					break;
				}
				try {
					//					(new TouchAction((AppiumDriver<WebElement>) driver)).press(PointOption.point(startx, starty))
					//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					//					.moveTo(PointOption.point(startx, endy)).release().perform();

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(2000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				} catch (Exception e) {
				}
			}

			break;
		}
		}
		return isElementFound;

	}


	/***
	 * Name of Function :- clickUsingCoordinate
	 * Developed By :- Ifocus Automation Team
	 * Organisation Name :- Ifocus Systec
	 * Date :- 27-Jan-2022
	 * Function Description :-To Click on Screen co-ordinates 
	 */
	public void clickMidOfThePageUsingCoordinate(RemoteWebDriver driver) {
		int screenwscreenwidthidth = driver.manage().window().getSize().getWidth();
		int screenheight = driver.manage().window().getSize().getHeight();
		int startx = screenwscreenwidthidth / 2;
		int starty = screenheight / 2;

		try {
			try {
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence = new Sequence(finger1, 1)
						.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startx,starty ))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200)))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				System.out.println("clicked"); 
				driver.perform(Collections.singletonList(sequence));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String getTagName(RemoteWebDriver driver,ExtentTest test,By locator,int timeOut) {
		BasePage report = new BasePage(driver, test);
		String ele=null;
		if (locator != null) {
			if (waitUntilElementIsVisible(driver, locator, timeOut) ||
					waitUntilElementIsPresent(driver, locator, timeOut)) {
				ele = driver.findElement(locator).getTagName();

				report.logStatus("info", "tagName for particular element is "+ele);
			} else {
				report.logStatus("error", "Unable to get locator");
			}
		}
		return ele;

	}

	public void horizontaiSwipeForCount(RemoteWebDriver driver, String Xpath, int num) {
		WebElement element = null;

		element = driver.findElement(By.xpath(Xpath));
		for(int i = 0; i < num ; i++) {
			try {
				int startX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.40)));
				int startY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				int endX = (int) (element.getLocation().getX() + (element.getSize().getWidth() * (0.04)));
				int endY = (int) (element.getLocation().getY() + (element.getSize().getHeight() * 0.5));

				try {
					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(1000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX,endY ))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	/**
	 * Extracts integers from the given string and returns the result as a String.

	 * @author ROY
	 * @param driver The RemoteWebDriver instance for web interactions.
	 * @param value  The input string from which integers need to be extracted.
	 * @param test   The ExtentTest instance for logging test information.
	 * @return       A String containing the extracted integers from the input string.
	 * 
	 * 
	 */
	public String extractIntegerFromString(RemoteWebDriver driver,String value,ExtentTest test) {
		BasePage basePage = new BasePage(driver, test);
		String s= value;
		String number ="";
		for (int i = 0; i <s.length(); i++) {
			char ch = s.charAt(i);
			if(Character.isDigit(ch)) {
				number = number+ch;
				}
		}
		basePage.logStatus("info", "successfully extract "+number+" from given"+ value);
		
		return number;
	}
	
	
	public void clickProfileIconInManageMeConnect(RemoteWebDriver driver,double startX,double endX)
	{
		int screenwidth = driver.manage().window().getSize().getWidth();
		int screenheight = driver.manage().window().getSize().getHeight();
		int startx = (int) (screenwidth * startX);
		int starty = (int) (screenheight * endX);
		
		 PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		    Sequence sequence = new Sequence(finger1, 1)
		        .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startx,starty ))
		        .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		      
		        .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		    driver.perform(Collections.singletonList(sequence));
     
	}

	public  void refreshthepageforpreprod(RemoteWebDriver driver,String URL,String Environment,ExtentTest test)
	{
		BasePage basepage =new BasePage(driver, test);
		basepage.logStatus("info",URL);
				String Authentication="annapurna:mcvalley@preprod92";
				String Authentication1="annapurna:mcvalley@preprod98";
				if(URL.contains("preprod92"))
				{
					basepage.logStatus("info",URL.replace(Environment, Authentication));
					navigateToGivenUrl(driver,URL.replace(Environment, Authentication),test);
				}
				else if(URL.contains("preprod98"))
				{
					basepage.logStatus("info",URL.replace(Environment, Authentication1));
					navigateToGivenUrl(driver,URL.replace(Environment, Authentication1),test);
				}else {
				refreshPage(driver, test);
				}
	}

	public boolean verticalSwipeto10PercentForAndroid(RemoteWebDriver driver, int swipeLimit) {

		boolean isElementFound=false;
		switch (Web_Constants.PROJECT) {
		case "AndroidApp": {
			int width = driver.manage().window().getSize().getWidth();
			int height = driver.manage().window().getSize().getHeight();
			int startx = width / 2;
			int endx = startx;
			int starty = (int) (height * 0.50);
			int endy = (int) (height / 4);
			for (int i = 0; i < swipeLimit; i++) {
				try {
					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(2000)))
							.addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endx, endy))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Collections.singletonList(sequence));

				} catch (Exception e) {
				}
			}
			break;
		}
		}
		return isElementFound;
	}
	

	public ArrayList<String> getAttributevalueOfWebelements(RemoteWebDriver driver, By locator, int timeOut,String text,
			ExtentTest test) {
		BasePage report = new BasePage(driver, test);
		String valueofAtrributename="";
		ArrayList<String> textContent = new ArrayList<>();
		if (locator != null) {
			if (waitUntilElementIsVisible(driver, locator, timeOut)) {
				List<WebElement> ele = driver.findElements(locator);
				for (int i = 0; i < ele.size(); i++) {
					 valueofAtrributename = ele.get(i).getAttribute(text);
					textContent.add(valueofAtrributename);
				}
				report.logStatus("info", "Attribute values of the element is:-" + valueofAtrributename);
			} else {
				report.logStatus("error", "Unable to get the Attribute values of the element due to element not present");
			}
		}
		return textContent;
	}

	
	public boolean waitUntilElementIsDisplayed(RemoteWebDriver driver, By locator, int timeOut) {
	    boolean isElementVisible = false;
	    if (locator != null) {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	                    for (int i = 0; i < timeOut*2; i++) {
	                        try {
	                            if (driver.findElement(locator).isDisplayed()) {
	                                isElementVisible = true;
	                                break;
	                            }
	                        } catch (Exception e) {
	                        }
	                       Thread.sleep(500);
	                    }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    return isElementVisible;
	}
	
	public boolean waitUntilElementVisiblity(RemoteWebDriver driver, By locator, int timeOut) {
		boolean isElementEnabled = false;
		if (locator != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
					for(int i=0;i<80*timeOut;i++) {
						try {
							wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
							isElementEnabled = true;
							break;
						} catch (Exception e) {
						}

					}

			} catch (Exception e) {
				isElementEnabled = false;

			}
		}
		return isElementEnabled;
	}
	
	public void tapOnPlayerScreenUntileTheElementVisible_ForAndroidapp(RemoteWebDriver driver, By locator, By Expectedlocator) {
		if(Web_Constants.PROJECT.equalsIgnoreCase("AndroidApp")) {
			for(int i=0; i<5; i++) {
				if(waitUntilElementIsVisible(driver, Expectedlocator, 2)) {
					break;
				}else {
					WebElement element=driver.findElement(locator);
					Point location = element.getLocation();
					Dimension size = element.getSize();

					Point centerOfElement = getCenterOfElement(location, size);

					PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence sequence = new Sequence(finger1, 1)
							.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
							.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger1, Duration.ofMillis(200)))
							.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					driver.perform(Collections.singletonList(sequence));
				}
			}
		}
	}
	
}
