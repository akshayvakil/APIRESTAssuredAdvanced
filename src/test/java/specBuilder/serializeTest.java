package specBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;


public class serializeTest {
	
	/**
	 * Complete URL : https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123
		
		
		Pre-req: Create Object of Spec builder
		STEP1: Set baseURI in specBuilderReqeust
		STEP2: Set query in specBuilderReqeust
		RestAssured.baseURI = "https://rahulshettyacademy.com"; 
	 *Utility classes are created for using spec builder
	 * @param args
	 */

	public static void main(String[] args) {
		
	
		
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
		
		
		
	
		//STEP1 Create Object of Request specificationBuilder 
		//Common things are included included in this page.
		RequestSpecification reqeustspecBuilder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		//STEP2
		//Setup a Request before building it response in request object as below using spec builder
		RequestSpecification request = given().spec(reqeustspecBuilder).body(addPlacejson);
		
		//STEP3 common things like expectedCode is included
		//Create Object of response specification with speciBuilder
		ResponseSpecification responseBuilder=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			
		//STEP4 response is gathered in string
		//Hit request /build with spec builder		
			Response response=	request.when().post("/maps/api/place/add/json")
				.then().spec(responseBuilder).extract().response();
		//STEP5: print response as a string or further validation	
			String responsString= response.asString();
			System.out.println(responsString);
		
				
		
		
		/*//response WITHOUT speciBuilder
		Response resp=given().log().all().queryParam("key", "qaclick123").body(addPlacejson)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		*/
				/**
				 * We can write reqeust Builder as well
				 * RequestSpecification resp = given().spec(reqeustspecBuilder).body(addPlacejson);
				 * .when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
				 */


	}


}
