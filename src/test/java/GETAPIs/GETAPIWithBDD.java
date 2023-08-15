package GETAPIs;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.List;

public class GETAPIWithBDD {

	@Test
	public void getProductTest() {

		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection", "keep-alive").and()
												.body("$.size()", equalTo(20));
		System.out.println("****************first test");

	}

	@Test
	public void getUserAPITest() {

		RestAssured.baseURI = "https://gorest.co.in/";
			given().log().all()
				.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
					.when().log().all()
						.get("public/v2/users")
							.then().statusCode(200)
								.and().contentType(ContentType.JSON)
									.and()
										.body("$.size()", equalTo(10));
			System.out.println("****************second test");
			

	}

	@Test
	public void getProductDataAPIWithQueryParamTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		given().log().all().queryParam("limit", "1").queryParam("name", "Vipan").when().log().all().get("/products")
				.then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON);
		System.out.println("****************third test");

	}

	@Test
	public void getProductDataAPI_With_Extract_Body() {
		RestAssured.baseURI = "https://fakestoreapi.com/";

		Response response = given().log().all().queryParam("limit", "5").when().log().all().get("products");
		JsonPath js = response.jsonPath();
		// get the id of the first product
		int firstProductId = js.getInt("[0].id");
		System.out.println("firstProductId :" + firstProductId);
		String firstProductTitle = js.getString("[0].title");
		System.out.println("firstProductTitle :" + firstProductTitle);
		float firstProductPRICE = js.getFloat("[0].price");
		System.out.println("firstProductPRICE :" + firstProductPRICE);
		float firstProductRatingRate = js.getFloat("[0].rating.rate");
		System.out.println("firstProductRatingRate :" + firstProductRatingRate);
		System.out.println("****************Fourth test");

	}

	@Test
	public void getProductDataAPI_With_Extract_Body_WithArray() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = given().queryParam("limit", "10").when().get("/products");
		JsonPath js = response.jsonPath();
		List<Integer> idList = js.getList("id");

		System.out.println("idList :" + idList.size());
		System.out.println("idList numbers :" + idList);

		List<String> listTitleName = js.getList("title");
		System.out.println("listTitleName :" + listTitleName);

		List<Float> rateList = js.getList("rating.rate", Float.class);
		System.out.println("rateList :" + rateList);
		System.out.println("********************8");

		for (int i = 0; i < idList.size(); i++) {
			int id = idList.get(i);
			String title = listTitleName.get(i);
			Float rate = rateList.get(i);
			System.out.println("id" + ":" + id + " " + "title" + ":" + title + " " + "rate" + ":" + rate);

		}
		System.out.println("****************fifth test");

	}
	@Test
	public void getProductDataAPI_With_Extract_Body_WithJson() {
		RestAssured.baseURI ="https://gorest.co.in";
		Response response = given().log().all()
								.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
									.when().log().all()
										.get("/public/v2/users/3614597");
		
		JsonPath js = response.jsonPath();
		System.out.println(js);
		int id = js.getInt("id");
		String name = js.getString("name");
		System.out.println("id of single product"+":"+" "+id+"\n" +"name of single product"+":"+" "+name);
		System.out.println("****************sixth test");
		
					
		
	}
	//I am not getting json response with body why
	@Test
	public void getProductDataAPI_With_Extract_Body_WithJson_Extract1() {
		//extract method for one value
		RestAssured.baseURI ="https://gorest.co.in";
		int userId =		given().log().all()
								.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
									.when().log().all()
										.get("/public/v2/users/3614596")
											.then().log().all()
												.extract().path("id");
		System.out.println("Extract id with extract method for single product :"+ userId);
											
		
		
		System.out.println("****************seventh test");
		
					
		
	}
	@Test
	public void getProductDataAPI_With_Extract_Body_WithJson_Extract2() {
		//extract method for multiple value
		RestAssured.baseURI ="https://gorest.co.in";
		Response response  =		given().log().all()
								.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
									.when().log().all()
										.get("/public/v2/users/3614596");
		int id =response.then().extract().path("id");
		String email = response.then().extract().path("email");
		System.out.println("Extract id  and email with extract method for single product :"+ id +" "+ email);
											
	
		}
	@Test
	public void getProductDataAPI_With_Extract_Body_WithJson_Extract3() {
		//extract method for multiple value
		RestAssured.baseURI ="https://gorest.co.in";
		Response response  =		given().log().all()
								.header("Authorization", "Bearer 64949ee8e982452d1eed363152b3aaef3ff022d57cc95033aef7cac02a64bd28")
									.when().log().all()
										.get("/public/v2/users/3614596")
											.then()
												.extract()
													.response();
		int id = response.path("id");
		String email = response.path("email");
		System.out.println("Extract id  and email with extract method for single product :"+ id +" "+ email);
		
		
												
											
	
		}

}
