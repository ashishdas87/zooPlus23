package com.selenium.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import groovy.beans.PropertyReader;

public class DataReader {
	 public static final String DEFAULT_PROPERTY_FILE = "/ConfigProperties/Configuration/config.properties";
	  //  private static String currentEnv = PropertyReader.getGlobalProperty("ENV");
	    	    
	    public static String readPropertyUrl(String Apps)throws Exception {

	        String appURL =  readPropertyFromFile("/configProperties/"+readPropertyFromConfigFile("ENV")+"/url.properties",Apps);
	        return appURL;
	    }
	    
	    public static String readPropertyFromFile(String relativePath, String property) throws Exception{
	        Properties data = new Properties();
	        String result = "";
	        try {
	            try (InputStream resource = PropertyReader.class.getResourceAsStream(relativePath)) {
	                if (null == resource) {
	                    //log.fail("Resource file not found: " + relativePath);
	                }
	                data.load(resource);
	                result = data.getProperty(property);
	                if (result == null) {
	                    result = "N/A";
	                }
	            }
	        } catch (Exception e) {
	            // log.fail("Resource file error: " + relativePath, e);
	        }
	        return result;
	    }

	    
	    public String readLoginProperties(String credentialKey) throws FileNotFoundException, Exception {
	        if (System.getProperty(credentialKey) == null)
	            return readPropertyFromFile("/envProperties/" + readPropertyFromConfigFile("ENV") + "/loginDetails.properties", credentialKey);
	        else return System.getProperty(credentialKey);
	    }
	    
	    public static String readPropertyFromConfigFile (String property) throws Exception{
	        return readPropertyFromFile(DEFAULT_PROPERTY_FILE, property);
	    }

}
