package com.vmware.interview.test;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YahooStockPriceHandler extends AbstractWebStockPriceHandler {

	private WebDriver webDriver;
	private static final String BASE_URL = "http://finance.yahoo.com";
	private static final String PRICE_XPATH = ".//*[@id='yfi_rt_quote_summary']/div[2]/p/span[@class='time_rtq_ticker']/span"; //this can also go in config
	
	public YahooStockPriceHandler(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	protected WebDriver getWebDriver() {
		return this.webDriver;
	}
	
	public BigDecimal getStockPriceFor(String stockCode) {
		webDriver.get(getQueryURL(stockCode));
		WebElement element = webDriver.findElement(By.xpath(PRICE_XPATH));
		String quoteString = element.getText();
		return getParsedQuoteFromString(quoteString);
	}
	
	private String getQueryURL(String stockCode) {
		StringBuilder url = new StringBuilder(getWebURL()).append("/q").append("?").append("s=").append(stockCode);
		return url.toString();
	}

	public String getWebURL() {
		return BASE_URL;
	}
}