package com.cucumber.junit.kruidvat;

import com.google.gson.Gson;
import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class KruidvatAPI {

    private static final String baseURL = "https://www.kruidvat.nl";
    private static final String cartEndpoint = "/api/v2/kvn/users/anonymous/carts";
    private static final String cartEntriesEndpoint = "/entries";
    private static final String payload = "{\"product\": {\"code\": \"2876350\"},\"quantity\": 1}";
    public Map session = new HashMap<String, String>();
    private final String cartURL = baseURL + cartEndpoint + "/" + session.get("guid") + cartEntriesEndpoint;

    public static void main(String[] args) {
        //createKVNCart();
        //addTheProductToTheCart();
        //getTheCart();
    }

    public String createKVNCart() {

        Response response = given().post(baseURL + cartEndpoint);
        System.out.println("The status received: " + response.statusLine());

        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());

        JsonPath jsonPathEvaluator = response.jsonPath();
        String sessionGuid = jsonPathEvaluator.get("guid");
        session.put("guid", sessionGuid);
        System.out.println("guid received from Response " + sessionGuid);

        return sessionGuid;
    }


    public void addTheProductToTheCart() throws JsonProcessingException {
        Cart cartPayload = new Cart();

        Product productPayload = new Product();
        productPayload.setCode("2876350");
        cartPayload.setProduct(productPayload);
        cartPayload.setQuantity(1);


        Gson g = new Gson();
        String payloadJSON = g.toJson(payload);

        System.out.println("Session GUID: " + session.get("guid"));
        System.out.println(baseURL + cartEndpoint + "/" + session.get("guid") + cartEntriesEndpoint);
        System.out.println(cartPayload);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cartPayload);
        System.out.println(json);

        Response response = given()
                .header("Content-type", "application/json")
                .body(cartPayload).post(baseURL + cartEndpoint + "/" + session.get("guid") + cartEntriesEndpoint);
        //ResponseBody body = response.getBody();
        //System.out.println("Response Body is1: " + body.asString());
    }

    public String getTheCart() {
        System.out.println(session.get("guid"));
        Response response = given().get(baseURL + cartEndpoint + "/" + session.get("guid") + cartEntriesEndpoint);
        ResponseBody body = response.getBody();
        System.out.println("Response Body is2: " + body.asString());




        return body.asString();
    }

}
