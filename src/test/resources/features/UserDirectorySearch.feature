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
@UserDirectorySearch
Feature: UserDirectory search functionality
Description:  The purpose of this feature is to test user search capability
 in the User directory page

  @1 @RegressionTest @DRA
  Scenario: user directory search should function correctly
   Given _1 user logs in workspace with dra role
   Then _1 user navigates to userdirectory page and finds all user to be clickable
   
   @2 @RegressionTest @DRA
   Scenario: verify user directory orgpicker for restriction
   Given _2 user logs in workspace with dra role
   Then _2 user navigates to user directory org picker and see only his own org
  
 # the below test is first part of #4 on integration test doc 
   @3 @IntegrationTest @AssistanceUser
    Scenario: verify user directory search for assistance user
    Given _3 user logs in workspace with assistance userrole
    And _3 user navigates to user directory page and searches for assistance admin
    Then _3 user should be able to view access for assistance admin
   
   # the below test is one part of #13 on integration test doc 
   @4 @IntegrationTest @RA
    Scenario: verify user directory search for role administrator
    Given _4 user logs in workspace as role administrator
    And _4 user navigates to user directory page and searches for assistance admin
    Then _4 user should be able to view access for assistance admin
   