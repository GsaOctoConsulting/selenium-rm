package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.RequestRolePageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;

/**
 * This class refers to the page where the users land
 * after filling out the CommonProfile Page after signup
 * Users are able to request a bottom-up role from here
 * or skip it altogether and process to the Workspace Page
 *
 */
/**
 * @author ShahMRaiaan
 *
 */
public class RequestRoleOptionalPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(RequestRoleOptionalPage.class);

	private RequestRoleOptionalPage() {
	}

	public static WebDriver getDriver() {
		return RequestRoleOptionalPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RequestRoleOptionalPage.driver = driver;
	}

	public static void clickSkipAndFinish() {
		driver.findElement(By.id("cancel-button")).click();
		LaunchBrowserUtil.delay(2);
		T1WorkspacePage.setDriver(driver);
	}

	public static void enterSupervisorName(String supervisorname) {
		driver.findElement(By.id("supervisor-name")).sendKeys(supervisorname);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterSupervisorEmail(String supervisoremail) {
		driver.findElement(By.id("supervisor-email")).sendKeys(supervisoremail);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterAdditionalDetails(String details) {
		driver.findElement(By.id("additional-details")).sendKeys(details);
		LaunchBrowserUtil.delay(1);
	}

	public static void clickFinishButton() {
		driver.findElement(By.id("done-button")).click();
		T1WorkspacePage.setDriver(driver);
		LaunchBrowserUtil.delay(2);
	}

	/**
	 * this method is used to select the org from the org picker
	 * 
	 * @param org              the name of the org
	 * @param dropdownOptionNo the option number to be selected
	 * @return true if the org was found, false otherwise
	 */
	public static boolean selectOrgIfFound(String org, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("request-access-org-pickerpicker")).sendKeys(org);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(By.className("multiple-values"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(org.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	/**
	 * @param roleName the name of the role
	 * @return true if the role was found, false otherwise
	 */
	public static boolean selectRoleIfFound(String roleName) {
		boolean roleFound = false;
		Select role = new Select(driver.findElement(RequestRolePageLocator.ROLE_SELECTOR));

		try {
			role.selectByVisibleText(roleName);
			roleFound = true;
		} catch (NoSuchElementException e) {

			return roleFound;
		}
		return roleFound;
	}

	/**
	 * @param domainName the name of the domain
	 * @return true if domain was found, false otherwise
	 */
	public static boolean selectDomainIfFound(String domainName) {
		boolean domainFound = false;
		driver.findElement(By.tagName("textarea")).sendKeys(domainName);
		LaunchBrowserUtil.delay(3);
		List<WebElement> domain = driver.findElements(RequestRolePageLocator.DOMAIN_SELECTOR);
		logger.info(("The size of the list is......" + domain.size()));

		for (int i = 0; i < domain.size(); i++) {
			WebElement currentDomain = domain.get(i);
			logger.info(currentDomain.getText());
			if (domainName.equals(currentDomain.getText())) {
				domainFound = true;
				currentDomain.click();
				return domainFound;
			}
		}
		return domainFound;
	}

	public static void clickDomainDropdown() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(RequestRolePageLocator.DOMAINDROPDOWN_ICON).click();
	}
}
