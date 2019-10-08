package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.locators.RoleDefinitionPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the role definition page
 * the link for which is found on the Workspace 
 * page for users with specific role, eg SAMPMO ADMNISTRATOR ALL DOMAINS
 *
 */
public class RoleDefinitionPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleDefinitionPage.class);

	private RoleDefinitionPage() {
	}

	public static WebDriver getDriver() {
		return RoleDefinitionPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleDefinitionPage.driver = driver;
	}

	public static void clickCreateNewRole() {
		driver.findElement(RoleDefinitionPageLocator.CREATENEWROLE_BUTTON).click();
		CreateNewRolePage.setDriver(RoleDefinitionPage.getDriver());
		LaunchBrowserUtil.delay(3);

	}

	public static String get2ndHeaderTitle() {
		String headertitle = driver.findElement(RoleDefinitionPageLocator.PAGE_HEADER).getText();
		logger.info(headertitle);
		return headertitle;
	}

	public static void clickObjectDefinitions() {
		driver.findElement(RoleDefinitionPageLocator.OBJECTDEFINITION_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static List<WebElement> getDomainFilterList() {
		return driver.findElements(RoleDefinitionPageLocator.DOMAINFILTER_LIST);

	}

}
