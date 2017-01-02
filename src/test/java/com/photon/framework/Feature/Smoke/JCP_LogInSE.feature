@LoginvalidationSE
Feature: Login with valid credential

#________________________________________________________________________________________________  


  @JCP_LoginSE
  Scenario Outline: Verify and validate the Login using SE
  	
  	Given Customer launch the Browser
  	Then Customer validate LoginScreen    
    When I enter username as "<username>"                             
    And I enter password as "<password>"  
    Then Customer validate LogoutScreen                                 
    And Customer Close the Browser    
    
    
    Examples:
    				|username										|password				|
    				|testaccount@domain.com			|Rosejee@12345	|
    				|phtnqe@domain.com          |Test12345      |
    				
    				
    		
    				
    
    