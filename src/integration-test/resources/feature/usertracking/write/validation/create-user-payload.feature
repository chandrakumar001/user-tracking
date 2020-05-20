@ignore
Feature: User Tracking API

  Background:
    * url baseUrl

  Scenario Outline: validate userNumber
    # Create  user
    * def payload =
    """
        {
          "email": "abc@in.com",
          "userNumber": <userNumber>,
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
    And request payload
    When method POST
    Then status <statusCode>
    And assert response.code == <errorCode>
    And assert response.message == <errorMessage>

    Examples:
      | testCase                                 | userNumber  | statusCode | errorCode         | errorMessage                  |
#      | The userNumber is null                   | null        | 400        | "400 BAD_REQUEST" | "Property 'userNumber' must not be null or empty" |
      | The userNumber is empty                  | ''          | 400        | "400 BAD_REQUEST" | "Property 'userNumber' must not be null or empty" |
#      | The userNumber is null as string         | 'null'      | 400        | "400 BAD_REQUEST" | "Invalid 'userNumber' format" |
#      | The userNumber is invalid less than 8    | '12345'     | 400        | "400 BAD_REQUEST" | "Invalid 'userNumber' format" |
#      | The userNumber is invalid greater than 8 | '123456789' | 400        | "400 BAD_REQUEST" | "Invalid 'userNumber' format" |
