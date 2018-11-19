package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class RoleRequestPendingPageLocator {

	public static final By DELETE_BUTTON = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div[2]/div/sam-button/button");
	public static final By POPUP_CONFIRM = By
			.xpath("//*[@id=\"main-container\"]/ng-component/sam-modal/div/div/div[2]/div/sam-button[2]/button");
	public static final By ASSIGNROLE_BUTTON = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/form-only/div/div/div/div[2]/div[3]/div[2]/div/sam-button[2]/button");
}
