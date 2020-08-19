package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class UserDirectoryFilterStep {
	private static Logger logger = LoggerFactory.getLogger(UserDirectoryFilterStep.class);

	@Given("^_1 given user logs in as dra$")
	public void _1_given_user_logs_in_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user navigates to user directory page$")
	public void _1_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_1 user checks filter should be able to clears them$")
	public void _1_user_checks_filter_should_be_able_to_clears_them() throws Throwable {

		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickClearFilter();
		boolean assistanceUserSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);

		Assert.assertEquals(false, assistanceUserSelected);
	}

	@Then("^_1 user should be able to see provide feed links$")
	public void _1_user_should_be_able_to_see_provide_feed_links() throws Throwable {
		// check for provide feedback link
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
		afterScenario();
	}

	@Given("^_2 given user logs in as assistance user$")
	public void _2_given_user_logs_in_as_assistanceuser() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user navigates to user directory page$")
	public void _2_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_2 user checks filter should be able to clears them$")
	public void _2_user_checks_filter_should_be_able_to_clears_them() throws Throwable {
		UserDirectoryPage.clickAdminFilter();
		;
		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickClearFilter();

		boolean adminfilterSelected = UserDirectoryPage.isFilterSelected(UserDirectoryPageLocator.ADMIN_FILTER);
		boolean assistanceUserFilterSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);

		Assert.assertEquals(adminfilterSelected, false);
		Assert.assertEquals(assistanceUserFilterSelected, false);
	}

	@Then("^_2 user should be able to see provide feed links$")
	public void _2_user_should_be_able_to_see_provide_feed_links() throws Throwable {
		// check for provide feedback link
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
		afterScenario();
	}

	@Given("^_3 given user logs in as role administrator$")
	public void _3_given_user_logs_in_as_roleadministrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 user navigates to user directory page$")
	public void _3_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.scrollAllTheWayDown();
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_3 user checks filter should be able to clears them$")
	public void _3_user_checks_filter_should_be_able_to_clears_them() throws Throwable {
		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickAdminFilter();
		UserDirectoryPage.clickClearFilter();

		boolean assistanceUserfilterSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);
		boolean adminFilterSelected = UserDirectoryPage.isFilterSelected(UserDirectoryPageLocator.ADMIN_FILTER);

		Assert.assertEquals(assistanceUserfilterSelected, false);
		Assert.assertEquals(adminFilterSelected, false);
	}

	@Then("^_3 user should be able to see provide feed links$")
	public void _3_user_should_be_able_to_see_provide_feed_links() throws Throwable {
		// check for provide feedback link
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
		afterScenario();
	}

	@Given("^_4udf given user logs in as assitance admin administrator$")
	public void _4udf_given_user_logs_in_as_assitance_admin_administrator() throws Throwable {

	}

	@Then("^_4udf user checks filter should be able to clears them$")
	public void _4udf_user_checks_filter_should_be_able_to_clears_them() throws Throwable {

	}

	@Then("^_4udf user should be able to see provide feed links$")
	public void _4udf_user_should_be_able_to_see_provide_feed_links() throws Throwable {

	}

	@And("^_4udf user navigates to user directory page$")
	public void _4udf_user_navigates_to_user_directory_page() throws Throwable {

	}

	@Given("^_5udf given user logs in as spaad$")
	public void _5udf_given_user_logs_in_as_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5udf user navigates to user directory page$")
	public void _5udf_user_navigates_to_user_directory_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
	}

	@When("^_5udf user searches for a noroles user account and applies user with no role filter$")
	public void _5udf_user_searches_for_a_noroles_user_account_and_applies_user_with_no_role_filter() throws Throwable {
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickUserWithNoRolesFilter();
	}

	@Then("^_5udf user should be able to view the account for the no role user$")
	public void _5udf_user_should_be_able_to_view_the_account_for_the_no_role_user() throws Throwable {
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@Given("^_6udf given user logs in as spaad$")
	public void _6udf_given_user_logs_in_as_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6udf user navigates to user directory page$")
	public void _6udf_user_navigates_to_user_directory_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
	}

	@When("^_6udf user searches for a user with subtier admin role in federal hierarchy$")
	public void _6udf_user_searches_for_a_user_with_subtier_admin_role_in_federal_hierarchy() throws Throwable {
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.FH_SUBTIER_ADMIN_1);
		UserDirectoryPage.clickFHdomainFilter();
		UserDirectoryPage.clickRoleFilter(UserDirectoryPageLocator.ROLE_FILTER_SUBTIERADMIN);
	}

	@Then("^_6udf user should be able to view the account for the subtier admin$")
	public void _6udf_user_should_be_able_to_view_the_account_for_the_subtier_admin() throws Throwable {
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.FH_SUBTIER_ADMIN_1);
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
