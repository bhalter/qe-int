package com.vmware.vcac.qe;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MarketOnDemand implements Quotable {

	float quote = 0.0F;
	final String name = "MarketOnDemand";

	@Override
	public HttpUriRequest getRequest() {
		return new HttpGet("http://dev.markitondemand.com/Api/v2/Quote/json?symbol=VMW");
	}

	@Override
	public void process (final String payload) {
		JsonObject jobj = new JsonParser().parse(payload).getAsJsonObject();
		quote = jobj.get("LastPrice").getAsFloat();
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
		return String.format("%s=%s",getName(), quote);
	}
}
