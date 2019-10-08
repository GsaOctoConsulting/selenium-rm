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
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;

/**
 * This class refers to the page page seen when some users have been selected
 * from bulk update page
 */
public class BulkUpdateSelectionPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(BulkUpdateSelectionPage.class);

	private BulkUpdateSelectionPage() {
	}

	public static WebDriver getDriver() {
		return BulkUpdateSelectionPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		BulkUpdateSelectionPage.driver = driver;
	}

	public static String get2ndHeaderTitle() {
		return driver.findElement(BulkUpdateSelectionPageLocator.SECONDHEADER).getText();
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
