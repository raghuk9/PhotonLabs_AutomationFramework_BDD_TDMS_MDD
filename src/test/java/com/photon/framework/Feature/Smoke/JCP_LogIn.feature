@JCP_Demo
Feature: Login with valid credential

#________________________________________________________________________________________________  

  @JCP_Login
  Scenario: Verify and validate the Login functionality
    
    Given Customer launch the Browser            
    Then Customer validate LoginScreen        
    Then Customer Login into the JCpenny website    
     | InputFileName | SheetName| RowId |
     | JCP_Input    |    Login | User2 |     
    Then Customer validate LogoutScreen
    And Customer Close the Browser
    
 
  
  
  
  
  
  
  
  
  
  