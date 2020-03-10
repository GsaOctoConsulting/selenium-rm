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
				//------------------------------------------------------
				
			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}
		} while (currentPage < totalNoOfPages);
	}

}
