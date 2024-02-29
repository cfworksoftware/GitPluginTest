package webSites;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
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
		
		Assert.assertFalse(retrievedWebData.isEmpty());
		System.out.println("Retrieved Web Data: " + retrievedWebData.toString());
		System.out.println("Market status: " + retrievedWebData.get("stock_exchange").get(1).toString());
		System.out.println("Quote date: " + retrievedWebData.get("quote_date").get(1).toString());
		System.out.println("Unit price: " + retrievedWebData.get("unit_price").get(1).toString());
		System.out.println("Currency: " + retrievedWebData.get("currency_unit").get(1).toString());
		Assert.assertTrue("Market status missing",containsWords(retrievedWebData.get("stock_exchange").get(1),new String[]{"Open","Closed","After hours"}));
		Assert.assertTrue("Quote date missing",!retrievedWebData.get("quote_date").get(1).isEmpty());	
		Assert.assertTrue("Unit price missing",!retrievedWebData.get("unit_price").get(1).isEmpty());
		Assert.assertTrue("Currency missing",containsWords(retrievedWebData.get("currency_unit").get(1),new String[] {InvestmentWebsitesEnum.KEYSIGHT.getCurrency()}));	
		
	}
	
	public static boolean containsWords(String inputString, String[] items) {
	    boolean found = false;
	    for (String item : items) {
	        if (inputString.contains(item)) {
	            found = true;
	            break;
	        }
	    }
	    return found;
	}
	
//	@Test
//	@DisplayName("Test: Keysight Web Share Data sent to Database")
	void testSendStockDataToDatabase() {
		
		KeysightWebQuoteDataCollection dailyStockPriceData;
		
		DatabaseOperations databaseOperations;
		
		//String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
		HashMap<String, String> databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.KEYSIGHT.getDatabaseTableName(); 

		String expectedCurrencySymbol = InvestmentWebsitesEnum.KEYSIGHT.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailyStockPriceData = new KeysightWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
		
		System.out.println("retrievedstockdata: " + retrievedWebData.get(0).get(1) + ";"
		+ retrievedWebData.get(1).get(1) + ";" + retrievedWebData.get(2).get(1) + ";" 
				+ retrievedWebData.get(3).get(1) + ";");

		databaseOperations = new DatabaseOperations();	
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, retrievedWebData);
	}

}
