package gov.gsa.sam.rms.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class LaunchBrowserUtil {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ArrayList<String> tab_handles;
	private static Logger logger = LoggerFactory.getLogger(LaunchBrowserUtil.class);

	private LaunchBrowserUtil() {

	}

	public static WebDriver openThisBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		options.addArguments("disable-infobars");
		options.addArguments("start-maximized");
		options.setExperimentalOption("useAutomationExtension", false); // to
																		// remove
																		// 'Failed
																		// to
																		// load
																		// extension
																		// from:'
																		// dialog
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * 
	 */
	public static void closeThisBrowser() {
		driver.close();
	}

	public static void closeBrowsers() {
		driver.quit();
	}

	public static void scrolldown() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,30000000)", "");
		CommonMethods.delay(1);
	}

	public static void scrolldownToRmWidget() throws Exception {
		// ((JavascriptExecutor) driver).executeScript("scroll(0,800)", "");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		CommonMethods.delay(4);
	}

	public static void closeThisBrowserTab() {
		driver.close();
	}

	public static void closeAllOpenedTabs() {
		driver.quit();
	}

	public static void dismissFeedbackIcon() {
		driver.findElement(Locators.dismissFeedback).click();
	}

	public static void clickSignOutMainMenuButton() {
		driver.findElement(Locators.signOutSamMenuButton).click();
	}

	public static void clickSignOutToSamButton() {
		driver.findElement(Locators.signOutSamButton).click();
	}

	public static void enterUrl(String url) {
		driver.navigate().to(url);
		driver.navigate().refresh();
		driver.navigate().refresh();
		CommonMethods.delay(5);
	}

	public static void waitMethod() throws InterruptedException {
		Thread.sleep(1800);
	}

	public static void longWaitMethod() throws InterruptedException {
		Thread.sleep(2000);
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

	public static void re_enterTheUrl() {
		driver.findElement(Locators.tab).sendKeys(Keys.CONTROL, Keys.TAB);
	}

	// sign in
	public static void clickSignIn() throws Exception {
		driver.findElement(Locators.SignIn).click();
		Thread.sleep(1200);
	}

	public static void loginToSam_Gov(String myemail, String mypass) throws Exception {
		// Set Email in text box.
		driver.findElement(Locators.email).sendKeys(myemail);
		Thread.sleep(1400);
		// Set Password in text box.
		driver.findElement(Locators.Password1).sendKeys(mypass);
		Thread.sleep(1400);
		// click on dismiss feedback alert message
		// driver.findElement(dismissFeedback).click();
		// Thread.sleep(1400);
		// Click Login button
		driver.findElement(Locators.signInButton).click();
		Thread.sleep(1400);
	}

	// Click on Next button
	public static void clickForgotButton() throws Exception {
		driver.findElement(Locators.Forgot).click();
		Thread.sleep(1400);
	}

	// Click on Next button
	public static void clickSignIn2Button() throws Exception {
		driver.findElement(Locators.SignIn2).click();
		Thread.sleep(1400);
	}

	// open another browser
	public static void loginToPersonalMailBox(String myemail, String mypass, String otp) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open('https://outlook.live.com/owa/', '_blank');");
		Set<String> tab_handles = driver.getWindowHandles();
		int number_of_tabs = tab_handles.size();
		int new_tab_index = number_of_tabs - 1;
		driver.switchTo().window(tab_handles.toArray()[new_tab_index].toString());
		driver.findElement(Locators.SignInLook).click();
		// Set Email in text box.
		WebElement elt = driver.findElement(Locators.userMail);
		Actions builder = new Actions(driver);
		Actions seriesOfActions = builder.moveToElement(elt).click().sendKeys(elt, myemail);
		seriesOfActions.perform();
		Thread.sleep(1400);
		// click on next button in gmail
		driver.findElement(Locators.nextButton).click();
		// Set Password in text box.
		WebElement eltpass = driver.findElement(Locators.passMail);
		Actions builders = new Actions(driver);
		Actions serieOfActions = builders.moveToElement(eltpass).click().sendKeys(eltpass, mypass);
		serieOfActions.perform();
		Thread.sleep(2000);
		driver.findElement(Locators.nextButton).click();
		Thread.sleep(2000);
		driver.findElements(By.xpath("//*[@class='_lvv_B noMargin ms-font-m o365button']")).get(0).click();
		Constants.pass = driver.findElement(By.xpath("//*[@id='Item.MessageUniqueBody']")).getText().substring(30, 38);
		System.out.println(Constants.pass);
		System.out.println("The pascode is printed above");
		driver.switchTo().window(tab_handles.toArray()[0].toString());
		Thread.sleep(2000);
		driver.findElement(Locators.pinBox).sendKeys(Constants.pass);
		// Thread.sleep(3000);
		driver.findElement(Locators.samSubmitButton2).click();
	}

	// log into GSA and capture otp from email & come back to homepage
	public static void captureOTPfromGsaEmail(String myemail) throws Exception {
		CommonMethods.delay(8);
		((JavascriptExecutor) driver).executeScript("window.open('https://mail.google.com/mail/#inbox')");
		CommonMethods.delay(2);
		tab_handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(myemail);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();

		CommonMethods.delay(10);
		// List<WebElement> lastMail =
		// driver.findElements(By.xpath("//*[@id=\":2k\"]/div/div/div"));// want
		List<WebElement> lastMail = driver.findElements(By.className("zA")); // to
		// know
		logger.info("The size of email list is - " + lastMail.size()); // how
		// this
		// was
		// captured
		lastMail.get(0).click();
		CommonMethods.delay(1);
		Constants.OTP = driver.findElement(Locators.iaeOtp).getText().substring(30, 38);

		logger.info("The captured OTP is- " + Constants.OTP);

		driver.switchTo().window(tab_handles.get(tab_handles.size() - 2));
	}

	public static String captureTitleFromLastEmail() {
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 1));
		CommonMethods.delay(20);
		driver.navigate().back();
		CommonMethods.delay(5);
		List<WebElement> lastMail = driver.findElements(By.className("zA"));
		logger.info("The size of email list is - " + lastMail.size());
		String emailtext = lastMail.get(0).getText();
		driver.switchTo().window(tab_handles.get(tab_handles.size() - 2));
		return emailtext;
	}

	public static void clickMainMenu() {
		driver.findElement(Locators.menu).click();
	}

	public static void clickWorkspace() {
		List<WebElement> wicon = driver.findElements(Locators.workspaceIcon);
		wicon.get(7).click();
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
        //This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);		
	}

	/*
	 * public static void takeScreenshot() throws Exception { WebDriver
	 * augmentedDriver = new Augmenter().augment(getDriver()); File screenshot =
	 * ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
	 * FileUtils.copyFile(screenshot, new File("screenshots/" +
	 * System.currentTimeMillis() + ".png")); }
	 */
}