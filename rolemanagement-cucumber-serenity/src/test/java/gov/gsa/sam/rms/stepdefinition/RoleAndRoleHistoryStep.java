package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class RoleAndRoleHistoryStep {

	private static Logger logger = LoggerFactory.getLogger(RoleAndRoleHistoryStep.class);

	@Given("^_1rh user logs in workspace with dra role$")
	public void _1rh_user_logs_in_workspace_with_dra_role() throws Throwable {

	}

	@And("^_1rh dra navigates to user directory and looks up a user with contracting specialist role$")
	public void _1rh_dra_navigates_to_user_directory_and_looks_up_a_user_with_contracting_specialist_role()
			throws Throwable {

	}

	@When("^_1rh dra removes the role for the user$")
	public void _1rh_dra_removes_the_role_for_the_user() throws Throwable {

	}

	@Then("^_1rh the role history should update in the profile for dra$")
	public void _1rh_the_role_history_should_update_in_the_profile_for_dra() throws Throwable {

	}

	@And("^_1rh the role history should update in the profile for the user$")
	public void _1rh_the_role_history_should_update_in_the_profile_for_the_user() throws Throwable {

	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
