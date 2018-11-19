package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class FeedsRequestPageLocator {

	public static final By FEED_ITEM = By.className("msg-feed-item");// contains
																		// locators
																		// for
																		// FHLandingPageOnly
	public static final By ROLEREQUEST_FILTER = By.id("Role Requests");
	public static final By TOPLEFT_REQUEST_COUNT = By.className("request-total-count");
	public static final By APPROVED_FILTER = By.id("Approved");
	public static final By REJECTED_FILTER = By.id("Rejected");
	public static final By CANCELED_FILTER = By.id("Canceled");
	public static final By PENDING_FILTER = By.id("Pending");
	public static final By COMPLETED_FILTER = By.id("Complete");
	public static final By CLEARFILTER_LINK = By.linkText("Clear Filters");
	public static final By SEARCH_REQUESTOR_AREA = By.id("search-requester-ac-textarea");
	public static final By FIRST_REQUESTOR_OR_APPROVER = By.className("subhead");
	public static final By SEARCH_APPROVER_AREA = By.id("search-approver-ac-textarea");
	public static final By SEARCH_REQUEST_BAR = By.id("search-keyword");
	public static final By SUBMIT_AL_FILTER = By.id("Submit AL");
	public static final By SENT_ONSIDENAV = By.linkText("Sent");
	public static final By RECEIVED_ONSIDENAV = By.linkText("Received");
	public static final By SORT_SELECT = By.id("sort-select");
	public static final By WORKSPACEPAGE_BREADCRUMB_LINK = By.linkText("Workspace");
	
}