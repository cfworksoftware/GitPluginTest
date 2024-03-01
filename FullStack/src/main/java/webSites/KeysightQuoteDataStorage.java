package webSites;

import java.util.HashMap;
import java.util.List;

import databaseConnection.DatabaseOperations;

public class KeysightQuoteDataStorage {
	
	KeysightWebQuoteDataCollection dailySharePriceData;
	DatabaseOperations databaseOperations;
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	public void sendStockDataToDatabase() {
		
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.KEYSIGHT.getDatabaseTableName();
		String expectedCurrencySymbol = InvestmentWebsitesEnum.KEYSIGHT.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailySharePriceData = new KeysightWebQuoteDataCollection();
		retrievedWebData = dailySharePriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, retrievedWebData);
	}
}
