package com.cucumber.junit.steps;

import com.cucumber.junit.pages.HomePage;
import com.cucumber.junit.pages.PDPPAge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    private PDPPAge pdpPage = new PDPPAge();
    private String bookPrice;

    @When("the user is on PDP of the book with ISBN {string}")
    public void theUserIsOnOfTheBookWith(String isbn) {
        pdpPage.openPDPPage(isbn);
        System.out.println(pdpPage.getSalePrice().getText());
        bookPrice = pdpPage.getSalePrice().getText();

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
        pdpPage.getBasketCheckoutLink().click();
    }

    @Then("the {string} page opens with correct {string}")
    public void thePageOpensWithCorrect(String basket, String total) {
    }
}
