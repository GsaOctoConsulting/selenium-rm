package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.SystemAccountRequestDetailsPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class SystemAccountEmailStep {
	private static Logger logger = LoggerFactory.getLogger(SystemAccountStep.class);
	private static String comments = new String();
	private static StringBuilder systemManager = new StringBuilder(ConstantsAccounts.SYSTEM_MANAGER_1);
	private static StringBuilder systemAdmin = new StringBuilder(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
	private static StringBuilder gsasecurityapprover = new StringBuilder(ConstantsAccounts.GSASECURITY_APPROVER_1);
	private static StringBuilder securityOfficial = new StringBuilder("shah.raiaan+gsasecurityapprover@gsa.gov");
	private static StringBuilder nonfeduser = new StringBuilder("raiaan.shah");

	// String formattedDate= new SimpleDateFormat("hh:mm:ss a").format(new Date());
	long epoch = System.currentTimeMillis() / 1000;
	String formattedDate = Long.toString(epoch);

	@Given("^_1saemail_ user logs in as system account manager$")
	public void _1saemail_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_1saemail_ user navigates to system account directory page$")
	public void _1saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_1saemail_ user enters all the system information$")
	public void _1saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_1saemail_ user enters all the organization info$")
	public void _1saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(systemAdmin.toString());
		// NewSystemAccountPage.selectSystemManagerInOrgInfo(systemManager.toString());
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_1saemail_ user enters permissions info$")
	public void _1saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_1saemail_ user enters security info$")
	public void _1saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_1saemail_ user enters authorization info$")
	public void _1saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_1saemail_ the system manager should get an email notification$")
	public void _1saemail_the_system_manager_should_get_an_email_notification() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {// system manager received the
																							// email
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SA_SUBMISSION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemManager.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom1.contains(user));
			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemAdmin.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom1.contains(user));
			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {// system admin received the
																						// email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemAdmin.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom2.contains(user));

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SA_SUBMISSION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemManager.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom2.contains(user));
			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);

	}

	@Then("^_1saemail_ the system admin shold also get an email notification$")
	public void _1saemail_the_system_admin_should_get_an_email_notification() throws Throwable {

	}

	@And("^_1saemail_ the newly created account should show up on the system account directory page$")
	public void _1saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_2saemail_ user logs in as system account admin$")
	public void _2saemail_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_2saemail_ user navigates to system account directory page$")
	public void _2saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_2saemail_ user enters all the system information$")
	public void _2saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_2saemail_ user enters all the organization info$")
	public void _2saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_2saemail_ user enters permissions info$")
	public void _2saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_2saemail_ user enters security info$")
	public void _2saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_2saemail_ user enters authorization info$")
	public void _2saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_2saemail_ the system admin should get an email notification$")
	public void _2saemail_the_system_admin_should_get_an_email_notification() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1)) {// system admin received the email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SO_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SO_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SO_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//-----------------------------------------------------------------------------------------------------
		if (emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1)) {// system manager received the email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1)) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SO_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SO_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SO_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}

	}

	@And("^_2saemail_ the security official should get an email notification$")
	public void _2saemail_the_security_official_should_get_an_email_notification() throws Throwable {

	}

	@And("^_2saemail_ the newly created account should show up on the system account directory page$")
	public void _2saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		LaunchBrowserUtil.delay(2);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_3saemail_ user logs in as nonfed user$")
	public void _3saemail_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_3saemail_ user navigates to system account directory page$")
	public void _3saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_3saemail_ user enters all the system information$")
	public void _3saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_3saemail_ user enters all the organization info$")
	public void _3saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_NONFED);
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_3saemail_ user enters permissions info$")
	public void _3saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_3saemail_ user enters security info$")
	public void _3saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString());
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_3saemail_ user enters authorization info$")
	public void _3saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		// NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_3saemail_ the newly created account should show up on the system account directory page$")
	public void _3saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {

		LaunchBrowserUtil.delay(2);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
	}

	@Then("^_3saemail_ the nonfed user should get an email notification$")
	public void _3saemail_the_nonfed_user_should_get_an_email_notification() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION, Constants.USERPASS,
				ConstantsAccounts.NONFED_ADMIN_ENTITYREGISTRATION_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);

		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);
		String emailBody1 = LaunchBrowserUtil.captureEmailContentNonfed();
		LaunchBrowserUtil.switchTabs(2);

		// asserting email body
		Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_NONFED_SYSTEMACCOUNT_CREATION_EMAIL_BODY));
	}

	@Given("^_4saemail_ user logs in as system account manager$")
	public void _4saemail_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_4saemail_ user navigates to system account directory page$")
	public void _4saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_4saemail_ user enters all the system information$")
	public void _4saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_4saemail_ user enters all the organization info$")
	public void _4saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(systemAdmin.toString());
		// NewSystemAccountPage.selectSystemManagerInOrgInfo(systemManager.toString());
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_4saemail_ user enters permissions info$")
	public void _4saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_4saemail_ user enters security info$")
	public void _4saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_4saemail_ user enters authorization info$")
	public void _4saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_4saemail_ the system manager should get an email notification$")
	public void _4saemail_the_system_manager_should_get_an_email_notification() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {// system manager received the
																							// email
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SA_SUBMISSION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemManager.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom1.contains(user));
			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemAdmin.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom1.contains(user));
			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {// system admin received the
																						// email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemAdmin.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom2.contains(user));

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SA_SUBMISSION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemManager.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom2.contains(user));
			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);

	}

	@Then("^_4saemail_ the system admin shold also get an email notification$")
	public void _4saemail_the_system_admin_should_get_an_email_notification() throws Throwable {
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@And("^_4saemail_ the newly created account should show up on the system account directory page$")
	public void _4saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(3);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);
	}

	@When("^_4saemail_ system admin changes the request to pending approval$")
	public void _4saemail_system_admin_changes_the_request_to_pending_approval() throws Throwable {

		SystemAccountRequestDetailsPage.writeComment("request is reviewed");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_4saemail_ the system admin should get approved email notification$")
	public void _4saemail_the_system_admin_should_get_approved_email_notification() throws Throwable {

		LaunchBrowserUtil.goToOctoTestEmailInbox();
		LaunchBrowserUtil.switchTabs(1);
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(2);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {// system admin received the
																						// email
			// asserting the email subject line

			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_APPROVE_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_APPROVE_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemAdmin.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom1.contains(user));

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {

			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemManager.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom1.contains(user));

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;

		}
//---------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {// system admin received the
																						// email
			// asserting the email subject line

			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_APPROVE_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_APPROVE_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemAdmin.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom2.contains(user));

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {

			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			String user = systemManager.toString().replace("@gsa.gov", "");
			Assert.assertEquals(true, emailToAndFrom2.contains(user));

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
	}

	@Then("^_4saemail_ system manager should get intial approval email notification$")
	public void _4saemail_the_system_manager_should_get_approved_email_notification() throws Throwable {

		// --------------------------------
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_4saemail_ gsa security approver logs in$")
	public void _4saemail_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

	}

	@And("^_4saemail gsa security approver approves the reviewed request$")
	public void _4saemail_gsa_security_approver_approves_the_reviewed_request() throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_4saemail gsa security approver gets an approval email notification$")
	public void _4saemail_gsa_security_approver_gets_an_approval_email_notification() throws Throwable {
		LaunchBrowserUtil.goToOctoTestEmailInbox();
		LaunchBrowserUtil.switchTabs(1);
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.navigateBack();
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(2);
		String applicationLink3 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink3 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		Assert.assertNotEquals(emailToAndFrom1, emailToAndFrom2);
		Assert.assertNotEquals(emailToAndFrom1, emailToAndFrom3);
		Assert.assertNotEquals(emailToAndFrom2, emailToAndFrom3);

		int counter = 0;
		if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {// system admin received
																								// the email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		} else if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		}

		else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

		}
//----------------------------------
		if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {// system admin received
																								// the email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		}

		else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		}
//----------------------------------		
		if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		} else if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		}

		else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		}
		Assert.assertEquals(3, counter);

	}

	@And("^_4saemail system manager should get final approval email notification$")
	public void _4saemail_system_manager_should_get_final_approval_email_notification() throws Throwable {

	}

	@And("^_4saemail system admin gets final approval email notification$")
	public void _4saemail_system_admin_gets_final_approval_email_notification() throws Throwable {

	}

	@And("^_4saemail the published account can now be deleted$")
	public void _4saemail_the_published_account_can_now_be_deleted() throws Throwable {
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@Given("^_5saemail_ user logs in as system admin$")
	public void _5saemail_user_logs_in_as_system_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_5saemail_ user navigates to system account directory page$")
	public void _5saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_5saemail_ user enters all the system information$")
	public void _5saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_5saemail_ user enters all the organization info$")
	public void _5saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("celine.benedict+saa@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_5saemail_ user enters permissions info$")
	public void _5saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_5saemail_ user enters security info$")
	public void _5saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("security official");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_5saemail_ user enters authorization info$")
	public void _5saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_5saemail_ the system admin and the gsasecurityapprover should get pending email notification$")
	public void _5saemail_the_system_admin_should_get_an_email_notification() throws Throwable {
		if (Constants.SAM_HOME_PAGE.equals(Constants.SAM_HOME_PAGE)) {
			LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		}

		// -----------------------------------------------------------------

		if (emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		}

		Assert.assertEquals(2, counter);
	}

	@And("^_5saemail_ the newly created account should show up on the system account directory page$")
	public void _5saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		LaunchBrowserUtil.delay(2);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_5saemail_ gsa security approver logs in$")
	public void _5saemail_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_5saemail gsa security approver approves the reviewed request$")
	public void _5saemail_gsa_security_approver_approves_the_reviewed_request() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		// ----------------------------------------------------
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

	}

	@Then("^_5saemail gsa security approver gets an approval email notification$")
	public void _5saemail_gsa_security_approver_gets_an_approval_email_notification() throws Throwable {
		if (Constants.SAM_HOME_PAGE.equals(Constants.SAM_HOME_PAGE)) {
			LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;

		} else if (emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;
		}

		// -----------------------------------------------------------------

		if (emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;
		}

		Assert.assertEquals(2, counter);

	}

	@And("^_5saemail system admin gets final approval email notification$")
	public void _5saemail_system_admin_gets_final_approval_email_notification() throws Throwable {

	}

	@Then("^_5saemail the published account can now be deleted$")
	public void _5saemail_the_published_account_can_now_be_deleted() throws Throwable {
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		boolean accountstatusUpdated = SystemAccountDirectoryPage.accountFound(formattedDate,
				Constants.STATUS_PUBLISHED, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.DELETE);
		Assert.assertEquals(true, accountstatusUpdated);
	}

	@Given("^_6saemail_ user logs in as system account manager$")
	public void _6saemail_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_6saemail_ user navigates to system account directory page$")
	public void _6saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_6saemail_ user enters all the system information$")
	public void _6saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.clickCheckAvailabilityButton();
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_6saemail_ user enters all the organization info$")
	public void _6saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		// NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_6saemail_ user enters permissions info$")
	public void _6saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_6saemail_ user enters security info$")
	public void _6saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_6saemail_ user enters authorization info$")
	public void _6saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_6saemail_ the newly created account should show up on the system account directory page$")
	public void _6saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		LaunchBrowserUtil.delay(4);
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Then("^_6saemail_ system accound admin logs in$")
	public void _6saemail_system_accound_admin_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^__6saemail_ system account admin navigates to system account directory page$")
	public void _6saemail_system_account_admin_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^__6saemail_ admin should be able to change the request status to pending approval$")
	public void _6saemail_admin_should_be_able_to_change_the_request_status_to_pending_approval() throws Throwable {
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		LaunchBrowserUtil.delay(3);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is reviewed");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();

		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();

	}

	@When("^_6saemail_ gsa security approver logs in$")
	public void _6saemail_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_6saemail gsa security approver rejects the reviewed request$")
	public void _6saemail_gsa_security_approver_approves_the_reviewed_request() throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();

		// ----------------------------------------------------
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is rejected");
		SystemAccountRequestDetailsPage.clickRejectButton();
		SystemAccountRequestDetailsPage.clickCloseButton();

	}

	@Then("^_6saemail gsa security approver gets an rejection email notification$")
	public void _6saemail_gsa_security_approver_gets_an_rejection_email_notification() throws Throwable {
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.goToOctoTestEmailInbox();
		LaunchBrowserUtil.switchTabs(1);
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.navigateBack();
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(2);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(2);
		String applicationLink3 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink3 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(3);

		Assert.assertNotEquals(emailToAndFrom1, emailToAndFrom2);
		Assert.assertNotEquals(emailToAndFrom1, emailToAndFrom3);
		Assert.assertNotEquals(emailToAndFrom2, emailToAndFrom3);

		int counter = 0;

		if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;

		} else if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}

		else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		// ------------------------------------------------------------------------------------
		if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {//

			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}

		else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//----------------------------------		
		if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {

			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}

		else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
	}

	@And("^_6saemail system manager should get rejection email notification$")
	public void _6saemail_system_manager_should_get_rejection_email_notification() throws Throwable {

	}

	@And("^_6saemail system admin gets rejection email notification$")
	public void _6saemail_system_admin_gets_rejection_email_notification() throws Throwable {

		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		LaunchBrowserUtil.delay(6);
		LaunchBrowserUtil.closeBrowsers();
	}

	@Given("^_7saemail_ user logs in as system account manager$")
	public void _7saemail_user_logs_in_as_system_account_manager() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_7saemail_ user navigates to system account directory page$")
	public void _7saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_7saemail_ user enters all the system information$")
	public void _7saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_7saemail_ user enters all the organization info$")
	public void _7saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("shah.raiaan+saadmin@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("shah.raiaan+samanager@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_7saemail_ user enters permissions info$")
	public void _7saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_7saemail_ user enters security info$")
	public void _7saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail("a@b.c");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_7saemail_ user enters authorization info$")
	public void _7saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_7saemail_ the newly created account should show up on the system account directory page$")
	public void _7saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {

		LaunchBrowserUtil.delay(2);

		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
	}

	@When("^_7saemail_ system accound admin logs in$")
	public void _7saemail_system_accound_admin_logs_in() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^__7saemail_ system account admin navigates to system account directory page$")
	public void _7saemail_system_account_admin_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^__7saemail_ admin rejects the request the request$")
	public void _7saemail_admin_rejects_the_request_the_request() throws Throwable {
		SystemAccountDirectoryPage.clickPendingReviewFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		// ----------------------------------------------------
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_REVIEW,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is rejected");
		SystemAccountRequestDetailsPage.clickRejectButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@Then("^_7saemail_ both the manger and admin should get account rejection email$")
	public void _7saemail_both_the_manger_and_admin_should_get_account_rejection_email() throws Throwable {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString())) {// system manager received the email

			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);

			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString())) {// system admin received the email
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString()), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
	}

	@Then("^_7saemail system admin deletes the application$")
	public void _7saemail_system_admin_deletes_the_application() throws Throwable {
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_DRAFT,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_8saemail_ user logs in as system account admin$")
	public void _8saemail_user_logs_in_as_system_account_admin() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^_8saemail_ user navigates to system account directory page$")
	public void _8saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_8saemail_ user enters all the system information$")
	public void _8saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_8saemail_ user enters all the organization info$")
	public void _8saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo("celine.benedict+saa@gsa.gov");
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_8saemail_ user enters permissions info$")
	public void _8saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_8saemail_ user enters security info$")
	public void _8saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("security official");
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.GSASECURITY_APPROVER_1);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_8saemail_ user enters authorization info$")
	public void _8saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		// NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();

		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@Then("^_8saemail_ the system admin and the gsasecurityapprover should get pending email notification$")
	public void _8saemail_the_system_admin_and_the_gsasecurityapprover_should_get_pending_email_notification()
			throws Throwable {
		if (Constants.SAM_HOME_PAGE.equals(Constants.SAM_HOME_PAGE)) {
			LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		} else if (emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		}

		// -----------------------------------------------------------------

		if (emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN));

			// asserting links
			// Assert.assertEquals(true,
			// applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			// Assert.assertEquals(true,
			// learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK));
			counter++;
		}

		Assert.assertEquals(2, counter);
	}

	@Then("^_8saemail_ the newly created account should show up on the system account directory page$")
	public void _8saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {

		LaunchBrowserUtil.delay(2);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_8saemail_ gsasecurityapprover logs in$")
	public void _8saemail_gsasecurityapprover_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();

	}

	@And("^__8saemail_ gsasecurityapprover navigates to system account directory page$")
	public void _8saemail_gsasecurityapprover_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^__8saemail_ gsasecurityapprover rejects the request the request$")
	public void _8saemail_gsasecurityapprover_rejects_the_request() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		// ----------------------------------------------------
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is rejected");
		SystemAccountRequestDetailsPage.clickRejectButton();
		SystemAccountRequestDetailsPage.clickCloseButton();

	}

	@Then("^_8saemail_ both the admin and gsasecurityapprover should get account rejection email$")
	public void _saemail_both_the_admin_and_gsasecurityapprover_should_get_account_rejection_email() throws Throwable {

		if (Constants.SAM_HOME_PAGE.equals(Constants.SAM_HOME_PAGE)) {
			LaunchBrowserUtil.goToFedMailInbox(Constants.GMAIL_USERNAME, Constants.USERPASS);
		} else {
			LaunchBrowserUtil.switchTabs(1);
			LaunchBrowserUtil.navigateBack();
		}
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(1);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(1);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();
		LaunchBrowserUtil.switchTabs(2);

		int counter = 0;
		if (emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;

		} else if (emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom1.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;
		}

		// -----------------------------------------------------------------

		if (emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;
		} else if (emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));
			Assert.assertEquals(true,
					emailToAndFrom2.contains(ConstantsAccounts.GSASECURITY_APPROVER_1.replace("@gsa.gov", "")));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);

			// asserting links
			// Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK),
			// true);
			// Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK),
			// true);
			counter++;
		}

		Assert.assertEquals(2, counter);
		// **********************************************************************************************************************
		/*
		 * int counter = 0; if (emailToAndFrom1.contains(systemManager.toString())) {//
		 * system manager received the email
		 * 
		 * // asserting the email subject line Assert.assertEquals(true,
		 * emailSubject1.contains(Constants.EMAIL_SENT_FROM)); Assert.assertEquals(true,
		 * emailSubject1.contains(Constants.EMAIL_SAM_REJECT_SUBJECT_LINE));
		 * 
		 * // asserting email body Assert.assertEquals(true,
		 * emailBody1.contains(Constants.EMAIL_SAM_REJECT_EMAIL_BODY));
		 * Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN
		 * ), true);
		 * Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString()),
		 * true);
		 * 
		 * // asserting links Assert.assertEquals(applicationLink1.contains(Constants.
		 * EMAIL_SA_APPLICATION_LINK_REJECT), true);
		 * Assert.assertEquals(learningCenterLink1.contains(Constants.
		 * EMAIL_SA_LEARNING_CENTER_LINK), true);
		 * 
		 * counter++; } else if (emailToAndFrom1.contains(systemAdmin.toString())) { //
		 * asserting the email subject line Assert.assertEquals(true,
		 * emailSubject2.contains(Constants.EMAIL_SENT_FROM)); Assert.assertEquals(true,
		 * emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));
		 * 
		 * // asserting email body Assert.assertEquals(true,
		 * emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
		 * Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN
		 * ), true);
		 * Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString()), true);
		 * 
		 * // asserting links Assert.assertEquals(applicationLink2.contains(Constants.
		 * EMAIL_SA_APPLICATION_LINK_REJECT), true);
		 * Assert.assertEquals(learningCenterLink2.contains(Constants.
		 * EMAIL_SA_LEARNING_CENTER_LINK), true); counter++; }
		 * //-------------------------------------------- if
		 * (emailToAndFrom2.contains(systemAdmin.toString())) {// system admin received
		 * the email // asserting the email subject line Assert.assertEquals(true,
		 * emailSubject2.contains(Constants.EMAIL_SENT_FROM)); Assert.assertEquals(true,
		 * emailSubject2.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));
		 * 
		 * // asserting email body Assert.assertEquals(true,
		 * emailBody2.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
		 * Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN
		 * ), true);
		 * Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString()), true);
		 * 
		 * // asserting links Assert.assertEquals(applicationLink2.contains(Constants.
		 * EMAIL_SA_APPLICATION_LINK_REJECT), true);
		 * Assert.assertEquals(learningCenterLink2.contains(Constants.
		 * EMAIL_SA_LEARNING_CENTER_LINK), true); counter++; } else if
		 * (emailToAndFrom2.contains(systemManager.toString())) { // asserting the email
		 * subject line Assert.assertEquals(true,
		 * emailSubject1.contains(Constants.EMAIL_SENT_FROM)); Assert.assertEquals(true,
		 * emailSubject1.contains(Constants.EMAIL_SAA_REJECT_SUBJECT_LINE));
		 * 
		 * // asserting email body Assert.assertEquals(true,
		 * emailBody1.contains(Constants.EMAIL_SAA_REJECT_EMAIL_BODY));
		 * Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN
		 * ), true);
		 * Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString()), true);
		 * 
		 * // asserting links Assert.assertEquals(applicationLink1.contains(Constants.
		 * EMAIL_SA_APPLICATION_LINK_REJECT), true);
		 * Assert.assertEquals(learningCenterLink1.contains(Constants.
		 * EMAIL_SA_LEARNING_CENTER_LINK), true); counter++; } Assert.assertEquals(2,
		 * counter);
		 */
	}

	@Then("^_8saemail when system account logs back in they should see status of the application in draft$")
	public void _8saemail_gsasecurityapprover_deletes_the_application() throws Throwable {
		LaunchBrowserUtil.delay(4);
		LaunchBrowserUtil.closeBrowsers();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickDraftFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_DRAFT,
				Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.DELETE);
		Assert.assertEquals(true, accountFound);
	}

	@Given("^_9saemail_ user logs in as nonfed user$")
	public void _9saemail_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_9saemail_ user navigates to system account directory page$")
	public void _9saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_9saemail_ user enters all the system information$")
	public void _9saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_9saemail_ user enters all the organization info$")
	public void _9saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo("raiaan.shah@gmail.com");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_9saemail_ user enters permissions info$")
	public void _9saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_9saemail_ user enters security info$")
	public void _9saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString());
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_9saemail_ user enters authorization info$")
	public void _9saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		// NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_9saemail_ the newly created account should show up on the system account directory page$")
	public void _9saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {

		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_9saemail_ gsa security approver logs in$")
	public void _9saemail_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_9saemail gsa security approver approves the request$")
	public void _9saemail_gsa_security_approver_approves_the_request() throws Throwable {
		LaunchBrowserUtil.delay(14);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		// ----------------------------------------------------
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);

		SystemAccountRequestDetailsPage.writeComment("request is approved");
		SystemAccountRequestDetailsPage.clickApproveButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
		SystemAccountDirectoryPage.clickPublishedFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
	}

	@And("^_9saemail the nonfed user gets a final approval notification$")
	public void _9saemail_the_nonfed_user_gets_a_final_approval_notification() throws Throwable {

		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		// ----------------------------------------------------
		LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED);
		String emailBody = LaunchBrowserUtil.captureEmailContentNonfed();

		// asserting email body
		Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY));

	}

	@Given("^_10saemail_ user logs in as nonfed user$")
	public void _10saemail_user_logs_in_as_nonfed_user() throws Throwable {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_NONFED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@And("^_10saemail_ user navigates to system account directory page$")
	public void _10saemail_user_navigates_to_system_account_directory_page() throws Throwable {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@And("^_10saemail_ user enters all the system information$")
	public void _10saemail_user_enters_all_the_system_information() throws Throwable {
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("testv1");
		NewSystemAccountPage.enterSystemDescription("description");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@And("^_10saemail_ user enters all the organization info$")
	public void _10saemail_user_enters_all_the_organization_info() throws Throwable {
		NewSystemAccountPage.selectSystemAdminInOrgInfo("raiaan.shah@gmail.com");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_10saemail_ user enters permissions info$")
	public void _10saemail_user_enters_permissions_info() throws Throwable {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_10saemail_ user enters security info$")
	public void _10saemail_user_enters_security_info() throws Throwable {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Ashburn VA");
		NewSystemAccountPage.enterSecurityOfficialName("a");
		NewSystemAccountPage.enterSecurityOfficialEmail(gsasecurityapprover.toString());
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_10saemail_ user enters authorization info$")
	public void _10saemail_user_enters_authorization_info() throws Throwable {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();

		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFed(Constants.EMAIL_NONFED,
				Constants.USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();

		// NewSystemAccountPage.goToWorkspaceWithoutBreadcrumbs();
		NewSystemAccountPage.goToWorkspace();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Then("^_10saemail_ the newly created account should show up on the system account directory page$")
	public void _10saemail_the_newly_created_account_should_show_up_on_the_system_account_directory_page()
			throws Throwable {
		LaunchBrowserUtil.delay(2);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton();
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.NOACTION);
		Assert.assertEquals(true, accountFound);

	}

	@When("^_10saemail_ gsa security approver logs in$")
	public void _10saemail_gsa_security_approver_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_10saemail gsa security approver rejects the request$")
	public void _10saemail_gsa_security_approver_approves_the_request() throws Throwable {
		LaunchBrowserUtil.delay(14);
		SystemAccountDirectoryPage.searchByKeyword(formattedDate);
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.clickSortDescedingByTimestampButton(); //
		boolean accountFound = SystemAccountDirectoryPage.accountFound(formattedDate, Constants.STATUS_PENDING_APPROVAL,
				Constants.ORG_OCTO_CONSULTING_GROUP, Constants.DOMAIN_CONTRACT_OPPORTUNITIES,
				Constants.GO_TO_REQUEST_DETAILS);
		Assert.assertEquals(true, accountFound);
		SystemAccountRequestDetailsPage.writeComment("request is rejected");
		SystemAccountRequestDetailsPage.clickRejectButton();
		SystemAccountRequestDetailsPage.clickCloseButton();
	}

	@Then("^_10saemail gsa security approver gets a rejection email notification$")
	public void _10saemail_gsa_security_approver_gets_an_approval_email_notification() throws Throwable {
		LaunchBrowserUtil.delay(5);
		/*
		 * LaunchBrowserUtil.goToOctoTestEmailInbox(); String emailSubject1 =
		 * LaunchBrowserUtil.captureTitleFromLastEmail(0); String emailBody1 =
		 * LaunchBrowserUtil.captureEmailMessage(0); String applicationLink1 =
		 * LaunchBrowserUtil.getApplicationLink(); String learningCenterLink1 =
		 * LaunchBrowserUtil.getLearningCenterLink(); String emailToAndFrom1 =
		 * LaunchBrowserUtil.captureToAndFromInEmail(); LaunchBrowserUtil.switchTabs(2);
		 */
		/*
		 * // asserting the email subject line Assert.assertEquals(true,
		 * emailSubject1.contains(Constants.EMAIL_SENT_FROM)); Assert.assertEquals(true,
		 * emailSubject1.contains(Constants.EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));
		 * 
		 * // asserting email body Assert.assertEquals(true,
		 * emailBody1.contains(Constants.EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
		 * Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN
		 * ), true);
		 * Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString()),
		 * true);
		 * 
		 * // asserting links Assert.assertEquals(applicationLink1.contains(Constants.
		 * EMAIL_SA_APPLICATION_LINK), true);
		 * Assert.assertEquals(learningCenterLink1.contains(Constants.
		 * EMAIL_SA_LEARNING_CENTER_LINK), true);
		 */

	}

	@And("^_10saemail the nonfed user gets a rejection email notification$")
	public void _10saemail_the_nonfed_user_gets_a_final_approval_notification() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
		LaunchBrowserUtil.delay(3);
		
		 LaunchBrowserUtil.goToNonFedFedMailInbox(Constants.EMAIL_NONFED); 
			String emailBody = LaunchBrowserUtil.captureEmailContentNonfed();

			// asserting email body
			Assert.assertEquals(true, emailBody.contains(Constants.EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_EMAIL_BODY));
	}


	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
