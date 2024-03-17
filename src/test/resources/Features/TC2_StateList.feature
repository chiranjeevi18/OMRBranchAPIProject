@State
Feature: State Module API Automation

  Scenario: Verify user get stateList through API
    Given User add headers for to stateList
    When User send "GET" request for stateList endpoint
    Then User should verify the status code is 200
    And User should verify the stateList response message mathces "Tamil Nadu" and saved state ID
