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
@RoleAndRoleHistory 
Feature: Role and Role History functionality 
	Description:  The purpose of this feature is to validate users role
and role history

@1 @RegressionTest @DRA 
Scenario: remmoval of existing role should update the role history for both admin and user
	Given _1rh user logs in workspace with dra role 
	And _1rh dra navigates to user directory and looks up a user with contracting specialist role
	When _1rh dra removes the role for the user
	Then _1rh the role history should update in the profile for dra
	And _1rh the role history should update in the profile for the user 