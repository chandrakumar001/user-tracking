@ignore
Feature: User Tracking API

  Background:
    * url baseUrl

  Scenario:  create user

    Given path 'users'
    And request read('json/user.json')
    When method POST