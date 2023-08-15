package com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTestWithLombok {
	
	
		public static String getRandomEmailId() {
		
		return "vipanApiAutomation"+System.currentTimeMillis()+"@gmail.com";
		
	}
//	@Test
//	public void createUserTest() {
//		RestAssured.baseURI ="https://gorest.co.in";
//		
//		User user = new User("Vipan", getRandomEmailId(),  "male", "active");
//		Response response = RestAssured.given()
//											.contentType(ContentType.JSON)
//												.header("Authorization","Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
//													.body(user)
//														.when()
//															.post("/public/v2/users");
//		Integer userId = response.jsonPath().get("id");
//		System.out.println(userId);
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		
//		//Get API to get the same user  
//		RestAssured.baseURI = "https://gorest.co.in";
//		Response getResponse = RestAssured.given().log().all()
//								.contentType(ContentType.JSON)
//									.header("Authorization","Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
//										.when()
//											.get("/public/v2/users/"+userId);
//											
//		//deserialization 
//		ObjectMapper mapper = new ObjectMapper();
//			try {
//		User userRes	=	mapper.readValue(getResponse.body().asString(), User.class);
//		System.out.println(userRes.getId()+":"+userRes.getGender()+":"+userRes.getName()+":"+userRes.getStatus());
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		Assert.assertEquals(userId, userRes.getId());
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//					
//	
//		
//		}
	
	@Test
	public void createUser_WithBuilderPattern_Test() {
		RestAssured.baseURI ="https://gorest.co.in";
		
	User user =	new User.UserBuilder()
			.name("Vipan Gautam")
			.email(getRandomEmailId())
			.status("active")
			.gender("male")
			.build();
			
		Response response = RestAssured.given().log().all()
											.contentType(ContentType.JSON)
												.header("Authorization","Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
													.body(user)
														.when().log().all()
															.post("/public/v2/users");
		Integer userId = response.jsonPath().get("id");
		System.out.println(userId);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		//Get API to get the same user  
		RestAssured.baseURI = "https://gorest.co.in";
		Response getResponse = RestAssured.given().log().all()
								.contentType(ContentType.JSON)
									.header("Authorization","Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
										.when()
											.get("/public/v2/users/"+userId);
											
		//deserialization 
		ObjectMapper mapper = new ObjectMapper();
			try {
		User userRes	=	mapper.readValue(getResponse.body().asString(), User.class);
		System.out.println(userRes.getId()+":"+userRes.getGender()+":"+userRes.getName()+":"+userRes.getStatus());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Assert.assertEquals(userId, userRes.getId());
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	
		
		}
	
	
	
	
	//I think ID tried to bypass inorder match POJO to json
	}