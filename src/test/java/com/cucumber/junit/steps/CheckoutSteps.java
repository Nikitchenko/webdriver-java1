package com.cucumber.junit.steps;

import com.cucumber.junit.pages.BasketPage;
import com.cucumber.junit.pages.CheckoutPage;
import com.cucumber.junit.pages.PDPPAge;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    private PDPPAge pdpPage;
    private BasketPage basketPage;
    private CheckoutPage checkoutPage;



    @When("the user is on PDP of the book with ISBN {string}")
    public void openSpecificPDPOfTheBookWithISBN(String isbn) {
        pdpPage = new PDPPAge();
        pdpPage.openPDPPage(isbn);
    }

    @When("the user clicks on *Add to basket* button")
    public void clickOnAddToBasketButtonInPDP() {
        pdpPage.addToBasketBtnClick();
    }


    @When("the user clicks on *Basket-Checkout* button")
    public void clickOnBasketCheckoutButton() {
        basketPage = pdpPage.basketCheckoutBtnClick();
    }

    @Then("*Basket* page opens with correct Item Price {string} and Total Price {string}")
    public void basketPageWithCorrectPrices(String itemPrice, String totalPrice) {

        assertAll("Check the Basket",
                () -> assertEquals(itemPrice, basketPage.getItemTotal(),
                        "Not expected Basket Item Total."),
                () -> assertEquals(totalPrice, basketPage.getTotalPrice(),
                        "Not expected Basket Total.")
        );
    }

    @When("the user clicks on *Checkout* button")
    public void clickOnCheckoutButton() {
        checkoutPage = basketPage.checkoutBtnClick();
    }

    @Then("*Checkout* page opens with correct Item Price {string}, Total Price {string} and VAT {string}")
    public void checkoutPageWithCorrectPricesAndVAT(String itemPrice, String totalPrice, String vat) {

        assertAll("Check the OrderSummary on Checkout page",
                () -> assertEquals(itemPrice, checkoutPage.getCheckoutSubtotal(),
                        "Not expected Checkout Subtotal."),
                () -> assertEquals(totalPrice, checkoutPage.getCheckoutTotal(),
                        "Not expected Checkout Total."),
                () -> assertEquals(vat, checkoutPage.getCheckoutVAT(),
                        "Not expected Checkout VAT.")

        );

    }

    @When("the user provides valid email address {string}")
    public void userProvidesCorrectEmailAddress(String email) {
        checkoutPage.getCheckoutEmailAddressField().sendKeys(email);
    }

    @When("the user clicks *Buy now* button")
    public void userClicksBuyNowButton() {
        checkoutPage.getBuyNowBtn().click();
    }

    @Then("the *Invalid email* error does not appear")
    public void invalidEmailErrorDoesNotAppear() {

        assertAll("Check the Invalid Email Error",
                () -> assertFalse( checkoutPage.isInvalidErrorMessageDisplayed(),
                        "Email error appeared.")
        );
    }

}
