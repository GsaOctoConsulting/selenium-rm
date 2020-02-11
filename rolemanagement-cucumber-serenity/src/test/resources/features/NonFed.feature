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
@Nonfed 
Feature: Nonfed functionalities 
	Description:  The purpose of this feature is to test the workflow
for nonfed users 

@1 
Scenario: A NonFed User Without a Role Can See the Entity Management Widget 
	Given _1nf nonfed user without a role logs in 
	Then _1nf nonfed user should be able to view entity management Widget 
	When _1nf nonfed user navigates to the profile page 
	Then _1nf they should not see the entity details section 
	
@2 
Scenario: A NonFed User Without a Role can make role request from a finite set of selections 
	Given _2nf nonfed user without a role logs in 
	And _2nf nonfed user navigates to profile page 
	Then _2nf user should have no roles and should see that message 
	When _2nf user clicks role request button to go to role request page 
	Then _2nf nonfed user should see the expected list of role to choose from 
	
@3 
Scenario: bottom up nonfed role request should sent feeds notifications to spaad and the requester 
	Given _3nf nonfed user without a role logs in 
	When _3nf nonfed user requests data entry role in entity compliance 
	Then _3nf user should see pending notification and feeds entry for the request 
	When _3nf spaad logs in 
	Then _3nf spaad should see the users pending request 
	When _3nf spaad approves the request 
	Then _3nf spaad should see the status updated in their feeds 
	When _3nf nonfed user logs back in 
	Then _3nf user should see the role assigned 
	And _3nf user should see the feeds notifications for the requested updated to approved 
	
@4 
Scenario: bottom up nonfed role request should reflect correct feeds notifications for canceled requests 
	Given _4nf nonfed user without a role logs in 
	And _4nf nonfed user requests data entry role in entity registration 
	Then _4nf user should see pending notification and feeds entry for the request 
	When _4nf user cancels the pending request 
	Then _4nf user should see the canceled status in the feeds notifications for the request 
	
@5 
Scenario: bottom up nonfed role request should reflect correct feeds notifications for rejected requests 
	Given _5nf nonfed user without a role logs in 
	And _5nf nonfed user requests data entry role in contract opportunities 
	Then _5nf user should see pending notification and feeds entry for the request 
	When _5nf spaad log in and rejects the pending request 
	Then _5nf spaad should see the rejected status in the feeds notifications for the request 
	When _5nf nonfed user logs back in 
	Then _5nf user should see the feeds updated with rejected status for the request 
	
#@6 
#Scenario: bottom up nonfed role request should reflect completed status in feeds notifications for bottomup flow 
#	Given _6nf nonfed user without a role logs in 
#	And _6nf user requests data entry role in entity compliance 
#	When _6nf spaad logs in 
#	And _6nf spaad assigns the same role to the user without approving the pending request 
#	Then _6nf the pending request should appear as complete in the feeds 
#	When _6nf the nonfed requestor logs into their account 
#	Then _6nf the requester should also see the request updated as complete in feeds 
#	And _6nf the requester will also see the updated role in my roles page 
	
@7 
Scenario: a new nonfed user should be able to register for an account without requesting role at signup 
	Given _7nf nonfed user attempts to signup for an account 
	And _7nf user goes through all the identity verification 
	Then _7nf user should not see the entity section in common profile page 
	And _7nf user should be able to skip role request and land on workspace page
	And _7nf user should be able to deactivate their account 

@8 
Scenario: a new nonfed user should be able to register for an account and requesting role at signup with feeds 
	Given _8nf nonfed user attempts to signup for an account 
	And _8nf user goes through all the identity verification  
	And _8nf user should be able to request role and land on workspace page
	Then _8nf user should be able to view their pending request in feeds
	
@9
Scenario: a nonfed user without a role should not be able to view the userdirectory
	Given _9nf nonfed user without a role logs in 
	Then _9nf user should not see user directory widget  
	And _9nf user should also not be able to hit the user directory url

@10
Scenario: a nonfed user with a role should be able to view the userdirectory
	Given _10nf nonfed user with a role logs in 
	Then _10nf nonfed user should see user directory widget  
	And _10nf nonfed user should also be able to hit the user directory url to access the page	

@11
Scenario: a nonfed user with a role should be able to view the userdirectory
	Given _11nf nonfed user logs in with data entry role in entity registration  
	And _11nf user navigates to user directory page and clicks data entry and entity registration filter  
	And _11nf user should see all those users as clickable
	
	
	
	
	