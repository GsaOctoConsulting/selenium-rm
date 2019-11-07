#Author: Manasa Nayani
@MN_DeleteUpload
Feature: ATO files Delete Uploads validation
Description:  The purpose of this feature is to test Security Approver and IAE PMO should not be able to delete upload files

Scenario: Security Approver should not be able to delete uploaded files

	Given _01 user login as Sys Account Admin and create a new account
	And _01 user enters public permission information
	And _01 user uploads a file enters OTP from email and submit
	And _01 user login as Security Approver
	And _01 select Pending Approval status 
	And _01 user enters the keyword and search for the account
	When _01 user go to the request details and check the Authorization 
	Then _01 Security Approver should not be able to delete upload files

Scenario: IAE PMO should not be able to delete uploaded files

Given _02 user login as IAE PMO
And  _02_user select Pending Approval status
And _02_user go to request details and check the Authorization
Then _02_IAE PMO should not be able to delete upload files
