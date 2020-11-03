package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RequestRoleOptionalPage;
import gov.gsa.sam.rms.pages.RoleMigrationPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class RoleMigrationStep {
	private static Logger logger = LoggerFactory.getLogger(RoleMigrationStep.class);
	
	String newsignedupuser = "";
	String newsignedupusersecretkey = "";
	String legacyuserid1 = "gsaactest";
	String legacyuseridpassword1 = "testpassword";
	String legacyuserid2= "justin.hamer";
	String legacyuseridpassword2 = "testpassword";
	String legacyuserid3 = "deanna.lucky";
	String legacyuseridpassword3 = "testpassword";
	private String legacyuserid4 = "michaela.garcia@gsa.gov";
	private String legacyuseridpassword4="testpassword";
	private String legacyuserid5 = "amurakaam";
	private String legacyuseridpassword5="testpassword";
	
	
	@Given("^_1rm_ user is registered in login dot gov$")
	public void _1rm_user_is_registered_in_login_dot_gov() throws Throwable {
		RoleMigrationPage.resetTestData(
				Constants.ROLE_MIGRATION_RESET_URL+"/iam/import/domain/fbo/reset/deanna.lucky/");
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		SignUpUtility.signUpNewUser("octotestaccount1+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
	}

	@And("^_1rm_ user signs in$")
	public void _1rm_user_signs_in() throws Throwable {
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();
	}

	@And("^_1rm_ user nagivates to role migrration page$")
	public void _1rm_user_nagivates_to_role_migrration_page() throws Throwable {
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_1rm_ user enters proper legacy domain name and password$")
	public void _1rm_user_enters_proper_domain_name_and_password() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("FBO.gov");
		RoleMigrationPage.enterLegacyUserid(legacyuserid3);
		RoleMigrationPage.enterLegacyUserPassword(legacyuseridpassword1);
		RoleMigrationPage.clickMigrateButton();

	}

	@Then("^_1rm_ user should have their corresponding role migrated$")
	public void _1rm_user_should_have_their_corresponding_role_migrated() throws Throwable {
		
		RoleMigrationPage.clickShowMoreLink();
		
		String alertmessage = RoleMigrationPage.getAlertMessage();
		Assert.assertEquals("Legacy User : deanna.luckyclaimed for Legacy System FBO", alertmessage);
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		
		

		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_ADMIN, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, userAlreadyHasRole);

	}

	@Given("^_2rm_ user is registered in login dot gov$")
	public void _2rm_user_is_registered_in_login_dot_gov() throws Throwable {
		RoleMigrationPage.resetTestData(
				Constants.ROLE_MIGRATION_RESET_URL+"/iam/import/domain/cfda/reset/gsaactest/");

		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		SignUpUtility.signUpNewUser("octotestaccount1+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
		RequestRoleOptionalPage.clickSkipAndFinish();
	}

	@And("^_2rm_ user nagivates to role migrration page$")
	public void _2rm_user_nagivates_to_role_migrration_page() throws Throwable {
		T1WorkspacePage.goToRoleMigrationPage();
		RoleMigrationPage.goToWorkspace();
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_2rm_ user enters proper legacy domain name and password$")
	public void _2rm_user_enters_proper_legacy_domain_name_and_password() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("CFDA.gov");
		RoleMigrationPage.enterLegacyUserid("gsaactest");
		RoleMigrationPage.enterLegacyUserPassword("testpassword");
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_2rm_ user should have their corresponding role migrated$")
	public void _2rm_user_should_have_their_corresponding_role_migrated() throws Throwable {
	RoleMigrationPage.clickShowMoreLink();
		
		String alertmessage = RoleMigrationPage.getAlertMessage();
		Assert.assertEquals("Legacy User : deanna.luckyclaimed for Legacy System CFDA", alertmessage);
		AccountDetailsPage.goToPageOnSideNav("My Roles");
	}

	@When("^_2rm_ user comes back to migrate roles using the same credentials$")
	public void _2rm_user_comes_back_to_migrate_roles_using_the_same_credentials() throws Throwable {
		RoleMigrationPage.goToWorkspace();
		T1WorkspacePage.goToRoleMigrationPage();
		RoleMigrationPage.selectLegacyDomainIfFound("CFDA.gov");
		RoleMigrationPage.enterLegacyUserid("gsaactest");
		RoleMigrationPage.enterLegacyUserPassword("testpassword");
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_2rm_ user should see user already migrated error$")
	public void _2rm_user_should_see_user_already_migrated_error() throws Throwable {
		String alertmessage = RoleMigrationPage.getAlertMessage();
		Assert.assertEquals("Error", alertmessage);
	}

	@Given("^_3rm_ user signs in$")
	public void _3rm_user_signs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CLAIM_NOROLEUSER_1, Constants.USERPASS,
				ConstantsAccounts.CLAIM_NOROLEUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3rm_ user nagivates to role migration page$")
	public void _3rm_user_nagivates_to_role_migration_page() throws Throwable {
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_3rm_ user enters incorrect legacy username only$")
	public void _3rm_user_enters_incorrect_legacy_username_only() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("CFDA.gov");
		RoleMigrationPage.enterLegacyUserid("incorrect");
		RoleMigrationPage.enterLegacyUserPassword("testpassword");
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_3rm_ user should see proper error message$")
	public void _3rm_user_should_see_proper_error_message() throws Throwable {
		LaunchBrowserUtil.delay(3);
		//LaunchBrowserUtil.scrollAllTheWayDown();
		String alertmessage = RoleMigrationPage.getAlertMessage();
		LaunchBrowserUtil.delay(2);
		Assert.assertTrue(alertmessage.contains("User not found"));
		RoleMigrationPage.goToWorkspace();
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_3rm user enters incorrect passowrd$")
	public void _3rm_user_enters_incorrect_passowrd() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("CFDA.gov");
		RoleMigrationPage.enterLegacyUserid("gsaactest");
		RoleMigrationPage.enterLegacyUserPassword("incorrectpassword");
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_3rm_ user should see proper password error message$")
	public void _3rm_user_should_see_proper_password_error_message() throws Throwable {
		LaunchBrowserUtil.delay(3);
		String alertmessage = RoleMigrationPage.getAlertMessage();
		LaunchBrowserUtil.delay(2);
		Assert.assertTrue(alertmessage.contains("User not found"));
		
		RoleMigrationPage.goToWorkspace();
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_3rm_ user enters incorrect domain name$")
	public void _3rm_user_enters_incorrect_domain_name() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("CPARS.gov");
		RoleMigrationPage.enterLegacyUserid("gsaactest");
		RoleMigrationPage.enterLegacyUserPassword("testpassword");
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_3rm_ user should see appropriate error response$")
	public void _3rm_user_should_see_appropriate_error_response() throws Throwable {
		LaunchBrowserUtil.delay(3);
		String alertmessage = RoleMigrationPage.getAlertMessage();
		LaunchBrowserUtil.delay(2);
		Assert.assertTrue(alertmessage.contains("User not found"));
	}

	@Given("^_4rm_ new user signs up$")
	public void _4rm_new_user_signs_up() throws Throwable {
		RoleMigrationPage.resetTestData(
				"https://39rolemanagementcomp.apps.prod-iae.bsp.gsa.gov/iam/import/domain/cfda/reset/gsaactest/");

		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		newsignedupusersecretkey = SignUpUtility.signUpNewUser("shah.raiaan+newregistereduser" + counter + "@gsa.gov",
				Constants.USERPASS);
		newsignedupuser = "shah.raiaan+newregistereduser" + counter + "@gsa.gov";
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
		RequestRoleOptionalPage.clickSkipAndFinish();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_4rm_spaad logs in and assigns assistance listing admin to the new user$")
	public void _4rmspaad_logs_in_and_assigns_assistance_listing_admin_to_the_new_user() throws Throwable {

		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);

		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(newsignedupuser);
		UserDirectoryPage.clickAssignRole(newsignedupuser);

		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_ADMIN, Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();

		// ---------delete the newly granted role-----------
		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_ADMIN, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, userHasRole);
	}

	@And("^_4rm_ user logs back in and navigates to role migration page$")
	public void _4rm_user_logs_back_in_and_navigates_to_role_migration_page() throws Throwable {
		SignInUtility.signIntoWorkspace(newsignedupuser, Constants.USERPASS, newsignedupusersecretkey,
				Constants.USER_FED);
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@And("^_4rm_ user enters domain username and password$")
	public void _4rm_user_enters_domain_username_and_password() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("CFDA.gov");
		RoleMigrationPage.enterLegacyUserid("gsaactest");
		RoleMigrationPage.enterLegacyUserPassword("testpassword");
	}

	@When("^_4rm user tries to migrate the roles$")
	public void _3rm_user_tries_to_migrate_the_roles() throws Throwable {
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_4rm_ the migration should be successful and the role assigned should still reflect previous timeline$")
	public void _3rm_the_migration_should_be_successful_and_the_role_assigned_should_still_reflect_previous_timeline()
			throws Throwable {

	}

	@Given("^_5rm_ nonfed user signs in$")
	public void _5rm_nonfed_user_signs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_5rm_ nonfed user should not see migrate roles link in workspace$")
	public void _5rm_nonfed_user_should_not_see_migrate_roles_link_in_workspace() throws Throwable {
		boolean migraterolelinkFound = T1WorkspacePage.profileLinksFound("Migrate Roles");
		Assert.assertEquals(false, migraterolelinkFound);
	}

	@Given("^_6rm_ user with contracting officer roles signs in$")
	public void _6rm_user_with_contracting_officer_roles_signs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6rm_spaad logs in and assigns assistance listing admin to the new user$")
	public void _6rmspaad_logs_in_and_assigns_assistance_listing_admin_to_the_new_user() throws Throwable {

	}

	@And("^_6rm_ user logs back in and navigates to role migration page$")
	public void _6rm_user_logs_back_in_and_navigates_to_role_migration_page() throws Throwable {

	}

	@And("^_6rm_ user enters domain username and password$")
	public void _6rm_user_enters_domain_username_and_password() throws Throwable {

	}

	@When("^_6rm user tries to migrate the roles$")
	public void _6rm_user_tries_to_migrate_the_roles() throws Throwable {

	}

	@Then("^_6rm_ the migration should be successful and the role assigned should still reflect previous timeline$")
	public void _6rm_the_migration_should_be_successful_and_the_role_assigned_should_still_reflect_previous_timeline()
			throws Throwable {

	}

	@Given("^_7rm_ user signs in$")
	public void _7rm_user_signs_in() throws Throwable {
		RoleMigrationPage.resetTestData(
				Constants.ROLE_MIGRATION_RESET_URL+"/iam/import/domain/cfda/reset/justin.hamer/");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMIN, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMIN_SECRETKEY, Constants.USER_FED);
	}
	@And("^_7rm_ user nagivates to role migration page$")
	public void _7rm_user_nagivates_to_role_migration_page() throws Throwable {
		T1WorkspacePage.goToRoleMigrationPage();	
	}

	@When("^_7rm_ user enters cfda as domain name$")
	public void _7rm_user_enters_cfda_as_domain_name() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("CFDA.gov");
	}

	@And("^_7rm user enters username and password$")
	public void _7rm_user_enters_username_and_password() throws Throwable {
	
		RoleMigrationPage.enterLegacyUserid(legacyuserid2);
		RoleMigrationPage.enterLegacyUserPassword(legacyuseridpassword2);
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_7rm user should get role not supported error$")
	public void _7rm_user_should_get_role_not_supported_error() throws Throwable {
		String alertmessage = RoleMigrationPage.getAlertMessage();
		Assert.assertEquals("Legacy User Not Supported", alertmessage);
		AccountDetailsPage.goToPageOnSideNav("My Roles");

	}

	@Given("^_8rm_ user is registered in login dot gov$")
	public void _8rm_user_is_registered_in_login_dot_gov() throws Throwable {

	}

	@And("^_8rm_ user signs in$")
	public void _8rm_user_signs_in() throws Throwable {

	}

	@And("^_8rm_ user nagivates to role migration page$")
	public void _8rm_user_nagivates_to_role_migration_page() throws Throwable {

	}

	@And("^_8rm_ user enters proper legacy domain name and password$")
	public void _8rm_user_enters_proper_legacy_domain_name_and_password() throws Throwable {

	}

	@When("^_8rm_ user enters incorrect password three times$")
	public void _8rm_user_enters_incorrect_password_three_times() throws Throwable {

	}

	@Then("^_8rm user should get proper error message$")
	public void _8rm_user_should_get_proper_error_message() throws Throwable {

	}
	
	@Given("^_9rm_ user is registered in login dot gov$")
	public void _9rm_user_is_registered_in_login_dot_gov() throws Throwable {
		RoleMigrationPage.resetTestData(
				Constants.ROLE_MIGRATION_RESET_URL+"/iam/import/domain/fbo/reset/amurakaam/");
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		SignUpUtility.signUpNewUser("shah.raiaan+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
	}

	@And("^_9rm_ user signs in$")
	public void _9rm_user_signs_in() throws Throwable {
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();
	}

	@And("^_9rm_ user nagivates to role migrration page$")
	public void _9rm_user_nagivates_to_role_migrration_page() throws Throwable {
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_9rm_ user enters proper legacy domain name and password$")
	public void _9rm_user_enters_proper_domain_name_and_password() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("FBO.gov");
		RoleMigrationPage.enterLegacyUserid(legacyuserid5);
		RoleMigrationPage.enterLegacyUserPassword(legacyuseridpassword5);
		RoleMigrationPage.clickMigrateButton();
	}

	@Then("^_9rm_ user should have their corresponding role migrated$")
	public void _9rm_user_should_have_their_corresponding_role_migrated() throws Throwable {
		String alertmessage = RoleMigrationPage.getAlertMessage();
		Assert.assertEquals("Legacy User : amurakaamclaimed for Legacy System FBO ... Show more", alertmessage);
		LaunchBrowserUtil.delay(2);
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_PUBLIC_BUILDING_SERVICE,
			Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
			Assert.assertEquals(true, roleFound);
	}
	
	
	@Given("^_10rm_ user is registered in login dot gov$")
	public void _10rm_user_is_registered_in_login_dot_gov() throws Throwable {
		RoleMigrationPage.resetTestData(
				Constants.ROLE_MIGRATION_RESET_URL+"/iam/import/domain/fbo/reset/michaela.garcia@gsa.gov/");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMIN, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMIN_SECRETKEY, Constants.USER_FED);
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		SignUpUtility.signUpNewUser("shah.raiaan+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
	}

	@And("^_10rm_ user signs in$")
	public void _10rm_user_signs_in() throws Throwable {
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();
	}

	@And("^_10rm_ user nagivates to role migrration page$")
	public void _10rm_user_nagivates_to_role_migrration_page() throws Throwable {
		T1WorkspacePage.goToRoleMigrationPage();
	}

	@When("^_10rm_ user enters proper legacy domain name and password$")
	public void _10rm_user_enters_proper_domain_name_and_password() throws Throwable {
		RoleMigrationPage.selectLegacyDomainIfFound("FBO.gov");
		RoleMigrationPage.enterLegacyUserid(legacyuserid4);
		RoleMigrationPage.enterLegacyUserPassword(legacyuseridpassword4);
		RoleMigrationPage.clickMigrateButton();

	}

	@Then("^_10rm_ user should have their corresponding role migrated$")
	public void _10rm_user_should_have_their_corresponding_role_migrated() throws Throwable {
		String alertmessage = RoleMigrationPage.getAlertMessage();
		Assert.assertEquals("Legacy User : michaela.garcia@gsa.govclaimed for Legacy System FBO ... Show more", alertmessage);
		LaunchBrowserUtil.delay(2);
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_PUBLIC_BUILDING_SERVICE,
			Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
			Assert.assertEquals(true, roleFound);

	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
