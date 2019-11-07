package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class AAM_UniqueSystemAccountNameStep {

	private static Logger logger = LoggerFactory.getLogger(AAM_UniqueSystemAccountNameStep.class);

	public static String uniqSan = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan_E = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan_F = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSan_I = NewSystemAccountPage.uniqueSAN();
	public static String expectedStatus = "System Information - completed";
	public static String systemAccountName = "Test";
	public static String expectedErrorMessage = "An account with this name already exists";

	// Scenario: Field level validation of non-unique account name
	@Given("^_A User logs in as a System Account Manager$")
	public void _a_User_logs_in_as_a_System_Account_Manager() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A User navigates to system account directory page$")
	public void _a_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_A User enters non-unique system account name$")
	public void _a_User_enters_non_unique_system_account_name() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(systemAccountName);
	}

	@Then("^_A User gets error message upon text box$")
	public void _a_User_gets_error_message_upon_text_box() throws Exception {
		Assert.assertEquals(expectedErrorMessage, NewSystemAccountPage.getErrorMessage());
		logger.info("System account name has been verified. Name is non-unique");
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Scenario: Field level validation of non-unique account name
	@Given("^_B User logs in as a System Account Admin$")
	public void _b_User_logs_in_as_a_System_Account_Admin() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B User navigates to system account directory page$")
	public void _b_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_B User enters non-unique system account name$")
	public void _b_User_enters_non_unique_system_account_name() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(systemAccountName);
	}

	@Then("^_B User gets error message upon text box$")
	public void _b_User_gets_error_message_upon_text_box() throws Exception {
		Assert.assertEquals(expectedErrorMessage, NewSystemAccountPage.getErrorMessage());
		logger.info("System account name has been verified. Name is non-unique");
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Scenario: Field level validation of unique account name
	@Given("^_C User logs in as a System Account Manager$")
	public void _c_User_logs_in_as_a_System_Account_Manager() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_C User navigates to system account directory page$")
	public void _c_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_C User enters unique system account name$")
	public void _c_User_enters_unique_system_account_name() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan);
	}

	@Then("^_C User should be able to continue registering for an account$")
	public void _c_User_should_be_able_to_continue_registering_for_an_account() throws Exception {
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		logger.info("System account name has been verified. Name is unique");
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Scenario: Field level validation of unique account name
	@Given("^_D User logs in as a System Account Admin$")
	public void _d_User_logs_in_as_a_System_Account_Admin() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_D User navigates to system account directory page$")
	public void _d_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_D User enters unique system account name$")
	public void _d_User_enters_unique_system_account_name() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan);
	}

	@Then("^_D User should be able to continue registering for an account$")
	public void _d_User_should_be_able_to_continue_registering_for_an_account() throws Exception {
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		logger.info("System account name has been verified. Name is unique");
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Validation of system account if it is in draft after clicking next button
	@Given("^_E User logs in as a System Account Manager$")
	public void _e_User_logs_in_as_a_System_Account_Manager() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_E User navigates to system account directory page$")
	public void _e_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_E User enters all the system information$")
	public void _e_User_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan_E);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
	}

	@When("^_E User clicks on next button$")
	public void _e_User_clicks_on_next_button() throws Exception {
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@When("^_E User again navigates to system account directory page$")
	public void _e_User_again_navigates_to_system_account_directory_page() throws Exception {
		SystemAccountDirectoryPage.clickWorkspaceLink();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@When("^_E User clicks on draft checkbox$")
	public void _e_User_clicks_on_draft_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickDraftFilter();
	}

	@When("^_E User enters same account name in search box$")
	public void _e_User_enters_same_account_name_in_search_box() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan_E);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
	}

	@Then("^_E User should be able to find system account name status as completed$")
	public void _e_User_should_be_able_to_find_system_account_name_status_as_completed() throws Exception {
		Assert.assertEquals(expectedStatus, NewSystemAccountPage.checkStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Validation of system account if it is in draft after clicking next button
	@Given("^_F User logs in as a System Account Admin$")
	public void _f_User_logs_in_as_a_System_Account_Admin() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_F User navigates to system account directory page$")
	public void _f_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_F User enters all the system information$")
	public void _f_User_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan_F);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
	}

	@When("^_F User clicks on next button$")
	public void _f_User_clicks_on_next_button() throws Exception {
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@When("^_F User again navigates to system account directory page$")
	public void _f_User_again_navigates_to_system_account_directory_page() throws Exception {
		SystemAccountDirectoryPage.clickWorkspaceLink();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@When("^_F User clicks on draft checkbox$")
	public void _f_User_clicks_on_draft_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickDraftFilter();
	}

	@When("^_F User enters same account name in search box$")
	public void _f_User_enters_same_account_name_in_search_box() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan_F);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
	}

	@Then("^_F User should be able to find system account name status as completed$")
	public void _f_User_should_be_able_to_find_system_account_name_status_as_completed() throws Exception {
		Assert.assertEquals(expectedStatus, NewSystemAccountPage.checkStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Field level validation of non-unique account name
	@Given("^_G User logs in as a non-fed user$")
	public void _g_User_logs_in_as_a_non_fed_user() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_G User navigates to system account directory page$")
	public void _g_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_G User enters non-unique system account name$")
	public void _g_User_enters_non_unique_system_account_name() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(systemAccountName);
	}

	@Then("^_G User gets error message upon text box$")
	public void _g_User_gets_error_message_upon_text_box() throws Exception {
		Assert.assertEquals(expectedErrorMessage, NewSystemAccountPage.getErrorMessage());
		logger.info("System account name has been verified. Name is non-unique");
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Field level validation of unique account name
	@Given("^_H User logs in as a non-fed user$")
	public void _h_User_logs_in_as_a_non_fed_user() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_H User navigates to system account directory page$")
	public void _h_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_H User enters unique system account name$")
	public void _h_User_enters_unique_system_account_name() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan);
	}

	@Then("^_H User should be able to continue registering for an account$")
	public void _h_User_should_be_able_to_continue_registering_for_an_account() throws Exception {
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		logger.info("System account name has been verified. Name is unique");
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Validation of system account if it is in draft after clicking next button
	@Given("^_I User logs in as a non-fed user$")
	public void _i_User_logs_in_as_a_non_fed_user() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_I User navigates to system account directory page$")
	public void _i_User_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@When("^_I User enters all the system information$")
	public void _i_User_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSan_I);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
	}

	@When("^_I User clicks on next button$")
	public void _i_User_clicks_on_next_button() throws Exception {
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@When("^_I User again navigates to system account directory page$")
	public void _i_User_again_navigates_to_system_account_directory_page() throws Exception {
		SystemAccountDirectoryPage.clickWorkspaceLink();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@When("^_I User clicks on draft checkbox$")
	public void _i_User_clicks_on_draft_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickDraftFilter();
	}

	@When("^_I User enters same account name in search box$")
	public void _i_User_enters_same_account_name_in_search_box() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSan_I);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
	}

	@Then("^_I User should be able to find system account name status as completed$")
	public void _i_User_should_be_able_to_find_system_account_name_status_as_completed() throws Exception {
		Assert.assertEquals(expectedStatus, NewSystemAccountPage.checkStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
