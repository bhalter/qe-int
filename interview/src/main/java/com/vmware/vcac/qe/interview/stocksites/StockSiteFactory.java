package com.vmware.vcac.qe.interview.stocksites;

import java.net.MalformedURLException;

public class StockSiteFactory {
	public static StockSite getStockSite(StockSiteType s) throws MalformedURLException
	{
		switch (s)
		{
		case Yahoo: return new YahooStockSite();
		case Google: return new GoogleStockSite();
		default: return new GoogleStockSite();
		}
	}
}
