package APIAutomation;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class APIBasics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		/**=========== Notes
		 * given: Give all inputs
		 * when:  Submit request
		 * then:  Validate response
		 * 
		 * =====Use Hamrest package to make use of EqualTo Method=== 
		 * =====Validate Server status code, response body and response headers 
		 */
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}")
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("status",equalTo("OK")).body("scope", equalTo("APP"))
		//Server response is imp to check if response coming from proper server
		
		.header("Server","Apache/2.4.52 (Ubuntu)");
		
	}

}
