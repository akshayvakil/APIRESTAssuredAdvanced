package APIAutomation;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.Payload;

public class APIBasics3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		/**=========== Notes
		 *If we want to only validated, methods after.then() are sufficient
		 *String response = given...If we want to take response in string we should write a code to take response in string
		 *extract().response().String() 
		 *Location of variables inside json path
		 *e.g we we can extract LocationID using JsonPath interface's getString("varaibleName")
		 *
		 *First store response in a string
		 */
		
		String responseString=given().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.addplacejson())
		.when().post("maps/api/place/add/json")
		.then().statusCode(200).body("status",equalTo("OK")).body("scope", equalTo("APP"))
		//Server response is imp to check if response coming from proper server
		
		.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		//using jsonPath interface pass above string
	
		JsonPath jsn = new JsonPath(responseString);
		
		String placeid=jsn.getString("place_id");
		System.out.println("Place ID Extracted is --->"+placeid);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
