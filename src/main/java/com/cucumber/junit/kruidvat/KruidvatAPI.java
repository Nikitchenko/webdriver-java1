package com.cucumber.junit.kruidvat;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class KruidvatAPI {

    public static final String BASE_URL = "https://www.kruidvat.nl";
    public static final String CART_ENDPOINT = "/api/v2/kvn/users/anonymous/carts";
    public static final String CART_ENTRIES_ENDPOINT = "/entries";
    public Map<String, String> sessionStorage = new HashMap<>();

    public String createKVNCart() {

        Response response = given().post(BASE_URL + CART_ENDPOINT);
        JsonPath jsonPathEvaluator = response.jsonPath();

        String sessionGuid = jsonPathEvaluator.get("guid");
        sessionStorage.put("guid", sessionGuid);

        return sessionGuid;
    }

    public void addTheProductToTheCart(String code, int quantity) {

        Cart cartPayload = new Cart();
        Product productPayload = new Product();
        productPayload.setCode(code);
        cartPayload.setProduct(productPayload);
        cartPayload.setQuantity(quantity);

        given().header("Content-type", "application/json")
                .body(cartPayload)
                .post(BASE_URL + CART_ENDPOINT + "/" + sessionStorage.get("guid") + CART_ENTRIES_ENDPOINT);
    }

    public Response getTheCartResponse() {

        return given().get(BASE_URL + CART_ENDPOINT + "/" + sessionStorage.get("guid") + CART_ENTRIES_ENDPOINT);
    }

}
