package interview;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.logging.impl.SimpleLog;

import interview.LogFactory;
import interview.WebAccess;
import interview.Processor;


public class ProcessorTests {

	public static SimpleLog log;
	private static final String loggerName = "ProcessorTests";
	private static WebAccess webAccess = null;
	private static Processor processor;
	
	public ProcessorTests() throws Exception {
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log = LogFactory.getLogger(loggerName, LogFactory.LogLevel.WARN); 
		webAccess = new WebAccess(WebAccess.WebBrowser.FIREFOX,log);            
        processor = new Processor(webAccess,log);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		webAccess.releaseDriver();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testProcessor() {
		assertEquals(0,processor.getCompareResult());
		assertEquals(0.0f,processor.getGoogleValue(), 0.001f);
		assertEquals(0.0f,processor.getYahooValue(), 0.001f);
	}

	@Test
	public void testRunOnce() throws InterruptedException {
		processor.runOnce(1);   
		assertEquals(0,processor.getCompareResult());
		Float g1 = processor.getGoogleValue();
		Float y1 = processor.getYahooValue();
		
		processor.runOnce(2);   
		assertEquals(0,processor.getCompareResult());
		Float g2 = processor.getGoogleValue();
		Float y2 = processor.getYahooValue();
		
		assertEquals(g1,g2,1.00f);   //It should not have changed by more than 1 pt.
		assertEquals(y1,y2,1.00f);   //It should not have changed by more than 1 pt.
	}

	
}
