Feature: JPMCRewardsPage
  Description: Validate JPMC rewards application

  @smoke
  Scenario: Verify the links in JPMC rewards page
    When JPMC application is launched
    Then user validates the ultimate rewards page
    And user validates open an account link
    And user validates browse card link
    And user validates manage my account footer link
    And user validates travel cards footer link
    And user validates rewards cards footer link
    And user validates cash back cards footer link
    And user validates partner cards footer link
    And user validates small business cards footer link

  @smoke
  Scenario: Verify signup in JPMC rewards page
    When JPMC application is launched
    When user signup in jpmc rewards page
    And user fill account details with accountNumber
    And user fill ssn details with ssnNumber
    And user fill username details with userName
    And user submit the information
    Then user should validate the error messaage
