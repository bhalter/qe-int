package interview;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class StockPriceChecker {
    private static Log log = LogFactory.getLog(StockPriceChecker.class);

	public static void main(String[] args) throws InterruptedException
	{		
		log.info("Starting");	
		
		WebDriver driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// eliminate excessive logging of CSS parsing errors by the HTMLUnit framework
		// see: http://stackoverflow.com/questions/3600557/turning-htmlunit-warnings-off/7200842#7200842
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 

		double priceOnYahoo, priceOnGoogle;

		while (true) 
		{
			priceOnYahoo = checkYahoo(driver);
			priceOnGoogle = checkGoogle(driver);
			if (priceOnYahoo == priceOnGoogle)
			{
				log.info("The VMware stock price on Yahoo and Google is the same: " + priceOnYahoo);
			}
	    
			else
			{
				log.info("The VMware stock price on Yahoo and Google is different. Yahoo reports:" 
						+ priceOnYahoo + " and Google reports: " + priceOnGoogle);			
			}
			
			log.info("Now waiting 5 minutes before the next stock price check");			
			Thread.sleep(5*60*1000); //300,000 Milliseconds = 5 minutes
			
		}
		
		
    }
	
	private static double checkYahoo(WebDriver driver) {
		log.info("Checking the stock price on Yahoo");

	    driver.get("http://finance.yahoo.com");

	    //finance.yahoo.com "Enter Symbol" search box
	    WebElement symbolField = driver.findElement(By.id("txtQuotes"));
	   
	    //finance.yahoo.com "Look Up" button
	    WebElement lookUpButton = driver.findElement(By.id("btnQuotes"));

	    // Search for VMW stock
	    symbolField.sendKeys("VMW");
	    lookUpButton.click();

	    // Check the title of the page
	    String pageTitle = driver.getTitle();	    
	    if (pageTitle.startsWith("VMW"))
	    {
		    WebElement priceField = driver.findElement(By.id("yfs_l84_vmw"));
		    Double price =  Double.parseDouble(priceField.getText()); 			
			return (price.doubleValue());
	    }
	    else 
	    {
	    	return (0.0);
	    }
	
	}

	private static double checkGoogle(WebDriver driver) {
		log.info("Checking the stock price on Google");

		driver.get("http://finance.google.com");

	    //google.com/finance search box
	    WebElement symbolField = driver.findElement(By.id("gbqfq"));
	   
	    //google.com/finance search button
	    WebElement searchButton = driver.findElement(By.id("gbqfb"));

	    // Search for VMW stock
	    symbolField.sendKeys("VMW");
	    searchButton.click();

	    // Check the title of the page
	    String pageTitle = driver.getTitle();	    
	    if (pageTitle.startsWith("VMware"))
	    {
		    // Dynamic price is contained within the Price Panel
		    WebElement pricePanel = driver.findElement(By.id("price-panel"));
		    // The pricePanel text start with something like: "84.78 +1.24 (1.48%)"
		    // The first number in the string is what we want
		    String priceText = pricePanel.getText().split("\\s")[0];
		    return Double.parseDouble(priceText);
	    }
	    else
	    {
	    	return (0.0);
	    }
	    
	}
}


