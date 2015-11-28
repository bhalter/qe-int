package com.vmware.vcac.qe;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class provides details and implements the functionality of the google
 * finance api. Implements the IFinanceApi interface.
 * 
 * @author shruthi balki
 */

public class GoogleFinanceApi implements IFinanceApi {

	public static final String CLASS_NAME = "com.vmware.vcac.qe.GoogleFinanceApi";
	/**
	 * Google finance URL to extract stock data
	 */
	public static final String DATA_SOURCE_URL = "http://finance.google.com/finance/info?q=NASDAQ:VMW";
	/**
	 * Json Object index in the json array where current stock price resides
	 */
	public static final int PRICE_OBJECT_INDEX = 0;
	/**
	 * Key in the json data to obtain current stock price
	 */
	public static final String GOOGLE_PRICE_KEY = "l_cur";

	/**
	 * Returns the url for google data source which could give the information
	 * about the current stock price
	 * 
	 * @return DATA_SOURCE_URL: String represents stock price url for google
	 */
	public String getStockPriceURI() {
		return DATA_SOURCE_URL;
	}

	/**
	 * Parses the response data obtained from Http request to get current stock
	 * price for google
	 * 
	 * @param responseData
	 *            which is a string obtained from http response and contains
	 *            stock price
	 * @return String which represents the stock price for googlee.
	 */
	public String parseResponseForStockPrice(String responseData) {
		String stockPrice = null;
		JSONArray jarray = new JSONArray(responseData.replaceAll("//", ""));
		JSONObject jobject = jarray.getJSONObject(PRICE_OBJECT_INDEX);
		stockPrice = jobject.getString(GOOGLE_PRICE_KEY);
		return stockPrice.toString().trim();
	}

}
