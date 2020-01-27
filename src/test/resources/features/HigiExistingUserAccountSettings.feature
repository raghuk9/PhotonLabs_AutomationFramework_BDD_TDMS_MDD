Feature: HigiExistingUserAccountSettings
  Description: Existing user logs in and validates Account settings

  @android
  Scenario: User validates address
    Given higi is launched
    And user navigates to Login
    When user logs in with email email1 and password1
    Then user is logged in successfully
    Given user navigates to Account Settings
    When user validates Address with State
    Then user changes Address with newState
    And user logs out
    Then user is logged out successfully
    And user navigates to Login
    When user logs in with email email1 and password1
    Given user navigates to Account Settings
    When user validates Address with newState
    Then user changes Address with State
    And user logs out
    Then user is logged out successfully