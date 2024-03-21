package com.mediacorp.utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.mediacorp.pages.BasePage;

public class NetworkThrottleUtils {
	public static InetAddress localhost;
	public static String ipAdress;
	public static RemoteWebDriver driver;

	public NetworkThrottleUtils(RemoteWebDriver driver)
	{
		this.driver=driver;
	}

	public static void openCharles(String operatingMachine, ExtentTest test) throws IOException, InterruptedException {
		BasePage basePage = new BasePage(driver, test);

		switch (operatingMachine.toLowerCase()) {
		case "windows":
			Runtime.getRuntime().exec("C:\\Program Files\\Charles\\Charles.exe");
			basePage.logStatus("info", "Charles opened on windows");

			break;

		case "mac":
			Runtime.getRuntime().exec("open /Applications/Charles.app");
			basePage.logStatus("info", "Charles opened on mac");
			break;

		default:
			break;
		}

		System.out.println("Charles was Opened Sucessfully");
		Thread.sleep(20000);
		localhost = InetAddress.getLocalHost();
		ipAdress = localhost.getHostAddress().trim();
		basePage.logStatus("info", "ipAdress of the system is : " + ipAdress);
		System.out.println("System IP Address : " + ipAdress.trim());
		Thread.sleep(20000);
	}

	public void exportCharlesInXmlFormat(String xmlPath, ExtentTest test) throws IOException {
		BasePage basePage = new BasePage(driver, test);

		Runtime.getRuntime().exec(
				"curl -o " + xmlPath + " -x http://" + ipAdress + ":8888 http://control.charles/session/export-xml");
		basePage.logStatus("info", "xml file details path : " + xmlPath);

	}

	public void exportCharlesInChlsFormat(String filePathlogs, ExtentTest test) throws IOException {
		BasePage basePage = new BasePage(driver, test);

		Runtime.getRuntime().exec(
				"curl -o " + filePathlogs + " -x http://" + ipAdress + ":8888 http://control.charles/session/download");
		basePage.logStatus("info", "xml file details path : " + filePathlogs);

	}

	public static void createFolder(String foldPath) {
		File f = new File(foldPath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	public static void startRecordingOfCharles(ExtentTest test) {
		try {
			BasePage basePage = new BasePage(driver, test);

			InetAddress localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/recording/start");
			basePage.logStatus("info", "recording started");
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void activateThrottlingTo4G(ExtentTest test) {
		try {
			BasePage basePage = new BasePage(driver, test);

			InetAddress localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			Runtime.getRuntime().exec(
					"curl -v -x http://" + ipAdress + ":8888 http://control.charles/throttling/activate?preset=4G");
			Thread.sleep(5000);
			basePage.logStatus("info", "4g throttling started");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void activateThrottlingTo3G(ExtentTest test) {
		try {
			BasePage basePage = new BasePage(driver, test);

			InetAddress localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			Runtime.getRuntime().exec(
					"curl -v -x http://" + ipAdress + ":8888 http://control.charles/throttling/activate?preset=3G");
			Thread.sleep(5000);
			basePage.logStatus("info", "3g throttling started");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearCharles(ExtentTest test) {
		try {
			BasePage basePage = new BasePage(driver, test);

			InetAddress localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(5000);
			basePage.logStatus("info", "Charles cleared logs ");

		} catch (Exception e) {
		}
	}

	public static void closeCharles(ExtentTest test) {
		try {
			BasePage basePage = new BasePage(driver, test);

			InetAddress localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/quit");
			Thread.sleep(5000);
			basePage.logStatus("info", "Charles closed ");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void enableParticularPreset(String presetName, ExtentTest test) {

		try {
			BasePage basePage = new BasePage(driver, test);

			InetAddress localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			String pName = "curl -v -x http://" + ipAdress + ":8888 http://control.charles/throttling/activate?preset="
					+ presetName + "";

			Runtime.getRuntime().exec(pName);
			Thread.sleep(5000);
			basePage.logStatus("info", "Charles preset :  " + presetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
