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
@RoleRequest 
Feature: Role Request With Feeds 
	Description:  The purpose of this feature is to test users
capability for requesting a role and getting relevant feeds notifications

	#1 and #3 on smoke test doc
@1 @SmokeTest @NoRoleUser 
Scenario: request assistance user role and get feeds notifications 
	Given _1 user logs in workspace with no role 
	And _1 user requests assistance user role in assistance listing 
	Then _1 this request should be visible in Sent tab for the requester in their feeds and for assistance admin 
	
	#2 on smoke test doc	 
@2 @SmokeTest @NoRoleUser 
Scenario: correct organizations should be displayed while requesting role 
	Given _2 user logs in workspace with no role and GSA as default org 
	And _2 user navigates to request role page 
	Then _2 organization text box suggestions should only show GSA orgs 
	
	#7 and 8 on smoke test doc
@3 @SmokeTest @IntegrationTest
Scenario: both RM admin and requester should see request update in feeds 
	Given _3 no role user logs into workspace 
	And _3 user requests assistance user role in assistance listing 
	Then _3 this request should be visible in received tab for RM admin in feeds 
	And _3 RM admin assigns the same role to the user without approving the pending request 
	Then _3 the pending request should appear as complete in the feeds 
	And _3 the requestor logs into their account 
	Then _3 the requester see the request updated as complete in feeds 
	Then _3 the requester see the updated role in my roles page 
	
@4 @RegressionTest @DRA 
Scenario: bottom-up role request should function correctly for dra 
	Given _4 no role user logs into workspace 
	And _4 user requests assistance user role in assistance listing 
	When _4 dra logs into common workspace 
	Then _4 they should be able to approve role for the requester 
	
@5 @RegressionTest @DRA 
Scenario: role request feed should function correctly for dra 
	Given _5 no role user logs into workspace 
	And _5 user requests assitance user role in assistance listing 
	Then _5 dra logs in and verfies the feeds for the request 
	
	#6 & #7 on the integration-test doc
@6 @IntegrationTest @AssistanceUser 
Scenario: role request should be editable 
	Given _6 assistance user logs into workspace 
	And _6 the user navigates to my roles page to request contracting officer role 
	Then _6 user should receive role request submit email 
	When _6 user updates the comment of the from the pending request link 
	Then _6 the user should see the updated comment 
	And _6 the user should be able to delete the request 
	
	#8 & #9 on the integration-test doc
@7 @IntegrationTest @AssistanceUser 
Scenario: role request comment update 
	Given _7 assistance user logs into workspace 
	And _7 the user navigates to my roles page to request contracting officer role 
	And _7 the user then updates the comments
	Then _7 the user should be able to sign out
	When _7 role admin logs in
	Then  _7 role admin should see both the original and the updated comments
	
	
	
	
	
	
	
	
