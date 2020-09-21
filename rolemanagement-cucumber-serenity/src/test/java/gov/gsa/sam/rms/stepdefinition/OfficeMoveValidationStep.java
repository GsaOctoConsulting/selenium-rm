package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.FHUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class OfficeMoveValidationStep {
	private static Logger logger = LoggerFactory.getLogger(OfficeMoveValidationStep.class);
	private String createdSubtier1 = "";
	private String createdSubtier2 = "";
	private String createdOfficeUnderSubtier1 = "";

	@Given("^_1omv fh super admin logs in$")
	public void _1omv_fh_super_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_SUPER_ADMIN, Constants.USERPASS,
				ConstantsAccounts.FH_SUPER_ADMIN_SECRETKEY, Constants.USER_FED);

	}

	@And("^_1omv super admin creates the first subiter under gsa$")
	public void _1omv_super_admin_creates_the_first_subiter_under_gsa() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		T1WorkspacePage.gotoFHPage();
		FHUtility.goToOrgDetails(Constants.ORG_GSA);
		String uniqueagencycode = "1" + counter.substring(1, counter.length());
		createdSubtier1 = FHUtility.createSubTier("RMOffice" + uniqueagencycode, uniqueagencycode, "111", "111");
	}

	@And("^_1omv super admin creates the second subtier under gsa$")
	public void _1omv_super_admin_creates_the_second_subtier_under_gsa() throws Throwable {

	}

	@And("^_1omv super admin creates an office under the second subtier$")
	public void _1omv_super_admin_creates_an_office_under_the_second_subtier() throws Throwable {

	}

	@Then("^_1omv spaad logs in$")
	public void _1omv_spaad_logs_in() throws Throwable {

	}

	@And("^_1omv assigns contracting officer role in contract data and contracting opportunites domain to a user at office level under subtiert two$")
	public void _1omv_assigns_contracting_officer_role_in_contract_data_and_contracting_opportunites_domain_to_a_user_at_office_level_under_subtiert_two()
			throws Throwable {

	}

	@And("^_1omv spaad assigns contracting specialist role to the user under contract data domain$")
	public void _1omv_spaad_assigns_contracting_specialist_role_to_the_user_under_contract_data_domain()
			throws Throwable {

	}

	@When("^_1omv super admin moves the office into subtier 1$")
	public void _1omv_super_admin_moves_the_office_into_subtier_1() throws Throwable {

	}

	@Then("^_1omv user should be left with one role$")
	public void _1omv_user_should_be_left_with_one_role() throws Throwable {

	}

}
