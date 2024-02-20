package webSites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AgilentQuoteDataStorageTest.class, InvestmentWebsitesEnumTest.class,
		KeysightQuoteDataStorageTest.class, OrangeQuoteDataStorageTest.class, RLUMQuoteDataStorageTest.class,
		ShareClassTest.class })
public class AllTests {

}
