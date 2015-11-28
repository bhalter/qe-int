package com.vmware.vcac.qe;

/**
 * This interface gives the abstract view of all functionalities to be
 * implemented by Finance Api's.
 * 
 * @author shruthi balki
 */

public interface IFinanceApi {

	/**
	 * Returns the url for a particular data source which could give the
	 * information about the current stock price
	 * 
	 * @return String represents stock price url for a datasource
	 */
	public String getStockPriceURI();

	/**
	 * Parses the response data obtained from Http request for current stock
	 * price.
	 * 
	 * @param responseData
	 *            which is a string obtained from http response and contains
	 *            stock price
	 * @return String which represents the stock price for that data source.
	 */
	public String parseResponseForStockPrice(String responseData);

}
