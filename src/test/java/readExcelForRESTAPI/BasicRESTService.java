package readExcelForRESTAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.ReusableMethods;

public class BasicRESTService {

	@Test
	public void addBook() {
		/*
		 * We can pass json to payload as well
		 * 
		 */
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		//Fetching Response in a string
		String resp = given().header("Content-Type", "application/json")
				.body("{\r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n" + "\"isbn\":\"bcd\",\r\n"
						+ "\"aisle\":\"3124\",\r\n" + "\"author\":\"John foer\"\r\n" + "}\r\n" + "")
				.when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath addBookJson = ReusableMethods.convertRawResponseToJson(resp);
		String BookID = addBookJson.getString("ID");
		String ResponseMessage = addBookJson.getString("Msg");
		System.out.println("BookID which is created is -->"+BookID);
		System.out.println(ResponseMessage);

	}

}
