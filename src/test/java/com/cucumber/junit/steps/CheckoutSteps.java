package com.cucumber.junit.steps;

import com.cucumber.junit.pages.BasketPage;
import com.cucumber.junit.pages.CheckoutPage;
import com.cucumber.junit.pages.PDPPAge;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {

    private PDPPAge pdpPage;
    private BasketPage basketPage;
    private CheckoutPage checkoutPage;

    private String bookPricePDP;


    @When("the user is on PDP of the book with ISBN {string}; notice book price")
    public void openSpecificPDPOfTheBookWithISBN(String isbn) {
        pdpPage = new PDPPAge();
        pdpPage.openPDPPage(isbn);
        bookPricePDP = pdpPage.getSalePrice();
    }

    @When("the user clicks on *Add to basket* button")
    public void clickOnAddToBasketButtonInPDP() {
        pdpPage.getAddToBasketBtn().click();
    }

    @Then("*Item add to your basket* pop-up opens")
    public void itemAddToYourBasketPopUpOpens() {
    }

    @When("the user clicks on *Basket-Checkout* button")
    public void clickOnBasketCheckoutButton() {
        basketPage = pdpPage.basketCheckoutLinkClick();
    }

    @Then("*Basket* page opens with correct Item Price and Total Price - same as on PDP")
    public void basketPageWithCorrectPrices() {

        assertAll("Check the Basket",
                () -> assertEquals(bookPricePDP, basketPage.getItemTotal(),
                        "Not expected Basket Item Total."),
                () -> assertEquals(bookPricePDP, basketPage.getTotalPrice(),
                        "Not expected Basket Total.")
        );
    }

    @When("the user clicks on *Checkout* button")
    public void clickOnCheckoutButton() {
        checkoutPage = basketPage.checkoutBtnClick();
    }

    @Then("*Checkout* page opens with correct Item Price and Total Price - same as on PDP; VAT equals {string}")
    public void checkoutPageWithCorrectPricesAndVAT(String vat) {

        assertAll("Check the OrderSummary on Checkout page",
                () -> assertEquals(bookPricePDP, checkoutPage.getCheckoutSubtotal(),
                        "Not expected Checkout Subtotal."),
                () -> assertEquals(vat, checkoutPage.getCheckoutVAT(),
                        "Not expected Checkout VAT."),
                () -> assertEquals(bookPricePDP, checkoutPage.getCheckoutTotal(),
                        "Not expected Checkout Total.")
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
                () -> assertEquals("", checkoutPage.getInvalidErrorMessage(),
                        "Email error appeared.")
        );
    }

}
