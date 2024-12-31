package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection con;
	ResultSet result=null;
	PropertiesFileUtility flib=new PropertiesFileUtility();
	public void getDbconnection(String url,String username,String password) {
		try {
			Driver dbDriver=new Driver();	  
			  DriverManager.registerDriver(dbDriver);
			 con = DriverManager.getConnection(url,username,password);
		}catch (Exception e) {
		}
	}
	public void getDbconnection() throws Throwable {
		try {
			Driver dbDriver=new Driver(); 
			  DriverManager.registerDriver(dbDriver);
			 con = DriverManager.getConnection(flib.getDataFromPropertiesFile("DBUrl"),flib.getDataFromPropertiesFile("DB_Username"),flib.getDataFromPropertiesFile("Db_Password"));
		}catch (Exception e) {
		}
	}
	public void closedbconnection() throws SQLException {
		con.close();
	}
	public ResultSet executeConSelectQuery(String query) {
		
		try {
			Statement stat=con.createStatement();
				result=stat.executeQuery(query);		
			}catch (Exception e) {
			}
			return result;
		}
	public int ExecuteNonSelectQuery(String query) {
		int result=0;
		try {
			Statement stat=con.createStatement();
			result=stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return result;
	}
		
		public boolean executeQueryVerifyAndGetData(String query, int columnIndex, String ExpectedData) throws Throwable {
			boolean flag = false;
			result=con.createStatement().executeQuery(query);
			while(result.next()) {
				if(result.getString(columnIndex).equals(ExpectedData)) {
					flag=true;
					break;
				}
			}
			if(flag) {
				System.out.println(ExpectedData+"===>data verified in data base table");
				return true;
			}else {
				System.out.println(ExpectedData+"===>data not verified in data base table");
				return false;
			}
		}
	}

