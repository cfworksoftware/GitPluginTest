package webSites;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import webDriverSelenium.ConfigureSeleniumBrowserDriver;

public class OrangeWebQuoteDataCollection {

	ShareClass OrangeSA = new ShareClass(InvestmentWebsitesEnum.ORANGESA);
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver("Chrome");
	
	public HashMap<String, List<String>> getWebData(String browserName) {
	
		if (browserName.equals("Chrome")) {
			driver = newBrowserDriver.getChromeDriver();
		}
		if (driver !=null) {
			newBrowserDriver.setDriverURL(OrangeSA.getShareURL());
			newBrowserDriver.setZoomLevel();
			WebDataMarketWatchShareQuotation orangeWebData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = orangeWebData.getFinancialWebsiteShareQuote(driver, 
					OrangeSA.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE),
					OrangeSA.getShareName());
			newBrowserDriver.quitDriverInstance();
			driver = null;
		}
		return retrievedWebData;
	}
}
