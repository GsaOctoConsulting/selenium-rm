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
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;

public class RoleAndRoleHistoryStep {

	private static Logger logger = LoggerFactory.getLogger(RoleAndRoleHistoryStep.class);

	@Given("^_1rh user logs in workspace with dra role$")
	public void _1rh_user_logs_in_workspace_with_dra_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1rh dra navigates to user directory and looks up a user with contracting specialist role$")
	public void _1rh_dra_navigates_to_user_directory_and_looks_up_a_user_with_contracting_specialist_role()
			throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1);

	}

	@When("^_1rh dra removes the role for the user$")
	public void _1rh_dra_removes_the_role_for_the_user() throws Throwable {
		// ---------delete the role-----------
		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, Constants.DELETE);
		Assert.assertEquals(userAlreadyHasRole, true);
	}

	@Then("^_1rh the role history should update in the profile for dra$")
	public void _1rh_the_role_history_should_update_in_the_profile_for_dra() throws Throwable {
		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan",
				Constants.ROLEHISTORY_STATUS_ROLE_REMOVED, Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR,
				Constants.ORG_GSA, Constants.NOACTION);
		Assert.assertEquals(true, roleHistoryFound);

		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_1rh the role history should update in the profile for the user$")
	public void _1rh_the_role_history_should_update_in_the_profile_for_the_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");

		boolean userAlreadyHasRole = MyRolesPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, Constants.NOACTION);
		Assert.assertEquals(false, userAlreadyHasRole);

		// --------------------------
		boolean roleHistoryFound = UserDirectoryViewAccessPage.latestRoleHistoryFound("shah raiaan",
				Constants.ROLEHISTORY_STATUS_ROLE_REMOVED, Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR,
				Constants.ORG_GSA, Constants.NOACTION);
		Assert.assertEquals(true, roleHistoryFound);

		LaunchBrowserUtil.closeBrowsers();

		// ------------assigning the role back---------------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1);
		
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 1);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		LaunchBrowserUtil.delay(5);

	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
