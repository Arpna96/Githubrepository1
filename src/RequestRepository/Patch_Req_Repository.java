package RequestRepository;

import java.io.IOException;
import java.util.ArrayList;

import Common_API_Methods.Common_Utility_Method;

public class Patch_Req_Repository {
	
	public static String BaseURL() {
		String baseURL = "https://reqres.in/";
		return baseURL;
	}
	public static String Resource() {
		String Resource = "api/users/2";
		return Resource;
	}
	
	public static String Patch_Up_TC1() throws IOException {
		ArrayList<String> Req_Data = Common_Utility_Method.ReadDataExcel("PatchApi", "TC2");
		//System.out.println(Req_Data);
		String Req_Name = Req_Data.get(1);
		String Req_job = Req_Data.get(2);
		String RequestBody = "{\r\n"
				+ "    \"name\": \""+Req_Name+"\",\r\n"
				+ "    \"job\": \""+Req_job+"\"\r\n"
				+ "}";
		return RequestBody;
	}

}
