package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestWithoutBDDTest {
	RequestSpecification request;
	
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "https://gorest.co.in/";
		request = RestAssured.given();
		request.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28");
		
	}

	@Test
	public void getAllUsersAPITest() {
		// Request:

		
		Response response = request.get("public/v2/users");
		// ==============================================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);
		// verification:
		Assert.assertEquals(statusCode, 200);
		// status message:
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		// fetch body:
		response.prettyPrint();
		// fetch header:
		String contentType = response.header("Content-Type");
		System.out.println(contentType);
		System.out.println("************************************************888");
		// fetch all headers :
		List<Header> headers = response.headers().asList();
		System.out.println(headers.size());
		for (Header h : headers) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

	}

	@Test
	public void getAllUsersWithQueryParameterAPITest1() {
		// Request:

		
		Response response = request.get("public/v2/users?name=Bhishma&status=active");
		// ==============================================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);
		// verification:
		Assert.assertEquals(statusCode, 200);
		// status message:
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		// fetch body:
		response.prettyPrint();

	}
	@Test
	public void getAllUsersWithQueryParameterAPITest2() {
		// Request:

		
		request.queryParam("name", "Bhishma");
		request.queryParam("status", "active");
		Response response = request.get("public/v2/users");
		// ==============================================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);
		// verification:
		Assert.assertEquals(statusCode, 200);
		// status message:
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		// fetch body:
		response.prettyPrint();

	}
	@Test
	public void getAllUsersWithQueryParameter_WithHashMap_APITest() {
		// Request:

		
		Map<String,String> queryParamMap = new HashMap<String,String>();
		queryParamMap.put("name", "Bhishma");
		queryParamMap.put("status", "active");
		request.queryParams(queryParamMap);
		Response response = request.get("public/v2/users");
		// ==============================================
		// status code:
		int statusCode = response.statusCode();
		System.out.println("Status code: " + statusCode);
		// verification:
		Assert.assertEquals(statusCode, 200);
		// status message:
		String statusMsg = response.statusLine();
		System.out.println(statusMsg);
		// fetch body:
		response.prettyPrint();

	}
}
