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