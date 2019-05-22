package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.RolesDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class EmailStep {

	private static Logger logger = LoggerFactory.getLogger(EmailStep.class);

	@Given("^_1 a no role user logs$")
	public void _1_a_no_role_user_logs() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user requests assitance user role in assistance listing$")
	public void _1_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.GMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		CommonMethods.delay(2);
	}

	@Then("^_1 user should receive an email with the proper message$")
	public void _1_user_should_receive_an_email_with_the_proper_message() throws Throwable {
		if (Constants.ACTIVE_ENV_URL.equals(Constants.LOGINGOV_HOME_PAGE)) {
			LaunchBrowserUtil.goToMailInbox("shah.raiaan@gsa.gov", "password", Constants.USER_FED);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}

		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		// asserting email subject
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ACTION_SUBMITTED));
		Assert.assertEquals(true, emailSubject.contains(Constants.ROLE_ASSISTANCE_USER));

		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));

		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ACTION_SUBMITTED));
		Assert.assertEquals(true,emailBody.contains(Constants.ORG_GSA));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING));

		// delete the request
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
		CommonMethods.delay(6);
	}

	@Then("^_1 supervisor should also receive an email message$")
	public void _1_supervisor_should_also_receive_an_email_message() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);

		if (Constants.ACTIVE_ENV_URL.equals(Constants.LOGINGOV_HOME_PAGE)) {
			LaunchBrowserUtil.goToMailInbox("raiaan.shah@gmail.com", Constants.USERPASS_NONFED, Constants.USER_NONFED);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}

		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		// asserting the email subject line
		Assert.assertEquals( true,emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ACTION_PENDING));
		Assert.assertEquals(true,emailSubject.contains(Constants.ROLE_ASSISTANCE_USER));

		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));

		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.ORG_GSA));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING));

	}

	@Given("^_2 a no role user logs in$")
	public void _2_a_no_role_user_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user requests assistance user role in assistance listing$")
	public void _2_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("a@b.c");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		CommonMethods.delay(2);
	}

	@When("^_2 assistance admin approves the request$")
	public void _2_assistance_admin_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("assign");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_2 assistance admin should receive email message$")
	public void _2_assistance_admin_should_receive_email_message() throws Throwable {
		if (Constants.ACTIVE_ENV_URL.equals(Constants.LOGINGOV_HOME_PAGE)) {
			LaunchBrowserUtil.goToMailInbox("shah.raiaan@gsa.gov", "password", Constants.USER_FED);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}
		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		
		// asserting the email subject line
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailSubject.contains(Constants.ROLE_ASSISTANCE_USER));

		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true,
				emailToAndFrom.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));

		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.ORG_GSA));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
	}

	@Then("^_2 the requester should also receive an email message$")
	public void _2_the_requester_should_also_receive_an_email_message() throws Throwable {
		LaunchBrowserUtil.navigateBack();
		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ACTION_APPROVED));

		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true, emailToAndFrom.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));

		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.ORG_GSA));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING));

		// ----------------delete the role for the user---------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		CommonMethods.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(true,userAlreadyHasRole);

		// delete the role for the user
		userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		CommonMethods.delay(4);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_3 assistance admin logs into workspace$")
	public void _3_assistance_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 assistance admin looks up a no role user through the user directory$")
	public void _3_assistance_admin_looks_up_a_no_role_user_through_the_user_directory() throws Throwable {
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@And("^_3 assistance admin gives assistance user role to this user$")
	public void _3_assistance_admin_gives_assistance_user_role_to_this_user() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment("test");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_3 assistance admin should receive proper email message$")
	public void _3_assistance_admin_should_receive_proper_email_message() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailTitle = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_ASSIGNED), true);

		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailBody.contains(Constants.ORG_GSA), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);
		Assert.assertEquals(emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING), true);

	}

	@And("^_3 assistance admin removes the role$")
	public void _3_assistance_admin_removes_the_role() throws Throwable {
		// ----------------remove the role-----------------------------------
		MyRolesPage.setDriver(MyWorkspacePage.getDriver());
		MyRolesPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING,
				Constants.DELETE);
	}

	@Then("^_3 assistance admin should receive role remove email$")
	public void _3_assistance_admin_should_receive_role_remove_email() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailTitle = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_REMOVED), true);
		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_ACTION_REMOVED), true);
		Assert.assertEquals(emailBody.toLowerCase().contains(Constants.ORG_GSA.toLowerCase()), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);

	}

	@When("^_3 the user logs in$")
	public void _3_the_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_3 the user should have received a role assign email$")
	public void _3_the_user_should_have_received_a_role_assign_email() throws Throwable {
		if (Constants.ACTIVE_ENV_URL.equals(Constants.LOGINGOV_HOME_PAGE)) {
			LaunchBrowserUtil.goToMailInbox("shah.raiaan@gsa.gov", "password", Constants.USER_FED);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}
		String emailTitle = LaunchBrowserUtil.captureTitleFromLastEmail(4);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(4);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_ASSIGNED), true);

		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_ASSIGNED), true);
		Assert.assertEquals(emailBody.contains(Constants.ORG_GSA), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);
		Assert.assertEquals(emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING), true);

	}

	@And("^_3 the user should have received a role removed email$")
	public void _3_the_user_should_have_received_a_role_removed_email() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailTitle = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(2);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);
		// asserting the email subject line
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_REMOVED), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_ACTION_REMOVED), true);
		Assert.assertEquals(emailBody.toLowerCase().contains(Constants.ORG_GSA.toLowerCase()), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);

	}
	// ---------------------------------------------

	@Given("^_4 user logs in workspace as assistance admin$")
	public void _4_user_logs_in_workspace_as_assistance_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_4 admin looks up assistance user account in user directory$")
	public void _4_admin_looks_up_assistance_user_account_in_user_directory() throws Throwable {
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
	}

	@And("^_4 admin changes the users org to office of acquisition policy$")
	public void _4_admin_changes_the_users_org_to_office_of_acquisition_policy() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole = RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.EDIT);
		Assert.assertEquals(userAlreadyHasRole, true);
		// --------------------------------------------------
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.writeComment("adding organization");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_4 assistance admin should receive proper email message$")
	public void _4_assistance_admin_should_receive_proper_email_message() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailTitle = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		// asserting the email subject line
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_UPDATED), true);

		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_ACTION_UPDATED), true);
		Assert.assertEquals(
				emailBody.toLowerCase().contains(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY.toLowerCase()), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);
		Assert.assertEquals(emailBody.contains(Constants.DOMAIN_ASSISTANCE_LISTING), true);
	}

	@Then("^_4 the assistance user should also receive proper email message$")
	public void _4_the_assistance_user_should_also_receive_proper_email_message() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailTitle = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailTitle.contains(Constants.EMAIL_ACTION_UPDATED), true);
		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);
		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_ACTION_UPDATED), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);
		// -------------------------change the role back------------------------
		RolesDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);

	}

	@Given("^_5 a no role user logs in$")
	public void _5_a_no_role_user_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);

	}

	@And("^_5email_user requests assitance user role in assistance listing$")
	public void _5_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		MyWorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		CommonMethods.delay(2);
	}

	@When("^_5 assistance admin rejects the request$")
	public void _5_assistance_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		RMWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		MyRolesPage.writeAdditionalInformation("rejecting this request");
		RoleRequestPendingPage.clickRejectButton();
	}

	@Then("^_5 supervisor should receive email message$")
	public void _5_supervisor_should_receive_email_message() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailSubject.contains(Constants.EMAIL_ACTION_REJECTED), true);

		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailSubject.contains(Constants.EMAIL_ACTION_REJECTED), true);
		Assert.assertEquals(emailSubject.contains(Constants.ROLE_ASSISTANCE_USER), true);
		Assert.assertEquals(emailSubject.contains(ConstantsAccounts.NO_ROLE_USER_2), true);
	}

	@Then("^_5 the requester should also receive an email message$")
	public void _5_the_requester_should_also_receive_an_email_message() throws Throwable {
		SignInUtility.signIntoCommonWorkspacePage(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS);
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(2);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);
		// asserting the email subject line
		Assert.assertEquals(emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
		Assert.assertEquals(emailSubject.contains(Constants.EMAIL_ACTION_REJECTED), true);

		// asserting email to and from address
		Assert.assertEquals(emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);

		// asserting the email body
		Assert.assertEquals(emailBody.contains(Constants.EMAIL_REQUESTOR_NAME), true);
		Assert.assertEquals(emailSubject.contains(Constants.EMAIL_ACTION_REJECTED), true);
		Assert.assertEquals(emailBody.contains(Constants.ROLE_ASSISTANCE_USER), true);
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}