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
@MiscelleneousUsers
Feature: assistance admin business rules
Description:  The purpose of this feature is to test business rules for 
various roles

@1
Scenario: user Directory Should Appear Without Admin Functions
Given _1mis user logs in as hhs user
Then _1mis user should see not see role definitions link
And _1mis user should not see bulk update link
And _1mis user should not see pending request link

@2
Scenario: user Directory Should Appear Without Admin Functions
Given _2mis user logs in as hhs user
Then _2mis user search for themself through user directory
And _2mis user should not see assign role dropdown
And _2mis user should be able to see view access dropdown

@3
Scenario: hhs user should be able to request roles and delete that request
Given _3mis user logs in as hhs user
Then _3mis user navigates to request role page
And _3mis user requests assistance user role for hhs in assistance listing
And _3mis user should then be able to delete that request
