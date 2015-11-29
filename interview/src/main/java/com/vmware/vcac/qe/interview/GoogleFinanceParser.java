package com.vmware.vcac.qe.interview;
// This class parse the response and returns the stock price from google finance.
// The response is JSONObject 

import org.json.*;

public class GoogleFinanceParser implements IVmwFinanceParser{

	//key in Json object corresponding to the current stock Price
	static String stockPriceKey = "l_cur";
	
	
	// function to parse the Json response
	// @params: jsonResGoogle
	// @return: Google Stock price
	public double parseAndGetValue(String jsonResGoogle) 
	{
		String jsonObjSting = jsonResGoogle.substring(3).replace("[", "").replace("]", "");
		
		JSONObject jobj;
		try 
		{
			jobj = new JSONObject(jsonObjSting);
			return Double.parseDouble(jobj.get(stockPriceKey).toString().trim());
			
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}


}
