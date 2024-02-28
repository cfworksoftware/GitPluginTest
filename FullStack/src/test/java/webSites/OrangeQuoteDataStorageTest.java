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

class OrangeQuoteDataStorageTest {

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
	@DisplayName("Test: Orange SA Web Share Data fetched From Browser")
	void testShareDataFetchedFromBrowser() {
		
		OrangeWebQuoteDataCollection dailySharePriceData;

		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
		String expectedCurrencySymbol = InvestmentWebsitesEnum.ORANGESA.getCurrency();
		String primaryKeyName = "quote_date";
		
		dailySharePriceData = new OrangeWebQuoteDataCollection();
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
		Assert.assertTrue("Currency missing",containsWords(retrievedWebData.get("currency_unit").get(1),new String[] {InvestmentWebsitesEnum.ORANGESA.getCurrency()}));	
	}
	
	public static boolean containsWords(String inputString, String[] items) {
	    boolean found = false;
	    for (String item : items) {
	  //  	System.out.println("item: " + item.toString());
	        if (inputString.contains(item)) {
	            found = true;
	            break;
	        }
	    }
	    return found;
	}
	
//	@Test
//	@DisplayName("Test: Orange Web Share Data sent to Database")
	void testSendStockDataToDatabase() {

		OrangeWebQuoteDataCollection dailyStockPriceData;
		
		DatabaseOperations databaseOperations;
		
	//	String[] databaseFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
	//	String[] retrievedWebData = new String[4];
		
		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
					
	//	String databaseName = "accounting";
	//	String tableName = "webdata_orangesa_quote"; 
		String expectedCurrencySymbol = "€";
	//	String expectedCurrencySymbol = "€";
		String primaryKeyName = "quote_date";
		
		HashMap<String, String> databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
//		String[] databaseFieldNames = DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames();
		String databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		String tableName = DatabaseTableNamesEnum.ORANGESA.getDatabaseTableName(); 
		
		dailyStockPriceData = new OrangeWebQuoteDataCollection();
		retrievedWebData = dailyStockPriceData.getWebData("Chrome");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseFieldNames,*/ retrievedWebData);
	
	}

}
