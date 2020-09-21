package gov.gsa.sam.rms.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gov.gsa.sam.rms.pages.T1WorkspacePage;
import gov.gsa.sam.rms.utilities.Constants;
import gov.gsa.sam.rms.utilities.ConstantsAccounts;
import gov.gsa.sam.rms.utilities.FHUtility;
import gov.gsa.sam.rms.utilities.SignInUtility;
import gov.gsa.sam.rms.utilities.SignUpUtility;

public class OfficeMoveValidationStep {
	private static Logger logger = LoggerFactory.getLogger(OfficeMoveValidationStep.class);
	
	
	 @Given("^_1omv fh super admin logs in$")
	    public void _1omv_fh_super_admin_logs_in() throws Throwable {
		 SignInUtility.signIntoWorkspace(ConstantsAccounts.FH_SUPER_ADMIN, Constants.USERPASS,
				 ConstantsAccounts.FH_SUPER_ADMIN_SECRETKEY, Constants.USER_FED);  
		 
	    }

	    @And("^_1omv super admin creates the first subiter under gsa$")
	    public void _1omv_super_admin_creates_the_first_subiter_under_gsa() throws Throwable {
	     
	    	
	    	String counter = SignUpUtility.updatecounter("login.fed.accountno");
	    T1WorkspacePage.gotoFHPage();   
	     FHUtility.goToOrgDetails(Constants.ORG_GSA);
	     String uniqueagencycode ="1"+ counter.substring(1, counter.length());
	     FHUtility.createSubTier("RMOffice"+uniqueagencycode,uniqueagencycode,"111","111");
	    }
}
