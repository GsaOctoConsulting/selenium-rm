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
@DraBusinessRules 
Feature: dra business rules 
	Description:  The purpose of this feature is to validate
business rules related to dra

@1 
Scenario:
dra role cannot be assigned in subtier or any department other than users default department 
	Given _1 role admin logs into workspace 
	And _1 role admin looks up a no role user through user directory 
	And _1 role admin tries to assign dra to this user in subtier 
	Then _1 correct error message should appear 
	When _1 role admin tries to assign dra to user at other department 
	Then _1 correct error message should appear again 
@2 
Scenario: dra role can be assigned at department level 
	Given _2 role admin logs into workspace 
	And _2 role admin looks up a no role user through user directory 
	And _2 role admin tries to assign dra to this user at department level 
	Then _2 the newly given role should show up under roles tab 

@3
Scenario: dra can only be assigned system account admin role
Given _3 role admin logs into workspace
And _3 role admin looks up dra user through user directory
And _3 role admin tries to assign alerts admin to this user
Then _3 correct error message should appear
When _3 role admin tries to assign system account admin to dra
Then _3 the role assignment should show up under roles tab

# ---------edit roles--------
@4
Scenario: dra should be able to modiy role for users in own department
Given _4 dra logs into workspace
And _4 dra looks up a user with contracting officer role through user directory
And _4 dra edits the role to contracting specialist
Then _4 the edited role should show up on the roles pages

@5
Scenario: dra cannot assign ra and dra role
Given _5 dra logs into workspace 
And _5 dra looks up a user who is ra through user directory
Then _5 in the roles tab there should be no assign role button
When _5 dra looks a dra through the user directory
Then _5 there should also be no assign button

@6
Scenario: dra cannot be assigned omb admin
Given _6 role admin logs into workspace
And _6 role admin looks up dra user through user directory
And _6 role admin tries to assign omb admin to this user
Then _6 correct error message should appear

@7 @G1
Scenario: dra should not be able to request roles
Given _7 dra logs into workspace
And _7 dra navigates to profile page
Then _7 dra should not see the role request button
When _7 dra tries to hit the role request page directly through url 
Then _7 dra should not be able to access the role request page

@8 @G1
Scenario: dra should not be able to view any links related to role migration
Given _8 dra logs into workspace
Then _8 dra should not see migrate roles link
When _8 dra navigates to profile page
Then _8 they should not see the role migration banner 
When _8 dra tries to hit the role migration page directly through url
Then _8 dra they should not be able to access the role request page








	
	
