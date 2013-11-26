package com.vmware.interview.test;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static final ConfigManager INSTANCE = new ConfigManager();
	private Properties properties;
	
	private ConfigManager(){
		this("config.properties");
	}
	
	public ConfigManager(String configFile) {
		this.properties = loadProperties(configFile);
	}
	
	private Properties loadProperties(String fileName) {
		assert(fileName != null);
        assert(fileName.length() > 0);
        try {
        	Properties properties = new Properties();
        	properties.load(getClass().getClassLoader().getResourceAsStream(fileName));
        	return properties;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
	}
	
	public static ConfigManager getInstance() {
		return INSTANCE;
	}
	
	public long getDelayInMillis() {
		return longValueOf(this.properties.getProperty("timerDelay", "100"));
	}
	
	public long getIntervalInMillis() {
		return longValueOf(this.properties.getProperty("timerInterval", "300000"));
	}
	
	private long longValueOf(String str) {
		try {
			return Long.valueOf(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static final class Constants {
		public static final String VMW_CODE = "VMW";
	}
}
