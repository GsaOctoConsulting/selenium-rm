package gov.gsa.sam.rms.stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class BetaSmokeTestStep {
	@Given("^_1bst user logs in with no role user$")
	public void _1_user_logs_in_with_no_role_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.BETA_NO_ROLE_GSA_1, Constants.USERPASS,
				ConstantsAccounts.BETA_NO_ROLE_GSA_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_1bst user should be able to navigate to acount details page$")
	public void _1_user_should_be_able_to_navigate_to_acount_details_page() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();

	}

	@And("^_1bst user should be able to navigate to role migration page$")
	public void _1_user_should_be_able_to_navigate_to_role_migration_page() throws Throwable {
		AccountDetailsPage.goToPageOnSideNav("Role Migrations");
	}

	@And("^_1bst user should be able to navigate to request role page$")
	public void _1_user_should_be_able_to_browse_to_role_request_page() throws Throwable {
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		AccountDetailsPage.goToMyWorkspacePage();

	}

	@And("^_1bst user should be able to browse to user directory page$")
	public void _1_user_should_be_able_to_browse_to_user_directory_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		
		
		
	}
	@And("^_1bst user should be able to search user in the search bar$")
	public void _1_user_should_be_able_to_search_user_using_search_bar() throws Throwable {
		
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.BETA_NO_ROLE_GSA_1);
		LaunchBrowserUtil.delay(2);
		UserDirectoryPage.goToMyWorkspacePage();	
	}	
	@And("^_1bst user should be able to browse to feeds page$")
    public void _1bst_user_should_be_able_to_browse_to_feeds_page() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
    }

    @And("^_1bst user should be able to sign out$")
    public void _1bst_user_should_be_able_to_sign_out() throws Throwable {
    	FeedsRequestPage.goToWorkspacePage();
    	T1WorkspacePage.signOut();
    }
}
