package serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {
	
	/**
	 * Serialization where setters are used to convertJSON to javaObject
	 * we need NOT have to use json file
	 * object will be converted  to json by REST Assured
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		
		//STEP1: Create object of json file e.g. addplacejson 
		
		PayloadPojo addPlacejson= new PayloadPojo();		
		
		addPlacejson.setAccuracy(50);
		addPlacejson.setAddress("29, side layout, cohen 09");
		addPlacejson.setLanguage("French-IN");		
		addPlacejson.setPhone_number("\"(+91) 983 893 3937");
		addPlacejson.setName("Frontline house");
		
		//setting location which is another class we need to create a  object of location
		
		location loc=new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		addPlacejson.setLocation(loc);
		
		//setting type which is list we need to create a list object first
		List<String> typelist= new ArrayList<String>();
		typelist.add("shoe park");
		typelist.add("shop");
		addPlacejson.setTypes(typelist);
		
		
		
	//STEP2: Pass JavaObject to payload	
		
		
		//Complete URL : https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Response resp=given().log().all().queryParam("key", "qaclick123").body(addPlacejson)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responsString= resp.asString();
		System.out.println(responsString);
	
		


	}


}
