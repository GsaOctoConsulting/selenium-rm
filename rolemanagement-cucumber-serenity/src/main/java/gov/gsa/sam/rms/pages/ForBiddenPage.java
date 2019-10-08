package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

/**
 * this page is where users are directed to 
 * if they try to access a link where they dont
 * have the necessary permission for.
 *
 */
public class ForBiddenPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ForBiddenPage.class);
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		ForBiddenPage.driver = driver;
	}

	public static String getPrimaryContentMessage() {
		return driver.findElement(By.id("primary-content")).getText();
	}
}
