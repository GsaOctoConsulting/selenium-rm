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
   Given _1uds user logs in workspace with dra role
   Then _1uds user navigates to userdirectory page and finds all user to be clickable
   
   @2 @RegressionTest @DRA
   Scenario: verify user directory orgpicker for restriction
   Given _2 user logs in workspace with dra role
   Then _2 user navigates to user directory org picker and see only his own org
  
 # the below test is first part of #4 on integration test doc 
   @3 @IntegrationTest @AssistanceUser @G1 @temp
    Scenario: verify user directory search for assistance user
    Given _3 user logs in workspace with assistance userrole
    And _3 user navigates to user directory page and searches for assistance admin
    Then _3 user should be able to view access for assistance admin
   
   # the below test is one part of #13 on integration test doc 
   @4 @IntegrationTest @RA @G1
    Scenario: verify user directory search for role administrator
    Given _4 user logs in workspace as role administrator
    And _4 user navigates to user directory page and searches for assistance admin
    Then _4 user should be able to view access for assistance admin
    
   @5 @G1
    Scenario: verify user directory search for feduser filter
    Given _5 user logs in workspace as role administrator
    And _5 user navigates to user directory page 
    When _5 user selects fed user filter
    Then _5 user should only see accounts with federal user id 
    
     @6 @G1 # test for bug IAE-19751
    Scenario: verify user directory search for feduser filter
    Given _6uds user logs in workspace as role administrator
    And _6uds user navigates to user directory page 
    And _6uds user selects fed user filter and goes to page ten
    When _6uds user unselects feduser filter and selects nonfed filter
    Then _6uds user should only see accounts with nonfederal user id 
    
    @7 @G1 @temp
     Scenario: user directory search box should give expected list of users for assistance user
    Given _7uds user logs in workspace as assistanceuser 
    And _7uds user navigates to user directory page 
    When _7uds user searches user using firstname
    Then _7uds user should only see accounts containing firstname
    When _7uds user searches using lastname
    Then _7uds user should only see accounts containing lastname
    When _7uds user searches using fullname
    Then _7uds user should only see accounts containing fullname
    
    @8 @G1
    Scenario: user directory org search filter should return expected results
    Given _8uds user logs into workspace as role administrator
    And _8uds user navigates to user directory page
    And _8uds user searches for a user with noroles in the user search box
    And _8uds enters gsa in org search box
    When _8uds selects the filter for org where users have roles
    Then _8uds no search results message should be displayed
    
    @9 @G1 @temp
     Scenario: user directory search box should give expected list of users for contract opp admin
    Given _9uds user logs in workspace as contract opp admin 
    And _9uds user navigates to user directory page 
    When _9uds user searches user using firstname
    Then _9uds user should only see accounts containing firstname
    When _9uds user searches using lastname
    Then _9uds user should only see accounts containing lastname
    When _9uds user searches using fullname
    Then _9uds user should only see accounts containing fullname
   
    
    
    
    
    
   