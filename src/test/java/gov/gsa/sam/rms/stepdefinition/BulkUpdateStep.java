package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.BulkUpdatePage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class BulkUpdateStep {
	private static Logger logger = LoggerFactory.getLogger(BulkUpdateStep.class);

	@Given("^_1 user logs in as dra$")
	public void _1_user_logs_in_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+deptAdminSelenium@gsa.gov", Constants.userPass);
	}

	@And("^_1 user navigates to bulk update page$")
	public void _1_user_navigates_to_bulk_update_page() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		RMWidgetUtility.clickBulkUpdateLink();
	}

	@Then("^_1 org picker should show proper options$")
	public void _1_org_picker_should_show_proper_options() throws Throwable {
		BulkUpdatePage.selectOrgIfFound(Constants.ORG_GSA);
		BulkUpdatePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		BulkUpdatePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		afterScenario();
	}

	@Given("^_2 user logs in as roleadmin$")
	public void _2_user_logs_in_as_dra() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.userPass);
	}

	@And("^_2 user navigates to bulk update page$")
	public void _2_user_navigates_to_bulk_update_page() throws Throwable {
		LaunchBrowserUtil.scrolldownToRmWidget();
		RMWidgetUtility.clickBulkUpdateLink();
	}

	@Then("^_2 org picker should show proper options$")
	public void _2_org_picker_should_show_proper_options() throws Throwable {
		BulkUpdatePage.selectOrgIfFound("services");
		BulkUpdatePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		BulkUpdatePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);

		BulkUpdatePage.clickNext();
		BulkUpdatePage.clickSelectAll();
		boolean userFound = BulkUpdatePage.userFound("shah.raiaan+assistanceuserv2@gsa.gov");
		Assert.assertEquals(true, userFound);
		BulkUpdatePage.clickRemoveAccess();
		BulkUpdatePage.clickNext();
		BulkUpdatePage.writeComment("removing access");
		BulkUpdatePage.clickDone();
		
		UserDirectoryPage.searchUserInUserPicker("shah.raiaan+assistanceuserv2@gsa.gov");
		UserDirectoryPage.clickViewAccess("shah.raiaan+assistanceuserv2@gsa.gov");
		boolean userHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(false, userHasRole);
		// ------------give the role back-----------------------------------------
		RolesDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("assigning role back");
		AssignRolePage.clickDone();
		
		boolean roleFound = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);
		
		// ------sign out------
		// BulkUpdatePage.signOut();

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
