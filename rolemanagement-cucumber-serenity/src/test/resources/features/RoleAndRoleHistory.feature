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
Scenario: verify my roles page for Links Data And Role hist
	Given _1 user logs in workspace with dra role 
	Then _1 user navigates to My Roles page to see their current role 