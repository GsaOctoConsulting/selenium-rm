package gov.gsa.sam.rms.stepdefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.locators.MyWorkspacePageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.BulkUpdatePage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

public class OMBAdminBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(OMBAdminBusinessRulesStep.class);

	@Given("^omb_ user logs in as omb admin$")
	public void user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^user should see all user link$")
	public void user_should_see_all_user_link() throws Throwable {
		// all user link check
		boolean alluserfilterFound = T1WorkspacePage.elementFound(MyWorkspacePageLocator.ALL_USER_FSDFILTER);
		Assert.assertEquals(alluserfilterFound, false);
	}

	@And("^user should see role definition link$")
	public void user_should_see_role_definition_link() throws Throwable {
		// role definition link check
		boolean roleDefinitionLinkFound = UserDirectoryWidgetUtility.linkFound("Role Definitions");
		Assert.assertEquals(roleDefinitionLinkFound, false);
	}

	@And("^user should see bulk update link$")
	public void user_should_see_bulk_update_link() throws Throwable {
		// bulk update link check
		boolean bulkUpdateLinkFound = UserDirectoryWidgetUtility.linkFound("Bulk Update");
		Assert.assertEquals(bulkUpdateLinkFound, true);
	}

	@And("^user should see pending request link$")
	public void user_should_see_pending_request_link() throws Throwable {
		// pending request link check
		boolean pendingRequestLinkFound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK);
		Assert.assertEquals(pendingRequestLinkFound, true);
	}

	@Given("^_2_omb_ user logs in as omb admin$")
	public void _2_omb_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2_user navigates to my profile page$")
	public void _2_user_navigates_to_my_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
	}

	@Then("^_2_user should see the correct roles$")
	public void _2_user_should_see_the_correct_roles() throws Throwable {

		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_OMB_ADMIN,
				Constants.DOMAIN_ASSISTANCE_LISTING, "NO ACTION");
		Assert.assertEquals(roleFound, true);
	}

	@Given("^_3omb user logs in as omb admin$")
	public void _3omb_user_logs_in_as_omb_admin() throws Throwable {

		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3omb user navigates to user directory page$")
	public void _3omb_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_3omb user directory links should be clickable$")
	public void _3omb_user_directory_links_should_be_clickable() throws Throwable {
		int totalNoOfPages = UserDirectoryPage.getTotalNoOfPages();
		int currentPage = 1;

		do {// search page 1 regardless of whether other pages exist

			List<WebElement> userList = UserDirectoryPage.getUserList();
			logger.info("The size fo the user list is--" + userList.size());

			for (int i = 0; i < userList.size(); i++) {
				WebElement user = userList.get(i).findElement(UserDirectoryPageLocator.USERNAME);
				boolean isClickable = UserDirectoryPage.isClickable(user);
				Assert.assertTrue(isClickable);
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}

		} while (currentPage < totalNoOfPages);
	}

	@Given("^_4omb user logs in as omb admin$")
	public void _4omb_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4omb user navigates to user directory page$")
	public void _4omb_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_4omb user searches for hhs in org picker$")
	public void _4omb_user_searches_for_hhs_in_org_picker() throws Throwable {
		UserDirectoryPage.typeInOrgPicker("human");
		String firstSuggestedOrg = UserDirectoryPage.getFirstOrgFound();
		String expectedOrg = "Health and Human Services, Department of";
		Assert.assertEquals(firstSuggestedOrg, expectedOrg);// omb can search across departments
	}

	@Given("^_5omb user logs in as omb admin$")
	public void _5omb_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5omb user navigates to user directory page$")
	public void _5omb_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_5omb user should be able to clear filters$")
	public void _5omb_user_should_be_able_to_clear_filters() throws Throwable {
		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickClearFilter();

		boolean assistanceUserfilterSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);
		Assert.assertEquals(assistanceUserfilterSelected, false);

	}

	@Given("^_6omb user logs in as omb admin$")
	public void _6omb_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6omb user navigates to bulk update page$")
	public void _6omb_user_navigates_to_bulk_update_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickBulkUpdateLink();
	}

	@Then("^_6omb user should see org and roles across departments$")
	public void _6omb_user_should_see_org_and_roles_across_departments() throws Throwable {
		Assert.assertEquals(BulkUpdatePage.selectOrgIfFound(Constants.ORG_GSA), true);
		Assert.assertEquals(BulkUpdatePage.selectRoleIfFound(Constants.ROLE_OMB_ANALYST), true);
		Assert.assertEquals(BulkUpdatePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING), true);
		// -------------- ---
		Assert.assertEquals(BulkUpdatePage.selectOrgIfFound(Constants.ORG_HHS), true);
		Assert.assertEquals(BulkUpdatePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER), false);
		Assert.assertEquals(BulkUpdatePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA), false);
	}

	@Given("^_7omb user logs in as omb admin$")
	public void _7omb_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_7omb user tries to assign a role to a user through user directory$")
	public void _7omb_user_tries_to_assign_a_role_to_a_user_through_user_directory() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
		Assert.assertEquals(AssignRolePage.getCurrentTextInOrgPicker().trim(), "");
	}

	@Then("^_7omb in the assign role page organiztion should not be prepopulated$")
	public void _7omb_in_the_assign_role_page_organiztion_should_not_be_prepopulated() throws Throwable {

	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
