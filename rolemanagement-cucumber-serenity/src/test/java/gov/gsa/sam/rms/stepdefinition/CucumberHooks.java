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
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.VideoRecordingUtility;

public class CucumberHooks {
	private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
	VideoRecordingUtility recorder;
	
	@Before
	public void beforeScenario(Scenario scenario) {
		logger.info("-------------------------------Start of the scenario--------------------------------");
		//---------- configuring unique file name-------
		Collection<String> lines = scenario.getSourceTagNames();
		String filename=String.join("-", lines);
		recorder = new VideoRecordingUtility(Constants.SCENARIO_VIDEO_FILE_PATH,filename);
		recorder.start();	
		}

	@After
	public void afterScenario() throws ATUTestRecorderException {
		logger.info("----------------------End of the scenario--------------------------");
		recorder.stop();
		LaunchBrowserUtil.closeBrowsers();
	}
	
}
