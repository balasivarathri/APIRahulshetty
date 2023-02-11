package org.api.resetassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static org.hamcrest.Matcher.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceAPI {
    public static void main(String[] args) {
        //validate if AddPlaceAPI is working as expected
        //given ---> all input details
        //when ---> submit the api resource and http method
        //then ---> validate the response

        // End to End scenario like add a place update and get a place

        //ADD place API
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String addApiResponse=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payloads.addPlacePayload()).
                when().post("maps/api/place/add/json").
                then().statusCode(200).extract().body().asPrettyString();
        System.out.println(addApiResponse);
        String placeId= ReUsableMethods.rawToJson(addApiResponse).getString("place_id");
        System.out.println("Place id is : "+placeId);
        //Update place API
        String expectedAddress = "85 Tulip Gardens, RSA";
        String updateApiResponse = given().log().all().queryParam("key","qaclick123").body("{\n" +
                "\"place_id\":\""+placeId+"\",\n" +
                "\"address\":\""+expectedAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n").header("Content-Type","application/json").
                when().put("maps/api/place/update/json").
                then().assertThat().body("msg",equalTo("Address successfully updated")).extract().response().asPrettyString();
        System.out.println(updateApiResponse);

        //Get place API
        String getApiResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId).
                when().get("maps/api/place/get/json").
                then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(getApiResponse);
        String actualAddress = ReUsableMethods.rawToJson(getApiResponse).getString("address");
        Assert.assertEquals(expectedAddress,actualAddress);
    }
}
