package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import junit.framework.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;

import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class RoleAssignStep {
	private static Logger logger = LoggerFactory.getLogger(RoleAssignStep.class);

	@Given("^_1 user logs in with dra role$")
	public void _1_user_logs_in_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.USERPASS);
	}
	
	@And("^_1 user navigates to userdirectory and looks up a no role user$")
	public void _1_user_navigates_to_userdirectory_and_looks_up_a_no_role_user() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		String noRoleUser = "shah.raiaan+noRoles@gsa.gov";
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(noRoleUser);
		UserDirectoryPage.clickAssignRole(noRoleUser);
	}

	@Then("^_1 user gives assistance user role in assistance listing$")
	public void _1_user_gives_assistance_user_role_in_assistance_listing() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		// ---------delete the newly granted role-----------
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, "DELETE");
		Assert.assertEquals(userAlreadyHasRole, true);
		afterScenario();
	}

	 @Given("^_2 user logs in as dra$")
	    public void _2_user_logs_in_as_dra() throws Throwable {
		 SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+deptAdminSelenium@gsa.gov", Constants.USERPASS);
	    }
 @And("^_2 user navigates to Assign Role Page$")
	    public void _2_user_navigates_to_assign_role_page() throws Throwable {
	 
	 LaunchBrowserUtil.scrollAllTheWayDown();
		String noRoleUser = "shah.raiaan+noRoles@gsa.gov";
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(noRoleUser);
		UserDirectoryPage.clickAssignRole(noRoleUser);
	       
	    }
	    @Then("^_2 organziation box should show as empty$")
	    public void _2_organziation_box_should_show_as_empty() throws Throwable {
	    	Assert.assertEquals(AssignRolePage.getCurrentTextInOrgPicker().trim(), "");
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    @Given("^_3 user logs in with ra role$")
		public void _3_user_logs_in_with_ra_role() throws Throwable {
			beforeScenario();
			SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.USERPASS);
		}
		
		@And("^_3 user navigates to userdirectory and looks up a non-fed no role user$")
		public void _3_user_navigates_to_userdirectory_and_looks_up_a_nonfed_no_role_user() throws Throwable {
			LaunchBrowserUtil.scrollAllTheWayDown();
			String noRoleUser = "raiaan.shah+41@gmail.com";
			RMWidgetUtility.clickUserDirectoryLink();
			UserDirectoryPage.searchUserInUserPicker(noRoleUser);
			UserDirectoryPage.clickAssignRole(noRoleUser);
		}

		@Then("^_3 user gives data entry role in contract opportunities$")
		public void _3_user_gives_data_entry_role_in_contract_opportunities() throws Throwable {
			AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
			AssignRolePage.selectRoleIfFound(Constants.ROLE_DATA_ENTRY);
			AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
			AssignRolePage.writeComment("test");
			AssignRolePage.clickDone();
			// ---------delete the newly granted role-----------
			boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
					Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, "DELETE");
			Assert.assertEquals(userAlreadyHasRole, true);
			afterScenario();
		}
	   
	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
