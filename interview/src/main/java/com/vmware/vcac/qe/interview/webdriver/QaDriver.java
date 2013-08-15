package com.vmware.vcac.qe.interview.webdriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QaDriver {
	private WebDriver driver;
	private final URL defaultUrl;
	public QaDriver(String url) throws MalformedURLException
	{
		driver = WebDriverFactory.getConfiguredFirefoxDriver();
		defaultUrl = new URL(url);
		driver.navigate().to(defaultUrl);
	}
	
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
	
	public String getControlValue(String id)
	{
		WebElement control = driver.findElement(By.id(id));
		return control.getText();
	}
	
	public void close()
	{
		driver.close();
	}
}
