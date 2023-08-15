package POSTAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingAuthWithPojoTest {
	@Test
	public void getBookingAuthTokenTest_With_Json_String() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
		Credentials cred = new Credentials("admin","password123");
		
	String tokenId = 	given().log().all()
			.contentType(ContentType.JSON)
			
			.body(cred)
			
			.when().log().all()
			.post("auth")
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.extract()
								.path("token");
System.out.println(tokenId);
Assert.assertNotNull(tokenId);
	
		
	}

}
