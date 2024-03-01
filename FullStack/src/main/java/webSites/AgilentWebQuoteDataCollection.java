package webSites;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import webDriverSelenium.ConfigureSeleniumBrowserDriver;

public class AgilentWebQuoteDataCollection {

	ShareClass Agilent = new ShareClass(InvestmentWebsitesEnum.AGILENT);
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();
	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver("Chrome");

	public HashMap<String, List<String>> getWebData(String browserName) {

		if (browserName.equals("Chrome")) {
			driver = newBrowserDriver.getChromeDriver();			
		}

		if (driver != null) {
			newBrowserDriver.setDriverURL(Agilent.getShareURL());
			newBrowserDriver.setZoomLevel();
			WebDataMarketWatchShareQuotation agilentWebData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = agilentWebData.getFinancialWebsiteShareQuote(driver, 
					Agilent.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE),
					Agilent.getShareName());
			newBrowserDriver.quitDriverInstance();
			driver = null;
		}
		return retrievedWebData;
	}
}
