package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class AssignRolePageLocator {// contains locators for AssignRolePageOnly
	public static final String title = "beta.SAM.gov | Assign Role";

	// the following locators are available without user action
	public static final By ROLE_SELECTOR = By.id("role-selector");

	public static final By DOMAINDROPDOWN_ICON = By.id(
			"domain-ac-textarea");

	public static final By DOMAIN_SELECTOR = By.className("category-item");

	public static final By COMMENT_TEXTBOX = By.className("sam-text-area");

	public static final By DONE_BUTTON = By.id("done-button");

	public static final By ERROR_ALERT = By.tagName("sam-alert");

	public static final By UNSELECT_DEFAULTORG_ICON = By.className("fa-times-circle");

	public static final By ORGPICKER_TEXTAREA = By.id("federalHierarchy");

	public static final By ORG_SELECTOR = By.id("federalHierarchy-listbox");

	public static final By CONTAINER = By
			.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[3]");

	public static final By FEED_NOTIFICATION_ICON = By.id("headerIconRequests");

	public static final By SHOWMORE_REQUEST_LINK = By.className("fa-chevron-circle-right");

	public static final By ENTITYPICKER_TEXTAREA = By.id("grant-access-entity-picker-ac-textarea");

	public static final By ENTITY_SELECTOR = By.className("category-item");

	// the following locators are avaialble after some user action

	private AssignRolePageLocator() {

	}
}