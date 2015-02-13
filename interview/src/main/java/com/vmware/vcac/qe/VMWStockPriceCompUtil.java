package com.vmware.vcac.qe;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Utility class
 * @author Prativa
 *
 */
public class VMWStockPriceCompUtil {

	static Log log = LogFactory.getLog(VMWStockPriceCompUtil.class);
	/**
	 * gets the URI and returns the response
	 * @param URI
	 * @return HTTP GET response
	 */
	public static String getVMWareStockDetails(String URI){
		HttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(URI);
		String getResponse = null;
		try {
			HttpResponse response = client.execute(getRequest);
			HttpEntity entity = response.getEntity();
			byte[] buffer = new byte[1024];
			if (null != entity) {
				InputStream iStream = entity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream biStream = new BufferedInputStream(iStream);
					while ((bytesRead = biStream.read(buffer)) != -1) {
						getResponse = new String(buffer, 0, bytesRead);
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} 
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return getResponse;
	}
	
	/**
	 * compare VMWare stock prices on Yahoo Finance and Google Finance
	 * @param google
	 * @param yahoo
	 */
	public static void compareStockPrices(double google, double yahoo){
		log.info("VMWare stock price on Google Finance is "+google+" and on Yahoo Finance is "+yahoo);
		if(google > yahoo)
			log.info("VMWare stock price on Google Finance is more than Yahoo Finance by "+(google-yahoo));
		else if(yahoo > google)
			log.info("VMWare stock price on Yahoo Finance is more than Google Finance by "+(yahoo-google));
		else
			log.info("VMWare stock price on Yahoo Finance and Google Finance is same");
	}
}
