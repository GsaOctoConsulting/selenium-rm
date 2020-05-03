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
@RoleDefinition 
Feature: Role Definition Functionality 
	Description:  The purpose of this feature is to test role definition capability

	#23 on integration test doc
@1 @IntegrationTest @RA @G1 
Scenario: role definition pages and links shoud work for role admin 
	Given _1 user logs in a role admin 
	And _1 user navigates to role definition page through widget 
	Then _1 user shoudl see proper links and buttons 
	
@2 
Scenario:
role permissions should be consisent in both dev and test environment for all roles in contract data domain 
	Given _2rd user logs in a spaad 
	And _2rd user navigates to role definition page and filters all roles in contract data domain 
	When _2rd user goes through all permission for aad role 
	Then _2rd all the expected checkbox should be marked for aad role 
	When _2rd user goes through all permssion for sampmo admin role 
	Then _2rd all the expected checkbox should be marked for sampmo role 
	When _2rd user goes through all permission for administrator role 
	Then _2rd all the expeted checkbox should be marked for administrator role 
	When _2rd user goes through all permission for contracting officer role 
	Then _2rd all the expected checkbox should be marked for contracting officer role 
	When _2rd user goes through all the permission for contracting specialist role 
	Then _2rd all the expected checkbox should be marked for contracting specialist role 
	
@3 
Scenario:
role permissions should be consisent in both dev and test environment for all roles in contract opportunities domain 
	Given _3rd user logs in a spaad 
	And _3rd user navigates to role definition page and filters all roles in contract opportunities domain 
	When _3rd user goes through all permission for aad role 
	Then _3rd all the expected checkbox should be marked for aad role 
	When _3rd user goes through all permssion for sampmo admin role 
	Then _3rd all the expected checkbox should be marked for sampmo role 
	When _3rd user goes through all permission for administrator role 
	Then _3rd all the expeted checkbox should be marked for administrator role 
	When _3rd user goes through all permission for contracting officer role 
	Then _3rd all the expected checkbox should be marked for contracting officer role 
	When _3rd user goes through all the permission for opportunities admin role 
	Then _3rd all the expected checkbox should be marked for opportunities admin role 
	When _3rd user goes through all the permission for contracting specialist role 
	Then _3rd all the expected checkbox should be marked for contracting specialist role 
	When _3rd user goes throgh all the permission for data entry role 
	Then _3rd all the expected checkbox should be marked for data entry role 
	When _3rd user goes through all the permission for viewer role 
	Then _3rd all the expected checkbox should be marked for viewer role 
	
	
@4 
Scenario:
role permissions should be consisent in both dev and test environment for all roles in assistance listing domain 
	Given _4rd user logs in a spaad 
	And _4rd user navigates to role definition page and filters all roles in assistance listing domain 
	When _4rd user goes through all permission for administrator role 
	Then _4rd all the expected checkbox should be marked for administrator role 
	When _4rd user goes through all permssion for assistance user role 
	Then _4rd all the expected checkbox should be marked for assistance user role 
	When _4rd user goes through all permission for omb analyst role 
	Then _4rd all the expeted checkbox should be marked for omb analyst role 
	When _4rd user goes through all permission for aad role 
	Then _4rd all the expected checkbox should be marked for aad role 
	When _4rd user goes through all the permission for sampmo admin role 
	Then _4rd all the expected checkbox should be marked for sampmo admin role 
	When _4rd user goes through all the permission for omb administrator role 
	Then _4rd all the expected checkbox should be marked for omb administrator role 
	
@5 
Scenario:
role permissions should be consisent in both dev and test environment for all roles in admin domain 
	Given _5rd user logs in a spaad 
	And _5rd user navigates to role definition page and filters all roles in admin domain 
	When _5rd user goes through all permission for iaepmo administrator role 
	Then _5rd all the expected checkbox should be marked iaepmo administrator role 
	When _5rd user goes through all permssion for system account administrator role 
	Then _5rd all the expected checkbox should be marked for system account administrator role 
	When _5rd user goes through all permission for fsd administrator role 
	Then _5rd all the expeted checkbox should be marked for fsd administrator role 
	When _5rd user goes through all permission for iam administrator role 
	Then _5rd all the expected checkbox should be marked for iam administrator role 
	When _5rd user goes through all the permission for system manager role 
	Then _5rd all the expected checkbox should be marked for system manager role 
	When _5rd user goes through all the permission for fsd agent role 
	Then _5rd all the expected checkbox should be marked for fsd agent role 
	When _5rd user goes through all the permission for gsa security approver role 
	Then _5rd all the expected checkbox should be marked for gsa security approver role 
	When _5rd user goes through all the permission for sampmo administrator all domains role 
	Then _5rd all the expected checkbox should be marked for sampmo administrator all domains role 
	When _5rd user goes through all the permission for content manager role 
	Then _5rd all the expected checkbox should be marked for content manager role 
	When _5rd user goes through all the permission for tier2 functional helpdesk role 
	Then _5rd all the expected checkbox should be marked for tier2 functional helpdesk role 
	When _5rd user goes through all the permission for aad role 
	Then _5rd all the expected checkbox should be marked for aad role 
	When _5rd user goes through all the permission for tier2 technical helpdesk role 
	Then _5rd all the expected checkbox should be marked for tier2 technical helpdesk role 
	When _5rd user goes through all the permission for tier1 helpdesk role 
	Then _5rd all the expected checkbox should be marked for tier1 helpdesk role 
	When _5rd user goes through all the permission for gsa data approver role 
	Then _5rd all the expected checkbox should be marked for gsa data approver role 
	
@6 
Scenario:
role permissions should be consisent in both dev and test environment for all roles in entity compliance domain 
	Given _6rd user logs in a spaad 
	And _6rd user navigates to role definition page and filters all roles in entity compliance domain 
	When _6rd user goes through all permission for administrator role 
	Then _6rd all the expected checkbox should be marked administrator role 
	When _6rd user goes through all permssion for tier3 helpdesk role 
	Then _6rd all the expected checkbox should be marked for tier3 helpdesk role 
	When _6rd user goes through all permission for data entry role 
	Then _6rd all the expeted checkbox should be marked for data entry role 
	When _6rd user goes through all permission for agency roles administrator role 
	Then _6rd all the expeted checkbox should be marked for agency roles administrator role 
	
@7 
Scenario:
role permissions should be consisent in both dev and test environment for all roles in entity registration domain 
	Given _7rd user logs in a spaad 
	And _7rd user navigates to role definition page and filters all roles in entity registration domain 
	When _7rd user goes through all permission for entity management data access system only role 
	Then _7rd all the expected checkbox should be marked for entity management data access system only role 
	When _7rd user goes through all permssion for administrator role 
	Then _7rd all the expected checkbox should be marked for administrator role 
	When _7rd user goes through all permission for tier3helpdesk role 
	Then _7rd all the expected checkbox should be marked for tier3helpdesk role 
	When _7rd user goes through all permission for sensitive entity management data viewer role 
	Then _7rd all the expected checkbox should be marked for sensitive entity management data viewer role 
	When _7rd user goes through all permission for fouo executive compensation data viewer role 
	Then _7rd all the expected checkbox should be marked for fouo executive compensation data viewer role 
	When _7rd user goes through all permission for agency administrator entity management role 
	Then _7rd all the expected checkbox should be marked for agency administrator entity management role 
	When _7rd user goes through all permission for data entry role 
	Then _7rd all the expected checkbox should be marked for data entry role 
	When _7rd user goes through all permission for viewer role 
	Then _7rd all the expected checkbox should be marked for viewer role 
	When _7rd user goes through all permission for fouo entity management data viewer role 
	Then _7rd all the expected checkbox should be marked for fouo entity management data viewer role 
	When _7rd user goes through all permission for agency roles administrator role 
	Then _7rd all the expected checkbox should be marked for agency roles administrator role 
	When _7rd user goes through all permission for fouo and fapiis role 
	Then _7rd all the expected checkbox should be marked for fouo and fapiis role 
	When _7rd user goes through all permission for office registration representatitve role 
	Then _7rd all the expected checkbox should be marked for office registration representatitve role 
	
@8 
Scenario: role permissions should be consisent in both dev and test environment for all roles in federal hierarchy domain 
	Given _8rd user logs in a spaad 
	And _8rd user navigates to role definition page and filters all roles in federal hierarchy domain 
	When _8rd user goes through all permission for aac user role 
	Then _8rd all the expected checkbox should be marked aac user role 
	When _8rd user goes through all permssion for subtier administrator role 
	Then _8rd all the expected checkbox should be marked for subtier administrator role 
	When _8rd user goes through all permission for sampmo administrator role 
	Then _8rd all the expeted checkbox should be marked for sampmo administrator role 
	When _8rd user goes through all permission for department administrator role 
	Then _8rd all the expeted checkbox should be marked for department administrator role 
	When _8rd user goes through all permission for office administrator role 
	Then _8rd all the expeted checkbox should be marked for office administrator role 

@9 
Scenario: role permissions should be consisent in both dev and test environment for all roles in exclusions domain 
	Given _9rd user logs in a spaad 
	And _9rd user navigates to role definition page and filters all roles in exclusions domain 
	When _9rd user goes through all permission for tier3 helpdesk role 
	Then _9rd all the expected checkbox should be marked for tier3 helpdesk role 
	When _9rd user goes through all permssion for agency administrator exclusions role 
	Then _9rd all the expected checkbox should be marked for agency administrator exclusions role 
	When _9rd user goes through all permission for agency exclusions representative role 
	Then _9rd all the expeted checkbox should be marked for agency exclusions representative role 
	When _9rd user goes through all permission for agency roles administrator role 
	Then _9rd all the expeted checkbox should be marked for agency roles administrator role
	
   