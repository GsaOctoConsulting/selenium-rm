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
@Login 
Feature: Login functionality 
	Description:  The purpose of this feature is to test login
capability for users with accounts setup

@1 @DRA @Test
Scenario: dra logs into common Worspace page 
	Given _1 user already has dra account setup and enters "email" and "password" 
	Then _1 dra should see UserDirectory Widget 
	
@2 @IntegrationTest @Test
Scenario: login into workspace with login dot gov for user created using login signup should be asked to complete profile 
	Given _2 user has an account in login dot gov domain 
	And _2 the user did not complete the profile 
	Then _2 user should be shown the complete profile page when logging back in 
	
@3 @IntegrationTest 
Scenario: login into workspace with login dot gov for user created using login signup should be able signout 
	Given _3 user has an account in login dot gov domain 
	And _3 the user already completed the profile page details 
	Then _3 user should land on the workspace page and be able to signout 
	
@4 @IntegrationTest 
Scenario: existing sam.gov dra user should be able to log into workspace using login dot gov and see relevant roles 
	Given _4 user has an existing dra account in sam dot gov domain 
	And _4 user enters same id in login dot gov to signin 
	Then _4 user should be able to log into workspace 
	And _4 user navigates to myprofile page 
	Then _4 they should see their roles show up 
	
@5 @IntegrationTest 
Scenario: existing sam.gov assistance user should be able to log into workspace using login dot gov and see relevant roles 
	Given _5 user has an existing assistance user account in sam dot gov domain 
	And _5 user enters same id in login dot gov to signin 
	Then _5 user should be able to log into workspace 
	And _5 user navigates to myprofile page 
	Then _5 they should see their roles show up 
	
@6 
Scenario: existing sam.gov system account admin should be able to log into workspace using login dot gov and see relevant roles 
	Given _6 user has an existing system account admin in sam dot gov domain 
	And _6 user enters same id in login dot gov to signin 
	Then _6 user should be able to log into workspace 
	And _6 user navigates to myprofile page 
	Then _6 they should see their roles show up 
	
@7 @IntegrationTest 
Scenario: newly signed up fed user should be able to complete profile and loginto workspace in login dot gov env 
	When _7 user creates a new account in login dot gov 
	Then _7 user should be able to complete the profile 
	And _7 user should land on my workspace page 
	
@8 @IntegrationTest 
Scenario: newly signed up nonfed user should be able to complete profile and loginto workspace 
	When _8 nonfed user creates a new account in login dot gov 
	Then _8 user should be able to complete the profile 
	And _8 user should land on my workspace page 
	
@9 
Scenario: newly signed up user should be able to request role at signup and see relevant feeds notification 
	When _9 user creates a new account in login dot gov 
	Then _9 user should be able to complete the profile 
	And _9 user should be able to request role before landing on my workspace page 
	Then _9 user should see feeds notifications for the role requested 
	
@10 @IntegrationTest 
Scenario: fed user should be able to login using forgot password link 
	When _10 feduser creates a new account in login dot gov 
	And _10 user signs out 
	And _10 user goes to login dot gov to recover password 
	And _10 user creates new password and navigates given links 
	Then _10 user should be able to login into the workspace 
	
@11 
Scenario: nonfed user should be able to login using forgot password link 
	When _11 nonfeduser creates a new account in login dot gov 
	And _11 user signs out 
	And _11 user goes to login dot gov to recover password 
	And _11 user creates new password and navigates given links 
	Then _11 user should be able to login into the workspace 
	
@12 
Scenario: the available links and widgets in the t1workspace should be functional 
	Given _12 user logs in as various users 
	And _12 user lands on the new t1workspace 
	Then _12 user should be able to view the widget as per their roles 

@tag2
  Scenario Outline: Title of your scenario outline
    Given _2df1 I want to write a step with <name>
    When _2df1 I check for the <value> in step
    Then _2df1 I verify the <status> in step
    
    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
      | name3 |     8 | success |	
	
	
	
	
	
	
	
	
   