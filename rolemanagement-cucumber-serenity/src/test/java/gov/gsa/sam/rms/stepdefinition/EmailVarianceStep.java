package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EmailVarianceStep {
	private static Logger logger = LoggerFactory.getLogger(EmailVarianceStep.class);
	@Given("^_1ev a no role user logs into login dot gov$")
	public void _1_ev_a_no_role_user_logs_into_login_dot_gov(int arg1) throws Exception {
	   
	   
	}

	@Given("^_1ev user register a second email in the account which was not associated previously$")
	public void _1_ev_user_register_a_second_email_in_the_account_which_was_not_associated_previously(int arg1) throws Exception {
	   
	}

	@When("^_1ev user goes to sam portal$")
	public void _1_ev_user_goes_to_sam_portal(int arg1) throws Exception {
	  
	}

	@Then("^_1ev user should be able to login using new id$")
	public void _1_ev_user_should_be_able_to_login_using_new_id(int arg1) throws Exception {
	   
	}

	@Then("^_1ev user should receives additional modals before landing into workspace$")
	public void _1_ev_user_should_receives_additional_modals_before_landing_into_workspace(int arg1) throws Exception {
	   
	}
	
	
	
}
