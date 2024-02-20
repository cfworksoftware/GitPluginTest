package webSites;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import webDriverSelenium.ConfigureSeleniumBrowserDriver;
//import databaseConnection.DatabaseOperations;

public class KeysightWebQuoteDataCollection {
	
	ShareClass Keysight = new ShareClass(InvestmentWebsitesEnum.KEYSIGHT);
	
//	static String keysightURL = InvestmentWebsitesEnum.KEYSIGHT.getShareURL();
//	static String keysightName  = InvestmentWebsitesEnum.KEYSIGHT.getShareName();

//	static String keysightURL = "https://www.marketwatch.com/investing/stock/keys";
//	static String keysightName = "Keysight";
	
//	String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
//	String databaseName = "accounting";
//	String tableName = "webdata_keysight_quote"; 
//	String expectedCurrencySymbol = "$";
//	String primaryKeyName = "quote_date";
//	static String sharePriceQuoteCSSSelectorPath = InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE.getCSSSelector(); 
//	static String sharePriceQuoteCSSSelectorPath = "h2.intraday__price > bg-quote";
	
//	String[] retrievedWebData = new String[4];
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver();
	
	public HashMap<String, List<String>> /*String[]*/  getWebData(String browserName) {
	
		if (browserName.equals("Chrome")) {
			newBrowserDriver.getChromeDriver();
			newBrowserDriver.maximiseWindow();
			driver = newBrowserDriver.getChromeDriver();
		}
		if (driver !=null) {
			newBrowserDriver.setDriverURL(/*keysightURL*/Keysight.getShareURL());			
		//	retrievedStockData = findStockNameElementTextByCSSSelector(driver, stockPriceQuoteCSSSelectorPath, stockName);
			WebDataMarketWatchShareQuotation keysightWebData = new WebDataMarketWatchShareQuotation();
//			DatabaseOperations writeKeysightData = new DatabaseOperations();
			retrievedWebData = keysightWebData.getFinancialWebsiteShareQuote(driver,/*sharePriceQuoteCSSSelectorPath*/
					Keysight.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE), 
					/*keysightName*/ Keysight.getShareName());
//			writeKeysightData.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, databaseFieldNames, retrievedStockData);
			newBrowserDriver.quitDriverInstance();
			driver = null;
		}
		return retrievedWebData;
	}

}
