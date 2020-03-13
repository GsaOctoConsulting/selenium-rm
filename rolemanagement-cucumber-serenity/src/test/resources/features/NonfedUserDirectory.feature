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
@NonfedUserDirectory 
Feature: nonfed userdirectory functionality 
	Description:  The purpose of this feature is to test nonfed userdirectory related flows

@1 @IntegrationTest 
Scenario: nonfed user with role in contract opp should be able to view access for user in contract opp domain
	Given _1nfusdr a nonfed user with data entry with contract opp logs in
	And _1nfusdr user goes to user directory page 
	When _1nfusdr user selects data entry role filter and contract opp domain filter 
	Then _1nfusdr user should be able to see the access for all the users they can see 

@2 @IntegrationTest 
Scenario: nonfed user should be able to view users in different domainis but should not be able to view access
	Given _2nfusdr a nonfed user with data entry with contract opp logs in
	And _2nfusdr user goes to user directory page 
	And _2nfusdr user selects octo consulting group in entity picker
	When _2nfusdr user searches for a user in entity registration domain 
	Then _2nfusdr user should not be clickable 

@3 @IntegrationTest 
Scenario: nonfed admin in entity registration should be able to view users in different domains as clickable 
	Given _3nfusdr a nonfed user with admin role in entity registration logs in
	And _3nfusdr user goes to user directory page 
	When _3nfusdr user selects contract opportunities domain filter
	Then _3nfusdr user should see all the users as clickable 


@4 @IntegrationTest 
Scenario: nonfed admin in entity registration should not able to view users in different domains as clickable 
	Given _4nfusdr user logs in with admin in entity registration in octo and data entry in contract opp in ibm
	And _4nfusdr user goes to user directory page and searches for octo user with data entry in entity registration
	Then _4nfusdr user should be clickable
	When _4nfusdr logged in user searches for user with data entry in entity compliance in ibm
	Then _4nfusdr user should not be clickable

 
 
	
	
