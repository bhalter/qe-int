package com.vmware.vcac.qe.interview.webdriver;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	public static WebDriver getConfiguredFirefoxDriver()
	{
		WebDriver w = new FirefoxDriver();
		return w;
	}
	
	public static QaDriver getDefaultQaDriver(String url) throws MalformedURLException
	{
		return new QaDriver(url);
	}
}
