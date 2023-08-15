package specificationconcept;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecification {

	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec =	new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
					.setContentType(ContentType.JSON)
						.addHeader("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
							.build();
		return requestSpec;
	}
	public static ResponseSpecification get_res_spec_200_OK_With_Body() {
		ResponseSpecification res_pec_200_ok =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
					.expectHeader("Server", "cloudflare")
					.expectBody("$.size()", equalTo(10))
					.expectBody("id",hasSize(10))
						.build();
		return res_pec_200_ok;
			
		
	}
	
	
	@Test
	public static void getUser_With_Request_And_Response_Spec() {
			RestAssured.given().log().all()
			.spec(user_req_spec())
			.when().log().all()
			.get("/public/v2/users")
			.then().log().all()
			.assertThat()
			.spec(get_res_spec_200_OK_With_Body());
	}

	
}
