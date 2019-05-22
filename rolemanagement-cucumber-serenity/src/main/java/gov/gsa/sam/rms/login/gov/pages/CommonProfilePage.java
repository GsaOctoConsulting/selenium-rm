package gov.gsa.sam.rms.login.gov.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class CommonProfilePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(CommonProfilePage.class);

	private CommonProfilePage() {

	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return CommonProfilePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		CommonProfilePage.driver = driver;
	}

	public static void enterWorkphone(String phoneno) {
		driver.findElement(By.id("workphone-phone")).sendKeys(phoneno);
	}

	public static boolean selectOrgIfFound(String org, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("orgPicker")).sendKeys(org);
		List<WebElement> orgList = driver.findElements(By.className("selected-item"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(org.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			CommonMethods.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	public static void clickSubmitButton() {
		driver.findElement(By.id("btn-submit")).click();
		CommonMethods.delay(3);
		RequestRoleOptionalPage.setDriver(driver);
	}

	public static boolean selectOrgNonFedIfFound(String org, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("entityPicker")).sendKeys(org);
		List<WebElement> orgList = driver.findElements(By.className("selected-item"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(org.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			CommonMethods.delay(3);
			orgFound = true;
		}
		return orgFound;	
	}

	public static void enterFirstName(String firstname) {
		CommonMethods.delay(1);
		driver.findElement(By.id("first-name")).sendKeys(firstname);
		
	}

	public static void enterLastName(String lastname) {
		CommonMethods.delay(1);
		driver.findElement(By.id("last-name")).sendKeys(lastname);
		
	}
}
