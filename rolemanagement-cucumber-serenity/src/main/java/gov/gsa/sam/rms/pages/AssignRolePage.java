package gov.gsa.sam.rms.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.RequestRolePageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import org.openqa.selenium.support.ui.Select;

/**
 * This class refers to the page visible during role assignment
 *
 */
public class AssignRolePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(AssignRolePage.class);

	private AssignRolePage() {
	}

	public static WebDriver getDriver() {
		return AssignRolePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		AssignRolePage.driver = driver;
	}

	public static void clickRoleDropdown() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(AssignRolePageLocator.ROLE_SELECTOR).click();
	}

	public static void clickDomainDropdown() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(AssignRolePageLocator.DOMAINDROPDOWN_ICON).click();
	}

	/**
	 * this method will select the given role name if found
	 * 
	 * @param roleName the role name to be selected
	 * @return true if found
	 */
	public static boolean selectRoleIfFound(String roleName) {
		boolean roleFound = false;
		Select role = new Select(driver.findElement(AssignRolePageLocator.ROLE_SELECTOR));

		try {
			role.selectByVisibleText(roleName);
			roleFound = true;
		} catch (NoSuchElementException e) {

			return roleFound;
		}
		return roleFound;
	}

	/**
	 * this overloaded method will select the given role name if both the provided
	 * role name and corresponding domain for that role is found
	 * 
	 * @param roleName   the role name to be selected
	 * @param domainName the domain for the given role
	 * @return true if found
	 */
	public static boolean selectRoleIfFound(String roleName, String domainName) {
		boolean roleFound = false;

		WebElement roleselector = driver.findElement(AssignRolePageLocator.ROLE_SELECTOR);
		// roleselector.click();
		List<WebElement> optgroupdomains = roleselector.findElements(By.tagName("optgroup"));
		logger.info("The number of domains found is-" + optgroupdomains.size());
		for (int i = 0; i < optgroupdomains.size(); i++) {
			WebElement domain = optgroupdomains.get(i);
			String domainlabel = optgroupdomains.get(i).getAttribute("label");
			logger.info("The name of the domain is-- " + domainlabel);
			if (domainName.trim().equals(domainlabel.trim())) {

				List<WebElement> roles = domain.findElements(By.tagName("option"));
				logger.info("The number of roles found for this domain is--- " + roles.size());

				for (int j = 0; j < roles.size(); j++) {

					WebElement role = roles.get(j);
					logger.info(role.getText());
					if (roleName.equals(role.getText())) {
						logger.info("The role name was found");
						roleFound = true;
						role.click();
						break;
					}
				}
				return roleFound;
			}
		}
		return roleFound;

	}

	/**
	 * this method will select the given domain name if found
	 * 
	 * @param domainName the domain name to be selected
	 * @return true if found
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
	 * this method will select the given org name if found
	 * 
	 * @param orgName the org name to be selected
	 * @return true if found
	 */
	public static boolean selectOrgIfFound(String orgName) {
		boolean orgFound = false;
		driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(orgName);
		List<WebElement> orgList = driver.findElements(AssignRolePageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(0);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgName.toLowerCase())) {
			orgList.get(0).click();
			LaunchBrowserUtil.delay(3);
			driver.findElement(AssignRolePageLocator.CONTAINER).click();
			orgFound = true;
		}
		return orgFound;
	}

	/**
	 * this overloaded method will use the provided orgName as a search term and
	 * then select the exact number of dropdown option from the suggested list if
	 * applicable. <br>
	 * 
	 * @param orgName          the search term
	 * @param dropdownOptionNo the option number to be selected
	 * @return true if any org with the given search term is found, false otherwise
	 */
	public static boolean selectOrgIfFound(String orgName, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).sendKeys(orgName);
		LaunchBrowserUtil.delay(4);
		List<WebElement> orgList = driver.findElements(By.xpath("//li[starts-with(@role, 'option')]"));

		logger.info(("The size of the list is......" + orgList.size()));
		LaunchBrowserUtil.delay(2);
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgName.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			LaunchBrowserUtil.delay(3);
			driver.findElement(AssignRolePageLocator.CONTAINER).click();
			orgFound = true;
		}
		return orgFound;
	}

	public static void writeComment(String string) {
		driver.findElement(AssignRolePageLocator.COMMENT_TEXTBOX).sendKeys(string);
		LaunchBrowserUtil.delay(5);

	}

	public static void clickDone() {
		LaunchBrowserUtil.delay(3);
		driver.findElement(AssignRolePageLocator.DONE_BUTTON).click();
		UserDirectoryViewAccessPage.setDriver(AssignRolePage.getDriver());
		MyRolesPage.setDriver(AssignRolePage.getDriver());
		LaunchBrowserUtil.delay(6);

	}

	public static void clickAssign() {
		driver.findElement(AssignRolePageLocator.DONE_BUTTON).click();
		MyRolesPage.setDriver(AssignRolePage.getDriver());
		LaunchBrowserUtil.delay(4);

	}

	public static String getCurrentTextInOrgPicker() {
		return driver.findElement(AssignRolePageLocator.ORGPICKER_TEXTAREA).getText();

	}

	public static void goToFeedsPage() {
		LaunchBrowserUtil.delay(3);
		driver.findElement(AssignRolePageLocator.FEED_NOTIFICATION_ICON).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(AssignRolePageLocator.SHOWMORE_REQUEST_LINK).click();
		LaunchBrowserUtil.delay(3);
		FeedsRequestPage.setDriver(AssignRolePage.getDriver());

	}

	/**
	 * this method searches for the presence or absence of an element in this page
	 * 
	 * @param locator the element to be searched for
	 * @return true if found, false otherwise
	 */
	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void clickCancel() {
		driver.findElement(
				By.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/form/div[2]/button[1]"))
				.click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickCancelButtonWhenAlertIsOn() {
		driver.findElement(
				By.xpath("//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/form/div[3]/button[1]"))
				.click();

	}

	public static void clickCloseButton() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div/sam-button/button"))
				.click();
		LaunchBrowserUtil.delay(4);
	}

	/**
	 * this method is used for nonfed users only and uses the provided entityName as
	 * a search term and then select the exact number of dropdown option from the
	 * suggested list if applicable. <br>
	 * 
	 * @param entityName       the nonfed entity used as the search term
	 * @param dropdownOptionNo the option number to be selected
	 * @return true if any entity with the given search term is found, false
	 *         otherwise
	 */
	public static boolean selectEntityNonFedIfFound(String entity, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("grant-access-entity-picker-ac-textarea")).sendKeys(entity);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(By.xpath("//li[starts-with(@role, 'option')]"));
		logger.info(("The size of the list is......" + orgList.size()));
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
		Select role = new Select(driver.findElement(AssignRolePageLocator.ROLE_SELECTOR));
		try {
			role.selectByVisibleText(roleName);
			roleFound = true;
		} catch (NoSuchElementException e) {

			return roleFound;
		}
		return roleFound;
	}

	public static void cancelSelectedOrg() {
		driver.findElement(By.xpath(
				"//*[@id=\"org-picker\"]/div[1]/div/div/sam-agency-selector/sam-label-wrapper/div/div[2]/div[1]/sam-sds-autocomplete/sam-sds-selected-result/ul/li[1]/div/span"))
				.click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clearEntitySelector() {
		driver.findElement(AssignRolePageLocator.ENTITYPICKER_TEXTAREA).clear();
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
