package webSites;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InvestmentWebsiteCSSSelectorEnumTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	List<Integer> listDuplicateUsingCollectionsFrequency(List<Integer> list) {
	    List<Integer> duplicates = new ArrayList<>();
	    Set<Integer> set = list.stream()
	    		.filter(i -> Collections.frequency(list, i) > 1)
	    		.collect(Collectors.toSet());
	    duplicates.addAll(set);
	    return duplicates;
	}

	@Test
	@DisplayName("Test: InvestmentWebsiteCSSSelectorEnum no duplicate keys present")
	void testNoDuplicateKey() {
		List<Integer> keys = new ArrayList<Integer>();	
		for(InvestmentWebsiteCSSSelectorEnum test: InvestmentWebsiteCSSSelectorEnum.values()) {
			keys.add(test.getKey());
//			System.out.println(keys);
		}
	    List<Integer> duplicates = listDuplicateUsingCollectionsFrequency(keys);
//	    System.out.println(duplicates);
	    assertEquals(duplicates.size(), 0,"Duplicate key found");	
	}

	@Test
	@DisplayName("Test: InvestmentWebsiteCSSSelectorEnum keys not null, not empty")
	void testKeyNotNullNorEmpty() {
		for(InvestmentWebsiteCSSSelectorEnum test: InvestmentWebsiteCSSSelectorEnum.values()) {
//			assertNotEquals (test.getKey(),null, test.name()+ " key is null");			
			assertAll("Key not null nor empty",
					()->assertNotEquals(test.getKey(),null, test.name()+ " key is null"),
					()->assertNotEquals(test.getKey(),"",test.name() + "key is empty")
					);
		}		
	}
	
	@Test
	@DisplayName("Test: InvestmentWebsiteCSSSelectorEnum elementName not null, not empty")
	void testElementNameNotNullNorEmpty() {
		for(InvestmentWebsiteCSSSelectorEnum test: InvestmentWebsiteCSSSelectorEnum.values()) {
			assertAll("elementName contains a string",
					()->assertNotEquals(test.getElementName(),null, test.name()+ " elementName is null"),
					()->assertNotEquals(test.getElementName(),"",test.name() + "elementName string is empty")
					);
		}
	}

	@Test
	@DisplayName("Test: InvestmentWebsiteCSSSelectorEnum CSSSelector not null, not empty")
	void testCSSSelectorNotNullNorEmpty() {
		for(InvestmentWebsiteCSSSelectorEnum test: InvestmentWebsiteCSSSelectorEnum.values()) {
			assertAll("CSSSelector contains a string",
					()->assertNotEquals(test.getCSSSelector(),null, test.getCSSSelector()+ "CSSSelector is null"),
					()->assertNotEquals(test.getCSSSelector(),"",test.getCSSSelector()+ "CSSSelector is empty")
					);
		}
	}	
}
