package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RequestRoleOptionalPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.PropertiesFileUtility;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;
import junit.framework.Assert;
import gov.gsa.sam.rms.pages.T1WorkspacePage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginStep {

	private static Logger logger = LoggerFactory.getLogger(LoginStep.class);
	String newid = "";
	String secretkey = "";

	@Given("^_1 user already has dra account setup and enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_already_has_dra_account_setup_and_enters_and(String arg1, String arg2) throws Throwable {

		beforeScenario();
		
		/*  SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1,
		  Constants.USERPASS, ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY,
		  Constants.USER_FED);*/
		 
		/*
		 * SignInUtility.signIntoWorkspace(ConstantsAccounts.
		 * CONTRACT_OPPORTUNITIES_SPA_1, Constants.USERPASS,
		 * ConstantsAccounts.CONTRACT_OPPORTUNITIES_SPA_1_SECRETKEY,
		 * Constants.USER_FED); CommonMethods.delay(4);
		 */
	/*	SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);*/
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	
		//WorkspacePage.goToRoleMigrationPage();
		
		
	/*	SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);*/
		//LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		/*
		 * RMWidgetUtility.clickUserDirectoryLink();
		 * UserDirectoryPage.searchUserInUserPicker("shah.raiaan+noroless2@gsa.gov");
		 * UserDirectoryPage.clickAssignRole("shah.raiaan+noroless2@gsa.gov");
		 * CommonMethods.delay(4); AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		 * AssignRolePage.selectRoleIfFound(Constants.
		 * ROLE_AL_GRANDUSER_SAMPMOADMINISTRATOR, Constants.DOMAIN_FEDERAL_HIERARCHY);
		 * //AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		 * //AssignRolePage.writeComment("test"); //AssignRolePage.clickDone();
		 */

		
		 /* SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2,
		  Constants.USERPASS, ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY,
		  Constants.USER_FED); */
		 

		
	/*	  SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2,
		  Constants.USERPASS, ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY,
		  Constants.USER_FED); */
		 

		/*
		 * SignInUtility.testSignin(LaunchBrowserUtil.getDriver(),
		 * Constants.ACTIVE_ENV_URL, ConstantsAccounts.DEPT_ROLEADMIN_2,
		 * Constants.USERPASS, ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY);
		 */

		 /* SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1,
		  Constants.USERPASS, ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY,
		  Constants.USER_FED);*/
		 

		/*
		 * SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2,
		 * Constants.USERPASS, ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY,
		 * Constants.USER_FED); CommonMethods.delay(4);
		 */
		
		
		  /*SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1,
		  Constants.USERPASS, ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY,
		  Constants.USER_FED);*/
		 
		/*SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
		ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);*/
		
	//	LaunchBrowserUtil.captureSignUpLinkFromGmail();
		
		
		
	}

	@Then("^_1 dra should see UserDirectory Widget$")
	public void dra_should_see_userdirectory_widget() throws Throwable {
		/*
		 * boolean rmWidgetFound = RMWidgetUtility.widgetFound();
		 * Assert.assertEquals(rmWidgetFound, true); afterScenario();
		 * //LaunchBrowserUtil.closeBrowsers();
		 */
	}

	@Given("^_2 user has an account in login dot gov domain$")
	public void _2_user_has_an_account_in_login_dot_gov_domain() throws Throwable {
		SignInUtility.signIntoWorkspace("shah.raiaan+newregistereduser319@gsa.gov",
				Constants.USERPASS, "V3ZZVK7YC3XUXE4A", Constants.USER_FED);

		/*
		 * SignUpUtility.signUpNewUser("shah.raiaan+newregistereduser21@gsa.gov",
		 * Constants.userPass); CommonProfilePage.enterWorkphone("5555555555");
		 * LaunchBrowserUtil.scrolldownToRmWidget();
		 * CommonProfilePage.selectOrg(Constants.ORG_GSA,0);
		 * CommonProfilePage.clickSubmitButton();
		 * LaunchBrowserUtil.scrolldownToRmWidget();
		 * RequestRolePage.clickSkipAndFinish();
		 */

	}

	@And("^_2 the user did not complete the profile$")
	public void _2_user_did_not_complete_the_profile() throws Throwable {

	}

	@Then("^_2 user should be shown the complete profile page when logging back in$")
	public void _2_user_should_be_able_to_sign_into_workspace() throws Throwable {
		CommonProfilePage.setDriver(LaunchBrowserUtil.getDriver());
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_3 user has an account in login dot gov domain$")
	public void _3_user_has_an_account_in_login_dot_gov_domain() throws Throwable {
		SignInUtility.signIntoWorkspace("shah.raiaan+profilecompleted@gsa.gov", Constants.USERPASS,
				"TKWPBBXYHQOBGD7L", Constants.USER_FED);
	}

	@And("^_3 the user already completed the profile page details$")
	public void _3_the_user_already_completed_the_profile_page_details() throws Throwable {

	}

	@Then("^_3 user should land on the workspace page and be able to signout$")
	public void _3_user_should_land_on_the_workspace_page_and_be_able_to_signout() throws Throwable {
		T1WorkspacePage.signOut();
	}

	// -----------------

	@Given("^_4 user has an existing dra account in sam dot gov domain$")
	public void _4_user_has_an_existing_account_in_sam_dot_gov_domain() throws Throwable {

	}

	@And("^_4 user enters same id in login dot gov to signin$")
	public void _4_user_enters_same_id_in_login_dot_gov_to_signin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_4 user should be able to log into workspace$")
	public void _4_user_should_be_able_to_log_into_workspace() throws Throwable {

	}

	@And("^_4 user navigates to myprofile page$")
	public void _4_user_navigates_to_myprofile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_4 they should see their roles show up$")
	public void _4_they_should_see_their_roles_show_up() throws Throwable {
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS, Constants.DOMAIN_ADMIN, "NO ACTION");
		Assert.assertEquals(roleFound, true);
	}

	@Given("^_5 user has an existing assistance user account in sam dot gov domain$")
	public void _5_user_has_an_existing_assistance_user_account_in_sam_dot_gov_domain() throws Throwable {

	}

	@And("^_5 user enters same id in login dot gov to signin$")
	public void _5_user_enters_same_id_in_login_dot_gov_to_signin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_5 user should be able to log into workspace$")
	public void _5_user_should_be_able_to_log_into_workspace() throws Throwable {

	}

	@And("^_5 user navigates to myprofile page$")
	public void _5_user_navigates_to_myprofile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_5 they should see their roles show up$")
	public void _5_they_should_see_their_roles_show_up() throws Throwable {
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, "NO ACTION");
		Assert.assertEquals(roleFound, true);

		boolean beginNowLinkFound = MyRolesPage.beginNowLinkFound();
		Assert.assertEquals(beginNowLinkFound, true);
	}

	@Given("^_6 user has an existing system account admin in sam dot gov domain$")
	public void _6_user_has_an_existing_system_account_admin_in_sam_dot_gov_domain() throws Throwable {

	}

	@And("^_6 user enters same id in login dot gov to signin$")
	public void _6_user_enters_same_id_in_login_dot_gov_to_signin() throws Throwable {
		SignInUtility.signIntoWorkspace("shah.raiaan+saadmin@gsa.gov", Constants.USERPASS,
				"CE667WGKHWKY5GC7", Constants.USER_FED);
	}

	@Then("^_6 user should be able to log into workspace$")
	public void _6_user_should_be_able_to_log_into_workspace() throws Throwable {

	}

	@And("^_6 user navigates to myprofile page$")
	public void _6_user_navigates_to_myprofile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_6 they should see their roles show up$")
	public void _6_they_should_see_their_roles_show_up() throws Throwable {
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_SYSTEM_ACCOUNT_ADMIN,
				Constants.DOMAIN_ADMIN, "NO ACTION");
		Assert.assertEquals(roleFound, true);

		boolean beginNowLinkFound = MyRolesPage.beginNowLinkFound();
		Assert.assertEquals(beginNowLinkFound, true);
	}
	// ------------

	@When("^_7 user creates a new account in login dot gov$")
	public void _7_user_creates_a_new_account_in_login_dot_gov() throws Throwable {
		//String counter = SignUpUtility.updatecounter("login.fed.accountno");
		//SignUpUtility.signUpNewUser("octotestaccount1+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
		SignUpUtility.signUpNewUser(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS);
	}

	@Then("^_7 user should be able to complete the profile$")
	public void _7_user_should_be_able_to_complete_the_profile() throws Throwable {
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		//CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
	}

	@And("^_7 user should land on my workspace page$")
	public void _7_user_should_land_on_my_workspace_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();
		T1WorkspacePage.goToAccountDetailsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		// AccountDetailsPage.clickDeactivateAccount();
	}

	@When("^_8 nonfed user creates a new account in login dot gov$")
	public void _8_nonfed_user_creates_a_new_account_in_login_dot_gov() throws Throwable {

		//String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		//SignUpUtility.signUpNewUserNonFed("nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com",
			//Constants.USERPASS);
		SignUpUtility.signUpNewUserNonFed(ConstantsAccounts.NONFED_USER_MULTIPLE_ROLES,Constants.USERPASS);
	}
	@Then("^_8 user should be able to complete the profile$")
	public void _8_user_should_be_able_to_complete_the_profile() throws Throwable {
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		CommonProfilePage.clickSubmitButton();
	}

	@And("^_8 user should land on my workspace page$")
	public void _8_user_should_land_on_my_workspace_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		// AccountDetailsPage.clickDeactivateAccount();
	}

	@When("^_9 user creates a new account in login dot gov$")
	public void _9_user_creates_a_new_account_in_login_dot_gov() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		SignUpUtility.signUpNewUser("shah.raiaan+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
	}

	@Then("^_9 user should be able to complete the profile$")
	public void _9_user_should_be_able_to_complete_the_profile() throws Throwable {
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
	}

	@And("^_9 user should be able to request role before landing on my workspace page$")
	public void _9_user_should_be_able_to_request_role_before_landing_on_my_workspace_page() throws Throwable {
		RequestRoleOptionalPage.enterSupervisorName("sample name");
		RequestRoleOptionalPage.enterSupervisorEmail("valid@invalid.valid");
		RequestRoleOptionalPage.selectOrgIfFound(Constants.ORG_GSA, 0);
		RequestRoleOptionalPage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRoleOptionalPage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRoleOptionalPage.enterAdditionalDetails("additional comments");
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickFinishButton();
	}

	@Then("^_9 user should see feeds notifications for the role requested$")
	public void _9_user_should_see_feeds_notifications_for_the_role_requested() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		LaunchBrowserUtil.delay(3);
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				"", Constants.STATUS_PENDING, Constants.REJECTROLE);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(2);
	}

	/*
	 * @Given("^_6 user has an existing account in sam dot gov domain$") public void
	 * _6_user_has_an_existing_account_in_sam_dot_gov_domain() throws Throwable {
	 * SignInUtility.signIntoCommonWorkspaceWithLoginDotGov("shah.raiaan+2@gsa.gov",
	 * Constants.userPass, "6XA6VHAAALNL6Q7S"); }
	 * 
	 * @And("^_6 user logs into workspace$") public void
	 * _6_user_logs_into_workspace() throws Throwable {
	 * 
	 * }
	 * 
	 * @And("^_6 user navigates to my profile page$") public void
	 * _6_user_navigates_to_my_profile_page() throws Throwable {
	 * 
	 * }
	 * 
	 * @Then("^_6 user should be able to request role$") public void
	 * _6_user_should_be_able_to_request_role() throws Throwable {
	 * 
	 * }
	 */
	// private methods are below this line
	@When("^_10 feduser creates a new account in login dot gov$")
	public void _10_feduser_creates_a_new_account_in_login_dot_gov() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		secretkey = SignUpUtility.signUpNewUser("octotestaccount1+newregistereduser" + counter + "@gsa.gov",
				Constants.USERPASS);
		newid = "octotestaccount1+newregistereduser" + counter + "@gsa.gov";

	}

	@And("^_10 user signs out$")
	public void _10_user_signs_out() throws Throwable {
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();
		T1WorkspacePage.signOut();
	}

	@And("^_10 user goes to login dot gov to recover password$")
	public void _10_user_goes_to_login_dot_gov_to_recover_password() throws Throwable {
		LaunchBrowserUtil.recoverThroughForgotPasswordForFed(newid, secretkey, Constants.PASSWORD_NEW);
	}

	@And("^_10 user creates new password and navigates given links$")
	public void _10_user_creates_a_new_password_and_navigates_given_links() throws Throwable {

	}

	@Then("^_10 user should be able to login into the workspace$")
	public void _10_user_should_be_able_to_login_into_the_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(newid, Constants.PASSWORD_NEW, secretkey, Constants.USER_FED);
	}

	@When("^_11 nonfeduser creates a new account in login dot gov$")
	public void _11_nonfeduser_creates_a_new_account_in_login_dot_gov() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		secretkey = SignUpUtility.signUpNewUserNonFed("raiaan.shah+newregisterednonfeduser" + counter + "@gmail.com",
				Constants.USERPASS);
		newid = "raiaan.shah+newregisterednonfeduser" + counter + "@gmail.com";
	}

	@And("^_11 user signs out$")
	public void _11_user_signs_out() throws Throwable {
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		CommonProfilePage.clickSubmitButton();
		T1WorkspacePage.signOut();
	}

	@And("^_11 user goes to login dot gov to recover password$")
	public void _11_user_goes_to_login_dot_gov_to_recover_password() throws Throwable {
		LaunchBrowserUtil.recoverThroughForgotPasswordForNonFed(newid, secretkey, Constants.PASSWORD_NEW);
	}

	@And("^_11 user creates new password and navigates given links$")
	public void _11_user_creates_new_password_and_navigates_given_links() throws Throwable {

	}

	@Then("^_11 user should be able to login into the workspace$")
	public void _11_user_should_be_able_to_login_into_the_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(newid, Constants.PASSWORD_NEW, secretkey, Constants.USER_NONFED);
	}

	@Given("^_12 user logs in as various users$")
	public void _12_user_logs_in_as_role_admin() throws Throwable {
		/*
		 * SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1,
		 * Constants.USERPASS, ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY,
		 * Constants.USER_FED); CommonMethods.delay(2);
		 */

		/*
		 * SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3,
		 * Constants.USERPASS, ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY,
		 * Constants.USER_FED); CommonMethods.delay(2);
		 */
		
	}

	@And("^_12 user lands on the new t1workspace$")
	public void _12_user_lands_on_the_new_t1workspace() throws Throwable {

		// T1WorkspacePage.clickUserDirectoryLink();
		// T1WorkspacePage.signOut();
		// T1WorkspacePage.clickRoleDefinitionLink();
		// T1WorkspacePage.goToAccountDetailsPage();
		// T1WorkspacePage.goToFeedsPage();
		// T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_12 user should be able to view the widget as per their roles$")
	public void _12_user_should_be_able_to_navigate_the_website_through_working_links() throws Throwable {
		/*// role definition link check
		boolean roleDefinitionLinkFound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.ROLE_DEFINITION_BUTTON);
		Assert.assertEquals(roleDefinitionLinkFound, true);

		// bulk Update link check
		boolean bulkUpdateLinkFound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(bulkUpdateLinkFound, true);
		// pending role request widge link
		boolean pendingRoleRequestLinkFound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK);
		Assert.assertEquals(pendingRoleRequestLinkFound, true);

		// ----------------dra---------------------

		SignInUtility.testSignin(LaunchBrowserUtil.getDriver(), Constants.LOGINGOVCOMP_HOME_PAGE,
				ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS, ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY);
		// role definition link check
		boolean roleDefinitionLinkFoundDra = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ROLE_DEFINITION_BUTTON);
		Assert.assertEquals(roleDefinitionLinkFoundDra, false);

		// bulk Update link check
		boolean bulkUpdateLinkFoundDra = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(bulkUpdateLinkFoundDra, true);
		// pending role request widge link
		boolean pendingRoleRequestLinkFoundDra = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK);
		Assert.assertEquals(pendingRoleRequestLinkFoundDra, true);

		// ---------- assistance admin-----------

		// -----------assistance user----------
		SignInUtility.testSignin(LaunchBrowserUtil.getDriver(), Constants.LOGINGOVCOMP_HOME_PAGE,
				ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS, ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY);
		// role definition link check
		boolean roleDefinitionLinkFoundAssistanceUser = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ROLE_DEFINITION_BUTTON);
		Assert.assertEquals(roleDefinitionLinkFoundAssistanceUser, false);

		// bulk Update link check
		boolean bulkUpdateLinkFoundAssistanceUser = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(bulkUpdateLinkFoundDra, false);

		// pending role request widge link
		boolean pendingRoleRequestLinkFoundAssistanceUser = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK);
		Assert.assertEquals(pendingRoleRequestLinkFoundDra, true);

		// --------------assistance listing granduser-------
*/
	}

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
