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
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_VIEWER_ENTITYREGISRATION);
	}

	@Then("^_1nri admin should receive proper message and be able to assign role to user$")
	public void _1nri_admin_should_receive_proper_message_and_be_able_to_assign_role_to_user() throws Throwable {
		RoleInviteAssignRolePage.clickExistingUserAcceptButton();
		RoleInviteAssignRolePage.selectDomain(Constants.DOMAIN_ENTITY_REGISTRATION);
	}

}
