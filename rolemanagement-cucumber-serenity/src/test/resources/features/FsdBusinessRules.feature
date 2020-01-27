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
@FsdBusinessRules 
Feature: fsd related bussiness rules 
	Description:  The purpose of this feature is to capture all 
relevant business rules related to fsd

@1 @RoleAdmin @IntegrationTest @G1 
Scenario:
nonfed user with no entity should be able to get fsd agent role by role admin 
	Given _1 non fed user signs up 
	And _1 role admin logs in 
	And _1 role admin looks up the newly signed nonfed user in user directory 
	And _1 role admin should be able to view the users profile 
	Then _1 role admin should be able to assign fsdagent role to the nonfed user 
	
@2 @FsdAdmin @IntegrationTest @G1 
Scenario:
nonfed user with no entity should be able to get fsd agent role by fsd admin 
	Given _2 non fed user signs up 
	And _2 fsd admin admin logs in 
	And _2 fsd admin looks up the newly signed nonfed user in user directory 
	And _2 fsd admin should be able to view the users profile 
	Then _2 fsd admin should be able to assign fsdagent role to the nonfed user 
	
@3 @FsdAdmin @IntegrationTest @G1 
Scenario: fsd admin should be able to view permissions for nonfed user 
	Given _3 fsd admin logs in 
	And _3 fsd admin looks up a nonfed user with data entry role 
	And _3 fsd admin should be able to view permission for this user 
	
@4 @FsdAdmin @IntegrationTest @G1 
Scenario:
fsd admin should be able to view permissions for nonfed user with multiple roles 
	Given _4 fsd admin logs in 
	And _4 fsd admin looks up a nonfed user with multiple roles 
	And _4 fsd admin should be able to view permission for this user 
	
@5 @FsdAdmin @IntegrationTest @G1 
Scenario: fsd agent should be able to view permissions for nonfed user 
	Given _5 fsd agent logs in 
	And _5 fsd agent looks up a nonfed user with data entry role 
	And _5 fsd agent should be able to view permission for this user 
	
@6 @FsdAdmin @IntegrationTest @G1 
Scenario: some orgs in entity picker should be restricted to gsa user 
	Given _6 spaad logs in 
	And _6 spaad looks up a nonfed user through the user directory 
	When _6 spaad tries to assign a role to this user 
	Then _6 spaad should not be able to view certain orgs in the entity picker 
	
@7 @FsdAdmin @IntegrationTest @G1 
Scenario: nonfed user should not be able to view entity picker anywhere in the system at any point 
	Given _7 nonfed user logs in 
	When _7 nonfed user goes to the profile page 
	Then _7 user should not see the entity section in account details tab 
	And _7 spaad logs in 
	When _7 spaad looks up the nonfed user 
	Then _7 spaad should not see the entity section for this users profile 

@8 @FsdAdmin @IntegrationTest @G1 
Scenario: spaad and fsd administrator cannot assign fsd agent to a nonfed user who currently has a role assigned 
	Given _8fbr spaad user logs in 
	And _8fbr spaad looks upa a nonfed user with roles 
	When _8fbr spaad tries to assign fsd agent to this user 
	Then _8fbr spaad should see an error message 
	When _7 spaad looks up the nonfed user 
	Then _7 spaad should not see the entity section for this users profile 

	
	
	
	
   