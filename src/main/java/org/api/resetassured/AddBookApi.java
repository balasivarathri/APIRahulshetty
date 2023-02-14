package org.api.resetassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class AddBookApi {
    @Test
    public void addBookApi(){
        RestAssured.baseURI="http://216.10.245.166";
        String response = given().header("Content-Type","application/json").body(Payloads.addBookApi("kkddlj","8768")).
                when().post("Library/Addbook.php").
                then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }
}
