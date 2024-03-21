package com.mediacorp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class Android_Page {

	RemoteWebDriver driver;
	
	public Android_Page(RemoteWebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.widget.TextView[@text='Skip']")
	public WebElement SkipbtnOfAndroid;

	@FindBy(xpath = "//android.widget.Button[@text='NOT NOW']")
	public WebElement notNowbtnOfAndroid;

	@FindBy(id = "com.sonyliv:id/iv_home_logo")
	public WebElement HomeLogoOfAndroid;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.sonyliv:string/search']")
	public WebElement SearchButtonOfAndroid;

	@FindBy(id = "com.sonyliv:id/search_edit_text")
	public WebElement SearchFieldOfAndroid;

	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.sonyliv:id/card_show_name'])[1]")
	public WebElement searchResultContentOfAndroid;

	@FindBy(id = "com.sonyliv:id/spotlight_textview")
	public WebElement PlayButtonOfAndroid;

	@FindBy(id = "com.sonyliv:id/details_show_name")
	public WebElement TitleOfSearchResultOfAndroid;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.sonyliv:id/play_button_holder']//android.widget.LinearLayout")
	public WebElement watchButtonOfAndroid;

}
