package com.cucumber.junit.steps;

import com.cucumber.junit.pages.BasketPage;
import com.cucumber.junit.pages.HomePage;
import com.cucumber.junit.pages.PDPPAge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.sql.DriverManager;
import java.util.Base64;

import static com.cucumber.junit.constants.Constants.BASKET_URL;


import static org.junit.jupiter.api.Assertions.*;



import static com.cucumber.junit.constants.Constants.THE_BOOK_ISBN13;


public class CheckoutSteps {

    private PDPPAge pdpPage;
    private String bookPricePDP;
    private BasketPage basketPage;
    private String basketTotal;
    private String basketItemTotal;



    @When("the user is on PDP of the book with ISBN {string}")
    public void theUserIsOnOfTheBookWith(String isbn) {
        pdpPage = new PDPPAge();
        pdpPage.openPDPPage(isbn);
        System.out.println(pdpPage.getSalePrice().getText());
        bookPricePDP = pdpPage.getSalePrice().getText();
    }

    @When("users clicks on {string} button")
    public void usersClicksOnButton(String addToBtn) {
       //assertNotNull( pdpPage.getAddToBasketBtn(), " Add to the Basket Button not exist (probably the the Book is OOS). ");
       pdpPage.getAddToBasketBtn().click();
    }

    @Then("{string} pop-up appears")
    public void popUpAppears(String itemAddedPopup) {
    }

    @Then("the user clicks on {string} button")
    public void theUserClicksOnButton(String basketCheckout) {
        basketPage = pdpPage.basketCheckoutLinkClick();
    }

    @Then("the {string} page opens with correct {string}")
    public void thePageOpensWithCorrect(String basket, String total) {
        basketPage.openBasketPage();
        basketTotal = basketPage.getTotalPrice().getText();
        basketItemTotal = basketPage.getItemTotal().getText();
        System.out.println(basketTotal);
        System.out.println(basketItemTotal);

        assertAll("Check the Basket",
                () -> assertEquals(bookPricePDP, basketItemTotal,
                        "Not expected Basket Item Total."),
                () -> assertEquals(bookPricePDP, basketTotal,
                        "Not expected Basket Total.")
        );
    }



}
