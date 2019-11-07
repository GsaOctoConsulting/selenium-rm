package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

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

public class MN_SystemAccount_UploadFileReorder 
{
//Scenario: system account manager should be able reorder uploaded files
	long epoch = System.currentTimeMillis() / 1000;
	 String formattedDate = Long.toString(epoch);
	@Given("^_(\\d+) system account manager enters all the organization info$")
	public void __system_account_manager_enters_all_the_organization_info(int arg1) throws Exception 
	{
		//Login and Go to System Account directory page 
				SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
				T1WorkspacePage.goToSystemAccountDirectoryPage();
				SystemAccountDirectoryPage.clickNewButton();// Click on New button
				//Enter System Information and click next to go to Organization info
				NewSystemAccountPage.enterSystemAccountName(formattedDate);
				NewSystemAccountPage.enterInterfacingSystemName("Test");
				NewSystemAccountPage.enterSystemDescription("Test");
				NewSystemAccountPage.clickNextToGoToOrgInfo();
				//Enter Organization info and click Next to go to Permissions
				LaunchBrowserUtil.driver.findElement(By.id("cws-agency-requiredpicker")).sendKeys(Constants.ORG_GSA);
				LaunchBrowserUtil.driver.findElement(By.xpath("//*[contains(text(),'4700 - GENERAL SERVICES ADMINISTRATION')]")).click();
				NewSystemAccountPage.selectSystemAdminInOrgInfo("Manasa");
				NewSystemAccountPage.selectSystemManagerInOrgInfo("mahammad.abasguliyev+gsasm@gsa.gov");
				NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_(\\d+) system account manager enters permissions info$")
	public void __system_account_manager_enters_permissions_info(int arg1) throws Exception 
	{
		//Enter information in Permission page and click next to go to Security
				NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
				NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
				LaunchBrowserUtil.scrollAllTheWayDown();
				NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
				NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_(\\d+) system account manager enters security info$")
	public void __system_account_manager_enters_security_info(int arg1) throws Exception 
	{
		//Enter Security info and click next to go to Authorization page
				NewSystemAccountPage.enterIPaddress("192.168.1.1");
				NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
				NewSystemAccountPage.enterPhysicalLocation("Reston VA");
				NewSystemAccountPage.enterSecurityOfficialName("Tester");
				NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
				NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_(\\d+) system account manager enters autohorization info and select files to upload$")
	public void __system_account_manager_enters_autohorization_info_and_select_files_to_upload(int arg1) throws Exception 
	{
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(20);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);
		
	}

	@When("^_(\\d+) user reorders the files$")
	public void __user_reorders_the_files(int arg1) throws Exception
	{
		NewSystemAccountPage.reorderFileUpload();
		NewSystemAccountPage.clickReviewButton(); 
	}

	@Then("^_(\\d+) attachements should be reordered$")
	public void __attachements_should_be_reordered(int arg1) throws Exception 
	{
		LaunchBrowserUtil.scrollToEnd();
		WebElement reviewPagevalue = LaunchBrowserUtil.driver.findElement(By.xpath("//div[@class = 'sam-ui segment']//a[@class= \"ng-star-inserted\"]"));
		String actualReviewValue = reviewPagevalue.getText();
		String expectedvalue = "eula.1031.txt";
		Assert.assertEquals(expectedvalue, actualReviewValue);
		System.out.println("Uploaded fiels are reordered successfully");
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.quit();
	    
	}
//-------------------------------------------------------------------------------------------------------------------------
	//Scenario: system account admin should be able to reorder uploaded files
	@Given("^_(\\d+) system account admin enters all the organization info$")
	public void __system_account_admin_enters_all_the_organization_info(int arg1) throws Exception 
	{
		//Login as System Account Administrator and Go to System Account directory page 
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
		ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		NewSystemAccountPage.enterSystemAccountName(NewSystemAccountPage.uniqueSAN());//Enter Unique system account name
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		//Enter Organization info and click Next to go to Permissions
		LaunchBrowserUtil.driver.findElement(By.id("cws-agency-requiredpicker")).sendKeys(Constants.ORG_GSA);
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[contains(text(),'4700 - GENERAL SERVICES ADMINISTRATION')]")).click();
		NewSystemAccountPage.selectSystemAdminInOrgInfo("Manasa");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("mahammad.abasguliyev+gsasm@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_(\\d+) system account admin enters permissions info$")
	public void __system_account_admin_enters_permissions_info(int arg1) throws Exception 
	{
		//Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_(\\d+) system account admin enters security info$")
	public void __system_account_admin_enters_security_info(int arg1) throws Exception 
	{
		//Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_(\\d+) system account admin enters autohorization info and select files to upload$")
	public void __system_account_admin_enters_autohorization_info_and_select_files_to_upload(int arg1) throws Exception
	{
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(20);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);
		
	}

	@When("^_(\\d+) system account admin reorders the files$")
	public void __system_account_admin_reorders_the_files(int arg1) throws Exception 
	{
		NewSystemAccountPage.reorderFileUpload();
		NewSystemAccountPage.clickReviewButton(); 
	}

	@Then("^_(\\d+) attachements should be reordered for system account files$")
	public void __attachements_should_be_reordered_for_system_account_files(int arg1) throws Exception 
	{
		LaunchBrowserUtil.scrollToEnd();
		WebElement reviewPagevalue = LaunchBrowserUtil.driver.findElement(By.xpath("//div[@class = 'sam-ui segment']//a[@class= \"ng-star-inserted\"]"));
		String actualReviewValue = reviewPagevalue.getText();
		String expectedvalue = "eula.1031.txt";
		Assert.assertEquals(expectedvalue, actualReviewValue);
		System.out.println("Uploaded files are reordered successfully");
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.quit();
	}
		
}
