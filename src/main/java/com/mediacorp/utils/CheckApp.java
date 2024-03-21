package com.mediacorp.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class CheckApp 
{
    
	boolean appLive=false;
	String appActivity="com.android.vending";
	String apppackage="com.android.vending.AssetBrowserActivity";
	String url="https://app.bitrise.io/app/c706b5afc5759c07/build/9e0bc3d7-83c1-49b0-a507-cf20c8b2ab99/artifact/4597734673926d94/p/7e924433c19f3cf0c76fe01c842122ba";
	WebDriver chromedriver;
	
	AppiumDriver driver;
	AppiumDriverLocalService service;
	
	 @Test
	public void launchServerWithCondition() throws InterruptedException
	{
		
		
		//service=AppiumDriverLocalService.buildDefaultService();
		
		
				
				// buildDefaultService();
		service = 	AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
						.withAppiumJS(new File("C:\\Users\\IFOCUS.DESKTOP-JHF0INJ\\node_modules\\appium\\build\\lib\\main.js"))
						.withIPAddress("127.0.0.1").usingPort(4723)
						.withArgument(GeneralServerFlag.LOG_LEVEL, "error"));// this is the flag to remove debug
		if (service.isRunning() == true) {
			service.stop();
		} else {
			service.start();
			System.out.println("[EVENT] Appium Server Started Sucessfully.");
		}

		boolean statusOfService=service.isRunning();
		if(statusOfService==true)
		{
			service.stop();
			try {
				Thread.sleep(10000);
				service.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			service.start();
		}
		
		DesiredCapabilities dCaps=new DesiredCapabilities();
		dCaps.setCapability("appPackage", "com.android.vending");
		dCaps.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
		dCaps.setCapability("deviceName", "Moto-G5S");
		dCaps.setCapability("platformName", "Android");
		dCaps.setCapability("udid", "ZY322CF54R");
		  
		try 
		{
			driver = new AndroidDriver(service.getUrl(), dCaps);
			
			try {
				Runtime.getRuntime().exec("adb uninstall sg.mediacorp.android");
				System.out.println("[Event]  Me wwatch if present, then Uninstalled or Not Present Now");
				Thread.sleep(10000);
			} catch (Exception g) {
				System.err.println("[EXCEPTION] Unable to Uninstall Voot App");
				 
			}
			
			driver = new AndroidDriver(service.getUrl(), dCaps);
		} catch (Exception e) 
		{
		    	
		}
		 
		
		if(appLive==true)
		{
			try {
				Runtime.getRuntime().exec("adb uninstall sg.mediacorp.android");
				System.out.println("[Event]  Me wwatch if present, then Uninstalled or Not Present Now");
				Thread.sleep(10000);
			} catch (Exception g) {
				System.err.println("[EXCEPTION] Unable to Uninstall Voot App");
				 
			}
			
			String searchBox="//android.widget.ImageView[@content-desc=\r\n" + 
					"\"Voice Search\"]//parent::android.view.ViewGroup//android.widget.TextView";
		
		    try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		    driver.findElement(By.xpath(searchBox)).click();
		    
		    
		    String searchBoxField="//android.widget.EditText[@text=\r\n" + 
		    		"\"Search for apps & games\"]";
		    
		    driver.findElement(By.xpath(searchBoxField)).sendKeys("ABC");;
		    
		    
		    
		}else
		{
			try {
				Runtime.getRuntime().exec("adb uninstall com.android.vending");
				System.out.println("[Event]  Me wwatch if present, then Uninstalled or Not Present Now");
				Thread.sleep(10000);
			} catch (Exception g) {
				System.err.println("[EXCEPTION] Unable to Uninstall Voot App");
				 
			}
			
			
			 chromedriver=new ChromeDriver();
			 chromedriver.get(url);
			
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				Thread.sleep(10000);
			String downloadXpath="//a[text()='Download']";
			chromedriver.findElement(By.xpath(downloadXpath)).click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String filePath=checkFilepathInDirectory();
			
			  boolean installedStatus= isAppIstalled(driver,filePath);
			System.err.println(installedStatus);
						
			
		}
		
	 }
	 
	 
	  
   
   public String checkFilepathInDirectory()
   {
	  String filePath=null;;
		String home = System.getProperty("user.home");
		System.out.println(home+"/Downloads/");
		String downloadFolder=home+"/Downloads/";	     
		File dir = new File(downloadFolder);
		      FilenameFilter filter = new FilenameFilter() {
		         public boolean accept (File dir, String name) { 
		            return name.startsWith("mewatch");
		         } 
		      }; 
		      String[] children = dir.list(filter);
		      if (children == null) {
		         System.out.println("Either dir does not exist or is not a directory"); 
		      } else { 
		         for (int i = 0; i< children.length; i++) {
		            String filename = children[i];
		            System.out.println(filename);
		            filePath=filename;
		            break;
		         } 
		      } 
		      
		      return downloadFolder+filePath;
   }
 
  
	 public boolean isAppIstalled(WebDriver driver , String filepath) throws InterruptedException {
			 
		 boolean isAppIstalled=false;
				try 
				{
					
					String filePath=checkFilepathInDirectory();
					String str = "adb install "+filePath.replace(" ", "");
					Runtime.getRuntime().exec(str);
					Thread.sleep(15000);
					
					for(int i=0;i<5;i++)
					{
						try {
							boolean istalled=((AndroidDriver)driver).isAppInstalled("sg.mediacorp.android");
							if(istalled==true)
							{
								isAppIstalled=true;
								System.out.println("installed");
								break;
							}else
							{
								Thread.sleep(1500);
								
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
 	
					
				} catch (IOException e) {

					System.out.println("Failed due to Exception: " + e.getMessage());
					System.out.println("uninstall not req'd");
				}
				return isAppIstalled;
			 
		}



		 
	
}
