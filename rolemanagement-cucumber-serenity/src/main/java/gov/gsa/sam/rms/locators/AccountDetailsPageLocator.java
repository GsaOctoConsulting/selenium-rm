package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AccountDetailsPageLocator {
	public static final String TITLE = "beta.SAM.gov | Account Details";
	public static final By DEPARTMENT = By.className("usa-form-group");
	public static final By MENU_DROPDOWN = By.className("fa-bars");
	public static final By WORKSPACE = By.className("fa-table");
	public static final By SIDE_NAV = By.className("step");

	public static final By BUTTON_DEACTIVATE = By.xpath("//*[@id=\"deactivate\"]/div[2]/sam-button/button");
	public static final By POPUP_YES_DEACTIVATE = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/section/ng-component/sam-modal[1]/div/div/div[2]/div/sam-button[2]/button");
	public static final By POPUP_YES_DEACTIVATE2 = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/section/ng-component/sam-modal[2]/div/div/div[2]/div/sam-button[2]/button");
	public static final By API_KEY_TEXT = By.xpath("//*[@id=\"api-key-grid\"]/div/span[1]");
	public static final By API_KEY_ERROR_MESSAGE = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/section/ng-component/sam-modal-basic/div/div/div[2]/div/div[1]/span");
	public static final By ENTITY_INFO = By.id("organization-information");
}
