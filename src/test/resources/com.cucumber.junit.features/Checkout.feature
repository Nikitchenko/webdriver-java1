Feature: Checkout

  @checkout
  Scenario: user goes through Checkout process
    Given the user is on PDP of the book with ISBN "9780131872486"; notice book price
    #Given the user is on PDP of the book with ISBN "9781783982707"; notice book price
    When the user clicks on *Add to basket* button
    Then *Item add to your basket* pop-up opens
    And the user clicks on *Basket-Checkout* button
    And *Basket* page opens with correct Item Price and Total Price - same as on PDP
    And the user clicks on *Checkout* button
    And *Checkout* page opens with correct Item Price and Total Price - same as on PDP; VAT equals "0,00 €"
    And the user provides valid email address "test@user.com"
    And the user clicks *Buy now* button
    And the *Invalid email* error does not appear
