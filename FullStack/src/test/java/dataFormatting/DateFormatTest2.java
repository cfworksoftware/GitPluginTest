package dataFormatting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateFormatTest2 {

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

	@Test
	void testFormatDate() {
		
		DateFormat test = new DateFormat();
		System.out.println("Date format test: "+test.formatDate("Feb 28, 2024 1:59 p.m."));
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testConvertMonthtoInteger() {
		fail("Not yet implemented"); // TODO
	}

}
