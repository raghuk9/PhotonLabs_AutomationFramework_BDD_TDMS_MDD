Feature: JPMCSignin 
	Description: Validate JPMC Login and Signup

@smoke1
Scenario: Verify login with invalid credentials 
	When JPMC application is launched 
	Then navigate to login page 
	And validate userNameField is displaying 
	And validate passwordField is displaying 
	And validate rememberMe check box and text 
	And validate useToken link 
	And validate signInButton and text 
	And validate forgotLink in page 
	And validate signUp link in page 
	
@smoke
Scenario: Verify signup in JPMC rewards page 
	When JPMC application is launched 
	When user signup in jpmc rewards page 
	And user fill account details with accountNumber 
	And user fill ssn details with ssnNumber 
	And user fill username details with userName 
	And user submit the information 
	Then user should validate the error messaage 
