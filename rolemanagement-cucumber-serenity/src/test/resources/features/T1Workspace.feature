#Author: shah.raiaan@gsa.gov
#Keywords Summary:
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
@T1Workspace 
Feature: New t1workspace functionality 
	Description:  The purpose of this feature is to test the steps for 
	
@1 @RA 
Scenario: spaad should see proper links and widgets in the t1 workspace 
	Given _1t1 user logs in as spaad 
	Then _1t1 spaad should be able to view user directory widget and relevant links 
	And _1t1 spaad should see both fed and nonfed in user dropdown in widget 
	And _1t1 spaad should be able to view assistance listing widget 
	And _1t1 spaad should be able to view collective bargaining widget
	
@2 
Scenario: assistance admin should see proper links and widgets in the t1 workspace 
	Given _2t1 user logs in as assistance admin 
	Then _2t1 aad should be able to view user directory widget and relevant links 
	And _2t1 aad should be able to view assistance listing widget 
	And _2t1 aad should be able to view collective bargaining widget 
	
@3 
Scenario: fsd admin should see proper links and widgets in the t1 workspace 
	Given _3t1 user logs in as fsd admin 
	Then _3t1 fsdadmin should be able to view user directory widget and relevant links 
	And _3t1 fsdadmin should be able to view collective bargaining widget 
	
@4 
Scenario: fsd agent should see proper links and widgets in the t1 workspace 
	Given _4t1 user logs in as fsd agent 
	Then _4t1 fsdagent should be able to view user directory widget and relevant links 
	And _4t1 fsdagent should be able to view collective bargaining widget 
	
@5 
Scenario: system account admin should see proper links and widgets in the t1 workspace 
	Given _5t1 user logs in as system account admin 
	Then _5t1 saa should be able to view user directory widget and relevant links 
	And _5t1 aad should be able to view system account widget and relevant links 
	And _5t1 aad should be able to view collective bargaining widget 
	
@6 
Scenario: gsasecurity approver should see proper links and widgets in the t1 workspace 
	Given _6t1 user logs in as gsa security approver 
	Then _6t1 gsa securityapprover should be able to view user directory widget and relevant links 
	And _6t1 gsa securityapprover should be able to view system account widget and relevant links 
	And _6t1 gsa securityapprover should be able to view collective bargaining widget 
	
@7 
Scenario: system manager should see proper links and widgets in the t1 workspace 
	Given _7t1 user logs in as system manager 
	Then _7t1 system manager should be able to view user directory widget and relevant links 
	And _7t1 system manager should be able to view system account widget and relevant links 
	And _7t1 system manager should be able to view collective bargaining widget 
	
@8 
Scenario: content manager should see proper links and widgets in the t1 workspace 
	Given _8t1 user logs in as content manager 
	Then _8t1 content manager should be able to view user directory widget and relevant links 
	And _8t1 content manager should be able to view collective bargaining widget 
	And _8t1 content manager should be able to view content management widget 
	
@9 
Scenario: iam administrator should see proper links and widgets in the t1 workspace 
	Given _9t1 user logs in as iam administrator 
	Then _9t1 iam admin should be able to view user directory widget and relevant links 
	And _9t1 iam admin should be able to view collective bargaining widget 
	
@10 
Scenario: al spa should see proper links and widgets in the t1 workspace 
	Given _10t1 user logs in as assistance listing sam pmo administrator 
	Then _10t1 alspa should be able to view user directory widget and relevant links 
	And _10t1 alspa should be able to view assistance listing widget 
	And _10t1 alspa should be able to view collective bargaining widget 
	
@11 
Scenario: al administrator should see proper links and widgets in the t1 workspace 
	Given _11t1 user logs in as assistance listing administrator 
	Then _11t1 al admin should be able to view user directory widget and relevant links 
	And _11t1 al admin should be able to view assistance listing widget 
	And _11t1 al admin should be able to view collective bargaining widget 
	
@12 
Scenario: al assistance user should see proper links and widgets in the t1 workspace 
	Given _12t1 user logs in as assistance user 
	Then _12t1 assistance user should be able to view user directory widget and relevant links 
	And _12t1 assistance user should be able to view assistance listing widget 
	And _12t1 assistance user should be able to view collective bargaining widget 
	
@13 
Scenario: omb admin should see proper links and widgets in the t1 workspace 
	Given _13t1 user logs in as omb admin 
	Then _13t1 omb admin should be able to view user directory widget and relevant links 
	And _13t1 omb admin should be able to view assistance listing widget 
	And _13t1 omb admin should be able to view collective bargaining widget 
	
@14 
Scenario: contract data spa should see proper links and widgets in the t1 workspace 
	Given _14t1 user logs in as contract data sam pmo administrator 
	Then _14t1 cdspa should be able to view user directory widget and relevant links 
	And _14t1 cdspa should be able to view collective bargaining widget 
	
@15 
Scenario: contract data admin should see proper links and widgets in the t1 workspace 
	Given _15t1 user logs in as contract data administrator 
	Then _15t1 cd admin should be able to view user directory widget and relevant links 
	And _15t1 cd admin should be able to view collective bargaining widget 
	
@16 
Scenario: contract data contracting officer should see proper links and widgets in the t1 workspace 
	Given _16t1 user logs in as contract data contracting officer 
	Then _16t1 cd co should be able to view user directory widget and relevant links 
	And _16t1 cd co should be able to view collective bargaining widget 
	
@17 
Scenario: contract data contracting specialist should see proper links and widgets in the t1 workspace 
	Given _17t1 user logs in as contract data contracting specialist 
	Then _17t1 cd cs should be able to view user directory widget and relevant links 
	And _17t1 cd cs should be able to view collective bargaining widget 
	
@18 
Scenario: contracting opportunities spa should see proper links and widgets in the t1 workspace 
	Given _18t1 user logs in as contract opportunities sam pmo administrator 
	Then _18t1 cospa should be able to view user directory widget and relevant links 
	And _18t1 cospa should be able to view collective bargaining widget 
	And _18t1 cospa should be able to view contract opportunites widget 
	
@19 
Scenario: contract opportunities admin should see proper links and widgets in the t1 workspace 
	Given _19t1 user logs in as contract opportunities administrator 
	Then _19t1 co admin should be able to view user directory widget and relevant links 
	And _19t1 co admin should be able to view collective bargaining widget 
	And _19t1 co admin should be able to view contract opportunites widget 
	
@20 
Scenario: contract opportunities contracting officer should see proper links and widgets in the t1 workspace 
	Given _20t1 user logs in as contract opportunities contracting officer 
	Then _20t1 contract opportunities co should be able to view user directory widget and relevant links 
	And _20t1 contract opportunities co should be able to view collective bargaining widget 
	And _20t1 contract opportunities co should be able to view contract opportunities widget 
	
@21 
Scenario: contract opportunities contracting specialist should see proper links and widgets in the t1 workspace 
	Given _21t1 user logs in as contract opportunities contracting specialist 
	Then _21t1 contract opportunities cs should be able to view user directory widget and relevant links 
	And _21t1 contract opportunities cs should be able to view collective bargaining widget 
	And _21t1 contract opportunities cs should be able to view contract opportunities widget 
	
@22 
Scenario: contract opportunities nonfed should see proper links and widgets in the t1 workspace 
	Given _22t1 user logs in as contract opportunities nonfed data entry user 
	Then _22t1 contract opportunities nonfed user should be able to view user directory widget and relevant links 
	And _22t1 contract opportunities nonfed user should also be able to view system account widget 
	
@23 
Scenario: contract opportunities viewer should see proper links and widgets in the t1 workspace 
	Given _23t1 user logs in as contract opportunities viewer 
	Then _23t1 contract opportunities viewer should be able to view user directory widget and relevant links 
	And _23t1 contract opportunities viewer user should also be able to view system account widget 
	
@24 
Scenario: federal hierarchy spa should see proper links and widgets in the t1 workspace 
	Given _24t1 user logs in as fh sampmo administrator 
	Then _24t1 fh spa should be able to view user directory widget and relevant links 
	And _24t1 fh spa should be able to view federal hierarchy widget 
	And _24t1 fh spa should be able to view upload aac widget 
	And _24t1 fh spa should be able to view collective bargaining widget
	
@25 
Scenario: federal hierarchy department admin should see proper links and widgets in the t1 workspace 
	Given _25t1 user logs in as fh department administrator 
	Then _25t1 fh dept admin should be able to view user directory widget and relevant links 
	And _25t1 fh dept admin should be able to view federal hierarchy widget 
	And _25t1 fh dept admin should be able to view collective bargaining widget 
	
@26 
Scenario: federal hierarchy subtier admin should see proper links and widgets in the t1 workspace 
	Given _26t1 user logs in as fh subtier administrator 
	Then _26t1 fh subtier admin should be able to view user directory widget and relevant links 
	And _26t1 fh subtier admin should be able to view federal hierarchy widget 
	And _26t1 fh subtier admin should be able to view collective bargaining widget 
	
@27 
Scenario: federal hierarchy aac user should see proper links and widgets in the t1 workspace 
	Given _27t1 user logs in as fh aac 
	Then _27t1 aac user should be able to view aac widget 
	And _27t1 aac user should be able to view collective bargaining widget 
	
@28 
Scenario: federal hierarchy office admin should see proper links and widgets in the t1 workspace 
	Given _28t1 user logs in as fh office administrator 
	Then _28t1 fh office admin should be able to view user directory widget and relevant links 
	And _28t1 fh office admin should be able to view collective bargaining widget 
	
@29 
Scenario: multiple roles with cospa fhspa and system manager should see proper links and widgets in the t1 workspace 
	Given _29t1 user logs in with cospa fhspa and system manager role 
	Then _29t1 user should be able to view user directory widget and relevant links 
	And _29t1 user should be able to view system account widget 
	And _29t1 user should be able to view federal hierarchy widget 
	And _29t1 user should be able to view aac widget 
	
@30 
Scenario: multiple roles with cospa fhspa and content manager should see proper links and widgets in the t1 workspace 
	Given _30t1 user logs in with cospa fhspa and content manager role 
	Then _30t1 user should be able to view user directory widget and relevant links 
	And _30t1 user should be able to view content management account widget 
	And _30t1 user should be able to view federal hierarchy widget 
	And _30t1 user should be able to view aac widget 
	And _30t1 user should be able to view collective bargaining widget 
	
@31 
Scenario: multiple roles with cospa fhspa and gsa security approver should see proper links and widgets in the t1 workspace 
	Given _31t1 user logs in with cospa fhspa and gsa security approver 
	Then _31t1 user should be able to view user directory widget and relevant links 
	And _31t1 user should be able to view federal hierarchy widget 
	And _31t1 user should be able to view aac widget 
	And _31t1 user should be able to view collective bargaining widget 
	And _31t1 user should be able to see system account widget 
	
@32 
Scenario: selection of role in workspace should trigger the display of the descriptioin of the role 
	Given _32t1 user logs in with role that has access to t1workspace and rolerequest option 
	When _32t1 user selects assistance user role in the dropdown selection 
	Then _32t1 that role should also have a corresponding sentence on what the role is 
	When _32t1 user selects contracting officer user role in the dropdown selection 
	Then _32t1 that role should also have a contracting officer description 
	When _32t1 user selects contracting specialist role in the dropdown selection 
	Then _32t1 that role should also have a contracting specialist description 
	
@33 
Scenario: approved status renamed as active should appear in system account widget with links for SM 
	Given _33t1 user logs in as system manager 
	Then  _33t1 user should see the approved status renamed as active 
	And  _33t1 the draft status should be visible to system manager 
	And  _33t1 the pending status should be visible to system manager 
	When _33t1 user clicks the pending bubble then they go to system account page with filters selected
	
@34 
Scenario: approved status renamed as active should appear in system account widget with links for SAA 
	Given _34t1 user logs in as system account admin 
	Then  _34t1 user should see the approved status renamed as active 
	And  _34t1 the draft status should be visible to system admin 
	And  _34t1 the pending status should be visible to system admin
	When _34t1 user clicks the pending bubble then they go to system account page with filters selected 
	
@35 
Scenario: approved status renamed as active should appear in system account widget with links for GSA 
	Given _35t1 user logs in as gsa security approver 
	Then  _35t1 user should see the approved status renamed as active 
	And  _35t1 the draft status should not be visible to gsa security approver 
	And  _35t1 the pending status should be visible to gsa security approver
	When _35t1 user clicks the pending bubble then they go to system account page with pendingapproval selected  

@36 
Scenario: approved status renamed as active should appear in system account widget with links for nonfed 
	Given _36t1 user logs in as nonfed 
	Then  _36t1 user should see the approved status renamed as active 
	And  _36t1 the draft status should be visible to nonfed user 
	And  _36t1 the pending status should be visible to nonfed user

@37 
Scenario: when a status count is zero then the bubble will not take the user to system account
	Given _37t1 new user signs up 
	And  _37t1 the user is given system manager role by spaad 
	When _37t1 the user logs in and clicks on the system account bubble with zero count 
	Then _37t1 the user should not be taken to the system account page
	
@38
Scenario: tier 1 help desk user should see proper links and widgets in the t1 workspace
	Given _38t1 user logs in with tier1 help desk role 
	Then  _38t1 user should be able to view user directory widget and relevant links 
	And _38t1 the user should be able to to view entity registration widget 


@39
Scenario: functional help desk tier 2 should see proper links and widgets in t1 workspace
	Given _39t1 user logs in with functional help desk tier two role 
	Then  _39t1 user should be able to view user directory widget and relevant links 
	And _39t1 the user should be able to to view entity registration widget 
	
@40
Scenario: technical help desk tier 2 should see proper links and widgets in t1 workspace
	Given _40t1 user logs in with technical help desk tier two role 
	Then  _40t1 user should be able to view user directory widget and relevant links 
	And _40t1 the user should be able to to view entity registration widget 

@41
Scenario: entity administrator entity compliance should see proper links and widgets in t1 workspace
	Given _41t1 user logs in with entity admin in entity compliance role 
	Then  _41t1 user should be able to view user directory widget and relevant links
	And _41t1 user should be able to view entity compliance widget

@42
Scenario: data entry in entity compliance should see proper links and widgets in t1 workspace
	Given _42t1 user logs in with data entry in entity compliance domain 
	Then  _42t1 user should be able to view entity compliance widget
	And  _42t1 user should be able to view user directory widget and relevant links 

@43
Scenario: agency admin in exclusions domain should see proper links and widgets in t1 workspace
	Given _43t1 user logs in with agency admin in exclusions domain 
	Then  _43t1 user should be able to view user directory widget and relevant links
	And  _43t1 user should be able to view exclusions and relevant links
	And _43t1 user should see collective bargaining widget

@44
Scenario: agency exclusions representative should see proper links and widgets in t1 workspace
	Given _44t1 user logs in with agency exclusions representative 
	Then _44t1 user should be able to view user directory widget and relevant links
	And _44t1 user should be able to view exclusions and relevant links
	And _44t1 user should see collective bargaining widget

@45
Scenario: entity admin in entity registration should see proper links and widgets in t1 workspace
	Given _45t1 user logs in with entity admin role in entity registration 
	Then _45t1 user should be able to view user directory widget and relevant links
	And _45t1 user should be able to view entity management widget and relevant links
	And _45t1 user should be able to view entity compliance widget 

@46
Scenario: data entry in entity registration should see proper links and widgets in t1 workspace
	Given _46t1 user logs in with data entry role in entity registration 
	Then _46t1 user should be able to view user directory widget and relevant links
	And _46t1 user should be able to view entity management widget and relevant links
	
	
	 