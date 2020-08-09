Feature: Hardware Service

  Background:
    * url baseUrl
    * headers headers

  Scenario:  create hardware, get hardware and delete hardware

    #Create hardware
    Given def req =
    """
    {
      "manufacture": "HP",
      "model": "HP EliteBook 850 G3",
      "processor": "intel i5",
      "memory": "8GB",
      "virtualization": "yes"
    }

    """
    And path 'hardware'
    And request req
    When method POST
    Then status 201
    * def id = response.id

    #Get hardware
    Given path 'hardware/'+id
    When method GET
    Then status 200
    And assert response.data.manufacture == "HP"

    #Update hardware
    Given def putReq =
    """
    {
      "manufacture": "New HP",
      "model": "HP EliteBook 850 G3",
      "processor": "intel i5",
      "memory": "8GB",
      "virtualization": "yes"
    }

    """
    And path 'hardware/'+id
    And request putReq
    When method PUT
    Then status 202
    And assert response.data.manufacture == "New HP"

    #Delete hardware
    Given path 'hardware/'+id
    When method DELETE
    Then status 200

    #Again Get hardware
    Given path 'hardware/'+id
    When method GET
    Then status 404
    And assert response.message == "hardware not found"

    #Again Delete hardware
    Given path 'hardware/'+id
    When method DELETE
    Then status 404
    And assert response.message == "hardware not found"

    #Again Update hardware
    And path 'hardware/'+id
    And request putReq
    When method PUT
    Then status 404
    And assert response.message == "hardware not found"
