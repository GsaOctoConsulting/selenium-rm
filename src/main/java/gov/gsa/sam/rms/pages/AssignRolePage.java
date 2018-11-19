package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.HomePageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import org.openqa.selenium.support.ui.Select;

public class AssignRolePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(AssignRolePage.class);

	private AssignRolePage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void clickRoleDropdown() {
		CommonMethods.delay(Constants.seconds);
		driver.findElement(AssignRolePageLocator.ROLE_SELECTOR).click();
	}

	public static void clickDomainDropdown() {
		CommonMethods.delay(Constants.seconds);
		driver.findElement(AssignRolePageLocator.DOMAINDROPDOWN_ICON).click();
	}

	public static List<WebElement> getAllRoles() {
		CommonMethods.delay(Constants.seconds);
		return driver.findElements(AssignRolePageLocator.ROLE_SELECTOR);
	}

	public static boolean selectRoleIfFound(String roleName) {
		boolean roleFound = false;
		Select role = new Select(driver.findElement(AssignRolePageLocator.ROLE_SELECTOR));

		try {
			role.selectByVisibleText(roleName);
			roleFound = true;
		} catch (NoSuchElementException e) {

			return roleFound;
		}
		return roleFound;

	}

	public static boolean selectDomainIfFound(String domainName) {
		boolean domainFound = false;
		clickDomainDropdown();
		List<WebElement> domain = driver.findElements(AssignRolePageLocator.DOMAIN_SELECTOR);
		logger.info(("The size of the list is......" + domain.size()));

		for (int i = 0; i < domain.size(); i++) {
			WebElement currentDomain = domain.get(i);
			logger.info(currentDomain.getText());
			if (domainName.equals(currentDomain.getText())) {
				domainFound = true;
				currentDomain.click();
				return domainFound;
			}
		}
		return domainFound;

	}

	public static boolean selectOrgIfFound(String orgName) {
		boolean orgFound = false;
		driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(orgName);
		List<WebElement> orgList = driver.findElements(AssignRolePageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg= orgList.get(0);
		logger.info("*****************the text from first org is*****"+firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgName.toLowerCase())) {
			orgList.get(0).click();
			CommonMethods.delay(3);
			driver.findElement(AssignRolePageLocator.CONTAINER).click();
			orgFound = true;
		}
		return orgFound;
	}
	public static boolean selectOrgIfFound(String orgName,int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(orgName);
		List<WebElement> orgList = driver.findElements(AssignRolePageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg= orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****"+firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgName.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			CommonMethods.delay(3);
			driver.findElement(AssignRolePageLocator.CONTAINER).click();
			orgFound = true;
		}
		return orgFound;
	}

	public static void writeComment(String string) {
		driver.findElement(AssignRolePageLocator.COMMENT_TEXTBOX).sendKeys(string);
		CommonMethods.delay(5);

	}

	public static void clickDone() {
		driver.findElement(AssignRolePageLocator.DONE_BUTTON).click();
		RolesDirectoryViewAccessPage.setDriver(AssignRolePage.getDriver());
		CommonMethods.delay(6);

	}
	public static void clickAssign() {
		driver.findElement(AssignRolePageLocator.DONE_BUTTON).click();
		MyRolesPage.setDriver(AssignRolePage.getDriver());
		CommonMethods.delay(2);
		
	}
	public static String getCurrentTextInOrgPicker() {
		return driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).getText();
		
	}
	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page..eg. clickNavSignIn()
	// *****************************************************************************
	public static void goToFeedsPage() {
		CommonMethods.delay(3);
		driver.findElement(AssignRolePageLocator.FEED_NOTIFICATION_ICON).click();
		CommonMethods.delay(3);
		driver.findElement(AssignRolePageLocator.SHOWMORE_REQUEST_LINK).click();
		CommonMethods.delay(3);
		FeedsRequestPage.setDriver(AssignRolePage.getDriver());
		
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return AssignRolePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		AssignRolePage.driver = driver;
	}
	

}
