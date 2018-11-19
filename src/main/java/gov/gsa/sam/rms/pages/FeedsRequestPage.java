package gov.gsa.sam.rms.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.FeedsRequestPageLocator;
import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.Constants;



public class FeedsRequestPage {
	private static WebDriver driver;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FeedsRequestPage.class);

	private FeedsRequestPage() {
	}

	// *****************************************************************************
	// the following methods describe actions that can be taken immediately on
	// loading of this Page
	// *****************************************************************************
	public static List<WebElement> getFeedsList() {
		return driver.findElements(FeedsRequestPageLocator.FEED_ITEM);

	}

	public static void clickRoleRequestFilter() {
		driver.findElement(FeedsRequestPageLocator.ROLEREQUEST_FILTER).click();
		CommonMethods.delay(4);

	}

	public static int getPendingRequestCount() {
		int pendingRequestCount = Integer
				.parseInt(driver.findElement(FeedsRequestPageLocator.TOPLEFT_REQUEST_COUNT).getText());
		return pendingRequestCount;
	}

	public static void clickApprovedFilter() {
		driver.findElement(FeedsRequestPageLocator.APPROVED_FILTER).click();
		CommonMethods.delay(2);

	}

	public static void clickRejectedFilter() {
		driver.findElement(FeedsRequestPageLocator.REJECTED_FILTER).click();
		CommonMethods.delay(2);
	}

	public static void clickCanceledFilter() {
		driver.findElement(FeedsRequestPageLocator.CANCELED_FILTER).click();
		CommonMethods.delay(2);
	}

	public static void clickPendingFilter() {
		driver.findElement(FeedsRequestPageLocator.PENDING_FILTER).click();
		CommonMethods.delay(2);
	}

	public static void clickSubmitALFilter() {
		driver.findElement(FeedsRequestPageLocator.SUBMIT_AL_FILTER).click();
		CommonMethods.delay(2);
	}

	public static void clickClearFilters() {
		driver.findElement(FeedsRequestPageLocator.CLEARFILTER_LINK).click();
	}
	public static void clickCompletedFilter() {
		driver.findElement(FeedsRequestPageLocator.COMPLETED_FILTER).click();
		CommonMethods.delay(2);
	}
	public static void clickSentOnSideBar() {
		driver.findElement(FeedsRequestPageLocator.SENT_ONSIDENAV).click();
		CommonMethods.delay(4);
	}
	public static void clickReceivedOnSideNav() {
		driver.findElement(FeedsRequestPageLocator.RECEIVED_ONSIDENAV).click();
		CommonMethods.delay(4);		
	}
	
	public static void clickToSortBy(String visibleTextOption) {
		WebElement sortSelectOptions = driver.findElement(FeedsRequestPageLocator.SORT_SELECT);
		Select sortOptions = new Select(sortSelectOptions);
		sortOptions.selectByVisibleText(visibleTextOption);
		CommonMethods.delay(2);
	}
	public static void searchRequest(String searchterm) {
		WebElement searchBar = driver.findElement(FeedsRequestPageLocator.SEARCH_REQUEST_BAR);
		searchBar.sendKeys(searchterm);
		searchBar.sendKeys(Keys.ENTER);
		CommonMethods.delay(1);

	}

	// *****************************************************************************
	// the following methods describe actions that are only available after some
	// previous actions were taken on this SAME page.
	// *****************************************************************************
	public static void searchRequestor(String requestoremail) {
		driver.findElement(FeedsRequestPageLocator.SEARCH_REQUESTOR_AREA).sendKeys(requestoremail);
		CommonMethods.delay(2);
		driver.findElement(FeedsRequestPageLocator.FIRST_REQUESTOR_OR_APPROVER).click();
		CommonMethods.delay(1);
	}

	public static void searchApprover(String approveremail) {
		driver.findElement(FeedsRequestPageLocator.SEARCH_APPROVER_AREA).sendKeys(approveremail);
		CommonMethods.delay(2);
		driver.findElement(FeedsRequestPageLocator.FIRST_REQUESTOR_OR_APPROVER).click();
		CommonMethods.delay(1);

	}
	public static void goToWorkspacePage() {
		driver.findElement(FeedsRequestPageLocator.WORKSPACEPAGE_BREADCRUMB_LINK).click();
		CommonMethods.delay(1);
		
	}

	// *****************************************************************************
	// driver getter and setter & private methods
	// *****************************************************************************
	public static boolean requestFound(String fullName, String org, String role, String timestamp, String status,
			String action) {
		boolean requestFound = false;
		List<WebElement> feedList = getFeedsList();

		for (int i = 0; i < feedList.size(); i++) {
			String eachFeedDetails = feedList.get(i).getText().toLowerCase();
			if (eachFeedDetails.contains(fullName.toLowerCase()) && eachFeedDetails.contains(org.toLowerCase())
					&& eachFeedDetails.contains(role.toLowerCase()) && eachFeedDetails.contains(timestamp.toLowerCase())
					&& eachFeedDetails.contains(status.toLowerCase())
					&& action.equalsIgnoreCase(Constants.REJECTROLE)) {
				logger.info(eachFeedDetails);
				logger.info("This role will be rejected");
				requestFound = true;
				CommonMethods.delay(2);
				feedList.get(i).click();
				RoleRequestApprovalPage.setDriver(FeedsRequestPage.getDriver());
				CommonMethods.delay(2);
				RoleRequestApprovalPage.clickRejectButton();
				break;

			} else if (eachFeedDetails.contains(fullName.toLowerCase()) && eachFeedDetails.contains(org.toLowerCase())
					&& eachFeedDetails.contains(role.toLowerCase()) && eachFeedDetails.contains(timestamp.toLowerCase())
					&& eachFeedDetails.contains(status.toLowerCase()) && action.equalsIgnoreCase(Constants.NOACTION)) {
				logger.info(eachFeedDetails);
				logger.info("No action will be taken");
				requestFound = true;
			}
		}
		return requestFound;
	}

	public static String getLastRequestRequestTimestamp() {
		List<WebElement> feedList = getFeedsList();
		String timestamp = feedList.get(0).findElement(By.className("msg-feed-date")).getText();
		logger.info("The timestamp is--" + timestamp);
		return timestamp;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		FeedsRequestPage.driver = driver;
	}

	

	
	

	

	

}
