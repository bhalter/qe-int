package com.vmware.vcac.qe.interview.stocksites;

import java.net.MalformedURLException;

public class YahooStockSite extends StockSite{
	private static final String YAHOO_PRICE_CONTROL_ID = "yfs_l84_vmw";
	private static final String YAHOO_STOCK_URL_FORMAT = "http://finance.yahoo.com/q?s=%s&ql=1";
	private StockSiteType type = StockSiteType.Yahoo;
	public YahooStockSite() throws MalformedURLException
	{
		super(String.format(YAHOO_STOCK_URL_FORMAT,"vmw"), YAHOO_PRICE_CONTROL_ID);
	}
	
	public StockSiteType getSiteType()
	{
		return type;
	}
	
}
