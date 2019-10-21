Feature: Find category by code
    I want to find category by code

    Scenario Outline: Find category by code <TC>
        Given The endpoint "/v1/categories/<categoryCode>"
        When Make "GET" call
        Then I verify the <responseStatusCode> response code
        And If response code is 200 i verify the contract for path "/v1/categories/{code}" and method "GET"
        And If response code not 200 i verify the error response message <errorResponseMessage>
        And If response code is 200 i verify the category code  <responseCategoryCode>

        Examples: 
            | TC    | categoryCode      | responseStatusCode    | responseCategoryCode  | errorResponseMessage                          |
            | TC01  | updated-category  | 200                   | updated-category      |                                               |
            | TC02  | first-category    | 404                   |                       | No exist category with code: first-category   |
