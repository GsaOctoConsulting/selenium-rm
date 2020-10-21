package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import junit.framework.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RoleInviteAssignRolePage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class RoleAssignStep {
	private static Logger logger = LoggerFactory.getLogger(RoleAssignStep.class);

	@Given("^_1 user logs in with dra role$")
	public void _1_user_logs_in_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user navigates to userdirectory and looks up a no role user$")
	public void _1_user_navigates_to_userdirectory_and_looks_up_a_no_role_user() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@Then("^_1 user gives assistance user role in assistance listing$")
	public void _1_user_gives_assistance_user_role_in_assistance_listing() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		LaunchBrowserUtil.delay(5);

		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan", Constants.ASSIGNED,
				Constants.ROLE_ASSISTANCE_USER, Constants.ORG_GSA, Constants.GO_INTO_ROLE_ASSIGNED);// also checking if
																									// role history is
																									// clickable
		Assert.assertEquals(true, roleHistoryFound);

		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_1 the user logs back in they should see the assigned role$")
	public void _1_the_user_logs_back_in_they_should_see_the_assigned_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 the user should also see the role history updated with correct name format$")
	public void _1_the_user_should_also_see_the_role_history_updated_with_correct_name_format() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(userAlreadyHasRole, true);

		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan", Constants.ASSIGNED,
				Constants.ROLE_ASSISTANCE_USER, Constants.ORG_GSA, Constants.NOACTION);
		Assert.assertEquals(true, roleHistoryFound);

		// String latesthistorydescription = MyRolesPage.getLatestRoleHistory();
//		String descriptionwordarray[] = latesthistorydescription.split(" ");
//		String requestername = descriptionwordarray[0] + descriptionwordarray[1];
//		logger.info("The name of the requester is -- " + requestername);
//		Assert.assertTrue(FeedsRequestPage.isStringOnlyAlphabetAndSpace(requestername));
		LaunchBrowserUtil.delay(5);

	}

	@Then("^_1 the dra should be able to remove the role for the user$")
	public void _1_the_dra_should_be_able_to_remove_the_role_for_the_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);

		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		afterScenario();
	}

	@Given("^_2 user logs in as dra$")
	public void _2_user_logs_in_as_dra() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user navigates to Assign Role Page$")
	public void _2_user_navigates_to_assign_role_page() throws Throwable {

		LaunchBrowserUtil.scrollAllTheWayDown();
		String noRoleUser = "shah.raiaan+noRoles@gsa.gov";
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(noRoleUser);
		UserDirectoryPage.clickAssignRole(noRoleUser);

	}

	@Then("^_2 organziation box should show as empty$")
	public void _2_organziation_box_should_show_as_empty() throws Throwable {
		Assert.assertEquals(AssignRolePage.getCurrentTextInOrgPicker().trim(), "");
	}

	@Given("^_3 user logs in with ra role$")
	public void _3_user_logs_in_with_ra_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 user navigates to userdirectory and looks up a non-fed no role user$")
	public void _3_user_navigates_to_userdirectory_and_looks_up_a_nonfed_no_role_user() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		String noRoleUser = "raiaan.shah+41@gmail.com";
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(noRoleUser);
		UserDirectoryPage.clickAssignRole(noRoleUser);
	}

	@Then("^_3 user gives data entry role in contract opportunities$")
	public void _3_user_gives_data_entry_role_in_contract_opportunities() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, "DELETE");
		Assert.assertEquals(userAlreadyHasRole, true);
		afterScenario();
	}

	@Given("^_4 user logs in with ra role$")
	public void _4_user_logs_in_with_ra_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_4 user navigates to userdirectory and looks up a fed user$")
	public void _4_user_navigates_to_userdirectory_and_looks_up_a_fed_user() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_4 ra tries to assign role they should not see subtier admin instead of agency admin$")
	public void _4_ra_tries_to_assign_role_they_should_not_see_subtier_admin_instead_of_agency_admin()
			throws Throwable {
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);

		boolean agencyAdminRoleFound = AssignRolePage.selectRoleIfFound(Constants.ROLE_AGENCY_ADMIN);
		Assert.assertEquals(false, agencyAdminRoleFound);

		boolean subtierAdminRoleFound = AssignRolePage.selectRoleIfFound(Constants.ROLE_SUBTIER_ADMIN);
		Assert.assertEquals(true, subtierAdminRoleFound);
	}

	@Given("^_5 user logs in with administrator all domains role$")
	public void _5_user_logs_in_with_administrator_all_domains_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_5 user navigates to userdirectory and looks up a no role user$")
	public void _5_user_navigates_to_userdirectory_and_looks_up_a_no_role_user() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@Then("^_5 aad should be able to assign administrator role in contract data in subtier$")
	public void _5_aad_should_be_able_to_assign_administrator_role_in_contract_data_in_subtier() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA_FAS_OFFICE_OF_ACQUISITIONOPERA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTDATA_ADMIN);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(
				Constants.ORG_GSA_FAS_OFFICE_OF_ACQUISITIONOPERA, Constants.ROLE_CONTRACTDATA_ADMIN,
				Constants.DOMAIN_CONTRACT_DATA, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		afterScenario();
	}

	@Given("^_6ra user logs in as sampmo admin$")
	public void _6ra_user_logs_in_as_sampmo_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6ra user navigates to userdirectory and looks up a user with system account admin role$")
	public void _6ra_user_navigates_to_userdirectory_and_looks_up_a_user_with_system_account_admin_role()
			throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
	}

	@When("^_6ra spaad tries to assign adminstrator all domains role to this user$")
	public void _6ra_spaad_tries_to_assign_adminstrator_all_domains_role_to_this_user() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_SYSTEM_ACCOUNT_ADMIN, Constants.DOMAIN_ADMIN, Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole1);

		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("assigning this role");
		// AssignRolePage.clickDone();

	}

	@Then("^_6ra appropriate error message should be displayed$")
	public void _6ra_appropriate_error_message_should_be_displayed() throws Throwable {
		boolean alertFound = AssignRolePage.elementFound(AssignRolePageLocator.ERROR_ALERT);
		Assert.assertEquals(true, alertFound);
	}

	@Given("^_7ra user logs in as contract opportunities admin$")
	public void _7ra_user_logs_in_as_contract_opportunities_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_7ra user navigates to userdirectory and looks up a user with contracting officer role in contract opp$")
	public void _7ra_user_navigates_to_userdirectory_and_looks_up_a_user_with_contracting_officer_role_in_contract_opp()
			throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGOFFICER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGOFFICER_1);
	}

	@When("^_7ra admin tries to assign same role to this user$")
	public void _7ra_admin_tries_to_assign_same_role_to_this_user() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole1);

		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);

	}

	@Then("^_7ra appropriate error message should be displayed$")
	public void _7ra_appropriate_error_message_should_be_displayed() throws Throwable {
		String alertmessage = AssignRolePage.getPendingUserAccessAlertMessage();
		Assert.assertTrue(alertmessage.contains("User(s) already has another role for the same org hierarchy."));
	}

	@Given("^_8ra user logs in as spaad$")
	public void _8ra_user_logs_in_as_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_8ra user navigates to userdirectory and looks up a user in hhs with no role$")
	public void _8ra_user_navigates_to_userdirectory_and_looks_up_a_user_in_hhs_with_no_role() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.HHS_ASSISTANCEUSER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.HHS_ASSISTANCEUSER_1);
	}

	@When("^_8ra spaad tries to assign gsa security approve role to this user with gsa$")
	public void _8ra_spaad_tries_to_assign_gsa_security_approve_role_to_this_user_with_gsa() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_HHS,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole1);
		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_GSA_SECURITYAPPROVER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);

	}

	@Then("^_8ra appropriate error message should be displayed$")
	public void _8ra_appropriate_error_message_should_be_displayed() throws Throwable {
		String alertmessage = AssignRolePage.getPendingUserAccessAlertMessage();
		Assert.assertTrue(
				alertmessage.contains("This role can only be assigned to user(s) belonging to GSA organization."));
	}

	@Given("^_9ra user logs in as admin in contract data$")
	public void _9ra_user_logs_in_as_admin_in_contract_data() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_9ra user navigates to userdirectory and looks up a user with sampmo admin role in contract opportunities$")
	public void _9ra_user_navigates_to_userdirectory_and_looks_up_a_user_with_sampmo_admin_role_in_contract_opportunities()
			throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_OPPORTUNITIES_SPA_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_OPPORTUNITIES_SPA_1);
	}

	@Then("^_9ra admin should see admin role button and proceed to assign a role to the user$")
	public void _9ra_admin_should_see_admin_role_button_and_proceed_to_assign_a_role_to_the_user() throws Throwable {

		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("assigning this role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_9ra role assign should be successful$")
	public void _9ra_role_assign_should_be_successful() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole1);
	}

	@Given("^_10ra user with spaad role logs in$")
	public void _10ra_user_with_spaad_role_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_10ra user navigates to userdirectory and looks up a user with no roles$")
	public void _10ra_user_navigates_to_userdirectory_and_looks_up_a_user_with_no_roles() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@When("^_10ra admin selects two orgs in the same hierarchy$")
	public void _10ra_admin_selects_two_orgs_in_the_same_hierarchy() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 1);

	}

	@And("^_10ra admin tries to assign (.+) role in (.+)$")
	public void _10ra_admin_tries_to_assign_assistance_user_role(String role, String domain) throws Throwable {
		AssignRolePage.selectRoleIfFound(role);
		AssignRolePage.selectDomainIfFound(domain);
	}

	@Then("^_10ra admin will see error message$")
	public void _10ra_admin_will_see_error_message() throws Throwable {
		LaunchBrowserUtil.delay(3);
		String alertfound = AssignRolePage.getAlertText();

		Assert.assertTrue(alertfound
				.contains("User cannot be assigned a role to more than one organization within the same hierarchy"));
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
