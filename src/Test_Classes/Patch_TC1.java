package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.Put_Method;
import RequestRepository.Patch_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Patch_TC1 {
	@Test
	public static void execute() throws IOException {
		int StatusCode = Put_Method.ResponseStatusCode(Patch_Req_Repository.BaseURL(), Patch_Req_Repository.Resource(), Patch_Req_Repository.Patch_Up_TC1());
		System.out.println(StatusCode);
		
		String ResponseBody = Put_Method.ResponseBody(Patch_Req_Repository.BaseURL(), Patch_Req_Repository.Resource(), Patch_Req_Repository.Patch_Up_TC1());
		System.out.println(ResponseBody);
		String RequestBody = Patch_Req_Repository.Patch_Up_TC1();
		JsonPath JspReq = new JsonPath(RequestBody);
		String Req_name = JspReq.getString("name");
		String Req_job = JspReq.getString("job");
		LocalDateTime currenttime = LocalDateTime.now();
		String expecteddate = currenttime.toString().substring(0, 11);
		
		 JsonPath JspResponse = new JsonPath(ResponseBody);
	       String res_name = JspResponse.getString("name");
	       String res_job = JspResponse.getString("job");
	       String res_updatedAt = JspResponse.getString("updatedAt");
	       res_updatedAt = res_updatedAt.substring(0, 11);
	       Assert.assertEquals(res_name, Req_name);
	       Assert.assertEquals(res_job, Req_job);
	       Assert.assertEquals(res_updatedAt, expecteddate);
	}

}
