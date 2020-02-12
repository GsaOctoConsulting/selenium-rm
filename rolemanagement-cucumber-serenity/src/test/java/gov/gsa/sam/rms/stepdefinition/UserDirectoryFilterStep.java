package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class UserDirectoryFilterStep {
	private static Logger logger = LoggerFactory.getLogger(UserDirectoryFilterStep.class);

	@Given("^_1 given user logs in as dra$")
	public void _1_given_user_logs_in_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user navigates to user directory page$")
	public void _1_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_1 user checks filter should be able to clears them$")
	public void _1_user_checks_filter_should_be_able_to_clears_them() throws Throwable {

		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickClearFilter();
		boolean assistanceUserSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);

		
		Assert.assertEquals(false, assistanceUserSelected);
	}

	@Then("^_1 user should be able to see provide feed links$")
	public void _1_user_should_be_able_to_see_provide_feed_links() throws Throwable {
		// check for provide feedback link
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
		afterScenario();
	}

	@Given("^_2 given user logs in as assistance user$")
	public void _2_given_user_logs_in_as_assistanceuser() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user navigates to user directory page$")
	public void _2_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_2 user checks filter should be able to clears them$")
	public void _2_user_checks_filter_should_be_able_to_clears_them() throws Throwable {
		UserDirectoryPage.clickAdminFilter();;
		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickClearFilter();

		boolean adminfilterSelected = UserDirectoryPage.isFilterSelected(UserDirectoryPageLocator.ADMIN_FILTER);
		boolean assistanceUserFilterSelected = UserDirectoryPage
				.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);

		Assert.assertEquals(adminfilterSelected, false);
		Assert.assertEquals(assistanceUserFilterSelected, false);
	}

	@Then("^_2 user should be able to see provide feed links$")
	public void _2_user_should_be_able_to_see_provide_feed_links() throws Throwable {
		// check for provide feedback link
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
		afterScenario();
	}

	@Given("^_3 given user logs in as role administrator$")
	public void _3_given_user_logs_in_as_roleadministrator() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 user navigates to user directory page$")
	public void _3_user_navigates_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.scrollAllTheWayDown();
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@Then("^_3 user checks filter should be able to clears them$")
	public void _3_user_checks_filter_should_be_able_to_clears_them() throws Throwable {
		UserDirectoryPage.clickAssistanceUserFilter();
		UserDirectoryPage.clickAdminFilter();
		UserDirectoryPage.clickClearFilter();

		boolean assistanceUserfilterSelected = UserDirectoryPage.isFilterSelected(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER);
		boolean adminFilterSelected = UserDirectoryPage.isFilterSelected(UserDirectoryPageLocator.ADMIN_FILTER);

		Assert.assertEquals(assistanceUserfilterSelected, false);
		Assert.assertEquals(adminFilterSelected, false);
	}

	@Then("^_3 user should be able to see provide feed links$")
	public void _3_user_should_be_able_to_see_provide_feed_links() throws Throwable {
		// check for provide feedback link
		boolean providefeedbackLinkFound = UserDirectoryPage
				.elementFound(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK);
		Assert.assertEquals(providefeedbackLinkFound, true);
		afterScenario();
	}

	
	  @Given("^_4udf given user logs in as assitance admin administrator$")
	    public void _4udf_given_user_logs_in_as_assitance_admin_administrator() throws Throwable {
	       
	    }

	    @Then("^_4udf user checks filter should be able to clears them$")
	    public void _4udf_user_checks_filter_should_be_able_to_clears_them() throws Throwable {
	        
	    }

	    @Then("^_4udf user should be able to see provide feed links$")
	    public void _4udf_user_should_be_able_to_see_provide_feed_links() throws Throwable {
	        
	    }

	    @And("^_4udf user navigates to user directory page$")
	    public void _4udf_user_navigates_to_user_directory_page() throws Throwable {
	       
	    }
	
	
	
	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
