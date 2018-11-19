package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.SystemAccountDirectoryPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import net.thucydides.core.annotations.findby.By;

public class SystemAccountDirectoryPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(SystemAccountDirectoryPage.class);

	private SystemAccountDirectoryPage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return SystemAccountDirectoryPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		SystemAccountDirectoryPage.driver = driver;
	}

	public static void clickNewButton() {
		driver.findElement(SystemAccountDirectoryPageLocator.NEW_BUTTON).click();
		NewSystemAccountPage.setDriver(driver);
		CommonMethods.delay(1);
	}

	public static boolean accountFound(String accountname, String status, String org, String domain, String action) {
		boolean found = false;
		List<WebElement> allAccounts = driver.findElements(By.tagName("system-listing"));
		logger.info("The number of accounts found is --" + allAccounts.size());
		for (int i = 0; i < allAccounts.size(); i++) {
			String uiAccountName = allAccounts.get(i).findElement(By.tagName("h4")).getText();
			boolean accountNameMatched = uiAccountName.toLowerCase().trim().equals(accountname.toLowerCase().trim());
			logger.info("The status of account#" + i + " is --" + uiAccountName + "  & The accountNameMatched ---"
					+ accountNameMatched);
			CommonMethods.delay(2);
			String uiStatus = allAccounts.get(i).findElement(By.className("text-center")).getText();
			boolean statusMatched = uiStatus.toLowerCase().trim().equals(status.toLowerCase().trim());
			logger.info(
					"The status of account#" + i + " is --" + uiStatus + "  & The statusMatched ---" + statusMatched);
			CommonMethods.delay(1);
			StringBuilder uiOrgName = new StringBuilder(
					allAccounts.get(i).findElement(By.tagName("sam-label-wrapper")).getText().toLowerCase().trim());
			boolean orgNameMatched = (uiOrgName).toString().contains(org.toLowerCase().trim());
			logger.info(
					"The org of account#" + i + " is --" + uiOrgName + "  & The orgNameMatched ---" + orgNameMatched);
			logger.info(
					"------------------------------------------------------------------------------------------------");

			if (statusMatched == true && accountNameMatched == true && orgNameMatched == true) {
				if (action.equals(Constants.DELETE)) {
					CommonMethods.delay(1);
					WebElement actions = allAccounts.get(i).findElement(By.className("usa-button"));
					actions.click();
					CommonMethods.delay(2);
					allAccounts.get(i).findElement(By.className("fa-times")).click();
				} else if (action.equals(Constants.GO_TO_REQUEST_DETAILS)) {
					CommonMethods.delay(1);
					logger.info(
							"The current code has been hit*********************************************************************");
					allAccounts.get(i).findElement(By.tagName("a")).click();
					SystemAccountRequestPage.setDriver(driver);
					break;
				}

				found = true;
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
		driver.findElement(By.id("filter-pending")).click();
		CommonMethods.delay(2);

	}

	public static void clickSortDescedingByTimestampButton() {
		driver.findElement(By.className("fa-sort-amount-asc")).click();
		CommonMethods.delay(3);

	}

	public static void clickRejectedFilter() {
		driver.findElement(By.id("filter-rejected")).click();
		CommonMethods.delay(2);

	}

	public static void clickDraftFilter() {
		driver.findElement(By.id("filter-draft")).click();
		CommonMethods.delay(2);
		
	}

	
}