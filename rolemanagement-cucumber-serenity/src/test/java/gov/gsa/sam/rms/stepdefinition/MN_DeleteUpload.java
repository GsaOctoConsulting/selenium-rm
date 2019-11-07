package gov.gsa.sam.rms.stepdefinition;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;

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

public class MN_DeleteUpload 
{
	public final static String  sysActName = NewSystemAccountPage.uniqueSAN();

	@Given("^_(\\d+) user login as Sys Account Admin and create a new account$")
	public void __user_login_as_Sys_Account_Admin_and_create_a_new_account(int arg1) throws Exception 
	{
		//Login as System Account Administrator and Go to System Account directory page 
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);

			T1WorkspacePage.goToSystemAccountDirectoryPage();
			SystemAccountDirectoryPage.clickNewButton();// Click on New button
			//Enter System Information and click next to go to Organization info
			NewSystemAccountPage.enterSystemAccountName(sysActName);//Enter Unique system account name
			NewSystemAccountPage.enterInterfacingSystemName("Test");
			NewSystemAccountPage.enterSystemDescription("Test");
			NewSystemAccountPage.clickNextToGoToOrgInfo();
			//Enter Organization info and click Next to go to Permissions
			LaunchBrowserUtil.driver.findElement(By.id("cws-agency-requiredpicker")).sendKeys(Constants.ORG_GSA);
			LaunchBrowserUtil.driver.findElement(By.xpath("//*[contains(text(),'4700 - GENERAL SERVICES ADMINISTRATION')]")).click();
			NewSystemAccountPage.enterSAAMInOrgInfoModified("Manasa");
			NewSystemAccountPage.selectSystemManagerInOrgInfo("mahammad.abasguliyev+gsasm@gsa.gov");
			NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_(\\d+) user enters public permission information$")
	public void __user_enters_public_permission_information(int arg1) throws Exception 
	{
		//Enter information in Permission page and click next to go to Security
				NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
				NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
				LaunchBrowserUtil.scrollAllTheWayDown();
				NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
				NewSystemAccountPage.clickNextToGoToSecurity();
				//Enter Security info and click next to go to Authorization page
				NewSystemAccountPage.enterIPaddress("192.168.1.1");
				NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
				NewSystemAccountPage.enterPhysicalLocation("Reston VA");
				NewSystemAccountPage.enterSecurityOfficialName("Tester");
				NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
				NewSystemAccountPage.clickNextToGoToAuthorization();
				
	}

	@Given("^_(\\d+) user uploads a file enters OTP from email and submit$")
	public void __user_uploads_a_file_enters_OTP_from_email_and_submit(int arg1) throws Exception 
	{
		NewSystemAccountPage.browseUpload();
		//check for Virus scan completion
		String stausComplete = "Ready";
		Assert.assertEquals(stausComplete, NewSystemAccountPage.virusScan());
		LaunchBrowserUtil.delay(3);
		NewSystemAccountPage.certifyCorrectInformation();
		//Click on Review button and Submit
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.scrollUp();
		NewSystemAccountPage.clickSubmit();
		//Select all terms of use and enter OTP
		NewSystemAccountPage.selectAllTermsOfUse();
	    LaunchBrowserUtil.scrollAllTheWayDown();
		String otp = LaunchBrowserUtil.getOtpForSystemAccountFromEmailModified(Constants.GMAIL_USERNAME);
	    NewSystemAccountPage.enterOtpOnTermsOfUser(otp);
	    //Submit the account
	    NewSystemAccountPage.clickContinueOnTermsOfUse();
	    NewSystemAccountPage.clickSubmitOnTermsOfUser();
	    LaunchBrowserUtil.delay(5);
	    LaunchBrowserUtil.driver.quit();
	}

	@Given("^_(\\d+) user login as Security Approver$")
	public void __user_login_as_Security_Approver(int arg1) throws Exception 
	{
		//Login as Security Approver and Go to System Account directory page 
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Given("^_(\\d+) select Pending Approval status$")
	public void __select_Pending_Approval_status(int arg1) throws Exception 
	{
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		
	}

	@Given("^_(\\d+) user enters the keyword and search for the account$")
	public void __user_enters_the_keyword_and_search_for_the_account(int arg1) throws Exception 
	{
		SystemAccountDirectoryPage.searchByKeyword(sysActName);
	}

	@When("^_(\\d+) user go to the request details and check the Authorization$")
	public void __user_go_to_the_request_details_and_check_the_Authorization(int arg1) throws Exception 
	{
		SystemAccountDirectoryPage.accountFound(sysActName, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.delay(5);
		//LaunchBrowserUtil.scrollToEnd();
		LaunchBrowserUtil.scrollToMiddle();
	    
	}

	@Then("^_(\\d+) Security Approver should not be able to delete upload files$")
	public void __Security_Approver_should_not_be_able_to_delete_upload_files(int arg1) throws Exception 
	{
		assertTrue(isElementNotPresent());
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.driver.quit();
	 }

	//Method to validate non-existence of the element
	public boolean isElementNotPresent() 
	{
	    try 
	    {
	    	LaunchBrowserUtil.driver.findElement(NewSystemAccountPageLocator.DELETE_UPLOAD);
	    	return false;
	    }
	    catch (Exception e) {
	        return true;
	    }
	}

	@Given("^_(\\d+) user login as IAE PMO$")
	public void __user_login_as_IAE_PMO(int arg1) throws Exception 
	{
		//Login as IAE PMO and Go to System Account directory page 
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
	}

	@Given("^_(\\d+)_user select Pending Approval status$")
	public void ___user_select_Pending_Approval_status(int arg1) throws Exception 
	{
		SystemAccountDirectoryPage.clickPendingApprovalFilter();
		
	}

	@Given("^_(\\d+)_user go to request details and check the Authorization$")
	public void ___user_go_to_request_details_and_check_the_Authorization(int arg1) throws Exception
	{
		SystemAccountDirectoryPage.searchByKeyword(sysActName);
		SystemAccountDirectoryPage.accountFound(sysActName, Constants.STATUS_PENDING_APPROVAL, Constants.ORG_GSA, Constants.DOMAIN_CONTRACT_OPPORTUNITIES, Constants.GO_TO_REQUEST_DETAILS);
		LaunchBrowserUtil.delay(5);
		//LaunchBrowserUtil.scrollToEnd();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_(\\d+)_IAE PMO should not be able to delete upload files$")
	public void ___IAE_PMO_should_not_be_able_to_delete_upload_files(int arg1) throws Exception 
	{
		assertTrue(elementNotPresent());
		LaunchBrowserUtil.delay(5);
		LaunchBrowserUtil.driver.quit();
	 }

	//Method to validate non-existence of the element
	public boolean elementNotPresent() 
	{
	    try 
	    {
	    	LaunchBrowserUtil.driver.findElement(NewSystemAccountPageLocator.DELETE_UPLOAD);
	    	return false;
	    }
	    catch (Exception e) {
	        return true;
	    } 
	}




	
	
}
