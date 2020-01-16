package gov.gsa.sam.rms.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.MyWorkspacePageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.pages.BulkUpdatePage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RoleDefinitionPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;

/**
 * use this class to interact with user directory widget
 * and all its element on the workspace page. For interactions
 * with other elements outside of widget use methods defined in WorkspacePage.java
 *
 */
public class UserDirectoryWidgetUtility {
	private static Logger logger = LoggerFactory.getLogger(UserDirectoryWidgetUtility.class);

	/**
	 * @return true if userdirectory widget is found, false otherwise
	 */
	public static boolean widgetFound() {
		try {
			T1WorkspacePage.getDriver().findElement(T1WorkspacePageLocator.USER_SEARCH_BAR);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuchElementException--User Directory Widget Not Found");
			return false;
		}

	}

	/**
	 * @param username the id of the user to be searched for
	 * @return true if the user is found and then page goes to userdirectory page,
	 *         false otherwise
	 */
	public static boolean searchUser(String username) {
		try {
			T1WorkspacePage.getDriver().findElement(T1WorkspacePageLocator.USER_SEARCH_BAR).sendKeys(username);
			LaunchBrowserUtil.delay(2);
			WebElement user = T1WorkspacePage.getDriver().findElement(T1WorkspacePageLocator.AUTOCOMPLETE_RESULTS);
			user.click();
			UserDirectoryViewAccessPage.setDriver(T1WorkspacePage.getDriver());
			UserDirectoryPage.setDriver(T1WorkspacePage.getDriver());
			LaunchBrowserUtil.delay(3);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuchElementException--Searched user not found");
			return false;
		}

	}

	public static void clickUserDirectoryLink() {
		T1WorkspacePage.getDriver().findElement(By.id("user-directory-link")).click();
		UserDirectoryPage.setDriver(T1WorkspacePage.getDriver());
		LaunchBrowserUtil.delay(3);
	}

	public static void clickRoleDefinitionsLink() {
		T1WorkspacePage.getDriver().findElement(By.id("role-definitions-button")).click();
		RoleDefinitionPage.setDriver(T1WorkspacePage.getDriver());
		LaunchBrowserUtil.delay(2);
	}

	public static void clickBulkUpdateLink() {
		T1WorkspacePage.getDriver().findElement(By.id("bulk-update-button")).click();
		BulkUpdatePage.setDriver(T1WorkspacePage.getDriver());
		LaunchBrowserUtil.delay(3);
	}

	public static void clickPendingRequestLink() {
		T1WorkspacePage.getDriver().findElement(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK).click();
		LaunchBrowserUtil.delay(4);
	}

	/**
	 * @param linkname the name of the links on the userdirectory widget, eg Bulk
	 *                 Update, Role Definitions etc, see method implementation
	 * @return true if the link is found, false otherwise
	 */
	public static boolean linkFound(String linkname) {
		boolean optionFound = false;
		String widgetText = T1WorkspacePage.getDriver().findElement(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET).getText();

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
}
