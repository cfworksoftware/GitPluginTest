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

//	String agilentURL = InvestmentWebsitesEnum.AGILENT.getShareURL();
//	String agilentName = InvestmentWebsitesEnum.AGILENT.getShareName();

//	static String agilentURL = "https://www.marketwatch.com/investing/stock/a";
//	static String agilentName = "Agilent";

//	static String stockPriceQuoteCSSSelectorPath = "h2.intraday__price > bg-quote";
//	static String sharePriceQuoteCSSSelectorPath = InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE.getCSSSelector(); 
	/* String[] retrievedStockData = new String[4]; */
	HashMap<String, List<String>> retrievedWebData = new HashMap<String, List<String>>();

	WebDriver driver = null;
	ConfigureSeleniumBrowserDriver newBrowserDriver = new ConfigureSeleniumBrowserDriver("Chrome");

	public HashMap<String, List<String>> /* String[] */ getWebData(String browserName) {

		if (browserName.equals("Chrome")) {
//			newBrowserDriver.getChromeDriver();
//			newBrowserDriver.maximiseWindow();
			driver = newBrowserDriver.getChromeDriver();
			
		}

		if (driver != null) {
			newBrowserDriver.setDriverURL(/* agilentURL */Agilent.getShareURL());

			/*
			 * // Javascript executor to return value JavascriptExecutor j =
			 * (JavascriptExecutor) driver;
			 * j.executeScript("return document.readyState").toString().equals("complete");
			 */
			newBrowserDriver.setZoomLevel();
	//		JavascriptExecutor jse = (JavascriptExecutor)driver;
	//		jse.executeScript("document.body.style.zoom = '70%';");
			/*
			 * System.out.println("page loaded");
			 * WebDataMarketWatchConsentNotification(driver,"div.message-container"
			 * [title=\"SP Consent Message"]","button [title = \"YES, I AGREE\"]");
			 */
			WebDataMarketWatchShareQuotation agilentWebData = new WebDataMarketWatchShareQuotation();
			retrievedWebData = agilentWebData.getFinancialWebsiteShareQuote(driver, /* sharePriceQuoteCSSSelectorPath */
					Agilent.getWebsiteCSSSelector(InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE),
					/* agilentName */Agilent.getShareName());
			newBrowserDriver.quitDriverInstance();
			//driver.quit();
			driver = null;
		}
		return retrievedWebData;
	}

	private void WebDataMarketWatchConsentNotification(WebDriver driver, String frameCSSSelector,
			String btnCSSSelector) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();

		// Store the ID of the original window
		String originalWindow = driver.getWindowHandle();

		// Check we don't have other windows open already
		assert driver.getWindowHandles().size() == 1;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				System.out.println("Switching windows");
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		/*
		 * WebElement iframe = driver.findElement(By.cssSelector(frameCSSSelector));
		 * System.out.println("Button title: "
		 * +iframe.getAttribute("title").toString()); //Switch to the frame
		 * driver.switchTo().frame(iframe);
		 */

		System.out.println(
				"Button title: " + driver.findElement(By.cssSelector("button")).getAttribute("title").toString());
		// Now we can click the button
		driver.findElement(By.cssSelector(btnCSSSelector)).click();
	}

}
