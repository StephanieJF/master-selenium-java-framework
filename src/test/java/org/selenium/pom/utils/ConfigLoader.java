package org.selenium.pom.utils;

import java.util.Properties;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configLoader;
	
	private ConfigLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
	}
	
	public static ConfigLoader getInstance() {
		if(configLoader == null) { //if there is no instance of the class
			configLoader = new ConfigLoader();  //then create the new instance and assign to the variable
		}								//if there is then don't create a new one, there will only be one instance of the class
		return configLoader;
	}
	
	
	public String getBaseUrl() {
		String prop = properties.getProperty("baseUrl");
		if(prop != null) return prop;
		else throw new RuntimeException("property baseUrl is not specified in the config.properties file");
	}

	
	public String getUserName() {
		String prop = properties.getProperty("username");
		if(prop != null) return prop;
		else throw new RuntimeException("property username is not specified in the config.properties file");
	}

	
	public String getPassword() {
		String prop = properties.getProperty("password");
		if(prop != null) return prop;
		else throw new RuntimeException("property password is not specified in the config.properties file");
	}
	
}
