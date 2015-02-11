package com.finance.stockcheck;

public interface Constants {

	public String VMWARE = "VMW";
	public String GOOGLE_FINANCE_API = "http://finance.google.com/finance/info?client=ig&q=";
	public String YAHOO_FINANCE_API = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20"
			+ "(%22company%22)%0A%09%09&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env";
	public long TASK_DELAY = 30000;
}
