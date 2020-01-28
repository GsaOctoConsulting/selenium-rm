package gov.gsa.sam.rms.utilities;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.RequestRoleOptionalPage;

/**
 * Description: this class provides method/s to take a user through the signup
 * process and take them to the CommonProfile page of SAM webpage environment
 * 
 * @author Raiaan
 *
 */
public class SignUpUtility {
	private static Logger logger = LoggerFactory.getLogger(SignUpUtility.class);
	private static PropertiesFileUtility applicationproperties = new PropertiesFileUtility("utility.properties");

	/**
	 * This methods allows the caller to land on CommonWorkspae page after signup
	 * process for fed user only <br>
	 * <br>
	 * Assumption and precondition - user must use fed username.
	 * 
	 * @param useremail fed useremail that will be used to signup. e.g
	 *                  travis@gsa.gov
	 * @param password  the desired password for this account
	 * @return
	 * @throws Exception
	 */
	public static String signUpNewUser(String useremail, String password) throws Exception {
		String secretCode = new String("");
		if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL2")) {
			// to be implemented in future if IAL2 is enforced
		} else if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL1")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			LaunchBrowserUtil.driver.findElement(By.id("signin-button")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("login-accept")).click();
			LaunchBrowserUtil.delay(6);
			LaunchBrowserUtil.driver.findElement(By.linkText("Create an account")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(useremail);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			// -----capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromGmail();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("password_form_password")).sendKeys(password);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver
					.findElement(
							By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[2]/div/span[1]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.getDriver().findElement(By.id("name")).sendKeys("xxde");
			secretCode = LaunchBrowserUtil.driver.findElement(By.id("qr-code")).getText();
			LaunchBrowserUtil.delay(2);
			String otp = LaunchBrowserUtil.getOtp(useremail, secretCode);
			LaunchBrowserUtil.delay(2);
			logger.info("The captured secret code is --- " + secretCode);
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otp);
			LaunchBrowserUtil.delay(4);
			LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@data-disable-with, 'Submit')]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(5);
			LaunchBrowserUtil.driver
					.findElement(
							By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[1]/div/span[1]"))
					.click();
			LaunchBrowserUtil.delay(1);
			LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[2]/input"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("new_phone_form_phone")).sendKeys(ConstantsAccounts.PHONE);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			String otpFromText = LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUp(Constants.GMAIL_USERNAME);
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).clear();
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otpFromText.trim());
			LaunchBrowserUtil.delay(5);
			LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@data-disable-with, 'Submit')]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());// passing reference to common profile page
			RequestRoleOptionalPage.setDriver(LaunchBrowserUtil.getDriver());// passing reference to requestroleoption
																				// page
		}
		return secretCode;
	}

	/**
	 * This methods allows the caller to land on CommonWorkspae page after signup
	 * process for nonfed user only <br>
	 * <br>
	 * Assumption and precondition - user must use nonfed username.
	 * 
	 * @param useremail nonfed useremail that will be used to signup. e.g
	 *                  john@gmail.com
	 * @param password  the desired password for this account
	 * @return
	 * @throws Exception
	 */
	public static String signUpNewUserNonFed(String nonfeduseremail, String password) throws Exception {
		String secretCode = new String("");
		if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL2")) {
			// to be implemented in future if IAL2 is enforced
		} else if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL1")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			LaunchBrowserUtil.driver.findElement(By.id("signin-button")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("login-accept")).click();
			LaunchBrowserUtil.delay(6);
			LaunchBrowserUtil.driver.findElement(By.linkText("Create an account")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(nonfeduseremail);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@data-disable-with, 'Submit')]"))
					.click();
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			// -----capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromNonFedEmail(Constants.EMAIL_NONFED);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("password_form_password")).sendKeys(password);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(5);
			LaunchBrowserUtil.driver
					.findElement(
							By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[2]/div/span[1]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.id("name")).sendKeys("xxde");
			secretCode = LaunchBrowserUtil.driver.findElement(By.id("qr-code")).getText();
			String otp = LaunchBrowserUtil.getOtp(nonfeduseremail, secretCode);
			logger.info("The captured secret code is --- " + secretCode);
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otp);
			LaunchBrowserUtil.delay(4);
			LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@data-disable-with, 'Submit')]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(5);
			LaunchBrowserUtil.driver
					.findElement(
							By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[1]/div/span[1]"))
					.click();
			LaunchBrowserUtil.delay(1);
			LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[2]/input"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("new_phone_form_phone")).sendKeys(ConstantsAccounts.PHONE);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(8);
			// String otpFromText =
			// LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUp(Constants.GMAIL_USERNAME);
			String otpFromText = LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUpNonFed(Constants.EMAIL_NONFED);
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otpFromText);
			LaunchBrowserUtil.delay(3);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
			// RequestRoleOptionalPage.setDriver(LaunchBrowserUtil.getDriver());
			LaunchBrowserUtil.delay(3);
		}
		return secretCode;
	}
	public static String signUpNewUserNonFedTemporary(String nonfeduseremail, String password) throws Exception {
		String secretCode = new String("");
		if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL2")) {
			// to be implemented in future if IAL2 is enforced
		} else if (Constants.SIGNUP_SECURITYLEVEL.equals("IAL1")) {
			LaunchBrowserUtil.openThisBrowser();
			LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
			LaunchBrowserUtil.driver.findElement(By.id("signin-button")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("login-accept")).click();
			LaunchBrowserUtil.delay(6);
			LaunchBrowserUtil.driver.findElement(By.linkText("Create an account")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(nonfeduseremail);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@data-disable-with, 'Submit')]"))
					.click();
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			// -----capture link----------------------
			LaunchBrowserUtil.captureSignUpLinkFromNonFedEmailTemporary(Constants.EMAIL_NONFED);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("password_form_password")).sendKeys(password);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(5);
			LaunchBrowserUtil.driver
					.findElement(
							By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[2]/div/span[1]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.id("name")).sendKeys("xxde");
			secretCode = LaunchBrowserUtil.driver.findElement(By.id("qr-code")).getText();
			String otp = LaunchBrowserUtil.getOtp(nonfeduseremail, secretCode);
			logger.info("The captured secret code is --- " + secretCode);
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otp);
			LaunchBrowserUtil.delay(4);
			LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@data-disable-with, 'Submit')]"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(5);
			LaunchBrowserUtil.driver
					.findElement(
							By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[1]/div/span[1]"))
					.click();
			LaunchBrowserUtil.delay(1);
			LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[2]/input"))
					.click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.driver.findElement(By.id("new_phone_form_phone")).sendKeys(ConstantsAccounts.PHONE);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(8);
			// String otpFromText =
			// LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUp(Constants.GMAIL_USERNAME);
			String otpFromText = LaunchBrowserUtil.getPhoneOtpFromEmailDuringSignUpNonFed(Constants.EMAIL_NONFED);
			LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otpFromText);
			LaunchBrowserUtil.delay(3);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
			LaunchBrowserUtil.delay(2);
			CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
			// RequestRoleOptionalPage.setDriver(LaunchBrowserUtil.getDriver());
			LaunchBrowserUtil.delay(3);
		}
		return secretCode;
	}
	/**
	 * This methods updates the count for fed and nonfed signup in order to prevent
	 * duplicates <br>
	 * eg. userfed1@gsa.gov, userfed2@gsa.gov
	 *
	 * @param property the property name from application.properties
	 * @return the updated value of the property
	 */
	public static String updatecounter(String property) {
		String counter = applicationproperties.getProperty(property);
		int updatedcounter = Integer.parseInt(counter);
		updatedcounter++;
		applicationproperties.updateProperty(property, String.valueOf(updatedcounter));
		return counter;
	}

}
