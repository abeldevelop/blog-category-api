Feature: Create new category
  I want to create new category

  Scenario Outline: Create category <TC>
  	Given The endpoint "/v1/categories"
  	And The resource com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource
    And The input data
    	| name 		|
    	| <name> 	|
    When Make "POST" call
    Then I verify the <responseStatusCode> response code
    And If response code is 201 i verify the contract
    And If response code is 201 i verify the contract for path "/v1/categories" and method "POST"
    And If response code not 201 i verify the error response message <errorResponseMessage>
    And If response code is 201 i verify the category code  <responseCategoryCode>

    Examples: 
			| TC  	| name 													| responseStatusCode  | responseCategoryCode	| errorResponseMessage																		|
			| TC01 	| First Category								| 201 								|	first-category				|																													|
			| TC02 	| Second Category   						| 201    							| second-category 			|																													|
			| TC03 	| First Category   							| 400    							| 											| The Category with name First Category already exists		|
			| TC04 	|    														| 400    							| 											| The category name must be between 3 and 25 characters		|
			| TC05 	| a  														| 400    							| 											| The category name must be between 3 and 25 characters		|
			| TC06 	| aaaaa aaaaa aaaaa aaaaa aaaaa	| 400    							| 											| The category name must be between 3 and 25 characters		|
