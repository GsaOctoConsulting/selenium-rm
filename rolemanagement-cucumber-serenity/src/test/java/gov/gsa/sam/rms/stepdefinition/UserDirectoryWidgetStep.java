package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import org.junit.Assert;

public class UserDirectoryWidgetStep {
	private static Logger logger = LoggerFactory.getLogger(UserDirectoryWidgetStep.class);

	@Given("^_1 user logs in workspace with assistance admin role$")
	public void _1_user_logs_in_workspace_with_assistance_admin_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonMethods.delay(4);
	}

	@Then("^_1 user should see widget and pending role request should be clickable$")
	public void _1_user_should_see_widget_and_pending_role_request_should_be_clickable() throws Throwable {
		boolean rmWidgetFound = RMWidgetUtility.widgetFound();
		Assert.assertEquals(true,rmWidgetFound);
		RMWidgetUtility.clickPendingRequestLink();
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2 user longs in workspace with dra role$")
	public void _2_user_longs_in_workspace_with_dra_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
		CommonMethods.delay(4);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_2 user should see pending request and bulk update link but no role defintion link$")
	public void _2_user_should_see_pending_request_and_bulk_update_link_but_no_role_defintion_link() throws Throwable {
		// role definition link check
		boolean roleDefinitionLinkFound = RMWidgetUtility.linkFound("Role Definitions");
		Assert.assertEquals(false,roleDefinitionLinkFound);

		// bulk update link check
		boolean bulkUpdateLinkFound = RMWidgetUtility.linkFound("Bulk Update");
		Assert.assertEquals(true,bulkUpdateLinkFound);

		// pending request link check
		boolean pendingRequestLinkFound = RMWidgetUtility.linkFound("Pending Role Requests");
		Assert.assertEquals(true,pendingRequestLinkFound);
		afterScenario();
	}

	@Given("^_3 user logs in workspace with assistance user role$")
	public void _3_user_logs_in_workspace_with_assistance_user_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
		CommonMethods.delay(4);
	}

	@Then("^_3 user navigates to my roles page to ensure information is accurate$")
	public void _3_user_navigates_to_my_roles_page_to_ensure_information_is_accurate() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
	}

	@And("^_3 user goes back to workspace page and searches for a user through widget$")
	public void _3_user_goes_back_to_workspace_page_and_searches_for_a_user_through_widget() throws Throwable {
		AccountDetailsPage.goToMyWorkspacePage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.searchUser(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);

	}

	@Then("^_3 the result should be displayed in the user directory page$")
	public void _3_the_result_should_be_displayed_in_the_user_directory_page() throws Throwable {
		UserDirectoryPage.clickAdminFilter();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);
		UserDirectoryPage.clickViewAccessOnly(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);
		boolean roleFound = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_ADMIN,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
	}

	@Given("^_4 user logs in workspace as role admin$")
	public void _4_user_logs_in_workspace_as_role_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		CommonMethods.delay(4);
	}

	@Then("^_4 user navigates to my roles page to ensure information is accurate$")
	public void _4_user_navigates_to_my_roles_page_to_ensure_information_is_accurate() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ROLEADMIN_SAMPMOADMINALLDOMAINS,
				Constants.DOMAIN_ADMIN, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
	}

	@And("^_4 user goes back to workspace page and searches for a user through widget$")
	public void _4_user_goes_back_to_workspace_page_and_searches_for_a_user_through_widget() throws Throwable {
		AccountDetailsPage.goToMyWorkspacePage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		// role definition link check
		boolean roleDefinitionLinkFound = RMWidgetUtility.linkFound("Role Definitions");
		Assert.assertEquals(true,roleDefinitionLinkFound);

		// bulk update link check
		boolean bulkUpdateLinkFound = RMWidgetUtility.linkFound("Bulk Update");
		Assert.assertEquals(true,bulkUpdateLinkFound);

		// pending request link check
		boolean pendingRequestLinkFound = RMWidgetUtility.linkFound("Pending Role Requests");
		Assert.assertEquals(true,pendingRequestLinkFound);

		CommonMethods.delay(2);

		RMWidgetUtility.userSearchForRA(ConstantsAccounts.ROLE_ADMIN_USER_3);

	}

	@Then("^_4 the result should be displayed in the user directory page$")
	public void _4_the_result_should_be_displayed_in_the_user_directory_page() throws Throwable {
		UserDirectoryPage.clickSAMPmoAdminAllDomainsFilter();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ROLE_ADMIN_USER_3);
		UserDirectoryPage.clickViewAccessOnly(ConstantsAccounts.ROLE_ADMIN_USER_3);
		boolean roleFound = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ROLEADMIN_SAMPMOADMINALLDOMAINS,
				Constants.DOMAIN_ADMIN, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		afterScenario();
		
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		CommonMethods.delay(3);
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
