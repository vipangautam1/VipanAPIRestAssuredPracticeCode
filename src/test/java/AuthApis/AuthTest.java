package AuthApis;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTest {
	
	@Test
	public void basicAuthWithJsonBody() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		String jwtTokenId = RestAssured.given()
						.contentType(ContentType.JSON)
							.body("{\n"
									+ "    \"username\": \"mor_2314\",\n"
									+ "    \"password\": \"83r5^_\"\n"
									+ "}")
								.when()
									.post("/auth/login")
										.then()
											.assertThat()
												.statusCode(200)
												.and()
												.extract()
												.path("token");
		System.out.println(jwtTokenId);
		//String payload = jwtTokenId.split("\\.")[0];
		String []tokenArr = jwtTokenId.split("\\.");
		System.out.println(tokenArr[0]);
		System.out.println(tokenArr[1]);
		System.out.println(tokenArr[2]);
		
		
		
	}

}
