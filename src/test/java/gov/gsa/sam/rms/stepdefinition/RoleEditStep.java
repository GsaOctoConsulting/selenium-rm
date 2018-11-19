package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import org.junit.Assert;

public class RoleEditStep {

	private static Logger logger = LoggerFactory.getLogger(RoleEditStep.class);

	@Given("^_1 user logs in workspace as assistance admin$")
	public void _1_user_logs_in_workspace_as_assistance_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+assistanceadminv2@gsa.gov", Constants.userPass);
	}

	@And("^_1 user looks up assistance user account in UserDirectory$")
	public void _1_user_looks_up_assistance_user_account_in_userdirectory() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		String assistanceUserId = "shah.raiaan+assistanceuserv2@gsa.gov";
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(assistanceUserId);
		UserDirectoryPage.clickViewAccess(assistanceUserId);

		// check whether user already has the role
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);

	}

	@Then("^_1 user should be able to edit their roles$")
	public void _1_user_should_be_able_to_edit_their_roles() throws Throwable {
		// edit the role
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.writeComment("adding organization");
		AssignRolePage.clickDone();
		// ------------------edit the role back to previous state---------------

		// change the role back
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(
				Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
		CommonMethods.delay(3);
		// confirming the change has gone through
		boolean roleRestored = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleRestored);
		afterScenario();
		CommonMethods.delay(2);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2 user logs in workspace as dra$")
	public void _2_user_logs_in_workspace_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+deptAdminSelenium@gsa.gov", Constants.userPass);
		
	}

	@And("^_2 dra looks up contracting officer in contract data$")
	public void _2_dra_looks_up_contracting_officer_in_contract_data() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		RMWidgetUtility.clickUserDirectoryLink();
		String searchUser = "shah.raiaan+coSelenium@gsa.gov";
		UserDirectoryPage.searchUserInUserPicker(searchUser);
		UserDirectoryPage.clickViewAccess(searchUser);
		// check whether user already has the role

		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, "EDIT");
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Then("^_2 dra changes users role to contracting speacialist$")
	public void _2_dra_changes_users_role_to_contracting_speacialist() throws Throwable {
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("changing roles");
		AssignRolePage.clickDone();

		// check to ensure page is back on RolesDirectoryViewAccessPage
		boolean assignButtonFound = RolesDirectoryViewAccessPage
				.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);
		Assert.assertEquals(assignButtonFound, true);

		// check to ensure the changed role has gone through
		boolean roleHasChanged = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, "NO ACTION");
		Assert.assertEquals(roleHasChanged, true);

		// ------------------edit the role back---------------
		CommonMethods.delay(3);
		// change the role back
		RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, "EDIT");
	
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("reverting back");
		CommonMethods.delay(2);
		AssignRolePage.clickDone();

		
	}
	@Then("^_2 dra sees the role change showing up in my profile page$")
    public void _2_dra_sees_the_role_change_showing_up_in_my_profile_page() throws Throwable {

		// check to ensure the changed role has gone through
		boolean previousRoleRestored=RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_DATA, "NO ACTION");
		Assert.assertEquals(previousRoleRestored, true);
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
