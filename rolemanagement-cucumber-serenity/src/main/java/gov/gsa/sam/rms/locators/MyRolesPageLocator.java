package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class MyRolesPageLocator {
	public static final By REQUESTROLE_BUTTON = By.linkText("Request Role");
	public static final By FEED_NOTIFICATION_ICON = By.id("headerIconRequests");

	public static final By SHOWMORE_REQUEST_LINK = By.className("fa-chevron-circle-right");
	public static final By ROLEASSIGN_LINK = By.linkText("Role Assigned");
	public static final By PENDINGREQUEST_LINK = By.linkText("1 pending role request");
	public static final By PENDING = By.linkText("Pending");
	public static final By WEBSITE_LINK = By.linkText("websites");
	public static final By BEGINNOW_LINK = By.linkText("Begin Now");
	public static final By NO_ROLE_TEXT = By.className("result-box");
	public static final By ASSIGN_BUTTON = By.className("role-buttons");
}