package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Common_Utility_Method;
import Common_API_Methods.Patch_Method;
import RequestRepository.Patch_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Patch_Retry {
	@Test (priority = 2)
	public static void execute() throws IOException {
		System.out.println("Extractor Method Call");
		for (int i=0 ; i<5 ; i++) {
			int StatusCode = Patch_Method.ResponseStatusCode(Patch_Req_Repository.BaseURL(), Patch_Req_Repository.Resource(), Patch_Req_Repository.Patch_Up_TC1());
			System.out.println(StatusCode);
			if (StatusCode==200) {
					String ResponseBody = Patch_Method.ResponseBody(Patch_Req_Repository.BaseURL(), Patch_Req_Repository.Resource(), Patch_Req_Repository.Patch_Up_TC1());
					System.out.println(ResponseBody);
					String RequestBody = Patch_Req_Repository.Patch_Up_TC1();
					validator(RequestBody,ResponseBody);
					Common_Utility_Method.EvidenceCreator("Patch_Retry", RequestBody, ResponseBody, StatusCode);
					break;
			}
			else
			{
				System.out.println("StatusCode INVALID");
			}
		}
	}
	@Test (priority = 1)
public static void validator(String RequestBody,String ResponseBody) {
		System.out.println("Validator Method Call");
	JsonPath JspRequest = new JsonPath(RequestBody);
	String req_name = JspRequest.getString("name");
	String req_job = JspRequest.getString("job");
	LocalDateTime currenttime = LocalDateTime.now();
	String expecteddate = currenttime.toString().substring(0, 11);
	 JsonPath JspResponse = new JsonPath(ResponseBody);
     String res_name = JspResponse.getString("name");
     String res_job = JspResponse.getString("job");
     String res_updatedAt = JspResponse.getString("updatedAt");
     res_updatedAt = res_updatedAt.substring(0, 11);
     Assert.assertEquals(res_name, req_name);
     Assert.assertEquals(res_job, req_job);
     Assert.assertEquals(res_updatedAt, expecteddate);
	
}
}
