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

	static String missingData = "XX";
	static String dataStorageFileNameType = "StockPriceData.txt";
	
	WebDriver driver;
	
	public HashMap<String, List<String>> getFinancialWebsiteShareQuote(WebDriver driver, 
			String sharePriceQuoteCSSSelectorPath, String shareName) {
		HashMap<String, List<String>> databaseFields = new HashMap<String, List<String>>();
		if (driver !=null) {
			databaseFields = findShareNameElementTextByCSSSelector(driver, sharePriceQuoteCSSSelectorPath, shareName);
		}
		return databaseFields;
	}
	
	private HashMap<String, List<String>> findShareNameElementTextByCSSSelector(WebDriver driver, String cssPath, 
			String shareName) {
		String sharePriceQuote = missingData;
		String shareCurrency = missingData;
		String shareQuoteDate = missingData;
		String marketStatus = missingData;
		HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
		this.driver = driver;

		if (cssPath.contains(InvestmentWebsiteCSSSelectorEnum.INTRADAYCSSTAG.getCSSSelector())){		
			if (webElementPresent(cssPath)) {
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.INTRADAYCSSTAG.getCSSSelector())).isDisplayed()) { 
					sharePriceQuote = getVisibleWebElementText(cssPath);
				}
				else {
					sharePriceQuote = getInVisibleWebElementText(cssPath);
				}
				marketStatus = "Open";
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector())) {
				sharePriceQuote = null;
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector())).isDisplayed()) { 
					sharePriceQuote = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector());
				}
				else {
					sharePriceQuote = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETAFTERHOURS.getCSSSelector());
					System.out.println("After hours invisible");
				}
				System.out.println("After hours:" + sharePriceQuote);
				marketStatus = "After hours";
			}
			else if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector())) {
					sharePriceQuote = null;
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector())).isDisplayed()) { 
					sharePriceQuote = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector());
				}
				else {
					sharePriceQuote = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.MARKETCLOSED.getCSSSelector());
				}
				marketStatus = "Closed";
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector())) {
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector())).isDisplayed()) 
				{
					shareCurrency = getVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector());
				}
				else {
					shareCurrency = getInVisibleWebElementText(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTECURRENCY.getCSSSelector());
				}
			}
			if (webElementPresent(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector())) {
				if (driver.findElement(By.cssSelector(InvestmentWebsiteCSSSelectorEnum.SHAREQUOTEDATETIME.getCSSSelector())).isDisplayed())
				{
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
					shareQuoteDate = newDateFormat.formatDateCommaSeperated(shareQuoteDate);
				}
			}
			
			new WriteToFile(dataStorageFileNameType).writeDataToFile(shareName + QuoteDescriptorEnum.QUOTETAG.getTagText() 
			+ shareCurrency + sharePriceQuote 
			+ QuoteDescriptorEnum.QUOTEDATETAG.getTagText() + shareQuoteDate 
			+ QuoteDescriptorEnum.MARKETSTATUSTAG.getTagText() + marketStatus);

			retrievedWebData.put("unit_price",addRetrievedWebDatatoList("unit_price",sharePriceQuote));
			retrievedWebData.put("quote_date",addRetrievedWebDatatoList("quote_date",shareQuoteDate));
			retrievedWebData.put("stock_exchange",addRetrievedWebDatatoList("stock_exchange",marketStatus));
			retrievedWebData.put("currency_unit",addRetrievedWebDatatoList("currency_unit",shareCurrency));
		}
		return retrievedWebData;
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
