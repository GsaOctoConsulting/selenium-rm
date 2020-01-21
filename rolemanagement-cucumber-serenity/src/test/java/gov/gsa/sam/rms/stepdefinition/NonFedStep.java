package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.locators.MyRolesPageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class NonFedStep {

	private static Logger logger = LoggerFactory.getLogger(NonFedStep.class);

	@Given("^_1nf nonfed user without a role logs in$")
	public void _1_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_1nf nonfed user should be able to view entity management Widget$")
	public void _1_nonfed_user_should_be_able_to_view_entity_management_widget() throws Throwable {
		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);

		boolean registerentitybuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.REGISTER_ENTITY_BUTTON);
		Assert.assertEquals(true, registerentitybuttonfound);

		boolean getentityIdbuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.GET_ENTITYID_BUTTON);
		Assert.assertEquals(true, getentityIdbuttonfound);
		afterScenario();
	}

	@When("^_1nf nonfed user navigates to the profile page$")
	public void _1nf_nonfed_user_navigates_to_the_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_1nf they should not see the entity details section$")
	public void _1nf_they_should_not_see_the_entity_details_section() throws Throwable {
		boolean entitysectionFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.ENTITY_INFO);
		Assert.assertEquals(false, entitysectionFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2nf nonfed user without a role logs in$")
	public void _2nf_nonfed_user_without_a_role_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_2_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_2_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_2nf nonfed user navigates to profile page$")
	public void _2nf_nonfed_user_navigates_to_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
	}

	@Then("^_2nf user should have no roles$")
	public void _2nf_user_should_have_no_roles() throws Throwable {
		String noroletext = MyRolesPage.getTextForNoRoleUser();
		Assert.assertEquals("No roles are assigned to user.", noroletext);
	}

	@When("^_2nf user clicks role request button to go to role request page$")
	public void _2nf_user_clicks_role_request_button_to_go_to_role_request_page() throws Throwable {
		MyRolesPage.clickRequestRoleButton();

	}

	@Then("^_2nf nonfed user should see the expected list of role to choose from$")
	public void _2nf_nonfed_user_should_see_the_expected_list_of_role_to_choose_from() throws Throwable {
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_COMPLIANCE);
		Assert.assertEquals(true, domainfound1);
		
		boolean roleFound2 = RequestRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound2);

		boolean domainfound2 = RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainfound2);

		boolean roleFound3 = RequestRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound3);

		boolean domainfound3 = RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		Assert.assertEquals(true, domainfound3);
	}

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
