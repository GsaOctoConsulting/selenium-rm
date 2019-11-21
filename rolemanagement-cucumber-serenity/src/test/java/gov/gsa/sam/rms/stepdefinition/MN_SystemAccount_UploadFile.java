package gov.gsa.sam.rms.stepdefinition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
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
	@Given("^_1 sys user enters all the organization info$")
	public void __sys_user_enters_all_the_organization_info() throws Exception {
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

	@Given("^_1 sys user enters permissions info$")
	public void __sys_user_enters_permissions_info() throws Exception {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();

	}

	@Given("^_1 sys user enters security info$")
	public void __sys_user_enters_security_info() throws Exception {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();

	}

	@When("^_1 user enters autohorization info and select one or more files to upload$")
	public void __user_enters_autohorization_info_and_select_one_or_more_files_to_upload() throws Exception {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.browseUpload();

	}

	@Then("^_1 files should be uploaded successfully$")
	public void __files_should_be_uploaded_successfully() throws Exception {
		WebElement value = LaunchBrowserUtil.driver.findElement(By.id("fileName0"));
		String expectedFileName = "sampletestfile.txt";
		String actualFileName = value.getText();
		Assert.assertEquals(expectedFileName, actualFileName);
		logger.info("File Uploaded successfully");

	}

	@Then("^_1 edited date should be displayed in the correct format$")
	public void __edited_date_should_be_displayed_in_the_correct_format() throws Exception {
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

	@Then("^_1 user should be able to delete file$")
	public void __user_should_be_able_to_delete_file() throws Exception {
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
		String expectedfilename2 = "sampletestfile2.txt";
		Assert.assertEquals(expectedfilename1, actualfilename1);
		Assert.assertEquals(expectedfilename2, actualfilename2);
		logger.info("Multiple files are uploaded successfully");
		afterScenario();
		LaunchBrowserUtil.delay(3);
	}

	@Given("^_4 sys user enters all the organization info$")
	public void _4_sys_user_enters_all_the_organization_info() throws Throwable {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		LaunchBrowserUtil.delay(2);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.selectSystemManagerInOrgInfo(ConstantsAccounts.SYSTEM_MANAGER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_4 sys user enters permissions info$")
	public void _4_sys_user_enters_permissions_info() throws Throwable {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_4 sys user enters security info$")
	public void _4_sys_user_enters_security_info() throws Throwable {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@When("^_4 user enters autohorization info and select one or more files to upload$")
	public void _4_user_enters_autohorization_info_and_select_one_or_more_files_to_upload() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.browseUploadDocFile();
	}

	@Then("^_4 files should be uploaded successfully$")
	public void _4_files_should_be_uploaded_successfully() throws Throwable {
		WebElement value = LaunchBrowserUtil.driver.findElement(By.id("fileName0"));
		String expectedFileName = "sampletestdocfile.docx";
		String actualFileName = value.getText();
		Assert.assertEquals(expectedFileName, actualFileName);
		logger.info("File Uploaded successfully");
	}

	@Then("^_4 edited date should be displayed in the correct format$")
	public void _4_edited_date_should_be_displayed_in_the_correct_format() throws Throwable {
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

	@Then("^_4 user should be able to delete file$")
	public void _4_user_should_be_able_to_delete_file() throws Throwable {
		Assert.assertTrue(NewSystemAccountPage.deleteFileValidation().isDisplayed());
		NewSystemAccountPage.deleteFileUpload();
		LaunchBrowserUtil.delay(4);
	}

	@Given("^_5 sys account admin is in the authorization page$")
	public void _5_sys_account_admin_is_in_the_authorization_page() throws Throwable {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
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

	@When("^_5 sys acount admin enters authorization info and uploads invalid file type$")
	public void _5_sys_acount_admin_enters_authorization_info_and_uploads_invalid_file_type() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.invalidFileUpload();
	}

	@Then("^_5 sys account admin should not be able to upload file and error message is displayed$")
	public void _5_sys_account_admin_should_not_be_able_to_upload_file_and_error_message_is_displayed()
			throws Throwable {
		WebElement invalidFile = LaunchBrowserUtil.driver
				.findElement(By.xpath("//div[@class='file-error ng-star-inserted']"));
		String actualFileValue = invalidFile.getText();
		String expectedErrorMsg = "Error: File type not supported";
		Assert.assertEquals(expectedErrorMsg, actualFileValue);
		System.out.println("Error message is displayed for Invalid File type");
		LaunchBrowserUtil.delay(4);
		NewSystemAccountPage.deleteFileUpload();
	}

	@Given("^_6 sys account admin is in the authorization page for ATO upload$")
	public void _6_sys_account_admin_is_in_the_authorization_page_for_ato_upload() throws Throwable {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
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

	@When("^_6 sys account admin enters authorization info and uploads multiple files$")
	public void _6_sys_account_admin_enters_authorization_info_and_uploads_multiple_files() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.clickReviewButton();
	}

	@Then("^_6 sys account admin should be able to upoad multiple files successfully$")
	public void _6_sys_account_admin_should_be_able_to_upoad_multiple_files_successfully() throws Throwable {
		LaunchBrowserUtil.scrollToEnd();
		WebElement filenametext1 = LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[1]/td[1]/a"));
		WebElement filenametext2 = LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[2]/td[1]/a"));
		String actualfilename1 = filenametext1.getText();
		String actualfilename2 = filenametext2.getText();
		String expectedfilename1 = "sampletestfile.txt";
		String expectedfilename2 = "sampletestfile2.txt";
		Assert.assertEquals(expectedfilename1, actualfilename1);
		Assert.assertEquals(expectedfilename2, actualfilename2);
		logger.info("Multiple files are uploaded successfully");
		afterScenario();
		LaunchBrowserUtil.delay(3);
	}

//------------------------------------------------------------------------------------------------------------------------
//Scenario : nonfed should be able to upload an ato file
	@Given("^_7 nonfed user enters all the organization info$")
	public void _7_nonfed_user_enters_all_the_organization_info() throws Throwable {
		// Login and Go to System Account directory page
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		SystemAccountDirectoryPage.clickNewButton();// Click on New button
		// Enter System Information and click next to go to Organization info
		NewSystemAccountPage.enterSystemAccountName(formattedDate);
		NewSystemAccountPage.enterInterfacingSystemName("Test");
		NewSystemAccountPage.enterSystemDescription("Test");
		NewSystemAccountPage.clickNextToGoToOrgInfo();
		// Enter Organization info and click Next to go to Permissions
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.NONFED_USER_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_7 nonfed user enters permissions info$")
	public void _7_nonfed_user_enters_permissions_info() throws Throwable {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_7 nonfed user enters security info$")
	public void _7_nonfed_user_enters_security_info() throws Throwable {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@When("^_7 nonfed user enters autohorization info and select one or more files to upload$")
	public void _7_nonfed_user_enters_autohorization_info_and_select_one_or_more_files_to_upload() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		NewSystemAccountPage.browseUploadDocFile();
	}

	@Then("^_7 nonfed files should be uploaded successfully$")
	public void _7_nonfed_files_should_be_uploaded_successfully() throws Throwable {
		WebElement value = LaunchBrowserUtil.driver.findElement(By.id("fileName0"));
		String expectedFileName = "sampletestdocfile.docx";
		String actualFileName = value.getText();
		Assert.assertEquals(expectedFileName, actualFileName);
		logger.info("File Uploaded successfully");
	}

	@Then("^_7 nonfed edited date should be displayed in the correct format$")
	public void _7_nonfed_edited_date_should_be_displayed_in_the_correct_format() throws Throwable {
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

	@Then("^_7 nonfed user should be able to delete file$")
	public void _7_nonfed_user_should_be_able_to_delete_file() throws Throwable {
		Assert.assertTrue(NewSystemAccountPage.deleteFileValidation().isDisplayed());
		NewSystemAccountPage.deleteFileUpload();
		LaunchBrowserUtil.delay(4);
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
