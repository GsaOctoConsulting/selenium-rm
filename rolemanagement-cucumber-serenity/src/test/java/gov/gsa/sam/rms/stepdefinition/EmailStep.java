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
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class EmailStep {

	private static Logger logger = LoggerFactory.getLogger(EmailStep.class);
	private static String decisionComment = "comments written here";

	@Given("^_1 a no role user logs$")
	public void _1_a_no_role_user_logs() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_1 user requests assitance user role in assistance listing$")
	public void _1_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.EMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,0);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(2);
	}

	@Then("^_1 user should receive an email with the proper message$")
	public void _1_user_should_receive_an_email_with_the_proper_message() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();

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

		

		// delete the request
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_1 supervisor should also receive an email message$")
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

	@Given("^_2 a no role user logs in$")
	public void _2_a_no_role_user_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_2 user requests assistance user role in assistance listing$")
	public void _2_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail("a@b.c");
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,0);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(2);
	}

	@When("^_2 assistance admin approves the request$")
	public void _2_assistance_admin_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment(decisionComment);
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_2 assistance admin should receive an email message$")
	public void _2_assistance_admin_should_receive_email_message() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();

		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();
		
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(2);
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();
		
		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", ""))) {// assistance
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV)); // admin
			// received
			
			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject1.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody1.contains(decisionComment));

			counter++;
		}

		else if (emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))) {// requester
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		}
//----------------------------------
		if (emailToAndFrom2.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", ""))) {// assistance
																											// admin
																											// received
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject2.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody2.contains(decisionComment));
			counter++;
		}

		else if (emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		}
//----------------------------------		
		if (emailToAndFrom3.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", ""))) {// assistance
																											// admin
																											// received
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject3.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody3.contains(decisionComment));
			counter++;
		}

		else if (emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_ASSISTANCE_USER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));
			counter++;
		}
		Assert.assertEquals(3, counter);
	}

	@Then("^_2 the requester should also receive two email messages$")
	public void _2_the_requester_should_also_receive_an_email_message() throws Throwable {

		// ----------------delete the role for the user---------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		LaunchBrowserUtil.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);

		// delete the role for the user
		userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		LaunchBrowserUtil.delay(4);
		afterScenario();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_3 assistance admin logs into workspace$")
	public void _3_assistance_admin_logs_into_workspace() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_3 assistance admin looks up a no role user through the user directory$")
	public void _3_assistance_admin_looks_up_a_no_role_user_through_the_user_directory() throws Throwable {
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickAssignRole(ConstantsAccounts.NO_ROLE_USER_2);
	}

	@And("^_3 assistance admin gives assistance user role to this user$")
	public void _3_assistance_admin_gives_assistance_user_role_to_this_user() throws Throwable {
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		AssignRolePage.writeComment(decisionComment);
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_3 assistance admin should receive proper email message$")
	public void _3_assistance_admin_should_receive_proper_email_message() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();

		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();
		LaunchBrowserUtil.switchTabs(3);
		// asserting the email subject line
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true,
				emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));// admin
		// received
		// asserting the email body
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
		Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_DEPT));
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

		// asserting the email subject line
		Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true, emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));// user
																														// received
		// asserting the email body
		Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));
		Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
		Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_DEPT));
		Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

	}

	@And("^_3 the user should also receive proper email message$")
	public void _3_assistance_admin_removes_the_role() throws Throwable {
		// ------remove the role----------------
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);
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
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.ASSISTANCE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.ASSISTANCE_USER_2);
	}

	@And("^_4 admin changes the users org to office of acquisition policy$")
	public void _4_admin_changes_the_users_org_to_office_of_acquisition_policy() throws Throwable {
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
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

		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);
		String emailTitle1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();
		String emailTitle2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", ""))) {// admin
			// asserting the email subject line
			Assert.assertEquals(emailTitle1.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
			Assert.assertEquals(emailTitle1.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(true, emailTitle1.contains(Constants.EMAIL_ENV));

			// asserting email to and from address
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));

			// asserting the email body
			Assert.assertEquals(emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME), true);
			Assert.assertEquals(emailBody1.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(emailBody1.toLowerCase().contains(Constants.ORG_GSA.toLowerCase()), true);
			Assert.assertEquals(emailBody1.contains(Constants.ROLE_ASSISTANCE_USER), true);
			Assert.assertEquals(emailBody1.contains(Constants.DOMAIN_ASSISTANCE_LISTING), true);
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_DEPT));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_USER_2.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(emailTitle1.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
			Assert.assertEquals(emailTitle1.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(true, emailTitle1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.ASSISTANCE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME), true);
			Assert.assertEquals(emailBody1.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(emailBody1.contains(Constants.ROLE_ASSISTANCE_USER), true);
			Assert.assertEquals(emailBody1.contains(Constants.EMAIL_PERMISSIONS), true);
			counter++;
		}
		// -------------------------------------------------------------------------
		if (emailToAndFrom2.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", ""))) {// admin
			// asserting the email subject line
			Assert.assertEquals(emailTitle2.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
			Assert.assertEquals(emailTitle2.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(true, emailTitle2.contains(Constants.EMAIL_ENV));

			// asserting email to and from address
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2.replace("@gsa.gov", "")));

			// asserting the email body
			Assert.assertEquals(emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME), true);
			Assert.assertEquals(emailBody2.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(emailBody2.toLowerCase().contains(Constants.ORG_GSA.toLowerCase()), true);
			Assert.assertEquals(emailBody2.contains(Constants.ROLE_ASSISTANCE_USER), true);
			Assert.assertEquals(emailBody2.contains(Constants.DOMAIN_ASSISTANCE_LISTING), true);
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_DEPT));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.ASSISTANCE_USER_2.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(emailTitle2.contains(Constants.EMAIL_REGULAR_SENT_FROM), true);
			Assert.assertEquals(emailTitle2.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(true, emailTitle2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.ASSISTANCE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME), true);
			Assert.assertEquals(emailBody2.contains(Constants.EMAIL_ACTION_UPDATED), true);
			Assert.assertEquals(emailBody2.contains(Constants.ROLE_ASSISTANCE_USER), true);
			Assert.assertEquals(emailBody2.contains(Constants.EMAIL_PERMISSIONS), true);
			counter++;
		}

	}

	@Then("^_4 the assistance user should also receive proper email message$")
	public void _4_the_assistance_user_should_also_receive_proper_email_message() throws Throwable {

		// -------------------------change the role back------------------------
		UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA_OFFICE_OF_ACQUISITION_POLICY,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();

	}

	@Given("^_5 a no role user logs in$")
	public void _5_a_no_role_user_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);

	}

	@And("^_5email_user requests assitance user role in assistance listing$")
	public void _5_user_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.EMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,0);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5 assistance admin rejects the request$")
	public void _5_assistance_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		MyRolesPage.writeAdditionalInformation(decisionComment);
		RoleRequestPendingPage.clickRejectButton();
		LaunchBrowserUtil.delay(3);
	}

	@Then("^_5 the requester should receive an email message$")
	public void _5_the_requester_should_also_receive_an_email_message() throws Throwable {

		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);
		String emailSubject = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();

		// asserting the email subject line
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_REGULAR_SENT_FROM));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ACTION_REJECTED));
		Assert.assertEquals(true, emailSubject.contains(Constants.EMAIL_ENV));

		// asserting email to and from address
		Assert.assertEquals(true, emailToAndFrom.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
		Assert.assertEquals(true, emailToAndFrom.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));

		// asserting the email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ACTION_REJECTED));
		Assert.assertEquals(true, emailBody.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailBody.contains(decisionComment));
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_ENV));

		//
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_5 supervisor should also receive email message$")
	public void _5_supervisor_should_receive_email_message() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);
		String emailContent = LaunchBrowserUtil.captureEmailContentNonfed();

		// asserting the email body
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_ACTION_REJECTED));
		Assert.assertEquals(true, emailContent.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailContent.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailContent.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailContent.contains(Constants.DOMAIN_ASSISTANCE_LISTING));

		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_SUPERVISOR));
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_6email a no role user logs in$")
	public void _6email_a_no_role_user_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_6email_user requests assitance user role in assistance listing$")
	public void _6emailuser_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.EMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,0);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_6email assistance admin approves the request$")
	public void _6email_assistance_admin_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment("assign");
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_6email supervisor should receive email message regarding the approval$")
	public void _6email_supervisor_should_receive_email_message_regarding_the_approval() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailContent = LaunchBrowserUtil.captureEmailContentNonfed();

		// asserting the email body
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_ACTION_APPROVED));
		Assert.assertEquals(true, emailContent.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailContent.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailContent.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailContent.contains(Constants.DOMAIN_ASSISTANCE_LISTING));

		Assert.assertEquals(true, emailContent.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_ENV));

		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_SUPERVISOR));
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();

		// ----------------delete the role for the user---------------------
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		LaunchBrowserUtil.delay(2);
		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_ASSISTANCE_USER, Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);

		// delete the role for the user
		userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA, Constants.ROLE_ASSISTANCE_USER,
				Constants.DOMAIN_ASSISTANCE_LISTING, Constants.DELETE);
		LaunchBrowserUtil.delay(4);
		afterScenario();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();

	}

	@Given("^_7email a no role user logs in$")
	public void _7email_a_no_role_user_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_7email_user requests assitance user role in assistance listing$")
	public void _7emailuser_requests_assitance_user_role_in_assistance_listing() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.EMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,0);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_ASSISTANCE_USER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_7email assistance admin rejects the request$")
	public void _7email_assistance_admin_rejects_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		MyRolesPage.writeAdditionalInformation("rejecting this request");
		RoleRequestPendingPage.clickRejectButton();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_7email supervisor should receive email message regarding the rejection$")
	public void _7email_supervisor_should_receive_email_message_regarding_the_rejection() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailContent = LaunchBrowserUtil.captureEmailContentNonfed();

		// asserting the email body
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_ACTION_REJECTED));
		Assert.assertEquals(true, emailContent.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailContent.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailContent.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailContent.contains(Constants.DOMAIN_ASSISTANCE_LISTING));

		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_SUPERVISOR));
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_8 a no role user logs$")
	public void _8_a_no_role_user_logs() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_8 user requests assitance user role in assistance listing through workspace page$")
	public void _8_user_requests_assitance_user_role_in_assistance_listing_from_workspace() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		boolean roleFound = T1WorkspacePage.selectRoleForRoleRequest(Constants.ROLE_ASSISTANCE_USER);
		Assert.assertEquals(true, roleFound);
		T1WorkspacePage.clickVerifyBusineesNeedCheckbox();
		T1WorkspacePage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(Constants.EMAIL_NONFED);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,0);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_ASSISTANCE_LISTING);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		// delete the request
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickDeleteButton();
		RoleRequestPendingPage.confirmDeleteOnPopup();
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_8 supervisor should also receive an email message$")
	public void _8_supervisor_should_receive_an_email_message() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);
		String emailContent = LaunchBrowserUtil.captureEmailContentNonfed();

		// asserting the email body
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_REQUESTOR_NAME));
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_ACTION_REQUESTED));
		Assert.assertEquals(true, emailContent.contains(Constants.ORG_GSA.toUpperCase()));
		Assert.assertEquals(true, emailContent.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailContent.contains(Constants.ROLE_ASSISTANCE_USER));
		Assert.assertEquals(true, emailContent.contains(Constants.DOMAIN_ASSISTANCE_LISTING));
		Assert.assertEquals(true, emailContent.contains(Constants.CODE_ORG_GSA_SUBTIER));
		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_ENV));

		Assert.assertEquals(true, emailContent.contains(Constants.EMAIL_SUPERVISOR));
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();

	}

	@Given("^_9 a no role user logs in$")
	public void _9_a_no_role_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@And("^_9 user requests contracting officer role in contract opportunities$")
	public void _9_user_requests_contracting_officer_role_in_contract_opportunities() throws Throwable {
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.setDriver(AccountDetailsPage.getDriver());
		MyRolesPage.clickRequestRoleButton();
		RequestRolePage.writeSupervisorName("AJ");
		RequestRolePage.writeSupervisorEmail(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1);
		RequestRolePage.selectOrgIfFound(Constants.ORG_GSA,1);
		RequestRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		RequestRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		RequestRolePage.writeComment("test");
		RequestRolePage.clickSubmit();
		LaunchBrowserUtil.delay(2);
	}

	@When("^_9 contract opportunities admin who is also the supervisor approves the request$")
	public void _9_contract_opportunities_admin_who_is_also_the_supervisor_approves_the_request() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NO_ROLE_USER_2);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NO_ROLE_USER_2);
		MyRolesPage.click1PendingRequest();
		MyRolesPage.clickPendingLink();
		RoleRequestPendingPage.clickAssignRole();
		AssignRolePage.writeComment(decisionComment);
		AssignRolePage.clickAssign();
		AssignRolePage.clickCloseButton();
	}

	@Then("^_9 contract opportunities admin should receive an email message$")
	public void _9_contract_opportunities_admin_should_receive_an_email_message() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();

		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(2);
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();
		String emailSubject4 = LaunchBrowserUtil.captureTitleFromLastEmail(3);
		String emailBody4 = LaunchBrowserUtil.captureEmailMessage(3);
		String emailToAndFrom4 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();

		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (!emailSubject1.contains("A role"))) {// admin
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV)); // admin
			// received

			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject1.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody1.contains(decisionComment));

			counter++;
		}

		else if (emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (!emailSubject1.contains("SAM.gov"))) {// requester
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (emailSubject1.contains("A role"))) {// admin who is also supervisor receiving supervisor email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SUPERVISOR));

			counter++;
		}
		// ----------------------------------
		if (emailToAndFrom2.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (!emailSubject2.contains("A role"))) {// assistance
			// admin
			// received
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject2.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody2.contains(decisionComment));
			counter++;
		}

		else if (emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (!emailSubject2.contains("SAM.gov"))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (emailSubject2.contains("A role"))) {// admin who is also supervisor receiving supervisor email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SUPERVISOR));

			counter++;
		}
		// ----------------------------------
		if (emailToAndFrom3.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (!emailSubject3.contains("A role"))) {// assistance
			// admin
			// received
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject3.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody3.contains(decisionComment));
			counter++;
		}

		else if (emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (!emailSubject3.contains("SAM.gov"))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom3.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (emailSubject3.contains("A role"))) {// admin who is also supervisor receiving supervisor email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom3.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom3.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody3.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody3.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody3.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody3.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_SUPERVISOR));

			counter++;
		}
		// ------------------------
		if (emailToAndFrom4.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (!emailSubject4.contains("A role"))) {// assistance
			// admin
			// received
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom4.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom4.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody4.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody4.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody4.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody4.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom4.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (emailSubject4.contains("SAM.gov"))) {// requester receiving approval email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom4.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom4.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody4.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody4.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody4.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody4.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_APPROVED_ORGANIZATIONS));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_APPROVED_ROLE));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_APPROVED_DOMAINS));
			Assert.assertEquals(true, emailBody4.contains(decisionComment));
			counter++;
		}

		else if (emailToAndFrom4.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", ""))
				&& (!emailSubject4.contains("SAM.gov"))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom4.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom4.contains(ConstantsAccounts.NO_ROLE_USER_2.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ACTION_ASSIGNED));
			Assert.assertEquals(true, emailBody4.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody4.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody4.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody4.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ENV));
			counter++;
		} else if (emailToAndFrom4.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", ""))
				&& (emailSubject4.contains("A role"))) {// admin who is also supervisor receiving supervisor email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_REGULAR_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_ENV));
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom4.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom4.contains(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1.replace("@gsa.gov", "")));
			// asserting the email body
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ACTION_APPROVED));
			Assert.assertEquals(true, emailBody4.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody4.contains(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER));
			Assert.assertEquals(true, emailBody4.contains(Constants.DOMAIN_CONTRACT_OPPORTUNITIES));
			Assert.assertEquals(true, emailBody4.contains(Constants.CODE_ORG_GSA_SUBTIER));
			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_ENV));

			Assert.assertEquals(true, emailBody4.contains(Constants.EMAIL_SUPERVISOR));

			counter++;
		}
		Assert.assertEquals(4, counter);
	}

	@Then("^_9 the requester should also receive two email messages$")
	public void _9_the_requester_should_also_receive_two_email_messages() throws Throwable {
		// ----------------delete the role for the user---------------------

		// check whether user already has the role
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);

		LaunchBrowserUtil.delay(4);
		afterScenario();
		LaunchBrowserUtil.delay(3);
		// LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_10 contracting data admin logs into workspace$")
	public void _10_contracting_data_admin_logs_into_workspace() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@And("^_10 admin looks up a user with contracting specialist role in contract data$")
	public void _10_admin_looks_up_a_user_with_contracting_specialist_role_in_contract_data() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1);
	}

	@When("^_10 admin removes the users role$")
	public void _10_admin_removes_the_users_role() throws Throwable {
		boolean userAlreadyHasRole = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_GSA,
				Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR, Constants.DOMAIN_CONTRACT_DATA, Constants.DELETE);
		Assert.assertEquals(true, userAlreadyHasRole);
	}

	@Then("^_10 contracting data admin should receive proper email message$")
	public void _10_assistance_admin_should_receive_proper_email_message() throws Throwable {
		LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);

		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();
		LaunchBrowserUtil.navigateBack();

		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.verifyLinkInEmailIfFound();

		LaunchBrowserUtil.navigateBack();
		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailSubject1.contains("You removed role")) {// uid for admin receiving email
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.CONTRACT_DATA_ADMIN_1.replace("@gsa.gov", "")));// admin
			// received
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_REMOVED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR));
			Assert.assertEquals(true, emailBody1.contains(Constants.DOMAIN_CONTRACT_DATA));
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_DEPT));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;
		} else if (emailSubject1.contains("removed your role")) {// uid for user receiving email

			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true, emailToAndFrom1
					.contains(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1.replace("@gsa.gov", "")));// user
			// received
			// asserting the email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_REMOVED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR));
			Assert.assertEquals(false, emailBody1.contains(Constants.DOMAIN_CONTRACT_DATA)); // user does not see domain
																								// currently
			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_GSA_DEPT));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;
		}

		// -----------------------------------------------

		if (emailSubject2.contains("You removed role")) {// uid for admin receiving email
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.CONTRACT_DATA_ADMIN_1.replace("@gsa.gov", "")));// admin
			// received
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_REMOVED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR));
			Assert.assertEquals(true, emailBody2.contains(Constants.DOMAIN_CONTRACT_DATA));
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_DEPT));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			counter++;
		} else if (emailSubject2.contains("removed your role")) {// uid for user receiving email
			// asserting email to and from address
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_REGULAR_SENT_FROM_DOMAIN));
			Assert.assertEquals(true, emailToAndFrom2
					.contains(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1.replace("@gsa.gov", "")));// user
			// received
			// asserting the email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_REQUESTOR_NAME));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ACTION_REMOVED));
			Assert.assertEquals(true, emailBody2.contains(Constants.ORG_GSA.toUpperCase()));
			Assert.assertEquals(true, emailBody2.contains(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR));
			Assert.assertEquals(false, emailBody2.contains(Constants.DOMAIN_CONTRACT_DATA)); // user does not see domain
																								// currently
			Assert.assertEquals(true, emailBody2.contains(Constants.CODE_ORG_GSA_DEPT));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));

			counter++;
		}
		Assert.assertEquals(2, counter);

	}

	@And("^_10 the user should also receive proper email message$")
	public void _10_the_user_should_also_receive_proper_email_message() throws Throwable {
		// assign the role back
		UserDirectoryViewAccessPage.clickAssignRole();
		AssignRolePage.selectOrgIfFound(Constants.ORG_GSA, 0);
		AssignRolePage.selectRoleIfFound(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		LaunchBrowserUtil.scrollToMiddle();
		AssignRolePage.selectDomainIfFound(Constants.DOMAIN_CONTRACT_DATA);
		AssignRolePage.writeComment("assigning the role back");
		AssignRolePage.clickDone();
		AssignRolePage.clickCloseButton();
	}

	// private methods are below this line

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}