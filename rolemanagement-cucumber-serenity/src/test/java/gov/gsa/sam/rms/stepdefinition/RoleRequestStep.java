package gov.gsa.sam.rms.stepdefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.FeedsRequestPageLocator;
import gov.gsa.sam.rms.locators.RequestRolePageLocator;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class RoleRequestStep {

	private static Logger logger = LoggerFactory.getLogger(RoleRequestStep.class);
	String timestamp = new String();
	String comments = new String();
	String updatedComments = new String();

	@Given("^_1 user logs in workspace with no role$")
	public void user_logs_in_workspace_with_no_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user requests assistance user role in assistance listing$")
	public void user_requests_assistance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("shah.raiaan@gsa.gov");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test comments");
		RequestRolePage.clickSubmit();
	}

	@Then("^_1 this request should be visible in Sent tab for the requester in their feeds and for assistance admin$")
	public void this_request_should_be_visible_in_sent_tab_for_the_requester_in_their_feeds() throws Throwable {
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickSentOnSideBar();
		LaunchBrowserUtil.delay(3);
		String timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.closeBrowsers();
		// --------------------login as assistance admin------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		// LaunchBrowserUtil.scrollToMiddle();
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(4);
		boolean sameRequestFound = FeedsRequestPage.requestFound("", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_PENDING, Constants.REJECTROLE);
		Assert.assertEquals(true, sameRequestFound);
		afterScenario();
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2 user logs in workspace with no role and GSA as default org$")
	public void user_logs_in_workspace_with_no_role_and_gsa_as_default_org() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user navigates to request role page$")
	public void user_navigates_to_request_role_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("email@gmail.com");

	}

	@Then("^_2 organization text box suggestions should only show GSA orgs$")
	public void organization_text_box_suggestions_should_only_show_gsa_orgs() throws Throwable {

		boolean correctOrgShown = RequestRolePage.validateOrgSuggestionContainsGivenWord("human", "47");
		Assert.assertEquals(correctOrgShown, true);
		afterScenario();

		LaunchBrowserUtil.delay(7);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_3 no role user logs into workspace$")
	public void _3_no_role_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 user requests assistance user role in assistance listing$")
	public void _3_user_requests_assistance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("email@gmail.com");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test comments");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(3);
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_3 this request should be visible in received tab for RM admin in feeds$")
	public void _3_this_request_should_be_visible_in_received_tab_for_rm_admin_in_feeds() throws Throwable {

		// --------------------login as RM admin-------------------------

		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollAllTheWayDown();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(3);
		boolean requestFound = FeedsRequestPage.requestFound("", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(true, requestFound);

	}

	@And("^_3 RM admin assigns the same role to the user without approving the pending request$")
	public void _3_rm_admin_assigns_the_same_role_to_the_user_without_approving_the_pending_request() throws Throwable {
		FeedsRequestPage.goToWorkspacePage();
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 1);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_3 the pending request should appear as complete in the feeds$")
	public void _3_the_pending_request_should_appear_as_complete_in_the_feeds() throws Throwable {
		LaunchBrowserUtil.delay(2);
		AssignRolePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickCompletedFilter();
		LaunchBrowserUtil.delay(1);

		boolean requestFound = FeedsRequestPage.requestFound("", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_COMPLETE, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_3 the requestor logs into their account$")
	public void _3_the_requestor_logs_into_their_account() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_3 the requester see the request updated as complete in feeds$")
	public void _3_the_requester_see_the_request_updated_as_complete_in_feeds() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickCompletedFilter();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_COMPLETE, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
	}

	@Then("^_3 the requester see the updated role in my roles page$")
	public void _3_the_requester_see_the_updated_role_in_my_roles_page() throws Throwable {
		LaunchBrowserUtil.scrollUp();
		FeedsRequestPage.goToWorkspacePage();
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		LaunchBrowserUtil.delay(2);
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
		// --------------------------delete the role---------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		LaunchBrowserUtil.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		LaunchBrowserUtil.delay(2);
		// delete the role for the user
		userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		LaunchBrowserUtil.delay(8);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_4 no role user logs into workspace$")
	public void _4_no_role_user_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4 user requests assistance user role in assistance listing$")
	public void _4_user_requests_assistance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("a@b.c");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_4 dra logs into common workspace$")
	public void _4_dra_logs_into_common_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);

	}

	@Then("^_4 they should be able to approve role for the requester$")
	public void _4_they_should_be_able_to_approve_role_for_the_requester() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		String noRoleUser = ConstantsAccounts.NO_ROLE_USER_2;
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(noRoleUser);
		UserDirectoryPage.clickViewAccess(noRoleUser);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();

		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("giving this role");
		AssignRolePage.clickAssign();

		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, "DELETE");
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Given("^_5 no role user logs into workspace$")
	public void _5_no_role_user_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5 user requests assitance user role in assistance listing$")
	public void _5_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("email@gmail.com");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test comments");
		RequestRolePage.clickSubmit();

		// Get request timestamp
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickPendingFilter();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_5 dra logs in and verfies the feeds for the request$")
	public void _5_dra_logs_in_and_verfies_the_feeds_for_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);

		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickRoleRequestFilter();
		boolean requestFound = FeedsRequestPage.requestFound("SHAH noroless2 RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_PENDING, Constants.REJECTROLE);

		Assert.assertEquals(requestFound, true);

		// -----assert with 'role request' filter---------
		MyRolesPage.setDriver(FeedsRequestPage.getDriver());
		LaunchBrowserUtil.delay(4);
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickRoleRequestFilter();
		requestFound = FeedsRequestPage.requestFound("SHAH noroless2 RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_REJECTED, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);// assert with 'role request'
												// filter
	}

	@Given("^_6 assistance user logs into workspace$")
	public void _6_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);

	}

	@And("^_6 the user navigates to my roles page to request contracting officer role$")
	public void _6_the_user_navigates_to_my_roles_page_to_request_contracting_officer_role() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("email@gmail.com");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		comments = "this is the first comment";
		RequestRolePage.writeComment(comments);
		RequestRolePage.clickSubmit();
	}

	@When("^_6 user updates the comment of the from the pending request link$")
	public void _6_user_updates_the_comment_of_the_from_the_pending_request_link() throws Throwable {
		LaunchBrowserUtil.delay(2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		updatedComments = "Comments updated";
		LaunchBrowserUtil.delay(1);
		RoleRequestPendingPage.updateComment(updatedComments);
	}

	@Then("^_6 the user should see the updated comment$")
	public void _6_the_user_should_see_the_updated_comment() throws Throwable {
		boolean updatedCommentsFound = RoleRequestPendingPage.commentsExist(updatedComments);
		Assert.assertEquals(true, updatedCommentsFound);
	}

	@And("^_6 the user should be able to delete the request$")
	public void _6_the_user_should_be_able_to_delete_the_request() throws Throwable {
		LaunchBrowserUtil.delay(2);
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
	}

	@Given("^_7 assistance user logs into workspace$")
	public void _7_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_7 the user navigates to my roles page to request contracting officer role$")
	public void _7_the_user_navigates_to_my_roles_page_to_request_contracting_officer_role() throws Throwable {
		Assert.assertTrue(false);// intentionally failing until bug is fixed
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("email@gmail.com");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		comments = "test";
		RequestRolePage.writeComment(comments);
		RequestRolePage.clickSubmit();
	}

	@And("^_7 the user then updates the comments$")
	public void _7_the_user_then_updates_the_comments() throws Throwable {
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();

		boolean commentsFound = RoleRequestPendingPage.commentsExist(comments);

		Assert.assertEquals(true, commentsFound);
		updatedComments = "Comments updated";

		RoleRequestPendingPage.updateComment(updatedComments);
		LaunchBrowserUtil.delay(2);

		boolean updatedCommentsFound = RoleRequestPendingPage.commentsExist(updatedComments);
		Assert.assertEquals(true, updatedCommentsFound);

	}

	@Then("^_7 the user should be able to sign out$")
	public void _7_the_user_should_be_able_to_sign_out() throws Throwable {
		RoleRequestPendingPage.signOut();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_7 role admin logs in$")
	public void _7_role_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_7 role admin should see both the original and the updated comments$")
	public void _7_role_admin_should_see_both_the_original_and_the_updated_comments() throws Throwable {

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryViewAccessPage.setDriver(RoleRequestPendingPage.getDriver());
		LaunchBrowserUtil.delay(2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();

		LaunchBrowserUtil.delay(2);

		/*
		 * boolean originalCommentsFound = RoleRequestPendingPage.commentsExist("",
		 * comments); Assert.assertEquals(true, originalCommentsFound);
		 * 
		 * boolean updatedCommentsFound = RoleRequestPendingPage.commentsExist("",
		 * updatedComments); Assert.assertEquals(true, updatedCommentsFound);
		 */
		LaunchBrowserUtil.delay(1);
		RoleRequestPendingPage.enterAdditionalInformation("rejecting this");
		RoleRequestPendingPage.clickRejectButton();
	}

	@Given("^_8 assistance user logs into workspace$")
	public void _8_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_8 the user navigates to request roles page$")
	public void _8_the_user_navigates_to_request_roles_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();

	}

	@When("^_8 the user clicks submit button without entering information$")
	public void _8_the_user_clicks_submit_button_without_entering_information() throws Throwable {
		RequestRolePage.clickSubmit();
	}

	@Then("^_8 the user should see all the error messages showing up$")
	public void _8_the_user_should_see_all_the_error_messages_showing_up() throws Throwable {

		boolean supervisornameErrorFound = RequestRolePage
				.elementFound(RequestRolePageLocator.ERRORMESSAGE_SUPERVISOR_NAME);
		Assert.assertEquals(true, supervisornameErrorFound);
		boolean supervisoremailErrorFound = RequestRolePage
				.elementFound(RequestRolePageLocator.ERRORMESSAGE_SUPERVISOR_EMAIL);
		Assert.assertEquals(true, supervisoremailErrorFound);
		boolean orgErrorFound = RequestRolePage.elementFound(RequestRolePageLocator.ERRORMESSAGE_ORG);
		Assert.assertEquals(true, orgErrorFound);
		boolean roleErrorFound = RequestRolePage.elementFound(RequestRolePageLocator.ERRORMESSAGE_ROLE);
		Assert.assertEquals(true, roleErrorFound);
		boolean domainErrorFound = RequestRolePage.elementFound(RequestRolePageLocator.ERRORMESSAGE_DOMAIN);
		Assert.assertEquals(true, domainErrorFound);
		boolean commentErrorFound = RequestRolePage.elementFound(RequestRolePageLocator.ERRORMESSAGE_ADDITIONALDETAILS);
		Assert.assertEquals(true, commentErrorFound);
	}

	@Given("^_9 assistance user logs into workspace$")
	public void _9_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_9 the user navigates to my roles page to request contracting officer role$")
	public void _9_the_user_navigates_to_my_roles_page_to_request_contracting_officer_role() throws Throwable {

		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("shah.raiaan@gsa.gov");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		comments = "test";
		RequestRolePage.writeComment(comments);
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(3);

		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		LaunchBrowserUtil.delay(3);
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.closeBrowsers();

	}

	@And("^_9 role admin looks up the request in feeds through pending role request link$")
	public void _9_role_admin_looks_up_the_request_in_feeds_through_pending_role_request_link() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);

		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(4);
		boolean sameRequestFound = FeedsRequestPage.requestFound("", Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(true, sameRequestFound);
		afterScenario();
		LaunchBrowserUtil.delay(6);

	}

	@And("^_9 role admin looks up users profile page to see pending request link and rejects the request$")
	public void _9_role_admin_looks_up_users_profile_page_to_see_pending_request_link_and_rejects_the_request()
			throws Throwable {
		FeedsRequestPage.goToWorkspacePage();
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		MyRolesPage.writeAdditionalInformation("rejecting this request");
		RoleRequestPendingPage.clickRejectButton();

		LaunchBrowserUtil.delay(17);
	}

	@Then("^_9 role admin should see the request status change in the feeds$")
	public void _9_role_admin_should_see_the_request_status_change_in_the_feeds() throws Throwable {
		LaunchBrowserUtil.delay(45);
	}

	@Given("^_10 assistance user logs into workspace$")
	public void _10_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_10 the user navigates to my roles page to request contracting officer role$")
	public void _10_the_user_navigates_to_my_roles_page_to_request_contracting_officer_role() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("shah.raiaan@gsa.gov");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		comments = "test";
		RequestRolePage.writeComment(comments);
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(3);

	}

	@Then("^_10 the supervisor should receive role request email$")
	public void _10_the_role_admin_should_receive_role_request_email() throws Throwable {
		/*
		 * SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov",
		 * Constants.userPass); String emailText =
		 * LaunchBrowserUtil.captureTitleFromLastEmail(1);
		 * Assert.assertEquals(emailText.contains(Constants.
		 * EMAIL_ROLEREQUEST_MESSAGE_FOR_SUPERVISOR), true);
		 */

		LaunchBrowserUtil.delay(14);

	}

	@And("^_10 role admin looks up the request in feeds through pending role request link$")
	public void _10_role_admin_looks_up_the_request_in_feeds_through_pending_role_request_link() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);

		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(4);
		boolean sameRequestFound = FeedsRequestPage.requestFound("", Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(true, sameRequestFound);
		afterScenario();
		LaunchBrowserUtil.delay(6);
	}

	@And("^_10 role admin looks up users profile page to see pending request link and approves the request$")
	public void _10_role_admin_looks_up_users_profile_page_to_see_pending_request_link_and_rejects_the_request()
			throws Throwable {
		FeedsRequestPage.goToWorkspacePage();
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		MyRolesPage.writeAdditionalInformation("approving this request");
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("approving this request");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_10 role admin should see the request status change in the feeds$")
	public void _10_role_admin_should_see_the_request_status_change_in_the_feeds() throws Throwable {
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Given("^_11 spaad logs into workspace$")
	public void _11_spaad_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_11 the user navigates to feeds page$")
	public void _11_the_user_navigates_to_feeds_page() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
	}

	@When("^_11 spaad goes through the feeds requests$")
	public void _11_spaad_goes_through_the_feeds_requests() throws Throwable {
		int totalNoOfPages = FeedsRequestPage.getTotalNoOfPages();
		int currentPage = 1;

		do {// search page 1 regardless of whether other pages exist

			List<WebElement> feedsList = FeedsRequestPage.getFeedsList();
			

			for (int i = 0; i < feedsList.size(); i++) {
				/*WebElement description = feedsList.get(i).findElement(FeedsRequestPageLocator.REQUESTER_NAME);
				String requestername = description.getText();*/
				WebElement descriptionelement = feedsList.get(i).findElement(FeedsRequestPageLocator.MESSAGE_FEED_DESCRIPTION);
				String description = descriptionelement.getText();
				String descriptionwordarray[]= description.split(" ");
				String requestername = descriptionwordarray[0]+descriptionwordarray[1];
				logger.info("The name of the requester is -- "+requestername);
				Assert.assertTrue(FeedsRequestPage.isStringOnlyAlphabetAndSpace(requestername));
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				FeedsRequestPage.clickPageNo(currentPage, totalNoOfPages);
			}

		} while (currentPage < totalNoOfPages);
		
		
		
	}

	@Then("^_11 should see requester name appearing as expected without any comma$")
	public void _11_should_see_requester_name_appearing_as_expected_without_any_comma() throws Throwable {

	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
