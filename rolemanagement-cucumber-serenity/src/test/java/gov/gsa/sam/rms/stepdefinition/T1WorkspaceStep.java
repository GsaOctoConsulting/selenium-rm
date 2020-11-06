package gov.gsa.sam.rms.stepdefinition;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import gov.gsa.sam.rms.utilities.Constants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.SystemAccountDirectoryPageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.CommonProfilePage;
import gov.gsa.sam.rms.pages.SystemAccountDirectoryPage;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.pages.UserDirectoryViewAccessPage;

public class T1WorkspaceStep {

	private static Logger logger = LoggerFactory.getLogger(T1WorkspaceStep.class);
	String newid = "";
	String secretkey = "";

	@Given("^_1t1 user logs in as spaad$")
	public void _1t1_user_logs_in_as_spaad() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ROLE_ADMIN_USER_3, Constants.USERPASS,
				ConstantsAccounts.ROLE_ADMIN_USER_3_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.delay(4);
	}

	@Then("^_1t1 spaad should be able to view user directory widget and relevant links$")
	public void _1t1_spaad_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean roledefinitionbuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.ROLE_DEFINITION_BUTTON);
		Assert.assertEquals(true, roledefinitionbuttonfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		/*
		 * boolean pendingrolerequestlinkfound =
		 * T1WorkspacePage.elementFound(T1WorkspacePageLocator.PENDING_ROLE_REQUEST_LINK
		 * ); Assert.assertEquals(true, pendingrolerequestlinkfound);
		 */ // not needed anymore

	}

	@And("^_1t1 spaad should see both fed and nonfed in user dropdown in widget$")
	public void _1t1_spaad_should_see_both_fed_and_nonfed_in_user_dropdown_in_widget() throws Throwable {
		boolean feduseroptionfound = T1WorkspacePage.selectUserTypeIfFound("Federal users");
		Assert.assertEquals(true, feduseroptionfound);

		boolean nonfeduseroptionfound = T1WorkspacePage.selectUserTypeIfFound("Non-federal users");
		Assert.assertEquals(true, nonfeduseroptionfound);
	}

	@And("^_1t1 spaad should be able to view assistance listing widget$")
	public void _1t1_spaad_should_be_able_to_view_assistance_listing_widget() throws Throwable {
		boolean assistancelistingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ASSISTANCE_LISTING_WIDGET);
		Assert.assertEquals(true, assistancelistingwidgetfound);
	}

	@And("^_1t1 spaad should be able to view collective bargaining widget$")
	public void _1t1_spaad_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);// not seeing for this user
	}

	@And("^_1t1 spaad should be able to view wage determination widget$")
	public void _1t1_spaad_should_be_able_to_view_wage_determination_widget() throws Throwable {

	}

	@Given("^_2t1 user logs in as assistance admin$")
	public void _2t1_user_logs_in_as_assistance_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_2t1 aad should be able to view user directory widget and relevant links$")
	public void _2t1_spaad_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_2t1 aad should be able to view assistance listing widget$")
	public void _2t1_spaad_should_be_able_to_view_assistance_listing_widget() throws Throwable {
		boolean assistancelistingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ASSISTANCE_LISTING_WIDGET);
		Assert.assertEquals(true, assistancelistingwidgetfound);
	}

	@And("^_2t1 aad should be able to view collective bargaining widget$")
	public void _2t1_spaad_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);// not seeing for this user
	}

	@Given("^_3t1 user logs in as fsd admin$")
	public void _3t1_user_logs_in_as_fsd_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FSD_ADMIN_5, Constants.USERPASS,
				ConstantsAccounts.FSD_ADMIN_5_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_3t1 fsdadmin should be able to view user directory widget and relevant links$")
	public void _3t1_fsdadmin_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean feduseroptionfound = T1WorkspacePage.selectUserTypeIfFound("Federal users");
		Assert.assertEquals(true, feduseroptionfound);

		boolean nonfeduseroptionfound = T1WorkspacePage.selectUserTypeIfFound("Non-federal users");
		Assert.assertEquals(true, nonfeduseroptionfound);
	}

	@And("^_3t1 fsdadmin should be able to view collective bargaining widget$")
	public void _3t1_fsdadmin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_4t1 user logs in as fsd agent$")
	public void _4t1_user_logs_in_as_fsd_agent() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FSD_AGENT_1, Constants.USERPASS,
				ConstantsAccounts.FSD_AGENT_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_4t1 fsdagent should be able to view user directory widget and relevant links$")
	public void _4t1_fsdagent_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		/*
		 * boolean bulkupdatebuttonfound =
		 * T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		 * Assert.assertEquals(true, bulkupdatebuttonfound);
		 */

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean feduseroptionfound = T1WorkspacePage.selectUserTypeIfFound("Federal users");
		Assert.assertEquals(true, feduseroptionfound);

		boolean nonfeduseroptionfound = T1WorkspacePage.selectUserTypeIfFound("Non-federal users");
		Assert.assertEquals(true, nonfeduseroptionfound);
	}

	@And("^_4t1 fsdagent should be able to view collective bargaining widget$")
	public void _4t1_fsdagent_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_4t1 fsdagent should not see add a new role link$")
    public void _4t1_fsdagent_should_not_see_add_a_new_role_link() throws Throwable {
		boolean addNewRoleLink = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ADD_NEW_ROLE_LINK);
		Assert.assertEquals(false, addNewRoleLink); 
    }
	@Given("^_5t1 user logs in as system account admin$")
	public void _5t1_user_logs_in_as_system_account_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_2, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_5t1 saa should be able to view user directory widget and relevant links$")
	public void _5t1_saa_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_5t1 aad should be able to view system account widget and relevant links$")
	public void _5t1_aad_should_be_able_to_view_system_account_widget_and_relevant_links() throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean systemaccountlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.GO_TO_SYSTEM_ACCOUNT);
		Assert.assertEquals(true, systemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean pendingreviewaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingreviewaccountlinkfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.DEACTIVATED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);
	}

	@And("^_5t1 aad should be able to view collective bargaining widget$")
	public void _5t1_aad_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_6t1 user logs in as gsa security approver$")
	public void _6t1_user_logs_in_as_gsa_security_approver() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_6t1 gsa securityapprover should be able to view user directory widget and relevant links$")
	public void _6t1_gsa_securityapprover_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_6t1 gsa securityapprover should be able to view system account widget and relevant links$")
	public void _6t1_gsa_securityapprover_should_be_able_to_view_system_account_widget_and_relevant_links()
			throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean systemaccountlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.GO_TO_SYSTEM_ACCOUNT);
		Assert.assertEquals(true, systemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		/*
		 * boolean pendingreviewaccountlinkfound = T1WorkspacePage
		 * .elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		 * Assert.assertEquals(true, pendingreviewaccountlinkfound);
		 */

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.DEACTIVATED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);
	}

	@And("^_6t1 gsa securityapprover should be able to view collective bargaining widget$")
	public void _6t1_gsa_securityapprover_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_7t1 user logs in as system manager$")
	public void _7t1_user_logs_in_as_system_manager() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_7t1 system manager should be able to view user directory widget and relevant links$")
	public void _7t1_system_manager_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_7t1 system manager should be able to view system account widget and relevant links$")
	public void _7t1_system_manager_should_be_able_to_view_system_account_widget_and_relevant_links() throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean systemaccountlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.GO_TO_SYSTEM_ACCOUNT);
		Assert.assertEquals(true, systemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean pendingreviewaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingreviewaccountlinkfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.DEACTIVATED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);
	}

	@And("^_7t1 system manager should be able to view collective bargaining widget$")
	public void _7t1_system_manager_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_8t1 user logs in as content manager$")
	public void _8t1_user_logs_in_as_content_manager() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTENT_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.CONTENT_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_8t1 content manager should be able to view user directory widget and relevant links$")
	public void _8t1_content_manager_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_8t1 content manager should be able to view collective bargaining widget$")
	public void _8t1_content_manager_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_8t1 content manager should be able to view content management widget$")
	public void _8t1_content_manager_should_be_able_to_view_content_management_widget() throws Throwable {
		boolean contentmanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.CONTENT_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, contentmanagementwidgetfound);
	}

	@Given("^_9t1 user logs in as iam administrator$")
	public void _9t1_user_logs_in_as_iam_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.IAM_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.IAM_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_9t1 iam admin should be able to view user directory widget and relevant links$")
	public void _9t1_iam_admin_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_9t1 iam admin should be able to view collective bargaining widget$")
	public void _9t1_iam_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_10t1 user logs in as assistance listing sam pmo administrator$")
	public void _10t1_user_logs_in_as_assistance_listing_sam_pmo_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_LISTING_SPA_1, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_LISTING_SPA_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_10t1 alspa should be able to view user directory widget and relevant links$")
	public void _10t1_alspa_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("_10t1 alspa should be able to view assistance listing widget")
	public void _10t1_alspa_should_be_able_to_view_assistance_listing_widget() throws Throwable {
		boolean assistancelistingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ASSISTANCE_LISTING_WIDGET);
		Assert.assertEquals(true, assistancelistingwidgetfound);
	}

	@And("^_10t1 alspa should be able to view collective bargaining widget$")
	public void _10t1_alspa_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_11t1 user logs in as assistance listing administrator$")
	public void _11t1_user_logs_in_as_assistance_listing_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_ADMIN_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_ADMIN_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_11t1 al admin should be able to view user directory widget and relevant links$")
	public void _11t1_al_admin_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("_11t1 al admin should be able to view assistance listing widget")
	public void _11t1_al_admin_should_be_able_to_view_assistance_listing_widget() throws Throwable {
		boolean assistancelistingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ASSISTANCE_LISTING_WIDGET);
		Assert.assertEquals(true, assistancelistingwidgetfound);
	}

	@And("^_11t1 al admin should be able to view collective bargaining widget$")
	public void _11t1_al_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_12t1 user logs in as assistance user$")
	public void _12t1_user_logs_in_as_assistance_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ASSISTANCE_USER_2, Constants.USERPASS,
				ConstantsAccounts.ASSISTANCE_USER_2_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_12t1 assistance user should be able to view user directory widget and relevant links$")
	public void _12t1_assistance_user_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
		/*
		 * boolean bulkupdatebuttonfound =
		 * T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		 * Assert.assertEquals(true, bulkupdatebuttonfound);
		 */
	}

	@And("^_12t1 assistance user should be able to view assistance listing widget$")
	public void _12t1_assistance_user_should_be_able_to_view_assistance_listing_widget() throws Throwable {
		boolean assistancelistingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ASSISTANCE_LISTING_WIDGET);
		Assert.assertEquals(true, assistancelistingwidgetfound);
	}

	@And("^_12t1 assistance user should be able to view collective bargaining widget$")
	public void _12t1_assistance_user_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_13t1 user logs in as omb admin$")
	public void _13t1_user_logs_in_as_omb_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OMB_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.OMB_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_13t1 omb admin should be able to view user directory widget and relevant links$")
	public void _13t1_omb_admin_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_13t1 omb admin should be able to view assistance listing widget$")
	public void _13t1_omb_admin_should_be_able_to_view_assistance_listing_widget() throws Throwable {
		boolean assistancelistingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ASSISTANCE_LISTING_WIDGET);
		Assert.assertEquals(true, assistancelistingwidgetfound);
	}

	@And("^_13t1 omb admin should be able to view collective bargaining widget$")
	public void _13t1_omb_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_14t1 user logs in as contract data sam pmo administrator$")
	public void _14t1_user_logs_in_as_contract_data_sam_pmo_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_SPA_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_SPA_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_14t1 cdspa should be able to view user directory widget and relevant links$")
	public void _14t1_alspa_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_14t1 cdspa should be able to view collective bargaining widget$")
	public void _14t1_alspa_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_15t1 user logs in as contract data administrator$")
	public void _15t1_user_logs_in_as_contract_data_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_15t1 cd admin should be able to view user directory widget and relevant links$")
	public void _15t1_cdspa_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_15t1 cd admin should be able to view collective bargaining widget$")
	public void _15t1_cdspa_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_16t1 user logs in as contract data contracting officer$")
	public void _16t1_user_logs_in_as_contract_data_contracting_officer() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGOFFICER_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_CONTRACTINGOFFICER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_16t1 cd co should be able to view user directory widget and relevant links$")
	public void _16t1_cd_co_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_16t1 cd co should be able to view collective bargaining widget$")
	public void _16t1_cd_co_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_17t1 user logs in as contract data contracting specialist$")
	public void _17t1_user_logs_in_as_contract_data_contracting_specialist() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_DATA_CONTRACTINGSPECIALIST_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_17t1 cd cs should be able to view user directory widget and relevant links$")
	public void _17t1_cd_cs_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_17t1 cd cs should be able to view collective bargaining widget$")
	public void _17t1_cd_cs_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_18t1 user logs in as contract opportunities sam pmo administrator$")
	public void _18t1_user_logs_in_as_contract_opportunities_sam_pmo_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_SPA_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_SPA_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_18t1 cospa should be able to view user directory widget and relevant links$")
	public void _18t1_cospa_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_18t1 cospa should be able to view collective bargaining widget$")
	public void _18t1_cospa_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_18t1 cospa should be able to view contract opportunites widget$")
	public void _18t1_cospa_admin_should_be_able_to_view_contract_opportunites_widget() throws Throwable {
		boolean contractopportunitieswidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.CONTRACT_OPPORTUNITIES_WIDGET);
		Assert.assertEquals(true, contractopportunitieswidgetfound);
	}

	@Given("^_19t1 user logs in as contract opportunities administrator$")
	public void _19t1_user_logs_in_as_contract_opportunities_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_19t1 co admin should be able to view user directory widget and relevant links$")
	public void _19t1_co_admin_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_19t1 co admin should be able to view collective bargaining widget$")
	public void _19t1_co_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_19t1 co admin should be able to view contract opportunites widget$")
	public void _19t1_co_admin_should_be_able_to_view_contract_opportunites_widget() throws Throwable {
		boolean contractopportunitieswidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.CONTRACT_OPPORTUNITIES_WIDGET);
		Assert.assertEquals(true, contractopportunitieswidgetfound);
	}

	@Given("^_20t1 user logs in as contract opportunities contracting officer$")
	public void _20t1_user_logs_in_as_contract_opportunities_contracting_officer() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGOFFICER_1,
				Constants.USERPASS, ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGOFFICER_1_SECRETKEY,
				Constants.USER_FED);
	}

	@Then("^_20t1 contract opportunities co should be able to view user directory widget and relevant links$")
	public void _20t1_contract_opportunities_co_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		/*
		 * boolean bulkupdatebuttonfound =
		 * T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		 * Assert.assertEquals(true, bulkupdatebuttonfound);
		 */
	}

	@And("^_20t1 contract opportunities co should be able to view collective bargaining widget$")
	public void _20t1_contract_opportunities_co_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_20t1 contract opportunities co should be able to view contract opportunities widget$")
	public void _20t1_contract_opportunities_co_should_be_able_to_view_contract_opportunities_widget()
			throws Throwable {
		boolean contractopportunitieswidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.CONTRACT_OPPORTUNITIES_WIDGET);
		Assert.assertEquals(true, contractopportunitieswidgetfound);
	}

	@Given("^_21t1 user logs in as contract opportunities contracting specialist$")
	public void _21t1_user_logs_in_as_contract_opportunities_contracting_specialist() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGSPECIALIST_1,
				Constants.USERPASS, ConstantsAccounts.CONTRACT_OPPORTUNITIES_CONTRACTINGSPECIALIST_1_SECRETKEY,
				Constants.USER_FED);
	}

	@Then("^_21t1 contract opportunities cs should be able to view user directory widget and relevant links$")
	public void _21t1_contract_opportunities_cs_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_21t1 contract opportunities cs should be able to view collective bargaining widget$")
	public void _21t1_contract_opportunities_cs_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_21t1 contract opportunities cs should be able to view contract opportunities widget$")
	public void _21t1_contract_opportunities_cs_should_be_able_to_view_contract_opportunities_widget()
			throws Throwable {
		boolean contractopportunitieswidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.CONTRACT_OPPORTUNITIES_WIDGET);
		Assert.assertEquals(true, contractopportunitieswidgetfound);
	}

	@Given("^_22t1 user logs in as contract opportunities nonfed data entry user$")
	public void _22t1_user_logs_in_as_contract_opportunities_nonfed_user() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES, Constants.USERPASS,
				ConstantsAccounts.NONFED_DATAENTRY_CONTRACTOPPORTUNITIES_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_22t1 contract opportunities nonfed user should not be able to view user directory widget and relevant links$")
	public void _22t1_contract_opportunities_nonfed_user_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(false, userdirectorywidgetfound);
	}

	@And("^_22t1 contract opportunities nonfed user should also be able to view system account widget$")
	public void _22t1_contract_opportunities_nonfed_user_should_also_be_able_to_view_system_account_widget()
			throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean pendingreviewsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingreviewsystemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);

		// request account
		// link to system account
	}

	@Given("^_23t1 user logs in as contract opportunities viewer$")
	public void _23t1_user_logs_in_as_contract_opportunities_viewer() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.CONTRACT_OPPORTUNITIES_VIEWER_1, Constants.USERPASS,
				ConstantsAccounts.CONTRACT_OPPORTUNITIES_VIEWER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_23t1 contract opportunities viewer should not be able to view user directory widget and relevant links$")
	public void _23t1_contract_opportunities_viewer_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(false, userdirectorywidgetfound);
	}

	@And("^_23t1 contract opportunities viewer user should also be able to view system account widget$")
	public void _23t1_contract_opportunities_nonfed_user_should_also_be_able_to_view_system_account_widget()
			throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean pendingreviewsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingreviewsystemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);

		// request account
		// link to system account
	}

	@Given("^_24t1 user logs in as fh sampmo administrator$")
	public void _24t1_user_logs_in_as_fh_sampmo_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_SAMPMO_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.FH_SAMPMO_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_24t1 fh spa should be able to view user directory widget and relevant links$")
	public void _24t1_fh_spa_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

	}

	@And("^_24t1 fh spa should be able to view federal hierarchy widget$")
	public void _24t1_fh_spa_should_be_able_to_view_federal_hierarchy_widget() throws Throwable {
		boolean fhwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.FH_WIDGET);
		Assert.assertEquals(true, fhwidgetfound);
	}

	@And("^_24t1 fh spa should be able to view collective bargaining widget$")
	public void _24t1_fh_spa_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectiveBargainingWidgetFound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectiveBargainingWidgetFound);
	}

	@Given("^_25t1 user logs in as fh department administrator$")
	public void _25t1_user_logs_in_as_fh_department_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_DEPT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.FH_DEPT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_25t1 fh dept admin should be able to view user directory widget and relevant links$")
	public void _25t1_fh_dept_admin_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_25t1 fh dept admin should be able to view federal hierarchy widget$")
	public void _25t1_fh_dept_admin_should_be_able_to_view_federal_hierarchy_widget() throws Throwable {
		boolean fhwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.FH_WIDGET);
		Assert.assertEquals(true, fhwidgetfound);
	}

	@And("^_25t1 fh dept admin should be able to view collective bargaining widget$")
	public void _25t1_fh_dept_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_26t1 user logs in as fh subtier administrator$")
	public void _26t1_user_logs_in_as_fh_subtier_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_SUBTIER_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.FH_SUBTIER_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_26t1 fh subtier admin should be able to view user directory widget and relevant links$")
	public void _26t1_fh_subtier_admin_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_26t1 fh subtier admin should be able to view federal hierarchy widget$")
	public void _26t1_fh_subtier_admin_should_be_able_to_view_federal_hierarchy_widget() throws Throwable {
		boolean fhwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.FH_WIDGET);
		Assert.assertEquals(true, fhwidgetfound);
	}

	@And("^_26t1 fh subtier admin should be able to view collective bargaining widget$")
	public void _26t1_fh_subtier_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_27t1 user logs in as fh aac$")
	public void _27t1_user_logs_in_as_fh_aac() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_AAC_USER_1, Constants.USERPASS,
				ConstantsAccounts.FH_AAC_USER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_27t1 aac user should be able to view aac widget$")
	public void _27t1_aac_user_should_be_able_to_view_aac_widget() throws Throwable {
		boolean aacwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.UPLOAD_AAC_WIDGET);
		Assert.assertEquals(true, aacwidgetfound);
	}

	@And("^_27t1 aac user should be able to view collective bargaining widget$")
	public void _27t1_aac_user_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_28t1 user logs in as fh office administrator$")
	public void _28t1_user_logs_in_as_fh_office_administrator() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_OFFICE_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.FH_OFFICE_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_28t1 fh office admin should be able to view user directory widget and relevant links$")
	public void _28t1_fh_office_admin_should_be_able_to_view_user_directory_widget_and_relevant_links()
			throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_28t1 fh office admin should be able to view collective bargaining widget$")
	public void _28t1_fh_office_admin_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_29t1 user logs in with cospa fhspa and system manager role$")
	public void _29t1_user_logs_in_with_cospa_fhspa_and_system_manager_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.COSPA_FHSPA_SYSTEMMANAGER_1, Constants.USERPASS,
				ConstantsAccounts.COSPA_FHSPA_SYSTEMMANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_29t1 user should be able to view user directory widget and relevant links$")
	public void _29t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);

	}

	@And("^_29t1 user should be able to view system account widget$")
	public void _29t1_user_should_be_able_to_view_system_account_widget() throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean pendingreviewsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingreviewsystemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);

		// request account
		// link to system account
	}

	@And("^_29t1 user should be able to view federal hierarchy widget$")
	public void _29t1_user_should_be_able_to_view_federal_hierarchy_widget() throws Throwable {
		boolean fhwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.FH_WIDGET);
		Assert.assertEquals(true, fhwidgetfound);
	}

	

	@Given("^_30t1 user logs in with cospa fhspa and content manager role$")
	public void _30t1_user_logs_in_with_cospa_fhspa_and_content_manager_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.COSPA_FHSPA_CONTENTMANAGER_1, Constants.USERPASS,
				ConstantsAccounts.COSPA_FHSPA_CONTENTMANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_30t1 user should be able to view user directory widget and relevant links$")
	public void _30t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_30t1 user should be able to view content management account widget$")
	public void _30t1_user_should_be_able_to_view_content_management_account_widget() throws Throwable {
		boolean contentmanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.CONTENT_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, contentmanagementwidgetfound);
	}

	@And("^_30t1 user should be able to view federal hierarchy widget$")
	public void _30t1_user_should_be_able_to_view_federal_hierarchy_widget() throws Throwable {
		boolean fhwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.FH_WIDGET);
		Assert.assertEquals(true, fhwidgetfound);
	}


	@And("^_30t1 user should be able to view collective bargaining widget$")
	public void _30t1_user_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_31t1 user logs in with cospa fhspa and gsa security approver$")
	public void _31t1_user_logs_in_with_cospa_fhspa_and_gsa_security_approver() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.COSPA_FHSPA_GSASECURITYAPPROVER_1, Constants.USERPASS,
				ConstantsAccounts.COSPA_FHSPA_GSASECURITYAPPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_31t1 user should be able to view user directory widget and relevant links$")
	public void _31t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);

		boolean bulkupdatebuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.BULK_UPDATE_BUTTON);
		Assert.assertEquals(true, bulkupdatebuttonfound);
	}

	@And("^_31t1 user should be able to view federal hierarchy widget$")
	public void _31t1_user_should_be_able_to_view_federal_hierarchy_widget() throws Throwable {
		boolean fhwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.FH_WIDGET);
		Assert.assertEquals(true, fhwidgetfound);
	}

	@And("^_31t1 user should be able to view collective bargaining widget$")
	public void _31t1_user_should_be_able_to_view_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@And("^_31t1 user should be able to see system account widget$")
	public void _31t1_user_should_be_able_to_see_system_account_widget() throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		/*
		 * boolean pendingreviewsystemaccountlinkfound = T1WorkspacePage
		 * .elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		 * Assert.assertEquals(true, pendingreviewsystemaccountlinkfound);
		 */

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);

		// request account
		// link to system account
	}

	@Given("^_32t1 user logs in with role that has access to t1workspace and rolerequest option$")
	public void _32t1_user_logs_in_with_role_that_has_access_to_t1workspace_and_rolerequest_option() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NO_ROLE_USER_2, Constants.USERPASS,
				ConstantsAccounts.NO_ROLE_USER_2_SECRETKEY, Constants.USER_FED);
		LaunchBrowserUtil.scrollAllTheWayDown();
	}

	@When("^_32t1 user selects assistance user role in the dropdown selection$")
	public void _32t1_user_selects_a_role_in_the_dropdown_selection() throws Throwable {
		boolean rolefound = T1WorkspacePage.selectRoleForRoleRequest(Constants.ROLE_ASSISTANCE_USER);
		Assert.assertEquals(true, rolefound);

	}

	@Then("^_32t1 that role should also have a corresponding sentence on what the role is$")
	public void _32t1_that_role_should_also_have_a_corresponding_sentence_on_what_the_role_is() throws Throwable {
		LaunchBrowserUtil.delay(3);
		String roledescription = T1WorkspacePage.getRoleDescriptionForSelectedRole();
		Assert.assertEquals(Constants.ROLEDESCRIPTION_ASSISTANCEUSER, roledescription);
	}

	@When("^_32t1 user selects contracting officer user role in the dropdown selection$")
	public void _32t1_user_selects_contracting_officer_user_role_in_the_dropdown_selection() throws Throwable {
		LaunchBrowserUtil.delay(3);
		boolean rolefound = T1WorkspacePage.selectRoleForRoleRequest(Constants.ROLE_CONTRACTING_OFFICER_PUBLISHER);
		Assert.assertEquals(true, rolefound);
	}

	@Then("^_32t1 that role should also have a contracting officer description$")
	public void _32t1_user_should_also_see_a_corresponding_sentence_on_what_the_role_is() throws Throwable {
		String roledescription = T1WorkspacePage.getRoleDescriptionForSelectedRole();
		Assert.assertEquals(Constants.ROLEDESCRIPTION_CONSTRACTINGOFFICER, roledescription);
	}

	@When("^_32t1 user selects contracting specialist role in the dropdown selection$")
	public void _32t1_user_selects_contracting_specialist_role_in_the_dropdown_selection() throws Throwable {

		boolean rolefound = T1WorkspacePage.selectRoleForRoleRequest(Constants.ROLE_CONTRACTING_SPECIALIST_EDITOR);
		Assert.assertEquals(true, rolefound);
	}

	@Then("^_32t1 that role should also have a contracting specialist description$")
	public void _32t1_user_should_also_see_corresponding_sentence_on_what_the_role_is() throws Throwable {
		LaunchBrowserUtil.delay(3);
		String roledescription = T1WorkspacePage.getRoleDescriptionForSelectedRole();
		Assert.assertEquals(Constants.ROLEDESCRIPTION_CONSTRACTINGSPECIALIST, roledescription);
	}

	@Given("^_33t1 user logs in as system manager$")
	public void _33t1_user_logs_in_as_system_manager() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEM_MANAGER_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEM_MANAGER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_33t1 user should see the approved status renamed as active$")
	public void _33t1_user_should_see_the_approved_status_renamed_as_active() throws Throwable {
		String linktextforapprovedacconts = T1WorkspacePage
				.getElementsText(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(false, linktextforapprovedacconts.contains("APPROVED"));
		Assert.assertEquals(true, linktextforapprovedacconts.contains("ACTIVE"));
	}

	@And("^_33t1 the draft status should be visible to system manager$")
	public void _33t1_the_draft_status_should_be_visible_to_system_manager() throws Throwable {
		boolean draftlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.DRAFT);
		Assert.assertEquals(true, draftlinkfound);
	}

	@And("^_33t1 the pending status should be visible to system manager$")
	public void _33t1_the_pending_status_should_be_visible_to_system_manager() throws Throwable {
		boolean pendingstatus = T1WorkspacePage.elementFound(T1WorkspacePageLocator.PENDING);
		Assert.assertEquals(true, pendingstatus);
	}

	@When("^_33t1 user clicks the pending bubble then they go to system account page with filters selected$")
	public void _33t1_user_clicks_the_pending_bubble_then_they_go_to_system_account_page_with_filters_selected()
			throws Throwable {
		T1WorkspacePage.clickPendingBubble();
		boolean pendingreviewfilterselected = SystemAccountDirectoryPage
				.isFilterSelected(SystemAccountDirectoryPageLocator.PENDING_REVIEW_FILTER);
		Assert.assertEquals(true, pendingreviewfilterselected);

		boolean pendingapprovalfilterselected = SystemAccountDirectoryPage
				.isFilterSelected(SystemAccountDirectoryPageLocator.PENDING_APPROVAL_FILTER);
		Assert.assertEquals(true, pendingapprovalfilterselected);

	}

	@Given("^_34t1 user logs in as system account admin$")
	public void _34t1_user_logs_in_as_system_account_admin() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1, Constants.USERPASS,
				ConstantsAccounts.SYSTEMACCOUNT_ADMIN_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_34t1 user should see the approved status renamed as active$")
	public void _34t1_user_should_see_the_approved_status_renamed_as_active() throws Throwable {
		String linktextforapprovedacconts = T1WorkspacePage
				.getElementsText(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(false, linktextforapprovedacconts.contains("APPROVED"));
		Assert.assertEquals(true, linktextforapprovedacconts.contains("ACTIVE"));
	}

	@And("^_34t1 the draft status should be visible to system admin$")
	public void _34t1_the_draft_status_should_be_visible_to_system_admin() throws Throwable {
		boolean draftlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.DRAFT);
		Assert.assertEquals(true, draftlinkfound);
	}

	@And("^_34t1 the pending status should be visible to system admin$")
	public void _34t1_the_pending_status_should_be_visible_to_system_admin() throws Throwable {
		boolean pendingstatus = T1WorkspacePage.elementFound(T1WorkspacePageLocator.PENDING);
		Assert.assertEquals(true, pendingstatus);
	}

	@When("^_34t1 user clicks the pending bubble then they go to system account page with filters selected$")
	public void _34t1_user_clicks_the_pending_bubble_then_they_go_to_system_account_page_with_filters_selected()
			throws Throwable {
		T1WorkspacePage.clickPendingBubble();
		boolean pendingreviewfilterselected = SystemAccountDirectoryPage
				.isFilterSelected(SystemAccountDirectoryPageLocator.PENDING_REVIEW_FILTER);
		Assert.assertEquals(true, pendingreviewfilterselected);

		boolean pendingapprovalfilterselected = SystemAccountDirectoryPage
				.isFilterSelected(SystemAccountDirectoryPageLocator.PENDING_APPROVAL_FILTER);
		Assert.assertEquals(true, pendingapprovalfilterselected);
	}

	@Given("^_35t1 user logs in as gsa security approver$")
	public void _35t1_user_logs_in_as_gsa_security_approver() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.GSASECURITY_APPROVER_1, Constants.USERPASS,
				ConstantsAccounts.GSASECURITY_APPROVER_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_35t1 user should see the approved status renamed as active$")
	public void _35t1_user_should_see_the_approved_status_renamed_as_active() throws Throwable {
		String linktextforapprovedacconts = T1WorkspacePage
				.getElementsText(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(false, linktextforapprovedacconts.contains("APPROVED"));
		Assert.assertEquals(true, linktextforapprovedacconts.contains("ACTIVE"));
	}

	@And("^_35t1 the draft status should not be visible to gsa security approver$")
	public void _35t1_the_draft_status_should_not_be_visible_to_gsa_security_approver() throws Throwable {
		boolean draftlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.DRAFT);
		Assert.assertEquals(false, draftlinkfound);
	}

	@And("^_35t1 the pending status should be visible to gsa security approver$")
	public void _35t1_the_pending_status_should_be_visible_to_gsa_security_approver() throws Throwable {
		boolean pendingstatus = T1WorkspacePage.elementFound(T1WorkspacePageLocator.PENDING);
		Assert.assertEquals(true, pendingstatus);
	}

	@When("^_35t1 user clicks the pending bubble then they go to system account page with pendingapproval selected$")
	public void _35t1_user_clicks_the_pending_bubble_then_they_go_to_system_account_page_with_pendingapproval_selected()
			throws Throwable {
		T1WorkspacePage.clickPendingBubble();

		boolean pendingapprovalfilterselected = SystemAccountDirectoryPage
				.isFilterSelected(SystemAccountDirectoryPageLocator.PENDING_APPROVAL_FILTER);
		Assert.assertEquals(true, pendingapprovalfilterselected);

	}

	@Given("^_36t1 user logs in as nonfed user with no entity association$")
	public void _36t1_user_logs_in_as_nonfed() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_NO_ROLES_T1WORKSPACE, Constants.USERPASS,
				ConstantsAccounts.NONFED_NO_ROLES_T1WORKSPACE_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_36t1 user should see the approved status renamed as active$")
	public void _36t1_user_should_see_the_approved_status_renamed_as_active() throws Throwable {
		String linktextforapprovedaccounts = T1WorkspacePage
				.getElementsText(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(false, linktextforapprovedaccounts.contains("APPROVED"));
		Assert.assertEquals(true, linktextforapprovedaccounts.contains("ACTIVE"));
	}

	@And("^_36t1 the draft status should be visible to nonfed user$")
	public void _36t1_the_draft_status_should_be_visible_to_nonfed_user() throws Throwable {
		boolean draftlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.DRAFT);
		Assert.assertEquals(true, draftlinkfound);
	}

	@And("^_36t1 the pending status should be visible to nonfed user$")
	public void _36t1_the_pending_status_should_be_visible_to_nonfed_user() throws Throwable {
		boolean pendingstatus = T1WorkspacePage.elementFound(T1WorkspacePageLocator.PENDING);
		Assert.assertEquals(true, pendingstatus);
	}
	
	@And("^_36t1 the user should not see userdirectory widget$")
    public void _36t1_the_user_should_not_see_userdirectory_widget() throws Throwable {
        boolean userdiredtorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
        Assert.assertEquals(false, userdiredtorywidgetfound);
    }

	

	@Given("^_38t1 user logs in with tier1 help desk role$")
	public void _38t1_user_logs_in_with_tier1_help_desk_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.HELPDESK_TIER1_1, Constants.USERPASS,
				ConstantsAccounts.HELPDESK_TIER1_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_38t1 user should be able to view user directory widget and relevant links$")
	public void _38t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_38t1 the user should be able to to view entity registration widget$")
	public void _38t1_the_user_should_be_able_to_to_view_entity_registration_widget() throws Throwable {
		boolean entityregistrationwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_REGISTRATION_WIDGET);
		Assert.assertEquals(true, entityregistrationwidgetfound);

		boolean feduseroptionfound = T1WorkspacePage.selectEntityTypeIfFound("Federal");
		Assert.assertEquals(true, feduseroptionfound);

		boolean nonfeduseroptionfound = T1WorkspacePage.selectEntityTypeIfFound("Non-Federal");
		Assert.assertEquals(true, nonfeduseroptionfound);
	}

	@Given("^_39t1 user logs in with functional help desk tier two role$")
	public void _39t1_user_logs_in_with_functional_help_desk_tier_two_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.FUNCTIONALHELPDESK_TIER2_1, Constants.USERPASS,
				ConstantsAccounts.FUNCTIONALHELPDESK_TIER2_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_39t1 user should be able to view user directory widget and relevant links$")
	public void _39t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_39t1 the user should be able to to view entity registration widget$")
	public void _39t1_the_user_should_be_able_to_to_view_entity_registration_widget() throws Throwable {
		boolean entityregistrationwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_REGISTRATION_WIDGET);
		Assert.assertEquals(true, entityregistrationwidgetfound);

		boolean feduseroptionfound = T1WorkspacePage.selectEntityTypeIfFound("Federal");
		Assert.assertEquals(true, feduseroptionfound);

		boolean nonfeduseroptionfound = T1WorkspacePage.selectEntityTypeIfFound("Non-Federal");
		Assert.assertEquals(true, nonfeduseroptionfound);
	}

	@Given("^_40t1 user logs in with technical help desk tier two role$")
	public void _40t1_user_logs_in_with_technical_help_desk_tier_two_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.TECHNICALHELPDESK_TIER2_1, Constants.USERPASS,
				ConstantsAccounts.TECHNICALHELPDESK_TIER2_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_40t1 user should be able to view user directory widget and relevant links$")
	public void _40t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_40t1 the user should be able to to view entity registration widget$")
	public void _40t1_the_user_should_be_able_to_to_view_entity_registration_widget() throws Throwable {
		boolean entityregistrationwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_REGISTRATION_WIDGET);
		Assert.assertEquals(true, entityregistrationwidgetfound);

		boolean feduseroptionfound = T1WorkspacePage.selectEntityTypeIfFound("Federal");
		Assert.assertEquals(true, feduseroptionfound);

		boolean nonfeduseroptionfound = T1WorkspacePage.selectEntityTypeIfFound("Non-Federal");
		Assert.assertEquals(true, nonfeduseroptionfound);
	}

	@Given("^_41t1 user logs in with entity admin in entity compliance role$")
	public void _41t1_user_logs_in_with_help_desk_tier_three_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYCOMPLIANCE_1, Constants.USERPASS,
				ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYCOMPLIANCE_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_41t1 user should be able to view user directory widget and relevant links$")
	public void _41t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_41t1 user should be able to view entity compliance widget$")
	public void _41t1_user_should_be_able_to_view_entity_compliance_widget() throws Throwable {

	}

	@Given("^_42t1 user logs in with data entry in entity compliance domain$")
	public void _42t1_user_logs_in_with_data_entry_in_entity_compliance_domain() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1, Constants.USERPASS,
				ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_42t1 user should be able to view entity compliance widget$")
	public void _42t1_user_should_be_able_to_view_entity_compliance_widget() throws Throwable {

	}

	@And("^_42t1 user should be able to view user directory widget and relevant links$")
	public void _42t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@Given("^_43t1 user logs in with agency admin in exclusions domain$")
	public void _43t1_user_logs_in_with_agency_admin_in_exclusions_domain() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AGENCYADMIN_EXCLUSIONS_1, Constants.USERPASS,
				ConstantsAccounts.AGENCYADMIN_EXCLUSIONS_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_43t1 user should be able to view user directory widget and relevant links$")
	public void _43t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_43t1 user should be able to view exclusions and relevant links$")
	public void _43t1_user_should_be_able_to_view_exclusions_and_relevant_links() throws Throwable {
		boolean exclusionwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_WIDGET);
		Assert.assertEquals(true, exclusionwidgetfound);

		boolean exclusionlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_LINK);
		Assert.assertEquals(true, exclusionlinkfound);

		boolean newexclusionlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.NEW_EXCLUSION_LINK);
		Assert.assertEquals(true, newexclusionlinkfound);

		boolean proceedingspending = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_PROCEEDINGS_PENDING_BUBBLE);
		Assert.assertEquals(true, proceedingspending);

		boolean proceedingscompleted = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_PROCEEDINGS_COMPLETED_BUBBLE);
		Assert.assertEquals(true, proceedingscompleted);

		boolean prohibitionrestriction = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_PROHIBITION_RESTRICTION_BUBBLE);
		Assert.assertEquals(true, prohibitionrestriction);

		boolean voluntaryexclusion = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_VOLUNTARY_EXCLUSION_BUBBLE);
		Assert.assertEquals(true, voluntaryexclusion);

		boolean myactive = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_MY_ACTIVE_BUBBLE);
		Assert.assertEquals(true, myactive);

		boolean reviewneeded = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_REVIEWNEEDED_BUBBLE);
		Assert.assertEquals(true, reviewneeded);
	}

	@And("^_43t1 user should see collective bargaining widget$")
	public void _43t1_user_should_see_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_44t1 user logs in with agency exclusions representative$")
	public void _44t1_user_logs_in_with_agency_exclusions_representative() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AGENCY_EXCLUSIONS_REPRESENTATIVE_1, Constants.USERPASS,
				ConstantsAccounts.AGENCY_EXCLUSIONS_REPRESENTATIVE_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_44t1 user should be able to view user directory widget and relevant links$")
	public void _44t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_44t1 user should be able to view exclusions and relevant links$")
	public void _44t1_user_should_be_able_to_view_exclusions_and_relevant_links() throws Throwable {
		boolean exclusionwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_WIDGET);
		Assert.assertEquals(true, exclusionwidgetfound);

		boolean exclusionlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_LINK);
		Assert.assertEquals(true, exclusionlinkfound);

		boolean newexclusionlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.NEW_EXCLUSION_LINK);
		Assert.assertEquals(true, newexclusionlinkfound);

		boolean proceedingspending = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_PROCEEDINGS_PENDING_BUBBLE);
		Assert.assertEquals(true, proceedingspending);

		boolean proceedingscompleted = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_PROCEEDINGS_COMPLETED_BUBBLE);
		Assert.assertEquals(true, proceedingscompleted);

		boolean prohibitionrestriction = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_PROHIBITION_RESTRICTION_BUBBLE);
		Assert.assertEquals(true, prohibitionrestriction);

		boolean voluntaryexclusion = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.EXCLUSION_VOLUNTARY_EXCLUSION_BUBBLE);
		Assert.assertEquals(true, voluntaryexclusion);

		boolean myactive = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_MY_ACTIVE_BUBBLE);
		Assert.assertEquals(true, myactive);

		boolean reviewneeded = T1WorkspacePage.elementFound(T1WorkspacePageLocator.EXCLUSION_REVIEWNEEDED_BUBBLE);
		Assert.assertEquals(true, reviewneeded);
	}

	@And("^_44t1 user should see collective bargaining widget$")
	public void _44t1_user_should_see_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_45t1 user logs in with entity admin role in entity registration$")
	public void _45t1_user_logs_in_with_entity_admin_role_in_entity_registration() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYREGISTRATION_1, Constants.USERPASS,
				ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYREGISTRATION_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_45t1 user should be able to view user directory widget and relevant links$")
	public void _45t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_45t1 user should be able to view entity management widget and relevant links$")
	public void _45t1_user_should_be_able_to_view_entity_management_widget_and_relevant_links() throws Throwable {

		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);

		boolean registerentitybuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.REGISTER_ENTITY_BUTTON);
		Assert.assertEquals(true, registerentitybuttonfound);
	}

	@And("^_45t1 user should be able to view entity compliance widget$")
	public void _45t1_user_should_be_able_to_view_entity_compliance_widget() throws Throwable {

	}

	@Given("^_46t1 user logs in with data entry role in entity registration$")
	public void _46t1_user_logs_in_with_data_entry_role_in_entity_registration() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.DATA_ENTRY_ENTITYREGISTRATION_1, Constants.USERPASS,
				ConstantsAccounts.DATA_ENTRY_ENTITYREGISTRATION_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_46t1 user should not be able to view user directory widget and relevant links$")
	public void _46t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(false, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(false, userdirectorylinkfound);
	}

	@And("^_46t1 user should be able to view entity management widget and relevant links$")
	public void _46t1_user_should_be_able_to_view_entity_management_widget_and_relevant_links() throws Throwable {
		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);

		boolean registerentitybuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.REGISTER_ENTITY_BUTTON);
		Assert.assertEquals(true, registerentitybuttonfound);
	}
	@And("^_46t1 the user should not see userdirectory widget$")
    public void _46t1_the_user_should_not_see_userdirectory_widget() throws Throwable {
        boolean userdiredtorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
        Assert.assertEquals(false, userdiredtorywidgetfound);
    }
	
	
	@And("^_46t1 being a nonfed user with roles this user should also see system account widget$")
    public void _46t1_being_a_nonfed_user_with_roles_this_user_should_also_see_system_account_widget() throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);
    }

	@Given("^_47t1 user logs in with entity registration viewer role$")
	public void _47t1_user_logs_in_with_entity_registration_viewer_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.VIEWER_ENTITYREGISTRATION_1, Constants.USERPASS,
				ConstantsAccounts.VIEWER_ENTITYREGISTRATION_1_SECRETKEY, Constants.USER_NONFED);
	}

	@Then("^_47t1 user should be able to view user directory widget and relevant links$")
	public void _47t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(false, userdirectorywidgetfound);
	}

	@And("^_47t1 user should be able to view entity management widget and relevant links$")
	public void _47t1_user_should_be_able_to_view_entity_management_widget_and_relevant_links() throws Throwable {
		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);

		boolean registerentitybuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.REGISTER_ENTITY_BUTTON);
		Assert.assertEquals(true, registerentitybuttonfound);
	}

	@Given("^_48t1 user logs in with agency admin entity management role$")
	public void _48t1_user_logs_in_with_agency_admin_entity_management_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.AGENCYADMIN_ENTITYREGISTRATION_1, Constants.USERPASS,
				ConstantsAccounts.AGENCYADMIN_ENTITYREGISTRATION_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_48t1 user should be able to view user directory widget and relevant links$")
	public void _48t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_48t1 user should be able to view entity management widget and relevant links$")
	public void _48t1_user_should_be_able_to_view_entity_management_widget_and_relevant_links() throws Throwable {
		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);
	}

	@And("^_48t1 user should see collective bargaining widget$")
	public void _48t1_user_should_see_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_49t1 user logs in with office registration representative role$")
	public void _49t1_user_logs_in_with_office_registration_representative_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.OFFICE_REGISTRATION_REPRESENTATIVE_1, Constants.USERPASS,
				ConstantsAccounts.OFFICE_REGISTRATION_REPRESENTATIVE_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_49t1 user should be able to view user directory widget and relevant links$")
	public void _49t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_49t1 user should be able to view entity management widget and relevant links$")
	public void _49t1_user_should_be_able_to_view_entity_management_widget_and_relevant_links() throws Throwable {
		boolean entitymanagementwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WIDGET);
		Assert.assertEquals(true, entitymanagementwidgetfound);

		boolean entitymanagementlandinglinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_LANDING_LINK);
		Assert.assertEquals(true, entitymanagementlandinglinkfound);

		boolean registerentitybuttonfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.REGISTER_ENTITY_BUTTON);
		Assert.assertEquals(true, registerentitybuttonfound);

		// -----------
		boolean entitymanagementactivebubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_ACTIVELINK);
		Assert.assertEquals(true, entitymanagementactivebubble);

		boolean entitymanagementdraftbubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_DRAFTLINK);
		Assert.assertEquals(true, entitymanagementdraftbubble);

		boolean entitymanagementworkinprogressbubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_WORKINPROGRESSLINK);
		Assert.assertEquals(true, entitymanagementworkinprogressbubble);

		boolean entitymanagementsubmittedbubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_SUBMITTEDLINK);
		Assert.assertEquals(true, entitymanagementsubmittedbubble);

		// -------
		boolean uniteEntityIdActivebubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_UNIQUEID_ACTIVELINK);
		Assert.assertEquals(true, uniteEntityIdActivebubble);

		boolean uniteEntityIdDraftBubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_UNIQUEID_DRAFTLINK);
		Assert.assertEquals(true, uniteEntityIdDraftBubble);

		boolean uniteEntityIdWorkInProgressBubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_UNIQUEID_WORKINPROGRESSLINK);
		Assert.assertEquals(true, uniteEntityIdWorkInProgressBubble);

		boolean uniteEntityIdSubmittedBubble = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_MANAGEMENT_UNIQUEID_SUBMITTEDLINK);
		Assert.assertEquals(true, uniteEntityIdSubmittedBubble);

	}

	@And("^_49t1 user should see collective bargaining widget$")
	public void _49t1_user_should_see_collective_bargaining_widget() throws Throwable {
		boolean collectivebargainingwidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.COLLECTIVE_BARGAINING_WIDGET);
		Assert.assertEquals(true, collectivebargainingwidgetfound);
	}

	@Given("^_50t1 user logs in with entity admin in entity compliance role$")
	public void _50t1_user_logs_in_with_entity_admin_in_entity_compliance_role() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYCOMPLIANCE_1, Constants.USERPASS,
		ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYCOMPLIANCE_1_SECRETKEY, Constants.USER_FED);
	}

	@Then("^_50t1 user should be able to view user directory widget and relevant links$")
	public void _50t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
		LaunchBrowserUtil.scrollToMiddle();
		boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
		Assert.assertEquals(true, userdirectorywidgetfound);

		boolean userdirectorylinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_LINK);
		Assert.assertEquals(true, userdirectorylinkfound);
	}

	@And("^_50t1 user should be able to view entity compliance widget and relevant links$")
	public void _50t1_user_should_be_able_to_view_entity_compliance_widget_and_relevant_links() throws Throwable {
		boolean entitycompliancewidgetfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE);
		Assert.assertEquals(true, entitycompliancewidgetfound);

		boolean biopreferredrequiredlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_BIOPREFERREDREQUIRED_LINK);
		Assert.assertEquals(true, biopreferredrequiredlinkfound);

		boolean biopreferredsubmittedlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_BIOPREFERREDSUBMITTED_LINK);
		Assert.assertEquals(true, biopreferredsubmittedlinkfound);
	
		// -----------
		boolean servicecontractrequiredlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_SERVICECONTRACTREQUIRED_LINK);
		Assert.assertEquals(true, servicecontractrequiredlinkfound);

		boolean servicecontractsubmittedlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_SERVICECONTRACTSUBMITTED_LINK);
		Assert.assertEquals(true, servicecontractsubmittedlinkfound);
	}

	@And("^_50t1 user should see upload aac widget$")
	public void _50t1_user_should_see_collective_bargaining_widget() throws Throwable {
		//boolean aacwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.UPLOAD_AAC_WIDGET);
		//Assert.assertEquals(true, aacwidgetfound);
	}
	
	@And("^_50t1 user should be able to view system account widget and relevant links$")
    public void _50t1_user_should_be_able_to_view_system_account_widget_and_relevant_links() throws Throwable {
		boolean systemaccountwidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.SYSTEMACCOUNT_WIDGET);
		Assert.assertEquals(true, systemaccountwidgetfound);

		boolean systemaccountlinkfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.GO_TO_SYSTEM_ACCOUNT);
		Assert.assertEquals(true, systemaccountlinkfound);

		boolean pendingapprovalsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGAPPROVAL_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingapprovalsystemaccountlinkfound);

		boolean pendingreviewaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.PENDINGREVIEW_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, pendingreviewaccountlinkfound);

		boolean approvedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.APPROVED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, approvedsystemaccountlinkfound);

		boolean deactivatedsystemaccountlinkfound = T1WorkspacePage
				.elementFound(T1WorkspacePageLocator.DEACTIVATED_SYSTEMACCOUNT_LINK);
		Assert.assertEquals(true, deactivatedsystemaccountlinkfound);  
    }
	
	
	
	 @Given("^_51t1 user logs in with data entry in entity compliance role$")
	    public void _51t1_user_logs_in_with_data_entry_in_entity_compliance_role() throws Throwable {
		 SignInUtility.signIntoWorkspace(ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1, Constants.USERPASS,
					ConstantsAccounts.DATA_ENTRY_ENTITYCOMPLIANCE_1_SECRETKEY, Constants.USER_FED);
	    }

	    @Then("^_51t1 user should not be able to view user directory widget and relevant links$")
	    public void _51t1_user_should_be_able_to_view_user_directory_widget_and_relevant_links() throws Throwable {
	    	LaunchBrowserUtil.scrollToMiddle();
			boolean userdirectorywidgetfound = T1WorkspacePage.elementFound(T1WorkspacePageLocator.USER_DIRECTORY_WIDGET);
			Assert.assertEquals(false, userdirectorywidgetfound);
	    }

	    @And("^_51t1 user should be able to view entity compliance widget and relevant links$")
	    public void _51t1_user_should_be_able_to_view_entity_compliance_widget_and_relevant_links() throws Throwable {
	    	boolean entitycompliancewidgetfound = T1WorkspacePage
					.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE);
			Assert.assertEquals(true, entitycompliancewidgetfound);

			boolean biopreferredrequiredlinkfound = T1WorkspacePage
					.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_BIOPREFERREDREQUIRED_LINK);
			Assert.assertEquals(true, biopreferredrequiredlinkfound);

			boolean biopreferredsubmittedlinkfound = T1WorkspacePage
					.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_BIOPREFERREDSUBMITTED_LINK);
			Assert.assertEquals(true, biopreferredsubmittedlinkfound);
		
			// -----------
			boolean servicecontractrequiredlinkfound = T1WorkspacePage
					.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_SERVICECONTRACTREQUIRED_LINK);
			Assert.assertEquals(true, servicecontractrequiredlinkfound);

			boolean servicecontractsubmittedlinkfound = T1WorkspacePage
					.elementFound(T1WorkspacePageLocator.ENTITY_COMPLIANCE_SERVICECONTRACTSUBMITTED_LINK);
			Assert.assertEquals(true, servicecontractsubmittedlinkfound);  
	    }

	private void beforeScenario() {
		logger.info("*************************START OF SCENARIO****************************************************");
	}

	private void afterScenario() {
		logger.info("*************************END OF SCENARIO****************************************************");
	}
}
