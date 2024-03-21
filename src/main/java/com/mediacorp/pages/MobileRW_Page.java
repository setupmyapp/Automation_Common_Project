package com.mediacorp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


public class MobileRW_Page {

	RemoteWebDriver driver;
	ExtentTest test;

	public MobileRW_Page(RemoteWebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='overlayModalModalClose']")
	public WebElement installPopUpOfMobileRW;

	@FindBy(xpath = "//button[text()='Skip']")
	public WebElement notificationPopUpOfMobileRW;

	@FindBy(xpath = "//div[@class='header-left-container']//img[@title='Sony LIV']")
	public WebElement homelogoOfMobileRW;

	@FindBy(xpath = "//div[@class='search']")
	public WebElement searchOfMobileRW;

	@FindBy(id = "search")
	public WebElement inputofSearchOfMobileRw;

	@FindBy(xpath = "//*[@class='watchNowContainer']")
	public WebElement watchbtnOfMobileRW;

	@FindBy(xpath = "//h1[@class='content-title']")
	public WebElement titleofEpisodepageOfMobileRW;

	@FindBy(xpath = "//div[@class='play-button-text-container columnText']")
	public WebElement watchbtnOfEpisodepageOfMobileRw;

	@FindBy(xpath = "//div[@class='ad-info-container']")
	public WebElement preRollAdofShowPageOfMobileRW;

	@FindBy(xpath = "//div[@aria-label='Video Player']")
	public WebElement videoPlayerOfMobileRW;

	@FindBy(xpath = "//div[@class='player-middle-controls']//Img[@title='Pause']")
	public WebElement pausebtnOfMobileRw;

	@FindBy(xpath = "//div[@class='player-middle-controls']//Img[@title='Play']")
	public WebElement playbtnOfMobileRW;

	@FindBy(xpath = "//div[@class='episode-title-metadata-container']")
	public WebElement headerbtnOfMobileRW;

	@FindBy(xpath = "//div[@class='player-header__left-controls']//img")
	public WebElement backbtnOfshowpageOfMobileRW;

	@FindBy(xpath = "//div[@class='player-middle-controls']//div[@aria-label='10 seconds backward']")
	public WebElement backWardbtnOfshowPageOfMobileRW;

	@FindBy(xpath = "//div[@class='player-middle-controls']//div[@aria-label='10 seconds forward']")
	public WebElement forWardbtnOfShowPageOfMobileRW;

	@FindBy(xpath = "//img[@class='mwebVolume-icon']")
	public WebElement volumebtnOfShowPageOfMobileRW;

	@FindBy(xpath = "//img[@class='mwebFullscreen-icon']")
	public WebElement fullScreenOfshowOfMobileRW;

	@FindBy(xpath = "//div[@class='setting-wrapper']")
	public WebElement settingbtnOfShowOfMobileRW;

	@FindBy(xpath = "//div[@data-value='quality']")
	public WebElement videoQualitybtnOfshowOfMobileRW;

	@FindBy(xpath = "//div[@data-value='subtitle']")
	public WebElement subtitlebtnOfshowOfMobileRW;

	@FindBy(xpath = "//div[@data-value='audio']")
	public WebElement audiobtnOfshowOfMobileRW;

}
