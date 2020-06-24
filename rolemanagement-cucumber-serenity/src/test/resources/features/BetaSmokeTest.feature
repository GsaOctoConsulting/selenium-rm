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
@BetaSmokeTest 
Feature: Beta smoke test scenarios 
	Description:  The purpose of this feature is hold all smoke testing scnearios for beta.sam.gov

@1 
Scenario: user should be able to login into beta sam and browse to various pages
	Given _1bst user logs in with no role user 
	Then _1bst user should be able to navigate to acount details page
	And _1bst user should be able to navigate to role migration page
	And _1bst user should be able to navigate to request role page 
	And _1bst user should be able to browse to user directory page
	And _1bst user should be able to search user in the search bar 
	And _1bst user should be able to browse to feeds page 
	And _1bst user should be able to sign out
	
   