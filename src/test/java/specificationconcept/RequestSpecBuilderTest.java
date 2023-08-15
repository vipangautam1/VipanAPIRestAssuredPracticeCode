package specificationconcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec =	new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
							.build();
		return requestSpec;
	}
	
	@Test
	public void getUser_With_Request_Spec1() {
//	RequestSpecification requestSpec =	new RequestSpecBuilder()
//			.setBaseUri("https://gorest.co.in")
//				.setContentType(ContentType.JSON)
//					.addHeader("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
//						.build();
	RestAssured.given()
					//.spec(requestSpec)
						.spec(user_req_spec())
							.get("/public/v2/users")
								.then()
									.statusCode(200);
		
	}
	@Test
	public void getUser_With_Request_Spec2() {
	RequestSpecification requestSpec =	new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
					.addHeader("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
						.build();
	RestAssured.given().log().all()
					.spec(requestSpec)
						.when().log().all()
							.get("/public/v2/users")
								.then().log().all()
									.statusCode(200);
		
	}
	@Test
	public void getUser_With_Param_Request_Spec() {

	RestAssured.given()
	
			.param("name", "naveen")
				.param("status", "active")
				.spec(user_req_spec())
						.when().log().all()
							.get("/public/v2/users")
								.then().log().all()
									.statusCode(200);
		
	}
	

}
