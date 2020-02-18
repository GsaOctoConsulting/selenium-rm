package gov.gsa.sam.rms.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * This class is the primary utility class for browser actions and other custom
 * methods that are applicable across all pages. <br>
 * <br>
 * The driver instance in created in this class and the reference is passed to
 * other pages. eg. WorkspacePage.setDriver(LaunchBrowserUtil.getDriver()) <br>
 * <br>
 * Other actions performed by this class includes opering the browser, scrolling
 * up and down, accessing email, etc. See method documentation for detail
 * 
 * 
 * 
 * @author Raiaan
 *
 */
public class LaunchBrowserUtil {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ArrayList<String> tab_handles;
	private static Logger logger = LoggerFactory.getLogger(LaunchBrowserUtil.class);

	private LaunchBrowserUtil() {

	}

	/**
	 * This method instantiates the driver instance and can be passed around from
	 * page to page.
	 * 
	 * @return
	 */
	public static WebDriver openThisBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		options.addArguments("disable-infobars");
		options.addArguments("start-maximized");
		options.addArguments("--no-sandbox");
		options.setExperimentalOption("useAutomationExtension", false); // to
																		// remove
																		// 'Failed
																		// to
																		// load
																		// extension
																		// from:'
																		// dialog
		String chromedriverpath = System.getProperty("user.dir") + "\\chromedriver.exe";
		logger.info("The full path of the chromedriver is - " + chromedriverpath);
		// System.setProperty("webdriver.gecko.driver", chromedriverpath);
		System.setProperty("webdriver.chrome.driver", chromedriverpath);
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		return driver;
	}

	public static void closeThisBrowser() {
		driver.close();
	}

	public static void closeBrowsers() {
		driver.quit();
	}

	/**
	 * This method scroll down to the bottom of the page
	 * 
	 * @throws Exception
	 */
	public static void scrollAllTheWayDown() throws Exception {
		// ((JavascriptExecutor) driver).executeScript("scroll(0,800)", "");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		delay(4);
	}

	public static void closeThisBrowserTab() {
		driver.close();
	}

	public static void closeAllOpenedTabs() {
		driver.quit();
	}

	public static void enterUrl(String url) {
		driver.navigate().to(url);
		driver.navigate().refresh();
		driver.navigate().refresh();
		delay(5);
	}

	public static void waitExplicitMethod(WebDriver driver, By element) throws Exception {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(element));

	}

	public static void scrollToMiddle() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,400)", "");
	}

	public static void scrollUp() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,-800)", "");
	}

	/**
	 * use this method to clear all cookies from the browser
	 * 
	 * @throws InterruptedException
	 */
	public static void clearCookies() throws InterruptedException {
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
	}

	public static void goToOctoTestEmailInbox() {
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		delay(2);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(Constants.GMAIL_USERNAME);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[5]/div[3]/div/a[2]/div/div[1]")).click();
		delay(4);
	}

	public static String captureTitleFromLastEmail(int emailNo) {
		delay(15);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		String emailtitle = lastMail.get(emailNo).getText();
		logger.info(".................................................................................");
		logger.info("The email subject line is-- " + emailtitle);
		logger.info("..................................................................................");
		return emailtitle;
	}

	public static String captureEmailMessage(int emailNo) {
		List<WebElement> lastMail = driver.findElements(By.className("zA"));

		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(emailNo).click();

		String emailbody = driver.findElement(By.className("ii")).getText();
		logger.info("..................................................................................");
		logger.info("The email message is-- " + emailbody);
		logger.info("..................................................................................");
		delay(5);

		// driver.findElement(By.className("ar9")).click();
		// driver.findElement(By.xpath("//div[@aria-label='Delete']")).click();
		// driver.findElement(By.cssSelector("#\\3a 5 > div:nth-child(2) >
		// div.iH.bzn > div > div:nth-child(2) >
		// div.T-I.J-J5-Ji.nX.T-I-ax7.T-I-Js-Gs.mA.T-I-Zf-aw2 > div >
		// div")).click();
		// new
		// Actions(driver).click(driver.findElement(By.cssSelector("div[aria-label='Delete']>div>div"))).perform();
		return emailbody;
	}

	public static String captureToAndFromInEmail() {

		String toandFrom = driver.findElement(By.className("gE")).getText();
		logger.info("The to and from text is---------" + toandFrom);
		delay(2);
		return toandFrom;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		LaunchBrowserUtil.driver = driver;
	}

	public static void scrollByVisibleElement(By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(by);
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public static String getApplicationLink() {
		String systemAccountId = driver.findElement(By.xpath("//a[contains(@id, 'systemAccountId')]"))
				.getAttribute("href");
		String message = "captured system account id is--- " + systemAccountId;
		// logger.info("captured system account id is--- " + systemAccountId);
		logger.info(": {}", message); //
		return systemAccountId;
	}

	public static String getLearningCenterLink() {
		String learningCenterLink = driver.findElement(By.xpath("//a[contains(@id, 'learningCenter')]"))
				.getAttribute("href");
		logger.info("captured learning center link is--- " + learningCenterLink);
		return learningCenterLink;
	}

	public static void switchTabs(int i) {
		delay(1);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		logger.info("The size of the tab array is ---- " + tab_handles.size());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - i));
		delay(2);
	}

	public static void navigateBack() {
		delay(1);
		driver.navigate().back();
		delay(1);
	}

	/**
	 * this method is used internally to generate otp from secretkey
	 * 
	 * @param accountId the email id of the account
	 * @param secretkey the secret key captured at signup
	 * @return
	 */
	public static String getOtp(String accountId, String secretkey) {
		Totp totp = new Totp(secretkey);
		return totp.now();
	}

	public static ArrayList<String> captureSignUpLinkFromGmail() throws InterruptedException {
		delay(15);
		// LaunchBrowserUtil.clearCookies();
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		driver.navigate().refresh();
		delay(5);
		// ((JavascriptExecutor)
		// driver).executeScript("window.open('https://mail.google.com/mail/#inbox')");
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(Constants.GMAIL_USERNAME);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		LaunchBrowserUtil.delay(10);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		// driver.findElement(By.linkText("octotestaccount1@gsa.gov
		// (delegated)")).click();
		/*
		 * WebElement list = driver.findElement(By.cssSelector("img[alt='Profile']"));
		 * 
		 * logger.info("---------------------------------- the now list size is- "+list.
		 * getText()); list.click();
		 */
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[3]/div[2]/a[4]/div/div[3]")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(3);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		delay(6);
		driver.findElement(By.linkText("Confirm email address")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		return tab_handles;
	}

	public static ArrayList<String> captureSignUpLinkFromNonFedEmail(String email) throws InterruptedException {
		delay(8);
		LaunchBrowserUtil.clearCookies();
		((JavascriptExecutor) driver).executeScript("window.open('http://yopmail.com')");
		delay(2);
		driver.navigate().refresh();
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.id("login")).sendKeys(email);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"f\"]/table/tbody/tr[1]/td[3]/input")).click();
		delay(3);
		// List<WebElement> lastMail = driver.findElements(By.className("zA"));
		// logger.info("The size of email list is - " + lastMail.size()); // how
		// lastMail.get(0).click();
		// delay(1);

		delay(6);
		/*
		 * driver.findElement(By.id( "mailmillieu")) .click();
		 */
		// driver.findElement(by) startsWith
		/*
		 * driver.findElement(By.xpath(
		 * "By.xpath(\"//a[contains(@href, 'https://idp.int.identitysandbox.gov/sign_up/email/confirm?')]\"));"
		 * )) .click();
		 */

		// (By.className("float-center")).click();
		/*
		 * WebElement element = driver.findElement(By.xpath(
		 * "//*[@id=\"mailmillieu\"]/div[2]/table/tbody/tr/td/center/table[2]/tbody/tr/td/table[3]/tbody/tr/th/table/tbody/tr/th/table[2]/tbody/tr/td[1]/table/tbody/tr/td/center/a"
		 * )); Actions actions = new Actions(driver);
		 * actions.moveToElement(element).click().perform();
		 */
		driver.manage().window().maximize();
		driver.switchTo().frame("ifmail");
		driver.findElement(By.xpath(
				"//*[@id=\"mailmillieu\"]/div[2]/table/tbody/tr/td/center/table[2]/tbody/tr/td/table[3]/tbody/tr/th/table/tbody/tr/th/table[2]/tbody/tr/td[1]/table/tbody/tr/td/center/a"))
				.click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		return tab_handles;
	}

	public static ArrayList<String> captureSignUpLinkFromNonFedEmailTemporary(String email)
			throws InterruptedException {
		delay(8);
		LaunchBrowserUtil.clearCookies();
		((JavascriptExecutor) driver).executeScript("window.open('http://gmail.com')");
		delay(2);
		driver.navigate().refresh();
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.id("identifierId")).sendKeys(email);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		delay(3);
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("WhiteColor1!");
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/span/span")).click();
		delay(3);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		delay(6);
		driver.findElement(By.linkText("Confirm email address")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		return tab_handles;
	}

	public static void goToFedMailInbox(String username, String password) {
		delay(3);
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		delay(2);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(2);
		// if (nooffetch == 1) {
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(username);
		delay(1);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[3]/div[2]/a[4]/div/div[2]")).click();
		delay(2);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(2);
	}

	public static String getOtpFromEmailForApiKey(String email, String userpass, int nooffetch)
			throws InterruptedException {
		delay(8);
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		LaunchBrowserUtil.delay(3);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(3);
		if (nooffetch == 1) {
			driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		}
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[3]/div[2]/a[4]")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		delay(15);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		int cookielist = driver.manage().getCookies().size();
		logger.info("the number of cookies are - " + cookielist);

		delay(10);
		List<WebElement> lastMail = driver.findElements(By.className("zA")); // to
		// know
		logger.info("The size of email list is - " + lastMail.size()); // how
		// this
		// was
		// captured
		lastMail.get(0).click();
		delay(1);
		Constants.OTP = driver.findElement(By.className("a3s")).getText().substring(26, 33);
		logger.info("The captured OTP is- " + Constants.OTP);
		driver.navigate().back();
		return Constants.OTP;
	}

	public static String getOtpFromEmailForApiKeyNonFed(String email, String userpass, int nooffetch) {
		((JavascriptExecutor) driver).executeScript("window.open('http://yopmail.com')");
		LaunchBrowserUtil.delay(3);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.id("login")).sendKeys(email);
		delay(3);
		driver.findElement(By.xpath("//*[@id=\"f\"]/table/tbody/tr[1]/td[3]/input")).click();
		delay(3);
		driver.manage().window().maximize();
		driver.switchTo().frame("ifmail");
		delay(2);
		String phoneotp = driver.findElement(By.tagName("h3")).getText();
		logger.info("The captured phone otp is-------- " + phoneotp);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		delay(2);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 2));
		/*
		 * int cookielist = driver.manage().getCookies().size();
		 * logger.info("the number of cookies are - " + cookielist); if (nooffetch == 1)
		 * { driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(email);
		 * driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click()
		 * ;
		 * driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))
		 * .sendKeys(userpass); delay(3);
		 * driver.findElement(By.id("passwordNext")).click(); delay(3); }
		 */
		return phoneotp;
	}

	public static String getPhoneOtpFromEmailDuringSignUp(String gmailUsername) {
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		LaunchBrowserUtil.delay(19);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(3);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[3]/div[2]/a[4]/div/div[2]")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(4);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		delay(3);
		String phoneotp = driver.findElement(By.className("a3s")).getText().substring(6, 13).trim();
		logger.info("The captured OTP is- " + phoneotp);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		delay(2);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 3));
		return phoneotp;
	}

	public static void recoverThroughForgotPasswordForFed(String userid, String secretkey, String newpassword)
			throws InterruptedException {
		T1WorkspacePage.clickSignInButton();
		T1WorkspacePage.clickAcceptOnBanner();
		driver.findElement(By.linkText("Forgot your password?")).click();
		delay(1);
		driver.findElement(By.id("password_reset_email_form_email")).sendKeys(userid);
		delay(1);
		driver.findElement(By.xpath("//*[@id=\"new_password_reset_email_form\"]/input[3]")).click();
		delay(1);
		// driver.findElement(By.xpath("//*[@id=\"new_password_reset_email_form\"]/input[5]")).click();

		// ----------get reset password link-------------------
		// LaunchBrowserUtil.clearCookies();
		delay(12);
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		LaunchBrowserUtil.delay(3);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(3);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[5]/div[3]/div/a[2]/div/div[1]")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(9);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		delay(5);
		driver.findElement(By.linkText("Reset your password")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));

		// ---------------set new password------
		delay(2);
		driver.findElement(By.id("reset_password_form_password")).sendKeys(newpassword);
		driver.findElement(By.xpath("//*[@id=\"new_reset_password_form\"]/input[4]")).click();
		delay(5);
	}

	public static void recoverThroughForgotPasswordForNonFed(String userid, String secretkey, String newpassword)
			throws InterruptedException {
		T1WorkspacePage.clickSignInButton();
		T1WorkspacePage.clickAcceptOnBanner();
		driver.findElement(By.linkText("Forgot your password?")).click();
		delay(1);
		driver.findElement(By.id("password_reset_email_form_email")).sendKeys(userid);
		delay(1);
		driver.findElement(By.xpath("//*[@id=\"new_password_reset_email_form\"]/input[3]")).click();
		delay(1);
		// driver.findElement(By.xpath("//*[@id=\"new_password_reset_email_form\"]/input[5]")).click();

		// ----------get reset password link-------------------
		LaunchBrowserUtil.clearCookies();
		delay(5);
		delay(12);
		// ((JavascriptExecutor)
		// driver).executeScript("window.open('https://mail.google.com/')");
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/mail/#inbox')");
		delay(2);
		driver.navigate().refresh();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));

		/*
		 * driver.navigate().refresh(); tab_handles = new
		 * ArrayList<String>(driver.getWindowHandles());
		 * driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		 * driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(
		 * "shah.raiaan@gsa.gov");
		 * driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click()
		 * ;
		 */
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size()); // how
		lastMail.get(0).click();
		delay(1);

		delay(6);
		driver.findElement(By.linkText("Reset your password")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));

		// ---------------set new password------
		delay(2);
		driver.findElement(By.id("reset_password_form_password")).sendKeys(newpassword);
		driver.findElement(By.xpath("//*[@id=\"new_reset_password_form\"]/input[4]")).click();
	}

	public static String getOtpForSystemAccountFromEmail(String email) throws InterruptedException {
		delay(15);
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(Constants.GMAIL_USERNAME);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		LaunchBrowserUtil.delay(10);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[3]/div[2]/a[4]/div/div[2]")).click();
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		delay(3);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		/*
		 * delay(2); tab_handles = new ArrayList<String>(driver.getWindowHandles());
		 * driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		 * driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(email);
		 * driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click()
		 * ; driver.findElement(By.xpath(
		 * "//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		 * delay(2); driver.findElement(By.xpath(
		 * "//*[@id=\"gb\"]/div[2]/div[5]/div[3]/div/a[2]/div/div[1]")).click();
		 * delay(2); tab_handles = new ArrayList<String>(driver.getWindowHandles());
		 * driver.switchTo().window(tab_handles.get(tab_handles.size() - 1)); delay(4);
		 * List<WebElement> lastMail = driver.findElements(By.className("zA")); // to
		 * delay(4); logger.info("The size of email list is - " + lastMail.size());
		 * lastMail.get(0).click();
		 */
		delay(2);
		Constants.OTP = driver.findElement(By.className("a3s")).getText().substring(27, 35);
		logger.info("The captured OTP is- " + Constants.OTP);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 3));
		return Constants.OTP;
	}

	public static String getOtpForSystemAccountFromEmailNonFed(String email, String userpass) {
		((JavascriptExecutor) driver).executeScript("window.open('http://yopmail.com')");
		LaunchBrowserUtil.delay(3);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.id("login")).sendKeys(email);
		delay(3);
		driver.findElement(By.xpath("//*[@id=\"f\"]/table/tbody/tr[1]/td[3]/input")).click();
		delay(3);
		driver.manage().window().maximize();
		driver.switchTo().frame("ifmail");
		delay(4);
		Constants.OTP = driver.findElement(By.tagName("h3")).getText();
		logger.info("The captured OTP is- " + Constants.OTP);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 2));
		return Constants.OTP;
	}

	/**
	 * Custom Method for delay entered in seconds
	 * 
	 * @param seconds the desired amount of delay in seconds
	 */
	public static void delay(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException ex) {
		}
	}

	public static void clickSideNavToGoToPage(String pageName, WebDriver driver) {
		List<WebElement> pageList = driver.findElements(AccountDetailsPageLocator.SIDE_NAV);
		logger.info("The size of the side-nav list is----" + pageList.size());
		for (int i = 0; i < pageList.size(); i++) {
			if (pageName.equals(pageList.get(i).getText())) {

				pageList.get(i).click();
			}
		}

	}

	public static String getPhoneOtpFromEmailDuringSignUpNonFed(String gmailNonfed) {
		((JavascriptExecutor) driver).executeScript("window.open('http://yopmail.com')");
		LaunchBrowserUtil.delay(3);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.id("login")).sendKeys(gmailNonfed);
		delay(7);
		driver.findElement(By.xpath("//*[@id=\"f\"]/table/tbody/tr[1]/td[4]/input")).click();
		delay(3);
		driver.manage().window().maximize();
		driver.switchTo().frame("ifmail");
		delay(2);
		String phoneotp = driver
				.findElement(By.xpath("//*[@id=\"mailmillieu\"]/div[2]/table/tbody/tr/td/table[2]/tbody/tr[1]/td"))
				.getText().substring(6, 13);
		logger.info("The captured phone otp is-------- " + phoneotp);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		delay(2);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 2));
		return phoneotp;
	}

	public static String getPhoneOtpFromEmailDuringSignUpNonFedTemporary(String gmailNonfed) {
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		delay(4);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		LaunchBrowserUtil.delay(19);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		delay(3);
		String phoneotp = driver.findElement(By.className("a3s")).getText().substring(6, 13);
		logger.info("The captured OTP is- " + phoneotp);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		delay(2);
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 2));
		return phoneotp.trim();
	}

	public static void goToNonFedFedMailInbox(String nonfedemail) {
		((JavascriptExecutor) driver).executeScript("window.open('http://yopmail.com')");
		LaunchBrowserUtil.delay(3);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.id("login")).sendKeys(nonfedemail);
		delay(3);
		driver.findElement(By.xpath("//*[@id=\"f\"]/table/tbody/tr[1]/td[3]/input")).click();
		delay(4);
		driver.findElement(By.xpath("//*[@id=\"butmail\"]/tbody/tr/td[10]/a")).click();
		delay(5);
		// List<WebElement> emailist =driver.findElements(By.className("m2"));
		// logger.info("The emaillist size is-- "+emailist.size());
		driver.manage().window().maximize();
		driver.switchTo().frame("ifmail");
		delay(2);

	}

	public static String captureEmailContentNonfed() {
		delay(3);
		String emailcontent = driver.findElement(By.id("mailmillieu")).getText();
		logger.info("-------------------------below is the email content--------------------------------------");
		logger.info(emailcontent);
		logger.info("---------------------------------------------------------------------------------------");
		return emailcontent;
	}

	public static String getOtpForSystemAccountFromEmailModified(String email) {
		LaunchBrowserUtil.delay(4);
		((JavascriptExecutor) LaunchBrowserUtil.driver).executeScript("window.open('https://mail.google.com/')");
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.tab_handles = new ArrayList<String>(LaunchBrowserUtil.driver.getWindowHandles());
		LaunchBrowserUtil.driver.switchTo()
				.window(LaunchBrowserUtil.tab_handles.get(LaunchBrowserUtil.tab_handles.size() - 1));
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(email);
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.tab_handles = new ArrayList<String>(LaunchBrowserUtil.driver.getWindowHandles());
		LaunchBrowserUtil.driver.switchTo()
				.window(LaunchBrowserUtil.tab_handles.get(LaunchBrowserUtil.tab_handles.size() - 1));
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.driver.navigate().refresh();
		LaunchBrowserUtil.delay(3);
		List<WebElement> lastMail = LaunchBrowserUtil.driver.findElements(By.className("zA"));
		// LaunchBrowserUtil.delay(4);
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		LaunchBrowserUtil.delay(2);
		Constants.OTP = LaunchBrowserUtil.driver
				.findElement(By.xpath("//div[contains(text(),'Your one time password is:')]//h3")).getText();
		logger.info("The captured OTP is- " + Constants.OTP);
		LaunchBrowserUtil.driver.switchTo()
				.window(LaunchBrowserUtil.tab_handles.get(LaunchBrowserUtil.tab_handles.size() - 2));
		return Constants.OTP;
	}

	public static void goToGSAEmailInboxModified(String fedUser) {
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/')");
		delay(2);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(fedUser);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		LaunchBrowserUtil.delay(4);
	}

	public static String getOtpForSystemAccountFromEmailNonFedModified(String email, String userpass) {

		LaunchBrowserUtil.delay(4);
		((JavascriptExecutor) LaunchBrowserUtil.driver).executeScript("window.open('https://mail.google.com/')");
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.tab_handles = new ArrayList<String>(LaunchBrowserUtil.driver.getWindowHandles());
		LaunchBrowserUtil.driver.switchTo()
				.window(LaunchBrowserUtil.tab_handles.get(LaunchBrowserUtil.tab_handles.size() - 1));
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(email);
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))
				.sendKeys(userpass);
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.findElement(By.id("passwordNext")).click();
		LaunchBrowserUtil.delay(6);
		List<WebElement> lastMail = LaunchBrowserUtil.driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		lastMail.get(0).click();
		LaunchBrowserUtil.delay(2);
		Constants.OTP = LaunchBrowserUtil.driver
				.findElement(By.xpath("//div[contains(text(),'Your one time password is:')]//h3")).getText();
		logger.info("The captured OTP is- " + Constants.OTP);
		LaunchBrowserUtil.driver.switchTo()
				.window(LaunchBrowserUtil.tab_handles.get(LaunchBrowserUtil.tab_handles.size() - 2));
		return Constants.OTP;
	}

	/**
	 * This method adds fed email account
	 */
	public static void addFedEmailAccount(String fedUser) {
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[contains(text(),'Add another account')]")).click();
		switchTabs(1);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(fedUser);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		delay(2);
	}

	/**
	 * This method adds non fed email account
	 */
	public static void addNonFedEmailAccount(String nonfedUser, String userpass) {
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div[2]/div/a/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[contains(text(),'Add another account')]")).click();
		switchTabs(1);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(nonfedUser);
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click();
		delay(2);
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(userpass);
		delay(2);
		driver.findElement(By.id("passwordNext")).click();
		delay(2);
	}

	public static void scrollToEnd() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,1600)", "");
	}

	public static void takeScreenshot() throws IOException {
		Screenshot screenshot = new AShot().takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "png", new File(".\\screenshot\\fullimage.jpg"));
	}
}