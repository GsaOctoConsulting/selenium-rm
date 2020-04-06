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
	}

	@Then("^_2rd all the expected checkbox should be marked for aad role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_aad_role() throws Throwable {
		Assert.assertEquals(false, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).isSelected());
//		LaunchBrowserUtil.getDriver()
//		.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_ALL_DEFAULT).click();
		Assert.assertEquals(true, LaunchBrowserUtil.getDriver()
				.findElement(RolePermissionPageLocator.OTHERTRANSCATIONAWARED_DELETE_ASSOCIABLESELECTED).isSelected());
		
	}

	@When("^_2rd user goes through all permssion for sampmo admin role$")
	public void _2rd_user_goes_through_all_permssion_for_sampmo_admin_role() throws Throwable {

	}

	@Then("^_2rd all the expected checkbox should be marked for sampmo role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_sampmo_role() throws Throwable {

	}

	@When("^_2rd user goes through all permission for administrator role$")
	public void _2rd_user_goes_through_all_permission_for_administrator_role() throws Throwable {

	}

	@Then("^_2rd all the expeted checkbox should be marked for administrator role$")
	public void _2rd_all_the_expeted_checkbox_should_be_marked_for_administrator_role() throws Throwable {

	}

	@When("^_2rd user goes through all permission for contracting officer role$")
	public void _2rd_user_goes_through_all_permission_for_contracting_officer_role() throws Throwable {

	}

	@Then("^_2rd all the expected checkbox should be marked for contracting officer role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_contracting_officer_role() throws Throwable {

	}

	@When("^_2rd user goes through all the permission for contracting specialist role$")
	public void _2rd_user_goes_through_all_the_permission_for_contracting_specialist_role() throws Throwable {

	}

	@Then("^_2rd all the expected checkbox should be marked for contracting specialist role$")
	public void _2rd_all_the_expected_checkbox_should_be_marked_for_contracting_specialist_role() throws Throwable {

	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}