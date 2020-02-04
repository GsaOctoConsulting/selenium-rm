package gov.gsa.sam.rms.stepdefinition;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gov.gsa.sam.rms.utilities.VideoRecordingUtility;
import gov.gsa.sam.rms.utilities.Constants;

public class CucumberHooks {
	private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
	VideoRecordingUtility recorder = new VideoRecordingUtility(Constants.SCENARIO_VIDEO_FILE_PATH);
	
	@Before
	public void beforeScenario(Scenario scenario) throws SecurityException, ATUTestRecorderException {
		logger.info("-------------------------------Start of the scenario--------------------------------");

		Collection<String> lines = scenario.getSourceTagNames();
		String filenames=String.join("-", lines);
		logger.info("The name of the file is-- "+filenames);
		recorder.setFilename(filenames);
		recorder.start();
		
		//recorder.setFolderlocation(Constants.SCENARIO_VIDEO_FILE_PATH);
		//recorder.start();
		}

	@After
	public void afterScenario() throws ATUTestRecorderException {
		logger.info("----------------------End of the scenario--------------------------");
		recorder.stop();
	}
	
}
