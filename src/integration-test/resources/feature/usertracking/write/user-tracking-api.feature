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

    # update  user
    * def payload = read('json/update-user.json')
    * def reqPayload = { userId: '#(userId)',payload:'#(payload)'}
    * def userResponse = call read('operation/update-user.feature') reqPayload
    * match userResponse.responseStatus == 202

    # delete  user
    * def payload = read('json/user.json')
    * def reqPayload = { userId: '#(userId)'}
    * def userResponse = call read('operation/delete-user.feature') reqPayload
    * match userResponse.responseStatus == 200

    # again delete user then throw id not found
    * def payload = read('json/user.json')
    * def reqPayload = { userId: '#(userId)'}
    * def userResponse = call read('operation/delete-user.feature') reqPayload
    * match userResponse.responseStatus == 404

    # update  user
    * def payload = read('json/update-user.json')
    * def reqPayload = { userId: '#(userId)',payload:'#(payload)'}
    * def userResponse = call read('operation/update-user.feature') reqPayload
    * match userResponse.responseStatus == 404

  Scenario:  duplicate entity for user tracking
    # Create  user
    * def payload = read('json/duplicate-user.json')
    * def reqPayload = {payload:'#(payload)'}
    * def userResponse = call read('operation/create-user.feature') reqPayload
    * def userNumber = userResponse.response.userNumber
    * def userId = userResponse.response.id
    * match userResponse.responseStatus == 201
    * match userResponse.response.data contains { email: 'abc1@in.com' }
    * print userId
    * print "Successfully Created userId"

    # duplicate  user
    * def payload = read('json/duplicate-user.json')
    * def reqPayload = {payload:'#(payload)'}
    * def userResponse = call read('operation/create-user.feature') reqPayload
    * match userResponse.responseStatus == 409
    * match userResponse.response.code == "409 CONFLICT"
    * print userNumber
    * print "Duplicate entity found with userNumber"

    # delete  user
    * def payload = read('json/user.json')
    * def reqPayload = { userId: '#(userId)'}
    * def userResponse = call read('operation/delete-user.feature') reqPayload
    * match userResponse.responseStatus == 200