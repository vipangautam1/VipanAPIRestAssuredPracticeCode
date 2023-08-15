package GETAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class GetPathWithParam {
	@DataProvider
	public Object[][] getCircuitDataAPIWith_Year_DataProvider() {
		
		
		return new Object[][] {
			{"2016",21},
			{"2017",20},
			{"1966",9},
			{"2023",22}
		};
		
	}
	@Test(dataProvider= "getCircuitDataAPIWith_Year_DataProvider")
	public void getCircuitDataAPIWith_YearTest(String seasonYear ,int totalCircuits ) {
		RestAssured.baseURI = "http://ergast.com";

		given().log().all()
			.pathParam("year", seasonYear)
				.when().log().all()
					.get("/api/f1/{year}/circuits.json")
						.then().log().all()
							.assertThat()
								.statusCode(200)
			
//to check how many circuit 
									.and().log().all()
										.body("MRData.CircuitTable.season", equalTo(seasonYear))
											.and()
												.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));
	}

	
	

}
