package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.BulkUpdatePageLocator;
import gov.gsa.sam.rms.locators.RequestRolePageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * This class refers to the first page seen when doing bulk updates
 *
 */
public class BulkUpdatePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(BulkUpdatePage.class);

	private BulkUpdatePage() {
	}

	public static WebDriver getDriver() {
		return BulkUpdatePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		BulkUpdatePage.driver = driver;
	}

	/**
	 * this method will select the given org name if found
	 * 
	 * @param orgName the role name to be selected
	 * @return true if found
	 */
	public static boolean selectOrgIfFound(String orgName) {
		boolean orgFound = false;
		driver.findElement(BulkUpdatePageLocator.ORGPICKER_TEXTAREA).sendKeys(orgName);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(BulkUpdatePageLocator.ORG_SELECTOR);
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
	 * this method will select the given role name if found
	 * 
	 * @param roleName the role name to be selected
	 * @return true if found
	 */
	public static boolean selectRoleIfFound(String roleName) {
		boolean roleFound = false;
		Select role = new Select(driver.findElement(BulkUpdatePageLocator.ROLE_SELECTOR));
		try {
			role.selectByVisibleText(roleName);
			roleFound = true;
		} catch (NoSuchElementException e) {
			return roleFound;
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
		driver.findElement(BulkUpdatePageLocator.DOMAIN_TEXTAREA).sendKeys(domainName);
		LaunchBrowserUtil.delay(1);
		List<WebElement> domain = driver.findElements(BulkUpdatePageLocator.DOMAIN_SELECTOR);
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
		driver.findElement(BulkUpdatePageLocator.DOMAINDROPDOWN_ICON).click();
	}

	public static void goToBulkUpdateSelectionPage() {

		driver.findElement(BulkUpdatePageLocator.NEXT_BUTTON).click();
		BulkUpdateSelectionPage.setDriver(BulkUpdatePage.getDriver());
		LaunchBrowserUtil.delay(1);

	}

	public static void signOut() {
		driver.findElement(BulkUpdatePageLocator.HEADER_ICON_PROFILE).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(BulkUpdatePageLocator.HEADERLINK_SIGNOUT).click();

	}

	public static void clickNext() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/div[1]/div[3]/div/button/span"))
				.click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickRemoveAccess() {
		driver.findElement(By.id("remove")).click();
	}

	/**
	 * @param userid selects the provided userid if found
	 * @return true if the id provided is found, false otherwise
	 */
	public static boolean userFound(String userid) {
		boolean userFound = false;
		List<WebElement> allUsers = driver.findElements(By.className("bulk-update-user-row"));
		logger.info("The number of user found is --" + allUsers.size());
		for (int i = 0; i < allUsers.size(); i++) {
			String idtext = allUsers.get(i).getText();
			if (idtext.contains(userid)) {
				logger.info("User has been found*******************************the textid-is--- " + idtext);
				LaunchBrowserUtil.delay(4);
				/*
				 * WebElement element = allUsers.get(i).findElement(By.tagName("input"));
				 * Actions actions = new Actions(driver);
				 * actions.moveToElement(element).perform();
				 */
				WebElement element = allUsers.get(i).findElement(By.tagName("input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				LaunchBrowserUtil.delay(3);
				Actions actions = new Actions(driver);
				actions.moveToElement(allUsers.get(i).findElement(By.className("input-toggle"))).click().perform();
				// allUsers.get(i).findElement(By.className("input-toggle")).click();
				userFound = true;
				LaunchBrowserUtil.delay(3);
				break;
			}
		}
		return userFound;
	}

	public static void clickSelectAll() {
		driver.findElement(By.id("select-all")).click();
		LaunchBrowserUtil.delay(1);

	}

	public static void writeComment(String comment) {
		driver.findElement(By.id("comments")).sendKeys(comment);
		LaunchBrowserUtil.delay(1);

	}

	public static void clickDone() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/div[1]/div[3]/div/button/span"))
				.click();
		UserDirectoryPage.setDriver(driver);
		LaunchBrowserUtil.delay(2);

	}
}
