package webSites;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import dataFileConnection.WriteToFile;
import dataFormatting.DateFormat;


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
/*
		if (webElementPresent(cssPath)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
		       js.executeScript("window.scrollBy(0,350)", "");
		}
		*/
/*		WebElement revealed = driver.findElement(By.cssSelector(cssPath));
		
	//    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    Wait<WebDriver> wait =
	            new FluentWait<>(driver)
	                .withTimeout(Duration.ofSeconds(10))
	                .pollingEvery(Duration.ofMillis(100))
	                .ignoring(ElementNotInteractableException.class);
	    
	    wait.until(d -> revealed.isDisplayed());
*/
		if (cssPath.contains(InvestmentWebsiteCSSSelectorEnum.INTRADAYCSSTAG.getCSSSelector())){		
			if (webElementPresent(cssPath)) {
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.INTRADAYCSSTAG.getCSSSelector())).isDisplayed()) { 
				System.out.println("Intraday present");
//				driver.findElement(By.cssSelector(cssPath)).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
					sharePriceQuote = getVisibleWebElementText(cssPath);
					System.out.println("Intraday visible");
				}
				else {
					sharePriceQuote = getInVisibleWebElementText(cssPath);
					System.out.println("Intraday invisible");
				}
//				System.out.println("Default status Open: cssPath: " + cssPath +", Share price quote: "+ sharePriceQuote);
				marketStatus = "Open";
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector())) {
				sharePriceQuote = null;
				System.out.println("After hours present");
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector())).isDisplayed()) { 
		//		driver.findElement(By.cssSelector(cssPath)).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
				sharePriceQuote = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector());
				System.out.println("After hours visible");
				}
				else {
					sharePriceQuote = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector());
					System.out.println("After hours invisible");
				}
				System.out.println("After hours:" + sharePriceQuote);
				marketStatus = "After hours";
			}
			else if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector())) {
				System.out.println("Closed present");
		//		driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector())).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
				sharePriceQuote = null;
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector())).isDisplayed()) { 
					sharePriceQuote = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector());
					System.out.println("Closed visible");
				}
				else {
					sharePriceQuote = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector());
					System.out.println("Closed invisible");
				}
		//		sharePriceQuote = driver.findElement(By.cssSelector(marketClosedCSSSelectorPath)).getText().toString();
				System.out.println("Closed:" + sharePriceQuote);
				marketStatus = "Closed";
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector())) {
				System.out.println("Currency present");
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector())).isDisplayed()) 
				{
		//		driver.findElement(By.cssSelector(cssPath)).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
					shareCurrency = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector());
					System.out.println("Currency visible");
				}
				else {
					shareCurrency = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector());
					System.out.println("Currency invisible");
				}
				System.out.println("ShareCurrency:" + shareCurrency);
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector())) {
				System.out.println("Share date present");
			//	driver.findElement(By.cssSelector(cssPath)).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
			//	System.out.println("Share date present");
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector())).isDisplayed())
				{
					System.out.println("Share date displayed");
					shareQuoteDate = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector());			
					if ((shareQuoteDate == null)||(shareQuoteDate.isEmpty()))
					{
						LocalDate dateNow = LocalDate.now();
						System.out.println("Retrieved Share Quote Date substitution Date:"+ dateNow.toString());
						shareQuoteDate = dateNow.toString();
					}
				}
				else {
					shareQuoteDate = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector());
					DateFormat newDateFormat = new DateFormat();
					shareQuoteDate = newDateFormat.formatDate(shareQuoteDate);
					System.out.println("Element not visible");
				}
			}
			
			new WriteToFile(dataStorageFileNameType).writeDataToFile(shareName + QuoteDescriptorEnum.QUOTETAG.getTagText() 
			+/*" | Quote Price: "+*/ shareCurrency + sharePriceQuote 
			+ QuoteDescriptorEnum.QUOTEDATETAG.getTagText()/*" | Quote date: "*/ + shareQuoteDate 
			+ QuoteDescriptorEnum.MARKETSTATUSTAG.getTagText() /*+ " | Market Status: "*/ + marketStatus);
			
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
	
	public String getVisibleWebElementText(String cssSelectorPath) {
		String elementText = null;
		elementText = driver.findElement(By.cssSelector(cssSelectorPath)).getText();
		System.out.println("Element text: "+elementText);
		if ((elementText == null) ||(elementText.isEmpty())) {
			System.out.println("Element text not found: found-> " + driver.findElement(By.cssSelector(cssSelectorPath)).getText().toString());
			elementText = missingData;
		}
		return elementText;
	}
	
	public String getInVisibleWebElementText(String cssSelectorPath) {
		String elementText = null;
		elementText = driver.findElement(By.cssSelector(cssSelectorPath)).getAttribute("textContent");
		System.out.println("Element text: "+elementText);
		if ((elementText == null) ||(elementText.isEmpty())) {
			System.out.println("Element text not found: found-> " + driver.findElement(By.cssSelector(cssSelectorPath)).getText().toString());
			elementText = missingData;
		}
		return elementText;
	}
	
	
}
