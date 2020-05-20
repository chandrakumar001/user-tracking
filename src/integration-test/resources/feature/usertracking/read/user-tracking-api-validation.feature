@ignore
Feature: Partcv Validation

  Scenario:  create, get , and delete for partcv validation
    * def re = call read('validation/create-partcv-payload.feature')
    * def re = call read('validation/delete-partcv-id.feature')
    * def re = call read('validation/get-partcv-id.feature')
#    * def re = call read('validation/put-partcv-id.feature')

