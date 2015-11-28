package com.vmware.vcac.qe;

/**
 * This class provides details and implements the functionality of the yahoo
 * finance api. Implements the IFinanceApi interface.
 * 
 * @author shruthi balki
 */

public class YahooFinanceApi implements IFinanceApi {

	public static final String CLASS_NAME = "YahooFinanceApi";
	/**
	 * Yahoo finance URL to extract stock data
	 */
	public static final String DATA_SOURCE_URL = "http://download.finance.yahoo.com/d/quotes.csv?s=VMW&f=l1.csv";
	public static final String CSV_SPILTTER = ",";
	/**
	 * Column number of csv where the stock price resides according to the uri
	 */
	public static final int YAHOO_PRICE_COLUMN = 0;

	/**
	 * Returns the url for yahoo data source which could give the information
	 * about the current stock price
	 * 
	 * @return DATA_SOURCE_URL: String represents stock price url for yahoo
	 */
	public String getStockPriceURI() {
		return DATA_SOURCE_URL;
	}

	/**
	 * Parses the response data obtained from Http request to get current stock
	 * price for yahoo
	 * 
	 * @param responseData
	 *            which is a string obtained from http response and contains
	 *            stock price
	 * @return String which represents the stock price for yahoo.
	 */
	public String parseResponseForStockPrice(String responseData) {
		String[] columns = responseData.split(CSV_SPILTTER);
		return (columns[YAHOO_PRICE_COLUMN].toString().trim());
	}

}
