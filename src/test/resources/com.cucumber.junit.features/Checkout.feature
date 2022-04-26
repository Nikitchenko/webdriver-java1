Feature: Checkout

  @checkout
  Scenario Outline: user goes through Checkout process
    Given the user is on PDP of the book with ISBN <isbn>
    When the user clicks on *Add to basket* button
    And the user clicks on *Basket-Checkout* button
    Then *Basket* page opens with correct Item Price <itemPrice> and Total Price <totalPrice>
    And the user clicks on *Checkout* button
    And *Checkout* page opens with correct Item Price <itemPrice>, Total Price <totalPrice> and VAT <vat>
    And the user provides valid email address "test@user.com"
    And the user clicks *Buy now* button
    And the *Invalid email* error does not appear

    Examples:
      |isbn|itemPrice|totalPrice|vat|
      |"9781783982707"|"27,58 €"|"27,58 €"|"0,00 €"|
      |"9780131872486"|"77,88 €"|"77,88 €"|"0,00 €"|
      |"9780596158101"|"71,09 €"|"71,09 €"|"0,00 €"|

