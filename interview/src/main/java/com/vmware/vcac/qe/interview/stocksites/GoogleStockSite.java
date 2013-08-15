package com.vmware.vcac.qe.interview.stocksites;

import java.net.MalformedURLException;

public class GoogleStockSite extends StockSite{
	
	private final StockSiteType type = StockSiteType.Google;
	private static final String SITE_URL_FORMAT = "https://www.google.com/finance?q=%s";
	private static final String PRICE_CONTROL_ID = "ref_718288_l";
	public GoogleStockSite() throws MalformedURLException
	{
		super(String.format(SITE_URL_FORMAT, "vmw"),PRICE_CONTROL_ID);
	}

	public StockSiteType getSiteType() {
		return type;
	}
}
