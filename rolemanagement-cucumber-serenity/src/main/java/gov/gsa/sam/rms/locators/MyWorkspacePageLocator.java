package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class MyWorkspacePageLocator {// contains locators for MyWorkspacePgOnly

	public static final String title = "beta.SAM.gov | Workspace";

	// the following locators are available without user action
	public static final By USER_SEARCH_BAR = By.id("search-users");
	public static final By USER_SEARCH_FOR_RA = By.id("fsd-user-search");

	// the following locators are avaialble after some user action
	public static final By AUTOCOMPLETE_RESULTS = By.id("sam-autocomplete-results-kv");

	public static final By ROLE_DEFINITION_LINK = By.id(
			"role-definition-link");

	public static final By BULK_UPDATELINK = By.id(
			"bulk-update-link");

	public static final By PENDING_REQUEST = By.id(
			"pending-role-request-link");

	public static final By USERDIRECTORY_LINK = By.id(
			"user-dir-link");

	public static final By WIDGET = By.tagName("rm-widget");

	public static final By GO_TO_MYPROFILE_LINK = By.className("fa-sm");

	public static final By NOTIFICATIONS = By.className("notification-num");
	// the following locators are avaialble after some user action
	public static final By FH_WIDGET = By.tagName("fh-widget");

	public static final By SHOWMORE_LINK = By.className("fa-chevron-circle-right ");

	public static final By ALL_USER_FSDFILTER = By.id("fsd-filter");

	public static final By SIGNIN_TAB = By.id("signin-button");

	public static final By GO_TO_SYSTEM_ACCOUNT = By.xpath(
			"//*[@id=\"workspace-widgets\"]/div[5]/workspace-administration/div/div[1]/workspace-widget/div/div[2]/div/a");

	private MyWorkspacePageLocator() {

	}
}
