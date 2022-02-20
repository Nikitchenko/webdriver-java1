package com.cucumber.junit.steps;

import com.cucumber.junit.pages.BasketPage;
import com.cucumber.junit.pages.CheckoutPage;
import com.cucumber.junit.pages.PDPPAge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {

    private PDPPAge pdpPage;
    private BasketPage basketPage;
    private CheckoutPage checkoutPage;

    private String bookPricePDP;

    private String basketItemTotal;
    private String basketTotal;

    private String checkoutSubtotal;
    private String checkoutVAT;
    private String VAT = "0,00 €";
    private String checkoutTotal;


    @When("the user is on PDP of the book with ISBN {string}")
    public void openSpecificPDPOfTheBookWithISBN(String isbn) {
        pdpPage = new PDPPAge();
        pdpPage.openPDPPage(isbn);
        System.out.println(pdpPage.getSalePrice().getText());
        bookPricePDP = pdpPage.getSalePrice().getText();
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

    @Then("*Basket* page opens with correct Item Price and Total Price")
    public void basketPageWithCorrectPrices() {
        basketItemTotal = basketPage.getItemTotal().getText();
        basketTotal = basketPage.getTotalPrice().getText();
        System.out.println(basketItemTotal);
        System.out.println(basketTotal);

        assertAll("Check the Basket",
                () -> assertEquals(bookPricePDP, basketItemTotal,
                        "Not expected Basket Item Total."),
                () -> assertEquals(bookPricePDP, basketTotal,
                        "Not expected Basket Total.")
        );
    }

    @When("the user clicks on *Checkout* button")
    public void clickOnCheckoutButton() {
        checkoutPage = basketPage.checkoutBtnClick();
    }

    @Then("*Checkout* page opens with correct Item Price, Total Price and VAT")
    public void checkoutPageWithCorrectPricesAndVAT() {
        checkoutSubtotal = checkoutPage.getCheckoutSubtotalElem().getText();
        checkoutVAT = checkoutPage.getCheckoutVATElem().getText();
        checkoutTotal = checkoutPage.getCheckoutTotalElem().getText();

        assertAll("Check the OrderSummary on Checkout page",
                () -> assertEquals(bookPricePDP, checkoutSubtotal,
                        "Not expected Checkout Subtotal."),
                () -> assertEquals(VAT, checkoutVAT,
                        "Not expected Checkout VAT."),
                () -> assertEquals(bookPricePDP, checkoutTotal,
                        "Not expected Checkout Total.")
        );

    }

    @When("the user provides correct email address {string}")
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
                () -> assertEquals("", checkoutPage.getInvalidErrorMessage().getText(),
                        "Email error appeared.")
        );
    }
}
