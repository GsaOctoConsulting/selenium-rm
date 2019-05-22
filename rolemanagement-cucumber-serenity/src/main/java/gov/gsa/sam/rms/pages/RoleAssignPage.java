package gov.gsa.sam.rms.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.HomePageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;

public class RoleAssignPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(RoleAssignPage.class);
	private RoleAssignPage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on 
	//loading of this Page
	// *****************************************************************************
	

	// *****************************************************************************
	// the following methods describe actions that are only available after some 
	//previous actions were taken on this SAME page..eg. clickNavSignIn()
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return RoleAssignPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleAssignPage.driver = driver;
	}

}