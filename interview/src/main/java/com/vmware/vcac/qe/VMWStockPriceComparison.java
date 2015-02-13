package com.vmware.vcac.qe;
/**
 * Compares the VMWare stock prices from Yahoo Finance and Google Finance UI
 * @author Prativa
 *
 */
public class VMWStockPriceComparison {

	public static void main(String args[]){

		Thread t = Thread.currentThread();
		GoogleFinance googleFinance = new GoogleFinance();
		YahooFinance yahooFinance = new YahooFinance();
		for(;;){
			String googleValues = VMWStockPriceCompUtil.getVMWareStockDetails(googleFinance.getRESTURI());
			String yahooValues = VMWStockPriceCompUtil.getVMWareStockDetails(yahooFinance.getRESTURI());
			VMWStockPriceCompUtil.compareStockPrices(googleFinance.parseData(googleValues), yahooFinance.parseData(yahooValues));
			try {
				//sleep for  minutes
				t.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
