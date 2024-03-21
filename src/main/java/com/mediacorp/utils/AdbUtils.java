package com.mediacorp.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class AdbUtils {
	
	public void okButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_ENTER");
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}
	
	public void upButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_UP");
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}

	public void downButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_DOWN");
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}

	public void rightButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_RIGHT");
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}
	public void leftButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_DPAD_LEFT");
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}
	
	public void backButton(ExtentTest test) {
		try {
			adbCommand("KEYCODE_BACK");
		} catch (Exception e) {
			test.log(Status.INFO, "Not able to click on Up Button");
		}
	}

	public void adbCommand(String command) throws Exception {
		Runtime.getRuntime().exec("adb -s "+ Web_Constants.UDID + " shell input keyevent "+command);
	}
	
}
