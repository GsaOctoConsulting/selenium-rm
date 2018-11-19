package gov.gsa.sam.rms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	driver.findElement(MyWorkspacePageLocator.GO_TO_MYPROFILE_LINK).click();
	AccountDetailsPage.setDriver(MyWorkspacePage.getDriver());
	CommonMethods.delay(3);
		
		
	}
	public static void clickSignInButton() {
		driver.findElement(MyWorkspacePageLocator.SIGNIN_TAB).click();
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
			driver.findElement(MyWorkspacePageLocator.NOTIFICATIONS).click();
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
			CommonMethods.delay(3);
			
		}

		
	}

