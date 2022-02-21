Feature: Checkout

  @checkout
  Scenario Outline: user goes through Checkout process
    Given the user is on PDP of the book with ISBN <isbn>; notice book price

    #Given the user is on PDP of the book with ISBN "9780131872486"; notice book price
    #Given the user is on PDP of the book with ISBN "9781783982707"; notice book price
    When the user clicks on *Add to basket* button
    #Then *Item added to your basket* pop-up opens
    And the user clicks on *Basket-Checkout* button
    And *Basket* page opens with correct Item Price and Total Price <price>
    And the user clicks on *Checkout* button
    And *Checkout* page opens with correct Item Price and Total Price <price>; VAT equals "0,00 €"
    And the user provides valid email address "test@user.com"
    And the user clicks *Buy now* button
    And the *Invalid email* error does not appear

    Examples:
      |isbn|price|
      |"9781783982707"|"27,80 €"|
      |"9780131872486"|"75,79 €"|
      |"9780596158101"|"71,66 €"|
      |"9781491946008"|"42,76 €"|
