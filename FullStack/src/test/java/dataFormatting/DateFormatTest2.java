package dataFormatting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.constraints.AssertTrue;

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
	void testFormatDateCommaSeperated() {	
		DateFormat testDate = new DateFormat();
		Assert.assertTrue("Date format test: "+testDate.formatDateCommaSeperated("Feb 28, 2024 1:59 p.m."),testDate.formatDateCommaSeperated("Feb 28, 2024 1:59 p.m.").equals("2024-02-28"));
	}
	
	@Test
	void testFormatDateTwoDigitMonth() {
		
		DateFormat testDate = new DateFormat();
		Assert.assertTrue("28/02/2021 transformed to " + testDate.formatDateTwoDigitMonth("28/02/2021")+";",testDate.formatDateTwoDigitMonth("28/02/2021").equals("2021-02-28"));
	}

	@Test
	void testConvertMonthtoInteger() {
		DateFormat testDate = new DateFormat();
		Assert.assertTrue("Jan not working",testDate.convertMonthtoInteger("Jan")=="01");
		Assert.assertTrue("Feb not working",testDate.convertMonthtoInteger("Feb")=="02");
		Assert.assertTrue("Mar not working",testDate.convertMonthtoInteger("Mar")=="03");
		Assert.assertTrue("Apr not working",testDate.convertMonthtoInteger("Apr")=="04");
		Assert.assertTrue("Mai not working",testDate.convertMonthtoInteger("Mai")=="05");
		Assert.assertTrue("Jun not working",testDate.convertMonthtoInteger("Jun")=="06");
		Assert.assertTrue("Jul not working",testDate.convertMonthtoInteger("Jul")=="07");
		Assert.assertTrue("Aug not working",testDate.convertMonthtoInteger("Aug")=="08");
		Assert.assertTrue("Sep not working",testDate.convertMonthtoInteger("Sep")=="09");
		Assert.assertTrue("Oct not working",testDate.convertMonthtoInteger("Oct")=="10");
		Assert.assertTrue("Nov not working",testDate.convertMonthtoInteger("Nov")=="11");
		Assert.assertTrue("Dec not working",testDate.convertMonthtoInteger("Dec")=="12");
		
		Assert.assertTrue("Default case not working",testDate.convertMonthtoInteger("Jup")=="00");

	}

}
