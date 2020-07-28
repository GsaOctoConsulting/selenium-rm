package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class EmailVarianceStep {
	private static Logger logger = LoggerFactory.getLogger(EmailVarianceStep.class);
	String additionalemailid = "";

	@Given("^_1ev a no role user logs into login dot gov$")
	public void _1_ev_a_no_role_user_logs_into_login_dot_gov() throws Exception {

		additionalemailid = SignInUtility.signIntoLogindotgov(ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE,
				Constants.USERPASS, ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE_SECRETKEY, Constants.ADD_EMAIL);

	}

	@Given("^_1ev user register a second email in the account which was not associated previously$")
	public void _1_ev_user_register_a_second_email_in_the_account_which_was_not_associated_previously()
			throws Exception {
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_1ev user goes to sam portal$")
	public void _1_ev_user_goes_to_sam_portal() throws Exception {
		SignInUtility.signIntoWorkspace(additionalemailid, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_1ev user should be able to login using new id$")
	public void _1_ev_user_should_be_able_to_login_using_new_id() throws Exception {
		String splashpageheading = T1WorkspacePage.getSplashPageHeading();
		Assert.assertEquals("New Email ID Detected", splashpageheading);
		T1WorkspacePage.clickProceedOnSplashPage();
	}

	@Then("^_1ev user should receives additional modals before landing into workspace$")
	public void _1_ev_user_should_receives_additional_modals_before_landing_into_workspace() throws Exception {
		T1WorkspacePage.goToAccountDetailsPage();
		LaunchBrowserUtil.closeBrowsers();
		// -----------------------delete the added email to set it
		// back-------------------------------
		SignInUtility.signIntoLogindotgov(ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE_SECRETKEY, Constants.DELETE);

	}

	@Given("^_2ev a no role nonfed user logs into login dot gov$")
	public void __ev_a_no_role_nonfed_user_logs_into_login_dot_gov() throws Exception {
		additionalemailid = SignInUtility.signIntoLogindotgovNonfed(ConstantsAccounts.NONFED_USER_3_NO_ROLES,
				Constants.USERPASS, ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.ADD_EMAIL);
	}

	@Given("^_2ev user register a second nonfed email in the account which was not associated previously$")
	public void _2ev_user_register_a_second_nonfed_email_in_the_account_which_was_not_associated_previously()
			throws Exception {
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_2ev user goes to sam portal$")
	public void __ev_user_goes_to_sam_portal() throws Exception {
		SignInUtility.signIntoWorkspace(additionalemailid, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_2ev user should be not able to login using new id and see proper message$")
	public void __ev_user_should_be_not_able_to_login_using_new_id_and_see_proper_message() throws Exception {
		String splashpageheading = T1WorkspacePage.getSplashPageHeading();
		Assert.assertEquals("New Email ID Detected", splashpageheading);
		T1WorkspacePage.clickCloseOnSplashPage();
		// -----------------------delete the added email to set it
		// back-------------------------------
		SignInUtility.signIntoLogindotgov(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.DELETE);
	}

	@Given("^_3ev a no role fed user logs into login dot gov$")
	public void _3_ev_a_no_role_fed_user_logs_into_login_dot_gov() throws Exception {
		additionalemailid = SignInUtility.signIntoLogindotgovNonfed(ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE,
				Constants.USERPASS, ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE_SECRETKEY, Constants.ADD_EMAIL);
	}

	@Given("^_3ev user register a nonfed email in the account which was not associated previously$")
	public void _3_ev_user_register_a_nonfed_email_in_the_account_which_was_not_associated_previously()
			throws Exception {
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_3ev user goes to sam portal$")
	public void _3_ev_user_goes_to_sam_portal() throws Exception {
		SignInUtility.signIntoWorkspace(additionalemailid, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_3ev user should be not able to login using new id and see proper message$")
	public void _3_ev_user_should_be_not_able_to_login_using_new_id_and_see_proper_message() throws Exception {
		String splashpageheading = T1WorkspacePage.getSplashPageHeading();
		Assert.assertEquals("New Email ID Detected", splashpageheading);
		T1WorkspacePage.clickCloseOnSplashPage();
		// -----------------------delete the added email to set it
		// back-------------------------------
		SignInUtility.signIntoLogindotgov(ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_7_EMAIL_VARIANCE_SECRETKEY, Constants.DELETE);
	}

	@Given("^_4ev a no role nonfed user logs into login dot gov$")
	public void _4ev_a_no_role_nonfed_user_logs_into_login_dot_gov() throws Throwable {
		additionalemailid = SignInUtility.signIntoLogindotgov(ConstantsAccounts.NONFED_USER_3_NO_ROLES,
				Constants.USERPASS, ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.ADD_EMAIL);
	}

	@And("^_4ev user register a fed email in the account which was not associated previously$")
	public void _4ev_user_register_a_fed_email_in_the_account_which_was_not_associated_previously() throws Throwable {
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_4ev user goes to sam portal$")
	public void _4ev_user_goes_to_sam_portal() throws Throwable {
		SignInUtility.signIntoWorkspace(additionalemailid, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_4ev user should be not able to login using new id and see proper message$")
	public void _4ev_user_should_be_not_able_to_login_using_new_id_and_see_proper_message() throws Throwable {
		String splashpageheading = T1WorkspacePage.getSplashPageHeading();
		Assert.assertEquals("New Email ID Detected", splashpageheading);
		T1WorkspacePage.clickCloseOnSplashPage();
		// -----------------------delete the added email to set it
		// back-------------------------------
		SignInUtility.signIntoLogindotgov(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.DELETE);
	}

}
