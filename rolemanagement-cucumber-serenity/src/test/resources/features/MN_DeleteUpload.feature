#Author: Manasa Nayani
@MN_DeleteUpload
Feature: ATO files Delete Uploads validation
Description:  The purpose of this feature is to test Security Approver and IAE PMO should not be able to delete upload files
@1
Scenario: Security Approver should not be able to delete uploaded files
	Given _1du user login as Sys Account Admin and create a new account
	And _1du user enters public permission information
	And _1du user uploads a file enters OTP from email and submit
	And _1du user login as Security Approver
	And _1du select Pending Approval status 
	And _1du user enters the keyword and search for the account
	When _1du user go to the request details and check the Authorization 
	Then _1du Security Approver should not be able to delete upload files
@2
Scenario: IAE PMO should not be able to delete uploaded files
Given _2du user login as Sys Account Admin and create a new account
And _2du user enters public permission information
And _2du user uploads a file enters OTP from email and submit
And _2du user login as IAE pmo admin
And _2du select Pending Approval status 
And _2du user enters the keyword and search for the account
When _2du user go to the request details and check the Authorization 
Then _2du pmo admin should not be able to delete upload files
