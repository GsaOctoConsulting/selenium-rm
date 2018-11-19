package gov.gsa.sam.rms.stepdefinition;

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

	@Given("^_1 user logs in as system account admin$")
	public void _1_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
	}

	@And("^_1 user navigates to system account directory page$")
	public void _1_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_1 user enters all the system information$")
	public void _1_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test");
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
		LaunchBrowserUtil.scrolldownToRmWidget();
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

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test", Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_2 user logs in as system account manager$")
	public void _2_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
	}

	@And("^_2 user navigates to system account directory page$")
	public void _2_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_2 user enters all the system information$")
	public void _2_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName("test");
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
		LaunchBrowserUtil.scrolldownToRmWidget();
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

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test", Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_3 user logs in as gsa security approver$")
	public void _3_user_logs_in_as_gsa_security_approver() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
		comments = "Test comment";
		NewSystemAccountPage.writeComment(comments);
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5 gsa security approver logs in$")
	public void _5_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+gsasecurityapprover@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		boolean commentFound = SystemAccountRequestPage.commentFound("shah.raiaan+samanager@gsa.gov", comments);
		Assert.assertEquals(true, commentFound);
	}

	@Then("^_5 user should be able to see the new account and view the comments posted$")
	public void _5_user_should_be_able_to_see_the_new_account_and_view_the_comments_posted() throws Throwable {
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
	}

	@And("^_6 _user navigates to system account directory page$")
	public void _6_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();

	}

	@When("^_6 user clicks on review tab then comment text box should not be available$")
	public void _6_user_clicks_on_review_tab_then_comment_text_box_should_not_be_available() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.userPass);

	}

	@And("^_7 user navigates to system account directory page$")
	public void _7_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
		NewSystemAccountPage.writeComment("   ");
	}

	@Then("^_7 error message should pop up$")
	public void _7_error_message_should_pop_up() throws Throwable {
		String alertMessage = NewSystemAccountPageLocator.COMMENT_ERROR;
		String alertMessageFound = NewSystemAccountPage.getAlertMessage();
		Assert.assertEquals(alertMessage, alertMessageFound);
		//---------delete the draft account--------
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+samanager@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
	}

	@And("^_8 _user navigates to system account directory page$")
	public void _8_user_navigates_to_system_account_directory_page() throws Throwable {
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();

	}

	@When("^_8 user clicks on review tab then comment text box should not be available$")
	public void _8_user_clicks_on_review_tab_then_comment_text_box_should_not_be_available() throws Throwable {
		NewSystemAccountPage.clickReviewTab();
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+saadmin@gsa.gov", Constants.userPass);

	}

	@And("^_9 user navigates to system account directory page$")
	public void _9_user_navigates_to_system_account_directory_page() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
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
		LaunchBrowserUtil.scrolldownToRmWidget();
		NewSystemAccountPage.writeComment("   ");
	}

	@Then("^_9 error message should pop up$")
	public void _9_error_message_should_pop_up() throws Throwable {
		String alertMessage = NewSystemAccountPageLocator.COMMENT_ERROR;
		String alertMessageFound = NewSystemAccountPage.getAlertMessage();
		Assert.assertEquals(alertMessage, alertMessageFound);
		//---------delete the draft account--------
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		MyWorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickDraftFilter();

		boolean accountFound = SystemAccountDirectoryPage.accountFound("test_v1", Constants.STATUS_DRAFT, "", "",
				Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}
	
	
	
	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
