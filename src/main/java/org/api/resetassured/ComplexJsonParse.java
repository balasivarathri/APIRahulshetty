package org.api.resetassured;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payloads.coursePrice());
        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print Purchase Amount and website
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        String website = js.getString("dashboard.website");
        System.out.println(website);

        //Print Title of the first course
        String title = js.get("courses[2].title");
        System.out.println(title);

        //Print All course titles and their respective Prices
        for(int i=0;i<count;i++){
            String courseTitles = js.get("courses["+i+"].title");
            System.out.println(courseTitles);
            System.out.println(js.get("courses["+i+"].price").toString());
        }
    }
}
