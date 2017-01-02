@JPMC_Regression_Demo
Feature: CucumberPOC

#________________________________________________________________________________________________  


  @Regression_HomeScreen_Scenario-1
  Scenario: JPMC
  
    Given Customer launch the Browser
    
    Then Customer validate JPMC home screen explore products objects
    
    Then Customer validate JPMC home screen images
    
    Then Customer validate JPMC home screen linkTexts
    
    Then Customer Login into the chase dot com
     | InputFileName | SheetName| RowId |
     | JPMC_Input    |    Login | User3 |
     
    Then Customer navigate back
     
    Given Customer Close the Browser
    
 #________________________________________________________________________________________________  


  @Regression_HomeScreen_Scenario-2
  Scenario: JPMC
  
    Given Customer launch the Browser
    
    Then Customer search data in chase dot com
     | InputFileName | SheetName     | RowId |
     | JPMC_Input    | ProductSearch |  PS02 |
     
    Then Customer navigate back
     
    Given Customer Close the Browser
    
    
#________________________________________________________________________________________________  


  @JPMC_Navigate_Creditcards
  Scenario: JPMC
  
    Given Customer launch the Browser
    
    Then Customer navigate to "Creditcards" products objects
    
    Then Customer validate credit card screen objects
    
    Then Customer navigate back
     
    Given Customer Close the Browser
    
#________________________________________________________________________________________________  


  @JPMC_Navigate_Checking
  Scenario: JPMC
  
    Given Customer launch the Browser
    
    Then Customer navigate to "Checking" products objects
    
    Then Customer validate checking screen objects
    
    Then Customer navigate back
     
    Given Customer Close the Browser
    
#________________________________________________________________________________________________  


  
 