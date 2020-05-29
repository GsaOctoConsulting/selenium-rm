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
## (Comments)
#Sample Feature Definition Template
@NonfedEmail 
Feature: nonfed Email functionality 
	Description:  The purpose of this feature is to test email validations for nonfed

@1 @IntegrationTest 
Scenario: role requester and supervisor should receive email when request is submitted 
	Given _1nfemail a no role user logs 
	And _1nfemail user requests assitance user role in assistance listing 
	Then _1nfemail user should receive an email with the proper message 
	Then _1nfemail supervisor should also receive an email message 

@2
Scenario: both the nonfed admin and the unregistered nonfed user should get emails when a role invite is made   
	Given _2nre nonfed admin logs in 
	And _2nre nonfed admin navigates to role invite page 
	When _2nre admin invites a nonfed unregistered user for viewer role in the admins domain 
	Then _2nre admin should receive an email about the role invite 
	And _2nre the unregistered user should also get an email about the role invite
 
	
	
