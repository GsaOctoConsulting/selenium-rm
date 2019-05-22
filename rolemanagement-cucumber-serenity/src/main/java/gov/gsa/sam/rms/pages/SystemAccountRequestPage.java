package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.CommonMethods;

public class SystemAccountRequestPage {
	private static WebDriver driver;

	private static Logger logger = LoggerFactory.getLogger(SystemAccountRequestPage.class);

	private SystemAccountRequestPage() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		SystemAccountRequestPage.driver = driver;
	}

	public static void writeComment(String string) {
		driver.findElement(By.id("request-comment")).sendKeys(string);
		CommonMethods.delay(1);

	}

	public static void clickRejectButton() {
		driver.findElement(By.id("button-reject")).click();
		CommonMethods.delay(2);
	}

	public static void clickCloseButton() {
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//WebElement Element = driver.findElement(By.id("button-close"));
		// This will scroll the page till the element is found
		//js.executeScript("arguments[0].scrollIntoView();", Element);

		driver.findElement(By.id("button-close")).click();
		CommonMethods.delay(2);

	}

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
		driver.findElement(By.id("button-approve")).click();
		CommonMethods.delay(6);

	}

	public static void enterNewPassword(String userpass) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'password')]")).sendKeys(userpass);
		;
		CommonMethods.delay(1);

	}

	public static void enterConfirmPassword(String userpass) {
		driver.findElement(By.xpath("//input[starts-with(@id, 'new-password')]")).sendKeys(userpass);
		;
		CommonMethods.delay(1);

	}

	public static void clickPasswordSaveButton() {
		driver.findElement(By.id("button-password")).click();
		CommonMethods.delay(1);
	}

	public static void clickShowApiKeyCheckbox() {
		driver.findElement(By.id("usa-api-toggle")).click();
		CommonMethods.delay(1);
	}

	public static void enterShowApiKeyPassword(String password) {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/review/div[1]/div[6]/div/sam-fieldset-wrapper/div/fieldset/div[2]/div[2]/input"))
				.sendKeys(password);
		CommonMethods.delay(1);
	}

	public static void enterApiKeySubmitButton() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/review/div[1]/div[6]/div/sam-fieldset-wrapper/div/fieldset/div[2]/div[2]/div/sam-button/button\r\n"
						+ ""))
				.click();
		CommonMethods.delay(1);
	}

	public static void clickDeactivateButton() {
		driver.findElement(By.id("button-deactivate")).click();
		CommonMethods.delay(1);
	}

	public static void clickYesDeactivateMyAccount() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/sam-modal[1]/div/div/div[2]/div/sam-button[2]/button"))
				.click();
		CommonMethods.delay(1);
	}

	public static void clickYesContinueWithDeactivation() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/sam-modal[2]/div/div/div[2]/div/sam-button[2]/button"))
				.click();
		CommonMethods.delay(3);
	}

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
		CommonMethods.delay(4);	
	}
}
