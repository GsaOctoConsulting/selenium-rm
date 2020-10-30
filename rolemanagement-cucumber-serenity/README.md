Project Title - System Accounts RM Automation

Getting Started -
The examples below should help running tests and what prerequisites
are needed to be setup before. Also refers to the javadoc on
specific classes and methods. The main browser configuration
is in the LaunchBrowserUtil.java file.
Also refer to the resource folder for the features which contain
the scenarios available.

Prerequisites
- script for email inbox needs to be tailored for the user's inbox.
- setup accounts, save secretkeys and ids in ConstantsAccounts.java file
(currently the accounts included in the file are used by octo, new accounts
should be created and replaced in the file)

Examples: -
1) to Run scenarios, use cucummber Tags in the format (eg. "@FeatureName, @scenario#")
in the CucumberTestSuite class

2) to generate reports, use maven command, -Dverify=CucumberTestSuite clean verify
Note - the project supported automation through various requirements
over a period of time and so some of the scenarios are deprecated but
they can be easily edited to be uptodate. The ones tagged @IntegrationTest
are uptodate.


More Details - ----------------------------
 - Some legacy/inactive scenarios are present in the feature files for reference.
   
   
-    The groups of tagged scnearios that are uptodate and active are listed below.
   @SmokeTest
   @Login,@IntegrationTest
   @G1, @IntegrationTest
   @G2, @IntegrationTest
   @AccountDetails
   @Email,@IntegrationTest
   @Nonfed
   @NonfedRoleInvite
   @T1Workspace
   @NonfedEmail
   
   * the SAM.gov accounts that are manually setup act as the test-data for
   the above mentioned test groups. Therefore before running each test group
   the accounts in use should be checked for their role and any pending requests
   / pending invites should be removed before executing the tests. This can be 
   done by loggin in as SPAAD and using user directory to check for the accounts. 
   
 - Please note the following features exist for reference
 	SystemAccount, SystemAccountEmail, MN_DeleteUpload
 	MN_SystemAccount_UploadFile, MN_SystemAccount_UploadFileReorder
 	AAM* (all features starting with AAM)
 	(responsibility have been transferred to Gold Team)
 	
    
 - Configuration File - LaunchBrowserUtil * also contains helper methods in specific flows
 - Every loggging in functionality will be done through SignInUtility class
 - SignUp 
 - utility.properties file will hold the counter values used for signup for new users


