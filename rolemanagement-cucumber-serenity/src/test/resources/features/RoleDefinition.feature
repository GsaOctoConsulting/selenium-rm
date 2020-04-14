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
   
 @2 
  Scenario: role permissions should be consisent in both dev and test environment for all roles in contract data domain
   Given _2rd user logs in a spaad
   And _2rd user navigates to role definition page and filters all roles in contract data domain
   When _2rd user goes through all permission for aad role
   Then _2rd all the expected checkbox should be marked for aad role
   When _2rd user goes through all permssion for sampmo admin role
   Then _2rd all the expected checkbox should be marked for sampmo role
   When _2rd user goes through all permission for administrator role
   Then _2rd all the expeted checkbox should be marked for administrator role
   When _2rd user goes through all permission for contracting officer role
   Then _2rd all the expected checkbox should be marked for contracting officer role
   When _2rd user goes through all the permission for contracting specialist role
   Then _2rd all the expected checkbox should be marked for contracting specialist role

 @3 
  Scenario: role permissions should be consisent in both dev and test environment for all roles in contract opportunities domain
   Given _3rd user logs in a spaad
   And _3rd user navigates to role definition page and filters all roles in contract opportunities domain
   When _3rd user goes through all permission for aad role
   Then _3rd all the expected checkbox should be marked for aad role
   When _3rd user goes through all permssion for sampmo admin role
   Then _3rd all the expected checkbox should be marked for sampmo role
   When _3rd user goes through all permission for administrator role
   Then _3rd all the expeted checkbox should be marked for administrator role
   When _3rd user goes through all permission for contracting officer role
   Then _3rd all the expected checkbox should be marked for contracting officer role
   When _3rd user goes through all the permission for opportunities admin role
   Then _3rd all the expected checkbox should be marked for opportunities admin role
   When _3rd user goes through all the permission for contracting specialist role
   Then _3rd all the expected checkbox should be marked for contracting specialist role
   When _3rd user goes throgh all the permission for data entry role
   Then _3rd all the expected checkbox should be marked for data entry role
   When _3rd user goes through all the permission for viewer role
   Then _3rd all the expected checkbox should be marked for viewer role

   
   
   