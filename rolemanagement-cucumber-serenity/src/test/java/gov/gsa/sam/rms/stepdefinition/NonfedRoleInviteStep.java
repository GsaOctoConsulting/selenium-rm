package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.FeedsRequestPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.PendingRoleInvitationPage;
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
import junit.framework.Assert;

public class NonfedRoleInviteStep {
	private static Logger logger = LoggerFactory.getLogger(NonfedRoleInviteStep.class);
	String nonfeduseremail = "";
	String secretkey = "";
	String counter = "";
	String timestamp = new String();
	String acceptedtime = new String();

	@Given("^_1nri nonfed admin logs in$")
	public void _1nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_1nri goes to the role invite page through user directory$")
	public void _1nri_goes_to_the_role_invite_page_through_user_directory() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();

	}

	@When("^_1nri admin enters an id for a user with roles in the admins own domain$")
	public void _1nri_admin_enters_an_id_for_a_user_with_roles_in_the_admins_own_domain() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1);
	}

	@Then("^_1nri admin should receive proper message and be able to assign role to user$")
	public void _1nri_admin_should_receive_proper_message_and_be_able_to_assign_role_to_user() throws Throwable {
		RoleInviteAssignRolePage.clickExistingUserAcceptButton();

	}

	@Given("^_2nri nonfed admin logs in$")
	public void _2nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_2nri goes to the role invite page through user directory$")
	public void _2nri_goes_to_the_role_invite_page_through_user_directory() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_2nri admin enters an id for a user with no roles who has a pending invite$")
	public void _2nri_admin_enters_an_id_for_a_user_with_no_roles_in_the_admins_own_domain() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_4_NO_ROLES_PENDINGROLEINVITE);
	}

	@Then("^_2nri admin should receive error message for the users pending role invite$")
	public void _2nri_admin_should_not_receive_any_dialog_box_and_proceed_to_invite_the_user() throws Throwable {

		boolean roleFound = RoleInviteAssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, roleFound);

		boolean domainFound = RoleInviteAssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainFound);

		boolean entityFound = RoleInviteAssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP,
				0);
		Assert.assertEquals(true, entityFound);

		String alertmessage = RoleInviteAssignRolePage.getPendingUserAccessAlertMessage();
		Assert.assertTrue(alertmessage.contains("User has pending user access for current Organization and Domain"));
	}

	@Given("^_3nri spaad logs in$")
	public void _3nri_spaad_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_3nri spaad goes to the role invite page through user directory$")
	public void _3nri_spaad_goes_to_the_role_invite_page_through_user_directory() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_3nri spaad enters a federal id in the user email box$")
	public void _3nri_spaad_enters_a_federal_id_in_the_user_email_box() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.ROLE_ADMIN_USER_3);
	}

	@Then("^_3nri spaad should see error message asking entry of nonfederal email id only$")
	public void _3nri_spaad_should_see_error_message_asking_entry_of_nonfederal_email_id_only() throws Throwable {
		String errormessage = RoleInviteAssignRolePage.getUserEmailErrorMessage();
		Assert.assertEquals("Please enter a non-federal email", errormessage);
	}

//	@Given("^_4nri new nonfed user signs up$")
//	public void _4nri_new_nonfed_user_signs_up() throws Throwable {
//		counter = SignUpUtility.updatecounter("login.nonfed.accountno");
//		secretkey = SignUpUtility.signUpNewUserNonFed(
//				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
//		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
//		CommonProfilePage.enterFirstName("shah");
//		CommonProfilePage.enterLastName("raiaan");
//		CommonProfilePage.enterWorkphone("5555555555");
//		LaunchBrowserUtil.scrollAllTheWayDown();
//		CommonProfilePage.clickSubmitButton();
//		LaunchBrowserUtil.closeBrowsers();
//	}

	@Given("^_4nri nonfed admin logs in$")
	public void _4nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_4nri admin enters an id for a user with no roles$")
	public void _4nri_admin_enters_an_id_for_a_user_with_no_roles() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@Then("^_4nri admin should not receive any dialog box and proceed to invite the user$")
	public void _4nri_admin_should_not_receive_any_dialog_box_and_proceed_to_invite_the_user() throws Throwable {
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
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
	}

	@When("^_4nri invited user logs in$")
	public void _4nri_invited_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS, ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_4nri the invited user should receive a dialog box and be able to skip to workspace$")
	public void _4nri_the_invited_user_should_receive_a_dialog_box() throws Throwable {
		T1WorkspacePage.clickSkipOnRoleInviteModal();
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToMyWorkspacePage();
		
		
		// decline request to allow repeatability of the test
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickPendingFilter();
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_ENTITY_REGISTRATION,
				Constants.CODE_ORG_OCTO_CONSULTING.toString(), Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		PendingRoleInvitationPage.writeAdditionalInformation("declining this role invite");
		PendingRoleInvitationPage.clickDeclineButton();
		String heading = PendingRoleInvitationPage.getHeading();
		Assert.assertEquals("You have Declined a Role Invitation", heading);
		
		
	}

	@Given("^_5nri nonfed admin logs in$")
	public void _5nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_5nri nonfed admin navigates to role invite page$")
	public void _5nri_nonfed_admin_navigates_to_role_invite_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_5nri admin enters an id for a user who currently does not exist in the system$")
	public void _5nri_admin_enters_an_id_for_a_user_who_currently_does_not_exist_in_the_system() throws Throwable {
		counter = SignUpUtility.updatecounter("login.nonfed.accountno");

		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";

		RoleInviteAssignRolePage.enterEmailAddress(nonfeduseremail);
		boolean roleFound = RoleInviteAssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, roleFound);

		boolean domainFound = RoleInviteAssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainFound);

		boolean entityFound = RoleInviteAssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP,
				0);
		Assert.assertEquals(true, entityFound);

	}

	@Then("^_5nri admin should not receive any dialog box and proceed to invite the user$")
	public void _5nri_admin_should_not_receive_any_dialog_box_and_proceed_to_invite_the_user() throws Throwable {

		RoleInviteAssignRolePage.enterBusinessJustification("sending invite");

		RoleInviteAssignRolePage.clickSendInvitationButton();
		RoleInviteAssignRolePage.clickCloseButton();
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5nri the invited user sign up for an account$")
	public void _5nri_the_invited_user_sign_up_for_an_account() throws Throwable {
		secretkey = SignUpUtility.signUpNewUserNonFed(
				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
		T1WorkspacePage.clickGoToRequestButtonOnRoleInviteModal();
	}

	@Then("^_5nri the invited user should receive a dialog box for the role invite upon registration completion$")
	public void _5nri_the_invited_user_should_receive_a_dialog_box_for_the_role_invite_upon_registration_completion()
			throws Throwable {

	}

	@Given("^_6nri new nonfed user signs up$")
	public void _6nri_new_nonfed_user_signs_up() throws Throwable {

		counter = SignUpUtility.updatecounter("login.nonfed.accountno");
		secretkey = SignUpUtility.signUpNewUserNonFed(
				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
		CommonProfilePage.enterFirstName("shah");
		CommonProfilePage.enterLastName("raiaan");
		CommonProfilePage.enterWorkphone("5555555555");
		LaunchBrowserUtil.scrollAllTheWayDown();
		CommonProfilePage.clickSubmitButton();
		LaunchBrowserUtil.closeBrowsers();

	}

	@And("^_6nri nonfed admin logs in$")
	public void _6nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_6nri nonfed admin navigates to role invite page$")
	public void _6nri_nonfed_admin_navigates_to_role_invite_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
	}

	@When("^_6nri admin invites a nonfed user for viewer role in the admins domain$")
	public void _6nri_admin_invites_a_nonfed_user_for_viewer_role_in_the_admins_domain() throws Throwable {
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

	@Then("^_6nri admin should receive an email about the role invite$")
	public void _6nri_admin_should_receive_an_email_about_the_role_invite() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);

		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();
		String emailBody2 = LaunchBrowserUtil.captureEmailContentNonfed();

		int counter = 0;
		if (emailBody1.contains("You have been invited to accept a role")) {

			// asserting the email sent to user
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ACTION_INVITED));
			Assert.assertEquals(true, emailBody1.contains(Constants.ORG_OCTO_CONSULTING_GROUP));
			Assert.assertEquals(true, emailBody1.contains(Constants.ROLE_VIEWER));

			Assert.assertEquals(true, emailBody1.contains(Constants.CODE_ORG_OCTO_CONSULTING));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));

			counter++;

		} else if (emailBody1.contains("You have sent a role invitation")) {

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

	@And("^_6nri the user should also get an email about the role invite$")
	public void _6nri_the_user_should_also_get_an_email_about_the_role_invite() throws Throwable {

	}

	@Given("^_7nri nonfed user with pending role invite logs in$")
	public void _7nri_nonfed_user_with_pending_role_invite_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_4_NO_ROLES_PENDINGROLEINVITE, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_4_NO_ROLES_PENDINGROLEINVITE_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_7nri requests data entry role in entity registration domain$")
	public void _7nri_requests_data_entry_role_in_entity_registration_domain() throws Throwable {
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

	@When("^_7nri nonfed entity registration admin logs in$")
	public void _7nri_nonfed_entity_registration_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);

	}

	@And("^_7nri nonfed admin tries to approve the request for the nonfed user$")
	public void _7nri_nonfed_admin_tries_to_approve_the_request_for_the_nonfed_user() throws Throwable {
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
		LaunchBrowserUtil.navigateBack();
		RoleRequestPendingPage.enterAdditionalInformation("dsfsdf");
		RoleRequestPendingPage.clickRejectButton();
	}

	@Then("^_7nri proper error message should be shown$")
	public void _7nri_proper_error_message_should_be_shown() throws Throwable {

	}

//	@Given("^_8nri new nonfed user signs up$")
//	public void _8nri_new_nonfed_user_signs_up() throws Throwable {
////		counter = SignUpUtility.updatecounter("login.nonfed.accountno");
////		secretkey = SignUpUtility.signUpNewUserNonFed(
////				"nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com", Constants.USERPASS);
////		nonfeduseremail = "nonfedgsaemail+newregisterednonfeduser" + counter + "@yopmail.com";
////		CommonProfilePage.enterFirstName("shah");
////		CommonProfilePage.enterLastName("raiaan");
////		CommonProfilePage.enterWorkphone("5555555555");
////		LaunchBrowserUtil.scrollAllTheWayDown();
////		CommonProfilePage.clickSubmitButton();
////		LaunchBrowserUtil.closeBrowsers();
//	}

	@Given("^_8nri nonfed admin logs in$")
	public void _8nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_8nri admin enters an id for a user with no roles$")
	public void _8nri_admin_enters_an_id_for_a_user_with_no_roles() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
	}

	@Then("^_8nri admin should not receive any dialog box and proceed to invite the user$")
	public void _8nri_admin_should_not_receive_any_dialog_box_and_proceed_to_invite_the_user() throws Throwable {

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
	}

	@When("^_8nri invited user logs in$")
	public void _8nri_invited_user_logs_in() throws Throwable {
//		SignInUtility.signIntoWorkspace(nonfeduseremail, Constants.USERPASS, secretkey, Constants.USER_NONFED);
//		LaunchBrowserUtil.delay(4);

		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_8nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked$")
	public void _8nri_the_invited_user_should_receive_a_dialog_box_and_land_on_feeds_page_when_go_to_request_button_is_clicked()
			throws Throwable {
		T1WorkspacePage.clickGoToRequestButtonOnRoleInviteModal();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickPendingFilter();
	}

	@When("^_8nri the user selects the pending request in feeds and accepts the role invite$")
	public void _8nri_the_user_selects_the_pending_request_in_feeds_and_accepts_the_role_invite() throws Throwable {
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_ENTITY_REGISTRATION,
				Constants.CODE_ORG_OCTO_CONSULTING.toString(), Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		PendingRoleInvitationPage.writeAdditionalInformation("accepting this role");
		PendingRoleInvitationPage.clickAcceptButton();
		String heading = PendingRoleInvitationPage.getHeading();
		Assert.assertEquals("You Have Accepted a Role Invitation", heading);
		String acceptedtime = PendingRoleInvitationPage.getAcceptedTime();
		PendingRoleInvitationPage.clickCloseButton();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickAcceptedFilter();
		FeedsRequestPage.selectSortyBy("Response Date");

		boolean requestFound2 = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_ENTITY_REGISTRATION, acceptedtime, Constants.STATUS_ACCEPTED,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound2);
		PendingRoleInvitationPage.clickCloseButton();
		FeedsRequestPage.goToWorkspacePage();
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_8nri the user should see the role in profile with the correctly role history reflected$")
	public void _8nri_the_user_should_see_the_role_in_profile_with_the_correctly_role_history_reflected()
			throws Throwable {
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_VIEWER,
				Constants.DOMAIN_ENTITY_REGISTRATION, Constants.NOACTION);
		MyRolesPage.roleHistoryFound(timestamp, Constants.ASSIGNED,
				"shah raiaan assigned the Viewer for the OCTO CONSULTING GROUP, INC..", 0);
	}

	@And("^_8nri nonfed admin should now be able to look up the user through user directory$")
	public void _8nri_nonfed_admin_should_now_be_able_to_look_up_the_user_through_user_directory() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		// delete the role
		boolean roleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_ENTITY_REGISTRATION, Constants.DELETE);
		Assert.assertEquals(true, roleFound);

	}

	@Given("^_9nri nonfed admin logs in$")
	public void _9nri_nonfed_admin_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_9nri admin enters an id for a user with no roles$")
	public void _9nri_admin_enters_an_id_for_a_user_with_no_roles() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
	}

	@Then("^_9nri admin should not receive any dialog box and proceed to invite the user$")
	public void _9nri_admin_should_not_receive_any_dialog_box_and_proceed_to_invite_the_user() throws Throwable {
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
	}

	@When("^_9nri invited user logs in$")
	public void _9nri_invited_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_9nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked$")
	public void _9nri_the_invited_user_should_receive_a_dialog_box_and_land_on_feeds_page_when_go_to_request_button_is_clicked()
			throws Throwable {
		T1WorkspacePage.clickGoToRequestButtonOnRoleInviteModal();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickPendingFilter();
	}

	@When("^_9nri the user selects the pending request in feeds and declines the role invite$")
	public void _9nri_the_user_selects_the_pending_request_in_feeds_and_declines_the_role_invite() throws Throwable {
		boolean requestFound = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_ENTITY_REGISTRATION,
				Constants.CODE_ORG_OCTO_CONSULTING.toString(), Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		PendingRoleInvitationPage.writeAdditionalInformation("declining this role invite");
		PendingRoleInvitationPage.clickDeclineButton();
		String heading = PendingRoleInvitationPage.getHeading();
		Assert.assertEquals("You have Declined a Role Invitation", heading);
		String acceptedtime = PendingRoleInvitationPage.getDeclinedTime();
		PendingRoleInvitationPage.clickCloseButton();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickDeclinedFilter();
		FeedsRequestPage.selectSortyBy("Response Date");

		boolean requestFound2 = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_VIEWER, Constants.DOMAIN_ENTITY_REGISTRATION, acceptedtime, Constants.STATUS_DECLINED,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound2);
		PendingRoleInvitationPage.clickCloseButton();
		FeedsRequestPage.goToWorkspacePage();
		T1WorkspacePage.goToAccountDetailsPage();
	}

	@Then("^_9nri the user should see no roles assigned in profile with the status of the feed changed to declined$")
	public void _9nri_the_user_should_see_no_roles_assigned_in_profile_with_the_status_of_the_feed_changed_to_declined()
			throws Throwable {

	}

	@Given("^_10nf user logs in nonfed admin$")
	public void _10nf_user_logs_in_nonfed_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@And("^_10nf user navigates to role invite page$")
	public void _10nf_user_navigates_to_role_invite_page() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
	}

	@Then("^_10nf additional information box should now be called business justrification$")
	public void _10nf_additional_information_box_should_now_be_called_business_justrification() throws Throwable {
		boolean roleFound = RoleInviteAssignRolePage.selectEntityRoleIfFound(Constants.ROLE_VIEWER);
		Assert.assertEquals(true, roleFound);

		boolean domainFound = RoleInviteAssignRolePage.selectEntityDomainIfFound(Constants.DOMAIN_ENTITY_REGISTRATION);
		Assert.assertEquals(true, domainFound);

		boolean entityFound = RoleInviteAssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP,
				0);
		Assert.assertEquals(true, entityFound);

		String bussinessjustificationtext = RoleInviteAssignRolePage.getTextForBusinessJustification();
		Assert.assertEquals("Business Justification", bussinessjustificationtext);

	}

	@And("^_10nf the business justification field should also be mandatory$")
	public void _10nf_the_business_justification_field_should_also_be_mandatory() throws Throwable {
		RoleInviteAssignRolePage.enterBusinessJustification("  ");
		RoleInviteAssignRolePage.clickSendInvitationButton();
		String commenterror = RoleInviteAssignRolePage.getCommentError();
		Assert.assertEquals("Business Justification field is required.", commenterror);
	}

	@Given("^_11nri nonfed admin in contract opp logs in$")
	public void _11nri_nonfed_admin_in_contract_opp_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@When("^_11nri admin enters an id for a user with no roles and sends out an invite$")
	public void _11nri_admin_enters_an_id_for_a_user_with_no_roles_and_sends_out_an_invite() throws Throwable {
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.clickAssignRoleButton();
		RoleInviteAssignRolePage.enterEmailAddress(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		boolean roleFound = RoleInviteAssignRolePage.selectEntityRoleIfFound(Constants.ROLE_DATA_ENTRY);
		Assert.assertEquals(true, roleFound);

		boolean domainFound = RoleInviteAssignRolePage
				.selectEntityDomainIfFound(Constants.DOMAIN_CONTRACT_OPPORTUNITIES);
		Assert.assertEquals(true, domainFound);

		boolean entityFound = RoleInviteAssignRolePage.selectEntityNonFedIfFound(Constants.ORG_OCTO_CONSULTING_GROUP,
				0);
		Assert.assertEquals(true, entityFound);

		RoleInviteAssignRolePage.enterBusinessJustification("sending invite");

		RoleInviteAssignRolePage.clickSendInvitationButton();
		RoleInviteAssignRolePage.clickCloseButton();
	}

	@Then("^_11nri admin should be able to see the pending status of the invite in their feeds$")
	public void _11nri_admin_should_be_able_to_see_the_pending_status_of_the_invite_in_their_feeds() throws Throwable {
		UserDirectoryPage.goToWorkspacePage();
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickPendingFilter();
		FeedsRequestPage.selectSortyBy("Response Date");
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.CODE_ORG_OCTO_CONSULTING.toString(), Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
	}

	@When("^_11nri invited user logs in$")
	public void _11nri_invited_user_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_3_NO_ROLES, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_3_NO_ROLES_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_11nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked$")
	public void _11nri_the_invited_user_should_receive_a_dialog_box_and_land_on_feeds_page_when_go_to_request_button_is_clicked()
			throws Throwable {
		T1WorkspacePage.clickGoToRequestButtonOnRoleInviteModal();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickPendingFilter();
	}

	@When("^_11nri the user selects the pending request in feeds and accepts the role invite$")
	public void _11nri_the_user_selects_the_pending_request_in_feeds_and_accepts_the_role_invite() throws Throwable {
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.CODE_ORG_OCTO_CONSULTING.toString(), Constants.STATUS_PENDING,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound);
		PendingRoleInvitationPage.writeAdditionalInformation("accepting this role");
		PendingRoleInvitationPage.clickAcceptButton();
		String heading = PendingRoleInvitationPage.getHeading();
		Assert.assertEquals("You Have Accepted a Role Invitation", heading);
		acceptedtime = PendingRoleInvitationPage.getAcceptedTime();
		PendingRoleInvitationPage.clickCloseButton();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickAcceptedFilter();
		FeedsRequestPage.selectSortyBy("Response Date");

		boolean requestFound2 = FeedsRequestPage.requestFound("shah raiaan", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, acceptedtime, Constants.STATUS_ACCEPTED,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, requestFound2);
		PendingRoleInvitationPage.clickCloseButton();
		
	}

	@Then("^_11nri the user should see the status in feeds and the role in profile with the correctly role history reflected$")
	public void _11nri_the_user_should_see_the_status_in_feeds_and_the_role_in_profile_with_the_correctly_role_history_reflected()
			throws Throwable {
		FeedsRequestPage.goToWorkspacePage();
		T1WorkspacePage.goToAccountDetailsPage();
		AccountDetailsPage.goToPageOnSideNav("My Roles");
		MyRolesPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP, Constants.ROLE_DATA_ENTRY,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		MyRolesPage.roleHistoryFound(timestamp, Constants.ASSIGNED,
				"shah raiaan assigned the Data Entry for the OCTO CONSULTING GROUP, INC..", 0);
	}

	@When("^_11nri nonfed admin logs back in$")
	public void _11nri_nonfed_admin_logs_back_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_CONTRACTOPP_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_11nri the admin should also see the status update in their feeds$")
	public void _11nri_the_admin_should_also_see_the_status_update_in_their_feeds() throws Throwable {
		T1WorkspacePage.goToFeedsPage();
		FeedsRequestPage.clickSentOnSideBar();
		FeedsRequestPage.clickRoleInviteFilter();
		FeedsRequestPage.clickAcceptedFilter();
		FeedsRequestPage.selectSortyBy("Response Date");
		boolean requestFound = FeedsRequestPage.requestFound("You", Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				acceptedtime, Constants.STATUS_ACCEPTED,
				Constants.GO_TO_REQUEST_DETAILS);
		PendingRoleInvitationPage.clickCloseButton();
		FeedsRequestPage.goToWorkspacePage();
	}

	@And("^_11nri the admin should now be able to search the user in userdirectory$")
	public void _11nri_the_admin_should_now_be_able_to_search_the_user_in_userdirectory() throws Throwable {
		
		T1WorkspacePage.clickUserDirectoryLink();
		UserDirectoryPage.searchUserInUserPicker(ConstantsAccounts.NONFED_USER_3_NO_ROLES);
		UserDirectoryPage.clickViewAccess(ConstantsAccounts.NONFED_USER_3_NO_ROLES);

		// delete the role
		boolean roleFound = UserDirectoryViewAccessPage.userHasRole(Constants.ORG_OCTO_CONSULTING_GROUP,
				Constants.ROLE_DATA_ENTRY, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, roleFound);
	}

}
