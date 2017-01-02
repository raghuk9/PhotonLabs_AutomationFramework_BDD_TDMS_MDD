@JCP_Demo
Feature: Product Search

#________________________________________________________________________________________________  


 @JCP_Search_Product
  Scenario: Perform product searches for various products   
		 Given Customer launch the Browser		 
		 Then Customer search data in JCPenny website
 		  | InputFileName | SheetName     | RowId |
  		| JCP_Input    | ProductSearch |  PS02 |  	   
 		 And Customer Close the Browser
  
  
  
  
  
  
  
  
  