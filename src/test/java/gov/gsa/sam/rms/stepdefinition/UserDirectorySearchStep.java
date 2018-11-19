package gov.gsa.sam.rms.stepdefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class UserDirectorySearchStep {
	private static Logger logger = LoggerFactory.getLogger(UserDirectorySearchStep.class);

	@Then("^_1 user navigates to userdirectory page and finds all user to be clickable$")
	public void _1_user_navigates_to_userdirectory_page_and_finds_all_user_to_be_clickable() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		RMWidgetUtility.clickUserDirectoryLink();

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

	@Given("^_2 user logs in workspace with dra role$")
	public void _2_user_logs_in_workspace_with_dra_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+assistanceAdminSelenium@gsa.gov", Constants.userPass);
	}

	@Then("^_2 user navigates to user directory org picker and see only his own org$")
	public void _2_user_navigates_to_user_directory_org_picker_and_see_only_his_own_org() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.typeInOrgPicker("human");
		String firstSuggestedOrg = UserDirectoryPage.getFirstOrgFound();
		String expectedOrg = "Health and Human Services, Department of";// should
																		// not
																		// get
																		// this
																		// because
																		// different
																		// dept
		Assert.assertNotEquals(firstSuggestedOrg, expectedOrg);
		afterScenario();
	}

	@Given("^_3 user logs in workspace with assistance userrole$")
	public void _3_user_logs_in_workspace_with_assistance_user_role() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+assistanceuserv2@gsa.gov", Constants.userPass);
	}

	@And("^_3 user navigates to user directory page and searches for assistance admin$")
	public void _3_user_navigates_to_user_directory_page_and_searches_for_assistance_admin() throws Throwable {
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker("shah.raiaan+assistanceadminv2@gsa.gov");

	}

	@Then("^_3 user should be able to view access for assistance admin$")
	public void _3_user_should_be_able_to_view_access_for_assistance_admin() throws Throwable {
		/*UserDirectoryPage.clickViewAccess("shah.raiaan+assistanceadminv2@gsa.gov");*/
	}
	@Given("^_4 user logs in workspace as role administrator$")
	public void _4_user_logs_in_workspace_as_role_administrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.userPass);
	}
	@And("^_4 user navigates to user directory page and searches for assistance admin$")
	public void _4_user_navigates_to_user_directory_page_and_searches_for_assistance_admin() throws Throwable {
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker("shah.raiaan+assistanceAdminSelenium@gsa.gov");

	}@Then("^_4 user should be able to view access for assistance admin$")
	public void _4_user_should_be_able_to_view_access_for_assistance_admin() throws Throwable {
		UserDirectoryPage.clickViewAccess("shah.raiaan+assistanceAdminSelenium@gsa.gov");
	}
	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
