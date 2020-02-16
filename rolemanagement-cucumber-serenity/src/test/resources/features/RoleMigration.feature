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
@RoleMigration 
Feature: Role Migration functionality 
	Description:  The purpose of this feature is to test role migration capabilities

@1 #1 on confluence doc
Scenario: Legacy user should be able to migrate a legacy beta.SAM supported role 
	Given _1rm_ user is registered in login dot gov 
	And _1rm_ user signs in 
	And _1rm_ user nagivates to role migrration page 
	When _1rm_ user enters proper legacy domain name and password 
	Then _1rm_ user should have their corresponding role migrated 
	
@2 #2 & #3 on confluence doc
Scenario: user should not able to claim a legacy profile more than once 
	Given _2rm_ user is registered in login dot gov 
	And _2rm_ user nagivates to role migrration page 
	When _2rm_ user enters proper legacy domain name and password 
	Then _2rm_ user should have their corresponding role migrated 
	When _2rm_ user comes back to migrate roles using the same credentials 
	Then _2rm_ user should see user already migrated error 
	
@3 #4, 5 &6 on confluence doc
Scenario: System should throw error if one or the other data fields has incorrect data 
	Given _3rm_ user signs in 
	And _3rm_ user nagivates to role migration page 
	When _3rm_ user enters incorrect legacy username only 
	Then _3rm_ user should see proper error message 
	When _3rm user enters incorrect passowrd 
	Then _3rm_ user should see proper password error message 
	When _3rm_ user enters incorrect domain name 
	Then _3rm_ user should see appropriate error response 
	
@4 #7 on confluence doc
Scenario: when user is given a role then system should not assign the same role twice 
	Given _4rm_ new user signs up 
	And _4rm_spaad logs in and assigns assistance listing admin to the new user 
	And _4rm_ user logs back in and navigates to role migration page 
	And _4rm_ user enters domain username and password 
	When _4rm user tries to migrate the roles 
	Then _4rm_ the migration should be successful and the role assigned should still reflect previous timeline 
	
@5 #8 on confluence doc
Scenario: nonfederal user should not be able to migrate roles
	Given _5rm_ nonfed user signs in
	Then _5rm_ nonfed user should not see migrate roles link in workspace
	
@6 #9 on confluence doc
Scenario: when user is given a role in addition to what they already have then system should not assign the same role twice
	Given _6rm_ user with contracting officer roles signs in
	And _6rm_spaad logs in and assigns assistance listing admin to the new user 
	And _6rm_ user logs back in and navigates to role migration page 
	And _6rm_ user enters domain username and password 
	When _6rm user tries to migrate the roles 
	Then _6rm_ the migration should be successful and the role assigned should still reflect previous timeline 

@7 #10 on confluence doc
Scenario: unsupported role migrations should display proper message
	Given _7rm_ user signs in 
	And _7rm_ user nagivates to role migration page 
	When _7rm_ user enters cfda as domain name 
	And _7rm user enters username and password
	Then _7rm user should get role not supported error 


@8 #12 on confluence doc
Scenario: User should be presented an error message for maximum password use attempts
	Given _8rm_ user is registered in login dot gov 
	And _8rm_ user signs in 
	And _8rm_ user nagivates to role migration page 
	And _8rm_ user enters proper legacy domain name and password 
	When _8rm_ user enters incorrect password three times
	Then _8rm user should get proper error message
	
@9 # migration for fbo domain
Scenario: Legacy user should be able to migrate a legacy beta.SAM supported role with fbo domain to CS role
	Given _9rm_ user is registered in login dot gov 
	And _9rm_ user signs in 
	And _9rm_ user nagivates to role migrration page 
	When _9rm_ user enters proper legacy domain name and password 
	Then _9rm_ user should have their corresponding role migrated 	
	
@10 # migration for fbo domain
Scenario: Legacy user should be able to migrate a legacy beta.SAM supported role with fbo domain to CO role
	Given _10rm_ user is registered in login dot gov 
	And _10rm_ user signs in 
	And _10rm_ user nagivates to role migrration page 
	When _10rm_ user enters proper legacy domain name and password 
	Then _10rm_ user should have their corresponding role migrated 		
	
	
	
   