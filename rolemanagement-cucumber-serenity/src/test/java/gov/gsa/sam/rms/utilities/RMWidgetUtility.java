package gov.gsa.sam.rms.utilities;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.MyWorkspacePageLocator;
import gov.gsa.sam.rms.pages.BulkUpdatePage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.pages.RoleDefinitionPage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;

public class RMWidgetUtility {
	private static Logger logger = LoggerFactory.getLogger(RMWidgetUtility.class);

	public static boolean widgetFound() {
		try {
			MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.USER_SEARCH_BAR);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuchElementException--RM Widget Not Found");
			return false;
		}

	}

	public static boolean searchUser(String username) {
		try {
			MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.USER_SEARCH_BAR).sendKeys(username);
			CommonMethods.delay(Constants.SECONDS);
			WebElement user = MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.AUTOCOMPLETE_RESULTS);
			user.click();
			RolesDirectoryViewAccessPage.setDriver(MyWorkspacePage.getDriver());
			UserDirectoryPage.setDriver(MyWorkspacePage.getDriver());
			CommonMethods.delay(2);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuchElementException--Searched user not found");
			return false;
		}

	}

	public static void clickUserDirectoryLink() {
		MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.USERDIRECTORY_LINK).click();
		UserDirectoryPage.setDriver(MyWorkspacePage.getDriver());
		CommonMethods.delay(2);
	}

	public static void clickRoleDefinitionsLink() {
		MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.ROLE_DEFINITION_LINK).click();
		RoleDefinitionPage.setDriver(MyWorkspacePage.getDriver());
		CommonMethods.delay(2);
	}

	public static void clickBulkUpdateLink() {
		MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.BULK_UPDATELINK).click();
		BulkUpdatePage.setDriver(MyWorkspacePage.getDriver());
		CommonMethods.delay(3);
	}

	public static void clickPendingRequestLink() {
		MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.PENDING_REQUEST).click();
		CommonMethods.delay(2);
	}
	/*
	 * public static boolean widgetFound() { int secondsToWait = 0;
	 * WebDriverWait wait = new WebDriverWait(MyWorkspacePage.getDriver(),
	 * secondsToWait); try {
	 * wait.until(ExpectedConditions.presenceOfElementLocated(
	 * MyWorkspacePageLocator.USER_SEARCH_BAR)); return true; } catch
	 * (org.openqa.selenium.TimeoutException e) { return false; } }
	 */
	public static boolean linkFound(String linkname) {
		boolean optionFound = false;
		String widgetText = MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.WIDGET).getText();

		logger.info("" + widgetText);

		if (linkname.equals("Pending Role Requests") && widgetText.contains("Pending Role Requests")) {
			optionFound = true;
		} else if (linkname.equals("Bulk Update") && widgetText.contains("Bulk Update")) {
			optionFound = true;
		} else if (linkname.equals("Role Definitions") && widgetText.contains("Role Definitions")) {
			optionFound = true;
		}
		return optionFound;
	}

	public static boolean userSearchForRA(String username) {
		try {
			MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.USER_SEARCH_FOR_RA).sendKeys(username);
			CommonMethods.delay(Constants.SECONDS);
			WebElement user = MyWorkspacePage.getDriver().findElement(MyWorkspacePageLocator.AUTOCOMPLETE_RESULTS);
			user.click();
			RolesDirectoryViewAccessPage.setDriver(MyWorkspacePage.getDriver());
			UserDirectoryPage.setDriver(MyWorkspacePage.getDriver());
			CommonMethods.delay(2);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuchElementException--Searched user not found");
			return false;
		}

	}
}
