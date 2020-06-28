package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the Page for nonfed role invitations seen by the user
 *
 */
public class PendingRoleInvitationPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(PendingRoleInvitationPage.class);

	private PendingRoleInvitationPage() {
	}

	public static WebDriver getDriver() {
		return PendingRoleInvitationPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		PendingRoleInvitationPage.driver = driver;
	}

	public static void writeAdditionalInformation(String comments) {
		driver.findElement(By.id("additional-information")).sendKeys(comments);
		LaunchBrowserUtil.delay(1);

	}

	public static void clickAcceptButton() {
		driver.findElement(By.id("accept-button")).click();
		LaunchBrowserUtil.delay(2);
	}

	public static String getHeading() {
		return driver.findElement(By.tagName("h1")).getText();

	}

	public static void clickCloseButton() {
		driver.findElement(By.id("close-button")).click();
		FeedsRequestPage.setDriver(driver);
		LaunchBrowserUtil.delay(2);

	}

	public static String getAcceptedTime() {
		String acceptedtime = driver
				.findElement(By.xpath("//*[@id=\"main-container\"]/nonfed-role-invite/div/div[2]/div/div[3]/div[3]"))
				.getText();
		String formattedacceptedtime = acceptedtime.substring(acceptedtime.length() - 7);
		String spaceremovedformattedtime = formattedacceptedtime.replaceAll("\\s", "");
		logger.info("The accepted time for this request was - " + spaceremovedformattedtime);
		return spaceremovedformattedtime;
	}

}
