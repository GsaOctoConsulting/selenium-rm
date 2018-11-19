package gov.gsa.sam.rms.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.CommonMethods;
import net.serenitybdd.core.annotations.findby.By;

public class SystemAccountRequestPage {
	private static WebDriver driver;

	private static Logger logger = LoggerFactory.getLogger(SystemAccountRequestPage.class);

	private SystemAccountRequestPage() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		SystemAccountRequestPage.driver = driver;
	}

	public static void writeComment(String string) {
		driver.findElement(By.id("request-comment")).sendKeys(string);
		CommonMethods.delay(1);

	}

	public static void clickRejectButton() {
		driver.findElement(By
				.xpath("//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/div/div/sam-button[2]/button"))
				.click();
		CommonMethods.delay(2);
	}

	public static void clickCloseButton() {
		driver.findElement(By
				.xpath("//*[@id=\"main-container\"]/ng-component/page/div/div/div[2]/div/div[1]/div/div/sam-button/button"))
				.click();
		CommonMethods.delay(2);

	}

	public static boolean commentFound(String useremail, String comments) {
		boolean commentFound=false;
		List<WebElement>commentlist= driver.findElements(By.className("sam-comment"));
		logger.info("The size of the comment list is------- "+commentlist.size());		
		
		for(int i=0;i<commentlist.size();i++){
		String completetext = commentlist.get(i).getText();
	
		logger.info("The matching full comment is---- "+completetext);
		if(completetext.contains(useremail)&&completetext.contains(comments)){
			commentFound=true;
		}
		}
		
		
		return commentFound;
	}
}
