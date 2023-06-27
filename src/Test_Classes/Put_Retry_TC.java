package Test_Classes;

import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Put_Method;
import RequestRepository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_Retry_TC {
	@Test
	public static void execute() {
		for(int i=0 ; i<5 ; i++) {
			int StatusCode = Put_Method.ResponseStatusCode(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_Up_TC1());
			System.out.println(StatusCode);
			if (StatusCode == 200) {
				String ResponseBody = Put_Method.ResponseBody(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_Up_TC1());
				System.out.println(ResponseBody);
				String RequestBody = Put_Req_Repository.Put_Up_TC1();
				validator(RequestBody,ResponseBody);
				break;
			}
			else 
			{
				System.out.println("StatusCode Invalid");
			}
		}
	}
  public static void validator(String RequestBody,String ResponseBody) {
		JsonPath jspRequest = new JsonPath(RequestBody);
		String req_name = jspRequest.getString("name");
		String req_job = jspRequest.getString("job");
		LocalDateTime currenttime = LocalDateTime.now();
		String expecteddate = currenttime.toString().substring(0, 11);
		JsonPath jspResponse = new JsonPath(ResponseBody);
		String res_name = jspResponse.getString("name");
		String res_job = jspResponse.getString("job");
		String res_updatedAt = jspResponse.getString("updatedAt");
		res_updatedAt = res_updatedAt.substring(0,11);
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt, expecteddate);
  }
}
