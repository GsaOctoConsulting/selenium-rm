package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.SystemAccountDirectoryPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;

/**
 * this class refers to the System account Directory
 * page listing all the system account and their status
 * visible to the user
 *
 */
public class SystemAccountDirectoryPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(SystemAccountDirectoryPage.class);

	private SystemAccountDirectoryPage() {
	}

	public static WebDriver getDriver() {
		return SystemAccountDirectoryPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		SystemAccountDirectoryPage.driver = driver;
	}

	public static void clickNewButton() {
		driver.findElement(SystemAccountDirectoryPageLocator.NEW_BUTTON).click();
		NewSystemAccountPage.setDriver(driver);
		LaunchBrowserUtil.delay(1);
	}

	/**
	 * this method confirms the presence of an account when looked up using the given parameters
	 * @param accountname 
	 * @param status eg. pending review, pending approval, etc
	 * @param org the name of the org
	 * @param domain the name of the domain
	 * @param action defines what to do if the account was found, eg GO Into details
	 * @return
	 */
	public static boolean accountFound(String accountname, String status, String org, String domain, String action) {
		boolean found = false;
		List<WebElement> allAccounts = driver.findElements(By.tagName("system-listing"));
		logger.info("The number of accounts found is --" + allAccounts.size());
		for (int i = 0; i < allAccounts.size(); i++) {
			String uiAccountName = allAccounts.get(i).findElement(By.tagName("h4")).getText();
			boolean accountNameMatched = uiAccountName.toLowerCase().trim().equals(accountname.toLowerCase().trim());
			logger.info("The status of account#" + i + " is --" + uiAccountName + "  & The accountNameMatched ---"
					+ accountNameMatched);
			LaunchBrowserUtil.delay(2);
			String uiStatus = allAccounts.get(i).findElement(By.className("text-center")).getText();
			boolean statusMatched = uiStatus.toLowerCase().trim().equals(status.toLowerCase().trim());
			logger.info(
					"The status of account#" + i + " is --" + uiStatus + "  & The statusMatched ---" + statusMatched);
			LaunchBrowserUtil.delay(1);
			StringBuilder uiOrgName = new StringBuilder(
					allAccounts.get(i).findElement(By.tagName("sam-label-wrapper")).getText().toLowerCase().trim());
			boolean orgNameMatched = (uiOrgName).toString().contains(org.toLowerCase().trim());
			logger.info(
					"The org of account#" + i + " is --" + uiOrgName + "  & The orgNameMatched ---" + orgNameMatched);
			logger.info(
					"------------------------------------------------------------------------------------------------");

			if (statusMatched == true && accountNameMatched == true && orgNameMatched == true) {
				found = true;
				if (action.equals(Constants.DELETE)) {
					LaunchBrowserUtil.delay(1);
					WebElement actions = allAccounts.get(i).findElement(By.className("usa-button"));
					actions.click();
					LaunchBrowserUtil.delay(2);
					allAccounts.get(i).findElement(By.className("fa-times")).click();
				} else if (action.equals(Constants.GO_TO_REQUEST_DETAILS)) {
					LaunchBrowserUtil.delay(1);
					logger.info("***************GO_TO_REQUEST_DETAILS option picked***************");
					allAccounts.get(i).findElement(By.tagName("a")).click();
					SystemAccountRequestDetailsPage.setDriver(driver);
					break;
				}

				// found = true;
			}
		}
		return found;
	}

	public static boolean elementFound(org.openqa.selenium.By newButton) {
		try {
			driver.findElement(newButton);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void clickPendingApprovalFilter() {
		// driver.findElement(By.id("filter-pending-approval")).click();
		// option 3
		WebElement element = driver.findElement(By.id("filter-pending-approval"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		// CommonMethods.delay(2);
		LaunchBrowserUtil.delay(2);

	}

	public static void clickSortDescedingByTimestampButton() {
		driver.findElement(By.className("fa-sort-amount-asc")).click();
		LaunchBrowserUtil.delay(4);

	}

	public static void clickRejectedFilter() {
		driver.findElement(By.id("filter-rejected")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickDraftFilter() {
		driver.findElement(By.id("filter-draft")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickPendingReviewFilter() {
		// driver.findElement(By.id("filter-pending-review")).click();

		// option 2
		/*
		 * WebDriverWait wait2 = new WebDriverWait(driver, 10);
		 * wait2.until(ExpectedConditions.elementToBeClickable(By.id(
		 * "filter-pending-review"))).click();
		 */

		// option 3
		WebElement element = driver.findElement(By.id("filter-pending-review"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickDeactivatedFilter() {
		driver.findElement(By.id("filter-deactivated")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickPublishedFilter() {
		driver.findElement(By.id("filter-published")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void searchByKeyword(String keyword) {
		driver.findElement(By.id("search-keyword")).clear();
		driver.findElement(By.id("search-keyword")).sendKeys(keyword);
		LaunchBrowserUtil.delay(3);
	}
	public static boolean isFilterSelected(By filterlocator) {
		return driver.findElement(filterlocator).isSelected();
	}

	public static void clickPendingPermissionsApprovalFilter() {
		driver.findElement(By.id("filter-pending-permissions-approval")).click();
		LaunchBrowserUtil.delay(2);
		
	}

}