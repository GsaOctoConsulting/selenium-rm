package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class AAM_SystemAccountEmailWorkflowStep {

	private static Logger logger = LoggerFactory.getLogger(AAM_SystemAccountEmailWorkflowStep.class);

	public static final String uniqSanEmail1 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail2 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail3 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail4 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail5 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail6 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail7 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail8 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail9 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail10 = NewSystemAccountPage.uniqueSAN();
	public static final String uniqSanEmail11 = NewSystemAccountPage.uniqueSAN();

	private static StringBuilder systemManager = new StringBuilder(ConstantsAccounts.SYSTEM_MANAGER_1);
	private static StringBuilder systemAdmin = new StringBuilder(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
	private static StringBuilder iaePmoAdmin = new StringBuilder(ConstantsAccounts.IAE_PMO_ADMINISTRATOR);
	private static StringBuilder gsasecurityapprover = new StringBuilder(ConstantsAccounts.GSASECURITY_APPROVER_1);
	private static StringBuilder nonfeduser = new StringBuilder(ConstantsAccounts.NONFED_NOROLE_USER_1);

	// _A In workflow IAE PMO Admin receives email when SAM submits system account
	// including Non-public permissions info - Positive scenario
	@Given("^_A_System Account Manager logs in$")
	public void _a_system_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_A_SAM navigates to system account directory page$")
	public void _a_sam_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_A_SAM enters all the system information$")
	public void _a_sam_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail1);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_A_SAM enters all the organization info$")
	public void _a_sam_enters_all_the_organization_info() throws Exception { //
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_A_SAM enters Non-public permissions info$")
	public void _a_sam_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_A_SAM enters security info$")
	public void _a_sam_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_A_SAM enters authorization info$")
	public void _a_sam_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_A_SAM accepts terms and conditions and submits account$")
	public void _a_sam_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_A_SAM and SAA receive email notification$")
	public void _a_sam_and_SAA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_SUBMISSION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_SUBMISSION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_A_System Account Administrator logs in$")
	public void _a_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A_SAA navigates to system account directory page$")
	public void _a_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@When("^_A_SAA searches for Pending Review request by SAM and approve it$")
	public void _a_saa_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		// The comments has been automated in separate scenario
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_A_SAA, SAM and IAE PMOA receive email notification$")
	public void _a_saa_SAM_and_IAE_PMOA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_A_IAE PMO Administrator logs in$")
	public void _a_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A_IAE PMOA approve the system account$")
	public void _a_iae_PMOA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_A_SAM, SAA, PMOA and GSA SA receive email notification$")
	public void _a_sam_SAA_PMOA_and_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		LaunchBrowserUtil.navigateBack();
		String emailSubject4 = LaunchBrowserUtil.captureTitleFromLastEmail(3);
		String emailBody4 = LaunchBrowserUtil.captureEmailMessage(3);
		String applicationLink4 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink4 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom4 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_SUBJECT_LINE));
			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}

// --------------------------------------------
		if (emailToAndFrom4.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody4.contains(Constants.MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAM_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody4.contains(Constants.MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(4, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_A_GSA Security Approver logs in$")
	public void _a_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_A_GSA SA approve the system account$")
	public void _a_gsa_SA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail1);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_A_SAA, SAM, PMOA, GSA SA receive email notification$")
	public void _a_saa_SAM_PMOA_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		LaunchBrowserUtil.navigateBack();
		String emailSubject4 = LaunchBrowserUtil.captureTitleFromLastEmail(3);
		String emailBody4 = LaunchBrowserUtil.captureEmailMessage(3);
		String applicationLink4 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink4 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom4 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
// --------------------------------------------
		if (emailToAndFrom4.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(4, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// _B In workflow IAE PMO Admin receives email when SAM submits system account
	// including Non-public permissions info - Negative scenario
	@Given("^_B_System Account Manager logs in$")
	public void _b_system_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_B_SAM navigates to system account directory page$")
	public void _b_sam_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_B_SAM enters all the system information$")
	public void _b_sam_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail2);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_B_SAM enters all the organization info$")
	public void _b_sam_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_B_SAM enters Non-public permissions info$")
	public void _b_sam_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_B_SAM enters security info$")
	public void _b_sam_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_B_SAM enters authorization info$")
	public void _b_sam_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_B_SAM accepts terms and conditions and submits account$")
	public void _b_sam_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_B_SAM and SAA receive email notification$")
	public void _b_sam_and_SAA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A");
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_B_System Account Administrator logs in$")
	public void _b_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B_SAA navigates to system account directory page$")
	public void _b_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@When("^_B_SAA searches for Pending Review request by SAM and approve it$")
	public void _b_saa_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail2);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_B_SAM, SAA, and IAE PMOA receive email notification$")
	public void _b_sam_SAA_and_IAE_PMOA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A");
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_B_IAE PMO Administrator logs in$")
	public void _b_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B_IAE PMOA approve the system account$")
	public void _b_iae_PMOA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail2);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_B_SAM, SAA, PMOA and GSA SA receive email notification$")
	public void _b_sam_SAA_PMOA_and_GSA_SA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A.");
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_B_GSA Security Approver logs in$")
	public void _b_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.MA_USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_B_GSA SA reject the system account$")
	public void _b_gsa_SA_reject_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail2);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
	}

	@Then("^_B_SAM, SAA, PMOA, GSA SA receive email$")
	public void _b_sam_SAA_PMOA_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		LaunchBrowserUtil.navigateBack();
		String emailSubject4 = LaunchBrowserUtil.captureTitleFromLastEmail(3);
		String emailBody4 = LaunchBrowserUtil.captureEmailMessage(3);
		String applicationLink4 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink4 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom4 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
// --------------------------------------------
		if (emailToAndFrom4.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom4.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject4.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject4.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody4.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom4.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom4.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink4.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink4.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(4, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// In workflow IAE PMO Admin receives email when SAA submits system account
	// including Non-public permissions info - Positive scenario
	@Given("^_C_System Account Administrator logs in$")
	public void _c_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_C_SAA navigates to system account directory page$")
	public void _c_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_C_SAA enters all the system information$")
	public void _c_saa_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail3);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_C_SAA enters all the organization info$")
	public void _c_saa_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_C_SAA enters Non-public permissions info$")
	public void _c_saa_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_C_SAA enters security info$")
	public void _c_saa_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_C_SAA enters authorization info$")
	public void _c_saa_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Given("^_C_SAA accepts terms and conditions and submits account$")
	public void _c_saa_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_C_SAA, IAE PMOA receive email notification$")
	public void _c_saa_IAE_PMOA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORPMOA_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORPMOA_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			// Assert.assertEquals(learningCenterLink1.contains(Constants.MA_EMAIL_FROM_ENV),
			// true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			// Assert.assertEquals(learningCenterLink1.contains(Constants.MA_EMAIL_FROM_ENV),
			// true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORPMOA_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORPMOA_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			// Assert.assertEquals(learningCenterLink2.contains(Constants.MA_EMAIL_FROM_ENV),
			// true);

			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			// Assert.assertEquals(learningCenterLink2.contains(Constants.MA_EMAIL_FROM_ENV),
			// true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_C_IAE PMO Administrator logs in$")
	public void _c_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_C_IAE PMOA approve the system account$")
	public void _c_iae_PMOA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail3);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@When("^_C_SAA, IAE PMOA, GSA SA receive email notification$")
	public void _c_saa_IAE_PMOA_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_C_GSA Security Approver logs in$")
	public void _c_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_C_GSA SA approve the system account$")
	public void _c_gsa_SA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail3);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@When("^_C_SAA, IAE PMOA and GSA SA receive email$")
	public void _c_saa_IAE_PMOA_and_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	// _D In workflow IAE PMO Admin receives email when SAA submits system account
	// including Non-public permissions info - Negative scenario
	@Given("^_D_System Account Administrator logs in$")
	public void _d_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_D_SAA navigates to system account directory page$")
	public void _d_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_D_SAA enters all the system information$")
	public void _d_saa_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail4);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_D_SAA enters all the organization info$")
	public void _d_saa_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_D_SAA enters Non-public permissions info$")
	public void _d_saa_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_D_SAA enters security info$")
	public void _d_saa_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_D_SAA enters authorization info$")
	public void _d_saa_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Given("^_D_SAA accepts terms and conditions and submits account$")
	public void _d_saa_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_D_SAA, PMOA receive email notification$")
	public void _d_saa_PMOA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario C");
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_D_IAE PMO Administrator logs in$")
	public void _d_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_D_IAE PMOA approve the system account$")
	public void _d_iae_PMOA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail4);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@When("^_D_SAA, PMOA and GSA SA receive email notification$")
	public void _d_saa_PMOA_and_GSA_SA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario C");
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_D_GSA Security Approver logs in$")
	public void _d_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_D_GSA SA reject the system account$")
	public void _d_gsa_SA_reject_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail4);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
	}

	@When("^_D_SAA, PMOA, GSA SA receives email$")
	public void _d_saa_PMOA_GSA_SA_receives_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// In workflow IAE PMO Admin receives email when Non_Fed user submits system
	// account including Non-public info - Positive scenario
	@Given("^_E_Non_Fed user logs in$")
	public void _e_non_fed_user_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_E_NFU navigates to system account directory page$")
	public void _e_nfu_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_E_NFU enters all the system information$")
	public void _e_nfu_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail5);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_E_NFU enters all the organization info$")
	public void _e_nfu_enters_all_the_organization_info() throws Exception {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.NONFED_NOROLE_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.NONFED_NOROLE_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_E_NFU enters Non-public permissions info$")
	public void _e_nfu_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);

		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_E_NFU enters security info$")
	public void _e_nfu_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_E_NFU enters authorization info$")
	public void _e_nfu_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Given("^_E_NFU accepts terms and conditions and submits account$")
	public void _e_nfu_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFedModified(
				Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_E_NFU, PMOA receive email notification$")
	public void _e_nfu_PMOA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.addFedEmailAccount(Constants.MA_GMAIL_FED_USERNAME);
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_TOPMOA_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_TOPMOA_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_TOPMOA_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_TOPMOA_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_E_IAE PMO Administrator logs in$")
	public void _e_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_E_IAE PMOA approve the system account$")
	public void _e_iae_PMOA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail5);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@When("^_E_NFU, PMOA and GSA SA receive email notification$")
	public void _e_nfu_PMOA_and_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		LaunchBrowserUtil.addNonFedEmailAccount(Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink3 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink3 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1
					.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2
					.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject3.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3
					.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_E_GSA Security Approver logs in$")
	public void _e_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_E_GSA SA approve the system account$")
	public void _e_gsa_SA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail5);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@When("^_E_NFU, PMOA, GSA SA receive email$")
	public void _e_nfu_PMOA_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		LaunchBrowserUtil.addNonFedEmailAccount(Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink3 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink3 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject3.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// F In workflow IAE PMO Admin receives email when Non_Fed user submits system
	// account including Non-public info - Negative scenario
	@Given("^_F_Non_Fed user logs in$")
	public void _f_non_fed_user_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_F_NFU navigates to system account directory page$")
	public void _f_nfu_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_F_NFU enters all the system information$")
	public void _f_nfu_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail6);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_F_NFU enters all the organization info$")
	public void _f_nfu_enters_all_the_organization_info() throws Exception {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.NONFED_NOROLE_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.NONFED_NOROLE_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_F_NFU enters Non-public permissions info$")
	public void _f_nfu_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_F_NFU enters security info$")
	public void _f_nfu_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_F_NFU enters authorization info$")
	public void _f_nfu_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Given("^_F_NFU accepts terms and conditions and submits account$")
	public void _f_nfu_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFedModified(
				Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_F_NFU, PMOA receive email notification$")
	public void _f_nfu_PMOA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario E");
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_F_IAE PMO Administrator logs in$")
	public void _f_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_F_IAE PMOA approve the system account$")
	public void _f_iae_PMOA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail6);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@When("^_F_NFU, PMOA and GSA SA receive email notification$")
	public void _f_nfu_PMOA_and_GSA_SA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario E");
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_F_GSA Security Approver logs in$")
	public void _f_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_F_GSA SA reject the system account$")
	public void _f_gsa_SA_reject_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail6);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
	}

	@When("^_F_NFU, PMOA, GSA SA receive email$")
	public void _f_nfu_PMOA_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		LaunchBrowserUtil.addNonFedEmailAccount(Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		String emailSubject3 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody3 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink3 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink3 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom3 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject3.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// In workflow GSA SA should not receive email when IAE PMOA reject system
	// account including Non-public permissions info
	@Given("^_G_ System Account Manager logs in$")
	public void _g__System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_G_SAM navigates to system account directory page$")
	public void _g_sam_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_G_SAM enters all the system information$")
	public void _g_sam_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail7);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_G_SAM enters all the organization info$")
	public void _g_sam_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_G_SAM enters Non-public permissions info$")
	public void _g_sam_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_G_SAM enters security info$")
	public void _g_sam_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_G_SAM enters authorization info$")
	public void _g_sam_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_G_SAM accepts terms and conditions and submits account$")
	public void _g_sam_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_G_SAM, SAA receive email notification$")
	public void _g_sam_SAA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A.");
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_G_System Account Administrator logs in$")
	public void _g_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_G_SAA navigates to system account directory page$")
	public void _g_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@When("^_G_SAA searches for Pending Review request by SAM and approve it$")
	public void _g_saa_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail7);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		// The comments has been automated in separate scenario
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_G_SAM, SAA and PMOA receive email notification$")
	public void _g_sam_SAA_and_PMOA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A");
		LaunchBrowserUtil.closeThisBrowser();
	}

	@When("^_G_IAE PMO Administrator logs in$")
	public void _g_iae_PMO_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_G_IAE PMOA reject the system account$")
	public void _g_iae_PMOA_reject_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail7);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_PMOA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
	}

	@Then("^_G_SAM, SAA, PMOA receive email$")
	public void _g_sam_SAA_PMOA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_PMOA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_PMOA_ERROR_EMAIL));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(iaePmoAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// H In workflow When SAM submits system account including public permissions
	// info and SAA approves it GSA SA directly receives email
	@Given("^_H_ System Account Manager logs in$")
	public void _h__System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_H_SAM navigates to system account directory page$")
	public void _h_sam_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_H_SAM enters all the system information$")
	public void _h_sam_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail8);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_H_SAM enters all the organization info$")
	public void _h_sam_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_H_SAM enters public permissions info$")
	public void _h_sam_enters_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_H_SAM enters security info$")
	public void _h_sam_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_H_SAM enters authorization info$")
	public void _h_sam_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_H_SAM accepts terms and conditions and submits account$")
	public void _h_sam_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectPublicPermissionsTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_H_SAM, SAA receive email notification$")
	public void _h_sam_SAA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A.");
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_H_System Account Administrator logs in$")
	public void _h_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_H_SAA navigates to system account directory page$")
	public void _h_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@When("^_H_SAA searches for Pending Review request by SAM and approve it$")
	public void _h_saa_searches_for_Pending_Review_request_by_SAM_and_approve_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail8);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		// The comments has been automated in separate scenario
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_H_SAM, SAA, GSA SA receive email notification$")
	public void _h_sam_SAA_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody1.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody2.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAM_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true,
					emailBody3.contains(Constants.MA_EMAIL_SAA_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_H_GSA Security Approver logs in$")
	public void _h_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_H_GSA SA approve the system account$")
	public void _h_gsa_SA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail8);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_H_SAM, SAA and GSA SA receive email$")
	public void _h_sam_SAA_and_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject3.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody3.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom3.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom3.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink3.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink3.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(3, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// In workflow When SAA submits system account including public permissions info
	// GSA SA directly receives email
	@Given("^_I_ System Account Admin logs in$")
	public void _i__System_Account_Admin_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_I_SAA navigates to system account directory page$")
	public void _i_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_I_SAA enters all the system information$")
	public void _i_saa_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail9);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_I_SAA enters all the organization info$")
	public void _i_saa_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_I_SAA enters public permissions info$")
	public void _i_saa_enters_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_I_SAA enters security info$")
	public void _i_saa_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_I_SAA enters authorization info$")
	public void _i_saa_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Given("^_I_SAA accepts terms and conditions and submits account$")
	public void _i_saa_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectPublicPermissionsTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_I_SAA, GSA SA receive email notification$")
	public void _i_saa_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORGSASA_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORGSASA_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORGSASA_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_ACCOUNT_CREATION_FORGSASA_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_I_GSA Security Approver logs in$")
	public void _i_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_I_GSA SA approve the system account$")
	public void _i_gsa_SA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail9);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_I_SAA and GSA SA receive email$")
	public void _i_saa_and_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// In workflow When Non-Fed user submits system account including public
	// permissions info GSA SA directly receives email
	@Given("^_J_ Non-Fed user logs in$")
	public void _j__Non_Fed_user_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NOROLE_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_NOROLE_USER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_J_NFU navigates to system account directory page$")
	public void _j_nfu_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_J_NFU enters all the system information$")
	public void _j_nfu_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail10);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_J_NFU enters all the organization info$")
	public void _j_nfu_enters_all_the_organization_info() throws Exception {
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.NONFED_NOROLE_USER_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.NONFED_NOROLE_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_J_NFU enters public permissions info$")
	public void _j_nfu_enters_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_J_NFU enters security info$")
	public void _j_nfu_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_J_NFU enters authorization info$")
	public void _j_nfu_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Given("^_J_NFU accepts terms and conditions and submits account$")
	public void _j_nfu_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectPublicPermissionsTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailNonFedModified(
				Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_J_NFU, GSA SA receive email notification$")
	public void _j_nfu_GSA_SA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.switchTabs(1);
		LaunchBrowserUtil.navigateBack();
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.addFedEmailAccount(Constants.MA_GMAIL_FED_USERNAME);
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_J_GSA Security Approver logs in$")
	public void _j_gsa_Security_Approver_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_J_GSA SA approve the system account$")
	public void _j_gsa_SA_approve_the_system_account() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail10);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_GSASA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnApproveButton();
	}

	@Then("^_J_NFU and GSA SA receive email$")
	public void _j_nfu_and_GSA_SA_receive_email() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
		String emailSubject1 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody1 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink1 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink1 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom1 = LaunchBrowserUtil.captureToAndFromInEmail();

		LaunchBrowserUtil.addNonFedEmailAccount(Constants.MA_GMAIL_NON_FED_USERNAME, Constants.MA_USERPASS_NONFED);
		String emailSubject2 = LaunchBrowserUtil.captureTitleFromLastEmail(0);
		String emailBody2 = LaunchBrowserUtil.captureEmailMessage(0);
		String applicationLink2 = LaunchBrowserUtil.getApplicationLink();
		String learningCenterLink2 = LaunchBrowserUtil.getLearningCenterLink();
		String emailToAndFrom2 = LaunchBrowserUtil.captureToAndFromInEmail();

		int counter = 0;
		if (emailToAndFrom1.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
//--------------------------------------------
		if (emailToAndFrom2.contains(nonfeduser.toString())) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true,
					emailSubject2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(nonfeduser.toString()), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(gsasecurityapprover.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK));
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// PMO Admin should not receive email when SAM submits system account and SAA
	// reject it
	@Given("^_K_System Account Manager logs in$")
	public void _k_system_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Given("^_K_SAM navigates to system account directory page$")
	public void _k_sam_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();
	}

	@Given("^_K_SAM enters all the system information$")
	public void _k_sam_enters_all_the_system_information() throws Exception {
		NewSystemAccountPage.enterSystemAccountName(uniqSanEmail11);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
	}

	@Given("^_K_SAM enters all the organization info$")
	public void _k_sam_enters_all_the_organization_info() throws Exception {
		NewSystemAccountPage.enterOrgModified(Constants.ORG_GSA);
		NewSystemAccountPage.enterSAAMInOrgInfoModified(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_K_SAM enters Non-public permissions info$")
	public void _k_sam_enters_Non_public_permissions_info() throws Exception {
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_SENSITIVE);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_SENSITIVE);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_K_SAM enters security info$")
	public void _k_sam_enters_security_info() throws Exception {
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston, VA");
		NewSystemAccountPage.enterSecurityOfficialName(ConstantsAccounts.MA_SECURITYOFFICIAL);
		NewSystemAccountPage.enterSecurityOfficialEmail(ConstantsAccounts.MA_SECURITYOFFICIAL_EMAIL);
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_K_SAM enters authorization info$")
	public void _k_sam_enters_authorization_info() throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
	}

	@Then("^_K_SAM accepts terms and conditions and submits account$")
	public void _k_sam_accepts_terms_and_conditions_and_submits_account() throws Exception {
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.MA_GMAIL_FED_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
	}

	@Then("^_K_SAM and SAA receive email notification$")
	public void _k_sam_and_SAA_receive_email_notification() throws Exception {
		logger.info("This step has been automated in scenario A");
		LaunchBrowserUtil.closeBrowsers();
	}

	@When("^_K_System Account Administrator logs in$")
	public void _k_system_Account_Administrator_logs_in() throws Exception {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_K_SAA navigates to system account directory page$")
	public void _k_saa_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
		SystemAccountDirectoryPage.clickOnPendReviewFilterModified();
	}

	@When("^_K_SAA searches for Pending Review request by SAM and reject it$")
	public void _k_saa_searches_for_Pending_Review_request_by_SAM_and_reject_it() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(uniqSanEmail11);
		SystemAccountDirectoryPage.clickOnFirstSearchResult();
		// NewSystemAccountPage.addMessageAndHitEnter(Constants.MA_SAA_ADD_COMMENT);
		NewSystemAccountPage.addDecisionComment(Constants.MA_ADD_DECISIONCOMMENT);
		NewSystemAccountPage.clickOnRejectButton();
	}

	@Then("^_K_SAM, SAA receive email notification$")
	public void _k_sam_SAA_receive_email_notification() throws Exception {
		LaunchBrowserUtil.goToGSAEmailInboxModified(Constants.MA_GMAIL_FED_USERNAME);
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

		int counter = 0;
		if (emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(true, applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT));
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject1.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject1.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody1.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom1.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom1.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink1.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink1.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
// --------------------------------------------
		if (emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAA_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAA_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemAdmin.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		} else if (emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", ""))) {
			// asserting the email subject line
			Assert.assertEquals(true, emailSubject2.contains(Constants.EMAIL_SENT_FROM));
			Assert.assertEquals(true, emailSubject2.contains(Constants.MA_EMAIL_SAM_REJECT_SUBJECT_LINE));

			// asserting email body
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_MESSAGE_LERNINGCENTER));
			Assert.assertEquals(true, emailBody2.contains(Constants.MA_EMAIL_FROM_ENV));
			Assert.assertEquals(emailToAndFrom2.contains(Constants.EMAIL_SENT_FROM_DOMAIN), true);
			Assert.assertEquals(emailToAndFrom2.contains(systemManager.toString().replace("@gsa.gov", "")), true);

			// asserting links
			Assert.assertEquals(applicationLink2.contains(Constants.EMAIL_SA_APPLICATION_LINK_REJECT), true);
			Assert.assertEquals(learningCenterLink2.contains(Constants.EMAIL_SA_LEARNING_CENTER_LINK), true);
			counter++;
		}
		Assert.assertEquals(2, counter);
		afterScenario();
		LaunchBrowserUtil.closeBrowsers();
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
