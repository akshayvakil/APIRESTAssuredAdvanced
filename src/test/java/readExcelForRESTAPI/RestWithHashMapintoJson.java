package readExcelForRESTAPI;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.ReusableMethods;

public class RestWithHashMapintoJson {
	/**
	 * Instead of using json in body we will convert Hashmap in json
	 * Hashmp will be used in body, it will be REST Assured duty to convert back to json.
	 * If we have complext json use code below after program
	 */

	@Test
	public void addBook() {
		
		HashMap<String,Object> jsonInHashMap = new HashMap<String, Object>();
		jsonInHashMap.put("name", "REST API Automation by Akshay");
		jsonInHashMap.put("isbn", "bcd");
		jsonInHashMap.put("aisle", "3125");
		jsonInHashMap.put("author", "Akshay Vakil");
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		//Fetching Response in a string
		String resp = given().header("Content-Type", "application/json")
				.body(jsonInHashMap)
				.when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath addBookJson = ReusableMethods.convertRawResponseToJson(resp);
		String BookID = addBookJson.getString("ID");
		String ResponseMessage = addBookJson.getString("Msg");
		System.out.println("BookID which is created is -->"+BookID);
		System.out.println(ResponseMessage);

	}
	/**
	 * If we have nested json like below, we will need another new map object for json 
	 * {
	     "name":"Learn Appium Automation with Java",
        "isbn":"bcd",
	    "location": {
             "lat": -38.383494,
             "lng": 33.427362
  },
  }
	 * e.g.
	 * HashMap<String,Object> jsonInHashMap = new HashMap<String, Object>();
	    jsonInHashMap.put("name", "REST API Automation by Akshay");
		jsonInHashMap.put("isbn", "bcd");
		
		
		HashMap<String,Object> locationMap = new HashMap<String, Object>();
		 locationMap.put("lat", "1234");
		  locationMap.put("lng", "789");
		  
		  jsonInHashMap.put("location", locationMap);
		  
		  
	 */

}
