package gov.gsa.sam.rms.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.pages.AssignRolePage;
import gov.gsa.sam.rms.pages.T1WorkspacePage;

public class FHUtility {
	private static Logger logger = LoggerFactory.getLogger(FHUtility.class);

	public static void goToOrgDetails(String org) {
		LaunchBrowserUtil.delay(1);
		LaunchBrowserUtil.driver.findElement(By.xpath("//input[starts-with(@aria-label, 'search federal hierarchy')]"))
				.sendKeys(org);
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver
				.findElement(By.xpath("//*[@id=\"main-container\"]/ng-component/div[1]/div/div[2]/div/i")).click();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver
				.findElement(By.xpath(
						"//*[@id=\"main-container\"]/ng-component/div[1]/div/div[4]/div/div[2]/div/div[2]/div[1]/a"))
				.click();
	}

	public static String createSubTier(String name, String agencycode, String cgac, String tas2code) {
		LaunchBrowserUtil.driver
				.findElement(By.xpath("//*[@id=\"primary-content\"]/ng-component/div/div[1]/div[1]/button")).click();
		LaunchBrowserUtil.delay(1);
		LaunchBrowserUtil.driver.findElement(By.id("orgName-input")).sendKeys(name);

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

		LaunchBrowserUtil.driver.findElement(By.id("org-start-date month required. Enter Month Here"))
				.sendKeys(dateFormat.format(currentDateMinusOne));

		// ------------------creating endate---
		c.add(Calendar.DATE, +2);
		// convert calendar to date
		Date currentDatePlusOne = c.getTime();
		logger.info("The end date entered is- " + dateFormat.format(currentDatePlusOne));
		LaunchBrowserUtil.driver.findElement(By.id("org-end-date month Enter Month Here"))
				.sendKeys(dateFormat.format(currentDatePlusOne));

		// enter agency code
		LaunchBrowserUtil.driver.findElement(By.id("fpds-input")).sendKeys(agencycode);
		LaunchBrowserUtil.driver.findElement(By.id("confirmButton")).click();
		CustomWaitsUtility.elementToBeClickable(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/div/div/div[2]/create-org-form/div/div[2]/div/div[2]/sam-button/button"),
				3).click();
		return name;
	}

	public static void gotoWorkspacePage() {
		LaunchBrowserUtil.delay(5);
		CustomWaitsUtility.elementToBeClickable(By.linkText("Workspace"), 7).click();
	}

	public static String createOffice(String officename, String aaccode) {
		CustomWaitsUtility
				.elementToBeClickable(By.xpath("//*[@id=\"primary-content\"]/ng-component/div/div[1]/div[1]/button"), 8)
				.click();
		LaunchBrowserUtil.driver.findElement(By.id("ofcAAC-input")).sendKeys(aaccode);
		LaunchBrowserUtil.driver.findElement(By.id("ofcName-input")).sendKeys(officename);
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

		LaunchBrowserUtil.driver.findElement(By.id("ofc-start-date month required. Enter Month Here"))
				.sendKeys(dateFormat.format(currentDateMinusOne));

		// ------------------creating endate---
		c.add(Calendar.DATE, +2);
		// convert calendar to date
		Date currentDatePlusOne = c.getTime();
		logger.info("The end date entered is- " + dateFormat.format(currentDatePlusOne));
		LaunchBrowserUtil.driver.findElement(By.id("ofc-end-date month Enter Month Here"))
				.sendKeys(dateFormat.format(currentDatePlusOne));

		LaunchBrowserUtil.driver.findElement(By.id("street1-Mailing-Address")).sendKeys("123 MAIN ST");
		LaunchBrowserUtil.driver.findElement(By.id("Mailing-Addresscountry")).sendKeys("USA");
		CustomWaitsUtility.visibilityOf(By.id("resultItem_0"), 3).click();
		LaunchBrowserUtil.driver.findElement(By.id("Mailing-Addresszip")).sendKeys("20191");
		LaunchBrowserUtil.driver.findElement(By.id("Mailing-Addresscity")).sendKeys("reston");
		CustomWaitsUtility.visibilityOf(By.id("result_0"), 3).click();

		LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/div/div/div[2]/page/div/div/div[2]/div/div[1]/div[2]/div/div[3]/sam-button/button"))
				.click();
		CustomWaitsUtility.visibilityOf(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/div/div/div[2]/page/div/div/div[2]/div/div[1]/div[2]/div/div[2]/sam-button/button"),
				15).click();
		LaunchBrowserUtil.delay(5);
		return officename;
	}

	public static void moveOfficeIntoSubtier(String parent) {
		LaunchBrowserUtil.delay(2);
		CustomWaitsUtility.visibilityOf(By.xpath("//*[@id=\"main-container\"]/ng-component/div/aside/div/ul/li[2]"), 3)
				.click();
		// code to click the office tab on the side bar
		LaunchBrowserUtil.delay(1);
		LaunchBrowserUtil.driver.findElement(By.className("federal-hierarchy-org-item-title")).click();
		LaunchBrowserUtil.delay(1);
		LaunchBrowserUtil.driver.findElement(By.id("actionsButton")).click();
		LaunchBrowserUtil.delay(1);
		LaunchBrowserUtil.driver.findElement(By.id("menuitem1")).click();
		LaunchBrowserUtil.delay(2);
		AssignRolePage.setDriver(LaunchBrowserUtil.driver);
		selectOrgIfFound(parent, 0);
		LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/fh-move-office/div/div/section/fh-move-office-form/div[8]/button[2]"))
				.click();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/fh-move-office/div/div/section/fh-move-office-review/div[5]/button[2]"))
				.click();
		LaunchBrowserUtil.delay(3);
		LaunchBrowserUtil.driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/fh-move-office/div/div/section/fh-move-office-review/sam-modal/div[2]/div/div[2]/div/sam-button[2]/button"))
				.click();
	}

	public static boolean selectOrgIfFound(String parent, int menuoptionno) {
		boolean orgFound = false;
		LaunchBrowserUtil.driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(parent);
		LaunchBrowserUtil.delay(4);
		List<WebElement> orgList = LaunchBrowserUtil.driver
				.findElements(By.xpath("//li[starts-with(@role, 'option')]"));

		logger.info(("The size of the list is......" + orgList.size()));
		LaunchBrowserUtil.delay(2);
		WebElement firstOrg = orgList.get(menuoptionno);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(parent.toLowerCase())) {
			orgList.get(menuoptionno).click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

}
