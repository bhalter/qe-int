package com.vmware.vcac.qe.interview;

import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vmware.vcac.qe.interview.stocksites.StockSite;
import com.vmware.vcac.qe.interview.stocksites.StockSiteFactory;
import com.vmware.vcac.qe.interview.stocksites.StockSiteType;

public class StockComparitor {
	
	public static void main(String[] args) throws MalformedURLException {
		Log logger = LogFactory.getLog("stocks");
		StockSite yahoo = StockSiteFactory.getStockSite(StockSiteType.Yahoo);
		logger.debug("Created Yahoo stock site object");
		StockSite google = StockSiteFactory.getStockSite(StockSiteType.Google);
		logger.debug("Created Google stock site object");
		Double priceDelta = getPriceDelta(yahoo,google);
		printOutcome(priceDelta, google.getSiteType(), yahoo.getSiteType());
	}
	
	public static Double getPriceDelta(StockSite yahoo, StockSite google)
	{
		Log logger = LogFactory.getLog("stocks");
		Double yahooPrice = yahoo.getCurrentStockPrice();
		logger.debug("Yahoo price was "+yahooPrice);
		Double googlePrice =google.getCurrentStockPrice();
		logger.debug("Google price was "+googlePrice);
		Double priceDelta = (googlePrice - yahooPrice); 
		return priceDelta;
	}
	
	public static void printOutcome(Double priceDelta, StockSiteType firstSite, StockSiteType secondSite)
	{
		Log logger = LogFactory.getLog("stocks");
		if ( priceDelta == 0)
		{
			logger.info("Prices were the same");
		}
		else if (priceDelta > 0)
		{
			logger.info(firstSite.toString()+" had higher price");
		}
		else
		{
			logger.info(secondSite.toString()+" had higher price");
		}		
	}
}
