package com.mediacorp.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mediacorp.utils.Utilities;
import com.mediacorp.utils.Web_Constants;

public class BasePage {

	RemoteWebDriver driver;
	ExtentTest test;

	public BasePage(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void logStatus(String status, String Message) {

		
		switch (status.toLowerCase()) {
		case "info":
			System.out.println("["+status.toUpperCase()+"]"+" : "+Message);
			test.info(MarkupHelper.createLabel(Message, ExtentColor.WHITE));
			break;
		case "warning":
			System.err.println("["+status.toUpperCase()+"]"+" : "+Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.WHITE));
			break;
		case "error":
			System.err.println("["+status.toUpperCase()+"]"+" : "+Message);
			test.warning(MarkupHelper.createLabel(Message, ExtentColor.RED));
			logScreenShot(Status.INFO, "");
			break;
		case "pass":
			test.pass(MarkupHelper.createLabel(Message, ExtentColor.GREEN));
			System.out.println("["+status.toUpperCase()+"]"+" : "+Message);
			break;
		case "fail":
			System.err.println("["+status.toUpperCase()+"]"+" : "+Message);
			test.fail(MarkupHelper.createLabel(Message, ExtentColor.RED));
			logScreenShot(Status.INFO, "");
			break;
		case "skip":
			test.skip(Message);
			break;
		default:
//			test.error("Invalid Status");
//			test.
			break;
		}

	}

	public void logScreenShot(Status status, String details) {

		Utilities utilities = new Utilities();
		Web_Constants VKConstants = new Web_Constants();

		if (VKConstants.SCREENSHOT_TO_FOLDER) {
			try {
				test.log(status, details, MediaEntityBuilder.createScreenCaptureFromPath(
						utilities.captureScreenshot(driver, VKConstants.SCREENSHOT_TO_FOLDER)).build());
			} catch (Exception e) {
				e.printStackTrace();
				test.log(Status.INFO, "Unable to take a screenshot");
			}
		} else if (!VKConstants.SCREENSHOT_TO_FOLDER) {
			try {
				test.log(status, details, MediaEntityBuilder.createScreenCaptureFromBase64String(
						utilities.captureScreenshot(driver, VKConstants.SCREENSHOT_TO_FOLDER)).build());
			} catch (Exception e) {
				e.printStackTrace();
				test.log(Status.INFO, "Unable to take a screenshot");
			}
		}
	}

}
