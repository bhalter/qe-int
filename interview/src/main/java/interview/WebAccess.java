/**
 * 
 */
package interview;

import org.apache.commons.logging.impl.SimpleLog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;


public class WebAccess {

	public static enum WebBrowser {
		FIREFOX, CHROME;
	}
	
	private static String ChromeDriverPath = "F:\\Tools\\Selenium\\chromedriver_win32\\chromedriver.exe";
	private static String ChromeBinaryPath = "C:\\Documents and Settings\\crhoffman\\Local Settings\\Application Data\\Google\\Chrome\\Application\\chrome.exe";
	private static String ChromeLogfilePath= "C:\\TEMP\\chromedriver.log";
	
	public static String setChromeDriverPath(String path) {
		String prior = ChromeDriverPath;
		ChromeDriverPath = path;
		return prior;
	}
	
	public static String setChromeBinaryPath(String path) {
		String prior = ChromeBinaryPath;
		ChromeBinaryPath = path;
		return prior;
	}	

	public static String getChromeDriverPath() {
		return ChromeDriverPath;
	}
	
	public static String getChromeBinaryPath() {
		return ChromeBinaryPath;
	}

	
	// -----------------------------------------------------------------------
	private SimpleLog log;
	private WebDriver driver = null;
	private WebBrowser webBrowser= null;
	
	public WebAccess(WebBrowser webBrowser, SimpleLog log) throws Exception {
        this.log = log;
        this.driver = getNewWebDriver(webBrowser);
        this.webBrowser= webBrowser; // Set after getWebDriver() call, as this is checked.
	}
	
	// For unit test
	public WebDriver getDriver() { return driver; }
		
	
	public WebDriver getNewWebDriver(WebBrowser webBrowser) throws Exception {
		if (webBrowser == this.webBrowser){
			return driver;
		}
		
		if (driver != null) {
			releaseDriver();
		}
		
		switch (webBrowser) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", ChromeDriverPath);		
			System.setProperty("webdriver.chrome.logfile", ChromeLogfilePath);			
			ChromeOptions options = new ChromeOptions();
			options.setBinary(ChromeBinaryPath);
			driver = new ChromeDriver(options);
			break;
		default:
			throw new Exception("Unsupported webBrowser: " + webBrowser.toString());
		}
		return driver;
	}
	
	public void releaseDriver() {
		driver.quit();	
		driver = null;
		webBrowser= null;
	}
		
	public void loadWebPage(final String URI, final String testElementId) {
		driver.get(URI);
		(new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean found = false;
				try {
					found = driver.findElement(By.id(testElementId)) != null;
				}
				catch (NoSuchElementException e) {
					found = false;
				}					
				return (found);
			}
		});
	}

	// It is coincidental that Google and Yahoo both have their stock prices
	// in a <span> with a unique id.   This may not be so in the future, so do not
	// use a common method.
	
	public Float getGoogleValue() throws InterruptedException {
		log.debug("getGoogleValue");
		String URI = "https://www.google.com/finance?q=NYSE%3AVMW&ei=80vxUqDxOISjiALRzQE";
		String elementId  = "ref_718288_l";
		Float value = 0.0f; 
		try {			
			loadWebPage(URI, elementId);
			WebElement element = driver.findElement(By.id(elementId));
	    	value = Float.parseFloat(element.getText());
		}
		catch( Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return value;
	}
	
	public Float getYahooValue() throws InterruptedException {
		log.debug("getYahooValue");
		String URI = "http://finance.yahoo.com/q?s=VMW&ql=0";
		String elementId  = "yfs_l84_vmw";
		Float value = 0.0f; 
		try {
			loadWebPage(URI, elementId);
			WebElement element = driver.findElement(By.id(elementId));
	    	value = Float.parseFloat(element.getText());
		}
		catch( Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return value;
	}
	
}
