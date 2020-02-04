package gov.gsa.sam.rms.utilities;

import org.openqa.selenium.WebDriver;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class VideoRecordingUtility {
	private String folderlocation;
	private ATUTestRecorder recorder;
	StringBuilder filename = new StringBuilder("");

	public StringBuilder getFilename() {
		return filename;
	}

	public void setFilename(String filenames) {
		System.out.println("The saved filename arriving is--------" + filenames);
		this.filename.append(filenames);
	}

	public VideoRecordingUtility(String folderlocation) {
		try {
			
			recorder = new ATUTestRecorder(folderlocation, filename.toString(), true);
		} catch (SecurityException | ATUTestRecorderException e) {
			e.printStackTrace();
		}
	}

	public String getFolderlocation() {
		return folderlocation;
	}

	public void setFolderlocation(String folderlocation) {
		this.folderlocation = folderlocation;
	}

	/**
	 * this methord in addition to initiating recording will also name the file in
	 * the following format - FeatureName+scenario no, eg Login_1
	 * 
	 * @throws SecurityException
	 * @throws ATUTestRecorderException
	 */
	public void start() throws SecurityException, ATUTestRecorderException {
		recorder.start();
	}

	public void stop() throws ATUTestRecorderException {
		recorder.stop();

	}

}
