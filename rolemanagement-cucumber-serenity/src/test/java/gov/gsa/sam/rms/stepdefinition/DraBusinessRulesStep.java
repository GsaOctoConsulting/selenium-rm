package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.RoleHistoryDetailsPage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class DraBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(DraBusinessRulesStep.class);
	String noRoleuser = "shah.raiaan+noroless2@gsa.gov";
	String draUser = "shah.raiaan+deptroleadmin@gsa.gov";
	String coUser = "shah.raiaan+contractingofficer@gsa.gov";
	String raUser = "shah.raiaan+ra@gsa.gov";

	@Given("^_1 role admin logs into workspace$")
	public void _1_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.USERPASS);
	}

	@And("^_1 role admin looks up a no role user through user directory$")
	public void _1_role_admin_looks_up_a_no_role_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(noRoleuser);
		UserDirectoryPage.clickAssignRole(noRoleuser);
	}

	@And("^_1 role admin tries to assign dra to this user in subtier$")
	public void _1_role_admin_tries_to_assign_dra_to_this_user_in_subtier() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 1);// selecting
																// subtier
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_1 correct error message should appear$")
	public void _1_correct_error_message_should_appear() throws Throwable {
		boolean errorAlertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, errorAlertFound);
	}

	@When("^_1 role admin tries to assign dra to user at other department$")
	public void _1_role_admin_tries_to_assign_dra_to_user_at_other_department() throws Throwable {
		CommonMethods.delay(2);
		LaunchBrowserUtil.scrollAllTheWayDown();
		AssignRolePage.clickCancelButtonWhenAlertIsOn();
		UserDirectoryPage.searchUserInUserPicker(noRoleuser);
		UserDirectoryPage.clickAssignRole(noRoleuser);
		AssignRolePage.selectOrgIfFound(Constants.ORG_HHS, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();

	}

	@Then("^_1 correct error message should appear again$")
	public void _1_correct_error_message_should_appear_again() throws Throwable {
		boolean errorAlertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, errorAlertFound);
	}

	@Given("^_2 role admin logs into workspace$")
	public void _2_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.USERPASS);
	}

	@And("^_2 role admin looks up a no role user through user directory$")
	public void _2_role_admin_looks_up_a_no_role_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(noRoleuser);
		UserDirectoryPage.clickAssignRole(noRoleuser);
	}

	@And("^_2 role admin tries to assign dra to this user at department level$")
	public void _2_role_admin_tries_to_assign_dra_to_this_user_at_department_level() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);// selecting
		// subtier
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_2 the newly given role should show up under roles tab$")
	public void _2_the_newly_given_role_should_show_up_under_roles_tab() throws Throwable {
		boolean roleFound = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		// --------------------------
		boolean roleHistoryFound = RolesDirectoryViewAccessPage.latestRoleHistoryFound("SHAH M RAIAAN",
				Constants.ASSIGNED, Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.ORG_GSA,
				Constants.GO_INTO_ROLE_ASSIGNED);
		Assert.assertEquals(true, roleHistoryFound);
		RoleHistoryDetailsPage.clickHereLink();
		//--------delete the role-------------------
		RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.DELETE);
	}

	@Given("^_3 role admin logs into workspace$")
	public void _3_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.USERPASS);
	}

	@And("^_3 role admin looks up dra user through user directory$")
	public void _3_role_admin_looks_up_dra_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(draUser);
		UserDirectoryPage.clickAssignRole(draUser);
	}

	@And("^_3 role admin tries to assign alerts admin to this user$")
	public void _3_role_admin_tries_to_assign_alerts_admin_to_this_user() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ALERTS_ADMIN);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_3 correct error message should appear$")
	public void _3_correct_error_message_should_appear() throws Throwable {
		boolean errorAlertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, errorAlertFound);
	}

	@When("^_3 role admin tries to assign system account admin to dra$")
	public void _3_role_admin_tries_to_assign_system_account_admin_to_dra() throws Throwable {
		AssignRolePage.clickCancelButtonWhenAlertIsOn();;
		UserDirectoryPage.searchUserInUserPicker(draUser);
		UserDirectoryPage.clickAssignRole(draUser);
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_SYSTEM_ACCOUNT_ADMIN);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_3 the role assignment should show up under roles tab$")
	public void _3_the_role_assignment_should_show_up_under_roles_tab() throws Throwable {
		boolean draRoleFound = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, draRoleFound);

		boolean systemAccountAdminRoleFound = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_SYSTEM_ACCOUNT_ADMIN, Constants.DOMAIN_ADMIN, Constants.DELETE);
		Assert.assertEquals(true, systemAccountAdminRoleFound);

	}

	@Given("^_4 dra logs into workspace$")
	public void _4_dra_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage(draUser, Constants.USERPASS);
	}

	@And("^_4 dra looks up a user with contracting officer role through user directory$")
	public void _4_dra_looks_up_a_user_with_contracting_officer_role_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(coUser);
		UserDirectoryPage.clickViewAccess(coUser);
	}

	@And("^_4 dra edits the role to contracting specialist$")
	public void _4_dra_edits_the_role_to_contracting_specialist() throws Throwable {
		// check whether user already has the role

		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, "EDIT");
		Assert.assertEquals(userAlreadyHasRole, true);
		// edit the role
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("changing roles");
		AssignRolePage.clickDone();

		// check to ensure the changed role has gone through
		userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, "NO ACTION");
		Assert.assertEquals(userAlreadyHasRole, true);

		// --------------------------
		boolean roleHistoryFound = RolesDirectoryViewAccessPage.latestRoleHistoryFound(
				"SHAH departmentroleadmin RAIAAN", Constants.UPDATED, Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER,
				Constants.ORG_GSA, Constants.GO_INTO_ROLE_UPDATED);
		Assert.assertEquals(true, roleHistoryFound);
		RoleHistoryDetailsPage.clickHereLink();
	}

	@Then("^_4 the edited role should show up on the roles pages$")
	public void _4_the_edited_role_should_show_up_on_the_roles_pages() throws Throwable {
		// ------------------edit the role back---------------

		// change the role back
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, "EDIT");
		Assert.assertEquals(userAlreadyHasRole, true);

		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("reverting back");
		CommonMethods.delay(4);
		AssignRolePage.clickDone();
	}

	@Given("^_5 dra logs into workspace$")
	public void _5_dra_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage(draUser, Constants.USERPASS);
	}

	@And("^_5 dra looks up a user who is ra through user directory$")
	public void _5_dra_looks_up_a_user_who_is_ra_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(raUser);
		UserDirectoryPage.clickViewAccessOnly(raUser);
	}

	@Then("^_5 in the roles tab there should be no assign role button$")
	public void _5_in_the_roles_tab_there_should_be_no_assign_role_button() throws Throwable {
		boolean assignRoleButtonFound = RolesDirectoryViewAccessPage
				.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);
		Assert.assertEquals(false, assignRoleButtonFound);
	}

	@When("^_5 dra looks a dra through the user directory$")
	public void _5_dra_looks_a_dra_through_the_user_directory() throws Throwable {
	RolesDirectoryViewAccessPage.goToUserDirectoryPage();
	UserDirectoryPage.searchUserInUserPicker(draUser);
	UserDirectoryPage.clickViewAccessOnly(draUser);
	
	}

	@Then("^_5 there should also be no assign button$")
	public void _5_there_should_also_be_no_assign_button() throws Throwable {
		boolean assignRoleButtonFound = RolesDirectoryViewAccessPage
				.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);
		Assert.assertEquals(false, assignRoleButtonFound);
	}

	@Given("^_6 role admin logs into workspace$")
	public void _6_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.USERPASS);
	}

	@And("^_6 role admin looks up dra user through user directory$")
	public void _6_role_admin_looks_up_dra_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(draUser);
		UserDirectoryPage.clickAssignRole(draUser);
	}

	@And("^_6 role admin tries to assign omb admin to this user$")
	public void _6_role_admin_tries_to_assign_omb_admin_to_this_user() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_OMB_ADMIN);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
	}

	@Then("^_6 correct error message should appear$")
	public void _6_correct_error_message_should_appear() throws Throwable {
		CommonMethods.delay(3);
		boolean errorAlertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, errorAlertFound);
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
