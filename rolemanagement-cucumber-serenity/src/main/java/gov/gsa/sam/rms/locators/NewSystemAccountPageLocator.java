package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class NewSystemAccountPageLocator {

	public static final By TEXTBOX_SYSTEM_ACCOUNT_NAME = By.id("system-account-name-required");
	public static final By TEXTBOX_SYSTEM_INTERFACING_NAME = By.id("interfacing-system-version-required");
	public static final By TEXTBOX_SYSTEM_DESCRIPTION = By.id("system-description-and-function-required");
	public static final By NEXT_TO_DESCRIPTION_BUTTON = By.id("button-next");
	public static final By AGENCYPICKER_ORG_INFO = By.id("federalHierarchy");
	public static final By ORG_SELECTOR = By.id("federalHierarchy-listbox");
	public static final By CONTAINER = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[1]");
	public static final By SYSTEM_MANAGER_PICKER = By.id("system-managers-optional-ac-textarea");
	public static final By SYSTEMMANAGER_ID_SELECTOR = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[1]/div/edit/div/div/div/div/organization/div/div[3]/sam-autocomplete-multiselect/div/sam-label-wrapper/div/div[2]/div[2]/ul");
	public static final By SYSTEM_ADMIN_PICKER = By.id("system-admins-optional-ac-textarea");
	public static final By SYSTEMADMIN_ID_SELECTOR = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[1]/div/edit/div/div/div/div/organization/div/div[2]/div/sam-autocomplete-multiselect/div/sam-label-wrapper/div/div[2]/div[2]/ul/li/ul/li");
	public static final By CO_READ_PUBLIC = By.id("co-read-public");
	public static final By CO_WRITE_PUBLIC = By.id("co-write-public");
	public static final String FIPS_LOW = "Low";
	public static final By FIPS_CATEGORY_TEXBOX = By.id("fips199-categorization");
	public static final By FIPS_CATEGORY_LIST = By.id("sam-autocomplete-results-kv");
	public static final String REST_APIS = "Rest APIs";
	public static final By IP_ADDRESS_TEXTBOX = By.id("ip-input");
	public static final By TEXTAREA_TYPE_OF_CONNECTION = By.id("type-of-connection-required-ac-textarea");
	public static final By TYPE_OF_CONNECTION_LIST = By
			.xpath("//*[@id=\"type-of-connection\"]/div/sam-label-wrapper/div/div[2]/div[2]/ul");
	public static final By PHYSICAL_LOCATION = By.id("physical-location-required");
	public static final By SECURITY_OFFICIAL_NAME = By.cssSelector("input[formcontrolname='securityOfficialName']");
	public static final By SECURITY_OFFICIAL_EMAIL = By.cssSelector("input[formcontrolname='securityOfficialEmail']");
	public static final By CERTIFY_CORRECT_INFO = By.id("authorization-confirmation");
	public static final By REVIEW_BUTTON = By.id("button-review");
	public static final By SUBMIT_BUTTON = By.id("button-submit");
	public static final By WORKSPACE_LINK = By.linkText("Workspace");
	public static final By COMMENT_TEXTBOX = By.id("comment-component-input");
	public static final By EDIT_TAB = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/div/a[1]");
	public static final String COMMENT_ERROR = "Please enter at least 1 character before submitting a comment.";
	public static final org.openqa.selenium.By CO_READ_SENSITIVE = By.xpath("//label[starts-with(@for, 'co-read-sensitive')]");
	public static final org.openqa.selenium.By CO_WRITE_SENSITIVE = By.xpath("//label[starts-with(@for, 'co-write-sensitive')]");
	
	
	public static final By CD_READ_PUBLIC = By.id("cd-read");
	public static final By CD_WRITE = By.id("cd-write");
	public static final By CD_READ_DOD_DATA = By.id("cd-read-dod-data");
	public static final By CD_WRITE_DOD_DATA = By.id("cd-write-dod-data");
	public static final By EI_READ_PUBLIC = By.id("ei-read-public");
	public static final By EI_READ_FOUO = By.id("ei-read-fouo");
	public static final By EI_READ_SENSITIVE = By.id("ei-read-sensitive");
	public static final By FH_READ_ONLY = By.id("fh-public");
	public static final By FH_READ_FOUO = By.id("fh-official");
	public static final By AL_READ_PUBLIC = By.id("al-public");
	public static final By WD_READ_PUBLIC = By.id("wd-public");
	public static final By RD_READ_PUBLIC = By.id("rd-public");
	public static final By BROWSE_UPLOAD = By.id("fileLinkId");
	public static final By DELETE_UPLOAD = By.xpath("//button[@class= 'clear-button']");
		public static final By CONFIRM_DELETE = By.xpath("//button[@class= 'sam-ui button secondary']");
		public static final By UPDATED_DATE = By.id("date0");
		public static final By REORDER_UPLOAD = By.id("moveDown0");

}
