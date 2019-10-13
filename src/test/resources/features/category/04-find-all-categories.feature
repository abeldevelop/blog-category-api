Feature: Find all categories
  I want to find all categories

  Scenario Outline: Find all categories <TC>
    Given The endpoint "/v1/categories"
    When Make "GET" call
    Then I verify the <responseStatusCode> response code
    And If response code is 200 i verify the categories result size <resultSize>

    Examples: 
			| TC  	| responseStatusCode  | resultSize 	|
			| TC01 	| 200 								|	2						|
			
