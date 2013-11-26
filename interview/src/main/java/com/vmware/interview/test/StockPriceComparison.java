package com.vmware.interview.test;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StockPriceComparison extends TimerTask {

	private WebDriver webDriver;
	private String stockCode;
	
	//synchronize this because multiple timer task threads may access this at same time
	public synchronized WebDriver getFireFoxDriver() {
		if (this.webDriver == null) {
			webDriver = new FirefoxDriver();
		}
		return webDriver;
	}
	
	public StockPriceComparison(String stockCode) {
		this.stockCode = stockCode;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Getting the Stock Price for " + ConfigManager.Constants.VMW_CODE);
		StockPriceComparison task = new StockPriceComparison(ConfigManager.Constants.VMW_CODE);
		
		Timer timer = new Timer();
		System.out.println("Timer Task Schedule: Initial delay = " + ConfigManager.getInstance().getDelayInMillis() + ", Interval = " + ConfigManager.getInstance().getIntervalInMillis());
		timer.schedule(task, ConfigManager.getInstance().getDelayInMillis(), ConfigManager.getInstance().getIntervalInMillis());
		
	}

	@Override
	public void run() {
		
		//Yahoo
		WebStockPriceHandler yahooHandler = new YahooStockPriceHandler(getFireFoxDriver());
		BigDecimal yahooQuote = yahooHandler.getStockPriceFor(stockCode);
		
		//Google
		WebStockPriceHandler googleHandler = new GoogleStockPriceHandler(getFireFoxDriver());
		BigDecimal googleQuote = googleHandler.getStockPriceFor(stockCode);
		
		if (yahooQuote != null && googleQuote != null) {
			if (yahooQuote.equals(googleQuote)) {
				System.out.println("EQUAL:\t===>YAHOO: " + yahooQuote + " ===>GOOGLE: " + googleQuote);
			} else {
				System.out.println("UNEQUAL:\t===>YAHOO: " + yahooQuote + " ===>GOOGLE: " + googleQuote);
			}
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		WebDriver webDriver = getFireFoxDriver();
		if (webDriver != null) {
			webDriver.close();
			webDriver.quit();
		}
		super.finalize();
	}
}
