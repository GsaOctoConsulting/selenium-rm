package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import junit.framework.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class RoleRequestStep {

	private static Logger logger = LoggerFactory.getLogger(RoleRequestStep.class);
	String timestamp = new String();
	String comments = new String();
	String updatedComments = new String();

	@Given("^_1 user logs in workspace with no role$")
	public void user_logs_in_workspace_with_no_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+noroless@gsa.gov", Constants.userPass);
	}

	@And("^_1 user requests assistance user role in assistance listing$")
	public void user_requests_assistance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
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
	}

	@Then("^_1 this request should be visible in Sent tab for the requester in their feeds and for assistance admin$")
	public void this_request_should_be_visible_in_sent_tab_for_the_requester_in_their_feeds() throws Throwable {
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		String timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
		CommonMethods.delay(1);
		LaunchBrowserUtil.closeBrowsers();
		// --------------------login as assistance admin------------------
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+assistanceadminv2@gsa.gov", Constants.userPass);
		MyWorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickPendingFilter();
		CommonMethods.delay(4);
		boolean sameRequestFound = FeedsRequestPage.requestFound("SHAH noRoless RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_PENDING, Constants.REJECTROLE);
		Assert.assertEquals(true, sameRequestFound);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2 user logs in workspace with no role and GSA as default org$")
	public void user_logs_in_workspace_with_no_role_and_gsa_as_default_org() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+noRoless@gsa.gov", Constants.userPass);
	}

	@And("^_2 user navigates to request role page$")
	public void user_navigates_to_request_role_page() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("email@gmail.com");

	}

	@Then("^_2 organization text box suggestions should only show GSA orgs$")
	public void organization_text_box_suggestions_should_only_show_gsa_orgs() throws Throwable {

		boolean correctOrgShown = RequestRolePage.validateOrgSuggestionContainsGivenWord("human", "Dept: GSA");
		Assert.assertEquals(correctOrgShown, true);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_3 no role user logs into workspace$")
	public void _3_no_role_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+noroless@gsa.gov", Constants.userPass);
	}

	@And("^_3 user requests assistance user role in assistance listing$")
	public void _3_user_requests_assistance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
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
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
		CommonMethods.delay(1);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_3 this request should be visible in received tab for RM admin in feeds$")
	public void _3_this_request_should_be_visible_in_received_tab_for_rm_admin_in_feeds() throws Throwable {

		// --------------------login as RM admin-------------------------
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.userPass);
		MyWorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		boolean requestFound = FeedsRequestPage.requestFound("SHAH noroless RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_PENDING, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);

	}

	@And("^_3 RM admin assigns the same role to the user without approving the pending request$")
	public void _3_rm_admin_assigns_the_same_role_to_the_user_without_approving_the_pending_request() throws Throwable {
		FeedsRequestPage.goToWorkspacePage();
		LaunchBrowserUtil.scrolldownToRmWidget();

		RMWidgetUtility.clickUserDirectoryLink();
		String user = "shah.raiaan+noroless@gsa.gov";
		UserDirectoryPage.searchUserInUserPicker(user);
		UserDirectoryPage.clickAssignRole(user);
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 1);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_3 the pending request should appear as complete in the feeds$")
	public void _3_the_pending_request_should_appear_as_complete_in_the_feeds() throws Throwable {
		CommonMethods.delay(2);
		AssignRolePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickCompletedFilter();
		CommonMethods.delay(1);

		boolean requestFound = FeedsRequestPage.requestFound("SHAH noroless RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_COMPLETE, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
		CommonMethods.delay(2);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_3 the requestor logs into their account$")
	public void _3_the_requestor_logs_into_their_account() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+noroless@gsa.gov", Constants.userPass);
	}

	@Then("^_3 the requester see the request updated as complete in feeds$")
	public void _3_the_requester_see_the_request_updated_as_complete_in_feeds() throws Throwable {
		MyWorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickCompletedFilter();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				timestamp, Constants.STATUS_COMPLETE, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
	}

	@Then("^_3 the requester see the updated role in my roles page$")
	public void _3_the_requester_see_the_updated_role_in_my_roles_page() throws Throwable {
		FeedsRequestPage.goToWorkspacePage();
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		CommonMethods.delay(2);
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(roleFound, true);
		CommonMethods.delay(2);
		LaunchBrowserUtil.closeBrowsers();
		// --------------------------delete the role---------
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
		String noRoleUserId = "shah.raiaan+noroless@gsa.gov";
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(noRoleUserId);
		UserDirectoryPage.clickViewAccess(noRoleUserId);
		CommonMethods.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);

		// delete the role for the user
		userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		CommonMethods.delay(2);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_4 no role user logs into workspace$")
	public void _4_no_role_user_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+noRoles@gsa.gov", Constants.userPass);
	}

	@And("^_4 user requests assistance user role in assistance listing$")
	public void _4_user_requests_assistance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+deptAdminSelenium@gsa.gov", Constants.userPass);
	}

	@Then("^_4 they should be able to approve role for the requester$")
	public void _4_they_should_be_able_to_approve_role_for_the_requester() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		String noRoleUser = "shah.raiaan+noRoles@gsa.gov";
		RMWidgetUtility.clickUserDirectoryLink();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+noRoles@gsa.gov", Constants.userPass);
	}

	@And("^_5 user requests assitance user role in assistance listing$")
	public void _5_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
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
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+deptAdminSelenium@gsa.gov", Constants.userPass);
		MyWorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickRoleRequestFilter();
		boolean requestFound = FeedsRequestPage.requestFound("SHAH noRoles RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_PENDING, Constants.REJECTROLE);

		Assert.assertEquals(requestFound, true);

		// -----assert with 'role request' filter---------
		MyRolesPage.setDriver(FeedsRequestPage.getDriver());
		CommonMethods.delay(4);
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickRoleRequestFilter();
		requestFound = FeedsRequestPage.requestFound("SHAH noRoles RAIAAN", Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, timestamp, Constants.STATUS_REJECTED, Constants.NOACTION);
		Assert.assertEquals(requestFound, true);// assert with 'role request'
												// filter
	}

	@Given("^_6 assistance user logs into workspace$")
	public void _6_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+assistanceuserv2@gsa.gov", Constants.userPass);

	}

	@And("^_6 the user navigates to my roles page to request contracting officer role$")
	public void _6_the_user_navigates_to_my_roles_page_to_request_contracting_officer_role() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
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

	@Then("^_6 user should receive role request submit email$")
	public void _6_user_should_receive_role_request_submit_email() throws Throwable {
		String emailText = LaunchBrowserUtil.captureTitleFromLastEmail();
		Assert.assertEquals(emailText.contains(Constants.EMAIL_ROLEREQUEST_MESSAGE), true);
	}

	@When("^_6 user updates the comment of the from the pending request link$")
	public void _6_user_updates_the_comment_of_the_from_the_pending_request_link() throws Throwable {
		CommonMethods.delay(2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		boolean commentsFound = RoleRequestPendingPage.commentsExist("shah.raiaan+assistanceUserSelenium@gsa.gov",
				comments);
		Assert.assertEquals(true, commentsFound);
		String updatedComments = "Comments updated";
		CommonMethods.delay(1);
		RoleRequestPendingPage.updateComment(updatedComments);
	}

	@Then("^_6 the user should see the updated comment$")
	public void _6_the_user_should_see_the_updated_comment() throws Throwable {
		boolean updatedCommentsFound = RoleRequestPendingPage
				.commentsExist("shah.raiaan+assistanceUserSelenium@gsa.gov", comments);
		Assert.assertEquals(true, updatedCommentsFound);
	}

	@And("^_6 the user should be able to delete the request$")
	public void _6_the_user_should_be_able_to_delete_the_request() throws Throwable {
		CommonMethods.delay(2);
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
	}

	@Given("^_7 assistance user logs into workspace$")
	public void _7_assistance_user_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+assistanceuserv2@gsa.gov", Constants.userPass);
	}

	@And("^_7 the user navigates to my roles page to request contracting officer role$")
	public void _7_the_user_navigates_to_my_roles_page_to_request_contracting_officer_role() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
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
		boolean commentsFound = RoleRequestPendingPage.commentsExist("", comments);
		Assert.assertEquals(true, commentsFound);
		updatedComments = "Comments updated";

		RoleRequestPendingPage.updateComment(updatedComments);
		CommonMethods.delay(2);
		boolean updatedCommentsFound = RoleRequestPendingPage.commentsExist("",
				updatedComments);
		Assert.assertEquals(true, updatedCommentsFound);
	}

	@Then("^_7 the user should be able to sign out$")
	public void _7_the_user_should_be_able_to_sign_out() throws Throwable {
		RoleRequestPendingPage.signOut();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_7 role admin logs in$")
	public void _7_role_admin_logs_in() throws Throwable {

		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
	}

	@Then("^_7 role admin should see both the original and the updated comments$")
	public void _7_role_admin_should_see_both_the_original_and_the_updated_comments() throws Throwable {
		String assistanceUserId = "shah.raiaan+assistanceuserv2@gsa.gov";
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(assistanceUserId);
		UserDirectoryPage.clickViewAccess(assistanceUserId);
		RolesDirectoryViewAccessPage.setDriver(RoleRequestPendingPage.getDriver());
		CommonMethods.delay(2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		
		CommonMethods.delay(2);

		boolean originalCommentsFound = RoleRequestPendingPage.commentsExist("",
				comments);
		Assert.assertEquals(true, originalCommentsFound);

		boolean updatedCommentsFound = RoleRequestPendingPage.commentsExist("",updatedComments);
		Assert.assertEquals(true, updatedCommentsFound);
		CommonMethods.delay(1);
		RoleRequestPendingPage.clickRejectButton();
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
