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
Scenario: nonfed admin should be able to assign role to user with role in their domain without invite through role invite page 
	Given _1nri nonfed admin logs in 
	And _1nri goes to the role invite page through user directory  
	When _1nri admin enters an id for a user with roles in the admins own domain 
	Then _1nri admin should receive proper message and be able to assign role to user 
 
	
	
