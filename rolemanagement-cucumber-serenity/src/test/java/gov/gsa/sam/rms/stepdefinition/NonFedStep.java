package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
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

	}

	@Then("^_1nf they should not see the entity details section$")
	public void _1nf_they_should_not_see_the_entity_details_section() throws Throwable {

	}

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
