package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

public class RoleInviteAssignRolePage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleInviteAssignRolePage.class);
	
	
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		RoleInviteAssignRolePage.driver = driver;
	}
	public static void enterEmailAddress(String email) {
		driver.findElement(By.id("user-email")).sendKeys(email);
		LaunchBrowserUtil.delay(3);
		
	}
	public static void clickExistingUserAcceptButton() {
		driver.findElement(By.id("existing-user-accept-button")).click();
		LaunchBrowserUtil.delay(2);
	}
	public static void selectDomain(String domain) {
		driver.findElement(By.id("domains-ac-textarea")).sendKeys(domain);
		
	}
	public static String getUserEmailErrorMessage() {
		return driver.findElement(By.id("user-email-error")).getText();
		 
	}
	
}
