package com.vmware.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class VMwareWebDriver extends RemoteWebDriver {
	private static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();
	private static ThreadLocal<VMwareWebDriver> vDriverSession = new ThreadLocal<VMwareWebDriver>();
	private WebDriver driver;
	
	public VMwareWebDriver() {
		vDriverSession.set(this);
	}

	public WebDriver createWebDriver() throws Exception {
		driver = new FirefoxDriver();
		driverSession.set(driver);
		return driver;
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	public static WebDriver getWebDriver(Boolean isCreate) {
		if (driverSession.get() == null && isCreate) {
			try {
				getVMWareWebDriver().createWebDriver();
			} catch (Exception e) {
				System.out.println("Got exception when create web driver");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return driverSession.get();
	}
	
	public static WebDriver getWebDriver() {
		return getWebDriver(false);
	}
	
	public static VMwareWebDriver getVMWareWebDriver() {
		if (vDriverSession.get() == null) {
			vDriverSession.set(new VMwareWebDriver());
		}
		return vDriverSession.get();
	}
}