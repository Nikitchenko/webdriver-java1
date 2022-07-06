Feature: Checkout

  @checkout
  Scenario Outline: user goes through Checkout process
    Given the user is on PDP of the book with ISBN <isbn>
    When the user clicks on Add to basket button
    And the user clicks on Basket-Checkout button
    Then Basket page opens with correct values
      |itemPrice|totalPrice|
      |<itemPrice>|<totalPrice>|
    And the user clicks on Checkout button
    And Checkout page opens with correct values
      |itemPrice|totalPrice|vat|
      |<itemPrice>|<totalPrice>|<vat>|
    And the user provides valid email address "test@user.com"
    And the user clicks Buy now button
    And the Invalid email error does not appear

    Examples:
      |isbn|itemPrice|totalPrice|vat|
      |9781783982707|32,50 €|32,50 €|0,00 €|
      |9780131872486|80,47 €|80,47 €|0,00 €|
      |9780596158101|75,54 €|75,54 €|0,00 €|

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
      | Price range  | 30 € +        |
      | Availability | In Stock (5)  |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    And Search results contain only the following products
      | Thinking in Java                                                      |
      | Think Java                             |
      | Thinking Recursively with Java         |
      | Java and Algorithmic Thinking for the Complete Beginner (2nd Edition)|
      | Core Java Professional                |

