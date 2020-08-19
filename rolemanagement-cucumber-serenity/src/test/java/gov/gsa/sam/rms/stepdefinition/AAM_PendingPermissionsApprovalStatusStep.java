package gov.gsa.sam.rms.stepdefinition;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;

public class AAM_PendingPermissionsApprovalStatusStep {

	private static Logger logger = LoggerFactory.getLogger(AAM_PendingPermissionsApprovalStatusStep.class);

	public static String expectedStatus = "Pending Permissions Approval";
	public static String expectedFilter = "Pending Permissions Approval";

	// IAE PMO Administrator should be able to conform Pending Permissions Approval
	// status appear under Status section
	@Given("^_G IAE PMO Administrator logs in$")
	public void _g_IAE_PMO_Administrator_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_G IAE_PMOA navigates to system account directory page$")
	public void _g_IAE_PMOA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_G IAE_PMOA confirm Pending Permissions Approval appear under Status section$")
	public void _g_IAE_PMOA_confirm_Pending_Permissions_Approval_appear_under_Status_section() throws Exception {
		Assert.assertEquals(expectedStatus, SystemAccountDirectoryPage.validatePendingPermissionsApprovalStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// IAE PMO Administrator should be able to use filter to search for accounts
	// under Pending Permissions Approval status
	@Given("^_H IAE PMO Administrator logs in$")
	public void _h_IAE_PMO_Administrator_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAE_PMO_ADMINISTRATOR, Constants.USERPASS,
				ConstantsAccounts.IAE_PMO_ADMINISTRATOR_SECRETKEY, Constants.USER_FED);
	}

	@When("^_H IAE_PMOA navigates to system account directory page$")
	public void _h_IAE_PMOA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@When("^_H IAE_PMOA clicks on Pending Permissions Approval checkbox$")
	public void _h_IAE_PMOA_clicks_on_Pending_Permissions_Approval_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_H IAE_PMOA should be able find requests under Pending Permissions Approval status$")
	public void _h_IAE_PMOA_should_be_able_find_requests_under_Pending_Permissions_Approval_status() throws Exception {
		Assert.assertEquals(expectedFilter, SystemAccountDirectoryPage.validatePendingPermissionsApprovalFilter());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// System Account Manager should be able to conform Pending Permissions Approval
	// status appear under Status section
	@Given("^_I System Account Manager logs in$")
	public void _i_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_I SAM navigates to system account directory page$")
	public void _i_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_I SAM confirm Pending Permissions Approval appear under Status section$")
	public void _i_SAM_confirm_Pending_Permissions_Approval_appear_under_Status_section() throws Exception {
		Assert.assertEquals(expectedStatus, SystemAccountDirectoryPage.validatePendingPermissionsApprovalStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// System Account Manager should be able to use filter to search for accounts
	// under Pending Permissions Approval status
	@Given("^_J System Account Manager logs in$")
	public void _j_System_Account_Manager_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_J SAM navigates to system account directory page$")
	public void _j_SAM_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@When("^_J SAM clicks on Pending Permissions Approval checkbox$")
	public void _j_SAM_clicks_on_Pending_Permissions_Approval_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_J SAM should be able find requests under Pending Permissions Approval status$")
	public void _j_SAM_should_be_able_find_requests_under_Pending_Permissions_Approval_status() throws Exception {
		Assert.assertEquals(expectedFilter, SystemAccountDirectoryPage.validatePendingPermissionsApprovalFilter());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// System Account Administrator should be able to conform Pending Permissions
	// Approval status appear under Status section
	@Given("^_K System Account Administrator logs in$")
	public void _k_System_Account_Administrator_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_K SAA navigates to system account directory page$")
	public void _k_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_K SAA confirm Pending Permissions Approval appear under Status section$")
	public void _k_SAA_confirm_Pending_Permissions_Approval_appear_under_Status_section() throws Exception {
		Assert.assertEquals(expectedStatus, SystemAccountDirectoryPage.validatePendingPermissionsApprovalStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// System Account Administrator should be able to use filter to search for
	// accounts under Pending Permissions Approval status
	@Given("^_L System Account Administrator logs in$")
	public void _l_System_Account_Administrator_logs_in() throws Exception {
		beforeScenario();
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_L SAA navigates to system account directory page$")
	public void _l_SAA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@When("^_L SAA clicks on Pending Permissions Approval checkbox$")
	public void _l_SAA_clicks_on_Pending_Permissions_Approval_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_L SAA should be able find requests under Pending Permissions Approval status$")
	public void _l_SAA_should_be_able_find_requests_under_Pending_Permissions_Approval_status() throws Exception {
		Assert.assertEquals(expectedFilter, SystemAccountDirectoryPage.validatePendingPermissionsApprovalFilter());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Non-Fed user should be able to conform Pending Permissions Approval status
	// appear under Status section
	@Given("^_M Non-Fed user logs in$")
	public void _m_Non_Fed_user_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@When("^_M NFU navigates to system account directory page$")
	public void _m_NFU_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_M NFU confirm Pending Permissions Approval appear under Status section$")
	public void _m_NFU_confirm_Pending_Permissions_Approval_appear_under_Status_section() throws Exception {
		Assert.assertEquals(expectedStatus, SystemAccountDirectoryPage.validatePendingPermissionsApprovalStatus());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// Non-Fed user should be able to use filter to search for accounts under
	// Pending Permissions Approval status
	@Given("^_N Non-Fed user logs in$")
	public void _n_Non_Fed_user_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS_NONFED,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@When("^_N NFU navigates to system account directory page$")
	public void _n_NFU_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@When("^_N NFU clicks on Pending Permissions Approval checkbox$")
	public void _n_NFU_clicks_on_Pending_Permissions_Approval_checkbox() throws Exception {
		SystemAccountDirectoryPage.clickOnPendingPermissionsApprovalFilter();
	}

	@Then("^_N NFU should be able find requests under Pending Permissions Approval status$")
	public void _n_NFU_should_be_able_find_requests_under_Pending_Permissions_Approval_status() throws Exception {
		Assert.assertEquals(expectedFilter, SystemAccountDirectoryPage.validatePendingPermissionsApprovalFilter());
		afterScenario();
		LaunchBrowserUtil.closeThisBrowser();
	}

	// GSA Security Approver should be able to conform Pending Permissions Approval
	// status dosen't appear under Status section
	@Given("^_O GSA Security Approver logs in$")
	public void _o_GSA_Security_Approver_logs_in() throws Exception {
		beforeScenario();
		logger.info("User logging as a non-fed user...");
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@When("^_O GSA_SA navigates to system account directory page$")
	public void _o_GSA_SA_navigates_to_system_account_directory_page() throws Exception {
		T1WorkspacePage.goToSystemAccountDirectoryPage();
		LaunchBrowserUtil.scrollToMiddle();
	}

	@Then("^_O GSA_SA confirm Pending Permissions Approval status doesn't appear under Status section$")
	public void _o_GSA_SA_confirm_Pending_Permissions_Approval_status_doesn_t_appear_under_Status_section()
			throws Exception {
		List<WebElement> PPAStatus = LaunchBrowserUtil.driver.findElements(
				By.xpath("//*[@name='filter-types']//following-sibling::sam-checkbox[2]//fieldset//ul//li"));
		String status = "Pending Permissions Approval";
		for (int i = 0; i < PPAStatus.size(); i++) {
			PPAStatus.get(i);
			assertNotEquals(status, PPAStatus);
			LaunchBrowserUtil.delay(3);
			afterScenario();
		}
		LaunchBrowserUtil.closeThisBrowser();
	}

	// private methods are below this line
	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}

}
