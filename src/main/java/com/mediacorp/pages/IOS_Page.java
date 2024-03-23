package com.mediacorp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class IOS_Page {

	RemoteWebDriver driver;
	
	public IOS_Page(RemoteWebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.widget.TextView[@text='Skip']")
	public WebElement SkipbtnOfAndroid;

	@FindBy(xpath = "//android.widget.Button[@text='NOT NOW']")
	public WebElement notNowbtnOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='sonyLivLogo']")
	public WebElement HomeLogoOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='tabBarButtonSearch']")
	public WebElement SearchButtonOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeTextField[@value='Search for movies, shows, sports etc.']")
	public WebElement SearchFieldOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeTextField[@value='Type your search here']")
	public WebElement SearchFieldOfEnter;
	
	
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Resume')]")
	public WebElement resumeButtonIOS;

	
	@FindBy(xpath = "//XCUIElementTypeImage[@name='Mask']")
	public WebElement searchResultContentOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeImage[@name='Mask']")
	public WebElement PlayButtonOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Detailspage_titlename']")
	public WebElement TitleOfSearchResultOfAndroid;

	@FindBy(xpath = "//XCUIElementTypeImage[@name='watch_icon']")
	public WebElement watchButtonOfAndroid;
	
	
	@FindBy(xpath = "//XCUIElementTypeButton[@name='fullScreenIcon']")
	public WebElement playerFullScreenIcon;
	
	@FindBy(xpath = "//XCUIElementTypeOther[@name='Carousel_Ad']")
	public WebElement playerad;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='player back']")
	public WebElement playerBackButton;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='Pause']")
	public WebElement playerPauseButton;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='Video Quality']")
	public WebElement playerVideoQualityButton;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='Subtitle & Audio']")
	public WebElement playerVideoSubtitleButton;

	@FindBy(xpath = "//XCUIElementTypeButton[@name='Episodes']")
	public WebElement playerEpisodesButton;

	

	//XCUIElementTypeButton[@name="Play"]





}
