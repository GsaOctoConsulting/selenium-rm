package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import org.junit.Assert;

public class RoleEditStep {

	private static Logger logger = LoggerFactory.getLogger(RoleEditStep.class);

	@Given("^_1 user logs in workspace as assistance admin$")
	public void _1_user_logs_in_workspace_as_assistance_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user looks up assistance user account in UserDirectory$")
	public void _1_user_looks_up_assistance_user_account_in_userdirectory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();

		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Then("^_1 user should be able to edit their roles$")
	public void _1_user_should_be_able_to_edit_their_roles() throws Throwable {
		// edit the role
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.writeComment("adding organization");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// ------------------edit the role back to previous state---------------

		// change the role back
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(
				Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		LaunchBrowserUtil.delay(4);
		// confirming the change has gone through
		boolean roleRestored = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleRestored);
		afterScenario();
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2 user logs in workspace as dra$")
	public void _2_user_logs_in_workspace_as_dra() throws Throwable {

		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 dra looks up contracting officer in contract data$")
	public void _2_dra_looks_up_contracting_officer_in_contract_data() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGOFFICER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGOFFICER_1);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Then("^_2 dra changes users role to contracting speacialist$")
	public void _2_dra_changes_users_role_to_contracting_speacialist() throws Throwable {
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("changing roles");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		// check to ensure page is back on RolesDirectoryViewAccessPage
		boolean assignButtonFound = UserDirectoryViewAccessPage
				.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);
		Assert.assertEquals(assignButtonFound, true);

		// check to ensure the changed role has gone through
		boolean roleHasChanged = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, Constants.NOACTION);
		Assert.assertEquals(roleHasChanged, true);

		// ------------------edit the role back---------------
		LaunchBrowserUtil.delay(3);
		// change the role back
		UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR,
				Constants.DOMAIN_CONTRACT_DATA, Constants.EDIT);

		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("reverting back");
		LaunchBrowserUtil.delay(2);
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

	}

	@Then("^_2 dra sees the role change showing up in my profile page$")
	public void _2_dra_sees_the_role_change_showing_up_in_my_profile_page() throws Throwable {
		// check to ensure the changed role has gone through
		boolean previousRoleRestored = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, Constants.NOACTION);
		Assert.assertEquals(previousRoleRestored, true);

	}

	@Given("^_3re user logs in workspace as contract opportunities admin$")
	public void _3re_user_logs_in_workspace_as_contract_opportunities_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3re user looks up a contracting officer in user directory$")
	public void _3re_user_looks_up_a_contracting_officer_in_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGOFFICER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGOFFICER_1);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);

	}

	@Then("^_3re user should be able to edit their roles$")
	public void _3re_user_should_be_able_to_edit_their_roles() throws Throwable {
		// edit the role
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.writeComment("adding organization");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// ------------------edit the role back to previous state---------------

		// change the role back
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(
				Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY, Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		LaunchBrowserUtil.delay(4);
		// confirming the change has gone through
		boolean roleRestored = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, roleRestored);
		afterScenario();
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_4re user logs in spaad$")
	public void _4re_user_logs_in_spaad() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4re user looks up a nonfed user with data entry role in contract opportunities$")
	public void _4re_user_looks_up_a_nonfed_user_with_data_entry_role_in_contract_opportunities() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_1);

		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@And("^_4re spaad should be able to edit the role to viewer$")
	public void _4re_spaad_should_be_able_to_edit_the_role_to_viewer() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectRoleIfFound(Constants.ROLE_VIEWER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("editing the role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_4re nonfed user should be left with the edit role$")
	public void _4re_nonfed_user_should_be_left_with_the_edited_role() throws Throwable {
		// check to ensure the changed role has gone through
		boolean roleHasChanged = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(roleHasChanged, true);
		// edit the role back to data entry
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("editing the role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		// check to ensure the changed role has gone through
		boolean roleHasChanged2 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(roleHasChanged2, true);
	}

	@Given("^_5re user logs in workspace as contract opportunities admin$")
	public void _5re_user_logs_in_workspace_as_contract_opportunities_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5re user looks up a contracting specialist in user directory$")
	public void _5re_user_looks_up_a_contracting_specialist_in_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage
				.searchUserInUserPicker(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGSPECIALIST_APOSTROPHE_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGSPECIALIST_APOSTROPHE_1);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Then("^_5re user should be able to edit their roles$")
	public void _5re_user_should_be_able_to_edit_their_roles() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("editing the role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(userHasRole, true);

		// give the original role back to the user
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("editing the role back");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		boolean usersRoleHasBeenRestored = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(usersRoleHasBeenRestored, true);
	}

	@Given("^_6re user logs in as as contract opportunities admin$")
	public void _6re_user_logs_in_as_as_contract_opportunities_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6re admin looks up a user with contracting specialist role in both contract opp and contract data$")
	public void _6re_admin_looks_up_a_user_with_contracting_specialist_role_in_both_contract_opp_and_contract_data()
			throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.MULTIPLE_ROLES_CONTRACTING_SPECIALIST_IN_CO_CD);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.MULTIPLE_ROLES_CONTRACTING_SPECIALIST_IN_CO_CD);
		// check whether user already has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole1);

		boolean userAlreadyHasRole2 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole2, true);
	}

	@When("^_6re admin tries to edit the role and reassign contracting specialist role in contract data$")
	public void _6re_admin_trie_to_edit_the_role_and_reassign_contracting_specialist_role_in_contract_data()
			throws Throwable {

	}

	@Then("^_6re admin should received role already assigned error$")
	public void _6re_admin_should_received_role_already_assigned_error() throws Throwable {

	}

	@And("^_6re both the existing roles should stay intact as before$")
	public void _6re_both_the_existing_roles_should_stay_intact_as_before() throws Throwable {

	}

	@Given("^_7re user logs in with spaad role$")
	public void _7re_user_logs_in_with_spaad_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_7re spaad looks up a user with aad role and system account admin role in admin domain$")
	public void _7re_spaad_looks_up_a_user_with_aad_role_and_system_manager_role_in_admin_domain() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.MULTIPLE_ROLES_AAD_AND_SYSTEM_MANAGER);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.MULTIPLE_ROLES_AAD_AND_SYSTEM_MANAGER);
	}

	@When("^_7re spaad tries to edit the role to keep only system admin role$")
	public void _7re_spaad_tries_to_edit_the_role_to_keep_only_system_manager_role() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole2 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole2);

		// check whether user already has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_SYSTEM_ACCOUNT_ADMIN, Constants.DOMAIN_ADMIN, Constants.EDIT);
		Assert.assertEquals(true, userAlreadyHasRole1);

		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("editing the role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		// giving system account admin the role back
		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_SYSTEM_ACCOUNT_ADMIN);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		LaunchBrowserUtil.delay(5);
	}

	@Then("^_7re the role edit attempt should successfully go through$")
	public void _7re_the_role_edit_attempt_should_successfully_go_through() throws Throwable {
		// confirm whether user has the role
		boolean userAlreadyHasRole2 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole2);

		// confirm whether user has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_SYSTEM_ACCOUNT_ADMIN, Constants.DOMAIN_ADMIN, Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole1);
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
