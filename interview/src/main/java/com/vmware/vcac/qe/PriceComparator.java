package com.vmware.vcac.qe;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PriceComparator implements Runnable {

	private  WebDriver yahooDriver;
	private  WebDriver googleDriver;
	
	//This method is to set up the configuration required and open the browsers
	public void setup()  {
		
		try {
			Config.loadConfig();
			yahooDriver = new FirefoxDriver();
			googleDriver = new FirefoxDriver();
			yahooDriver.get(Config.YAHOO_FINANACE_URL+Config.SYMBOL);
			googleDriver.get(Config.GOOGLE_FINANACE_URL+Config.SYMBOL);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	//This method is to close the web drivers and any other clean up stuff
	public void tearDown() {
			yahooDriver.close();
			googleDriver.close();
	}

	//This method will run periodically every 5 minutes for 20 minutes
	@Override
	public void run() {
		
		//Refresh the page to get latest data
		yahooDriver.navigate().refresh();  
		googleDriver.navigate().refresh();

		String googlePriceStr = googleDriver.findElement(By.xpath(Config.GOOGLE_SYMBOL_XPATH)).getText();
		String yahooPriceStr = yahooDriver.findElement(By.xpath(Config.YAHOO_SYMBOL_XPATH)).getText();
		Double googlePrice = Double.valueOf(googlePriceStr).doubleValue();
		Double yahooPrice = Double.valueOf(yahooPriceStr).doubleValue();
		if(googlePrice.equals(yahooPrice))
			System.out.println("Price Same from both data sources : Price = "+googlePrice);
		else if(googlePrice > yahooPrice )
			System.out.println("Google Price greater: Google Price = "+googlePrice+ ": Yahoo Price = "+yahooPrice);
		else if(googlePrice < yahooPrice )
			System.out.println("Yahoo Price greater: Google Price = "+googlePrice+ ": Yahoo Price = "+yahooPrice);
	}	
}
