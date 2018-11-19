package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.BulkUpdatePageLocator;
import gov.gsa.sam.rms.locators.BulkUpdateSelectionPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;

public class BulkUpdateSelectionPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(BulkUpdateSelectionPage.class);

	private BulkUpdateSelectionPage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************

	public static String get2ndHeaderTitle() {
		return driver.findElement(BulkUpdateSelectionPageLocator.SECONDHEADER).getText();
	}

	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page..eg. clickNavSignIn()
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return BulkUpdateSelectionPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		BulkUpdateSelectionPage.driver = driver;
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}
}
