Feature: Delete category by code
    I want to delete category by code

    Scenario Outline: Delete category by code <TC>
        Given The endpoint "/v1/categories/<categoryCode>"
        When Make "DELETE" call
        Then I verify the <responseStatusCode> response code
        And If response code is 200 i verify the contract for path "/v1/categories/{code}" and method "DELETE"
        And If response code not 204 i verify the error response message <errorResponseMessage>

        Examples: 
            | TC    | categoryCode      | responseStatusCode    | errorResponseMessage                          |
            | TC01  | updated-category  | 204                   |                                               |
            | TC02  | updated-category  | 404                   | No exist category with code: updated-category |
