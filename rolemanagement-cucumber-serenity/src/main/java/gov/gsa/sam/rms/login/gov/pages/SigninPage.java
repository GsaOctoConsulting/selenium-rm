package gov.gsa.sam.rms.login.gov.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.sam.rms.utilities.CommonMethods;
import gov.gsa.sam.rms.utilities.LaunchBrowserUtil;

public class SigninPage {
	private static WebDriver driver;
	private static Logger logger = LoggerFactory.getLogger(SigninPage.class);
	static Random random = new Random();

	private SigninPage() {
		logger.info("logger instantiated internally");
	}

	public static void clickSignInButton() {
		driver.findElement(By.linkText("Sign in")).click();

		CommonMethods.delay(2);
	}

	public static void enterEmailAddress(String email) {
		driver.findElement(By.id("user_email")).sendKeys(email);
		CommonMethods.delay(2);
	}

	public static void enterPassword(String password) {
		driver.findElement(By.id("user_password")).sendKeys(password);
		CommonMethods.delay(2);
	}

	public static void clickNextButton() {
		driver.findElement(By.className("btn-primary")).click();
		CommonMethods.delay(2);
	}

	public static void enterOtp(String otp) {
		LaunchBrowserUtil.getDriver().findElement(By.id("code")).sendKeys(otp);
		CommonMethods.delay(2);
	}

	public static void clickSubmit() {
		LaunchBrowserUtil.getDriver().findElement(By.className("btn-primary")).click();
		CommonMethods.delay(2);
	}

	// *****************************************************************************
	// driver getter and setter
	// *****************************************************************************
	public static WebDriver getDriver() {
		return SigninPage.driver;
	}

	public static void setDriver(WebDriver driver) {
		SigninPage.driver = driver;
	}

	public static void clickCreateAnAccount() {
		driver.findElement(By.linkText("Create an account")).click();
		CommonMethods.delay(2);

	}

	public static void clickResend() {
		driver.findElement(By.className("btn-primary")).click();
		CommonMethods.delay(2);

	}

	public static void enterSignUpPassword(String password) {
		CommonMethods.delay(2);
		driver.findElement(By.id("password_form_password")).sendKeys(password);
		CommonMethods.delay(2);

	}

	public static void clickContinue() {
		driver.findElement(By.className("btn-primary")).click();
		CommonMethods.delay(2);

	}

	public static void clickAuthenticationApplication() {
		driver.findElement(By.xpath("//*[@id=\"new_two_factor_options_form\"]/div[1]/fieldset/label[3]/div/span[2]"))
				.click();
		CommonMethods.delay(2);

	}

	public static String getQRSecretCode() {
		return driver.findElement(By.id("qr-code")).getText();

	}

	public static void clickSubmitOnOtp() {
		CommonMethods.delay(2);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/ul/li[3]/div[2]/form/div/div[2]/input")).click();
		CommonMethods.delay(2);

	}

	public static String getPersonalKey() {
		String personalkey = driver.findElement(By.className("bg-pk-box")).getText();
		CommonMethods.delay(2);
		return personalkey;
	}

	public static void clickContinueOnPersonalKey() {
		CommonMethods.delay(2);
		driver.findElement(By.xpath("//*[@id=\"confirm-key\"]/div/div[3]/button")).click();
		CommonMethods.delay(2);

	}

	public static void enterPersonalKeyOnAlertBox(String personalkey) {
		CommonMethods.delay(2);
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("confirm-key")));
		actions.sendKeys(personalkey);
		actions.build().perform();
		CommonMethods.delay(2);

	}

	public static void clickContinueToBeta() {
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/form/input[1]")).click();
		CommonMethods.delay(2);
	}

	public static void clickPersonalKeyConfirm() {
		driver.findElement(By.xpath("//*[@id=\"confirm-key\"]/div/div[3]/button")).click();
		/*
		 * Actions actions = new Actions(driver);
		 * actions.moveToElement(driver.findElement(By.className("personal-key-confirm")
		 * )); actions.click(); actions.build().perform(); CommonMethods.delay(2);
		 */

		CommonMethods.delay(2);

	}

	public static void selectStateforDriversPermit(String state) {
		Select select = new Select(driver.findElement(By.id("jurisdiction_state")));
		select.selectByVisibleText(state);
		CommonMethods.delay(1);
	}

	public static void clickGetStarted() {
		driver.findElement(By.className("btn-primary")).click();
		CommonMethods.delay(1);

	}

	public static void clickUseYourComputer() {
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[3]/form/button")).click();
		CommonMethods.delay(1);
	}

	public static void uploadFileWithUrl(String fileUploadPath) {
		driver.findElement(By.xpath("//*[@id=\"doc_auth_image\"]")).sendKeys(fileUploadPath);
		CommonMethods.delay(3);

	}

	public static void enterSSN() {

		String fourdigit = String.format("%04d", random.nextInt(10000));
		driver.findElement(By.id("doc_auth_ssn")).sendKeys("18667" + fourdigit);
		CommonMethods.delay(1);

	}

	public static void enterPhoneNoForIdentityVerification(String phoneno) {
		driver.findElement(By.id("idv_phone_form_phone")).sendKeys(phoneno);
		CommonMethods.delay(2);

	}

	public static void clickTextMessage() {
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/form/fieldset/label[1]/div/span[2]")).click();
		CommonMethods.delay(1);
	}

	public static void enterOtpFromText(String otpFromText) {
		CommonMethods.delay(2);
		driver.findElement(By.id("user_phone_form_phone")).sendKeys(otpFromText);

	}

	public static void enterMobilePhoneNo(String phone) {
		CommonMethods.delay(1);
		driver.findElement(By.id("user_phone_form_phone")).sendKeys(phone);
		
	}
}
