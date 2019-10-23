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
@SystemAccount 
Feature: Login functionality 
	Description:  The purpose of this feature is to test functionalities
related to system accounts

@1 @SystemAccountAdmin 
Scenario: system account admin should be able to create an account 
	Given _1 user logs in as system account admin 
	And _1 user navigates to system account directory page 
	And _1 user enters all the system information 
	And _1 user enters all the organization info 
	And _1 user enters permissions info 
	And _1 user enters security info 
	And _1 user enters authorization info 
	Then _1 the newly created account should show up on the system account directory page 
	
@2 @SystemAccountManager 
Scenario: system account manager should be able to create an account 
	Given _2 user logs in as system account manager 
	And _2 user navigates to system account directory page 
	And _2 user enters all the system information 
	And _2 user enters all the organization info 
	And _2 user enters permissions info 
	And _2 user enters security info 
	And _2 user enters authorization info 
	Then _2 the newly created account should show up on the system account directory page 
	
@3 @GsaSecurityApprover 
Scenario: gsa security approver should not be able to create an account 
	Given _3 user logs in as gsa security approver 
	And _3 user navigates to system account directory page 
	Then _3 user should not see the create account button 
	
@4 @GsaSecurityApprover 
Scenario: gsa security approver should be able to reject request 
	Given _4 user logs in as system account manager 
	And _4 user navigates to system account directory page 
	And _4 user enters all the system information 
	And _4 user enters all the organization info 
	And _4 user enters permissions info 
	And _4 user enters security info 
	And _4 user enters authorization info 
	Then _4 the newly created account should show up on the system account directory page 
	And _4 gsa security approver logs in 
	Then _4 user should be able to see the account and reject the account 
	
@5 @GsaSecurityApprover 
Scenario: account creator shoiuld be able to add comments that would also be visible to security approver 
	Given _5 user logs in as system account manager 
	And _5 user navigates to system account directory page 
	And _5 user enters all the system information 
	And _5 user enters all the organization info 
	And _5 user enters permissions info 
	And _5 user enters security info 
	And _5 user enters authorization info 
	Then _5 under the review tab the user should be able to enter comments 
	When _5 gsa security approver logs in 
	Then _5 user should be able to see the new account and view the comments posted 
	
@6 @SystemAccountAdmin 
Scenario: comments for system accounts should only be available after system info has been correctly filled out 
	Given _6 user logs in as system account admin 
	And _6 _user navigates to system account directory page 
	When _6 user clicks on review tab then comment text box should not be available 
	Then _6 user goes back to fill out system information and clicks next 
	Then _6 user should see the comment text box under the edit tab 
	
@7 @SystemAccountManager 
Scenario: entering empty comments will throw an error message 
	Given _7 user logs in as system account manager 
	And _7 user navigates to system account directory page 
	And _7 user enters all system information and clicks next 
	And _7 user goes to review tab 
	When _7 user enters blank spaces and hits enter 
	Then _7 error message should pop up 
	
@8 @SystemAccountManager 
Scenario: comments for system accounts should only be available after system info has been correctly filled out 
	Given _8 user logs in as system account manager 
	And _8 _user navigates to system account directory page 
	When _8 user clicks on review tab then comment text box should not be available 
	Then _8 user goes back to fill out system information and clicks next 
	Then _8 user should see the comment text box under the edit tab 
	
@9 @SystemAccountAdmin 
Scenario: entering empty comments will throw an error message 
	Given _9 user logs in as system account admin 
	And _9 user navigates to system account directory page 
	And _9 user enters all system information and clicks next 
	And _9 user goes to review tab 
	When _9 user enters blank spaces and hits enter 
	Then _9 error message should pop up 
	
@10 @SystemAccountManager 
Scenario: system account manager should only see accounts opened by him or her 
	Given _10 user logs in as system account manager 
	And _10 user navigates to system account directory page 
	Then _10 user should only see accounts opened by them 
	
	
@11 @SystemAccountAdmin 
Scenario: system account should be able to see all accounts 
	Given _11 user logs in as system account admin 
	And _11 user navigates to system account directory page 
	Then _11 user should accounts opened by system managers and themselves 
	
	
@12 @GsaSecurityApprover 
Scenario: gsa security approver should be able to publish accounts by system account admin 
	Given _12 user logs in as system account admin 
	And _12 user navigates to system account directory page 
	And _12 user enters all the system information 
	And _12 user enters all the organization info 
	And _12 user enters permissions info 
	And _12 user enters security info 
	And _12 user enters authorization info 
	
	When _12 gsa security approver logs into workspace 
	And _12 gsa security approver goes to system account page 
	Then _12 gsa security approver should be able to publish the account 
	
	
	# filter verifications---------------------------------------
@13 
Scenario: system account admin, system manager, gsa security approver and nonfed users should only see correct number and type of filters 
	Given _13 user logs in as gsa security approver 
	Then _13 gsa security approver should see correct number and type of filters on system account page 
	When _13 user logs in as system account admin 
	Then _13 system account admin see should correct number and type of filters on system account page 
	When _13 user logs in as system manager 
	Then _13 system manager should see should correct number and type of filters on system account page 
	When 13_user logs in as nonfed user 
	Then _13 nonfed user should see should correct number and type of filters on system account page 
	#-------------------------------------------------------------------------------------------------------------
	
@14 @IntegrationTest 
Scenario: system account admin should be able to deactivate their published system accounts
	Given _14 user logs in as system account admin 
	And _14 user navigates to system account directory page 
	And _14 user enters all the system information 
	And _14 user enters all the organization info 
	And _14 user enters permissions info 
	And _14 user enters security info 
	And _14 user enters authorization info 
	Then _14 the newly created account should show up on the system account directory page 
	When _14 iae admin logs in 
	Then _14 iam admin should be able to approve the permission
	
	When _14 gsa security approver logs in 
	And _14 gsa security approver navigates to system accounts directory page 
	Then _14 gsa security approver should be able to approve the request by system account admin 
	When _14 system account admin logs in again 
	And _14 admin goes to system account directory page 
	And _14 admin generates api key 
	Then _14 they should be able to deactivate their system account 
	
@15 
Scenario: system account manager should be able to deactivate their published system accounts
	Given _15 user logs in as system account manager 
	And _15 system manager navigates to system account directory page 
	And _15 system manager enters all the system information 
	And _15 system manager enters all the organization info 
	And _15 system manager enters permissions info 
	And _15 system manager enters security info 
	And _15 system manager enters authorization info 
	Then _15 the newly created account request should show up on the system account directory page 
	
	When _15 system accound admin logs in 
	And _15 system account admin navigates to system account directory page 
	Then _15 admin should be able to change the request status to pending approval 
	When _15 gsa security approver logs in 
	And _15 gsa security approver navigates to system accounts directory page 
	Then _15 gsa security approver should be able to approve the request by system account manager 
	
	When _15 system account manager logs in again 
	And _15 manager goes to system account directory page 
	And _15 manager generates api key 
	Then _15 they should be able to deactivate their system account 
	
	
@16 
Scenario: gsasecurity approver should be able to deactivate fed published system accounts
	Given _16 user logs in as system account admin 
	And _16 user navigates to system account directory page 
	And _16 user enters all the system information 
	And _16 user enters all the organization info 
	And _16 user enters permissions info 
	And _16 user enters security info 
	And _16 user enters authorization info 
	Then _16 the newly created account should show up on the system account directory page 
	When _16 gsa security approver logs in 
	And _16 gsa security approver navigates to system accounts directory page 
	Then _16 gsa security approver should be able to approve the request by system account admin 
	Then _16 gsa security approver should be able to deactivate the published system account 
	And _16 admin goes to system account directory page 
	And _16 admin generates api key 
	Then _16 they should be able to deactivate their system account 
	
	
@17 @IntegrationTest @S1 
Scenario: system manager account approval flow with account history validation and account deactivation 
	Given _17saaccount user logs in as system account manager 
	And _17saaccount user navigates to system account directory page 
	And _17saaccount user enters all the system information 
	And _17saaccount user enters all the organization info 
	And _17saaccount user enters permissions info 
	And _17saaccount user enters security info 
	And _17saaccount user enters authorization info 
	And _17saaccount the newly created account should show up on the system account directory page 
	Then _17saaccount the system manager should see the account history updated 
	
	
	When _17saaccount system admin changes the request to pending approval 
	Then _17saaccount the system admin should see the account history updated 
	
	When _17saaccount gsa security approver logs in 
	And _17saaccount gsa security approver approves the reviewed request 
	Then _17saaccount the gsa security approver should see the account history updated 
	When _17saaccount user logs backin in as system account manager 
	Then _17saacount they should be able to deactivate their published system account 	
	
@18 @IntegrationTest @S1 
Scenario: system manager account rejection flow with account history validation 
	Given _18saaccount user logs in as system account manager 
	And _18saaccount user navigates to system account directory page 
	And _18saaccount user enters all the system information 
	And _18saaccount user enters all the organization info 
	And _18saaccount user enters permissions info 
	And _18saaccount user enters security info 
	And _18saaccount user enters authorization info 
	And _18saaccount the newly created account should show up on the system account directory page 
	Then _18saaccount the system manager should see the account history updated 
	When _18saaccount system admin rejects the request 
	Then _18saaccount the system admin should see the account history updated 
	
@19 @IntegrationTest @S1 
Scenario: nonfed system account approval flow with account history validation permission approval and account deactivation 
	Given _19saaccount user logs in as nonfed user 
	And _19saaccount user navigates to system account directory page 
	And _19saaccount user enters all the system information 
	And _19saaccount user enters all the organization info 
	And _19saaccount user enters permissions info 
	And _19saaccount user enters security info 
	And _19saaccount user enters authorization info 
	And _19saaccount the newly created account should show up on the system account directory page 
	Then _19saaccount the nonfed user should see the account history updated 
	When _19saaccount gsasecurity approver approves the request 
	Then _19saaccount the gsasecurity approver should see the account history updated 
	When _19saaccount user logs backin in as nonfed user 
	Then _19saaccount they should be able to deactivate their published system account 
	
@20 @IntegrationTest @S1 
Scenario: nonfed system account rejection flow with account history validation 
	Given _20saaccount user logs in as nonfed user 
	And _20saaccount user navigates to system account directory page 
	And _20saaccount user enters all the system information 
	And _20saaccount user enters all the organization info 
	And _20saaccount user enters permissions info 
	And _20saaccount user enters security info 
	And _20saaccount user enters authorization info 
	And _20saaccount the newly created account should show up on the system account directory page 
	Then _20saaccount the nonfed user should see the account history updated 
	When _20saaccount gsasecurity approver rejects the request 
	Then _20saaccount the gsasecurity approver should see the account history updated 
	
@21 @IntegrationTest @S1 
Scenario: fed user of a published system account should be able to set reset and recover password 
	Given _21saaccount user logs in as system admin 
	And _21saaccount user navigates to system account directory page 
	And _21saaccount user enters all the system information 
	And _21saaccount user enters all the organization info 
	And _21saaccount user enters permissions info 
	And _21saaccount user enters security info 
	And _21saaccount user enters authorization info 
	And _21saaccount the newly created account should show up on the system account directory page 
	When _21saaccount gsasecurity approver approves the request 
	Then _21saaccount system admin should be able to set a password for the published account 
	And _21saaccount system admin should be able to reset the password 
	And _21saccount system admin should be able to set the password through forgot password link 
	
@22 @IntegrationTest @S1 
Scenario: nonfed user of a published system account should be able to set reset and recover password 
	Given _22saaccount user logs in as nonfed user 
	And _22saaccount user navigates to system account directory page 
	And _22saaccount user enters all the system information 
	And _22saaccount user enters all the organization info 
	And _22saaccount user enters permissions info 
	And _22saaccount user enters security info 
	And _22saaccount user enters authorization info 
	And _22saaccount the newly created account should show up on the system account directory page 
	When _22saaccount gsasecurity approver approves the request 
	Then _22saaccount nonfed user should be able to set a password for the published account 
	And _22saaccount nonfed user should be able to reset the password 
	And _22saccount nonfed user should be able to set the password through forgot password link 
	
@23 @IntegrationTest @S1
Scenario: user should be able to search for system accounts using special characters 
	Given _23saaccount user logs in as system manager 
	And _23saaccount user navigates to system account directory page 
	When _23saaccount user searches for sytem accounts using special characters 
	Then _23saaccount the filtered accounts displayed should contain those letters
	
@24 @IntegrationTest @S1
Scenario: nonfed system account approval flow with permission approval
	Given _24saaccount user logs in as nonfed user 
	And _24saaccount user navigates to system account directory page 
	And _24saaccount user enters all the system information 
	And _24saaccount user enters all the organization info 
	And _24saaccount user enters permissions info for sensitive read and write 
	And _24saaccount user enters security info 
	And _24saaccount user enters authorization info 
	And _24saaccount the newly created account should show up on the system account directory page 
	When _24 iae admin logs in 
	Then _24 iam admin should be able to approve the permission
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	