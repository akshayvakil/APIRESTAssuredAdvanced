package APIAutomation;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTestSessionIntegratePathandQueryParam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter sessionfilter = new SessionFilter();
//Step1 -Login
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"JiraUser1\", \"password\": \"Jira123#\" }").log().all().filter(sessionfilter)
				.when().post("/rest/auth/1/session").then().extract().response().asString();

//Step2-Update Comment		
		given().pathParam("id", "10007").log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"This is my  comment with attachment from REST API semper.\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.filter(sessionfilter).when().post("rest/api/2/issue/{id}/comment").then().log().all().statusCode(201);
		/// rest/api/2/issue/{id}/comment --> anything with {} is path parameter for
		/// rest assured id=10007 will be fetched form abovepathParam method

//Step3-Update Comment with attachments
		/**
		 * Jira documentation is based on curl command below
		 * https://docs.atlassian.com/software/jira/docs/api/REST/9.14.0/#api/2/issue/{issueIdOrKey}/attachments-addAttachment
		 * curl -D- -u admin:admin -X POST -H "X-Atlassian-Token: no-check" -F
		 * "file=@myfile.txt" http://myhost/rest/api/2/issue/TEST-123/attachments
		 * 
		 * X = header -H = Header -F -D - u
		 * 
		 * 
		 * 
		 */
		given().header("X-Atlassian-Token", "no-check").pathParam("id", "10007").filter(sessionfilter)
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("JiraAttachment.txt")).when()
				.post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
//Step4: Get Issue
		/**
		 * https://docs.atlassian.com/software/jira/docs/api/REST/9.14.0/#api/2/issue-getIssue
		 * /rest/api/2/issue/{issueIdOrKey}
		 * 
		 */
		String resonseIssuedetails = given().filter(sessionfilter).pathParam("id", "10007")
				.queryParam("fields", "comment").log().all().when()
				.get("rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		
		System.out.println("================================");

		System.out.println(resonseIssuedetails);
	}

}
