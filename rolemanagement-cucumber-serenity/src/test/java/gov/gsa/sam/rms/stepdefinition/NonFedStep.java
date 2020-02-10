package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
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

	@Given("^_1nf nonfed user without a role logs in$")
	public void _1_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_2nf nonfed user navigates to profile page$")
	public void _2nf_nonfed_user_navigates_to_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
	}

	@Then("^_2nf user should have no roles and should see that message$")
	public void _2nf_user_should_have_no_roles() throws Throwable {
		String noroletext = MyRolesPage.getTextForNoRoleUser();
		Assert.assertEquals("No roles are assigned to user.", noroletext);
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
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_3nf user should see the role assigned$")
	public void _3nf_user_should_see_the_role_assigned() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_DATA_ENTRY,
				Constants.DOMAIN_ENTITY_COMPLIANCE, Constants.NOACTION);
	}

	@And("^_3nf user should see the feeds notifications for the requested updated to approved$")
	public void _3nf_user_should_see_the_feeds_notifications_for_the_requested_updated_to_approved() throws Throwable {
		MyRolesPage.goToFeedsPage();
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
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
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
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
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
		SignUpUtility.signUpNewUserNonFedTemporary("raiaan.zyx+newregisterednonfeduser" + counter + "@gmail.com",
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
		SignUpUtility.signUpNewUserNonFedTemporary("raiaan.zyx+newregisterednonfeduser" + counter + "@gmail.com",
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

	}

	@Then("^_9nf user should not see user directory widget$")
	public void _9nf_user_should_not_see_user_directory_widget() throws Throwable {

	}

	@And("^_9nf user should also not be able to hit the user directory url$")
	public void _9nf_user_should_also_not_be_able_to_hit_the_user_directory_url() throws Throwable {

	}

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
