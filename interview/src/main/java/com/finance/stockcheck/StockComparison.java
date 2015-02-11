package com.finance.stockcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class contains methods to fetch stock price
 * from Google and Yahoo finance
 * @author Ramesh
 *
 */
public class StockComparison {
		
	/**
	 * This method is used to fetch the stock price from Google finance API.
	 * @return float
	 */
	public float getGoogleFinancePrice(){
		float currentValue = 0.0f;
		try{			
			//URL formed dynamically so that data can be fetched for other companies also
			String googleFinanceUrl = Constants.GOOGLE_FINANCE_API+Constants.VMWARE;
			//The data contains unnecessary characters so replacing them with empty spaces.
			//Can use Apache StringUtils class for operations on the string.
			String responseData = fetchDataFromAPI(googleFinanceUrl).replace("//","");
			//Trimming the empty spaces
			responseData = responseData.trim();
			//The response is a JSONArray,so creating one from the string
			JSONArray apiResponse = new JSONArray(responseData);
			for(int count =0 ;count<apiResponse.length();count++){
				JSONObject vmStock = apiResponse.getJSONObject(count);
				currentValue = Float.valueOf(vmStock.getString("l_cur"));
			}
		}catch(IOException ie){
			System.out.println("Error while retrieving data from Google Finance API.");
		}catch(Exception ex){
			System.out.println("Something went wrong while fetching stock price from Google finance.");
		}
		return currentValue;
	}
	
	/**
	 * This method is used to fethc stock price from Yahoo finance API.
	 * @return float
	 */
	public float getYahooFinancePrice(){
		float currentValue = 0.0f;
		try{			
			//URL formed dynamically so that data can be fetched for other companies also
			String yahooFinanceUrl = Constants.YAHOO_FINANCE_API.replace("company", Constants.VMWARE);
			//Method to call Yahoo finance API
			String responseData = fetchDataFromAPI(yahooFinanceUrl);
			//Convert the API response to JSONObject
			JSONObject apiResponse = new JSONObject(responseData);
			currentValue = Float.valueOf(apiResponse.getJSONObject("query").getJSONObject("results").getJSONObject("quote").getString("AskRealtime"));			
		}
		catch(IOException ie){
			System.out.println("Error while retrieving data from Yahoo Finance API.");
		}catch(Exception ex){
			System.out.println("Something went wrong while fetching stock price from Google finance.");
		}
		return currentValue;
	}
	
	/**
	 * This method makes API call to Yahoo or Google finance
	 * based on the URL
	 * @param financeUrl
	 * @return String
	 * @throws IOException
	 */
	private String fetchDataFromAPI(String financeUrl) throws IOException {
		StringBuilder response = new StringBuilder();
		URL url = new URL(financeUrl);
		URLConnection connection = url.openConnection();
		//Create buffered reader
		BufferedReader br = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String data;
		//Read entire data
		while((data = br.readLine())!=null){
			response.append(data);
		}
		//CLose the buffered reader after data reading is completed.
		br.close();
		return response.toString();
	}
}
