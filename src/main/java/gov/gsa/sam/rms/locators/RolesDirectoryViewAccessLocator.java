package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class RolesDirectoryViewAccessLocator {
	public static final String TITLE = "beta.SAM.gov";
	// the following locators are available without user action
	public static final By ASSIGN_ROLE_BUTTON = By.className("primary");
	public static final By CURRENT_ROLES = By.className("link-hover");
	// the following locators are avaialble after some user action

	private RolesDirectoryViewAccessLocator() {

	}
}
