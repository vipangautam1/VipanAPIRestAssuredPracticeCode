package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

import java.io.File;

public class BookingAuthTest {
	
	
	@Test
	public void getBookingAuthTokenTest_WithJSON_String() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
				.body("{\n"
						+ "    \"username\" : \"admin\",\n"
						+ "    \"password\" : \"password123\"\n"
						+ "}")
					.when()
						.post("auth")
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
				
		
	}
	
	@Test
	public void getBookingAuthTokenTest_WithJSON_File() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
				.body(new File("./src/test/resources/data/basicauth.json"))
				
					.when()
						.post("auth")
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);
				
		
	}
	//post --add a user --userId =123--assert(200,body)
	//get--get a  user -->/user/123--200--userid=123
	@Test
	public void addUserTest() {
		//1. add a user - Post
		RestAssured.baseURI ="https://gorest.co.in";
		
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/adduser.json"))
			.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
				.when().log().all()
					.post("/public/v2/users")
						.then().log().all()
						.assertThat()
						.statusCode(201)
						.and()
						.body("name", equalTo("Sunny"))
						.extract()
						.path("id");
		System.out.println("user id--->"+userId);
		
		//get the same user and verify it 
		given().log().all()
		//.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
			.when().log().all()
				.get("/public/v2/users/"+userId)
				
					.then().log().all()
					.assertThat()
						.statusCode(200)
						.and()
							
							.body("id", equalTo(userId));
		
									
						
	}

}
