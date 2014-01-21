package com.vmware.vcac.qe;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public final class Config {
	private static final String CONFIG_FILE_LOC = "src/main/resources/config.properties";
	public static String GOOGLE_FINANACE_URL;
	public static String YAHOO_FINANACE_URL;
	public static String SYMBOL;
	public static String GOOGLE_SYMBOL_XPATH;
	public static String YAHOO_SYMBOL_XPATH;
	
	
	public static void loadConfig() throws IOException{
		Properties config = new Properties();
	     try{
	    	 
	    	 config.load(new FileInputStream(CONFIG_FILE_LOC));
	     }
	     catch (IOException e){	    	
	         throw new IOException(e.toString());
	     }
	     GOOGLE_FINANACE_URL = config.getProperty("GOOGLE_FINANACE_URL");
	     YAHOO_FINANACE_URL = config.getProperty("YAHOO_FINANACE_URL");
	     SYMBOL = config.getProperty("SYMBOL");
	     YAHOO_SYMBOL_XPATH = config.getProperty("YAHOO_SYMBOL_XPATH");
	     GOOGLE_SYMBOL_XPATH = config.getProperty("GOOGLE_SYMBOL_XPATH");
	}
}