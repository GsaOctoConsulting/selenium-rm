#Author: shah.raiaan@gsa.gov
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@SystemAccountEmail 
Feature: System Account Email functionality 
	Description:  The purpose of this feature is to test email validations for system account flows

@1 
Scenario: system manager as account creator and system admin should get an email for the account creation 
	Given _1saemail_ user logs in as system account manager 
	And _1saemail_ user navigates to system account directory page 
	And _1saemail_ user enters all the system information 
	And _1saemail_ user enters all the organization info 
	And _1saemail_ user enters permissions info 
	And _1saemail_ user enters security info 
	And _1saemail_ user enters authorization info 
	Then _1saemail_ the system manager should get an email notification 
	And _1saemail_ the system admin shold also get an email notification 
	
	And _1saemail_ the newly created account should show up on the system account directory page 
	
@2 #1 on confluence doc
Scenario: system admin as system account creator and security official should get email for the account creation 
	Given _2saemail_ user logs in as system account admin 
	And _2saemail_ user navigates to system account directory page 
	And _2saemail_ user enters all the system information 
	And _2saemail_ user enters all the organization info 
	And _2saemail_ user enters permissions info 
	And _2saemail_ user enters security info 
	And _2saemail_ user enters authorization info 
	Then _2saemail_ the system admin should get an email notification
	And _2saemail_ the security official should get an email notification 
	And _2saemail_ the newly created account should show up on the system account directory page 
	
@3 
Scenario: nonfed system account creator should get an email for the account submission 
	Given _3saemail_ user logs in as nonfed user 
	And _3saemail_ user navigates to system account directory page 
	And _3saemail_ user enters all the system information 
	And _3saemail_ user enters all the organization info 
	And _3saemail_ user enters permissions info 
	And _3saemail_ user enters security info 
	And _3saemail_ user enters authorization info 
	And _3saemail_ the newly created account should show up on the system account directory page 
	Then _3saemail_ the nonfed user should get an email notification 
	
	
@4 @IntegrationTest @S1 #5 on confluence doc
Scenario: system manager account approval flow 
	Given _4saemail_ user logs in as system account manager 
	And _4saemail_ user navigates to system account directory page 
	And _4saemail_ user enters all the system information 
	And _4saemail_ user enters all the organization info 
	And _4saemail_ user enters permissions info 
	And _4saemail_ user enters security info 
	And _4saemail_ user enters authorization info 
	Then _4saemail_ the system manager should get an email notification 
	And _4saemail_ the system admin shold also get an email notification 
	
	And _4saemail_ the newly created account should show up on the system account directory page 
	When _4saemail_ system admin changes the request to pending approval 
	
	Then _4saemail_ the system admin should get approved email notification 
	Then _4saemail_ system manager should get intial approval email notification 
	
	When _4saemail_ gsa security approver logs in 
	And _4saemail gsa security approver approves the reviewed request 
	Then _4saemail gsa security approver gets an approval email notification 
	And _4saemail system manager should get final approval email notification 
	And _4saemail system admin gets final approval email notification 
	Then _4saemail the published account can now be deleted 
	
	
@5 #1 on confluence doc
Scenario: system admin account approval flow 
	Given _5saemail_ user logs in as system admin 
	And _5saemail_ user navigates to system account directory page 
	And _5saemail_ user enters all the system information 
	And _5saemail_ user enters all the organization info 
	And _5saemail_ user enters permissions info 
	And _5saemail_ user enters security info 
	And _5saemail_ user enters authorization info 
	Then _5saemail_ the system admin and the gsasecurityapprover should get pending email notification 
	And _5saemail_ the newly created account should show up on the system account directory page 
	When _5saemail_ gsa security approver logs in 
	And _5saemail gsa security approver approves the reviewed request 
	Then _5saemail gsa security approver gets an approval email notification 
	And _5saemail system admin gets final approval email notification 
	Then _5saemail the published account can now be deleted 
	
	
@6 @IntegrationTest @S1 #6 on confluence doc
Scenario: sytem managers account rejection by gsapprover 
	Given _6saemail_ user logs in as system account manager 
	And _6saemail_ user navigates to system account directory page 
	And _6saemail_ user enters all the system information 
	And _6saemail_ user enters all the organization info 
	And _6saemail_ user enters permissions info 
	And _6saemail_ user enters security info 
	And _6saemail_ user enters authorization info 
	Then _6saemail_ the newly created account should show up on the system account directory page 
	Then _6saemail_ system accound admin logs in 
	And __6saemail_ system account admin navigates to system account directory page 
	And __6saemail_ admin should be able to change the request status to pending approval 
	When _6saemail_ gsa security approver logs in 
	And _6saemail gsa security approver rejects the reviewed request 
	Then _6saemail gsa security approver gets an rejection email notification 
	And _6saemail system manager should get rejection email notification 
	And _6saemail system admin gets rejection email notification 
	
@7 # 7 on confluence doc
Scenario: sytem managers account rejection by system admin 
	Given _7saemail_ user logs in as system account manager 
	And _7saemail_ user navigates to system account directory page 
	And _7saemail_ user enters all the system information 
	And _7saemail_ user enters all the organization info 
	And _7saemail_ user enters permissions info 
	And _7saemail_ user enters security info 
	And _7saemail_ user enters authorization info 
	Then _7saemail_ the newly created account should show up on the system account directory page 
	When _7saemail_ system accound admin logs in 
	And __7saemail_ system account admin navigates to system account directory page 
	And __7saemail_ admin rejects the request the request 
	Then _7saemail_ both the manger and admin should get account rejection email 
	Then _7saemail system admin deletes the application 
	
@8 #2 & #3 (delete email not implemented) on confluence doc
Scenario: sytem admins account rejection by gsasecurityapprover 
	Given _8saemail_ user logs in as system account admin 
	And _8saemail_ user navigates to system account directory page 
	And _8saemail_ user enters all the system information 
	And _8saemail_ user enters all the organization info 
	And _8saemail_ user enters permissions info 
	And _8saemail_ user enters security info 
	And _8saemail_ user enters authorization info 
	Then _8saemail_ the system admin and the gsasecurityapprover should get pending email notification 
	Then _8saemail_ the newly created account should show up on the system account directory page 
	When _8saemail_ gsasecurityapprover logs in 
	And __8saemail_ gsasecurityapprover navigates to system account directory page 
	And __8saemail_ gsasecurityapprover rejects the request the request 
	Then _8saemail_ both the admin and gsasecurityapprover should get account rejection email 
	Then _8saemail when system account logs back in they should see status of the application in draft 
	
@9 @IntegrationTest @S1
Scenario: nonfed system account approval by gsasecurity approver 
	Given _9saemail_ user logs in as nonfed user 
	And _9saemail_ user navigates to system account directory page 
	And _9saemail_ user enters all the system information 
	And _9saemail_ user enters all the organization info 
	And _9saemail_ user enters permissions info 
	And _9saemail_ user enters security info 
	And _9saemail_ user enters authorization info 
	Then _9saemail_ the newly created account should show up on the system account directory page 
	When _9saemail_ gsa security approver logs in 
	And _9saemail gsa security approver approves the request 
	And _9saemail the nonfed user gets a final approval notification 
	
@10 @IntegrationTest @S1 #9 on confluence doc
Scenario: nonfed system account rejection by gsasecurity approver 
	Given _10saemail_ user logs in as nonfed user 
	And _10saemail_ user navigates to system account directory page 
	And _10saemail_ user enters all the system information 
	And _10saemail_ user enters all the organization info 
	And _10saemail_ user enters permissions info 
	And _10saemail_ user enters security info 
	And _10saemail_ user enters authorization info 
	Then _10saemail_ the newly created account should show up on the system account directory page 
	When _10saemail_ gsa security approver logs in 
	And _10saemail gsa security approver rejects the request 
	Then _10saemail gsa security approver gets a rejection email notification 
	And _10saemail the nonfed user gets a rejection email notification 
	
	
	
	
