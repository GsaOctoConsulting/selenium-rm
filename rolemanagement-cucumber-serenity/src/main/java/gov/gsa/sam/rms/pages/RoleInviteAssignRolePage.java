package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.RoleInviteAssignRolePageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

public class RoleInviteAssignRolePage {
	private static WebDriver driver;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(RoleInviteAssignRolePage.class);

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		RoleInviteAssignRolePage.driver = driver;
	}

	public static void enterEmailAddress(String email) {
		driver.findElement(By.id("user-email")).sendKeys(email);
		LaunchBrowserUtil.delay(3);

	}

	public static void clickExistingUserAcceptButton() {
		driver.findElement(By.id("existing-user-accept-button")).click();
		LaunchBrowserUtil.delay(2);
	}

	public static boolean selectEntityDomainIfFound(String domainname) {
		boolean domainFound = false;
		Select domain = new Select(driver.findElement(RoleInviteAssignRolePageLocator.DOMAIN_SELECTOR));
		try {
			domain.selectByVisibleText(domainname);
			domainFound = true;
		} catch (NoSuchElementException e) {

			return domainFound;
		}
		return domainFound;

	}

	public static String getUserEmailErrorMessage() {
		return driver.findElement(By.id("user-email-error")).getText();

	}

	public static boolean selectEntityNonFedIfFound(String entity, int dropdownOptionNo) {
		boolean orgFound = false;
		driver.findElement(By.id("entityPicker-Entities")).sendKeys(entity);
		LaunchBrowserUtil.delay(3);
		List<WebElement> orgList = driver.findElements(By.xpath("//li[starts-with(@role, 'option')]"));
		logger.info(("The size of the list is......" + orgList.size()));
		WebElement firstOrg = orgList.get(dropdownOptionNo);
		logger.info("*****************the text from first org is*****" + firstOrg.getText());
		if (firstOrg.getText().toLowerCase().contains(entity.toLowerCase())) {
			orgList.get(dropdownOptionNo).click();
			LaunchBrowserUtil.delay(3);
			orgFound = true;
		}
		return orgFound;
	}

	public static boolean selectEntityRoleIfFound(String roleName) {
		boolean roleFound = false;
		Select role = new Select(driver.findElement(AssignRolePageLocator.ROLE_SELECTOR));
		try {
			role.selectByVisibleText(roleName);
			roleFound = true;
		} catch (NoSuchElementException e) {

			return roleFound;
		}
		return roleFound;
	}

	public static void enterBusinessJustification(String comment) {
		driver.findElement(By.id("comment")).sendKeys(comment);
		LaunchBrowserUtil.delay(2);

	}

	public static void clickSendInvitationButton() {
		driver.findElement(By.id("send-invitation-button")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickCloseButton() {
		driver.findElement(By.tagName("sam-button")).click();
		LaunchBrowserUtil.delay(3);

	}

	public static String getPendingUserAccessAlertMessage() {
		LaunchBrowserUtil.delay(3);
		String message = driver.findElement(By.tagName("sam-alert")).getText();
		logger.info(message);
		return message;

	}

	public static String getTextForBusinessJustification() {

		String justificationtext = driver.findElement(By.xpath(
				"//*[@id=\"main-container\"]/nonfed-role-assign/div/div[2]/form/div[5]/sam-text-area/sam-label-wrapper/div/label"))
				.getText();
		LaunchBrowserUtil.delay(2);
		return justificationtext;
	}

	public static String getCommentError() {
		String commenterror = driver.findElement(By.id(
				"comment-error"))
				.getText();
		LaunchBrowserUtil.delay(2);
		return commenterror;
		
	}
}
