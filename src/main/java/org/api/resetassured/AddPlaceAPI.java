package org.api.resetassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddPlaceAPI {
    public static <JsonArray, JsonObject> void main(String[] args) {
        //validate if AddPlaceAPI is working as expected
        //given ---> all input details
        //when ---> submit the api resource and http method
        //then ---> validate the response

        // End to End scenario like add a place update and get a place

        //ADD place API
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String addApiResponse=given().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payloads.addPlacePayload()).
                when().post("maps/api/place/add/json").
                then().statusCode(200).extract().body().asPrettyString();
        System.out.println(addApiResponse);

        //Update place API


        //Get place API
    }
}
