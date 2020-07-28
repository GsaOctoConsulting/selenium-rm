package gov.gsa.sam.rms.utilities;

import java.util.ArrayList;
import java.util.List;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.gsa.sam.rms.pages.T1WorkspacePage;

/**
 * Description: simply put this class provides method/s to take a user through
 * the sign in process and land into the workspace of SAM environment
 * 
 * @author Raiaan
 */
public class SignInUtility {

	private static Logger logger = LoggerFactory.getLogger(SignInUtility.class);
	public static ArrayList<String> tab_handles = LaunchBrowserUtil.tab_handles;

	/**
	 * This methods allows the caller to land on Workspace page <br>
	 * <br>
	 * Assumption and precondition - user must use the username, password and
	 * secretkey for an account that is already setup and active
	 * 
	 * @param username  user's email id e.g travis@gsa.gov
	 * @param password  user's password
	 * @param secretkey the secretkey for this account saved during signup
	 * @param usertype  specify which type of useraccount this is eg. fed / nonfed
	 *                  user
	 * @throws InterruptedException
	 */
	public static void signIntoWorkspace(String username, String password, String secretkey, String usertype)
			throws InterruptedException {
		LaunchBrowserUtil.openThisBrowser();
		LaunchBrowserUtil.clearCookies();
		LaunchBrowserUtil.enterUrl(Constants.SAM_HOME_PAGE);
		// ---------------------------
		LaunchBrowserUtil.driver.findElement(By.id("signin-button")).click();
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.id("login-accept")).click();
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.id("login-proceed")).click();
		Thread.sleep(2000);

		LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(username);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.id("user_password")).sendKeys(password);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.className("usa-button--primary")).click();
		Thread.sleep(2000);
		Totp totp = new Totp(secretkey);
		String otp = totp.now();
		LaunchBrowserUtil.driver.findElement(By.id("code")).sendKeys(otp);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
		Thread.sleep(3000);
		// ---------------------------
		T1WorkspacePage.setDriver(LaunchBrowserUtil.getDriver());
		logger.info("------------Sign into workspace completed------------------------------");
		// LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE+"/t1-workspace");
		// LaunchBrowserUtil.delay(3);
	}

	public static String signIntoLogindotgov(String username, String userpassword, String secretkey, String action)
			throws InterruptedException {
		String message = "";

		LaunchBrowserUtil.openThisBrowser();
		LaunchBrowserUtil.clearCookies();
		LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
		LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(username);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.id("user_password")).sendKeys(userpassword);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.className("usa-button--primary")).click();
		Thread.sleep(2000);
		Totp totp = new Totp(secretkey);
		String otp = totp.now();
		LaunchBrowserUtil.driver.findElement(By.id("code")).sendKeys(otp);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
		Thread.sleep(3000);

		if (action.equals(Constants.NOACTION)) {
			// do nothing
		} else if (action.equals(Constants.DELETE)) {
			// write code to delet account here
			List<WebElement> elements = LaunchBrowserUtil.driver.findElements(By.xpath(
					"//a[starts-with(@href, 'https://idp.int.identitysandbox.gov/manage/email/confirm_delete/')]"));
			logger.info("The size of the accounts email found-- " + elements.size());
			elements.get(1).click();//assuming the second one is the recently added one that is to be deleted
			LaunchBrowserUtil.delay(1);
			LaunchBrowserUtil.driver.findElement(By.className("btn-danger")).click();
			LaunchBrowserUtil.delay(1);

		} else if (action.equals(Constants.ADD_EMAIL)) {
			LaunchBrowserUtil.driver
					.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[3]/div[2]/div[1]/div[2]/div/a")).click();
			String counter = SignUpUtility.updatecounter("login.fed.accountno");
			String newneverregisteredemail = "octotestaccount1+" + counter + "@gsa.gov";
			logger.info("The new added email is-- " + newneverregisteredemail);
			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(newneverregisteredemail);
			Thread.sleep(2000);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.id("user_password")).sendKeys(userpassword);
//			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
//			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(newneverregisteredemail);
//			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
//			Thread.sleep(2000);

			LaunchBrowserUtil.captureSignUpLinkFromGmail();
			return newneverregisteredemail;

		} else {
			logger.debug(
					"No action parameters assigned when calling this method. Please double check if that was intended");

		}
		return message;
	}

	public static String signIntoLogindotgovNonfed(String username, String userpassword, String secretkey,
			String action) throws InterruptedException {
		String message = "";

		LaunchBrowserUtil.openThisBrowser();
		LaunchBrowserUtil.clearCookies();
		LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
		LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(username);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.id("user_password")).sendKeys(userpassword);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.className("usa-button--primary")).click();
		Thread.sleep(2000);
		Totp totp = new Totp(secretkey);
		String otp = totp.now();
		LaunchBrowserUtil.driver.findElement(By.id("code")).sendKeys(otp);
		Thread.sleep(2000);
		LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
		Thread.sleep(3000);
		if (action.equals(Constants.NOACTION)) {
			// do nothing
		} else if (action.equals(Constants.DELETE)) {
			// write code to delet account here
			List<WebElement> elements = LaunchBrowserUtil.driver.findElements(By.xpath(
					"//a[starts-with(@href, 'https://idp.int.identitysandbox.gov/manage/email/confirm_delete/')]"));
			logger.info("The size of the accounts email found-- " + elements.size());
			elements.get(1).click();
			LaunchBrowserUtil.delay(1);
			LaunchBrowserUtil.driver.findElement(By.className("btn-danger")).click();
			LaunchBrowserUtil.delay(1);

		} else if (action.equals(Constants.ADD_EMAIL)) {
			LaunchBrowserUtil.driver
					.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[3]/div[2]/div[1]/div[2]/div/a")).click();
			String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
			String newneverregisteredemail = "nonfedgsaemail+" + counter + "@yopmail.com";
			logger.info("The new added email is-- " + newneverregisteredemail);
			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(newneverregisteredemail);
			Thread.sleep(2000);
			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.id("user_password")).sendKeys(userpassword);
//			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
//			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.id("user_email")).sendKeys(newneverregisteredemail);
//			Thread.sleep(2000);
//			LaunchBrowserUtil.driver.findElement(By.className("btn-primary")).click();
//			Thread.sleep(2000);

			LaunchBrowserUtil.captureSignUpLinkFromNonFedEmail(Constants.EMAIL_NONFED);
			return newneverregisteredemail;

		} else {
			logger.debug(
					"Unknown action parameters assigned when calling this method. Please double check if that was intended");

		}
		return message;

	}

}