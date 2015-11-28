package com.vmware.vcac.qe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the main class which handles the connections to api, comparisons of
 * stockprices and logging them
 * 
 * @author shruthi balki
 */

public class VmwareStockComparision {

	public static final String CLASS_NAME = "VmwareStockComparision";
	static Log console_log = LogFactory.getLog(VmwareStockComparision.class);

	/**
	 * This map provides a mapping of the dataSource and its apis present in the
	 * package
	 */
	public static HashMap<String, String> financeApiList;
	/**
	 * stockPrice gives a mapping of the dataSource and the current stock price
	 * in it
	 */
	private static HashMap<String, Double> stockPrices;
	/**
	 * Gives the name of the data source with the highest stock price if present
	 */
	private static String highestStockPriceSource = null;
	private static double highestStockPrice = Double.MIN_VALUE;

	
	/**
	 * Constructor
	 */
	public VmwareStockComparision() {
		stockPrices = new HashMap<String, Double>();
	}

	
	/**
	 * Update the finnace list by loading all the finance apis defined in the
	 * package
	 */
	public static void updateListOfFinanceApi() {
		financeApiList = new HashMap<String, String>();
		// Initially load it manually
		financeApiList.put("google", "com.vmware.vcac.qe.GoogleFinanceApi");
		financeApiList.put("yahoo", "com.vmware.vcac.qe.YahooFinanceApi");

	}

	/**
	 * This function gets fills up the stockPrices map by getting stock price
	 * from all the data Sources specified. Takes in the input list of
	 * DataSources(Google, yahoo.. etc)
	 * 
	 * @param DataSources
	 *            represents list of data sources whose stock prices need to be
	 *            compared
	 */
	public static void getStockPrices(List<String> DataSources) {
		String financeApi;
		if (!DataSources.isEmpty()) {
			for (String dataSource : DataSources) {
				financeApi = financeApiList.get(dataSource);
				if (financeApi != null) {
					getStockPrice(dataSource, financeApi);
				} else {
					console_log.error("financeApi does not exist for "
							+ dataSource);
				}
			}
		} else {
			console_log
					.error("Please specify atleast one site to get the stock price");
		}
	}

	/**
	 * This function connects the to the connection driver and gets the stock
	 * price of the particular data source and updates the highest stock price
	 * if present
	 * 
	 * @param dataSource
	 *            String which represents the site to get stock price
	 * @param apiclass
	 *            class name of the finance api of the datasource
	 */
	public static void getStockPrice(String dataSource, String apiClass) {
		double price = Double.parseDouble(VmwareConnectionDriver
				.getStockData(apiClass));
		stockPrices.put(dataSource, price);
		if (highestStockPrice < price) {
			if (highestStockPrice != Double.MIN_VALUE) {
				highestStockPriceSource = dataSource;
			}
			highestStockPrice = price;
		}
	}

	/**
	 * This method handles the comparison of various stock prices and logs on to
	 * the console the output.
	 */
	public static void compareStockPrices() {
		StringBuilder output = new StringBuilder();
		output.append("\n********VMWARE STOCK ANALYSIS********\n");
		if (highestStockPriceSource == null) {
			output.append("StockPrices for ");
			for (Entry<String, Double> entry : stockPrices.entrySet()) {
				output.append(entry.getKey() + ", ");
			}
			output.append("are in sync with price of" + highestStockPrice);
		} else {
			output.append("The Datasource " + highestStockPriceSource
					+ " is showing the highest with a value of "
					+ highestStockPrice + "\n");
			output.append("Details of the other dataSources are \n");
			for (Entry<String, Double> entry : stockPrices.entrySet()) {
				output.append(entry.getKey() + " : " + entry.getValue() + "\n");
			}
		}
		console_log.info(output.toString());
	}

	/**
	 * Gets the names of datasources to be compared
	 * 
	 * @return List of all the datasource names whose prices need to be compared
	 */
	public static List<String> getDataSources() {
		List<String> DataSources = Arrays.asList("google", "yahoo");
		return DataSources;
	}

	/**
	 * Main function to run the application
	 */
	public void startApplication() {
		Thread current = Thread.currentThread();
		while (true) {
			try {
				getStockPrices(getDataSources());
				compareStockPrices();
				current.sleep(300000);
			} catch (InterruptedException e) {
				console_log.error(e.getMessage());
			}
		}
	}

	public static void main(String args[]) {
		updateListOfFinanceApi();
		VmwareStockComparision appInstance = new VmwareStockComparision();
		appInstance.startApplication();

	}

}
