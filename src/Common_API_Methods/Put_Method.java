package Common_API_Methods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Put_Method {
	
	public static int ResponseStatusCode(String BaseURL, String Resource, String RequestBody) {
		
		RestAssured.baseURI = BaseURL;
		
		int StatusCode = given().header("Content-Type","application/json").body(RequestBody).when().put(Resource).then().extract().statusCode();
		return StatusCode;
		
	}
	
   public static String  ResponseBody(String BaseURL, String Resource, String RequestBody) {
		
		RestAssured.baseURI = BaseURL;
		
		String ResponseBody= given().header("Content-Type","application/json").body(RequestBody).when().put(Resource).then().extract().response().asString();
		return ResponseBody;

    }
}
