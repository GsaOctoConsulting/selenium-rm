package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.MyWorkspacePageLocator;
import gov.gsa.sam.rms.locators.T1WorkspacePageLocator;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.pages.T1WorkspacePage;

/**
 * this class refers to the workspace page which is 
 * the landing page after signin for every user and 
 * so is the starting point of automation
 * 
 */
public class T1WorkspacePage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(T1WorkspacePage.class);

	private T1WorkspacePage() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		T1WorkspacePage.driver = driver;
	}

	public static void searchUsers(String searchtext) {
		driver.findElement(T1WorkspacePageLocator.USER_SEARCH_BAR).sendKeys(searchtext);
		LaunchBrowserUtil.delay(1);
	}

	public static void goToAccountDetailsPage() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(T1WorkspacePageLocator.GO_TO_MYPROFILE_LINK).click();
		AccountDetailsPage.setDriver(T1WorkspacePage.getDriver());
		UserDirectoryViewAccessPage.setDriver(driver);
		LaunchBrowserUtil.delay(4);
	}

	public static void clickSignInButton() {
		driver.findElement(T1WorkspacePageLocator.SIGNIN_TAB).click();
		LaunchBrowserUtil.delay(2);
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void goToFeedsPage() {
		/*Actions actions = new Actions(driver);
		WebElement feed = driver.findElement(MyWorkspacePageLocator.NOTIFICATIONS);
		actions.moveToElement(feed).click().perform();
		logger.info("" + driver.findElement(MyWorkspacePageLocator.SHOWMORE_LINK).getText());
		// option 4
		LaunchBrowserUtil.delay(2);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement thelink = wait.until(ExpectedConditions.elementToBeClickable(MyWorkspacePageLocator.SHOWMORE_LINK));
		thelink.click();
		FeedsRequestPage.setDriver(WorkspacePage.getDriver());*/
		String feedurl = Constants.LOGINGOV_HOME_PAGE+"/workspace/myfeed/requests";
		driver.navigate().to(feedurl);
		FeedsRequestPage.setDriver(driver);
		LaunchBrowserUtil.delay(4);
	}

	public static void goToSystemAccountDirectoryPage() {
		driver.findElement(T1WorkspacePageLocator.GO_TO_SYSTEM_ACCOUNT).click();
		SystemAccountDirectoryPage.setDriver(driver);
		LaunchBrowserUtil.delay(8);
		driver.navigate().refresh();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickAcceptOnBanner() {
		driver.findElement(By.id("login-accept")).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void signOut() {
		LaunchBrowserUtil.delay(1);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("headerIconProfile")));
		actions.click();
		actions.build().perform();
		// driver.findElement(By.id("headerIconProfile")).click();
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("header-link-signout")).click();
		LaunchBrowserUtil.delay(2);
		// driver.findElement(By.id("login-accept")).click();

	}
	public static void goToRoleMigrationPage() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(T1WorkspacePageLocator.GO_TO_MYPROFILE_LINK).click();
		AccountDetailsPage.setDriver(T1WorkspacePage.getDriver());
		UserDirectoryViewAccessPage.setDriver(driver);
		LaunchBrowserUtil.delay(2);
		
		AccountDetailsPage.setDriver(driver);
		UserDirectoryViewAccessPage.setDriver(driver);
		AccountDetailsPage.goToPageOnSideNav("Role Migrations");
		RoleMigrationPage.setDriver(driver);
		LaunchBrowserUtil.delay(4);
	}
	public static void clickRoleDefinitionLink() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(By.id("role-definitions-button")).click();
		LaunchBrowserUtil.delay(1);
	}

	public static boolean profileLinksFound(String linkName) {
		List<WebElement> list = driver.findElements(By.className("relaxed"));
		logger.info("The size of the list is-- " + list.size());
		boolean linkFound = false;
		for (int i = 0; i < list.size(); i++) {
			String linkText = list.get(i).getText();
			logger.info("The link test found is ---- " + linkText);
			if (linkText.contains(linkName)) {
				linkFound = true;
			}
		}
		return linkFound;
	}

	public static void clickUserDirectoryLink() {
		driver.findElement(By.id("user-directory-link")).click();
		UserDirectoryPage.setDriver(driver);
		LaunchBrowserUtil.delay(4);
	}

	public static boolean selectUserTypeIfFound(String usertype) {
		boolean usertypeFound = false;
		Select role = new Select(driver.findElement(T1WorkspacePageLocator.FSD_FILTER));

		try {
			role.selectByVisibleText(usertype);
			usertypeFound = true;
		} catch (NoSuchElementException e) {

			return usertypeFound;
		}
		LaunchBrowserUtil.delay(1);
		return usertypeFound;
	}

	public static boolean selectRoleForRoleRequest(String rolename) {
		boolean roleFound = false;
		Select role = new Select(driver.findElement(T1WorkspacePageLocator.ROLE_SELECTOR));

		try {
			role.selectByVisibleText(rolename);
			roleFound = true;
		} catch (NoSuchElementException e) {

			return roleFound;
		}
		return roleFound;
	}

	public static String getRoleDescriptionForSelectedRole() {
		WebElement element = driver.findElement(By.tagName("form"));
		String description = element.findElement(By.tagName("p")).getText();
		logger.info("The role description is ---  "+ description);
		return description;
	}

	/**
	 * This method returns text for the given element
	 * @param element the element locator for which the text content is needed
	 * @return the text content of the element 
	 */
	public static String getElementsText(By element) {
		LaunchBrowserUtil.delay(2);
		String elementtext = driver.findElement(element).getText();
		logger.info("The element text is ---  "+ elementtext);
		return elementtext;
	}

	public static void clickPendingBubble() {
		LaunchBrowserUtil.delay(1);
		driver.findElement(T1WorkspacePageLocator.PENDING).click();
		LaunchBrowserUtil.delay(4);
		SystemAccountDirectoryPage.setDriver(driver);
	}

}
