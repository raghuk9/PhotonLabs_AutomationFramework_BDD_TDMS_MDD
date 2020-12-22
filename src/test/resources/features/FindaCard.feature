Feature: Functional validation of Find a Card

@Smoke @JPMC-16
Scenario: Find a credit card for Personal Rewards Cash Back Balance Transfer
	Given JPMC application is launched
	When the user clicks on find a credit card link
	Then validate user navigates to "Credit Cards - Compare Credit Card Offers & Apply Online | Chase.com" Page
	And the user clicks on 'Try our Card Finder' link
	Then validate user navigates to "Credit Card Finder | Chase.com" page
	And the user clicks on Personal icon
	And the user clicks on Rewards icon
	And the user clicks on Cash Back icon
	And the user clicks on No Annual Fee icon
	Then Validate application shows only the list of cards which is matching the above criteria
	|Personal|
	|Rewards|
	|Cash Back|
	|No Annual Fee|
	
@Smoke @JPMC-17
Scenario: Find a credit card for Business Rewards Cash Back
	Given JPMC application is launched
	When the user clicks on find a credit card link
	Then validate user navigates to "Credit Cards - Compare Credit Card Offers & Apply Online | Chase.com" Page
	And the user clicks on 'Try our Card Finder' link
	Then validate user navigates to "Credit Card Finder | Chase.com" page
	And the user clicks on Business icon
	And the user clicks on Rewards icon
	And the user clicks on Cash Back icon
	Then Validate application shows only the list of cards which is matching the above criteria
	|Business|
	|Rewards|
	|No Annual Fee|