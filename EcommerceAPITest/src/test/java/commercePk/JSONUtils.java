package commercePk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSONUtils {

	public static Object[][] getTestData(String filePath) throws IOException 
	{
		
		// Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON file into JsonNode object
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        // Get the "products" array
        JsonNode productsNode = rootNode.get("products");

        // Create a 2D array to store test data
        Object[][] data = new Object[productsNode.size()][2];

        // Loop through JSON array and extract values
        for (int i = 0; i < productsNode.size(); i++) {
            data[i][0] = productsNode.get(i).get("name").asText();  // Product Name
            data[i][1] = productsNode.get(i).get("price").asInt();  // Product Price
        }
        return data;
	}
}