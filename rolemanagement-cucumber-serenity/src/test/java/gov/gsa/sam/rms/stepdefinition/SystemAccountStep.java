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
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.SystemAccountRequestPage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import net.serenitybdd.core.annotations.findby.By;

public class SystemAccountStep {
	private static Logger logger = LoggerFactory.getLogger(SystemAccountStep.class);
	private static String comments = new String();
	private static StringBuilder systemManager = new StringBuilder("shah.raiaan+samanager");
	private static StringBuilder systemAdmin = new StringBuilder("shah.raiaan+saadmin");
	private static StringBuilder gsasecurityapprover = new StringBuilder("shah.raiaan+gsasecurityapprover");
	private static StringBuilder securityOfficial = new StringBuilder("shah.raiaan+gsasecurityapprover@gsa.gov");
	private static StringBuilder nonfeduser = new StringBuilder("raiaan.shah");

	// String formattedDate= new SimpleDateFormat("hh:mm:ss a").format(new Date());
	long epoch = System.currentTimeMillis() / 1000;
	String formattedDate = Long.toString(epoch);

	@Given("^_1 user logs in as system account admin$")
	public void _1_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_1 user navigates to system account directory page$")
	public void _1_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_2 user navigates to system account directory page$")
	public void _2_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_3 user navigates to system account directory page$")
	public void _3_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_4 user navigates to system account directory page$")
	public void _4_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_4 the newly created account should show up on the system account directory page$")
	public void _4_the_newly_created_account_should_show_up_on_the_system_account_directory_page() throws Throwable {
		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
	}

	@And("^_4 gsa security approver logs in$")
	public void _4_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_4 user should be able to see the account and reject the account$")
	public void _4_user_should_be_able_to_see_the_account_and_reject_the_account() throws Throwable {
		SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestPage.writeComment("request is rejected");
		SystemAccountRequestPage.clickRejectButton();
		SystemAccountRequestPage.clickCloseButton();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_5 user navigates to system account directory page$")
	public void _5_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5 gsa security approver logs in$")
	public void _5_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@Then("^_5 user should be able to see the new account and view the comments posted$")
	public void _5_user_should_be_able_to_see_the_new_account_and_view_the_comments_posted() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		boolean commentFound = SystemAccountRequestPage.commentFound("shah.raiaan+samanager@gsa.gov", comments);
		Assert.assertEquals(true, commentFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("sam-comment"));
		CommonMethods.delay(2);

		boolean commentsFound = SystemAccountRequestPage.commentFound("shah.raiaan+samanager@gsa.gov", "Test comment");
		Assert.assertEquals(true, commentsFound);
		CommonMethods.delay(2);
		// ----------------delete the request---------------
		SystemAccountRequestPage.writeComment("rejecting request");
		SystemAccountRequestPage.clickRejectButton();
		SystemAccountRequestPage.clickCloseButton();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_6 _user navigates to system account directory page$")
	public void _6_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_7 user logs in as system account manager$")
	public void _7_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);

	}

	@And("^_7 user navigates to system account directory page$")
	public void _7_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_8 user logs in as system account manager$")
	public void _8_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_8 _user navigates to system account directory page$")
	public void _8_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_9 user logs in as system account admin$")
	public void _9_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);

	}

	@And("^_9 user navigates to system account directory page$")
	public void _9_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_10 user navigates to system account directory page$")
	public void _10_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_11 user navigates to system account directory page$")
	public void _11_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_12 user navigates to system account directory page$")
	public void _12_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@When("^_12 gsa security approver logs into workspace$")
	public void _12_gsa_security_approver_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_12 gsa security approver goes to system account page$")
	public void _12_gsa_security_approver_goes_to_system_account_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_12 gsa security approver should be able to publish the account$")
	public void _12_gsa_security_approver_should_be_able_to_publish_the_account() throws Throwable {

	}

	@Given("^_13 user logs in as gsa security approver$")
	public void _13_user_logs_in_as_gsa_security_approver() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePageNonFed(Constants.GMAIL_NONFED, Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_14 user navigates to system account directory page$")
	public void _14_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_14 user enters permissions info$")
	public void _14_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
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
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_14 the newly created account should show up on the system account directory page$")
	public void _14_the_newly_created_account_should_show_up_on_the_system_account_directory_page() throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
	}

	@When("^_14 gsa security approver logs in$")
	public void _14_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_14 gsa security approver navigates to system accounts directory page$")
	public void _14_gsa_security_approver_navigates_to_system_accounts_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_14 gsa security approver should be able to approve the request by system account admin$")
	public void _14_gsa_security_approver_should_be_able_to_approve_the_request_by_system_account_admin()
			throws Throwable {

		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestPage.writeComment("request is approved");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButton();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@When("^_14 system account admin logs in again$")
	public void _14_system_account_admin_logs_in_again() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_14 admin goes to system account directory page$")
	public void _14_goes_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@And("^_14 admin generates api key$")
	public void _14_admin_generates_api_key() throws Throwable {
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		SystemAccountRequestPage.enterNewPassword(Constants.USERPASS);
		SystemAccountRequestPage.enterConfirmPassword(Constants.USERPASS);
		SystemAccountRequestPage.clickPasswordSaveButton();
		SystemAccountRequestPage.clickShowApiKeyCheckbox();
		SystemAccountRequestPage.enterShowApiKeyPassword(Constants.USERPASS);
		SystemAccountRequestPage.enterApiKeySubmitButton();
		SystemAccountRequestPage.clickDeactivateButton();
		SystemAccountRequestPage.clickYesDeactivateMyAccount();
		SystemAccountRequestPage.clickYesContinueWithDeactivation();
	}

	@Then("^_14 they should be able to deactivate their system account$")
	public void _14_they_should_be_able_to_deactivate_their_system_account() throws Throwable {
		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_DEACTIVATED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@Given("^_15 user logs in as system account manager$")
	public void _15_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 system manager navigates to system account directory page$")
	public void _15_system_manager_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 system account admin navigates to system account directory page$")
	public void _15_system_account_admin_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_15 admin should be able to change the request status to pending approval$")
	public void _15_admin_should_be_able_to_change_the_request_status_to_pending_review() throws Throwable {
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestPage.writeComment("request is reviewed");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButton();

		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@When("^_15 gsa security approver logs in$")
	public void _15_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 gsa security approver navigates to system accounts directory page$")
	public void _15_gsa_security_approver_navigates_to_system_accounts_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_15 gsa security approver should be able to approve the request by system account manager$")
	public void _15_gsa_security_approver_should_be_able_to_approve_the_request_by_system_account_manager()
			throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		SystemAccountRequestPage.writeComment("request is approved");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@When("^_15 system account manager logs in again$")
	public void _15_system_account_manager_logs_in_again() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_15 manager goes to system account directory page$")
	public void _15_manager_goes_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@And("^_15 manager generates api key$")
	public void _15_man_ager_generates_api_key() throws Throwable {
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		SystemAccountRequestPage.enterNewPassword(Constants.USERPASS);
		SystemAccountRequestPage.enterConfirmPassword(Constants.USERPASS);
		SystemAccountRequestPage.clickPasswordSaveButton();
		SystemAccountRequestPage.clickShowApiKeyCheckbox();
		SystemAccountRequestPage.enterShowApiKeyPassword(Constants.USERPASS);
		SystemAccountRequestPage.enterApiKeySubmitButton();
		SystemAccountRequestPage.clickDeactivateButton();
		SystemAccountRequestPage.clickYesDeactivateMyAccount();
		SystemAccountRequestPage.clickYesContinueWithDeactivation();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword("1548958073");
		SystemAccountDirectoryPage.clickPublishedFilter();

		SystemAccountDirectoryPage.accountFound("1548958073", Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				"Approval Status Email Sent to shah.raiaan+gsasecurityapprover@gsa.gov", 1);

		Assert.assertEquals(true, historystampFound);
	}

	@Given("^_17saaccount user logs in as system account manager$")
	public void _17saaccount_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage(systemManager.toString() + "@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_17saaccount user navigates to system account directory page$")
	public void _17saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+samanager@gsa.gov");
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
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString() + "@gsa.gov");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_17saaccount user enters authorization info$")
	public void _17saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		MyWorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_17saaccount the newly created account should show up on the system account directory page$")
	public void _17saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_17saaccount the system manager should see the account history updated$")
	public void _17saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + gsasecurityapprover + "@gsa.gov", 0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"shah samanager raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

	}

	@When("^_17saaccount system admin changes the request to pending approval$")
	public void _17saaccount_system_admin_changes_the_request_to_pending_approval() throws Throwable {
		/*SignInUtility.signIntoCommonWorkspacePage(systemAdmin.toString() + "@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		CommonMethods.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestPage.writeComment("request is reviewed");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButtonWithoutJavascriptExecutor();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();*/
		CommonMethods.delay(48);

	}

	@Then("^_17saaccount the system admin should see the account history updated$")
	public void _17saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {

		/*boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				Constants.SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO + " " + systemAdmin + "@gsa.gov", 0);

		Assert.assertEquals(true, historystampFound);
*/
		/*
		 * boolean historystampFound2 = SystemAccountRequestPage.accountHistoryFound(
		 * Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
		 * "shah samanager raiaan "+Constants.
		 * SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);
		 * 
		 * Assert.assertEquals(true, historystampFound2);
		 */
CommonMethods.delay(47);
	}

	@When("^_17saaccount gsa security approver logs in$")
	public void _17saaccount_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage(gsasecurityapprover.toString() + "@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_17saaccount gsa security approver approves the reviewed request$")
	public void _17saaccount_gsa_security_approver_approves_the_reviewed_request() throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		//Assert.assertEquals(true, accountFound);

	/*	SystemAccountRequestPage.writeComment("request is approved");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();*/
		CommonMethods.delay(38);
	}

	@Then("^_17saaccount the gsa security approver should see the account history updated$")
	public void _17saaccount_the_gsa_security_approver_should_see_the_account_history_updated() throws Throwable {
		/*boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				Constants.SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO + " " + gsasecurityapprover + "@gsa.gov", 0);

		Assert.assertEquals(true, historystampFound);*/
		CommonMethods.delay(18);

	/*	SystemAccountRequestPage.clickCloseButtonWithoutJavascriptExecutor();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);*/

	}

	@Given("^_18saaccount user logs in as system account manager$")
	public void _18saaccount_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage(systemManager.toString() + "@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_18saaccount user navigates to system account directory page$")
	public void _18saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_18saaccount user enters all the system information$")
	public void _18saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_18saaccount user enters all the organization info$")
	public void _18saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+samanager@gsa.gov");
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
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString() + "@gsa.gov");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_18saaccount user enters authorization info$")
	public void _18saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		MyWorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_18saaccount the newly created account should show up on the system account directory page$")
	public void _18saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_18saaccount the system manager should see the account history updated$")
	public void _18saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + gsasecurityapprover + "@gsa.gov", 0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"shah samanager raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

	}

	@When("^_18saaccount system admin rejects the request$")
	public void _18saaccount_system_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage(systemAdmin.toString() + "@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		CommonMethods.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestPage.writeComment("request is rejected");
		SystemAccountRequestPage.clickRejectButton();
		/*SystemAccountRequestPage.clickCloseButton();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();*/
	}

	@Then("^_18saaccount the system admin should see the account history updated$")
	public void _18saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {
		
		
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		CommonMethods.delay(25);
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONREJECTED,
				"shah saadmin raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_REJECTED, 0);

		//Assert.assertEquals(true, historystampFound);
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Given("^_19saaccount user logs in as nonfed user$")
	public void _19saaccount_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePageNonFed(nonfeduser.toString() + "@gmail.com", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_19saaccount user navigates to system account directory page$")
	public void _19saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
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
		NewSystemAccountPage.selectSystemAdminInOrgInfo("raiaan.shah@gmail.com");
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
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString() + "@gsa.gov");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_19saaccount user enters authorization info$")
	public void _19saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		MyWorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_19saaccount the newly created account should show up on the system account directory page$")
	public void _19saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_19saaccount the nonfed user should see the account history updated$")
	public void _19saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + gsasecurityapprover + "@gsa.gov", 0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"shah nonfed raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

	}

	@When("^_19saaccount gsasecurity approver approves the request$")
	public void _19saaccount_system_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage(gsasecurityapprover.toString() + "@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		CommonMethods.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestPage.writeComment("request is approved");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_19saaccount the gsasecurity approver should see the account history updated$")
	public void _19saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {
		
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);
		
		
		
		
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		CommonMethods.delay(25);
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				"shah saadmin raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_REJECTED, 0);

	//	Assert.assertEquals(true, historystampFound);
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Given("^_20saaccount user logs in as nonfed user$")
	public void _20saaccount_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePageNonFed(nonfeduser.toString() + "@gmail.com", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_20saaccount user navigates to system account directory page$")
	public void _20saaccount_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_20saaccount user enters all the system information$")
	public void _20saaccount_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_20saaccount user enters all the organization info$")
	public void _20saaccount_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo("raiaan.shah+4@gmail.com");
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
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString() + "@gsa.gov");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_20saaccount user enters authorization info$")
	public void _20saaccount_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		MyWorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_20saaccount the newly created account should show up on the system account directory page$")
	public void _20saaccount_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);

		Assert.assertEquals(true, accountFound);
	}

	@Then("^_20saaccount the nonfed user should see the account history updated$")
	public void _20saaccount_the_system_manager_should_see_the_account_history_updated() throws Throwable {
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILSUBMITTED,
				Constants.SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO + " " + gsasecurityapprover + "@gsa.gov", 0);

		Assert.assertEquals(true, historystampFound);

		boolean historystampFound2 = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_APPLICATIONSUBMITTED,
				"shah nonfed raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED, 1);

		Assert.assertEquals(true, historystampFound2);

	}

	@When("^_20saaccount gsasecurity approver rejects the request$")
	public void _20saaccount_system_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage(gsasecurityapprover.toString() + "@gsa.gov", Constants.USERPASS);
		LaunchBrowserUtil.scrollAllTheWayDown();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		CommonMethods.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestPage.writeComment("request is approved");
		SystemAccountRequestPage.clickApproveButton();
		SystemAccountRequestPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_20saaccount the gsasecurity approver should see the account history updated$")
	public void _20saaccount_the_system_admin_should_see_the_account_history_updated() throws Throwable {
		
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PUBLISHED,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);
		
		
		
		
		LaunchBrowserUtil.scrollByVisibleElement(By.className("history-item-0"));
		CommonMethods.delay(25);
		boolean historystampFound = SystemAccountRequestPage.accountHistoryFound(
				Constants.SAHISTORY_STATUS_EMAILAPPROVED,
				"shah saadmin raiaan " + Constants.SAHISTORY_MESSAGE_SUFFIX_APPLICATION_REJECTED, 0);

	//	Assert.assertEquals(true, historystampFound);
		

	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
