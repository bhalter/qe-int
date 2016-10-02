package com.vmware.vcac.qe;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Quotable interface used to create REST requests and store quotes
 * 
 * @author willwallace
 *
 */
public interface Quotable {
	/**
	 * Get the request to use for query
	 * 
	 * @return HttpUriRequest to use for the query
	 */
	public HttpUriRequest getRequest();

	/**
	 * Extract the quote from the response
	 * 
	 * @param payload
	 */
	public void process(final String payload);

	/**
	 * Get the quote or actual price of the stock.
	 * 
	 * @return the quote
	 */
	public float getQuote();

	/**
	 * The name of the site
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get a report with the name and the quote.
	 * 
	 * @return
	 */
	public String getReport();
}
