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
@BulkUpdate
Feature: Bulk update functionality
Description:  The purpose of this feature is to test users
capability for doing bulk updates

  @1 @RegressionTest @DRA
  Scenario: bulk update should function correctly
   Given _1 user logs in as dra
   And _1 user navigates to bulk update page
   Then _1 org picker should show proper options
   
   #24 , #25, & #27 on the integration-test doc
   @2 @IntegrationTest @RA @G1
   Scenario: bulk update should function correctly for RA
    Given _2 user logs in as roleadmin
   And _2 user navigates to bulk update page
   Then _2 org picker should show proper options
   