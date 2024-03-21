package com.mediacorp.utils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.mediacorp.pages.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class EventUtilsnew {

	RemoteWebDriver driver;
	ExtentTest test;

	public EventUtilsnew(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	// To get the Attribute of a Web Element
	public String getAttributeOfWebelement(WebElement element,String Attribute, int timeOut) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (waitUntilElementIsPresent(element, timeOut)) {
			text = element.getAttribute(Attribute);
			System.out.println("[INFO] Text Of The Element:-"+text);
			report.logStatus("info", "Text of the element is:-" + text);
		} else {
			report.logStatus("error", "Unable to get the text of the element due to element not present");
		}
		return text;
	}
	
	// To get the Attribute of a Web Element by Locator
	public String getAttributeOflocator(By locator,String Attribute, int timeOut) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (locator != null) {
			if (waitUntilElementIsPresent(locator, timeOut)) {
				text = driver.findElement(locator).getAttribute(Attribute);
				report.logStatus("info", "Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return text;
	}
	
	// To get the Text of a Web Element
	public String getTextOfWebelementWithoutPrintLogstatus(By locator, int timeOut) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (locator != null) {
			if (waitUntilElementIsPresent(locator, timeOut)) {
				text = driver.findElement(locator).getText();
				System.out.println("Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return text;
	}
	
	// To get the Text of a Web Element
	public String getTextOfWebelement(WebElement element, int timeOut) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (waitUntilElementIsPresent(element, timeOut)) {
			text = element.getText();
//			report.logStatus("info", "Text of the element is:-" + text);
		} else {
			report.logStatus("error", "Unable to get the text of the element due to element not present");
		}
		return text;
	}

	// To get the Text of a Web Element by Locator
	public String getTextOfWebelement(By locator, int timeOut) {
		BasePage report = new BasePage(driver, test);
		String text = "";
		if (locator != null) {
			if (waitUntilElementIsPresent(locator, timeOut)) {
				text = driver.findElement(locator).getText();
				report.logStatus("info", "Text of the element is:-" + text);
			} else {
				report.logStatus("error", "Unable to get the text of the element due to element not present");
			}
		}
		return text;
	}
	
	// To get the Text of a Web Element by Locator
	public List<WebElement> getWebelements(WebElement element, int timeOut) {
		BasePage report = new BasePage(driver, test);
		List<WebElement> ele = null;
		if (element != null) {
			if (waitUntilElementIsVisible(element, timeOut)) {
				report.logStatus("info", "get webelements");
			} else {
				report.logStatus("error", "Unable to get webelement");
			}
		}
		return ele;
	}

	// To get the Text of a Web Element by Locator
	public List<WebElement> getWebelements(By locator, int timeOut) {
		BasePage report = new BasePage(driver, test);
		List<WebElement> ele = null;
		if (locator != null) {
			if (waitUntilElementIsVisible(locator, timeOut)) {
				ele = driver.findElements(locator);

				report.logStatus("info", "get webelements");
			} else {
				report.logStatus("error", "Unable to get webelement");
			}
		}
		return ele;
	}

	// To dismiss Alert
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	// To accept Alert
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	// To switch to frame by Index
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	// To switch to frame by Name
	public void switchToFrame(String name) {
		driver.switchTo().frame(name);
	}

	// To switch to Frame by web element
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	// To wait for particular seconds
	public void sleep(int seconds) {

		String sec = String.valueOf(seconds) + "000";
		Integer time = Integer.parseInt(sec);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}

	
	// To Scroll To Top Of The Page
	public void scrollToTop(RemoteWebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)", "");
	}
	
	// To get the Current URL
	public String getCurrentUrl() {
		BasePage basePage = new BasePage(driver, test);
		basePage.logStatus("info", "Current Page Url:-" + driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}

	// To get the Current Page Title
	public String getPageTitle() {
		BasePage basePage = new BasePage(driver, test);
		basePage.logStatus("info", "Current Page Title:-" + driver.getTitle());
		return driver.getTitle();
	}
	
	// To click the element by webElement using javascript
	public void clickOnElement(WebElement elementToClick, String typeOfElement, int timeOut) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;
		if (elementToClick != null) {
			if (waitUntilElementIsPresent(elementToClick, timeOut)) {
//				scrollToParticularElement(elementToClick);
				if (waitUntilElementIsVisible(elementToClick, timeOut)) {
					if (waitUntilElememtIsClickable(elementToClick, timeOut)) {
						try {
							elementToClick.click();
							isElementClicked = true;
						} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
							sleep(1);
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
							isElementClicked = true;
						} catch (Exception e) {
							isElementClicked = false;
						}
					} else {
						basePage.logStatus("info",
								typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("info", typeOfElement + " is not visible waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("info", "Element not present");
			}
		} else {
			basePage.logStatus("info", "Not able to find the element");
		}
		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
		}
	}
	
	// To click the element by webElement using javascript
		public void ClickOnElement(WebElement elementToClick, String typeOfElement, int timeOut) {
			BasePage basePage = new BasePage(driver, test);
			boolean isElementClicked = false;
			if (elementToClick != null) {
				if (waitUntilElementIsPresent(elementToClick, timeOut)) {
//					scrollToParticularElement(elementToClick);
					if (waitUntilElementIsVisible(elementToClick, timeOut)) {
						if (waitUntilElememtIsClickable(elementToClick, timeOut)) {
							try {
								Thread.sleep(5000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
								isElementClicked = true;
							} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
								sleep(1);
								elementToClick.click();
								isElementClicked = true;
							} catch (Exception e) {
								isElementClicked = false;
							}
						} else {
							basePage.logStatus("info",
									typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
						}
					} else {
						basePage.logStatus("info", typeOfElement + " is not visible waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("info", "Element not present");
				}
			} else {
				basePage.logStatus("info", "Not able to find the element");
			}
			if (isElementClicked) {
				basePage.logStatus("info", "User clicked on " + typeOfElement);
			} else {
				basePage.logStatus("error", "User unable to click on " + typeOfElement);
			}
		}
		
	// To click the element by locator
	public void clickOnElement(By elementToClick, String typeOfElement, int timeOut) {
		BasePage basePage = new BasePage(driver, test);
		boolean isElementClicked = false;
		if (elementToClick != null) {
			if (waitUntilElementIsVisible(elementToClick, timeOut)) {
				if (waitUntilElememtIsClickable(elementToClick, timeOut)) {
					try {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();",
								driver.findElement(elementToClick));
						System.out.println("[EVENT] Clicked on content:-" + typeOfElement);
					} catch (Exception e) {
						try {
							sleep(2);
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
							isElementClicked = true;
						} catch (Exception e1) {
							basePage.logStatus("error", "unable to click on element due to :-" + e);
							System.out.println("[INFO] Not able to clicked on content:-" + typeOfElement);
						}
					}
					isElementClicked = true;
				} else {
					basePage.logStatus("info",
							typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("info", typeOfElement + " is not visible waiting till " + timeOut + " seconds");
			}
		} else {
			basePage.logStatus("info", "Not able to find the element");
		}
		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable to click on " + typeOfElement);
			System.out.println("[INFO] User unable to click on :-" + typeOfElement);

		}
	}

	// To click the element by webElement using javascript
		public void clickOnElementWithoutPrintLogStatus(WebElement elementToClick, String typeOfElement, int timeOut) {
			BasePage basePage = new BasePage(driver, test);
			boolean isElementClicked = false;
			if (elementToClick != null) {
				if (waitUntilElementIsPresent(elementToClick, timeOut)) {
					scrollToParticularElement(elementToClick);
					if (waitUntilElementIsVisible(elementToClick, timeOut)) {
						if (waitUntilElememtIsClickable(elementToClick, timeOut)) {
							try {
								elementToClick.click();
								isElementClicked = true;
							} catch (StaleElementReferenceException | ElementClickInterceptedException ECIR) {
								sleep(1);
								((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
								isElementClicked = true;
							} catch (Exception e) {
								isElementClicked = false;
							}
						} else {
							basePage.logStatus("info",
									typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
						}
					} else {
						basePage.logStatus("info", typeOfElement + " is not visible waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("info", "Element not present");
				}
			} else {
				basePage.logStatus("info", "Not able to find the element");
			}
		}
	// To enter the value in the field
	public void enterValue(WebElement element, String valueToEnter, String typeOfField, int timeOut) {

		boolean isValueEntered = false;
		BasePage basePage = new BasePage(driver, test);

		if (element != null) {
			if (waitUntilElementIsPresent(element, 20)) {
//				scrollToParticularElement(element);
				if (waitUntilElementIsVisible(element, timeOut)) {
					if (waitUntilElememtIsClickable(element, timeOut)) {
						try {
							element.clear();
							element.sendKeys(valueToEnter);
							isValueEntered = true;
						} catch (Exception e) {
							basePage.logStatus("error", "Unable to enter the value due to exception:-" + e);
						}
					} else {
						basePage.logStatus("error",
								typeOfField + " is not clickable waiting till " + timeOut + " seconds");
					}
				} else {
					basePage.logStatus("error", typeOfField + " is not visible waiting till " + timeOut + " seconds");
				} 
			}
		} else {
			basePage.logStatus("error", "Not able to find the element" + typeOfField);
		}

		if (isValueEntered) {
			basePage.logStatus("info", "User entered the value :-  " + valueToEnter + " in " + typeOfField);
		} else {
			basePage.logStatus("error", "User unable to enter " + valueToEnter + " in " + typeOfField);
		}
	}

	// To enter the value in the field
		public boolean enterValueToTheField(WebElement element, String valueToEnter, String typeOfField, int timeOut) {

			boolean isValueEntered = false;
			BasePage basePage = new BasePage(driver, test);

			if (element != null) {
				if (waitUntilElementIsPresent(element, 20)) {
					scrollToParticularElement(element);
					if (waitUntilElementIsVisible(element, timeOut)) {
						if (waitUntilElememtIsClickable(element, timeOut)) {
							try {
								element.click();
								element.clear();
								element.sendKeys(valueToEnter);
								basePage.logStatus("info", "User entered the value :-  " + valueToEnter + " in " + typeOfField);
								isValueEntered = true;
							} catch (Exception e) {
								basePage.logStatus("error", "Unable to enter the value due to exception:-" + e);
								basePage.logStatus("error", "User unable to enter " + valueToEnter + " in " + typeOfField);
								isValueEntered = false;
							}
						} else {
							basePage.logStatus("error",
									typeOfField + " is not clickable waiting till " + timeOut + " seconds");
						}
					} else {
						basePage.logStatus("error", typeOfField + " is not visible waiting till " + timeOut + " seconds");
					} 
				}
			} else {
				basePage.logStatus("error", "Not able to find the element" + typeOfField);
			}
			return isValueEntered;
		}
	// To move to the element and click
	public void moveToElementAndClick(RemoteWebDriver driver, WebElement element, String typeOfElement, int timeOut) {

		boolean isElementClicked = false;
		BasePage basePage = new BasePage(driver, test);

		if (element != null) {
			if (waitUntilElementIsVisible(element, timeOut)) {
				if (waitUntilElememtIsClickable(element, timeOut)) {
					try {
						Actions act = new Actions(driver);
						act = act.moveToElement(element);
						act.click().build().perform();
						isElementClicked = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					basePage.logStatus("info",
							typeOfElement + " is not clickable waiting till " + timeOut + " seconds");
				}
			} else {
				basePage.logStatus("info", typeOfElement + " is not visible waiting till " + timeOut + " seconds");
			}
		} else {
			basePage.logStatus("info", "Unable to find the element:-" + typeOfElement);
		}
		if (isElementClicked) {
			basePage.logStatus("info", "User clicked on " + typeOfElement);
		} else {
			basePage.logStatus("error", "User unable click " + typeOfElement);

		}

	}

	// To select the value from the drop down
	public void selectValueFromDropDown(List<WebElement> elements, String input, int timeout) {
		BasePage basePage = new BasePage(driver, test);
		boolean isValueSelected = false;
		if (elements == null) {
			basePage.logStatus("error", "Element not found");
		} else {
			for (WebElement option : elements) {
				String optionText = option.getAttribute("innerText");
				if (optionText.equalsIgnoreCase(input)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
					isValueSelected = true;
					break;
				} else {
					basePage.logStatus("error", "User selected the  option:-" + optionText);
				}
			}
		}
		if (isValueSelected) {
			basePage.logStatus("info", "User selected the  option:-" + input);
		} else {
			basePage.logStatus("error", "User unable to select the  option:-" + input);
		}
	}

	// To switch To Latest Window
	public void switchToLatestWindow() {
		BasePage basePage = new BasePage(driver, test);
		Set<String> windows = driver.getWindowHandles();
		for (String winHandle : windows) {
			driver.switchTo().window(winHandle);
			basePage.logStatus("info", "Switched to the window");
		}
	}

	// To get current url of child window
	public String getCurrentUrlofchildWindow() {
		BasePage basePage = new BasePage(driver, test);
		String url = "";
		Set<String> windows = driver.getWindowHandles();
		String parentwindow = driver.getWindowHandle();
		for (String winHandle : windows) {
			driver.switchTo().window(winHandle);
			if (!parentwindow.equalsIgnoreCase(winHandle)) {
				url = getCurrentUrl();
				basePage.logStatus("info", "Fetched the current url of child window");
				driver.close();
			}
			driver.switchTo().window(parentwindow);
		}
		return url;
	}

	// To scroll to particular element by web element
	public void scrollToParticularElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrollToParticularElement(String element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// To scroll to particular element by locator
	public void scrollToParticularElement(By locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-400)");
		} catch (Exception e) {

		}
	}

	// Scroll To Page End
	public void scrollTillEnd(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i < 30; i++) {
			try {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				Thread.sleep(500);
			} catch (Exception e) {
				break;
			}
		}
	}
	
	// Scroll To Page End
	public void scrollToTopUsingJavaScript(RemoteWebDriver driver) throws Exception {
		for (int i = 0; i < 30; i++) {
			try {
				((JavascriptExecutor) driver).executeScript("document.querySelector(\"#root > div > div.planner-container.amagi-splitshell-row > "
						+ "div.planner-sidebar.left-sidebar.show-sidebar > div:nth-child(1) > div.filter-container > div.middle-container\").scrollTop=00");
				Thread.sleep(500);
			} catch (Exception e) {
				break;
			}
		}
	}


	// To wait until element is visible by web element
	public boolean waitUntilElementIsVisible(WebElement element, int timeOut) {
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

	// To wait until element is visible by locator
	public boolean waitUntilElementIsVisible(By locator, int timeOut) {
		boolean isElementEnabled = false;
		if (locator != null) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
				wait.pollingEvery(Duration.ofMillis(200));
				isElementEnabled = true;
			} catch (Exception e) {
				isElementEnabled = false;
			}
		}
		return isElementEnabled;
	}

	// To wait until element is clickable
	public boolean waitUntilElememtIsClickable(WebElement element, int timeOut) {
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

	// To wait until element is clickable
	public boolean waitUntilElememtIsClickable(By locator, int timeOut) {
		boolean isElementEnabled;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementEnabled = true;
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			isElementEnabled = false;
		}
		return isElementEnabled;
	}

	// To wait until element is present by Webelement
	public boolean waitUntilElementIsPresent(WebElement element, int timeout) {
		boolean isElementPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementPresent = true;
		} 
		
		 catch (TimeoutException e) {
				isElementPresent = false;
				 System.out.println(String.format("[ELEMENT] Unable to Find %s within %s Seconds",element, timeout));
			}
		
		catch (Exception e) {
			isElementPresent = false;
			e.printStackTrace();
		}
		return isElementPresent;
	}

	// To wait until element is present by locator
	public boolean waitUntilElementIsPresent(By locator, int timeout) {
		boolean isElementPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.pollingEvery(Duration.ofMillis(200));
			isElementPresent = true;
		} 
		 catch (TimeoutException e) {
				isElementPresent = false;
				 System.out.println(String.format("[ELEMENT] Unable to Find %s within %s Seconds",locator, timeout));
			}
		
		
		catch (Exception e) {
			isElementPresent = false;
//			e.printStackTrace();
		}
		return isElementPresent;
	}

	// To refresh the page
	public void refreshPage() {
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
	}

	// To scroll to element
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	

	

	// drag and drop   to the element by element
	public void dragAndDropToElement(WebElement from,WebElement to) {
			BasePage report = new BasePage(driver, test);
			try {

					Actions action = new Actions(driver);
					action.dragAndDrop(from,to).build().perform();			

			} catch (Exception e) {
	 			report.logStatus("INFO", "Unable to drag and drop   to the element");
			}
		}
		
		// drag and drop   to the element by locator
	public void dragAndDropToElement(By from,By to) {
					BasePage report = new BasePage(driver, test);
					try {

							Actions action = new Actions(driver);
							action.dragAndDrop(driver.findElement(from), driver.findElement(to)).build().perform();			

					} catch (Exception e) {
			 			report.logStatus("INFO", "Unable to drag and drop   to the element");
					}
				}
	
	// To scroll to element
	public void scrollToElementInSearch(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,200)", "");
	}

	public void scrollDownBy500(RemoteWebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	// To mousehover to element by locator
	public void navigateBack() {
		BasePage report = new BasePage(driver, test);
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			report.logStatus("error", "Unable to navigate back");
		}
	}

	// To scroll to particular element by locator (to avoid tray header this method
	// can be used)
	public void scrollToParticularElementInPage(By locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
		} catch (Exception e) {

		}
	}

	public void navigateToGivenUrl(String url) {
		BasePage report = new BasePage(driver, test);
		try {
			driver.navigate().to(url);
			;
		} catch (Exception e) {
			e.printStackTrace();
			report.logStatus("error", "Unable to navigate to specified url");
		}
	}

	public String getTextOfAlert() {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void scrollDownUp()  {
		try {
			scrollTillEnd(driver);
			scrollToTop(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// To wait until  elements are visible by web element
	public boolean waitUntilElementAreVisible(List<WebElement> element, int timeOut) {
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
	
	// To wait until  elements are visible by locator
		public boolean waitUntilElementsAreVisible(By locator, int timeout) {
			boolean isElementEnabled;
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
				wait.pollingEvery(Duration.ofMillis(200));
				isElementEnabled = true;
			} catch (Exception e) {
				isElementEnabled = false;
			}
			return isElementEnabled;
		}
	

	

	public void printStackTrace(Exception e) {
//		 e.printStackTrace();
		
	}

	public void draganddrop (WebElement drag,WebElement drop)
	{

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("function createEvent(typeOfEvent) {\n" +"var event =document.createEvent"
				+ ""
				+ "(\"CustomEvent\");\n" +"event.initCustomEvent(typeOfEvent,true, true, null);\n" 
				+"event.dataTransfer = {\n" +"data: {},\n" +"setData: function (key, value) {\n" 
				+"this.data[key] = value;\n" +"},\n" +"getData: function (key) {\n" 
				+"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n" 
				+"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n" 
				+"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" 
				+"}\n" +"if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" 
				+"} else if (element.fireEvent) {\n" +"element.fireEvent(\"on\" + event.type, event);\n" 
				+"}\n" +"}\n" +"\n" +"function simulateHTML5DragAndDrop(element, destination) {\n" 
				+"var dragStartEvent =createEvent('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n" 
				+"var dropEvent = createEvent('drop');\n" +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" 
				+"var dragEndEvent = createEvent('dragend');\n" +"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" 
				+"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n" 
				+"simulateHTML5DragAndDrop(source,destination);",drag, drop);

	}
	
	public void cleartext(RemoteWebDriver driver, WebElement element, ExtentTest test) {
		try {
			for(int i=0; i<3; i++) {
				clickOnElement(element, "clickOnElement", 20);
				String c = Keys.BACK_SPACE.toString();
				element.sendKeys(c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c + c
						+ c + c + c + c + c + c + c + c + c + c + c + c + c);
				if(element.equals("")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String add0ToTheDate(String date) {
		String dateTest = date.substring(5);
		String removeSpaces = dateTest.trim().replaceAll("[, ]", "");
		String newdate = null;
		if(removeSpaces.length()<9) {
			String getSubDate = dateTest.substring(4, 7);
			newdate = dateTest.replaceAll(getSubDate.trim(), StringUtils.leftPad(getSubDate.trim(), 3, "0"));
		}else {
			newdate = dateTest;
		}
		System.out.println(newdate);
		return newdate;
	}

	public void rightClick(WebElement element, WebElement value) {
		EventUtilsnew event = new EventUtilsnew(driver, test);
		Actions act = new Actions(driver);
		try {
			act.moveToElement(element).contextClick().build().perform();
			sleep(5);
			if(!event.waitUntilElementIsVisible(value, 20)) {
				act.moveToElement(element).contextClick().build().perform();
				sleep(5);
			}
		}catch (Exception e) {
			if(!event.waitUntilElementIsVisible(value, 20)) {
				act.moveToElement(element).contextClick().build().perform();
				sleep(5);
			}
		}
	}
	public void clickByCoordinates(RemoteWebDriver driver,  WebElement locator) {
		// For Watch Tab, these are working x= 0.35, y = 0.69
		// For My Stuff Tab, these are working x= 0.50, y = 0.80
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();

		int startx = (int) (width * 0.5);
		int starty = (int) (height * 0.3);

		//		TouchAction action1 = new TouchAction(driver);
		try {

			for (int i = 0; i < 10; i++) {
				if (!waitUntilElementIsPresent(locator, 5)) {
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
	public void slideByPercentage(RemoteWebDriver driver, ExtentTest test, WebElement sliderLengthControl,
			String slider, String value) throws Exception {
		EventUtilsnew eventUtils =  new EventUtilsnew(driver, test);
		BasePage base1 = new BasePage(driver, test);
		try {
			if(slider.equalsIgnoreCase("min")) {
				if(value.equalsIgnoreCase("right")) {
					Actions move = new Actions(driver);
					move.moveToElement(sliderLengthControl).clickAndHold().moveByOffset(100, 30).build().perform();
				}else {
					Actions move = new Actions(driver);
					move.moveToElement(sliderLengthControl).clickAndHold().moveByOffset(-100, 00).build().perform();
				}
			}else {
				if(value.equalsIgnoreCase("right")) {
					Actions move = new Actions(driver);
					move.moveToElement(sliderLengthControl).clickAndHold().moveByOffset(100, 50).build().perform();
				}else {
					Actions move = new Actions(driver);
					move.moveToElement(sliderLengthControl).clickAndHold().moveByOffset(-50, 0).build().perform();
				}
			}
			eventUtils.sleep(8);
			base1.logStatus("info", "Able to scrub duration");
		}catch (Exception e) {
			base1.logStatus("warning", "Unable scrub duration");
		}
	}

	// To double click on WebElement, DoubletimeClick renamed to doubleClick
	public void doubleClick(RemoteWebDriver driver, WebElement el) {
		try {
			Actions action = new Actions(driver);
			action.doubleClick(el).perform();
		}catch (Exception e) {
			Actions action = new Actions(driver);
			action.moveToElement(el).doubleClick().perform();
		}
	}
	public void okButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_UP");
			sleep(1);
			adbCommand("KEYCODE_ENTER");

//			test.info("Clicked on OK Button");
			System.out.println("[INFO]:- Clicked on OK Button");
		} catch (Exception e) {
			System.err.println("Clicked on OK Button");
//			test.warning("Not able to click on Up Button");
		}
	}

	public void upButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_UP");
			sleep(1);
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
			sleep(1);
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
			sleep(1);
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
			sleep(1);
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
			sleep(1);
			} catch (Exception e) {
//			test.log(Status.INFO, "Not able to click on BACK Button");
			System.err.println("[ERROR]:- Not able to click on BACK Button");
		}
	}

	public void adbCommand(String command) throws Exception {
		Runtime.getRuntime().exec("adb -s " + Web_Constants.UDID + " shell input keyevent " + command);
	}
}
