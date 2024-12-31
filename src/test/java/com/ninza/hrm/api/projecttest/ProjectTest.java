package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;
import com.ninza.hrm.api.baseclass.BaseApiClass;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.genericutility.PropertiesFileUtility;
import com.ninza.hrm.api.pojoclass.projectPOJO;
import com.ninza.hrm.contains.endpoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectTest extends BaseApiClass{

	
	projectPOJO pobj;
@Test
public void addSingleProjectWithCreatedTest() throws Throwable   {
	String baseuri=flib.getDataFromPropertiesFile("BASEUri");
	
	String expsucMsg="Successfully Added";
	String projectname="abbq"+jlib.getRandomNumber();
	
	pobj=new projectPOJO(projectname,"Created","shabber",0);
	
	//verify the projectName in Api layer
	Response res=given().spec(specReqobj).body(pobj)
	.when().post(IEndPoint.ADDProject);
	      
	res.then().assertThat().statusCode(201)
		   .assertThat().time(Matchers.lessThan(3000L))
	.spec(specRespobj).log().all();
	
	String actmsg=res.jsonPath().get("msg");
	Assert.assertEquals(expsucMsg, actmsg);
	
	//verify the projectname in db layer
	
	boolean flag=dlib.executeQueryVerifyAndGetData("select * from project", 4, projectname);
	Assert.assertTrue(flag, "project in db is not verified");	
}

	@Test(dependsOnMethods = "addSingleProjectWithCreatedTest")
	public void createDuplicateProjectTest() throws Throwable {
		String baseuri=flib.getDataFromPropertiesFile("BASEUri");
		given().spec(specReqobj).body(pobj)
		.when().post(IEndPoint.ADDProject)     
		.then().assertThat().statusCode(409).spec(specRespobj).log().all();
	}
	
	
}
