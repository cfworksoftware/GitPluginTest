package webSites;

import java.util.HashMap;
import java.util.List;

import databaseConnection.DatabaseOperations;

public class KeysightQuoteDataStorage {
	
	KeysightWebQuoteDataCollection dailySharePriceData;
	
	DatabaseOperations databaseOperations;
	
	//String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
	
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	//String[] retrievedWebData = new String[4];
	
	public void sendStockDataToDatabase() {
		
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.KEYSIGHT.getDatabaseTableName();
		String expectedCurrencySymbol = InvestmentWebsitesEnum.KEYSIGHT.getCurrency();
		
	//	String databaseName = "accounting";
	//	String tableName = "webdata_keysight_quote"; 
	//	String expectedCurrencySymbol = "$";
		String primaryKeyName = "quote_date";
		
		dailySharePriceData = new KeysightWebQuoteDataCollection();
		retrievedWebData = dailySharePriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseFieldNames,*/ retrievedWebData);
	}
}
