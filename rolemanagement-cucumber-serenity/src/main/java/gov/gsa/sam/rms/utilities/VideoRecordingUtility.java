package gov.gsa.sam.rms.utilities;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

/**
 * @author ShahMRaiaan
 * Description: - this class provides video recording capabilities for 
 * cucumber tests. This class needs to be instantiated in
 * the @before method in CucumberHooks.java to be executed before 
 * each scenario. 
 *
 */
public class VideoRecordingUtility {
	private String folderlocation;
	private ATUTestRecorder recorder;
	private StringBuilder filename = new StringBuilder("");

	/**
	 * @param folderlocation  the url for storing the recorded videos
	 * @param desiredfilename the name of the file to be saved
	 */
	public VideoRecordingUtility(String folderlocation, String desiredfilename) {
		try {
			recorder = new ATUTestRecorder(desiredfilename, false);
		} catch (SecurityException | ATUTestRecorderException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method will start recording and save the file as .mov format
	 * 
	 * @throws SecurityException
	 * @throws ATUTestRecorderException
	 */
	public void start() {
		try {
			recorder.start();
		} catch (ATUTestRecorderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * stop the recording
	 * 
	 * @throws ATUTestRecorderException
	 */
	public void stop() {
		try {
			recorder.stop();
		} catch (ATUTestRecorderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getFolderlocation() {
		return folderlocation;
	}

	public void setFolderlocation(String folderlocation) {
		this.folderlocation = folderlocation;
	}

	public StringBuilder getFilename() {
		return filename;
	}

	public void setFilename(String filenames) {
		this.filename.append(filenames);
	}

}
