package com.cucumber.junit.kruidvat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public static final String BASE_URL = "https://www.kruidvat.nl";
    public static final String CART_ENDPOINT = "/api/v2/kvn/users/anonymous/carts";
    public static final String CART_ENTRIES_ENDPOINT = "/entries";
    public static final String payload = "{\"product\": {\"code\": \"2876350\"},\"quantity\": 1}";
    public Map session = new HashMap<String, String>();
    //private final String cartURL = BASE_URL + CART_ENDPOINT + "/" + session.get("guid") + CART_ENTRIES_ENDPOINT;
 

    public String createKVNCart() {

        Response response = given().post(BASE_URL + CART_ENDPOINT);
        System.out.println("The status received: " + response.statusLine());

        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());

        JsonPath jsonPathEvaluator = response.jsonPath();
        String sessionGuid = jsonPathEvaluator.get("guid");
        session.put("guid", sessionGuid);
        System.out.println("guid received from Response " + sessionGuid);

        return sessionGuid;
    }


    public void addTheProductToTheCart(String code, int  quantity) throws JsonProcessingException {

        Cart cartPayload = new Cart();
        Product productPayload = new Product();
        productPayload.setCode(code);
        cartPayload.setProduct(productPayload);
        cartPayload.setQuantity(quantity);


        //Gson g = new Gson();
        //String payloadJSON = g.toJson(payload);

        System.out.println("Session GUID: " + session.get("guid"));
        System.out.println(BASE_URL + CART_ENDPOINT + "/" + session.get("guid") + CART_ENTRIES_ENDPOINT);
        System.out.println(cartPayload);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cartPayload);
        System.out.println(json);

       given()
                .header("Content-type", "application/json")
                .body(cartPayload).post(BASE_URL + CART_ENDPOINT + "/" + session.get("guid") + CART_ENTRIES_ENDPOINT);


        //ResponseBody body = response.getBody();
        //System.out.println("Response Body is1: " + body.asString());
    }

    public Response getTheCart() {
        System.out.println(session.get("guid"));
        Response response = given().get(BASE_URL + CART_ENDPOINT + "/" + session.get("guid") + CART_ENTRIES_ENDPOINT);

        ResponseBody body = response.getBody();
        System.out.println("Response Body is2: " + body.asString());

        return response;

    }

    public JsonObject jObjectFromString(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        return jsonObject;
    }


    public void getQuantity() throws NoSuchFieldException, IllegalAccessException {


    }

}
