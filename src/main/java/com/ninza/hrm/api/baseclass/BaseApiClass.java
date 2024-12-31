package com.ninza.hrm.api.baseclass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static io.restassured.RestAssured.*;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.genericutility.PropertiesFileUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class BaseApiClass {
	public JavaUtility jlib = new JavaUtility();
	public PropertiesFileUtility flib=new PropertiesFileUtility();
	public DataBaseUtility dlib=new DataBaseUtility();
	public static RequestSpecification specReqobj;
	public static ResponseSpecification specRespobj;
	@BeforeSuite
	public void configBS() throws Throwable {
		dlib.getDbconnection();
		System.out.println("=====Connect to DB======");
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
//		builder.setAuth(basic("username","password"));
//		builder.addHeader("","");
		builder.setBaseUri(flib.getDataFromPropertiesFile("BASEUri"));
		specReqobj=builder.build();
		
		ResponseSpecBuilder resbuilder=new ResponseSpecBuilder();
		resbuilder.expectContentType(ContentType.JSON);
		 specRespobj = resbuilder.build();
	}
	@AfterSuite
	public void configAS() throws Throwable {
		dlib.closedbconnection();
		System.out.println("=====disconnect to DB======");
		
		
	}
}
