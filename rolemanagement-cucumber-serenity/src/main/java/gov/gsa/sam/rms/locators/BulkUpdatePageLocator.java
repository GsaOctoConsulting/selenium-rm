package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class BulkUpdatePageLocator {

	public static final By ORGPICKER_TEXTAREA = By.id("federalHierarchy");
	public static final By ORG_SELECTOR = By.id("federalHierarchy-listbox");
	public static final By ROLE_SELECTOR = By.id("role");
	public static final By DOMAIN_SELECTOR = By.className("category-item");
	public static final By DOMAINDROPDOWN_ICON = By.xpath(
			"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/div[1]/div[1]/div/div/div[3]/sam-autocomplete-multiselect/div/sam-label-wrapper/div/div[2]/div[2]/div/div/span");
	public static final By DOMAIN_TEXTAREA = By.id("domain-ac-textarea");
	public static final By NEXT_BUTTON = By.className("primary");
	public static final By HEADER_ICON_PROFILE = By.id("headerIconProfile");
	public static final By HEADERLINK_SIGNOUT=By.id("header-link-signout");

}
