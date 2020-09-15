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
@RoleAssign 
Feature: Top-down role assign functionality 
	Description:  The purpose of this feature is to test top-down
role assign capability admin users

@1 @DRA @IntegrationTest @G1
Scenario: top down role assignment for dra should function correctly 
	Given _1 user logs in with dra role 
	And _1 user navigates to userdirectory and looks up a no role user 
	And _1 user gives assistance user role in assistance listing 
	When _1 the user logs back in they should see the assigned role 
	And _1 the user should also see the role history updated with correct name format 
	Then _1 the dra should be able to remove the role for the user 
	
@2 @DRA 
Scenario: organization on assign role page should not be prepopulated 
	Given _2 user logs in as dra 
	And _2 user navigates to Assign Role Page 
	Then _2 organziation box should show as empty 
	
@4 @IntegrationTest @G1 
Scenario: agency admin is now known as subtier admin for fh 
	Given _4 user logs in with ra role 
	And _4 user navigates to userdirectory and looks up a fed user 
	When _4 ra tries to assign role they should not see subtier admin instead of agency admin 
	
@5 @IntegrationTest @G1 
Scenario: aad should be able to assign administrator role in contract data 
	Given _5 user logs in with administrator all domains role 
	And _5 user navigates to userdirectory and looks up a no role user 
	Then _5 aad should be able to assign administrator role in contract data in subtier 
	
@6 @IntegrationTest @G1 
Scenario: system account administrator cannot be assigned administrator all domains role 
	Given _6ra user logs in as sampmo admin 
	And _6ra user navigates to userdirectory and looks up a user with system account admin role 
	When _6ra spaad tries to assign adminstrator all domains role to this user 
	Then _6ra appropriate error message should be displayed 

@7 @IntegrationTest @G1 @temp
Scenario: proper error message should be  thrown during role assignment if the user already has the role 
	Given _7ra user logs in as contract opportunities admin 
	And _7ra user navigates to userdirectory and looks up a user with contracting officer role in contract opp 
	When _7ra admin tries to assign same role to this user 
	Then _7ra appropriate error message should be displayed 
	
	
 