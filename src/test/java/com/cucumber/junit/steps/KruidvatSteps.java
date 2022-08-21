package com.cucumber.junit.steps;

import com.cucumber.junit.driver.DriverManager;
import com.cucumber.junit.kruidvat.KruidvatAPI;
import com.cucumber.junit.kruidvat.KruidvatApp;
import com.cucumber.junit.kruidvat.KruidvatCart;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class KruidvatSteps {

    KruidvatAPI kruidvatAPI = new KruidvatAPI();
    private KruidvatApp kruidvatApp;
    private KruidvatCart kruidvatCart;

    @When("Create Cart via API")
    public void createCartViaAPI() {
        kruidvatAPI.createKVNCart();
    }

    @And("Add product with code {string} and quantity {string} to Cart via API")
    public void addProductToCartViaAPI(String code, String  quantity) throws JsonProcessingException {

        kruidvatAPI.addTheProductToTheCart(code, Integer.parseInt(quantity));
    }

    @Then("Verify cart response has expected json schema")
    public void verifyCartResponseHasExpectedJsonSchema() {

        kruidvatAPI.getTheCart()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("kvn_schema.json"));
    }

    @And("Verify cart response has expected product with code {string} and quantity {string}")
    public void verifyCartResponseHasExpectedQuantityAndProductCode(String code, String quantity) {

        kruidvatAPI.getTheCart()
                .then()
                .assertThat()
                .body("size()", is(1))
                .body("orderEntries[0].entryNumber", equalTo(0))
                .body("orderEntries[0].quantity", equalTo(Integer.parseInt(quantity)))
                .body("orderEntries[0].product.code", equalTo(code));
    }

    @And("Open web application {string}")
    public void openWebApplication(String url) throws InterruptedException {
        //KruidvatAbstract.openSitePage(url);
        DriverManager.getDriver().get(url);
        Thread.sleep(1000);
    }

    @And("Authenticate to web application by adding cookie {string} with the guid to the browser via UI")
    public void authenticateToWebApplicationByAddingCookieWithValueToTheBrowserViaUI(String cookie) throws InterruptedException {
        //KruidvatAbstract.clearAllCookies();
        //KruidvatAbstract.addTheCookie(cookie, guid);
        DriverManager.getDriver().manage().deleteAllCookies(); // Deletes all the cookies
        //Thread.sleep(1000);
        DriverManager.getDriver().manage().addCookie(new Cookie(cookie, (String) kruidvatAPI.session.get("guid"))); //Creates and adds the cookie
        Thread.sleep(1000);
        DriverManager.getDriver().navigate().refresh();
        //Thread.sleep(1000);
    }

    @And("Navigate to Cart page")
    public void navigateToCartPage() {
        //kruidvatApp.miniCartBtnClick();
        DriverManager.getDriver().get("https://www.kruidvat.nl/cart");
    }

    @And("Verify in UI that cart contains expected expected product with code {string} and quantity {string}")
    public void verifyThatCartContainsExpectedProductViaUI(String code, String quantity ) throws InterruptedException {
        Thread.sleep(2000);

        WebElement product = DriverManager.getDriver().findElement(By.xpath("//a[@class = 'product-summary__img-link']"));
        WebElement counter = DriverManager.getDriver().findElement(By.xpath("//span[@class = 'minicart__quantity-badge-number']"));

        assertAll("Check the Basket",
                () -> assertTrue(product.getAttribute("href").contains(code), "Not expected code"),
                () -> assertEquals(quantity, counter.getText(), "More products in the Cart than needed.")
        );
    }


}
