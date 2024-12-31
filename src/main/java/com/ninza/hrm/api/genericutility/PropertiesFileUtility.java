package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesFileUtility {
	
	public String getDataFromPropertiesFile(String key) throws Throwable {
		FileInputStream fis = new FileInputStream("./config_env_data/configEnvdata.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		String data=properties.getProperty(key);
		return data;
	
}
}
