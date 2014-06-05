package com.vmware.pages;

import org.openqa.selenium.WebDriver;

import com.vmware.driver.VMwareWebDriver;

public class BaseWebPage {
	protected WebDriver driver;
	protected static final long EXPLICIT_WAIT = 5000;
	
	public BaseWebPage(String url) throws Exception {
		if (url != null) {
			driver = VMwareWebDriver.getWebDriver(true);
			driver.get(url);
		} else {
			throw new NullPointerException();
		}
	}
	
	public BaseWebPage() throws Exception {
		driver = VMwareWebDriver.getWebDriver();;
	}
	
	public WebDriver getDriver() {
		driver = VMwareWebDriver.getWebDriver();
		return driver;
	}

}
