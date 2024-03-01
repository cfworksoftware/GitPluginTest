package webSites;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import webDriverSelenium.ConfigureSeleniumBrowserDriver;

public class KeysightWebQuoteDataCollection {
	
	ShareClass Keysight = new ShareClass(InvestmentWebsitesEnum.KEYSIGHT);
	HashMap<String, List<String>> retrievedWebData  = new HashMap<String, List<String>>();
	
	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver("Chrome");
	
	public HashMap<String, List<String>> getWebData(String browserName) {
	
		if (browserName.equals("Chrome")) {
			driver = newBrowserDriver.getChromeDriver();
		}
		if (driver !=null) {
			newBrowserDriver.setDriverURL(Keysight.getShareURL());
			newBrowserDriver.setZoomLevel();
			WebDataMarketWatchShareQuotation keysightWebData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = keysightWebData.getFinancialWebsiteShareQuote(driver,
					Keysight.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE), 
					Keysight.getShareName());
			newBrowserDriver.quitDriverInstance();
			driver = null;
		}
		return retrievedWebData;
	}
}
