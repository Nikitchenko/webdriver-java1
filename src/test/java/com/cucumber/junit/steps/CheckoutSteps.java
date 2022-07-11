package com.cucumber.junit.steps;

import com.cucumber.junit.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    private PDPPAge pdpPage;
    private BasketPage basketPage;
    private CheckoutPage checkoutPage;
    private HomePage homePage;
    private SearchPage searchPage;


    @When("^the (?:user|guest) is on PDP of the book with ISBN (\\d+)$")
    public void openSpecificPDPOfTheBookWithISBN(String isbn) {
        pdpPage = new PDPPAge();
        pdpPage.openPDPPage(isbn);
    }

    @When("^the (?:user|guest) clicks on Add to basket button$")
    public void clickOnAddToBasketButtonInPDP() {
        pdpPage.addToBasketBtnClick();
    }

    @When("^the (?:user|guest) clicks on Basket-Checkout button$")
    public void clickOnBasketCheckoutButton() {
        basketPage = pdpPage.basketCheckoutBtnClick();
    }

    @Then("^Basket page opens with correct values$")
    public void basketPageWithCorrectValues(DataTable table) {

        List<Map<String, String>> prices = table.asMaps(String.class, String.class);

        assertAll("Check the Basket",
                () -> assertEquals(prices.get(0).get("itemPrice"), basketPage.getItemTotal(),
                        "Not expected Basket Item Total."),
                () -> assertEquals(prices.get(0).get("totalPrice"), basketPage.getTotalPrice(),
                        "Not expected Basket Total.")
        );
    }

    @When("^the (?:user|guest) clicks on Checkout button$")
    public void clickOnCheckoutButton() {
        basketPage.checkoutBtnClick();
        checkoutPage = basketPage.checkoutPageOpened();
    }

    @Then("^Checkout page opens with correct values$")
    public void checkoutPageWithCorrectValues(DataTable table) {

        List<Map<String, String>> values = table.asMaps(String.class, String.class);

        assertAll("Check the OrderSummary on Checkout page",
                () -> assertEquals(values.get(0).get("itemPrice"), checkoutPage.getCheckoutSubtotal(),
                        "Not expected Checkout Subtotal."),
                () -> assertEquals(values.get(0).get("totalPrice"), checkoutPage.getCheckoutTotal(),
                        "Not expected Checkout Total."),
                () -> assertEquals(values.get(0).get("vat"), checkoutPage.getCheckoutVAT(),
                        "Not expected Checkout VAT.")
        );
    }

    @When("^the (?:user|guest) provides valid email address \"([\\w\\.]+[+\\w+]*@\\w+\\.\\w*\\.*\\w{3})\"$")
    public void userProvidesCorrectEmailAddress(String email) {
        checkoutPage.getCheckoutEmailAddressField().sendKeys(email);
    }

    @When("^the (?:user|guest) clicks Buy now button$")
    public void userClicksBuyNowButton() {
        checkoutPage.getBuyNowBtn().click();
    }

    @Then("^the Invalid email error( does not|) appear$")
    public void invalidEmailErrorDoesNotAppear(String neg) {

        assertAll("Check the Invalid Email Error",
                () -> assertEquals(checkoutPage.isInvalidErrorMessageDisplayed(), neg.isEmpty(),
                        "Email error appeared.")
        );
    }

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
        //to do ?
    }

    @And("I open the {string}")
    public void iOpenThe(String arg0) {
        homePage = new HomePage();
        homePage.openBookdepositoryWebsite();
    }

    @And("I search for {string}")
    public void iSearchFor(String book) {
        homePage.searchForBook(book);
    }

    @And("I am redirected to a {string}")
    public void iAmRedirectedToA(String arg0) throws InterruptedException {
        Thread.sleep(1000);
        searchPage = homePage.searchBtnClick();
        //Thread.sleep(1000);
    }

    @And("^Search results contain the following products$")
    public void searchResultsContainTheFollowingProducts(DataTable table) {

        List<String> expectedBooks = table.asList(String.class);
        List<String> searchedBooks = searchPage.getListOfFirst30SearchedResults();

        assertAll("Check the Search Result",
                () -> assertTrue(searchedBooks.contains(expectedBooks.get(0)), "Search result does not contain 'Thinking in Java'."),
                () -> assertTrue(searchedBooks.contains(expectedBooks.get(1)), "Search result does not contain 'Thinking in Java Part I'."),
                () -> assertTrue(searchedBooks.contains(expectedBooks.get(2)), "Search result does not contain 'Core Java Professional'.")
                //() -> assertTrue(searchedBooks.contains(expectedBooks), "Search result does not contain one of expected values.")
        );
    }

    @And("^I apply the following search filters$")
    public void iApplyTheFollowingSearchFilters(DataTable table) throws InterruptedException {
        Map<String, String> filterValues = table.asMap(String.class, String.class);

        searchPage.selectByPrice(filterValues.get("Price range"));
        searchPage.selectByAvailability(filterValues.get("Availability"));
        searchPage.selectByLang(filterValues.get("Language"));
        searchPage.selectByFormat(filterValues.get("Format"));
        searchPage.refineSearchResults();
    }

    @And("^Search results contain only the following products$")
    public void searchResultsContainOnlyTheFollowingProducts(DataTable table) {
        List<String> expectedBooks = table.asList(String.class);
        List<String> searchedBooks = searchPage.getListOfFirst30SearchedResults();

        assertAll("Check the Search Result",
                () -> assertArrayEquals(new List[]{expectedBooks}, new List[]{searchedBooks},
                        "There is a difference between expected and actual Search Results")
        );
    }

    @And("I click 'Add to basket' button for product with name {string}")
    public void iClickAddToBasketButtonForProductWithName(String bookName) {
        searchPage.bookAddToBasket(bookName);
    }

    @And("^I select 'Basket/Checkout' in basket pop-up$")
    public void iSelectBasketCheckoutInBasketPopUp() {
        searchPage.basketCheckout();
    }

    @And("I am redirected to a Basket page")
    public void iAmRedirectedToABasketPage() {
        basketPage = searchPage.basketCheckoutBtnClick();
    }

    @And("^Basket order summary is as following:$")
    public void basketOrderSummaryIsAsFollowing(DataTable table) {
        List<Map<String, String>> orderEntities = table.asMaps(String.class, String.class);

        assertAll("Check the Basket",
                () -> assertEquals(orderEntities.get(0).get("Delivery cost"), basketPage.getDeliveryCost(),
                        "Not expected Delivery Cost."),
                () -> assertEquals(orderEntities.get(0).get("Total"), basketPage.getTotalPrice(),
                        "Not expected Basket Total.")
        );
    }

    @And("^I click 'Checkout' button on 'Basket' page$")
    public void iClickCheckoutButtonOnBasketPage() {
        basketPage.checkoutBtnClick();
    }

    @And("^I checkout as a new customer with email \"([\\w\\.]+[+\\w+]*@\\w+\\.\\w*\\.*\\w{3})\"$")
    public void iCheckoutAsANewCustomerWithEmail(String email) {
        checkoutPage = basketPage.checkoutPageOpened();
        checkoutPage.provideCheckoutEmail(email);

        //test thing
        checkoutPage.provideCheckoutPhone("7700900077");
    }

    @And("^Checkout order summary is as following:$")
    public void checkoutOrderSummaryIsAsFollowing(DataTable table) {
        List<Map<String, String>> OrderValues = table.asMaps(String.class, String.class);

        assertAll("Check the OrderSummary on Checkout page",
                () -> assertEquals(OrderValues.get(0).get("Sub-total"), checkoutPage.getCheckoutSubtotal(),
                        "Not expected Checkout Subtotal."),
                () -> assertEquals(OrderValues.get(0).get("Delivery"), checkoutPage.getCheckoutDelivery(),
                        "Not expected Checkout SDelivery."),
                () -> assertEquals(OrderValues.get(0).get("VAT"), checkoutPage.getCheckoutVAT(),
                        "Not expected Checkout VAT."),
                () -> assertEquals(OrderValues.get(0).get("Total"), checkoutPage.getCheckoutTotal(),
                        "Not expected Checkout Total.")

        );
    }

    @And("^I fill delivery address information manually:$")
    public void iFillDeliveryAddressInformationManually(DataTable table) {
        List<Map<String, String>> deliveryAddressValues = table.asMaps(String.class, String.class);
        checkoutPage.provideFullName(deliveryAddressValues.get(0).get("Full name"));
        checkoutPage.setDeliveryCountry(deliveryAddressValues.get(0).get("Delivery country"));
        checkoutPage.manualEntryAddressButtonClick();

        checkoutPage.provideDeliveryAddressLine1(deliveryAddressValues.get(0).get("Address line 1"));
        checkoutPage.provideDeliveryAddressLine2(deliveryAddressValues.get(0).get("Address line 2"));
        checkoutPage.provideDeliveryCity(deliveryAddressValues.get(0).get("Town/City"));
        checkoutPage.provideDeliveryCounty(deliveryAddressValues.get(0).get("County/State"));
        checkoutPage.provideDeliveryPostcode(deliveryAddressValues.get(0).get("Postcode"));

    }

    @And("^'Payment' section is disabled for editing$")
    public void paymentSectionIsDisabledForEditing() {
        // to do
    }

    @When("^I press 'Continue to payment' button on checkout$")
    public void iPressContinueToPaymentButtonOnCheckout() {
        // is it correct?
        checkoutPage.buyNowBtnClick();

    }

    @And("^'Delivery Address' and 'Billing Address' sections are disabled for editing$")
    public void deliveryAddressAndBillingAddressSectionsAreDisabledForEditing() {
        //to do
    }

    @And("^I enter my card details$")
    public void iEnterMyCardDetails(DataTable table) throws InterruptedException {
        Map<String, String> creditCardValues = table.asMap(String.class, String.class);

        checkoutPage.provideCreditCardNumber(creditCardValues.get("cardNumber"));
        checkoutPage.provideExpirationDate(creditCardValues.get("Expiry Month") + creditCardValues.get("Expiry Year"));
        checkoutPage.provideCVV(creditCardValues.get("Cvv"));
        Thread.sleep(1000);

    }
}
