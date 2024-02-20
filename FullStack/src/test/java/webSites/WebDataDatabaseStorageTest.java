package webSites;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import databaseConnection.DatabaseOperations;

class WebDataDatabaseStorageTest {

	DatabaseOperations databaseOperations;
	
	String[] databaseShareFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
	String[] databaseEuroExchangeRateFieldNames = {"quote_date","conversion_rate","currency_from_gbp","currency_to_eur"};
	String[] databaseDollarExchangeRateFieldNames = {"quote_date","conversion_rate","currency_from_gbp","currency_to_usd"};
	String databaseName;
	String tableName; 
	String primaryKeyName;
	String expectedCurrencySymbol;
	
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
	
//	String[] retrievedWebData = new String[4];
	
	
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

//	@Test
//	@DisplayName("Test: Web Share Data sent to Database cycling through InvestmentWebsitesEnum")
	void WebsitesLoopThroughTest() {

		databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		
//		databaseName = "accounting";
		primaryKeyName = "quote_date";
		
		// System.out.println("Keysight set of Tests start of.");
		//Loop here through enum
		for(InvestmentWebsitesEnum test: InvestmentWebsitesEnum.values()) {
			System.out.println("Tests start of:" + test.getShareName());
			WebDataDailyFinancialQuoteRetrieval dailyQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
			if (test.name().toString().equalsIgnoreCase("RLUM")){
				retrievedWebData = dailyQuote.getWebRLUMData(InvestmentWebsitesEnum.RLUM);
			}
			else {
				retrievedWebData = dailyQuote.getWebShareData(InvestmentWebsitesEnum.valueOf(test.name()));
			}
			System.out.println("retrievedWebData: " + retrievedWebData.get(0).get(1) + ";"
					+ retrievedWebData.get(1).get(1) + ";" + retrievedWebData.get(2).get(1) + ";" + retrievedWebData.get(3).get(1) + ";");
			databaseOperations = new DatabaseOperations();
			tableName = DatabaseTableNamesEnum.valueOf(test.name()).getDatabaseTableName();
			expectedCurrencySymbol = InvestmentWebsitesEnum.valueOf(test.name()).getCurrency();
			databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseShareFieldNames,*/ retrievedWebData);
		}
		/*
		databaseName = "accounting";
		tableName = "webdata_keysight_quote"; 
		expectedCurrencySymbol = "$";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval keysightQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = keysightQuote.getWebShareData(InvestmentWebsitesEnum.KEYSIGHT);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, databaseShareFieldNames, retrievedWebData);
	
		databaseName = "accounting";
		tableName = "webdata_agilent_quote"; 
		expectedCurrencySymbol = "$";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval agilentQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = agilentQuote.getWebShareData(InvestmentWebsitesEnum.AGILENT);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, databaseShareFieldNames, retrievedWebData);
	
		databaseName = "accounting";
		tableName = "webdata_orangesa_quote"; 
		expectedCurrencySymbol = "€";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval orangesaQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = orangesaQuote.getWebShareData(InvestmentWebsitesEnum.ORANGESA);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, databaseShareFieldNames, retrievedWebData);
		*/
	}

//	@Test
//	@DisplayName("Test: RLUM Web Share Data sent to Database")
	void rlumTest() {
		databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		tableName = DatabaseTableNamesEnum.RLUM.getDatabaseTableName();
		expectedCurrencySymbol = InvestmentWebsitesEnum.RLUM.getCurrency();
		//	databaseName = "accounting";	
		//tableName = "webdata_rlumuk_growthincome_quote"; 
		//expectedCurrencySymbol = "p";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval rlumQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = rlumQuote.getWebRLUMData(InvestmentWebsitesEnum.RLUM);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseShareFieldNames,*/ retrievedWebData);
	}
	
//	@Test
//	@DisplayName("Test: Euro Pound exchange rate sent to Database")
	void euroRateTest() {	
		
		databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		tableName = DatabaseTableNamesEnum.POUNDEURO.getDatabaseTableName();
		
//		databaseName = "accounting";
//		tableName = "webdata_currencyrate_gbp_eur";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval dailyExchangeRate = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = dailyExchangeRate.getWebCurrencyData(CurrencyWebsiteEnum.XE,"GBP", "EUR");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertExchangeRateDataToDatabase(databaseName, tableName, primaryKeyName, /*databaseEuroExchangeRateFieldNames,*/ retrievedWebData);
	}
	
//	@Test
//	@DisplayName("Test: Dollar Pound exchange rate sent to Database")
	void dollarRateTest() {	
		
		databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		tableName = DatabaseTableNamesEnum.POUNDDOLLAR.getDatabaseTableName();
		
		//databaseName = "accounting";
		//tableName = "webdata_currencyrate_gbp_usd";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval dailyExchangeRate = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = dailyExchangeRate.getWebCurrencyData(CurrencyWebsiteEnum.XE,"GBP", "USD");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertExchangeRateDataToDatabase(databaseName, tableName, primaryKeyName, /*databaseDollarExchangeRateFieldNames,*/ retrievedWebData);
	}
	
//	@Test
//	@DisplayName("Test: InvestmentWebsitesEnum mapping")
	void enumMapping() {
		String currencySymbol = InvestmentWebsitesEnum.ORANGESA.getCurrency();
		System.out.println(InvestmentWebsitesEnum.valueOf("ORANGESA").getShareName());
		InvestmentWebsitesEnum dummy = InvestmentWebsitesEnum.KEYSIGHT;
		
		System.out.println(dummy.name());
		
		for(InvestmentWebsitesEnum test: InvestmentWebsitesEnum.values()) {
			assertDoesNotThrow( ()->DatabaseTableNamesEnum.valueOf(test.name()));
//			assertNotEquals(DatabaseTableNamesEnum.valueOf(test.name()),null,test.name()+"does not exist as a table");
		}	
		System.out.println("By index: " + InvestmentWebsitesEnum.values()[0]);
	}
	
//	@Test
//	@DisplayName("Test: InvestmentWebsitesEnum reverse mapping")
	void enumReverseMapping() {
		for(DatabaseTableNamesEnum test: DatabaseTableNamesEnum.values()) {
			assertDoesNotThrow(()->InvestmentWebsitesEnum.valueOf(test.name()));
//			assertNotEquals(DatabaseTableNamesEnum.valueOf(test.name()),null,test.name()+"does not exist as a table");
		}	
	}
	
//	@Test
//	@DisplayName("Test: Orange Web Share Data sent to Database")
	void missingValueThrowsIllegalArgumentException() {
	    assertThrows(IllegalArgumentException.class, () -> DatabaseTableNamesEnum.valueOf("XXXX"));
//	    assertDoesNotThrow(()->DatabaseTableNamesEnum.valueOf("RLUM"));
	}
}

