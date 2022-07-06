package com.cucumber.junit.steps;

import com.cucumber.junit.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
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
        checkoutPage = basketPage.checkoutBtnClick();
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
        searchPage = homePage.searchBtnClick();
        Thread.sleep(1000);
    }

    @And("^Search results contain the following products$")
    public void searchResultsContainTheFollowingProducts(DataTable table) {

        List<String> expectedBooks = table.asList( String.class);
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
    public void searchResultsContainOnlyTheFollowingProducts(DataTable table) throws InterruptedException {
        List<String> expectedBooks = table.asList( String.class);
        List<String> searchedBooks = searchPage.getListOfFirst30SearchedResults();

        assertAll("Check the Search Result",
                () -> assertArrayEquals(new List[]{expectedBooks}, new List[]{searchedBooks},
                        "There is a difference between expected and actual Search Results")

        );
        Thread.sleep(3000);
    }
}
