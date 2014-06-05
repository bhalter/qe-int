package com.vmware.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YahooFinancePage extends BaseWebPage {

	public YahooFinancePage() throws Exception {
		super();
	}
	
	private WebElement quoteLookupTextField;
	private WebElement quoteLookupButton;
	private WebElement quoteValueLabel;
	
	public void setQuoteLookupTextFieldValue(String value) {
		quoteLookupTextField =  driver.findElement(By.xpath("//input[@id='txtQuotes']"));
		quoteLookupTextField.sendKeys(value);
	}
	
	
	public void clickQuoteLookupButton() throws InterruptedException {
		quoteLookupButton = driver.findElement(By.xpath("//input[@id='btnQuotes']"));
		quoteLookupButton.click();
		Thread.sleep(EXPLICIT_WAIT);
	}
	
	public String getQuoteValueLabelText(String symbol) {
		quoteValueLabel = driver.findElement(By.xpath("//span[@id='yfs_l84_" + symbol.toLowerCase() + "']"));
		return quoteValueLabel.getText();
	}

}
