package com.cucumber.junit.steps;

import com.cucumber.junit.pages.BasketPage;
import com.cucumber.junit.pages.CheckoutPage;
import com.cucumber.junit.pages.PDPPAge;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    private PDPPAge pdpPage;
    private BasketPage basketPage;
    private CheckoutPage checkoutPage;


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

}
