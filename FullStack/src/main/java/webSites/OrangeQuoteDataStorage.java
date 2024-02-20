package webSites;

import java.util.HashMap;
import java.util.List;

import databaseConnection.DatabaseOperations;

public class OrangeQuoteDataStorage {

	OrangeWebQuoteDataCollection dailyStockPriceData;
	
	DatabaseOperations databaseOperations;
	
	//String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
	//String[] retrievedStockData = new String[4];
	
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	public void sendStockDataToDatabase() {
		
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.ORANGESA.getDatabaseTableName();
		String expectedCurrencySymbol = InvestmentWebsitesEnum.ORANGESA.getCurrency();
		
	//	String databaseName = "accounting";
	//	String tableName = "webdata_orangesa_quote"; 
	//	String expectedCurrencySymbol = "€";
		String primaryKeyName = "quote_date";
		
		dailyStockPriceData = new OrangeWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseFieldNames,*/ retrievedWebData);
	}
}
