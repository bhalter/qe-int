package com.finance.stockcheck;

import java.util.TimerTask;

/**
 * This is the scheduled task which would check
 * the stock price in Yahoo and Google finance.
 * @author Ramesh
 *
 */
public class ScheduledTask extends TimerTask{

	//Extending the TimerTask class and overriding the run method
	@Override
	public void run() {
		StockComparison pc = new StockComparison();
		if(pc.getGoogleFinancePrice() >  pc.getYahooFinancePrice())
			System.out.println("Price of VMWare in Google finance is: "+pc.getGoogleFinancePrice()
					+ " and greater than the price in Yahoo finance: "+pc.getYahooFinancePrice()+" by: "+(pc.getGoogleFinancePrice()-pc.getYahooFinancePrice()));
		else if (pc.getGoogleFinancePrice() <  pc.getYahooFinancePrice())
			System.out.println("Price of VMWare in Yahoo finance is: "+pc.getYahooFinancePrice()
					+ " and greater than the price in Google finance: "+pc.getGoogleFinancePrice()+" by: "+(pc.getYahooFinancePrice()-pc.getGoogleFinancePrice()));
		else
			System.out.println("Price of VMWare in Yahoo finance: "+pc.getYahooFinancePrice()
					+ "is equal to one in Google finance: "+pc.getGoogleFinancePrice());
	}
 
}
