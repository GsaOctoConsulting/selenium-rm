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
Scenario: 
	account creator shoiuld be able to add comments that would also be visible to security approver 
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
Scenario: 
	comments for system accounts should only be available after system info has been correctly filled out 
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
Scenario: 
	comments for system accounts should only be available after system info has been correctly filled out 
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
	
	
	
	