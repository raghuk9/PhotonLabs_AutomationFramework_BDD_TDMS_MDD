Feature: JPMCSignin 
	Description: Validate JPMC Login and Signup

@Smoke1 @Regression
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
	
@Smoke1 @Regression
Scenario: Verify signup in JPMC rewards page 
	When JPMC application is launched 
	When user signup in jpmc rewards page 
	And user fill account details with accountNumber 
	And user fill ssn details with ssnNumber 
	And user fill username details with userName 
	And user submit the information 
	Then user should validate the error messaage 

@Smoke1 @Regression
Scenario: Home Page Visual Validation 
	When JPMC application is launched
	Then visually validate the logo is displaying in the top center of the page
	Then visually validate the menu button is displaying in the top left corner of the page
	
@Regression
Scenario: Page Load Performance Validation
	When JPMC application is launched
	Then Validate home page compents are loaded with in 3 sec
	
	