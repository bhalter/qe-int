package main.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Main {
	static WebDriver ieDriver;
	static int stockWatchTime = 300; //5 minutes
	static Timer timer;

	public static void main(String[] args) {
	}
	
	/**
	 * Main method that can be called externally to kick of stock price comparison
	 */
	public static void CompareStocksOnSchedule() {
		File ieDriverFile = new File("C:/Users/Nidhi/Downloads/IEDriverServer_x64_2.37.0/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", ieDriverFile.getAbsolutePath());
		
		Timer timer = new Timer();
		 timer.scheduleAtFixedRate(new CompareVMWStockPrice(), 0, stockWatchTime*1000);
	}

	/**
	 * Compare VMW Stock Price Class
	 * @author Nidhi
	 *
	 */
	static class CompareVMWStockPrice extends TimerTask {
		
        @Override
        public void run() {
        	CompareStockPrice();
            //timer.cancel(); //Not necessary because we call System.exit
        }
        
        /**
         * Gets VMW stock price from Yahoo and Google finance and compares the price 
         */
    	private static void CompareStockPrice()
    	{
    		ieDriver = new InternetExplorerDriver();
    		
    		java.util.Date date= new java.util.Date();
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		System.out.println("Getting stock price at " + dateFormat.format(date));
    		
    		double YahooVMW = GetYahooStocksValue("VMW");
    		System.out.println("Yahoo value = " + YahooVMW);
    		double GoogleVMW = GetGoogleStocksValue("VMW");
    		System.out.println("Yahoo value = " + GoogleVMW);
    		if (YahooVMW == GoogleVMW)
    		{
    			System.out.println("The VMWare stock price on Yahoo Finance and Google Finance is equal.");
    			
    		} else 
    		{
    			System.out.println("The VMWare stock price on Yahoo Finance and Google Finance is NOT equal.");
    		}

    		ieDriver.quit();		
    	}
    	
    	/**
    	 * Get the current price for a stock from Yahoo Finance
    	 * @param stockSymbol
    	 * @return
    	 */
    	private static double GetYahooStocksValue(String stockSymbol)
    	{
    		ieDriver.get("http://finance.yahoo.com/");
    		WebElement ieWebElement = ieDriver.findElement(By.id("mnp-search_box"));
    		ieWebElement.sendKeys(stockSymbol);
    		ieWebElement.sendKeys(Keys.RETURN);
    		
    		ieWebElement = ieDriver.findElement(By.id("yfs_l84_vmw"));
    		double val = Double.parseDouble(ieWebElement.getText());
    		return val;
    	}
    	
    	/**
    	 * Get the current price for a stock from Google Finance
    	 * @param stockSymbol
    	 * @return
    	 */
    	private static double GetGoogleStocksValue(String stockSymbol)
    	{
    		ieDriver.get("https://www.google.com/finance");
    		WebElement ieWebElement = ieDriver.findElement(By.id("gbqfq"));
    		ieWebElement.sendKeys(stockSymbol);
    		ieWebElement.sendKeys(Keys.RETURN);
    		
    		ieWebElement = ieDriver.findElement(By.id("ref_718288_l"));
    		double val = Double.parseDouble(ieWebElement.getText());
    		return val;
    	}
    }


}
