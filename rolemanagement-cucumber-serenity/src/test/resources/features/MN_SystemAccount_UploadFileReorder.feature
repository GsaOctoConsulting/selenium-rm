#Author : Manasa Nayani
@MN_SystemAccount_UploadFileReorder
Feature: ATO files Upload files reorder
Description:  The purpose of this feature is to test Upload ATO files reorder 

@1
Scenario: system account manager should be able reorder uploaded files
	Given  _1 system account manager enters all the organization info 
	And _1 system account manager enters permissions info 
	And _1 system account manager enters security info 
	And _1 system account manager enters autohorization info and select files to upload
	When _1 user reorders the files
	Then _1 attachements should be reordered
		
@2	
Scenario: system account admin should be able to reorder uploaded files
	Given _2 system account admin enters all the organization info 
	And _2 system account admin enters permissions info 
	And _2 system account admin enters security info 
	And _2 system account admin enters autohorization info and select files to upload
	When _2 system account admin reorders the files
	Then _2 attachements should be reordered for system account files