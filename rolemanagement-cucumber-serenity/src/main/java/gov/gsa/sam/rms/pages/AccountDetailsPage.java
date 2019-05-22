package gov.gsa.sam.rms.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;


public class AccountDetailsPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(AccountDetailsPage.class);

	private AccountDetailsPage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void goToMyWorkspacePage() {
		clickMenuDropdown();
		driver.findElement(AccountDetailsPageLocator.WORKSPACE).click();
		MyWorkspacePage.setDriver(AccountDetailsPage.getDriver());
		CommonMethods.delay(2);
	}

	public static void clickMenuDropdown() {
		driver.findElement(AccountDetailsPageLocator.MENU_DROPDOWN).click();
		CommonMethods.delay(2);
	}

	public static void goToPageOnSideNav(String pageName) {
		CommonMethods.clickSideNavToGoToPage(pageName, driver);
		CommonMethods.delay(3);
	}

	public static void clickDeactivateAccount() {
		driver.findElement(AccountDetailsPageLocator.BUTTON_DEACTIVATE).click();
		CommonMethods.delay(2);
		driver.findElement(AccountDetailsPageLocator.POPUP_YES_DEACTIVATE).click();
		CommonMethods.delay(1);
		driver.findElement(AccountDetailsPageLocator.POPUP_YES_DEACTIVATE2).click();
	}
	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page.
	// *****************************************************************************

	// *****************************************************************************
	// driver getter and setter & private methods
	// *****************************************************************************
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

	public static WebDriver getDriver() {
		return AccountDetailsPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		AccountDetailsPage.driver = driver;
	}

	public static void clickShowAPIKeyCheckbox() {
		driver.findElement(By.id("usa-api-toggle")).click();

	}

	public static void enterOtp(String otp) {
		driver.findElement(By.id("api-pin")).clear();
		driver.findElement(By.id("api-pin")).sendKeys(otp);
		CommonMethods.delay(1);

	}

	public static void clickContinueButton() {
		driver.findElement(By.xpath("//*[@id=\"api-key-grid\"]/div/div[2]/sam-button/button")).click();

	}

	public static void clickSendNewCodeLink() {
		driver.findElement(By.linkText("Send new code")).click();
		CommonMethods.delay(1);
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
		CommonMethods.delay(2);
		driver.findElement(By.xpath("//*[@id=\"api-key-grid\"]/div/sam-button/button")).click();
		CommonMethods.delay(5);

	}

	public static void clickContinueOnSessionExtension() {
		driver.findElement(By.xpath("/html/body/app/sam-modal/div[2]/div/div[2]/div/sam-button[2]/button")).click();	
	}

}