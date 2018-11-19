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
@UserDirectoryFilter 
Feature: User Directory filter functionality 
	Description:  The purpose of this feature is to test filter and links
capability on the UserDirectoryPage


@1 @RegressionTest @IntegrationTest @DRA 
Scenario: verify clear filter and provide feed links 
	Given _1 given user logs in as dra 
	And _1 user navigates to user directory page 
	Then _1 user checks filter should be able to clears them 
	Then _1 user should be able to see provide feed links 
	
	# the below test is second part of #4 on integration test doc
@2 @IntegrationTest @AssistanceUser 
Scenario: verify clear filter and provide feed links 
	Given _2 given user logs in as assistance user 
	And _2 user navigates to user directory page 
	Then _2 user checks filter should be able to clears them 
	Then _2 user should be able to see provide feed links 
	
	
	# the below test is second part of #13 on integration test doc
@3 @IntegrationTest @RA 
Scenario: verify clear filter and provide feed links 
	Given _3 given user logs in as role administrator
	And _3 user navigates to user directory page 
	Then _3 user checks filter should be able to clears them 
	Then _3 user should be able to see provide feed links 
	
	
   