@PendPermisApprovalStatus @MA_RegressionTesting
Feature: Validation of Pending Permissions Approval status and filter


@1 
Scenario: IAE PMO Administrator should be able to conform Pending Permissions Approval status appear under Status section

	Given _G IAE PMO Administrator logs in
	When _G IAE_PMOA navigates to system account directory page
	Then _G IAE_PMOA confirm Pending Permissions Approval appear under Status section
	
@2 @MA_IntegrationTest @MA_SmokeTest
Scenario: IAE PMO Administrator should be able to use filter to search for accounts under Pending Permissions Approval status

	Given _H IAE PMO Administrator logs in
	When _H IAE_PMOA navigates to system account directory page
	And _H IAE_PMOA clicks on Pending Permissions Approval checkbox
	Then _H IAE_PMOA should be able find requests under Pending Permissions Approval status

@3
Scenario: System Account Manager should be able to conform Pending Permissions Approval status appear under Status section

	Given _I System Account Manager logs in
	When _I SAM navigates to system account directory page
	Then _I SAM confirm Pending Permissions Approval appear under Status section
	
@4 
Scenario: System Account Manager should be able to use filter to search for accounts under Pending Permissions Approval status

	Given _J System Account Manager logs in
	When _J SAM navigates to system account directory page
	And _J SAM clicks on Pending Permissions Approval checkbox
	Then _J SAM should be able find requests under Pending Permissions Approval status
	
@5
Scenario: System Account Administrator should be able to conform Pending Permissions Approval status appear under Status section

	Given _K System Account Administrator logs in
	When _K SAA navigates to system account directory page
	Then _K SAA confirm Pending Permissions Approval appear under Status section
	
@6
Scenario: System Account Administrator should be able to use filter to search for accounts under Pending Permissions Approval status

	Given _L System Account Administrator logs in
	When _L SAA navigates to system account directory page
	And _L SAA clicks on Pending Permissions Approval checkbox
	Then _L SAA should be able find requests under Pending Permissions Approval status
	
@7 
Scenario: Non-Fed user should be able to conform Pending Permissions Approval status appear under Status section

	Given _M Non-Fed user logs in
	When _M NFU navigates to system account directory page
	Then _M NFU confirm Pending Permissions Approval appear under Status section
	
@8  
Scenario: Non-Fed user should be able to use filter to search for accounts under Pending Permissions Approval status

	Given _N Non-Fed user logs in
	When _N NFU navigates to system account directory page
	And _N NFU clicks on Pending Permissions Approval checkbox
	Then _N NFU should be able find requests under Pending Permissions Approval status
	
@9 @MA_IntegrationTest 
Scenario: GSA Security Approver should be able to conform Pending Permissions Approval status dosen't appear under Status section

	Given _O GSA Security Approver logs in
	When _O GSA_SA navigates to system account directory page
	Then _O GSA_SA confirm Pending Permissions Approval status doesn't appear under Status section
