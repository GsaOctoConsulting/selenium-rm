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
@Email 
Feature: Email functionality 
	Description:  The purpose of this feature is to test email validations

@1 @IntegrationTest 
Scenario: role requester and supervisor should receive email when request is submitted 
	Given _1 a no role user logs 
	And _1 user requests assitance user role in assistance listing 
	Then _1 user should receive an email with the proper message 
	Then _1 supervisor should also receive an email message 
	
@2 @IntegrationTest 
Scenario: role requested if approved should sent two emails to requester and one to approver if supervisor email is not selected 
	Given _2 a no role user logs in 
	And _2 user requests assistance user role in assistance listing 
	When _2 assistance admin approves the request 
	Then _2 assistance admin should receive an email message 
	Then _2 the requester should also receive two email messages 
	
@3 @IntegrationTest 
Scenario: top down role assignment should sent emails to both the assigner and assignee 
	Given _3 assistance admin logs into workspace 
	And _3 assistance admin looks up a no role user through the user directory 
	And _3 assistance admin gives assistance user role to this user 
	Then _3 assistance admin should receive proper email message 
	And _3 the user should also receive proper email message 
	
@4 @IntegrationTest 
Scenario: editing a role should send updated status emails to both the editor and role holder 
	Given _4 user logs in workspace as assistance admin 
	And _4 admin looks up assistance user account in user directory 
	And _4 admin changes the users org to office of acquisition policy 
	Then _4 assistance admin should receive proper email message 
	Then _4 the assistance user should also receive proper email message 
	
@5 @IntegrationTest 
Scenario: role requested if rejected should sent emails to requester and approver 
	Given _5 a no role user logs in 
	And _5email_user requests assitance user role in assistance listing 
	When _5 assistance admin rejects the request 
	Then _5 the requester should receive an email message 
	Then _5 supervisor should also receive email message 
	
@6 @IntegrationTest 
Scenario: role requested if approved should sent emails supervisor 
	Given _6email a no role user logs in 
	And _6email_user requests assitance user role in assistance listing 
	When _6email assistance admin approves the request 
	Then _6email supervisor should receive email message regarding the approval 
	
@7 @IntegrationTest 
Scenario: role requested if rejected should sent emails to supervisor 
	Given _7email a no role user logs in 
	And _7email_user requests assitance user role in assistance listing 
	When _7email assistance admin rejects the request 
	Then _7email supervisor should receive email message regarding the rejection 
	
@8 @IntegrationTest 
Scenario: when role is requested through workpace then also supervisor should receive email 
	Given _8 a no role user logs 
	And _8 user requests assitance user role in assistance listing through workspace page 
	Then _8 supervisor should also receive an email message 
	
@9 @IntegrationTest 
Scenario: role requested if approved should sent two emails to requester and one to supervisor if supervisor is also the approver 
	Given _9 a no role user logs in 
	And _9 user requests contracting officer role in contract opportunities 
	When _9 contract opportunities admin who is also the supervisor approves the request 
	Then _9 contract opportunities admin should receive an email message 
	Then _9 the requester should also receive two email messages 
	
@10 @IntegrationTest 
Scenario: role removal should sent emails to both the admin and user 
	Given _10 contracting data admin logs into workspace 
	And _10 admin looks up a user with contracting specialist role in contract data 
	When _10 admin removes the users role 
	Then _10 contracting data admin should receive proper email message 
	And _10 the user should also receive proper email message

 
	
	
