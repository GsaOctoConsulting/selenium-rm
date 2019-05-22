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
@1 @SmokeTest @NoRoleUser @IntegrationTest 
Scenario: request assistance user role and get feeds notifications 
	Given _1 user logs in workspace with no role 
	And _1 user requests assistance user role in assistance listing 
	Then _1 this request should be visible in Sent tab for the requester in their feeds and for assistance admin 
	
	#2 on smoke test doc	 
@2 @SmokeTest @NoRoleUser @IntegrationTest 
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
@6 @IntegrationTest @AssistanceUser @G1 
Scenario: role request should be editable 
	Given _6 assistance user logs into workspace 
	And _6 the user navigates to my roles page to request contracting officer role 
	When _6 user updates the comment of the from the pending request link 
	Then _6 the user should see the updated comment 
	And _6 the user should be able to delete the request 
	
	#8 & #9 on the integration-test doc
@7 @IntegrationTest @AssistanceUser @G1 
Scenario: role request comment update 
	Given _7 assistance user logs into workspace 
	And _7 the user navigates to my roles page to request contracting officer role 
	And _7 the user then updates the comments 
	Then _7 the user should be able to sign out 
	When _7 role admin logs in 
	Then  _7 role admin should see both the original and the updated comments 
	
@8 @SmokeTest @IntegrationTest 
Scenario: 
	empty org role domain and comment box should should show error messages 
	Given _8 assistance user logs into workspace 
	And _8 the user navigates to request roles page 
	When _8 the user clicks submit button without entering information 
	Then _8 the user should see all the error messages showing up 
	
@9 @IntegrationTest @G1 
Scenario:
both RA and assistance user should see feeds updated during rejection of a request 
	Given _9 assistance user logs into workspace 
	And _9 the user navigates to my roles page to request contracting officer role 
	And _9 role admin looks up the request in feeds through pending role request link 
	And _9 role admin looks up users profile page to see pending request link and rejects the request 
	Then _9 role admin should see the request status change in the feeds 
	
@10 @IntegrationTest @G1 
Scenario:
both RA and assistance user should see feeds updated during approval of a request 
	Given _10 assistance user logs into workspace 
	And _10 the user navigates to my roles page to request contracting officer role 
	And _10 role admin looks up the request in feeds through pending role request link 
	And _10 role admin looks up users profile page to see pending request link and approves the request 
	Then _10 role admin should see the request status change in the feeds 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
