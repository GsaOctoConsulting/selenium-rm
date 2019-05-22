package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.RoleMigrationiPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class RoleMigrationPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleMigrationPage.class);

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

	private RoleMigrationPage() {
	}

	public static WebDriver getDriver() {
		return RoleMigrationPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleMigrationPage.driver = driver;
	}

	public static void enterLegacyUserid(String userid) {
		driver.findElement(By.id("legacy-username")).sendKeys("");
		driver.findElement(By.id("legacy-username")).clear();
		driver.findElement(By.id("legacy-username")).sendKeys(userid);
		CommonMethods.delay(1);

	}

	public static void enterLegacyUserPassword(String userpassword) {
		driver.findElement(By.id("legacy-password")).sendKeys("");
		driver.findElement(By.id("legacy-password")).clear();
		driver.findElement(By.id("legacy-password")).sendKeys(userpassword);
		CommonMethods.delay(1);

	}

	public static void clickMigrateButton() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/section/ng-component/div[2]/form/div/div[2]/div/button"))
				.click();
	}

	public static String getAlertMessage() {
		CommonMethods.delay(2);
		List<WebElement> alert = driver.findElements(By.tagName("sam-alert"));
		for (int i = 0; i < alert.size(); i++) {
			if (i == 0) {
				CommonMethods.delay(1);
				return alert.get(i).findElement(By.tagName("h3")).getText();
			}
		}
		return "Nothing found";
	}

	public static void goToWorkspace() {
		CommonMethods.delay(1);
		driver.findElement(By.id("headerIconMenu")).click();
		CommonMethods.delay(1);
		driver.findElement(By.id("header-link-workspace")).click();

	}
}
