@UniqueSAN @MA_RegressionTesting
Feature: Validation of non-unique system account name 

	This feature is testing that system account name is unique or not
	
@1
Scenario: _A Field level validation of non-unique account name 

	Given _A User logs in as a System Account Manager 
	When _A User navigates to system account directory page 
	And _A User enters non-unique system account name 
	Then _A User gets error message upon text box 
	
@2
Scenario: _B Field level validation of non-unique account name 

	Given _B User logs in as a System Account Admin 
	When _B User navigates to system account directory page 
	And _B User enters non-unique system account name 
	Then _B User gets error message upon text box 
	
@3
Scenario: _C Field level validation of unique account name 

	Given _C User logs in as a System Account Manager 
	When _C User navigates to system account directory page 
	And _C User enters unique system account name 
	Then _C User should be able to continue registering for an account 
	
@4
Scenario: _D Field level validation of unique account name 

	Given _D User logs in as a System Account Admin 
	When _D User navigates to system account directory page 
	And _D User enters unique system account name 
	Then _D User should be able to continue registering for an account 
	
@5 @MA_SmokeTest @MA_IntegrationTest
Scenario: _E Validation of system account if it is in draft after clicking next button 

	Given _E User logs in as a System Account Manager 
	When _E User navigates to system account directory page 
	And _E User enters all the system information 
	And _E User clicks on next button 
	And _E User again navigates to system account directory page 
	And _E User clicks on draft checkbox 
	And _E User enters same account name in search box 
	Then _E User should be able to find system account name status as completed 
	
@6 
Scenario: _F Validation of system account if it is in draft after clicking next button 

	Given _F User logs in as a System Account Admin 
	When _F User navigates to system account directory page 
	And _F User enters all the system information 
	And _F User clicks on next button 
	And _F User again navigates to system account directory page 
	And _F User clicks on draft checkbox 
	And _F User enters same account name in search box 
	Then _F User should be able to find system account name status as completed 
	
@7
Scenario: _G Field level validation of non-unique account name 

	Given _G User logs in as a non-fed user 
	When _G User navigates to system account directory page 
	And _G User enters non-unique system account name 
	Then _G User gets error message upon text box 
	
@8
Scenario: _H Field level validation of unique account name 

	Given _H User logs in as a non-fed user 
	When _H User navigates to system account directory page 
	And _H User enters unique system account name 
	Then _H User should be able to continue registering for an account 
	
@9 @MA_IntegrationTest
Scenario: _I Validation of system account if it is in draft after clicking next button 

	Given _I User logs in as a non-fed user 
	When _I User navigates to system account directory page 
	And _I User enters all the system information 
	And _I User clicks on next button 
	And _I User again navigates to system account directory page 
	And _I User clicks on draft checkbox 
	And _I User enters same account name in search box 
	Then _I User should be able to find system account name status as completed 
	