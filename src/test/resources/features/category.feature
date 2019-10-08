#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Create categories
  	Given The resource com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource
    And The input data
    	| name 		|
    	| <name> 	|
    When Make "GET" call to the endpoint "/v1/categories"
    Then I verify the <responseStatusCode> response code
    And I verify the error response message <errorResponseMessage>.

    Examples: 
			| TC  	| name 													| responseStatusCode  | errorResponseMessage																		|
			| TC01 	| First Category								| 200 								|	a																												|
			| TC02 	| Second Category   						| 200    							|	a																												|
			| TC03 	| First Category   							| 400    							| The Category with name First Category already exists		|
			| TC04 	|    														| 400    							| The category name is mandatory													|
			| TC05 	| a  														| 400    							| The category name must be between 3 and 25 characters		|
			| TC06 	| aaaaa aaaaa aaaaa aaaaa aaaaa	| 400    							| The category name is mandatory													|
			
