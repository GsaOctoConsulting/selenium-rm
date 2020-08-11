package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class RequestRolePageLocator {
	public static final By COMMENT_TEXTBOX = By.className("sam-text-area");
	public static final By SUPERVISOR_NAME_TEXTBOX = By.id("supervisor-name");
	public static final By SUPERVISOR_EMAIL_TEXTBOX = By.id("supervisor-email");
	public static final By ROLE_SELECTOR = By.id("role-select");
	public static final By ORGPICKER_TEXTAREA = By.id("federalHierarchy");
	public static final By ORG_SELECTOR = By.xpath(".//li[starts-with(@id, 'federalHierarchy-resultItem')]");
	public static final By CONTAINER = By
			.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[3]");
	public static final By DOMAINDROPDOWN_ICON = By.className("fa-chevron-down");
	public static final By DOMAIN_SELECTOR = By.xpath("//li[(@role, 'option')]");
	public static final By SUBMIT_BUTTON = By.id("done-button");
	public static final By PENDING_REQUESTS = By.linkText("1 pending role request");
	public static final By PENDING = By.linkText("Pending");
	public static final By ERRORMESSAGE_SUPERVISOR_NAME = By.id("superName-topErrorLink");
	public static final By ERRORMESSAGE_SUPERVISOR_EMAIL = By.id("superEmail-topErrorLink");
	public static final By ERRORMESSAGE_ORG = By.id("org-topErrorLink");
	public static final By ERRORMESSAGE_ROLE = By.id("role-topErrorLink");
	public static final By ERRORMESSAGE_DOMAIN = By.id("domains-topErrorLink");
	public static final By ERRORMESSAGE_ADDITIONALDETAILS = By.id("comment-topErrorLink");
	public static final By ENTITYPICKER_TEXTAREA = By.id("entityPicker");
	public static final By ENTITY_SELECTOR = By.xpath(".//li[starts-with(@id, 'entityPicker-resultItem')]");
}
