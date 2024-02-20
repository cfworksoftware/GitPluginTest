package webSites;

import java.util.HashMap;
import java.util.List;

import databaseConnection.DatabaseOperations;

public class WebDataDatabaseStorage {

	KeysightWebQuoteDataCollection dailySharePriceData;
	
	DatabaseOperations databaseOperations;
	
//	String[] databaseShareFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
//	String[] databaseExchangeRateFieldNames = {"quote_date","conversion_rate","currency_from_gbp","currency_to_eur"};
//	String[] databaseDollarExchangeRateFieldNames = {"quote_date","conversion_rate","currency_from_gbp","currency_to_usd"};
	String databaseName;
	String tableName; 
	String expectedCurrencySymbol;
	String primaryKeyName;
	
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
	
//	String[] retrievedData = new String[4];
	
	public void sendFinancialDataToDatabase() {
		
		databaseName = DatabaseNameEnum.FINANCIALDATABASE.getDatabaseName();
		
		//databaseName = "accounting"
		tableName = DatabaseTableNamesEnum.POUNDEURO.getDatabaseTableName();
		//tableName = "webdata_currencyrate_gbp_eur";
		//tableName = "webdata_keysight_quote"; 
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval dailyEuroExchangeRate = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData /*retrievedData*/  = dailyEuroExchangeRate.getWebCurrencyData(CurrencyWebsiteEnum.XE,"GBP", "EUR");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertExchangeRateDataToDatabase(databaseName, tableName, primaryKeyName, /*DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseEuroExchangeRateFieldNames(),*/ retrievedWebData);
			
		//databaseName = "accounting";
		tableName = DatabaseTableNamesEnum.POUNDDOLLAR.getDatabaseTableName();
		//tableName = "webdata_currencyrate_gbp_usd";
		//tableName = "webdata_keysight_quote"; 
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval dailyDollarExchangeRate = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData /*retrievedData*/  = dailyDollarExchangeRate.getWebCurrencyData(CurrencyWebsiteEnum.XE,"GBP", "USD");
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertExchangeRateDataToDatabase(databaseName, tableName, primaryKeyName, /*DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseDollarExchangeRateFieldNames(),*/ retrievedWebData);
			
		//databaseName = "accounting";
		tableName = DatabaseTableNamesEnum.KEYSIGHT.getDatabaseTableName();
		//tableName = "webdata_keysight_quote"; 
		expectedCurrencySymbol =InvestmentWebsitesEnum.KEYSIGHT.getCurrency();
		//expectedCurrencySymbol = "$";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval keysightQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData /*retrievedData*/  = keysightQuote.getWebShareData(InvestmentWebsitesEnum.KEYSIGHT);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames(),*/ retrievedWebData);
	
		//databaseName = "accounting";
		tableName = DatabaseTableNamesEnum.AGILENT.getDatabaseTableName();
		//tableName = "webdata_agilent_quote";
		expectedCurrencySymbol =InvestmentWebsitesEnum.AGILENT.getCurrency();
		//expectedCurrencySymbol = "$";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval agilentQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData /*retrievedData*/ = agilentQuote.getWebShareData(InvestmentWebsitesEnum.AGILENT);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames(),*/ retrievedWebData);
		
		//databaseName = "accounting";
		tableName = DatabaseTableNamesEnum.ORANGESA.getDatabaseTableName();
		//tableName = "webdata_orangesa_quote"; 
		expectedCurrencySymbol =InvestmentWebsitesEnum.ORANGESA.getCurrency();
		//expectedCurrencySymbol = "€";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval orangesaQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData /*retrievedData*/  = orangesaQuote.getWebShareData(InvestmentWebsitesEnum.ORANGESA);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames(),*/ retrievedWebData);
		
		//databaseName = "accounting";
		tableName = DatabaseTableNamesEnum.RLUM.getDatabaseTableName();
		//tableName = "webdata_rlumuk_growthincome_quote;"; 
		expectedCurrencySymbol =InvestmentWebsitesEnum.RLUM.getCurrency();
		//expectedCurrencySymbol = "p";
		primaryKeyName = "quote_date";
		
		WebDataDailyFinancialQuoteRetrieval rlumQuote = new WebDataDailyFinancialQuoteRetrieval("Chrome");
		retrievedWebData /*retrievedData*/ = rlumQuote.getWebShareData(InvestmentWebsitesEnum.RLUM);
		databaseOperations = new DatabaseOperations();
		databaseOperations.insertDataToDatabase(databaseName, tableName, primaryKeyName, expectedCurrencySymbol, /*DatabaseTableFieldNamesEnum.TableFieldNames.getDatabaseShareFieldNames(),*/ retrievedWebData);
		
	}
}
