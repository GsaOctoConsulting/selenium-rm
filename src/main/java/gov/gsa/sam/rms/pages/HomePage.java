package gov.gsa.sam.rms.pages;

import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.HomePageLocator;

import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;

public class HomePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(HomePage.class);
	private HomePage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on 
	//loading of this Page
	// *****************************************************************************
	public static void clickNavSignIn() {
		CommonMethods.delay(Constants.seconds);
		driver.findElement(HomePageLocator.navBarSignIn).click();
	}

	// *****************************************************************************
	// the following methods describe actions that are only available after some 
	//previous actions were taken on this SAME page..eg. clickNavSignIn()
	// *****************************************************************************
	
	public static void enterUserNamePasswordAndClickSignIn(String username, String password) {
		CommonMethods.delay(Constants.seconds);
		driver.findElement(HomePageLocator.emailPlaceHolder).sendKeys(username);
		driver.findElement(HomePageLocator.signInPasswordPlaceHolder).sendKeys(password);
		logger.info("Username entered --  "+username);
		CommonMethods.delay(1);
		driver.findElement(HomePageLocator.dropdownSignInButton).click();
	}

	public static void enterOtpAndSubmit() {
		driver.findElement(HomePageLocator.OTP_PLACEHOLDER).sendKeys(Constants.OTP);
		CommonMethods.delay(1);
		driver.findElement(HomePageLocator.SUBMIT_BUTTON).click();
		CommonMethods.delay(1);
	}

	public static void acceptGovPolicyPopup(){
		driver.findElement(HomePageLocator.POPUP_SUBMIT_BUTTON).click();
		CommonMethods.delay(3);
	}
	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return HomePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		HomePage.driver = driver;
	}

}
