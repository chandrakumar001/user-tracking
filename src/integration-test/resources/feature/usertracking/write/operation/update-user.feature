@ignore
Feature: User Tracking API

  Background:
    * url baseUrl
    * def reqParam = { userId: '#(userId)',payload:'#(payload)'}

  Scenario:  update user

    Given path 'users/'+reqParam.userId
    And request reqParam.payload
    When method PUT