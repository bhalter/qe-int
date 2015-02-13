
/**
 * This class handles processing of data fetched from Yahoo Finance
 * @author Prativa
 *
 */
public class YahooFinance implements IFinance{

	public static final String YAHOO_FINANCE_URI = "http://download.finance.yahoo.com/d/quotes.csv?s=%40%5EDJI,VMW&f=nl1&e=.csv";
	public static final int VMW_CURR_STOCK_COLUMN = 2;

	/**
	 * parsing the data(CSV format) fetched from Yahoo Finance 
	 */
	public double parseData(String str) {
		 String[] s = str.split(",");
         return (Double.parseDouble(s[VMW_CURR_STOCK_COLUMN].toString().trim()));
	}
	
	/**
	 * returns Yahoo Finance REST API for getting VMWare stock prices
	 */
	public String getRESTURI() {
		return YAHOO_FINANCE_URI;
	}

}
