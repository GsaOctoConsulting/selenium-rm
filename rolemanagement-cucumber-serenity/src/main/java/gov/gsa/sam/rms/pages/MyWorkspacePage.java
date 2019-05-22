package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.MyWorkspacePageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;

public class MyWorkspacePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(MyWorkspacePage.class);

	private MyWorkspacePage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static void searchUsers(String searchtext) {
		driver.findElement(MyWorkspacePageLocator.USER_SEARCH_BAR).sendKeys(searchtext);
		CommonMethods.delay(1);
	}

	public static void goToAccountDetailsPage() {
		CommonMethods.delay(2);
		driver.findElement(MyWorkspacePageLocator.GO_TO_MYPROFILE_LINK).click();
		AccountDetailsPage.setDriver(MyWorkspacePage.getDriver());
		CommonMethods.delay(4);

	}

	public static void clickSignInButton() {
		driver.findElement(MyWorkspacePageLocator.SIGNIN_TAB).click();
		CommonMethods.delay(2);

	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return MyWorkspacePage.driver;
	}

	public static void setDriver(WebDriver driver) {
		MyWorkspacePage.driver = driver;
	}

	public static void goToFeedsPage() {
		Actions actions = new Actions(driver);
		WebElement feed = driver.findElement(MyWorkspacePageLocator.NOTIFICATIONS);
		actions.moveToElement(feed).click().perform();
		logger.info("" + driver.findElement(MyWorkspacePageLocator.SHOWMORE_LINK).getText());
		// option 4
		WebDriverWait wait = new WebDriverWait(driver, 10);

		WebElement thelink = wait.until(ExpectedConditions.elementToBeClickable(MyWorkspacePageLocator.SHOWMORE_LINK));
		thelink.click();
		FeedsRequestPage.setDriver(MyWorkspacePage.getDriver());
	}

	public static void goToSystemAccountDirectoryPage() {
		driver.findElement(MyWorkspacePageLocator.GO_TO_SYSTEM_ACCOUNT).click();
		SystemAccountDirectoryPage.setDriver(driver);
		CommonMethods.delay(9);

	}

	public static void clickAcceptOnBanner() {
		driver.findElement(By.id("login-accept")).click();
		CommonMethods.delay(2);

	}

	public static void signOut() {
		CommonMethods.delay(1);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("headerIconProfile")));
		actions.click();
		actions.build().perform();
		// driver.findElement(By.id("headerIconProfile")).click();
		CommonMethods.delay(1);
		driver.findElement(By.id("header-link-signout")).click();
		CommonMethods.delay(2);
		// driver.findElement(By.id("login-accept")).click();

	}

	public static void goToRoleMigrationPage() {
		CommonMethods.delay(1);
		driver.findElement(By.xpath(
				"//*[@id=\"workspace-widgets\"]/div[1]/workspace-widget/div/profile-widget/div/div[1]/div/a[2]"))
				.click();
		CommonMethods.delay(1);
		AccountDetailsPage.setDriver(driver);
		RolesDirectoryViewAccessPage.setDriver(driver);
		RoleMigrationPage.setDriver(driver);
	}

}
