package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.NewSystemAccountPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * this class refers to all the pages during the creation of new system
 * account.*note that it is different from the System Account Directory Page.
 *
 */
public class NewSystemAccountPage {

	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(NewSystemAccountPage.class);

	private NewSystemAccountPage() {
	}

	public static WebDriver getDriver() {
		return NewSystemAccountPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		NewSystemAccountPage.driver = driver;
	}

	public static void enterSystemAccountName(String accountname) {
		driver.findElement(NewSystemAccountPageLocator.TEXTBOX_SYSTEM_ACCOUNT_NAME).sendKeys(accountname);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterInterfacingSystemName(String interfacingname) {
		driver.findElement(NewSystemAccountPageLocator.TEXTBOX_SYSTEM_INTERFACING_NAME).sendKeys(interfacingname);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterSystemDescription(String description) {
		driver.findElement(NewSystemAccountPageLocator.TEXTBOX_SYSTEM_DESCRIPTION).sendKeys(description);
		LaunchBrowserUtil.delay(2);
	}

	public static void clickNextToGoToOrgInfo() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		LaunchBrowserUtil.delay(3);
	}

	/**
	 * @param orgname select the org for this system account
	 * @return true if the org was found, false otherwise
	 */
	public static boolean selectOrgInOrgInfo(String orgname) {
		boolean orgFound = false;
		driver.findElement(NewSystemAccountPageLocator.AGENCYPICKER_ORG_INFO).sendKeys(orgname);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(NewSystemAccountPageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(0);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(orgname.toLowerCase())) {
			driver.findElement(By.xpath("//*[@id=\"sam-autocomplete-results-kv\"]/li[1]/span[1]")).click();
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			orgFound = true;
		}
		return orgFound;
	}

	/**
	 * @param managerid provide an existing id of the system manager
	 * @return true if the id was found, false otherwise
	 */
	public static boolean selectSystemManagerInOrgInfo(String managerid) {
		boolean idFound = false;
		driver.findElement(NewSystemAccountPageLocator.SYSTEM_MANAGER_PICKER).sendKeys(managerid);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.SYSTEMMANAGER_ID_SELECTOR);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(managerid.toLowerCase())) {
			idList.get(0).click();
			LaunchBrowserUtil.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			idFound = true;
		}
		return idFound;
	}

	/**
	 * @param systemadmin provide an existing id of the system admin
	 * @return true if the id was found, false otherwise
	 */
	public static boolean selectSystemAdminInOrgInfo(String systemadmin) {
		boolean idFound = false;
		driver.findElement(NewSystemAccountPageLocator.SYSTEM_ADMIN_PICKER).sendKeys(systemadmin);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.SYSTEMADMIN_ID_SELECTOR);
		LaunchBrowserUtil.delay(2);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(systemadmin.toLowerCase())) {
			idList.get(0).click();
			LaunchBrowserUtil.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			idFound = true;
		}
		return idFound;
	}

	public static void clickNextToGoToPermissions() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickPermission(By checkbox) {
		driver.findElement(checkbox).click();
		LaunchBrowserUtil.delay(1);
	}

	/**
	 * @param category provide FIPS category, eg Low, High etc
	 * @return true if category was found, false otherwise
	 */
	public static boolean selectFIPSCategorization(String category) {
		boolean categoryFound = false;
		// driver.findElement(NewSystemAccountPageLocator.FIPS_CATEGORY_TEXBOX).sendKeys(category);
		driver.findElement(NewSystemAccountPageLocator.FIPS_CATEGORY_TEXBOX).click();
		LaunchBrowserUtil.delay(1);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.FIPS_CATEGORY_LIST);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(category.toLowerCase())) {
			idList.get(0).click();
			LaunchBrowserUtil.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			categoryFound = true;
		}
		return categoryFound;
	}

	public static void clickNextToGoToSecurity() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void enterIPaddress(String ipaddress) {
		driver.findElement(NewSystemAccountPageLocator.IP_ADDRESS_TEXTBOX).sendKeys(ipaddress);
		LaunchBrowserUtil.delay(2);
		driver.findElement(NewSystemAccountPageLocator.IP_ADDRESS_TEXTBOX).sendKeys(Keys.TAB, Keys.ENTER);

		/*
		 * Actions action=new Actions(driver);
		 * action.moveToElement(driver.findElement(By.
		 * cssSelector("button[class='add-button usa-button-primary']"))).click().
		 * perform();
		 */
		LaunchBrowserUtil.delay(1);
	}

	/**
	 * @param connectiontype provide FIPS category, eg Low, High etc
	 * @return true if category was found, false otherwise
	 */
	public static boolean selectTypeConnection(String connectiontype) {
		boolean typeFound = false;
		driver.findElement(NewSystemAccountPageLocator.TEXTAREA_TYPE_OF_CONNECTION).sendKeys(connectiontype);
		List<WebElement> idList = driver.findElements(NewSystemAccountPageLocator.TYPE_OF_CONNECTION_LIST);
		logger.info(("The size of the list is......" + idList.size()));
		WebElement firstId = idList.get(0);
		logger.info("*****************the text from first id is*****" + firstId.getText());
		if (firstId.getText().toLowerCase().contains(connectiontype.toLowerCase())) {
			idList.get(0).click();
			LaunchBrowserUtil.delay(3);
			driver.findElement(NewSystemAccountPageLocator.CONTAINER).click();
			typeFound = true;
		}
		return typeFound;

	}

	public static void enterPhysicalLocation(String location) {
		driver.findElement(NewSystemAccountPageLocator.PHYSICAL_LOCATION).sendKeys(location);
		LaunchBrowserUtil.delay(1);

	}

	public static void enterSecurityOfficialName(String officialname) {
		driver.findElement(NewSystemAccountPageLocator.SECURITY_OFFICIAL_NAME).sendKeys(officialname);
		LaunchBrowserUtil.delay(1);
	}

	public static void enterSecurityOfficialEmail(String email) {
		driver.findElement(NewSystemAccountPageLocator.SECURITY_OFFICIAL_EMAIL).sendKeys(email);
		LaunchBrowserUtil.delay(1);
	}

	public static void clickNextToGoToAuthorization() {
		driver.findElement(NewSystemAccountPageLocator.NEXT_TO_DESCRIPTION_BUTTON).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void certifyCorrectInformation() {
		driver.findElement(NewSystemAccountPageLocator.CERTIFY_CORRECT_INFO).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void clickReviewButton() {
		driver.findElement(NewSystemAccountPageLocator.REVIEW_BUTTON).click();
		LaunchBrowserUtil.delay(3);
	}

	public static void clickSubmit() {
		driver.findElement(NewSystemAccountPageLocator.SUBMIT_BUTTON).click();
		LaunchBrowserUtil.delay(5);

	}

	public static void goToWorkspace() {
		driver.findElement(NewSystemAccountPageLocator.WORKSPACE_LINK).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void goToWorkspaceWithoutBreadcrumbs() {
		LaunchBrowserUtil.delay(7);
		driver.findElement(By.id("headerIconMenu")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("header-link-workspace")).click();
		LaunchBrowserUtil.delay(2);
		driver.navigate().refresh();
		LaunchBrowserUtil.delay(3);
	}

	public static void writeComment(String comments) {
		driver.findElement(NewSystemAccountPageLocator.COMMENT_TEXTBOX).sendKeys(comments);
		driver.findElement(NewSystemAccountPageLocator.COMMENT_TEXTBOX).sendKeys(Keys.ENTER);
		LaunchBrowserUtil.delay(3);
	}

	public static void clickReviewTab() {
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/div/a[2]"))
				.click();
		LaunchBrowserUtil.delay(2);
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
		LaunchBrowserUtil.delay(1);
	}

	public static String getAlertMessage() {
		return driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/form/sam-tabs/sam-tab[2]/div/review/div[1]/div[6]/sam-alert/div/div/p"))
				.getText();
	}

	public static void selectAllTermsOfUse() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term0")).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.id("term1")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term2")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term3")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term4")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term5")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term19")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term20")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term21")).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.cssSelector("sam-button[buttontext='Send Password']")).click();
	}

	public static void clickContinueOnTermsOfUse() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.cssSelector("sam-button[buttontext='Continue']")).click();
	}

	public static void enterOtpOnTermsOfUser(String otp) {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("api-pin")).sendKeys(otp);
		LaunchBrowserUtil.delay(1);
	}

	public static void clickSubmitOnTermsOfUser() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.cssSelector("sam-button[class='usa-modal-content-submit-btn']")).click();
		LaunchBrowserUtil.delay(7);

	}

	public static void clickCheckAvailabilityButton() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("system-account-name-button")).click();
		LaunchBrowserUtil.delay(1);
		
	}

	public static void selectAllTermsOfUseSensitivePermission() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term0")).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.id("term1")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term2")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term3")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term4")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term5")).click();
		LaunchBrowserUtil.delay(1);
		
		driver.findElement(By.id("term6")).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.id("term7")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term8")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term9")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term10")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term11")).click();
		LaunchBrowserUtil.delay(1);
		
		driver.findElement(By.id("term12")).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.id("term13")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term14")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term15")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term16")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term17")).click();
		LaunchBrowserUtil.delay(1);
		
		driver.findElement(By.id("term18")).click();
		LaunchBrowserUtil.delay(1);
		
		driver.findElement(By.id("term19")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term20")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("term21")).click();
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.cssSelector("sam-button[buttontext='Send Password']")).click();
		
	}
}