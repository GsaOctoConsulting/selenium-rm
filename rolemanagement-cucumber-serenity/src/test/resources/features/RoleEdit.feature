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
@RoleEdit 
Feature: Role Edit Functionality 
	Description:  The purpose of this feature is to test specific users
capability for editing  a role 

#5 on smoke test doc
@1 @SmokeTest @AssistanceAdmin @IntegrationTest
Scenario: assistance admin should be able to edit role for assistance user 
	Given _1 user logs in workspace as assistance admin 
	And _1 user looks up assistance user account in UserDirectory 
	Then _1 user should be able to edit their roles 
	
@2 @RegressionTest @DRA 
Scenario: edit role should function correctly for dra 
	Given _2 user logs in workspace as dra 
	And _2 dra looks up contracting officer in contract data 
	Then _2 dra changes users role to contracting speacialist
	Then _2 dra sees the role change showing up in my profile page

@3 @SmokeTest @AssistanceAdmin @IntegrationTest
Scenario: contract opportunities admin should be able to edit role for contracting officer 
	Given _3re user logs in workspace as contract opportunities admin 
	And _3re user looks up a contracting officer in user directory 
	Then _3re user should be able to edit their roles 	
	