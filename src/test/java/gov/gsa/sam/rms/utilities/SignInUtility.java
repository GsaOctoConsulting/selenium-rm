package gov.gsa.sam.rms.utilities;

import gov.gsa.sam.rms.pages.HomePage;
import gov.gsa.sam.rms.pages.MyWorkspacePage;

public class SignInUtility {

	public static void signIntoCommonWorkspacePage(String userEmail, String userPassword) throws Exception {
		LaunchBrowserUtil.openThisBrowser();
		LaunchBrowserUtil.enterUrl(Constants.COMP_HOME_PAGE);
		HomePage.setDriver(LaunchBrowserUtil.getDriver());
		HomePage.clickNavSignIn();
		HomePage.acceptGovPolicyPopup();
		HomePage.enterUserNamePasswordAndClickSignIn(userEmail, userPassword);
		CommonMethods.delay(17);
		LaunchBrowserUtil.captureOTPfromGsaEmail(Constants.GMAIL_USERNAME);
		HomePage.enterOtpAndSubmit();
		MyWorkspacePage.setDriver(LaunchBrowserUtil.getDriver());
		CommonMethods.delay(2);
		
		
		//MyWorkspacePage.getDriver().navigate().refresh();
		//MyWorkspacePage.clickSignInButton();
		
		
		CommonMethods.delay(8);
	}
}
