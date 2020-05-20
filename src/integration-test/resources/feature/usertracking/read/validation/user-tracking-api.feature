Feature: User Tracking API

  Background:
    * url baseUrl

  Scenario:  create, get , and delete for user tracking
    # Create  user
    * def userResponse = call read('operation/create-user.feature')
    * def userNumber = userResponse.response.userNumber
    * match userResponse.responseStatus == 201
    * match userResponse.response contains { email: 'abc@in.com' }
    * print userNumber
    * print "Successfully Created userNumber"