package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleInviteAssignRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class NonfedEmailStep {
	private static Logger logger = LoggerFactory.getLogger(NonfedEmailStep.class);
	private String nonfeduseremail = "";
	String timestamp = new String();

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
		RoleInviteAssignRolePage.enterBusinessJustification("sending invite");

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
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_3nre nonfed admin looks up a data entry user in contract opp and updates to viewer role$")
	public void _3nre_nonfed_admin_looks_up_a_dataentry_user_incontractopp_and_assigns_data_entry_role()
			throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		boolean rolefound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(true, rolefound);

		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("assigning new role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		boolean rolechangeoccured = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, rolechangeoccured);
		LaunchBrowserUtil.closeBrowsers();

	}

	@Then("^_3nre admin should receive an email about the role update$")
	public void _3nre_admin_should_receive_an_email_about_the_role_update() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();
		String emailBody2 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("role was updated")) {// uid for user's email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_UPDATED));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody1.contains("You have updated")) {// uid for admins email

			// asserting the email sent to admin
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_UPDATED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		}
		// --------------------------------------------------------------------
		if (emailBody2.contains("role was updated")) {

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_UPDATED));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody2.contains("You have updated")) {

			// asserting the email sent to admin
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_UPDATED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		}

		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.delay(3);
	}

	@And("^_3nre nonfed user should also receive an email about the role update$")
	public void _3nre_nonfed_user_should_also_receive_an_email_about_the_role_update() throws Throwable {
		// set the role back
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		boolean rolefound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.EDIT);
		Assert.assertEquals(true, rolefound);

		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("assigning new role");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		boolean rolechangeoccured = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, rolechangeoccured);

	}

	@Given("^_4nre spaad logs in$")
	public void _4nre_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_4nre spaad looks up a data entry user in contract opp and removes the role$")
	public void _4nre_nonfed_admin_looks_up_a_data_entry_user_in_contract_opp_and_removes_the_role() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		boolean rolefound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, rolefound);
	}

	@Then("^_4nre spaad should receive an email about the role removal$")
	public void _4nre_admin_should_receive_an_email_about_the_role_removal() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.navigateBack();

		LaunchBrowserUtil.switchTabs(3);
		// asserting the email subject line
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_REMOVED));
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true,
				emailToAndFrom1.contains(ConstantsAccounts.ROLE_ADMIN_USER_3.replace("@gsa.gov", "")));// SPAAD received
		// asserting the email body
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_REMOVED));
		Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP.toUpperCase()));
		Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
		Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
		Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

	}

	@And("^_4nre nonfed user should also receive an email about the role removal$")
	public void _4nre_nonfed_user_should_also_receive_an_email_about_the_role_removal() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES, Constants.USERPASS,
				ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("removed your")) {// uid for user's email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_REMOVED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;
		}
		Assert.assertEquals(1, counter);
		// --------------------------assign the role
		// back------------------------------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES);
		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// --------
		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(userHasRole, true);

	}

	@Given("^_5nre spaad logs in$")
	public void _5nre_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_5nre spaad looks up a no role nonfed user and assigns data entry role$")
	public void _5nre_spaad_looks_up_a_no_role_nonfed_user_and_assigns_data_entry_role() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		AssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		AssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
		// --------------------
		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.NOACTION);
		Assert.assertEquals(userHasRole, true);
	}

	@Then("^_5nre spaad should receive an email about the role assignment$")
	public void _5nre_spaad_should_receive_an_email_about_the_role_assignment() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.navigateBack();

		LaunchBrowserUtil.switchTabs(3);
		// asserting the email subject line
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true,
				emailToAndFrom1.contains(ConstantsAccounts.ROLE_ADMIN_USER_3.replace("@gsa.gov", "")));// SPAAD received
		// asserting the email body
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP.toUpperCase()));
		Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
		Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ENTITY_REGISTRATION));
		Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
	}

	@And("^_5nre nonfed user should also receive an email about the role assignment$")
	public void _5nre_nonfed_user_should_also_receive_an_email_about_the_role_assignment() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("You have been assigned")) {// uid for user's email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ENTITY_REGISTRATION));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;
		}
		Assert.assertEquals(1, counter);
		// --------------------------remove the
		// role------------------------------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.DELETE);
		Assert.assertEquals(userHasRole, true);
	}

	@Given("^_6nre no role nonfed user logs in$")
	public void _6nre_no_role_nonfed_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_6nre nonfed users requests data entry role$")
	public void _6nre_nonfed_users_requests_data_entry_role() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);
		RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(2);

		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_6nre the user should receive an email about the role request$")
	public void _6nre_the_user_should_receive_an_email_about_the_role_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("You have submitted a request")) {// uid for user's email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_SUBMITTED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ENTITY_REGISTRATION));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;
		}
		Assert.assertEquals(1, counter);
	}

	@Given("^_7nre no role nonfed user logs in$")
	public void _7nre_no_role_nonfed_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_7nre nonfed users requests data entry role in entity registration$")
	public void _7nre_nonfed_users_requests_data_entry_role_in_entity_registration() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();
		MyRolesPage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_7nre entity registration admin approves the request$")
	public void _7nre_entity_registration_admin_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);

		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(3);
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("approving role");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_7nre the admin should receive an email about the approval$")
	public void _7nre_the_admin_should_receive_an_email_about_the_approval() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();
		String emailBody2 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("You have assigned")) {// uid for admins email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));

			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ENTITY_REGISTRATION));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody1.contains("You have been assigned")) {// uid for users email

			// asserting the email sent to admin
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ENTITY_REGISTRATION));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		}
		// --------------------------------------------------------------------
		if (emailBody2.contains("You have assigned")) {

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));

			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_ENTITY_REGISTRATION));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody2.contains("You have been assigned")) {

			// asserting the email sent to admin
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_ENTITY_REGISTRATION));

			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		}

		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_7nre the user should also receive an email about the approval$")
	public void _7nre_the_user_should_also_receive_an_email_about_the_approval() throws Throwable {
// delete the assigned role
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		boolean userHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.DELETE);
		Assert.assertEquals(userHasRole, true);

	}

	@Given("^_8nre no role nonfed user logs in$")
	public void _8nre_no_role_nonfed_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_8nre nonfed users requests data entry role in entity registration$")
	public void _8nre_nonfed_users_requests_data_entry_role_in_entity_registration() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP, 0);

		boolean roleFound1 = RequestRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound1);

		boolean domainfound1 = RequestRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainfound1);
		RequestRolePage.writeComment("requesting role");
		RequestRolePage.clickSubmit();
		MyRolesPage.goToFeedsPage();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickRoleRequestFilter();
		LaunchBrowserUtil.scrollUp();
		timestamp = FeedsRequestPage.getLastRequestRequestTimestamp();
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, timestamp, Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_8nre entity registration admin rejects the request$")
	public void _8nre_entity_registration_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);

		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickReceivedOnSideNav();
		LaunchBrowserUtil.scrollAllTheWayDown();
		FeedsRequestPage.clickPendingFilter();
		LaunchBrowserUtil.scrollUp();
		LaunchBrowserUtil.delay(3);
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_ENTITY_REGISTRATION, timestamp, Constants.STATUS_PENDING,
				Constants.REJECTROLE);
		Assert.assertEquals(true, requestFound);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_8nre the admin should receive an email about the role rejection$")
	public void _8nre_the_admin_should_receive_an_email_about_the_role_rejection() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("has been rejected")) {// uid for user's email

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_REJECTED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_DATA_ENTRY));
			Assert.assertEquals(true, emailBody1.contains("Request is rejected"));// decision comment
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;
		}
		Assert.assertEquals(1, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_8nre the user should also receive an email about the role rejection$")
	public void _8nre_the_user_should_also_receive_an_email_about_the_role_rejection() throws Throwable {

	}

}
