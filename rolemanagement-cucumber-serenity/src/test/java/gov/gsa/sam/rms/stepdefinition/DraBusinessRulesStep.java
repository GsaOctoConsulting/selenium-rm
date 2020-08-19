package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.ForBiddenPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;

import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 role admin looks up a no role user through user directory$")
	public void _1_role_admin_looks_up_a_no_role_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

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
		LaunchBrowserUtil.delay(2);
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 role admin looks up a no role user through user directory$")
	public void _2_role_admin_looks_up_a_no_role_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

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
		boolean roleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("SHAH M RAIAAN",
				Constants.ASSIGNED, Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.ORG_GSA,
				Constants.GO_INTO_ROLE_ASSIGNED);
		Assert.assertEquals(true, roleHistoryFound);
		
		// --------delete the role-------------------
		UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN, Constants.DELETE);
	}

	@Given("^_3 role admin logs into workspace$")
	public void _3_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 role admin looks up dra user through user directory$")
	public void _3_role_admin_looks_up_dra_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

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
		AssignRolePage.clickCancelButtonWhenAlertIsOn();
		;
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
		boolean draRoleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, draRoleFound);

		boolean systemAccountAdminRoleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_SYSTEM_ACCOUNT_ADMIN, Constants.DOMAIN_ADMIN, Constants.DELETE);
		Assert.assertEquals(true, systemAccountAdminRoleFound);

	}

	@Given("^_4 dra logs into workspace$")
	public void _4_dra_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4 dra looks up a user with contracting officer role through user directory$")
	public void _4_dra_looks_up_a_user_with_contracting_officer_role_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(coUser);
		UserDirectoryPage.clickViewAccess(coUser);
	}

	@And("^_4 dra edits the role to contracting specialist$")
	public void _4_dra_edits_the_role_to_contracting_specialist() throws Throwable {
		// check whether user already has the role

		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, "EDIT");
		Assert.assertEquals(userAlreadyHasRole, true);
		// edit the role
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("changing roles");
		AssignRolePage.clickDone();

		// check to ensure the changed role has gone through
		userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, "NO ACTION");
		Assert.assertEquals(userAlreadyHasRole, true);

		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound(
				"SHAH departmentroleadmin RAIAAN", Constants.UPDATED, Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER,
				Constants.ORG_GSA, Constants.GO_INTO_ROLE_UPDATED);
		Assert.assertEquals(true, roleHistoryFound);
		
	}

	@Then("^_4 the edited role should show up on the roles pages$")
	public void _4_the_edited_role_should_show_up_on_the_roles_pages() throws Throwable {
		// ------------------edit the role back---------------

		// change the role back
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, "EDIT");
		Assert.assertEquals(userAlreadyHasRole, true);

		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("reverting back");
		LaunchBrowserUtil.delay(4);
		AssignRolePage.clickDone();
	}

	@Given("^_5 dra logs into workspace$")
	public void _5_dra_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5 dra looks up a user who is ra through user directory$")
	public void _5_dra_looks_up_a_user_who_is_ra_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(raUser);
		UserDirectoryPage.clickViewAccessOnly(raUser);
	}

	@Then("^_5 in the roles tab there should be no assign role button$")
	public void _5_in_the_roles_tab_there_should_be_no_assign_role_button() throws Throwable {
		boolean assignRoleButtonFound = UserDirectoryViewAccessPage
				.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);
		Assert.assertEquals(false, assignRoleButtonFound);
	}

	@When("^_5 dra looks a dra through the user directory$")
	public void _5_dra_looks_a_dra_through_the_user_directory() throws Throwable {
		UserDirectoryViewAccessPage.goToUserDirectoryPage();
		UserDirectoryPage.searchUserInUserPicker(draUser);
		UserDirectoryPage.clickViewAccessOnly(draUser);

	}

	@Then("^_5 there should also be no assign button$")
	public void _5_there_should_also_be_no_assign_button() throws Throwable {
		boolean assignRoleButtonFound = UserDirectoryViewAccessPage
				.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);
		Assert.assertEquals(false, assignRoleButtonFound);
	}

	@Given("^_6 role admin logs into workspace$")
	public void _6_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6 role admin looks up dra user through user directory$")
	public void _6_role_admin_looks_up_dra_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

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
		LaunchBrowserUtil.delay(3);
		boolean errorAlertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, errorAlertFound);
	}

	@Given("^_7 dra logs into workspace$")
	public void _7_dra_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_7 dra navigates to profile page$")
	public void _7_dra_navigates_to_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		boolean draRoleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, draRoleFound);
		LaunchBrowserUtil.delay(1);

	}

	@Then("^_7 dra should not see the role request button$")
	public void _7_dra_should_not_see_the_role_request_button() throws Throwable {
		boolean requstrolebuttonFound = MyRolesPage.elementFound(MyRolesPageLocator.REQUESTROLE_BUTTON);
		Assert.assertEquals(false, requstrolebuttonFound);
	}

	@When("^_7 dra tries to hit the role request page directly through url$")
	public void _7_dra_tries_to_hit_the_role_request_page_directly_through_url() throws Throwable {
		LaunchBrowserUtil.enterUrl(Constants.SAM_HOME_PAGE+"/profile/request-role");
		ForBiddenPage.setDriver(MyRolesPage.getDriver());
		LaunchBrowserUtil.delay(2);
	}

	@Then("^_7 dra should not be able to access the role request page$")
	public void _7_dra_should_not_be_able_to_access_the_role_request_page() throws Throwable {
		String message = ForBiddenPage.getPrimaryContentMessage();
		Assert.assertEquals("You do not have sufficient privileges to view the requested page.", message);
	}

	@Given("^_8 dra logs into workspace$")
	public void _8_dra_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_8 dra should not see migrate roles link$")
	public void _8_dra_should_not_see_migrate_roles_link() throws Throwable {
		boolean migrateRolesFound=T1WorkspacePage.profileLinksFound("Migrate Roles");
		Assert.assertEquals(false, migrateRolesFound);
	}

	@When("^_8 dra navigates to profile page$")
	public void _8_dra_navigates_to_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		boolean draRoleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN,
				Constants.NOACTION);
		Assert.assertEquals(true, draRoleFound);
	}

	@Then("^_8 they should not see the role migration banner$")
	public void _8_they_should_not_see_the_role_migration_banner() throws Throwable {
		boolean bannerFound = MyRolesPage.elementFound(MyRolesPageLocator.BEGINNOW_LINK);
		Assert.assertEquals(false, bannerFound);
	}

	@When("^_8 dra tries to hit the role migration page directly through url$")
	public void _8_dra_tries_to_hit_the_role_migration_page_directly_through_url() throws Throwable {
		LaunchBrowserUtil.enterUrl(Constants.SAM_HOME_PAGE+"/profile/migrations");
		ForBiddenPage.setDriver(MyRolesPage.getDriver());
		LaunchBrowserUtil.delay(2);
	}

	@Then("^_8 dra they should not be able to access the role request page$")
	public void _8_dra_they_should_not_be_able_to_access_the_role_request_page() throws Throwable {
		String message = ForBiddenPage.getPrimaryContentMessage();
		Assert.assertEquals("You do not have sufficient privileges to view the requested page.", message);
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
