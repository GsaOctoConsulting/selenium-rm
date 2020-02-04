package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.gsa.sam.rms.utilities.VideoRecordingUtility;

public class CucumberHooks {
	private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
	VideoRecordingUtility recorder;

	@Before
	public void beforeScenario() throws SecurityException, ATUTestRecorderException {
		logger.info("-------------------------------Start of the scenario--------------------------------");
		recorder = new VideoRecordingUtility();
		recorder.setFolderlocation("");
		recorder.start();
		
		
	}

	@After
	public void afterScenario() {
		logger.info("----------------------End of the scenario--------------------------");
	}
}
