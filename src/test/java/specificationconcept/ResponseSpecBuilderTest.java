package specificationconcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecBuilderTest {
	
	
	
	
	public static ResponseSpecification get_res_spec_200_OK() {
		ResponseSpecification res_pec_200_ok =	new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
					.expectHeader("Server", "cloudflare")
						.build();
		return res_pec_200_ok;
			
		
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
	public static ResponseSpecification get_res_spec_401_Auth_Fail() {
		ResponseSpecification res_spec_401_Auth_Fail =	new ResponseSpecBuilder()
				.expectStatusCode(401)
					.expectHeader("Server", "cloudflare")
						.build();
		return res_spec_401_Auth_Fail;
			
	}
	@Test
	public void get_user_response_200_spec_Test() {
		RestAssured.baseURI ="https://gorest.co.in";
		RestAssured.given()
			.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								//.spec(get_res_spec_200_OK());
								.spec(get_res_spec_200_OK_With_Body());
				
	}
	@Test
	public void get_user_res_spec_401_Auth_Fail_spec_Test() {
		RestAssured.baseURI ="https://gorest.co.in";
		RestAssured.given()
			.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28x")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_401_Auth_Fail());
				
	}

}
