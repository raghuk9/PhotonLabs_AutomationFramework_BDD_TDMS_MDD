Feature: HigiNewUserRegistration
  Description: New user registration with valid credentials

  Background: Platform is launched
    Given higi is launched
    And user is logged out

  @android
  Scenario: User validates error message when the password less than 6 characters during registration
    Given user navigates to sign up
    When user signs up with email email1 and password1
    Then user validates error message message1 when the password is incorrect