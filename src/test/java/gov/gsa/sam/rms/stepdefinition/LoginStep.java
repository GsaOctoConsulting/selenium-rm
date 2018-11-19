package gov.gsa.sam.rms.stepdefinition;




import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.RMWidgetUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginStep {

	private static Logger logger = LoggerFactory.getLogger(LoginStep.class);
	
	@Given("^_1 user already has dra account setup and enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_already_has_dra_account_setup_and_enters_and(String arg1, String arg2) throws Throwable {
		beforeScenario();
		SignInUtility.signIntoCommonWorkspacePage("shah.raiaan+ra@gsa.gov", Constants.userPass);
		LaunchBrowserUtil.scrolldownToRmWidget();
		CommonMethods.delay(4);
	}

	@Then("^_1 dra should see UserDirectory Widget$")
	public void dra_should_see_userdirectory_widget() throws Throwable {
		boolean rmWidgetFound = RMWidgetUtility.widgetFound();
		Assert.assertEquals(rmWidgetFound, true);
		afterScenario();
		//LaunchBrowserUtil.closeBrowsers();
	}
	
	
	
// private methods are below this line
	
	private void beforeScenario(){
		logger.info("*************************START OF SCENARIO****************************************************");
	}
	private void afterScenario(){
		logger.info("*************************END OF SCENARIO****************************************************");
	}
	
}
