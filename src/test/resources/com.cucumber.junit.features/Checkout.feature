Feature: Checkout

  @checkout
  Scenario Outline: user goes through Checkout process
    Given the user is on PDP of the book with ISBN <isbn>
    When the user clicks on Add to basket button
    And the user clicks on Basket-Checkout button
    Then Basket page opens with correct values
      | itemPrice   | totalPrice   |
      | <itemPrice> | <totalPrice> |
    And the user clicks on Checkout button
    And Checkout page opens with correct values
      | itemPrice   | totalPrice   | vat   |
      | <itemPrice> | <totalPrice> | <vat> |
    And the user provides valid email address "test@user.com"
    And the user clicks Buy now button
    And the Invalid email error does not appear

    Examples:
      | isbn          | itemPrice | totalPrice | vat    |
      | 9781783982707 | 32,50 €   | 32,50 €    | 0,00 € |
      | 9780131872486 | 80,47 €   | 80,47 €    | 0,00 € |
      | 9780596158101 | 75,54 €   | 75,54 €    | 0,00 € |


  @regression
  Scenario: Proceed to checkout, final review and place order as guest user
    Given I am an anonymous customer with clear cookies
    And I open the "Initial home page"
    And I search for "Thinking in Java"
    And I am redirected to a "Search page"
    And Search results contain the following products
      | Thinking in Java       |
      | Thinking Java Part I   |
      | Core Java Professional |
    And I apply the following search filters
      | Price range  | 30 € +         |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    And Search results contain only the following products
      | Thinking in Java                                                  |
      | Think Java                                                        |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE) |
      | Think Data Structures                                             |
    And I click 'Add to basket' button for product with name "Thinking in Java"
    And I select 'Basket/Checkout' in basket pop-up
    And I am redirected to a Basket page
    And Basket order summary is as following:
      | Delivery cost | Total   |
      | FREE          | 81,99 € |
    And I click 'Checkout' button on 'Basket' page
    And I checkout as a new customer with email "test@user.com"
    And Checkout order summary is as following:
      | Sub-total | Delivery | VAT    | Total   |
      | 81,99 €   | FREE     | 0,00 € | 81,99 € |
    And I fill delivery address information manually:
      | Full name | Delivery country | Address line 1   | Address line 2   | Town/City | County/State | Postcode |
      | John      | Poland           | Random address 1 | Random address 2 | Kyiv      | Random State | 111111   |
    And 'Payment' section is disabled for editing
    When I press 'Continue to payment' button on checkout
    And 'Delivery Address' and 'Billing Address' sections are disabled for editing
    And I enter my card details
      | Card Type    | Visa             |
      | Name On Card | RandomName       |
      | cardNumber   | 4111111111111111 |
      | Expiry Year  | 2022             |
      | Expiry Month | 03               |
      | Cvv          | 123              |


