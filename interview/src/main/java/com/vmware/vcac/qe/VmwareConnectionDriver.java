package com.vmware.vcac.qe;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * The class acts as a Connection api between the Vmware application and the
 * finance apis. It handles the communication between them.
 * 
 * @author shruthi balki
 */

public class VmwareConnectionDriver {

	static Log console_log = LogFactory.getLog(VmwareConnectionDriver.class);

	/**
	 * Function which connects Driver and VMStockComparator
	 * 
	 * @param apiClass
	 *            String which represents class name of the api
	 * @return String which is the stock price
	 */
	public static String getStockData(String apiClass) {
		return loadFinanceApi(apiClass);
	}

	/**
	 * This function loads the finance api class and handles the parsing of the
	 * http response to obtain the stock price.
	 * 
	 * @param apiClass
	 *            String which represents class name of the api
	 * @return String which is the stock price
	 */
	public static String loadFinanceApi(String apiClass) {
		String stockPrice = null;
		try {
			IFinanceApi apiInstance = Class.forName(apiClass)
					.asSubclass(IFinanceApi.class).newInstance();
			String response = getResponseData(apiInstance.getStockPriceURI());
			stockPrice = apiInstance.parseResponseForStockPrice(response);
		} catch (Exception e) {
			console_log.error(e.getMessage());
		}
		return stockPrice;
	}

	/**
	 * This function handles the Http requests and response. Connects to the
	 * specified url and gets the response data
	 * 
	 * @param url
	 *            String which represents the stock price url.
	 * @return String which is the http response data
	 */
	public static String getResponseData(String url) {
		String response = null;
		try {
			HttpResponse HttpResponse = new DefaultHttpClient()
					.execute(new HttpGet(url));
			HttpEntity httpEntity = HttpResponse.getEntity();
			byte[] buffer = new byte[1024];
			if (httpEntity != null) {
				InputStream iStream = httpEntity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream biStream = new BufferedInputStream(
							iStream);
					while ((bytesRead = biStream.read(buffer)) != -1) {
						response = new String(buffer, 0, bytesRead);
					}
				} catch (ClientProtocolException e) {
					console_log.info(e.getMessage());
				}
			}
		} catch (IOException e) {
			console_log.info(e.getMessage());
		}
		return response;
	}

}
