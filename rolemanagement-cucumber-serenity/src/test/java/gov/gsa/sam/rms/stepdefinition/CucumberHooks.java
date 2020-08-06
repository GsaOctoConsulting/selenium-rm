package gov.gsa.sam.rms.stepdefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

public class CucumberHooks {
	private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
		
	@Before
	public void beforeScenario(Scenario scenario) {
		logger.info("-------------------------------Start of the scenario--------------------------------");
		logger.info("Environment initialized is --- "+ Constants.EMAIL_ENV);
		
	}

	@After
	public void afterScenario() throws ATUTestRecorderException {
		logger.info("----------------------End of the scenario--------------------------");
		LaunchBrowserUtil.closeBrowsers();
	}
	
}
