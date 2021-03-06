package gov.gsa.sam.rms.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AssignRolePageLocator;
import gov.gsa.sam.rms.locators.FeedsRequestPageLocator;
import gov.gsa.sam.rms.locators.UserDirectoryPageLocator;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

public class UserDirectoryPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(UserDirectoryPage.class);

	private UserDirectoryPage() {

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		UserDirectoryPage.driver = driver;
	}

	public static void searchUserOrgUserPicker(String userid) {
		driver.findElement(UserDirectoryPageLocator.USER_PICKER_BAR).sendKeys(userid);
		driver.findElement(UserDirectoryPageLocator.FIRST_RESULT).click();
		LaunchBrowserUtil.delay(2);
	}

	public static List<WebElement> getUserList() {
		logger.info("The userdirectory list size is - "
				+ driver.findElements(UserDirectoryPageLocator.GET_LISTOFUSERS).size());
		return driver.findElements(UserDirectoryPageLocator.GET_LISTOFUSERS);
	}

	public static void clickActions(String useremail) {
//		int itemNo = 0; // new view
//		String id = useremail.concat("-action-" + itemNo);
//		driver.findElement(By.id(id)).click();
//		LaunchBrowserUtil.delay(2);

		// new view

		driver.findElement(By.id(useremail.concat("-action-button"))).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickDownload() {
		driver.findElement(UserDirectoryPageLocator.DOWNLOAD_ICON).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickPdf() {
		driver.findElement(UserDirectoryPageLocator.PDF_DOWNLOAD).click();
		LaunchBrowserUtil.delay(3);
	}

	public static void clickCsv() {
		driver.findElement(UserDirectoryPageLocator.CSV_DOWNLOAD).click();
		LaunchBrowserUtil.delay(4);
	}

	public static void clickAssignRole(String useremail) {
		clickActions(useremail);
		LaunchBrowserUtil.delay(2);
		// driver.findElement(UserDirectoryPageLocator.ASSIGN_ROLE).click();
		driver.findElement(By.id("menuitem1")).click();
		AssignRolePage.setDriver(UserDirectoryPage.getDriver());
		LaunchBrowserUtil.delay(4);
	}

	public static void clickViewAccess(String useremail) {
		clickActions(useremail);
		LaunchBrowserUtil.delay(4);

		driver.findElement(By.id("menuitem0")).click();
		LaunchBrowserUtil.delay(2);
		MyRolesPage.setDriver(UserDirectoryPage.getDriver());
		UserDirectoryViewAccessPage.setDriver(UserDirectoryPage.getDriver());
		AccountDetailsPage.setDriver(driver);
	}

	public static void clickCancel() {
		driver.findElement(UserDirectoryPageLocator.DOWNLOAD_CANCEL).click();
	}

	public static void clickNonFedFilter() {
		driver.findElement(UserDirectoryPageLocator.NONFED_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickFedFilter() {
		driver.findElement(UserDirectoryPageLocator.FED_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickContractingOfficerFilter() {
		driver.findElement(UserDirectoryPageLocator.CONTRACTING_OFFICER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickAssistanceAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.ASSISTANCE_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickALGrandUserFilter() {
		driver.findElement(UserDirectoryPageLocator.AL_GRANDUSER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickDRAFilter() {
		driver.findElement(UserDirectoryPageLocator.DRA_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickSAMPmoAdminAllDomainsFilter() {
		driver.findElement(UserDirectoryPageLocator.RA_FILTER_SAMPMOALLDOMAINS).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickAssistanceUserFilter() {
		driver.findElement(UserDirectoryPageLocator.ASSISTANCEUSER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickFsdAgentFilter() {
		driver.findElement(UserDirectoryPageLocator.FSDAGENT_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickFsdAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.FSDADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickAlertsAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.ALERTSADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickAgencyAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.AGENCYADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickCOAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.CO_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickOmbAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.OMB_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickDepartmentAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.DEPARTMENT_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickOfficeAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.OFFICE_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickSuperAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.SUPERADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickContractDataGrandUserFilter() {
		driver.findElement(UserDirectoryPageLocator.CONTRACTDATA_GRADUSER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickContractOpportunitiesGrandUserFilter() {
		driver.findElement(UserDirectoryPageLocator.CONTRACTOPPORTUNITIES_GRADUSER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickContractingSpecialistFilter() {
		driver.findElement(UserDirectoryPageLocator.CONTRACTING_SPECIALIST_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickDodAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.DOD_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickGSASecurityApproverFilter() {
		driver.findElement(UserDirectoryPageLocator.GSA_SECURITYAPPROVER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickIAMadminFilter() {
		driver.findElement(UserDirectoryPageLocator.IAM_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickNasaAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.NASA_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickReportUserFilter() {
		driver.findElement(UserDirectoryPageLocator.REPORTUSER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickSystemAccountAdminFilter() {
		driver.findElement(UserDirectoryPageLocator.SYSTEMACCOUNT_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickSystemManagerFilter() {
		driver.findElement(UserDirectoryPageLocator.SYSTEM_MANAGER_FILTER).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickOmbAnalystFilter() {
		driver.findElement(UserDirectoryPageLocator.OMB_ANALYST_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	// -----------role administration filters------
	public static void clickUserIAdministerFilter() {
		driver.findElement(UserDirectoryPageLocator.USER_I_ADMINSTER_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickUserWithNoRolesFilter() {
		driver.findElement(UserDirectoryPageLocator.USER_WITHNOROLES_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	// --------domain filters------------
	public static void clickAdminDomainFilter() {
		driver.findElement(UserDirectoryPageLocator.DOMAIN_ADMIN_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickAssitanceListingDomainFilter() {
		driver.findElement(UserDirectoryPageLocator.DOMAIN_AL_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickContractDataDomainFilter() {
		driver.findElement(UserDirectoryPageLocator.DOMAIN_CONTRACTDATA_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickContractOpportunitiesDomainFilter() {
		driver.findElement(UserDirectoryPageLocator.DOMAIN_CONTRACTOPPORTUNITIES_FILTER).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickFHdomainFilter() {
		driver.findElement(UserDirectoryPageLocator.DOMAIN_FH_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickReportingDomainFilter() {
		driver.findElement(UserDirectoryPageLocator.DOMAIN_REPORTING_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickClearFilter() {
		driver.findElement(UserDirectoryPageLocator.CLEARFILTER).click();
		LaunchBrowserUtil.delay(1);
	}

	public static void clickProvideFeedbackLink() {
		driver.findElement(UserDirectoryPageLocator.PROVIDEFEEDBACK_LINK).click();

	}

	public static void clickAdvancedInOrgPicker() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement elem = driver.findElement(UserDirectoryPageLocator.ORGPICKER_ADVANCED);
		// WebElement elem =
		// driver.findElement(By.xpath("//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[1]/sidebar/div[1]/sam-agency-picker-v2/sam-label-wrapper/div/div[2]/a[1]"));

		boolean present = driver.findElement(UserDirectoryPageLocator.ORGPICKER_ADVANCED).isDisplayed();
		logger.info("boolean::::::" + present);
		Actions builder = new Actions(driver);

		//// *[@id="main-container"]/ng-component/page/div/div/div[2]/div[1]/sidebar/div[1]/sam-agency-picker-v2/sam-label-wrapper/div/div[2]/a[1]
		Actions builder1 = builder.moveToElement(elem).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).click();

		logger.info("Click ON ::::::" + present);
		builder1.build().perform();

		Thread.sleep(2000);

		/*
		 * * elem.sendKeys(Keys.TAB); elem.sendKeys(Keys.ENTER);
		 * 
		 * // driver.findElement(UserDirectoryPageLocator.ORGPICKER_ADVANCED).
		 * sendKeys(Keys.ENTER);
		 */
	}

	public static void clickSearchInOrgPicker() throws InterruptedException {
		driver.findElement(UserDirectoryPageLocator.SEARCH_ORGPICKER).click();
		LaunchBrowserUtil.delay(2);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement elem = driver.findElement(UserDirectoryPageLocator.SEARCH_ORGPICKER);
		// WebElement elem =
		// driver.findElement(By.xpath("//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[1]/sidebar/div[1]/sam-agency-picker-v2/sam-label-wrapper/div/div[2]/a[1]"));
		Actions builder = new Actions(driver);

		//// *[@id="main-container"]/ng-component/page/div/div/div[2]/div[1]/sidebar/div[1]/sam-agency-picker-v2/sam-label-wrapper/div/div[2]/a[1]
		Actions builder1 = builder.moveToElement(elem).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).click();
		builder1.build().perform();

		Thread.sleep(2000);
	}

	public static List<WebElement> getPagination() {
		List<WebElement> pagination = driver.findElements(UserDirectoryPageLocator.PAGINATION);
		logger.info("The size of the pagination is -" + pagination.size());
		return pagination;
	}

	public static boolean userActionOptionsFound(String optionslookup) {
		boolean optionFound = false;
		List<WebElement> list = driver.findElements(UserDirectoryPageLocator.GET_LISTOFUSERACTIONS);
		for (int i = 0; i < list.size(); i++) {
			String options = list.get(i).getText().toLowerCase();
			if (options.contains(optionslookup.toLowerCase())) {
				optionFound = true;
			}
		}
		return optionFound;
	}

	public static boolean isClickable(WebElement webelement) {
		try {
			webelement.findElement(By.tagName("a"));
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}

		/*
		 * if (linktag.isDisplayed()) { return true; } else { return false; }
		 */

		/*
		 * if (webelement.getTagName().equals("a")) { return true; } else { return
		 * false; }
		 */
	}

	public static boolean elementFound(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("NoSuch Element found");
			return false;
		}
	}

	public static boolean isFilterSelected(By filterlocator) {
		return driver.findElement(filterlocator).isSelected();
	}

	public static void departmentSearch(String name) {
		driver.findElement(UserDirectoryPageLocator.DEPARTMENT_SEARCHBOX).sendKeys(name);
		LaunchBrowserUtil.delay(1);
	}

	public static int suggestedOrgCount() {
		List<WebElement> list = driver.findElements(UserDirectoryPageLocator.DEPARTMENT_SUGGESTIONLIST);
		logger.info("Size of suggestion list is--" + list.size());
		return list.size();
	}

	public static String getFirstOrgFound() {
		return driver.findElement(UserDirectoryPageLocator.FIRST_ORG_RESULT).getText();
	}

	public static int getTotalNoOfPages() {
		List<WebElement> pages = driver.findElements(UserDirectoryPageLocator.TOTAL_NO_OFRECORDS);
		logger.info("The number of pages in userdirectory are---" + pages.size());

		return pages.size();

		/*
		 * String resultMessage =
		 * driver.findElement(UserDirectoryPageLocator.TOTAL_NO_OFRECORDS).getText();
		 * String[] bits = resultMessage.split(" "); int recordNo =
		 * Integer.parseInt(bits[bits.length - 2]);
		 * logger.info("The number of records found are - " + recordNo); int
		 * totalNoPages = noOfPageExpected(recordNo);
		 * logger.info("The number of pages found are - " + totalNoPages); return
		 * totalNoPages;
		 */
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

	public static void clickPageNo(int pageno, int pageLimit) {
		List<WebElement> pagelist = getPagination();

		for (int i = 0; i < pageLimit && pagelist.size() > 1; i++) {
			String obtainedpageno = pagelist.get(i).getText();
			logger.info("The obtained page text from ui is--  " + obtainedpageno);
			if (Integer.parseInt(pagelist.get(i).getText()) == pageno) {
				logger.info("Text from the pagebutton - " + pagelist.get(i).getText());
				pagelist.get(i).click();
				LaunchBrowserUtil.delay(3);
				break;
			}
		}
	}

	public static void typeInOrgPicker(String orgsearchterm) {
		driver.findElement(UserDirectoryPageLocator.ORG_PICKER).sendKeys(orgsearchterm);
		LaunchBrowserUtil.delay(2);

	}

	public static int searchUserInUserPicker(String user) {
		LaunchBrowserUtil.delay(1);
		driver.findElement(UserDirectoryPageLocator.USER_PICKER_BAR).clear();
		driver.findElement(UserDirectoryPageLocator.USER_PICKER_BAR).sendKeys(user);
		LaunchBrowserUtil.delay(2);
		List<WebElement> userlist = driver.findElements(By.xpath("//li[starts-with(@id, 'userPicker-resultItem')]"));
		logger.info("The size of the user search list is --- "+userlist.size());
		driver.findElement(UserDirectoryPageLocator.FIRST_RESULT).click();
		LaunchBrowserUtil.delay(1);
		return userlist.size();
	}

	public static void clickViewAccessOnly(String useremail) {
		clickActions(useremail);
		LaunchBrowserUtil.delay(3); //
//		driver.findElement(By.xpath(
//				"//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div[2]/results/div[3]/div[1]/sam-actions-dropdown/div/ul/li/button/span[2]"))
//				.click(); // old view
		driver.findElement(By.id("menuitem0")).click(); // new view
		LaunchBrowserUtil.delay(2);
		MyRolesPage.setDriver(UserDirectoryPage.getDriver());
		UserDirectoryViewAccessPage.setDriver(UserDirectoryPage.getDriver());
	}

	public static int getCurrentSelectedPage() {
		List<WebElement> pages = driver.findElements(UserDirectoryPageLocator.PAGINATION);
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getAttribute("aria-label").equals("current")) {
				return i;
			}
		}
		return 0;
	}

	public static boolean userPickerAllUsersContainsThisSearchTerm(String searchterm) {
		WebElement usersearchbox = driver.findElement(UserDirectoryPageLocator.USER_PICKER_BAR);
		usersearchbox.clear();
		usersearchbox.sendKeys(searchterm);
		LaunchBrowserUtil.delay(3);
		WebElement allusers = driver.findElement(By.id("userPicker-listbox"));
		List<WebElement> listofusers = allusers
				.findElements(By.xpath(".//li[starts-with(@id, 'userPicker-resultItem')]"));
		logger.info("The size of the list of users --- " + listofusers.size());
		boolean allTrue = false;
		int counter = 0;
		for (int i = 0; i < listofusers.size(); i++) {
			String user = listofusers.get(i).getText().toLowerCase();
			logger.info("The text for username is -- " + user);
			if (user.contains(searchterm.toLowerCase())) {
				counter++;
			}
		}
		if ((listofusers.size() == counter) && (counter != 0)) {
			allTrue = true;
		}
		return allTrue;

	}

	public static boolean orgPickerAllOrgsContainsThisSearchTermAndOrgName(String searchterm, String org) {
		driver.findElement(UserDirectoryPageLocator.ORG_PICKER).sendKeys(searchterm);
		LaunchBrowserUtil.delay(1);
		List<WebElement> listoforgs = driver.findElements(By.id("federalHierarchy-listbox"));
		logger.info("The size of the list of orgs --- " + listoforgs.size());
		boolean allTrue = false;
		int counter = 0;
		for (int i = 0; i < listoforgs.size(); i++) {
			String orgdetails = listoforgs.get(i).getText().toLowerCase();
			logger.info("The text for orgname is -- " + orgdetails);
			if ((orgdetails.contains(searchterm.toLowerCase())) && (orgdetails.contains(org.toLowerCase()))) {
				counter++;
			}
		}
		if ((listoforgs.size() == counter) && (counter != 0)) {
			allTrue = true;
		}
		return allTrue;

	}

	public static void selectOrgInOrgPicker(String orgname) {
		driver.findElement(UserDirectoryPageLocator.ORG_PICKER).sendKeys(orgname);
		LaunchBrowserUtil.delay(2);
		List<WebElement> orgList = driver.findElements(AssignRolePageLocator.ORG_SELECTOR);
		logger.info(("The size of the list is......" + orgList.size()));
		/*
		 * WebElement firstOrg = orgList.get(0); firstOrg.click();
		 */
		driver.findElement(By.xpath("//*[@id=\"federalHierarchy_100006688\"]")).click();
		LaunchBrowserUtil.delay(2);
	}

	public static void clickOrgTypeWhereUsersHaveRoles() {
		driver.findElement(UserDirectoryPageLocator.ORG_PICKER_FILTER_WHERE_USERS_HAVE_ROLES).click();
		LaunchBrowserUtil.delay(1);

	}

	public static String getNoResultsmessageFound() {
		return driver.findElement(UserDirectoryPageLocator.NO_RESULTS_MESSAGE).getText();
	}

	public static void clickUsersOwnDomain() {
		driver.findElement(By.xpath("//input[starts-with(@id, 'my-domain-')]")).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void clickDataEntryFilter() {
		driver.findElement(UserDirectoryPageLocator.DATA_ENTRY_FILTER).click();
		LaunchBrowserUtil.delay(2);
	}

	public static boolean selectFilter(By filterlocator) {
		LaunchBrowserUtil.delay(1);
		boolean found = true;
		try {
			driver.findElement(filterlocator).click();
			LaunchBrowserUtil.delay(2);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			found = false;
			return found;
		}
		return found;
	}

	public static boolean searchEntityInEntityPicker(String entityname) {
		boolean entityfound = false;
		driver.findElement(UserDirectoryPageLocator.ENTITY_PICKER).sendKeys(entityname);
		LaunchBrowserUtil.delay(2);
		List<WebElement> entityList = driver.findElements(AssignRolePageLocator.ENTITY_LIST_SELECTOR);
		logger.info(("The size of the entity list is......" + entityList.size()));
		for (int i = 0; i < entityList.size(); i++) {
			WebElement currententity = entityList.get(i);
			String singleentitytext = currententity.getText();
			logger.info("Current entity is - " + singleentitytext);
			if (singleentitytext.toLowerCase().contains(entityname.toLowerCase())) {
				entityfound = true;
				currententity.click();
				break;
			}

		}
		return entityfound;
	}

	public static void searchUserInEntityPicker(String user) {
		searchUserInUserPicker(user);

	}

	public static boolean ifAllUsersAreClicable(int numberofpagestosearch, String texttoAssert) {
		boolean allAreClickable = true;
		boolean breakouterloop = false;
		int totalNoOfPages = numberofpagestosearch;// UserDirectoryPage.getTotalNoOfPages();
		// int currentlyselectedPage = 1;//UserDirectoryPage.getCurrentSelectedPage();
		int currentPage = 1;// always start at page 1
		do {// search page 1 regardless of whether other pages exist
			List<WebElement> userList = UserDirectoryPage.getUserList();
			for (int i = 0; i < userList.size(); i++) {
				WebElement currentuser = userList.get(i);
				String usertext = "";
				try {
					WebElement id = currentuser.findElement(UserDirectoryPageLocator.ID);
					usertext = id.getText();
					// ensures names are clickable
					logger.info("The text is ---- " + id.getText());
				} catch (NoSuchElementException e) {
					allAreClickable = false;
					breakouterloop = true;
					break;
				}

				// boolean fedIdFound = usertext.contains(texttoAssert);// ensures fed id not
				// found
				// Assert.assertEquals(false, fedIdFound);
				// ------------------------------------------------------

			}
			// click to next page and increment page counter
			if (totalNoOfPages > 1 && currentPage < totalNoOfPages) {
				currentPage++;
				UserDirectoryPage.clickPageNo(currentPage, totalNoOfPages);
			}
		} while ((currentPage < totalNoOfPages) && (breakouterloop == false));
		return allAreClickable;
	}

	public static boolean entityPickerAllUsersContainsThisSearchTerm(String entityname) {
		WebElement usersearchbox = driver.findElement(UserDirectoryPageLocator.ENTITY_PICKER);
		usersearchbox.clear();
		usersearchbox.sendKeys(entityname);
		LaunchBrowserUtil.delay(3);
		WebElement allusers = driver.findElement(By.id("entityPicker-Name-listbox"));
		List<WebElement> listofentities = allusers
				.findElements(By.xpath(".//li[starts-with(@id, 'entityPicker-Name-resultItem')]"));
		logger.info("The size of the list of entities --- " + listofentities.size());
		boolean allTrue = false;
		int counter = 0;
		for (int i = 0; i < listofentities.size(); i++) {
			String entity = listofentities.get(i).getText().toLowerCase();
			logger.info("The text for entity is -- " + entity);
			if (entity.contains(entityname.toLowerCase())) {
				counter++;
			}
		}
		if ((listofentities.size() == counter) && (counter != 0)) {
			allTrue = true;
		}
		return allTrue;
	}

	public static boolean dunsPickerAllUsersContainsThisSearchTerm(String dunsnumber) {
		WebElement dunsentitysearchbox = driver.findElement(UserDirectoryPageLocator.DUNS_PICKER);
		dunsentitysearchbox.clear();
		dunsentitysearchbox.sendKeys(dunsnumber);
		LaunchBrowserUtil.delay(3);
		WebElement allentity = driver.findElement(By.id("entityPicker-Unique Entity ID-listbox"));
		List<WebElement> listofentities = allentity
				.findElements(By.xpath(".//li[starts-with(@id, 'entityPicker-Unique Entity ID-resultItem')]"));
		logger.info("The size of the list of entities --- " + listofentities.size());
		boolean allTrue = false;
		int counter = 0;
		for (int i = 0; i < listofentities.size(); i++) {
			String entity = listofentities.get(i).getText().toLowerCase();
			logger.info("The text for entity is -- " + entity);
			if (entity.contains(dunsnumber.toLowerCase())) {
				counter++;
			}
		}
		if ((listofentities.size() == counter) && (counter != 0)) {
			allTrue = true;
		}
		return allTrue;
	}

	public static boolean cagePickerAllUsersContainsThisSearchTerm(String cagecode) {
		WebElement cageentitysearchbox = driver.findElement(UserDirectoryPageLocator.CAGECODE_PICKER);
		cageentitysearchbox.clear();
		cageentitysearchbox.sendKeys(cagecode);
		LaunchBrowserUtil.delay(3);
		WebElement allentity = driver.findElement(By.id("entityPicker-CAGE Code-listbox"));
		List<WebElement> listofentities = allentity
				.findElements(By.xpath(".//li[starts-with(@id, 'entityPicker-CAGE Code-resultItem')]"));
		logger.info("The size of the list of entities --- " + listofentities.size());
		boolean allTrue = false;
		int counter = 0;
		for (int i = 0; i < listofentities.size(); i++) {
			String entity = listofentities.get(i).getText().toLowerCase();
			logger.info("The text for entity is -- " + entity);
			if (entity.contains(cagecode.toLowerCase())) {
				counter++;
			}
		}
		if ((listofentities.size() == counter) && (counter != 0)) {
			allTrue = true;
		}
		return allTrue;
	}

	public static void clickAssignRoleButton() {
		driver.findElement(
				By.xpath("//*[@id=\"main-container\"]/sam-user-directory-v2/page/div/div/div[2]/div/div[1]/div/a"))
				.click();
		RoleInviteAssignRolePage.setDriver(driver);
		LaunchBrowserUtil.delay(3);
	}

	public static void clickRoleFilter(By roleFilterSubtieradmin) {
		driver.findElement(roleFilterSubtieradmin).click();
		LaunchBrowserUtil.delay(2);

	}

	public static void goToWorkspacePage() {
		LaunchBrowserUtil.delay(2);
		driver.findElement(UserDirectoryPageLocator.WORKSPACEPAGE_BREADCRUMB_LINK).click();
		LaunchBrowserUtil.delay(1);
	}

}
