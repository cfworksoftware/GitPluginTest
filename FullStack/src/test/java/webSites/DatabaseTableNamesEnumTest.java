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

class DatabaseTableNamesEnumTest {

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
	@DisplayName("Test: DatabaseTableNamesEnum no duplicate keys present")
	void testNoDuplicateKey() {
		List<Integer> keys = new ArrayList<Integer>();	
		for(DatabaseTableNamesEnum test: DatabaseTableNamesEnum.values()) {
			keys.add(test.getKey());
//			System.out.println(keys);
		}
	    List<Integer> duplicates = listDuplicateUsingCollectionsFrequency(keys);
//	    System.out.println(duplicates);
	    assertEquals(duplicates.size(), 0,"Duplicate key found");	
	}
		
	@Test
	@DisplayName("Test: DatabaseTableNamesEnum keys not null, not empty")
	void testKeyNotNullNorEmpty() {
		for(DatabaseTableNamesEnum test: DatabaseTableNamesEnum.values()) {
			assertAll("Key not null nor empty",
					()->assertNotEquals(test.getKey(),null, test.name()+ " key is null"),
					()->assertNotEquals(test.getKey(),"",test.name() + "key is empty")
					);
		}		
	}

	@Test
	@DisplayName("Test: DatabaseTableNamesEnum GetDatabaseTableName not null, not empty")
	void testGetDatabaseTableNameNotNullNorEmpty() {
		for(DatabaseTableNamesEnum test: DatabaseTableNamesEnum.values()) {
			assertAll("elementName contains a string",
					()->assertNotEquals(test.getDatabaseTableName(),null, test.name()+ " GetDatabaseTableName is null"),
					()->assertNotEquals(test.getDatabaseTableName(),"",test.name() + "GetDatabaseTableName string is empty")
					);
		}
	}
}
