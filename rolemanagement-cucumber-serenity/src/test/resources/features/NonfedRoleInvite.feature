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
@NonfedRoleInvite 
Feature: nonfed roleinvite functionality 
	Description:  The purpose of this feature is to test nonfed role invite related scenarios

@1
Scenario: nonfed admin should be able to assign role to user with a role already in their domain without invite through role invite page 
	Given _1nri nonfed admin logs in 
	And _1nri goes to the role invite page through user directory  
	When _1nri admin enters an id for a user with roles in the admins own domain 
	Then _1nri admin should receive proper message and be able to assign role to user 
@2
Scenario: role invite cannot be sent to a user with same pending invite 
	Given _2nri nonfed admin logs in 
	And _2nri goes to the role invite page through user directory  
	When _2nri admin enters an id for a user with no roles who has a pending invite 
	Then _2nri admin should receive error message for the users pending role invite	 
 
 @3
Scenario: admins should see proper error message when federal email ids are entered in the id box 
	Given _3nri spaad logs in 
	And _3nri spaad goes to the role invite page through user directory  
	When _3nri spaad enters a federal id in the user email box 
	Then _3nri spaad should see error message asking entry of nonfederal email id only

@4 @t1
Scenario: nonfed admin should be able to invite nonfed user who exists and the user should see dialog box when logging in  
	Given _4nri new nonfed user signs up 
	And _4nri nonfed admin logs in   
	When _4nri admin enters an id for a user with no roles 
	Then _4nri admin should not receive any dialog box and proceed to invite the user
	When _4nri invited user logs in
	Then _4nri the invited user should receive a dialog box and be able to skip to workspace

@5 @t1
Scenario: nonfed admin should be able to invite nonfed user who currently dont have an account 
	Given _5nri nonfed admin logs in 
	And _5nri nonfed admin navigates to role invite page 
	When _5nri admin enters an id for a user who currently does not exist in the system 
	Then _5nri admin should not receive any dialog box and proceed to invite the user
	When _5nri the invited user sign up for an account
	Then _5nri the invited user should receive a dialog box for the role invite upon registration completion

@6
Scenario: both the nonfed admin and the nonfed request receiver should get emails when a role invite is made  
	Given _6nri new nonfed user signs up 
	And _6nri nonfed admin logs in 
	And _6nri nonfed admin navigates to role invite page 
	When _6nri admin invites a nonfed user for viewer role in the admins domain 
	Then _6nri admin should receive an email about the role invite 
	And _6nri the user should also get an email about the role invite

@7 @t1
Scenario: when a nonfed admin tries to assign a role to a existing user with pending invite then error should be shown  
	Given _7nri nonfed user with pending role invite logs in 
	And _7nri requests data entry role in entity registration domain
	When _7nri nonfed entity registration admin logs in 
	And _7nri nonfed admin tries to approve the request for the nonfed user  
	Then _7nri proper error message should be shown

@8 @t1
Scenario: existing user should land on feeds page when go to request button is clicked on pending invite modal when loggin in  
	Given _8nri new nonfed user signs up 
	And _8nri nonfed admin logs in   
	When _8nri admin enters an id for a user with no roles 
	Then _8nri admin should not receive any dialog box and proceed to invite the user
	When _8nri invited user logs in
	Then _8nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked

	

	


 
	
	
