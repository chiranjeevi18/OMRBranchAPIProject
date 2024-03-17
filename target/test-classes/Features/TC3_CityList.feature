@City
Feature: City module API Automation

  Scenario: Verify user get cityList through API
    Given User add header for to get cityList
    When User add request body state id for get cityList
    And User send "POST" request for cityList endpoint
    Then User should verify the status code is 200
    And User verify the cityList response message matches "Vellore" and save city id
