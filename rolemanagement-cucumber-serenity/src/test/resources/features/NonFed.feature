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
	
@10 @x
Scenario: a nonfed user with a role should be able to view the userdirectory and system account widget
	Given _10nf nonfed user with a role logs in 
	Then _10nf nonfed user should see user directory widget 
	And _10nf nonfed user should also be able to hit the user directory url to access the page
	When _10nf nonfed user is in the workspace page
	Then _10nf they should be able to see the system account widget 
	
@11 
Scenario: a nonfed user with data entry in entity registration should be able to view users as clickable 
	Given _11nf nonfed user logs in with data entry role in entity registration 
	And _11nf user navigates to user directory page and clicks data entry and entity registration filter 
	And _11nf user should see all those users as clickable 
	
@12
Scenario: a nonfed user with data entry in entity compliance should be able to view users as clickable 
	Given _12nf nonfed user logs in with data entry role in entity entity compliance 
	And _12nf user navigates to user directory page and clicks data entry and entity compliance filter 
	And _12nf user should see all those users as clickable 
	
@13
Scenario: a nonfed user with data entry in contract opportunities should be able to view users as clickable 
	Given _13nf nonfed user logs in with data entry role in contract opportunities 
	And _13nf user navigates to user directory page and clicks data entry and contract opportunities filter 
	And _13nf user should see all those users as clickable 
	
@14 
Scenario: when requested role is accepted a nonfed user should see role assigned status in profile history 
	Given _14nf a nonfed user with no role logs in 
	And _14nf user requests viewer role in contract opportunities 
	When _14nf spaad accepts the pending role request for the user 
	Then _14nf then requsters profile page should show the role assigned in role history 
	
@15 
Scenario: when a role is removed from a nonfed user then user should see role removed status in profile history 
	Given _15nf spaad logs in 
	And _15nf spaad looks up a nonfed user with a role 
	When _15nf spaad removes the users role 
	Then _15nf then users history should show role removed status in in profile history 
	
@16 
Scenario: when a role is updated for a nonfed user then user should see role updated status in profile history 
	Given _16nf spaad logs in 
	And _16nf spaad looks up a nonfed user with data entry in contract opportunities 
	When _16nf spaad updates users role to viewer in contract opportunities 
	Then _16nf then the user should see role updated status in profile history 
	
@17 
Scenario: when user is being assigned a role that they already have proper error message should display 
	Given _17nf user logs in with viewer role in entity registration 
	And _17nf user requests viewer role in entity registration 
	When _17nf spaad logs in 
	And _17nf spaad tries to approve the pending request for the user 
	Then _17nf proper error message show up 
	
@18 
Scenario: a nonfed admin in a particular domain when requesting a role should not see any role options under that domain 
	Given _18nf user logs in with admin role in entity registration 
	And _18nf user navigates to role request page 
	When _18nf user looks for role under role tab 
	Then _18nf no role options under entity registration should show up 
	
@19 
Scenario: a new nonfed users pending request should diplay with accurate info in spaads 
	Given _19nf new nonfed user signs up 
	And _19nf user requests data entry at entity compliance at octo consulting group 
	When _19nf spaads logs in and views the pending request 
	Then _19nf spaad should be able to view the requesting org in the pending link 
	
	
@20 
Scenario: user directory search box should give expected list of users for nonfed user 
	Given _20nf user logs in workspace as nonfed user with a role 
	And _20nf user navigates to user directory page 
	When _20nf user searches user using firstname 
	Then _20nf user should only see accounts containing firstname 
	When _20nf user searches using lastname 
	Then _20nf user should only see accounts containing lastname 
	When _20nf user searches using fullname 
	Then _20nf user should only see accounts containing fullname 
	
@21
Scenario: user directory search box should give expected list of users with entity specific emails for nonfed user 
	Given _21nf user logs in workspace as nonfed user with a role 
	And _21nf user navigates to user directory page 
	When _21nf user searches users with ids ending with gmail 
	Then _21nf user should only see accounts containing gmail ids 
	When _21nf user searches users with ids ending with octoconsulting 
	Then _21nf user should only see accounts containing octoconsulting 
	When _21nf user searches users with ids ending with gsa 
	Then _21nf user should not see no results 
	
@22
Scenario: user directory entiy search should give expected list of orgs with name duns and cage code for nonfed admin 
	Given _22nf user logs in workspace as nonfed admin 
	And _22nf user navigates to user directory page 
	When _22nf user searches entity with name in entity search box 
	Then _22nf user should only see entities containing the search term in the results 
	When _22nf user searches enity with duns no in duns search box 
	Then _22nf user should only see entities containing the duns no in the results 
	When _22nf user searches enity with cage code in cage search box 
	Then _22nf user should only see entities containing the cage no in the results 

@23 
Scenario: a nonfed user when requesting a role should not see duns plus four in entity picker during role request 
	Given _23nf user logs in as nonfed user 
	And _23nf user navigates to role request page 
	When _23nf user searches for entities in the entity picker 
	Then _23nf duns plus four should not show up and only duns should show

@24 
Scenario: a nonfed admin when assigning a role should not see duns plus four in entity picker  
	Given _24nf user logs in as nonfed admin
	And _24nf user navigates to userdirectory page and searches for a nonfed user 
	When _24nf user searches for entity in the assign role page 
	Then _24nf duns plus four should not show up and only duns should show

@25
Scenario: spaad when assigning a role should not see duns plus four in entity picker  
	Given _25nf user logs in as spaad
	And _25nf user navigates to userdirectory page and searches for a nonfed user 
	When _25nf user searches for entity in the assign role page 
	Then _25nf duns plus four should not show up and only duns should show	

	 
	
	
	
	
	
	
	
	
	
	
	
	