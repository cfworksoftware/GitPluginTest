package webSites;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import databaseConnection.DatabaseOperations;

class KeysightQuoteDataStorageTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test: Keysight Web Share Data fetched From Browser")
	void testShareDataFetchedFromBrowser() {
		
		KeysightWebQuoteDataCollection dailySharePriceData;

		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
		String expectedCurrencySymbol = InvestmentWebsitesEnum.KEYSIGHT.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailySharePriceData = new KeysightWebQuoteDataCollection();
		retrievedWebData = dailySharePriceData.getWebData("Chrome");
		System.out.println("Retrieved Web Data: " + retrievedWebData.toString());
		
	}
	
	
//	@Test
//	@DisplayName("Test: Keysight Web Share Data sent to Database")
	void test() {
		
		KeysightWebQuoteDataCollection dailyStockPriceData;
		
		DatabaseOperations databaseOperations;
		
		//String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
//		String[] retrievedWebData = new String[4];
		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
		HashMap<String, String> databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
//		String[] databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.KEYSIGHT.getDatabaseTableName(); 
		
		
	//	String databaseName = "accounting";
	//	String tableName = "webdata_keysight_quote"; 
		String expectedCurrencySymbol = InvestmentWebsitesEnum.KEYSIGHT.getCurrency();
	//	String expectedCurrencySymbol = "$";
		String primaryKeyName = "quote_date";
		
		dailyStockPriceData = new KeysightWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
		
		System.out.println("retrievedstockdata: " + retrievedWebData.get(0).get(1) + ";"
		+ retrievedWebData.get(1).get(1) + ";" + retrievedWebData.get(2).get(1) + ";" 
				+ retrievedWebData.get(3).get(1) + ";");
		
//		System.out.println("retrievedstockdata: " + retrievedWebData[0] + ";"
//		+ retrievedWebData[1] + ";" + retrievedWebData[2] + ";" + retrievedWebData[3] + ";");
		databaseOperations = new DatabaseOperations();
		
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseFieldNames,*/ retrievedWebData);
	}

}
