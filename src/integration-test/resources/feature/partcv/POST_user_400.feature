Feature: Partcv Service

  Background:
    * url baseUrl

  Scenario:  create partcv

    Given def req =
"""
{
  "email": "string",
  "contactInfo": [
    {
      "firstName": "string",
      "lastName": "string",
      "phone": "string"
    }
  ],
  "selectedPlan": 0,
  "auditModel": {
    "createdDate": "string",
    "updateDate": "string"
  }
}
"""
    Given path 'users'
    And request req
    When method POST
    Then status 400