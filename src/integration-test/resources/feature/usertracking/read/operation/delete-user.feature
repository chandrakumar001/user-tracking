@ignore
Feature: User Tracking API

  Background:
    * url baseUrl
    * def reqParam = { userId: '#(userId)'}

  Scenario: delete user id

    Given path '/users/'+reqParam.userId
    When method delete
