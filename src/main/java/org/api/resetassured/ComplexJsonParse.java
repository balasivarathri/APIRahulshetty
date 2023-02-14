package org.api.resetassured;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        //Print No of courses returned by API
        System.out.println("Print No of courses returned by API........");
        JsonPath js =  new JsonPath(Payloads.coursePrice());
        int count = js.getInt("courses.size");
        System.out.println(count);

        //Print Purchase Amount and website
        System.out.println("Print Purchase Amount and website........");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        String website = js.getString("dashboard.website");
        System.out.println(website);

        //Print Title of the first course
        System.out.println("Print Title of the first course........");
        String title = js.getString("courses[0].title");
        System.out.println(title);

        //Print All course titles and their respective Prices
        System.out.println("Print All course titles and their respective Prices........");
        for (int i = 0; i<count; i++){
            System.out.println(js.getString("courses["+i+"].title"));
            System.out.println(js.getInt("courses["+i+"].price"));
            System.out.println(js.getInt("courses["+i+"].copies"));
        }

        //Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course........");
        for (int i =0; i<count;i++) {
            String courseTitles = js.getString("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA")) {
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println(copies);
            }
        }
    }
}
