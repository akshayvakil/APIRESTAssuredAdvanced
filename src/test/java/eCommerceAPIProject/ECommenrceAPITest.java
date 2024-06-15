package eCommerceAPIProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;

public class ECommenrceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * Spec buidelr, serialize and de-serialize all concepts are used.
		 * UI --> https://rahulshettyacademy.com/client/dashboard/dash
		 */
		// =================Login Code STARTS=============
		// Spec Builder to set BASEURI and Content Type
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.setContentType(ContentType.JSON).build();
		// Pojo Class for Login service Reqeust
		PojoLoginRequest loginReqeust = new PojoLoginRequest();
		loginReqeust.setUserEmail("postman@gmail.com");
		loginReqeust.setUserPassword("Hello123@");

		// Spec builder another object with body
		RequestSpecification reqLogin = given().spec(request).body(loginReqeust);

		// Capture response in class
		PojoLoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login").then().extract().response()
				.as(PojoLoginResponse.class);

		String token = loginResponse.getToken();
		String userID = loginResponse.getUserId();
		System.out.println("Extracted Token is -->" + token);
		System.out.println("Extracted Token is -->" + userID);
//=================Login Code ENDS=============	
		System.out.println("==============Login ends and add product starts==============");

		// Spec Builder for Add product service
		RequestSpecification addproductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).build();

		RequestSpecification addProductRequest = given().spec(addproductBaseReq).param("productName", "AkshayCAR")
				.param("productAddedBy", userID).param("productCategory", "Automobile")
				.param("productSubCategory", "FourWheeler").param("productPrice", "99999")
				.param("productDescription", "TestCar").param("productFor", "Racing")
				//Multiple part is used to upload file
				.multiPart("productImage", new File("C:\\Users\\AKSHAY\\Documents\\ImagePostman.png"));

		String addProductResponse = addProductRequest.when().post("api/ecom/product/add-product").then().extract()
				.response().asString();
//We are not using pojo as response json is simple
		JsonPath addproductResponseJson = new JsonPath(addProductResponse);
		String productID = addproductResponseJson.get("productId");
		String message = addproductResponseJson.get("message");
		System.out.println("product ID -->" + productID);
		System.out.println("response message -->" + message);

		System.out.println("==============add product ends and order product starts==============");

	}

}
