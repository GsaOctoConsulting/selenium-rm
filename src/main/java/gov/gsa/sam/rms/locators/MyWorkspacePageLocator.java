package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class MyWorkspacePageLocator {// contains locators for MyWorkspacePgOnly

	public static final String title = "beta.SAM.gov | Workspace";

	// the following locators are available without user action
	public static final By USER_SEARCH_BAR = By.id("search-users");
	public static final By USER_SEARCH_FOR_RA = By.id("fsd-user-search");

	// the following locators are avaialble after some user action
	public static final By AUTOCOMPLETE_RESULTS = By.id("sam-autocomplete-results-kv");

	public static final By WIDGET_SELECTIONLIST = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[3]/div/workspace-administration/div/div/workspace-widget/div/rm-widget/div/div/a[3]");

	public static final By BULK_UPDATELINK = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[3]/div/workspace-administration/div/div/workspace-widget/div/rm-widget/div/div/a[2]");

	public static final By PENDING_REQUEST = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[3]/div/workspace-administration/div/div/workspace-widget/div/rm-widget/div/div/a[1]");

	public static final By USERDIRECTORY_LINK = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[3]/div/workspace-administration/div/div/workspace-widget/div/div[2]/div/a");

	public static final By WIDGET = By.tagName("rm-widget");

	public static final By GO_TO_MYPROFILE_LINK = By.className("fa-sm");

	public static final By NOTIFICATIONS = By.className("notification-num");
	// the following locators are avaialble after some user action
	public static final By FH_WIDGET = By.tagName("fh-widget");

	public static final By SHOWMORE_LINK = By.className("fa-chevron-circle-right ");

	public static final By ALL_USER_FSDFILTER = By.id("fsd-filter");

	public static final By SIGNIN_TAB = By.id("signin-button");

	public static final By GO_TO_SYSTEM_ACCOUNT = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[3]/div/workspace-administration/div/div[1]/workspace-widget/div/div[2]/div/a");

	private MyWorkspacePageLocator() {

	}
}
