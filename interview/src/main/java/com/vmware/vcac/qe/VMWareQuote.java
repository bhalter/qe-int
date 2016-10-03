/**
 * 
 */
package com.vmware.vcac.qe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Gather stock quotes for VMW from two sources on a schedule (every 5 minutes).
 * Print and compare the results.
 * 
 * @author willwallace
 */
public class VMWareQuote {
	private static Log LOG = LogFactory.getLog(VMWareQuote.class);

	/**
	 * main: Entry point.
	 * 
	 * @param args:
	 *            not used
	 * @throws InterruptedException|IOException
	 */
	public static void main(String[] args) {
		final RestClient client = new RestClient();
		try {
			for (;;) {
				final java.util.List<Quotable> quotes = new ArrayList<Quotable>();
				quotes.add(new YahooFinance());
				quotes.add(new MarketOnDemand());

				for (final Quotable quote : quotes) {
					client.execute(quote);
				}

				logOutput(quotes);
				Thread.sleep(TimeUnit.MINUTES.toMillis(5L));
			}
		} catch (IOException | InterruptedException e) {
			LOG.info(String.format("Caught %s. %s", e.getClass().getName(), e.getMessage()));
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Log the output
	 * @param quotes List of quotes to log from
	 */
	public static void logOutput(final java.util.List<Quotable> quotes) {
		String logline = "";
		for (final Quotable quote : quotes) {
			logline += quote.getReport() + ", ";
		}
		logline += String.format("Difference %f", CalculateDifference(
				quotes.get(0).getQuote(), 
				quotes.get(1).getQuote()));
		LOG.info(logline);
	}
	
	/**
	 * Calculate the difference between two floats
	 * 
	 * @param f1
	 *            first float value
	 * @param f2
	 *            second float value
	 * @return difference
	 */
	public static float CalculateDifference(final float f1, final float f2) {
		return Math.abs(f1 - f2);
	}

}
