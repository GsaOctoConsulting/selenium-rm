package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.RoleRequestPendingPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class RoleRequestPendingPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleRequestPendingPage.class);

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************

	public static void clickDeleteButton() {
		driver.findElement(RoleRequestPendingPageLocator.DELETE_BUTTON).click();
	}

	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page.
	// *****************************************************************************
	public static void confirmDeleteOnPopup() {
		driver.findElement(RoleRequestPendingPageLocator.POPUP_CONFIRM).click();

	}

	public static void clickAssignRole() {
		driver.findElement(RoleRequestPendingPageLocator.ASSIGNROLE_BUTTON).click();
		AssignRolePage.setDriver(RoleRequestPendingPage.getDriver());
		CommonMethods.delay(2);
	}

	// *****************************************************************************
	// driver getter and setter & private methods
	// *****************************************************************************
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleRequestPendingPage.driver = driver;
	}

	public static boolean commentsExist(String string, String comments) {
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

	public static void updateComment(String updatedComments) {
		driver.findElement(By.id("comment-component-input")).sendKeys(updatedComments);
		driver.findElement(By
				.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/rm-comments/section/form/div[2]/sam-button/button"))
				.click();

	}

	public static void signOut() {
		driver.findElement(By.xpath("//*[@id=\"headerIconProfile\"]/i")).click();
		CommonMethods.delay(1);
		driver.findElement(By.id("header-link-signout")).click();
		CommonMethods.delay(1);
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/sam-login/us-security-modal/div/div/div[2]/div/sam-button/button")).click();
		CommonMethods.delay(2);
		
	}

	public static void clickRejectButton() {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div[2]/div/sam-button[1]/button")).click();
		
	}

}
