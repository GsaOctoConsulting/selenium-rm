package gov.gsa.sam.rms.stepdefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.locators.MyWorkspacePageLocator;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.BulkUpdatePage;
import gov.gsa.sam.rms.pages.BulkUpdateSelectionPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;

public class ALGrandUserBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(ALGrandUserBusinessRulesStep.class);

	@Given("^_1alg user logs in as assistance listing granduser$")
	public void _1alg_user_logs_in_as_assistance_listing_granduser() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AL_GRANDUSER_1, Constants.USERPASS,
		ConstantsAccounts.AL_GRANDUSER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@Then("^_1alg user should see not see role definitions link$")
	public void _1alg_user_should_see_not_see_role_definitions_link() throws Throwable {
		// role definition link check
		boolean roleDefinitionLinkFound = UserDirectoryWidgetUtility.linkFound("Role Definitions");
		Assert.assertEquals(roleDefinitionLinkFound, false);
	}

	@And("^_1alg user should see bulk update link$")
	public void _1alg_user_should_see_bulk_update_link() throws Throwable {
		// bulk update link check
		boolean bulkUpdateLinkFound = UserDirectoryWidgetUtility.linkFound("Bulk Update");
		Assert.assertEquals(bulkUpdateLinkFound, true);
	}

	@And("^_1alg user should see pending request link$")
	public void _1alg_user_should_see_pending_request_link() throws Throwable {
		// pending request link check
		boolean pendingRequestLinkFound = UserDirectoryWidgetUtility.linkFound("Pending Role Requests");
		Assert.assertEquals(pendingRequestLinkFound, true);
	}

	@Given("^_2alg user logs in as assistance listing grand user$")
	public void _2alg_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AL_GRANDUSER_1, Constants.USERPASS,
				ConstantsAccounts.AL_GRANDUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2alg user navigates to my profile page$")
	public void _2alg_user_navigates_to_my_profile_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
	}

	@Then("^_2alg user should see the correct roles$")
	public void _2alg_user_should_see_the_correct_roles() throws Throwable {
		boolean roleFound = MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_AL_GRANDUSER_SAMPMOADMINISTRATOR,
				Constants.DOMAIN_ASSISTANCE_LISTING, "NO ACTION");
		Assert.assertEquals(true, roleFound);

		boolean beginNowLinkFound = MyRolesPage.beginNowLinkFound();
		Assert.assertEquals(beginNowLinkFound, true);
	}

	@Given("^_3alg user logs in as assistance listing grand user$")
	public void _3alg_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AL_GRANDUSER_1, Constants.USERPASS,
				ConstantsAccounts.AL_GRANDUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3alg user navigates to user directory page$")
	public void _3alg_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_3alg user directory links should be clickable$")
	public void _3alg_user_directory_links_should_be_clickable() throws Throwable {
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

	@Given("^_4alg user logs in as assistance listing grand user$")
	public void _4alg_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AL_GRANDUSER_1, Constants.USERPASS,
				ConstantsAccounts.AL_GRANDUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4alg user navigates to user directory page$")
	public void _4alg_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_4alg user searches for hhs in org picker$")
	public void _4alg_user_searches_for_hhs_in_org_picker() throws Throwable {
		UserDirectoryPage.typeInOrgPicker("human");
		String firstSuggestedOrg = UserDirectoryPage.getFirstOrgFound();
		String expectedOrg = "Health and Human Services, Department of";
		Assert.assertEquals(firstSuggestedOrg, expectedOrg);
	}

	@Given("^_5alg user logs in as assistance listing grand user$")
	public void _5alg_user_logs_in_as_assistance_listing_grand_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AL_GRANDUSER_1, Constants.USERPASS,
				ConstantsAccounts.AL_GRANDUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5alg user navigates to user directory page$")
	public void _5alg_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_5alg user should be able to clear filters$")
	public void _5alg_user_should_be_able_to_clear_filters() throws Throwable {
		UserDirectoryPage.clickAlertsAdminFilter();
		UserDirectoryPage.clickAssistanceUserFilter();

		UserDirectoryPage.clickClearFilter();

		boolean alAdminfilterSelected = UserDirectoryPage.isFilterSelected(UserDirectoryPageLocator.ALERTSADMIN_FILTER);
		boolean assistanceUserfilterSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);

		Assert.assertEquals(alAdminfilterSelected, false);
		Assert.assertEquals(assistanceUserfilterSelected, false);
		// check for provide feedback link
		LaunchBrowserUtil.scrollToMiddle();
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
	}

	@And("^_5alg user should be able to see provide feedback link$")
	public void _5alg_user_should_be_able_to_see_provide_feedback_link() throws Throwable {
		// check for provide feedback link
		LaunchBrowserUtil.scrollToMiddle();
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
	}

	@Given("^_6alg user logs in as assistance listing grand user$")
	public void _6alg_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AL_GRANDUSER_1, Constants.USERPASS,
				ConstantsAccounts.AL_GRANDUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6alg user navigates to bulk update page$")
	public void _6alg_user_navigates_to_bulk_update_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickBulkUpdateLink();
	}

	@Then("^_6alg user should see org and roles in gsa$")
	public void _6alg_user_should_see_org_and_roles_in_gsa() throws Throwable {
		BulkUpdatePage.selectOrgIfFound(Constants.ORG_GSA);
		BulkUpdatePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		BulkUpdatePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		BulkUpdatePage.goToBulkUpdateSelectionPage();
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}