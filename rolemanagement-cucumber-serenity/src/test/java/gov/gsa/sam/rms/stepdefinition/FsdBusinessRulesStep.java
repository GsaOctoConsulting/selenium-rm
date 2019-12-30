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
		AssignRolePage.selectEntityIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
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
		AssignRolePage.selectEntityIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
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

	@Given("^_6 spaad logs in$")
	public void _6_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6 spaad looks up a nonfed user through the user directory$")
	public void _6_spaad_looks_up_a_nonfed_user_through_the_user_directory() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
	}

	@When("^_6 spaad tries to assign a role to this user$")
	public void _6_spaad_tries_to_assign_a_role_to_this_user() throws Throwable {
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NONFED_USER_1);

	}

	@Then("^_6 spaad should not be able to view certain orgs in the entity picker$")
	public void _6_spaad_should_not_be_able_to_view_certain_orgs_in_the_entity_picker() throws Throwable {
		boolean orgnotfound1 = AssignRolePage.selectEntityIfFound(Constants.ORG_UTAH_COMMUNICATIONS_AUTHORITY, 0);
		Assert.assertEquals(false, orgnotfound1);
		AssignRolePage.clearEntitySelector();
		
		boolean orgnotfound2 = AssignRolePage
				.selectEntityIfFound(Constants.ORG_COCACOLA_BOTTLINGCOMPANY_OFNORTHERNNEWENGLAND, 0);
		Assert.assertEquals(false, orgnotfound2);
		AssignRolePage.clearEntitySelector();
		
		boolean orgnotfound3 = AssignRolePage
				.selectEntityIfFound("001441674", 0);
		Assert.assertEquals(false, orgnotfound3);
		AssignRolePage.clearEntitySelector();
		
		
		
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
