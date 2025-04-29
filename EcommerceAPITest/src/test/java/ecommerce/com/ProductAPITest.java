package ecommerce.com;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAPITest {

	@Test
    public void testGetProducts() {
        // Set the base URL for the API
        RestAssured.baseURI = "http://localhost:3000";

        // Send GET request to fetch products
        String response = RestAssured.given()
            .when()
            .get("/products")
            .then()
            .statusCode(200)  // Check if status code is 200
            .extract()
            .asString();

        // Assert that the response contains product data
        Assert.assertTrue(response.contains("Laptop"));
        Assert.assertTrue(response.contains("Phone"));
    }
	
}
