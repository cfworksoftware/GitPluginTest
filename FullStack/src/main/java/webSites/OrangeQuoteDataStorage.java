package webSites;

import java.util.HashMap;
import java.util.List;

import databaseConnection.DatabaseOperations;

public class OrangeQuoteDataStorage {

	OrangeWebQuoteDataCollection dailyStockPriceData;	
	DatabaseOperations databaseOperations;
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	public void sendStockDataToDatabase() {
		
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.ORANGESA.getDatabaseTableName();
		String expectedCurrencySymbol = InvestmentWebsitesEnum.ORANGESA.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailyStockPriceData = new OrangeWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, retrievedWebData);
	}
}
