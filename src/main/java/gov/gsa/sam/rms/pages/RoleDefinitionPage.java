package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.locators.RoleDefinitionPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class RoleDefinitionPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleDefinitionPage.class);

	
	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void clickCreateNewRole() {
		driver.findElement(RoleDefinitionPageLocator.CREATENEWROLE_BUTTON).click();
		CreateNewRolePage.setDriver(RoleDefinitionPage.getDriver());
		CommonMethods.delay(3);
		
	}
	public static String get2ndHeaderTitle() {
		String headertitle = driver.findElement(RoleDefinitionPageLocator.PAGE_HEADER).getText();
		logger.info(headertitle);
		return headertitle;
	}
	
	public static void clickObjectDefinitions() {
		driver.findElement(RoleDefinitionPageLocator.OBJECTDEFINITION_FILTER).click();
		CommonMethods.delay(2);
	}
	public static List<WebElement> getDomainFilterList() {
		return driver.findElements(RoleDefinitionPageLocator.DOMAINFILTER_LIST);
		
	}
	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page.
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter & private methods
	// *****************************************************************************
	
	private RoleDefinitionPage() {
	}

	public static WebDriver getDriver() {
		return RoleDefinitionPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleDefinitionPage.driver = driver;
	}
}
