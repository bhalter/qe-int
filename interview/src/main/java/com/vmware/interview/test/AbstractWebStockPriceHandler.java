package com.vmware.interview.test;

import java.math.BigDecimal;

public abstract class AbstractWebStockPriceHandler implements WebStockPriceHandler {

	protected BigDecimal getParsedQuoteFromString(String quoteStr) {
		
		BigDecimal quote = null;
		try {
			quote = new BigDecimal(quoteStr);
		} catch (Exception e) {
			System.out.println("Couldn't parse quote value from " + quoteStr);
		}
		return quote;
	}
}
