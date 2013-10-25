package main.java;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetStockPrice {

	static Logger appLogs = Logger.getLogger(GetStockPrice.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		while(true){
			try {
				Thread.sleep(1000*60*5);
				// Declare a new Firefox driver and go to Yahoo Finance
				FirefoxDriver driver = new FirefoxDriver();
				//Add a global implicit wait of 
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				// Part 1: Getting the VMW stock price from Yahoo Finance
				driver.get("http://www.finance.yahoo.com");
				appLogs.debug("Successfully launched Finance.yahoo.com");
				
				

				WebElement symbolField = driver.findElement(By.xpath("//input[@name='s']"));
				symbolField.click();
				symbolField.sendKeys("VMW");

				WebElement lookupBtn = driver.findElement(By.xpath("//input[@id='btnQuotes']"));
				lookupBtn.click();

				boolean yahooTitleText = driver.findElement(By.xpath("//div[@class='title']/h2"))
						                       .getText().contains("VMw");
				if (!yahooTitleText) {
					appLogs.debug("Invalid Stock Symbol of VMware on Yahoo Finance Page");
				} else {
					appLogs.debug("Valid Stock Symbol of VMware on Yahoo Finance Page");
				}

				double yahooPrice = Double.parseDouble(driver.findElement(
						                 By.xpath("//span[@class='time_rtq_ticker']/span")).getText());
				appLogs.debug("VMW stock price on Yahoo is: "+yahooPrice);

				// Part 2: Getting VMW stock price from Google
				driver.get("http://www.google.com/finance");
				appLogs.debug("Successfully navigated to Google Finance");
				
				WebElement searchStock = driver.findElement(By.xpath("//input[contains(@class, 'searchbox-input')]"));
				searchStock.click();
				searchStock.sendKeys("VMw");
				searchStock.sendKeys(Keys.ENTER);
				
				WebElement searchBtn = driver.findElement(By.xpath("//button[@id='gbqfb']"));
				searchBtn.click();
				
				boolean googleTitleText = driver.findElement(By.xpath("//div[@class='appbar-snippet-primary']/span"))
						                        .getText().contains("VMw");
				if (!googleTitleText) {
					appLogs.debug("Invalid Stock Symbol of VMware on Google Finance Page");
				} else {
					appLogs.debug("Valid Stock Symbol of VMware on Google Finance Page");
				}
				
				double googlePrice = Double.parseDouble(driver.findElement(
						                                    By.xpath("//div[@id='price-panel']//span[@id='ref_718288_l']"))
						                                      .getText());
				appLogs.debug("VMW stock price on Yahoo is: "+googlePrice);

				double diff = yahooPrice - googlePrice;
				
				if(diff==0)
					appLogs.debug("VMW stock price Equal on Yahoo Finance("+yahooPrice+") and Google Finance("+googlePrice+")");
				else if(diff>1)
					appLogs.debug("VMW stock great on Yahoo Finance("+yahooPrice+") by "+diff+" as compared to Google Finance("+googlePrice+")");
				else 
					appLogs.debug("VMW stock great on Google Finance("+googlePrice+") by "+diff+" as compared to Yahoo Finance("+yahooPrice+")");

				driver.quit();
			} catch ( InterruptedException ie )  {
				
			}
		}

	}

}
