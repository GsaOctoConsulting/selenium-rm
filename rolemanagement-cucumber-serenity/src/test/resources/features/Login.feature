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
@Login 
Feature: Login functionality 
	Description:  The purpose of this feature is to test login
capability for users with accounts setup

@1 @DRA @Test
Scenario: dra logs into common Worspace page 
	Given _1 user already has dra account setup and enters "email" and "password" 
	

	
	
	
	
	
	
	
	
	
   