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
import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.ForBiddenPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RequestRoleOptionalPage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;

public class NonFedStep {

	private static Logger logger = LoggerFactory.getLogger(NonFedStep.class);
	String timestamp = new String();
	String newsignedupnonfeduser = "";
	String newsignedupnonfedusersecretkey = "";

	@Given("^_1nf nonfed user without a role logs in$")
	public void _1_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);

	}

	@Then("^_1nf nonfed user should be able to view entity management Widget$")
	public void _1_nonfed_user_should_be_able_to_view_entity_management_widget() throws Throwable {
		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);

		boolean registerentitybuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.REGISTER_ENTITY_BUTTON);
		Assert.assertEquals(true, registerentitybuttonfound);

		boolean getentityIdbuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.GET_ENTITYID_BUTTON);
		Assert.assertEquals(true, getentityIdbuttonfound);
		afterScenario();
	}

	@When("^_1nf nonfed user navigates to the profile page$")
	public void _1nf_nonfed_user_navigates_to_the_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_1nf they should not see the entity details section$")
	public void _1nf_they_should_not_see_the_entity_details_section() throws Throwable {
		boolean entitysectionFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.ENTITY_INFO);
		Assert.assertEquals(false, entitysectionFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2nf nonfed user without a role logs in$")
	public void _2nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_2nf nonfed user navigates to profile page$")
	public void _2nf_nonfed_user_navigates_to_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
	}

	@Then("^_2nf user should have no roles and should see that message$")
	public void _2nf_user_should_have_no_roles() throws Throwable {
		String noroletext = MyRolesPage.getTextForNoRoleUser();
		Assert.assertEquals("You Currently Have No Roles Assigned", noroletext);
	}

	@When("^_2nf user clicks role request button to go to role request page$")
	public void _2nf_user_clicks_role_request_button_to_go_to_role_request_page() throws Throwable {
		MyRolesPage.clickRequestRoleButton();
	}

	@Then("^_2nf nonfed user should see the expected list of role to choose from$")
	public void _2nf_nonfed_user_should_see_the_expected_list_of_role_to_choose_from() throws Throwable {
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		Assert.assertEquals(true, domainfound1);

		boolean roleFound2 = RequestRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound2);

		boolean domainfound2 = RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainfound2);

		boolean roleFound3 = RequestRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound3);

		boolean domainfound3 = RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		Assert.assertEquals(true, domainfound3);
	}

	@Given("^_3nf nonfed user without a role logs in$")
	public void _3nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@When("^_3nf nonfed user requests data entry role in entity compliance$")
	public void _3nf_nonfed_user_requests_data_entry_role_in_entity_compliance() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();

		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();

		MyRolesPage.goToFeedsPage();
	}

	@Then("^_3nf user should see pending notification and feeds entry for the request$")
	public void _3nf_user_should_see_pending_notification_and_feeds_entry_for_the_request() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_3nf spaad logs in$")
	public void _3nf_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_3nf spaad should see the users pending request$")
	public void _3nf_spaad_should_see_the_users_pending_request() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(3);
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
	}

	@When("^_3nf spaad approves the request$")
	public void _3nf_spaad_approves_the_request() throws Throwable {
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("assigning this role");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
		// ----------
		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan",
				Constants.ROLEHISTORY_STATUS_ROLE_ASSIGNED, Constants.ROLE_DATA_ENTRY,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.GO_INTO_ROLE_ASSIGNED);
		Assert.assertEquals(true, roleHistoryFound);

		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.navigateBack();
		LaunchBrowserUtil.delay(3);
		AssignRolePage.goToFeedsPage();
	}

	@Then("^_3nf spaad should see the status updated in their feeds$")
	public void _3nf_spaad_should_be_see_the_status_updated_in_their_feeds() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		FeedsRequestPage.clickApprovedFilter();
		LaunchBrowserUtil.scrollUp();
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_APPROVED,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_3nf nonfed user logs back in$")
	public void _3nf_nonfed_user_logs_back_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_3nf user should see the role assigned$")
	public void _3nf_user_should_see_the_role_assigned() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_DATA_ENTRY,
				Constants.DOMAIN_ENTITY_COMPLIANCE, Constants.NOACTION);

		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan", Constants.ASSIGNED,
				Constants.ROLE_DATA_ENTRY, Constants.ORG_OCTO_CONSULTING_GROUP, Constants.NOACTION);
		Assert.assertEquals(true, roleHistoryFound);
	}

	@And("^_3nf user should see the feeds notifications for the requested updated to approved$")
	public void _3nf_user_should_see_the_feeds_notifications_for_the_requested_updated_to_approved() throws Throwable {
		MyRolesPage.goToFeedsPage();
		FeedsRequestPage.clickRoleRequestFilter();
		FeedsRequestPage.clickApprovedFilter();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_APPROVED,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		// LaunchBrowserUtil.closeBrowsers();

		// ------------------------------deleting the role for this
		// user-------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		LaunchBrowserUtil.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);
		LaunchBrowserUtil.delay(8);
		LaunchBrowserUtil.closeBrowsers();
		afterScenario();
	}

	@Given("^_4nf nonfed user without a role logs in$")
	public void _4nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_4nf nonfed user requests data entry role in entity registration$")
	public void _4nf_nonfed_user_requests_data_entry_role_in_entity_compliance() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();
	}

	@Then("^_4nf user should see pending notification and feeds entry for the request$")
	public void _4nf_user_should_see_pending_notification_and_feeds_entry_for_the_request() throws Throwable {
		MyRolesPage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		FeedsRequestPage.goToWorkspacePage();
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
	}

	@When("^_4nf user cancels the pending request$")
	public void _4nf_user_cancels_the_pending_request() throws Throwable {
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
	}

	@Then("^_4nf user should see the canceled status in the feeds notifications for the request$")
	public void _4nf_user_should_see_the_canceled_status_in_the_feeds_notifications_for_the_request() throws Throwable {
		MyRolesPage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, timestamp, Constants.STATUS_CANCELED,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
	}

	@Given("^_5nf nonfed user without a role logs in$")
	public void _5nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_5nf nonfed user requests data entry role in contract opportunities$")
	public void _5nf_nonfed_user_requests_data_entry_role_in_contract_opportunities() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();
		MyRolesPage.goToFeedsPage();
	}

	@Then("^_5nf user should see pending notification and feeds entry for the request$")
	public void _5nf_user_should_see_pending_notification_and_feeds_entry_for_the_request() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5nf spaad log in and rejects the pending request$")
	public void _5nf_spaad_log_in_and_rejects_the_pending_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_NONFED);

		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(3);
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, timestamp, Constants.STATUS_PENDING,
				Constants.REJECTROLE);
		Assert.assertEquals(true, requestFound);
	}

	@Then("^_5nf spaad should see the rejected status in the feeds notifications for the request$")
	public void _5nf_spaad_should_see_the_rejected_status_in_the_feeds_notifications_for_the_request()
			throws Throwable {
		MyRolesPage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();

		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, timestamp,
				Constants.STATUS_REJECTED, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);

	}

	@When("^_5nf nonfed user logs back in$")
	public void _5nf_nonfed_user_logs_back_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_5nf user should see the feeds updated with rejected status for the request$")
	public void _5nf_user_should_see_the_feeds_updated_with_rejected_status_for_the_request() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		FeedsRequestPage.clickRejectedFilter();
		LaunchBrowserUtil.scrollUp();

		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, timestamp,
				Constants.STATUS_REJECTED, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);

	}

	@Given("^_6nf nonfed user without a role logs in$")
	public void _6nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_6nf user requests data entry role in entity compliance$")
	public void _6nf_user_requests_data_entry_role_in_entity_compliance() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();

		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_6nf spaad logs in$")
	public void _6nf_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_6nf spaad assigns the same role to the user without approving the pending request$")
	public void _6nf_spaad_assigns_the_same_role_to_the_user_without_approving_the_pending_request() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_6nf the pending request should appear as complete in the feeds$")
	public void _6nf_the_pending_request_should_appear_as_complete_in_the_feeds() throws Throwable {
		LaunchBrowserUtil.delay(2);
		AssignRolePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		FeedsRequestPage.clickCompletedFilter();
		LaunchBrowserUtil.delay(3);

		boolean requestFound = FeedsRequestPage.requestFound("", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_COMPLETE,
				Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_6nf the nonfed requestor logs into their account$")
	public void _6nf_the_nonfed_requestor_logs_into_their_account() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_6nf the requester should also see the request updated as complete in feeds$")
	public void _6nf_the_requester_should_also_see_the_request_updated_as_complete_in_feeds() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickCompletedFilter();
		boolean requestFound = FeedsRequestPage.requestFound("", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_COMPLETE,
				Constants.NOACTION);
		Assert.assertEquals(requestFound, true);
	}

	@And("^_6nf the requester will also see the updated role in my roles page$")
	public void _6nf_the_requester_will_also_see_the_updated_role_in_my_roles_page() throws Throwable {
		LaunchBrowserUtil.scrollUp();
		FeedsRequestPage.goToWorkspacePage();
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		LaunchBrowserUtil.delay(2);
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_DATA_ENTRY,
				Constants.DOMAIN_ENTITY_COMPLIANCE, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();

		// --------------------------delete the role for the user----------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		LaunchBrowserUtil.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		LaunchBrowserUtil.delay(2);
		// delete the role for the user
		userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ENTITY_COMPLIANCE, Constants.DELETE);
		LaunchBrowserUtil.delay(8);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_7nf nonfed user attempts to signup for an account$")
	public void _7nf_nonfed_user_attempts_to_signup_for_an_account() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
//		SignUpUtility.signUpNewUserNonFedTemporary("raiaan.zyx+newregisterednonfeduser" + counter + "@gmail.com",
//				Constants.USERPASS);
		SignUpUtility.signUpNewUserNonFed("nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com",
				Constants.USERPASS);
	}

	@And("^_7nf user goes through all the identity verification$")
	public void _7nf_user_goes_through_all_the_identity_verification() throws Throwable {

	}

	@Then("^_7nf user should not see the entity section in common profile page$")
	public void _7nf_user_should_not_see_the_entity_section_in_common_profile_page() throws Throwable {
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		boolean entitysectionFound = CommonProfilePage.elementFound(AccountDetailsPageLocator.ENTITY_INFO);
		Assert.assertEquals(false, entitysectionFound);

		CommonProfilePage.clickSubmitButton();

	}

	@And("^_7nf user should be able to skip role request and land on workspace page$")
	public void _7nf_user_should_be_able_to_skip_role_request_and_land_on_workspace_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@And("^_7nf user should be able to deactivate their account$")
	public void _7nf_user_should_be_able_to_deactivate_their_account() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		AccountDetailsPage.clickDeactivateAccount();
	}

	@Given("^_8nf nonfed user attempts to signup for an account$")
	public void _8nf_nonfed_user_attempts_to_signup_for_an_account() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
//		SignUpUtility.signUpNewUserNonFedTemporary("raiaan.zyx+newregisterednonfeduser" + counter + "@gmail.com",
//				Constants.USERPASS);
		SignUpUtility.signUpNewUserNonFed("nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com",
				Constants.USERPASS);
	}

	@And("^_8nf user goes through all the identity verification$")
	public void _8nf_user_goes_through_all_the_identity_verification() throws Throwable {
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		CommonProfilePage.clickSubmitButton();
	}

	@And("^_8nf user should be able to request role and land on workspace page$")
	public void _8nf_user_should_be_able_to_request_role_and_land_on_workspace_page() throws Throwable {
		RequestRoleOptionalPage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		RequestRoleOptionalPage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		RequestRoleOptionalPage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		RequestRoleOptionalPage.enterAdditionalDetails("requesting this role");
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickFinishButton();
	}

	@Then("^_8nf user should be able to view their pending request in feeds$")
	public void _8nf_user_should_be_able_to_view_their_pending_request_in_feeds() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_COMPLIANCE, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
	}

	@Given("^_9nf nonfed user without a role logs in$")
	public void _9nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_9nf user should not see user directory widget$")
	public void _9nf_user_should_not_see_user_directory_widget() throws Throwable {
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(false, userdirectorywidgetfound);
	}

	@And("^_9nf user should also not be able to hit the user directory url$")
	public void _9nf_user_should_also_not_be_able_to_hit_the_user_directory_url() throws Throwable {
		ForBiddenPage.setDriver(T1WorkspacePage.getDriver());
		LaunchBrowserUtil.enterUrl(Constants.SAM_HOME_PAGE + "/role-management/user-directory");
		LaunchBrowserUtil.delay(2);
		String message = ForBiddenPage.getPrimaryContentMessage();
		Assert.assertEquals("You do not have sufficient privileges to view the requested page.", message);
	}

	@Given("^_10nf nonfed user with a role logs in$")
	public void _10nf_nonfed_user_with_a_role_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_10nf nonfed user should see user directory widget$")
	public void _10nf_nonfed_user_should_see_user_directory_widget() throws Throwable {
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);
	}

	@And("^_10nf nonfed user should also be able to hit the user directory url to access the page$")
	public void _10nf_nonfed_user_should_also_be_able_to_hit_the_user_directory_url_to_access_the_page()
			throws Throwable {
		LaunchBrowserUtil.enterUrl(Constants.SAM_HOME_PAGE + "/role-management/user-directory");
		LaunchBrowserUtil.delay(2);
		UserDirectoryPage.setDriver(LaunchBrowserUtil.getDriver());
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.goToWorkspacePage();

	}

	@When("^_10nf nonfed user is in the workspace page$")
	public void _10_nf_nonfed_user_is_in_the_workspace_page() throws Exception {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);
	}

	@Then("^_10nf they should be able to see the system account widget$")
	public void _10_nf_they_should_be_able_to_see_the_system_account_widget() throws Exception {

	}

	@Given("^_11nf nonfed user logs in with data entry role in entity registration$")
	public void _11nf_nonfed_user_logs_in_with_data_entry_role_in_entity_registration() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DATA_ENTRY_ENTITYREGISTRATION_1, Constants.USERPASS,
				ConstantsAccounts.DATA_ENTRY_ENTITYREGISTRATION_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_11nf user navigates to user directory page and clicks data entry and entity registration filter$")
	public void _11nf_user_navigates_to_user_directory_page_and_clicks_data_entry_and_entity_registration_filter()
			throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickUsersOwnDomain();
		UserDirectoryPage.clickDataEntryFilter();
		LaunchBrowserUtil.scrollUp();
	}

	@And("^_11nf user should see all those users as clickable$")
	public void _11nf_user_should_see_all_those_users_as_clickable() throws Throwable {
		int totalNoOfPages = 1; // UserDirectoryPage.getTotalNoOfPages(); check 3 pages
		int currentPage = 1;

		do {// search page 1 regardless of whether other pages exist

			List<WebElement> userList = UserDirectoryPage.getUserList();
			logger.info("The size fo the user list is--" + userList.size());

			for (int i = 0; i < userList.size(); i++) {
				WebElement user = userList.get(i).findElement(UserDirectoryPageLocator.USERNAME);
				boolean isClickable = UserDirectoryPage.isClickable(user);
				Assert.assertTrue(isClickable);
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}

		} while (currentPage < totalNoOfPages);
	}

	@Given("^_12nf nonfed user logs in with data entry role in entity entity compliance$")
	public void _12nf_nonfed_user_logs_in_with_data_entry_role_in_entity_entity_compliance() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1, Constants.USERPASS,
				ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_12nf user navigates to user directory page and clicks data entry and entity compliance filter$")
	public void _12nf_user_navigates_to_user_directory_page_and_clicks_data_entry_and_entity_compliance_filter()
			throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickUsersOwnDomain();
		UserDirectoryPage.clickDataEntryFilter();
		LaunchBrowserUtil.scrollUp();
	}

	@And("^_12nf user should see all those users as clickable$")
	public void _12nf_user_should_see_all_those_users_as_clickable() throws Throwable {
		int totalNoOfPages = 3; // UserDirectoryPage.getTotalNoOfPages(); check 3 pages
		int currentPage = 1;

		do {// search page 1 regardless of whether other pages exist

			List<WebElement> userList = UserDirectoryPage.getUserList();
			logger.info("The size fo the user list is--" + userList.size());

			for (int i = 0; i < userList.size(); i++) {
				WebElement user = userList.get(i).findElement(UserDirectoryPageLocator.USERNAME);
				boolean isClickable = UserDirectoryPage.isClickable(user);
				Assert.assertTrue(isClickable);
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}

		} while (currentPage < totalNoOfPages);
	}

	@Given("^_13nf nonfed user logs in with data entry role in contract opportunities$")
	public void _13nf_nonfed_user_logs_in_with_data_entry_role_in_contract_opportunities() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES, Constants.USERPASS,
				ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_13nf user navigates to user directory page and clicks data entry and contract opportunities filter$")
	public void _13nf_user_navigates_to_user_directory_page_and_clicks_data_entry_and_contract_opportunities_filter()
			throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickUsersOwnDomain();
		UserDirectoryPage.clickDataEntryFilter();
		LaunchBrowserUtil.scrollUp();
	}

	@And("^_13nf user should see all those users as clickable$")
	public void _13nf_user_should_see_all_those_users_as_clickable() throws Throwable {
		int totalNoOfPages = 2; // UserDirectoryPage.getTotalNoOfPages(); check 3 pages
		int currentPage = 1;

		do {// search page 1 regardless of whether other pages exist

			List<WebElement> userList = UserDirectoryPage.getUserList();
			logger.info("The size fo the user list is--" + userList.size());

			for (int i = 0; i < userList.size(); i++) {
				WebElement user = userList.get(i).findElement(UserDirectoryPageLocator.USERNAME);
				boolean isClickable = UserDirectoryPage.isClickable(user);
				Assert.assertTrue(isClickable);
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}

		} while (currentPage < totalNoOfPages);
	}

	@Given("^_14nf a nonfed user with no role logs in$")
	public void _14nf_a_nonfed_user_with_no_role_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_14nf user requests viewer role in contract opportunities$")
	public void _14nf_user_requests_viewer_role_in_contract_opportunities() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();
	}

	@When("^_14nf spaad accepts the pending role request for the user$")
	public void _14nf_spaad_accepts_the_pending_role_request_for_the_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_NONFED);
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();

		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("giving this role");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_14nf then requsters profile page should show the role assigned in role history$")
	public void _14_then_requsters_profile_page_should_show_the_role_assigned_in_role_history() throws Throwable {
		boolean rolehistoryfound = MyRolesPage.roleHistoryFound("", Constants.ROLEHISTORY_STATUS_ROLE_ASSIGNED, "", 0);
		Assert.assertEquals(true, rolehistoryfound);

		LaunchBrowserUtil.openNewTab();
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.getDriver().get(Constants.SWAGGER_URL);
		String response = LaunchBrowserUtil.getValidateIPAddressParameter(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		Assert.assertTrue(response.contains("\"adminIp\":"));

	}

	@And("^_14nf the request assignment should send ip address information as well$")
	public void _14nf_the_request_assignment_should_send_ip_address_information_as_well() throws Throwable {
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.switchTabs(2);
		LaunchBrowserUtil.delay(2);

		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_VIEWER,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Given("^_15nf spaad logs in$")
	public void _15nf_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_15nf spaad looks up a nonfed user with a role$")
	public void _15nf_spaad_looks_up_a_nonfed_user_with_a_role() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_1);
	}

	@When("^_15nf spaad removes the users role$")
	public void _15nf_spaad_removes_the_users_role() throws Throwable {
		MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_DATA_ENTRY,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
	}

	@Then("^_15nf then users history should show role removed status in in profile history$")
	public void _15nf_then_users_history_should_show_role_removed_status_in_in_profile_history() throws Throwable {
		boolean rolehistoryfound = MyRolesPage.roleHistoryFound("", Constants.ROLEHISTORY_STATUS_ROLE_REMOVED, "", 0);
		Assert.assertEquals(true, rolehistoryfound);
		// ---------------------assign the role
		// back------------------------------------------------
		MyRolesPage.clickAssignRoleButton();
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("giving this role back");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();

		boolean rolefound = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_DATA_ENTRY,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, rolefound);
	}

	@Given("^_16nf spaad logs in$")
	public void _16nf_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_16nf spaad looks up a nonfed user with data entry in contract opportunities$")
	public void _16nf_spaad_looks_up_a_nonfed_user_with_data_entry_in_contract_opportunities() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_1);
	}

	@When("^_16nf spaad updates users role to viewer in contract opportunities$")
	public void _16nf_spaad_updates_users_role_to_viewer_in_contract_opportunities() throws Throwable {
		boolean rolefound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(true, rolefound);
		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.writeComment("editing this role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_16nf then the user should see role updated status in profile history$")
	public void _16nf_then_the_user_should_see_role_updated_status_in_profile_history() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
//		boolean rolehistoryfound = MyRolesPage.roleHistoryFound("", Constants.ROLEHISTORY_STATUS_ROLE_UPDATED, "", 0);
//		Assert.assertEquals(true, rolehistoryfound);

		boolean rolehistoryfound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan",
				Constants.ROLEHISTORY_STATUS_ROLE_UPDATED, Constants.ROLE_DATA_ENTRY,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.NOACTION);
		Assert.assertEquals(true, rolehistoryfound);

		// ------------------edit the role back to previous state---------------
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);
		LaunchBrowserUtil.delay(4);

		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.writeComment("editing this role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		// confirming the change has gone through
		boolean roleRestored = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(roleRestored, true);
		LaunchBrowserUtil.delay(5);
	}

	@Given("^_17nf user logs in with viewer role in entity registration$")
	public void _17nf_user_logs_in_with_viewer_role_in_entity_registration() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_VIEWER_ENTITYREGISRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_VIEWER_ENTITYREGISRATION_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_17nf user requests viewer role in entity registration$")
	public void _17nf_user_requests_viewer_role_in_entity_registration() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();
	}

	@When("^_17nf spaad logs in$")
	public void _17nf_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_17nf spaad tries to approve the pending request for the user$")
	public void _17nf_spaad_tries_to_approve_the_pending_request_for_the_user() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_VIEWER_ENTITYREGISRATION);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_VIEWER_ENTITYREGISRATION);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();

		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("giving this role");

	}

	@Then("^_17nf proper error message show up$")
	public void _17nf_proper_error_message_show_up() throws Throwable {
		LaunchBrowserUtil.scrollUp();
		boolean alertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, alertFound);
		// --------------------------reject the request-----------------------------
		AssignRolePage.clickCancel();
		RoleRequestPendingPage.enterAdditionalInformation("rejecting");
		RoleRequestPendingPage.clickRejectButton();

	}

	@Given("^_18nf user logs in with admin role in entity registration$")
	public void _18nf_user_logs_in_with_admin_role_in_entity_registration() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_18nf user navigates to role request page$")
	public void _18nf_user_navigates_to_role_request_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();

	}

	@When("^_18nf user looks for role under role tab$")
	public void _18nf_user_looks_for_role_under_role_tab() throws Throwable {
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

	}

	@Then("^_18nf no role options under entity registration should show up$")
	public void _18nf_no_role_options_under_entity_registration_should_show_up() throws Throwable {
		boolean viewerfound = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, viewerfound);
		boolean entityregistrationdomainNotFound = RequestRolePage
				.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(false, entityregistrationdomainNotFound);

		boolean dataentryfound = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, dataentryfound);
		boolean entityregistrationdomainNotFound2 = RequestRolePage
				.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(false, entityregistrationdomainNotFound2);
	}

	@Given("^_19nf new nonfed user signs up$")
	public void _19nf_new_nonfed_user_signs_up() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		newsignedupnonfedusersecretkey = SignUpUtility.signUpNewUserNonFed(
				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
		newsignedupnonfeduser = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();

	}

	@And("^_19nf user requests data entry at entity compliance at octo consulting group$")
	public void _19nf_user_requests_data_entry_at_entity_compliance_at_octo_consulting_groupo() throws Throwable {
		RequestRoleOptionalPage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		RequestRoleOptionalPage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		RequestRoleOptionalPage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		RequestRoleOptionalPage.enterAdditionalDetails("requesting this role");
		RequestRoleOptionalPage.clickFinishButton();
	}

	@When("^_19nf spaads logs in and views the pending request$")
	public void _19nf_spaads_logs_in_and_views_the_pending_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_19nf spaad should be able to view the requesting org in the pending link$")
	public void _19nf_spaad_should_be_able_to_view_the_requesting_org_in_the_pending_link() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(newsignedupnonfeduser);
		UserDirectoryPage.clickViewAccess(newsignedupnonfeduser);
		MyRolesPage.click1PendingRequest();
		String message = MyRolesPage.getTextForPendingRequest();
		Assert.assertEquals("Data Entry for Entity Compliance at the OCTO CONSULTING GROUP, INC.", message);
	}

	@Given("^_20nf user logs in workspace as nonfed user with a role$")
	public void _20nf_user_logs_in_workspace_as_nonfed_user_with_a_role() throws Throwable {
		SignInUtility.signIntoWorkspace(
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM,
				Constants.USERPASS,
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM_SECRETKEY,
				Constants.USER_NONFED);
	}

	@And("^_20nf user navigates to user directory page$")
	public void _20nf_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_20nf user searches user using firstname$")
	public void _20nf_user_searches_user_using_firstname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("shah");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_20nf user should only see accounts containing firstname$")
	public void _20nf_user_should_only_see_accounts_containing_firstname() throws Throwable {

	}

	@When("^_20nf user searches using lastname$")
	public void _20nf_user_searches_using_lastname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("raiaan");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_20nf user should only see accounts containing lastname$")
	public void _20nf_user_should_only_see_accounts_containing_lastname() throws Throwable {

	}

	@When("^_20nf user searches using fullname$")
	public void _20nf_user_searches_using_fullname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("shah raiaan");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_20nf user should only see accounts containing fullname$")
	public void _20nf_user_should_only_see_accounts_containing_fullname() throws Throwable {

	}

	@Given("^_21nf user logs in workspace as nonfed user with a role$")
	public void _21nf_user_logs_in_workspace_as_nonfed_user_with_a_role() throws Throwable {
		SignInUtility.signIntoWorkspace(
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM,
				Constants.USERPASS,
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM_SECRETKEY,
				Constants.USER_NONFED);
	}

	@And("^_21nf user navigates to user directory page$")
	public void _21nf_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_21nf user searches users with ids ending with gmail$")
	public void _21nf_user_searches_users_with_ids_ending_with_gmail() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("@gmail");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_21nf user should only see accounts containing gmail ids$")
	public void _21nf_user_should_only_see_accounts_containing_gmail_ids() throws Throwable {

	}

	@When("^_21nf user searches users with ids ending with octoconsulting$")
	public void _21nf_user_searches_users_with_ids_ending_with_octoconsulting() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("@octoconsulting");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_21nf user should only see accounts containing octoconsulting$")
	public void _21nf_user_should_only_see_accounts_containing_octoconsulting() throws Throwable {

	}

	@When("^_21nf user searches users with ids ending with gsa$")
	public void _21nf_user_searches_users_with_ids_ending_with_gsa() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("@gsa");
		Assert.assertEquals(false, searchtermfound);
	}

	@Then("^_21nf user should not see no results$")
	public void _21nf_user_should_not_see_no_results() throws Throwable {

	}

	@Given("^_22nf user logs in workspace as nonfed admin$")
	public void _22nf_user_logs_in_workspace_as_nonfed_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM,
				Constants.USERPASS,
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM_SECRETKEY,
				Constants.USER_NONFED);
	}

	@And("^_22nf user navigates to user directory page$")
	public void _22nf_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_22nf user searches entity with name in entity search box$")
	public void _22nf_user_searches_entity_with_name_in_entity_search_box() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.entityPickerAllUsersContainsThisSearchTerm("octo");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_22nf user should only see entities containing the search term in the results$")
	public void _22nf_user_should_only_see_entities_containing_the_search_term_in_the_results() throws Throwable {

	}

	@When("^_22nf user searches enity with duns no in duns search box$")
	public void _21nf_user_searches_enity_with_duns_no_in_duns_search_box() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.dunsPickerAllUsersContainsThisSearchTerm("0103");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_22nf user should only see entities containing the duns no in the results$")
	public void _22nf_user_should_only_see_entities_containing_the_duns_no_in_the_results() throws Throwable {

	}

	@When("^_22nf user searches enity with cage code in cage search box$")
	public void _22nf_user_searches_enity_with_cage_code_in_cage_search_box() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.cagePickerAllUsersContainsThisSearchTerm("61gj");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_22nf user should only see entities containing the cage no in the results$")
	public void _22nf_user_should_only_see_entities_containing_the_cage_no_in_the_results() throws Throwable {

	}

	@Given("^_23nf user logs in as nonfed user$")
	public void _23nf_user_logs_in_as_nonfed_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_23nf user navigates to role request page$")
	public void _23nf_user_navigates_to_role_request_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
	}

	@When("^_23nf user searches for entities in the entity picker$")
	public void _23nf_user_searches_for_entities_in_the_entity_picker() throws Throwable {
		boolean entityfound = RequestRolePage
				.validateEntitySuggestionContainsGivenWord(Constants.ORG_OCTO_CONSULTING_GROUP, "DUNS+4");
		Assert.assertEquals(false, entityfound);

	}

	@Then("^_23nf duns plus four should not show up and only duns should show$")
	public void _23nf_duns_plus_four_should_not_show_up_and_only_duns_should_show() throws Throwable {

		boolean entityfound = RequestRolePage
				.validateEntitySuggestionContainsGivenWord(Constants.ORG_OCTO_CONSULTING_GROUP, "DUNS");
		Assert.assertEquals(true, entityfound);
	}

	@Given("^_24nf user logs in as nonfed admin$")
	public void _24nf_user_logs_in_as_nonfed_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_24nf user navigates to userdirectory page and searches for a nonfed user$")
	public void _24nf_user_navigates_to_userdirectory_page_and_searches_for_a_nonfed_user() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
	}

	@When("^_24nf user searches for entity in the assign role page$")
	public void _24nf_user_searches_for_entity_in_the_assign_role_page() throws Throwable {
		boolean entityfound = AssignRolePage
				.validateEntitySuggestionContainsGivenWord(Constants.ORG_OCTO_CONSULTING_GROUP, "DUNS+4");
		Assert.assertEquals(false, entityfound);
	}

	@Then("^_24nf duns plus four should not show up and only duns should show$")
	public void _24nf_duns_plus_four_should_not_show_up_and_only_duns_should_show() throws Throwable {
		boolean entityfound = AssignRolePage
				.validateEntitySuggestionContainsGivenWord(Constants.ORG_OCTO_CONSULTING_GROUP, "DUNS");
		Assert.assertEquals(true, entityfound);
	}

	@Given("^_25nf user logs in as spaad$")
	public void _25nf_user_logs_in_as_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_25nf user navigates to userdirectory page and searches for a nonfed user$")
	public void _25nf_user_navigates_to_userdirectory_page_and_searches_for_a_nonfed_user() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
	}

	@When("^_25nf user searches for entity in the assign role page$")
	public void _25nf_user_searches_for_entity_in_the_assign_role_page() throws Throwable {
		boolean entityfound = AssignRolePage
				.validateEntitySuggestionContainsGivenWord(Constants.ORG_OCTO_CONSULTING_GROUP, "DUNS+4");
		Assert.assertEquals(false, entityfound);

	}

	@Then("^_25nf duns plus four should not show up and only duns should show$")
	public void _25nf_duns_plus_four_should_not_show_up_and_only_duns_should_show() throws Throwable {
		boolean entityfound = AssignRolePage
				.validateEntitySuggestionContainsGivenWord(Constants.ORG_OCTO_CONSULTING_GROUP, "DUNS");
		Assert.assertEquals(true, entityfound);
	}

	@Given("^_26nf user logs in as spaad$")
	public void _26nf_user_logs_in_as_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_26nf user navigates to userdirectory page and searches for a nonfed user with no role$")
	public void _26nf_user_navigates_to_userdirectory_page_and_searches_for_a_nonfed_user_with_no_role()
			throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
	}

	@When("^_26nf spaad assigns a role to the user$")
	public void _26nf_spaad_assigns_a_role_to_the_user() throws Throwable {
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_26nf the role assignment should send ip address information as well$")
	public void _26nf_the_role_assignment_should_send_ip_address_information_as_well() throws Throwable {
		LaunchBrowserUtil.openNewTab();
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.getDriver().get(Constants.SWAGGER_URL);
		String response = LaunchBrowserUtil.getValidateIPAddressParameter(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		Assert.assertTrue(response.contains("\"adminIp\":"));

		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.switchTabs(2);
		LaunchBrowserUtil.delay(2);

		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_VIEWER,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Given("^_27nf nonfed user signs up$")
	public void _27nf_nonfed_user_signs_up() throws Throwable {
//		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
//				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
//		LaunchBrowserUtil.delay(4);
//		

		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		newsignedupnonfedusersecretkey = SignUpUtility.signUpNewUserNonFed(
				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
		newsignedupnonfeduser = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
		RequestRoleOptionalPage.clickSkipAndFinish();
	}

	@And("^_27nf user call auto assign api with session token$")
	public void _27nf_user_call_auto_assign_api_with_session_token() throws Throwable {
		String sessionkey = LaunchBrowserUtil.getDriver().manage().getCookieNamed("SESSION").getValue();
		logger.info("The captured sessionkey is - " + sessionkey);
		String authorizatinheader = "aUhHpyv4Ilvewa55FQizkJ+L65KoLL41XoIC65DvELg";
		logger.info("The authorization header used is  - " + authorizatinheader);

//		LaunchBrowserUtil.openNewTab();
//		LaunchBrowserUtil.switchTabs(1);
//		LaunchBrowserUtil.getDriver().get(Constants.SWAGGER_URL);
//		LaunchBrowserUtil.makeAssignAPICall(sessionkey, "800127859", "4RSCO", Constants.ORG_OCTO_CONSULTING_GROUP,
//				newsignedupnonfeduser);
//		LaunchBrowserUtil.delay(2);
//		LaunchBrowserUtil.switchTabs(2);
//		LaunchBrowserUtil.delay(2);

	}

	@Then("^_27nf nonfed user should be assigned draft registration user role$")
	public void _27nf_user_should_be_assigned_draft_registration_user_role() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DRAFTREGISTRATION_USER, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.NOACTION);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Given("^_28nf user logs in as admin in entity registration$")
	public void _28nf_user_logs_in_as_admin_in_entity_registration() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_28nf user call auto assign api with session token$")
	public void _28nf_user_call_auto_assign_api_with_session_token() throws Throwable {
		String sessionkey = LaunchBrowserUtil.getDriver().manage().getCookieNamed("SESSION").getValue();
		logger.info("The captured sessionkey is - " + sessionkey);

		LaunchBrowserUtil.openNewTab();
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.getDriver().get(Constants.SWAGGER_URL);
		LaunchBrowserUtil.makeAssignAPICall(sessionkey, "800127859", "4RSCO", Constants.ORG_OCTO_CONSULTING_GROUP,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION);
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.switchTabs(2);
		LaunchBrowserUtil.delay(2);
	}

	@Then("^_28nf user should get conflict error$")
	public void _28nf_user_should_get_conflict_error() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DRAFTREGISTRATION_USER, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.NOACTION);
		Assert.assertEquals(userAlreadyHasRole, false);
	}
	
	
	
	 @Given("^_29nf user logs in as data entry in entity registration$")
	    public void _29nf_user_logs_in_as_data_entry_in_entity_registration() throws Throwable {
		 SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_DATAENTRY_ENTITYREGISTRATION_2, Constants.USERPASS,
					ConstantsAccounts.NONFED_DATAENTRY_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
			LaunchBrowserUtil.delay(4);  
	    }

	    @When("^_29nf user call auto assign api with session token$")
	    public void _29nf_user_call_auto_assign_api_with_session_token() throws Throwable {
	    	String sessionkey = LaunchBrowserUtil.getDriver().manage().getCookieNamed("SESSION").getValue();
			logger.info("The captured sessionkey is - " + sessionkey);

			LaunchBrowserUtil.openNewTab();
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.getDriver().get(Constants.SWAGGER_URL);
			LaunchBrowserUtil.makeAssignAPICall(sessionkey, "800127859", "4RSCO", Constants.ORG_OCTO_CONSULTING_GROUP,
					ConstantsAccounts.NONFED_DATAENTRY_ENTITYREGISTRATION_2);
			LaunchBrowserUtil.delay(2);
			LaunchBrowserUtil.switchTabs(2);
			LaunchBrowserUtil.delay(2);
	    }

	    @Then("^_29nf user should not get conflict error$")
	    public void _29nf_user_should_get_conflict_error() throws Throwable {
	    	T1WorkspacePage.goToAccountDetailsPage();
			AccountDetailsPage.goToPageOnSideNav("My Roles");
			// ---------assert the newly granted role-----------
			boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
					Constants.ROLE_DRAFTREGISTRATION_USER, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.NOACTION);
			Assert.assertEquals(userAlreadyHasRole, true);
	    }

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
