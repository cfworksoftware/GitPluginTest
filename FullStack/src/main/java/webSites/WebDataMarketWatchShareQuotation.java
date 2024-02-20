package webSites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import dataFileConnection.WriteToFile;


public class WebDataMarketWatchShareQuotation {

//	static String shareQuoteDateTimeCSSSelectorPath =  "div.intraday__timestamp > span.timestamp__time";
//	static String currencyCSSSelectorPath = "h2.intraday__price > sup.character";
//	static String marketClosedCSSSelectorPath = "h2.intraday__price > span.value";
//	not used static String marketAfterHoursCSSSelectorPath = "h2.intraday__price > bg-quote[session = 'after']";
//	static String marketAfterHoursCSSSelectorPath = "h2.intraday__price > bg-quote.value";
	static String missingData = "XX";
	static String dataStorageFileNameType = "StockPriceData.txt";
	
	WebDriver driver;
	
	public HashMap<String, List<String>> /*String[]*/ getFinancialWebsiteShareQuote(WebDriver driver, String sharePriceQuoteCSSSelectorPath, String shareName) {
		HashMap<String, List<String>> databaseFields = new HashMap<String, List<String>>();
//		String[] retrievedShareData = new String[4];
		if (driver !=null) {
			databaseFields /*retrievedShareData*/ = findShareNameElementTextByCSSSelector(driver, sharePriceQuoteCSSSelectorPath, shareName);
		}
		return databaseFields /*retrievedShareData*/;
	}
	
	private /*String[]*/ HashMap<String, List<String>> findShareNameElementTextByCSSSelector(WebDriver driver, String cssPath, String shareName) {
		String sharePriceQuote = missingData;
		String shareCurrency = missingData;
		String shareQuoteDate = missingData;
		String marketStatus = missingData;
		HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
		//String[] retrievedWebData = new String [4];
		this.driver = driver;

		if (cssPath.contains(InvestmentWebsiteCSSSelectorEnum.INTRADAYCSSTAG.getCSSSelector())){		
			if (webElementPresent(cssPath)) {
				sharePriceQuote = getWebElementText(cssPath);
				System.out.println("Open:" + sharePriceQuote);
				marketStatus = "Open";
			}
			else if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector())) {
				sharePriceQuote = getWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector());
				System.out.println("After hours:" + sharePriceQuote);
				marketStatus = "After hours";
			}
			else if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector())) {
				sharePriceQuote = getWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector());
		//		sharePriceQuote = driver.findElement(By.cssSelector(marketClosedCSSSelectorPath)).getText().toString();
				System.out.println("Closed:" + sharePriceQuote);
				marketStatus = "Closed";
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector())) {
				shareCurrency = getWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector());
				System.out.println("ShareCurrency:" + shareCurrency);
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector())) {
				shareQuoteDate = getWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector());
				if (shareQuoteDate.equals("null")||shareQuoteDate.isEmpty())
				{
					LocalDate dateNow = LocalDate.now();
					System.out.println("Retrieved Share Quote Date substitution Date:"+ dateNow.toString());
					shareQuoteDate = dateNow.toString();
				}
			}
			new WriteToFile(dataStorageFileNameType).writeDataToFile(shareName + QuoteDescriptorEnum.QUOTETAG.getTagText() +/*" | Quote Price: "+*/ shareCurrency + sharePriceQuote + QuoteDescriptorEnum.QUOTEDATETAG.getTagText()/*" | Quote date: "*/ + shareQuoteDate + QuoteDescriptorEnum.MARKETSTATUSTAG.getTagText() /*+ " | Market Status: "*/ + marketStatus);
	/*		
			retrievedWebData[0] = sharePriceQuote;
			retrievedWebData[1] = shareQuoteDate;
			retrievedWebData[2] = marketStatus;
			retrievedWebData[3] = shareCurrency;	
		*/	
			retrievedWebData.put("unit_price",addRetrievedWebDatatoList("unit_price",sharePriceQuote));
			retrievedWebData.put("quote_date",addRetrievedWebDatatoList("quote_date",shareQuoteDate));
			retrievedWebData.put("stock_exchange",addRetrievedWebDatatoList("stock_exchange",marketStatus));
			retrievedWebData.put("currency_unit",addRetrievedWebDatatoList("currency_unit",shareCurrency));
		}
		return retrievedWebData /*retrievedWebData*/;
	}
	
	private List<String> addRetrievedWebDatatoList(String dataFieldName,String dataFieldValue)
	{
		List<String> data = new ArrayList<String>();
		data.add(dataFieldName);
		data.add(dataFieldValue);
		return data;
	}
		
	public boolean webElementPresent(String cssSelectorPath) {
		if (this.driver.findElements(By.cssSelector(cssSelectorPath)).size()!=0) {
			return true;
		}
		return false;
	}
	
	public String getWebElementText(String cssSelectorPath) {
		String elementText = null;
		elementText = driver.findElement(By.cssSelector(cssSelectorPath)).getText().toString();
		if (elementText.equalsIgnoreCase(null)) {
			elementText = missingData;
		}
		return elementText;
	}
	

	
	
}
