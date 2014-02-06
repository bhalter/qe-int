package interview;

import org.apache.commons.logging.impl.SimpleLog;

import java.text.NumberFormat;
import java.util.Date;

import interview.WebAccess;

public class Processor {

	private WebAccess webAccess;
	private SimpleLog log;
	private NumberFormat format;
	private int count;
	private Float googleValue;
	private Float yahooValue;
	private int compareResult;
	
	public Processor(WebAccess webAccess, SimpleLog log) {
		this.webAccess = webAccess;
        this.log = log;
        format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        this.count = 0;
        this.googleValue = 0.0f;
        this.yahooValue = 0.0f;
        this.compareResult = 0;
	}
	
	// For unit test
	public Float getGoogleValue() { return this.googleValue; }
	public Float getYahooValue() { return this.yahooValue; }
	public int getCompareResult() { return this.compareResult; }
	
	// For common usage.
	
	public boolean runOnce(int count) throws InterruptedException {		
		System.out.println( (new Date()).toString() + "  count: " + count);
		
		googleValue = webAccess.getGoogleValue();
		log.debug("output googleValue");
		System.out.println("GoogleValue: $" + format.format(googleValue) );

		yahooValue = webAccess.getYahooValue();
		log.debug("output yahooValue");
		System.out.println("YahooValue: $" + format.format(yahooValue) );

		compareResult = googleValue.compareTo(yahooValue);
		if (compareResult > 0) {
			System.out.println("Google value is higher");
		}
		else if (compareResult < 0) {
			System.out.println("Yahoo value is higher");
		}
		else {
			System.out.println("Values are the same");
		}			
		return googleValue > 0.0f && yahooValue > 0.0f;
	}
	
	
	public void run() throws InterruptedException {
		int sleepMills = 5 * 60 *1000;  // Five minutes
		boolean more = true;
		while (more) {
			count += 1;
			more = runOnce(count);
			if (more) {
				System.out.println("");
				Thread.sleep(sleepMills);  
			}
		}
	}
	
}
