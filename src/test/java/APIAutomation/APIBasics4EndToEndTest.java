package APIAutomation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.Payload;
import jsonFiles.ReusableMethods;

public class APIBasics4EndToEndTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		/**
		 * =========== Notes In this we will do Create Place -->fetchID -->update
		 * address --> Validate address is updated just validated respone message may
		 * not be enough
		 *
		 * Learning in this--> Add variable to string In json add ""+" or ""+""
		 */

//Step1: Create a place
		String responseString = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addplacejson()).when().post("maps/api/place/add/json").then().statusCode(200)
				.body("status", equalTo("OK")).body("scope", equalTo("APP"))
				// Server response is imp to check if response coming from proper server

				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		// using jsonPath interface pass above string

		JsonPath jsn = ReusableMethods.convertRawResponseToJson(responseString);
//Step2: Fetch unique id of a place
		String placeid = jsn.getString("place_id");
		System.out.println("Place ID Extracted is --->" + placeid);
//Step3: update place and check response message
		System.out.println("Started update location");

		String newAdress = "70 Summer walk, USA xyz";

		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeid + "\",\r\n" + "\"address\":\"" + newAdress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

//Step4: Verify address is updated as per request we sent
		System.out.println("Started Fetching  location");
		String updateResponsejson = given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when()
				.get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();

		// For below line we can utilize "ReusableMethods.convertRawResponseToJson" as
		// well
		JsonPath jsnupdate = new JsonPath(updateResponsejson);
		String updatedadress = jsnupdate.getString("address");

		assertEquals(newAdress, updatedadress);

		System.out.println(updatedadress);

	}

}
