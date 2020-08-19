package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;

public class MiscelleneousUsersBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(MiscelleneousUsersBusinessRulesStep.class);

	@Given("^_1mis user logs in as hhs user$")
	public void _1mis_user_logs_in_as_hhs_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.HHS_ASSISTANCEUSER_1, Constants.USERPASS,
				ConstantsAccounts.HHS_ASSISTANCEUSER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_1mis user should see not see role definitions link$")
	public void _1mis_user_should_see_not_see_role_definitions_link() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		// role definition link check
		boolean roleDefinitionLinkFound = UserDirectoryWidgetUtility.linkFound("Role Definitions");
		Assert.assertEquals(roleDefinitionLinkFound, false);
	}

	@And("^_1mis user should not see bulk update link$")
	public void _1mis_user_should_not_see_bulk_update_link() throws Throwable {
		// bulk update link check
		boolean bulkUpdateLinkFound = UserDirectoryWidgetUtility.linkFound("Bulk Update");
		Assert.assertEquals(bulkUpdateLinkFound, false);
	}

	@And("^_1mis user should not see pending request link$")
	public void _1mis_user_should_not_see_pending_request_link() throws Throwable {
		// pending request link check
		boolean pendingRequestLinkFound = UserDirectoryWidgetUtility.linkFound("Pending Role Requests");
		Assert.assertEquals(pendingRequestLinkFound, false);
	}

	
	
	
	
	
	
	
	
	
	 @Given("^_2mis user logs in as hhs user$")
	    public void _2mis_user_logs_in_as_hhs_user() throws Throwable {
		 SignInUtility.signIntoWorkspace(ConstantsAccounts.HHS_ASSISTANCEUSER_1, Constants.USERPASS,
					ConstantsAccounts.HHS_ASSISTANCEUSER_1_SECRETKEY, Constants.USER_FED);
	    }

	    @Then("^_2mis user search for themself through user directory$")
	    public void _2mis_user_search_for_themself_through_user_directory() throws Throwable {
	    	LaunchBrowserUtil.scrollAllTheWayDown();
			UserDirectoryWidgetUtility.searchUser(ConstantsAccounts.HHS_ASSISTANCEUSER_1);
			
			UserDirectoryPage.clickActions(ConstantsAccounts.HHS_ASSISTANCEUSER_1);
	    }

	    @And("^_2mis user should not see assign role dropdown$")
	    public void _2mis_user_should_not_see_assign_role_dropdown() throws Throwable {
	    	boolean assignRoleOptionFound = UserDirectoryPage.userActionOptionsFound("Assign Role");// 3
			Assert.assertEquals(assignRoleOptionFound, false);	
	    }

	    @And("^_2mis user should be able to see view access dropdown$")
	    public void _2mis_user_should_be_able_to_see_view_access_dropdown() throws Throwable {
	    	boolean viewAccessOptionFound = UserDirectoryPage.userActionOptionsFound("View Access");
			Assert.assertEquals(viewAccessOptionFound, true); 
	    }
	    
	    
	    @Given("^_3mis user logs in as hhs user$")
	    public void _3mis_user_logs_in_as_hhs_user() throws Throwable {
	    	SignInUtility.signIntoWorkspace(ConstantsAccounts.HHS_ASSISTANCEUSER_1, Constants.USERPASS,
					ConstantsAccounts.HHS_ASSISTANCEUSER_1_SECRETKEY, Constants.USER_FED);
	    }

	    @Then("^_3mis user navigates to request role page$")
	    public void _3mis_user_navigates_to_request_role_page() throws Throwable {
	    	T1WorkspacePage.goToAccountDetailsPage();
			AccountDetailsPage.goToPageOnSideNav("My Roles");
			MyRolesPage.setDriver(AccountDetailsPage.getDriver());
			MyRolesPage.clickRequestRoleButton();
	    }

	    @And("^_3mis user requests assistance user role for hhs in assistance listing$")
	    public void _3mis_user_requests_assistance_user_role_for_hhs_in_assistance_listing() throws Throwable {
	    	RequestRolePage.writeSupervisorName("AJ"); 
			RequestRolePage.writeSupervisorEmail("email@gmail.com");
			RequestRolePage.selectOrgIfFound(Constants.ORG_HHS,0);
			RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
			RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
			RequestRolePage.writeComment("test comments");
			RequestRolePage.clickSubmit();
	    }

	    @And("^_3mis user should then be able to delete that request$")
	    public void _3mis_user_should_then_be_able_to_delete_that_request() throws Throwable {
	    	//------------delete pending request-----
			RequestRolePage.clickPendingRoleRequest();
			RequestRolePage.clickPending();
			RoleRequestPendingPage.clickDeleteButton();
			RoleRequestPendingPage.confirmDeleteOnPopup();  
	    }
	    
	    
	    
	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}