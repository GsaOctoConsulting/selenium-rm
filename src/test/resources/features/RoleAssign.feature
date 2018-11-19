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
@RoleAssign
Feature: Top-down role assign functionality
Description:  The purpose of this feature is to test top-down
role assign capability admin users

  @1 @DRA
  Scenario: top down role assignment for dra should function correctly
   Given _1 user logs in as dra
   And _1 user navigates to userdirectory and looks up a no role user
   And _1 user gives assistance user role in assistance listing
   
 @2 @DRA
 Scenario: organization on assign role page should not be prepopulated
 Given _2 user logs in as dra
 And _2 user navigates to Assign Role Page
 Then _2 organziation box should show as empty
 