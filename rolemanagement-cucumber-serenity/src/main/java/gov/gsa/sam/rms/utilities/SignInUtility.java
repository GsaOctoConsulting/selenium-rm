package gov.gsa.sam.rms.utilities;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
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
		LaunchBrowserUtil.enterUrl(Constants.LOGINGOV_HOME_PAGE);
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

}