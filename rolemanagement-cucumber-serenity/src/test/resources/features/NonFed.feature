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
@NonFed
Feature: Nonfed functionalities 
	Description:  The purpose of this feature is to test the workflow
for nonfed users 

@1
Scenario: A NonFed User Without a Role Can See the Entity Management Widget 
	Given _1nf nonfed user without a role logs in  
	Then _1nf nonfed user should be able to view entity management Widget
	When _1nf nonfed user navigates to the profile page
	Then _1nf they should not see the entity details section 