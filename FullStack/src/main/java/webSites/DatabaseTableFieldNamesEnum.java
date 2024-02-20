package webSites;

import java.util.HashMap;

public enum DatabaseTableFieldNamesEnum {

	
//	String[] databaseShareFieldNames = {"unit_price","quote_date","stock_exchange","currency_unit"};
//	String[] databaseExchangeRateFieldNames = {"quote_date","conversion_rate","currency_from_gbp","currency_to_eur"};
//	String[] databaseDollarExchangeRateFieldNames = {"quote_date","conversion_rate","currency_from_gbp","currency_to_usd"};
	
    TableFieldNames(new HashMap<String, String>(),new HashMap<String, String>(), new HashMap<String, String>());
   // TableFieldNames(new String[]{"unit_price","quote_date","stock_exchange","currency_unit"},new String[]{"quote_date","conversion_rate","currency_from_gbp","currency_to_eur"}, new String[]{"quote_date","conversion_rate","currency_from_gbp","currency_to_usd"});
	
   // private final String[] databaseShareFieldNames;
   // private final String[] databaseEuroExchangeRateFieldNames;
   // private final String[] databaseDollarExchangeRateFieldNames;

    private HashMap<String, String> databaseShareFieldNames = new HashMap<String, String>();
    private HashMap<String, String> databaseEuroExchangeRateFieldNames = new HashMap<String, String>();
    private HashMap<String, String> databaseDollarExchangeRateFieldNames = new HashMap<String, String>(); 
    
    DatabaseTableFieldNamesEnum(HashMap<String, String> /*String[]*/ databaseShareFieldNames, HashMap<String, String> /*String[]*/ databaseEuroExchangeRateFieldNames, HashMap<String, String> /*String[]*/ databaseDollarExchangeRateFieldNames) {
    	databaseShareFieldNames.put("unit_price", "unit_price");
    	databaseShareFieldNames.put("quote_date", "quote_date");
    	databaseShareFieldNames.put("stock_exchange", "stock_exchange");
    	databaseShareFieldNames.put("currency_unit", "currency_unit");
    	
    	databaseEuroExchangeRateFieldNames.put("quote_date", "quote_date");
     	databaseEuroExchangeRateFieldNames.put("conversion_rate", "conversion_rate");
    	databaseEuroExchangeRateFieldNames.put("currency_from_gbp", "currency_from_gbp");
    	databaseEuroExchangeRateFieldNames.put("currency_to_eur", "currency_to_eur");

    	databaseDollarExchangeRateFieldNames.put("quote_date", "quote_date");
     	databaseDollarExchangeRateFieldNames.put("conversion_rate", "conversion_rate");
    	databaseDollarExchangeRateFieldNames.put("currency_from_gbp", "currency_from_gbp");
    	databaseDollarExchangeRateFieldNames.put("currency_to_usd", "currency_to_usd");
    	
    	this.databaseShareFieldNames = databaseShareFieldNames;
        this.databaseEuroExchangeRateFieldNames = databaseEuroExchangeRateFieldNames;
        this.databaseDollarExchangeRateFieldNames = databaseDollarExchangeRateFieldNames;
    }

    public HashMap<String, String> /*String[]*/ getDatabaseShareFieldNames() {
        return databaseShareFieldNames;
    }
    public HashMap<String, String> /*String[]*/ getDatabaseEuroExchangeRateFieldNames() {
        return databaseEuroExchangeRateFieldNames;
    }
	
    public HashMap<String, String> /*String[]*/  getDatabaseDollarExchangeRateFieldNames() {
        return databaseDollarExchangeRateFieldNames;
    }
	
}

