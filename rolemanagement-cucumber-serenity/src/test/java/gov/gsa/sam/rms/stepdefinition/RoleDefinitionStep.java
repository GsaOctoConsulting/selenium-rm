package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.locators.CreateNewRolePageLocator;
import gov.gsa.sam.rms.locators.RoleDefinitionPageLocator;
import gov.gsa.sam.rms.locators.RolePermissionPageLocator;
import gov.gsa.sam.rms.pages.CreateNewRolePage;
import gov.gsa.sam.rms.pages.RoleDefinitionPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;

public class RoleDefinitionStep {
	private static Logger logger = LoggerFactory.getLogger(BulkUpdateStep.class);

	@Given("^_1 user logs in a role admin$")
	public void _1_user_logs_in_a_role_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user navigates to role definition page through widget$")
	public void _1_user_navigates_to_role_definition_page_through_widget() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickRoleDefinitionsLink();
	}

	@Then("^_1 user shoudl see proper links and buttons$")
	public void _1_user_shoudl_see_proper_links_and_buttons() throws Throwable {
		// check create newrole page...and submit and cancel button
		RoleDefinitionPage.clickCreateNewRole();
		LaunchBrowserUtil.scrollAllTheWayDown();

		boolean submitButtonFound = CreateNewRolePage.elementFound(CreateNewRolePageLocator.SUBMIT_BUTTON);
		Assert.assertEquals(submitButtonFound, true);
		CreateNewRolePage.clickCancel();

		// check role definition and object definition filter
		String pageTitle = RoleDefinitionPage.get2ndHeaderTitle();
		Assert.assertEquals(pageTitle, "Role Definitions");

		RoleDefinitionPage.clickObjectDefinitions();
		String pageTitle2 = RoleDefinitionPage.get2ndHeaderTitle();
		Assert.assertEquals(pageTitle2, "Object Definitions");

		afterScenario();
	}

	@Given("^_2rd user logs in a spaad$")
	public void _2rd_user_logs_in_a_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2rd user navigates to role definition page and filters all roles in contract data domain$")
	public void _2rd_user_navigates_to_role_definition_page_and_filters_all_roles_in_contract_data_domain()
			throws Throwable {
		T1WorkspacePage.clickRoleDefinitionLink();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTDATA);

	}

	@When("^_2rd user goes through all permission for aad role$")
	public void _2rd_user_goes_through_all_permission_for_aad_role() throws Throwable {
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_2rd all the expected checkbox should be marked for aad role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_aad_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ----------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// --------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.AWARDIDV_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.USER_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLEUNSELECTED).isSelected());
	}

	@When("^_2rd user goes through all permssion for sampmo admin role$")
	public void _2rd_user_goes_through_all_permssion_for_sampmo_admin_role() throws Throwable {
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTDATA);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_AL_GRANDUSER_SAMPMOADMINISTRATOR,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_2rd all the expected checkbox should be marked for sampmo role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_sampmo_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ----------------------------------

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------------------------

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// --------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.AWARDIDV_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.USER_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLEUNSELECTED).isSelected());
	}

	@When("^_2rd user goes through all permission for administrator role$")
	public void _2rd_user_goes_through_all_permission_for_administrator_role() throws Throwable {
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTDATA);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_CONTRACTDATA_ADMIN,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_2rd all the expeted checkbox should be marked for administrator role$")
	public void _2rd_all_the_expeted_checkbox_should_be_marked_for_administrator_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ----------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// --------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.AWARDIDV_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.USER_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLEUNSELECTED).isSelected());
	}

	@When("^_2rd user goes through all permission for contracting officer role$")
	public void _2rd_user_goes_through_all_permission_for_contracting_officer_role() throws Throwable {
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTDATA);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_2rd all the expected checkbox should be marked for contracting officer role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_contracting_officer_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ----------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// --------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.AWARDIDV_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.USER_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLEUNSELECTED).isSelected());
	}

	@When("^_2rd user goes through all the permission for contracting specialist role$")
	public void _2rd_user_goes_through_all_the_permission_for_contracting_specialist_role() throws Throwable {
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTDATA);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_2rd all the expected checkbox should be marked for contracting specialist role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_contracting_specialist_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ----------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.OTHERTRANSCATIONAWARED_CORRECTUPDATE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_TRANSFER_ASSOCIABLEUNSELECTED)
						.isSelected());

		// --------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_APPROVE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// --------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.AWARDIDV_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_DELETEDRAFT_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AWARDIDV_CREATEVALIDATEISCOMPLETEMODIFY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_CORRECTUPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_TRANSFER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AWARDIDV_APPROVE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.REPORTS_ACCESSADMINISTRATIVEREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.USER_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USER_ASSIGNREMOVEPRIVILEGES_ASSOCIABLEUNSELECTED).isSelected());
	}

	@Given("^_3rd user logs in a spaad$")
	public void _3rd_user_logs_in_a_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3rd user navigates to role definition page and filters all roles in contract opportunities domain$")
	public void _3rd_user_navigates_to_role_definition_page_and_filters_all_roles_in_contract_opportunities_domain()
			throws Throwable {
		T1WorkspacePage.clickRoleDefinitionLink();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
	}

	@When("^_3rd user goes through all permission for aad role$")
	public void _3rd_user_goes_through_all_permission_for_aad_role() throws Throwable {
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for aad role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_aad_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------

	}

	@When("^_3rd user goes through all permssion for sampmo admin role$")
	public void _3rd_user_goes_through_all_permssion_for_sampmo_admin_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_AL_GRANDUSER_SAMPMOADMINISTRATOR,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for sampmo role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_sampmo_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------

	}

	@When("^_3rd user goes through all permission for administrator role$")
	public void _3rd_user_goes_through_all_permission_for_administrator_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_ADMIN, Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expeted checkbox should be marked for administrator role$")
	public void _3rd_all_the_expeted_checkbox_should_be_marked_for_administrator_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
	}

	@When("^_3rd user goes through all permission for contracting officer role$")
	public void _3rd_user_goes_through_all_permission_for_contracting_officer_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for contracting officer role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_contracting_officer_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
	}

	@When("^_3rd user goes through all the permission for opportunities admin role$")
	public void _3rd_user_goes_through_all_the_permission_for_opportunities_admin_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_OPPADMIN, Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for opportunities admin role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_opportunities_admin_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
	}

	@When("^_3rd user goes through all the permission for contracting specialist role$")
	public void _3rd_user_goes_through_all_the_permission_for_contracting_specialist_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR,
				Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for contracting specialist role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_contracting_specialist_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
	}

	@When("^_3rd user goes throgh all the permission for data entry role$")
	public void _3rd_user_goes_throgh_all_the_permission_for_data_entry_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_DATA_ENTRY, Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for data entry role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_data_entry_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
	}

	@When("^_3rd user goes through all the permission for viewer role$")
	public void _3rd_user_goes_through_all_the_permission_for_viewer_role() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.navigateBack();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_CONTRACTOPPORTUNITIES);
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_VIEWER, Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_3rd all the expected checkbox should be marked for viewer role$")
	public void _3rd_all_the_expected_checkbox_should_be_marked_for_viewer_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ----------------new permission------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.BIDS_SUBMIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.BIDS_SUBMIT_ASSOCIABLEUNSELECTED).isSelected());

		// ----------------new permission--------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.NOTICES_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHDRAFTNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PUBLISHDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_CANCELPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_ARCHIVEPUBLISHEDNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_UNARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_VIEWPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_SEARCHPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_PRINTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_DOWNLOADPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_SEARCHARCHIVENOTICES_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NOTICES_EXPORTPUBLISHEDNOTICES_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_EDITAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_CREATEAWARDNOTICE_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEDRAFTNOTICE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETENOTICEVERSIONS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.NOTICES_DELETEBASEANDALLVERSIONSOFNOTICE_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ALL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWDOCUMENTAUDITTRAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_SEARCHREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_CREATEREPORT_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_DOWNLOADDATAFILE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_VIEWSYSTEMSTATS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSPUBLICREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSFEDERALREPORTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.CONTRACTOPPORTUNITIESDATABANK_ACCESSADMINREPORTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ------------NEW PERMISSION---------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_UNRELEASE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_CREATE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETE_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_EDITDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_DELETEDRAFT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.NONFBOSOLICITATIONS_SEARCH_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_CREATEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_EDITDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_RELEASEDRAFTDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_ASSIGNDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_DELETEDOCPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHPUBLISHEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.DOCUMENTPACKAGE_SEARCHARCHIVEDPACKAGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.DOCUMENTPACKAGE_REQUESTTOVIEWSECUREOPPORTUNITYPACKAGES_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHPUBLISHEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ATTACHMENTS_SEARCHARCHIVEDATTACHMENTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTSTHROUGHAUTHORIZEDPARTIESLIST_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_CREATEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_RELEASEDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_ASSIGNATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_DELETEATTACHMENT_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ATTACHMENTS_REQUESTTOVIEWATTACHMENTTHROUGHJOINTCERTIFICATIONCONTROLLED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ATTACHMENTS_EDITDRAFTATTACHMENT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.VENDORACCESSCONTROL_SEARCHAPPROVEREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.VENDORACCESSCONTROL_APPROVEORREJECTEPENDINGREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_ADDAUTHORIZEDPARTY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.VENDORACCESSCONTROL_EDITREJECTEDREQUESTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWINTERESTEDVENDORS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLNL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.INTERESTEDVENDORLIST_ENABLEDISABLEVVIVLOL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_ADDENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.INTERESTEDVENDORLIST_VIEWLIST_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -------------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWELECTRONICACCOUNTS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_APPROVEANACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DISABLEACCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_DELETEANCCOUNT_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITACCOUNTTAB_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_VIEWUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EDITUSERROLES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.USERACCOUNTMANAGEMENT_EMAILUSERS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION------------------
	}

	@Given("^_4rd user logs in a spaad$")
	public void _4rd_user_logs_in_a_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4rd user navigates to role definition page and filters all roles in assistance listing domain$")
	public void _4rd_user_navigates_to_role_definition_page_and_filters_all_roles_in_assistance_listing_domain()
			throws Throwable {
		T1WorkspacePage.clickRoleDefinitionLink();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_ASSISTANCELISTING);
	}

	@When("^_4rd user goes through all permission for administrator role$")
	public void _4rd_user_goes_through_all_permission_for_administrator_role() throws Throwable {
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_ASSISTANCE_ADMIN, Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_4rd all the expected checkbox should be marked for administrator role$")
	public void _4rd_all_the_expected_checkbox_should_be_marked_for_administrator_role() throws Throwable {
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_INITIATEARCHIVECHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_INITIATEARCHIVECHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_INITIATEARCHIVECHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_APPROVEARCHIVECHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSPUBLISHED_APPROVEARCHIVECHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_APPROVEARCHIVECHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_INITIATETITLECHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSPUBLISHED_INITIATETITLECHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_INITIATETITLECHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_APPROVEREJECTTITLECHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_APPROVEREJECTTITLECHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.FALSPUBLISHED_APPROVEREJECTTITLECHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_NUMBERCHANGEREQUESTINITIATE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_NUMBERCHANGEREQUESTINITIATE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.FALSPUBLISHED_NUMBERCHANGEREQUESTINITIATE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_NUMBERCHANGEREQUESTAPPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_NUMBERCHANGEREQUESTAPPROVE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_NUMBERCHANGEREQUESTAPPROVE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_INITIATEAGENCYCHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSPUBLISHED_INITIATEAGENCYCHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_INITIATEAGENCYCHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSPUBLISHED_APPROVEAGENCYCHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSPUBLISHED_APPROVEAGENCYCHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSPUBLISHED_APPROVEAGENCYCHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION------------------

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_INITIATEUNARCHIVECHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSARCHIVED_INITIATEUNARCHIVECHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSARCHIVED_INITIATEUNARCHIVECHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_APPROVEUNARCHIVECHANGEREQ_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSARCHIVED_APPROVEUNARCHIVECHANGEREQ_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSARCHIVED_APPROVEUNARCHIVECHANGEREQ_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_VIEWARCHIVERECORDS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSARCHIVED_VIEWARCHIVERECORDS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSARCHIVED_VIEWARCHIVERECORDS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSARCHIVED_VIEWSEARCHRESULTSSUMMARY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSARCHIVED_VIEWSEARCHRESULTSSUMMARY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.FALSARCHIVED_VIEWSEARCHRESULTSSUMMARY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------NEW PERMISSION------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_CREATEFALS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_CREATEFALS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_CREATEFALS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_VIEWNONPUBLICINFO_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_VIEWNONPUBLICINFO_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_VIEWNONPUBLICINFO_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_EDITFALS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_EDITFALS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_EDITFALS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_DELETEDRAFTFALS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_DELETEDRAFTFALS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_DELETEDRAFTFALS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SEARCHPENDINGFALS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SEARCHPENDINGFALS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SEARCHPENDINGFALS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REVIEWPROGRAMS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REVIEWPROGRAMS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REVIEWPROGRAMS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_APPROVEFALS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_APPROVEFALS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_APPROVEFALS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_EXTENDREVIEWPERIOD_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_EXTENDREVIEWPERIOD_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_EXTENDREVIEWPERIOD_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_APPROVELISTING_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_APPROVELISTING_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_APPROVELISTING_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REJECTLISTING_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REJECTLISTING_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REJECTLISTING_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_RETRACTPROGRAMS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_RETRACTPROGRAMS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_RETRACTPROGRAMS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REJECTFALS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REJECTFALS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_REJECTFALS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SUBMITLISTING_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SUBMITLISTING_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SUBMITLISTING_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SUBMITTOOMB_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SUBMITTOOMB_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FALSSUBMISSIONS_SUBMITTOOMB_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION--------------------------

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.HISTORICALINDEX_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.HISTORICALINDEX_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.HISTORICALINDEX_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.HISTORICALINDEX_VIEWHISTORICALINDEXENTRIES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.HISTORICALINDEX_VIEWHISTORICALINDEXENTRIES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.HISTORICALINDEX_VIEWHISTORICALINDEXENTRIES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.HISTORICALINDEX_EDITHISTORICALINDEXENTRIES_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.HISTORICALINDEX_EDITHISTORICALINDEXENTRIES_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.HISTORICALINDEX_EDITHISTORICALINDEXENTRIES_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION--------------------------

		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.EXTERNALSYSTEMDELEGATION_FRONTENDDATAENTRYISALLOWED_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------NEW PERMISSION------------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_CREATEREGIONAGENCYOFFS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.AGENCYADMINISTRATION_CREATEREGIONAGENCYOFFS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.AGENCYADMINISTRATION_CREATEREGIONAGENCYOFFS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_VIEWEREGIONAGENCYOFFS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AGENCYADMINISTRATION_VIEWEREGIONAGENCYOFFS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.AGENCYADMINISTRATION_VIEWEREGIONAGENCYOFFS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_EDITREGIONAGENCYOFFS_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AGENCYADMINISTRATION_EDITREGIONAGENCYOFFS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.AGENCYADMINISTRATION_EDITREGIONAGENCYOFFS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_MANAGECFDANUMBER_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_MANAGECFDANUMBER_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AGENCYADMINISTRATION_MANAGECFDANUMBER_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_VIEWAGENCYCHANGE_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.AGENCYADMINISTRATION_VIEWAGENCYCHANGE_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.AGENCYADMINISTRATION_VIEWAGENCYCHANGE_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------NEW PERMISSION--------------------------

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_CREATEEDITUSERPERMISSIONS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ACCOUNTMANAGEMENT_CREATEEDITUSERPERMISSIONS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ACCOUNTMANAGEMENT_CREATEEDITUSERPERMISSIONS_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_VIEW_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_VIEW_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ACCOUNTMANAGEMENT_VIEW_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION--------------------------

		// -----------------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------NEW PERMISSION--------------------------
	}

	@When("^_4rd user goes through all permssion for assistance user role$")
	public void _4rd_user_goes_through_all_permssion_for_assistance_user_role() throws Throwable {

	}

	@Then("^_4rd all the expected checkbox should be marked for assistance user role$")
	public void _4rd_all_the_expected_checkbox_should_be_marked_for_assistance_user_role() throws Throwable {

	}

	@When("^_4rd user goes through all permission for omb analyst role$")
	public void _4rd_user_goes_through_all_permission_for_omb_analyst_role() throws Throwable {

	}

	@Then("^_4rd all the expeted checkbox should be marked for omb analyst role$")
	public void _4rd_all_the_expeted_checkbox_should_be_marked_for_omb_analyst_role() throws Throwable {

	}

	@When("^_4rd user goes through all permission for aad role$")
	public void _4rd_user_goes_through_all_permission_for_aad_role() throws Throwable {

	}

	@Then("^_4rd all the expected checkbox should be marked for aad role$")
	public void _4rd_all_the_expected_checkbox_should_be_marked_for_aad_role() throws Throwable {

	}

	@When("^_4rd user goes through all the permission for sampmo admin role$")
	public void _4rd_user_goes_through_all_the_permission_for_sampmo_admin_role() throws Throwable {

	}

	@Then("^_4rd all the expected checkbox should be marked for sampmo admin role$")
	public void _4rd_all_the_expected_checkbox_should_be_marked_for_sampmo_admin_role() throws Throwable {

	}

	@When("^_4rd user goes through all the permission for omb administrator role$")
	public void _4rd_user_goes_through_all_the_permission_for_omb_administrator_role() throws Throwable {

	}

	@Then("^_4rd all the expected checkbox should be marked for omb administrator role$")
	public void _4rd_all_the_expected_checkbox_should_be_marked_for_omb_administrator_role() throws Throwable {

	}

	@Given("^_5rd user logs in a spaad$")
	public void _5rd_user_logs_in_a_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5rd user navigates to role definition page and filters all roles in admin domain$")
	public void _5rd_user_navigates_to_role_definition_page_and_filters_all_roles_in_admin_domain() throws Throwable {
		T1WorkspacePage.clickRoleDefinitionLink();
		RoleDefinitionPage.clickDomainFilter(RoleDefinitionPageLocator.DOMAIN_FILTER_ADMIN);
	}

	@When("^_5rd user goes through all permission for iaepmo administrator role$")
	public void _5rd_user_goes_through_all_permission_for_iaepmo_administrator_role() throws Throwable {
		RoleDefinitionPage.getRoleDefinitionDetails(Constants.ROLE_IAM_PMO_ADMIN, Constants.GO_INTO_EDITPERMISSIONS);
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_5rd all the expected checkbox should be marked iaepmo administrator role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_iaepmo_administrator_role() throws Throwable {
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_REGISTERENTITY_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_REGISTERENTITY_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_REGISTERENTITY_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_UPDATEDRAFTWORKINPROGRESSREGISTRATION_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_UPDATEDRAFTWORKINPROGRESSREGISTRATION_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_UPDATEDRAFTWORKINPROGRESSREGISTRATION_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_VIEWDRAFTWORKINPROGRESSSUBMITTEDREGISTRATION_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_VIEWDRAFTWORKINPROGRESSSUBMITTEDREGISTRATION_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_VIEWDRAFTWORKINPROGRESSSUBMITTEDREGISTRATION_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_DELETEDRAFTWORKINPROGRESSREGISTRATION_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_DELETEDRAFTWORKINPROGRESSREGISTRATION_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_DELETEDRAFTWORKINPROGRESSREGISTRATION_ASSOCIABLEUNSELECTED)
				.isSelected());
		// -----------------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ENTITYREGISTRATION_UPDATEACTIVEEXPIREDREGISTRATION_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_UPDATEACTIVEEXPIREDREGISTRATION_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_UPDATEACTIVEEXPIREDREGISTRATION_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ENTITYREGISTRATION_VIEWACTIVEEXPIREDREGISTRATIONS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_VIEWACTIVEEXPIREDREGISTRATIONS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_VIEWACTIVEEXPIREDREGISTRATIONS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_DEACTIVATEACTIVEEXPIREDREGISTRATIONS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_DEACTIVATEACTIVEEXPIREDREGISTRATIONS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_DEACTIVATEACTIVEEXPIREDREGISTRATIONS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_VIEWDEACTIVATEDREGISTRATIONS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_VIEWDEACTIVATEDREGISTRATIONS_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_VIEWDEACTIVATEDREGISTRATIONS_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_VIEWPENDINGHIERARCHYREGISTRATIONSREQUESTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_VIEWPENDINGHIERARCHYREGISTRATIONSREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_VIEWPENDINGHIERARCHYREGISTRATIONSREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_APPROVEPENDINGHIERARCHYREGISTRATIONSREQUESTS_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_APPROVEPENDINGHIERARCHYREGISTRATIONSREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_APPROVEPENDINGHIERARCHYREGISTRATIONSREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_REJECTPENDINGHIERARCHYREGISTRATIONSREQUESTS_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_REJECTPENDINGHIERARCHYREGISTRATIONSREQUESTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.ENTITYREGISTRATION_REJECTPENDINGHIERARCHYREGISTRATIONSREQUESTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.ENTITYREGISTRATION_NOTARIZEDLETTERAPPROVAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.ENTITYREGISTRATION_NOTARIZEDLETTERAPPROVAL_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.ENTITYREGISTRATION_NOTARIZEDLETTERAPPROVAL_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------NEW PERMISSION------------------------

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYEXECUTIVECOMPENSATIONDATA_DEFAULT)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYEXECUTIVECOMPENSATIONDATA_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYEXECUTIVECOMPENSATIONDATA_ASSOCIABLEUNSELECTED)
				.isSelected());

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYFOUODATA_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYFOUODATA_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYFOUODATA_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYSENSITIVEDATA_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYSENSITIVEDATA_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYSENSITIVEDATA_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYSYSTEMONLYDATA_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYSYSTEMONLYDATA_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYSYSTEMONLYDATA_ASSOCIABLEUNSELECTED)
						.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYPROCEEDINGSDATA_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYPROCEEDINGSDATA_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SITEWIDESEARCH_VIEWENTITYPROCEEDINGSDATA_ASSOCIABLEUNSELECTED)
						.isSelected());
		// -----------------NEW PERMISSION------------------------

		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMADMINISTRATION_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMADMINISTRATION_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMADMINISTRATION_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMADMINISTRATION_MANAGEAGENCYPOC_DEFAULT).isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.SYSTEMADMINISTRATION_MANAGEAGENCYPOC_ASSOCIABLESELECTED)
						.isSelected());

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SYSTEMADMINISTRATION_MANAGEAGENCYPOC_ASSOCIABLEUNSELECTED)
						.isSelected());

		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.SYSTEMADMINISTRATION_VIEWDATAACCESSREQUESTSSYSTEMACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_VIEWDATAACCESSREQUESTSSYSTEMACCOUNTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_VIEWDATAACCESSREQUESTSSYSTEMACCOUNTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver()
						.findElement(
								RolePermissionPageLocator.SYSTEMADMINISTRATION_VIEWDATAACCESSREQUESTSINDIVIDUAL_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_VIEWDATAACCESSREQUESTSINDIVIDUAL_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_VIEWDATAACCESSREQUESTSINDIVIDUAL_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.SYSTEMADMINISTRATION_APPROVEDATAACCESSREQUESTSSYSTEMACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_APPROVEDATAACCESSREQUESTSSYSTEMACCOUNTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_APPROVEDATAACCESSREQUESTSSYSTEMACCOUNTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(
						RolePermissionPageLocator.SYSTEMADMINISTRATION_REJECTDATAACCESSREQUESTSSYSTEMACCOUNTS_DEFAULT)
						.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_REJECTDATAACCESSREQUESTSSYSTEMACCOUNTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_REJECTDATAACCESSREQUESTSSYSTEMACCOUNTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_APPROVEDATAACCESSREQUESTSINDIVIDUALACCOUNTS_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_APPROVEDATAACCESSREQUESTSINDIVIDUALACCOUNTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_APPROVEDATAACCESSREQUESTSINDIVIDUALACCOUNTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_REJECTDATAACCESSREQUESTSINDIVIDUALACCOUNTS_DEFAULT)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_REJECTDATAACCESSREQUESTSINDIVIDUALACCOUNTS_ASSOCIABLESELECTED)
				.isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver().findElement(
				RolePermissionPageLocator.SYSTEMADMINISTRATION_REJECTDATAACCESSREQUESTSINDIVIDUALACCOUNTS_ASSOCIABLEUNSELECTED)
				.isSelected());
		// ---------------NEW PERMISSION-----------------

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.REPORTS_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ALL_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_REPORTUSER_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_ADHOCREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.REPORTS_SCHEDULEREPORTS_ASSOCIABLEUNSELECTED).isSelected());
		// --------------NEW PERMISSION---------------------

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_VIEW_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_VIEW_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_VIEW_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_CREATE_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_EDIT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_EDIT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_EDIT_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_APPROVE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_APPROVE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_APPROVE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------------------------
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_REJECT_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_REJECT_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.SYSTEMACCOUNT_REJECT_ASSOCIABLEUNSELECTED).isSelected());
		// --------------NEW PERMISSION---------------------

		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.FSD_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.FSD_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_CREATE_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.FSD_READ_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_READ_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_READ_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.FSD_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.FSD_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
		// -----------------NEW PERMISSION------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.IAM_ALL_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_ALL_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_ALL_ASSOCIABLEUNSELECTED).isSelected());
		// ---------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.IAM_CREATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_CREATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_CREATE_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
				Assert.assertEquals(false,
						LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.IAM_DELETE_DEFAULT).isSelected());

				Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.IAM_DELETE_ASSOCIABLESELECTED).isSelected());

				Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
						.findElement(RolePermissionPageLocator.IAM_DELETE_ASSOCIABLEUNSELECTED).isSelected());

				// -----------------------------------
		
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.IAM_READ_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_READ_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_READ_ASSOCIABLEUNSELECTED).isSelected());

		// -----------------------------------
		Assert.assertEquals(false,
				LaunchBrowserUtil.getDriver().findElement(RolePermissionPageLocator.IAM_UPDATE_DEFAULT).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_UPDATE_ASSOCIABLESELECTED).isSelected());

		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.IAM_UPDATE_ASSOCIABLEUNSELECTED).isSelected());
	}

	@When("^_5rd user goes through all permssion for system account administrator role$")
	public void _5rd_user_goes_through_all_permssion_for_system_account_administrator_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for system account administrator role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_system_account_administrator_role()
			throws Throwable {

	}

	@When("^_5rd user goes through all permission for fsd administrator role$")
	public void _5rd_user_goes_through_all_permission_for_fsd_administrator_role() throws Throwable {

	}

	@Then("^_5rd all the expeted checkbox should be marked for fsd administrator role$")
	public void _5rd_all_the_expeted_checkbox_should_be_marked_for_fsd_administrator_role() throws Throwable {

	}

	@When("^_5rd user goes through all permission for iam administrator role$")
	public void _5rd_user_goes_through_all_permission_for_iam_administrator_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for iam administrator role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_iam_administrator_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for system manager role$")
	public void _5rd_user_goes_through_all_the_permission_for_system_manager_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for system manager role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_system_manager_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for fsd agent role$")
	public void _5rd_user_goes_through_all_the_permission_for_fsd_agent_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for fsd agent role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_fsd_agent_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for gsa security approver role$")
	public void _5rd_user_goes_through_all_the_permission_for_gsa_security_approver_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for gsa security approver role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_gsa_security_approver_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for sampmo administrator all domains role$")
	public void _5rd_user_goes_through_all_the_permission_for_sampmo_administrator_all_domains_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for sampmo administrator all domains role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_sampmo_administrator_all_domains_role()
			throws Throwable {

	}

	@When("^_5rd user goes through all the permission for content manager role$")
	public void _5rd_user_goes_through_all_the_permission_for_content_manager_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for content manager role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_content_manager_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for tier2 functional helpdesk role$")
	public void _5rd_user_goes_through_all_the_permission_for_tier2_functional_helpdesk_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for tier2 functional helpdesk role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_tier2_functional_helpdesk_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for aad role$")
	public void _5rd_user_goes_through_all_the_permission_for_aad_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for aad role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_aad_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for tier2 technical helpdesk role$")
	public void _5rd_user_goes_through_all_the_permission_for_tier2_technical_helpdesk_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for tier2 technical helpdesk role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_tier2_technical_helpdesk_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for tier1 helpdesk role$")
	public void _5rd_user_goes_through_all_the_permission_for_tier1_helpdesk_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for tier1 helpdesk role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_tier1_helpdesk_role() throws Throwable {

	}

	@When("^_5rd user goes through all the permission for gsa data approver role$")
	public void _5rd_user_goes_through_all_the_permission_for_gsa_data_approver_role() throws Throwable {

	}

	@Then("^_5rd all the expected checkbox should be marked for gsa data approver role$")
	public void _5rd_all_the_expected_checkbox_should_be_marked_for_gsa_data_approver_role() throws Throwable {

	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}