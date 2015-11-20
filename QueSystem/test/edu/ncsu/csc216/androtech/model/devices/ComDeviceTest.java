/**
 * 
 */
package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the different methods included in the
 * ComDevice class and the Device class
 * @author Emily Ring
 *
 */
public class ComDeviceTest {
	private Device com1;

	/**
	 * Setup for Device class testing 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		com1 = new ComDevice("NC123456", "Doe, Jane", 2);
	}
	
	/**
	 * Checks adding devices and errors for adding incorrect devices
	 * @throws BadDeviceInformationException is device is missing information
	 */
	@Test 
	public void testToString() throws BadDeviceInformationException{
		assertEquals("C Gold      NC123456 Doe, Jane", com1.toString());
		ComDevice com2 = new ComDevice("123", "Emily", 0);
		assertEquals("C None      123 Emily", com2.toString());
		ComDevice com3 = new ComDevice("123", "Emily", 1);
		assertEquals("C Silver    123 Emily", com3.toString());
	}
	
	/**
	 * Checks adding devices and errors for adding incorrect devices
	 * @throws BadDeviceInformationException is device is missing information
	 */
	@Test (expected = BadDeviceInformationException.class)
	public void testBadToString() throws BadDeviceInformationException {
		assertEquals("C Gold      NC123456 Doe, Jane", com1.toString());
		
		ComDevice com4 = new ComDevice("  ", "Emily", 3);
		com4.getName();
	}
	
	/**
	 * Checks adding devices and errors for adding incorrect devices
	 * @throws BadDeviceInformationException is device is missing information
	 */
	@Test (expected = BadDeviceInformationException.class)
	public void testBadInformation() throws BadDeviceInformationException{
		assertEquals("C Gold      NC123456 Doe, Jane", com1.toString());
		
		ComDevice com6 = new ComDevice("1234", "", 2);
		com6.getName();
	}
	
	/**
	 * Checks the filter requirements for a device
	 * @throws BadDeviceInformationException is device is missing information
	 */
	@Test
	public void testMeetsFilter() throws BadDeviceInformationException{
		assertTrue(com1.meetsFilter("Doe"));
		ComDevice com2 = new ComDevice("123", "Emily", 0);
		assertFalse(com2.meetsFilter("Doe"));
		assertTrue(com2.meetsFilter(""));
	}
	
	/**
	 * checks for correct names on devices
	 */
	@Test 
	public void testGetName() {
		assertEquals("Doe, Jane", com1.getName());
	}
	
	/**
	 * Checks for correct serial number on devices
	 */
	@Test
	public void testGetSerialNum(){
		assertEquals("NC123456", com1.getSerialNum());
	}
	
	/**
	 * Checks for correct tiet level on devices
	 */
	@Test
	public void testGetTier(){
		assertEquals(2, com1.getTier());
	}
	
	/**
	 * Checks to see if 2 devices have the same tier
	 * @throws BadDeviceInformationException is device is missing information
	 */
	@Test
	public void testCompareToTier() throws BadDeviceInformationException{
		Prioritizable nu = com1;
		int num = com1.compareToTier(nu);
		assertEquals(0, num);
		
		Prioritizable com2 = new ComDevice("123", "Emily", 0);
		num = com1.compareToTier(com2);
		assertEquals(2, num);
		
		Prioritizable com3 = new ComDevice("123", "Emily", 3);
		num = com1.compareToTier(com3);
		assertEquals(-2, num);
	}
}
