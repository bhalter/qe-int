package testing;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Inter {
	
	private static Log LOG = LogFactory.getLog(Inter.class);
	
	public static void main(String[] args) {

		Double yahooQuote = getYahooPrice();

		Double googleQuote = getGooglePrice();
		
		if ( yahooQuote > googleQuote )
		{
			System.out.println("Price from Yahoo is higher: " + yahooQuote);
			LOG.info("Price from Yahoo is higher: " + yahooQuote);
		}
		else if (yahooQuote == googleQuote)
		{
			System.out.println("They are equal");
			LOG.info("They are equal");
		}
		else 
		{
			System.out.println("Price from google is higher: " + googleQuote);
			LOG.info("Price from google is higher: " + googleQuote);
		}
	}
	
	public static Double getGooglePrice() {
		return 80.12;
	}

	public static Double getYahooPrice() {

		// The Firefox driver
		WebDriver driver = new FirefoxDriver();

		driver.navigate().to("http://finance.yahoo.com/");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("p"));

		// Enter something to search for
		element.sendKeys("vmw");

		// Click the stock button
		WebElement stockButton = driver.findElement(By.id("yucs-sprop_button"));
		stockButton.click();

		// Get the resulting text price
		String quote = driver.findElement(By.id("yfs_l84_vmw")).getText();
		
	    Double quoteDouble =	Double.parseDouble(quote);

		// Close the driver
		driver.close();

		// return the price
		return quoteDouble;
	}
}