package org.selenium.pom.utils;

import java.util.Properties;

import org.selenium.pom.constants.EnvType;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configLoader;
	
	private ConfigLoader() {
		String env = System.getProperty("env", String.valueOf(EnvType.STAGING));
		
		switch (EnvType.valueOf(env)) {
		case STAGING:
			properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
			break;
		case PRODUCTION: 
			properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
			break;
		default: throw new IllegalStateException("Invalid env type: " + env);
		}
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
