package oAuth2Validation;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuth2Validation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String response=	given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type","client_credentials")
		.formParam("scope","trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asPrettyString();
	
	System.out.println(response);
	JsonPath jsonp = new JsonPath(response);
	String accessToken=jsonp.getString("access_token");
	
	String response2= given()
			.queryParams("access_token",accessToken)
			.when()
			.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asPrettyString();
	System.out.println("========Second services hit multiple times===========");
	
	System.out.println(response2);
		
	}

}
