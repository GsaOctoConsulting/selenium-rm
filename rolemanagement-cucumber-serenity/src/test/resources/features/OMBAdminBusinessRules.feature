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
@OMBAdminBusinessRules
Feature: OMB admin business rules
Description:  The purpose of this feature is to test functionality
for OMB admin

@1
Scenario: user Directory Should Appear With Admin Functions
Given omb_ user logs in as omb admin
Then user should see all user link
And user should see bulk update link
And user should see pending request link


@2
Scenario: verify my Roles page
Given _2_omb_ user logs in as omb admin
Then _2_user navigates to my profile page
And _2_user should see the correct roles


@3
Scenario: user directory search should function correctly
Given _3omb user logs in as omb admin 
And _3omb user navigates to user directory page
Then _3omb user directory links should be clickable

@4
Scenario: omb can search across departments
Given _4omb user logs in as omb admin 
And _4omb user navigates to user directory page
Then _4omb user searches for hhs in org picker

@5
Scenario: user filters should function correctly
Given _5omb user logs in as omb admin 
And _5omb user navigates to user directory page
Then _5omb user should be able to clear filters
And _5omb user should be able to see provide feedback link


@6 
Scenario: bulk update page show show proper ord 
Given _6omb user logs in as omb admin
And _6omb user navigates to bulk update page
Then _6omb user should see org and roles across departments

@7 
Scenario: in assign role page org should not be prepopulated 
Given _7omb user logs in as omb admin
And _7omb user tries to assign a role to a user through user directory
Then _7omb in the assign role page organiztion should not be prepopulated






 