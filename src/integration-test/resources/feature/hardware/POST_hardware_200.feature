Feature: Hardware Service

  Background:
    * url baseUrl
    * headers headers

  Scenario:  create hardware

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