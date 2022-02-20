Feature: Bookdepository Checkout

  @checkout
  Scenario: a user of Bookdepository can go trough Checkout process
    Given the user is on PDP of the book with ISBN "9780131872486"
    When users clicks on "Add to basket" button
    Then "Item added to your basket" pop-up appears
    And the user clicks on "Checkout" button
    And the "Basket" page opens with correct "total"
