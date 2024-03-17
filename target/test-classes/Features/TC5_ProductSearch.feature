@Search
Feature: Search Product Module in API Automation

  Scenario Outline: Verify searching for a product in the Application through API
    Given User add headers to access the search product endpoint
    When User add request body to search for the product "<product>"
    And User send "POST" request for the search product response
    Then User should verify the status code is 200
    And User verifies that the search product response message matches "OK"

    Examples: 
      | product |
      | Nuts    |