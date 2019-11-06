@IAE_PMOA_Email @MA_RegressionTesting
Feature: Validation email notifications in Workflow

				This feature is validating whole email notifications including IAE PMO Administrator role
				when SM, SAA and Non_fed user request system account
				
@1 
Scenario: _A In workflow IAE PMO Admin receives email when SAM submits system account including Non-public permissions info - Positive scenario

	Given _A_System Account Manager logs in
	And _A_SAM navigates to system account directory page
	And _A_SAM enters all the system information
	And _A_SAM enters all the organization info
	And _A_SAM enters Non-public permissions info
	And _A_SAM enters security info
	And _A_SAM enters authorization info
	Then _A_SAM accepts terms and conditions and submits account
	And _A_SAM and SAA receive email notification
	When _A_System Account Administrator logs in
	And _A_SAA navigates to system account directory page
	And _A_SAA searches for Pending Review request by SAM and approve it
	Then _A_SAA, SAM and IAE PMOA receive email notification
	When _A_IAE PMO Administrator logs in
	And _A_IAE PMOA approve the system account
	Then _A_SAM, SAA, PMOA and GSA SA receive email notification
	When _A_GSA Security Approver logs in
	And _A_GSA SA approve the system account
	Then _A_SAA, SAM, PMOA, GSA SA receive email notification
	
@2 
Scenario: _B In workflow IAE PMO Admin receives email when SAM submits system account including Non-public permissions info - Negative scenario

	Given _B_System Account Manager logs in
	And _B_SAM navigates to system account directory page
	And _B_SAM enters all the system information
	And _B_SAM enters all the organization info
	And _B_SAM enters Non-public permissions info
	And _B_SAM enters security info
	And _B_SAM enters authorization info
	Then _B_SAM accepts terms and conditions and submits account
	And _B_SAM and SAA receive email notification
	When _B_System Account Administrator logs in
	And _B_SAA navigates to system account directory page
	And _B_SAA searches for Pending Review request by SAM and approve it
	Then _B_SAM, SAA, and IAE PMOA receive email notification
	When _B_IAE PMO Administrator logs in
	And _B_IAE PMOA approve the system account
	Then _B_SAM, SAA, PMOA and GSA SA receive email notification
	When _B_GSA Security Approver logs in
	And _B_GSA SA reject the system account
	Then _B_SAM, SAA, PMOA, GSA SA receive email
	
@3 
Scenario: _C In workflow IAE PMO Admin receives email when SAA submits system account including Non-public permissions info - Positive scenario

	Given _C_System Account Administrator logs in
	And _C_SAA navigates to system account directory page
	And _C_SAA enters all the system information
	And _C_SAA enters all the organization info
	And _C_SAA enters Non-public permissions info
	And _C_SAA enters security info
	And _C_SAA enters authorization info
	And _C_SAA accepts terms and conditions and submits account
	Then _C_SAA, IAE PMOA receive email notification
	When _C_IAE PMO Administrator logs in
	And _C_IAE PMOA approve the system account
	And _C_SAA, IAE PMOA, GSA SA receive email notification
	When _C_GSA Security Approver logs in
	And _C_GSA SA approve the system account
	And _C_SAA, IAE PMOA and GSA SA receive email
	
@4 
Scenario: _D In workflow IAE PMO Admin receives email when SAA submits system account including Non-public permissions info - Negative scenario

	Given _D_System Account Administrator logs in
	And _D_SAA navigates to system account directory page
	And _D_SAA enters all the system information
	And _D_SAA enters all the organization info
	And _D_SAA enters Non-public permissions info
	And _D_SAA enters security info
	And _D_SAA enters authorization info
	And _D_SAA accepts terms and conditions and submits account
	Then _D_SAA, PMOA receive email notification
	When _D_IAE PMO Administrator logs in
	And _D_IAE PMOA approve the system account
	And _D_SAA, PMOA and GSA SA receive email notification
	When _D_GSA Security Approver logs in
	And _D_GSA SA reject the system account
	And _D_SAA, PMOA, GSA SA receives email
	
@5 
Scenario: _E In workflow IAE PMO Admin receives email when Non_Fed user submits system account including Non-public info - Positive scenario

	Given _E_Non_Fed user logs in
	And _E_NFU navigates to system account directory page
	And _E_NFU enters all the system information
	And _E_NFU enters all the organization info
	And _E_NFU enters Non-public permissions info
	And _E_NFU enters security info
	And _E_NFU enters authorization info
	And _E_NFU accepts terms and conditions and submits account
	Then _E_NFU, PMOA receive email notification
	When _E_IAE PMO Administrator logs in
	And _E_IAE PMOA approve the system account
	And _E_NFU, PMOA and GSA SA receive email notification
	When _E_GSA Security Approver logs in
	And _E_GSA SA approve the system account
	And _E_NFU, PMOA, GSA SA receive email
	
@6 
Scenario: _F In workflow IAE PMO Admin receives email when Non_Fed user submits system account including Non-public info - Negative scenario

	Given _F_Non_Fed user logs in
	And _F_NFU navigates to system account directory page
	And _F_NFU enters all the system information
	And _F_NFU enters all the organization info
	And _F_NFU enters Non-public permissions info
	And _F_NFU enters security info
	And _F_NFU enters authorization info
	And _F_NFU accepts terms and conditions and submits account
	Then _F_NFU, PMOA receive email notification
	When _F_IAE PMO Administrator logs in
	And _F_IAE PMOA approve the system account
	And _F_NFU, PMOA and GSA SA receive email notification
	When _F_GSA Security Approver logs in
	And _F_GSA SA reject the system account
	And _F_NFU, PMOA, GSA SA receive email
	
@7 
Scenario: _G In workflow GSA SA should not receive email when IAE PMOA reject system account including Non-public permissions info

	Given _G_ System Account Manager logs in
	And _G_SAM navigates to system account directory page
	And _G_SAM enters all the system information
	And _G_SAM enters all the organization info
	And _G_SAM enters Non-public permissions info
	And _G_SAM enters security info
	And _G_SAM enters authorization info
	Then _G_SAM accepts terms and conditions and submits account
	And _G_SAM, SAA receive email notification
	When _G_System Account Administrator logs in
	And _G_SAA navigates to system account directory page
	And _G_SAA searches for Pending Review request by SAM and approve it
	Then _G_SAM, SAA and PMOA receive email notification
	When _G_IAE PMO Administrator logs in
	And _G_IAE PMOA reject the system account
	Then _G_SAM, SAA, PMOA receive email
	
@8 
Scenario: _H In workflow When SAM submits system account including public permissions info and SAA approves it GSA SA directly receives email

	Given _H_ System Account Manager logs in
	And _H_SAM navigates to system account directory page
	And _H_SAM enters all the system information
	And _H_SAM enters all the organization info
	And _H_SAM enters public permissions info
	And _H_SAM enters security info
	And _H_SAM enters authorization info
	Then _H_SAM accepts terms and conditions and submits account
	And _H_SAM, SAA receive email notification
	When _H_System Account Administrator logs in
	And _H_SAA navigates to system account directory page
	And _H_SAA searches for Pending Review request by SAM and approve it
	Then _H_SAM, SAA, GSA SA receive email notification
	When _H_GSA Security Approver logs in
	And _H_GSA SA approve the system account
	Then _H_SAM, SAA and GSA SA receive email
	 
@9 
Scenario: _I In workflow When SAA submits system account including public permissions info GSA SA directly receives email

	Given _I_ System Account Admin logs in
	And _I_SAA navigates to system account directory page
	And _I_SAA enters all the system information
	And _I_SAA enters all the organization info
	And _I_SAA enters public permissions info
	And _I_SAA enters security info
	And _I_SAA enters authorization info
	And _I_SAA accepts terms and conditions and submits account
	Then _I_SAA, GSA SA receive email notification
	When _I_GSA Security Approver logs in
	And _I_GSA SA approve the system account
	Then _I_SAA and GSA SA receive email
	
@10 
Scenario: _J In workflow When Non-Fed user submits system account including public permissions info GSA SA directly receives email

	Given _J_ Non-Fed user logs in
	And _J_NFU navigates to system account directory page
	And _J_NFU enters all the system information
	And _J_NFU enters all the organization info
	And _J_NFU enters public permissions info
	And _J_NFU enters security info
	And _J_NFU enters authorization info
	And _J_NFU accepts terms and conditions and submits account
	Then _J_NFU, GSA SA receive email notification
	When _J_GSA Security Approver logs in
	And _J_GSA SA approve the system account
	Then _J_NFU and GSA SA receive email

@11 
Scenario: _K PMO Admin should not receive email when SAM submits system account and SAA reject it
	
	Given _K_System Account Manager logs in
	And _K_SAM navigates to system account directory page
	And _K_SAM enters all the system information
	And _K_SAM enters all the organization info
	And _K_SAM enters Non-public permissions info
	And _K_SAM enters security info
	And _K_SAM enters authorization info
	Then _K_SAM accepts terms and conditions and submits account
	And _K_SAM and SAA receive email notification
	When _K_System Account Administrator logs in
	And _K_SAA navigates to system account directory page
	And _K_SAA searches for Pending Review request by SAM and reject it
	Then _K_SAM, SAA receive email notification
