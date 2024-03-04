package APIAutomation;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class APIBasics5ReadJsonFromFile {
	
	/**
	 * Instead of reading jon from code, read json from .json file
	 * Body 
	 * @param args
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		/**=========== Notes
		 * Static json files from external location
		 * new String (Files.readAllBytes(Paths.get(jsonPath))
		 * String -> bytes -> String   
		 */
		
		String jsonPath="D:\\Users\\AKSHAY\\new-eclipse-workspace\\APIRestAssured2\\src\\test\\resources\\addPlace.json";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(new String (Files.readAllBytes(Paths.get(jsonPath))))
		
		.when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("status",equalTo("OK")).body("scope", equalTo("APP"))
		//Server response is imp to check if response coming from proper server
		
		.header("Server","Apache/2.4.52 (Ubuntu)");
		
	}

}
