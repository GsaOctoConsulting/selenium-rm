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
@ALGrandUserBusinessRules
Feature: ALGrandusre business rules
Description:  The purpose of this feature is to test functionality
for OMB admin

@1
Scenario: user Directory Should Appear Without Admin Functions
Given _1alg user logs in as assistance listing granduser
Then _1alg user should see not see role definitions link
And _1alg user should see bulk update link
And _1alg user should see pending request link

@2
Scenario: verify my Roles page
Given _2alg user logs in as assistance listing grand user
And _2alg user navigates to my profile page
Then _2alg user should see the correct roles


@3
Scenario: user directory search should function correctly with clickable users
Given _3alg user logs in as assistance listing grand user 
And _3alg user navigates to user directory page
Then _3alg user directory links should be clickable

@4
Scenario: al granduser can search across departments
Given _4alg user logs in as assistance listing grand user 
And _4alg user navigates to user directory page
Then _4alg user searches for hhs in org picker

@5
Scenario: user directory filter should be clickable
Given _5alg user logs in as assistance listing grand user 
And _5alg user navigates to user directory page
Then _5alg user should be able to clear filters
And _5alg user should be able to see provide feedback link

@6 
Scenario: bulk update page show proper ORD combinations
Given _6alg user logs in as assistance listing grand user
And _6alg user navigates to bulk update page
Then _6alg user should see org and roles in gsa


