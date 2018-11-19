package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.CreateNewRolePageLocator;
import gov.gsa.sam.rms.locators.RoleDefinitionPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class CreateNewRolePage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CreateNewRolePage.class);

	
	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	
public static void clickCancel() {
	driver.findElement(CreateNewRolePageLocator.CANCEL_BUTTON).click();
	RoleDefinitionPage.setDriver(CreateNewRolePage.getDriver());
	CommonMethods.delay(3);
		
	}
	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page.
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter & private methods & utility methods
	// *****************************************************************************
	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			logger.info(driver.findElement(locator).getText());
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}
	private CreateNewRolePage() {
	}

	public static WebDriver getDriver() {
		return CreateNewRolePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		CreateNewRolePage.driver = driver;
	}

	
}
