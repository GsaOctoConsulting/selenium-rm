package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
		SignUpUtility.signUpNewUserNonFed(
				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser"+counter+"@yopmail.com";
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
		SignUpUtility.signUpNewUserNonFed(
				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser"+counter+"@yopmail.com";

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

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
