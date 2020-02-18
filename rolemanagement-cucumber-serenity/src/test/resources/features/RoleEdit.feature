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

@4 @G2
Scenario: spaad should be able to edit role for nonfed user 
	Given _4re user logs in spaad 
	And _4re user looks up a nonfed user with data entry role in contract opportunities 
	And _4re spaad should be able to edit the role to viewer
	Then _4re nonfed user should be left with the edit role  

@5 @G2 
Scenario: contract opportunities admin should be able to edit role for contracting specialist with an apostrophe in the id  
	Given _5re user logs in workspace as contract opportunities admin 
	And _5re user looks up a contracting specialist in user directory 
	Then _5re user should be able to edit their roles
		
@6 @G2 
Scenario: role edit of user with multiple roles but in different domains is not working expectedly  
	Given _6re user logs in as as contract opportunities admin 
	And _6re admin looks up a user with contracting specialist role in both contract opp and contract data 
	When _6re admin tries to edit the role and reassign contracting specialist role in contract data
	Then _6re admin should received role already assigned error
	And _6re both the existing roles should stay intact as before
	

	
	