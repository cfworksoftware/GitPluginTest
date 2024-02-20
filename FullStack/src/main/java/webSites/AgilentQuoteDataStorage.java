package webSites;

import databaseConnection.DatabaseOperations;
import java.util.HashMap; // import the HashMap class
import java.util.List;

public class AgilentQuoteDataStorage {

	AgilentWebQuoteDataCollection dailyStockPriceData;
	
	DatabaseOperations databaseOperations;
	
//	String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
//	String[] retrievedStockData = new String[4];
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
	
	public void sendStockDataToDatabase() {

		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.AGILENT.getDatabaseTableName();
		String expectedCurrencySymbol = InvestmentWebsitesEnum.AGILENT.getCurrency();
		
	//	String databaseName = "accounting";
	//	String tableName = "webdata_agilent_quote"; 
	//	String expectedCurrencySymbol = "$";
		String primaryKeyName = "quote_date";
		
		dailyStockPriceData = new AgilentWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
/*		databaseFields.put("unit_price","");
		databaseFields.put("quote_date","");
		databaseFields.put("stock_exchange","");
		databaseFields.put("currency_unit",""); */
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseFieldNames,*/ retrievedWebData);
	}
	
}
