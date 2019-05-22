package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.RoleRequestApprovalPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class RoleRequestApprovalPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleRequestApprovalPage.class);

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void clickRejectButton() {
		logger.info(driver.findElement(RoleRequestApprovalPageLocator.REJECT_BUTTON).getText());
		driver.findElement(RoleRequestApprovalPageLocator.REJECT_BUTTON).click();
		CommonMethods.delay(3);
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
		RoleRequestApprovalPage.driver = driver;
	}

}
