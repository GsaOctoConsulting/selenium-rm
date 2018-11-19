package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.BulkUpdatePageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class MyRolesPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyRolesPage.class);

	private MyRolesPage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void clickRequestRoleButton() {
		driver.findElement(MyRolesPageLocator.REQUESTROLE_BUTTON).click();
		CommonMethods.delay(2);
		RequestRolePage.setDriver(MyRolesPage.getDriver());
	}

	public static void clickRoleAssignedInRoleHistory() {
		driver.findElement(MyRolesPageLocator.ROLEASSIGN_LINK).click();
		CommonMethods.delay(2);
		RoleAssignPage.setDriver(MyRolesPage.getDriver());
	}

	public static void click1PendingRequest() {
		driver.findElement(MyRolesPageLocator.PENDINGREQUEST_LINK).click();

	}

	public static void clickPendingLink() {
		driver.findElement(MyRolesPageLocator.PENDING).click();
		CommonMethods.delay(2);
		RoleRequestPendingPage.setDriver(MyRolesPage.getDriver());
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
				System.out.println(eachRow.get(j).getText());

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

					WebElement deleteButton = eachRow.get(j).findElement(By.className("fa-trash"));
					deleteButton.click();
					CommonMethods.delay(2);

					WebElement deleteConfirmButton = driver.findElement(By.className("usa-modal-content-submit-btn"));
					deleteConfirmButton.click();
					return true;
				}
				if ((j == 3) && (orgFound == true && roleFound == true && domainFound == true)
						&& (action.equalsIgnoreCase("EDIT"))) {

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

	public static boolean websiteLinkFound() {
		String tag = driver.findElement(MyRolesPageLocator.WEBSITE_LINK).getTagName();
		if (tag.equals("a")) {
			return true;
		}
		return false;
	}
	public static boolean beginNowLinkFound() {
		String tag = driver.findElement(MyRolesPageLocator.BEGINNOW_LINK).getTagName();
		if (tag.equals("a")) {
			return true;
		}
		return false;
	}
	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page.
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter & private methods
	// *****************************************************************************
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		MyRolesPage.driver = driver;
	}

	public static void goToFeedsPage() {
		CommonMethods.delay(3);
		driver.findElement(MyRolesPageLocator.FEED_NOTIFICATION_ICON).click();
		CommonMethods.delay(3);
		driver.findElement(MyRolesPageLocator.SHOWMORE_REQUEST_LINK).click();
		CommonMethods.delay(3);
		FeedsRequestPage.setDriver(MyRolesPage.getDriver());
	}

	public static void signOut() {
		driver.findElement(BulkUpdatePageLocator.HEADER_ICON_PROFILE).click();
		CommonMethods.delay(1);
		driver.findElement(BulkUpdatePageLocator.HEADERLINK_SIGNOUT).click();

	}

}
