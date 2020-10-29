package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.RolesDirectoryViewAccessLocator;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class RaBusinessRulesStep {
	private static Logger logger = LoggerFactory.getLogger(RaBusinessRulesStep.class);

	String raUser = "shah.raiaan+ra3@gsa.gov";
	
	
	@Given("^_1 roleadmin logs into workspace$")
    public void _1_role_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
    }

    @When("^_1 role admin looks up another ra$")
    public void _1_role_admin_looks_up_another_ra() throws Throwable {
    	LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ROLE_ADMIN_USER_4);
		UserDirectoryPage.clickViewAccessOnly((ConstantsAccounts.ROLE_ADMIN_USER_4));
		
		
    }

    @Then("^_1 assign role button should not be there$")
    public void _1_assign_role_button_should_not_be_there() throws Throwable {
     boolean assignRoleButtonFound= UserDirectoryViewAccessPage.elementFound(RolesDirectoryViewAccessLocator.ASSIGN_ROLE_BUTTON);  
     Assert.assertEquals(false, assignRoleButtonFound);
    }
	
    
    
    @Given("^_2 roleadmin logs into workspace$")
    public void _2_role_admin_logs_into_workspace() throws Throwable {
    	beforeScenario();
    	SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
    }

    @When("^_2 role admin looks up nonfed user through user directory$")
    public void _2_role_admin_looks_up_nonfed_user_through_user_directory() throws Throwable {
    	LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_1);
		
    }

    @Then("^_2 role admin should be able to view access$")
    public void _2_role_admin_should_be_able_to_view_access() throws Throwable {
    	UserDirectoryPage.clickViewAccess((ConstantsAccounts.NONFED_USER_1));  
    }
	
	

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
