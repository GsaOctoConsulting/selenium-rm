package gov.gsa.sam.rms.utilities;

public class Constants {

	static PropertiesFileUtility fileUtilityreader = new PropertiesFileUtility("application.properties");
	// urls
	public static final String LOGINGOV_HOME_PAGE = "https://100samfrontendaltminc.apps.prod-iae.bsp.gsa.gov";
	public static final String ROLE_MIGRATION_RESET_URL = "https://39rolemanagementminc.apps.prod-iae.bsp.gsa.gov";
	// public static final String LOGINGOV_HOME_PAGE =
	// "https://100samfrontendaltcomp.apps.prod-iae.bsp.gsa.gov";
	// login credentials
	public static final String USERPASS = "WhiteColor1!";
	public static final String USERPASS_NONFED = "WhiteColor1!";
	public static String PASS;
	public static final String GMAIL_USERNAME = "octotestaccount1@gsa.gov";
	public static String OTP;

	// Org----------------------------
	public static final String ORG_GSA = "General Services Administration".toUpperCase();
	public static final String ORG_HHS = "Health and Human Services, Department of".toUpperCase();
	public static final String ORG_EOP = "Executive Office of the President".toUpperCase();
	public static final String ORG_GSA_FAS_OFFICE_OF_ACQUISITIONOPERA = "OFFICE OF ACQUISITION OPERA".toUpperCase();
	public static final String ORG_EOIR = "Executive Office for Immigration Review".toUpperCase();
	public static final String ORG_OCTO_CONSULTING_GROUP = "OCTO CONSULTING GROUP, INC".toUpperCase();
	public static final String ORG_CODA_OCTOPUS = "Coda Octopus Products".toUpperCase();
	public static final String ORG_JACKSON_BOOKBINDING = "Jackson Bookbinding Co".toUpperCase();
	public static final String ORG_UTAH_COMMUNICATIONS_AUTHORITY = "UTAH COMMUNICATIONS AUTHORITY".toUpperCase();
	public static final String ORG_COCACOLA_BOTTLINGCOMPANY_OFNORTHERNNEWENGLAND = "COCA-COLA BOTTLING COMPANY OF NORTHERN NEW ENGLAND, INC, THE"
			.toUpperCase();
	// Org Codes
	public static final String CODE_ORG_GSA_DEPT = "047";
	public static final String CODE_ORG_GSA_SUBTIER = "4700";
	public static final CharSequence CODE_ORG_OCTO_CONSULTING = "800127859";

	// Roles---------------------------------
	public static final String ROLE_ROLEADMIN_SAMPMOADMINALLDOMAINS = "SAM PMO Administrator All Domains";
	public static final String ROLE_CONTRACTING_SPECIALIST_EDITOR = "Contracting Specialist";
	public static final String ROLE_CONTRACTING_OFFICER_PUBLISHER = "Contracting Officer";
	public static final String ROLE_CONTRACT_OPPORTUNITIES_GRANDUSER_SAMPMOADMINISTRATOR = "SAM PMO Administrator";
	public static final String ROLE_ASSISTANCE_USER = "Assistance User";
	public static final String ROLE_ASSISTANCE_ADMIN = "Administrator";
	public static final String ROLE_SYSTEM_ACCOUNT_ADMIN = "System Account Administrator";
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
	public static final String ROLE_DATA_ENTRY = "Data Entry";
	public static final String ROLE_AGENCY_ADMIN = "Agency Admin";
	public static final String ROLE_SUBTIER_ADMIN = "Sub-Tier Administrator";
	public static final String ROLE_VIEWER = "Viewer";
	public static final String ROLE_CONTRACTDATA_ADMIN = "Administrator";
	// domains--------------------------
	public static final String DOMAIN_CONTRACT_OPPORTUNITIES = "Contract Opportunities";
	public static final String DOMAIN_CONTRACT_DATA = "Contract Data";
	public static final String DOMAIN_ASSISTANCE_LISTING = "Assistance Listing";
	public static final String DOMAIN_ADMIN = "Admin";
	public static final String DOMAIN_FEDERAL_HIERARCHY = "Federal Hierarchy";
	public static final String DOMAIN_ENTITY_COMPLIANCE = "Entity Compliance";
	public static final String DOMAIN_ENTITY_REGISTRATION = "Entity Registration";

	// status--------------------------------
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_REJECTED = "Rejected";
	public static final String STATUS_APPROVED = "Approved";
	public static final String STATUS_CANCELED = "Canceled";
	public static final String STATUS_COMPLETE = "Complete";
	public static final String STATUS_PENDING_APPROVAL = "Pending Approval";
	public static final String STATUS_PENDING_PERMISSIONS_APPROVAL = "Pending Permissions Approval";
	public static final String STATUS_DRAFT = "Draft";
	public static final String STATUS_PENDING_REVIEW = "Pending Review";
	public static final String STATUS_PUBLISHED = "Published";
	public static final String STATUS_DEACTIVATED = "Deactivated";
	public static final String STATUS_ACCEPTED = "Accepted";
	public static final String STATUS_DECLINED = "Declined";

	// Role descriptions
	public static final String ROLEDESCRIPTION_ASSISTANCEUSER = "As an Assistance User, you may create and update assistance listings and regional office information. You may also submit listings to OMB for approval and request agency, title, or CFDA number changes.";
	public static final String ROLEDESCRIPTION_CONSTRACTINGOFFICER = "As a Contracting Officer, you may create, update, delete, and approve publication of notices.";
	public static final String ROLEDESCRIPTION_CONSTRACTINGSPECIALIST = "As a Contracting Specialist, you may create and update notices, and delete drafts. A Contracting Officer must approve your notices for publication.";
	// custom commands--------------------------

	/**
	 * These constants are used at various places as method parameters
	 */
	public static final String REJECTROLE = "RejectRole";
	public static final String NOACTION = "No Action";
	public static final String EDIT = "EDIT";
	public static final String DELETE = "DELETE";
	public static final String GO_TO_REQUEST_DETAILS = "Go into request details";
	public static final String GO_INTO_ROLE_ASSIGNED = "Go into role assigned";
	public static final String GO_INTO_ROLE_UPDATED = "Go into role updated";
	public static final String ASSIGNED = "Assigned";
	public static final String UPDATED = "Updated";
	public static final String VIEW_PERMISSION = "VIEW PERMISSION";
	public static final String REQUEST_CHANGES = "Request Changes";

	// email message constants-----------------------
	public static final String EMAIL_ROLEREQUEST_MESSAGE = "You have submitted a pending request for Contracting Officer";
	public static final String ORG_GSA_OFFICE_OF_ACQUISITION_POLICY = "Office of Acquisition Policy";
	public static final String EMAIL_ROLEREQUEST_MESSAGE_FOR_SUPERVISOR = "SHAH RAIAAN submitted a SAM.gov role request.";
	public static final String EMAIL_REGULAR_SENT_FROM = "donotreply";
	public static final String EMAIL_REGULAR_SENT_FROM_DOMAIN = "donotreply@sam.gov";
	public static final String EMAIL_ACTION_SUBMITTED = "submitted";
	public static final String EMAIL_REQUESTOR_NAME = "shah raiaan";
	public static final String EMAIL_ACTION_ASSIGNED = "assigned";
	public static final String EMAIL_ACTION_GRANTED = "granted";
	public static final String EMAIL_ACTION_PENDING = "pending";
	public static final String EMAIL_ACTION_REJECTED = "rejected";
	public static final String EMAIL_ACTION_REMOVED = "removed";
	public static final String EMAIL_ACTION_UPDATED = "updated";
	public static final String EMAIL_ACTION_APPROVED = "approved";
	public static final CharSequence EMAIL_ACTION_REQUESTED = "requested";
	public static final String EMAIL_ACTION_SENT = "sent";
	public static final CharSequence EMAIL_ACTION_INVITED = "invited";

	// system account email constants--
	public static final String EMAIL_SA_SUBMISSION_SUBJECT_LINE = "You have submitted a pending System Account application for initial review";
	public static final String EMAIL_SA_SUBMISSION_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by a System Account Administrator prior to a GSA Security Approver making a final determination.";
	public static final String EMAIL_SA_APPLICATION_LINK = "https://100samfrontendaltminc.apps.prod-iae.bsp.gsa.gov/workspace/requests/system/";
	public static final String EMAIL_SA_APPLICATION_LINK_REJECT = "https://100samfrontendaltminc.apps.prod-iae.bsp.gsa.gov/workspace/system/new/";
	public static final String EMAIL_SA_LEARNING_CENTER_LINK = "https://100samfrontendaltminc.apps.prod-iae.bsp.gsa.gov/help/new-to-sam";
	public static final CharSequence EMAIL_SENT_FROM = "donotreply";

	public static final CharSequence EMAIL_SENT_FROM_DOMAIN = "donotreply@sam.gov";
	public static CharSequence EMAIL_ENV;
	

	// system account admin email constants
	public static final String EMAIL_SAA_PENDING_SUBJECT_LINE = "You have a pending System Account application to review";
	public static final String EMAIL_SAA_SUBMISSION_EMAIL_BODY = "A System Account application has been submitted for review.";
	public static final String EMAIL_SAA_APPROVE_SUBJECT_LINE = "You approved a System Account application";
	public static final String EMAIL_SAA_APPROVE_EMAIL_BODY = "You have approved a System Account application";
	public static final String EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE = "Your System Account application has received final approval";
	public static final String EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY = "Your System Account application has received final approval";
	public static final String EMAIL_SAA_ACCOUNT_CREATION_SUBJECT_LINE = "You have submitted a pending System Account application for approval";
	public static final String EMAIL_SAA_ACCOUNT_CREATION_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by a GSA Security Approver for a final determination";

	public static final String EMAIL_SAA_REJECT_SUBJECT_LINE = "You rejected a System Account application";
	public static final String EMAIL_SAA_REJECT_EMAIL_BODY = "You have rejected a System Account application which can be viewed";

	public static final String EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE = "A System Account application was rejected";
	public static final String EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE = "A System Account application was rejected which can be viewed";

	// system accout manager email constants
	public static final String EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE = "Your System Account application has received initial approval";
	public static final String EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY = "Your System Account application has received initial approval";
	public static final String EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE = "Your System Account application has received final approval";
	public static final String EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY = "Your System Account application has received final approval";

	public static final String EMAIL_SAM_REJECT_SUBJECT_LINE = "Your System Account application has been rejected";
	public static final String EMAIL_SAM_REJECT_EMAIL_BODY = "Your System Account application has been rejected and can be viewed";

	// gsa security email constants
	public static final String EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE = "You have a pending system account request";
	public static final String EMAIL_GSAPPROVER_PENDING_EMAIL_BODY = "You have a pending system account request for approval/rejection";
	public static final String EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE = "You approved a System Account application";
	public static final String EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY = "You have approved a System Account application";

	public static final String EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE = "You rejected a System Account application";
	public static final String EMAIL_GSAPPROVER_REJECT_EMAIL_BODY = "You have rejected a System Account application which can be viewed";

	// system account nonfed constants
	public static final String EMAIL_NONFED_SYSTEMACCOUNT_CREATION_SUBJECT_LINE = "You have submitted a pending System Account application for approval";
	public static final String EMAIL_NONFED_SYSTEMACCOUNT_CREATION_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by a GSA Security Approver for a final determination";
	public static final String EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE = "Your System Account application has received final approval";
	public static final String EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY = "Your System Account application has received final approval and can be viewed";
	public static final String EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_SUBJECT_LINE = "Your System Account application has been rejected";
	public static final String EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_EMAIL_BODY = "Your System Account application has been rejected and can be viewed";

	// system accounts security official constants
	public static final String EMAIL_SO_PENDING_SUBJECT_LINE = "You have a pending system account request";
	public static final String EMAIL_SO_SUBMISSION_EMAIL_BODY = "You have a pending system account request for approval/rejection";
	public static final CharSequence EMAIL_SO_APPLICATION_LINK = "https://55samfrontendminc.apps.prod-iae.bsp.gsa.gov/workspace/requests/system/";

	// system account history constants------------------------------
	public static final String SAHISTORY_STATUS_EMAILAPPROVED = "System Account Email - Approved";
	public static final String SAHISTORY_STATUS_EMAILSUBMITTED = "System Account Email - Submitted";
	public static final String SAHISTORY_STATUS_APPLICATIONSUBMITTED = "System Account Application Submitted";
	public static final String SAHISTORY_STATUS_APPLICATIONREJECTED = "System Account Application Rejected";
	public static final String SAHISTORY_STATUS_APPLICATIONAPPROVED = "";

	public static final String SAHISTORY_MESSAGE_SUBMITTED_STATUS_SENT_TO = "Submitted Status Email Sent to";
	public static final String SAHISTORY_MESSAGE_APPROVAL_STATUS_SENT_TO = "Approval Status Email Sent to";
	public static final String SAHISTORY_MESSAGE_SUFFIX_APPLICATION_SUBMITTED = "submitted the System Account Application";
	public static final String SAHISTORY_MESSAGE_SUFFIX_APPLICATION_REJECTED = "rejected the System Account Application";

	// system account password
	public static final String SYSTEMACCOUNT_PASSWORD = "WhiteColor1!";

	// non fed
	public static final String EMAIL_NONFED = "nonfedgsaemail@yopmail.com";
	public static final String PASSWORD_NEW = fileUtilityreader.getProperty("password.new");
	// public static final String FILE_UPLOAD_PATH_FRONT_PIC =
	// fileUtilityreader.getProperty("fileuploadpath.frontpic");
	// public static final String FILE_UPLOAD_PATH_BACK_PIC =
	// fileUtilityreader.getProperty("fileuploadpath.backpic");
	public static final String USER_FED = "Fed";
	public static final String USER_NONFED = "Nonfed";
	public static final String NO_SECRET_KEY = "No Secret Key";
	public static final String SIGNUP_SECURITYLEVEL = "IAL1";

	private Constants() {
	}

	static {
		if (Constants.LOGINGOV_HOME_PAGE.contains("comp")) {
			EMAIL_ENV = "DEV ENV";
		} else if (Constants.LOGINGOV_HOME_PAGE.contains("minc")) {
			EMAIL_ENV = "TEST ENV";
		}

	}

	// ************************************************************************************************************************************
	public static final String MA_GMAIL_FED_USERNAME = "shah.raiaan@gsa.gov";
	public static final String MA_USERPASS = "";

	public static final String MA_GMAIL_NON_FED_USERNAME = "octo.crimson@gmail.com";
	public static final String MA_USERPASS_NONFED = "Octo@gmail0001";

	// system account email constants--
	public static final String MA_EMAIL_FROM_ENV = "This email was sent from TEST ENV";
	public static final String MA_EMAIL_MESSAGE_LERNINGCENTER = "If you have additional questions, please review the Learning Center";

	// System Account Manager email constants
	public static final String MA_EMAIL_SAM_ERROR_EMAIL = "If you feel this email was sent in error please contact your administrator.";
	public static final String MA_EMAIL_SAM_SUBMISSION_SUBJECT_LINE = "You have submitted a pending System Account application for initial review";
	public static final String MA_EMAIL_SAM_SUBMISSION_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by a System Account Administrator prior to a GSA Security Approver making a final determination.";
	public static final String MA_EMAIL_SAM_INITIAL_APPROVAL_SUBJECT_LINE = "Your System Account Application has received initial approval";
	public static final String MA_EMAIL_SAM_INITIAL_APPROVAL_EMAIL_BODY = "Your System Account application has received initial approval and can be viewed";
	public static final String MA_EMAIL_SAM_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for Permissions approval by an IAE PMO Administrator, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_SAM_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE = "The application will now be reviewed by a GSA Security Approver for a final determination, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_SAM_PERMISSIONS_APPROVAL_SUBJECT_LINE = "Your System Account application has received permissions approval";
	public static final String MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY = "Your System Account application has received permissions approval and can be viewed";
	public static final String MA_EMAIL_SAM_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for final approval by a GSA Security Approver, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_SAM_FINAL_APPROVAL_SUBJECT_LINE = "Your System Account application has received final approval";
	public static final String MA_EMAIL_SAM_FINAL_APPROVAL_EMAIL_BODY = "Your System Account application has received final approval and can be viewed";
	public static final String MA_EMAIL_SAM_REJECT_SUBJECT_LINE = "Your System Account application has been rejected";
	public static final String MA_EMAIL_SAM_REJECT_EMAIL_BODY = "Your System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_SAM_REJECT_EMAIL_BODY_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";

	// System Account Administrator email constants
	public static final String MA_EMAIL_SAA_ERROR_EMAIL = "If you feel this email was sent in error please contact your administrator.";
	public static final String MA_EMAIL_SAA_PENDING_SUBJECT_LINE = "You have a pending System Account application to review";
	public static final String MA_EMAIL_SAA_SUBMISSION_EMAIL_BODY = "A System Account application has been submitted for review.";
	public static final String MA_EMAIL_SAA_REJECT_SUBJECT_LINE = "You rejected a System Account application";
	public static final String MA_EMAIL_SAA_REJECT_EMAIL_BODY = "You have rejected a System Account application which can be viewed";
	public static final String MA_EMAIL_SAA_REJECT_EMAIL_BODY_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";
	public static final String MA_EMAIL_SAA_INITIAL_APPROVAL_SUBJECT_LINE = "A System Account Application has received initial approval";
	public static final String MA_EMAIL_SAA_INITIAL_APPROVAL_EMAIL_BODY = "A System Account application has received initial approval and can be viewed";
	public static final String MA_EMAIL_SAA_INITIAL_APPROVAL_TOPMOA_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for Permissions approval by an IAE PMO Administrator.";
	public static final String MA_EMAIL_SAA_INITIAL_APPROVAL_TOGSASA_EMAIL_BODY_SECONDLINE = "The application will now be reviewed by a GSA Security Approver for a final determination, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_SAA_PERMISSIONS_APPROVAL_SUBJECT_LINE = "A System Account application has received permissions approval";
	public static final String MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY = "A System Account application has received permissions approval and can be viewed";
	public static final String MA_EMAIL_SAA_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for final approval by a GSA Security Approver, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_SUBJECT_LINE = "Your System Account application has received permissions approval";
	public static final String MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY = "Your System Account application has received permissions approval and can be viewed";
	public static final String MA_EMAIL_SAA_SUBMITTER_PERMISSIONS_APPROVAL_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for final approval by a GSA Security Approver, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_SAA_FINAL_APPROVAL_SUBJECT_LINE = "A System Account application has received final approval";
	public static final String MA_EMAIL_SAA_FINAL_APPROVAL_EMAIL_BODY = "A System Account application has received final approval and can be viewed";
	public static final String MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_SUBJECT_LINE = "Your System Account application has received final approval";
	public static final String MA_EMAIL_SAA_SUBMITTER_FINAL_APPROVAL_EMAIL_BODY = "Your System Account application has received final approval and can be viewed";
	public static final String MA_EMAIL_SAA_REJECT_SUBJECT_LINE_RECEIVE = "A System Account application has been rejected";
	public static final String MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE = "A System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_SAA_REJECT_EMAIL_BODY_RECEIVE_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";
	public static final String MA_EMAIL_SAA_ACCOUNT_CREATION_FORPMOA_SUBJECT_LINE = "You have submitted a pending System Account application for permissions approval";
	public static final String MA_EMAIL_SAA_ACCOUNT_CREATION_FORPMOA_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by an IAE PMO Administrator for permissions approval.";
	public static final String MA_EMAIL_SAA_ACCOUNT_CREATION_FORGSASA_SUBJECT_LINE = "You have submitted a pending System Account application for approval";
	public static final String MA_EMAIL_SAA_ACCOUNT_CREATION_FORGSASA_EMAIL_BODY = "ou have submitted a System Account application which will be reviewed by a GSA Security Approver for a final determination.";
	public static final String MA_EMAIL_SAA_FINAL_REJECT_SUBJECT_LINE = "A System Account application has been rejected";
	public static final String MA_EMAIL_SAA_FINAL_REJECT_EMAIL_BODY = "A System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_SUBJECT_LINE = "Your System Account application has been rejected";
	public static final String MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY = "Your System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_SAA_SUBMITTER_FINAL_REJECT_EMAIL_BODY_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";

	// IAE PMO Administrator email constants
	public static final String MA_EMAIL_PMOA_ERROR_EMAIL = "If you feel this email was sent in error please contact your administrator.";
	public static final String MA_EMAIL_PMOA_PENDING_SUBJECT_LINE = "A System Account application has been submitted for permissions review";
	public static final String MA_EMAIL_PMOA_SUBMISSION_EMAIL_BODY = "A System Account application has been submitted for permissions review";
	public static final String MA_EMAIL_PMOA_APPROVED_SUBJECT_LINE = "A System Account application has received permissions approval";
	public static final String MA_EMAIL_PMOA_APPROVED_EMAIL_BODY = "A System Account application has received permissions approval and can be viewed";
	public static final String MA_EMAIL_PMOA_APPROVED_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for final approval by a GSA Security Approver.";
	public static final String MA_EMAIL_PMOA_FINAL_APPROVAL_SUBJECT_LINE = "A System Account application has received final approval";
	public static final String MA_EMAIL_PMOA_FINAL_APPROVAL_EMAIL_BODY = "A System Account application has received final approval and can be viewed";
	public static final String MA_EMAIL_PMOA_REJECT_SUBJECT_LINE = "A System Account application has been rejected";
	public static final String MA_EMAIL_PMOA_REJECT_EMAIL_BODY = "A System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_PMOA_REJECT_EMAIL_BODY_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";
	public static final String MA_EMAIL_PMOA_FINAL_REJECT_SUBJECT_LINE = "A System Account application has been rejected";
	public static final String MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY = "A System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_PMOA_FINAL_REJECT_EMAIL_BODY_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";

	// GSA Security Approver email constants
	public static final String MA_EMAIL_GSAPPROVER_PENDING_SUBJECT_LINE = "A System Account application has been submitted for approval/rejection";
	public static final String MA_EMAIL_GSAPPROVER_PENDING_EMAIL_BODY = "A System Account application has been submitted for approval/rejection.";
	public static final String MA_EMAIL_GSAPPROVER_APPROVAL_SUBJECT_LINE = "A System Account application has been approved";
	public static final String MA_EMAIL_GSAPPROVER_APPROVAL_EMAIL_BODY = "A System Account has been approved and can be viewed";
	public static final String MA_EMAIL_GSAPPROVER_REJECT_SUBJECT_LINE = "A System Account application has been rejected";
	public static final String MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY = "A System Account application has been rejected and can be viewed";
	public static final String MA_EMAIL_GSAPPROVER_REJECT_EMAIL_BODY_SECONDLINE = "The application will now be found in draft status for resubmission at a later date or deletion.";

	// System Account Non-Fed constants
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_SUBJECT_LINE = "You have submitted a pending System Account application for approval";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by a GSA Security Approver for a final determination.";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_TOPMOA_SUBJECT_LINE = "You have submitted a pending System Account application for permissions approval";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_CREATION_TOPMOA_EMAIL_BODY = "You have submitted a System Account application which will be reviewed by an IAE PMO Administrator for permissions approval.";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_SUBJECT_LINE = "Your System Account application has received permissions approval";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY = "Your System Account application has received permissions approval and can be viewed";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_PERMISSIONSAPPROVAL_EMAIL_BODY_SECONDLINE = "The application will now be reviewed for final approval by a GSA Security Approver, and you will be notified of any change in status via an additional email.";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_SUBJECT_LINE = "Your System Account application has received final approval";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_APPROVAL_EMAIL_BODY = "Your System Account application has received final approval and can be viewed";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_SUBJECT_LINE = "Your System Account application has been rejected";
	public static final String MA_EMAIL_NONFED_SYSTEMACCOUNT_REJECTION_EMAIL_BODY = "Your System Account application has been rejected and can be viewed";

	// System Account comments
	public static final String MA_SAM_ADD_COMMENT = "The comment has been added by System Account Manager";
	public static final String MA_SAA_ADD_COMMENT = "The comment has been added by System Account Administrator";
	public static final String MA_PMOA_ADD_COMMENT = "The comment has been added by IAE PMO Administrator";
	public static final String MA_GSASA_ADD_COMMENT = "The comment has been added by GSA Security Approver";

	public static final String MA_ADD_DECISIONCOMMENT = "Decision comment";
	public static final String SCENARIO_VIDEO_FILE_PATH = "C:\\RM\\project\\selenium-rm\\rolemanagement-cucumber-serenity\\src\\test\\resources\\videos";
	public static final boolean VIDEO_RECORD_ON = false;
	public static final String SCREENSHOTS_FILEPATH = "C:\\RM\\project\\selenium-rm\\rolemanagement-cucumber-serenity\\src\\test\\resources\\screenshots.png";
	public static final String ORG_PUBLIC_BUILDING_SERVICE = "PUBLIC BUILDINGS SERVICE";

	// Role History Status
	public static final String ROLEHISTORY_STATUS_ROLE_ASSIGNED = "Role Assigned";
	public static final String ROLEHISTORY_STATUS_ROLE_REMOVED = "Role Removed";
	public static final String ROLEHISTORY_STATUS_ROLE_UPDATED = "Role Updated";
	public static final CharSequence EMAIL_APPROVED_ORGANIZATIONS = "Approved Organizations:";
	public static final CharSequence EMAIL_APPROVED_ROLE = "Approved Role";
	public static final CharSequence EMAIL_APPROVED_DOMAINS = "Approved Domains";
	public static final CharSequence EMAIL_SUPERVISOR = "supervisor";
	public static final CharSequence EMAIL_PERMISSIONS = "permissions";
	public static final String GO_INTO_EDITPERMISSIONS = "EDIT PERMISSIONS";
	public static final String ROLE_ADMIN = "Administrator";
	public static final String ROLE_OPPADMIN = "Opportunities Administrator";
	public static final String ROLE_IAM_PMO_ADMIN = "IAE PMO Administrator";
	public static final String ROLE_FSD_ADMINISTRATOR = "FSD Administrator";
	public static final String ROLE_IAM_ADMINISTRATOR = "IAM Administrator";
	public static final String ROLE_CONTENTMANAGER = "Content Manager";
	public static final String ROLE_TIER2FUNCTIONALHELPDEST = "Tier 2 Functional Help Desk";
	public static final String ROLE_TIER2TECHNICALHELPDESK = "Tier 2 Technical Help Desk";
	public static final String ROLE_TIER1HELPDESK = "Tier 1 Help Desk";
	public static final String ROLE_GSADATAAPPROVER = "GSA Data Approver";
	public static final String ROLE_ADMINSTRATORALLDOMAINS = "Administrator All Domains";
	public static final String ROLE_SAMPMOADMINISTRATOR = "SAM PMO Administrator";
	public static final String ROLE_TIER3HELPDESK = "Tier 3 Help Desk (Remove)";
	public static final String ROLE_AGENCY_ROLES_ADMINISTRATOR_REMOVE = "Agency Roles Administrator (Remove)";
	public static final String ROLE_ENTITYMANAGEMENTDATAACCESS_SYSTEMONLY = "Entity Management Data Access System Only";
	public static final String ROLE_OFFICEREGISTRATION_REPRESENTATIVE = "Office Registration Representative";
	public static final String ROLE_FOUOANDFAPIIS = "FOUO and FAPIIS";
	public static final String ROLE_AGENCYROLESADMINISTRATOR = "Agency Roles Administrator";
	public static final String ROLE_FOUOENTITYMANAGEMENT_DATAVIEWER = "FOUO Entity Management Data Viewer";
	public static final String ROLE_AGENCY_ADMINISTRATOR_ENTITYMANAGEMENT = "Agency Administrator Entity Management";
	public static final String ROLE_FOUOANDEXECUTIVECOMPENSATION_DATAVIEWER = "FOUO and Executive Compensation Data Viewer";
	public static final String ROLE_SENSITIVEENTITYMANAGEMENT = "Sensitive Entity Management Data Viewer";
	public static final String ROLE_TIER3HELPDESK_ENTITY_REGISTRATION = "Tier 3 Help Desk";
	public static final String ROLE_AACUSER = "Aac user";
	public static final String ROLE_DEPARTMENT_ADMINISTRATOR = "Department Administrator";
	public static final String ROLE_OFFICE_ADMINISTRATOR = "Office Administrator";
	
	//brower config
	public static final boolean INCOGNITO_ON = false;
	public static final String ROLE_AGENCY_ADMINISTRATOR_EXCLUSIONS = "Agency Administrator Exclusions";
	public static final String ROLE_AGENCYEXCLUSIONS_REPRESENTATIVE = "Agency Exclusions Representative";
	public static final String ROLE_AGENCY_ROLES_ADMINISTRATOR = "Agency Roles Administrator";
	public static final String ROLE_DATA_ENTRY_REMOVE = "Data Entry (REMOVE)";
	
	
	
	
	
	
	
	
	
	
	

	// ************************************************************************************************************************************

}