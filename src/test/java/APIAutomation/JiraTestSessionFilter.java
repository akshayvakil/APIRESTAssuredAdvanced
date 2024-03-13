package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTestSessionFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8080";
SessionFilter sessionfilter= new SessionFilter();
//Step1 -Login
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"JiraUser1\", \"password\": \"Jira123#\" }").log().all().filter(sessionfilter)
				.when()
				.post("/rest/auth/1/session")
				.then().extract().response().asString();

//Step2-Update Comment		
		given().pathParam("id", "10007").log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"This is my Second comment from REST API semper.\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}").filter(sessionfilter)
				.when()
				.post("rest/api/2/issue/{id}/comment")
				.then().log().all().statusCode(201);
		/// rest/api/2/issue/{id}/comment --> anything with {} is path parameter for
		/// rest assured id=10007 will be fetched form abovepathParam method
	}

}
