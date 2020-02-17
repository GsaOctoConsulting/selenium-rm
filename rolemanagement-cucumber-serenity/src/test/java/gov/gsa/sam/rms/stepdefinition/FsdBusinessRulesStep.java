package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RequestRoleOptionalPage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;

import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class FsdBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(FsdBusinessRulesStep.class);
	String nonfeduseremail = "";

	@Given("^_1 non fed user signs up$")
	public void _1_non_fed_user_signs_up() throws Throwable {
		beforeScenario();
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		SignUpUtility.signUpNewUserNonFed("nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com",
				Constants.USERPASS);
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@And("^_1 role admin logs in$")
	public void _1_role_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 role admin looks up the newly signed nonfed user in user directory$")
	public void _1_role_admin_looks_up_the_newly_signed_nonfed_user_in_user_directory() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(nonfeduseremail);

	}

	@And("^_1 role admin should be able to view the users profile$")
	public void _1_role_admin_should_be_able_to_view_the_users_profile() throws Throwable {
		UserDirectoryPage.clickAssignRole(nonfeduseremail);
	}

	@Then("^_1 role admin should be able to assign fsdagent role to the nonfed user$")
	public void _1_role_admin_should_be_able_to_assign_fsdagent_role_to_the_nonfed_user() throws Throwable {
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_FSD_AGENT);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// ---------delete the newly granted role-----------

		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_FSD_AGENT, Constants.DOMAIN_ADMIN, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);
		afterScenario();
	}

	@Given("^_2 non fed user signs up$")
	public void _2_non_fed_user_signs_up() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		SignUpUtility.signUpNewUserNonFed("nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com",
				Constants.USERPASS);
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";

		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToAccountDetailsPage();

	}

	@And("^_2 fsd admin admin logs in$")
	public void _2_fsd_admin_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FSD_ADMIN_5, Constants.USERPASS,
				ConstantsAccounts.FSD_ADMIN_5_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 fsd admin looks up the newly signed nonfed user in user directory$")
	public void _2_fsd_admin_looks_up_the_newly_signed_nonfed_user_in_user_directory() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(nonfeduseremail);
	}

	@And("^_2 fsd admin should be able to view the users profile$")
	public void _2_fsd_admin_should_be_able_to_view_the_users_profile() throws Throwable {
		UserDirectoryPage.clickAssignRole(nonfeduseremail);
	}

	@Then("^_2 fsd admin should be able to assign fsdagent role to the nonfed user$")
	public void _2_fsd_admin_should_be_able_to_assign_fsdagent_role_to_the_nonfed_user() throws Throwable {
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_FSD_AGENT);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ADMIN);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_FSD_AGENT, Constants.DOMAIN_ADMIN, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);
		afterScenario();
	}

	@Given("^_3 fsd admin logs in$")
	public void _3_fsd_admin_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FSD_ADMIN_5, Constants.USERPASS,
				ConstantsAccounts.FSD_ADMIN_5_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 fsd admin looks up a nonfed user with data entry role$")
	public void _3_fsd_admin_looks_up_a_nonfed_user_with_data_entry_role() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.clickViewAccessOnly(ConstantsAccounts.NONFED_USER_1);
	}

	@And("^_3 fsd admin should be able to view permission for this user$")
	public void _3_fsd_admin_should_be_able_to_view_permission_for_this_user() throws Throwable {
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.VIEW_PERMISSION);
		Assert.assertEquals(true, userAlreadyHasRole);

		String permissionspageheading = LaunchBrowserUtil.getDriver().findElement(By.tagName("h1")).getText();
		Assert.assertEquals(true, permissionspageheading.contains("Permissions"));
	}

	@Given("^_4 fsd admin logs in$")
	public void _4_fsd_admin_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FSD_ADMIN_5, Constants.USERPASS,
				ConstantsAccounts.FSD_ADMIN_5_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4 fsd admin looks up a nonfed user with multiple roles$")
	public void _4_fsd_admin_looks_up_a_nonfed_user_with_multiple_roles() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_MULTIPLE_ROLES);
		UserDirectoryPage.clickViewAccessOnly(ConstantsAccounts.NONFED_USER_MULTIPLE_ROLES);
	}

	@And("^_4 fsd admin should be able to view permission for this user$")
	public void _4_fsd_admin_should_be_able_to_view_permission_for_this_user() throws Throwable {
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.VIEW_PERMISSION);
		Assert.assertEquals(true, userAlreadyHasRole);

		LaunchBrowserUtil.navigateBack();
		LaunchBrowserUtil.delay(2);

		boolean userAlreadyHasRole1 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_CODA_OCTOPUS,
				Constants.ROLE_VIEWER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.VIEW_PERMISSION);
		Assert.assertEquals(true, userAlreadyHasRole1);

		LaunchBrowserUtil.navigateBack();
		LaunchBrowserUtil.delay(2);

		boolean userAlreadyHasRole2 = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_JACKSON_BOOKBINDING,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.VIEW_PERMISSION);
		Assert.assertEquals(true, userAlreadyHasRole2);

		LaunchBrowserUtil.navigateBack();
	}

	@Given("^_5 fsd agent logs in$")
	public void _5_fsd_agent_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FSD_AGENT_1, Constants.USERPASS,
				ConstantsAccounts.FSD_AGENT_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5 fsd agent looks up a nonfed user with data entry role$")
	public void _5_fsd_agent_looks_up_a_nonfed_user_with_data_entry_role() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.clickViewAccessOnly(ConstantsAccounts.NONFED_USER_1);
	}

	@And("^_5 fsd agent should be able to view permission for this user$")
	public void _5_fsd_agent_should_be_able_to_view_permission_for_this_user() throws Throwable {
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.VIEW_PERMISSION);
		Assert.assertEquals(true, userAlreadyHasRole);

		String permissionspageheading = LaunchBrowserUtil.getDriver().findElement(By.tagName("h1")).getText();
		Assert.assertEquals(true, permissionspageheading.contains("Permissions"));
	}

	@Given("^_6 nonfed user from octo consulting logs in$")
	public void _6_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_6 nonfed user navigates to role request page$")
	public void _6_spaad_looks_up_a_nonfed_user_through_the_user_directory() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
	}

	@When("^_6 nonfed user tries to search in the entity picker$")
	public void _6_spaad_tries_to_assign_a_role_to_this_user() throws Throwable {

	}

	@Then("^_6 nonfed user should not be able to view certain orgs in the entity picker$")
	public void _6_spaad_should_not_be_able_to_view_certain_orgs_in_the_entity_picker() throws Throwable {
		boolean orgnotfound1 = RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_UTAH_COMMUNICATIONS_AUTHORITY,
				0);
		Assert.assertEquals(false, orgnotfound1);

		boolean orgnotfound2 = RequestRolePage
				.selectEntityNonFedIfFound(Constants.ORG_COCACOLA_BOTTLINGCOMPANY_OFNORTHERNNEWENGLAND, 0);
		Assert.assertEquals(false, orgnotfound2);

		boolean orgnotfound3 = RequestRolePage.selectEntityNonFedIfFound("001441674", 0);
		Assert.assertEquals(false, orgnotfound3);
	}

	@Given("^_7 nonfed user logs in$")
	public void _7_nonfed_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@When("^_7 nonfed user goes to the profile page$")
	public void _7_user_goes_to_the_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_7 user should not see the entity section in account details tab$")
	public void _7_user_should_not_see_the_entity_section_in_the_account_details_tab() throws Throwable {
		boolean entitysectionFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.ENTITY_INFO);
		Assert.assertEquals(false, entitysectionFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_7 spaad logs in$")
	public void _7_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_NONFED);
	}

	@When("^_7 spaad looks up the nonfed user$")
	public void _7_looks_up_the_user_through_user_directory() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_1);
		AccountDetailsPage.goToPageOnSideNav("Account Details");
	}

	@Then("^_7 spaad should not see the entity section for this users profile$")
	public void _7_spaad_should_not_be_able_to_see_the_entity_section_in_the_users_profile_page() throws Throwable {
		boolean entitysectionFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.ENTITY_INFO);
		Assert.assertEquals(false, entitysectionFound);
		LaunchBrowserUtil.delay(5);
	}

	@Given("^_8fbr spaad user logs in$")
	public void _8fbr_spaad_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_8fbr spaad looks upa a nonfed user with roles$")
	public void _8fbr_spaad_looks_upa_a_nonfed_user_with_roles() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_MULTIPLE_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_MULTIPLE_ROLES);
	}

	@When("^_8fbr spaad tries to assign fsd agent to this user$")
	public void _8fbr_spaad_tries_to_assign_fsd_agent_to_this_user() throws Throwable {
		LaunchBrowserUtil.delay(3);
		UserDirectoryViewAccessPage.clickAssignRole();

	}

	@Then("^_8fbr spaad should not see fsd agent in the dropdown$")
	public void _8fbr_spaad_should_not_see_fsdagent_in_the_dropdown() throws Throwable {
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		boolean fsdagentrolefound = AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_FSD_AGENT);
		Assert.assertEquals(false, fsdagentrolefound);

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_8fbr fsd admin logs in$")
	public void _8fbr_fsd_admin_logs_in() throws Throwable {

	}

	@And("^_8fbr fsd admin looks up a nonfed user with roles$")
	public void _8fbr_fsd_admin_looks_up_a_nonfed_user_with_roles() throws Throwable {

	}

	@When("^_8fbr fsd admin tries to assign fsd agent to this user$")
	public void _8fbr_fsdadmin_tries_to_assign_fsd_agent_to_this_user() throws Throwable {

	}

	@Then("^_8fbr fsd admin should see an error message$")
	public void _8fbr_fsd_admin_should_see_an_error_message() throws Throwable {

	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
