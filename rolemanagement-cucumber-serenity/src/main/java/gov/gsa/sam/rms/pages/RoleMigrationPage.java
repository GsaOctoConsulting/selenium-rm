package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;
import gov.gsa.sam.rms.locators.RoleMigrationiPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the Page under the Role Migration tab in the
 * AccountDetails Page....consider this as a subpage under AccountDetails
 *
 */
public class RoleMigrationPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleMigrationPage.class);

	private RoleMigrationPage() {
	}

	public static WebDriver getDriver() {
		return RoleMigrationPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleMigrationPage.driver = driver;
	}

	/**
	 * @param legacydomainname the name for the legacy domain
	 * @return true if found in dropdown
	 */
	public static boolean selectLegacyDomainIfFound(String legacydomainname) {
		boolean domainfound = false;
		Select domain = new Select(driver.findElement(RoleMigrationiPageLocator.DOMAIN_SELECTOR));

		try {
			domain.selectByVisibleText(legacydomainname);
			domainfound = true;
		} catch (NoSuchElementException e) {

			return domainfound;
		}
		return domainfound;

	}

	public static void enterLegacyUserid(String userid) {
		driver.findElement(By.id("legacy-username")).sendKeys("");
		driver.findElement(By.id("legacy-username")).clear();
		driver.findElement(By.id("legacy-username")).sendKeys(userid);
		LaunchBrowserUtil.delay(1);

	}

	public static void enterLegacyUserPassword(String userpassword) {
		driver.findElement(By.id("legacy-password")).sendKeys("");
		driver.findElement(By.id("legacy-password")).clear();
		driver.findElement(By.id("legacy-password")).sendKeys(userpassword);
		LaunchBrowserUtil.delay(1);

	}

	public static void clickMigrateButton() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/section/ng-component/div[2]/form/div/div[2]/div/button"))
				.click();
	}

	/**
	 * this method returns the header alert message so that they can be asserted in
	 * test scenarios
	 * 
	 * @return the header of the alert message, eg. Error, if no alert it sends
	 *         "Nothing found"
	 */
	public static String getAlertMessage() {
		LaunchBrowserUtil.delay(2);
		List<WebElement> alert = driver.findElements(By.className("usa-alert-warning"));
		logger.info("----------The size of the alert list is- "+ alert.size());
		
		/*for (int i = 0; i < alert.size(); i++) {
			if (i == 0) {
				LaunchBrowserUtil.delay(1);
				return alert.get(i).findElement(By.tagName("h3")).getText();
			}
		}*/
		return alert.get(0).findElement(By.tagName("p")).getText();
	}

	public static void goToWorkspace() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("headerIconMenu")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("header-link-workspace")).click();

	}

	public static void resetTestData(String url) {
		LaunchBrowserUtil.openThisBrowser();
		LaunchBrowserUtil.enterUrl(url);
		LaunchBrowserUtil.delay(2);
		LaunchBrowserUtil.closeBrowsers();
	}

	public static void clickShowMoreLink() {
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.linkText("... Show more")).click();
		LaunchBrowserUtil.delay(2);
		
	}
}
