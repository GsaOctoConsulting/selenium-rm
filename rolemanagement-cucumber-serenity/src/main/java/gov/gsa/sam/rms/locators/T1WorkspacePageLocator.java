package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class T1WorkspacePageLocator {

	public static final By USER_SEARCH_BAR = By.id("userPicker");
	public static final By GO_TO_MYPROFILE_LINK = By.id("profile-details-link");
	public static final By GO_TO_SYSTEM_ACCOUNT = By.id("system-accounts-landing-link");
	public static final By ROLE_DEFINITION_BUTTON = By.id("role-definitions-button");
	public static final By BULK_UPDATE_BUTTON = By.id("bulk-update-button");
	public static final By PENDING_ROLE_REQUEST_LINK = By.id("workspace-pending-requests-link");
	public static final By USER_DIRECTORY_WIDGET = By.tagName("workspace-rm-widget");
	public static final By ASSISTANCE_LISTING_WIDGET = By.tagName("sam-workspace-assistance-listing-widget");
	public static final By USER_DIRECTORY_LINK = By.id("user-directory-link");
	public static final By COLLECTIVE_BARGAINING_WIDGET = By.tagName("sam-workspace-cba-wage-determination-widget");
	public static final By FSD_FILTER = By.id("userSearchFilter");
	public static final By SYSTEMACCOUNT_WIDGET = By.tagName("workspace-sa-widget");
	public static final By PENDINGAPPROVAL_SYSTEMACCOUNT_LINK = By.id("system-accounts-pending-approval-link");
	public static final By PENDINGREVIEW_SYSTEMACCOUNT_LINK =By.id("system-accounts-pending-review-link");
	public static final By APPROVED_SYSTEMACCOUNT_LINK = By.id("system-accounts-approved-link");
	public static final By DEACTIVATED_SYSTEMACCOUNT_LINK = By.id("system-accounts-deactivated-link");
	public static final By CONTENT_MANAGEMENT_WIDGET = By.tagName("workspace-cms");
	public static final By CONTRACT_OPPORTUNITIES_WIDGET = By.tagName("sam-workspace-opp-widget");
	public static final By FH_WIDGET = By.tagName("workspace-fh-widget");
	public static final By UPLOAD_AAC_WIDGET = By.tagName("workspace-upload-aac-widget");
	public static final By ROLE_SELECTOR = By.id("role-select");
	public static final By DRAFT = By.id("system-accounts-pending-review-link");
	public static final By PENDING = By.id("system-accounts-pending-approval-link");
	public static final By SIGNIN_TAB = By.id("signin-button");
	public static final By AUTOCOMPLETE_RESULTS = By.xpath("//li[starts-with(@id, 'userPicker-resultItem')]");
	public static final By VERIFY_BUSINESS_NEEDCHECKBOX = By.id("business-need-verify");
	public static final By REQUEST_ROLE_BUTTON =By.id("role-request-button");
	public static final By SAN_ERROR_MESSAGE = By.xpath("//span[contains(text(),\" An account with this name already exists \")]");
	public static final By EXCLUSION_WIDGET = By.tagName("workspace-entity-exclusions-widget");
	public static final By EXCLUSION_PROCEEDINGS_PENDING_BUBBLE = By.id("proceeding-pending-link");
	public static final By EXCLUSION_PROCEEDINGS_COMPLETED_BUBBLE = By.id("proceeding-completed-link");
	public static final By EXCLUSION_PROHIBITION_RESTRICTION_BUBBLE = By.id("prohibition-restriction-link");
	public static final By EXCLUSION_VOLUNTARY_EXCLUSION_BUBBLE = By.id("voluntary-exclusions-link");
	public static final By EXCLUSION_MY_ACTIVE_BUBBLE = By.id("my-active-link");
	public static final By EXCLUSION_REVIEWNEEDED_BUBBLE = By.id("review-needed-link");
	public static final By EXCLUSION_LINK = By.id("entity-exclusions-landing-link");
	public static final By NEW_EXCLUSION_LINK = By.id("new-exclusions-button");
	public static final By ENTITY_REGISTRATION_WIDGET = By.tagName("workspace-fsd-entity-registration-widget");
	public static final By ENTITY_USERTYPE_FILTER = By.id("entityTypeFilter");
	public static final By ENTITY_MANAGEMENT_WIDGET = By.tagName("workspace-entity-management-widget");
	public static final By ENTITY_MANAGEMENT_LANDING_LINK = By.id("entity-management-landing-link");
	public static final By REGISTER_ENTITY_BUTTON = By.id("register-entity-button");
	public static final By GET_ENTITYID_BUTTON = By.id("get-entity-id-button");
	public static final By ENTITY_MANAGEMENT_ACTIVELINK = By.id("entity-registration-active-link");
	public static final By ENTITY_MANAGEMENT_DRAFTLINK = By.id("entity-registration-draft-link");
	public static final By ENTITY_MANAGEMENT_WORKINPROGRESSLINK = By.id("entity-registration-inProgress-link");
	public static final By ENTITY_MANAGEMENT_SUBMITTEDLINK = By.id("entity-registration-submitted-link");
	public static final By ENTITY_MANAGEMENT_UNIQUEID_ACTIVELINK = By.id("unique-entity-id-active-link");
	public static final By ENTITY_MANAGEMENT_UNIQUEID_DRAFTLINK = By.id("unique-entity-id-draft-link");
	public static final By ENTITY_MANAGEMENT_UNIQUEID_WORKINPROGRESSLINK =By.id("unique-entity-id-inProgress-link");
	public static final By ENTITY_MANAGEMENT_UNIQUEID_SUBMITTEDLINK = By.id("unique-entity-id-submitted-link");
	public static final By ENTITY_COMPLIANCE = By.tagName("workspace-compliance-reporting-widget");
	public static final By ENTITY_COMPLIANCE_BIOPREFERREDREQUIRED_LINK = By.id("bioPreferred-required-link");
	public static final By ENTITY_COMPLIANCE_BIOPREFERREDSUBMITTED_LINK = By.id("bioPreferred-submitted-link");
	public static final By ENTITY_COMPLIANCE_SERVICECONTRACTREQUIRED_LINK = By.id("serviceContract-required-link");
	public static final By ENTITY_COMPLIANCE_SERVICECONTRACTSUBMITTED_LINK = By.id("serviceContract-submitted-link");
	public static final By ADD_NEW_ROLE_LINK = By.id("role-request-link");
	
	

}
