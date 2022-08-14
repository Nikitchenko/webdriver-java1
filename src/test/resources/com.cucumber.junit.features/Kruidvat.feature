Feature: Add a Product to the Basket on Kruidvat

  @addToKruidvat
  Scenario: product added via API call is in the Basket
    #Given Initial setup is made
    When Create Cart via API
    And Add product to Cart via API
    #Then Verify cart response has expected json schema
    And Verify cart response has expected quantity and product code
    #And Open web application "https://www.kruidvat.nl"
    #And Authenticate to web application by adding cookie "kvn-cart" with the guid to the browser via UI
    #And Navigate to Cart page
    #And Verify that cart contains expected product via UI
    #And (Optional) Calculate the time saving by running the test via UI only and using the hybrid approach (API + UI)
