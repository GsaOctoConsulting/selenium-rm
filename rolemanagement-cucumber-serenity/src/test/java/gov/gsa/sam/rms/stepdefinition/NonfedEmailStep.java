package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.pages.AccountDetailsPage;
import gov.gsa.sam.rms.pages.MyRolesPage;
import gov.gsa.sam.rms.pages.RequestRolePage;
import gov.gsa.sam.rms.pages.RoleRequestPendingPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class NonfedEmailStep {
	private static Logger logger = LoggerFactory.getLogger(EmailStep.class);
	
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
}
