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


@1 @RegressionTest @IntegrationTest @DRA @G1
Scenario: verify clear filter and provide feed links for dra
	Given _1 given user logs in as dra 
	And _1 user navigates to user directory page 
	Then _1 user checks filter should be able to clears them 
	Then _1 user should be able to see provide feed links 
	
	# the below test is second part of #4 on integration test doc
@2 @IntegrationTest @AssistanceUser @G1
Scenario: verify clear filter and provide feed links for assitance user
	Given _2 given user logs in as assistance user 
	And _2 user navigates to user directory page 
	Then _2 user checks filter should be able to clears them 
	
	
	# the below test is second part of #13 on integration test doc
@3 @IntegrationTest @RA @G1
Scenario: verify clear filter and provide feed links for ra
	Given _3 given user logs in as role administrator
	And _3 user navigates to user directory page 
	Then _3 user checks filter should be able to clears them 

@5 @IntegrationTest @G1
Scenario: no role userdirectory filter should work for spaad
	Given _5udf given user logs in as spaad
	And _5udf user navigates to user directory page 
	When _5udf user searches for a noroles user account and applies user with no role filter 
	Then _5udf user should be able to view the account for the no role user

@6 @IntegrationTest @G1
Scenario: filter for subtier admin and federal hierarchy domain should work for spaad
	Given _6udf given user logs in as spaad
	And _6udf user navigates to user directory page 
	When _6udf user searches for a user with subtier admin role in federal hierarchy 
	Then _6udf user should be able to view the account for the subtier admin	 

	
	
	
   