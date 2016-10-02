package com.vmware.vcac.qe;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * An implementation of the Quotable interface for the YahooFinance site.
 * 
 * @author willwallace
 *
 */
public class YahooFinance implements Quotable {

	float quote = 0.0F;
	final String name = "Yahoo";

	@Override
	public HttpUriRequest getRequest() {
		return new HttpGet("http://finance.yahoo.com:80/webservice/v1/symbols/VMW/quote?format=json");
	}

	@Override
	public void process(String payload) {
		// TODO Use a json parser to parse
		// final Gson gson = new GsonBuilder().create();
		// final YahooWrapper wrapper = gson.fromJson(payload,
		// YahooWrapper.class);
		// final List list = wrapper.getList();
		// quote = wrapper.getList().getResources()[0].getFields().getPrice();

		// Workaround
		String s = "\"price\" : \"";
		final int start = payload.indexOf(s) + s.length();
		final int end = payload.indexOf("\",", start);
		final String value = payload.substring(start, end);
		quote = Float.parseFloat(value);
	}

	@Override
	public float getQuote() {
		return quote;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getReport() {
		return String.format("%s=%s", getName(), quote);
	}

}
