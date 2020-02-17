package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.RequestRolePageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to the page showing during bottom-up role request when the
 * user clicks the request role button
 *
 */
public class RequestRolePage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RequestRolePage.class);

	private RequestRolePage() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		RequestRolePage.driver = driver;
	}

	public static void writeSupervisorName(String name) {
		driver.findElement(RequestRolePageLocator.SUPERVISOR_NAME_TEXTBOX).sendKeys(name);
	}

	public static void writeSupervisorEmail(String email) {
		driver.findElement(RequestRolePageLocator.SUPERVISOR_EMAIL_TEXTBOX).sendKeys(email);
	}

	public static void writeComment(String comment) {
		driver.findElement(RequestRolePageLocator.COMMENT_TEXTBOX).sendKeys(comment);
		LaunchBrowserUtil.delay(3);
	}

	public static void clickSubmit() {
		driver.findElement(RequestRolePageLocator.SUBMIT_BUTTON).click();
		MyRolesPage.setDriver(RequestRolePage.getDriver());
		LaunchBrowserUtil.delay(4);
	}

	public static void clickDomainDropdown() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(RequestRolePageLocator.DOMAINDROPDOWN_ICON).click();
	}

	public static void clickPendingRoleRequest() {
		driver.findElement(RequestRolePageLocator.PENDING_REQUESTS).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void clickPending() {
		driver.findElement(RequestRolePageLocator.PENDING).click();
		RoleRequestPendingPage.setDriver(driver);
		LaunchBrowserUtil.delay(1);
	}

	/**
	 * @param roleName the name of the role to be requested
	 * @return true if the role is found, false otherwise
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
	 * @return true if the domain is found, false otherwise
	 */
	public static boolean selectDomainIfFound(String domainName) {
		boolean domainFound = false;
		clickDomainDropdown();
		List<WebElement> domain = driver.findElements(AssignRolePageLocator.DOMAIN_SELECTOR);
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

	/**
	 * @param orgName the name of the org
	 * @return true if the org is found, false otherwise
	 */
	public static boolean selectOrgIfFound(String orgName) {
		boolean orgFound = false;
		driver.findElement(RequestRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(orgName);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(RequestRolePageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(0);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgName.toLowerCase())) {
			driver.findElement(By.xpath("//*[@id=\"federalHierarchy-listbox\"]/li[1]/div[1]/div")).click();
			// firstOrg.click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	/**
	 * this method validates whether all the search results contain the search term
	 * e.g 'general' as a search term should contain only results with that term in
	 * it.
	 * 
	 * @param search    the search term eg general services administration
	 * @param givenWord general
	 * @return true if all search results are validated
	 */
	public static boolean validateOrgSuggestionContainsGivenWord(String search, String givenWord) {
		boolean allOrgsContainsGivenWord = true;
		driver.findElement(RequestRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(search);
		List<WebElement> orgList = driver.findElements(RequestRolePageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));

		WebElement firstOrg = orgList.get(0);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());

		for (WebElement org : orgList) {
			logger.info(org.getText());
			if (org.getText().contains(givenWord) == false) {
				allOrgsContainsGivenWord = false;
			}
		}
		return allOrgsContainsGivenWord;
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static boolean selectEntityNonFedIfFound(String entity, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(RequestRolePageLocator.ENTITYPICKER_TEXTAREA).clear();
		driver.findElement(RequestRolePageLocator.ENTITYPICKER_TEXTAREA).sendKeys(entity);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(By.xpath("//li[starts-with(@role, 'option')]"));
		//List<WebElement> orgList = driver.findElements(By.className("selected-item"));
		logger.info(("The size of the list is......" + orgList.size()));
		if(orgList.size()==0) {
			return orgFound;
		}
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(entity.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	public static boolean selectEntityRoleIfFound(String roleName) {
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

	public static boolean selectEntityDomainIfFound(String domainname) {
		boolean domainFound = false;
		driver.findElement(By.id("domain-ac-textarea")).sendKeys("");
		LaunchBrowserUtil.delay(3);
		List<WebElement> domain = driver.findElements(By.xpath("//li[starts-with(@role, 'option')]"));
		logger.info(("The size of the list is......" + domain.size()));

		for (int i = 0; i < domain.size(); i++) {
			WebElement currentDomain = domain.get(i);
			logger.info(currentDomain.getText());
			if (domainname.equals(currentDomain.getText())) {
				domainFound = true;
				currentDomain.click();
				return domainFound;
			}
		}
		return domainFound;
	}
	
}