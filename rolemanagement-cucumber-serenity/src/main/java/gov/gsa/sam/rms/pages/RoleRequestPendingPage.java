package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.RoleRequestPendingPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the page that follows the given flow <br>
 * AccountDetailsPage --> MyRoles --> Pending request link -->RoleRequestPendingPage
 *
 */
public class RoleRequestPendingPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleRequestPendingPage.class);

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleRequestPendingPage.driver = driver;
	}
	public static void clickDeleteButton() {
		driver.findElement(RoleRequestPendingPageLocator.DELETE_BUTTON).click();
	}

	public static void confirmDeleteOnPopup() {
		driver.findElement(RoleRequestPendingPageLocator.POPUP_CONFIRM).click();
	}

	public static void clickAssignRole() {
		driver.findElement(RoleRequestPendingPageLocator.ASSIGNROLE_BUTTON).click();
		AssignRolePage.setDriver(RoleRequestPendingPage.getDriver());
		LaunchBrowserUtil.delay(4);
	}

	/**
	 * this methods checks whether a given string (comments) exist in the history
	 * section of the page
	 * @param comments the comments whose presence will be checked for
	 * @return true if the the comment is found, false otherwise
	 */
	public static boolean commentsExist(String comments) {
		List<WebElement> commentlist = driver.findElements(By.className("sam-comment"));
		logger.info("The size of the commentlist is-------" + commentlist.size());
		boolean commentFound = false;
		for (int i = 0; i < commentlist.size(); i++) {
			if (commentlist.get(i).getText().contains(comments)) {
				commentFound = true;
			}
		}
		return commentFound;
	}

	/**
	 * @param updatedComments the new comments to be entered
	 */
	public static void updateComment(String updatedComments) {
		driver.findElement(By.id("comment-component-input")).sendKeys(updatedComments);
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.id("comment-component-input")).sendKeys(Keys.RETURN);
		LaunchBrowserUtil.delay(2);

	}

	public static void signOut() {
		driver.findElement(By.id("headerIconProfile")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("header-link-signout")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickRejectButton() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div[2]/div/sam-button[1]/button"))
				.click();
	}

	public static void enterAdditionalInformation(String string) {
		driver.findElement(By.id("Additional Information")).sendKeys(string);
		LaunchBrowserUtil.delay(2);
	}

	public static String getAlertText() {
	String alerttext=	driver.findElement(By.tagName("sam-alert")).getText();
	logger.info("The text from the alert message is-- "+ alerttext);
	return alerttext;
		
	}

	public static void clickCancel() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("cancel-button")).click();
		LaunchBrowserUtil.delay(1);
	}
	

}
