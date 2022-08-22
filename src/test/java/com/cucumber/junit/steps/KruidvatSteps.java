package com.cucumber.junit.steps;

import com.cucumber.junit.driver.DriverManager;
import com.cucumber.junit.kruidvat.KruidvatAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class KruidvatSteps {

    KruidvatAPI kruidvatAPI = new KruidvatAPI();

    @When("Create Cart via API")
    public void createCartViaAPI() {

        kruidvatAPI.createKVNCart();
    }

    @And("Add product with code {string} and quantity {string} to Cart via API")
    public void addProductToCartViaAPI(String code, String quantity) {

        kruidvatAPI.addTheProductToTheCart(code, Integer.parseInt(quantity));
    }

    @Then("Verify cart response has expected json schema")
    public void verifyCartResponseHasExpectedJsonSchema() {

        kruidvatAPI.getTheCartResponse()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("kvn_schema.json"));
    }

    @And("Verify cart response has expected product with code {string} and quantity {string}")
    public void verifyCartResponseHasExpectedQuantityAndProductCode(String code, String quantity) {

        kruidvatAPI.getTheCartResponse()
                .then()
                .assertThat()
                .body("orderEntries[0].quantity", equalTo(Integer.parseInt(quantity)))
                .body("orderEntries[0].product.code", equalTo(code))
                .body("orderEntries[1]", equalTo(null)); // there are no other products in the Cart
    }

    @And("Open web application {string}")
    public void openWebApplication(String url) {

        DriverManager.getDriver().get(url);
    }

    @And("Authenticate to web application by adding cookie {string} with the guid to the browser via UI")
    public void authenticateToWebApplicationByAddingCookieWithValueToTheBrowserViaUI(String cookie) throws InterruptedException {

        DriverManager.getDriver().manage()
                .deleteAllCookies();
        DriverManager.getDriver().manage()
                .addCookie(new Cookie(cookie, kruidvatAPI.sessionStorage.get("guid")));
        DriverManager.getDriver().navigate()
                .refresh();
    }

    @And("Navigate to Cart page {string}")
    public void navigateToCartPage(String cartUrl) {

        DriverManager.getDriver().get(cartUrl);
    }

    @And("Verify in UI that cart contains expected expected product with code {string} and quantity {string}")
    public void verifyThatCartContainsExpectedProductViaUI(String code, String quantity) throws InterruptedException {
        Thread.sleep(1000);
        WebElement product = DriverManager.getDriver().findElement(By.xpath("//a[@class = 'product-summary__img-link']"));
        WebElement counter = DriverManager.getDriver().findElement(By.xpath("//span[@class = 'minicart__quantity-badge-number']"));

        assertAll("Check the Basket",
                () -> assertTrue(product.getAttribute("href").contains(code), "Not expected code"),
                () -> assertEquals(quantity, counter.getText(), "More products in the Cart than needed.")
        );
    }

}
