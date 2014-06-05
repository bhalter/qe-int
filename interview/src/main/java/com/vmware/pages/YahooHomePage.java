package com.vmware.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YahooHomePage extends BaseWebPage {
	private static final String YAHOO_URL = "https://www.yahoo.com";
	
	public YahooHomePage(String url) throws Exception {
		super(url);
		Thread.sleep(5000);
	}
	
	public YahooHomePage() throws Exception {
		super(YAHOO_URL);
		Thread.sleep(5000);
	}
	
	private WebElement financeLink;
	
	public void clickFinanceLink() {
		financeLink = driver.findElement(By.xpath("//a[contains(., 'Finance')]"));
		financeLink.click();
	}
}
