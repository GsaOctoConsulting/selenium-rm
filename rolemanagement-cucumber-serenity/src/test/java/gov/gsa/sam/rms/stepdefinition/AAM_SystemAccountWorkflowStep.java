package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class AAM_SystemAccountWorkflowStep {

	private static Logger logger = LoggerFactory.getLogger(SystemAccountStep.class);
	public static String expectedSearchResult = "You currently do not have any system accounts or applications";

	public static final String uniqSan1 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan2 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan3 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan4 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan5 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan6 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan7 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan8 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan9 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan10 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan11 = NewSystemAccountPage.uniqueSAN();

	// IAE PMO Administrator should be able to approve system account request when
	// System Account Manager submits system account
	@Given("^_A System Account Manager logs in$")
	public void _a_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_A SAM navigates to system account directory page$")
	public void _a_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_A SAM enters all the system information$")
	public void _a_SAM_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan1);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_A SAM enters all the organization info$")
	public void _a_SAM_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_A SAM enters non-public permissions info$")
	public void _a_SAM_enters_non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_A SAM enters security info$")
	public void _a_SAM_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_A SAM enters authorization info$")
	public void _a_SAM_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_A SAM accepts terms and conditions and submits account$")
	public void __A_SAM_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.driver.get().quit();
	}

	@When("^_A System Account Administrator logs in$")
	public void _a_System_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A SAA navigates to system account directory page$")
	public void _a_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@Then("^_A SAA searches for Pending Review request by SAM and approve it$")
	public void _a_SAA_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_A IAE PMO Administrator logs in$")
	public void _a_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A IAE_PMOA navigates to system account directory$")
	public void _a_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_A IAE_PMOA searches for Pending Permissions Approval request by SAA and approve it$")
	public void _a_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_by_SAA_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_A GSA Security Approver logs in$")
	public void _a_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A GSA SA navigates to system account directory page$")
	public void _a_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_A GSA SA should be able to approve the system account$")
	public void _a_GSA_SA_should_be_able_to_approve_the_system_account() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_A System Account Manager logs in again$")
	public void _a_System_Account_Manager_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_A SAM validate system account is in published status$")
	public void _a_SAM_validate_system_account_is_in_published_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// IAE PMO Administrator should be able to reject system account request when
	// System Account Manager submits system account
	@Given("^_B System Account Manager logs in$")
	public void _b_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_B SAM navigates to system account directory page$")
	public void _b_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_B SAM enters all the system information$")
	public void _b_SAM_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan2);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_B SAM enters all the organization info$")
	public void _b_SAM_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_B SAM enters non-public permissions info$")
	public void _b_SAM_enters_non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_B SAM enters security info$")
	public void _b_SAM_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_B SAM enters authorization info$")
	public void _b_SAM_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_B SAM accepts terms and conditions and submits account$")
	public void _b_SAM_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_B System Account Administrator logs in$")
	public void _b_System_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B SAA navigates to system account directory page$")
	public void _b_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@Then("^_B SAA searches for Pending Review request by SAM and approve it$")
	public void _b_SAA_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan2);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_B IAE PMO Administrator logs in$")
	public void _b_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.MA_USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B IAE_PMOA navigates to system account directory$")
	public void _b_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_B IAE_PMOA searches for Pending Permissions Approval request by SAA and reject it$")
	public void _b_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_by_SAA_and_reject_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan2);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_B GSA Security Approver logs in$")
	public void _b_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B GSA SA navigates to system account directory page$")
	public void _b_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_B GSA SA should not receive request$")
	public void _b_GSA_SA_should_not_receive_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan2);
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_B System Account Manager logs in again$")
	public void _b_System_Account_Manager_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_B SAM validate system account is in draft status$")
	public void _b_SAM_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan2);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// IAE PMO Administrator should be able to approve system account request when
	// System Account Administrator submits system account
	@Given("^_C System Account Administrator logs in$")
	public void _c_System_Account_Administrator_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_C SAA navigates to system account directory page$")
	public void _c_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_C SAA enters all the system information$")
	public void _c_SAA_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan3);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_C SAA enters all the organization info$")
	public void _c_SAA_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_C SAA enters non-public permissions info$")
	public void _c_SAA_enters_non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_C SAA enters security info$")
	public void _c_SAA_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_C SAA enters authorization info$")
	public void _c_SAA_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_C SAA accepts terms and conditions and submits account$")
	public void _c_SAA_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_C IAE PMO Administrator logs in$")
	public void _c_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_C IAE_PMOA navigates to system account directory$")
	public void _c_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_C IAE_PMOA searches for Pending Permissions Approval request by SAA and approve it$")
	public void _c_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_by_SAA_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan3);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_C GSA Security Approver logs in$")
	public void _c_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_C GSA SA navigates to system account directory page$")
	public void _c_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_C GSA SA should be able to reject the system account$")
	public void _c_GSA_SA_should_be_able_to_reject_the_system_account() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan3);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_C System Account Administrator logs in again$")
	public void _c_System_Account_Administrator_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_C SAA validate system account is in draft status$")
	public void _c_SAA_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan3);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// IAE PMO Administrator should be able to reject system account request when
	// System Account Administrator submits system account
	@Given("^_D System Account Administrator logs in$")
	public void _d_System_Account_Administrator_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_D SAA navigates to system account directory page$")
	public void _d_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_D SAA enters all the system information$")
	public void _d_SAA_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan4);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_D SAA enters all the organization info$")
	public void _d_SAA_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_D SAA enters non-public permissions info$")
	public void _d_SAA_enters_non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_D SAA enters security info$")
	public void _d_SAA_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_D SAA enters authorization info$")
	public void _d_SAA_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_D SAA accepts terms and conditions and submits account$")
	public void _d_SAA_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_D IAE PMO Administrator logs in$")
	public void _d_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_D IAE_PMOA navigates to system account directory$")
	public void _d_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_D IAE_PMOA searches for Pending Permissions Approval request by SAA and reject it$")
	public void _d_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_by_SAA_and_reject_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan4);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_D GSA Security Approver logs in$")
	public void _d_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_D GSA SA navigates to system account directory page$")
	public void _d_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_D GSA SA should not receive request$")
	public void _d_GSA_SA_should_not_receive_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan4);
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_D System Account Administrator logs in again$")
	public void _d_System_Account_Administrator_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_D SAA validate system account is in draft status$")
	public void _d_SAA_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan4);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// IAE PMO Administrator should be able to approve system account request when
	// Non-Fed User submits system account
	@Given("^_E Non-Fed User logs in$")
	public void _e_Non_Fed_User_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Given("^_E NFU navigates to system account directory page$")
	public void _e_NFU_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_E NFU enters all the system information$")
	public void _e_NFU_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan5);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_E NFU enters all the organization info$")
	public void _e_NFU_enters_all_the_organization_info() throws Exception {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_E NFU enters non-public permissions info$")
	public void _e_NFU_enters_non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_E NFU enters security info$")
	public void _e_NFU_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_E NFU enters authorization info$")
	public void _e_NFU_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_E NFU accepts terms and conditions and submits account$")
	public void _e_NFU_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFedModified(
				Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_E IAE PMO Administrator logs in$")
	public void _e_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_E IAE_PMOA navigates to system account directory$")
	public void _e_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_E IAE_PMOA searches for Pending Permissions Approval request by NFU and approve it$")
	public void _e_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_by_NFU_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan5);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_E GSA Security Approver logs in$")
	public void _e_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_E GSA SA navigates to system account directory page$")
	public void _e_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_E GSA SA should be able to approve the system account$")
	public void _e_GSA_SA_should_be_able_to_approve_the_system_account() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan5);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_E Non-Fed User logs in again$")
	public void _e_Non_Fed_User_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_E NFU validate system account is in published status$")
	public void _e_NFU_validate_system_account_is_in_published_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan5);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// IAE PMO Administrator should be able to reject system account request when
	// Non-Fed User submits system account
	@Given("^_F Non-Fed User logs in$")
	public void _f_Non_Fed_User_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Given("^_F NFU navigates to system account directory page$")
	public void _f_NFU_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_F NFU enters all the system information$")
	public void _f_NFU_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan6);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_F NFU enters all the organization info$")
	public void _f_NFU_enters_all_the_organization_info() throws Exception {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_F NFU enters non-public permissions info$")
	public void _f_NFU_enters_non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_F NFU enters security info$")
	public void _f_NFU_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_F NFU enters authorization info$")
	public void _f_NFU_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_F NFU accepts terms and conditions and submits account$")
	public void _f_NFU_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFedModified(
				Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_F IAE PMO Administrator logs in$")
	public void _f_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_F IAE_PMOA navigates to system account directory$")
	public void _f_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_F IAE_PMOA searches for Pending Permissions Approval request by NFU and reject it$")
	public void _f_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_by_NFU_and_reject_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan6);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_F GSA Security Approver logs in$")
	public void _f_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.MA_USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_F GSA SA navigates to system account directory page$")
	public void _f_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_F GSA SA should not receive request$")
	public void _f_GSA_SA_should_not_receive_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan6);
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_F Non-Fed User logs in again$")
	public void _f_Non_Fed_User_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_F NFU validate system account is in draft status$")
	public void _f_NFU_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan6);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// IAE PMO Admin shouldn't receive request when System Account Admin reject the
	// request submitted by System Account Manager
	@Given("^_P System Account Manager logs in$")
	public void _p_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_P SAM navigates to system account directory page$")
	public void _p_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_P SAM enters all the system information$")
	public void _p_SAM_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan7);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_P SAM enters all the organization info$")
	public void _p_SAM_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_P SAM enters non-public permissions info$")
	public void _p_SAM_enters_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_P SAM enters security info$")
	public void _p_SAM_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_P SAM enters authorization info$")
	public void _p_SAM_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_P SAM accepts terms and conditions and submits account$")
	public void _p_SAM_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_P System Account Administrator logs in$")
	public void _p_System_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_P SAA navigates to system account directory page$")
	public void _p_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@Then("^_P SAA searches for Pending Review request by SAM and reject it$")
	public void _p_SAA_searches_for_Pending_Review_request_by_SAM_and_reject_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan7);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_P IAE PMO Administrator logs in$")
	public void _p_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_P IAE_PMOA navigates to system account directory$")
	public void _p_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_P IAE_PMOA searches for Pending Permissions Approval request$")
	public void _p_IAE_PMOA_searches_for_Pending_Permissions_Approval_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan7);
	}

	@Then("^_P IAE_PMOA should not receive the request rejected by SAA$")
	public void _p_IAE_PMOA_should_not_receive_the_request_rejected_by_SAA() throws Exception {
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_P System Account Manager logs in again$")
	public void _p_System_Account_Manager_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_P SAM validate system account is in draft status$")
	public void _p_SAM_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan7);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// _Q IAE PMO Admin should not receive request when System Account Manager
	// submits system account including public permissions info only
	@Given("^_Q System Account Manager logs in$")
	public void _q_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_Q SAM navigates to system account directory page$")
	public void _q_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_Q SAM enters all the system information$")
	public void _q_SAM_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan8);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_Q SAM enters all the organization info$")
	public void _q_SAM_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_Q SAM enters only public permissions info$")
	public void _q_SAM_enters_only_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_Q SAM enters security info$")
	public void _q_SAM_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_Q SAM enters authorization info$")
	public void _q_SAM_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_Q SAM accepts terms and conditions and submits account$")
	public void _q_SAM_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectPublicPermissionsTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_Q System Account Administrator logs in$")
	public void _q_System_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_Q SAA navigates to system account directory page$")
	public void _q_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@Then("^_Q SAA searches for Pending Review request by SAM and approve it$")
	public void _q_SAA_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan8);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_Q IAE PMO Administrator logs in$")
	public void _q_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_Q IAE_PMOA navigates to system account directory$")
	public void _q_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@When("^_Q IAE_PMOA should not receive the request$")
	public void _q_IAE_PMOA_should_not_receive_the_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan8);
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_Q GSA Security Approver logs in$")
	public void _q_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_Q GSA SA navigates to system account directory$")
	public void _q_GSA_SA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_Q GSA SA receives request directly and approve it$")
	public void _q_GSA_SA_receives_request_directly() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan8);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_Q System Account Manager logs in again$")
	public void _q_System_Account_Manager_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_Q SAM validate system account is in published status$")
	public void _q_SAM_validate_system_account_is_in_published_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan8);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// _R IAE PMO Admin should not receive request when System Account Admin submits
	// system account including public permissions info only
	@Given("^_R System Account Admin logs in$")
	public void _r_System_Account_Admin_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_R SAA navigates to system account directory page$")
	public void _r_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_R SAA enters all the system information$")
	public void _r_SAA_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan9);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_R SAA enters all the organization info$")
	public void _r_SAA_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_R SAA enters only public permissions info$")
	public void _r_SAA_enters_only_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_R SAA enters security info$")
	public void _r_SAA_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_R SAA enters authorization info$")
	public void _r_SAA_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_R SAA accepts terms and conditions and submits account$")
	public void _r_SAA_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectPublicPermissionsTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_R IAE PMO Administrator logs in$")
	public void _r_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_R IAE_PMOA navigates to system account directory$")
	public void _r_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@When("^_R IAE_PMOA should not receive the request$")
	public void _r_IAE_PMOA_should_not_receive_the_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan9);
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_R GSA Security Approver logs in$")
	public void _r_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_R GSA SA navigates to system account directory$")
	public void _r_GSA_SA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_R GSA SA receives request directly and reject it$")
	public void _r_GSA_SA_receives_request_directly() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan9);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_R System Account Administrator logs in again$")
	public void _r_System_Account_Administrator_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_R SAA validate system account is in draft status$")
	public void _r_SAA_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan9);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// _S IAE PMO Admin should not receive request when Non-Fed user submits system
	// account including public permissions info only
	@Given("^_S Non-Fed user logs in$")
	public void _s_Non_Fed_user_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Given("^_S NFU navigates to system account directory page$")
	public void _s_NFU_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_S NFU enters all the system information$")
	public void _s_NFU_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan10);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_S NFU enters all the organization info$")
	public void _s_NFU_enters_all_the_organization_info() throws Exception {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_S NFU enters only public permissions info$")
	public void _s_NFU_enters_only_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_S NFU enters security info$")
	public void _s_NFU_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_S NFU enters authorization info$")
	public void _s_NFU_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_S NFU accepts terms and conditions and submits account$")
	public void _s_NFU_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectPublicPermissionsTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFedModified(
				Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_S IAE PMO Administrator logs in$")
	public void _s_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_S IAE_PMOA navigates to system account directory$")
	public void _s_IAE_PMOA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@When("^_S IAE_PMOA should not receive the request$")
	public void _s_IAE_PMOA_should_not_receive_the_request() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan10);
		Assert.assertEquals(expectedSearchResult, SystemAccountDirectoryPage.validateStatusSearchResult());
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_S GSA Security Approver logs in$")
	public void _s_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_S GSA SA navigates to system account directory$")
	public void _s_GSA_SA_navigates_to_system_account_directory() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_S GSA SA receives request directly and reject it$")
	public void _s_GSA_SA_receives_request_directly() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan10);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_S Non-Fed User logs in again$")
	public void _s_Non_Fed_User_logs_in_again() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_S NFU validate system account is in draft status$")
	public void _s_NFU_validate_system_account_is_in_draft_status() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan10);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// _T Comment validation in workflow
	@Given("^_T System Account Manager logs in$")
	public void _t_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_T SAM navigates to system account directory page$")
	public void _t_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_T SAM enters all the system information$")
	public void _t_SAM_enters_all_the_system_information() throws Exception {

		NewSystemAccountPage.enterSystemAccountName(uniqSan11);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_T SAM enters all the organization info$")
	public void _t_SAM_enters_all_the_organization_info() throws Exception {
		// NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_T SAM enters all permissions info$")
	public void _t_SAM_enters_all_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CD_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CD_WRITE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CD_READ_DOD_DATA);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CD_WRITE_DOD_DATA);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.EI_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.EI_READ_FOUO);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.EI_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.FH_READ_ONLY);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.FH_READ_FOUO);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.AL_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.WD_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.RD_READ_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_T SAM enters security info$")
	public void _t_SAM_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_T SAM enters authorization info$")
	public void _t_SAM_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
	}

	@Given("^_T SAM adds comment and validate it$")
	public void _t_SAM_adds_comment_and_validate_it() throws Exception {
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAM_ADD_COMMENT);
		String SAMComment = NewSystemAccountPage.validateComment(0);
		Assert.assertEquals(Constants.MA_SAM_ADD_COMMENT, SAMComment);
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_T SAM accepts terms and conditions and submits account$")
	public void _t_SAM_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectALLPermissions();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_T System Account Administrator logs in$")
	public void _t_System_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_T SAA searches for Pending Review request and validate comment$")
	public void _t_SAA_searches_for_Pending_Review_request_and_validate_comment() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan11);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		String SAMComment = NewSystemAccountPage.validateComment(0);
		Assert.assertEquals(Constants.MA_SAM_ADD_COMMENT, SAMComment);
	}

	@Then("^_T SAA adds comment, validate it and approve request$")
	public void _t_SAA_adds_comment_validate_it_and_approve_request() throws Exception {
		NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		String SAAComment = NewSystemAccountPage.validateComment(2);
		Assert.assertEquals(Constants.MA_SAA_ADD_COMMENT, SAAComment);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_T IAE PMO Administrator logs in$")
	public void _t_IAE_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_T IAE_PMOA searches for Pending Permissions Approval request and validate comments$")
	public void _t_IAE_PMOA_searches_for_Pending_Permissions_Approval_request_and_validate_comments() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan11);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		String SAMComment = NewSystemAccountPage.validateComment(0);
		Assert.assertEquals(Constants.MA_SAM_ADD_COMMENT, SAMComment);
		String SAAComment = NewSystemAccountPage.validateComment(2);
		Assert.assertEquals(Constants.MA_SAA_ADD_COMMENT, SAAComment);
	}

	@Then("^_T IAE_PMOA adds comment, validate it and approve request$")
	public void _t_IAE_PMOA_adds_comment_validate_it_and_approve_request() throws Exception {
		NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.clickOnShowPreviousCommentLink();
		String PMOAComment = NewSystemAccountPage.validateComment(4);
		Assert.assertEquals(Constants.MA_PMOA_ADD_COMMENT, PMOAComment);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_T GSA Security Approver logs in$")
	public void _t_GSA_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_T GSA SA searches for Pending Approval request and validate comments$")
	public void _t_GSA_SA_searches_for_Pending_Approval_request_and_validate_comments() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSan11);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		NewSystemAccountPage.clickOnShowPreviousCommentLink();
		String SAMComment = NewSystemAccountPage.validateComment(0);
		Assert.assertEquals(Constants.MA_SAM_ADD_COMMENT, SAMComment);
		String SAAComment = NewSystemAccountPage.validateComment(2);
		Assert.assertEquals(Constants.MA_SAA_ADD_COMMENT, SAAComment);
		String PMOAComment = NewSystemAccountPage.validateComment(4);
		Assert.assertEquals(Constants.MA_PMOA_ADD_COMMENT, PMOAComment);
	}

	@Then("^_T GSA SA adds comment, validate and approve request$")
	public void _t_GSA_SA_adds_comment_validate_and_approve_request() throws Exception {
		NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.clickOnShowPreviousCommentLink();
		String GSASAComment = NewSystemAccountPage.validateComment(6);
		Assert.assertEquals(Constants.MA_GSASA_ADD_COMMENT, GSASAComment);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}