package com.vmware.vcac.qe.interview;
/// 
///
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class VMWStockPrice {

	// REST API for google and yahoo VMWare Stock price
	private static final String googleUri = "https://finance.google.com/finance/info?client=ig&q=NASDAQ:VMW";
	private static final String yahooUri = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes"
    				+ "%20where%20symbol%20%3D%20%22VMW%22&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
	
	// Logger to log the information
	private static Log logger = LogFactory.getLog(VMWStockPrice.class);
	
	//Function to get the data from the URI 
	//@params: URI 
	//@return: String response from HttpGet 
	static String getStreamData(String URI)
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try
		{
			HttpGet getData = new HttpGet(URI);
			CloseableHttpResponse res = httpclient.execute(getData);
			try
			{
				//Check for status code
				//Status code 200 represents success
				if(res.getStatusLine().getStatusCode() == 200)
				{
					//res.getEntity provides the response in XML and Json format
					return EntityUtils.toString(res.getEntity());
				}
			}
			finally
			{
				res.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				httpclient.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	//Function to compare the stock values received from google finance and yahoo finance 
	//@params: googleStockPrice, yahooStockPrice
	//@return: void
	private static void comparePricing(double googleStockPrice, double yahooStockPrice)
	{
		logger.info("Comparing Stock pricing");
		if(googleStockPrice == yahooStockPrice)
			logger.info("Stock price are same in Google finance and Yahoo Finance");
		if(googleStockPrice > yahooStockPrice)
			logger.info("Stock price are high in Google finance by :" + (googleStockPrice-yahooStockPrice));
		else if(googleStockPrice < yahooStockPrice)
			logger.info("Stock price are high in Yahoo finance by : "+ (yahooStockPrice-googleStockPrice));
	}
	
	// Get data from server after every 5 minutes
	// Parse data from respective parsers
	// Compares the pricing from both the sources
    public static void main(String[] args)
    {
    	logger.info("Program Statred");
    	
    	//Variables to parse
    	GoogleFinanceParser googleFinParser = new GoogleFinanceParser();
    	YahooFinanceParser yahooFinParser = new YahooFinanceParser();
    	
    	//Variables to store the Stock pricing from google and yahoo
    	double googleStockPrice = 0.0;
    	double yahooStockPrice = 0.0;
    	
    	//
    	while(true)
    	{
    		//Google finance
    		logger.info("Getting response from Google Finance");
    		String googleResString = VMWStockPrice.getStreamData(googleUri);
    		logger.info("Parsing Json object for VMWare Stock Price");
    		googleStockPrice = googleFinParser.parseAndGetValue(googleResString);
    		logger.info("VMWare Stock Price from Google Finance : " + googleStockPrice);
    		
    		//Yahoo finance
    		logger.info("Getting response from Yahoo Finance");
    		String yahooResString = VMWStockPrice.getStreamData(yahooUri);
    		logger.info("Parsing XML response for VMWare Stock Price");
    		yahooStockPrice = yahooFinParser.parseAndGetValue(yahooResString);
    		logger.info("VMWare Stock Price from Yahoo Finance : " + yahooStockPrice);
    		
    		// Compare VMWare stock price
    		comparePricing(googleStockPrice,yahooStockPrice);
    		
    		logger.info("Waiting for 5 min...");
    		try 
    		{
				Thread.sleep(300000);	//sleep for 5 minutes
			} 
    		catch (InterruptedException e) 
    		{
				e.printStackTrace();
			}
    	}
    	
    }
}