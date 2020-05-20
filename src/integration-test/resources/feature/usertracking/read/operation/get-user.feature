@ignore
Feature: User Tracking API

  Background:
    * url baseUrl
    * def reqParam = { userId: '#(userId)'}
  Scenario:  get user by id
    Given path '/users/'+reqParam.userId
    When method GET