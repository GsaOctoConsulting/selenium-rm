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
@UserDirectoryWidget 
Feature: UserDirectory Widget 
	Description:  The purpose of this feature is to test users
capability for using the userdirectory widget in the common workspace page

#4 on smoke test doc
@1 @SmokeTest @IntegrationTest
Scenario: assistance admin should be able to view widget and pending role request 
   Given _1 user logs in workspace with assistance admin role
	Then _1 user should see widget and pending role request should be clickable
	
@2 @RegressionTest @DRA
Scenario: user directory should appear with admin functions for dra
Given _2 user longs in workspace with dra role
Then _2 user should see pending request and bulk update link but no role defintion link

#1, 2, 3 on integration-test doc
@3 @IntegrationTest @AssistanceUser @G1
Scenario: user directory widget should function correctly for assistance user
Given _3 user logs in workspace with assistance user role
Then _3 user navigates to my roles page to ensure information is accurate
And _3 user goes back to workspace page and searches for a user through widget
Then _3 the result should be displayed in the user directory page

#10, 11, 12 on integration-test doc
@4 @IntegrationTest @RA @G1
Scenario: user directory widget should function correctly for role administrator
Given _4 user logs in workspace as role admin
Then _4 user navigates to my roles page to ensure information is accurate
And _4 user goes back to workspace page and searches for a user through widget
Then _4 the result should be displayed in the user directory page







