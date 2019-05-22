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
@RoleDefinition
Feature: Role Definition Functionality
Description:  The purpose of this feature is to test role definition capability

#23 on integration test doc
  @1 @IntegrationTest @RA @G1
  Scenario: role definition pages and links shoud work for role admin
   Given _1 user logs in a role admin
   And _1 user navigates to role definition page through widget
   Then _1 user shoudl see proper links and buttons
   