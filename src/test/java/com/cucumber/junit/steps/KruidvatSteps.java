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

public class KruidvatSteps {

    KruidvatAPI kruidvatAPI = new KruidvatAPI();
    private KruidvatApp kruidvatApp;
    private KruidvatCart kruidvatCart;

    @When("Create Cart via API")
    public void createCartViaAPI() {
        kruidvatAPI.createKVNCart();

    }

    @And("Add product to Cart via API")
    public void addProductToCartViaAPI() throws JsonProcessingException {
        kruidvatAPI.addTheProductToTheCart();

    }

    @Then("Verify cart response has expected json schema")
    public void verifyCartResponseHasExpectedJsonSchema() {

        kruidvatAPI.getTheCart()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("kvn_schema.json"));
    }

    @And("Verify cart response has expected quantity and product code")
    public void verifyCartResponseHasExpectedQuantityAndProductCode() {
        //kruidvatAPI.getTheCart();

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
        Thread.sleep(3000);
        DriverManager.getDriver().manage().addCookie(new Cookie(cookie, (String) kruidvatAPI.session.get("guid"))); //Creates and adds the cookie
        Thread.sleep(3000);
        DriverManager.getDriver().navigate().refresh();
        Thread.sleep(3000);
    }

    @And("Navigate to Cart page")
    public void navigateToCartPage() {
        //kruidvatApp.miniCartBtnClick();
        DriverManager.getDriver().get("https://www.kruidvat.nl/cart");
    }

    @And("Verify that cart contains expected product via UI")
    public void verifyThatCartContainsExpectedProductViaUI() throws InterruptedException {
        Thread.sleep(3000);
        WebElement product = DriverManager.getDriver().findElement(By.xpath("//a[@class = 'product-summary__img-link']"));

        System.out.println(product.getAttribute("href"));
    }


}
