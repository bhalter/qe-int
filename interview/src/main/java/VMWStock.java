import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VMWStock {
	//Variable declarations
	String googleFinanceUrl = "http://finance.google.com";
	String yahooFinanceUrl = "http://finance.yahoo.com";
	String ticker = "VMW";
	double googleVMWQuote;
	double yahooVMWQuote;
	String googleSearchBoxXpath = "/html/body/div/div/div[1]/div[1]/div[3]/div/div/div/form/fieldset[2]/div/div/div[2]/input";
	String googleSearchButtonXpath = "/html/body/div/div/div[1]/div[1]/div[3]/div/div/div/form/div/button";
	String googleQuoteXpath = "/html/body/div/div/div[3]/div[2]/div/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/span/span";
	String yahooSearchBoxXPath = "/html/body/div[1]/div[1]/section/div[3]/div[3]/div[2]/form/table/tbody/tr/td[1]/input";
	String yahooSearchButtonXPath = "/html/body/div[1]/div[1]/section/div[3]/div[3]/div[2]/form/table/tbody/tr/td[2]/input";
	String yahooQuoteXPath = "/html/body/div[4]/div/div[3]/div[2]/div/div/div[2]/div[1]/span[1]/span";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Variable declarations
		final VMWStock vmw = new VMWStock();
		final Log log = LogFactory.getLog(VMWStock.class);
		log.info("Starting execution");
		Timer timer = new Timer();
		TimerTask quoteTask = new TimerTask () {
		    @Override
		    public void run () {
		    	//Execute function to get quotes
		    	vmw.compareQuotes();
		    }
		};
		// schedule the task to run starting now and then every hour...
		timer.schedule (quoteTask, 0l, 1000*60*5);


		
	}
	
	public void compareQuotes() {
		final Log vmwStockLog = LogFactory.getLog(VMWStock.class);
		//Get webdriver instance
		final WebDriver driver = new FirefoxDriver();
		//Get current hour and minutes
		Calendar calendar = Calendar.getInstance();
		int hrs = calendar.get(Calendar.HOUR_OF_DAY);
		int mins = calendar.get(Calendar.MINUTE);
		//Print current timestamp
		vmwStockLog.info("Current time is: " + hrs + ":" + mins);
		//Go to Google Finance URL
		driver.get(googleFinanceUrl);
		//Get elements on Google finance page using XPath and execute tasks to get quote as double
		WebElement googleSearchBox = driver.findElement(By.xpath(googleSearchBoxXpath));
		googleSearchBox.sendKeys(ticker);
		WebElement googleSearchButton = driver.findElement(By.xpath(googleSearchButtonXpath));
		googleSearchButton.click();
		googleVMWQuote = Double.parseDouble(driver.findElement(By.xpath(googleQuoteXpath)).getText());
		
		//Go to Yahoo Finance URL
		driver.get(yahooFinanceUrl);
		//Get elements on Yahoo finance page using XPath and execute tasks to get quote as double
		WebElement yahooSearchBox = driver.findElement(By.xpath(yahooSearchBoxXPath));
		yahooSearchBox.sendKeys(ticker);
		WebElement yahooSearchButton = driver.findElement(By.xpath(yahooSearchButtonXPath));
		yahooSearchButton.click();
		yahooVMWQuote = Double.parseDouble(driver.findElement(By.xpath(yahooQuoteXPath)).getText());
		
		//Compare actual quotes
		if (googleVMWQuote > yahooVMWQuote) {
			vmwStockLog.info("Google finance quote for VMW: " + googleVMWQuote + " is greater than " +
					"Yahoo finance quote for VMW:" + yahooVMWQuote);
		}
		else if (googleVMWQuote < yahooVMWQuote) {
			vmwStockLog.info("Yahoo finance quote for VMW: " + yahooVMWQuote + " is greater than " +
					"Google finance quote for VMW:" + googleVMWQuote);
		}
		else {
			vmwStockLog.info("Both Google finance and Yahoo finance have same quote: " + googleVMWQuote);
		}
		driver.quit();
	}
		

}
