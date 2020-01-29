package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * This class refers to the page where the users land
 * immediately after signup from login.gov
 *
 */
public class CommonProfilePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(CommonProfilePage.class);

	private CommonProfilePage() {

	}

	public static WebDriver getDriver() {
		return CommonProfilePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		CommonProfilePage.driver = driver;
	}

	public static void enterWorkphone(String phoneno) {
		driver.findElement(By.id("workphone-phone")).sendKeys(phoneno);
	}

	/**
	 * this method will select the given org name if found
	 * 
	 * @param org              the org name to be selected
	 * @param dropdownOptionNo the option to be selected
	 * @return true if found
	 */
	public static boolean selectOrgIfFound(String org, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("orgPicker")).sendKeys(org);
		List<WebElement> orgList = driver.findElements(By.className("selected-item"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(org.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	public static void clickSubmitButton() {
		driver.findElement(By.id("btn-submit")).click();
		RequestRoleOptionalPage.setDriver(driver);
		LaunchBrowserUtil.delay(3);
		T1WorkspacePage.setDriver(driver);
	}

	/**
	 * this method will select the given org name if found
	 * 
	 * @param entity           the entity to be selected
	 * @param dropdownOptionNo the option to be selected
	 * @return true if the entity is found
	 */
	public static boolean selectEntityNonFedIfFound(String entity, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("entityPicker")).sendKeys(entity);
		List<WebElement> orgList = driver.findElements(By.className("selected-item"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(entity.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	public static void enterFirstName(String firstname) {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("first-name")).sendKeys(firstname);

	}

	public static void enterLastName(String lastname) {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("last-name")).sendKeys(lastname);

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
