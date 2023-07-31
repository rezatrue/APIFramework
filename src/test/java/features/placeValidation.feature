Feature: Validate Place APIs
	@AddPlace
  Scenario Outline: Validate if Place is beeing successfully add using AddPlaceAPI
    Given Add place Payload "<name>" "<phone>" "<address>"
    When user call "AddPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in responce body is "OK"
    And "scope" in responce body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"
    
	Examples:
		|name|phone|address|
		|Old Book Store|(+1) 983 893 3000|200 North lane, Old Bookie|
#		|New Book Store|(+1) 983 893 3010|210 North lane, New Bookie|

	@DeletePlace
  Scenario: Validate if Delete Place is working
    Given Delete Place Payload
    When user call "DeletePlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in responce body is "OK"
