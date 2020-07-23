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
@EmailVariance
Feature: Email variance in login.gov functionality 
	Description:  The purpose of this feature is to test email addition and removal in login dot gov

@1 
Scenario: user with federal email domain should be able to add additional fed email for the account in the same fed org 
	Given _1ev a no role user logs into login dot gov 
	And _1ev user register a second email in the account which was not associated previously
	When _1ev user goes to sam portal 
	Then _1ev user should be able to login using new id
	And _1ev user should receives additional modals before landing into workspace 
	
	
	