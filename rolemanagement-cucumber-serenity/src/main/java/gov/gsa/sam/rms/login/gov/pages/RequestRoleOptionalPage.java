package gov.gsa.sam.rms.login.gov.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.RequestRolePageLocator;
import gov.gsa.sam.rms.pages.MyWorkspacePage;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;

public class RequestRoleOptionalPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(RequestRoleOptionalPage.class);

	private RequestRoleOptionalPage() {

	}

	public static void clickSkipAndFinish() {
		driver.findElement(By.id("cancel-button")).click();
		CommonMethods.delay(2);
		MyWorkspacePage.setDriver(driver);
	}

	public static void enterSupervisorName(String supervisorname) {
		driver.findElement(By.id("supervisor-name")).sendKeys(supervisorname);
		CommonMethods.delay(1);
	}

	public static void enterSupervisorEmail(String supervisoremail) {
		driver.findElement(By.id("supervisor-email")).sendKeys(supervisoremail);
		CommonMethods.delay(1);
	}

	public static void enterAdditionalDetails(String details) {
		driver.findElement(By.id("additional-details")).sendKeys(details);
		CommonMethods.delay(1);
	}

	public static void clickFinishButton() {
		driver.findElement(By.id("done-button")).click();
		MyWorkspacePage.setDriver(driver);
		CommonMethods.delay(2);
	}

	public static boolean selectOrgIfFound(String org, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("request-access-org-pickerpicker")).sendKeys(org);
		CommonMethods.delay(3);
		List<WebElement> orgList = driver.findElements(By.className("multiple-values"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(org.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			CommonMethods.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

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

	public static boolean selectDomainIfFound(String domainName) {
		boolean domainFound = false;
		driver.findElement(By.tagName("textarea")).sendKeys(domainName);
		CommonMethods.delay(3);
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
		CommonMethods.delay(Constants.SECONDS);
		driver.findElement(RequestRolePageLocator.DOMAINDROPDOWN_ICON).click();
	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return RequestRoleOptionalPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		RequestRoleOptionalPage.driver = driver;
	}

}
