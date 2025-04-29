package commercePk;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class ProductAPITest {

	// DataProvider that fetches data from JSON file
    @DataProvider(name = "productData")
    public Object[][] provideProductData() throws IOException {
        return JSONUtils.getTestData("src/test/resources/testData.json");
    }
    /*
	 @Test(dataProvider = "productData")
	    public void testGetProducts(String productName, int price) {
	        // Set the base URL for the API
	        RestAssured.baseURI = "http://localhost:3000";

	        // Create JSON dynamically using parameters
	        String newProduct = "{ \"name\": \"" + productName + "\", \"price\": " + price + " }";
	        
	        // Send GET request to fetch products
	        Response response = RestAssured.given()
	        		.header("Content-Type", "application/json")
	                .body(newProduct)
	                .when()
	                .post("/products")
	                .then()
	                .statusCode(201) // Assert successful creation
	                .extract()
	                .response();

	       String jsonResponse = response.asString();
	       Assert.assertTrue(jsonResponse.contains(productName));
	        
	     //    Assert that the response contains product data
	       Assert.assertTrue(jsonResponse.contains("Laptop"));
	    }
	*/ 
	 @Test
	 public void testCreateProduct() {
	     RestAssured.baseURI = "http://localhost:3000";

	     // Define product data
	     String newProduct = "{ \"name\": \"Smartwatch\", \"price\": 299 }";

	     // Send POST request to create product
	     Response response = RestAssured.given()
	         .header("Content-Type", "application/json")
	         .body(newProduct)
	         .when()
	         .post("/products")
	         .then()
	         .statusCode(201)  // Check if the product is created successfully
	         .extract()
	         .response();

	     // Extract response as string
	   String jsonResponse = response.asString();
	     
	     // Assert response contains product details
	    Assert.assertTrue(jsonResponse.contains("Smartwatch"));
	 }

	 @Test
	 public void testGetSingleProduct() {
	     RestAssured.baseURI = "http://localhost:3000";

	     // Send GET request to fetch product with ID 1
	     Response response = RestAssured.given()
	         .when()
	         .get("/products/1")
	         .then()
	         .statusCode(200)
	         .extract()
	         .response();

	     // Extract response as string
	    String jsonResponse = response.asString();
	     
	     // Assert response contains product details
	     Assert.assertTrue(jsonResponse.contains("Laptop"));
	 }

	 @Test
	 public void testUpdateProduct() {
	     RestAssured.baseURI = "http://localhost:3000";

	     // Updated product data
	     String updatedProduct = "{ \"name\": \"Walking stick\", \"price\": 250 }";

	     // Send PUT request
	     Response response = RestAssured.given()
	         .header("Content-Type", "application/json")
	         .body(updatedProduct)
	         .when()
	         .put("/products/3")
	         .then()
	         .statusCode(200) // Check if update was successful
	         .extract()
	         .response();

	     // Extract response as string
	     String jsonResponse = response.asString();
	     
	     // Assert new price is reflected
	    Assert.assertTrue(jsonResponse.contains("250"));
	 }

	 @Test
	 public void testDeleteProduct() {
	     RestAssured.baseURI = "http://localhost:3000";

	     // Send DELETE request for product ID 1
	     RestAssured.given()
	         .when()
	         .delete("/products/2")
	         .then()
	         .statusCode(200);  // Assert product is deleted
	 }

	
}
