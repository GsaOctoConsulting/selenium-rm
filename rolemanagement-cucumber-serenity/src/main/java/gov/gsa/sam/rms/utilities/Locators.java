package gov.gsa.sam.rms.utilities;

import org.openqa.selenium.By;

public class Locators {
	// common locators (locators used on many pages)
		public static final By tab = By.cssSelector("body");
		public static final By SignIn = By.linkText("Sign in");
		public static final By email = By.id("signin-username");
		public static final By Password1 = By.id("signin-password");

		public static final By signInButton = By.className("usa-button-controls");// By.xpath("//*[@class='usa-button-controls']//*[text()='Sign
																					// In']");
		public static final By SignInLook = By.xpath("//*[@class=\"buttonLargeBlue\"]"); // class="buttonLargeBlue"
		public static final By SignOutLook = By.id("signIn");
		public static final By signOutSamMenuButton = By.xpath("//*[@class=\"fa fa-bars\"]"); // title="menu"
		public static final By signOutSamButton = By.xpath("//*[@class=\"icon fa fa-sign-out\"]");
		public static final By SignIn2 = By.xpath("//*[@class='usa-button-primary']");
		public static final By pinBox = By.id("signin-otp");
		public static final By samSubmitButton1 = By.xpath("//*[@class=\"usa-button-controls\"]//*[@type='submit']");
		public static final By samSubmitButton2 = By.xpath("//*[text()='Submit']"); // [@class=\"usa-button-controls\"]
		public static final By mailbox = By.id("_ariaId_40.folder");
		public static final By Forgot = By.className("forgot_password");
		public static final By Sign_In_With_Card = By.className("usa-button-primary m_T-3x m_B-5x");
		public static final By Sign_In_Info = By.className("inline btn-round sign-in-info");
		public static final By userMail = By.xpath("//*[text()='Email, phone, or Skype']");
		public static final By nextButton = By.id("idSIButton9");
		public static final By passMail = By.xpath("//*[@placeholder=\"Password\"]");
		public static final By next = By.xpath("//*[@value=\"Next\"]"); // id="iLandingViewAction"
		public static final By dismissFeedback = By.xpath("//*[@class=\"usa-alert-close\"]");
		public static final By menu = By.xpath("//*[@class=\"fa fa-bars\"]"); // title="menu"
		public static final By workspaceIcon = By.xpath("//*[@class=\"item\"]");

		public static final By searchIcon = By.className("fa-search");
		public static final By domain = By.id("filter");
		public static final By searchButton = By.className("search-btn");
		public static final By recordDetailsLink = By.xpath("//*[@href=\"/wage-determination/FL20180190/1\"]");
		public static final By followBtn = By.className("fa-stack");

		// locators for GSA gmail
		public static final By myGsaEmail = By.xpath("//*[@aria-label=\"Email or phone\"]");
		public static final By myGsaNextButton = By.xpath("//*[text()='Next']");
		public static final By gmailInbox = By.xpath("//*[@class=\"J-Ke n0\"]");
		public static final By gmailLastIaeEmail = By.xpath("//*[@class='y6']");
		public static final By iaeOtp = By.className("a3s");// By.xpath("//*[@class='ii
															// a3s']");//ii gt adP
															// adO
		public static final By g = By.xpath("//*[@email=\"no-reply-iae@gsa.gov\"]");
		public static final By GMAIL_TOPRIGHT_ACCOUNTBUTTON = By
				.xpath("//*[@id=\"gb\"]/div[1]/div[1]/div[2]/div[5]/div[1]/a/span");
		public static final By GMAIL_SIGNOUT = By.xpath("//*[@id=\"gb_71\"]");
	}