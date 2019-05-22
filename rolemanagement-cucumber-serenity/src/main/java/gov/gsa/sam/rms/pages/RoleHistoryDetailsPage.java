package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.CommonMethods;


public class RoleHistoryDetailsPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(RoleHistoryDetailsPage.class);
	// *****************************************************************************
		// driver getter and setter
		// *****************************************************************************
		public static WebDriver getDriver() {
			return driver;
		}

		public static void setDriver(WebDriver driver) {
			RoleHistoryDetailsPage.driver = driver;
		}

		public static void clickHereLink() {
		driver.findElement(By.linkText("click here")).click();
		CommonMethods.delay(2);
		
		}


}
