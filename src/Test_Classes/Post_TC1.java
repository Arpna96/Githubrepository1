package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.API_Methods;
import RequestRepository.Post_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Post_TC1 {
	@Test
	public static void execute() throws IOException {
		
        int statusCode = API_Methods.ResponseStatusCode(Post_Req_Repository.BaseURI(), Post_Req_Repository.Post_Resource(),Post_Req_Repository.Post_Cre_TC1());
        System.out.println(statusCode);
       
        String ResponseBody = API_Methods.ResponseBody(Post_Req_Repository.BaseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_Cre_TC1());
        System.out.println(ResponseBody);
       String RequestBody =Post_Req_Repository.Post_Cre_TC1();
        JsonPath jsprequest = new JsonPath(RequestBody);
		String Req_name = jsprequest.getString("name");
		String Req_job = jsprequest.getString("job");
		LocalDateTime currenttime = LocalDateTime.now();
		String Exprcteddate = currenttime.toString().substring(0, 11);
               
        JsonPath jspresponse = new JsonPath(ResponseBody);
		String res_name = jspresponse.getString("name");
		String res_job = jspresponse.getString("job");
		String res_createdAt = jspresponse.getString("createdAt");
		res_createdAt = res_createdAt.substring(0, 11);
	
		Assert.assertEquals(res_name, Req_name);
		Assert.assertEquals(res_job, Req_job);
		//Assert.assertEquals(res_createdAt, Expecteddate);   
	}

}
