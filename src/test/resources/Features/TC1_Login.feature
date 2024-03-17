@Login
Feature: Login Module API automation

  Scenario: Get User Log Token from Login endpoint
    Given User add header
    When User add basic authentication for Login
    And User send "POST" request for Login endpoint
    Then User should verify the status code is 200
    And User should verify the login response body firstName present as "Chiran" and get the LogToken saved