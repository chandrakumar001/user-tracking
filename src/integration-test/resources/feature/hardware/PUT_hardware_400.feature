Feature: hardware Service

  Background:
    * url baseUrl
    * headers headers

  Scenario Outline: <testCase> with <manufacture>,<model>,<processor> and <memory> for error message <errorMessage>

    Given path 'hardware/c261536f-d8a6-43b9-af8b-a732d6d249bb'
    And request {manufacture:<manufacture>,model:<model>,processor:<processor>,memory:<memory>,virtualization:yes}

    When method PUT

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