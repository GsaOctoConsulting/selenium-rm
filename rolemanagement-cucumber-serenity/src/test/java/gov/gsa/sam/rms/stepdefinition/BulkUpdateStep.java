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
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class BulkUpdateStep {
	private static Logger logger = LoggerFactory.getLogger(BulkUpdateStep.class);

	@Given("^_1 user logs in as dra$")
	public void _1_user_logs_in_as_dra() throws Throwable {
		beforeScenario();

		SignInUtility.signIntoWorkspace(ConstantsAccounts.DEPT_ROLEADMIN_2, Constants.USERPASS,
				ConstantsAccounts.DEPT_ROLEADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user navigates to bulk update page$")
	public void _1_user_navigates_to_bulk_update_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickBulkUpdateLink();
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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user navigates to bulk update page$")
	public void _2_user_navigates_to_bulk_update_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickBulkUpdateLink();
	}

	@Then("^_2 org picker should show proper options$")
	public void _2_org_picker_should_show_proper_options() throws Throwable {
		BulkUpdatePage.selectOrgIfFound(Constants.ORG_GSA);

		BulkUpdatePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		BulkUpdatePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);

		BulkUpdatePage.clickNext();
		BulkUpdatePage.clickSelectAll();
		boolean userFound = BulkUpdatePage.userFound(ConstantsAccounts.ASSISTANCE_USER_2);
		Assert.assertEquals(true, userFound);
		BulkUpdatePage.clickRemoveAccess();
		BulkUpdatePage.clickNext();
		/*BulkUpdatePage.writeComment("removing access");
		BulkUpdatePage.clickDone();

		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(false, userHasRole);
		// ------------give the role back-----------------------------------------
		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("assigning role back");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		boolean roleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.NOACTION);
		Assert.assertEquals(true, roleFound);*/

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
