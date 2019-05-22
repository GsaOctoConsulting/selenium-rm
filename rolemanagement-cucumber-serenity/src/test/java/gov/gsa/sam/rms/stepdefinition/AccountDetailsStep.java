package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.login.gov.pages.CommonProfilePage;
import gov.gsa.sam.rms.login.gov.pages.RequestRoleOptionalPage;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class AccountDetailsStep {
	private static Logger logger = LoggerFactory.getLogger(AccountDetailsStep.class);

	@Given("^_1 a no role user logs in$")
	public void _1_a_no_role_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user navigates to profile page$")
	public void _1_user_navigates_to_profile_page() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_1 user generates otp with show apikey generation$")
	public void _1_user_generates_otp_with_show_apikey_generation() throws Throwable {
		AccountDetailsPage.clickShowAPIKeyCheckbox();
		String otp1 = LaunchBrowserUtil.getOtpFromEmailForApiKey(Constants.GMAIL_USERNAME, Constants.USERPASS, 1);
		LaunchBrowserUtil.closeThisBrowserTab();
		LaunchBrowserUtil.switchTabs(2);

		AccountDetailsPage.clickSendNewCodeLink();
		String otp2 = LaunchBrowserUtil.getOtpFromEmailForApiKey(Constants.GMAIL_USERNAME, Constants.USERPASS,2);
		LaunchBrowserUtil.switchTabs(2);
		AccountDetailsPage.enterOtp(otp1);
		AccountDetailsPage.clickContinueButton();

		boolean errorMessageFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.API_KEY_ERROR_MESSAGE);
		Assert.assertEquals(true, errorMessageFound);
		CommonMethods.delay(3);
		AccountDetailsPage.enterOtp(otp2);
		AccountDetailsPage.clickContinueButton();
		boolean textFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.API_KEY_TEXT);
		Assert.assertEquals(true, textFound);
		Assert.assertEquals("Api Key:", AccountDetailsPage.getApiKeyText());
		
	}

	@And("^_1 user generates second otp$")
	public void _1_user_generates_second_otp() throws Throwable {

	}

	@When("^_1 user uses first otp then api key generation should fail$")
	public void _1_user_uses_first_otp_then_api_key_generation_should_fail() throws Throwable {

	}

	@When("^_1 user uses latest otp then api key generation should work$")
	public void _1_user_uses_latest_otp_then_api_key_generation_should_work() throws Throwable {
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2 a role admin user logs in$")
	public void _2_a_role_admin_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user navigates to profile page$")
	public void _2_user_navigates_to_profile_page() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		CommonMethods.delay(3);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_2 user generates otp with show apikey generation$")
	public void _2_user_generates_otp_with_show_apikey_generation() throws Throwable {
		AccountDetailsPage.clickShowAPIKeyCheckbox();
	}

	@Then("^_2 user should be able to see the api key$")
	public void _2_user_should_be_able_to_see_the_api_key() throws Throwable {
		String otp = LaunchBrowserUtil.getOtpFromEmailForApiKey("shah.raiaan@gsa.gov", Constants.USERPASS,1);
		LaunchBrowserUtil.switchTabs(2);
		AccountDetailsPage.enterOtp(otp);
		AccountDetailsPage.clickContinueButton();
		boolean textFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.API_KEY_TEXT);
		Assert.assertEquals(true, textFound);
		Assert.assertEquals("Api Key:", AccountDetailsPage.getApiKeyText());
		
		CommonMethods.delay(5);
		LaunchBrowserUtil.closeBrowsers();

	}

	@Given("^_3 user creates a new account in login dot gov$")
	public void _3_user_creates_a_new_account_in_login_dot_gov() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.fed.accountno");
		SignUpUtility.signUpNewUser("shah.raiaan+newregistereduser" + counter + "@gsa.gov", Constants.USERPASS);
		CommonProfilePage.enterFirstName("firstname");
		CommonProfilePage.enterLastName("lastname");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.scrollAllTheWayDown();
		RequestRoleOptionalPage.clickSkipAndFinish();

	}

	@And("^_3 user navigates to profile page$")
	public void _3_user_navigates_to_profile_page() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_3 should be able to see request api key button$")
	public void _3_should_be_able_to_request_api_key_button() throws Throwable {
		AccountDetailsPage.clickRequestApiKeyButton();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_4 a no role user logs in$")
	public void _4_a_no_role_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4 user navigates to profile page$")
	public void _4_user_navigates_to_profile_page() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_4 user generates api key$")
	public void _4_user_generates_api_key() throws Throwable {
		AccountDetailsPage.clickShowAPIKeyCheckbox();
		String otp = LaunchBrowserUtil.getOtpFromEmailForApiKey(Constants.GMAIL_USERNAME, Constants.USERPASS,1);
		LaunchBrowserUtil.switchTabs(2);
		CommonMethods.delay(780);
		AccountDetailsPage.clickContinueOnSessionExtension();
		LaunchBrowserUtil.scrollToMiddle();
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonMethods.delay(240);
		AccountDetailsPage.enterOtp(otp);
		AccountDetailsPage.clickContinueButton();
		boolean textFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.API_KEY_TEXT);
		Assert.assertEquals(true, textFound);
		Assert.assertEquals("Api Key:", AccountDetailsPage.getApiKeyText());
		
	}

	@And("^_4 user continues session beyound fifteen minutes$")
	public void _4_user_continues_session_beyound_fifteen_minutes() throws Throwable {

	}

	@Then("^_4 user should able to use api key on the twentieth minute$")
	public void _4_user_should_able_to_use_api_key_on_the_twentieth_minute() throws Throwable {
		CommonMethods.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}
	
	
	@Given("^_5 a nonfed user logs in$")
	public void _5_a_nonfed_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_5 user navigates to profile page$")
	public void _5_user_navigates_to_profile_page() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		CommonMethods.delay(3);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_5 user generates otp with show apikey generation$")
	public void _5_user_generates_otp_with_show_apikey_generation() throws Throwable {
		AccountDetailsPage.clickShowAPIKeyCheckbox();
	}

	@Then("^_5 user should be able to see the api key$")
	public void _4_user_should_be_able_to_see_the_api_key() throws Throwable {
		String otp = LaunchBrowserUtil.getOtpFromEmailForApiKeyNonFed(Constants.GMAIL_NONFED, Constants.USERPASS_NONFED,1);
		LaunchBrowserUtil.switchTabs(2);
		AccountDetailsPage.enterOtp(otp);
		AccountDetailsPage.clickContinueButton();
		boolean textFound = AccountDetailsPage.elementFound(AccountDetailsPageLocator.API_KEY_TEXT);
		Assert.assertEquals(true, textFound);
		Assert.assertEquals("Api Key:", AccountDetailsPage.getApiKeyText());
		LaunchBrowserUtil.clearCookies();
		CommonMethods.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
