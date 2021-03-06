@ignore
Feature: User Tracking API

  Background:
    * url baseUrl

  Scenario:  create user

    Given def req =
"""
{
  "email": "abc@in.com",
  "userNumber": "12345678",
  "contactInfo": [
    {
      "firstName": "string",
      "lastName": "string",
      "phone": "string"
    }
  ],
  "selectedPlan": 1,
  "auditModel": {
    "createdDate": "string",
    "updateDate": "string"
  }
}
"""
    Given path 'users'
    And request req
    When method POST
    Then status 201