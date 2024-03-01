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

class DatabaseTableFieldNamesEnumTest {

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

	List<String> listDuplicateUsingCollectionsFrequency(List<String> values) {
	    List<String> duplicates = new ArrayList<>();
	    Set<String> set = values.stream()
	    		.filter(i -> Collections.frequency(values, i) > 1)
	    		.collect(Collectors.toSet());
	    duplicates.addAll(set);
	    return duplicates;
	}
	
	@Test
	@DisplayName("Test: GetDatabaseShareFieldNames null or empty values")
	void testNullEmptyValuesGetDatabaseShareFieldNames() {
		List<String> values = new ArrayList<String>();	
		int foundNullEmptyValue=0;
		for(DatabaseTableFieldNamesEnum test: DatabaseTableFieldNamesEnum.values()) {
			values.addAll(test.getDatabaseShareFieldNames().values());
		}
		if (values.contains(null)||values.contains(""))
		{
			foundNullEmptyValue =1;
		}
	    assertEquals(foundNullEmptyValue, 0,"Null or Empty value found");	
	}

	@Test
	@DisplayName("Test: GetDatabaseShareFieldNames duplicate values present")
	void testNoDuplicateValuesGetDatabaseShareFieldNames() {
		List<String> values = new ArrayList<String>();	
		for(DatabaseTableFieldNamesEnum test: DatabaseTableFieldNamesEnum.values()) {
			values.addAll(test.getDatabaseShareFieldNames().values());
		}
	    List<String> duplicates = listDuplicateUsingCollectionsFrequency(values);
	    assertEquals(duplicates.size(), 0,"Duplicate key found");	
	}
	
	@Test
	@DisplayName("Test: GetDatabaseEuroExchangeRateFieldNames null or empty values")
	void testNullEmptyValuesGetDatabaseEuroExchangeRateFieldNames() {
		List<String> values = new ArrayList<String>();	
		int foundNullEmptyValue=0;
		for(DatabaseTableFieldNamesEnum test: DatabaseTableFieldNamesEnum.values()) {
			values.addAll(test.getDatabaseEuroExchangeRateFieldNames().values());
		}
		if (values.contains(null)||values.contains(""))
		{
			foundNullEmptyValue =1;
		}
	    assertEquals(foundNullEmptyValue, 0,"Null or Empty value found");	
	}

	@Test
	@DisplayName("Test: GetDatabaseEuroExchangeRateFieldNames duplicate values present")
	void testNoDuplicateValuesGetDatabaseEuroExchangeRateFieldNames() {
		List<String> values = new ArrayList<String>();	
		for(DatabaseTableFieldNamesEnum test: DatabaseTableFieldNamesEnum.values()) {
			values.addAll(test.getDatabaseEuroExchangeRateFieldNames().values());
		}
	    List<String> duplicates = listDuplicateUsingCollectionsFrequency(values);
	    assertEquals(duplicates.size(), 0,"Duplicate key found");	
	}
	
	@Test
	@DisplayName("Test: GetDatabaseDollarExchangeRateFieldNames null or empty values")
	void testNullEmptyValuesGetDatabaseDollarExchangeRateFieldNames() {
		List<String> values = new ArrayList<String>();	
		int foundNullEmptyValue=0;
		for(DatabaseTableFieldNamesEnum test: DatabaseTableFieldNamesEnum.values()) {
			values.addAll(test.getDatabaseEuroExchangeRateFieldNames().values());
		}
		if (values.contains(null)||values.contains(""))
		{
			foundNullEmptyValue =1;
		}
	    assertEquals(foundNullEmptyValue, 0,"Null or Empty value found");	
	}
	
	@Test
	@DisplayName("Test: GetDatabaseDollarExchangeRateFieldNames duplicate values present")
	void testNoDuplicateValuesGetDatabaseDollarExchangeRateFieldNames() {
		List<String> values = new ArrayList<String>();	
		for(DatabaseTableFieldNamesEnum test: DatabaseTableFieldNamesEnum.values()) {
			values.addAll(test.getDatabaseDollarExchangeRateFieldNames().values());
		}
	    List<String> duplicates = listDuplicateUsingCollectionsFrequency(values);
	    assertEquals(duplicates.size(), 0,"Duplicate key found");	
	}

}
