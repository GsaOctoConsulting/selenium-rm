package gov.gsa.sam.rms.stepdefinition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
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
import net.serenitybdd.core.annotations.findby.By;

public class MN_SystemAccount_UploadFile {

	private static Logger logger = LoggerFactory.getLogger(NewSystemAccountPage.class);
	long epoch = System.currentTimeMillis() / 1000;
	String formattedDate = Long.toString(epoch);

//Scenario: System Manager should be able to upload ATO file
	@Given("^_(\\d+) sys user enters all the organization info$")
	public void __sys_user_enters_all_the_organization_info(int arg1) throws Exception {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();

	}

	@Given("^_(\\d+) sys user enters permissions info$")
	public void __sys_user_enters_permissions_info(int arg1) throws Exception {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();

	}

	@Given("^_(\\d+) sys user enters security info$")
	public void __sys_user_enters_security_info(int arg1) throws Exception {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();

	}

	@When("^_(\\d+) user enters autohorization info and select one or more files to upload$")
	public void __user_enters_autohorization_info_and_select_one_or_more_files_to_upload(int arg1) throws Exception {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.browseUpload();

	}

	@Then("^_(\\d+) files should be uploaded successfully$")
	public void __files_should_be_uploaded_successfully(int arg1) throws Exception {
		WebElement value = LaunchBrowserUtil.driver.findElement(By.id("fileName0"));
		String expectedFileName = "sampletestfile.txt";
		String actualFileName = value.getText();
		Assert.assertEquals(expectedFileName, actualFileName);
		logger.info("File Uploaded successfully");

	}

	@Then("^_(\\d+) edited date should be displayed in the correct format$")
	public void __edited_date_should_be_displayed_in_the_correct_format(int arg1) throws Exception {
		LaunchBrowserUtil.delay(2);
		WebElement updatedFileDate = LaunchBrowserUtil.driver.findElement(NewSystemAccountPageLocator.UPDATED_DATE);
		String actualDateValue = updatedFileDate.getText();
		logger.info("Date found on the page is-- " + actualDateValue);
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		Date dateobj = new Date();
		logger.info("Current date is-- " + df.format(dateobj));
		String expectedDateValue = df.format(dateobj);// current date
		Assert.assertEquals(expectedDateValue, actualDateValue);
		logger.info("Long Date format is displayed successfully");
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_(\\d+) user should be able to delete file$")
	public void __user_should_be_able_to_delete_file(int arg1) throws Exception {
		Assert.assertTrue(NewSystemAccountPage.deleteFileValidation().isDisplayed());
		NewSystemAccountPage.deleteFileUpload();
		LaunchBrowserUtil.delay(4);
	}

//--------------------------------------------------------------------------------------------------------------------------	
//Scenario: User should not be able to upload a file with unacceptable file extension	
	@Given("^_2 sys manager logs in and opens a system account$")
	public void _2_sys_manager_logs_in_and_opens_a_system_account() throws Throwable {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
		// -------------------------------------------------------------------

		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
		// --------------------------------------------------------------------------
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();

	}

	@When("^_2 sys manager enters authorization info and uploads invalid file type$")
	public void _2_sys_manager_enters_authorization_info_and_uploads_invalid_file_type() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.invalidFileUpload();
	}

	@Then("^_2 error message should be displayed for invalid file type$")
	public void _2_error_message_should_be_displayed_for_invalid_file_type() throws Throwable {
		WebElement invalidFile = LaunchBrowserUtil.driver
				.findElement(By.xpath("//div[@class='file-error ng-star-inserted']"));
		String actualFileValue = invalidFile.getText();
		String expectedErrorMsg = "Error: File type not supported";
		Assert.assertEquals(expectedErrorMsg, actualFileValue);
		System.out.println("Error message is displayed for Invalid File type");
		LaunchBrowserUtil.delay(4);
		NewSystemAccountPage.deleteFileUpload();
	}

//-------------------------------------------------------------------------------------------------------------------------
//Scenario: User should be able to upload multiple files
	@Given("^_3 sys manager logs in and opens a system account$")
	public void _3_sys_manager_logs_in_and_opens_a_system_account() throws Throwable {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
		// -------------------------------------------------------------------

		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
		// --------------------------------------------------------------------------
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@When("^_3 sys manager enters authorization info and uploads multiple files$")
	public void _3_sys_manager_enters_authorization_info_and_uploads_multiple_files() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.clickReviewButton();
	}

	@Then("^_3 multiple files should be uploaded successfully$")
	public void _3_multiple_files_should_be_uploaded_successfully() throws Throwable {
		LaunchBrowserUtil.scrollToEnd();
		WebElement filenametext1 = LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[1]/td[1]/a"));
		WebElement filenametext2 = LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[2]/td[1]/a"));
		String actualfilename1 = filenametext1.getText();
		String actualfilename2 = filenametext2.getText();
		String expectedfilename1 = "sampletestfile.txt";
		String expectedfilename2="sampletestfile2.txt";
		Assert.assertEquals(expectedfilename1, actualfilename1);
		Assert.assertEquals(expectedfilename2, actualfilename2);
		logger.info("Multiple files are uploaded successfully");
		afterScenario();
		LaunchBrowserUtil.delay(3);
	}

//------------------------------------------------------------------------------------------------------------------------
//Scenario : System Account Admin should be able to upload ATO file
	@Given("^_(\\d+) sys account admin enters all the organization info$")
	public void __sys_account_admin_enters_all_the_organization_info(int arg1) throws Exception {

		// Login as System Account Administrator and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		NewSystemAccountPage.enterSystemAccountName(NewSystemAccountPage.uniqueSAN());// Enter Unique system account
																						// name
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		LaunchBrowserUtil.driver.findElement(By.id("cws-agency-requiredpicker")).sendKeys(Constants.ORG_GSA);
		LaunchBrowserUtil.driver.findElement(By.xpath("//*[contains(text(),'4700 - GENERAL SERVICES ADMINISTRATION')]"))
				.click();
		NewSystemAccountPage.selectSystemAdminInOrgInfo("Manasa");
		NewSystemAccountPage.selectSystemManagerInOrgInfo("mahammad.abasguliyev+gsasm@gsa.gov");
		NewSystemAccountPage.clickNextToGoToPermissions();

	}

	@Given("^_(\\d+) sys account admin user enters permissions info$")
	public void __sys_account_admin_user_enters_permissions_info(int arg1) throws Exception {

		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();

	}

	@Given("^_(\\d+) sys account admin enters security info$")
	public void __sys_account_admin_enters_security_info(int arg1) throws Exception {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();

	}

	@When("^_(\\d+) sys account admin enters autohorization info and select one or more files to upload$")
	public void __sys_account_admin_enters_autohorization_info_and_select_one_or_more_files_to_upload(int arg1)
			throws Exception {
		NewSystemAccountPage.browseUpload();
	}

	@Then("^_(\\d+) ATO files should be uploaded successfully$")
	public void __ATO_files_should_be_uploaded_successfully(int arg1) throws Exception {
		WebElement value = LaunchBrowserUtil.driver.findElement(By.id("fileName0"));
		String expectedFileName = "eula.1028.txt";
		String actualFileName = value.getText();
		Assert.assertEquals(expectedFileName, actualFileName);
		System.out.println("File Uploaded successfully");

	}

	@Then("^_(\\d+) edited date should be displayed in correct format$")
	public void __edited_date_should_be_displayed_in_correct_format(int arg1) throws Exception {
		LaunchBrowserUtil.delay(2);
		WebElement updatedFileDate = LaunchBrowserUtil.driver.findElement(NewSystemAccountPageLocator.UPDATED_DATE);
		String actualDateValue = updatedFileDate.getText();
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		String expectedDateValue = df.format(dateobj);// current date
		Assert.assertEquals(expectedDateValue, actualDateValue);
		System.out.println("Long Date format is displayed successfully");
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_(\\d+) user should be able to delete the file$")
	public void __user_should_be_able_to_delete_the_file(int arg1) throws Exception {
		NewSystemAccountPage.deleteFileUpload();
		LaunchBrowserUtil.delay(4);
	}

//----------------------------------------------------------------------------------------------------------------
//Scenario: System Account Admin should not be able to upload a file with unacceptable file extension
	@Given("^_(\\d+) sys account admin is in the authorization page$")
	public void __sys_account_admin_is_in_the_authorization_page(int arg1) throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
	}

	@When("^_(\\d+) sys acount admin enters authorization info and uploads invalid file type$")
	public void __sys_acount_admin_enters_authorization_info_and_uploads_invalid_file_type(int arg1) throws Exception {
		NewSystemAccountPage.invalidFileUpload();
	}

	@Then("^_(\\d+) sys account admin should not be able to upload file and error message is displayed$")
	public void __sys_account_admin_should_not_be_able_to_upload_file_and_error_message_is_displayed(int arg1)
			throws Exception {
		WebElement invalidFile = LaunchBrowserUtil.driver
				.findElement(By.xpath("//div[@class='file-error ng-star-inserted']"));
		String actualFileValue = invalidFile.getText();
		String expectedErrorMsg = "Error: File type not supported";
		Assert.assertEquals(expectedErrorMsg, actualFileValue);
		System.out.println("Error message is displayed for Invalid File type");
		LaunchBrowserUtil.delay(4);
		NewSystemAccountPage.deleteFileUpload();
	}

//--------------------------------------------------------------------------------------------------------------------------------------------
//Scenario: System Account Administrator should be able to upload multiple files
	@Given("^_(\\d+) sys account admin is in the authorization page for ATO upload$")
	public void __sys_account_admin_is_in_the_authorization_page_for_ATO_upload(int arg1) throws Exception {
		NewSystemAccountPage.certifyCorrectInformation();
	}

	@When("^_(\\d+) sys account admin enters authorization info and uploads multiple files$")
	public void __sys_account_admin_enters_authorization_info_and_uploads_multiple_files(int arg1) throws Exception {
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(20);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.clickReviewButton();
		LaunchBrowserUtil.delay(3);
	}

	@Then("^_(\\d+) sys account admin should be able to upoad multiple files successfully$")
	public void __sys_account_admin_should_be_able_to_upoad_multiple_files_successfully(int arg1) throws Exception {
		LaunchBrowserUtil.scrollToEnd();
		WebElement reviewPagevalue = LaunchBrowserUtil.driver
				.findElement(By.xpath("//div[@class = 'sam-ui segment']//a[@class= \"ng-star-inserted\"]"));
		String actualReviewValue = reviewPagevalue.getText();
		String expectedvalue = "eula.1028.txt";
		Assert.assertEquals(expectedvalue, actualReviewValue);
		System.out.println("Multiple files are uploaded successfully");
		afterScenario();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.quit();
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
