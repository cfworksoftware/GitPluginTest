package webSites;

import databaseConnection.DatabaseOperations;
import java.util.HashMap; // import the HashMap class
import java.util.List;

public class AgilentQuoteDataStorage {

	AgilentWebQuoteDataCollection dailyStockPriceData;
	DatabaseOperations databaseOperations;
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
	
	public void sendStockDataToDatabase() {
 
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.AGILENT.getDatabaseTableName();
		String expectedCurrencySymbol = InvestmentWebsitesEnum.AGILENT.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailyStockPriceData = new AgilentWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, 
				retrievedWebData);
	}
	
}
