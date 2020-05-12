package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.RoleInviteAssignRolePage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import junit.framework.Assert;

public class NonfedRoleInviteStep {
	private static Logger logger = LoggerFactory.getLogger(NonfedRoleInviteStep.class);

	@Given("^_1nri nonfed admin logs in$")
	public void _1nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_1nri goes to the role invite page through user directory$")
	public void _1nri_goes_to_the_role_invite_page_through_user_directory() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();

	}

	@When("^_1nri admin enters an id for a user with roles in the admins own domain$")
	public void _1nri_admin_enters_an_id_for_a_user_with_roles_in_the_admins_own_domain() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1);
	}

	@Then("^_1nri admin should receive proper message and be able to assign role to user$")
	public void _1nri_admin_should_receive_proper_message_and_be_able_to_assign_role_to_user() throws Throwable {
		RoleInviteAssignRolePage.clickExistingUserAcceptButton();
		RoleInviteAssignRolePage.selectDomain(Constants.DOMAIN_ENTITY_REGISTRATION);
	}

	@Given("^_2nri nonfed admin logs in$")
	public void _2nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_2nri goes to the role invite page through user directory$")
	public void _2nri_goes_to_the_role_invite_page_through_user_directory() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_2nri admin enters an id for a user with no roles in the admins own domain$")
	public void _2nri_admin_enters_an_id_for_a_user_with_no_roles_in_the_admins_own_domain() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
	}

	@Then("^_2nri admin should not receive any dialog box and proceed to invite the user$")
	public void _2nri_admin_should_not_receive_any_dialog_box_and_proceed_to_invite_the_user() throws Throwable {
		RoleInviteAssignRolePage.selectDomain(Constants.DOMAIN_ENTITY_REGISTRATION);
	}

	@Given("^_3nri spaad logs in$")
	public void _3nri_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_3nri spaad goes to the role invite page through user directory$")
	public void _3nri_spaad_goes_to_the_role_invite_page_through_user_directory() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_3nri spaad enters a federal id in the user email box$")
	public void _3nri_spaad_enters_a_federal_id_in_the_user_email_box() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.ROLE_ADMIN_USER_3);
	}

	@Then("^_3nri spaad should see error message asking entry of nonfederal email id only$")
	public void _3nri_spaad_should_see_error_message_asking_entry_of_nonfederal_email_id_only() throws Throwable {
		String errormessage = RoleInviteAssignRolePage.getUserEmailErrorMessage(); 
		Assert.assertEquals("Please enter a non-federal email", errormessage);
	}

}