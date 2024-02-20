package webSites;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import webDriverSelenium.ConfigureSeleniumBrowserDriver;

public class AgilentWebQuoteDataCollection {
	
	ShareClass Agilent = new ShareClass(InvestmentWebsitesEnum.AGILENT);
	

//	String agilentURL = InvestmentWebsitesEnum.AGILENT.getShareURL();
//	String agilentName = InvestmentWebsitesEnum.AGILENT.getShareName();
	
//	static String agilentURL = "https://www.marketwatch.com/investing/stock/a";
//	static String agilentName = "Agilent";
	
//	static String stockPriceQuoteCSSSelectorPath = "h2.intraday__price > bg-quote";
//	static String sharePriceQuoteCSSSelectorPath = InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE.getCSSSelector(); 
	/*String[] retrievedStockData = new String[4];*/
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver();
	
	public HashMap<String, List<String>> /*String[]*/ getWebData(String browserName) {
	
		if (browserName.equals("Chrome")) {
			newBrowserDriver.getChromeDriver();
			newBrowserDriver.maximiseWindow();
			driver = newBrowserDriver.getChromeDriver();
		}
		if (driver !=null) {
			
			newBrowserDriver.setDriverURL(/*agilentURL*/Agilent.getShareURL());
			WebDataMarketWatchShareQuotation agilentWebData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = agilentWebData.getFinancialWebsiteShareQuote(driver, /*sharePriceQuoteCSSSelectorPath*/ 
					Agilent.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE), 
					/*agilentName*/Agilent.getShareName());
			newBrowserDriver.quitDriverInstance();
			driver = null;
		}
		return retrievedWebData;
	}
}
