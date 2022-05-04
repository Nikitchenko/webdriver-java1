Feature: Checkout

  @checkout
  Scenario Outline: user goes through Checkout process
    Given the user is on PDP of the book with ISBN <isbn>
    When the user clicks on *Add to basket* button
    And the user clicks on *Basket-Checkout* button
    Then Basket page opens with correct values
      |itemPrice|totalPrice|
      |<itemPrice>|<totalPrice>|
    And the user clicks on *Checkout* button
    And Checkout page opens with correct values
      |itemPrice|totalPrice|vat|
      |<itemPrice>|<totalPrice>|<vat>|
    And the user provides valid email address "test@user.com"
    And the user clicks *Buy now* button
    And the *Invalid email* error does not appear

    Examples:
      |isbn|itemPrice|totalPrice|vat|
      |"9781783982707"|27,57 €|27,57 €|0,00 €|
      |"9780131872486"|79,18 €|79,18 €|0,00 €|
      |"9780596158101"|71,07 €|71,07 €|0,00 €|

