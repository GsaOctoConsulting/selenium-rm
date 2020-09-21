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
@OfficeMoveValidation
Feature: Office Move related role validation functionality 
	Description:  The purpose of this feature is to test role validation for users when an
	office move takes place

@1 
Scenario: office move test
	Given _1omv fh super admin logs in  
	And _1omv super admin creates the first subiter under gsa 
	

	
	
	
	
	
	
	