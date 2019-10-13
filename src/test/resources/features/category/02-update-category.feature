Feature: Update category
  I want to update category
			
  Scenario Outline: Update category <TC>
  	Given The endpoint "/v1/categories/<categoryCode>"
  	And The resource com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource
    And The input data
    	| name 		|
    	| <name> 	|
    When Make "PUT" call
    Then I verify the <responseStatusCode> response code
    And If response code not 200 i verify the error response message <errorResponseMessage>
    And If response code is 200 i verify the category code  <responseCategoryCode>

    Examples: 
			| TC  	| name 													| categoryCode 				| responseStatusCode  | responseCategoryCode	| errorResponseMessage																		|
			| TC01 	| Updated Category							| first-category			| 200 								|	updated-category			|																													|
			| TC02 	| First Category   							| first-category			| 404    							| 											| No exist category with code: first-category							|
			| TC03 	|    														|	first-category			| 400    							| 											| The category name must be between 3 and 25 characters		|
			| TC04 	| a  														|	first-category			| 400    							| 											| The category name must be between 3 and 25 characters		|
			| TC05 	| aaaaa aaaaa aaaaa aaaaa aaaaa	|	first-category			| 400    							| 											| The category name must be between 3 and 25 characters		|
