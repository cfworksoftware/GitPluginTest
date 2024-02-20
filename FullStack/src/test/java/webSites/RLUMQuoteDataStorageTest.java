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

class RLUMQuoteDataStorageTest {

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
	@DisplayName("Test: RLUM Web Share Data fetched From Browser")
	void rlumTest() {
		
		HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
		
//		databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
//		tableName = DatabaseTableNamesEnum.RLUM.getDatabaseTableName();
//		expectedCurrencySymbol = InvestmentWebsitesEnum.RLUM.getCurrency();
		//	databaseName = "accounting";	
		//tableName = "webdata_rlumuk_growthincome_quote"; 
		//expectedCurrencySymbol = "p";
//		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval rlumQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData = rlumQuote.getWebRLUMData(InvestmentWebsitesEnum.RLUM);
		System.out.println("Retrieved Web Data: " + retrievedWebData.toString());
//		databaseOperations = new DatabaseOperations();
//		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*databaseShareFieldNames,*/ retrievedWebData);
	}
}
