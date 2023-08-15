package JsonPathValidatorTest;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;



public class JsonPathTest {
	@Test
    public void getCountryValue() {
        // Specify the base URL and path parameter (year) for the GET call
        String baseUrl = "http://ergast.com/api/f1";
        String year = "2017";
        String url = baseUrl + "/" + year + "/circuits.json";

        // Send the GET request and extract the response
        Response response = given().get(url);

        // Extract the response body as a string
        String jsonResponse = response.getBody().asString();

        // Use JsonPath to extract the country value using the given JSONPath expression
        List<String> countryList = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");

        // Print the country value
        System.out.println("Country: " + countryList);
       
    }
	@Test
	public void getCircuitDataAPIWith_YearTest() {
		RestAssured.baseURI = "http://ergast.com";

		Response response =	given().log().all()
							 .when().log().all()
								.get("/api/f1/2017/circuits.json");
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");	
		System.out.println("Total Circuits are :"+totalCircuits);
		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");		
		System.out.println("Country Size: " +countryList.size());
		System.out.println("Countries name: "+countryList);
		
	}
//with two keys rate and price (2 attribute)
	@Test
	public void getProductTest() {
		RestAssured.baseURI="https://fakestoreapi.com";

		Response response = given().log().all()
								.when().log().all()
									.get("/products");
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		//give me those rate which less than 3 
		List<Float> rateLessThanThree=JsonPath.read(jsonResponse,"$[?(@.rating.rate<3)].rating.rate" );
		System.out.println(rateLessThanThree);
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		List<Map<String,Object>> jewelleryList = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
		System.out.println(jewelleryList);
		//Itearte
		
		for(Map<String,Object> product: jewelleryList) {
			String title = (String)product.get("title");
			System.out.println("Title :"+title);
			Object price = (Object)product.get("price");
			System.out.println("Price :"+price);
			System.out.println(">>>>>>>>>>>>>>"); 
			
		}
		
		// with 3 attribute 
		
		List<Map<String,Object>> jewelleryList2 = JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\",\"id\"]");
		System.out.println(jewelleryList2);
		//Itearte
		
		for(Map<String,Object> product: jewelleryList2) {
			String title = (String)product.get("title");
			System.out.println("Title :"+title);
			Object price = (Object)product.get("price");
			System.out.println("Price :"+price);
			Integer id =(Integer)product.get("id");
			System.out.println("Id :"+id);
			System.out.println(">>>>>>>>>>>>>>"); 
			
		}
	}
	
}

















