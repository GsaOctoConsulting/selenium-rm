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

public class AssistanceAdminBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(AssistanceAdminBusinessRulesStep.class);

	@Given("^_1aa user logs in as assistance admin$")
	public void _1aa_user_logs_in_as_assistance_listing_granduser() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_1aa user should see not see role definitions link$")
	public void _1aa_user_should_see_not_see_role_definitions_link() throws Throwable {
		// role definition link check
		boolean roleDefinitionLinkFound = UserDirectoryWidgetUtility.linkFound("Role Definitions");
		Assert.assertEquals(roleDefinitionLinkFound, false);
	}

	@And("^_1aa user should see bulk update link$")
	public void _1aa_user_should_see_bulk_update_link() throws Throwable {
		// bulk update link check
		boolean bulkUpdateLinkFound = UserDirectoryWidgetUtility.linkFound("Bulk Update");
		Assert.assertEquals(bulkUpdateLinkFound, true);
	}

	@And("^_1aa user should see pending request link$")
	public void _1aa_user_should_see_pending_request_link() throws Throwable {
		// pending request link check
		boolean pendingRequestLinkFound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK);
		Assert.assertEquals(pendingRequestLinkFound, true);
	}

	@Given("^_2aa user logs in as assistance admin$")
	public void _2aa_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2aa user navigates to my profile page$")
	public void _2aa_user_navigates_to_my_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
	}

	@Then("^_2aa user should see the correct roles$")
	public void _2aa_user_should_see_the_correct_roles() throws Throwable {
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_ADMIN,
				Constants.DOMAIN_ASSISTANCE_LISTING, "NO ACTION");
		Assert.assertEquals(roleFound, true);

		boolean beginNowLinkFound = MyRolesPage.beginNowLinkFound();
		Assert.assertEquals(beginNowLinkFound, true);

	}

	@Given("^_3aa user logs in as assistance admin$")
	public void _3aa_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);

	}

	@And("^_3aa user navigates to user directory page$")
	public void _3aa_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_3aa user directory links should be clickable$")
	public void _3aa_user_directory_links_should_be_clickable() throws Throwable {
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

	@Given("^_4aa user logs in as assistance listing grand user$")
	public void _4aa_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4aa user navigates to user directory page$")
	public void _4aa_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_4aa user searches for hhs in org picker$")
	public void _4aa_user_searches_for_hhs_in_org_picker() throws Throwable {
		UserDirectoryPage.typeInOrgPicker("human");
		LaunchBrowserUtil.delay(2);
		String firstSuggestedOrg = UserDirectoryPage.getFirstOrgFound();
		
		String expectedOrg = "Health and Human Services, Department of";// should
																		// not
																		// get
																		// this
																		// because
																		// different
																		// department
		Assert.assertNotEquals(firstSuggestedOrg, expectedOrg);
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
