package Test_Classes;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Put_Method;
import RequestRepository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_TC1 {
	@Test
	public static void execute() {
		int StatusCode = Put_Method.ResponseStatusCode(Put_Req_Repository.BaseURI(),Put_Req_Repository.Put_Resource() ,Put_Req_Repository.Put_Up_TC1());
		System.out.println(StatusCode);
		
		String ResponseBody = Put_Method.ResponseBody(Put_Req_Repository.BaseURI(),Put_Req_Repository.Put_Resource() ,Put_Req_Repository.Put_Up_TC1());
		System.out.println(ResponseBody);
		
		JsonPath jspResponse = new JsonPath(ResponseBody);
		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		//String res_updatedAt = jspResponse.getString("updatedAt");
		//res_updatedAt = res_updatedAt.substring(0,11);
		//validate the responseBody parameters
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
		//Assert.assertEquals(res_updatedAt, "2023-06-06T");
	}

}
