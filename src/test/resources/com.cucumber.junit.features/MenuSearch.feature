Feature: as a user of BookDepository I want to have ability to find a product

@smoke
  Scenario: Menu Search should contain an Input field with a Placeholder
    Given the user opens Bookdepisitory site
    When the user looks at the Main Header
    Then the user sees "Menu Search" which is input field
    And the "Menu Search" has a correct placeholder "Search for books by keyword / title / author / ISBN"