package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.locators.CreateNewRolePageLocator;
import gov.gsa.sam.rms.pages.CreateNewRolePage;
import gov.gsa.sam.rms.pages.RoleDefinitionPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;

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
		RMWidgetUtility.clickRoleDefinitionsLink();
	}

	@Then("^_1 user shoudl see proper links and buttons$")
	public void _1_user_shoudl_see_proper_links_and_buttons() throws Throwable {
		//check create newrole page...and submit and cancel button
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

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}