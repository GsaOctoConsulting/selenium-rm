package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.BulkUpdatePageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the Page under the Roles tab in the AccountDetails
 * Page....consider this as a subpage under AccountDetails
 *
 */
public class MyRolesPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyRolesPage.class);

	private MyRolesPage() {
	}

	public static WebDriver getDriver() {
		return MyRolesPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		MyRolesPage.driver = driver;
	}

	public static void clickRequestRoleButton() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(MyRolesPageLocator.REQUESTROLE_BUTTON).click();
		LaunchBrowserUtil.delay(2);
		RequestRolePage.setDriver(MyRolesPage.getDriver());
	}

	public static void clickRoleAssignedInRoleHistory() {
		driver.findElement(MyRolesPageLocator.ROLEASSIGN_LINK).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void click1PendingRequest() {
		driver.findElement(MyRolesPageLocator.PENDINGREQUEST_LINK).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickPendingLink() {
		driver.findElement(MyRolesPageLocator.PENDING).click();
		LaunchBrowserUtil.delay(2);
		RoleRequestPendingPage.setDriver(MyRolesPage.getDriver());
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
				if ((j == 2) && (eachRow.get(j).getText().toLowerCase().contains(domain.toLowerCase()))) {// finding
					domainFound = true;
					logger.info("multiple domain has been found..."); // multiple // domains
				}
				if ((j == 3) && (orgFound == true && roleFound == true && domainFound == true)
						&& (action.equalsIgnoreCase("DELETE"))) {

					WebElement deleteButton = eachRow.get(j).findElement(By.className("fa-trash"));
					deleteButton.click();

					WebElement deleteConfirmButton = driver.findElement(By.className("usa-modal-content-submit-btn"));
					deleteConfirmButton.click();
					LaunchBrowserUtil.delay(7);
					return true;
				}
				if ((j == 3) && (orgFound == true && roleFound == true && domainFound == true)
						&& (action.equalsIgnoreCase("EDIT"))) {

					WebElement editButton = eachRow.get(j).findElement(By.className("fa-pencil"));
					logger.info("*********************about to click the edit button******************");
					editButton.click();
					logger.info("*********************about to click the edit button******************");
					AssignRolePage.setDriver(UserDirectoryViewAccessPage.getDriver());
					LaunchBrowserUtil.delay(3);
					return true;
				}

				if ((j == 3) && (orgFound == true && roleFound == true && domainFound == true)
						&& (action.equalsIgnoreCase("VIEW PERMISSION"))) {

					WebElement viewlink = eachRow.get(j).findElement(By.className("access-action-icon"));
					logger.info("*********************about to 'view' link******************");
					viewlink.click();
					LaunchBrowserUtil.delay(3);
					return true;
				}

			}
			if (orgFound == true && roleFound == true && domainFound == true) {
				LaunchBrowserUtil.delay(3);
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

	public static void goToFeedsPage() {
		LaunchBrowserUtil.delay(3);
		driver.findElement(MyRolesPageLocator.FEED_NOTIFICATION_ICON).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(MyRolesPageLocator.SHOWMORE_REQUEST_LINK).click();
		LaunchBrowserUtil.delay(3);
		FeedsRequestPage.setDriver(MyRolesPage.getDriver());
	}

	public static void signOut() {
		driver.findElement(BulkUpdatePageLocator.HEADER_ICON_PROFILE).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(BulkUpdatePageLocator.HEADERLINK_SIGNOUT).click();

	}

	public static void writeAdditionalInformation(String string) {
		driver.findElement(By.id("Additional Information")).sendKeys(string);
		LaunchBrowserUtil.delay(2);

	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static String getLatestRoleHistory() {
		List<WebElement> rolehistorylist = driver.findElements(By.tagName("sam-history"));
		String latesthistorydescription = rolehistorylist.get(0).findElement(By.tagName("em")).getText();
		logger.info("The description for latest history is-- " + latesthistorydescription);
		return latesthistorydescription;

	}

	public static String getTextForNoRoleUser() {
		LaunchBrowserUtil.delay(3);
		return driver.findElement(MyRolesPageLocator.NO_ROLE_TEXT).getText();

	}

	public static boolean roleHistoryFound(String timestamp, String rolestatus, String message, int rolehistoryno) {
		WebElement rolehistory = driver.findElement(By.tagName("sam-history"));
		List<WebElement> historylist = rolehistory.findElements(By.xpath("//li[starts-with(@class, 'history-item')]"));
		logger.info("The size of the role history list is - " + historylist.size());

		String fullhistorytext = historylist.get(rolehistoryno).getText();
		logger.info("The full text of this particular history is - " + fullhistorytext);
		boolean timestampfound = false;
		boolean rolestatusfound = false;
		boolean messagefound = false;

		if (fullhistorytext.contains(timestamp)) {
			logger.info("the timestamp was found");
			timestampfound = true;
		}
		if (fullhistorytext.contains(rolestatus)) {
			logger.info("the rolestatus was found");
			rolestatusfound = true;
		}
		if (fullhistorytext.contains(message)) {
			logger.info("the message was found");
			messagefound = true;
		}

		if (timestampfound && rolestatusfound && messagefound) {
			LaunchBrowserUtil.delay(3);
			return true;
		} else {
			LaunchBrowserUtil.delay(3);
			return false;
		}

	}

	public static void clickAssignRoleButton() {
		driver.findElement(MyRolesPageLocator.ASSIGN_BUTTON).click();
		AssignRolePage.setDriver(driver);
		
	}

	public static String getTextForPendingRequest() {
		String message = driver.findElement(By.className("usa-width-two-thirds")).getText();
		logger.info("The message found is -- "+message);
		return message;
	}
}
