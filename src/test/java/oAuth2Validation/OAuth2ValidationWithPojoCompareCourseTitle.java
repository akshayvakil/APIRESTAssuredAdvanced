package oAuth2Validation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.MainPoJoGetCourse;
import pojo.WebAutomation;
import pojo.api;

public class OAuth2ValidationWithPojoCompareCourseTitle {

	public static void main(String[] args) {

		/**
		 * All values are fetched using getters and setters response is converted into
		 * Class using response.get methods everything is fetched from line 40
		 */
		// TODO Auto-generated method stub
//response = response of login service needed for actual login
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asPrettyString();

		System.out.println(response);
		JsonPath jsonp = new JsonPath(response);
		String accessToken = jsonp.getString("access_token"); // fetch access token

		MainPoJoGetCourse gc = given().queryParams("access_token", accessToken).when().log().all() // Response is
																									// converted into
				// JAVA object below using .as
				// Classname.class // instead of
				// fetching from string
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(MainPoJoGetCourse.class);

		System.out.println("========Second services hit multiple times===========");
		System.out.println("class object is sysout as below -->" + gc);
		/*
		 * use below for trouble shooting String gcString =
		 * given().queryParams("access_token", accessToken) // Response is converted
		 * into // JAVA object below using .as // Classname.class // instead of //
		 * fetching from string
		 * .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").
		 * asPrettyString();
		 * 
		 * System.out.println("==========Priting repsonse in string");
		 * System.out.println(gcString);\
		 * 
		 * 
		 * 
		 */
		System.out.println("========Second services hit multiple times if needed ===========");
		// System.out.println("class object is sysout as below -->" + gc);

		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		// print course title with index get(1)
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		// print course title and price without index using for loop
		List<api> apiCourses = gc.getCourses().getApi();

		for (int i = 0; i < apiCourses.size(); i++) {
			System.out.println("inside for loop");
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getCourseTitle());
				System.out.println(apiCourses.get(i).getPrice());
			}

		}
		// COMPARE Expected course title with actual in webautomation node.
		String[] courseTitlesToCompare = { "Selenium Webdriver Java", "Cypress", "Protractor" };

		ArrayList<String> courseListFromService = new ArrayList<String>();
		List<WebAutomation> webautomation = gc.getCourses().getWebAutomation();
		for (int i = 0; i < webautomation.size(); i++) {

			courseListFromService.add(webautomation.get(i).getCourseTitle());

		}
		// Convert Expected String array in list using below method
		List<String> expectedList = Arrays.asList(courseTitlesToCompare);

		Assert.assertTrue(courseListFromService.equals(expectedList));

	}

}
