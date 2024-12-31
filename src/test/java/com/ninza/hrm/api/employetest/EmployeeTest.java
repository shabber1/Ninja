package com.ninza.hrm.api.employetest;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import com.ninza.hrm.api.pojoclass.employeePojo;
import com.ninza.hrm.api.pojoclass.projectPOJO;
import com.ninza.hrm.contains.endpoints.IEndPoint;

import io.restassured.http.ContentType;


public class EmployeeTest extends BaseApiClass{
	

@Test
public void addEmployeeTest() throws Throwable {
	
	String baseuri=flib.getDataFromPropertiesFile("BASEUri");
	String projectname="abbc"+jlib.getRandomNumber();
	String username="user_"+jlib.getRandomNumber();
	//API--1 creating 
	
	projectPOJO pobj=new projectPOJO(projectname,"Created","shabber",0);
	
	given().spec(specReqobj).body(pobj)
	.when().post(IEndPoint.ADDProject).then().spec(specRespobj).log().all();
	
	
	//api-2 add employee to same project
	//String designation, String dob, String email, String empName, int experience,
	//String mobileNo, String project, String role, String username
	
	employeePojo empobj=new employeePojo("Arcitect", "24/04/1997", "sha@gmail.com", username, 15, "9951488523", projectname, "ROLE_EMPLOYEE", username);
	
	given().spec(specReqobj).body(empobj)
	.when().post(IEndPoint.ADDEmp)
	.then().assertThat()
	.statusCode(201).and().time(Matchers.lessThan(3000L)).spec(specRespobj).log().all();
	
	//verify employee name in DB
	boolean flag=dlib.executeQueryVerifyAndGetData("select * from employee", 5, username);
	Assert.assertTrue(flag, "project in db is not verified");	
Assert.assertTrue(flag, "employee in db is not verified");	
}

	@Test
	public void addEmployeewithoutEmailTest() throws Throwable {
		
		String baseuri=flib.getDataFromPropertiesFile("BASEUri");
		String projectname="abbc"+jlib.getRandomNumber();
		String username="user_"+jlib.getRandomNumber();
		//API--1 creating 
		
		projectPOJO pobj=new projectPOJO(projectname,"Created","shabber",0);
		
		given().spec(specReqobj).body(pobj)
		.when().post(IEndPoint.ADDProject).then().spec(specRespobj).log().all();
		
		
		//api-2 add employee to same project
		//String designation, String dob, String email, String empName, int experience,
		//String mobileNo, String project, String role, String username
		
		employeePojo empobj=new employeePojo("Arcitect", "24/04/1997", "", username, 15, "9951488523", projectname, "ROLE_EMPLOYEE", username);
		
		given().spec(specReqobj).body(empobj)
		.when().post(IEndPoint.ADDEmp)
		.then()
		.statusCode(500).spec(specRespobj).log().all();
	}
	

}
