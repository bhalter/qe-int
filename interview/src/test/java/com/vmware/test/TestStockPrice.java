package com.vmware.test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.vmware.pages.GoogleFinancePage;
import com.vmware.pages.YahooFinancePage;
import com.vmware.pages.YahooHomePage;


public class TestStockPrice {
	private static String stockSymbol = "VMW";
	private static YahooHomePage yPage;
	private static YahooFinancePage yFinPage;
	private static GoogleFinancePage gFinPage;
	private static Double yPrice, gPrice;
	private static long interval = 15; // in minutes
	private static long delay = 10; // in seconds

	public static void main(String[] args) throws Exception {
		
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
		public void run() {
		try {
			compareStockPrice();
		}catch (Exception e) {
			e.printStackTrace();
		}}};
		timer.schedule(task, TimeUnit.SECONDS.toMillis(delay), TimeUnit.MINUTES.toMillis(interval));
	}
	
	public static void compareStockPrice() {
		try {
			yPage = new YahooHomePage();
			yFinPage = new YahooFinancePage();
			yPage.clickFinanceLink();
			yFinPage.setQuoteLookupTextFieldValue(stockSymbol);
			yFinPage.clickQuoteLookupButton();
			yPrice = Double.valueOf(yFinPage.getQuoteValueLabelText(stockSymbol));
			
			gFinPage = new GoogleFinancePage();
			gFinPage.setQuoteSearchTextFieldValue(stockSymbol);
			gFinPage.clickSearchButton();
			gPrice = Double.valueOf(gFinPage.getQuoteValueLabelText(stockSymbol));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(stockSymbol + " stock price from yahoo finance:	" + yPrice);
		System.out.println(stockSymbol + " stock price from google finance:	" + gPrice);
		System.out.println(stockSymbol + " stock price difference between google and yahoo: " + (gPrice - yPrice));
		System.out.println("***************************************************************");
	}
}
	
