package com.vmware.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GoogleFinancePage extends BaseWebPage {
	
	private static final String GOOG_FINANCE_URL = "https://www.google.com/finance";
	
	public GoogleFinancePage(String url) throws Exception {
		super(url);
		Thread.sleep(5000);
	}

	public GoogleFinancePage() throws Exception {
		super(GOOG_FINANCE_URL);
		Thread.sleep(5000);
	}
	
	private WebElement searchTextField;
	private WebElement searchButton;
	private WebElement quoteValueLabel;
	
	public void setQuoteSearchTextFieldValue(String value) {
		searchTextField = driver.findElement(By.xpath("//input[@id='gbqfq']"));
		searchTextField.sendKeys(value);
	}
	
	
	public void clickSearchButton() throws InterruptedException {
		searchButton = driver.findElement(By.xpath("//button[@id='gbqfb']"));
		searchButton.click();
		Thread.sleep(EXPLICIT_WAIT);
	}
	
	public String getQuoteValueLabelText(String symbol) {
		quoteValueLabel = driver.findElement(By.xpath("//span[@class = 'pr']"));
		return quoteValueLabel.getText();
	}
}
