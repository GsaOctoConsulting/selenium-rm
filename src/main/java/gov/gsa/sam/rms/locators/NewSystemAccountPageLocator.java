package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class NewSystemAccountPageLocator {

	public static final By TEXTBOX_SYSTEM_ACCOUNT_NAME = By.id("system-account-name");
	public static final By TEXTBOX_SYSTEM_INTERFACING_NAME = By.id("interfacing-system-version");
	public static final By TEXTBOX_SYSTEM_DESCRIPTION = By.id("system-description-and-function");
	public static final By NEXT_TO_DESCRIPTION_BUTTON = By.id("button-next");
	public static final By AGENCYPICKER_ORG_INFO = By.id("cws-agency-pickerpicker");
	public static final By ORG_SELECTOR = By.id("sam-autocomplete-results-kv");
	public static final By CONTAINER = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[1]");
	public static final By SYSTEM_MANAGER_PICKER = By.id("system-managers-ac-textarea");
	public static final By SYSTEMMANAGER_ID_SELECTOR = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[1]/div/edit/div/div/div/div/organization/div/div[3]/sam-autocomplete-multiselect/div/sam-label-wrapper/div/div[2]/div[2]/ul");
	public static final By SYSTEM_ADMIN_PICKER = By.id("system-admins-ac-textarea");
	public static final By SYSTEMADMIN_ID_SELECTOR = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[1]/div/edit/div/div/div/div/organization/div/div[2]/sam-autocomplete-multiselect/div/sam-label-wrapper/div/div[2]/div[2]/ul");
	public static final By CO_READ_PUBLIC = By.id("co-read-public");
	public static final By CO_WRITE_PUBLIC = By.id("co-write-public");
	public static final String FIPS_LOW = "Low";
	public static final By FIPS_CATEGORY_TEXBOX = By.id("NaN");
	public static final By FIPS_CATEGORY_LIST = By.id("sam-autocomplete-results-kv");
	public static final String REST_APIS = "Rest APIs";
	public static final By IP_ADDRESS_TEXTBOX = By.id("ip-address");
	public static final By TEXTAREA_TYPE_OF_CONNECTION = By.id("type-of-connection-ac-textarea");
	public static final By TYPE_OF_CONNECTION_LIST = By
			.xpath("//*[@id=\"type-of-connection\"]/div/sam-label-wrapper/div/div[2]/div[2]/ul");
	public static final By PHYSICAL_LOCATION = By.id("physical-location");
	public static final By SECURITY_OFFICIAL_NAME = By.id("security-official-name");
	public static final By SECURITY_OFFICIAL_EMAIL = By.id("security-official-email");
	public static final By CERTIFY_CORRECT_INFO = By.id("authorization-confirmation");
	public static final By REVIEW_BUTTON = By.id("button-review");
	public static final By SUBMIT_BUTTON = By.id("button-submit");
	public static final By WORKSPACE_LINK = By.linkText("Workspace");
	public static final By COMMENT_TEXTBOX = By.id("comment-component-input");
	public static final By EDIT_TAB = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/div/a[1]");
	public static final String COMMENT_ERROR = "Please enter at least 1 character before submitting a comment.";
	
}
