package gov.gsa.sam.rms.utilities;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CustomWaitsUtility {
	private static Logger logger = LoggerFactory.getLogger(CustomWaitsUtility.class);

	public static WebElement visibilityOf(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(LaunchBrowserUtil.getDriver(), timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement elementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(LaunchBrowserUtil.getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public static List<WebElement> visibilityOfAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(LaunchBrowserUtil.getDriver(), timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
}