package com.cucumber.junit.steps;

import com.cucumber.junit.pages.*;
import com.cucumber.junit.util.RegExParser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    public static Map<String, String> pageURLmap;

    static {
        pageURLmap = new HashMap<>();
        pageURLmap.put("Initial home page", "");
        pageURLmap.put("Search page", "/search");
        pageURLmap.put("Basket page", "/basket");
        pageURLmap.put("Checkout page", "/payment");
    }

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
                () -> assertEquals(checkoutPage.isInvalidEmailErrorMessageDisplayed(), neg.isEmpty(),
                        "Email error appeared.")
        );
    }

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
        homePage = new HomePage();
        homePage.openBookdepositoryWebsite();
    }

    @And("I open the {string}")
    public void iOpenThe(String page) {
        AbstractPage.openSitePage(pageURLmap.get(page));
    }

    @And("I search for {string}")
    public void iSearchFor(String book) {
        homePage.searchForBook(book);
        searchPage = homePage.searchBtnClickJS();
    }

    @And("I am redirected to the {string}")
    public void iAmRedirectedToA(String page) {

        String currentPageInnerURL = RegExParser.getInnerURL(AbstractPage.getURL());

        assertEquals(pageURLmap.get(page), currentPageInnerURL, "Incorrect URL");
    }

    @And("^Search results contain the following products$")
    public void searchResultsContainTheFollowingProducts(List<String> expectedBooks) {

        List<String> searchedBooks = searchPage.getListOfFirst30SearchedResults();

        assertAll("Check the Search Result",
                () -> assertTrue(searchedBooks.contains(expectedBooks.get(0)), "Search result does not contain " + expectedBooks.get(0) + "."),
                () -> assertTrue(searchedBooks.contains(expectedBooks.get(1)), "Search result does not contain " + expectedBooks.get(1) + "."),
                () -> assertTrue(searchedBooks.contains(expectedBooks.get(2)), "Search result does not contain " + expectedBooks.get(2) + "."),
                () -> assertTrue(searchedBooks.containsAll(expectedBooks), "Some of expected books are not present in the search result.")
        );
    }

    @And("^I apply the following search filters$")
    public void iApplyTheFollowingSearchFilters(DataTable table) {
        Map<String, String> filterValues = table.asMap(String.class, String.class);

        searchPage.selectByPrice(filterValues.get("Price range"));
        searchPage.selectByAvailability(filterValues.get("Availability"));
        searchPage.selectByLang(filterValues.get("Language"));
        searchPage.selectByFormat(filterValues.get("Format"));
        searchPage.refineSearchResults();
    }

    @And("^Search results contain only the following products$")
    public void searchResultsContainOnlyTheFollowingProducts(List<String> expectedBooks) {

        List<String> searchedBooks = searchPage.getListOfFirst30SearchedResults();

        assertEquals(expectedBooks, searchedBooks, "There is a difference between Expected and Actual Search Results");

    }

    @And("I click 'Add to basket' button for product with name {string}")
    public void iClickAddToBasketButtonForProductWithName(String bookName) {
        searchPage.bookAddToBasket(bookName);
    }

    @And("^I select 'Basket/Checkout' in basket pop-up$")
    public void iSelectBasketCheckoutInBasketPopUp() {
        searchPage.basketCheckout();
        basketPage = searchPage.basketCheckoutBtnClick();
    }

    @And("^Basket order summary is as following:$")
    public void basketOrderSummaryIsAsFollowing(@Transpose Map orderEntities) {

        assertAll("Check the Basket",
                () -> assertEquals(orderEntities.get("Delivery cost"), basketPage.getDeliveryCost(),
                        "Not expected Delivery Cost."),
                () -> assertEquals(orderEntities.get("Total"), basketPage.getTotalPrice(),
                        "Not expected Basket Total.")
        );
    }

    @And("^I click 'Checkout' button on 'Basket' page$")
    public void iClickCheckoutButtonOnBasketPage() {
        basketPage.checkoutBtnClick();
        checkoutPage = basketPage.checkoutPageOpened();
    }

    @When("^I click 'Buy now' button$")
    public void iClickBuyNowButton() {
        checkoutPage.buyNowBtnClickJS();
    }

    @Then("^the following validation error messages are displayed on 'Delivery Address' form:$")
    public void theFollowingValidationErrorMessagesAreDisplayedOnDeliveryAddressForm(DataTable table) {
        List<Map<String, String>> validationErrorMessages = table.asMaps(String.class, String.class);

        assertAll("Check the Validation Error Messages",

                () -> assertEquals(validationErrorMessages.get(0).get("validation error message"), checkoutPage.invalidEmailErrorMessage(),
                        validationErrorMessages.get(0).get("Form field name")
                                + ": Validation Error message is not displayed or differs from the Expected."),

                () -> assertEquals(validationErrorMessages.get(1).get("validation error message"), checkoutPage.invalidFullNameErrorMessage(),
                        validationErrorMessages.get(1).get("Form field name")
                                + ": Validation Error message is not displayed or differs from the Expected."),

                () -> assertEquals(validationErrorMessages.get(2).get("validation error message"), checkoutPage.invalidAddressLine1ErrorMessage(),
                        validationErrorMessages.get(2).get("Form field name")
                                + ": Validation Error message is not displayed or differs from the Expected."),

                () -> assertEquals(validationErrorMessages.get(3).get("validation error message"), checkoutPage.invalidCityErrorMessage(),
                        validationErrorMessages.get(3).get("Form field name")
                                + ": Validation Error message is not displayed or differs from the Expected."),

                () -> assertEquals(validationErrorMessages.get(4).get("validation error message"), checkoutPage.invalidPostcodeErrorMessage(),
                        validationErrorMessages.get(4).get("Form field name")
                                + ": Validation Error message is not displayed or differs from the Expected.")
        );
    }

    @And("^the following validation error messages are displayed on 'Payment' form:$")
    public void theFollowingValidationErrorMessagesAreDisplayedOnPaymentForm(DataTable table) {
        List<String> paymentValidationErrorMessages = table.asList(String.class);

        assertEquals(paymentValidationErrorMessages.get(0).replace(", ", "\n"),
                checkoutPage.paymentFieldsValidationErrorMessage(),
                "Validation Error message for Payment fields is not displayed or differs from the Expected.");

    }

    @And("^Checkout order summary is as following:$")
    public void checkoutOrderSummaryIsAsFollowing(@Transpose Map orderValues) {

        assertAll("Check the OrderSummary on Checkout page",
                () -> assertEquals(orderValues.get("Sub-total"), checkoutPage.getCheckoutSubtotal(),
                        "Not expected Checkout Subtotal."),
                () -> assertEquals(orderValues.get("Delivery"), checkoutPage.getCheckoutDelivery(),
                        "Not expected Checkout SDelivery."),
                () -> assertEquals(orderValues.get("VAT"), checkoutPage.getCheckoutVAT(),
                        "Not expected Checkout VAT."),
                () -> assertEquals(orderValues.get("Total"), checkoutPage.getCheckoutTotal(),
                        "Not expected Checkout Total.")
        );
    }

    @And("^I checkout as a new customer with email \"([\\w\\.]+[+\\w+]*@\\w+\\.\\w*\\.*\\w{3})\"$")
    public void iCheckoutAsANewCustomerWithEmail(String email) {
        checkoutPage.provideCheckoutEmail(email);
    }

    @And("^I fill delivery address information manually:$")
    public void iFillDeliveryAddressInformationManually(@Transpose Map deliveryAddressValues) {

        checkoutPage.provideFullName((String) deliveryAddressValues.get("Full name"));
        checkoutPage.setDeliveryCountry((String) deliveryAddressValues.get("Delivery country"));

        //checkoutPage.manualEntryAddressButtonClick();

        checkoutPage.provideAddressLinesActions((String) deliveryAddressValues.get("Address line 1"),
                (String) deliveryAddressValues.get("Address line 2"));

        checkoutPage.provideDeliveryCity((String) deliveryAddressValues.get("Town/City"));
        checkoutPage.provideDeliveryCounty((String) deliveryAddressValues.get("County/State"));

        checkoutPage.providePostcodeActions((String) deliveryAddressValues.get("Postcode"));
    }

    @Then("^there is no validation error messages displayed on 'Delivery Address' form$")
    public void thereIsNoValidationErrorMessagesDisplayedOnDeliveryAddressForm() {

        assertAll("Check the Error messages",
                () -> assertFalse(checkoutPage.isInvalidDeliveryFullNameMessageDisplayed(),
                        "Invalid Delivery Full Name message displayed."),
                () -> assertFalse(checkoutPage.isInvalidDeliveryAddressLine1MessageDisplayed(),
                        "Invalid Delivery Address Line 1 message displayed."),
                () -> assertFalse(checkoutPage.isInvalidDeliveryAddressLine2MessageDisplayed(),
                        "Invalid Delivery Address Line 2 message displayed."),
                () -> assertFalse(checkoutPage.isInvalidDeliveryCityMessageDisplayed(),
                        "Invalid Delivery City message displayed."),
                () -> assertFalse(checkoutPage.isInvalidDeliveryCountyMessageDisplayed(),
                        "Invalid Delivery County message displayed."),
                () -> assertFalse(checkoutPage.isInvalidDeliveryPostcodeMessageDisplayed(),
                        "Invalid Delivery Postcode message displayed.")
        );
    }

    @And("^I enter my card details$")
    public void iEnterMyCardDetails(DataTable table) {
        Map<String, String> creditCardValues = table.asMap(String.class, String.class);

        checkoutPage.provideCreditCardNumber(creditCardValues.get("cardNumber"));
        checkoutPage.provideExpirationDate(creditCardValues.get("Expiry Month") + creditCardValues.get("Expiry Year"));
        checkoutPage.provideCVV(creditCardValues.get("Cvv"));
    }


}
