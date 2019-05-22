package gov.gsa.sam.rms.utilities;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.locators.AccountDetailsPageLocator;

public class CommonMethods {
	private static Logger logger = LoggerFactory.getLogger(CommonMethods.class);

	private CommonMethods() {

	}

	public static void delay(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException ex) {
		}
	}

	public static boolean findPageElement(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean elementFound(WebDriver page, By locator, int seconds) {
		boolean elementFound = false;
		WebDriverWait wait = new WebDriverWait(page, seconds);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			elementFound = true;
		} catch (TimeoutException | NoSuchElementException e) {
			logger.info("Did not find the element within explicit wait time");
		}
		return elementFound;
	}

	public static void clickSideNavToGoToPage(String pageName, WebDriver driver) {
		List<WebElement> pageList = driver.findElements(AccountDetailsPageLocator.SIDE_NAV);
		logger.info("The size of the side-nav list is----" + pageList.size());
		for (int i = 0; i < pageList.size(); i++) {
			if (pageName.equals(pageList.get(i).getText())) {
				
				pageList.get(i).click();
			}
		}
		
	}
}
