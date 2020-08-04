package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the page when viewing the details of any system account
 *
 */
public class SystemAccountRequestDetailsPage {

	private static WebDriver driver;

	private static Logger logger = LoggerFactory.getLogger(SystemAccountRequestDetailsPage.class);

	private SystemAccountRequestDetailsPage() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		SystemAccountRequestDetailsPage.driver = driver;
	}

	public static void writeComment(String string) {
		driver.findElement(By.id("request-comment")).sendKeys(string);
		LaunchBrowserUtil.delay(2);

	}

	public static void clickRejectButton() {
		driver.findElement(By.id("button-reject")).click();
		LaunchBrowserUtil.delay(5);
	}

	public static void clickCloseButton() {
		driver.findElement(By.id("button-close")).click();
		LaunchBrowserUtil.delay(5);

	}

	/**
	 * check for matching user id and comments
	 * 
	 * @param useremail
	 * @param comments
	 * @return true if found , false otherwise
	 */
	public static boolean commentFound(String useremail, String comments) {
		boolean commentFound = false;
		List<WebElement> commentlist = driver.findElements(By.className("sam-comment"));
		logger.info("The size of the comment list is------- " + commentlist.size());

		for (int i = 0; i < commentlist.size(); i++) {
			String completetext = commentlist.get(i).getText();

			logger.info("The matching full comment is---- " + completetext);
			if (completetext.contains(useremail) && completetext.contains(comments)) {
				commentFound = true;
			}
		}

		return commentFound;
	}

	public static void clickApproveButton() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.id("button-approve")).click();
		LaunchBrowserUtil.delay(30);

	}

	public static void enterNewPassword(String userpass) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'password')]")).sendKeys(userpass);
		
		LaunchBrowserUtil.delay(1);

	}

	public static void enterConfirmPassword(String userpass) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'new-password')]")).sendKeys(userpass);
		;
		LaunchBrowserUtil.delay(1);

	}

	public static void clickPasswordSaveButton() {
		driver.findElement(By.id("button-password")).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void clickShowApiKeyCheckbox() {
		driver.findElement(By.id("usa-api-toggle")).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void enterShowApiKeyPassword(String password) {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/review/div[1]/div[6]/div/sam-fieldset-wrapper/div/fieldset/div[2]/div[2]/input"))
				.sendKeys(password);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterApiKeySubmitButton() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/review/div[1]/div[6]/div/sam-fieldset-wrapper/div/fieldset/div[2]/div[2]/div/sam-button/button\r\n"
						+ ""))
				.click();
		LaunchBrowserUtil.delay(1);
	}

	public static void clickDeactivateButton() {
		driver.findElement(By.id("button-deactivate")).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickYesDeactivateMyAccount() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/sam-modal[2]/div[2]/div/div[2]/div/sam-button[2]/button"))
				.click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickYesContinueWithDeactivation() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/sam-modal[2]/div/div/div[2]/div/sam-button[2]/button"))
				.click();
		LaunchBrowserUtil.delay(5);
	}

	/**
	 * this method checks for status and message in the history part of the system
	 * account
	 * 
	 * @param status    eg. approved
	 * @param message   the message to look for
	 * @param historyno the specific history stamp to match the status and message
	 *                  with
	 * @return true if found, false otherwise
	 */
	public static boolean accountHistoryFound(String status, String message, int historyno) {
		boolean accounthistoryfound = false;
		List<WebElement> historylist = driver.findElements(By.xpath("//li[contains(@class, 'history-item-')]"));
		logger.info("****************************************************************");
		logger.info("The size of the history list is---" + historylist.size());
		String firstitem = historylist.get(historyno).getText().toLowerCase();
		logger.info("******************text from the item*************");
		logger.info(firstitem);
		logger.info("The status entered was---- " + status);
		logger.info("The message entered was---- " + message);
		boolean statusFound = firstitem.contains(status.toLowerCase());
		logger.info("****StatusFound is ---" + statusFound);
		boolean messageFound = firstitem.contains(message.toLowerCase());
		logger.info("****messageFound is ---" + messageFound);

		if (statusFound && messageFound) {
			accounthistoryfound = true;
		}
		return accounthistoryfound;
	}

	public static void clickCloseButtonWithoutJavascriptExecutor() {
		driver.findElement(By.id("comment-component-input"));
		driver.findElement(By.id("button-close")).click();
		LaunchBrowserUtil.delay(4);
	}

	public static void enterNewPasswordForSystemAccount(String systemaccountPassword) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'password-')]")).sendKeys(systemaccountPassword);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterConfirmPasswordForSystemAccount(String systemaccountPassword) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'new-password-')]")).sendKeys(systemaccountPassword);
		LaunchBrowserUtil.delay(1);
	}

	public static void clickSaveButtonOnSystemAccountPassword() {
		driver.findElement(By.id("button-password")).click();
		LaunchBrowserUtil.delay(3);
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void clickResetPasswordRadioButton() {
		driver.findElement(By.id("radio-reset")).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void enterCurrentPasswordForSystemAccount(String passwordcurrent) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'current-password-')]")).sendKeys(passwordcurrent);
		LaunchBrowserUtil.delay(1);
	}

	public static void clickRequestAPIkeyButton(String accountpassword) {
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/review/div/div[6]/div/sam-fieldset-wrapper/div/fieldset/div[2]/button"))
				.click();
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.id("password-input")).sendKeys(accountpassword);
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/review/sam-modal-basic/div/div/div[2]/div/button[2]"))
				.click();
		LaunchBrowserUtil.delay(5);
	}

	public static void clickForgotPasswordRadioButton() {
		driver.findElement(By.id("radio-forgot")).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickRequestionAPIButtonForOtp() {
		driver.findElement(By.xpath(
				"//*[@id=\"password\"]/sam-fieldset-wrapper/div/fieldset/div/sam-label-wrapper/div/sam-button/button"))
				.click();
		LaunchBrowserUtil.delay(1);
	}

	public static void enterOtpOnForgotPassword(String systemaccountotp) {
		driver.findElement(By.id("sa-otp")).sendKeys(systemaccountotp);
		LaunchBrowserUtil.delay(1);
	}
}
