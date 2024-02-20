package webSites;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import databaseConnection.DatabaseOperations;

class AgilentQuoteDataStorageTest {

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
	@DisplayName("Test: Agilent Web Share Data fetched From Browser")
	void testShareDataFetchedFromBrowser() {
		
		AgilentWebQuoteDataCollection dailySharePriceData;

		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
		String expectedCurrencySymbol = InvestmentWebsitesEnum.AGILENT.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailySharePriceData = new AgilentWebQuoteDataCollection();
		retrievedWebData = dailySharePriceData.getWebData("Chrome");
		System.out.println("Retrieved Web Data: " + retrievedWebData.toString());
		
	}
	
	//@Test
	//@DisplayName("Test: Agilent Web Share Data sent to Database")
	void testSendStockDataToDatabase() {
		
		AgilentWebQuoteDataCollection dailySharePriceData;
		
		DatabaseOperations databaseOperations;
		
//		String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
//		String[] retrievedWebData = new String[4];
		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
		HashMap<String, String> databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
//		String[] databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.AGILENT.getDatabaseTableName(); 
		
//		String databaseName = "accounting";
//		String tableName = "webdata_agilent_quote"; 
//		String expectedCurrencySymbol = "$";
		String expectedCurrencySymbol = InvestmentWebsitesEnum.AGILENT.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailySharePriceData = new AgilentWebQuoteDataCollection();
		retrievedWebData = dailySharePriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseFieldNames,*/ retrievedWebData);
	
	}

}
