package gov.gsa.sam.rms.utilities;

import org.openqa.selenium.WebDriver;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class VideoRecordingUtility {
	private  String folderlocation;
	private  ATUTestRecorder recorder;
	
	public String getFolderlocation() {
		return folderlocation;
	}

	public void setFolderlocation(String folderlocation) {
		this.folderlocation = folderlocation;
	}

	/**
	 * this methord in addition to start recording will also
	 * name the file in the format - ClassName+scenario no, eg Login_1
	 * @throws SecurityException
	 * @throws ATUTestRecorderException
	 */
	public void start() throws SecurityException, ATUTestRecorderException {
		recorder = new ATUTestRecorder(
				folderlocation,
				this.getClass().getSimpleName() + new Object() {
				}.getClass().getEnclosingMethod().getName().substring(0, 2), false);
		recorder.start();
	}
	
	
	
	
	
}
