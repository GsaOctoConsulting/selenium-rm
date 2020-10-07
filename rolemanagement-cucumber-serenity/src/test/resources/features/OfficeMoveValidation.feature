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
Scenario: when an office is moved from one subtier to another conflicted role will be removed from the office 
	Given _1omv fh super admin logs in  
	And _1omv super admin creates the first subiter under gsa
	And _1omv super admin creates an office under the first subtier 
	And _1omv super admin creates the second subtier under gsa
	Then _1omv spaad logs in 
	And _1omv assigns contracting officer role in contract data at office level under subtier one
	And _1omv spaad assigns contracting specialist role to the user under contract data domain under subtier2
	When _1omv super admin moves the office into subtier2
	Then _1omv user should be left with one role

@2	
Scenario: conflicted role in assistance listing domain should also be removed during office move  
	Given _2omv spaad logs in 
	And _2omv assigns omb analyst role in assistance listing at office level under subtier one
	And _2omv spaad assigns assistance user in assistance listing to the user subtier2
	When _2omv super admin moves the office into subtier2
	Then _2omv user should be left with assistance user role only 

@3	
Scenario: role that do not conflict should hold true even after office move  
	Given _3omv spaad logs in 
	And _3omv assigns assistance user role in assistance listing at office level under subtier one
	And _3omv spaad assigns contracting specialist role in contract data to the user in subtier2
	When _3omv super admin moves the office into subtier2
	Then _3omv user should be left with both the roles

	
	
	
	
	
	
	