package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

/**
 * This class corresponds to the AccountDetails Page where user's signup and org
 * info are found
 */
public class AccountDetailsPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(AccountDetailsPage.class);

	private AccountDetailsPage() {
	}

	public static WebDriver getDriver() {
		return AccountDetailsPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		AccountDetailsPage.driver = driver;
	}

	public static void goToMyWorkspacePage() {
		clickMenuDropdown();
		driver.findElement(AccountDetailsPageLocator.WORKSPACE).click();
		T1WorkspacePage.setDriver(AccountDetailsPage.getDriver());
		LaunchBrowserUtil.delay(2);
	}

	public static void clickMenuDropdown() {
		driver.findElement(AccountDetailsPageLocator.MENU_DROPDOWN).click();
		LaunchBrowserUtil.delay(2);
	}

	/**
	 * @param sidenavoption eg. Roles, RoleMigration, etc
	 */
	public static void goToPageOnSideNav(String sidenavoption) {
		LaunchBrowserUtil.clickSideNavToGoToPage(sidenavoption, driver);
		MyRolesPage.setDriver(driver);
		LaunchBrowserUtil.delay(3);
	}

	public static void clickDeactivateAccount() {
		driver.findElement(AccountDetailsPageLocator.BUTTON_DEACTIVATE).click();
		LaunchBrowserUtil.delay(2);
		driver.findElement(AccountDetailsPageLocator.POPUP_YES_DEACTIVATE).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(AccountDetailsPageLocator.POPUP_YES_DEACTIVATE2).click();
	}

	/**
	 * this methods tests whether account info key is found and if found returns the
	 * value eg Department:
	 * 
	 * @param key
	 * @return the value of the key if found, empty otherwise
	 */
	public static String accountInfo(String key) {
		List<WebElement> allKey = driver.findElements(AccountDetailsPageLocator.DEPARTMENT);
		if (key.equalsIgnoreCase("Department:")) {
			for (int i = 0; i < allKey.size(); i++) {
				if (key.equalsIgnoreCase(allKey.get(i).findElement(By.tagName("label")).getText()))// key
																									// found
					return allKey.get(i).findElement(By.className("usa-form-control")).getText();// return
																									// value
			}
		}
		return "Key Not Found";
	}

	public static void clickEyeIconToGenerateAPIKey() {
		driver.findElement(By.className("fa-eye")).click();
		LaunchBrowserUtil.delay(1);

	}

	public static void enterOtp(String otp) {
		driver.findElement(By.id("password-input")).clear();
		driver.findElement(By.id("password-input")).sendKeys(otp);
		LaunchBrowserUtil.delay(1);

	}

	public static void clickSubmitButton() {
		LaunchBrowserUtil.delay(3);
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/section/ng-component/sam-modal-basic/div/div/div[2]/div/button[2]"))
				.click();

	}

	public static void clickSendNewCodeLink() {
		driver.findElement(By.linkText("Send new code")).click();
		LaunchBrowserUtil.delay(1);
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static String getApiKeyText() {
		return driver.findElement(AccountDetailsPageLocator.API_KEY_TEXT).getText();

	}

	public static void clickRequestApiKeyButton() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(By.xpath("//*[@id=\"api-key-grid\"]/div/sam-button/button")).click();
		LaunchBrowserUtil.delay(5);

	}

	public static void clickContinueOnSessionExtension() {
		driver.findElement(By.xpath("/html/body/app/sam-modal/div[2]/div/div[2]/div/sam-button[2]/button")).click();
	}

	public static String getWrongApiErrorMessage() {
		return driver.findElement(AccountDetailsPageLocator.API_KEY_ERROR_MESSAGE).getText();

	}

	public static void closeAPIKeyModal() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("password-modalClose")).click();
		LaunchBrowserUtil.delay(1);
	}

}