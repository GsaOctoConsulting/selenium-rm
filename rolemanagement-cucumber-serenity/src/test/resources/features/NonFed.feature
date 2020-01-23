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
Scenario:
A NonFed User Without a Role can make role request from a finite set of selections 
	Given _2nf nonfed user without a role logs in 
	And _2nf nonfed user navigates to profile page 
	Then _2nf user should have no roles 
	When _2nf user clicks role request button to go to role request page 
	Then _2nf nonfed user should see the expected list of role to choose from 
	
@3 
Scenario:
bottom up nonfed role request should sent feeds notifications to spaad and the requester 
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
	
	
	