package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class SystemAccountDirectoryPageLocator {

	public static final By NEW_BUTTON = By.tagName("sam-button");	
			/* By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/ng-component/page/div/div/div[3]/div[2]/div[2]/results/div[2]/div[2]/span/sam-button/button");
*/
	public static final By PASSWORD_SUCCESS_BANNER = By.className("usa-alert-success");
	public static final By PENDING_REVIEW_FILTER = By.id("filter-pending-review");
	public static final By PENDING_APPROVAL_FILTER = By.id("filter-pending-approval");
	public static final By WORKSPACE_LINK = By.xpath("//a[contains(text(),'Workspace')]");
}
