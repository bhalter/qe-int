package com.vmware.interview.test;

import java.math.BigDecimal;

public interface WebStockPriceHandler {

	public BigDecimal getStockPriceFor(String stockCode);
	public String getWebURL();
}
