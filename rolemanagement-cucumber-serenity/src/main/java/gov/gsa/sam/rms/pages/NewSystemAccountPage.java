package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class NewSystemAccountPage {

	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(NewSystemAccountPage.class);

	private NewSystemAccountPage() {
	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return NewSystemAccountPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		NewSystemAccountPage.driver = driver;
	}

	public static void enterSystemAccountName(String accountname) {
		driver.findElement(NewSystemAccountPageLocator.TEXTBOX_SYSTEM_ACCOUNT_NAME).sendKeys(accountname);
		CommonMethods.delay(1);
	}

	public static void enterInterfacingSystemName(String interfacingname) {
		driver.findElement(NewSystemAccountPageLocator.TEXTBOX_SYSTEM_INTERFACING_NAME).sendKeys(interfacingname);
		CommonMethods.delay(1);
	}

	public static void enterSystemDescription(String description) {
		driver.findElement(NewSystemAccountPageLocator.TEXTBOX_SYSTEM_DESCRIPTION).sendKeys(description);
		CommonMethods.delay(2);
	}

	public static void clickNextToGoToOrgInfo() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		CommonMethods.delay(3);
	}

	public static boolean selectOrgInOrgInfo(String orgname) {
		boolean orgFound = false;
		driver.findElement(NewSystemAccountPageLocator.AGENCYPICKER_ORG_INFO).sendKeys(orgname);
		List<WebElement> orgList = driver.findElements(NewSystemAccountPageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(0);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgname.toLowerCase())) {
			orgList.get(0).click();
			CommonMethods.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			orgFound = true;
		}
		return orgFound;
	}

	public static boolean selectSystemManagerInOrgInfo(String managerid) {
		boolean idFound = false;
		driver.findElement(NewSystemAccountPageLocator.SYSTEM_MANAGER_PICKER).sendKeys(managerid);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.SYSTEMMANAGER_ID_SELECTOR);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(managerid.toLowerCase())) {
			idList.get(0).click();
			CommonMethods.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			idFound = true;
		}
		return idFound;
	}

	public static boolean selectSystemAdminInOrgInfo(String systemadmin) {
		boolean idFound = false;
		driver.findElement(NewSystemAccountPageLocator.SYSTEM_ADMIN_PICKER).sendKeys(systemadmin);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.SYSTEMADMIN_ID_SELECTOR);
		CommonMethods.delay(2);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(systemadmin.toLowerCase())) {
			idList.get(0).click();
			CommonMethods.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			idFound = true;
		}
		return idFound;
	}

	public static void clickNextToGoToPermissions() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		CommonMethods.delay(2);
	}

	public static void clickPermission(By checkbox) {
		driver.findElement(checkbox).click();
		CommonMethods.delay(1);
	}

	public static boolean selectFIPSCategorization(String category) {
		boolean categoryFound = false;
		// driver.findElement(NewSystemAccountPageLocator.FIPS_CATEGORY_TEXBOX).sendKeys(category);
		driver.findElement(NewSystemAccountPageLocator.FIPS_CATEGORY_TEXBOX).click();
		CommonMethods.delay(1);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.FIPS_CATEGORY_LIST);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(category.toLowerCase())) {
			idList.get(0).click();
			CommonMethods.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			categoryFound = true;
		}
		return categoryFound;
	}

	public static void clickNextToGoToSecurity() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		CommonMethods.delay(1);
	}

	public static void enterIPaddress(String ipaddress) {
		driver.findElement(NewSystemAccountPageLocator.IP_ADDRESS_TEXTBOX).sendKeys(ipaddress);
		CommonMethods.delay(1);
	}

	public static boolean selectTypeConnection(String connectiontype) {
		boolean typeFound = false;
		driver.findElement(NewSystemAccountPageLocator.TEXTAREA_TYPE_OF_CONNECTION).sendKeys(connectiontype);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.TYPE_OF_CONNECTION_LIST);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(connectiontype.toLowerCase())) {
			idList.get(0).click();
			CommonMethods.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			typeFound = true;
		}
		return typeFound;

	}

	public static void enterPhysicalLocation(String location) {
		driver.findElement(NewSystemAccountPageLocator.PHYSICAL_LOCATION).sendKeys(location);
		CommonMethods.delay(1);

	}

	public static void enterSecurityOfficialName(String officialname) {
		driver.findElement(NewSystemAccountPageLocator.SECURITY_OFFICIAL_NAME).sendKeys(officialname);
		CommonMethods.delay(1);
	}

	public static void enterSecurityOfficialEmail(String email) {
		driver.findElement(NewSystemAccountPageLocator.SECURITY_OFFICIAL_EMAIL).sendKeys(email);
		CommonMethods.delay(1);

	}

	public static void clickNextToGoToAuthorization() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		CommonMethods.delay(1);
	}

	public static void certifyCorrectInformation() {
		driver.findElement(NewSystemAccountPageLocator.CERTIFY_CORRECT_INFO).click();
		CommonMethods.delay(1);
	}

	public static void clickReviewButton() {
		driver.findElement(NewSystemAccountPageLocator.REVIEW_BUTTON).click();
		CommonMethods.delay(3);
	}

	public static void clickSubmit() {
		driver.findElement(NewSystemAccountPageLocator.SUBMIT_BUTTON).click();
		CommonMethods.delay(3);

	}

	public static void goToWorkspace() {
		driver.findElement(NewSystemAccountPageLocator.WORKSPACE_LINK).click();

		/*
		 * driver.findElement(By.xpath("headerIconMenu")).click();
		 * CommonMethods.delay(1);
		 * driver.findElement(By.xpath("header-link-workspace")).click();
		 */
		CommonMethods.delay(1);

	}

	public static void goToWorkspaceWithoutBreadcrumbs() {
		CommonMethods.delay(1);
		driver.findElement(By.id("headerIconMenu")).click();
		CommonMethods.delay(1);
		driver.findElement(By.id("header-link-workspace")).click();

	}

	public static void writeComment(String comments) {
		driver.findElement(NewSystemAccountPageLocator.COMMENT_TEXTBOX).sendKeys(comments);
		driver.findElement(NewSystemAccountPageLocator.COMMENT_TEXTBOX).sendKeys(Keys.ENTER);
		CommonMethods.delay(3);
	}

	public static void clickReviewTab() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/div/a[2]"))
				.click();
		CommonMethods.delay(2);
	}

	public static boolean elementFound(org.openqa.selenium.By newButton) {
		try {
			driver.findElement(newButton);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void clickEditTab() {
		driver.findElement(NewSystemAccountPageLocator.EDIT_TAB).click();
		CommonMethods.delay(1);

	}

	public static String getAlertMessage() {
		return driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div[1]/div[6]/sam-alert/div/div/p"))
				.getText();

	}
}