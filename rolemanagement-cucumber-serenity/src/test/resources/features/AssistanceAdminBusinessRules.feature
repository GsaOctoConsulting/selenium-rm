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
@AssistanceAdminBusinessRules
Feature: assistance admin business rules
Description:  The purpose of this feature is to test functionality
for assistance admin

@1
Scenario: user Directory Should Appear  Admin Functions
Given _1aa user logs in as assistance admin
Then _1aa user should see not see role definitions link
And _1aa user should see bulk update link
And _1aa user should see pending request link

@2
Scenario: verify my Roles page
Given _2aa user logs in as assistance admin
And _2aa user navigates to my profile page
Then _2aa user should see the correct roles

@3
Scenario: user directory search should function correctly
Given _3aa user logs in as assistance admin 
And _3aa user navigates to user directory page
Then _3aa user directory links should be clickable

@4
Scenario: assistance admin can search within own department
Given _4aa user logs in as assistance listing grand user 
And _4aa user navigates to user directory page
Then _4aa user searches for hhs in org picker



