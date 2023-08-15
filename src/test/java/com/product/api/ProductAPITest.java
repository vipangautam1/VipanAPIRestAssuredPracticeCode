package com.product.api;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.fake.api.Product;
import com.fake.api.ProductLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {
	@Test
	public void getProductTest_With_POJO() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all().when().log().all().get("/products");

		ObjectMapper mapper = new ObjectMapper();
		try {
			Product[] product = mapper.readValue(response.body().asString(), Product[].class);
			// iterate array
			for (Product p : product) {
				System.out.println("ID: " + p.getId());
				System.out.println("TITLE: " + p.getTitle());
				System.out.println("PRICE: " + p.getPrice());
				System.out.println("DESCRIPTION: " + p.getDescription());
				System.out.println("IMAGE: " + p.getImage());
				System.out.println("Rate :"+ p.getRating().getRate());
				System.out.println("Count :"+ p.getRating().getCount());
				
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void getProductTest_With_POJO_Lombok() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all().when().log().all().get("/products");

		ObjectMapper mapper = new ObjectMapper();
		try {
			ProductLombok[] product = mapper.readValue(response.body().asString(), ProductLombok[].class);
			// iterate array
			for (ProductLombok p : product) {
				System.out.println("ID: " +p.getId() );
				System.out.println("TITLE: " + p.getTitle());
				System.out.println("PRICE: " + p.getPrice());
				System.out.println("DESCRIPTION: " + p.getDescription());
				System.out.println("IMAGE: " + p.getImage());
				System.out.println("Rate :"+ p.getRating());
				System.out.println("Count :"+ p.getRating().getCount());
				
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
