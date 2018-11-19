package gov.gsa.sam.rms.utilities;

public class Constants {

	// urls
	public static final String samDotGov = "https://alpha.sam.gov/";
	public static final String ALPHA_HOME_PAGE = "https://alpha.sam.gov/";
	public static final String COMP_HOME_PAGE = "https://55samfrontendcomp.apps.prod-iae.bsp.gsa.gov";
	public static final String samDotGovLogin = "https://alpha.sam.gov";

	// login credentials
	public static final String userEmail = "shah.raiaan+coSelenium@gsa.gov";
	public static final String userPass = "WhiteColor1!";
	public static String pass;

	public static final String GMAIL_USERNAME = "shah.raiaan@gsa.gov";
	public static String OTP;

	// sleep time between steps(to view tests slowly if needed)
	public static final int seconds = 2;

	// Org
	public static final String ORG_GSA = "General Services Administration";
	public static final String ORG_HHS = "Health and Human Services, Department of";
	public static final String ORG_EOP = "Executive Office of the President";
	public static final String ORG_GSA_FAS_OFFICE_OF_ACQUISITIONOPERA = "OFFICE OF ACQUISITION OPERA";
	public static final String ORG_EOIR = "Executive Office for Immigration Review";

	// roles
	public static final String ROLE_ROLEADMIN_SAMPMOADMINALLDOMAINS = "SAM PMO Administrator All Domains";
	public static final String ROLE_CONTRACTING_SPECIALIST_EDITOR = "Contracting Specialist";
	public static final String ROLE_CONTRACTING_OFFICER_PUBLISHER = "Contracting Officer";
	public static final String ROLE_CONTRACT_OPPORTUNITIES_GRANDUSER_SAMPMOADMINISTRATOR = "SAM PMO Administrator";
	public static final String ROLE_ASSISTANCE_USER = "Assistance User";
	public static final String ROLE_ASSISTANCE_ADMIN = "Assistance Administrator";
	public static final String ROLE_SYSTEM_ACCOUNT_ADMIN = "System Account Admin";
	public static final String ROLE_SYSTEM_MANAGER = "System Manager";
	public static final String ROLE_GSA_SECURITYAPPROVER = "GSA Security Approver";
	public static final String ROLE_IAM_ADMIN = "IAM Admin";
	public static final String ROLE_FSD_ADMIN = "FSD Admin";
	public static final String ROLE_OMB_ANALYST = "OMB Analyst";
	public static final String ROLE_ALERTS_ADMIN = "Alerts Admin";
	public static final String ROLE_FSD_AGENT = "FSD Agent";
	public static final String ROLE_DEPARTMENT_ROLE_ADMIN_ADMINISTRATORALLDOMAINS = "Administrator All Domains";
	public static final String ROLE_AL_GRANDUSER_SAMPMOADMINISTRATOR = "SAM PMO Administrator";
	public static final String ROLE_OMB_ADMIN = "OMB Administrator";
	// domains
	public static final String DOMAIN_CONTRACT_OPPORTUNITIES = "Contract Opportunities";
	public static final String DOMAIN_CONTRACT_DATA = "Contract Data";
	public static final String DOMAIN_ASSISTANCE_LISTING = "Assistance Listing";
	public static final String DOMAIN_ADMIN = "Admin";

	// status
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_REJECTED = "Rejected";
	public static final String STATUS_APPROVED = "Approved";
	public static final String STATUS_CANCELED = "Canceled";
	public static final String STATUS_COMPLETE = "Complete";
	public static final String STATUS_PENDING_APPROVAL = "Pending Approval";
	public static final String STATUS_DRAFT = "Draft";

	// custom commands
	public static final String REJECTROLE = "RejectRole";
	public static final String NOACTION = "No Action";
	public static final String EDIT = "EDIT";
	public static final String DELETE = "DELETE";
	public static final String GO_TO_REQUEST_DETAILS = "Go into request details";

	// email message constants
	public static final String EMAIL_ROLEREQUEST_MESSAGE = "Your SAM.gov role request was submitted";
	public static final String ORG_GSA_OFFICE_OF_ACQUISITION_POLICY = "Office of Acquisition Policy";

	private Constants() {

	}
}