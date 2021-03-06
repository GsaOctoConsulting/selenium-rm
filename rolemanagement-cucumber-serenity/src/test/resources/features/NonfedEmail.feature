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

@2
Scenario: both the nonfed admin and the unregistered nonfed user should get emails when a role invite is made   
	Given _2nre nonfed admin logs in 
	And _2nre nonfed admin navigates to role invite page 
	When _2nre admin invites a nonfed unregistered user for viewer role in the admins domain 
	Then _2nre admin should receive an email about the role invite 
	And _2nre the unregistered user should also get an email about the role invite
	
@3
Scenario: both the nonfed admin and the nonfed user should get emails when an admin updates a role   
	Given _3nre nonfed admin logs in 
	When _3nre nonfed admin looks up a data entry user in contract opp and updates to viewer role   
	Then _3nre admin should receive an email about the role update 
	And _3nre nonfed user should also receive an email about the role update 

@4
Scenario: both the spaad and the nonfed user should get emails when an admin removes a role   
	Given _4nre spaad logs in 
	When _4nre spaad looks up a data entry user in contract opp and removes the role   
	Then _4nre spaad should receive an email about the role removal 
	And _4nre nonfed user should also receive an email about the role removal

@5
Scenario: both the spaad and the nonfed user should get emails when spaad assigns a role   
	Given _5nre spaad logs in 
	When _5nre spaad looks up a no role nonfed user and assigns data entry role   
	Then _5nre spaad should receive an email about the role assignment
	And _5nre nonfed user should also receive an email about the role assignment

@6
Scenario: both the admin and the nonfed user should get emails when the nonfed user requests a role   
	Given _6nre no role nonfed user logs in 
	When _6nre nonfed users requests data entry role   
	Then _6nre the user should receive an email about the role request

@7
Scenario: both the admin and the nonfed user should get emails when the nonfed users requests is approved   
	Given _7nre no role nonfed user logs in 
	And _7nre nonfed users requests data entry role in entity registration   
	When _7nre entity registration admin approves the request
	Then _7nre the admin should receive an email about the approval
	And _7nre the user should also receive an email about the approval

@8
Scenario: both the admin and the nonfed user should get emails when the nonfed users requests is rejected   
	Given _8nre no role nonfed user logs in 
	And _8nre nonfed users requests data entry role in entity registration   
	When _8nre entity registration admin rejects the request
	Then _8nre the admin should receive an email about the role rejection
	And _8nre the user should also receive an email about the role rejection

 
	
	
