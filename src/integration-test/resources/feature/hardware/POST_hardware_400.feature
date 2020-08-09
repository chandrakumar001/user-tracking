Feature: hardware Service

  Background:
    * url baseUrl
    * headers headers

  Scenario:  create hardware

    Given def req =
                """
                {
                  "manufacture": "",
                  "model": "HP EliteBook 850 G3",
                  "processor": "intel i5",
                  "memory": "8GB",
                  "virtualization": "yes"
                }
                """
    And path 'hardware'
    And request req
    When method POST
    Then status 400
    And assert response.message =="Property 'manufacture' must not be null or empty"
#    And match response.[*].name contains [“Afghanistan”]
#    And match response.[*].currencies[*].code contains [“AFN”]
#    And match response.[*].languages[*].name contains [“Pashto”]

  Scenario Outline: <testCase> with <manufacture>,<model>,<processor> and <memory> for error message <errorMessage>

    Given path 'hardware'
    And request {manufacture:<manufacture>,model:<model>,processor:<processor>,memory:<memory>,virtualization:yes}

    When method POST

    Then status <statusCode>
    And assert response.message ==<errorMessage>
    And assert response.code ==<errorCode>
    Examples:
      | testCase                        | manufacture | model               | processor | memory | statusCode | errorCode         | errorMessage                                       |
      | Property 'manufacture' is empty | ""          | HP EliteBook 850 G3 | intel i5  | 8GB    | 400        | "400 BAD_REQUEST" | "Property 'manufacture' must not be null or empty" |
      | Property 'manufacture' is null  | null        | HP EliteBook 850 G3 | intel i5  | 8GB    | 400        | "400 BAD_REQUEST" | "Property 'manufacture' must not be null or empty" |
      | Property 'model' is empty       | HP          | ""                  | intel i5  | 8GB    | 400        | "400 BAD_REQUEST" | "Property 'model' must not be null or empty"       |
      | Property 'model' is null        | HP          | null                | intel i5  | 8GB    | 400        | "400 BAD_REQUEST" | "Property 'model' must not be null or empty"       |
      | Property 'processor' is empty   | HP          | HP EliteBook 850 G3 | ""        | 8GB    | 400        | "400 BAD_REQUEST" | "Property 'processor' must not be null or empty"   |
      | Property 'processor' is null    | HP          | HP EliteBook 850 G3 | null      | 8GB    | 400        | "400 BAD_REQUEST" | "Property 'processor' must not be null or empty"   |
      | Property 'memory' is empty      | HP          | HP EliteBook 850 G3 | intel i5  | ""     | 400        | "400 BAD_REQUEST" | "Property 'memory' must not be null or empty"      |
      | Property 'memory' is null       | HP          | HP EliteBook 850 G3 | intel i5  | null   | 400        | "400 BAD_REQUEST" | "Property 'memory' must not be null or empty"      |