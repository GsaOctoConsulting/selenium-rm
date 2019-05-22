package gov.gsa.sam.rms.utilities;

import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.login.gov.pages.CommonProfilePage;
import gov.gsa.sam.rms.login.gov.pages.SigninPage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.stepdefinition.LoginStep;

public class SignUpUtility {
	private static Logger logger = LoggerFactory.getLogger(SignUpUtility.class);
	private static PropertiesFileUtility applicationproperties = new PropertiesFileUtility("utility.properties");

	public static void signUpNewUser(String useremail, String password) throws Exception {

		if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL2")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			MyWorkspacePage.setDriver(LaunchBrowserUtil.getDriver());
			MyWorkspacePage.clickSignInButton();
			MyWorkspacePage.clickAcceptOnBanner();
			SigninPage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(4);
			SigninPage.clickCreateAnAccount();
			SigninPage.enterEmailAddress(useremail);
			SigninPage.clickSubmit();
			SigninPage.clickResend();
			// capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromGmail();
			SigninPage.enterSignUpPassword(password);
			SigninPage.clickContinue();
			SigninPage.clickAuthenticationApplication();
			SigninPage.clickContinue();
			String secretCode = SigninPage.getQRSecretCode();
			String otp = LaunchBrowserUtil.getOtp(useremail, secretCode);
			logger.info("The captured secret code is --- " + secretCode);
			SigninPage.enterOtp(otp);
			SigninPage.clickSubmitOnOtp();
			SigninPage.clickGetStarted();
			SigninPage.clickUseYourComputer();
			SigninPage.uploadFileWithUrl(Constants.FILE_UPLOAD_PATH_FRONT_PIC);
			SigninPage.clickContinue();
			SigninPage.uploadFileWithUrl(Constants.FILE_UPLOAD_PATH_BACK_PIC);
			SigninPage.clickContinue();
			/*
			 * SigninPage.selectStateforDriversPermit("Virginia");
			 * SigninPage.clickContinue();
			 */

			SigninPage.enterSSN();
			SigninPage.clickContinue();
			SigninPage.clickContinue();
			SigninPage.clickContinue();
			SigninPage.enterPhoneNoForIdentityVerification(ConstantsAccounts.PHONE);
			SigninPage.clickContinue();
			SigninPage.clickTextMessage();
			SigninPage.clickContinue();
			String phoneotp = LaunchBrowserUtil.getPhoneOtpFromEmail(Constants.GMAIL_USERNAME);
			SigninPage.enterOtp(phoneotp);
			SigninPage.clickSubmit();

			SigninPage.enterPassword(password);
			SigninPage.clickSubmit();
			String personalkey = SigninPage.getPersonalKey();
			LaunchBrowserUtil.scrollAllTheWayDown();
			SigninPage.clickContinueOnPersonalKey();
			SigninPage.enterPersonalKeyOnAlertBox(personalkey);
			SigninPage.clickPersonalKeyConfirm();
			SigninPage.clickContinueToBeta();
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(3);
		} else if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL1")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			MyWorkspacePage.setDriver(LaunchBrowserUtil.getDriver());
			MyWorkspacePage.clickSignInButton();
			MyWorkspacePage.clickAcceptOnBanner();
			SigninPage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(4);
			SigninPage.clickCreateAnAccount();
			SigninPage.enterEmailAddress(useremail);
			SigninPage.clickSubmit();
			SigninPage.clickResend();
			// capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromGmail();
			SigninPage.enterSignUpPassword(password);
			SigninPage.clickContinue();
			SigninPage.clickAuthenticationApplication();
			SigninPage.clickContinue();
			String secretCode = SigninPage.getQRSecretCode();
			String otp = LaunchBrowserUtil.getOtp(useremail, secretCode);
			logger.info("The captured secret code is --- " + secretCode);
			SigninPage.enterOtp(otp);
			SigninPage.clickSubmitOnOtp();
			SigninPage.clickGetStarted();
			SigninPage.enterMobilePhoneNo(ConstantsAccounts.PHONE);
			SigninPage.clickSubmit();
			String otpFromText=LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUp(Constants.GMAIL_USERNAME);
			SigninPage.enterOtp(otpFromText);
			SigninPage.clickSubmit();
			
		/*	String personalkey = SigninPage.getPersonalKey();
			SigninPage.enterPersonalKeyOnAlertBox(personalkey);
			SigninPage.clickContinueOnPersonalKey();*/
			//SigninPage.clickContinueToBeta();
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(3);
		}

	}

	public static void signUpNewUserNonFed(String nonfeduseremail, String password) throws Exception {
		if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL2")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			MyWorkspacePage.setDriver(LaunchBrowserUtil.getDriver());
			MyWorkspacePage.clickSignInButton();
			MyWorkspacePage.clickAcceptOnBanner();
			SigninPage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(4);
			SigninPage.clickCreateAnAccount();
			SigninPage.enterEmailAddress(nonfeduseremail);
			SigninPage.clickSubmit();
			SigninPage.clickResend();
			// capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromGmailNonFed(Constants.GMAIL_NONFED);
			SigninPage.enterSignUpPassword(password);
			SigninPage.clickContinue();
			SigninPage.clickAuthenticationApplication();
			SigninPage.clickContinue();
			String secretCode = SigninPage.getQRSecretCode();
			String otp = LaunchBrowserUtil.getOtp(nonfeduseremail, secretCode);
			logger.info("The captured secret code is --- " + secretCode);
			SigninPage.enterOtp(otp);
			SigninPage.clickSubmitOnOtp();

			SigninPage.selectStateforDriversPermit("Virginia");
			SigninPage.clickContinue();
			SigninPage.clickGetStarted();
			SigninPage.clickUseYourComputer();
			SigninPage.uploadFileWithUrl(Constants.FILE_UPLOAD_PATH_FRONT_PIC);
			SigninPage.clickContinue();
			SigninPage.uploadFileWithUrl(Constants.FILE_UPLOAD_PATH_BACK_PIC);
			SigninPage.clickContinue();
			SigninPage.enterSSN();
			SigninPage.clickContinue();
			SigninPage.clickContinue();
			SigninPage.clickContinue();
			SigninPage.enterPhoneNoForIdentityVerification(ConstantsAccounts.PHONE);
			SigninPage.clickContinue();
			SigninPage.clickTextMessage();
			SigninPage.clickContinue();

			String phoneotp = LaunchBrowserUtil.getPhoneOtpFromEmail(Constants.GMAIL_NONFED);
			SigninPage.enterOtp(phoneotp);
			SigninPage.clickSubmit();

			SigninPage.enterPassword(password);
			SigninPage.clickSubmit();
			String personalkey = SigninPage.getPersonalKey();
			LaunchBrowserUtil.scrollAllTheWayDown();
			SigninPage.clickContinueOnPersonalKey();
			SigninPage.enterPersonalKeyOnAlertBox(personalkey);
			SigninPage.clickPersonalKeyConfirm();
			SigninPage.clickContinueToBeta();
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(3);

		} else if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL1")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			MyWorkspacePage.setDriver(LaunchBrowserUtil.getDriver());
			MyWorkspacePage.clickSignInButton();
			MyWorkspacePage.clickAcceptOnBanner();
			SigninPage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(4);
			SigninPage.clickCreateAnAccount();
			SigninPage.enterEmailAddress(nonfeduseremail);
			SigninPage.clickSubmit();
			SigninPage.clickResend();
			// capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromGmailNonFed(Constants.GMAIL_NONFED);
			SigninPage.enterSignUpPassword(password);
			SigninPage.clickContinue();
			SigninPage.clickAuthenticationApplication();
			SigninPage.clickContinue();
			String secretCode = SigninPage.getQRSecretCode();
			String otp = LaunchBrowserUtil.getOtp(nonfeduseremail, secretCode);
			logger.info("The captured secret code is --- " + secretCode);
			SigninPage.enterOtp(otp);	
			SigninPage.clickSubmitOnOtp();
			SigninPage.clickGetStarted();
			SigninPage.enterMobilePhoneNo(ConstantsAccounts.PHONE);
			SigninPage.clickSubmit();
			String otpFromText=LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUp(Constants.GMAIL_USERNAME);
			SigninPage.enterOtp(otpFromText);
			SigninPage.clickSubmit();
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
			CommonMethods.delay(3);
			
		}

	}

	public static String updatecounter(String property) {
		String counter = applicationproperties.getProperty(property);
		int updatedcounter = Integer.parseInt(counter);
		updatedcounter++;
		applicationproperties.updateProperty(property, String.valueOf(updatedcounter));
		return counter;
	}

}
