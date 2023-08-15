package com.pet.api;

import java.util.Arrays;
import java.util.List;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePetTest {
	//allure report
	@BeforeTest
	public void allureSetUp() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@Test
	public void createPetTest() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		Category category = new Category(1,"CAT");
		List<String> photoUrls = Arrays.asList("https://Cat.com","https://Cat1.com");
		Tag tag = new Tag(15, "Black Cat");
		Tag tag1 = new Tag(16,"Green Cat");
		List<Tag> tags = Arrays.asList(tag, tag1);
		
		PetLombok pet = new  PetLombok(212, category, "Sheffred Cat", photoUrls, tags, "available");
		Response response = RestAssured.given()
								.contentType(ContentType.JSON)
									.body(pet)//serailization is happening here 
										.when()
											.post("/v2/pet");
		System.out.println(response.statusCode());
		response.prettyPrint();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//De -serialize 
		ObjectMapper mapper = new ObjectMapper();
		try {
		PetLombok petResponse = 	mapper.readValue(response.getBody().asString(),PetLombok.class);
		System.out.println(pet.getId()+":"+petResponse.getId());
		Assert.assertEquals(pet.getId(), petResponse.getId());
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
