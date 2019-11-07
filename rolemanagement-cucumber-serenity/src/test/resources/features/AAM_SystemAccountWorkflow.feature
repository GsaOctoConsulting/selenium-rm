@IAE_PMO_AdminRole @MA_RegressionTesting
Feature: Validation of IAE PMO Administrator role added to the wrokflow 

	This feature is testing IAE PMO Administrator role added to the workflow

@1
Scenario: _A In Workflow IAE PMO Admin should be able to approve system account request when System Account Manager submits system account 

	Given _A System Account Manager logs in 
	And _A SAM navigates to system account directory page 
	And _A SAM enters all the system information 
	And _A SAM enters all the organization info 
	And _A SAM enters non-public permissions info 
	And _A SAM enters security info 
	And _A SAM enters authorization info 
	Then _A SAM accepts terms and conditions and submits account 
	When _A System Account Administrator logs in 
	And _A SAA navigates to system account directory page 
	Then _A SAA searches for Pending Review request by SAM and approve it 
	When _A IAE PMO Administrator logs in 
	And _A IAE_PMOA navigates to system account directory 
	Then _A IAE_PMOA searches for Pending Permissions Approval request by SAA and approve it 
	When _A GSA Security Approver logs in 
	And _A GSA SA navigates to system account directory page 
	Then _A GSA SA should be able to approve the system account 
	When _A System Account Manager logs in again 
	Then _A SAM validate system account is in published status 
	
@2
Scenario: _B In Workflow IAE PMO Admin should be able to reject system account request when System Account Manager submits system account 

	Given _B System Account Manager logs in 
	And _B SAM navigates to system account directory page 
	And _B SAM enters all the system information 
	And _B SAM enters all the organization info 
	And _B SAM enters non-public permissions info 
	And _B SAM enters security info 
	And _B SAM enters authorization info 
	Then _B SAM accepts terms and conditions and submits account 
	When _B System Account Administrator logs in 
	And _B SAA navigates to system account directory page 
	Then _B SAA searches for Pending Review request by SAM and approve it 
	When _B IAE PMO Administrator logs in 
	And _B IAE_PMOA navigates to system account directory 
	Then _B IAE_PMOA searches for Pending Permissions Approval request by SAA and reject it 
	When _B GSA Security Approver logs in 
	And _B GSA SA navigates to system account directory page 
	Then _B GSA SA should not receive request 
	When _B System Account Manager logs in again 
	Then _B SAM validate system account is in draft status 
	
@3 
Scenario: _C In Workflow IAE PMO Admin should be able to approve system account request when System Account Administrator submits system account 

	Given _C System Account Administrator logs in 
	And _C SAA navigates to system account directory page 
	And _C SAA enters all the system information 
	And _C SAA enters all the organization info 
	And _C SAA enters non-public permissions info 
	And _C SAA enters security info 
	And _C SAA enters authorization info 
	Then _C SAA accepts terms and conditions and submits account 
	When _C IAE PMO Administrator logs in 
	And _C IAE_PMOA navigates to system account directory 
	Then _C IAE_PMOA searches for Pending Permissions Approval request by SAA and approve it 
	When _C GSA Security Approver logs in 
	And _C GSA SA navigates to system account directory page 
	Then _C GSA SA should be able to reject the system account 
	When _C System Account Administrator logs in again 
	Then _C SAA validate system account is in draft status 
	
@4  
Scenario: _D In Workflow IAE PMO Admin should be able to reject system account request when System Account Administrator submits system account 

	Given _D System Account Administrator logs in 
	And _D SAA navigates to system account directory page 
	And _D SAA enters all the system information 
	And _D SAA enters all the organization info 
	And _D SAA enters non-public permissions info 
	And _D SAA enters security info 
	And _D SAA enters authorization info 
	Then _D SAA accepts terms and conditions and submits account 
	When _D IAE PMO Administrator logs in 
	And _D IAE_PMOA navigates to system account directory 
	Then _D IAE_PMOA searches for Pending Permissions Approval request by SAA and reject it 
	When _D GSA Security Approver logs in 
	And _D GSA SA navigates to system account directory page 
	Then _D GSA SA should not receive request 
	When _D System Account Administrator logs in again 
	Then _D SAA validate system account is in draft status 
	
@5  
Scenario: _E In Workflow IAE PMO Admin should be able to approve system account request when when Non-Fed User submits system account 

	Given _E Non-Fed User logs in 
	And _E NFU navigates to system account directory page 
	And _E NFU enters all the system information 
	And _E NFU enters all the organization info 
	And _E NFU enters non-public permissions info 
	And _E NFU enters security info 
	And _E NFU enters authorization info 
	Then _E NFU accepts terms and conditions and submits account 
	When _E IAE PMO Administrator logs in 
	And _E IAE_PMOA navigates to system account directory 
	Then _E IAE_PMOA searches for Pending Permissions Approval request by NFU and approve it 
	When _E GSA Security Approver logs in 
	And _E GSA SA navigates to system account directory page 
	Then _E GSA SA should be able to approve the system account 
	When _E Non-Fed User logs in again 
	Then _E NFU validate system account is in published status 
	
@6  
Scenario: _F In Workflow IAE PMO Admin should be able to reject system account request when when Non-Fed User submits system account 

	Given _F Non-Fed User logs in 
	And _F NFU navigates to system account directory page 
	And _F NFU enters all the system information 
	And _F NFU enters all the organization info 
	And _F NFU enters non-public permissions info 
	And _F NFU enters security info 
	And _F NFU enters authorization info 
	Then _F NFU accepts terms and conditions and submits account 
	When _F IAE PMO Administrator logs in 
	And _F IAE_PMOA navigates to system account directory 
	Then _F IAE_PMOA searches for Pending Permissions Approval request by NFU and reject it 
	When _F GSA Security Approver logs in 
	And _F GSA SA navigates to system account directory page 
	Then _F GSA SA should not receive request 
	When _F Non-Fed User logs in again 
	Then _F NFU validate system account is in draft status 
	
@7  
Scenario: _P IAE PMO Admin shouldn't receive request when System Account Admin reject the request submitted by System Account Manager 

	Given _P System Account Manager logs in 
	And _P SAM navigates to system account directory page 
	And _P SAM enters all the system information 
	And _P SAM enters all the organization info 
	And _P SAM enters non-public permissions info 
	And _P SAM enters security info 
	And _P SAM enters authorization info 
	Then _P SAM accepts terms and conditions and submits account 
	When _P System Account Administrator logs in 
	And _P SAA navigates to system account directory page 
	Then _P SAA searches for Pending Review request by SAM and reject it 
	When _P IAE PMO Administrator logs in 
	And _P IAE_PMOA navigates to system account directory 
	Then _P IAE_PMOA searches for Pending Permissions Approval request 
	And _P IAE_PMOA should not receive the request rejected by SAA 
	When _P System Account Manager logs in again 
	Then _P SAM validate system account is in draft status 
	
@8  
Scenario: _Q IAE PMO Admin should not receive request when System Account Manager submits system account including public permissions info only 

	Given _Q System Account Manager logs in 
	And _Q SAM navigates to system account directory page 
	And _Q SAM enters all the system information 
	And _Q SAM enters all the organization info 
	And _Q SAM enters only public permissions info 
	And _Q SAM enters security info 
	And _Q SAM enters authorization info 
	Then _Q SAM accepts terms and conditions and submits account 
	When _Q System Account Administrator logs in 
	And _Q SAA navigates to system account directory page 
	Then _Q SAA searches for Pending Review request by SAM and approve it 
	When _Q IAE PMO Administrator logs in 
	And _Q IAE_PMOA navigates to system account directory 
	And _Q IAE_PMOA should not receive the request 
	When _Q GSA Security Approver logs in 
	And _Q GSA SA navigates to system account directory 
	Then _Q GSA SA receives request directly and approve it 
	When _Q System Account Manager logs in again 
	Then _Q SAM validate system account is in published status 
	
@9  
Scenario: _R IAE PMO Admin should not receive request when System Account Admin submits system account including public permissions info only 

	Given _R System Account Admin logs in 
	And _R SAA navigates to system account directory page 
	And _R SAA enters all the system information 
	And _R SAA enters all the organization info 
	And _R SAA enters only public permissions info 
	And _R SAA enters security info 
	And _R SAA enters authorization info 
	Then _R SAA accepts terms and conditions and submits account 
	When _R IAE PMO Administrator logs in 
	And _R IAE_PMOA navigates to system account directory 
	And _R IAE_PMOA should not receive the request 
	When _R GSA Security Approver logs in 
	And _R GSA SA navigates to system account directory 
	Then _R GSA SA receives request directly and reject it 
	When _R System Account Administrator logs in again 
	Then _R SAA validate system account is in draft status 
	
@10  
Scenario: _S IAE PMO Admin should not receive request when Non-Fed user submits system account including public permissions info only 

	Given _S Non-Fed user logs in 
	And _S NFU navigates to system account directory page 
	And _S NFU enters all the system information 
	And _S NFU enters all the organization info 
	And _S NFU enters only public permissions info 
	And _S NFU enters security info 
	And _S NFU enters authorization info 
	Then _S NFU accepts terms and conditions and submits account 
	When _S IAE PMO Administrator logs in 
	And _S IAE_PMOA navigates to system account directory 
	And _S IAE_PMOA should not receive the request 
	When _S GSA Security Approver logs in 
	And _S GSA SA navigates to system account directory 
	Then _S GSA SA receives request directly and reject it 
	When _S Non-Fed User logs in again 
	Then _S NFU validate system account is in draft status 
	
@11  
Scenario: _T Comment validation in workflow 

	Given _T System Account Manager logs in 
	And _T SAM navigates to system account directory page 
	And _T SAM enters all the system information 
	And _T SAM enters all the organization info 
	And _T SAM enters all permissions info 
	And _T SAM enters security info 
	And _T SAM enters authorization info 
	And _T SAM adds comment and validate it 
	Then _T SAM accepts terms and conditions and submits account 
	When _T System Account Administrator logs in 
	And _T SAA searches for Pending Review request and validate comment 
	Then _T SAA adds comment, validate it and approve request 
	When _T IAE PMO Administrator logs in 
	And _T IAE_PMOA searches for Pending Permissions Approval request and validate comments 
	Then  _T IAE_PMOA adds comment, validate it and approve request 
	When _T GSA Security Approver logs in 
	And _T GSA SA searches for Pending Approval request and validate comments 
	Then _T GSA SA adds comment, validate and approve request 
