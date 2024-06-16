package eCommerceAPIProject;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ECommenrceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * Spec buidelr, serialize and de-serialize all concepts are used.
		 * UI --> https://rahulshettyacademy.com/client/dashboard/dash
		 * Scenario: 1. Login 2. add product 3. orderdetails 4. vieworder 5. DeleteOrder
		 */
		// =================Login Code STARTS=============
		// Spec Builder to set BASEURI and Content Type  observe it does  have.setContentType(ContentType.JSON) it NOT headers
		RequestSpecification loginrequestspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.setContentType(ContentType.JSON).build();
		// Pojo Class for Login service Reqeust
		PojoLoginRequest loginReqeust = new PojoLoginRequest();
		loginReqeust.setUserEmail("postman@gmail.com");
		loginReqeust.setUserPassword("Hello123@");

		// Spec builder of login is used in another object with body
		RequestSpecification reqLogin = given().spec(loginrequestspec).body(loginReqeust);

		// Capture response in class
		PojoLoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login").then().extract().response()
				.as(PojoLoginResponse.class);

		String token = loginResponse.getToken();
		String userID = loginResponse.getUserId();
		System.out.println("Extracted Token is -->" + token);
		System.out.println("Extracted Token is -->" + userID);
//=================Login Code ENDS and =============	
		System.out.println("==============Login ends and add product starts==============");

		// Spec Builder for Add product service observe it does not have.setContentType(ContentType.JSON) it has headers
		RequestSpecification addproductBaseReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).build();

		RequestSpecification addProductRequest = given().spec(addproductBaseReqSpec).param("productName", "AkshayCAR")
				.param("productAddedBy", userID).param("productCategory", "Automobile")
				.param("productSubCategory", "FourWheeler").param("productPrice", "99999")
				.param("productDescription", "TestCar").param("productFor", "Racing")
				//Multiple part is used to upload file
				.multiPart("productImage", new File("C:\\Users\\AKSHAY\\Documents\\ImagePostman.png"));

		String addProductResponse = addProductRequest.when().post("api/ecom/product/add-product").then().extract()
				.response().asString();
//We are NOT using POJO as response json is simple
		JsonPath addproductResponseJson = new JsonPath(addProductResponse);
		String productID = addproductResponseJson.get("productId");
		String message = addproductResponseJson.get("message");
		System.out.println("product ID -->" + productID);
		System.out.println("response message -->" + message);
		
		
//=================Add product Code ENDS and =============	
			System.out.println("==============add product ends and order product starts==============");
//	spec builder for OrderDetails		 observe it does have  have.setContentType(ContentType.JSON) it has headers
//POJO classes are created 
			
			RequestSpecification CreateOrderBaseReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
					.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
			
//to use pojo create object of pojo classes
			SubPojoCreateOrderRequest subCreateOrderObject=new SubPojoCreateOrderRequest();
			subCreateOrderObject.setCountry("INDIA");
			subCreateOrderObject.setProductOrderedId(productID);
		
			
			// we can not pass object like above as it is not a list
			//list is needed because, currently have only one order in json, we can have more orders.
			//So create a list of order object
List<SubPojoCreateOrderRequest> orderlist = new ArrayList<SubPojoCreateOrderRequest> ();
orderlist.add(subCreateOrderObject);
//// orders.setOrders(subCreateOrderObject);
// list to be added in orderlist
PojoCreateOrderRequest orders= new PojoCreateOrderRequest();
orders.setOrders(orderlist);
	
//CALL Web-service now
  //First create payload
RequestSpecification createOrderRequest= given().spec(CreateOrderBaseReqSpec).body(orders);

//String createOrderResponse = 
String CreateOrderResponse= createOrderRequest.when().post("api/ecom/order/create-order").then().extract().asString();
System.out.println(CreateOrderResponse);

//=================ORDER PRODUCT STARTS =============	
System.out.println("==============order product ends and Delete product==============");
//DELETE ORDER
RequestSpecification deleteOrderSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
.addHeader("Authorization", token).setContentType(ContentType.JSON).build();

//Build Request
RequestSpecification deleteorderspec2= given().spec(deleteOrderSpec).pathParam("productId", productID);
//Curenty braces indicate it is path parameter "productId"
// we can use variable but it is not generic 
String deleteOrderResponse=deleteorderspec2.when().delete("api/ecom/product/delete-product/{productId}").then().extract().response().asString();

System.out.println(deleteOrderResponse);

JsonPath deleteResponse=new JsonPath(deleteOrderResponse);
System.out.println(deleteResponse.get("message"));
//Order is not deleted you will still see order of your product 








	
	

			
			
	}

}
