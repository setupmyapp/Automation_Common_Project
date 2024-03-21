package com.mediacorp.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


public class RW_Page {

	WebDriver driver;
	ExtentTest test;

	public RW_Page(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='homeLogo']")
	public WebElement homelogo;

	@FindBy(xpath = "//a[@class='search']//img")
	public WebElement SearchButton;

	@FindBy(id = "searchInputBoxMob")
	public WebElement SearchField;

	@FindBys(@FindBy(xpath = "//ul//li"))
	public List<WebElement> optionsofSearchfield;

	@FindBy(xpath = "//button[text()='Play Now']")
	public WebElement playNowbtn;

	@FindBy(xpath = "//div[@class='content_Title']//h1")
	public WebElement titleofshow;

	@FindBy(xpath = "//span[@class='revamp-watchFree_title']//ancestor::button")
	public WebElement watchbtnOfshow;

	@FindBy(xpath = "//div[@class='ad-info-container']")
	public WebElement prerolladofShow;

	@FindBy(xpath = "//div[@class='ad-timer-container']")
	public WebElement adtimer;

	@FindBy(xpath = "//div[@class='ad-wrapper ad-layer-hidden']")
	public WebElement playerPage;

	@FindBy(xpath = "//div[@class='player-footer-controls']//..//img[@title='Pause']")
	public WebElement pausebtnOfShow;

	@FindBy(xpath = "//div[@class='player-header__middle-controls']//div")
	public WebElement headerOfshow;

	@FindBy(xpath = "//div[@class='player-header__left-controls']/img")
	public WebElement backbtnOfshow;

	@FindBy(xpath = "//div[@class='quality-button flex flex--center footer-controls-btn player-icon']")
	public WebElement videoQualitybtnOfshow;

	@FindBy(xpath = "//div[@class='subtitle-audio-button flex flex--center footer-controls-btn player-icon ']")
	public WebElement SubtitleandaudiobtnOfshow;

	@FindBy(xpath = "//div[@class='assets-list flex flex--center footer-controls-btn hover-btn player-icon']")
	public WebElement EpisodesbtnOfshow;

	@FindBy(xpath = "//*[@class='carousel-close']")
	public WebElement closebtnOfEpisode;

	@FindBy(xpath = "//div[@class='player-footer-controls']//div[@aria-label='10 seconds backward']")
	public WebElement backwardbtnOfshow;

	@FindBy(xpath = "//div[@class='player-footer-controls']//..//img[@title='Play']")
	public WebElement playbtnOfshow;

	@FindBy(xpath = "//div[@class='player-footer-controls']//div[@aria-label='10 seconds forward']")
	public WebElement forwardbtnOfshow;

	@FindBy(xpath = "//div[@class='player-footer-controls']//div[@class='volume-container footer-controls-btn player-icon']")
	public WebElement volumebtnOfshow;

	@FindBy(xpath = "//div[@class='player-footer-controls']//img[@class='fullscreen footer-controls-btn hover-btn player-icon']")
	public WebElement fullscreenbtnOfshow;

}
