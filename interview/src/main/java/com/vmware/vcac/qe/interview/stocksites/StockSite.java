package com.vmware.vcac.qe.interview.stocksites;

import java.net.MalformedURLException;

import com.vmware.vcac.qe.interview.webdriver.QaDriver;
import com.vmware.vcac.qe.interview.webdriver.WebDriverFactory;

public abstract class StockSite {

	private QaDriver driver;
	private String STOCK_PRICE_CONTROL_ID;
	
	public StockSite(String url, String priceId) throws MalformedURLException
	{
		driver = WebDriverFactory.getDefaultQaDriver(url);
		STOCK_PRICE_CONTROL_ID = priceId;
	}
	public  Double getCurrentStockPrice()
	{
		driver.refreshPage();
		String priceString = driver.getControlValue(STOCK_PRICE_CONTROL_ID);
		return Double.valueOf(priceString); 
	}
	
	public void finalize()
	{
		driver.close();
	}
	public abstract StockSiteType getSiteType();
	
	
}
