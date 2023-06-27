package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.API_Methods;
import Common_API_Methods.Common_Utility_Method;
import RequestRepository.Post_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Post_TC_U {
	@Test
	public static void execute() throws IOException {
		for (int i=0; i<5 ; i++) {
			int StatusCode = API_Methods.ResponseStatusCode(Post_Req_Repository.BaseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_Cre_TC1());
			System.out.println(StatusCode);
			if (StatusCode ==201) {
				String ResponseBody = API_Methods.ResponseBody(Post_Req_Repository.BaseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_Cre_TC1());
				System.out.println(ResponseBody);
				String RequestBody = Post_Req_Repository.Post_Cre_TC1();
				Common_Utility_Method.EvidenceCreator("Post_TC_U", RequestBody, ResponseBody, StatusCode);
				validator(RequestBody,ResponseBody);
				break;
			}
			else
			{
				System.out.println("Status invalid");
			}
		}
	}
	public static void validator(String Request , String ResponseBody) {
		JsonPath JspReq = new JsonPath(Request);
		String req_Name = JspReq.getString("name");
		String req_job  = JspReq.getString("job");
		LocalDateTime currenttime = LocalDateTime.now();
		String expecteddate = currenttime.toString().substring(0, 11);
		
		JsonPath JspRes = new JsonPath(ResponseBody);
		String res_Name = JspRes.getString("name");
		String res_job = JspRes.getString("job");
		String res_createdAt = JspRes.getString("createdAt");
		res_createdAt = res_createdAt.substring(0, 11);
		
		Assert.assertEquals(res_Name, req_Name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_createdAt, expecteddate);
	}
}
