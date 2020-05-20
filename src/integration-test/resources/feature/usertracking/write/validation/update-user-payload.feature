@ignore
Feature: User Tracking API
  Background:
    * url baseUrl

  Scenario:  create, update and delete for user tracking
    # Create  user
    * def payload = read('json/user.json')
    * def reqPayload = {payload:'#(payload)'}
    * def userResponse = call read('operation/create-user.feature') reqPayload
    * def userNumber = userResponse.response.userNumber
    * def userId = userResponse.response.id
    * match userResponse.responseStatus == 201
    * match userResponse.response.data contains { email: 'abc@in.com' }
    * print userId
    * print "Successfully Created userId"