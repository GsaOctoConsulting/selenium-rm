package gov.gsa.sam.rms.stepdefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.pages.UserDirectoryPage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.UserDirectoryWidgetUtility;

public class NonfedUserDirectoryStep {
	private static Logger logger = LoggerFactory.getLogger(NonfedUserDirectoryStep.class);

	@Given("^_1nfusdr a nonfed user with data entry with contract opp logs in$")
	public void _1nfusdr_a_nonfed_user_with_data_entry_with_contract_opp() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_1nfusdr user goes to user directory page$")
	public void _1nfusdr_user_goes_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();

	}

	@When("^_1nfusdr user selects data entry role filter and contract opp domain filter$")
	public void _1nfusdr_user_selects_data_entry_role_filter_and_contract_opp_domain_filter() throws Throwable {
		UserDirectoryPage.selectFilter(UserDirectoryPageLocator.DATA_ENTRY_FILTER);
		UserDirectoryPage.selectFilter(UserDirectoryPageLocator.DOMAIN_CONTRACTOPPORTUNITIES_FILTER);

	}

	@Then("^_1nfusdr user should be able to see the access for all the users they can see$")
	public void _1nfusdr_user_should_be_able_to_see_the_access_for_all_the_users_they_can_see() throws Throwable {

		boolean allClickable = UserDirectoryPage.ifAllUsersAreClicable(6, "");
		Assert.assertEquals(true, allClickable);
		int totalNoOfPages = UserDirectoryPage.getTotalNoOfPages();
		// int currentlyselectedPage = 3;//UserDirectoryPage.getCurrentSelectedPage();
		int currentPage = 1;
		do {// search page 1 regardless of whether other pages exist
			List<WebElement> userList = UserDirectoryPage.getUserList();
			for (int i = 0; i < userList.size(); i++) {
				WebElement currentuser = userList.get(i);
				WebElement id = currentuser.findElement(UserDirectoryPageLocator.ID);// ensures names are clickable
				logger.info("The text is ---- " + id.getText());
				boolean fedIdFound = id.getText().contains("@gsa");// ensures fed id not found
				Assert.assertEquals(false, fedIdFound);
				// ------------------------------------------------------

			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}
		} while (currentPage < totalNoOfPages);
	}

	@Given("^_2nfusdr a nonfed user with data entry with contract opp logs in$")
	public void _2nfusdr_a_nonfed_user_with_data_entry_with_contract_opp_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.NONFED_USER_1, Constants.USERPASS,
				ConstantsAccounts.NONFED_USER_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_2nfusdr user goes to user directory page$")
	public void _2nfusdr_user_goes_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@And("^_2nfusdr user selects octo consulting group in entity picker$")
	public void _2nfusdr_user_selects_octo_consulting_group_in_entity_picker() throws Throwable {
		UserDirectoryPage.searchEntityInEntityPicker(Constants.ORG_OCTO_CONSULTING_GROUP);
	}

	@When("^_2nfusdr user searches for a user in entity registration domain$")
	public void _2nfusdr_user_searches_for_a_user_in_entity_registration_domain() throws Throwable {
		// UserDirectoryPage.searchUserInEntityPicker(ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYREGISTRATION_1);
	}

	@Then("^_2nfusdr user should not be clickable$")
	public void _2nfusdr_user_should_not_be_clickable() throws Throwable {
		boolean nameisclickable = UserDirectoryPage.ifAllUsersAreClicable(1, "");
		Assert.assertEquals(false, nameisclickable);
	}

	@Given("^_3nfusdr a nonfed user with admin role in entity registration logs in$")
	public void _3nfusdr_a_nonfed_user_with_admin_role_in_entity_registration_logs_in() throws Throwable {
		SignInUtility.signIntoWorkspace(ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYREGISTRATION_1, Constants.USERPASS,
				ConstantsAccounts.ENTITY_ADMINISTRATOR_ENTITYREGISTRATION_1_SECRETKEY, Constants.USER_NONFED);
	}

	@And("^_3nfusdr user goes to user directory page$")
	public void _3nfusdr_user_goes_to_user_directory_page() throws Throwable {
		LaunchBrowserUtil.scrollAllTheWayDown();
		UserDirectoryWidgetUtility.clickUserDirectoryLink();
	}

	@When("^_3nfusdr user selects contract opportunities domain filter$")
	public void _3nfusdr_user_selects_contract_opportunities_domain_filter() throws Throwable {
		UserDirectoryPage.selectFilter(UserDirectoryPageLocator.DOMAIN_CONTRACTOPPORTUNITIES_FILTER);
	}

	@Then("^_3nfusdr user should see all the users as clickable$")
	public void _3nfusdr_user_should_see_all_the_users_as_clickable() throws Throwable {
		boolean nameisclickable = UserDirectoryPage.ifAllUsersAreClicable(4, "");
		Assert.assertEquals(true, nameisclickable);
	}

	@Given("^_4nfusdr user logs in with admin in entity registration in octo and data entry in contract opp in ibm$")
	public void _4nfusdr_user_logs_in_with_admin_in_entity_registration_in_octo_and_data_entry_in_contract_opp_in_ibm()
			throws Throwable {
		SignInUtility.signIntoWorkspace(
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM,
				Constants.USERPASS,
				ConstantsAccounts.NONFED_ENTITYADMIN_ENTITYREGISTRATIONI_OCTO_DATAENTRY_CONTRACTOPP_IBM_SECRETKEY,
				Constants.USER_NONFED);
	}

	@And("^_4nfusdr user goes to user directory page and searches for octo user with data entry in entity registration$")
	public void _4nfusdr_user_goes_to_user_directory_page_and_searches_for_octo_user_with_data_entry_in_entity_registration()
			throws Throwable {

	}

	@Then("^_4nfusdr user should be clickable$")
	public void _4nfusdr_user_should_be_clickable() throws Throwable {

	}

	@When("^_4nfusdr logged in user searches for user with data entry in entity compliance in ibm$")
	public void _4nfusdr_logged_in_user_searches_for_user_with_data_entry_in_entity_management_in_ibm()
			throws Throwable {

	}

	@Then("^_4nfusdr user should not be clickable$")
	public void _4nfusdr_user_should_not_be_clickable() throws Throwable {

	}

}
