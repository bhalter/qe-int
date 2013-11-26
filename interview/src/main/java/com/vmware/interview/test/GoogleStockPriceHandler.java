package com.vmware.interview.test;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleStockPriceHandler extends AbstractWebStockPriceHandler {

	private WebDriver webDriver;
	private static final String BASE_URL = "https://www.google.com/finance";
	private static final String PRICE_XPATH = ".//*[@id='price-panel']/div[1]/span[@class='pr']/span"; //this can also go in config
	
	public GoogleStockPriceHandler(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public BigDecimal getStockPriceFor(String stockCode) {
		webDriver.get(getQueryURL(stockCode));
		WebElement element = webDriver.findElement(By.xpath(PRICE_XPATH));
		String quoteString = element.getText();
		return getParsedQuoteFromString(quoteString);
	}

	private String getQueryURL(String stockCode) {
		StringBuilder url = new StringBuilder(getWebURL()).append("?").append("q=").append(stockCode);
		return url.toString();
	}
	
	public String getWebURL() {
		return BASE_URL;
	}
}
