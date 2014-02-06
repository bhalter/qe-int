package interview;

import static org.junit.Assert.*;

import org.apache.commons.logging.impl.SimpleLog;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import interview.LogFactory;
import interview.WebAccess;

public class WebAccessTests {
	public static SimpleLog log;
	private static final String loggerName = "ProcessorTests";
	private static WebAccess webAccess = null;
	
	private static String defaultChromeDriverPath;
	private static String defaultChromeBinaryPath;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log = LogFactory.getLogger(loggerName, LogFactory.LogLevel.WARN); 
		webAccess = new WebAccess(WebAccess.WebBrowser.FIREFOX,log);
		defaultChromeDriverPath = WebAccess.getChromeDriverPath();
		defaultChromeBinaryPath = WebAccess.getChromeBinaryPath();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		webAccess.releaseDriver();
	}

	@Before
	public void setUp() throws Exception {
		WebAccess.setChromeDriverPath(defaultChromeDriverPath);		
		WebAccess.setChromeBinaryPath(defaultChromeBinaryPath);
		WebDriver driver = webAccess.getNewWebDriver(WebAccess.WebBrowser.FIREFOX);
		assertNotNull(driver);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetChromeDriverPath() {
		String newPath = "newPath";
		String oldPath = WebAccess.setChromeDriverPath(newPath);
		assertEquals(oldPath,defaultChromeDriverPath);
		String testPath = WebAccess.getChromeDriverPath();
		assertEquals(newPath,testPath);
	}

	@Test
	public void testSetChromeBinaryPath() {
		String newPath = "newPath";
		String oldPath = WebAccess.setChromeBinaryPath(newPath);
		assertEquals(oldPath,defaultChromeBinaryPath);
		String testPath = WebAccess.getChromeBinaryPath();
		assertEquals(newPath,testPath);
	}

	@Test
	public void testWebAccess() throws Exception {
		assertNotNull(webAccess.getDriver());
	}
	
	@Test
	public void testReleaseDriver() {
		assertNotNull(webAccess.getDriver());
		webAccess.releaseDriver();
		assertNull(webAccess.getDriver());
	}
	
	// TODO:  Should test with CHROME and FIREFOX explicitly.
	@Test
	public void testGetNewWebDriver() throws Exception {
		WebDriver driver = webAccess.getNewWebDriver(WebAccess.WebBrowser.FIREFOX);
		assertNotNull(driver);		
	}

	// TODO:  Should test with CHROME and FIREFOX explicitly.
	@Test
	public void testLoadWebPage() throws Exception {		
		String URI = "http://finance.yahoo.com/q?s=VMW&ql=0";
		String elementId  = "yfs_l84_vmw";
		boolean waitSuccess = false;
		try {			
			webAccess.loadWebPage(URI,elementId);
			waitSuccess = true;
		}
		catch( Exception e) {
			System.out.println("Exception: " + e.getMessage());
			waitSuccess = false;
		}
		assertTrue(waitSuccess);
	}

	@Test
	public void testGetGoogleValue() throws InterruptedException {
		assertNotNull(webAccess.getDriver());
		Float value = webAccess.getGoogleValue();
		log.debug("google value: " + value.toString());
		assertFalse((new Float(0.0f)).equals(value));
	}

	@Test
	public void testGetYahooValue() throws InterruptedException {
		assertNotNull(webAccess.getDriver());
		Float value = webAccess.getYahooValue();
		log.debug("yahoo value: " + value.toString());
		assertFalse((new Float(0.0f)).equals(value));
	}


	
}
