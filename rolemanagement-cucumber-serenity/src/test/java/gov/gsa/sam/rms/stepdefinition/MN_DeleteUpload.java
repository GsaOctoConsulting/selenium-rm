package gov.gsa.sam.rms.stepdefinition;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;

import cucumber.api.java.en.And;

//import org.junit.Assert;
//import org.openqa.selenium.WebElement;

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
import net.serenitybdd.core.annotations.findby.By;

public class MN_DeleteUpload {
	public final static String sysActName = NewSystemAccountPage.uniqueSAN();
	public final static String sysActName2 = NewSystemAccountPage.uniqueSAN();

	@Given("^_1du user login as Sys Account Admin and create a new account$")
	public void __user_login_as_Sys_Account_Admin_and_create_a_new_account() throws Exception {
		// Login as System Account Administrator and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);

		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(sysActName);// Enter Unique system account name
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_1du user enters public permission information$")
	public void __user_enters_public_permission_information() throws Exception {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();

	}

	@Given("^_1du user uploads a file enters OTP from email and submit$")
	public void __user_uploads_a_file_enters_OTP_from_email_and_submit() throws Exception {
		NewSystemAccountPage.browseUpload();
		// check for Virus scan completion
		String stausComplete = "Ready";
		Assert.assertEquals(stausComplete, NewSystemAccountPage.virusScan());
		LaunchBrowserUtil.delay(3);
		NewSystemAccountPage.certifyCorrectInformation();
		// Click on Review button and Submit
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		// Select all terms of use and enter OTP
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		// Submit the account
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.driver.quit();
	}

	@Given("^_1du user login as Security Approver$")
	public void __user_login_as_Security_Approver() throws Exception {
		// Login as Security Approver and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Given("^_1du select Pending Approval status$")
	public void __select_Pending_Approval_status() throws Exception {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();

	}

	@Given("^_1du user enters the keyword and search for the account$")
	public void __user_enters_the_keyword_and_search_for_the_account() throws Exception {
		SystemAccountDirectoryPage.searchByKeyword(sysActName);
	}

	@When("^_1du user go to the request details and check the Authorization$")
	public void __user_go_to_the_request_details_and_check_the_Authorization() throws Exception {
		SystemAccountDirectoryPage.accountFound(sysActName, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.delay(5);
		// LaunchBrowserUtil.scrollToEnd();
		LaunchBrowserUtil.scrollToMiddle();

	}

	@Then("^_1du Security Approver should not be able to delete upload files$")
	public void __Security_Approver_should_not_be_able_to_delete_upload_files() throws Exception {
		assertTrue(isElementNotPresent());
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.driver.quit();
	}

	// Method to validate non-existence of the element
	public boolean isElementNotPresent() {
		try {
			LaunchBrowserUtil.driver.findElement(NewSystemAccountPageLocator.DELETE_UPLOAD);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	@Given("^_2du user login as Sys Account Admin and create a new account$")
	public void _2du_user_login_as_sys_account_admin_and_create_a_new_account() throws Throwable {
		// Login as System Account Administrator and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);

		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(sysActName2);// Enter Unique system account name
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_2du user enters public permission information$")
	public void _2du_user_enters_public_permission_information() throws Throwable {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_2du user uploads a file enters OTP from email and submit$")
	public void _2du_user_uploads_a_file_enters_otp_from_email_and_submit() throws Throwable {
		NewSystemAccountPage.browseUpload();
		// check for Virus scan completion
		String stausComplete = "Ready";
		Assert.assertEquals(stausComplete, NewSystemAccountPage.virusScan());
		LaunchBrowserUtil.delay(3);
		NewSystemAccountPage.certifyCorrectInformation();
		// Click on Review button and Submit
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		// Select all terms of use and enter OTP
		NewSystemAccountPage.selectAllTermsOfUse();
		LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmail(Constants.GMAIL_USERNAME);
		NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
		// Submit the account
		NewSystemAccountPage.clickContinueOnTermsOfUse();
		NewSystemAccountPage.clickSubmitOnTermsOfUser();
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.driver.quit();
	}

	@And("^_2du user login as IAE pmo admin$")
	public void _2du_user_login_as_iae_pmo_admin() throws Throwable {
		// Login as Security Approver and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@And("^_2du select Pending Approval status$")
	public void _2du_select_pending_approval_status() throws Throwable {
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
	}

	@And("^_2du user enters the keyword and search for the account$")
	public void _2du_user_enters_the_keyword_and_search_for_the_account() throws Throwable {
		SystemAccountDirectoryPage.searchByKeyword(sysActName2);
	}

	@When("^_2du user go to the request details and check the Authorization$")
	public void _2du_user_go_to_the_request_details_and_check_the_authorization() throws Throwable {
		SystemAccountDirectoryPage.accountFound(sysActName2, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA,
				Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.delay(5);
		// LaunchBrowserUtil.scrollToEnd();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_2du pmo admin should not be able to delete upload files$")
	public void _2du_pmo_admin_should_not_be_able_to_delete_upload_files() throws Throwable {
		assertTrue(isElementNotPresent());
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.driver.quit();
	}

	// -------------------------------------------------------

	// Method to validate non-existence of the element
	public boolean elementNotPresent() {
		try {
			LaunchBrowserUtil.driver.findElement(NewSystemAccountPageLocator.DELETE_UPLOAD);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

}
