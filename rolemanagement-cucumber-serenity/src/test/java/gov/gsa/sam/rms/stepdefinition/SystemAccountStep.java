package gov.gsa.sam.rms.stepdefinition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.locators.SystemAccountDirectoryPageLocator;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.SystemAccountRequestDetailsPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.SignInUtility;
import net.serenitybdd.core.annotations.findby.By;

public class SystemAccountStep {
	private static Logger logger = LoggerFactory.getLogger(SystemAccountStep.class);
	private static String comments = new String();

	private static StringBuilder systemManager = new StringBuilder(ConstantsAccounts.SYSTEM_MANAGER_1);
	private static StringBuilder systemAdmin = new StringBuilder(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
	private static StringBuilder gsasecurityapprover = new StringBuilder(ConstantsAccounts.GSASECURITY_APPROVER_1);
	private static StringBuilder securityOfficial = new StringBuilder(ConstantsAccounts.GSASECURITY_APPROVER_1);
	private static StringBuilder nonfeduser = new StringBuilder(ConstantsAccounts.NONFED_USER_1);

	// String formattedDate= new SimpleDateFormat("hh:mm:ss a").format(new Date());
	long epoch = System.currentTimeMillis() / 1000;
	String formattedDate = Long.toString(epoch);

	@Given("^_1 user logs in as system account admin$")
	public void _1_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_1 user navigates to system account directory page$")
	public void _1_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_1 user enters all the system information$")
	public void _1_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_1 user enters all the organization info$")
	public void _1_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_1 user enters permissions info$")
	public void _1_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_1 user enters security info$")
	public void _1_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_1 user enters authorization info$")
	public void _1_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_1 the newly created account should show up on the system account directory page$")
	public void _1_the_newly_created_account_should_show_up_on_the_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_2 user logs in as system account manager$")
	public void _2_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_2 user navigates to system account directory page$")
	public void _2_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_2 user enters all the system information$")
	public void _2_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_2 user enters all the organization info$")
	public void _2_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+samanager@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_2 user enters permissions info$")
	public void _2_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_2 user enters security info$")
	public void _2_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_2 user enters authorization info$")
	public void _2_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_2 the newly created account should show up on the system account directory page$")
	public void _2_the_newly_created_account_should_show_up_on_the_system_account_directory_page() throws Throwable {

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_3 user logs in as gsa security approver$")
	public void _3_user_logs_in_as_gsa_security_approver() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_3 user navigates to system account directory page$")
	public void _3_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_3 user should not see the create account button$")
	public void _3_user_enters_all_the_system_information() throws Throwable {
		boolean newAccountButtonFound = SystemAccountDirectoryPage
				.elementFound(SystemAccountDirectoryPageLocator.NEW_BUTTON);
		Assert.assertEquals(false, newAccountButtonFound);
	}

	// ----------------------------------------
	@Given("^_4 user logs in as system account manager$")
	public void _4_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_4 user navigates to system account directory page$")
	public void _4_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_4 user enters all the system information$")
	public void _4_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test_v1");
		NewSystemAccountPage.enterInterfacingSystemName("testv1 interface");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_4 user enters all the organization info$")
	public void _4_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+samanager@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_4 user enters permissions info$")
	public void _4_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_4 user enters security info$")
	public void _4_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_4 user enters authorization info$")
	public void _4_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_4 the newly created account should show up on the system account directory page$")
	public void _4_the_newly_created_account_should_show_up_on_the_system_account_directory_page() throws Throwable {
		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
	}

	@And("^_4 gsa security approver logs in$")
	public void _4_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_4 user should be able to see the account and reject the account$")
	public void _4_user_should_be_able_to_see_the_account_and_reject_the_account() throws Throwable {
		SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("request is rejected");
		SystemAccountRequestDetailsPage.clickRejectButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickRejectedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_REJECTED,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	// ---------------------------------------------------------------
	@Given("^_5 user logs in as system account manager$")
	public void _5_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_5 user navigates to system account directory page$")
	public void _5_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_5 user enters all the system information$")
	public void _5_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test_v1");
		NewSystemAccountPage.enterInterfacingSystemName("testv1 interface");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_5 user enters all the organization info$")
	public void _5_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+samanager@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_5 user enters permissions info$")
	public void _5_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_5 user enters security info$")
	public void _5_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_5 user enters authorization info$")
	public void _5_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
	}

	@Then("^_5 under the review tab the user should be able to enter comments$")
	public void _5_under_the_review_tab_the_user_should_be_able_to_enter_comments() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		comments = "Test comment";
		NewSystemAccountPage.writeComment(comments);
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5 gsa security approver logs in$")
	public void _5_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_5 user should be able to see the new account and view the comments posted$")
	public void _5_user_should_be_able_to_see_the_new_account_and_view_the_comments_posted() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		boolean commentFound = SystemAccountRequestDetailsPage.commentFound("shah.raiaan+samanager@gsa.gov", comments);
		Assert.assertEquals(true, commentFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("sam-comment"));
		LaunchBrowserUtil.delay(2);

		boolean commentsFound = SystemAccountRequestDetailsPage.commentFound("shah.raiaan+samanager@gsa.gov",
				"Test comment");
		Assert.assertEquals(true, commentsFound);
		LaunchBrowserUtil.delay(2);
		// ----------------delete the request---------------
		SystemAccountRequestDetailsPage.writeComment("rejecting request");
		SystemAccountRequestDetailsPage.clickRejectButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickRejectedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_REJECTED,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);

	}

	// ------------------------------------------------------------
	@Given("^_6 user logs in as system account admin$")
	public void _6_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_2, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_6 _user navigates to system account directory page$")
	public void _6_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();

	}

	@When("^_6 user clicks on review tab then comment text box should not be available$")
	public void _6_user_clicks_on_review_tab_then_comment_text_box_should_not_be_available() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
		LaunchBrowserUtil.scrollAllTheWayDown();
		boolean commenttextboxFound = NewSystemAccountPage.elementFound(NewSystemAccountPageLocator.COMMENT_TEXTBOX);
		Assert.assertEquals(false, commenttextboxFound);
	}

	@Then("^_6 user goes back to fill out system information and clicks next$")
	public void _6_user_goes_back_to_fill_out_system_information_and_clicks_next() throws Throwable {
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickEditTab();
		NewSystemAccountPage.enterSystemAccountName("test_v1");
		NewSystemAccountPage.enterInterfacingSystemName("testv1 interface");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Then("^_6 user should see the comment text box under the edit tab$")
	public void _6_user_should_see_the_comment_text_box_under_the_edit_tab() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
		LaunchBrowserUtil.scrollAllTheWayDown();
		boolean commenttextboxFound = NewSystemAccountPage.elementFound(NewSystemAccountPageLocator.COMMENT_TEXTBOX);
		Assert.assertEquals(true, commenttextboxFound);

		// -------------delete the draft---------------
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_7 user logs in as system account manager$")
	public void _7_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);

	}

	@And("^_7 user navigates to system account directory page$")
	public void _7_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_7 user enters all system information and clicks next$")
	public void _7_user_enters_all_system_information_and_clicks_next() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test_v1");
		NewSystemAccountPage.enterInterfacingSystemName("testv1 interface");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_7 user goes to review tab$")
	public void _7_user_goes_to_review_tab() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
	}

	@When("^_7 user enters blank spaces and hits enter$")
	public void _7_user_enters_blank_spaces_and_hits_enter() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.writeComment("   ");
	}

	@Then("^_7 error message should pop up$")
	public void _7_error_message_should_pop_up() throws Throwable {
		String alertMessage = NewSystemAccountPageLocator.COMMENT_ERROR;
		String alertMessageFound = NewSystemAccountPage.getAlertMessage();
		Assert.assertEquals(alertMessage, alertMessageFound);
		// ---------delete the draft account--------
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_8 user logs in as system account manager$")
	public void _8_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_8 _user navigates to system account directory page$")
	public void _8_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();

	}

	@When("^_8 user clicks on review tab then comment text box should not be available$")
	public void _8_user_clicks_on_review_tab_then_comment_text_box_should_not_be_available() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
		LaunchBrowserUtil.scrollAllTheWayDown();
		boolean commenttextboxFound = NewSystemAccountPage.elementFound(NewSystemAccountPageLocator.COMMENT_TEXTBOX);
		Assert.assertEquals(false, commenttextboxFound);
	}

	@Then("^_8 user goes back to fill out system information and clicks next$")
	public void _8_user_goes_back_to_fill_out_system_information_and_clicks_next() throws Throwable {
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickEditTab();
		NewSystemAccountPage.enterSystemAccountName("test_v1");
		NewSystemAccountPage.enterInterfacingSystemName("testv1 interface");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Then("^_8 user should see the comment text box under the edit tab$")
	public void _8_user_should_see_the_comment_text_box_under_the_edit_tab() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
		LaunchBrowserUtil.scrollAllTheWayDown();
		boolean commenttextboxFound = NewSystemAccountPage.elementFound(NewSystemAccountPageLocator.COMMENT_TEXTBOX);
		Assert.assertEquals(true, commenttextboxFound);

		// -------------delete the draft---------------
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_9 user logs in as system account admin$")
	public void _9_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);

	}

	@And("^_9 user navigates to system account directory page$")
	public void _9_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_9 user enters all system information and clicks next$")
	public void _9_user_enters_all_system_information_and_clicks_next() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test_v1");
		NewSystemAccountPage.enterInterfacingSystemName("testv1 interface");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_9 user goes to review tab$")
	public void _9_user_goes_to_review_tab() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
	}

	@When("^_9 user enters blank spaces and hits enter$")
	public void _9_user_enters_blank_spaces_and_hits_enter() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.writeComment("   ");
	}

	@Then("^_9 error message should pop up$")
	public void _9_error_message_should_pop_up() throws Throwable {
		String alertMessage = NewSystemAccountPageLocator.COMMENT_ERROR;
		String alertMessageFound = NewSystemAccountPage.getAlertMessage();
		Assert.assertEquals(alertMessage, alertMessageFound);
		// ---------delete the draft account--------
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("SystemManagerCreatedAccount1",
				Constants.STATUS_PENDING_REVIEW, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountFound);

		boolean accountExist = SystemAccountDirectoryPage.accountFound("SystemAdminCreatedAccount1",
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(false, accountExist);
	}

	@Given("^_10 user logs in as system account manager$")
	public void _10_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_10 user navigates to system account directory page$")
	public void _10_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_10 user should only see accounts opened by them$")
	public void _10_user_should_only_see_accounts_opened_by_them() throws Throwable {

		boolean accountFound = SystemAccountDirectoryPage.accountFound("SystemManagerCreatedAccount1",
				Constants.STATUS_PENDING_REVIEW, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountFound);

		boolean accountExist = SystemAccountDirectoryPage.accountFound("SystemAdminCreatedAccount1",
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(false, accountExist);
	}

	@Given("^_11 user logs in as system account admin$")
	public void _11_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_11 user navigates to system account directory page$")
	public void _11_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_11 user should accounts opened by system managers and themselves$")
	public void _11_user_should_accounts_opened_by_system_managers_and_themselves() throws Throwable {
		boolean accountFound = SystemAccountDirectoryPage.accountFound("SystemManagerCreatedAccount1",
				Constants.STATUS_PENDING_REVIEW, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountFound);

		boolean accountExist = SystemAccountDirectoryPage.accountFound("SystemAdminCreatedAccount1",
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountExist);
	}

	@Given("^_12 user logs in as system account admin$")
	public void _12_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);

		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_12 user navigates to system account directory page$")
	public void _12_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_12 user enters all the system information$")
	public void _12_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test");
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_12 user enters all the organization info$")
	public void _12_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_12 user enters permissions info$")
	public void _12_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_12 user enters security info$")
	public void _12_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_12 user enters authorization info$")
	public void _12_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@When("^_12 gsa security approver logs into workspace$")
	public void _12_gsa_security_approver_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_12 gsa security approver goes to system account page$")
	public void _12_gsa_security_approver_goes_to_system_account_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_12 gsa security approver should be able to publish the account$")
	public void _12_gsa_security_approver_should_be_able_to_publish_the_account() throws Throwable {

	}

	@Given("^_13 user logs in as gsa security approver$")
	public void _13_user_logs_in_as_gsa_security_approver() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_13 gsa security approver should see correct number and type of filters on system account page$")
	public void _13_gsa_security_approver_should_see_correct_number_and_type_of_filters_on_system_account_page()
			throws Throwable {

		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickDeactivatedFilter();
		SystemAccountDirectoryPage.clickPublishedFilter();
		LaunchBrowserUtil.scrollToMiddle();

	}

	@When("^_13 user logs in as system account admin$")
	public void _13_user_logs_in_as_system_account_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_13 system account admin see should correct number and type of filters on system account page$")
	public void _13_system_account_admin_see_should_correct_number_and_type_of_filters_on_system_account_page()
			throws Throwable {
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickDeactivatedFilter();
		SystemAccountDirectoryPage.clickPublishedFilter();
		LaunchBrowserUtil.scrollToMiddle();

	}

	@When("^_13 user logs in as system manager$")
	public void _13_user_logs_in_as_system_manager() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);

		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_13 system manager should see should correct number and type of filters on system account page$")
	public void _13_system_manager_should_see_should_correct_number_and_type_of_filters_on_system_account_page()
			throws Throwable {
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickDeactivatedFilter();
		SystemAccountDirectoryPage.clickPublishedFilter();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@When("^13_user logs in as nonfed user$")
	public void _13_user_logs_in_as_nonfed_user() throws Throwable {
		/*
		 * SignInUtility.signIntoCommonWorkspacePageNonFed(Constants.GMAIL_NONFED,
		 * Constants.USERPASS); SignInUtility.signIntoWorkspace(Constants.GMAIL_NONFED,
		 * Constants.USERPASS, secretkey, usertype);
		 */

		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_13 nonfed user should see should correct number and type of filters on system account page$")
	public void _13_nonfed_user_should_see_should_correct_number_and_type_of_filters_on_system_account_page()
			throws Throwable {
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickDeactivatedFilter();
		SystemAccountDirectoryPage.clickPublishedFilter();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Given("^_14 user logs in as system account admin$")
	public void _14_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_14 user navigates to system account directory page$")
	public void _14_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_14 user enters all the system information$")
	public void _14_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_14 user enters all the organization info$")
	public void _14_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_14 user enters permissions info$")
	public void _14_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_14 user enters security info$")
	public void _14_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_14 user enters authorization info$")
	public void _14_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		// NewSystemAccountPage.selectAllTermsOfUse();
		NewSystemAccountPage.selectAllTermsOfUseSensitivePermission();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_14 the newly created account should show up on the system account directory page$")
	public void _14_the_newly_created_account_should_show_up_on_the_system_account_directory_page() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		LaunchBrowserUtil.scrollAllTheWayDown();
		SystemAccountDirectoryPage.clickPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_PERMISSIONS_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_14 iae admin logs in$")
	public void _14_iam_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_5, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_5_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_14 iam admin should be able to approve the permission$")
	public void _14_iam_admin_should_be_able_to_approve_the_permission() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_PERMISSIONS_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("permission is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);

	}

	@When("^_14 gsa security approver logs in$")
	public void _14_gsa_security_approver_logs_in() throws Throwable {
		// SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov",
		// Constants.USERPASS);

		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_14 gsa security approver navigates to system accounts directory page$")
	public void _14_gsa_security_approver_navigates_to_system_accounts_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_14 gsa security approver should be able to approve the request by system account admin$")
	public void _14_gsa_security_approver_should_be_able_to_approve_the_request_by_system_account_admin()
			throws Throwable {

		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@When("^_14 system account admin logs in again$")
	public void _14_system_account_admin_logs_in_again() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_14 admin goes to system account directory page$")
	public void _14_goes_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

	}

	@And("^_14 admin generates api key$")
	public void _14_admin_generates_api_key() throws Throwable {
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		SystemAccountRequestDetailsPage.enterNewPassword(Constants.USERPASS);
		SystemAccountRequestDetailsPage.enterConfirmPassword(Constants.USERPASS);
		SystemAccountRequestDetailsPage.clickPasswordSaveButton();
		// SystemAccountRequestPage.clickShowApiKeyCheckbox();
		// SystemAccountRequestPage.enterShowApiKeyPassword(Constants.USERPASS);
		// SystemAccountRequestPage.enterApiKeySubmitButton();
		SystemAccountRequestDetailsPage.clickDeactivateButton();
		SystemAccountRequestDetailsPage.clickYesDeactivateMyAccount();
		SystemAccountRequestDetailsPage.clickYesContinueWithDeactivation();
	}

	@Then("^_14 they should be able to deactivate their system account$")
	public void _14_they_should_be_able_to_deactivate_their_system_account() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_DEACTIVATED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@Given("^_15 user logs in as system account manager$")
	public void _15_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 system manager navigates to system account directory page$")
	public void _15_system_manager_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_15 system manager enters all the system information$")
	public void _15_system_manager_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_15 system manager enters all the organization info$")
	public void _15_system_manager_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_15 system manager enters permissions info$")
	public void _15_system_manager_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_15 system manager enters security info$")
	public void _15_system_manager_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_15 system manager enters authorization info$")
	public void _15_system_manager_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_15 the newly created account request should show up on the system account directory page$")
	public void _15_the_newly_created_account_request_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
	}

	@When("^_15 system accound admin logs in$")
	public void _15_system_accound_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 system account admin navigates to system account directory page$")
	public void _15_system_account_admin_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_15 admin should be able to change the request status to pending approval$")
	public void _15_admin_should_be_able_to_change_the_request_status_to_pending_review() throws Throwable {
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is reviewed");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();

		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@When("^_15 gsa security approver logs in$")
	public void _15_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 gsa security approver navigates to system accounts directory page$")
	public void _15_gsa_security_approver_navigates_to_system_accounts_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_15 gsa security approver should be able to approve the request by system account manager$")
	public void _15_gsa_security_approver_should_be_able_to_approve_the_request_by_system_account_manager()
			throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@When("^_15 system account manager logs in again$")
	public void _15_system_account_manager_logs_in_again() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);

		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 manager goes to system account directory page$")
	public void _15_manager_goes_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@And("^_15 manager generates api key$")
	public void _15_man_ager_generates_api_key() throws Throwable {
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		SystemAccountRequestDetailsPage.enterNewPassword(Constants.USERPASS);
		SystemAccountRequestDetailsPage.enterConfirmPassword(Constants.USERPASS);
		SystemAccountRequestDetailsPage.clickPasswordSaveButton();
		SystemAccountRequestDetailsPage.clickShowApiKeyCheckbox();
		SystemAccountRequestDetailsPage.enterShowApiKeyPassword(Constants.USERPASS);
		SystemAccountRequestDetailsPage.enterApiKeySubmitButton();
		SystemAccountRequestDetailsPage.clickDeactivateButton();
		SystemAccountRequestDetailsPage.clickYesDeactivateMyAccount();
		SystemAccountRequestDetailsPage.clickYesContinueWithDeactivation();
	}

	@Then("^_15 they should be able to deactivate their system account$")
	public void _15_they_should_be_able_to_deactivate_their_system_account() throws Throwable {
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_DEACTIVATED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@Given("^_16 test system account history$")
	public void _16_test_system_account_history() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword("1548958073");
		SystemAccountDirectoryPage.clickPublishedFilter();

		SystemAccountDirectoryPage.accountFound("1548958073", Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				"Approval Status Email Sent to shah.raiaan+gsasecurityapprover@gsa.gov", 1);

		Assert.assertEquals(true, historystampFound);
	}

	@Given("^_17saaccount user logs in as system account manager$")
	public void _17saaccount_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_17saaccount user navigates to system account directory page$")
	public void _17saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_17saaccount user enters all the system information$")
	public void _17saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_17saaccount user enters all the organization info$")
	public void _17saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_17saaccount user enters permissions info$")
	public void _17saaccount_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_17saaccount user enters security info$")
	public void _17saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_17saaccount user enters authorization info$")
	public void _17saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_17saaccount the newly created account should show up on the system account directory page$")
	public void _17saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		LaunchBrowserUtil.scrollAllTheWayDown();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_17saaccount the system manager should see the account history updated$")
	public void _17saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + ConstantsAccounts.GSASECURITY_APPROVER_1,
				0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"" + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_17saaccount system admin changes the request to pending approval$")
	public void _17saaccount_system_admin_changes_the_request_to_pending_approval() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		LaunchBrowserUtil.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is reviewed");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_17saaccount the system admin should see the account history updated$")
	public void _17saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				Constants.SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO + " " + systemAdmin, 0);

		Assert.assertEquals(true, historystampFound);

		LaunchBrowserUtil.delay(10);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_17saaccount gsa security approver logs in$")
	public void _17saaccount_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_17saaccount gsa security approver approves the reviewed request$")
	public void _17saaccount_gsa_security_approver_approves_the_reviewed_request() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		LaunchBrowserUtil.delay(8);
	}

	@Then("^_17saaccount the gsa security approver should see the account history updated$")
	public void _17saaccount_the_gsa_security_approver_should_see_the_account_history_updated() throws Throwable {

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				Constants.SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO + " " + gsasecurityapprover, 0);

		Assert.assertEquals(true, historystampFound);

		LaunchBrowserUtil.delay(10);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_17saaccount user logs backin in as system account manager$")
	public void _17saaccount_user_logs_backin_in_as_system_account_manager() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_17saacount they should be able to deactivate their published system account$")
	public void _17saacount_they_should_be_able_to_deactivate_their_published_system_account() throws Throwable {
		SystemAccountRequestDetailsPage.clickDeactivateButton();
		SystemAccountRequestDetailsPage.clickYesDeactivateMyAccount();
		SystemAccountRequestDetailsPage.clickYesContinueWithDeactivation();
		SystemAccountDirectoryPage.clickDeactivatedFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_DEACTIVATED,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();

	}

	@Given("^_18saaccount user logs in as system account manager$")
	public void _18saaccount_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_18saaccount user navigates to system account directory page$")
	public void _18saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_18saaccount user enters all the system information$")
	public void _18saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.clickCheckAvailabilityButton();
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_18saaccount user enters all the organization info$")
	public void _18saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_18saaccount user enters permissions info$")
	public void _18saaccount_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_18saaccount user enters security info$")
	public void _18saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("198.198.0.0");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_18saaccount user enters authorization info$")
	public void _18saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_18saaccount the newly created account should show up on the system account directory page$")
	public void _18saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

	}

	@Then("^_18saaccount the system manager should see the account history updated$")
	public void _18saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + ConstantsAccounts.GSASECURITY_APPROVER_1,
				0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"" + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_18saaccount system admin rejects the request$")
	public void _18saaccount_system_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(4);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is rejected");
		SystemAccountRequestDetailsPage.clickRejectButton();

	}

	@Then("^_18saaccount the system admin should see the account history updated$")
	public void _18saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		LaunchBrowserUtil.delay(25);
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONREJECTED,
				"" + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_REJECTED, 0);

		Assert.assertEquals(true, historystampFound);
	}

	@Given("^_19saaccount user logs in as nonfed user$")
	public void _19saaccount_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_19saaccount user navigates to system account directory page$")
	public void _19saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_19saaccount user enters all the system information$")
	public void _19saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_19saaccount user enters all the organization info$")
	public void _19saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_19saaccount user enters permissions info$")
	public void _19saaccount_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_19saaccount user enters security info$")
	public void _19saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_19saaccount user enters authorization info$")
	public void _19saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_19saaccount the newly created account should show up on the system account directory page$")
	public void _19saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_19saaccount the nonfed user should see the account history updated$")
	public void _19saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + gsasecurityapprover, 0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"" + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);
		Assert.assertEquals(true, historystampFound2);

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_19saaccount gsasecurity approver approves the request$")
	public void _19saaccount_system_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_19saaccount the gsasecurity approver should see the account history updated$")
	public void _19saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		LaunchBrowserUtil.delay(25);
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED, Constants.SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO, 0);
		Assert.assertEquals(true, historystampFound);
	}

	@When("^_19saaccount user logs backin in as nonfed user$")
	public void _19saaccount_user_logs_backin_in_as_nonfed_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_19saaccount they should be able to deactivate their published system account$")
	public void _19saaccount_they_should_be_able_to_deactivate_their_published_system_account() throws Throwable {
		SystemAccountRequestDetailsPage.clickDeactivateButton();
		SystemAccountRequestDetailsPage.clickYesDeactivateMyAccount();
		SystemAccountRequestDetailsPage.clickYesContinueWithDeactivation();
		SystemAccountDirectoryPage.clickDeactivatedFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_DEACTIVATED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_20saaccount user logs in as nonfed user$")
	public void _20saaccount_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_20saaccount user navigates to system account directory page$")
	public void _20saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_20saaccount user enters all the system information$")
	public void _20saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.clickCheckAvailabilityButton();
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_20saaccount user enters all the organization info$")
	public void _20saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_20saaccount user enters permissions info$")
	public void _20saaccount_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_20saaccount user enters security info$")
	public void _20saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString());
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_20saaccount user enters authorization info$")
	public void _20saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_20saaccount the newly created account should show up on the system account directory page$")
	public void _20saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_20saaccount the nonfed user should see the account history updated$")
	public void _20saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + gsasecurityapprover, 0);
		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"" + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

	}

	@When("^_20saaccount gsasecurity approver rejects the request$")
	public void _20saaccount_system_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_20saaccount the gsasecurity approver should see the account history updated$")
	public void _20saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		LaunchBrowserUtil.delay(25);
		boolean historystampFound = SystemAccountRequestDetailsPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED, "" + Constants.SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO, 0);
		Assert.assertEquals(true, historystampFound);
	}

	@Given("^_21saaccount user logs in as system admin$")
	public void _21saaccount_user_logs_in_as_system_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_21saaccount user navigates to system account directory page$")
	public void _21saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_21saaccount user enters all the system information$")
	public void _21saaccount_user_enters_all_the_system_information() throws Throwable {
		SystemAccountDirectoryPage.clickNewButton();
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.clickCheckAvailabilityButton();
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_21saaccount user enters all the organization info$")
	public void _21saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_21saaccount user enters permissions info$")
	public void _21saaccount_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_21saaccount user enters security info$")
	public void _21saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_21saaccount user enters authorization info$")
	public void _21saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@And("^_21saaccount the newly created account should show up on the system account directory page$")
	public void _21saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		LaunchBrowserUtil.delay(7);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_21saaccount gsasecurity approver approves the request$")
	public void _21saaccount_gsasecurity_approver_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(5);

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_21saaccount system admin should be able to set a password for the published account$")
	public void _21saaccount_system_admin_should_be_able_to_set_a_password_for_the_published_account()
			throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountRequestDetailsPage.enterNewPasswordForSystemAccount(Constants.SYSTEMACCOUNT_PASSWORD);
		SystemAccountRequestDetailsPage.enterConfirmPasswordForSystemAccount(Constants.SYSTEMACCOUNT_PASSWORD);
		SystemAccountRequestDetailsPage.clickSaveButtonOnSystemAccountPassword();
		boolean successbannerfound = SystemAccountRequestDetailsPage
				.elementFound(SystemAccountDirectoryPageLocator.PASSWORD_SUCCESS_BANNER);
		Assert.assertEquals(true, successbannerfound);
		SystemAccountRequestDetailsPage.clickRequestAPIkeyButton(Constants.SYSTEMACCOUNT_PASSWORD);
	}

	@And("^_21saaccount system admin should be able to reset the password$")
	public void _21saaccount_system_admin_should_be_able_to_reset_the_password() throws Throwable {
		// SystemAccountRequestPage.clickResetPasswordRadioButton();
		SystemAccountRequestDetailsPage.enterCurrentPasswordForSystemAccount(Constants.SYSTEMACCOUNT_PASSWORD);
		SystemAccountRequestDetailsPage.enterNewPasswordForSystemAccount(Constants.PASSWORD_NEW);
		SystemAccountRequestDetailsPage.enterConfirmPasswordForSystemAccount(Constants.PASSWORD_NEW);
		SystemAccountRequestDetailsPage.clickSaveButtonOnSystemAccountPassword();

		boolean successbannerfound = SystemAccountRequestDetailsPage
				.elementFound(SystemAccountDirectoryPageLocator.PASSWORD_SUCCESS_BANNER);
		Assert.assertEquals(true, successbannerfound);
	}

	@And("^_21saccount system admin should be able to set the password through forgot password link$")
	public void _21saccount_system_admin_should_be_able_to_set_the_password_through_forgot_password_link()
			throws Throwable {
		SystemAccountRequestDetailsPage.clickForgotPasswordRadioButton();
		SystemAccountRequestDetailsPage.clickRequestionAPIButtonForOtp();
		String systemaccountotp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		SystemAccountRequestDetailsPage.enterOtpOnForgotPassword(systemaccountotp);
		SystemAccountRequestDetailsPage.enterNewPasswordForSystemAccount("YellowColor1!");
		SystemAccountRequestDetailsPage.enterConfirmPasswordForSystemAccount("YellowColor1!");
		SystemAccountRequestDetailsPage.clickSaveButtonOnSystemAccountPassword();

		boolean successbannerfound = SystemAccountRequestDetailsPage
				.elementFound(SystemAccountDirectoryPageLocator.PASSWORD_SUCCESS_BANNER);
		Assert.assertEquals(true, successbannerfound);

	}

	@Given("^_22saaccount user logs in as nonfed user$")
	public void _22saaccount_user_logs_in_as_nonfed_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_22saaccount user navigates to system account directory page$")
	public void _22saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_22saaccount user enters all the system information$")
	public void _22saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_22saaccount user enters all the organization info$")
	public void _22saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo("raiaan.shah+newregisterednonfeduser81@gmail.com");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_22saaccount user enters permissions info$")
	public void _22saaccount_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_22saaccount user enters security info$")
	public void _22saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString() + "@gsa.gov");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_22saaccount user enters authorization info$")
	public void _22saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		LaunchBrowserUtil.delay(7);
		LaunchBrowserUtil.enterUrl("https://100samfrontendaltcomp.apps.prod-iae.bsp.gsa.gov/workspace/system/");// this
																												// line
																												// is
																												// necessary
																												// until
																												// bug
																												// is
																												// fixed
	}

	@And("^_22saaccount the newly created account should show up on the system account directory page$")
	public void _22saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@When("^_22saaccount gsasecurity approver approves the request$")
	public void _22saaccount_gsasecurity_approver_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(5);

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
	}

	@Then("^_22saaccount nonfed user should be able to set a password for the published account$")
	public void _22saaccount_system_admin_should_be_able_to_set_a_password_for_the_published_account()
			throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountRequestDetailsPage.enterNewPasswordForSystemAccount(Constants.SYSTEMACCOUNT_PASSWORD);
		SystemAccountRequestDetailsPage.enterConfirmPasswordForSystemAccount(Constants.SYSTEMACCOUNT_PASSWORD);
		SystemAccountRequestDetailsPage.clickSaveButtonOnSystemAccountPassword();
		boolean successbannerfound = SystemAccountRequestDetailsPage
				.elementFound(SystemAccountDirectoryPageLocator.PASSWORD_SUCCESS_BANNER);
		Assert.assertEquals(true, successbannerfound);
		SystemAccountRequestDetailsPage.clickRequestAPIkeyButton(Constants.SYSTEMACCOUNT_PASSWORD);
	}

	@And("^_22saaccount nonfed user should be able to reset the password$")
	public void _22saaccount_system_admin_should_be_able_to_reset_the_password() throws Throwable {
		// SystemAccountRequestPage.clickResetPasswordRadioButton();
		SystemAccountRequestDetailsPage.enterCurrentPasswordForSystemAccount(Constants.SYSTEMACCOUNT_PASSWORD);
		SystemAccountRequestDetailsPage.enterNewPasswordForSystemAccount(Constants.PASSWORD_NEW);
		SystemAccountRequestDetailsPage.enterConfirmPasswordForSystemAccount(Constants.PASSWORD_NEW);
		SystemAccountRequestDetailsPage.clickSaveButtonOnSystemAccountPassword();

		boolean successbannerfound = SystemAccountRequestDetailsPage
				.elementFound(SystemAccountDirectoryPageLocator.PASSWORD_SUCCESS_BANNER);
		Assert.assertEquals(true, successbannerfound);
	}

	@And("^_22saccount nonfed user should be able to set the password through forgot password link$")
	public void _22saccount_system_admin_should_be_able_to_set_the_password_through_forgot_password_link()
			throws Throwable {
		SystemAccountRequestDetailsPage.clickForgotPasswordRadioButton();
		SystemAccountRequestDetailsPage.clickRequestionAPIButtonForOtp();
		String systemaccountotp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		SystemAccountRequestDetailsPage.enterOtpOnForgotPassword(systemaccountotp);
		SystemAccountRequestDetailsPage.enterNewPasswordForSystemAccount("YellowColor1!");
		SystemAccountRequestDetailsPage.enterConfirmPasswordForSystemAccount("YellowColor1!");
		SystemAccountRequestDetailsPage.clickSaveButtonOnSystemAccountPassword();

		boolean successbannerfound = SystemAccountRequestDetailsPage
				.elementFound(SystemAccountDirectoryPageLocator.PASSWORD_SUCCESS_BANNER);
		Assert.assertEquals(true, successbannerfound);
	}

	@Given("^_23saaccount user logs in as system manager$")
	public void _23saaccount_user_logs_in_as_system_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_23saaccount user navigates to system account directory page$")
	public void _23saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@When("^_23saaccount user searches for sytem accounts using special characters$")
	public void _23saaccount_user_searches_for_sytem_accounts_using_special_characters() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword("@()");

	}

	@Then("^_23saaccount the filtered accounts displayed should contain those letters$")
	public void _23saaccount_the_filtered_accounts_displayed_should_contain_those_letters() throws Throwable {
		boolean accountFound1 = SystemAccountDirectoryPage.accountFound("@()_-", Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_DATA, Constants.NOACTION);
		Assert.assertEquals(true, accountFound1);
		LaunchBrowserUtil.delay(3);

		LaunchBrowserUtil.delay(3);
		SystemAccountDirectoryPage.searchByKeyword("@()_");
		boolean accountFound3 = SystemAccountDirectoryPage.accountFound("@()_-", Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_DATA, Constants.NOACTION);
		Assert.assertEquals(true, accountFound3);
	}

	@Given("^_24saaccount user logs in as nonfed user$")
	public void _24saaccount_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_24saaccount user navigates to system account directory page$")
	public void _24saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_24saaccount user enters all the system information$")
	public void _24saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_24saaccount user enters all the organization info$")
	public void _24saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_24saaccount user enters permissions info for sensitive read and write$")
	public void _24saaccount_user_enters_permissions_info_for_sensitive_read_and_write() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_24saaccount user enters security info$")
	public void _24saaccount_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_24saaccount user enters authorization info$")
	public void _24saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUseSensitivePermission();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_24saaccount the newly created account should show up on the system account directory page$")
	public void _24saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_PERMISSIONS_APPROVAL, Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);

		Assert.assertEquals(true, accountFound);
	}

	@When("^_24 iae admin logs in$")
	public void _24_iae_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_5, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_5_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_24 iam admin should be able to approve the permission$")
	public void _24_iam_admin_should_be_able_to_approve_the_permission() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_PERMISSIONS_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestDetailsPage.writeComment("permission is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}


	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
