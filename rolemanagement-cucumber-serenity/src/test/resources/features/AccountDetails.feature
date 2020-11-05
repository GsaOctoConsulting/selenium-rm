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
@AccountDetails 
Feature: Account Details validation and API key functionality 
	Description:  The purpose of this feature is to test account details info validations

@1 @IntegrationTest 
Scenario: only the latest otp should work to validate api key generation in profile page 
	Given _1 a no role user logs in 
	And _1 user navigates to profile page 
	And _1 user generates otp with show apikey generation 
	And _1 user generates second otp 
	When _1 user uses first otp then api key generation should fail 
	When _1 user uses latest otp then api key generation should work 
	
@2 @IntegrationTest
Scenario: correct otp should work to validate api key generation in profile page 
	Given _2 a role admin user logs in 
	And _2 user navigates to profile page 
	And _2 user generates otp with show apikey generation 
	Then _2 user should be able to see the api key 
	
@3 @IntegrationTest
Scenario: newly signedup fed user should see request api button instead of show apikey in profile page 
	Given _3 user creates a new account in login dot gov 
	And _3 user navigates to profile page 
	Then _3 should be able to see request api key button 
	
@5 @IntegrationTest 
Scenario: correct otp should work to validate api key generation in profile page for nonfed 
	Given _5 a nonfed user logs in 
	And _5 user navigates to profile page 
	And _5 user generates otp with show apikey generation 
	Then _5 user should be able to see the api key 
	
	
