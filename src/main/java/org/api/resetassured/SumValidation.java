package org.api.resetassured;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void allCoursePricesMatchesWithPurchaseAmount() {

        //Verify if Sum of all Course prices matches with Purchase Amount
        System.out.println("Verify if Sum of all Course prices matches with Purchase Amount.....");
        int total=0;
        JsonPath js = new JsonPath(Payloads.coursePrice());
        int count = js.getInt("courses.size");
        for (int i=0; i<count;i++) {
            int price = (js.getInt("courses[" + i + "].price"));
            int copies = (js.getInt("courses[" + i + "].copies"));
            int sum = price*copies;
            total = total+sum;
            System.out.println(total);
        }
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(total,purchaseAmount);
    }

}
