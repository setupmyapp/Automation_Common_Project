package com.mediacorp.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.TestNGCucumberRunner;

public class ReportConfigurations extends AbstractTestNGCucumberTests {

	public ReportUtils reportUtils;

	public InetAddress systemAddress;
	Utilities utilities;

//	@Parameters({ "OS", "Browser", "Project" })
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(@Optional ITestContext ctx, @Optional String OS, @Optional String Browser,
			@Optional String Project ,@Optional String platformName ,@Optional String browser_Stack ,@Optional String KLOV_FLAG) {

		reportUtils = new ReportUtils();

		 try {
			utilities = new Utilities();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}

		Web_Constants constants = new Web_Constants();

		Project = System.getProperty("Project");
		if (Project == null) {
			System.out.println("Jenkins Project Value:-" + Project);
			Project = Web_Constants.PROJECT;
		}
		
		OS = System.getProperty("OS");
		if (OS == null) {
			System.out.println("Jenkins OS Value:-" + OS);
			OS = Web_Constants.OS;
		}

		String environment = System.getProperty("Environment");
		if (environment == null) {
			System.out.println("Jenkins Environment Value:-" + environment);
			environment = Web_Constants.ENV;
		}

		Browser = System.getProperty("Browser");
		if (Browser == null) {
			System.out.println("Jenkins Browser Value:-" + Browser);
			Browser = Web_Constants.browser;
		}

		String UDID = System.getProperty("UDID");
		if (UDID == null) {
			System.out.println("Jenkins UDID Value:-" + UDID);
			UDID = Web_Constants.UDID;
		}
		
		 platformName = System.getProperty("platformName");
		if (platformName == null) {
			System.out.println("Jenkins platformName Value:-" + platformName);
			platformName = Web_Constants.PLATFORM_NAME;
		}
		
		browser_Stack= System.getProperty("browser_Stack");

		if (browser_Stack == null) {
			System.out.println("Jenkins browser_Stack Value:-" + browser_Stack);
			browser_Stack = Web_Constants.browser_Stack;
		}
		
		KLOV_FLAG= System.getProperty("KLOV_FLAG");

		if (KLOV_FLAG == null) {
			System.out.println("Jenkins KLOV_FLAG Value:-" + KLOV_FLAG);
			KLOV_FLAG = Web_Constants.KLOV_FLAG;
		}
		
		switch (UDID) {

		case "7a94414fd90ea61ee56520c77ce8ed4ef18385ef": {
			Web_Constants.DEVICE_NAME = "iPhone SE";
			Web_Constants.DEVICE_VERSION = "14.0";
			break;
		}

		case "00008030-001415193AD3402E": {
			Web_Constants.DEVICE_NAME = "iPhone SE";
			Web_Constants.DEVICE_VERSION = "14.7.1";
			break;
		}

		default: {
			break;
		}
		}

		String J_REPORT_EMAIL = System.getProperty("REPORT_EMAIL");
		if (J_REPORT_EMAIL == null) {
//			J_REPORT_EMAIL = String.valueOf(Web_Constants.REPORT_EMAIL);
		}

		constants.Web_ConstantsConfiguration(OS, Browser, Project, UDID, environment, platformName,browser_Stack,KLOV_FLAG);

		String suiteName;
		if (Web_Constants.PROJECT.equalsIgnoreCase("web") || Web_Constants.PROJECT.equalsIgnoreCase("MobileRW")) {
			suiteName = ctx.getCurrentXmlTest().getSuite().getName() + "#" + platform() + "#"
					+ Web_Constants.ENV + "#" + Web_Constants.browser+"#"+Web_Constants.webBuildVersion;
		} else {
			suiteName = ctx.getCurrentXmlTest().getSuite().getName() + "#" + platform() + "#"
					+ Web_Constants.ENV + "#" + Web_Constants.browser+"#" +Web_Constants.APPBuildVersion+"#"+ Web_Constants.UDID;
		}

		if (suiteName.toLowerCase().contains("default")) {
			suiteName = this.getClass().getSimpleName();
		}

		reportUtils.initializeReport(suiteName);

		try {
			systemAddress = Inet4Address.getLocalHost();
			System.out.println("SystemAddress:-" + systemAddress);
		} catch (UnknownHostException e) {

		}

		String IP_adress = systemAddress.getHostAddress().replace(".", "_");
		System.out.println("AP_Adress:-" + IP_adress);
		if (Web_Constants.OS.equalsIgnoreCase("Windows")) {
			utilities.createFolder(Web_Constants.SCREENSHOT_PATH);
			reportUtils.screenShotFolderPath = Web_Constants.SCREENSHOT_PATH;
			reportUtils.screenShotFilePath = Web_Constants.SCREENSHOT_PATH + IP_adress + "\\" + getDateStamp() + "\\";
			System.out.println("ReportScreenshot");
			System.out.println("ReportFilePath");
			System.out.println("AP_Adress:-" + IP_adress);
		} else if (Web_Constants.OS.equalsIgnoreCase("Mac")) {
			utilities.createFolder(Web_Constants.SCREENSHOT_PATH_MAC);
			reportUtils.screenShotFolderPath = Web_Constants.SCREENSHOT_PATH_MAC;
			reportUtils.screenShotFilePath = Web_Constants.SCREENSHOT_PATH_MAC + IP_adress + "/" + getDateStamp() + "/";
		}

	}

	@AfterSuite(alwaysRun = true)
	public void afterSetUp() {
		
		
		ReportUtils.report.flush();

		if (!Web_Constants.ExtentReportPath.toLowerCase().contains("runner")) {
			SendMailWeb mailWeb = new SendMailWeb();
			try {
//				mailWeb.reportMail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		


	}

	
	public String platform() {
		
		String platform="";
		
		switch (Web_Constants.PROJECT) {
		case "WEB": {
			platform ="RW";
			break;
		}
		case "MobileRW": {
			platform ="MRW";
			break;
		}
		case "AndroidApp": {
			platform ="AndroidAPP";
			break;
		}
		case "iOSApp": {
			platform ="iOSAPP";
			break;
		}
		
		}
		return platform;
		
	}
	
	public String getDateStamp() {
		DateFormat dfor = new SimpleDateFormat("ddMMyyyy");
		Date obj = new Date();
		String date = dfor.format(obj);
		return date;
	}

	public String hourStamp() {
		Date d = new Date();
		String hour = String.valueOf(d.getHours());
		return hour;
	}

	// To get current time
	public static String getTimeStamp() {
		Date d = new Date();
		return d.toString().replace(":", "_").replace(" ", "_");
	}

}
