@Address
Feature: Address module API Automation

  Scenario Outline: Verify add user address to the application through API
    Given User add header and bearer authentication for accessing address endpoint
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>", <state> , <city> , <country> ,"<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User should verify the status code is 200
    And User should verify the addUserAddress response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | Chiran     | Jeevi     | 8220549878 | TNHB      |    23 |   33 |     343 |  632007 | chennai | Office       |

  Scenario Outline: Verify update user address to the application through API
    Given User add header and bearer authentication for accessing address endpoint
    When User add request body to update new address "<first_name>","<last_name>","<mobile>","<apartment>", <state> , <city> , <country> ,"<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for update addUserAddress endpoint
    Then User should verify the status code is 200
    Then User verify the update address response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | Aravind    | V         | 8220549878 | TNHB      |    23 |   33 |     343 |  632007 | chennai | Office       |

  Scenario: Verify GetUerAddress to the application through API
    Given User add Headers and bearer authentication for accessing GetAddress endpoints
    When User send "GET" request for GetUserAddress Endpoint
    Then User should verify the status code is 200
    Then User verify the GetUserAddress response message matches "OK"

  Scenario: Verify Delete User Address to the application through API
    Given User add Headers and bearer authentication for accessing address endpoint
    When User add request body for address ID
    And User send "DELETE" request for DeleteAddress endpoint
    Then User should verify the status code is 200
    Then User Verify the DeleteAddress response message Matches "Address deleted successfully"
