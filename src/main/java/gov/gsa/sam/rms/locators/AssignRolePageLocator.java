package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class AssignRolePageLocator {// contains locators for AssignRolePageOnly
	public static final String title = "beta.SAM.gov | Assign Role";

	// the following locators are available without user action
	public static final By ROLE_SELECTOR = By.id("role-selector");

	public static final By DOMAINDROPDOWN_ICON = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/form/div[1]/div/div[3]/sam-autocomplete-multiselect/div/sam-label-wrapper/div/div/div[2]/div/div/span");

	public static final By DOMAIN_SELECTOR = By.className("category-item");

	public static final By COMMENT_TEXTBOX = By.className("sam-text-area");

	public static final By DONE_BUTTON = By.id("done-button");

	public static final By ERROR_ALERT = By.className("usa-alert-error");

	public static final By UNSELECT_DEFAULTORG_ICON = By.className("fa-times-circle");

	public static final By ORGPICKER_TEXTAREA = By.id("access-org-pickerpicker-ac-textarea");

	public static final By ORG_SELECTOR = By.className("category-item");

	public static final By CONTAINER = By
			.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[3]");

	public static final By FEED_NOTIFICATION_ICON = By.id("headerIconRequests");

	public static final By SHOWMORE_REQUEST_LINK = By.className("fa-chevron-circle-right");

	// the following locators are avaialble after some user action

	private AssignRolePageLocator() {

	}
}