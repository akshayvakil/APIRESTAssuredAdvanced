package APIAutomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.Payload;
import jsonFiles.ReusableMethods;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test
	public void addbook()

	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// String addBookResponse=
		// given().log().all().headers("Content-Type","text/plain").body(Payload.AddBookjson())
		/**
		 * Above apporach will send fixed book data which will fail after multiple
		 * execution We will build DYNAMIC json as below
		 */

		String addBookResponse = given().log().all().headers("Content-Type", "text/plain")
				.body(Payload.AddBookjson("ragg", "8785")).when().post("Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath addBookJson = ReusableMethods.convertRawResponseToJson(addBookResponse);
		String BookID = addBookJson.getString("ID");
		String ResponseMessage = addBookJson.getString("Msg");
		System.out.println(BookID);
		System.out.println(ResponseMessage);

	}
	
	

}
