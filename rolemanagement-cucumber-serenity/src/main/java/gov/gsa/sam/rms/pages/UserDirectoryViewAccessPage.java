package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.gsa.sam.rms.utilities.Constants;

import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this page refers to following flow for specific users only
 * WorkspacePage-->UserDirectoryPage-->UserDirectoryViewAccessPage (when looking up a user)
 *
 */
public class UserDirectoryViewAccessPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(UserDirectoryViewAccessPage.class);

	private UserDirectoryViewAccessPage() {
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		UserDirectoryViewAccessPage.driver = driver;
	}
	public static void clickAssignRole() {
		driver.findElement(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON).click();
		AssignRolePage.setDriver(UserDirectoryViewAccessPage.getDriver());
		LaunchBrowserUtil.delay(2);
	}
	/**
	 * this method look for whether a role is present or not. In addition the custom
	 * command is able to edit or delete the role if the user has the required
	 * permission
	 * 
	 * @param org    the organization name e.g GSA
	 * @param role   the role name e.g Assistance user
	 * @param domain the domain name eg. Assistance Listing
	 * @param action custom action if needed, eg. EDIT, DELETE
	 * @return true if the ORD combination is found, false otherwise
	 */
	public static boolean userHasRole(String org, String role, String domain, String action) {
		MyRolesPage.setDriver(driver);
		return MyRolesPage.userHasRole(org, role, domain, action);
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuch Element found");
			return false;
		}
	}

	/**
	 * @param accountEditor name of the account editor
	 * @param actiontaken eg. granted, removed
	 * @param role the name of the role
	 * @param org the org name
	 * @param nextAction eg. GO_INTO_ROLEASSIGNED
	 * @return true if found, false otherwise
	 */
	public static boolean latestRoleHistoryFound(String accountEditor, String actiontaken, String role, String org,
			String nextAction) {
		boolean roleHistoryFound = false;
		boolean accoundEditorNameFound = false;
		boolean roleFound = false;
		boolean orgFound = false;
		boolean actionTakenFound = false;
		List<WebElement> allTimelines = driver.findElements(By.xpath("//li[starts-with(@class, 'history-item')]"));
		logger.info("The number of timeline found for this user is --" + allTimelines.size());

		for (int i = 0; i < 1; i++) {// looking at the first one only at this
										// time...may change later

			if (allTimelines.get(0).getText().toLowerCase().contains(accountEditor.toLowerCase())) {
				accoundEditorNameFound = true;
			}
			if (allTimelines.get(0).getText().toLowerCase().contains(role.toLowerCase())) {
				roleFound = true;
			}
			if (allTimelines.get(0).getText().toLowerCase().contains(org.toLowerCase())) {
				orgFound = true;
			}
			if (allTimelines.get(0).getText().toLowerCase().contains(org.toLowerCase())) {
				actionTakenFound = true;
			}

		}
		if (accoundEditorNameFound == true && roleFound == true && orgFound == true && actionTakenFound == true
				&& (nextAction.equals(Constants.GO_INTO_ROLE_ASSIGNED))) {
			logger.info("The role history was found");
			roleHistoryFound = true;
			allTimelines.get(0).findElement(By.linkText("Role Assigned")).click();
		
		} else if (accoundEditorNameFound == true && roleFound == true && orgFound == true && actionTakenFound == true
				&& (nextAction.equals(Constants.GO_INTO_ROLE_UPDATED))) {
			logger.info("The role history was found");
			roleHistoryFound = true;
			allTimelines.get(0).findElement(By.linkText("Role Updated")).click();			
		}
		return roleHistoryFound;
	}

	public static void goToUserDirectoryPage() {
		driver.findElement(By.linkText("User Directory")).click();
		LaunchBrowserUtil.delay(2);	
	}

}
