package APIAutomation;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import jsonFiles.Payload;

public class APIBasics2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		/**=========== Notes
		 * Take input of json from different file
		 */
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.addplacejson())
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("status",equalTo("OK")).body("scope", equalTo("APP"))
		//Server response is imp to check if response coming from proper server
		
		.header("Server","Apache/2.4.52 (Ubuntu)");
		
	}

}
