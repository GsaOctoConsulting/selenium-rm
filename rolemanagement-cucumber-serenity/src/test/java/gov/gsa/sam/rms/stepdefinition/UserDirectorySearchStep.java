package gov.gsa.sam.rms.stepdefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class UserDirectorySearchStep {
	private static Logger logger = LoggerFactory.getLogger(UserDirectorySearchStep.class);

	@Given("^_1uds user logs in workspace with dra role$")
	public void _1_user_longs_in_workspace_with_dra_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_1uds user navigates to userdirectory page and finds all user to be clickable$")
	public void _1_user_navigates_to_userdirectory_page_and_finds_all_user_to_be_clickable() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

		// int totalNoOfPages = UserDirectoryPage.getTotalNoOfPages();
		int totalNoOfPages = 10; // searching 10 pages right now
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

	@Given("^_2 user logs in workspace with dra role$")
	public void _2_user_logs_in_workspace_with_dra_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_2 user navigates to user directory org picker and see only his own org$")
	public void _2_user_navigates_to_user_directory_org_picker_and_see_only_his_own_org() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		Assert.assertEquals(true, UserDirectoryPage.orgPickerAllOrgsContainsThisSearchTermAndOrgName("human", "47"));

		/*
		 * String firstSuggestedOrg = UserDirectoryPage.getFirstOrgFound(); String
		 * expectedOrg = "Health and Human Services, Department of";// should // not //
		 * get // this // because // different // dept
		 * Assert.assertNotEquals(firstSuggestedOrg, expectedOrg); afterScenario();
		 */
	}

	@Given("^_3 user logs in workspace with assistance userrole$")
	public void _3_user_logs_in_workspace_with_assistance_user_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 user navigates to user directory page and searches for assistance admin$")
	public void _3_user_navigates_to_user_directory_page_and_searches_for_assistance_admin() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2 + " ");// fails due to a bug
	}

	@Then("^_3 user should be able to view access for assistance admin$")
	public void _3_user_should_be_able_to_view_access_for_assistance_admin() throws Throwable {
		UserDirectoryPage.clickViewAccessOnly(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);
		boolean roleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_ADMIN,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		afterScenario();
	}

	@Given("^_4 user logs in workspace as role administrator$")
	public void _4_user_logs_in_workspace_as_role_administrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4 user navigates to user directory page and searches for assistance admin$")
	public void _4_user_navigates_to_user_directory_page_and_searches_for_assistance_admin() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);
	}

	@Then("^_4 user should be able to view access for assistance admin$")
	public void _4_user_should_be_able_to_view_access_for_assistance_admin() throws Throwable {
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);

		boolean roleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_ADMIN,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		afterScenario();
	}

	@Given("^_5 user logs in workspace as role administrator$")
	public void _5_user_logs_in_workspace_as_role_administrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_5 user navigates to user directory page$")
	public void _5_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_5 user selects fed user filter$")
	public void _5_user_selects_fed_user_filter() throws Throwable {
		UserDirectoryPage.clickFedFilter();
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_5 user should only see accounts with federal user id$")
	public void _5_user_should_only_see_accounts_with_federal_user_id() throws Throwable {
		// int totalNoOfPages = UserDirectoryPage.getTotalNoOfPages();
		int totalNoOfPages = 3;// currently looking 20 pages
		int currentPage = 1;

		do {// search page 1 regardless of whether other pages exist

			List<WebElement> userList = UserDirectoryPage.getUserList();
			logger.info("The size fo the user list is--" + userList.size());

			for (int i = 0; i < userList.size(); i++) {
				WebElement id = userList.get(i).findElement(UserDirectoryPageLocator.ID);
				String userid = id.getText();
				System.out.println(userid);

				String userinfo = userList.get(i).findElement(UserDirectoryPageLocator.USER_INFO).getText();
				System.out.println(userinfo);

				if (userid.contains("@gmail") && !userinfo.contains(Constants.ORG_GSA)) {
					Assert.assertFalse(true);
				}
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}
		} while (currentPage < totalNoOfPages);
	}

	@Given("^_6uds user logs in workspace as role administrator$")
	public void _6uds_user_logs_in_workspace_as_role_administrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6uds user navigates to user directory page$")
	public void _6uds_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@And("^_6uds user selects fed user filter and goes to page ten$")
	public void _6uds_user_selects_fed_user_filter_and_goes_to_page_ten() throws Throwable {
		UserDirectoryPage.clickFedFilter();
		LaunchBrowserUtil.delay(4);
		UserDirectoryPage.clickPageNo(8, 12);
	}

	@When("^_6uds user unselects feduser filter and selects nonfed filter$")
	public void _6uds_user_unselects_feduser_filter_and_selects_nonfed_filter() throws Throwable {
		LaunchBrowserUtil.scrollUp();
		UserDirectoryPage.clickFedFilter();
		UserDirectoryPage.clickNonFedFilter();
	}

	@Then("^_6uds user should only see accounts with nonfederal user id$")
	public void _6uds_user_should_only_see_accounts_with_nonfederal_user_id() throws Throwable {
		int totalNoOfPages = UserDirectoryPage.getTotalNoOfPages();
		int currentlyselectedPage = UserDirectoryPage.getCurrentSelectedPage();
		Assert.assertEquals(0, currentlyselectedPage);
		int currentPage = 1;
		do {// search page 1 regardless of whether other pages exist
			List<WebElement> userList = UserDirectoryPage.getUserList();
			logger.info("The size fo the user list is--" + userList.size());
			for (int i = 0; i < userList.size(); i++) {
				WebElement id = userList.get(i).findElement(UserDirectoryPageLocator.ID);
				System.out.println(id.getText());
				boolean fedIdFound = id.getText().contains("@gsa");
				Assert.assertFalse(fedIdFound);
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}
		} while (currentPage < totalNoOfPages);
	}

	@Given("^_7uds user logs in workspace as assistanceuser$")
	public void _7uds_user_logs_in_workspace_as_assistanceuser() throws Throwable {

		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_7uds user navigates to user directory page$")
	public void _7uds_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_7uds user searches user using firstname$")
	public void _7uds_user_searches_user_using_four_characters() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("shah");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_7uds user should only see accounts containing firstname$")
	public void _7uds_user_should_only_see_accounts_containing_the_four_characters() throws Throwable {

	}

	@When("^_7uds user searches using lastname$")
	public void _7uds_user_searches_using_lastname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("raiaan");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_7uds user should only see accounts containing lastname$")
	public void _7uds_user_should_only_see_accounts_containing_lastname() throws Throwable {

	}

	@When("^_7uds user searches using fullname$")
	public void _7uds_user_searches_using_fullname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("shah raiaan");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_7uds user should only see accounts containing fullname$")
	public void _7uds_user_should_only_see_accounts_containing_fullname() throws Throwable {

	}

	@Given("^_8uds user logs into workspace as role administrator$")
	public void _8uds_user_logs_into_workspace_as_role_administrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_8uds user navigates to user directory page$")
	public void _8uds_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

	}

	@And("^_8uds user searches for a user with noroles in the user search box$")
	public void _8uds_user_searches_for_a_user_with_noroles_in_the_user_search_box() throws Throwable {
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@And("^_8uds enters gsa in org search box$")
	public void _8uds_enters_gsa_in_org_search_box() throws Throwable {
		UserDirectoryPage.selectOrgInOrgPicker(Constants.ORG_GSA);
	}

	@When("^_8uds selects the filter for org where users have roles$")
	public void _8uds_selects_the_filter_for_org_where_users_have_roles() throws Throwable {
		UserDirectoryPage.clickOrgTypeWhereUsersHaveRoles();
	}

	@Then("^_8uds no search results message should be displayed$")
	public void _8uds_no_search_results_message_should_be_displayed() throws Throwable {
		String noresultsMessageFoundText = UserDirectoryPage.getNoResultsmessageFound();
		Assert.assertEquals("No User(s) Found for the Selected Criteria", noresultsMessageFoundText);
	}

	@Given("^_9uds user logs in workspace as contract opp admin$")
	public void _9uds_user_logs_in_workspace_as_contract_opp_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_9uds user navigates to user directory page$")
	public void _9uds_user_navigates_to_user_directory_page() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_9uds user searches user using firstname$")
	public void _9uds_user_searches_user_using_firstname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("shah");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_9uds user should only see accounts containing firstname$")
	public void _9uds_user_should_only_see_accounts_containing_firstname() throws Throwable {

	}

	@When("^_9uds user searches using lastname$")
	public void _9uds_user_searches_using_lastname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("raiaan");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_9uds user should only see accounts containing lastname$")
	public void _9uds_user_should_only_see_accounts_containing_lastname() throws Throwable {

	}

	@When("^_9uds user searches using fullname$")
	public void _9uds_user_searches_using_fullname() throws Throwable {
		boolean searchtermfound = UserDirectoryPage.userPickerAllUsersContainsThisSearchTerm("shah raiaan");
		Assert.assertEquals(true, searchtermfound);
	}

	@Then("^_9uds user should only see accounts containing fullname$")
	public void _9uds_user_should_only_see_accounts_containing_fullname() throws Throwable {

	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
