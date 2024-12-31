package com.ninza.hrm.api.genericutility;

import java.util.List;
import static io.restassured.RestAssured.*;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtility {
	
	PropertiesFileUtility flib=new PropertiesFileUtility();

	public String getDataonJsonPath(Response res,String jsonXpath) {
		List<Object> list=JsonPath.read(res.asString(), jsonXpath);
		return list.get(0).toString();	
	}
	
	public String getDataOnXpath(Response res,String xmlXpath) {
		return res.xmlPath().get(xmlXpath);
	}
	
	public boolean VerifyDataOnJsonPath(Response res,String jsonXpath,String expectedData) {
		List<String>list=JsonPath.read(res.asString(), jsonXpath);
		boolean flag=false;
		for(String str:list) {
			if(str.equals(expectedData)) {
				System.out.println(expectedData+" is available==PASS");
				flag=true;
			}
		}
		if(flag==false) {
			System.out.println(expectedData+" is not available==FAIL");
		}
		return flag;
	}
	
	public String getAccessToken() throws Throwable {
		Response res=given().formParam("client_id", flib.getDataFromPropertiesFile("ClientID"))
					.formParam("client_secret",  flib.getDataFromPropertiesFile("ClientSecret"))
					.formParam("grant_type", "client_credentials") 
					.when().post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
		res.then().log().all();
		
		String token = res.jsonPath().get("access_token");
		return token;
	
	
	
	
	
	}
	
	
	
	
	
	
	
}
