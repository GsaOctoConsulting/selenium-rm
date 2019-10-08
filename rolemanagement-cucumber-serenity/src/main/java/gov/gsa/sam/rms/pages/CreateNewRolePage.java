package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.CreateNewRolePageLocator;
import gov.gsa.sam.rms.locators.RoleDefinitionPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this page refers to the one that comes up when user clicks 'create new role'
 * on the role definition page
 *
 */
public class CreateNewRolePage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CreateNewRolePage.class);

	private CreateNewRolePage() {
	}
	public static WebDriver getDriver() {
		return CreateNewRolePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		CreateNewRolePage.driver = driver;
	}
	public static void clickCancel() {
		driver.findElement(CreateNewRolePageLocator.CANCEL_BUTTON).click();
		RoleDefinitionPage.setDriver(CreateNewRolePage.getDriver());
		LaunchBrowserUtil.delay(3);

	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			logger.info(driver.findElement(locator).getText());
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

}
