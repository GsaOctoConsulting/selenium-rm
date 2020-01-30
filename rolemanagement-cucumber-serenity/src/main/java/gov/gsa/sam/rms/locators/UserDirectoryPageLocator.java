package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class UserDirectoryPageLocator {
	public static final By USER_PICKER_BAR = By.id("userPicker");
	public static final By FIRST_RESULT = By.id("userPicker-resultItem-0");
	public static final By USER_VIEWACCESS_LINK = By.id("shah.raiaan+coSelenium@gsa.gov-action-0");
	public static final By ASSIGN_ROLE = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/results/div[3]/div[1]/sam-actions-dropdown/div/ul/li[1]/button/span[2]");
	public static final By VIEW_ACCESS = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/results/div[3]/div[1]/sam-actions-dropdown/div/ul/li[2]/button/span[2]");
	public static final By GET_LISTOFUSERS = By.className("user-role");
	public static final By GET_LISTOFUSERACTIONS = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/results/div[3]/div[1]/sam-actions-dropdown/div/ul");
	public static final By DOWNLOAD_ICON = By.className("menu-links-div");
	public static final By DOWNLOAD_CANCEL = By.className("usa-modal-content-cancel-btn");
	public static final By PDF_DOWNLOAD = By.className("fa-file-pdf-o");
	public static final By CSV_DOWNLOAD = By.className("fa-table");
	public static final By NONFED_FILTER = By.id("userTypeNon-Federal-Users");
	public static final By FED_FILTER = By.id("userTypeFederal-Users");
	public static final By CONTRACTING_OFFICER_FILTER = By.id("role-7");
	public static final By DOWNLOAD_DIALOGUE_BOX = By.className("usa-alert-info");
	public static final By USERNAME = By.className("h3");

	// role and
	// filters----------------------------------------------------------
	public static final By ASSISTANCE_ADMIN_FILTER = By.id("role-Assistance Administrator");
	public static final By AL_GRANDUSER_FILTER = By.id("role-5");
	public static final By DRA_FILTER = By.id("role-19");
	public static final By RA_FILTER_SAMPMOALLDOMAINS = By.id("role-SAM PMO Administrator All Domains");
	public static final By ASSISTANCEUSER_FILTER = By.id("role-Assistance User");
	public static final By FSDAGENT_FILTER = By.id("role-FSD Agent");
	public static final By FSDADMIN_FILTER = By.id("role-FSD Admin");
	public static final By ALERTSADMIN_FILTER = By.id("role-Alerts Admin");
	public static final By AGENCYADMIN_FILTER = By.id("role-Agency Admin");
	public static final By CO_ADMIN_FILTER = By.id("role-11");
	public static final By OMB_ADMIN_FILTER = By.id("role-OMB Administrator");
	public static final By DEPARTMENT_ADMIN_FILTER = By.id("role-Department Admin");
	public static final By OFFICE_ADMIN_FILTER = By.id("role-Office Admin");
	public static final By ADMIN_FILTER = By.id("role-Administrator");
	public static final By SUPERADMIN_FILTER = By.id("role-61");
	public static final By CONTRACTDATA_GRADUSER_FILTER = By.id("role-12");
	public static final By CONTRACTOPPORTUNITIES_GRADUSER_FILTER = By.id("role-10");
	public static final By CONTRACTING_SPECIALIST_FILTER = By.id("role-8");
	public static final By DOD_ADMIN_FILTER = By.id("role-22");
	public static final By GSA_SECURITYAPPROVER_FILTER = By.id("role-80");
	public static final By IAM_ADMIN_FILTER = By.id("role-15");
	public static final By NASA_ADMIN_FILTER = By.id("role-24");
	public static final By REPORTUSER_FILTER = By.id("role-21");
	public static final By SYSTEMACCOUNT_ADMIN_FILTER = By.id("role-13");
	public static final By SYSTEM_MANAGER_FILTER = By.id("role-79");
	public static final By OMB_ANALYST_FILTER = By.id("role-OMB Analyst");
	public static final By USER_I_ADMINSTER_FILTER = By.id("roleAdministrationUsers-whom-I-administer");
	public static final By USER_WITHNOROLES_FILTER = By.id("roleAdministrationUsers-who-don't-have-roles");

	// ----domain filters-----------------------
	public static final By DOMAIN_ADMIN_FILTER = By.id("domain-10");
	public static final By DOMAIN_AL_FILTER = By.id("domain-9");
	public static final By DOMAIN_CONTRACTDATA_FILTER = By.id("domain-1");
	public static final By DOMAIN_CONTRACTOPPORTUNITIES_FILTER = By.id("domain-2");
	public static final By DOMAIN_FH_FILTER = By.id("domain-5");
	public static final By DOMAIN_REPORTING_FILTER = By.id("domain-11");
	public static final By CLEARFILTER = By
			.xpath("//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[1]/sidebar/div[1]/a");
	public static final By PROVIDEFEEDBACK_LINK = By.className("usa-button-primary-alt");
	public static final By ORGPICKER_ADVANCED = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[1]/sidebar/div[1]/sam-agency-picker-v2/sam-label-wrapper/div/div[2]/a[2]");
	public static final By DEPARTMENT_SEARCHBOX = By.id("Department");
	public static final By DEPARTMENT_SUGGESTIONLIST = By.id("sam-autocomplete-results-kv");
	public static final By FIRST_ORG_RESULT = By.xpath("//*[@id=\"sam-autocomplete-results-kv\"]/li[1]/span");
	public static final By USER_INFO = By.className("p_All-4x");
	public static final By PAGINATION = By.className("page-button");
	public static final By TOTAL_NO_OFRECORDS = By.tagName("list-results-message");
	public static final By SEARCH_ORGPICKER = By.linkText("\r\n" + "        Advanced\r\n" + "        ");
	public static final By ORG_PICKER = By.id("federalHierarchy");
	public static final By ID = By.className("description");
	public static final By ORG_PICKER_FILTER_WHERE_USERS_HAVE_ROLES = By.id("orgTypeOrganizations-where-users-have-roles");
	public static final By NO_RESULTS_MESSAGE = By.xpath("//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/p");

	private UserDirectoryPageLocator() {

	}
}
