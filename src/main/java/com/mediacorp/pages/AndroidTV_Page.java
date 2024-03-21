package com.mediacorp.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class AndroidTV_Page {

	
	RemoteWebDriver driver;
	ExtentTest test;

	public AndroidTV_Page(RemoteWebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id = "com.sonyliv:id/sonyLivLogo")
	public WebElement HomeLogoOfAndroidTV;
	
	@FindBy(xpath = "(//android.widget.ImageView[@resource-id='com.sonyliv:id/menu_image'])[2]")
	public WebElement searchButtonOfAndroidTv;
	
	@FindBy(id = "com.sonyliv:id/editSearch")
	public WebElement searchFieldOfAndroidTv;
	
	@FindBys(@FindBy(xpath="//android.widget.TextView[@resource-id='com.sonyliv:id/item_name']"))
	public List<WebElement> SearchResultsOfAndroidTv;
	
	@FindBy(id = "com.sonyliv:id/title_text")
	public WebElement TitleofSearchResultOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/CTA_text")
	public WebElement platButtonOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/main_layout")
	public WebElement SearchResultContentOfAndroidTv;
	
	@FindBy(xpath = "(//android.widget.LinearLayout[@resource-id='com.sonyliv:id/main_button'])[1]")
	public WebElement WatchButtonOfAndroidTV;
	
	@FindBy(xpath = "(//android.view.View)[2]")
	public WebElement playerOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/iconBack")
	public WebElement BackButtonOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/play_back_controls_play_pause")
	public WebElement PlayandpauseButtonOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id="com.sonyliv:id/iconSetting")
	public WebElement settingButtonOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/iconStartFromBeginning")
	public WebElement StartFromBeginningButtonOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/iconNextContent")
	public WebElement nextContentButtonOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/play_back_controls_episode_title")
	public WebElement episodeTitleOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/iconLanguage")
	public WebElement LanguagebuttonOfShowDetailsPageOfAndroidTV;
	
	@FindBy(id = "com.sonyliv:id/play_back_controls_progress")
	public WebElement controlProgressOfShowDetailsPageOfAndroidTV;
}
