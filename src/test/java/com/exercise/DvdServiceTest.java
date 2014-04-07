package com.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for dvd service
 */
public class DvdServiceTest {

	/*
	 * Dvd service
	 */
	DvdService dvdService;

	@Before
	public void setUp() throws Exception {
		//Instantiate dvd service implementation
		dvdService = new DvdServiceImpl();
	}

	/**
	 * Test for invalid dvd reference prefix - retrieve dvd
	 */
	@Test (expected=RuntimeException.class)
	public void invalidDvdRetrieveDvdTest() throws DvdNotFoundException {
		dvdService.retrieveDvd("INVALID-TEXT");
	}

	/**
	 * Test for no dvd found - retrieve dvd
	 */
	@Test (expected=DvdNotFoundException.class)
	public void dvdNotFoundRetrieveDvdTest() throws DvdNotFoundException {
		 dvdService.retrieveDvd("DVD-999");
	}

	/**
	 * Test for retrieve dvd
	 */
	@Test
	public void retrieveDvdTest() throws DvdNotFoundException {
		assertEquals("Topgun", dvdService.retrieveDvd("DVD-TG423").getTitle());
	}

	/**
	 * Test for invalid dvd reference prefix - get dvd summary
	 */
	@Test (expected=RuntimeException.class)
	public void invalidGetDvdSummaryTest() throws DvdNotFoundException {
		dvdService.getDvdSummary("INVALID-TEXT");
	}

	/**
	 * Test for no dvd found - get dvd summary
	 */
	@Test (expected=DvdNotFoundException.class)
	public void dvdNotFoundGetDvdSummaryTest() throws DvdNotFoundException {
		dvdService.getDvdSummary("DVD-999");
	}

	/**
	 * Test for get dvd summary
	 */
	@Test
	public void getDvdSummaryTest() throws DvdNotFoundException {
		assertEquals("[DVD-TG423] Topgun - All action film", dvdService.getDvdSummary("DVD-TG423"));
		assertEquals("[DVD-S765] Shrek - Big green monsters, they're just all the rage these days...", dvdService.getDvdSummary("DVD-S765"));
	}

}
