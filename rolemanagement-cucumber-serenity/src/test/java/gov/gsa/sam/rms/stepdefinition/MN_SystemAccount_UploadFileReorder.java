package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.pages.NewSystemAccountPage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import net.serenitybdd.core.annotations.findby.By;

public class MN_SystemAccount_UploadFileReorder {
//Scenario: system account manager should be able reorder uploaded files
	long epoch = System.currentTimeMillis() / 1000;
	String formattedDate = Long.toString(epoch);
	private static Logger logger = LoggerFactory.getLogger(MN_SystemAccount_UploadFileReorder.class);

	@Given("^_1 system account manager enters all the organization info$")
	public void __system_account_manager_enters_all_the_organization_info() throws Exception {
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
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_1 system account manager enters permissions info$")
	public void __system_account_manager_enters_permissions_info() throws Exception {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_1 system account manager enters security info$")
	public void __system_account_manager_enters_security_info() throws Exception {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_1 system account manager enters autohorization info and select files to upload$")
	public void __system_account_manager_enters_autohorization_info_and_select_files_to_upload() throws Exception {
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);

	}

	@When("^_1 user reorders the files$")
	public void __user_reorders_the_files() throws Exception {
		NewSystemAccountPage.reorderFileUpload();
		NewSystemAccountPage.clickReviewButton();
	}

	@Then("^_1 attachements should be reordered$")
	public void __attachements_should_be_reordered() throws Exception {
		LaunchBrowserUtil.scrollToEnd();
		WebElement filenametext1 = LaunchBrowserUtil.driver.get().findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[1]/td[1]/a"));
		WebElement filenametext2 = LaunchBrowserUtil.driver.get().findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[2]/td[1]/a"));
		String actualfilename1 = filenametext1.getText();
		String actualfilename2 = filenametext2.getText();
		String expectedfilename1 = "sampletestfile2.txt";
		String expectedfilename2 = "sampletestfile.txt";
		Assert.assertEquals(expectedfilename1, actualfilename1);
		Assert.assertEquals(expectedfilename2, actualfilename2);
		logger.info("File Reorder success");
		afterScenario();
		LaunchBrowserUtil.delay(3);

	}

//-------------------------------------------------------------------------------------------------------------------------
	// Scenario: system account admin should be able to reorder uploaded files
	@Given("^_2 system account admin enters all the organization info$")
	public void __system_account_admin_enters_all_the_organization_info() throws Exception {
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
		NewSystemAccountPage.selectOrgInOrgInfo(Constants.ORG_GSA);
		NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1);
		NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@Given("^_2 system account admin enters permissions info$")
	public void __system_account_admin_enters_permissions_info() throws Exception {
		// Enter information in Permission page and click next to go to Security
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
		NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
		LaunchBrowserUtil.scrollAllTheWayDown();
		NewSystemAccountPage.selectFIPSCategorization(NewSystemAccountPageLocator.FIPS_LOW);
		NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@Given("^_2 system account admin enters security info$")
	public void __system_account_admin_enters_security_info() throws Exception {
		// Enter Security info and click next to go to Authorization page
		NewSystemAccountPage.enterIPaddress("192.168.1.1");
		NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
		NewSystemAccountPage.enterPhysicalLocation("Reston VA");
		NewSystemAccountPage.enterSecurityOfficialName("Tester");
		NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
		NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@Given("^_2 system account admin enters autohorization info and select files to upload$")
	public void __system_account_admin_enters_autohorization_info_and_select_files_to_upload() throws Exception {
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);

	}

	@When("^_2 system account admin reorders the files$")
	public void __system_account_admin_reorders_the_files() throws Exception {
		NewSystemAccountPage.reorderFileUpload();
		NewSystemAccountPage.clickReviewButton();
	}

	@Then("^_2 attachements should be reordered for system account files$")
	public void __attachements_should_be_reordered_for_system_account_files() throws Exception {
		LaunchBrowserUtil.scrollToEnd();
		WebElement filenametext1 = LaunchBrowserUtil.driver.get().findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[1]/td[1]/a"));
		WebElement filenametext2 = LaunchBrowserUtil.driver.get().findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[2]/td[1]/a"));
		String actualfilename1 = filenametext1.getText();
		String actualfilename2 = filenametext2.getText();
		String expectedfilename1 = "sampletestfile2.txt";
		String expectedfilename2 = "sampletestfile.txt";
		Assert.assertEquals(expectedfilename1, actualfilename1);
		Assert.assertEquals(expectedfilename2, actualfilename2);
		logger.info("File Reorder success");
		afterScenario();
		LaunchBrowserUtil.delay(3);
	}

	@Given("^_3 nonfed user enters all the organization info$")
	public void _3_nonfed_user_enters_all_the_organization_info() throws Throwable {
		
				SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
						ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_FED);
				T1WorkspacePage.goToSystemAccountDirectoryPage();
				SystemAccountDirectoryPage.clickNewButton();// Click on New button
				NewSystemAccountPage.enterSystemAccountName(NewSystemAccountPage.uniqueSAN());// Enter Unique system account
																								// name
				NewSystemAccountPage.enterInterfacingSystemName("Test");
				NewSystemAccountPage.enterSystemDescription("Test");
				NewSystemAccountPage.clickNextToGoToOrgInfo();
				// Enter Organization info and click Next to go to Permissions
				NewSystemAccountPage.selectSystemAdminInOrgInfo(ConstantsAccounts.NONFED_USER_1);
				NewSystemAccountPage.clickNextToGoToPermissions();
	}

	@And("^_3 nonfed user enters permissions info$")
	public void _3_nonfed_user_enters_permissions_info() throws Throwable {
		// Enter information in Permission page and click next to go to Security
				NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_READ_PUBLIC);
				NewSystemAccountPage.clickPermission(NewSystemAccountPageLocator.CO_WRITE_PUBLIC);
				LaunchBrowserUtil.scrollAllTheWayDown();
				NewSystemAccountPage.clickNextToGoToSecurity();
	}

	@And("^_3 nonfed user enters security info$")
	public void _3_nonfed_user_enters_security_info() throws Throwable {
		// Enter Security info and click next to go to Authorization page
				NewSystemAccountPage.enterIPaddress("192.168.1.1");
				NewSystemAccountPage.selectTypeConnection(NewSystemAccountPageLocator.REST_APIS);
				NewSystemAccountPage.enterPhysicalLocation("Reston VA");
				NewSystemAccountPage.enterSecurityOfficialName("Tester");
				NewSystemAccountPage.enterSecurityOfficialEmail("tester@test.com");
				NewSystemAccountPage.clickNextToGoToAuthorization();
	}

	@And("^_3 nonfed user enters autohorization info and select files to upload$")
	public void _3_nonfed_user_enters_autohorization_info_and_select_files_to_upload() throws Throwable {
		NewSystemAccountPage.uploadMultipleFiles();
		LaunchBrowserUtil.delay(5);
		NewSystemAccountPage.certifyCorrectInformation();
		LaunchBrowserUtil.delay(5);
	}

	@When("^_3 nonfed user reorders the files$")
	public void _3_nonfed_user_reorders_the_files() throws Throwable {
		NewSystemAccountPage.reorderFileUpload();
		NewSystemAccountPage.clickReviewButton();
	}

	@Then("^_3 attachements should be reordered for nonfed user$")
	public void _3_attachements_should_be_reordered_for_nonfed_user() throws Throwable {
		LaunchBrowserUtil.scrollToEnd();
		WebElement filenametext1 = LaunchBrowserUtil.driver.get().findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[1]/td[1]/a"));
		WebElement filenametext2 = LaunchBrowserUtil.driver.get().findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div/div[5]/sam-fieldset-wrapper/div/fieldset/sam-label-wrapper[1]/div/sam-ato-download/div/div/table/tbody/tr[2]/td[1]/a"));
		String actualfilename1 = filenametext1.getText();
		String actualfilename2 = filenametext2.getText();
		String expectedfilename1 = "sampletestfile2.txt";
		String expectedfilename2 = "sampletestfile.txt";
		Assert.assertEquals(expectedfilename1, actualfilename1);
		Assert.assertEquals(expectedfilename2, actualfilename2);
		logger.info("File Reorder success");
		afterScenario();
		LaunchBrowserUtil.delay(3);
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
