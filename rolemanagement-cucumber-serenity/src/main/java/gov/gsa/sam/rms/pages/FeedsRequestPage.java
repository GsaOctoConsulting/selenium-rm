package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.FeedsRequestPageLocator;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;
import gov.gsa.sam.rms.utilities.Constants;

/**
 * this page is where all the requests visible to the user is listed
 */
public class FeedsRequestPage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FeedsRequestPage.class);

	private FeedsRequestPage() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		FeedsRequestPage.driver = driver;
	}

	public static List<WebElement> getFeedsList() {
		List<WebElement> feedslist = driver.findElements(FeedsRequestPageLocator.FEED_ITEM);
		logger.info("The size fo the user list is--" + feedslist.size());
		return feedslist;

	}

	public static void clickRoleRequestFilter() {
		driver.findElement(FeedsRequestPageLocator.ROLEREQUEST_FILTER).click();
		LaunchBrowserUtil.delay(4);

	}

	public static int getPendingRequestCount() {
		int pendingRequestCount = Integer
				.parseInt(driver.findElement(FeedsRequestPageLocator.TOPLEFT_REQUEST_COUNT).getText());
		return pendingRequestCount;
	}

	public static void clickApprovedFilter() {
		driver.findElement(FeedsRequestPageLocator.APPROVED_FILTER).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickRejectedFilter() {
		driver.findElement(FeedsRequestPageLocator.REJECTED_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickCanceledFilter() {
		driver.findElement(FeedsRequestPageLocator.CANCELED_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickPendingFilter() {
		driver.findElement(FeedsRequestPageLocator.PENDING_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickSubmitALFilter() {
		driver.findElement(FeedsRequestPageLocator.SUBMIT_AL_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickClearFilters() {
		driver.findElement(FeedsRequestPageLocator.CLEARFILTER_LINK).click();
	}

	public static void clickCompletedFilter() {
		driver.findElement(FeedsRequestPageLocator.COMPLETED_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickSentOnSideBar() {
		driver.findElement(FeedsRequestPageLocator.SENT_ONSIDENAV).click();
		LaunchBrowserUtil.delay(5);
	}

	public static void clickReceivedOnSideNav() {
		driver.findElement(FeedsRequestPageLocator.RECEIVED_ONSIDENAV).click();
		LaunchBrowserUtil.delay(4);
	}

	public static void goToWorkspacePage() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(FeedsRequestPageLocator.WORKSPACEPAGE_BREADCRUMB_LINK).click();
		LaunchBrowserUtil.delay(1);

	}

	/**
	 * this methods confirms whether a request is present in the request list when
	 * all the parameters are provided
	 * 
	 * @param fullName  the name of the user (requester)
	 * @param org       the org name e.g General Services Administration
	 * @param role      the role name, e.g Assistance User
	 * @param timestamp the timestamp of the request (important as this helps to
	 *                  make the search parameter unique)
	 * @param status    status of the request
	 * @param action    the action to be taken on the request, eg REJECTROLE,
	 *                  NOACTION, see implementation
	 * @return true if the request is found, false otherwise
	 */
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
				LaunchBrowserUtil.delay(2);

				feedList.get(i).click();
				LaunchBrowserUtil.delay(2);
				driver.findElement(By.id("Additional Information")).sendKeys("Request is rejected");
				LaunchBrowserUtil.delay(1);
				FeedsRequestPage.clickRejectButton();
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

	public static void clickRejectButton() {
		logger.info(driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div[2]/div/sam-button[1]/button"))
				.getText());
		driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div[2]/div/sam-button[1]/button"))
				.click();
		LaunchBrowserUtil.delay(3);
	}

	/**
	 * this method returns the timestamp of the topmost request on the feeds page
	 * 
	 * @return the timestamp as a string
	 */
	public static String getLastRequestRequestTimestamp() {
		List<WebElement> feedList = getFeedsList();
		String timestamp = feedList.get(0).findElement(By.className("msg-feed-date")).getText();
		logger.info("The timestamp is--" + timestamp);
		return timestamp;
	}

	public static int getTotalNoOfPages() {
		String resultMessage = driver.findElement(FeedsRequestPageLocator.TOTAL_NO_OFRECORDS).getText();
		logger.info("Total number of records found is - "+resultMessage);
		String[] bits = resultMessage.trim().split(" ");
		int recordNo = Integer.parseInt(bits[bits.length - 3]);
		logger.info("The number of records found are - " + recordNo);
		int totalNoPages = noOfPageExpected(recordNo);
		logger.info("The number of pages found are - " + totalNoPages);
		return totalNoPages;
	}

	private static int noOfPageExpected(int totalNoOfRecords) {
		if (totalNoOfRecords <= 10) {
			return 0;
		} else if (totalNoOfRecords % 10 == 0) {
			return ((totalNoOfRecords / 10));
		} else {
			return ((totalNoOfRecords / 10) + 1);
		}
	}

	public static boolean isStringOnlyAlphabetAndSpace(String requestername) {
		return ((requestername != null) && (!requestername.equals(""))
				&& (requestername.matches("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$")));
	}
	public static List<WebElement> getPagination() {
		List<WebElement> pagination = driver.findElements(FeedsRequestPageLocator.PAGINATION);
		logger.info("The size of the pagination is -" + pagination.size());
		return pagination;
	}
	public static void clickPageNo(int pageno, int pageLimit) {
		List<WebElement> pagelist = getPagination();

		for (int i = 0; i < pageLimit && pagelist.size() > 1; i++) {
			if (Integer.parseInt(pagelist.get(i).getText()) == pageno) {
				logger.info("Text from the pagebutton - " + pagelist.get(i).getText());
				pagelist.get(i).click();
				LaunchBrowserUtil.delay(3);
				break;
			}
		}
	}

}
