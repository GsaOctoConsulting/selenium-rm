package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class HomePageLocator {// contains locators for HomePageOnly
	public static final String title = "beta.SAM.gov | Home";

	// the following locators are available without user action
	public static final By navBarSignIn = By.xpath("//*[@id=\"signin-button\"]");

	// the following locators are avaialble after some user action
	public static final By emailPlaceHolder = By.xpath("//*[@id=\"signin-username\"]");
	public static final By signInPasswordPlaceHolder = By.id("signin-password");
	public static final By dropdownSignInButton = By.className("text-left");

	public static final By OTP_PLACEHOLDER = By.xpath("//*[@id=\"signin-otp\"]");
	public static final By SUBMIT_BUTTON = By.xpath(
			"//*[@id=\"signInDropdownArea\"]/div/div/div/sam-login/div/div/div/form/div/button");

	public static final By POPUP_SUBMIT_BUTTON = By.className("usa-modal-content-submit-btn");

	private HomePageLocator() {

	}
}