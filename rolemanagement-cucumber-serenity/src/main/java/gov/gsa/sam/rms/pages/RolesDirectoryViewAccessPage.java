package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.gsa.sam.rms.utilities.Constants;

import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class RolesDirectoryViewAccessPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(RolesDirectoryViewAccessPage.class);

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void clickAssignRole() {
		driver.findElement(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON).click();
		AssignRolePage.setDriver(RolesDirectoryViewAccessPage.getDriver());
		CommonMethods.delay(2);
	}

	public static boolean userHasRole(String org, String role, String domain, String action) {
		List<WebElement> allRoles = driver.findElements(RolesDirectoryViewAccessLocator.CURRENT_ROLES);
		logger.info("The number of role this use has is --" + allRoles.size());
		for (int i = 0; i < allRoles.size(); i++) {
			List<WebElement> eachRow = allRoles.get(i).findElements(By.tagName("td"));
			boolean orgFound = false;
			boolean roleFound = false;
			boolean domainFound = false;

			for (int j = 0; j < eachRow.size(); j++) {// each columns

				if ((j == 0) && (eachRow.get(j).getText().toLowerCase().contains(org.toLowerCase()))) {
					orgFound = true;
					logger.info("The org has been found...");
				}
				if ((j == 1) && (eachRow.get(j).getText().equalsIgnoreCase(role))) {
					roleFound = true;
					logger.info("The role has been found...");
				}
				if ((j == 2) && (eachRow.get(j).getText().equalsIgnoreCase(domain))) {
					domainFound = true;
					logger.info("The domain has been found...");
				}
				if ((j == 3) && (orgFound == true && roleFound == true && domainFound == true)
						&& (action.equalsIgnoreCase("DELETE"))) {
					logger.info("The delete action has been requested");
					WebElement deleteButton = eachRow.get(j).findElement(By.className("fa-trash"));
					deleteButton.click();
					CommonMethods.delay(3);

					WebElement deleteConfirmButton = driver.findElement(By.className("usa-modal-content-submit-btn"));
					deleteConfirmButton.click();
					return true;
				}
				if ((j == 3) && (orgFound == true && roleFound == true && domainFound == true)
						&& (action.equalsIgnoreCase("EDIT"))) {
					logger.info("The edit action has been requested");
					WebElement editButton = eachRow.get(j).findElement(By.className("fa-pencil"));
					logger.info("*********************about to click the edit button******************");
					editButton.click();
					logger.info("*********************about to click the edit button******************");
					AssignRolePage.setDriver(RolesDirectoryViewAccessPage.getDriver());
					CommonMethods.delay(2);
					return true;
				}
				/*
				 * if (eachRow.get(j)) { List<WebElement> buttons =
				 * eachRow.get(j).findElements(By.className("fa")); System.out.
				 * println("The element is of size-------------------------  " +
				 * buttons.size()); buttons.get(0).click(); }
				 */

			}
			if (orgFound == true && roleFound == true && domainFound == true) {
				return true;
			}

		}

		return false;
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

	private RolesDirectoryViewAccessPage() {
	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		RolesDirectoryViewAccessPage.driver = driver;
	}

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
			RoleHistoryDetailsPage.setDriver(driver);
		} else if (accoundEditorNameFound == true && roleFound == true && orgFound == true && actionTakenFound == true
				&& (nextAction.equals(Constants.GO_INTO_ROLE_UPDATED))) {
			logger.info("The role history was found");
			roleHistoryFound = true;
			allTimelines.get(0).findElement(By.linkText("Role Updated")).click();
			RoleHistoryDetailsPage.setDriver(driver);
		}
		return roleHistoryFound;
	}

	public static void goToUserDirectoryPage() {
		driver.findElement(By.linkText("User Directory")).click();
		CommonMethods.delay(2);	
	}

}
