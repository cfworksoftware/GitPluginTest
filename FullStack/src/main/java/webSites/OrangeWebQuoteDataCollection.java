package webSites;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import webDriverSelenium.ConfigureSeleniumBrowserDriver;

public class OrangeWebQuoteDataCollection {

	ShareClass OrangeSA = new ShareClass(InvestmentWebsitesEnum.ORANGESA);
	
//	static String orangeURL = InvestmentWebsitesEnum.ORANGESA.getShareURL();
//	static String orangeName  = InvestmentWebsitesEnum.ORANGESA.getShareName();
//	static String sharePriceQuoteCSSSelectorPath = InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE.getCSSSelector(); 
	
//	static String orangeURL = "https://www.marketwatch.com/investing/stock/ORA?countryCode=FR";
//	static String orangeName = "Orange SA";

//	static String sharePriceQuoteCSSSelectorPath = "h2.intraday__price > bg-quote";
	
	//String[] retrievedWebData = new String[4];
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver();
	
	public HashMap<String, List<String>> /*String[]*/  getWebData(String browserName) {
	
		if (browserName.equals("Chrome")) {
			newBrowserDriver.getChromeDriver();
			newBrowserDriver.maximiseWindow();
			driver = newBrowserDriver.getChromeDriver();
		}
		if (driver !=null) {
			newBrowserDriver.setDriverURL(/*orangeURL*/OrangeSA.getShareURL());
			WebDataMarketWatchShareQuotation orangeWebData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = orangeWebData.getFinancialWebsiteShareQuote(driver, /*sharePriceQuoteCSSSelectorPath*/
					OrangeSA.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE),/*orangeName*/
					OrangeSA.getShareName());
			newBrowserDriver.quitDriverInstance();
			driver = null;
		}
		return retrievedWebData;
	}
}
