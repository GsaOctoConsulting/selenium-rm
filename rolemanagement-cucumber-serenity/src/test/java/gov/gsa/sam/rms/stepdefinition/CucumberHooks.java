package gov.gsa.sam.rms.stepdefinition;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.VideoRecordingUtility;

public class CucumberHooks {
	private static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);
	VideoRecordingUtility recorder;
		
	@Before
	public void beforeScenario(Scenario scenario) {
		logger.info("-------------------------------Start of the scenario--------------------------------");
		logger.info("Environment initialized is --- "+ Constants.EMAIL_ENV);
		//---------- configuring unique file name-------
	if(Constants.VIDEO_RECORD_ON) {
		Collection<String> lines = scenario.getSourceTagNames();
		String filename=String.join("-", lines);
		recorder = new VideoRecordingUtility(Constants.SCENARIO_VIDEO_FILE_PATH,filename);
		recorder.start();		
	}}

	@After
	public void afterScenario() throws ATUTestRecorderException {
		logger.info("----------------------End of the scenario--------------------------");
		if(Constants.VIDEO_RECORD_ON) {
		recorder.stop();
		}
		LaunchBrowserUtil.closeBrowsers();
	}
	
}
