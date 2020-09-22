package gov.gsa.sam.rms.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.pages.T1WorkspacePage;

public class FHUtility {
	private static Logger logger = LoggerFactory.getLogger(FHUtility.class);
	static WebDriver driver = LaunchBrowserUtil.getDriver();

	public static void goToOrgDetails(String org) {
		driver.findElement(By.xpath("//input[starts-with(@aria-label, 'search federal hierarchy')]")).sendKeys(org);
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/ng-component/div[1]/div/div[2]/div/i")).click();

		driver.findElement(
				By.xpath("//*[@id=\"main-container\"]/ng-component/div[1]/div/div[4]/div/div[2]/div/div[2]/div[1]/a"))
				.click();
	}

	public static String createSubTier(String name, String agencycode, String cgac, String tas2code) {
		driver.findElement(By.xpath("//*[@id=\"primary-content\"]/ng-component/div/div[1]/div[1]/button")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("orgName-input")).sendKeys(name);

		// entering date, start date is always one day behind current date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String startdate = dateFormat.format(date);
		// DeptAgencyEndDate.sendKeys(EndDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// manipulate date
		c.add(Calendar.DATE, -1);
		// convert calendar to date
		Date currentDateMinusOne = c.getTime();
		logger.info("The start date entered is- " + dateFormat.format(currentDateMinusOne));

		driver.findElement(By.id("org-start-date month required. Enter Month Here"))
				.sendKeys(dateFormat.format(currentDateMinusOne));

		// ------------------creating endate---
		c.add(Calendar.DATE, +2);
		// convert calendar to date
		Date currentDatePlusOne = c.getTime();
		logger.info("The end date entered is- " + dateFormat.format(currentDatePlusOne));
		driver.findElement(By.id("org-end-date month Enter Month Here"))
				.sendKeys(dateFormat.format(currentDatePlusOne));

		// enter agency code
		driver.findElement(By.id("fpds-input")).sendKeys(agencycode);
		driver.findElement(By.id("confirmButton")).click();
		CustomWaitsUtility.elementToBeClickable(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/div/div/div[2]/create-org-form/div/div[2]/div/div[2]/sam-button/button"),
				3).click();
		return name;
	}

	public static void gotoWorkspacePage() {
		CustomWaitsUtility.elementToBeClickable(By.linkText("Workspace"), 3).click();
	}

	public static String createOffice(String officename, String aaccode) {
		CustomWaitsUtility
				.elementToBeClickable(By.xpath("//*[@id=\"primary-content\"]/ng-component/div/div[1]/div[1]/button"), 2)
				.click();
		driver.findElement(By.id("ofcAAC-input")).sendKeys(aaccode);
		driver.findElement(By.id("ofcName-input")).sendKeys(officename);
		// entering date, start date is always one day behind current date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String startdate = dateFormat.format(date);
		// DeptAgencyEndDate.sendKeys(EndDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// manipulate date
		c.add(Calendar.DATE, -1);
		// convert calendar to date
		Date currentDateMinusOne = c.getTime();
		logger.info("The start date entered is- " + dateFormat.format(currentDateMinusOne));

		driver.findElement(By.id("ofc-start-date month required. Enter Month Here"))
				.sendKeys(dateFormat.format(currentDateMinusOne));

		// ------------------creating endate---
		c.add(Calendar.DATE, +2);
		// convert calendar to date
		Date currentDatePlusOne = c.getTime();
		logger.info("The end date entered is- " + dateFormat.format(currentDatePlusOne));
		driver.findElement(By.id("ofc-end-date month Enter Month Here"))
				.sendKeys(dateFormat.format(currentDatePlusOne));

		driver.findElement(By.id("street1-Mailing-Address")).sendKeys("123 MAIN ST");
		driver.findElement(By.id("Mailing-Addresscountry")).sendKeys("USA");
		CustomWaitsUtility.visibilityOf(By.id("resultItem_0"), 3).click();
		driver.findElement(By.id("Mailing-Addresszip")).sendKeys("20191");
		driver.findElement(By.id("Mailing-Addresscity")).sendKeys("reston");
		CustomWaitsUtility.visibilityOf(By.id("result_0"), 3).click();
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/div/div/div[2]/page/div/div/div[2]/div/div[1]/div[2]/div/div[3]/sam-button/button"))
				.click();
		return officename;
	}

}
