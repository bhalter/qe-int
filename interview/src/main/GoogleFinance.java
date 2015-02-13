
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class handles processing of data fetched from Yahoo Finance
 * @author Prativa
 *
 */
public class GoogleFinance implements IFinance{

	// Google REST API to get VMWare stock price
	public static final String GOOGLE_FINANCE_URI = "http://finance.google.com/finance/info?client=ig&q=NASDAQ:VMW"; 
	// key to fetch the value of the current stock price from the JSON data
	public static final String GOOGLE_CURR_PRICE = "l_cur";

	/**
	 * parsing the data(JSON format) fetched from Google Finance
	 */
	public double parseData(String str) {
		String currStockValue = null;
		// converting the string into a string of JSON format
		String temp = str.replaceAll("\\[|\\]|\\/", "");
		try {
			JSONObject jObj = new JSONObject(temp);
			currStockValue = jObj.getString(GOOGLE_CURR_PRICE);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Double.parseDouble(currStockValue.toString().trim());
	}

	/**
	 * returns Google Finance REST API for getting VMWare stock prices
	 */
	public String getRESTURI() {
		return GOOGLE_FINANCE_URI;
	}

}
