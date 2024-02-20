package webSites;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShareClassTest {

	ShareClass testAgilentShareClass;
	ShareClass testKeysightShareClass;
	ShareClass testOrangeSAShareClass;
	ShareClass testRLUMShareClass;
	ArrayList<ShareClass> testCases;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testAgilentShareClass = new ShareClass(InvestmentWebsitesEnum.AGILENT/*,InvestmentWebsiteCSSSelectorEnum.SHAREPRICEQUOTE*/);
		testKeysightShareClass = new ShareClass(InvestmentWebsitesEnum.KEYSIGHT);
		testOrangeSAShareClass = new ShareClass(InvestmentWebsitesEnum.ORANGESA);
		testRLUMShareClass = new ShareClass(InvestmentWebsitesEnum.RLUM);
		testCases = new ArrayList<ShareClass>();
		testCases.add(testAgilentShareClass);
		testCases.add(testKeysightShareClass);
		testCases.add(testOrangeSAShareClass);
		testCases.add(testRLUMShareClass);
	}

	@AfterEach
	void tearDown() throws Exception {
		testAgilentShareClass = null;
		testKeysightShareClass = null;
		testOrangeSAShareClass = null;
		testRLUMShareClass = null;
	}

	@Test
	void testShareClass() {
		
		for (int i = 0; i < testCases.size(); i++) {
			assertNotNull(testCases.get(i).getCurrency(), "Currency Null: "  + testCases.get(i).getClass().getSimpleName());
			assertNotNull(testCases.get(i).getShareName(), "ShareName Null: "  + testCases.get(i).getClass().getSimpleName());
			assertNotNull(testCases.get(i).getShareURL(), "ShareURL Null: "  + testCases.get(i).getClass().getSimpleName());
		}
		
		Iterator<ShareClass> it = testCases.iterator();
		while (it.hasNext())
		{
			ShareClass test = it.next();
			assertNotNull(test.getCurrency(),"Currency Null"  + testCases);
			assertNotNull(test.getShareName(),"ShareName Null" + test.getClass().getName());
			assertNotNull(test.getShareURL(),"URL Null" + test.getClass().getName());
		}
		
		for(InvestmentWebsitesEnum test: InvestmentWebsitesEnum.values()) {
		assertNotNull(test.getKey());
		assertNotNull(test.getShareName());
		assertNotNull(test.getShareURL());
		assertNotNull(test.getCurrency());
		}
	}

	@Test
	void testGetCurrency() {
		
		assertEquals(testAgilentShareClass.getCurrency(),InvestmentWebsitesEnum.AGILENT.getCurrency());
		assertEquals(testKeysightShareClass.getCurrency(),InvestmentWebsitesEnum.KEYSIGHT.getCurrency());
		assertEquals(testOrangeSAShareClass.getCurrency(),InvestmentWebsitesEnum.ORANGESA.getCurrency());
		assertEquals(testRLUMShareClass.getCurrency(),InvestmentWebsitesEnum.RLUM.getCurrency());		
	}

	@Test
	void testGetShareName() {
		assertEquals(testAgilentShareClass.getShareName(),InvestmentWebsitesEnum.AGILENT.getShareName());
		assertEquals(testKeysightShareClass.getShareName(),InvestmentWebsitesEnum.KEYSIGHT.getShareName());
		assertEquals(testOrangeSAShareClass.getShareName(),InvestmentWebsitesEnum.ORANGESA.getShareName());
		assertEquals(testRLUMShareClass.getShareName(),InvestmentWebsitesEnum.RLUM.getShareName());	
	}

	@Test
	void testGetShareURL() {
		assertEquals(testAgilentShareClass.getShareURL(),InvestmentWebsitesEnum.AGILENT.getShareURL());
		assertEquals(testKeysightShareClass.getShareURL(),InvestmentWebsitesEnum.KEYSIGHT.getShareURL());
		assertEquals(testOrangeSAShareClass.getShareURL(),InvestmentWebsitesEnum.ORANGESA.getShareURL());
		assertEquals(testRLUMShareClass.getShareURL(),InvestmentWebsitesEnum.RLUM.getShareURL());	
	}

}
