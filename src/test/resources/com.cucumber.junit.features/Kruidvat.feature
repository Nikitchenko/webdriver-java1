Feature: Add a Product to the Basket on Kruidvat

  @addToKruidvat
  Scenario: product added via API call is in the Basket
    When Create Cart via API
    And Add product with code "2876350" and quantity "1" to Cart via API
    Then Verify cart response has expected json schema
    And Verify cart response has expected product with code "2876350" and quantity "1"
    And Open web application "https://www.kruidvat.nl"
    And Authenticate to web application by adding cookie "kvn-cart" with the guid to the browser via UI
    And Navigate to Cart page
    And Verify in UI that cart contains expected expected product with code "2876350" and quantity "1"
