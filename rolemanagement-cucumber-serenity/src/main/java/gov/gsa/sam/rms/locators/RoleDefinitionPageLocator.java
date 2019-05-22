package gov.gsa.sam.rms.locators;

import org.openqa.selenium.By;

public class RoleDefinitionPageLocator {

	public static final By CREATENEWROLE_BUTTON = By.className("usa-button-outline");
	public static final By PAGE_HEADER = By.tagName("h2");
	public static final By OBJECTDEFINITION_FILTER = By.id("object-def");
	public static final By DOMAINFILTER_LIST = By
			.xpath("//*[@id=\"Domains-Filter\"]/sam-checkbox/sam-fieldset-wrapper/div/fieldset/ul");

}
