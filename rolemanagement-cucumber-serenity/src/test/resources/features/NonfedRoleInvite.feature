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
## (Comments)
#Sample Feature Definition Template
@NonfedRoleInvite 
Feature: nonfed roleinvite functionality 
	Description:  The purpose of this feature is to test nonfed role invite related scenarios

@1
Scenario: nonfed admin should be able to assign role to user with a role already in their domain without having to send invite 
	Given _1nri nonfed admin logs in 
	And _1nri goes to the role invite page through user directory  
	When _1nri admin enters an id for a user with roles in the admins own domain 
	Then _1nri admin should receive proper message and be able to assign role to user without seeing send invite button 
@2
Scenario: role invite cannot be sent to a user who already has the same pending invite 
	Given _2nri nonfed admin logs in 
	And _2nri goes to the role invite page through user directory  
	When _2nri admin enters an id for a user with no roles who has a pending invite 
	Then _2nri admin should receive error message for the users pending role invite	 
 
 @3
Scenario: admins should see proper error message when federal email ids are entered in the id box 
	Given _3nri spaad logs in 
	And _3nri spaad goes to the role invite page through user directory  
	When _3nri spaad enters a federal id in the user email box 
	Then _3nri spaad should see error message asking entry of nonfederal email id only

@4 
Scenario: nonfed admin should be able to invite nonfed user who exists in the system and the user should see dialog box when loggin in  
	 
	Given _4nri nonfed admin logs in   
	When _4nri admin enters an id for a user with no roles 
	Then _4nri admin should not receive any dialog box and proceed to invite the user
	When _4nri invited user logs in
	Then _4nri the invited user should receive a dialog box and be able to skip to workspace

@5 
Scenario: nonfed admin should be able to invite nonfed user who currently dont have an account registered
	Given _5nri nonfed admin logs in 
	And _5nri nonfed admin navigates to role invite page 
	When _5nri admin enters an id for a user who currently does not exist in the system 
	Then _5nri admin should not receive any dialog box and proceed to invite the user
	When _5nri the invited user sign up for an account
	Then _5nri the invited user should receive a dialog box for the role invite upon registration completion

@6
Scenario: both the nonfed admin and the nonfed request receiver should get emails when a role invite is made  
	Given _6nri new nonfed user signs up 
	And _6nri nonfed admin logs in 
	And _6nri nonfed admin navigates to role invite page 
	When _6nri admin invites a nonfed user for viewer role in the admins domain 
	Then _6nri admin should receive an email about the role invite 
	And _6nri the user should also get an email about the role invite

@7 
Scenario: after a role invitation is sent by admin if the user makes the same role request then the admin can no longer approve that until responds to the invite  
	Given _7nri nonfed user with pending role invite logs in 
	And _7nri requests same role as the pending invite 
	When _7nri nonfed entity registration admin logs in 
	And _7nri nonfed admin tries to approve the request made by the nonfed user  
	Then _7nri proper error message should be shown

@8 
Scenario: existing user with a role invitation should be able to accept the invite and obtain the role for entity registration domain    
	Given _8nri nonfed admin logs in   
	When _8nri admin enters an id for a user with no roles 
	Then _8nri admin should not receive any dialog box and proceed to invite the user
	When _8nri invited user logs in
	Then _8nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked
	When _8nri the user selects the pending request in feeds and accepts the role invite
	Then _8nri the user should see the role in profile with the correctly role history reflected
	And _8nri nonfed admin should now be able to look up the user through user directory
	
@9 
Scenario: existing user with a role invitation should be able to decline the role invite for entity registration domain    
	Given _9nri nonfed admin logs in   
	When _9nri admin enters an id for a user with no roles 
	Then _9nri admin should not receive any dialog box and proceed to invite the user
	When _9nri invited user logs in
	Then _9nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked
	When _9nri the user selects the pending request in feeds and declines the role invite
	Then _9nri the user should see no roles assigned in profile with the status of the feed changed to declined

@10
Scenario: Additional information should be called business justification for nonfed admins in role invite page 
	Given _10nf user logs in nonfed admin 
	And _10nf user navigates to role invite page 
	Then _10nf additional information box should now be called business justrification 
	And _10nf the business justification field should also be mandatory 

@11
Scenario: existing user with a role invitation should be able to accept the role invite for contract opportunities domain    
	Given _11nri nonfed admin in contract opp logs in   
	When _11nri admin enters an id for a user with no roles and sends out an invite 
	Then _11nri admin should be able to see the pending status of the invite in their feeds
	When _11nri invited user logs in
	Then _11nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked
	When _11nri the user selects the pending request in feeds and accepts the role invite
	Then _11nri the user should see the status in feeds and the role in profile with the correctly role history reflected
	When _11nri nonfed admin logs back in
	Then _11nri the admin should also see the status update in their feeds
	And _11nri the admin should now be able to search the user in userdirectory

@12	
Scenario: entity registration admin in two entitities should be able to send role invites for both entities in the same request    
	Given _12nri nonfed admin in two entities logs in   
	When _12nri admin enters an id for a user with no roles 
	Then _12nri admin proceeds to invite the user for viwer role in entity registration in both octo and ibm
	When _12nri invited user logs in
	Then _12nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked
	When _12nri the user selects the pending request in feeds and accepts the role invite
	Then _12nri the user should see no roles assigned in profile with the status of the feed changed to accepted
	When _12nri nonfed admin logs back in
	Then _12nri the admin should also see the status update in their feeds
	And _12nri the admin should now be able to search the user in userdirectory

@13
Scenario: entity compliance admin in two entitities should be able to send role invites for both entities in the same request    
	Given _13nri nonfed entity compliance admin in two entities logs in   
	When _13nri admin enters an id for a user with no roles 
	Then _13nri admin proceeds to invite the user for viewer role in entity compliance in both octo and ibm
	When _13nri invited user logs in
	Then _13nri the invited user should receive a dialog box and land on feeds page when go to request button is clicked
	When _13nri the user selects the pending request in feeds and accepts the role invite
	Then _13nri the user should see no roles assigned in profile with the status of the feed changed to accepted
	When _13nri nonfed admin logs back in
	Then _13nri the admin should also see the status update in their feeds
	And _13nri the admin should now be able to search the user in userdirectory

@14 @temp
Scenario:  pending role invitation will change status to canceled when user deactivates their account   
	Given _14nri nonfed admin in entity registration logs in   
	When _14nri admin enters an id for a user with no roles 
	Then _14nri admin proceeds to invite the user for viewer role in entity registration and see pending status in feeds 
	When _14nri invited user logs in
	And _14nri user deactivates their account
	Then _14nri admin should see the pending role invite status changes to canceled

@15 @temp
Scenario:  pending role invitation will change status to canceled when user deactivates their account   
	Given _15nri nonfed admin in entity compliance logs in   
	When _15nri admin enters an id for a user with no roles 
	Then _15nri admin proceeds to invite the user for data role in entity compliance and see pending status in feeds 
	When _15nri invited user logs in and accepts the role invite
	And _15nri user deactivates their account
	Then _15nri admin should see the accepted role invite status changes to canceled

@16 @temp
Scenario:  pending role request should change status to canceled in feeds when user deactivates their account   
	Given _16nri nonfed user signs up   
	And _16nri user requests viewer role in entity registration 
	When _16nri entity registration admin logs in 
	Then _16nri admin should see the pending role request status in feeds
	When _16nri user deactivates their account
	Then _16nri admin should see the pending role invite status changes to canceled in feeds
	
@17 @temp
Scenario:  accepted role request should change status to canceled in feeds when user deactivates their account
	Given _17nri fed user signs up
	And _17nri user requests assistance user role in assistance listing
	When _17nri assistance admin logs in
	And _17nri assistance admin should see the pending role request status in feeds and accept the invite
	When _17nri user deactivates their account
	Then _17nri admin should see the accepted role request status changes to canceled in feeds


 
	
	
