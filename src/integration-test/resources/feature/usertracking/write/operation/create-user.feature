@ignore
Feature: User Tracking API

  Background:
    * url baseUrl
    * def reqParam = { payload:'#(payload)'}

  Scenario:  create user

    Given path 'users'
    And request reqParam.payload
    When method POST