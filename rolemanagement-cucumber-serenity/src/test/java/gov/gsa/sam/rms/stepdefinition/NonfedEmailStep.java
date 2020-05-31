package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleInviteAssignRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class NonfedEmailStep {
	private static Logger logger = LoggerFactory.getLogger(EmailStep.class);
	private String nonfeduseremail = "";

	@Given("^_1nfemail a no role user logs$")
	public void _1_a_no_role_user_logs() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1nfemail user requests assitance user role in assistance listing$")
	public void _1_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.EMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(2);
	}

	@Then("^_1nfemail user should receive an email with the proper message$")
	public void _1_user_should_receive_an_email_with_the_proper_message() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.navigateBack();
		LaunchBrowserUtil.switchTabs(3);

		// asserting email subject
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ACTION_SUBMITTED));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ENV));

		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true, emailToAndFrom.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));

		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ACTION_SUBMITTED));
		Assert.assertEquals(true, emailBody.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
		Assert.assertEquals(true, emailBody.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ENV));

		// here link asserts

		// delete the request
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_1nfemail supervisor should also receive an email message$")
	public void _1_supervisor_should_also_receive_an_email_message() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody = LaunchBrowserUtil.captureEmailContentNonfed();
		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ACTION_REQUESTED));
		Assert.assertEquals(true, emailBody.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
		Assert.assertEquals(true, emailBody.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ENV));

		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_2nre nonfed admin logs in$")
	public void _2nre_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_2nre nonfed admin navigates to role invite page$")
	public void _2nre_nonfed_admin_navigates_to_role_invite_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_2nre admin invites a nonfed unregistered user for viewer role in the admins domain$")
	public void _2nre_admin_invites_a_nonfed_unregistered_user_for_viewer_role_in_the_admins_domain() throws Throwable {
		String counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";

		RoleInviteAssignRolePage.enterEmailAddress(nonfeduseremail);
		boolean roleFound = RoleInviteAssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, roleFound);

		boolean domainFound = RoleInviteAssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainFound);

		boolean entityFound = RoleInviteAssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP,
				0);
		Assert.assertEquals(true, entityFound);
		RoleInviteAssignRolePage.enterAdditionalInformation("sending invite");

		RoleInviteAssignRolePage.clickSendInvitationButton();
		RoleInviteAssignRolePage.clickCloseButton();
		LaunchBrowserUtil.closeBrowsers();

	}

	@Then("^_2nre admin should receive an email about the role invite$")
	public void _2nre_admin_should_receive_an_email_about_the_role_invite() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();
		String emailBody2 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("You have been invited to accept a role")) {// uid for user's email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_INVITED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_VIEWER));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody1.contains("You have sent a role invitation")) {// uid for admins email

			// asserting the email sent to admin
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_SENT));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_VIEWER));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		}
		// --------------------------------------------------------------------
		if (emailBody2.contains("You have been invited to accept a role")) {

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_INVITED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_VIEWER));

			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody2.contains("You have sent a role invitation")) {

			// asserting the email sent to admin
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_SENT));
			Assert.assertEquals(true, emailBody2.contains(nonfeduseremail));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_VIEWER));

			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		}

		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_2nre the unregistered user should also get an email about the role invite$")
	public void _2nre_the_unregistered_user_should_also_get_an_email_about_the_role_invite() throws Throwable {

	}

	@Given("^_3nre nonfed admin logs in$")
	public void _3nre_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYCOMPLIANCE, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYCOMPLIANCE_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_3nre nonfed admin looks up a data entry user in contract opp and assigns data entry in admins own domain$")
	public void _3nre_nonfed_admin_looks_up_a_dataentry_user_incontractopp_and_assigns_data_entry_role() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_USER_2_NO_ROLES);
	}

	@Then("^_3nre admin should receive an email about the role update$")
	public void _3nre_admin_should_receive_an_email_about_the_role_update() throws Throwable {

	}

	@And("^_3nre nonfed user should also receive an email about the role update$")
	public void _3nre_nonfed_user_should_also_receive_an_email_about_the_role_update() throws Throwable {

	}

}
