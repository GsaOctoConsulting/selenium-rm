#Author: Manasa Nayani
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
@MN_SystemAccount_UploadFile
Feature: ATO files Upload functionality 
Description:  The purpose of this feature is to test Upload functionality of System Account ATO files
@1 @ATO
Scenario: system account manager should be able to upload a ATO file
	Given _1 sys user enters all the organization info 
	And _1 sys user enters permissions info 
	And _1 sys user enters security info 
	When _1 user enters autohorization info and select one or more files to upload
	Then _1 files should be uploaded successfully
	Then _1 edited date should be displayed in the correct format
	Then _1 user should be able to delete file
	
@2 @ATO	
Scenario: system account manager should not be able to upload Invalid file type
	Given _2 sys manager logs in and opens a system account
	When _2 sys manager enters authorization info and uploads invalid file type
	Then _2 error message should be displayed for invalid file type
@3 @ATO	
Scenario: system account manager should be able to upload multiple files
	Given _3 sys manager logs in and opens a system account
	When _3 sys manager enters authorization info and uploads multiple files
	Then _3 multiple files should be uploaded successfully
	
@4 @ATO	
Scenario: system account admin should be able to upload a ATO file
	Given _4 sys user enters all the organization info 
	And _4 sys user enters permissions info 
	And _4 sys user enters security info 
	When _4 user enters autohorization info and select one or more files to upload
	Then _4 files should be uploaded successfully
	Then _4 edited date should be displayed in the correct format
	Then _4 user should be able to delete file
@5	@ATO
Scenario: system account adminstrator should not be able to upload Invalid file type
	Given _5 sys account admin is in the authorization page
	When _5 sys acount admin enters authorization info and uploads invalid file type
	Then _5 sys account admin should not be able to upload file and error message is displayed
@6	@ATO
Scenario: system account administrator should be able to upload multiple files
	Given _6 sys account admin is in the authorization page for ATO upload
	When _6 sys account admin enters authorization info and uploads multiple files
	Then _6 sys account admin should be able to upoad multiple files successfully

@7 @ATO	
Scenario: nonfed user should be able to upload an ATO file
	Given _7 nonfed user enters all the organization info 
	And _7 nonfed user enters permissions info 
	And _7 nonfed user enters security info 
	When _7 nonfed user enters autohorization info and select one or more files to upload
	Then _7 nonfed files should be uploaded successfully
	Then _7 nonfed edited date should be displayed in the correct format
	Then _7 nonfed user should be able to delete file
	