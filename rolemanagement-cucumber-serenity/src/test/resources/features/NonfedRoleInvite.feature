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
Scenario: nonfed admin should be able to invite a user with no roles in admins domain 
	Given _2nri nonfed admin logs in 
	And _2nri goes to the role invite page through user directory  
	When _2nri admin enters an id for a user with no roles in the admins own domain 
	Then _2nri admin should not receive any dialog box and proceed to invite the user 
 
 @3
Scenario: admins should see proper error message when federal email ids are entered in the id box 
	Given _3nri spaad logs in 
	And _3nri spaad goes to the role invite page through user directory  
	When _3nri spaad enters a federal id in the user email box 
	Then _3nri spaad should see error message asking entry of nonfederal email id only
 
	
	
