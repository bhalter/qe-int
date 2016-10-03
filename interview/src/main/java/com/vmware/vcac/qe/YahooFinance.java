package com.vmware.vcac.qe;

import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

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
		JsonObject jobj = new JsonParser().parse(payload).getAsJsonObject();
		JsonObject list = jobj.get("list").getAsJsonObject();
		JsonArray resources = list.get("resources").getAsJsonArray();
		JsonObject resource0 = resources.get(0).getAsJsonObject();
		JsonObject resource = resource0.get("resource").getAsJsonObject();
		JsonObject fields = resource.get("fields").getAsJsonObject();
		
		final Set<Entry<String,JsonElement>> entrySet = fields.entrySet();
		for (final Entry<String,JsonElement> entry : entrySet) {
			if ("price".equals(entry.getKey())) {
				quote = Float.parseFloat(entry.getValue().getAsString());
				break;
			}
		}
		
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
