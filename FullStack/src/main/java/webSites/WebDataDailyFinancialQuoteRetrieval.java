package webSites;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataFormatting.DateFormat;
import webDriverSelenium.ConfigureSeleniumBrowserDriver;

public class WebDataDailyFinancialQuoteRetrieval {

//	static String xeURL = "https://www.xe.com/";
//	static String sharePriceQuoteCSSSelectorPath = "h2.intraday__price > bg-quote";
	static String missingData = "XX";
//	String[] retrievedWebData = new String[4];
	String[] rlumWebData = new String[4];
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
	
	WebDriver webDriver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver();

	public WebDataDailyFinancialQuoteRetrieval(String browserName) {
		if (browserName.equals("Chrome")) {
			newBrowserDriver.getChromeDriver();
			newBrowserDriver.maximiseWindow();
			webDriver = newBrowserDriver.getChromeDriver();
		}
	}

	public HashMap<String, List<String>> /*String[]*/ getWebShareData(InvestmentWebsitesEnum shareName) {
		if (webDriver !=null) {
			newBrowserDriver.setDriverURL(shareName.getShareURL());
			WebDataMarketWatchShareQuotation webShareData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = webShareData.getFinancialWebsiteShareQuote(webDriver, InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE.getCSSSelector() /*sharePriceQuoteCSSSelectorPath*/, shareName.getShareName());
			newBrowserDriver.quitDriverInstance();
			webDriver = null;
		}
		return retrievedWebData;
	}

	public HashMap<String, List<String>> /*String[]*/ getWebCurrencyData(CurrencyWebsiteEnum websiteName, String fromCurrencyCode, String toCurrencyCode) {	
		String databaseFromCurrencyText = missingData;
		String databaseToCurrencyText = missingData;
		String exchangeRateQuote = missingData;
		String currencyQuoteDate = missingData;
		String displayedToCurrencySelectionContent = missingData;
		
	//	HashMap<String, String> retrievedCurrencyData = new HashMap<String, String>();
				
		if (webDriver !=null) {
			newBrowserDriver.setDriverURL(websiteName.getWebURL());
			clickCSSElementByText("button", "Accept");		
			if  (toCurrencyCode.equals("EUR")) {
				displayedToCurrencySelectionContent = "Euro";			
			}
			if  (toCurrencyCode.equals("USD")) {
				displayedToCurrencySelectionContent = "US Dollar";
			}
			int cnt =0;
			do {
				cnt++;
		//		findElementByCSSSelector("button[aria-describedby='midmarketFromCurrency-descriptiveText']").click();		
				findElementByCSSSelector("div#midmarketFromCurrency").click();	
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				findElementByCSSSelector("input[aria-describedby='midmarketFromCurrency-current-selection']").sendKeys(fromCurrencyCode);	
				
				findElementByCSSSelector("input[aria-describedby='midmarketFromCurrency-current-selection']").sendKeys(Keys.ENTER);
//			System.out.println("counter: "+cnt+"element text: "+ webDriver.findElement(By.cssSelector("div#midmarketFromCurrency-descriptiveText")).getText().toString());
			} 
			while ((!webDriver.findElement(By.cssSelector("div#midmarketFromCurrency-descriptiveText")).getText().contains("British Pound"))||(cnt==5));
//			System.out.println("counter1: "+cnt);
			cnt=0;
			do {
				cnt++;
				findElementByCSSSelector("div#midmarketToCurrency").click();
				findElementByCSSSelector("input[aria-describedby='midmarketToCurrency-current-selection']").sendKeys(toCurrencyCode);
				findElementByCSSSelector("input[aria-describedby='midmarketToCurrency-current-selection']").sendKeys(Keys.ENTER);
			} 
			while ((!findElementByCSSSelector("div#midmarketToCurrency-descriptiveText").getText().contains(displayedToCurrencySelectionContent))||(cnt==5));
			clickCSSElementByText("button", "Convert");				
			new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("p[class^='result__ConvertedText']")));

			WebElement p = webDriver.findElement(By.cssSelector("span.faded-digits"));
			WebElement t= p.findElement(By.xpath("parent::*"));
//			System.out.println(t.getText());
			exchangeRateQuote=t.getText().toString().replaceAll("[^0-9.]", "");  
			retrievedWebData.put("conversion_rate",addRetrievedWebDatatoList("conversion_rate",exchangeRateQuote));
//			retrievedWebData.put("conversion_rate",exchangeRateQuote);

			String fromCurrencyText = findElementByCSSSelector("p[class^='result__ConvertedText']").getText();
//			System.out.println("From Currency:" + fromCurrencyText);
			assertTrue(fromCurrencyText.contains("British Pound"),()->"British Pound");		
			String toCurrencyText =findElementByCSSSelector("div[class^='unit-rates'] > p").getText();
//			System.out.println("To Currency:" + toCurrencyText);
			
			if  (fromCurrencyCode.equals("GBP")) {
				assertTrue(fromCurrencyText.contains("British Pound"),()->"British Pound");	
				databaseFromCurrencyText ="GBP";
				retrievedWebData.put("currency_from_gbp",addRetrievedWebDatatoList("currency_from_gbp",databaseFromCurrencyText));
			//	retrievedWebData.put("currency_from_gbp",databaseFromCurrencyText);
			}
			
			if  (toCurrencyCode.equals("EUR")) {
				assertTrue(toCurrencyText.contains("EUR"),()->"Euros");	
				databaseToCurrencyText = "Euros";
				retrievedWebData.put("currency_to_eur",addRetrievedWebDatatoList("currency_to_eur",databaseToCurrencyText));
			//	retrievedWebData.put("currency_to_eur",databaseToCurrencyText);
			}
			if  (toCurrencyCode.equals("USD")) {
				assertTrue(toCurrencyText.contains("USD"),()->"US Dollars");
				databaseToCurrencyText = "US Dollars";
				retrievedWebData.put("currency_to_usd",addRetrievedWebDatatoList("currency_to_usd",databaseToCurrencyText));
			//	retrievedWebData.put("currency_to_usd",databaseToCurrencyText);
			}
		String update[] = findElementByCSSSelector("div[class^='result__LiveSubText']").getText().split("Last updated");
//		String update[] = webDriver.findElement(By.cssSelector("div[class^='result__LiveSubText']")).getText().split("Last updated");
//		System.out.println(update[0]+";"+update[1]);
		String dateparts[] = update[1].split(",", 0);
//		System.out.println("Date:" + dateparts[0]+";"  + dateparts[1]+";"  + dateparts[2]+";");
		String dateString = dateparts[1].trim()+dateparts[0];
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MMM dd");
		LocalDate date = LocalDate.parse(dateString, format);		
		currencyQuoteDate = date.toString();
		retrievedWebData.put("quote_date",addRetrievedWebDatatoList("quote_date",currencyQuoteDate));
	//	retrievedWebData.put("quote_date",currencyQuoteDate);
//		System.out.println("Formatted Date:"+ date.toString());
		
		/*		
		retrievedWebData[0] = sharePriceQuote;
		retrievedWebData[1] = shareQuoteDate;
		retrievedWebData[2] = marketStatus;
		retrievedWebData[3] = shareCurrency;	
	*/	
		
	//	"quote_date","conversion_rate","currency_from_gbp","currency_to_eur"
	//	"quote_date","conversion_rate","currency_from_gbp","currency_to_usd"
	/*	
		retrievedWebData[0] = currencyQuoteDate;
		retrievedWebData[1] = exchangeRateQuote;
		retrievedWebData[2] = databaseFromCurrencyText;
		retrievedWebData[3] = databaseToCurrencyText;
		*/
		newBrowserDriver.quitDriverInstance();
		webDriver = null;
		}	
		return retrievedWebData;
//		return retrievedWebData;
	}
	
	private List<String> addRetrievedWebDatatoList(String dataFieldName,String dataFieldValue)
	{
		List<String> data = new ArrayList<String>();
		data.add(dataFieldName);
		data.add(dataFieldValue);
		return data;
	}
	
	public HashMap<String, List<String>> /*String[]*/ getWebRLUMData(InvestmentWebsitesEnum shareName) 
	{
		if (webDriver !=null) {
			newBrowserDriver.setDriverURL(shareName.getShareURL());
			WebDriver driver = newBrowserDriver.getChromeDriver();
			new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button#onetrust-accept-btn-handler")));
			findElementByCSSSelector("button#onetrust-accept-btn-handler").click();
			WebElement fundRow = null;
	//		System.out.println("table");
			new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("iFrame[title = 'iFrame Content']")));
			driver.switchTo().frame(findElementByCSSSelector("iFrame[title = 'iFrame Content']"));
			List<WebElement>  options = driver.findElements(By.cssSelector("tbody.fe-fund-tableBody")); //table.fe-datatable>tbody.fe-fund-tableBody>
			WebElement data = options.get(1);
			List<WebElement>  rows = data.findElements(By.cssSelector("tr"));
			System.out.println("List size: " + options.size());
	/*		int cnt =0;
			for (WebElement row : rows) {
				System.out.println("List item " + cnt++ +" :"+ row.getText());
			} */
	//		int cnt =0;
			for (WebElement row : rows) 
			{
	//			System.out.println("List item " + cnt++ +" :"+ row.getText());
				if ((row.getText()).contains("Royal London UK Income With Growth Trust Inc")) {
			    	fundRow = row;
			        break;	
				}
			}
			if (fundRow!=null) 
			{
				List<WebElement>  columns = fundRow.findElements(By.cssSelector("td[class='pdfableColumn']")); // if use td.pdfableColumn returns all classes with that text in it
				int colcnt =0;
				for (WebElement column : columns) 
				{	
					System.out.println("List column " + colcnt +" :"+ column.getText());
	//				retrievedWebData[colcnt++] = column.getText().toString();
					rlumWebData[colcnt++] = column.getText().toString();
				}				
				String newDateFormat = (new DateFormat()).formatDateTwoDigitMonth(rlumWebData[1]);
				System.out.println("new Date Format: "+ newDateFormat);
				retrievedWebData.put("unit_price",addRetrievedWebDatatoList("unit_price",rlumWebData[0]));
				retrievedWebData.put("quote_date",addRetrievedWebDatatoList("quote_date",newDateFormat));
				retrievedWebData.put("stock_exchange",addRetrievedWebDatatoList("stock_exchange",rlumWebData[2]));
				System.out.println("unit_price" + rlumWebData[0]);
				System.out.println("currency" + InvestmentWebsitesEnum.RLUM.getCurrency().toString());
			//int currencyIndex = retrievedWebData.get("unit_price").get(1).indexOf('p'); 
				int currencyIndex = retrievedWebData.get("unit_price").get(1).indexOf(InvestmentWebsitesEnum.RLUM.getCurrency().toString());
				System.out.println("currency index" + currencyIndex );
				String unitValue = retrievedWebData.get("unit_price").get(1).substring(0, currencyIndex);
				String currencyFound = retrievedWebData.get("unit_price").get(1).substring(currencyIndex,7);
				System.out.println("unit value" + unitValue );
				retrievedWebData.put("unit_price",addRetrievedWebDatatoList("unit_price",unitValue));
				retrievedWebData.put("currency_unit",addRetrievedWebDatatoList("currency_unit",currencyFound));
			//	retrievedWebData.put("currency_unit",addRetrievedWebDatatoList("currency_unit",rlumWebData[3]));
			}
			newBrowserDriver.quitDriverInstance();	
		}
		return retrievedWebData;
	}
	
	public WebElement findElementByCSSSelector(String cssSelector) {
		return webDriver.findElement(By.cssSelector(cssSelector));
	}
	
	public void clickCSSElementByText(String cssElementType, String elementText) {
		List<WebElement> options = webDriver.findElements(By.cssSelector(cssElementType));
//		int cnt =0;
		for (WebElement opt : options) {
		    if (opt.getText().equals(elementText)) {
		        opt.click();
//		        System.out.println("Clicking");
		        break;
		    }
		}
	}
}